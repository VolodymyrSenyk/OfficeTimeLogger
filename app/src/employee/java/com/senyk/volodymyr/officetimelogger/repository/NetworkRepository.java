package com.senyk.volodymyr.officetimelogger.repository;

import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senyk.volodymyr.officetimelogger.exceptions.LogExistsException;
import com.senyk.volodymyr.officetimelogger.exceptions.NoSuchUserException;
import com.senyk.volodymyr.officetimelogger.exceptions.PasswordsMismatchException;
import com.senyk.volodymyr.officetimelogger.mappers.responsedto.TimeLogsResponseDtoMapper;
import com.senyk.volodymyr.officetimelogger.models.dto.CredentialsDto;
import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.response.LogResponse;
import com.senyk.volodymyr.officetimelogger.models.response.ServerError;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.Single;

public class NetworkRepository implements TimeLoggerRepository {
    private final Gson jsonConverter;
    private int userId;
    private final TimeLogsResponseDtoMapper mapper;

    private static final String BASE_URL = "https://androidapptimetable.000webhostapp.com/";

    private static NetworkRepository repository;

    private NetworkRepository() {
        this.jsonConverter = new GsonBuilder().create();
        this.mapper = new TimeLogsResponseDtoMapper();
    }

    public static NetworkRepository getFakeRepository() {
        if (repository == null) repository = new NetworkRepository();
        return repository;
    }

    @Override
    public Completable logIn(CredentialsDto creds) {
        this.userId = creds.getUserNumber();
        return Completable.fromCallable((Callable<Boolean>) () -> {
            String params = "personal_number=" + creds.getUserNumber() + "&password=" + creds.getPassword();
            String response = makeRequest("check_emloyee.php", params);
            if (response.contains("error")) {
                ServerError error = jsonConverter.fromJson(response, ServerError.class);
                if (error.getError() == 1) throw new NoSuchUserException();
                if (error.getError() == 2) throw new PasswordsMismatchException();
                return false;
            } else {
                return true;
            }
        });
    }

    @Override
    public Completable logTime(TimeLogDto log) {
        return Completable.fromCallable((Callable<Boolean>) () -> {
            String params = "personal_number=" + userId + "&arrival_time=" + log.getArrivalTime() / 1000 + "&leaving_time=" + log.getLeavingTime() / 1000;
            String response = makeRequest("add_log.php", params);
            if (response.contains("error")) {
                ServerError error = jsonConverter.fromJson(response, ServerError.class);
                if (error.getError() == 3) throw new LogExistsException();
                return false;
            } else {
                return true;
            }
        });
    }

    @Override
    public Single<List<TimeLogDto>> deleteLog(int logId) {
        return Single.fromCallable(() -> {
            String params = "personal_number=" + userId + "&id=" + logId;
            String response = makeRequest("delete_log.php", params);
            LogResponse logResponse = jsonConverter.fromJson(response, LogResponse.class);
            return mapper.convertToDto(logResponse.getLogs());
        });
    }

    @Override
    public Single<List<TimeLogDto>> getTimeLogs() {
        return Single.fromCallable(() -> {
            String params = "personal_number=" + userId;
            String response = makeRequest("get_logs_by_number.php", params);
            LogResponse logResponse = jsonConverter.fromJson(response, LogResponse.class);
            return mapper.convertToDto(logResponse.getLogs());
        });
    }

    @Override
    public Single<List<TimeLogDto>> getTimeLogs(long start, long end) {
        return Single.fromCallable(() -> {
            String params = "personal_number=" + userId + "&period_start=" + start / 1000 + "&period_end=" + end / 1000;
            String response = makeRequest("get_logs_in_period.php", params);
            LogResponse logResponse = jsonConverter.fromJson(response, LogResponse.class);
            return mapper.convertToDto(logResponse.getLogs());
        });
    }

    @Override
    public Completable changePassword(Pair<String, String> passwords) {
        return Completable.fromCallable((Callable<Boolean>) () -> {
            String params = "old_password=" + passwords.first + "&new_password=" + passwords.second;
            String response = makeRequest("change_password.php", params);
            if (response.contains("error")) {
                ServerError error = jsonConverter.fromJson(response, ServerError.class);
                if (error.getError() == 2) throw new PasswordsMismatchException();
                return false;
            } else {
                return true;
            }
        });
    }

    private String makeRequest(String requestUrl, String params) {
        byte[] data;
        InputStream is = null;
        String response = "";
        try {
            URL url = new URL(BASE_URL + requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Content-Length", Integer.toString(params.getBytes().length));
            OutputStream os = conn.getOutputStream();
            data = params.getBytes("UTF-8");
            os.write(data);

            conn.connect();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            is = conn.getInputStream();

            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            response = new String(baos.toByteArray());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }
}

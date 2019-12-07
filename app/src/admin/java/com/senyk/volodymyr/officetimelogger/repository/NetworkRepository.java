package com.senyk.volodymyr.officetimelogger.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senyk.volodymyr.officetimelogger.mappers.responsedto.UsersResponseDtoMapper;
import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;
import com.senyk.volodymyr.officetimelogger.models.response.AllEmployees;

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
    private final UsersResponseDtoMapper usersMapper;

    private static final String BASE_URL = "https://androidapptimetable.000webhostapp.com/";
    private static NetworkRepository repository;

    private NetworkRepository() {
        this.jsonConverter = new GsonBuilder().create();
        this.usersMapper = new UsersResponseDtoMapper();
    }

    public static NetworkRepository getFakeRepository() {
        if (repository == null) repository = new NetworkRepository();
        return repository;
    }

    @Override
    public Completable addUser(UserDto user) {
        return Completable.fromCallable((Callable<Boolean>) () -> {
            String params = "name=" + user.getFirstName() + "&surname=" + user.getLastName() + "&second_name=" + user.getMiddleName();
            String response = makeRequest("create_employee.php", params);
            return response.isEmpty();
        });
    }

    @Override
    public Completable resetPassword(int userId) {
        return Completable.complete();
    }

    @Override
    public Single<List<UserDto>> getUsers() {
        return Single.fromCallable(() -> {
            String response = makeRequest("get_employees.php", " ");
            AllEmployees employeesList = jsonConverter.fromJson(response, AllEmployees.class);
            return employeesList.getEmployees();
        }).map(this.usersMapper::convertToDtoList);
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

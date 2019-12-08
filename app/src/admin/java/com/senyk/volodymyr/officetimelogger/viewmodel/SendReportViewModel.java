package com.senyk.volodymyr.officetimelogger.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Pair;

import androidx.core.content.FileProvider;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.senyk.volodymyr.officetimelogger.BuildConfig;
import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.helpers.SingleEventLiveData;
import com.senyk.volodymyr.officetimelogger.models.ui.TimeLogUi;
import com.senyk.volodymyr.officetimelogger.models.ui.UserUi;

import java.io.File;
import java.util.Calendar;
import java.util.List;

import io.github.lucasfsc.html2pdf.Html2Pdf;

public class SendReportViewModel extends ViewModel {
    private SingleEventLiveData<String> message = new SingleEventLiveData<>();

    public LiveData<String> getMessage() {
        return this.message;
    }

    public void sendReportDay(Context context, String address, String subject, String message, List<Pair<UserUi, TimeLogUi>> data) {
        String formattedReport = generateReportDay(data);
        File reportFile = saveFile(context, formattedReport);
        send(context, address, subject, message, reportFile);
    }

    public void sendReportUser(Context context, String address, String subject, String message, List<TimeLogUi> data) {
        String formattedReport = generateReportUser(data);
        File reportFile = saveFile(context, formattedReport);
        send(context, address, subject, message, reportFile);
    }

    private void send(Context context, String address, String subject, String message, File file) {
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{address});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);

        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
        emailIntent.setType("application/pdf");
        context.startActivity(Intent.createChooser(emailIntent, context.getString(R.string.send_email_chooser_title)));
    }

    private File saveFile(Context context, String report) {
        String fileName = generateReportFileNameByDate(context);
        File file = new File(context.getFilesDir(), fileName);
        Html2Pdf converter = new Html2Pdf.Companion.Builder()
                .context(context)
                .html(report)
                .file(file)
                .build();
        converter.convertToPdf();
        return file;
    }

    private String generateReportFileNameByDate(Context context) {
        Calendar date = Calendar.getInstance();
        return context.getString(
                R.string.report_filename,
                date.get(Calendar.DAY_OF_MONTH),
                (date.get(Calendar.MONTH) + 1),
                date.get(Calendar.YEAR),
                date.get(Calendar.HOUR_OF_DAY),
                date.get(Calendar.MINUTE)
        );
    }

    private String generateReportUser(List<TimeLogUi> data) {
        StringBuilder result = new StringBuilder();
        result.append("<html>");
        result.append("<head>");
        result.append("<style>");
        result.append("table " +
                "{ " +
                "border-collapse: collapse; " +
                "}");
        result.append("th, td " +
                "{ " +
                "border: 1px solid black; " +
                "font-weight:bold; " +
                "}");
        result.append("</style>");
        result.append("</head>");
        result.append("<body>");
        result.append("<table>");
        result.append("<tr><th>Дата</th><th>Время прихода</th><th>Время ухода</th><th>Общее время</th></tr>");
        for (TimeLogUi log : data) {
            result.append("<tr>");
            result.append("<td>").append(log.getDay()).append("</td>");
            result.append("<td>").append(log.getArrivalTime()).append("</td>");
            result.append("<td>").append(log.getLeavingTime()).append("</td>");
            result.append("<td>").append(log.getTotalTime()).append("</td>");
            result.append("</tr>");
        }
        result.append("</table>");
        result.append("</body>");
        result.append("</html>");
        return new String(result);
    }

    private String generateReportDay(List<Pair<UserUi, TimeLogUi>> data) {
        StringBuilder result = new StringBuilder();
        result.append("<html>");
        result.append("<head>");
        result.append("<style>");
        result.append("table " +
                "{ " +
                "border-collapse: collapse; " +
                "}");
        result.append("th, td " +
                "{ " +
                "border: 1px solid black; " +
                "font-weight:bold; " +
                "}");
        result.append("</style>");
        result.append("</head>");
        result.append("<body>");
        result.append("<table>");
        result.append("<tr><th>Табельный номер</th><th>ФИО</th><th>Дата</th><th>Время прихода</th><th>Время ухода</th><th>Общее время</th></tr>");
        for (Pair<UserUi, TimeLogUi> pair : data) {
            result.append("<tr>");
            result.append("<td>").append(pair.first.getId()).append("</td>");
            result.append("<td>").append(pair.first.getFirstName()).append(" ").append(pair.first.getMiddleName()).append(" ").append(pair.first.getLastName()).append("</td>");
            result.append("<td>").append(pair.second.getDay()).append("</td>");
            result.append("<td>").append(pair.second.getArrivalTime()).append("</td>");
            result.append("<td>").append(pair.second.getLeavingTime()).append("</td>");
            result.append("<td>").append(pair.second.getTotalTime()).append("</td>");
            result.append("</tr>");
        }
        result.append("</table>");
        result.append("</body>");
        result.append("</html>");
        return new String(result);
    }
}

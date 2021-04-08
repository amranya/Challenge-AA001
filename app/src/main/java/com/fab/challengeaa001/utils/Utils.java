package com.fab.challengeaa001.utils;


import android.util.Log;

import com.fab.challengeaa001.CurrencyExchangeApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class Utils {

    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";

    public static String getFormattedCurrentDate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public static String getFormatted2MonthsAgoDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -2);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    public static String readFileFromAssets(String fileName) {

        StringBuilder builder = new StringBuilder();
        InputStream is;

        try {
            is = CurrencyExchangeApplication.getAppContext().getAssets().open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
            }

            bufferedReader.close();

        } catch (IOException e) {
            Log.d("FakeResponseError", e.getMessage(), e);
        }

        return builder.toString();
    }

}

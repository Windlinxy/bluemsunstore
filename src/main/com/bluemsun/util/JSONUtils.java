package com.bluemsun.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class JSONUtils {
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static String getJson(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder;
        String str;
        String jsonString;

        //读取前端传送的json文件存进stringBuilder（单线程安全）
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));

        stringBuilder = new StringBuilder();
        while ((str = reader.readLine()) != null) {
            stringBuilder.append(str);
        }
        reader.close();
        jsonString = stringBuilder.toString();
        return jsonString;
    }

    //json转对象
    public static <T> T fromJson(String jsonString, Class<T> cls) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            return null;
        }
    }


    public static void parseJson(Object obj, HttpServletResponse response) {
        PrintWriter out;
        String jsonString;
        try {
            out = response.getWriter();
            jsonString = toJson(obj);
            out.println(jsonString);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

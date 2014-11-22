package com.apj.ite.wkb.utils;

import android.content.Context;
import android.util.Log;

import com.apj.ite.wkb.entity.Entity_aa;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by student on 2014/11/22.
 */
public class DataUtils {

    public static List<Entity_aa> loadDate(Context context){
        String jsonString = "";
        try{
            InputStream inputStream = context.getAssets().open("home_json");
            jsonString = inputStream2String(inputStream);
        }catch (Exception ex){
            Log.e("DataUtils","loadDate",ex);
        }
        Gson gson = new Gson();
        List<Entity_aa> data = gson.fromJson(jsonString, new TypeToken<List<Entity_aa>>(){}.getType());
        try{
            JSONObject jsonObject = new JSONObject(jsonString);
        }catch (Exception ex){

        }
        return data;
    }

    public static String inputStream2String(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i=-1;
        while((i=is.read())!=-1){
            baos.write(i);
        }
        return baos.toString();
    }
}



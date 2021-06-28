package com.example.skidos_b;

import android.os.AsyncTask;

import com.example.skidos_b.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class showData extends AsyncTask<Void,Void,Void> {
    String data = "", dataParsed = "", singleParsed = "";

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String path = "/storage/emulated/0/Download/interest.json";
            File file = new File(path);
            InputStream inputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String Line = "";
            while (Line != null)
            {
                Line = bufferedReader.readLine();
                data += Line;
            }
            JSONArray JA = new JSONArray(data);
            for (int i = 0;i<JA.length();i++)
            {
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed =  "Name:" + JO.get("Name") + "\n"+
                        "Picture:" + JO.get("PictureUrl") + "\n"+
                        "DisplayName:" + JO.get("DisplayName") + "\n"+
                        "Language:" + JO.get("Language") + "\n"+
                        "InterestID:" + JO.get("InterestID") + "\n";

                dataParsed += singleParsed + "\n";
            }
        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        MainActivity.datax.setText(this.dataParsed);
    }
}
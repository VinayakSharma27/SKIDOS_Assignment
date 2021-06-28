package com.example.skidos_b;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    Button fbtn,sbtn;
    String url = "https://testinterest.s3.amazonaws.com/interest.json";
    String path = "/storage/emulated/0/Download/interest.json";
    public static TextView datax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbtn = findViewById(R.id.fbtn);
        sbtn = findViewById(R.id.sbtn);
        datax = findViewById(R.id.fetcheddata);

        File file = new File(path);
        if(file.exists())
        {
            fbtn.setVisibility(View.INVISIBLE);
        }

        fbtn.setOnClickListener(v -> {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            String title = URLUtil.guessFileName(url, null,null);
            request.setTitle(title);
            request.setDescription("Saving JSON file locally, Please wait...");
            String cookie = CookieManager.getInstance().getCookie(url);
            request.addRequestHeader("cookie",cookie);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);
            DownloadManager downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
            downloadManager.enqueue(request);
            Toast.makeText(MainActivity.this, "Saving file loacally...", Toast.LENGTH_SHORT).show();
            fbtn.setVisibility(View.INVISIBLE);
        });

        sbtn.setOnClickListener(v1 -> {
            showData process = new showData();
            process.execute();
        });
    }
}
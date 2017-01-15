package com.example.derp.galgeleg102;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity{
    Galgelogik logik;
    TextView textView;
    String str = "";
    SharedPreferences sharedPreferences;
    public static final String PREFERENCES = "key" ;
    private int i = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        logik = new Galgelogik();
        sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
       final SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString(PREFERENCES, "");
        if(sharedPreferences.contains(PREFERENCES)){
            String savedWords = sharedPreferences.getString(PREFERENCES, null);
            List<String> list = Arrays.asList(TextUtils.split(savedWords, ","));
            logik.setMuligeOrd(list);
            i = 500;


        }
        else {
            AsyncTaskURL task = new AsyncTaskURL();
            task.execute();
        }

        final ListView listView = (ListView) findViewById(R.id.listView);
        final ArrayAdapter adapter = new ArrayAdapter<>(this,R.layout.words_layout, logik.getMuligeOrd());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                listView.setAdapter(adapter);
                editor.putString(PREFERENCES, TextUtils.join(",", logik.getMuligeOrd()));
                editor.apply();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        Object o = listView.getItemAtPosition(position);
                        str=(String)o;
                        Intent intent = new Intent(MainActivity.this, GalgelegSpilActivity.class);
                        intent.putExtra("word", str);
                        startActivity(intent);




                    }
                });

            }

        }, i);

    }


    public class AsyncTaskURL extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                logik.hentOrdFraDr();
                }
          catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

        }
package com.example.clientnotificator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private PrestatgeAdabter pa;
    public APICheck ack;
    private FloatingActionButton fab;
    public String url;
    private RequestQueue queue;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab = findViewById(R.id.floatingActionButton);

        rv = findViewById(R.id.idRv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        pa = new PrestatgeAdabter();
        rv.setAdapter(pa);
        url = "http://10.0.2.2:3000/data";
        queue = Volley.newRequestQueue(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                ConfiguracionFragment editNameDialogFragment = ConfiguracionFragment.newInstance("Configuracion");
                editNameDialogFragment.show(fm, "fragment_edit_ip");
            }
        });
        loadData();
    }

    public void stop() {
        ack.interrupt();
    }

    private String readFromFile(Context context, String txt) {
        String ret = "";
        try {
            InputStream inputStream = context.openFileInput(txt);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }

    public void loadData() {
        String file = readFromFile(this, "config.txt");
        url = file;
        ack = new APICheck(pa, queue, url);
        if (!ack.isAlive()) {
            ack.start();
        }
        pa.updateData(new ArrayList<PrestatgeView>());
        pa.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        //rv.addView();
        super.onStart();
    }
}
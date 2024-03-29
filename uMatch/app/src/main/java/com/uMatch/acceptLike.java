package com.uMatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

// Implementasi CRUD Create

public class acceptLike extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_like);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url2 = "https://umatchumn.000webhostapp.com/insertmatch.php";
        //RequestQueue queue2 = Volley.newRequestQueue(view.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //Toast.makeText(getApplicationContext(), "sampe sini", Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString("message");
                    if(status.equals("success")){
                        //Toast.makeText(getApplicationContext(), "sampe sukses", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
                    }
                    else
                    //Toast.makeText(getApplicationContext(), "sampe eror", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    //textViewerror.setVisibility(View.VISIBLE);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                //textViewerror.setVisibility(View.VISIBLE);
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                Intent i = getIntent();
                String userNIMsatu = i.getStringExtra("userNIMsatu");
                String userNIMdua = i.getStringExtra("userNIMdua");
                String status = i.getStringExtra("status");
                paramV.put("userNIMsatu", userNIMsatu);
                paramV.put("userNIMdua", userNIMdua);
                paramV.put("status", status);
                return paramV;
            }
        };
        queue.add(stringRequest);

        Button btnGoChat = (Button) findViewById(R.id.btnGoChat);
        Button btnGoHome = (Button) findViewById(R.id.btnGoHome);

        btnGoChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ListViewChat.class);
                startActivity(i);
            }
        });

        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NavBar.class);
                startActivity(i);
            }
        });
    }
}
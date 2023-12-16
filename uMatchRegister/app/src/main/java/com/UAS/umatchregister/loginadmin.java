package com.UAS.umatchregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class loginadmin extends AppCompatActivity {

    EditText editTextemail, editTextpassword;
    Button buttonsubmit;
    String namaAdmin, emailAdmin, password, apiKey;
    TextView textViewerror;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginadmin);
        editTextemail = findViewById(R.id.email);
        editTextpassword = findViewById(R.id.password);
        buttonsubmit = findViewById(R.id.submit);
        textViewerror = findViewById(R.id.error);
        progressBar = findViewById(R.id.progressBar);
        sharedPreferences = getSharedPreferences("MyAppName", MODE_PRIVATE);


        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewerror.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                emailAdmin = String.valueOf(editTextemail.getText());
                password = String.valueOf(editTextpassword.getText());

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://umatchumn.000webhostapp.com/loginadmin.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String status = jsonObject.getString("status");
                                    String message = jsonObject.getString("message");
                                    if(status.equals("success")){
                                        namaAdmin = jsonObject.getString("namaAdmin");
                                        emailAdmin = jsonObject.getString("emailAdmin");
                                        apiKey = jsonObject.getString("apiKey");
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString("logged", "true");
                                        editor.putString("namaAdmin", namaAdmin);
                                        editor.putString("emailAdmin", emailAdmin);
                                        editor.putString("apiKey", apiKey);
                                        editor.apply();
                                        Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        textViewerror.setText(message);
                                        textViewerror.setVisibility(View.VISIBLE);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBar.setVisibility(View.GONE);
                        textViewerror.setText(error.getLocalizedMessage());
                        textViewerror.setVisibility(View.VISIBLE);
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("emailAdmin", emailAdmin);
                        paramV.put("password", password);
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });

    }
}

package com.UAS.umatchregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    Button btnsubmit, btnback;
    EditText editTextname, editTextemail, editTextpassword, editTextnim, editTextjurusan, editTextgender, editTextphone;
    String name, email, password, nim, jurusan,gender,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextname = findViewById(R.id.name);
        editTextemail = findViewById(R.id.email);
        editTextpassword = findViewById(R.id.password);
        editTextnim = findViewById(R.id.nim);
        editTextjurusan = findViewById(R.id.jurusan);
        editTextgender = findViewById(R.id.gender);
        editTextphone = findViewById(R.id.phone);
        btnsubmit = findViewById(R.id.btnsubmit);
        btnback = findViewById(R.id.btnback);


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = String.valueOf(editTextname.getText());
                email = String.valueOf(editTextemail.getText());
                password = String.valueOf(editTextpassword.getText());
                nim = String.valueOf(editTextnim.getText());
                jurusan = String.valueOf(editTextjurusan.getText());
                phone = String.valueOf(editTextphone.getText());
                gender = String.valueOf(editTextgender.getText());
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="https://umatchumn.000webhostapp.com/register.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success")){
                                    Toast.makeText(getApplicationContext(),"regristations successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("name", name);
                        paramV.put("email", email);
                        paramV.put("password", password);
                        paramV.put("nim", nim);
                        paramV.put("jurusan", jurusan);
                        paramV.put("phone", phone);
                        paramV.put("gender", gender);

                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
//
//
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
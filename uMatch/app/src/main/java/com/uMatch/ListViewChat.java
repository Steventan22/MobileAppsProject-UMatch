package com.uMatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListViewChat extends AppCompatActivity {

    Button btnChat, btnChat2, btnChat3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_chat);

        btnChat = (Button) findViewById(R.id.btnChat);
        btnChat2 = (Button) findViewById(R.id.btnChat2);
        btnChat3 = (Button) findViewById(R.id.btnChat3);



        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra("Name","Leonardo");
                startActivity(intent);

            }
        });

        btnChat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra("Name","Arya");
                startActivity(intent);

            }
        });

        btnChat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra("Name","Steven");
                startActivity(intent);

            }
        });



    }
}
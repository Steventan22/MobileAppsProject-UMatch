package com.uMatch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChatActivity extends AppCompatActivity {

    private ImageButton btnSend, buttonback;
    private EditText edtContent;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        iniKomponen();
        imageView = findViewById(R.id.profilePicture);
        buttonback = findViewById(R.id.buttonback);
        TextView nama = (TextView) findViewById(R.id.receiver_name);
        Intent i = getIntent();
        nama.setText(i.getStringExtra("Name").toString());

        if(nama.getText().toString().equals("Leonardo")) {
            imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_profile1));
        }else if (nama.getText().toString().equals("Arya")){
            imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_profile2));
        }else if (nama.getText().toString().equals("Steven")){
            imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_profile3));
        }

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NavBar.class);
                startActivity(intent);
                finish();
            }
        });
    }

    RecyclerView recyclerView;
    AdapterChat adapterChat;

    private void iniKomponen() {
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setHasFixedSize(true);

        adapterChat = new AdapterChat(this);
        recyclerView.setAdapter(adapterChat);

        btnSend = findViewById(R.id.btn_send);
        edtContent = findViewById(R.id.text_content);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendChat();
            }
        });

        (findViewById(R.id.layoutback)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistableBundle) {
        super.onPostCreate(savedInstanceState, persistableBundle);
        hideKeyboard();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void sendChat(){
        final String chat = edtContent.getText().toString();
        if (chat.isEmpty()) return;
        adapterChat.insertItem(new ChatItems(adapterChat.getItemCount(), Tools.getTime(System.currentTimeMillis()), chat, true, adapterChat.getItemCount() % 5 == 0));
        edtContent.setText("");
        recyclerView.scrollToPosition(adapterChat.getItemCount() - 1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapterChat.insertItem(new ChatItems(adapterChat.getItemCount(), Tools.getTime(System.currentTimeMillis()), chat, false, adapterChat.getItemCount() % 5 == 0));
                recyclerView.scrollToPosition(adapterChat.getItemCount() - 1);
            }
        }, 1000);
    }
}

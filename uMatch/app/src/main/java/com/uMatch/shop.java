package com.uMatch;

import static android.graphics.Color.argb;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;



public class shop extends Fragment {
    public shop() {
        // require a empty public constructor
    }

    RadioButton selection;
    Color ungumuda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_shop, container, false);
        selection = view.findViewById(R.id.rbQ1);
        Button btnPurchase = (Button) view.findViewById(R.id.btnPurchase);
        btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSelected = selection.isChecked();

                if(isSelected == true){
                    Intent i = new Intent(getContext().getApplicationContext(), paymentConfirmation.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getContext().getApplicationContext(), "Please select a bundle!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

}
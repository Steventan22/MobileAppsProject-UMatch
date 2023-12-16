package com.uMatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

//Implementasi Viewpager

public class MainOnboarding extends AppCompatActivity {

    public ContainerOnboarding containerOnboarding;
    public LinearLayout indicatorDots;
    public Button btnNext, btnSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_onboarding);
        indicatorDots = findViewById(R.id.indicatorDots);
        btnSkip = findViewById(R.id.btnSkip);
        btnNext = findViewById(R.id.btnNext);
        onboardingSetup();
        ViewPager2 viewOnboarding = findViewById(R.id.viewOnboarding);
        viewOnboarding.setAdapter(containerOnboarding);
        indicatorSetup();
        indicatorON(0);

        viewOnboarding.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                indicatorON(position);
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LogIn.class));
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewOnboarding.getCurrentItem() + 1 < containerOnboarding.getItemCount()) {
                    viewOnboarding.setCurrentItem(viewOnboarding.getCurrentItem() + 1);
                }else {
                    startActivity(new Intent(getApplicationContext(), LogIn.class));
                    finish();
                }
            }
        });

    }

    private void onboardingSetup(){
        List <ItemOnboarding> itemOnboardings = new ArrayList<>();
        ItemOnboarding firstOnboarding = new ItemOnboarding();
        firstOnboarding.setTitle("Ingin Menemukan Hubungan Baru?!");
        firstOnboarding.setDesc("Bosan Hidup Sendirian? uMatch Hadir Untuk Membantu Anda Menemukan Pasangan Baru!");
        firstOnboarding.setImage(R.drawable.ob_secondimage);

        ItemOnboarding secondOnboarding = new ItemOnboarding();
        secondOnboarding.setTitle("Sedang Patah Hati?");
        secondOnboarding.setDesc("Galau Memikiran Pasangan Lama Yang Tidak Memikirkan Anda Lagi? Temukan Pasangan Anda Sekarang!");
        secondOnboarding.setImage(R.drawable.ob_firstimage);

        ItemOnboarding thirdOnboarding = new ItemOnboarding();
        thirdOnboarding.setTitle("Ciptakan Hubungan Baru!");
        thirdOnboarding.setDesc("Mulailah saling berbagi rasa demi membangun perasaan untuk mencintai");
        thirdOnboarding.setImage(R.drawable.ob_thirdimage);

        itemOnboardings.add(firstOnboarding);
        itemOnboardings.add(secondOnboarding);
        itemOnboardings.add(thirdOnboarding);

        containerOnboarding = new ContainerOnboarding(itemOnboardings);
    }

    public void indicatorSetup(){
        ImageView[] dots = new ImageView[containerOnboarding.getItemCount()];
        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        lP.setMargins(10,0,10,0);
        for(int i = 0; i < dots.length; i++){
            dots[i] = new ImageView(getApplicationContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_off));
            dots[i].setLayoutParams(lP);
            indicatorDots.addView(dots[i]);
        }
    }

    public void indicatorON(int indicator){
        int dotsCount = indicatorDots.getChildCount();
        for(int i = 0; i < dotsCount; i++){
            ImageView image = (ImageView) indicatorDots.getChildAt(i);
            if (i == indicator) {
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_on));
            }else{
                image.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.indicator_off));
            }
        }
        if (indicator == containerOnboarding.getItemCount() - 1) {
            btnNext.setText("Start");
        } else {
            btnNext.setText("Next");
        }
    }
}
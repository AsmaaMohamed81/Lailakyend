package com.example.m.laylak.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.m.laylak.R;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class ContactActivity extends AppCompatActivity {
    Shimmer shimmer;
    ShimmerTextView offer_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        offer_txt=findViewById(R.id.shimmer);
        shimmer =new Shimmer();
        shimmer.setStartDelay(500);
        shimmer.setDuration(500);


        shimmer.start(offer_txt);

    }
}

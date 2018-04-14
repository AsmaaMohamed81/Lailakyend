package com.Alatheer.Projects.laylaky.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.Alatheer.Projects.laylaky.R;

public class DisplayGalleryActivity extends AppCompatActivity {

    private RecyclerView recView;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_gallery);
    }
}

package com.Alatheer.Projects.lailaky.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.Alatheer.Projects.lailaky.Fragments.Fragment_Cover_Shape1;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Cover_Shape2;
import com.Alatheer.Projects.lailaky.R;

import java.util.List;

public class DesiredCoverActivity extends AppCompatActivity {
    private int pos,album_size;
    private String user_id,id_offer,paper_id;
    private String from = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desired_cover);
        getDataFromIntent();
    }

    private void getDataFromIntent() {

        Intent intent = getIntent();

        if (intent!=null)
        {
            if (intent.hasExtra("from"))
            {
                from = intent.getStringExtra("from");

            }else
                {
                    from = "0";
                }
            pos = intent.getIntExtra("pos",0);
            user_id = intent.getStringExtra("user_id");
            album_size = intent.getIntExtra("album_size",0);
            id_offer = intent.getStringExtra("id_offer");
            paper_id = intent.getStringExtra("paper_id");

            UpdateUI(pos,user_id,album_size,id_offer,paper_id);
        }

    }

    private void UpdateUI(int pos, String user_id, int album_size, String id_offer, String paper_id) {

        switch (pos)
        {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_desired_cover_container, Fragment_Cover_Shape1.getInstance(user_id,id_offer,paper_id,album_size)).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_desired_cover_container, Fragment_Cover_Shape2.getInstance(user_id,id_offer,paper_id,album_size)).commit();

                break;

        }
    }


    public void Finish()
    {
        if (from.equals("0"))
        {
            finish();
        }else if (from.equals("1"))
        {
            Intent intent  = getIntent();
            setResult(RESULT_OK,intent);
            finish();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for (Fragment fragment:fragmentList)
        {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for (Fragment fragment:fragmentList)
        {
            fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}

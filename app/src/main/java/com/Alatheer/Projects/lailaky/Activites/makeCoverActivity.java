package com.Alatheer.Projects.lailaky.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.Alatheer.Projects.lailaky.ApiServices.Tags;
import com.Alatheer.Projects.lailaky.Fragments.Fragment_Cover;
import com.Alatheer.Projects.lailaky.R;

import java.util.List;

public class makeCoverActivity extends AppCompatActivity {

    FrameLayout frame;
    private String user_id = "", offer_id = "", paper_id = "";
    private int album_size;
    private  String from="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_cover);

        getDataFromIntent();
        initView();


    }

    private void initView() {
        getSupportFragmentManager().beginTransaction().add(R.id.frame, Fragment_Cover.getInstance(user_id, offer_id, paper_id, album_size)).commit();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {

            if (intent.hasExtra("from"))
            {
                from = "1";
            }else
                {
                    from = "0";
                }

            album_size = Integer.parseInt(intent.getStringExtra("album_size"));
            user_id = intent.getStringExtra("user_id");
            offer_id = intent.getStringExtra("id_offer");
            paper_id = intent.getStringExtra("paper_id");




            Log.e("twopage", album_size + "");
            Log.e("twopage", user_id + "");
            Log.e("twopage", offer_id + "");

        }
    }

    @Override
    public void onBackPressed() {


        finish();
    }


    public void NavigateToAnotherActivity(int pos)
    {

        if (from.equals("0"))
        {
            Intent intent = new Intent(this, DesiredCoverActivity.class);
            intent.putExtra("pos",pos);
            intent.putExtra("type", Tags.cover);
            intent.putExtra("album_size",album_size);
            intent.putExtra("user_id",user_id);
            intent.putExtra("id_offer",offer_id);
            intent.putExtra("paper_id",paper_id);

            intent.putExtra("from","0");

            startActivity(intent);
        }else
            {
                Intent intent = new Intent(this, DesiredCoverActivity.class);
                intent.putExtra("pos",pos);
                intent.putExtra("type", Tags.cover);
                intent.putExtra("album_size",album_size);
                intent.putExtra("user_id",user_id);
                intent.putExtra("id_offer",offer_id);
                intent.putExtra("paper_id",paper_id);

                intent.putExtra("from","1");

                startActivityForResult(intent,123);
            }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for (Fragment fragment :fragmentList)
        {
            fragment.onActivityResult(requestCode, resultCode, data);
        }


        if (requestCode == 123 && resultCode == RESULT_OK && data!=null)
        {
            Intent intent = getIntent();
            setResult(RESULT_OK,intent);
            finish();
        }

    }
}

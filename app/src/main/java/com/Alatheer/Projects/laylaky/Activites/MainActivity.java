package com.Alatheer.Projects.laylaky.Activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.Alatheer.Projects.laylaky.ApiServices.Preferences;
import com.Alatheer.Projects.laylaky.Models.UserModel;
import com.Alatheer.Projects.laylaky.R;
import com.Alatheer.Projects.laylaky.SingleTone.Users;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener , Users.onCompleteListener{

    private ImageButton albumgeded,albumaty;
    private Preferences preferences;
    private String user_id;
    AlertDialog.Builder builder;
    private Users users;
    private TextView name,email;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private String user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        getDataFromIntent();
        CreateAlertDialog();



    }
    private void CreateAlertDialog() {
        builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.do_logout));
        builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LogOut();
            }
        });
        builder.setNegativeButton((getString(R.string.no)), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                AlertDialog dialog = builder.create();
                dialog.dismiss();
            }
        });
        builder.create();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer =  findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView =  findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        preferences = new Preferences(this);
        albumgeded= findViewById(R.id.albumgeded);
        albumaty  = findViewById(R.id.albumaty);
        View view = navigationView.getHeaderView(0);
        name=view.findViewById(R.id.txt_username);
        email=view.findViewById(R.id.txt_email);
        users = Users.getInstance();
        users.getData(this);
        albumgeded.setOnClickListener(this);
        albumaty.setOnClickListener(this);


    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if (intent!=null)
        {
            user_type = intent.getStringExtra("user_type");
            Log.e("getDataFromIntent: ",user_type);

            if (intent.hasExtra("user_id"))
            {
                user_id = intent.getStringExtra("user_id");

                Log.e("getDataFromIntent: ",user_id);

            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

      if (id == R.id.nav_profile) {

            Intent i = new Intent(MainActivity.this,ProfileActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_contact) {
            Intent i = new Intent(MainActivity.this,ContactActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_about) {

            Intent i = new Intent(MainActivity.this,AboutUsActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_share) {

          String text ="استوديو ليلاكي";
          String link ="https://play.google.com/store/apps/details?id=com.Alatheer.Projects.laylaky";

          Intent intent=new Intent(Intent.ACTION_SEND);
          intent.putExtra(Intent.EXTRA_TEXT,text+"\n"+link);
          intent.setType("text/plain");
          startActivity(intent);




        } else if (id == R.id.nav_logout) {

            builder.show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void LogOut() {
        preferences.clear();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.albumgeded:

                Intent i = new Intent(MainActivity.this,OfferAlbum.class);
                i.putExtra("user_type",user_type);
                startActivity(i);

                break;
            case R.id.albumaty :

                Intent i2 = new Intent(MainActivity.this, Albumaty.class);

                startActivity(i2);
                break;


        }
    }

    @Override
    public void OnDataSuccess(UserModel userModel) {
        UpdateUi(userModel);
    }

    private void UpdateUi(UserModel userModel) {
        name.setText(userModel.getUser_name());
        email.setText(userModel.getUser_email());
    }
}

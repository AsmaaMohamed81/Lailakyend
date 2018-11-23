package com.Alatheer.Projects.lailaky.Activites;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.Alatheer.Projects.lailaky.ApiServices.Preferences;
import com.Alatheer.Projects.lailaky.Models.UserModel;
import com.Alatheer.Projects.lailaky.R;
import com.Alatheer.Projects.lailaky.SingleTone.Users;
import com.Alatheer.Projects.lailaky.share.Common;

import java.util.Locale;

import io.paperdb.Paper;




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
    String lang;
    private UserModel userModel;


    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);

        super.attachBaseContext(LanguageHelper.onAttach(newBase,Paper.book().read("language",Locale.getDefault().getLanguage())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Paper.init(this);
        LanguageHelper.onAttach(this, (String)Paper.book().read("language"));



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

        users = Users.getInstance();
        userModel = users.getUserModel();


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

        String lang = Paper.book().read("language");
        if (lang==null)
        {
            Paper.book().write("language","ar");
        }
        updateLayout((String)Paper.book().read("language"));


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

          if (userModel!=null)
          {
              Intent i = new Intent(MainActivity.this,ProfileActivity.class);
              startActivity(i);
          }else
          {

              android.support.v7.app.AlertDialog alertDialog = Common.CreateUserNotSignInAlertDialog(MainActivity.this);
              alertDialog.show();
              navigationView.getMenu().getItem(1).setChecked(false);

              navigationView.getMenu().getItem(0).setChecked(true);

          }



        }
        else if (id == R.id.complement) {

            if (userModel!=null)
            {
                Intent i = new Intent(MainActivity.this,Confirm_SignUPActivity.class);
                i.putExtra("user_id",user_id);
                startActivity(i);
            }else
            {

                android.support.v7.app.AlertDialog alertDialog = Common.CreateUserNotSignInAlertDialog(MainActivity.this);
                alertDialog.show();
                navigationView.getMenu().getItem(1).setChecked(false);

                navigationView.getMenu().getItem(0).setChecked(true);

            }



        }
         else if (id == R.id.lang) {

          drawer.closeDrawer(GravityCompat.START);

          CreateLanguageDialog();


      }else if (id == R.id.nav_contact) {
            Intent i = new Intent(MainActivity.this,ContactActivity.class);
            startActivity(i);


        } else if (id == R.id.nav_about) {

            Intent i = new Intent(MainActivity.this,AboutUsActivity.class);
            startActivity(i);


        }
      else if (id == R.id.nav_help) {

          Intent i = new Intent(MainActivity.this,HelpActivity.class);
          startActivity(i);


      }else if (id == R.id.nav_share) {

          String text ="استوديو ليلاكي";
          String link ="https://play.google.com/store/apps/details?id=com.Alatheer.Projects.laylaky";

          Intent intent=new Intent(Intent.ACTION_SEND);
          intent.putExtra(Intent.EXTRA_TEXT,text+"\n"+link);
          intent.setType("text/plain");
          startActivity(intent);




        } else if (id == R.id.nav_logout) {


          if (userModel==null)
          {
              Intent intent=new Intent(MainActivity.this,LoginActivity.class);
              startActivity(intent  );

          }else
          {
              builder.show();
          }



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


                if (userModel!=null)
                {
                    Intent i2 = new Intent(MainActivity.this, Albumaty.class);

                    startActivity(i2);
                }else
                {

                    android.support.v7.app.AlertDialog alertDialog = Common.CreateUserNotSignInAlertDialog(MainActivity.this);
                    alertDialog.show();
                    navigationView.getMenu().getItem(1).setChecked(false);

                    navigationView.getMenu().getItem(0).setChecked(true);

                }



                break;


        }
    }

    @Override
    public void OnDataSuccess(UserModel userModel) {
        UpdateUi(userModel);
    }

    private void UpdateUi(UserModel userModel) {
        try {
            name.setText(userModel.getUser_name());
            email.setText(userModel.getUser_email());
        }catch (NullPointerException e){}

    }

    private void CreateLanguageDialog()
    {

        View view = LayoutInflater.from(this).inflate(R.layout.custom_lang_alert_dialog,null);

        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(this);
        final android.app.AlertDialog alertDialog = dialog.create();

        final RadioButton rb_ar = view.findViewById(R.id.rb_ar);
        final RadioButton rb_en = view.findViewById(R.id.rb_en);
        final FrameLayout fl_ar = view.findViewById(R.id.fl_ar);
        final FrameLayout fl_en = view.findViewById(R.id.fl_en);

        lang= Paper.book().read("language");
        if (lang.equals("en"))
        {
            rb_en.setChecked(true);
            fl_ar.setVisibility(View.INVISIBLE);
            fl_en.setVisibility(View.VISIBLE);

        }else if (lang.equals("ar"))
        {
            rb_ar.setChecked(true);
            fl_ar.setVisibility(View.VISIBLE);
            fl_en.setVisibility(View.INVISIBLE);
        }
        rb_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb_ar.isChecked())
                {
                    fl_ar.setVisibility(View.VISIBLE);
                    fl_en.setVisibility(View.INVISIBLE);
                    alertDialog.dismiss();
                    Paper.book().write("language","ar");
                    updateLayout((String)Paper.book().read("language"));
                    RefreshLayout();
                }



            }
        });

        rb_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb_en.isChecked())
                {
                    fl_ar.setVisibility(View.INVISIBLE);
                    fl_en.setVisibility(View.VISIBLE);
                    alertDialog.dismiss();
                    Paper.book().write("language","en");

                    updateLayout((String)Paper.book().read("language"));
                    RefreshLayout();

                }




            }
        });

        alertDialog.setCancelable(true);
        alertDialog.setView(view);
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.CustomAnimations_slide; //style id

        alertDialog.show();

    }

    private void updateLayout(String language)
    {
        LanguageHelper.setLocality(language,this);

    }
    private void RefreshLayout()
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}

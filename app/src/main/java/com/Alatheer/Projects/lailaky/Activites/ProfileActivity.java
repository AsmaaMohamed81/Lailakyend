package com.Alatheer.Projects.lailaky.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Preferences;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Models.UserModel;
import com.Alatheer.Projects.lailaky.R;
import com.Alatheer.Projects.lailaky.SingleTone.Users;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity implements Users.onCompleteListener{

    private EditText user_name,user_email,user_phone;
    private Button updateBtn;
    private Users users;
    private Toolbar toolBar;
    private UserModel userModel;
    private String u_name,u_email,u_phone;
    private ProgressDialog dialog;
    private ImageView done;
    private Preferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        users = Users.getInstance();
        users.getData(this);
        preferences = new Preferences(this);

    }

    private void initView() {
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

        user_name = findViewById(R.id.user_name);
        user_email= findViewById(R.id.email);
        user_phone= findViewById(R.id.phone);
        updateBtn = findViewById(R.id.updateBtn);
        done = findViewById(R.id.done);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetUpUI();
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateProfile();
            }
        });


        ProgressBar progressBar = new ProgressBar(this);
        Drawable drawable = progressBar.getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        dialog = new ProgressDialog(this);
        dialog.setMessage("update profile...");
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminateDrawable(drawable);

    }

    private void UpdateProfile() {
        u_name = user_name.getText().toString();
        u_email = user_email.getText().toString();
        u_phone = user_phone.getText().toString();

        if (TextUtils.isEmpty(u_name))
        {
            Toast.makeText(this, "Enter user name", Toast.LENGTH_LONG).show();
        }else if (TextUtils.isEmpty(u_email))
        {
            Toast.makeText(this, "Enter email", Toast.LENGTH_LONG).show();

        }else if (!Patterns.EMAIL_ADDRESS.matcher(u_email).matches())
        {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_LONG).show();

        }else if (TextUtils.isEmpty(u_phone))
        {
            Toast.makeText(this, "Enter Phone number", Toast.LENGTH_LONG).show();

        }
        else if (!Patterns.PHONE.matcher(u_phone).matches())
        {
            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_LONG).show();

        }else
        {
            if (u_name.equals(userModel.getUser_name()) &&u_email.equals(userModel.getUser_email()) && u_phone.equals(userModel.getUser_phone()))
            {
                Toast.makeText(this, "No changes occur", Toast.LENGTH_LONG).show();
            }else
            {
                dialog.show();
                Map <String,String> map = new HashMap<>();
                map.put("user_name",u_name);
                map.put("user_email",u_email);
                map.put("user_phone",u_phone);
                Retrofit retrofit = Api.getClient();
                Services services = retrofit.create(Services.class);
                Call<UserModel> call = services.UpdateProfile(userModel.getUser_id(), map);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                        if (response.isSuccessful())
                        {
                            UserModel userModel = response.body();

                            if (userModel.getSuccess()==1)
                            {
                                UpdateUi(userModel);
                                dialog.dismiss();
                                users.setUserData(userModel);

                                preferences.UpdatePref(userModel);
                                user_name.setEnabled(false);
                                user_email.setEnabled(false);
                                user_phone.setEnabled(false);

                                u_name=userModel.getUser_name();
                                u_phone=userModel.getUser_phone();
                                u_email=userModel.getUser_email();

                                done.setVisibility(View.INVISIBLE);
                                updateBtn.setVisibility(View.VISIBLE);
                                Toast.makeText(ProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();

                            }else
                            {
                                dialog.dismiss();

                                Toast.makeText(ProfileActivity.this, "Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        dialog.dismiss();

                        Toast.makeText(ProfileActivity.this, "Error Something went haywire", Toast.LENGTH_SHORT).show();
                        Log.e("Error",t.getMessage());
                    }
                });
            }
        }

    }

    private void SetUpUI() {
        updateBtn.setVisibility(View.INVISIBLE);
        user_name.setEnabled(true);
        user_email.setEnabled(true);
        user_phone.setEnabled(true);
        done.setVisibility(View.VISIBLE);



    }

    @Override
    public void OnDataSuccess(UserModel userModel) {
        this.userModel = userModel;
        UpdateUi(userModel);
    }

    private void UpdateUi(UserModel userModel) {
        user_name.setText(userModel.getUser_name());
        user_email.setText(userModel.getUser_email());
        user_phone.setText(userModel.getUser_phone());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.pass)
        {

            Intent intent = new Intent(ProfileActivity.this,UpdatePasswordActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

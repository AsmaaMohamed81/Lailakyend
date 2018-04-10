package com.Alatheer.Projects.laylaky.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Alatheer.Projects.laylaky.ApiServices.Api;
import com.Alatheer.Projects.laylaky.ApiServices.Preferences;
import com.Alatheer.Projects.laylaky.ApiServices.Services;
import com.Alatheer.Projects.laylaky.Models.UserModel;
import com.Alatheer.Projects.laylaky.R;
import com.Alatheer.Projects.laylaky.SingleTone.Users;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private EditText userName,password;
    private TextView register,skip;
    private Button login;
    private ProgressDialog dialog;
    private Preferences preferences;
    Users users;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        users = Users.getInstance();
        preferences = new Preferences(this);
        SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
        if (pref!=null)
        {
                    String session = pref.getString("session","");
            if (session.equals("login"))
            {
                    String user_id = pref.getString("id","");
                if (!TextUtils.isEmpty(user_id)||user_id!=null)
                {
                    String user_name =pref.getString("user_name","");
                    String user_email=pref.getString("email","");
                    String user_phone=pref.getString("phone","");
                    String user_pass =pref.getString("pass","");
                    UserModel userModel = new UserModel();
                    userModel.setUser_id(user_id);
                    userModel.setUser_name(user_name);
                    userModel.setUser_email(user_email);
                    userModel.setUser_phone(user_phone);
                    userModel.setUser_pass(user_pass);
                    users.setUserData(userModel);
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();                }
            }
        }



    }

    private void initView() {


        login=findViewById(R.id.login);
        register = findViewById(R.id.register);
        userName = findViewById(R.id.user_name);
        password = findViewById(R.id.pass);
        skip     = findViewById(R.id.skip);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,UserActivity.class);
                intent.putExtra("user_type","visitor");
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(LoginActivity.this,AlbumsActivity.class);
                startActivity(intent);*/
                SignIn();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,Signup.class);
                startActivity(intent);
            }
        });
        ProgressBar progressBar = new ProgressBar(this);
        Drawable drawable = progressBar.getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Login...");
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminateDrawable(drawable);

    }

    private void SignIn() {
        String uname = userName.getText().toString();
        String upass = password.getText().toString();

        if (TextUtils.isEmpty(uname))
        {
            userName.setError("Enter user name");
        }else if (TextUtils.isEmpty(upass))
        {
            userName.setError(null);
            password.setError("Enter password");
        }else
            {
                password.setError(null);
                dialog.show();
                Map<String,String> map = new HashMap<>();
                map.put("user_name",uname);
                map.put("user_pass",upass);
                Retrofit retrofit = Api.getClient();
                Services services = retrofit.create(Services.class);
                Call<UserModel> call = services.Login(map);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if (response.isSuccessful())
                        {
                            if (response.body().getSuccess()==1)
                            {
                                preferences.CreatePref(response.body());

                                users.setUserData(response.body());
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                dialog.dismiss();
                                startActivity(intent);
                                finish();
                            }else
                                {
                                    dialog.dismiss();

                                    Toast.makeText(LoginActivity.this, "Check username or password", Toast.LENGTH_LONG).show();
                                }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {

                        Log.e("error",t.getMessage());
                        dialog.dismiss();
                        Toast.makeText(LoginActivity.this, "error some thing went haywire", Toast.LENGTH_LONG).show();
                    }
                });
            }
    }
}

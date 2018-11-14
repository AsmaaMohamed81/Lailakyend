package com.Alatheer.Projects.lailaky.Activites;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Preferences;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Models.UserModel;
import com.Alatheer.Projects.lailaky.R;
import com.Alatheer.Projects.lailaky.SingleTone.Users;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private EditText userName,password;
    private TextView register,skip,forgetPass;
    private Button login;
    private ProgressDialog dialog;
    private Preferences preferences;
    Users users;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(LanguageHelper.onAttach(newBase, Paper.book().read("language", Locale.getDefault().getLanguage())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Paper.init(this);


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
                    intent.putExtra("user_type","client");
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
        forgetPass=findViewById(R.id.forgetPass);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                intent.putExtra("user_type","visitor");
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);*/
               HideKeyBoard();
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

        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,confirmEmail_ForgetPass.class);
                startActivity(intent);
            }
        });


        ProgressBar progressBar = new ProgressBar(this);
        Drawable drawable = progressBar.getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.login_));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminateDrawable(drawable);

    }

    private void HideKeyBoard() {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(userName.getWindowToken(),0);

    }

    private void SignIn() {
        String uname = userName.getText().toString();
        String upass = password.getText().toString();

        if (TextUtils.isEmpty(uname))
        {
            userName.setError(getString(R.string.enter_user_name));
        }else if (TextUtils.isEmpty(upass))
        {
            userName.setError(null);
            password.setError(getString(R.string.enter_pass));
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
                                intent.putExtra("user_type","client");
                                dialog.dismiss();
                                startActivity(intent);
                                finish();
                            }else
                                {
                                    dialog.dismiss();

                                    Toast.makeText(LoginActivity.this, R.string.chk_user_pas, Toast.LENGTH_LONG).show();
                                }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {

                        Log.e("error",t.getMessage());
                        dialog.dismiss();
                        Toast.makeText(LoginActivity.this, getString(R.string.something), Toast.LENGTH_LONG).show();
                    }
                });
            }
    }

    private void RefreshLayout()
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}

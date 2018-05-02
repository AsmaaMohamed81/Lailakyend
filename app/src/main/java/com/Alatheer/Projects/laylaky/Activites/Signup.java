package com.Alatheer.Projects.laylaky.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Alatheer.Projects.laylaky.ApiServices.Preferences;
import com.Alatheer.Projects.laylaky.ApiServices.Services;
import com.Alatheer.Projects.laylaky.Models.UserModel;
import com.Alatheer.Projects.laylaky.R;
import com.Alatheer.Projects.laylaky.ApiServices.Api;
import com.Alatheer.Projects.laylaky.SingleTone.Users;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Signup extends AppCompatActivity {

    private EditText userName,password,email,phone;
    private Button signUp;
    private ProgressDialog dialog;
    private Preferences preferences;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        users = Users.getInstance();
        initView();
        preferences = new Preferences(this);

    }

    private void initView() {

        userName = findViewById(R.id.user_name);
        password = findViewById(R.id.pass);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        signUp = findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signup();
            }
        });

        ProgressBar progressBar = new ProgressBar(this);
        Drawable drawable = progressBar.getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.regis));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminateDrawable(drawable);



    }

    private void Signup() {
        String uname = userName.getText().toString();
        String upass = password.getText().toString();
        String uemail= email.getText().toString();
        String uphone= phone.getText().toString();

        if (TextUtils.isEmpty(uname))
        {
            userName.setError(getString(R.string.enter_user_name));
        }else if (TextUtils.isEmpty(upass))
        {
            userName.setError(null);
            password.setError(getString(R.string.enter_pass));
        }else if (TextUtils.isEmpty(uemail))
        {
            password.setError(null);
            email.setError(getString(R.string.enter_email));
        }else if (!Patterns.EMAIL_ADDRESS.matcher(uemail).matches())
        {
            password.setError(null);
            email.setError(getString(R.string.inv_email));

        }else if (TextUtils.isEmpty(uphone))
        {
            email.setError(null);
            phone.setError(getString(R.string.enter_phone));
        }else if (!Patterns.PHONE.matcher(uphone).matches())
        {
            phone.setError(getString(R.string.inv_phone));

        }else
            {
                phone.setError(null);
                dialog.show();
                Map <String,String> map = new HashMap<>();

                map.put("user_name",uname);
                map.put("user_pass",upass);
                map.put("user_email",uemail);
                map.put("user_phone",uphone);
                map.put("token_id","");

                Retrofit retrofit = Api.getClient();
                Services services = retrofit.create(Services.class);
                Call<UserModel> call = services.Register(map);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if (response.isSuccessful())
                        {
                            if (response.body().getSuccess()==1)
                            {
                                users.setUserData(response.body());
                                preferences.CreatePref(response.body());
                                Intent intent = new Intent(Signup.this,MainActivity.class);
                                intent.putExtra("user_id",response.body().getUser_id());
                                dialog.dismiss();
                                startActivity(intent);
                                finish();
                            }else if (response.body().getSuccess()==0)
                                {

                                    dialog.dismiss();
                                    Toast.makeText(Signup.this, R.string.error, Toast.LENGTH_SHORT).show();

                                }else if (response.body().getSuccess()==2)
                                {
                                    dialog.dismiss();

                                    Toast.makeText(Signup.this, R.string.register_befor, Toast.LENGTH_SHORT).show();

                                }
                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        Log.e("register error",t.getMessage());
                        Toast.makeText(Signup.this, getString(R.string.something), Toast.LENGTH_SHORT).show();
                    }
                });
            }



    }
}

package com.example.m.laylak.Activites;

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

import com.example.m.laylak.ApiServices.Services;
import com.example.m.laylak.Models.ResponsModel;
import com.example.m.laylak.R;
import com.example.m.laylak.ApiServices.Api;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        initView();
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
        dialog.setMessage("Wait for register...");
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
            userName.setError("Enter uer name");
        }else if (TextUtils.isEmpty(upass))
        {
            userName.setError(null);
            password.setError("Enter password");
        }else if (TextUtils.isEmpty(uemail))
        {
            password.setError(null);
            email.setError("Enter email address");
        }else if (!Patterns.EMAIL_ADDRESS.matcher(uemail).matches())
        {
            password.setError(null);
            email.setError("Invalid email address");

        }else if (TextUtils.isEmpty(uphone))
        {
            email.setError(null);

            phone.setError("Enter phone number");
        }else if (!Patterns.PHONE.matcher(uphone).matches())
        {
            phone.setError("Invalid phone number");

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
                Call<ResponsModel> call = services.Register(map);
                call.enqueue(new Callback<ResponsModel>() {
                    @Override
                    public void onResponse(Call<ResponsModel> call, Response<ResponsModel> response) {
                        if (response.isSuccessful())
                        {
                            if (response.body().getSuccess()==1)
                            {
                                Intent intent = new Intent(Signup.this,AlbumsActivity.class);
                                intent.putExtra("user_id",response.body().getUser_id());
                                dialog.dismiss();
                                startActivity(intent);
                                finish();
                            }else
                                {
                                    Toast.makeText(Signup.this, "Error", Toast.LENGTH_SHORT).show();

                                }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsModel> call, Throwable t) {
                        Log.e("register error",t.getMessage());
                        Toast.makeText(Signup.this, "Error some thing went haywire", Toast.LENGTH_SHORT).show();
                    }
                });
            }



    }
}

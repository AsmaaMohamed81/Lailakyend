package com.Alatheer.Projects.lailaky.Activites;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.ApiServices.Preferences;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Models.UserModel;
import com.Alatheer.Projects.lailaky.R;
import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.SingleTone.Users;
import com.lamudi.phonefield.PhoneInputLayout;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Signup extends AppCompatActivity {

    private EditText userName,password,email,phone;
    private PhoneInputLayout edt_phone_check;
    private Button signUp;
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
        setContentView(R.layout.signup);

        users = Users.getInstance();
        initView();
        preferences = new Preferences(this);

    }

    private void initView() {

        userName = findViewById(R.id.user_name);
        password = findViewById(R.id.pass);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.edt_phone);
        edt_phone_check = findViewById(R.id.edt_phone_check);
        signUp = findViewById(R.id.signUp);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HideKeyBoard();
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

    private void Signup()
    {
        String uname = userName.getText().toString();
        String upass = password.getText().toString();
        String uemail= email.getText().toString();
        String uphone= phone.getText().toString();
        edt_phone_check.setPhoneNumber(uphone);
        if (TextUtils.isEmpty(uname))
        {
            userName.setError(getString(R.string.enter_user_name));
        }else if (TextUtils.isEmpty(upass))
        {
            userName.setError(null);
            password.setError(getString(R.string.enter_pass));
        }else if (TextUtils.isEmpty(uemail))
        {
            userName.setError(null);
            password.setError(null);
            email.setError(getString(R.string.enter_email));
        }else if (!Patterns.EMAIL_ADDRESS.matcher(uemail).matches())
        {
            userName.setError(null);
            userName.setError(null);
            password.setError(null);
            email.setError(getString(R.string.inv_email));

        }else if (TextUtils.isEmpty(uphone))
        {
            userName.setError(null);
            userName.setError(null);
            password.setError(null);
            email.setError(null);
            phone.setError(getString(R.string.enter_phone));
        }else if (edt_phone_check.isValid())
        {
            userName.setError(null);
            userName.setError(null);
            password.setError(null);
            email.setError(null);
            phone.setError(getString(R.string.inv_phone));

        }else
            {
                userName.setError(null);
                userName.setError(null);
                password.setError(null);
                email.setError(null);
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
    private void HideKeyBoard()
    {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(userName.getWindowToken(),0);

    }
}

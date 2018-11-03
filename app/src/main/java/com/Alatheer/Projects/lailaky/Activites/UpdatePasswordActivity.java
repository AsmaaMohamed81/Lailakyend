package com.Alatheer.Projects.lailaky.Activites;

import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Models.ResponseModel;
import com.Alatheer.Projects.lailaky.Models.UserModel;
import com.Alatheer.Projects.lailaky.R;
import com.Alatheer.Projects.lailaky.SingleTone.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdatePasswordActivity extends AppCompatActivity implements Users.onCompleteListener{
    private EditText pass,re_pass;
    private Button confirm,cancel;
    private Toolbar toolbar;
    private ProgressDialog dialog;
    private UserModel userModel;
    private Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        initView();
        users = Users.getInstance();
        users.getData(this);
    }

    private void initView() {

        ProgressBar progressBar = new ProgressBar(this);
        Drawable drawable = progressBar.getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(this,R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.upd_pass));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminateDrawable(drawable);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        pass = findViewById(R.id.pass);
        re_pass = findViewById(R.id.re_pass);
        confirm = findViewById(R.id.confirm);
        cancel  = findViewById(R.id.cancel);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdatePassword();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    private void UpdatePassword() {
        String txtpass = pass.getText().toString();
        String txt_re_pass = re_pass.getText().toString();

        if (TextUtils.isEmpty(txtpass))
        {
            pass.setError(getString(R.string.enter_new_password));
        }else if (TextUtils.isEmpty(txt_re_pass))
        {
            pass.setError(null);
            re_pass.setError(getString(R.string.re_type_password));

        }
        else if (!txtpass.equals(txt_re_pass))
        {
            re_pass.setText(null);
            Toast.makeText(this, R.string.pass_not_match, Toast.LENGTH_SHORT).show();

        }
        else
            {

                dialog.show();
                Retrofit retrofit = Api.getClient();
                Services services = retrofit.create(Services.class);
                Call<ResponseModel> call = services.UpdatePassword(userModel.getUser_id(),txtpass);
                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        if (response.isSuccessful())
                        {
                            if (response.body().getSuccess()==1)
                            {
                                dialog.dismiss();
                                Toast.makeText(UpdatePasswordActivity.this, R.string.upd_succ, Toast.LENGTH_SHORT).show();
                                finish();

                            }else
                                {
                                    dialog.dismiss();

                                    Toast.makeText(UpdatePasswordActivity.this, R.string.fialed, Toast.LENGTH_SHORT).show();
                                }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        dialog.dismiss();
                        Toast.makeText(UpdatePasswordActivity.this, getString(R.string.something), Toast.LENGTH_LONG).show();
                        Log.e("error",t.getMessage());
                    }
                });
            }
    }

    @Override
    public void OnDataSuccess(UserModel userModel) {
        this.userModel = userModel;
    }
}

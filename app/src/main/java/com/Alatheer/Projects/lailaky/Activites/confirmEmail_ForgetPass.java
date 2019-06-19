package com.Alatheer.Projects.lailaky.Activites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Models.UserModel;
import com.Alatheer.Projects.lailaky.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class confirmEmail_ForgetPass extends AppCompatActivity {
    private EditText mail;
    private Button confirm;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_email__forget_pass);

        initView();
    }

    private void initView() {

        mail = findViewById(R.id.mail);
        confirm = findViewById(R.id.comfirm);


        ProgressBar progressBar = new ProgressBar(this);
        final Drawable drawable = progressBar.getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.sendmail));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setIndeterminateDrawable(drawable);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show();

//                Intent intent=new Intent(confirmEmail_ForgetPass.this,Signup.class);
//                intent.putExtra("mail",mail.getText().toString());
//                startActivity(intent);
                Log.e("forget", mail.getText().toString());

                Retrofit retrofit = Api.getClient();
                Services services = retrofit.create(Services.class);
                Call<UserModel> call = services.forgetPassword(mail.getText().toString());
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {



                        if (response.isSuccessful())
                        {

                            dialog.dismiss();

                            if (response.body().getSuccess()==1)
                            {

                                dialog.dismiss();
                                Toast.makeText(confirmEmail_ForgetPass.this, "افحص ايميلك ", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(confirmEmail_ForgetPass.this, LoginActivity.class);
                                intent.putExtra("mail", mail.getText().toString());
                                startActivity(intent);


                            }else if (response.body().getSuccess()==0)
                            {
                                Toast.makeText(confirmEmail_ForgetPass.this, "no Internet", Toast.LENGTH_SHORT).show();

                            }
                            else if (response.body().getSuccess()==2) {
                                Toast.makeText(confirmEmail_ForgetPass.this, "this mail not Register", Toast.LENGTH_SHORT).show();

                            }
                            }

                    }

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        dialog.dismiss();


                        Log.e("forget",t+"");
                    }
                });


            }
        });
    }
}

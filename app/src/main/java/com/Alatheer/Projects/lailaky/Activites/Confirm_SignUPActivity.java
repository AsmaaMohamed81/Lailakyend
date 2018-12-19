package com.Alatheer.Projects.lailaky.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Models.UserModel;
import com.Alatheer.Projects.lailaky.R;
import com.Alatheer.Projects.lailaky.SingleTone.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Confirm_SignUPActivity extends AppCompatActivity implements Users.onCompleteListener {
 private Button confirm;
 private EditText adress,city,governate,phone,mail;
 private String User_id,gadress,gcity,ggovernate,gphone,gmail;
 UserModel userModel;
    Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm__sign_up);
        initView();

    }

    private void initView() {
        confirm=findViewById(R.id.confirm);
        adress=findViewById(R.id.address);
        city=findViewById(R.id.city);
        phone=findViewById(R.id.phone);
        mail=findViewById(R.id.email);
        governate=findViewById(R.id.governate);
        users = Users.getInstance();
        users.getData(this);






        Intent i =getIntent();
        User_id=i.getStringExtra("user_id");
        Toast.makeText(this, ""+userModel.getUser_id(), Toast.LENGTH_SHORT).show();


        phone.setText(userModel.getUser_phone());
        mail.setText(userModel.getUser_email());
        governate.setText(userModel.getGovernate());
        city.setText(userModel.getCity());
        adress.setText(userModel.getAddress());
//
//        Services services=Api.getClient().create(Services.class);
//        Call<UserModel> call=services.personaldata(userModel.getUser_id());
//        call.enqueue(new Callback<UserModel>() {
//            @Override
//            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
//                if (response.isSuccessful()){
//
//                  //  Toast.makeText(Confirm_SignUPActivity.this, "yesss", Toast.LENGTH_SHORT).show();
//                    if (response.body()!=null)
//                    {
//
//                        phone.setText(response.body().getUser_phone());
//                        mail.setText(response.body().getUser_email());
//                        governate.setText(response.body().getGovernate());
//                        city.setText(response.body().getCity());
//                        adress.setText(response.body().getAddress());
//
//
//
//
//                    }}
//            }
//
//            @Override
//            public void onFailure(Call<UserModel> call, Throwable t) {
//           //     Toast.makeText(Confirm_SignUPActivity.this, "nooo"+t, Toast.LENGTH_SHORT).show();
//                Log.e("ttttttt",t+"");
//
//            }
//        });






        confirm.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                gadress=adress.getText().toString();
                gphone=phone.getText().toString();
                gmail=mail.getText().toString();
                ggovernate=governate.getText().toString();
                gcity=city.getText().toString();



                Log.e("hhh",gadress);
                Log.e("hhh",gphone);
                Log.e("hhh",ggovernate);
                Log.e("hhh",gcity);
                Log.e("hhh",gmail);
                Log.e("hhh",userModel.getUser_id());


                Services services=Api.getClient().create(Services.class);
                Call<UserModel> call=services.Updatepersonaldata(gadress,gcity,ggovernate,gmail,gphone,userModel.getUser_id());

                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        if (response.isSuccessful()) {

                        //    Toast.makeText(Confirm_SignUPActivity.this, "yesss", Toast.LENGTH_SHORT).show();


                            if (response.body().getSuccess()==1)

                            {

                                Toast.makeText(Confirm_SignUPActivity.this, "تم تعديل بيناتك ", Toast.LENGTH_SHORT).show();

                            }
                            else if (response.body().getSuccess()==2)
                            {

                                Toast.makeText(Confirm_SignUPActivity.this, "لم يتم تعديل البينات الرجاء التأكد من النت", Toast.LENGTH_SHORT).show();

                            }


                    }}

                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                       //// Toast.makeText(Confirm_SignUPActivity.this, "nooooooo", Toast.LENGTH_SHORT).show();

                        Log.e("yyyyyy",t+"");



                    }
                });



            }
        });
    }

    @Override
    public void OnDataSuccess(UserModel userModel) {
        this.userModel=userModel;

    }
}

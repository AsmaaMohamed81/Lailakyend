package com.Alatheer.Projects.lailaky.Activites;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.ApiServices.Api;
import com.Alatheer.Projects.lailaky.ApiServices.Services;
import com.Alatheer.Projects.lailaky.Models.ContactModel;
import com.Alatheer.Projects.lailaky.Models.ModelContactUs;
import com.Alatheer.Projects.lailaky.R;
import com.lamudi.phonefield.PhoneInputLayout;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener{
    private Shimmer shimmer;
    private ShimmerTextView offer_txt;
    private EditText name,email,message;
    private TextView mail,web,mob,map,time_work;
    private Button send;
    private PhoneInputLayout edt_phone;
    double lat,lang;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);

        super.attachBaseContext(LanguageHelper.onAttach(newBase, Paper.book().read("language", Locale.getDefault().getLanguage())));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        initView();
        getdata();

    }
    private void initView() {

        offer_txt=findViewById(R.id.shimmer);
        shimmer =new Shimmer();
        shimmer.setStartDelay(500);
        shimmer.setDuration(500);
        shimmer.start(offer_txt);
        name=findViewById(R.id.edt_name);
        edt_phone=findViewById(R.id.edt_phone);
        email=findViewById(R.id.edt_mail);
        message=findViewById(R.id.edt_message);
        send=findViewById(R.id.btn_contact);
        edt_phone.setDefaultCountry("sa");
        edt_phone.getTextInputLayout().getEditText().setTextSize(TypedValue.COMPLEX_UNIT_SP,13f);
        edt_phone.getTextInputLayout().getEditText().setHintTextColor(ContextCompat.getColor(this,R.color.black));
        edt_phone.getTextInputLayout().getEditText().setHintTextColor(ContextCompat.getColor(this,R.color.colorPrimary));
        edt_phone.getTextInputLayout().getEditText().setText(getString(R.string.phone));
        mail=findViewById(R.id.email);
        mob=findViewById(R.id.phone);
        web=findViewById(R.id.web);
        map=findViewById(R.id.mapk);
        time_work=findViewById(R.id.time_work);

        send.setOnClickListener(this);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactActivity.this,MapsActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lang",lang);
                startActivity(intent);
            }
        });

    }

    private void getdata() {

        Services services=Api.getClient().create(Services.class);
        Call<ModelContactUs> call=services.GetContactUs();
        call.enqueue(new Callback<ModelContactUs>() {
            @Override
            public void onResponse(Call<ModelContactUs> call, Response<ModelContactUs> response) {

                if (response.isSuccessful()){


                    if (response.body()!=null)
                    {
                        Toast.makeText(ContactActivity.this, "yesssssssssssss", Toast.LENGTH_SHORT).show();

                        mail.setText(response.body().getEmail_info());
                        web.setText(response.body().getWeb_info());
                        mob.setText(response.body().getTele_info());
                        time_work.setText(response.body().getTime_work());

                        lat=response.body().getLocation_google_lat();
                        lang=response.body().getLocation_google_long();
                    }




                }



            }

            @Override
            public void onFailure(Call<ModelContactUs> call, Throwable t) {
                Log.e("Errortttttttttttt",t.getMessage());

                Toast.makeText(ContactActivity.this, "faillllll"+call, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void SendDataToServer() {

        String uname = name.getText().toString();
        String uemail= email.getText().toString();
        String uphone= edt_phone.getPhoneNumber();
        String umessage = message.getText().toString();

        if (TextUtils.isEmpty(uname))
        {
            name.setError(getString(R.string.enter_name));
        }else if (TextUtils.isEmpty(uemail))
        {
            name.setError(null);
            email.setError(getString(R.string.email_require));
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(uemail).matches())
        {
            name.setError(null);
            email.setError(getString(R.string.inv_email));

        }else if (TextUtils.isEmpty(uphone))
        {
            name.setError(null);
            email.setError(null);
            edt_phone.getTextInputLayout().getEditText().setError(getString(R.string.enter_phone));
        }else if (edt_phone.isValid())
        {
            name.setError(null);
            email.setError(null);
            edt_phone.getTextInputLayout().getEditText().setError(getString(R.string.inv_phone));

        }else if (TextUtils.isEmpty(umessage))
        {
            name.setError(null);
            email.setError(null);
            edt_phone.getTextInputLayout().getEditText().setError(null);
            message.setError(getString(R.string.enter_msg));
        }else
            {
                name.setError(null);
                email.setError(null);
                edt_phone.getTextInputLayout().getEditText().setError(null);
                message.setError(null);
                Map<String,String> map = new HashMap<>();

                map.put("full_name",name.getText().toString());
                map.put("email",email.getText().toString());
                map.put("phone_number",uphone);
                map.put("message",message.getText().toString());

                Services services= Api.getClient().create(Services.class);
                Call<ContactModel> call=services.ContactUs(map);
                call.enqueue(new Callback<ContactModel>() {
                    @Override
                    public void onResponse(Call<ContactModel> call, Response<ContactModel> response) {
                        if (response.isSuccessful()){
                            if (response.body().getSuccess()==1){

                                Toast.makeText(ContactActivity.this, R.string.done, Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ContactModel> call, Throwable t) {

                    }
                });

    }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn_contact:
                SendDataToServer();
                break;

        }

    }
}

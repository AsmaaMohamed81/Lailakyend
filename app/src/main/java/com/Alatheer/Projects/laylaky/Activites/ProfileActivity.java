package com.Alatheer.Projects.laylaky.Activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.Alatheer.Projects.laylaky.Models.UserModel;
import com.Alatheer.Projects.laylaky.R;
import com.Alatheer.Projects.laylaky.SingleTone.Users;

public class ProfileActivity extends AppCompatActivity implements Users.onCompleteListener{

    private TextView user_name,user_email,user_phone;
    private Button updateBtn;
    private Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        users = Users.getInstance();
        users.getData(this);
    }

    private void initView() {
        user_name = findViewById(R.id.user_name);
        user_email= findViewById(R.id.email);
        user_phone= findViewById(R.id.phone);
        updateBtn = findViewById(R.id.updateBtn);

    }

    @Override
    public void OnDataSuccess(UserModel userModel) {
        UpdateUi(userModel);
    }

    private void UpdateUi(UserModel userModel) {
        user_name.setText(userModel.getUser_name());
        user_email.setText(userModel.getUser_email());
        user_phone.setText(userModel.getUser_phone());
    }
}

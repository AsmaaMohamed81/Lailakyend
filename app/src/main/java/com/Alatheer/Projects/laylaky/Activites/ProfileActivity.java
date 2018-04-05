package com.Alatheer.Projects.laylaky.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.Alatheer.Projects.laylaky.Models.UserModel;
import com.Alatheer.Projects.laylaky.R;
import com.Alatheer.Projects.laylaky.SingleTone.Users;

public class ProfileActivity extends AppCompatActivity implements Users.onCompleteListener{

    private TextView user_name,user_email,user_phone;
    private Button updateBtn;
    private Users users;
    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initView();
        users = Users.getInstance();
        users.getData(this);
    }

    private void initView() {
        toolBar = findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.pass)
        {

            Intent intent = new Intent(ProfileActivity.this,UpdatePasswordActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

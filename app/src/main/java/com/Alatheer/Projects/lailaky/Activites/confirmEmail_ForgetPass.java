package com.Alatheer.Projects.lailaky.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Alatheer.Projects.lailaky.R;

public class confirmEmail_ForgetPass extends AppCompatActivity {
 private EditText mail;
 private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_email__forget_pass);

        initView();
    }

    private void initView() {

        mail=findViewById(R.id.mail);
        confirm=findViewById(R.id.comfirm);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(confirmEmail_ForgetPass.this,Signup.class);
                intent.putExtra("mail",mail.getText().toString());
                startActivity(intent);
            }
        });
    }
}

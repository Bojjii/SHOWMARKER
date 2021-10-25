package com.example.bojii.Screen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bojii.R;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    Button callSignUp,login_btn;
    ImageView image;
    TextView logoText;
    TextInputLayout username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(android.view.Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.activity_login);


        callSignUp = findViewById(R.id.signup_screen);
        image = findViewById(R.id.logo_login);
        logoText = findViewById(R.id.logo_name_signup);
        username = findViewById(R.id.reg_username);
        password = findViewById(R.id.reg_password);
        login_btn = findViewById(R.id.login_btn);

        callSignUp.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this,SignUp.class);

            Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View,String>(image,"logo_image");
                pairs[1] = new Pair<View,String>(login_btn,"button_tran");
                pairs[2] = new Pair<View,String>(callSignUp,"login_signup_tran");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);

            startActivity(intent,options.toBundle());

        });
    }
}
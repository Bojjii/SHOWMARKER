package com.example.bojii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    TextInputLayout regName, regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    final String ONE_DIGIT = "^(?=.*[0-9]).{6,}$";
    final String ONE_LOWER_CASE = "^(?=.*[a-z]).{6,}$";
    final String ONE_UPPER_CASE = "^(?=.*[A-Z]).{6,}$";
    final String ONE_SPECIAL_CHAR = "^(?=.*[@#$%^&+=]).{6,}$";
    final String NO_SPACE = "^(?=\\S+$).{6,}$";
    final String MIN_CHAR = "^[a-zA-Z0-9._-].{5,}$";
    final String EMAIL_VALIDATE = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phone);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.signup_screen);

        regBtn.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callNextSignupScreen(view);

            }
        });
    }

    public void callNextSignupScreen(View view) {

        if (!validateFullName() | !validateUsername() | !validateEmail() | !validatePassword()) {
            return;
        }
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        //Get all values

        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        UserHelper helper= new UserHelper(email, name, password, phoneNo, username);

        reference.child(phoneNo).setValue(helper);


        Toast.makeText(this, "OK em", Toast.LENGTH_SHORT).show();
    }

    private boolean validateFullName() {
        String val = regName.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            regName.setError("Họ và tên không được để trống");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            regUsername.setError("Username không được để trống");
            return false;
        }
        if (val.length() > 20) {
            regUsername.setError("Username quá dài");
            return false;

        }
        if (val.length() <= 2) {
            regUsername.setError("Username quá ngắn");
            return false;
        }
        if (!val.matches(NO_SPACE)) {
            regUsername.setError("Username không được có khoảng trắng!");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString().trim();
        if (val.isEmpty()) {
            regEmail.setError("Email không được để trống!");
            return false;
        } else if (!val.matches(EMAIL_VALIDATE)) {
            regEmail.setError("Hãy viết email đúng định dạng!");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString().trim();
        String checkPassword = "^" +
                "(?=.*[0-9])" +                                 //Phải có ít nhất 1 số
                "(?=.*[a-z])" +                                 //Phải có ít nhất 1 từ viết thường
                "(?=.*[A-Z])" +                                 //Phải có ít nhất 1 từ viết hoa
                "(?=.*[a-zA-Z])" +                              //Tất cả các từ
                "(?=.*[@#$%^&+=])" +                            //Phải có ít nhất 1 kí tự đặc biệt
                //"(?=S+$)" +                                    //Không được có khoảng trắng
                ".{6,}" +                                      //Phải có ít nhất 6 kí tự
                "$";


        if (val.isEmpty()) {
            regPassword.setError("Mật khẩu không được để trống!");
            return false;
        } else if (!val.matches(MIN_CHAR)) {
            regPassword.setError("Mật khẩu phải có ít nhất 6 kí tự!");
            return false;
        } else if (!val.matches(ONE_DIGIT)) {
            regPassword.setError("Mật khẩu phải có ít nhất 1 chữ số!");
            return false;
        } else if (!val.matches(ONE_LOWER_CASE)) {
            regPassword.setError("Mật khẩu phải có ít nhất 1 chữ thường!");
            return false;
        } else if (!val.matches(ONE_UPPER_CASE)) {
            regPassword.setError("Mật khẩu phải có ít nhất 1 chữ viết hoa!");
            return false;
        } else if (!val.matches(ONE_SPECIAL_CHAR)) {
            regPassword.setError("Mật khẩu phải có ít nhất 1 kí tự đặc biệt!");
            return false;
        } else if (!val.matches(NO_SPACE)) {
            regPassword.setError("Mật khẩu không được để khoảng cách!");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }


    }
}
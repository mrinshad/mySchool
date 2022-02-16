package com.example.myschool;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    SQLiteDatabase mydb;
    TextInputLayout usernameTextInputLayout, passwordTextInputLayout;
    RelativeLayout relativeLayout;
    TextView registerPageLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        usernameTextInputLayout = (TextInputLayout) findViewById(R.id.usernameFieldLogin);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.passwordFieldLogin);
        registerPageLink = (TextView)  findViewById(R.id.registerPageLink); 
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        registerPageLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }

    public void login(View v) {
        Log.d(TAG, "login: mhfdjdj");
        mydb = openOrCreateDatabase(Constants.DBNAME, 0, null);
        String username = usernameTextInputLayout.getEditText().getText().toString();
        String password = passwordTextInputLayout.getEditText().getText().toString();
        Cursor cursor = mydb.rawQuery("SELECT * FROM ADMIN WHERE username ='" + username + "'", null);
        if (cursor.moveToFirst()){
            Cursor cursor1 = mydb.rawQuery("SELECT * FROM ADMIN WHERE username ='" + username + "' AND password ='" + password + "'", null);
            if (cursor1.moveToFirst()){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
                finish();
            }else{
                Snackbar.make(relativeLayout,"Invalid Password ..!",Snackbar.LENGTH_LONG).show();
            }
        }else{
            Snackbar.make(relativeLayout,"Invalid Login Details ..!",Snackbar.LENGTH_LONG).show();
        }
    }
}
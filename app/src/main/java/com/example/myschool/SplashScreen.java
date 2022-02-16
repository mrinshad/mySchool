package com.example.myschool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    SQLiteDatabase mydb;
    Constants constants = new Constants();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initTables();
        getSupportActionBar().hide();
        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                if (checkLoginStatus()) {
//                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }
            }
        }.start();

    }

    private void initTables() {
        try {
            mydb = this.openOrCreateDatabase(constants.DBNAME, 0, null);
            mydb.execSQL(constants.TABLE_ADMIN_DETAILS);
            mydb.close();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Database Error", Toast.LENGTH_SHORT).show();
        }
        }

        private boolean checkLoginStatus () {
            return true;
        }
    }
package com.example.myschool;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    SQLiteDatabase mydb;
    TextInputLayout usernameTextInputLayout, passwordTextInputLayout;
    RelativeLayout relativeLayout;
    TextView registerPageLink;
    Constants constants = new Constants();
    DatabaseReference mDatabase;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;


    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }

    private void reload() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        usernameTextInputLayout = (TextInputLayout) findViewById(R.id.usernameFieldLogin);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.passwordFieldLogin);
        registerPageLink = (TextView)  findViewById(R.id.registerPageLink);
        progressBar = (ProgressBar) findViewById(R.id.loginProgress);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        registerPageLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }

    public void login(View v) {
        String username = usernameTextInputLayout.getEditText().getText().toString();
        String password = passwordTextInputLayout.getEditText().getText().toString();
        progressBar.setVisibility(View.VISIBLE);
        if (constants.isOnline) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
           try {
               mAuth.signInWithEmailAndPassword(username, password)
                       .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   // Sign in success, update UI with the signed-in user's information
                                   Log.d(TAG, "signInWithEmail:success");
                                   FirebaseUser user = mAuth.getCurrentUser();
                                   progressBar.setVisibility(View.INVISIBLE);
                                   Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_SHORT).show();
                                   updateUI(user);
                               } else {
                                   // If sign in fails, display a message to the user.
                                   Log.w(TAG, "signInWithEmail:failure", task.getException());
                                   Toast.makeText(getApplicationContext(), "Authentication failed.",
                                           Toast.LENGTH_SHORT).show();
                                   progressBar.setVisibility(View.INVISIBLE);
//                                   updateUI(null);
                               }
                           }
                       });
           }catch (Exception e)
           {
               e.printStackTrace();
               Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show();
           }
        } else {
            mydb = openOrCreateDatabase(Constants.DBNAME, 0, null);

            Cursor cursor = mydb.rawQuery("SELECT * FROM ADMIN WHERE username ='" + username + "'", null);
            if (cursor.moveToFirst()) {
                Cursor cursor1 = mydb.rawQuery("SELECT * FROM ADMIN WHERE username ='" + username + "' AND password ='" + password + "'", null);
                if (cursor1.moveToFirst()) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    finish();
                } else Snackbar.make(relativeLayout, "Invalid Password ..!", Snackbar.LENGTH_LONG).show();
            } else Snackbar.make(relativeLayout, "Invalid Login Details ..!", Snackbar.LENGTH_LONG).show();
        }
    }

    private void updateUI(FirebaseUser user) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
package com.example.myschool;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void create_user(){
//        if (constants.isOnline) {
//            mDatabase = FirebaseDatabase.getInstance().getReference();
////            FirebaseDatabase database = FirebaseDatabase.getInstance();
////            DatabaseReference myRef = database.getReference("message");
////            myRef.setValue("Hello, World!");
//
//            try {
//                mAuth.createUserWithEmailAndPassword(username, password)
//                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    // Sign in success, update UI with the signed-in user's information
//                                    Log.d(TAG, "createUserWithEmail:success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
////                                updateUI(user);
//                                } else {
//                                    // If sign in fails, display a message to the user.
//                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
//                                            Toast.LENGTH_SHORT).show();
////                                updateUI(null);
//                                }
//                            }
//                        });
//            }catch (Exception e)
//            {
//                e.printStackTrace();
//                Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show();
//            }
//        }

    }
}
//package com.example.mylab4application;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseReference;
//
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//public class Read extends AppCompatActivity
//
//{
//    private DatabaseReference root;
//    Button read, write;
//    CheckBox database_checkbox;
//    Toast toast;
//    int duration = Toast.LENGTH_SHORT;
//    Intent intent1, intent2;
//    int count;
//    boolean databaseRead, userRead, userWrite;
//    ArrayList studentID, firstName, lastName, gender, dept;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.results_layout);
//
//        extra = intent.getExtras();
//        databaseRead = extra.getBoolean("checkedDatabase");


//        if (!database_checkbox.isActivated()) { //read from file
//            String filename = "data.txt";
//            ArrayList<String> user_data = new ArrayList<>();
//            FileInputStream fileInputStream;
//            InputStreamReader inputStreamReader;
//            try {
//                fileInputStream = openFileInput(filename);
//                inputStreamReader = new InputStreamReader(fileInputStream);
//                BufferedReader br = new BufferedReader(inputStreamReader);
//                if (br.readLine().equals(null)) {
//                    toast.show();
//                } else {
//                    while (br.readLine() != null) {
//                        count++;
//                        user_data.add("\t" + br.readLine() + "\n");
//                        intent1.putExtra(String.valueOf(count), user_data.get(count));
//                    }
//                    startActivity(intent2); //start reading activity
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } else { //read from database
//
//
//            OnCompleteListener<DataSnapshot> onFetchedValues = new OnCompleteListener<DataSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DataSnapshot> task) {
//                    if (task.isSuccessful()) {
//                        DataSnapshot user_data = task.getResult();
//                        for (DataSnapshot node : user_data.getChildren()) {
//                            studentID.add(node.getKey());
//                            firstName.add(node.child("firstName").getValue().toString());
//                            lastName.add(node.child("lastName").getValue().toString());
//                            gender.add(node.child("gender").getValue().toString());
//                            dept.add(node.child("Division").getValue().toString());
//                        }
//                        startActivity(intent2);
//                    } else {
//                        Log.e("firebase", "error getting data", task.getException());
//
//                    }
//                }
//            };
//
//        }
//    }
//});


//
//    }
//
//
//    }

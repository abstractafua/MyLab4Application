package com.example.mylab4application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference root;
    Button read, write;
    CheckBox database_checkbox;
    Toast toast;
    int duration = Toast.LENGTH_SHORT;
    Intent intent1, intent2;
    int count;
    boolean databaseRead, userRead, userWrite;
    ArrayList studentID, firstName, lastName, gender, dept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = FirebaseDatabase.getInstance().getReference();

        read = (Button) findViewById(R.id.read_data_button);
        write = (Button) findViewById(R.id.Write_data_button);
        database_checkbox = (CheckBox) findViewById(R.id.checkBox);
        toast = Toast.makeText(this, "There is no data to read", duration);
        intent1 = new Intent(this, SecondActivity.class);
        intent2 =  new Intent(this, FinalActivity.class);
        count = 0;
        databaseRead = false; userRead = false; userWrite = false;

        root.setValue("Hello World");

        database_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseRead = true;
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    intent2.putExtra("checkedDatabase", databaseRead);
                    startActivity(intent2);
                }
                if (!database_checkbox.isActivated()) { //read from file
                    String filename = "data.txt";
                    ArrayList<String> user_data = new ArrayList<>();
                    FileInputStream fileInputStream;
                    InputStreamReader inputStreamReader;
                    try {
                        fileInputStream = openFileInput(filename);
                        inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader br = new BufferedReader(inputStreamReader);
                        if (br.readLine().equals(null)) {
                           toast.show();
                        } else {
                            while (br.readLine() != null) {
                                count++;
                                user_data.add("\t" + br.readLine() + "\n");
                                intent1.putExtra(String.valueOf(count), user_data.get(count));
                            }
                            startActivity(intent2); //start reading activity
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else { //read from database


                    OnCompleteListener<DataSnapshot> onFetchedValues = new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()) {
                                DataSnapshot user_data = task.getResult();
                                for (DataSnapshot node : user_data.getChildren()) {
                                    studentID.add(node.getKey());
                                    firstName.add(node.child("firstName").getValue().toString());
                                    lastName.add(node.child("lastName").getValue().toString());
                                    gender.add(node.child("gender").getValue().toString());
                                    dept.add(node.child("Division").getValue().toString());
                                }
                                startActivity(intent2);
                            } else {
                                Log.e("firebase", "error getting data", task.getException());

                            }
                        }
                    };

                }
            }
        });

        write.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userWrite = true;
                intent1.putExtra("write", userWrite);

                if (!database_checkbox.isSelected()) { //go to next activty to write to file
                    intent1.putExtra("checkedDatabase", databaseRead);
                } else { //write to database
                    intent1.putExtra("checkedDatabase", databaseRead);
                }

                startActivity(intent1);

            }
        });

    }

}

//    public void acessDatabase(View view) {
//
//        if (read.isActivated()) {
//            //read from databse
//        } else {
//            //write to database
//        }
//    }


    //check for empty contents

    //read from file


    //read from database (checkbox?)

    //write from file

    //write from database (checkbox?)


//    String filename = "output.txt";
//    String fileContents = "";
//    FileOutputStream outputStream;
//
//                   try{
//        outputStream = openFileOutput(filename, Context.MODE_APPEND);
//        outputStream.write();
//    }catch(Exception e){
//        e.printStackTrace();
//    }
//
//}else{ //write to database




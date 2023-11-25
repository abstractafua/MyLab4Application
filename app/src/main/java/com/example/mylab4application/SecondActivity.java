package com.example.mylab4application;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.w3c.dom.Text;

import java.io.FileOutputStream;

public class SecondActivity extends AppCompatActivity {
    private DatabaseReference root;
    TextView studentID,firstName,lastName;
    Intent intent;
    Spinner div;
    RadioGroup gender;
    RadioButton female,other,male;
    Button submit;
    Toast toast;
    Bundle extra;
    String sex;
    int duration = Toast.LENGTH_SHORT;
    boolean databaseRead;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_layout);
        intent = getIntent();
        extra = intent.getExtras();

        studentID = (TextView)findViewById(R.id.studentID_editText);
        firstName = (TextView) findViewById(R.id.firstName_editText);
        lastName = (TextView) findViewById(R.id.lastName_editText);
        gender = (RadioGroup) findViewById(R.id.gender_group);
        female = (RadioButton) findViewById(R.id.female_button);
        male = (RadioButton) findViewById(R.id.male_button);
        other = (RadioButton) findViewById(R.id.other_button);
        submit = (Button) findViewById(R.id.SUBMIT);
        toast = Toast.makeText(this, "Please provide correct information", duration);
        div = (Spinner) findViewById(R.id.division_spinner);
        databaseRead = extra.getBoolean("checkedDatabase");
        sex = "UNDISCLOSED";
        root = FirebaseDatabase.getInstance().getReference();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.div_array, android.R.layout.simple_spinner_item);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        div.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id = studentID.getText().toString();
                String first = firstName.getText().toString();
                String last = lastName.getText().toString();
                String division = div.getSelectedItem().toString();

                if(studentID.length()!=8 | firstName.getText().length()==0 | lastName.getText().length()==0) {
                    toast.show();
                }else{
                    if(databaseRead) {//write to database

                        DatabaseReference current_user = root.child(studentID.getText().toString());
                        current_user.child("FirstName").setValue(firstName.getText().toString());
                        current_user.child("LastName").setValue(lastName.getText().toString());
                        current_user.child("Gender").setValue(sex);
                        current_user.child("Division").setValue(lastName.getText().toString());

                    }else{//write to file
                        FileOutputStream outputStream;
                        String filename = "data.txt";
                        String fileContents = "\n" + Id + " " + first + " " + last + " " + gender + " " + division;
                        try{
                            outputStream = openFileOutput(filename, Context.MODE_APPEND);
                            outputStream.write(fileContents.getBytes());
                            outputStream.close();
                        }catch(Exception e){
                            e.printStackTrace();
                        }

                        finish();
                    }
                }
            }

        });

        gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 sex = findViewById(gender.getCheckedRadioButtonId()).toString();

            }
        });


    }



//
//

}

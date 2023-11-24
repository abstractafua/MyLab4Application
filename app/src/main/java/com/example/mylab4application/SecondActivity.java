package com.example.mylab4application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.w3c.dom.Text;

public class SecondActivity extends MainActivity {

    private DatabaseReference root;
    TextView studentID,firstName,lastName;
    Intent intentOut;
    Spinner div;
    RadioGroup gender;
    RadioButton female,other,male;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        root = FirebaseDatabase.getInstance().getReference();
        intentOut = new Intent(this,FinalActivity.class);

//        studentID = (TextView)findViewById(R.id.studentID_editText);
//        firstName = (TextView) findViewById(R.id.firstName_editText);
//        lastName = (TextView) findViewById(R.id.lastName_editText);
//        gender = (RadioGroup) findViewById(R.id.gender_group);
//        female = (RadioButton) findViewById(R.id.female_button);
//        male = (RadioButton) findViewById(R.id.male_button);
//        other = (RadioButton) findViewById(R.id.other_button);
//        div = (Spinner) findViewById(R.id.division_spinner);

    }


}

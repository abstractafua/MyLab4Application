package com.example.mylab4application;

public class Registration {
    public int studentid;
    public String firstname;
    public String lastname;
    public String gender;
    public String division;
    public void Registration(int studentid, String firstname, String lastname, String gender, String division){
        this.division =division;
        this.studentid = studentid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }
}

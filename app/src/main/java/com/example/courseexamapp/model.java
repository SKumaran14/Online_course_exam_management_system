package com.example.courseexamapp;


public class model
{
    String coursename,duration;
    model()
    {

    }
    public model(String coursename, String duration) {
        this.coursename = coursename;
        this.duration = duration;

    }

    public String getcoursename() {
        return coursename;
    }

    public void setcoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getduration() {
        return duration;
    }

    public void setduration(String duration) {
        this.duration = duration;
    }

}


package com.example.courseexamapp;

public class StudyNotes {
    private String id;
    private String chapter;
    private String notes;


    public StudyNotes(String id, String chapter, String notes) {
        this.id = id;
        this.chapter = chapter;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}



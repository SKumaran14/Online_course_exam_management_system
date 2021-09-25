package com.example.courseexamapp;

public class Java {
    private String isSubmitted;
    private String deadline;
    private String fileName;
    private String submittedDate;

    public String getIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(String isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(String submittedDate) {
        this.submittedDate = submittedDate;
    }

    public Java(String isSubmitted,
                String deadline,
                String fileName,
                String submittedDate){
        this.isSubmitted = isSubmitted;
        this.deadline = deadline;
        this.fileName = fileName;
        this.submittedDate = submittedDate;
    }

    public Java(String isSubmitted, String deadline){
        this.isSubmitted = isSubmitted;
        this.deadline = deadline;
    }

    public Java(){

    }
}

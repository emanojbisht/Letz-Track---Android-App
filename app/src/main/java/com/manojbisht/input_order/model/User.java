package com.manojbisht.input_order.model;

public class User {
    private String rName;
    private String rUserName;
    private String rPassword;
    private String rQuestion;
    private String rAnswer;

    public User() {
    }

    public User(String rName, String rUserName, String rPassword, String rQuestion, String rAnswer) {
        this.rName = rName;
        this.rUserName = rUserName;
        this.rPassword = rPassword;
        this.rQuestion = rQuestion;
        this.rAnswer = rAnswer;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrUserName() {
        return rUserName;
    }

    public void setrUserName(String rUserName) {
        this.rUserName = rUserName;
    }

    public String getrPassword() {
        return rPassword;
    }

    public void setrPassword(String rPassword) {
        this.rPassword = rPassword;
    }

    public String getrQuestion() {
        return rQuestion;
    }

    public void setrQuestion(String rQuestion) {
        this.rQuestion = rQuestion;
    }

    public String getrAnswer() {
        return rAnswer;
    }

    public void setrAnswer(String rAnswer) {
        this.rAnswer = rAnswer;
    }
}

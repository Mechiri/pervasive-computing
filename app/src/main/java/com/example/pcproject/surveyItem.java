package com.example.pcproject;

/*
    Serves as accessor and mutator methods for all love language
    survey questions in fragment activities
 */

public class surveyItem {
    private String question;
    private String choice1;
    private String choice2;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }
}

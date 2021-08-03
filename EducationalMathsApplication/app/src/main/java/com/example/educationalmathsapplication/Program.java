package com.example.educationalmathsapplication;

import java.util.ArrayList;
import java.util.List;

public class Program {

    //Create variables
    private List<Questions> question;
    private int numberOfCorrect;
    private int numberOfIncorrect;
    private int totalQuestions;
    private int score;
    private Questions currentQuestion;

    //Assign values to variables
    public Program(){
        numberOfCorrect = 0;
        numberOfIncorrect = 0;
        totalQuestions = 0;
        score = 0;
        currentQuestion = new Questions(10);
        question = new ArrayList<Questions>();
    }

    //Creating a new maths problem while keeping track of how many questions have been asked
    public void createNewProblem(){
        currentQuestion = new Questions(totalQuestions * 2 + 5);
        totalQuestions++;
        question.add(currentQuestion);
    }

    //Check if the answer that the user has selected is correct
    public boolean checkAnswer(int submittedAnswer){
        boolean correct;
        if(currentQuestion.getAnswer() == submittedAnswer){
            numberOfCorrect++;
            correct = true;
        }else {
            numberOfIncorrect++;
            correct = false;
        }
        score = numberOfCorrect * 10 - numberOfIncorrect * 30;
        return correct;
    }

    //Generated getters and setters
    public List<Questions> getQuestion() {
        return question;
    }

    public void setQuestion(List<Questions> question) {
        this.question = question;
    }

    public int getNumberOfCorrect() {
        return numberOfCorrect;
    }

    public void setNumberOfCorrect(int numberOfCorrect) {
        this.numberOfCorrect = numberOfCorrect;
    }

    public int getNumberOfIncorrect() {
        return numberOfIncorrect;
    }

    public void setNumberOfIncorrect(int numberOfIncorrect) {
        this.numberOfIncorrect = numberOfIncorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Questions getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(Questions currentQuestion) {
        this.currentQuestion = currentQuestion;
    }
}

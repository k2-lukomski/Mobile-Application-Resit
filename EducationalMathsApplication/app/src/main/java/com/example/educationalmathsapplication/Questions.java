package com.example.educationalmathsapplication;

import java.util.Random;

public class Questions {

    private int firstNumber;
    private int secondNumber;
    private int answer;

    //There are 4 choices, the user can pick one of them
    private int [] answerArray;

    // The position of the correct answer
    private int answerPosition;

    //Difficulty of the questions, the limit up to which the values of firstNumber and secondNumber can rise to
    private int upperLimit;

    //Output the math problem in written form to the user
    private String questionPhrase;

    //Create a new maths problem randomly
    public Questions(int upperLimit){
        this.upperLimit = upperLimit;
        Random randomNumberMaker = new Random();

        //Assigning firstNumber and secondNumber random values within the upper limit
        this.firstNumber = randomNumberMaker.nextInt(upperLimit);
        this.secondNumber = randomNumberMaker.nextInt(upperLimit);
        this.answer = this.firstNumber + this.secondNumber;
        this.questionPhrase = firstNumber + "+" + secondNumber + "=";

        //The correct answer is assigned to a random index corresponding to an answer button
        this.answerPosition = randomNumberMaker.nextInt(4);
        this.answerArray = new int[] {0,1,2,3};

        //The answers around the correct answer have to be dismissive so additional values are added
        this.answerArray[0] = answer + 1;
        this.answerArray[1] = answer + 10;
        this.answerArray[2] = answer + -5;
        this.answerArray[3] = answer + -2;

        //Shuffle the array so that the correct answer doesn't appear in the same position
        this.answerArray = shuffleArray(this.answerArray);

        //Assign an answer to a position in the array
        answerArray[answerPosition] = answer;
    }

    //Method which shuffles the array
    private int [] shuffleArray(int [] array){
        int index, temp;
        Random randomNumberGenerator = new Random();

        for (int i = array.length - 1; i > 0; i--){
            index = randomNumberGenerator.nextInt(i+1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }

    //Generated getters and setters
    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public void setQuestionPhrase(String questionPhrase) {
        this.questionPhrase = questionPhrase;
    }
}

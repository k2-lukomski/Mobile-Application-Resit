package com.example.educationalmathsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Assigning buttons to variables
    Button btn_start, btn_answer1, btn_answer2, btn_answer3, btn_answer4;
    TextView tv_score,tv_question, tv_message, tv_timer;
    ProgressBar prog_bar;

    //Assign the Program variable to a variable for easy access
    Program p = new Program();

    //set amount of time
    int secondsLeft = 30;

    //setting up timer to count down from 30 seconds at the start of each turn
    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long l) {
            secondsLeft--;
            tv_timer.setText(Integer.toString(secondsLeft) + "Sec");
            prog_bar.setProgress(30 - secondsLeft);
        }

        // Disable all buttons and display a message when the time is up
        @Override
        public void onFinish() {
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);
            btn_answer4.setEnabled(false);
            tv_message.setText("Time's Up! " + p.getNumberOfCorrect() + "/" + (p.getTotalQuestions() - 1));

            final Handler handler = new Handler();

            //Make the start button reappear after 4 seconds
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn_start.setVisibility(View.VISIBLE);
                }
            }, 4000);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assigning and ID to each button so it can be used to find the position of user taps
        btn_start = findViewById(R.id.btn_start);
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer2 = findViewById(R.id.btn_answer2);
        btn_answer3 = findViewById(R.id.btn_answer3);
        btn_answer4 = findViewById(R.id.btn_answer4);

        tv_score = findViewById(R.id.tv_score);
        tv_question = findViewById(R.id.tv_question);
        tv_message = findViewById(R.id.tv_message);
        tv_timer = findViewById(R.id.tv_timer);

        prog_bar = findViewById(R.id.prog_bar);

        //Setting static values to text views before the application is run
        tv_timer.setText("0Sec");
        tv_question.setText("");
        tv_message.setText("Press Start");
        tv_score.setText("0pts");

        //Creating a new click listener for the start button
        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Assigning a view to the start button
                Button start_button = (Button) v;
                //Once the button is pressed it will disappear and another method will be called to start the program
                start_button.setVisibility(View.INVISIBLE);
                secondsLeft = 30;
                p = new Program();
                startNextTurn();
                timer.start();

            }
        };

        //Creating a new click listener for the answer buttons
        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;

                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());

                p.checkAnswer(answerSelected);
                tv_score.setText(Integer.toString(p.getScore()));
                startNextTurn();
            }
        };

        //Assigning the click listener to the start button
        btn_start.setOnClickListener(startButtonClickListener);

        //Assigning the click listener to the answer buttons
        btn_answer1.setOnClickListener(answerButtonClickListener);
        btn_answer2.setOnClickListener(answerButtonClickListener);
        btn_answer3.setOnClickListener(answerButtonClickListener);
        btn_answer4.setOnClickListener(answerButtonClickListener);
    }
    //Create a new turn
    private void startNextTurn(){
        //Call a function to create a new maths problem
        p.createNewProblem();
        int [] answer = p.getCurrentQuestion().getAnswerArray();

        //Display values on the buttons
        btn_answer1.setText(Integer.toString(answer[0]));
        btn_answer2.setText(Integer.toString(answer[1]));
        btn_answer3.setText(Integer.toString(answer[2]));
        btn_answer4.setText(Integer.toString(answer[3]));

        //Enable all buttons
        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);
        btn_answer4.setEnabled(true);

        //Display the question at the top of the screen
        tv_question.setText(p.getCurrentQuestion().getQuestionPhrase());

        tv_message.setText(p.getNumberOfCorrect() + "/" + (p.getTotalQuestions() - 1));


    }

}
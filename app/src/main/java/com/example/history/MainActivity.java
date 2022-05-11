package com.example.history;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluation(View view) {
        String[] answers = evaluateGui();

        int result = evaluateQuiz(answers);

        toastResult(result);
    }

    public String[] evaluateGui() {
        String[] ret = new String[5];
        EditText editTextQuestion1 = findViewById(R.id.question_1);

        CheckBox checkBoxQuestion2a = findViewById(R.id.question_2_year);
        CheckBox checkBoxQuestion2b = findViewById(R.id.question_2_year1);
        CheckBox checkBoxQuestion2c = findViewById(R.id.question_2_year2);

        boolean answerQuestion2 = false;

        if (checkBoxQuestion2a.isChecked() && !checkBoxQuestion2b.isChecked() && !checkBoxQuestion2c.isChecked()) {
            answerQuestion2 = true;
        }

        CheckBox checkBoxQuestion4Buchanan = findViewById(R.id.question_4_usa3);
        CheckBox checkBoxQuestion4Johnson = findViewById(R.id.question_4_usa1);
        CheckBox checkBoxQuestion4Lincoln = findViewById(R.id.question_4_usa2);

        boolean answerQuestion4 = false;

        boolean Buchanan = checkBoxQuestion4Buchanan.isChecked();
        boolean Johnson = checkBoxQuestion4Johnson.isChecked();
        boolean Lincoln = checkBoxQuestion4Lincoln.isChecked();


        if (!Buchanan && !Johnson && Lincoln) {
            answerQuestion4 = true;
        }

        ret[0] = editTextQuestion1.getText().toString().toLowerCase();
        ret[1] = Boolean.toString(answerQuestion2);
        ret[2] = evaluateRadioGroup(R.id.radio_group_question_3).toLowerCase();
        ret[3] = Boolean.toString(answerQuestion4);
        ret[4] = evaluateRadioGroup(R.id.radio_group_question_5).toLowerCase();

        return ret;
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"Martin Luther King Jr", "true", "1776", "true", "1963"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    public void toastResult(int result) {
        String message = result + " out of 5. ";

        if (result == 0) {
            message += "Poor luck.";
        } else if (result == 1) {
            message += "You could do better.";
        } else if (result == 2) {
            message += "Quite nice.";
        } else if (result == 3) {
            message += "Really nice.";
        } else if (result == 4) {
            message += "Great!";
        } else if (result == 5) {
            message += "Absolutely awesome!";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String)radioButtonQuestion.getText();
    }
}

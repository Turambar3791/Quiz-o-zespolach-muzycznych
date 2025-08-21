package com.example.aplikcjacrud;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplikcjacrud.dbhelper.DatabaseHelper;
import com.example.aplikcjacrud.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizFragment extends Fragment {
    private TextView tvPoints;
    private ImageView ivImage;
    private Button btnAnswer1, btnAnswer2;
    private int points = 0;
    private int currentIndex = 0;
    private List<Question> questions;
    private int counter = 0;
    Random random = new Random();
    private List<Integer> doneQuestions = new ArrayList<>();
    private Bundle args;
    private String player;
    private EndFragment endFragment = new EndFragment();
    private Bundle bundle = new Bundle();
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvPoints = view.findViewById(R.id.tvPoints);
        ivImage = view.findViewById(R.id.ivImage);
        btnAnswer1 = view.findViewById(R.id.btnAnswer1);
        btnAnswer2 = view.findViewById(R.id.btnAnswer2);
        questions = Question.generateList();

        currentIndex = drawQuestion(questions);
        doneQuestions.add(changeQuestion(ivImage, btnAnswer1, btnAnswer2, currentIndex));

        btnAnswer1.setOnClickListener(v -> {
            String odp = String.valueOf(btnAnswer1.getText());
            counter++;

            if (odp.equals(questions.get(currentIndex).getCorrectAnswer())) {
                points = correctAnswer(points);
            }
            currentIndex = checkQuestion(doneQuestions);
            doneQuestions.add(changeQuestion(ivImage, btnAnswer1, btnAnswer2, currentIndex));

            if (counter >= 10) {
                endQuiz();
            }
        });

        btnAnswer2.setOnClickListener(v -> {
            String odp = String.valueOf(btnAnswer2.getText());
            counter++;

            if (odp.equals(questions.get(currentIndex).getCorrectAnswer())) {
                points = correctAnswer(points);
            }
            currentIndex = checkQuestion(doneQuestions);
            doneQuestions.add(changeQuestion(ivImage, btnAnswer1, btnAnswer2, currentIndex));

            if (counter >= 10) {
                endQuiz();
            }
        });
    }

    private void showPoints(int points) {
        tvPoints.setText(getString(R.string.points2) + points + getString(R.string.points10));
    }

    private void endQuiz() {
        args = getArguments();
        player = args.getString("gracz");

        databaseHelper = new DatabaseHelper(requireContext());
        databaseHelper.updateBestScoreIfHigher(player, points);

        bundle.putString("gracz", player);
        bundle.putInt("points", points);
        endFragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in, R.anim.slide_out).replace(R.id.fragment_container_view, endFragment).commit();
    }

    private int correctAnswer(int points) {
        points++;
        showPoints(points);
        return points;
    }

    private int drawQuestion(List<Question> questions) {
        return random.nextInt(questions.size());
    }

    private int checkQuestion(List<Integer> doneQuestions) {
        int currentIndex;
        do {
            currentIndex = drawQuestion(questions);
        } while (doneQuestions.contains(currentIndex));
        return currentIndex;
    }

    private int changeQuestion(ImageView ivImage, Button btnAnswer1, Button btnAnswer2, int currentIndex) {
        ivImage.setImageResource(questions.get(currentIndex).getImage());
        btnAnswer1.setText(questions.get(currentIndex).getQuestions()[0]);
        btnAnswer2.setText(questions.get(currentIndex).getQuestions()[1]);
        return currentIndex;
    }
}
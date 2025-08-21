package com.example.aplikcjacrud;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {
    private TextView currentPlayer;
    private Button btnStart, btnEnd;
    private FloatingActionButton floatingActionButtonAccounts, floatingActionButtonCharts;
    private static final String argsAccountName = "accoutnName";
    private QuizFragment quizFragment = new QuizFragment();
    private Bundle bundle = new Bundle();

    public static HomeFragment newInstance(String accountName) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(argsAccountName, accountName);
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentPlayer = view.findViewById(R.id.currentPlayer);
        btnStart = view.findViewById(R.id.btnStart);
        btnEnd = view.findViewById(R.id.btnEnd);
        floatingActionButtonAccounts = view.findViewById(R.id.floatingActionButtonAccounts);
        floatingActionButtonCharts  = view.findViewById(R.id.floatingActionButtonCharts);

        floatingActionButtonAccounts.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in, R.anim.slide_out).replace(R.id.fragment_container_view, new AccountsFragment()).commit();
        });

        floatingActionButtonCharts.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in, R.anim.slide_out).replace(R.id.fragment_container_view, new ChartsFragment()).commit();
        });

        if (getArguments() != null) {
            currentPlayer.setText(getString(R.string.player) + " "+ getArguments().getString(argsAccountName));
        }

        btnStart.setOnClickListener(view1 -> {
            if (getArguments() != null) {
                bundle.putString("gracz", getArguments().getString(argsAccountName));
                quizFragment.setArguments(bundle);
                requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in, R.anim.slide_out).replace(R.id.fragment_container_view, quizFragment).commit();
            } else {
                Toast.makeText(requireContext(), R.string.before, Toast.LENGTH_SHORT).show();
            }

        });

        btnEnd.setOnClickListener(view1 -> {
            System.exit(0);
        });
    }
}
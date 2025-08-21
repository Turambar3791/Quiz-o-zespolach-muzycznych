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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EndFragment extends Fragment {
    private TextView result;
    private Button btnGoBackHome, btnEnd;
    private FloatingActionButton floatingActionButtonCharts;
    private Bundle args;
    private String player;
    private int points;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        result = view.findViewById(R.id.result);
        btnGoBackHome = view.findViewById(R.id.btnGoBackHome);
        btnEnd = view.findViewById(R.id.btnEnd);
        floatingActionButtonCharts = view.findViewById(R.id.floatingActionButtonCharts);
        args = getArguments();
        player = args.getString("gracz");
        points = args.getInt("points");

        result.setText(getString(R.string.finish) + " " + points + getString(R.string.points10));

        btnGoBackHome.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in2, R.anim.slide_out2).replace(R.id.fragment_container_view, new HomeFragment()).commit();
        });

        btnEnd.setOnClickListener(view1 -> {
            System.exit(0);
        });

        floatingActionButtonCharts.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in2, R.anim.slide_out2).replace(R.id.fragment_container_view, new ChartsFragment()).commit();
        });

    }
}
package com.example.aplikcjacrud;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aplikcjacrud.dbhelper.DatabaseHelper;
import com.example.aplikcjacrud.model.Account;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddAccountFragment extends Fragment {

    private EditText etName;
    private Button btnAdd;
    private FloatingActionButton floatingActionButtonBackToAccounts;
    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etName = view.findViewById(R.id.etName);
        btnAdd = view.findViewById(R.id.btnAdd);
        floatingActionButtonBackToAccounts = view.findViewById(R.id.floatingActionButtonBackToAccounts);

        databaseHelper = new DatabaseHelper(requireContext());

        floatingActionButtonBackToAccounts.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in2, R.anim.slide_out2).replace(R.id.fragment_container_view, new AccountsFragment()).commit();
        });

        btnAdd.setOnClickListener(view1 -> {
            String name = etName.getText().toString().trim();
            if (!name.isEmpty()) {
                if (databaseHelper.isUserExists(name)) {
                    Toast.makeText(requireContext(), R.string.userAlreadyExists, Toast.LENGTH_SHORT).show();
                } else {
                    databaseHelper.addUser(new Account(name));
                    Toast.makeText(requireContext(), R.string.userAdded, Toast.LENGTH_SHORT).show();
                    etName.getText().clear();
                }
            } else {
                Toast.makeText(requireContext(), R.string.giveAName, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
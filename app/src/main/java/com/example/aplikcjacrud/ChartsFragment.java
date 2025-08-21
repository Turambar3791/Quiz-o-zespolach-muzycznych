package com.example.aplikcjacrud;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aplikcjacrud.adapter.AccountAdapter;
import com.example.aplikcjacrud.adapter.AccountRecordsAdapter;
import com.example.aplikcjacrud.dbhelper.DatabaseHelper;
import com.example.aplikcjacrud.model.Account;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ChartsFragment extends Fragment {

    private RecyclerView recyclerViewRecords;
    private TextView msgWhenNoRecords;
    private FloatingActionButton floatingActionButtonBackToHome;
    private DatabaseHelper databaseHelper;
    private List<Account> accounts = new ArrayList<>();
    private AccountRecordsAdapter accountRecordsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_charts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewRecords = view.findViewById(R.id.recyclerViewRecords);
        msgWhenNoRecords = view.findViewById(R.id.msgWhenNoRecords);
        floatingActionButtonBackToHome = view.findViewById(R.id.floatingActionButtonBackToHome);

        databaseHelper = new DatabaseHelper(requireContext());

        recyclerViewRecords.setLayoutManager(new LinearLayoutManager(requireContext()));

        storeAccountsInList();

        floatingActionButtonBackToHome.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in2, R.anim.slide_out2).replace(R.id.fragment_container_view, new HomeFragment()).commit();
        });
    }

    private void storeAccountsInList() {
        Cursor cursor = databaseHelper.getAllUsersSorted();
        accounts.clear();

        if (cursor.getCount() == 0) {
            msgWhenNoRecords.setVisibility(View.VISIBLE);
        } else {
            msgWhenNoRecords.setVisibility(View.GONE);
            while (cursor.moveToNext()) {
                Account account = new Account(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
                accounts.add(account);
            }
        }

        accountRecordsAdapter = new AccountRecordsAdapter(accounts);
        recyclerViewRecords.setAdapter(accountRecordsAdapter);
    }
}
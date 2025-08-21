package com.example.aplikcjacrud;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aplikcjacrud.adapter.AccountAdapter;
import com.example.aplikcjacrud.adapter.OnAccountClickListener;
import com.example.aplikcjacrud.dbhelper.DatabaseHelper;
import com.example.aplikcjacrud.model.Account;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class AccountsFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView msgWhenNothing;
    private FloatingActionButton floatingActionButtonAddAccount;
    private FloatingActionButton floatingActionButtonBackToHome;
    private DatabaseHelper databaseHelper;
    private List<Account> accounts = new ArrayList<>();
    private AccountAdapter accountAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountAdapter = new AccountAdapter(accounts, ((position, view) -> {
            if (view.getId() == R.id.iconBin) {
                databaseHelper.deleteUser(accounts.get(position).getId());
                accounts.remove(position);
                accountAdapter.notifyItemRemoved(position);
                accountAdapter.notifyItemRangeChanged(position, accounts.size());
            } else {
                Account clickedAccount = accounts.get(position);
                HomeFragment homeFragment = HomeFragment.newInstance(clickedAccount.getName());
                requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in2, R.anim.slide_out2).replace(R.id.fragment_container_view, homeFragment).commit();
            }
        }));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accounts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        msgWhenNothing = view.findViewById(R.id.msgWhenNothing);
        floatingActionButtonAddAccount = view.findViewById(R.id.floatingActionButtonAddAccount);
        floatingActionButtonBackToHome = view.findViewById(R.id.floatingActionButtonBackToHome);

        databaseHelper = new DatabaseHelper(requireContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(accountAdapter);

        storeAccountsInList();

        floatingActionButtonAddAccount.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in, R.anim.slide_out).replace(R.id.fragment_container_view, new AddAccountFragment()).commit();
        });

        floatingActionButtonBackToHome.setOnClickListener(view1 -> {
            requireActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in2, R.anim.slide_out2).replace(R.id.fragment_container_view, new HomeFragment()).commit();
        });

    }

    private void storeAccountsInList() {
        Cursor cursor = databaseHelper.getAllUsers();
        if (cursor.getCount() == 0) {
            msgWhenNothing.setVisibility(View.VISIBLE);
        } else {
            msgWhenNothing.setVisibility(View.GONE);
            while (cursor.moveToNext()) {
                Account account = new Account(cursor.getInt(0), cursor.getString(1), cursor.getInt(2));
                accounts.add(account);
            }
        }
    }
}
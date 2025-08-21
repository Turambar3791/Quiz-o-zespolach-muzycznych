package com.example.aplikcjacrud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikcjacrud.R;
import com.example.aplikcjacrud.model.Account;

import java.util.List;

public class AccountRecordsAdapter extends RecyclerView.Adapter<AccountRecordsAdapter.AccountViewHolder> {
    private List<Account> accounts;

    public AccountRecordsAdapter(List<Account> accounts) {
        this.accounts = accounts;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record, parent, false);
        return new AccountRecordsAdapter.AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        holder.bind(accounts.get(position));
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    class AccountViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView itemName, itemScore;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemScore = itemView.findViewById(R.id.itemScore);
        }

        public void bind(Account account) {
            itemName.setText(account.getName());
            itemScore.setText(String.valueOf(account.getBestScore()));
        }

        @Override
        public void onClick(View view) {

        }
    }
}

package com.example.aplikcjacrud.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikcjacrud.R;
import com.example.aplikcjacrud.model.Account;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {
    private List<Account> accounts;
    private OnAccountClickListener onAccountClickListener;

    public AccountAdapter(List<Account> accounts, OnAccountClickListener onAccountClickListener) {
        this.accounts = accounts;
        this.onAccountClickListener = onAccountClickListener;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new AccountViewHolder(view);
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
        private TextView itemName;
        private ImageView iconBin;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            iconBin = itemView.findViewById(R.id.iconBin);

            iconBin.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        public void bind(Account account) {
            itemName.setText(account.getName());
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                onAccountClickListener.onAccountClick(getAdapterPosition(), view);
            } else {
                onAccountClickListener.onAccountClick(getAdapterPosition(), view);
            }
        }
    }
}

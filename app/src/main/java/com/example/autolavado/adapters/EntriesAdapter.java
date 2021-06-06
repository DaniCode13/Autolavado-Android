package com.example.autolavado.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.autolavado.R;
import com.example.autolavado.models.Entry;

import java.util.ArrayList;

public class EntriesAdapter extends RecyclerView.Adapter<EntriesAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Entry> listEntries;

    public EntriesAdapter(Context context, ArrayList<Entry> list) {
        this.context = context;
        this.listEntries = list;
    }

    @NonNull
    @Override
    public EntriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = 0;
        layout = R.layout.item_entry_description;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntriesAdapter.ViewHolder holder, int position) {
        holder.tv_price.setText(this.listEntries.get(position).getPrice());
        holder.tv_description.setText(this.listEntries.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return listEntries == null ? 0 : listEntries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_price, tv_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_description = itemView.findViewById(R.id.tv_description);
        }
    }
}

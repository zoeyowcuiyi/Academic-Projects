package com.example.assignment2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment2.provider.Category;

import java.util.List;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CustomViewHolder> {

    List<Category> categories;

    public void setCategories(List<Category> data) {
        categories = data;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category_layout, parent, false);
        CategoryRecyclerAdapter.CustomViewHolder viewHolder = new CustomViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvCategoryId.setText(categories.get(position).getCategoryId());
        holder.tvCategoryName.setText(categories.get(position).getCategoryName());
        holder.tvEventCount.setText(String.valueOf(categories.get(position).getEventCount()));
        if (categories.get(position).isActiveCat()) {
            holder.tvIsActiveCat.setText("Yes");
        } else {
            holder.tvIsActiveCat.setText("No");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String categoryId = holder.tvCategoryId.getText().toString();
                Intent intent = new Intent(v.getContext(), GoogleMapsActivity.class);
                intent.putExtra(KeyStore.KEY_CATEGORY_ID, String.valueOf(categoryId));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (categories != null) {
            return categories.size();
        }

        return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public TextView tvCategoryId;
        public TextView tvCategoryName;
        public TextView tvEventCount;
        public TextView tvIsActiveCat;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvCategoryId = itemView.findViewById(R.id.tv_category_id);
            tvCategoryName = itemView.findViewById(R.id.tv_category_name);
            tvEventCount = itemView.findViewById(R.id.tv_event_count);
            tvIsActiveCat = itemView.findViewById(R.id.tv_active_cat);
        }
    }
}

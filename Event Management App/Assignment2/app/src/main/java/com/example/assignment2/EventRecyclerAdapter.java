package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.assignment2.provider.Event;

import java.util.List;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.CustomViewHolder> {

    List<Event> events;

    public void setEvents(List<Event> data) {
        events = data;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event_layout, parent, false);
        CustomViewHolder viewHolder = new CustomViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvEventId.setText(events.get(position).getEventId());
        holder.tvEventName.setText(events.get(position).getEventName());
        holder.tvEveCategoryId.setText(events.get(position).getEveCategoryId());
        holder.tvTicketsAva.setText(String.valueOf(events.get(position).getTicketsAva()));
        if (events.get(position).isActiveEve()) {
            holder.tvIsActiveEve.setText("Active");
        } else {
            holder.tvIsActiveEve.setText("Inactive");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = holder.tvEventName.getText().toString();
                Intent intent = new Intent(v.getContext(), EventGoogleResult.class);
                intent.putExtra(KeyStore.KEY_EVENT_NAME, eventName);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (events != null) {
            return events.size();
        }

        return 0;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public TextView tvEventId;
        public TextView tvEventName;
        public TextView tvEveCategoryId;
        public TextView tvTicketsAva;
        public TextView tvIsActiveEve;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvEventId = itemView.findViewById(R.id.tv_event_id);
            tvEventName = itemView.findViewById(R.id.tv_event_name);
            tvEveCategoryId = itemView.findViewById(R.id.tv_eve_category_id);
            tvTicketsAva = itemView.findViewById(R.id.tv_tickets_ava);
            tvIsActiveEve = itemView.findViewById(R.id.tv_active_eve);
        }
    }

}

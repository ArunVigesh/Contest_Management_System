package com.example.android.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static com.example.android.cms.MainActivity.url_cms;
import static com.example.android.cms.StudentLogin.pid;
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.MyViewHolder> {
    private List<events> dataList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView EventId, EventName,EventDsc,EventTime,EventDate;
        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            EventId = view.findViewById(R.id.textView19);
            EventDsc = view.findViewById(R.id.textView21);
            EventName =  view.findViewById(R.id.textView20);
            EventDate =  view.findViewById(R.id.textView22);
            EventTime =  view.findViewById(R.id.textView23);
        }


    }
    public EventListAdapter(List<events> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_event_list_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final events e =dataList.get(position);
        holder.EventId.setText("Event Id : "+e.getEid());
        holder.EventName.setText("Event Name : "+e.getEname());
        holder.EventDsc.setText("Event Description : "+e.getEdesc());
        holder.EventDate.setText("Date : "+e.getEdate());
        holder.EventTime.setText("Time : "+e.getEtime());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}

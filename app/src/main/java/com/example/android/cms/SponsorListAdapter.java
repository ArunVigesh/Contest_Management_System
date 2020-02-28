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
public class SponsorListAdapter extends RecyclerView.Adapter<SponsorListAdapter.MyViewHolder> {
    private List<sponsors> dataList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView SponsorId, SponsorName;
        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            SponsorId = view.findViewById(R.id.textView19);
            SponsorName = view.findViewById(R.id.textView20);

        }


    }
    public SponsorListAdapter(List<sponsors> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_sponsor_list_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final sponsors s =dataList.get(position);
        holder.SponsorId.setText("Sponsor Id : "+s.getSid());
        holder.SponsorName.setText("Sponsor Name : "+s.getSname());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}

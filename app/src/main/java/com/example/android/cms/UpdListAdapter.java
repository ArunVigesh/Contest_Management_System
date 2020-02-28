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
public class UpdListAdapter extends RecyclerView.Adapter<UpdListAdapter.MyViewHolder> {
    private List<updJuges> dataList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView JId, JFName,JLName,JSpec;
        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            JId = view.findViewById(R.id.textView19);
            JFName = view.findViewById(R.id.textView20);
            JLName =  view.findViewById(R.id.textView21);
            JSpec =  view.findViewById(R.id.textView24);
        }


    }
    public UpdListAdapter(List<updJuges> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_upd_list_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final updJuges j =dataList.get(position);
        holder.JId.setText("Judge Id : "+j.getjId());
        holder.JFName.setText("First Name : "+j.getjFirstName());
        holder.JLName.setText("Last Name : "+j.getjLastName());
        holder.JSpec.setText("Specialization : "+j.getjSpecilalization());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}

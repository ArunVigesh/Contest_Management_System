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
public class JudgeListAdapter extends RecyclerView.Adapter<JudgeListAdapter.MyViewHolder> {
    private List<judges> dataList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView JudgeId, JFirstName,JLastName,JPhone,JEmail,JSpecialization;
        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            JudgeId = view.findViewById(R.id.textView19);
            JFirstName = view.findViewById(R.id.textView20);
            JLastName =  view.findViewById(R.id.textView21);
            JPhone =  view.findViewById(R.id.textView22);
            JEmail =  view.findViewById(R.id.textView23);
            JSpecialization =  view.findViewById(R.id.textView24);
        }


    }
    public JudgeListAdapter(List<judges> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_judge_list_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final judges j =dataList.get(position);
        holder.JudgeId.setText("Judge Id : "+j.getjId());
        holder.JFirstName.setText("First Name : "+j.getjFirstName());
        holder.JLastName.setText("Last Name : "+j.getjLastName());
        holder.JPhone.setText("Phone : "+j.getjPhone());
        holder.JEmail.setText("Email : "+j.getjEmail());
        holder.JSpecialization.setText("Specialization : "+j.getjSpecilalization());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}

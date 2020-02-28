package com.example.android.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;
import static com.example.android.cms.StudentLogin.pid;

public class StudentRegisteredEvents extends RecyclerView.Adapter<StudentRegisteredEvents.MyViewHolder> {
    private List<reg> dataList;

    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ename,edesc,etime,edate;

        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            ename = view.findViewById(R.id.textView);
            edesc = view.findViewById(R.id.textView12);
            edate =  view.findViewById(R.id.textView13);
            etime =  view.findViewById(R.id.textView14);

        }


    }
    public StudentRegisteredEvents(List<reg> dataList) {
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_student_registered_events, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final reg r =dataList.get(position);
        holder.ename.setText("Event Name : "+(r.geteEventName()));
        holder.edesc.setText("Event Description : "+r.geteDescription());
        holder.edate.setText("Event Date : "+r.geteDate());
        holder.etime.setText("Event Time : "+r.geteTime());

    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

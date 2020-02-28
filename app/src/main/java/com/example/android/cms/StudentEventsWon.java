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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;
import static com.example.android.cms.StudentLogin.pid;

public class StudentEventsWon extends RecyclerView.Adapter<StudentEventsWon.MyViewHolder> {
    private List<won> dataList1;
    Button cert;
    Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ename,edesc;
        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            ename = view.findViewById(R.id.textView);
            edesc = view.findViewById(R.id.textView12);
           cert=view.findViewById(R.id.button9);

        }


    }
    public StudentEventsWon(List<won> dataList1) {
        this.dataList1 = dataList1;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_student_events_won, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final won w =dataList1.get(position);
        holder.ename.setText("Event Name : "+w.getEname());
        holder.edesc.setText("Event Description : "+w.getEdesc());
        cert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "Winning Certificate", Toast.LENGTH_SHORT ).show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return dataList1.size();
    }
}

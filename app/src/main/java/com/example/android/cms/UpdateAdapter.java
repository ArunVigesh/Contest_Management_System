package com.example.android.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import static com.example.android.cms.MainActivity.url_cms;
import static com.example.android.cms.StudentLogin.pid;
public class UpdateAdapter extends RecyclerView.Adapter<UpdateAdapter.MyViewHolder> {
    private List<eventdata> dataList;
    private Context context;
    Button upt;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView EId, EName,EDsc;
        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            EId = view.findViewById(R.id.textView25);
            EDsc = view.findViewById(R.id.textView26);
            EName =  view.findViewById(R.id.textView27);
            upt=view.findViewById(R.id.button10);
        }


    }
    public UpdateAdapter(List<eventdata> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_update_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final eventdata e =dataList.get(position);
        holder.EId.setText("Event Id : "+e.geteId());
        holder.EName.setText("Event Name : "+e.geteEventName());
        holder.EDsc.setText("Event Description : "+e.geteDescription());
        upt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context,ViewEvent.class);
                i.putExtra("eid",e.geteId());
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

}

package com.example.android.cms;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;
import static com.example.android.cms.StudentLogin.pid;

public class DataAdapterEvents extends RecyclerView.Adapter<DataAdapterEvents.MyViewHolder>{
    private List<data> dataList;
    Button Register;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView EventId, EventName,EventDsc;
        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            EventId = view.findViewById(R.id.textView15);
            EventDsc = view.findViewById(R.id.textView18);
            EventName =  view.findViewById(R.id.textView17);
            Register= view.findViewById(R.id.button6);
        }


    }
    public DataAdapterEvents(List<data> dataList) {
        this.dataList = dataList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_data_adapter_events, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final data d =dataList.get(position);
        holder.EventId.setText("Event Id : "+(d.geteId()));
        holder.EventName.setText("Event Name : "+d.geteEventName());
        holder.EventDsc.setText("Event Description : "+d.geteDescription());

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Registration").
                        setMessage("Register This Event");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                AsyncHttpClient client = new AsyncHttpClient();
                                RequestParams params = new RequestParams();
                                params.add( "eId", String.valueOf(d.geteId()));
                                params.add( "pId", String.valueOf(pid));
                                client.post( url_cms+"pEventReg.php", params, new AsyncHttpResponseHandler() {
                                    @Override
                                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                        try {
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));
                                            Log.e( "ER", new String( responseBody ) );
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        Intent intent=new Intent(context,StudentDisplay.class);
                                        Toast.makeText( context.getApplicationContext(), "REGISTERED", Toast.LENGTH_SHORT).show();
                                        context.startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                                    }

                                } );



                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
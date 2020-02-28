package com.example.android.cms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;
import static com.example.android.cms.StudentLogin.pid;
import static com.example.android.cms.ViewEvent.i;

public class UpdStudentAdapter extends RecyclerView.Adapter<UpdStudentAdapter.MyViewHolder> {
    private List<partUpd> dataList1;
    private Context context;
    String sc;
    Button submit;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView psid, fname, lname, col;
        EditText score;


        public MyViewHolder(View view) {
            super(view);
            context = view.getContext();
            psid = view.findViewById(R.id.textView19);
            fname = view.findViewById(R.id.textView20);
            lname = view.findViewById(R.id.textView21);
            col = view.findViewById(R.id.textView24);
            score = view.findViewById(R.id.editText9);
            submit = view.findViewById(R.id.button11);
        }


    }

    public UpdStudentAdapter(List<partUpd> dataList1) {
        this.dataList1 = dataList1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_upd_student_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {

        final partUpd p = dataList1.get(position);
        holder.psid.setText("Participant Id : " + p.getPsid());
        holder.fname.setText("First Name : " + p.getFname());
        holder.lname.setText("Last Name : " + p.getLname());
        holder.col.setText("College : " + p.getCol());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc = holder.score.getText().toString();
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add("eId", String.valueOf(i));
                params.add("pId", String.valueOf(p.getPsid()));
                params.add("eScore", sc);
                Log.e("TAG", "onClick: "+sc );
                client.post(url_cms + "partEventScore.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));
                            Log.e("ER", new String(responseBody));
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(context.getApplicationContext(), "UPDATED", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {


                    }
                });
            }



        });
    }
    @Override
    public int getItemCount() {
        return dataList1.size();
    }
}


package com.example.android.cms;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;

public class StudentDisplay extends AppCompatActivity {

    int i;
    TextView logout, profile, changepass;
    ArrayList dataList = new ArrayList<>();
    RecyclerView recycler_View;
    DataAdapterEvents mAdapter;



    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder( StudentDisplay.this );
        builder.setTitle( "Warning" ).
                setMessage( "You sure, that you want to logout?" );
        builder.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent i = new Intent( getApplicationContext(), MainActivity.class );
                startActivity( i );
                Toast.makeText( getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT ).show();
                finish();
            }
        } );
        builder.setNegativeButton( "No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        } );
        AlertDialog alert11 = builder.create();
        alert11.show();
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_student_display );
        Intent intent = getIntent();
        //i = intent.getIntExtra( "int_value", 0 );
        logout = findViewById( R.id.textView8 );
        recycler_View = findViewById( R.id.recyclerView );

        AsyncHttpClient myClient = new AsyncHttpClient();
        myClient.get(url_cms+"eList.php",new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));

                    Log.e("ER",new String(responseBody));
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();
                    JSONObject jsonObject = new JSONObject(new  String(responseBody));
                    JSONArray jsonArray = jsonObject.getJSONArray("eventlist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        String id = jsonobject.getString("eId");
                        //Log.e("TAG", "onSuccess: "+id );
                        String ename = jsonobject.getString("eEventName");
                        //Log.e("TAG", "onSuccess: "+ename );
                        String edesc= jsonobject.getString("eDescription");
                        //Log.e("TAG", "onSuccess: "+edesc );
                        data d = new data();
                        d.seteId(Integer.parseInt(id));
                        d.seteEventName(ename);
                        d.seteDescription(edesc);
                        dataList.add(d);
                    }
                    mAdapter = new DataAdapterEvents(dataList);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recycler_View.setLayoutManager(mLayoutManager);
                    recycler_View.setItemAnimator(new DefaultItemAnimator());
                    recycler_View.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText( getApplicationContext(), "Failed", Toast.LENGTH_SHORT ).show();
            }
        });




        mAdapter = new DataAdapterEvents(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_View.setLayoutManager(mLayoutManager);
        recycler_View.setItemAnimator(new DefaultItemAnimator());
        recycler_View.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();




        logout = findViewById( R.id.textView8 );
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder( StudentDisplay.this );
                builder.setTitle( "Warning" ).
                        setMessage( "You sure, that you want to logout?" );
                builder.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent i = new Intent( getApplicationContext(), StudentLogin.class );
                        startActivity( i );
                        Toast.makeText( getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT ).show();
                        finish();
                    }
                } );
                builder.setNegativeButton( "No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                } );
                AlertDialog alert11 = builder.create();
                alert11.show();

            }
        } );

        profile =findViewById(R.id.textView10);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(StudentDisplay.this, StudentProfile.class);
                intent2.putExtra("int_value",i);
                startActivity(intent2);
            }
        });
        changepass=findViewById(R.id.textView9);
        changepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDisplay.this, StudentPass.class);
                intent.putExtra("int_value", i);
                startActivity(intent);
            }
        });


    }
}
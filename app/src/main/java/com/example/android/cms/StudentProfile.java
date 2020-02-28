package com.example.android.cms;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;
import static com.example.android.cms.StudentLogin.pid;

public class StudentProfile extends AppCompatActivity {
    RecyclerView recycler_View,recycler_View1;
    StudentRegisteredEvents mAdapter;
    ArrayList dataList = new ArrayList<reg>();
    StudentEventsWon mAdapter1;
    ArrayList dataList1 = new ArrayList<won>();
    //int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        //Intent intent = getIntent();
        //i = intent.getIntExtra("int_value", 0);
        final TextView id, fname, lname, pemail, pphone, pcollege;
        id = findViewById(R.id.textView2);
        fname = findViewById(R.id.textView3);
        lname = findViewById(R.id.textView4);
        pemail = findViewById(R.id.textView7);
        pphone = findViewById(R.id.textView5);
        pcollege = findViewById(R.id.textView6);


        AsyncHttpClient myClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("pId", String.valueOf(pid));
        myClient.post(url_cms + "pProfile.php", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));
                    Log.e("ER", new String(responseBody));
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();
                    String namef = null;
                    String namel = null;
                    String email = null;
                    String phone = null;
                    String col = null;
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    JSONArray jsonArray = jsonObject.getJSONArray("getlist");
                    if (jsonArray.length() == 0)
                        Toast.makeText(getApplicationContext(), "Invalid Credentials ", Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        namef = jsonobject.getString("pFirstName");
                        namel = jsonobject.getString("pLasttName");
                        email = jsonobject.getString("pEmail");
                        phone = jsonobject.getString("pPhone");
                        col = jsonobject.getString("pCollege");

                    }
                    id.setText("Student ID : " + pid);
                    fname.setText("First Name : " + namef);
                    lname.setText("Last Name : " + namel);
                    pemail.setText("Email : " + email);
                    pphone.setText("Phone : " + phone);
                    pcollege.setText("College : " + col);


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


        FloatingActionButton button = findViewById(R.id.floatingActionButton4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(StudentProfile.this, StudentDisplay.class);
                //intent1.putExtra("int_value", i);
                startActivity(intent1);
            }
        });


        recycler_View = findViewById(R.id.recyclerView3);

        AsyncHttpClient myClient1 = new AsyncHttpClient();
        RequestParams params1 = new RequestParams();
        params1.add("pId", String.valueOf(pid));
        myClient1.post(url_cms + "eRegList.php", params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));

                    Log.e("ER", new String(responseBody));
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    JSONArray jsonArray = jsonObject.getJSONArray("eventlist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        String etime = jsonobject.getString("eTime");
                        //Log.e("TAG", "onSuccess: "+id );
                        String ename = jsonobject.getString("eEventName");
                        //Log.e("TAG", "onSuccess: "+ename );
                        String edesc = jsonobject.getString("eDescription");
                        //Log.e("TAG", "onSuccess: "+edesc );
                        String edate = jsonobject.getString("eDate");
                        reg r = new reg();
                        r.seteDescription(edesc);
                        r.seteEventName(ename);
                        r.seteDate(edate);
                        r.seteTime(etime);
                        dataList.add(r);
                    }
                    mAdapter = new StudentRegisteredEvents(dataList);
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
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });


        mAdapter = new StudentRegisteredEvents(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_View.setLayoutManager(mLayoutManager);
        recycler_View.setItemAnimator(new DefaultItemAnimator());
        recycler_View.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


        recycler_View1 = findViewById(R.id.recyclerView4);

        AsyncHttpClient myClient2 = new AsyncHttpClient();
        RequestParams params2 = new RequestParams();
        params2.add("pId", String.valueOf(pid));
        myClient2.post(url_cms + "wEvents.php", params2, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));

                    Log.e("ER", new String(responseBody));
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();
                    JSONObject jsonObject = new JSONObject(new String(responseBody));
                    JSONArray jsonArray = jsonObject.getJSONArray("wonlist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        String Name = jsonobject.getString("eEventName");
                        String Desc = jsonobject.getString("eDescription");
                        won w = new won();
                        w.setEname(Name);
                        w.setEdesc(Desc);
                        dataList1.add(w);
                    }
                    mAdapter1 = new StudentEventsWon(dataList1);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recycler_View1.setLayoutManager(mLayoutManager);
                    recycler_View1.setItemAnimator(new DefaultItemAnimator());
                    recycler_View1.setAdapter(mAdapter1);
                    mAdapter1.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }





    public void onBackPressed() {
        Intent intent1 = new Intent(StudentProfile.this, StudentDisplay.class);
        //intent1.putExtra("int_value", i);
        startActivity(intent1);
    }
}
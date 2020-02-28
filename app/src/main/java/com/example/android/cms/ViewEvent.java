package com.example.android.cms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;
import static com.example.android.cms.StudentLogin.pid;

public class ViewEvent extends AppCompatActivity {
    ArrayList dataList = new ArrayList<updJuges>();
    ArrayList dataList1 = new ArrayList<partUpd>();
    RecyclerView recycler_View,recycler_View1;
    UpdListAdapter mAdapter;
    UpdStudentAdapter mAdapters;
    public static int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_event );

        Intent intent=getIntent();
        i=intent.getIntExtra("eid",0);
        recycler_View = findViewById( R.id.recyclerView1 );
        AsyncHttpClient myClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("eId", String.valueOf(i));
        Log.e("TAG", "onCreate: "+i);
        myClient.post(url_cms+"jUpdList.php",params,new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));

                    Log.e("ER",new String(responseBody));
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();
                    JSONObject jsonObject = new JSONObject(new  String(responseBody));
                    JSONArray jsonArray = jsonObject.getJSONArray("judgelist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        String id = jsonobject.getString("jId");
                        //Log.e("TAG", "onSuccess: "+id );
                        String fname = jsonobject.getString("jFirstName");
                        //Log.e("TAG", "onSuccess: "+ename );
                        String lname= jsonobject.getString("jLastName");
                        String jspec= jsonobject.getString("jSpecialization");
                        //Log.e("TAG", "onSuccess: "+edesc );
                        updJuges j=new updJuges();
                        j.setjId(id);
                        j.setjFirstName(fname);
                        j.setjLastName(lname);
                        j.setjSpecilalization(jspec);
                        dataList.add(j);
                    }
                    mAdapter = new UpdListAdapter(dataList);
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




        /*mAdapter = new UpdListAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_View.setLayoutManager(mLayoutManager);
        recycler_View.setItemAnimator(new DefaultItemAnimator());
        recycler_View.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();*/


        recycler_View1 = findViewById( R.id.recyclerView2);
        AsyncHttpClient myClient1 = new AsyncHttpClient();
        RequestParams params1 = new RequestParams();
        params1.add("eId", String.valueOf(i));
        Log.e("TAG", "onCreate: "+i);
        myClient1.post(url_cms+"eventPart.php",params1,new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));

                    Log.e("ER",new String(responseBody));
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();
                    JSONObject jsonObject = new JSONObject(new  String(responseBody));
                    JSONArray jsonArray = jsonObject.getJSONArray("partlist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        String id = jsonobject.getString("pId");
                        //Log.e("TAG", "onSuccess: "+id );
                        String fname = jsonobject.getString("pFirstName");
                        //Log.e("TAG", "onSuccess: "+ename );
                        String lname= jsonobject.getString("pLasttName");
                        String col= jsonobject.getString("pCollege");
                        //Log.e("TAG", "onSuccess: "+edesc );
                        partUpd p=new partUpd();
                        p.setPsid(Integer.parseInt(id));
                        p.setFname(fname);
                        p.setLname(lname);
                        p.setCol(col);
                        dataList1.add(p);
                    }
                    mAdapters = new UpdStudentAdapter(dataList1);
                    RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
                    recycler_View1.setLayoutManager(mLayoutManager1);
                    recycler_View1.setItemAnimator(new DefaultItemAnimator());
                    recycler_View1.setAdapter(mAdapters);
                    mAdapters.notifyDataSetChanged();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText( getApplicationContext(), "Failed", Toast.LENGTH_SHORT ).show();
            }
        });




        /*mAdapters = new UpdStudentAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_View1.setLayoutManager(mLayoutManager);
        recycler_View1.setItemAnimator(new DefaultItemAnimator());
        recycler_View1.setAdapter(mAdapters);
        mAdapters.notifyDataSetChanged();*/



    }
}

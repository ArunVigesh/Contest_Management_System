package com.example.android.cms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;

public class ViewOrganiserEvent extends AppCompatActivity {
    ArrayList dataList = new ArrayList<>();
    RecyclerView recycler_View;
    EventListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_organiser_event );


        recycler_View = findViewById( R.id.recyclerView5 );
        AsyncHttpClient myClient = new AsyncHttpClient();
        myClient.get(url_cms+"eOList.php",new AsyncHttpResponseHandler() {

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
                        String edate= jsonobject.getString("eDate");
                        String etime= jsonobject.getString("eTime");
                        //Log.e("TAG", "onSuccess: "+edesc );
                        events e=new events();
                        e.setEid(id);
                        e.setEname(ename);
                        e.setEdesc(edesc);
                        e.setEdate(edate);
                        e.setEtime(etime);
                        dataList.add(e);
                    }
                    mAdapter = new EventListAdapter(dataList);
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




        mAdapter = new EventListAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_View.setLayoutManager(mLayoutManager);
        recycler_View.setItemAnimator(new DefaultItemAnimator());
        recycler_View.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();



    }
}

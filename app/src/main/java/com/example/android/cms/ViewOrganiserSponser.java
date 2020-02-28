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

public class ViewOrganiserSponser extends AppCompatActivity {
    ArrayList dataList = new ArrayList<>();
    RecyclerView recycler_View;
    SponsorListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_organiser_sponser );


        recycler_View = findViewById( R.id.recyclerView6 );
        AsyncHttpClient myClient = new AsyncHttpClient();
        myClient.get(url_cms+"sOList.php",new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));

                    Log.e("ER",new String(responseBody));
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();
                    JSONObject jsonObject = new JSONObject(new  String(responseBody));
                    JSONArray jsonArray = jsonObject.getJSONArray("slist");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        String id = jsonobject.getString("sId");
                        //Log.e("TAG", "onSuccess: "+id );
                        String sname = jsonobject.getString("sName");
                        //Log.e("TAG", "onSuccess: "+ename );
                        sponsors s=new sponsors();
                        s.setSid(id);
                        s.setSname(sname);
                        dataList.add(s);
                    }
                    mAdapter = new SponsorListAdapter(dataList);
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




        mAdapter = new SponsorListAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_View.setLayoutManager(mLayoutManager);
        recycler_View.setItemAnimator(new DefaultItemAnimator());
        recycler_View.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();



    }
}

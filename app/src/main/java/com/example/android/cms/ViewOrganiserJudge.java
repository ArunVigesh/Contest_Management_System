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

public class ViewOrganiserJudge extends AppCompatActivity {
    ArrayList dataList = new ArrayList<>();
    RecyclerView recycler_View;
    JudgeListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_view_organiser_judge );


        recycler_View = findViewById( R.id.recyclerView6 );
        AsyncHttpClient myClient = new AsyncHttpClient();
        myClient.get(url_cms+"jOList.php",new AsyncHttpResponseHandler() {

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
                        String jFirstName = jsonobject.getString("jFirstName");
                        String jLastName = jsonobject.getString("jLastName");
                        String jPhone = jsonobject.getString("jPhone");
                        String jEmail = jsonobject.getString("jEmail");
                        String jSpecilalization = jsonobject.getString("jSpecialization");

                        judges j=new judges();
                        j.setjId(id);
                        j.setjFirstName(jFirstName);
                        j.setjLastName(jLastName);
                        j.setjPhone(jPhone);
                        j.setjEmail(jEmail);
                        j.setjSpecilalization(jSpecilalization);

                        dataList.add(j);
                    }
                    mAdapter = new JudgeListAdapter(dataList);
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




        mAdapter = new JudgeListAdapter(dataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_View.setLayoutManager(mLayoutManager);
        recycler_View.setItemAnimator(new DefaultItemAnimator());
        recycler_View.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();



    }
}

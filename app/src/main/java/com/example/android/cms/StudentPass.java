package com.example.android.cms;

import android.app.ProgressDialog;
import android.content.Intent;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;
import static com.example.android.cms.StudentLogin.pid;

public class StudentPass extends AppCompatActivity {

    private ProgressDialog progressDialog;
    int i;
    Button button;
    EditText pass=null,cnfpass=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        progressDialog = new ProgressDialog(this);
        i = intent.getIntExtra("int_value", 0);
        setContentView(R.layout.activity_student_pass);
        pass=findViewById(R.id.editText1);
        cnfpass=findViewById(R.id.editText2);
        FloatingActionButton button1 = findViewById(R.id.floatingActionButton);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StudentPass.this, StudentDisplay.class);
                intent.putExtra("int_value", i);
                startActivity(intent);
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Changing password...");
                progressDialog.show();
                if ((pass.getText().toString().equals(cnfpass.getText().toString()))&&(pass.getText().toString().length()>=6)) {
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.add("pId", Integer.toString(pid));
                    params.add("pPassword", pass.getText().toString());
                    client.post(url_cms + "pPassChange.php", params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            try {
                                progressDialog.dismiss();
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));
                                Log.e("ER", new String(responseBody));
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(), "Password Changed ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(StudentPass.this, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }

                    });


                }
                else

                {
                    Toast.makeText(getApplicationContext(), "Password Don't Match ", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}

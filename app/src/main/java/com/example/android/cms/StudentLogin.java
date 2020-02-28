package com.example.android.cms;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;


public class StudentLogin extends AppCompatActivity {

    private ProgressDialog progressDialog;
    public static final String PREFS_NAME = "PrefFile";
    private static final String PREF_EMAIL = "Email";
    private static final String PREF_PASS = "Password";
    EditText password=null, email=null;
    int in=0;
    boolean c,n,em;
    public static int pid;
    private static final String PREF_ISCHECKED = "IsChecked";
    public CheckBox remember;

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    public void onBackPressed() {
        SharedPreferences pref;
        SharedPreferences.Editor ed;
        pref=getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        ed=pref.edit();
        if(remember.isChecked()) {
            Boolean boolisChecked=remember.isChecked();
            ed.putString(PREF_EMAIL, email.getText().toString());
            ed.putString(PREF_PASS, password.getText().toString());
            ed.putBoolean(PREF_ISCHECKED, boolisChecked);
            ed.apply();
        }
        else
        {
            pref.edit().clear().apply();
        }
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                            Intent intent1 = new Intent(StudentLogin.this, MainActivity.class);
                            startActivity(intent1);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_student_login );
        Button Login = findViewById( R.id.button2 );
        Button Reg = findViewById( R.id.button3 );
        progressDialog = new ProgressDialog(this);
        String username,Password;
        email = findViewById(R.id.editText7);
        password = findViewById(R.id.editText8);
        remember=findViewById(R.id.checkBox);

        SharedPreferences spref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);


        if(spref.contains(PREF_EMAIL)) {
            username = spref.getString(PREF_EMAIL, "");
            email.setText(username);
        }
        if(spref.contains((PREF_PASS))) {
            Password = spref.getString(PREF_PASS, "");
            password.setText(Password);
        }
        if(spref.contains((PREF_ISCHECKED)))
        {
            Boolean c=spref.getBoolean(PREF_ISCHECKED,false);
            remember.setChecked(c);
        }



        Reg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent( StudentLogin.this, StudentRegister.class );
                startActivity( i );
            }
        } );

        Login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Logging in...");
                progressDialog.show();
                AsyncHttpClient myClient = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add("pEmail",email.getText().toString());
                params.add("pPassword",password.getText().toString());


                myClient.post(url_cms+"pLogin.php",params,new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            progressDialog.dismiss();
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));
                            Log.e("ER",new String(responseBody));
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();

                            JSONObject jsonObject = new JSONObject(new  String(responseBody));
                            JSONArray jsonArray = jsonObject.getJSONArray("getlist");
                            if(jsonArray.length()==0)
                                Toast.makeText( getApplicationContext(), "Invalid Credentials ", Toast.LENGTH_SHORT ).show();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonobject = jsonArray.getJSONObject(i);
                                String id = jsonobject.getString("pId");
                                pid=Integer.parseInt(id);
                                Log.e("ID", "onSuccess: "+pid);
                            }
                            n = notNull();
                            if (n) {
                                em = checkMail();
                                if (em) {
                                    Toast.makeText( getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT ).show();
                                    Intent i = new Intent( StudentLogin.this, StudentDisplay.class );
                                    startActivity( i );
                                    //i.putExtra("int_value",id);
                                    email.getText().clear();
                                    password.getText().clear();
                                }

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText( getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT ).show();
                    }
                });



            }
        } );

    }

    public boolean checkMail()
    {
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
        {

            return true;

        }
        else
        {
            email.setError("Invalid Mail Id");
            return false;
        }
    }

    public boolean notNull()
    {
        if((!email.getText().toString().isEmpty())&&(!password.getText().toString().isEmpty()))
        {

                return true;

        }
        else {
            if(email.getText().toString().isEmpty())
                email.setError("Field Empty");
            if(password.getText().toString().isEmpty())
                password.setError("Field Empty");


            return false;
        }
    }


    }

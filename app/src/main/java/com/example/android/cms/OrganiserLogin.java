package com.example.android.cms;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;

public class OrganiserLogin extends AppCompatActivity {

    private ProgressDialog progressDialog;
    public static final String PREFS_NAMEO = "PrefFileO";
    private static final String PREF_EMAILO = "EmailO";
    private static final String PREF_PASSO = "PasswordO";
    EditText password=null, email=null;
    int in=0;
    boolean c,n;
    private static final String PREF_ISCHECKEDO = "IsCheckedO";
    public CheckBox remember;

String id;
    public void onBackPressed() {

        SharedPreferences prefO;
        SharedPreferences.Editor edO;
        prefO=getSharedPreferences(PREFS_NAMEO, MODE_PRIVATE);
        edO=prefO.edit();
        if(remember.isChecked()) {
            Boolean boolisChecked=remember.isChecked();
            edO.putString(PREF_EMAILO, email.getText().toString());
            edO.putString(PREF_PASSO, password.getText().toString());
            edO.putBoolean(PREF_ISCHECKEDO, boolisChecked);
            edO.apply();
        }
        else
        {
            prefO.edit().clear().apply();
        }
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent1 = new Intent(OrganiserLogin.this, MainActivity.class);
                        startActivity(intent1);
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this);

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_organiser_login );
        Button Login = findViewById( R.id.button2 );
        Button Reg = findViewById( R.id.button3 );
        String usernameO,PasswordO;
        email = findViewById(R.id.editText7);
        password = findViewById(R.id.editText8);
        remember=findViewById(R.id.checkBox);

        SharedPreferences sprefo = getSharedPreferences(PREFS_NAMEO, MODE_PRIVATE);


        if(sprefo.contains(PREF_EMAILO)) {
            usernameO = sprefo.getString(PREF_EMAILO, "");
            email.setText(usernameO);
        }
        if(sprefo.contains((PREF_PASSO))) {
            PasswordO = sprefo.getString(PREF_PASSO, "");
            password.setText(PasswordO);
        }
        if(sprefo.contains((PREF_ISCHECKEDO)))
        {
            Boolean c=sprefo.getBoolean(PREF_ISCHECKEDO,false);
            remember.setChecked(c);
        }
        Reg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( OrganiserLogin.this, OrganiserRegister.class );
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
                params.add("oEmail",email.getText().toString());
                params.add("oPassword",password.getText().toString());
                myClient.post(url_cms+"oLogin.php",params,new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try { progressDialog.dismiss();
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));

                            Log.e("ER",new String(responseBody));
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();

                            JSONObject jsonObject = new JSONObject(new  String(responseBody));
                            JSONArray jsonArray = jsonObject.getJSONArray("getlist");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonobject = jsonArray.getJSONObject(i);
                                id = jsonobject.getString("oId");
                                Log.e("ID", "onSuccess: "+id);
                            }
                            n = notNull();
                            if (n) {
                                Toast.makeText( getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT ).show();
                                Intent i = new Intent( OrganiserLogin.this, OrganiserDisplay.class );
                                startActivity( i );
                                //i.putExtra("i0nt_value",id);
                                email.getText().clear();
                                password.getText().clear();
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


        email = findViewById(R.id.editText7);
        password = findViewById(R.id.editText8);

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

package com.example.android.cms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.simple.parser.JSONParser;

import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;

public class StudentRegister extends AppCompatActivity {

    private ProgressDialog progressDialog;
    EditText pid=null,pFirstName=null,pLastName=null, pPhone=null,pPass=null, pPassword=null, pCollege=null, pEmail=null;
    Button button;
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();

    private static final String TAG_SUCCESS = "success";
    boolean c,n,e;

    public void onBackPressed() {
        Intent intent1 = new Intent(StudentRegister.this, StudentLogin.class);
        startActivity(intent1);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Registering ...");
                progressDialog.show();

                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add( "pId", pid.getText().toString() );
                params.add( "pFirstName", pFirstName.getText().toString() );
                params.add( "pLastName", pLastName.getText().toString() );
                params.add( "pPhone", pPhone.getText().toString() );
                params.add( "pEmail", pEmail.getText().toString() );
                params.add( "pCollege", pCollege.getText().toString() );
                params.add( "pPassword", pPassword.getText().toString() );
                client.post( url_cms+"pRegister.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
                            String no=pPhone.getText().toString();
                            String msg=" Dear "+pFirstName+" "+pLastName+" Thank you for registering as a participant with us!!Your Participant ID is: " + pid + ". Looking forward to serving you soon!";
                            SmsManager sms= SmsManager.getDefault();
                            sms.sendTextMessage(no, null, msg, null,null);
                            progressDialog.dismiss();
//                            JSONObject jsonObject = new JSONObject(new String(responseBody));
                            Log.e( "ER", new String( responseBody ) );
//                            Toast.makeText(MainActivity.this,new String(responseBody),Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }

                } );

                n = notNull();
                if (n) {
                    c = checkPass();
                    if (c) {
                        e = checkMail();
                        if (e) {


                            Toast.makeText( getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT ).show();
                            Intent intent = new Intent( StudentRegister.this, StudentLogin.class );
                            String no=pPhone.getText().toString();
                            String fn=pFirstName.getText().toString();
                            String ln=pLastName.getText().toString();
                            String id=pid.getText().toString();

                            String msg=" Dear "+fn+" "+ln+" Thank you for registering as a participant with us!!Your Participant ID is: " + id + ". Looking forward to serving you soon!";
                            SmsManager sms= SmsManager.getDefault();
                            sms.sendTextMessage(no, null, msg, null,null);
                            startActivity( intent );
                            pid.getText().clear();
                            pFirstName.getText().clear();
                            pLastName.getText().clear();
                            pEmail.getText().clear();
                            pPhone.getText().clear();
                            pCollege.getText().clear();
                            pPass.getText().clear();
                            pPassword.getText().clear();
                        }
                    }
                }
            }
        });

        pid = findViewById(R.id.editText1);
        pFirstName = findViewById(R.id.editText2);
        pLastName = findViewById(R.id.editText3);
        pEmail = findViewById(R.id.editText4);
        pPhone = findViewById(R.id.editText5);
        pCollege = findViewById(R.id.editText6);
        pPass = findViewById(R.id.editText7);
        pPassword = findViewById(R.id.editText8);
    }

    public boolean checkPass()
    {
        EditText pass,cnfpass;
        pass=findViewById(R.id.editText7);
        cnfpass=findViewById(R.id.editText8);
        if(pass.getText().toString().equals(cnfpass.getText().toString()))
        {
            if(cnfpass.getText().toString().length()>=6)
                return true;
            else
            {
                pPassword.setError("Weak Password");
                return false;
            }
        }
        else {
            pass.getText().clear();
            cnfpass.getText().clear();
            Toast.makeText(getApplicationContext(), "Passwords Don't Match", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkMail()
    {
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(pEmail.getText().toString()).matches())
        {

                return true;

        }
        else
        {
            pEmail.setError("Invalid Mail Id");
            return false;
        }
    }

    public boolean checkName()
    {
        if(Pattern.matches("[a-zA-Z. ]+", pFirstName.getText().toString()))
            return true;
        else
            return false;
    }

    public boolean checkPhone()
    {
        if(Pattern.matches("[0-9]+", pPhone.getText().toString())&&pPhone.getText().toString().length()==10)
        {
            return true;
        }
        else
        {
            pPhone.setError("Invalid Phone Number");
            return false;
        }
    }



    public boolean notNull()
    {
        if((!pid.getText().toString().isEmpty())&&(!pFirstName.getText().toString().isEmpty())&&(!pEmail.getText().toString().isEmpty())&&(!pPhone.getText().toString().isEmpty())&&(!pPass.getText().toString().isEmpty())&&(!pPassword.getText().toString().isEmpty()))
        {
            if((checkName())&&(checkPass())&&(checkPhone()))
                return true;
            else {
                return false;
            }
        }
        else {
            if(pid.getText().toString().isEmpty())
                pid.setError("Field Empty");
            if(pFirstName.getText().toString().isEmpty())
                pFirstName.setError("Field Empty");
            if(pEmail.getText().toString().isEmpty())
                pEmail.setError("Field Empty");
            if(pPhone.getText().toString().isEmpty())
                pPhone.setError("Field Empty");
            if (pPass.getText().toString().isEmpty())
                pPass.setError("Field Empty");
            if (pPassword.getText().toString().isEmpty())
                pPassword.setError("Field Empty");

            return false;
        }
    }


}

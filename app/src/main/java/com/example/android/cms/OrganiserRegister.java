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

import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;

public class OrganiserRegister extends AppCompatActivity {

    private ProgressDialog progressDialog;
    EditText oid=null,oFirstName=null,oLastName=null, oPhone=null,oPass=null, oPassword=null, oEmail=null;
    Button button;
    boolean c,n,e;
    @Override

    public void onBackPressed() {
        Intent intent1 = new Intent(OrganiserRegister.this, OrganiserLogin.class);
        startActivity(intent1);
    }
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organiser_register);
        button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Registering ...");
                progressDialog.show();
                oid = findViewById(R.id.editText1);
                oFirstName = findViewById(R.id.editText2);
                oLastName = findViewById(R.id.editText3);
                oEmail = findViewById(R.id.editText4);
                oPhone = findViewById(R.id.editText5);
                oPass = findViewById(R.id.editText7);
                oPassword = findViewById(R.id.editText8);
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add("oId",oid.getText().toString());
                params.add("oFirstName",oFirstName.getText().toString());
                params.add("oLastName",oLastName.getText().toString());
                params.add("oPhone",oPhone.getText().toString());
                params.add("oEmail",oEmail.getText().toString());
                params.add("oPassword",oPassword.getText().toString());
                client.post(url_cms+"oRegister.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
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

                });
                n = notNull();
                if (n) {
                    c = checkPass();
                    if (c) {
                        e = checkMail();
                        if (e) {
                            String no=oPhone.getText().toString();
                            String fn=oFirstName.getText().toString();
                            String ln=oLastName.getText().toString();
                            String id=oid.getText().toString();

                            String msg=" Dear "+fn+" "+ln+" Thank you for registering as an Organiser with us!!Your Organiser ID is: " + id + ". Looking forward to serving you soon!";
                            SmsManager sms= SmsManager.getDefault();
                            sms.sendTextMessage(no, null, msg, null,null);
                            Toast.makeText( getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT ).show();
                            Intent intent = new Intent( OrganiserRegister.this, OrganiserLogin.class );
                            startActivity( intent );
                            oid.getText().clear();
                            oFirstName.getText().clear();
                            oLastName.getText().clear();
                            oEmail.getText().clear();
                            oPhone.getText().clear();
                            oPass.getText().clear();
                            oPassword.getText().clear();
                        }
                    }
                }
            }
        });

    }

    public boolean checkMail()
    {
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(oEmail.getText().toString()).matches())
        {

            return true;

        }
        else
        {
            oEmail.setError("Invalid Mail Id");
            return false;
        }
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
                oPassword.setError("Weak Password");
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

    public boolean checkName()
    {
        if(Pattern.matches("[a-zA-Z. ]+", oFirstName.getText().toString()))
            return true;
        else
            return false;
    }

    public boolean checkPhone()
    {
        if(Pattern.matches("[0-9]+", oPhone.getText().toString())&&oPhone.getText().toString().length()==10)
        {
            return true;
        }
        else
        {
            oPhone.setError("Invalid Phone Number");
            return false;
        }
    }

    public boolean notNull()
    {
        if((!oid.getText().toString().isEmpty())&&(!oFirstName.getText().toString().isEmpty())&&(!oEmail.getText().toString().isEmpty())&&(!oPhone.getText().toString().isEmpty())&&(!oPass.getText().toString().isEmpty())&&(!oPassword.getText().toString().isEmpty()))
        {
            if((checkName())&&(checkPass())&&(checkPhone()))
                return true;
            else {
                return false;
            }
        }
        else {
            if(oid.getText().toString().isEmpty())
                oid.setError("Field Empty");
            if(oFirstName.getText().toString().isEmpty())
                oFirstName.setError("Field Empty");
            if(oEmail.getText().toString().isEmpty())
                oEmail.setError("Field Empty");
            if(oPhone.getText().toString().isEmpty())
                oPhone.setError("Field Empty");
            if (oPass.getText().toString().isEmpty())
                oPass.setError("Field Empty");
            if (oPassword.getText().toString().isEmpty())
                oPassword.setError("Field Empty");

            return false;
        }
    }

}

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

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.simple.parser.JSONParser;

import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;

public class AddJudge extends AppCompatActivity {

    EditText jId=null,jFirstName=null,jLastName=null, jPhone=null, jEmail=null,jSpecilazation=null, eId=null;
    Button button;
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    //public static String url_create_product ="https://e19065d7.ngrok.io/cms/jRegister.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    boolean c,n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_judge);
        button = findViewById(R.id.button1);
        jId = findViewById(R.id.editText1);
        jFirstName = findViewById(R.id.editText2);
        jLastName = findViewById(R.id.editText3);
        jPhone = findViewById(R.id.editText4);
        jEmail = findViewById(R.id.editText5);
        jSpecilazation = findViewById(R.id.editText6);
        eId=findViewById(R.id.editText7);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add( "jId", jId.getText().toString() );
                params.add( "jFirstName", jFirstName.getText().toString() );
                params.add( "jLastName", jLastName.getText().toString() );
                params.add( "jPhone", jPhone.getText().toString() );
                params.add( "jEmail", jEmail.getText().toString() );
                params.add( "jSpecialization", jSpecilazation.getText().toString() );
                params.add( "eId", eId.getText().toString() );
                client.post( url_cms+"jRegister.php", params, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        try {
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
                    Toast.makeText( getApplicationContext(), "Judge Added Successfully", Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent( AddJudge.this, OrganiserDisplay.class );
                    startActivity( intent );
                    jId.getText().clear();
                    jFirstName.getText().clear();
                    jLastName.getText().clear();
                    jEmail.getText().clear();
                    jPhone.getText().clear();
                    eId.getText().clear();
                    jSpecilazation.getText().clear();
                }
            }
        });
    }

    public boolean checkName()
    {
        if(Pattern.matches("[a-zA-Z. ]+", jFirstName.getText().toString()))
            return true;
        else
            return false;
    }

    public boolean checkPhone()
    {
        if(Pattern.matches("[0-9]+", jPhone.getText().toString())&&jPhone.getText().toString().length()==10)
        {
            return true;
        }
        else
        {
            jPhone.setError("Invalid Phone Number");
            return false;
        }
    }

    public boolean notNull()
    {
        if((!jId.getText().toString().isEmpty())&&(!jFirstName.getText().toString().isEmpty())&&(!jLastName.getText().toString().isEmpty())&&(!jEmail.getText().toString().isEmpty())&&(!jPhone.getText().toString().isEmpty())&&(!jSpecilazation.getText().toString().isEmpty()))
        {
            if((checkName())&&(checkPhone()))
                return true;
            else {
                return false;
            }
        }
        else {
            if(jId.getText().toString().isEmpty())
                jId.setError("Field Empty");
            if(eId.getText().toString().isEmpty())
                eId.setError("Field Empty");
            if(jFirstName.getText().toString().isEmpty())
                jFirstName.setError("Field Empty");
            if(jLastName.getText().toString().isEmpty())
                jLastName.setError("Field Empty");
            if(jEmail.getText().toString().isEmpty())
                jEmail.setError("Field Empty");
            if (jPhone.getText().toString().isEmpty())
                jPhone.setError("Field Empty");
            if (jSpecilazation.getText().toString().isEmpty())
                jSpecilazation.setError("Field Empty");



            return false;
        }
    }
}

package com.example.android.cms;

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

import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;

public class AddSponser extends AppCompatActivity {

    EditText sId=null,sName=null,eId=null;
    Button button;
    //public static String url_create_product ="https://e19065d7.ngrok.io/cms/sRegister.php";
    boolean c,n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sponser);
        button = findViewById(R.id.button1);
        sId = findViewById(R.id.editText1);
        sName = findViewById(R.id.editText2);
        eId=findViewById(R.id.editText7);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add( "sId", sId.getText().toString() );
                params.add( "sName", sName.getText().toString() );
                params.add( "eId", eId.getText().toString() );
                client.post( url_cms+"sRegister.php", params, new AsyncHttpResponseHandler() {
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
                    Toast.makeText( getApplicationContext(), "Sponsor Added Successfully", Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent( AddSponser.this, OrganiserDisplay.class );
                    startActivity( intent );
                    sId.getText().clear();
                    sName.getText().clear();
                }
            }
        });
    }

    public boolean checkName()
    {
        if(Pattern.matches("[a-zA-Z. ]+", sName.getText().toString()))
            return true;
        else
            return false;
    }

    public boolean notNull()
    {
        if((!sId.getText().toString().isEmpty())&&(!sName.getText().toString().isEmpty()))
        {
            if((checkName()))
                return true;
            else {
                return false;
            }
        }
        else {
            if(sId.getText().toString().isEmpty())
                sId.setError("Field Empty");
            if(sName.getText().toString().isEmpty())
                sName.setError("Field Empty");


            return false;
        }
    }
}

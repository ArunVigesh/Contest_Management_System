package com.example.android.cms;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.simple.parser.JSONParser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;

import static com.example.android.cms.MainActivity.url_cms;

public class AddEvent extends AppCompatActivity {

    private Calendar myCalendar = Calendar.getInstance();
    EditText EventId=null,EventName=null, EventDsc=null,EventTime=null,EventDate=null,Prize=null,goodies=null,Vouchers=null;
    Button button;
    ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    //public static String url_create_product ="https://e19065d7.ngrok.io/cms/eRegister.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    boolean c,n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        button = findViewById(R.id.button1);
        EventId = findViewById(R.id.editText1);
        EventName = findViewById(R.id.editText2);
        EventDsc = findViewById(R.id.editText3);
        EventTime = findViewById(R.id.editText4);
        EventDate = findViewById(R.id.editText5);
        Prize = findViewById(R.id.editText6);
        goodies = findViewById(R.id.editText7);
        Vouchers = findViewById(R.id.editText8);

        final DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy/MM/dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                java.util.Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
                EventDate.setText(sdf.format(myCalendar.getTime()));
        }
        };

        EventDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddEvent.this, datePickerListener, myCalendar
                        .get( Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        EventTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddEvent.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        EventTime.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.add( "eId", EventId.getText().toString() );
                params.add( "eEventName", EventName.getText().toString() );
                params.add( "eDescription", EventDsc.getText().toString() );
                params.add( "eTime", EventTime.getText().toString() );
                params.add( "eDate", EventDate.getText().toString() );
                params.add( "ePrizeMoney", Prize.getText().toString() );
                params.add( "eGoodies", goodies.getText().toString() );
                params.add( "eVouchers", Vouchers.getText().toString() );
                client.post( url_cms+"eRegister.php", params, new AsyncHttpResponseHandler() {
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
                    Toast.makeText( getApplicationContext(), "EVENT Added Successfully", Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent( AddEvent.this, OrganiserDisplay.class );
                    startActivity( intent );
                    EventId.getText().clear();
                    EventName.getText().clear();
                    EventDsc.getText().clear();
                    EventTime.getText().clear();
                    EventDate.getText().clear();
                    Prize.getText().clear();
                    goodies.getText().clear();
                    Vouchers.getText().clear();
                }
            }
        });
    }

    public boolean checkName()
    {
        if(Pattern.matches("[a-zA-Z. ]+", EventName.getText().toString()))
            return true;
        else
            return false;
    }

    public boolean notNull()
    {
        if((!EventId.getText().toString().isEmpty())&&(!EventName.getText().toString().isEmpty())&&(!EventDsc.getText().toString().isEmpty())&&(!EventDate.getText().toString().isEmpty())&&(!EventTime.getText().toString().isEmpty())&&(!Prize.getText().toString().isEmpty())&&(!goodies.getText().toString().isEmpty())&&(!Vouchers.getText().toString().isEmpty()))
        {
            if((checkName()))
                return true;
            else {
                return false;
            }
        }
        else {
            if(EventId.getText().toString().isEmpty())
                EventId.setError("Field Empty");
            if(EventName.getText().toString().isEmpty())
                EventName.setError("Field Empty");
            if(EventDsc.getText().toString().isEmpty())
                EventDsc.setError("Field Empty");
            if(EventTime.getText().toString().isEmpty())
                EventTime.setError("Field Empty");
            if (EventDate.getText().toString().isEmpty())
                EventDate.setError("Field Empty");
            if (Prize.getText().toString().isEmpty())
                Prize.setError("Field Empty");
            if (goodies.getText().toString().isEmpty())
                goodies.setError("Field Empty");
            if (Vouchers.getText().toString().isEmpty())
                Vouchers.setError("Field Empty");



            return false;
        }
    }
}

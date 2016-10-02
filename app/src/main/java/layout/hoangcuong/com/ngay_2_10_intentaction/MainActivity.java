package layout.hoangcuong.com.ngay_2_10_intentaction;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText txtPhoneNum, txtSMS, txtHourm, txtMinute;
    private Button btnCall, btnSend, btnAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPhoneNum = (EditText) findViewById(R.id.txtPhoneNumber);
        btnCall = (Button) findViewById(R.id.btnCall);
        txtSMS = (EditText) findViewById(R.id.txtSMS);
        btnSend = (Button) findViewById(R.id.btnSMS);
        txtHourm = (EditText) findViewById(R.id.txtHour);
        txtMinute = (EditText) findViewById(R.id.txtMinute);
        btnAlarm = (Button) findViewById(R.id.btnAlarm);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***
                 *  Call
                 * **/
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + txtPhoneNum.getText().toString()));
                intent.setClassName("com.android.phone", "com.android.phone.OutgoingCallBroadcaster");

                try{
                    startActivity(intent);
                }catch (ActivityNotFoundException ax){

                }
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+ txtPhoneNum.getText()));
                intent.putExtra("sms_body",txtSMS.getText().toString());
                startActivity(intent);
            }
        });
        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                intent.putExtra(AlarmClock.EXTRA_HOUR, Integer.parseInt(txtHourm.getText().toString()));
                intent.putExtra(AlarmClock.EXTRA_MINUTES, Integer.parseInt(txtMinute.getText().toString()));
                startActivity(intent);
            }
        });
    }
}

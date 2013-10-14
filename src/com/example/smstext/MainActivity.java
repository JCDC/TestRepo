package com.example.smstext;
 
import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
public class MainActivity extends Activity {
 
	Button buttonSend;
	EditText textPhoneNo;
	EditText textSMS;
	private TextView mAnswerLabel;
	private String mPhoneNo;
	private String mSms;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
 
		buttonSend = (Button) findViewById(R.id.buttonSend);
		textPhoneNo = (EditText) findViewById(R.id.editTextPhoneNo);
		textSMS = (EditText) findViewById(R.id.editTextSMS);
 
		buttonSend.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View v) {
 
			  mPhoneNo = textPhoneNo.getText().toString();
			  mSms = textSMS.getText().toString();
			  
			  new CountDownTimer(4000, 1000) {

				     public void onTick(long millisUntilFinished) {
				    	 mAnswerLabel = (TextView)findViewById(R.id.textView1);
				    	 mAnswerLabel.setText("seconds remaining: " + millisUntilFinished / 1000);
				     }

				     public void onFinish() {
						  try {
	
								SmsManager smsManager = SmsManager.getDefault();
								smsManager.sendTextMessage(mPhoneNo, null, mSms, null, null);
								Toast.makeText(getApplicationContext(), "SMS Sent!",
											Toast.LENGTH_LONG).show();
							  } catch (Exception e) {
								Toast.makeText(getApplicationContext(),
									"SMS faild, please try again later!",
									Toast.LENGTH_LONG).show();
								e.printStackTrace();
							  }
				 
				     }
				     
				  }.start();
 

 
			}
		});
	}
}
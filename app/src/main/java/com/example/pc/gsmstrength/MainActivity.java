package com.example.pc.gsmstrength;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    TelephonyManager Tel;
    MyPhoneStateListener MyListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListener = new MyPhoneStateListener();
        Tel = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        Button button = (Button)findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Tel.listen(MyListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
            }

        });


    }

    private class MyPhoneStateListener extends PhoneStateListener {

        @Override
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            Toast.makeText(getApplicationContext(), "GSM Cinr = "
                    + String.valueOf(signalStrength.getGsmSignalStrength()), Toast.LENGTH_SHORT).show();

        }
    }

}

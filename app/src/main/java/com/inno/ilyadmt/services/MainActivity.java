package com.inno.ilyadmt.services;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver br;
    Button button;
    EditText numberPicker;
    MyService myService;
    ServiceConnection sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        numberPicker = (EditText) findViewById(R.id.numberPicker);
        IntentFilter intentFilter = new IntentFilter("myBroadcast");
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(MainActivity.this,
                        intent.getStringExtra("factorial"),
                        Toast.LENGTH_LONG).show();
            }
        };
        registerReceiver(br, intentFilter);

    }

    public void count(View view) {
        int factorial = Integer.parseInt(numberPicker.getText().toString());
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("factorial", factorial);
        startService(intent);
        sc = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MyService.MyBinder binder = (MyService.MyBinder) iBinder;
                myService = binder.getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                myService = null;
            }
        };
        bindService(intent, sc, 0);
    }

    public void update(View view) {
        if(myService != null && sc != null){
            Toast.makeText(this, myService.result+"", Toast.LENGTH_LONG).show();
        }
    }

    public void unbind(View view) {
        if (sc!=null) unbindService(sc);
    }
}

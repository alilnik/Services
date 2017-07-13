package com.inno.ilyadmt.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
    }

}

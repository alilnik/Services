package com.inno.ilyadmt.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by mjazz on 13.07.2017.
 */

public class MyService extends Service {

    MyBinder myBinder = new MyBinder();
    long result = 0;

    class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final Intent localIntent = intent;
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(localIntent != null){
                    Intent localIntent2 = new Intent("myBroadcast");
                    result = doWork(localIntent.getIntExtra("factorial", 1));
                    localIntent2.putExtra("factorial",
                            result+"");
                    sendBroadcast(localIntent2);
                }
//                stopSelf();
            }
        });
        thread.start();
        return super.onStartCommand(intent, flags, startId);

    }

    public int doWork(int i){
        if (i < 0) return -1;
        if (i == 0) return 1;
        return i*doWork(i-1);
    }
}

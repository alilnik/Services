package com.inno.ilyadmt.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by mjazz on 13.07.2017.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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
                Intent localIntent2 = new Intent("myBroadcast");
                localIntent2.putExtra("factorial",
                        doWork(localIntent.getIntExtra("factorial", 1))+"");
                sendBroadcast(localIntent2);
                stopSelf();
            }
        });
        thread.start();
        return super.onStartCommand(intent, flags, startId);

    }

    public long doWork(int i){
        if (i < 0) return -1;
        if (i == 0) return 1;
        return i*doWork(i-1);
    }
}

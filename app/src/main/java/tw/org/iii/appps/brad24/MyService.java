package tw.org.iii.appps.brad24;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private Timer timer;
    private int i;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("brad", "onCreate");
        timer = new Timer();
        timer.schedule(new MyTask(), 0, 1*1000);
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            if ( i % 10 == 0){
                Intent intent = new Intent("brad");
                intent.putExtra("i", i);
                sendBroadcast(intent);
            }

            Log.v("brad", "i = " + i++);



        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int newi = intent.getIntExtra("data", -1);
        if (newi != -1){
            i = newi;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("brad", "onDestroy");

        if (timer != null){
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}

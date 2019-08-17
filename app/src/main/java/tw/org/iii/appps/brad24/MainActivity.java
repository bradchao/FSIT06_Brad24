package tw.org.iii.appps.brad24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);

        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("brad");
        registerReceiver(myReceiver, filter);

    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }

    public void test1(View view) {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }
    public void test2(View view) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("data", (int)(Math.random()*1000));
        startService(intent);
    }
    public void test3(View view) {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int i = intent.getIntExtra("i", -1);
            tv.setText("" + i) ;
        }
    }

}

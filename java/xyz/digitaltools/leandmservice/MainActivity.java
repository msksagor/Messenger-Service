package xyz.digitaltools.leandmservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button start,stop,data;
    boolean ok;

    Messenger mymessenger;

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ok = true;
            mymessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            ok = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.startserviceId);
        stop = findViewById(R.id.stopserviceId);
        data = findViewById(R.id.dataserviceId);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(MainActivity.this,MyService.class),connection,BIND_AUTO_CREATE);
                Toast.makeText(MainActivity.this, "Start", Toast.LENGTH_SHORT).show();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
                Toast.makeText(getApplication(), "stop", Toast.LENGTH_SHORT).show();
            }
        });

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain(null,MyService.ONE,0,0);
                if(ok){
                    try {
                        mymessenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}

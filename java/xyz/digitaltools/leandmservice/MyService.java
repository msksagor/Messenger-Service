package xyz.digitaltools.leandmservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MyService extends Service {
    public static final int ONE = 1;
    public static final int Two = 2;
    public static final int THREE = 3;
    Handler mhander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ONE:
                    ShowToast();

            }
        }
    };

    Messenger messenger  = new Messenger(mhander);

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private void ShowToast(){
        Toast.makeText(this, "One", Toast.LENGTH_SHORT).show();
    }
}

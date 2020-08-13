package com.example.boundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BoundService boundService;

    //boolean variable to keep a check on service bind and unbind event
    boolean isBound = false;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this , BoundService.class);
//        startService(intent);
        bindService(intent , boundServiceConnection,BIND_AUTO_CREATE);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(boundService, boundService.name(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // to get data from service after 3 second
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, String.valueOf(boundService.randomGenerator()), Toast.LENGTH_SHORT).show();
            }
        };

        Handler handler = new Handler();
        handler.postDelayed(runnable,3000);

    }


    @Override
    protected void onStop() {
        super.onStop();
        if(isBound){
            unbindService(boundServiceConnection);
            isBound = false;
        }
    }

    private ServiceConnection boundServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            BoundService.MyBinder binder = (BoundService.MyBinder) service ;
            boundService = binder.getService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            boundService= null;
        }
    };
}
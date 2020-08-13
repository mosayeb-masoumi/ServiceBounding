package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Random;

public class BoundService extends Service {

    // Binder given to clients
    private final IBinder binder = new MyBinder();

    public BoundService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    //    This method creates a random number and return it
    public int randomGenerator() {
        Random randomNumber = new Random();
        int luckyNumber = randomNumber.nextInt();
        return luckyNumber;
    }

    public String name() {
        return "hassn";
    }

    public class MyBinder extends Binder {

        public BoundService getService() {
            return BoundService.this;
        }
    }
}

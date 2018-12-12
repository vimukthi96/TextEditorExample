package com.example.vimukthi.texteditorexample;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
                Thread thread = new Thread(){
                    public void run(){
                        try{
                            sleep(2000);
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                        finally {
                            startActivity(new Intent(SplashActivity.this,MainActivity.class));
                            finish();
                        }
                    }
                };
                thread.start();

    }

}


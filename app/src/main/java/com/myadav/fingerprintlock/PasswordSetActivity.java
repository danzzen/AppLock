package com.myadav.fingerprintlock;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.myadav.fingerprintlock.Custom.CustomDialog;
import com.myadav.fingerprintlock.Utils.SharedPrefrence;
import com.takwolf.android.lock9.Lock9View;

/**
 * Created by lenovo on 12-08-2017.Mohit yadav
 */

public class PasswordSetActivity extends AppCompatActivity {
    Button retry,confirm;
    TextView message;
    Lock9View lock9View;
    String passwordstr;
    boolean isfirsttime=true,isSecondtime=false;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordset);
        retry=(Button)findViewById(R.id.retrybtn);
        confirm=(Button)findViewById(R.id.confirmbtn);
        message=(TextView) findViewById(R.id.passwordset);
        lock9View=(Lock9View)findViewById(R.id.lock_9_view);
        retry.setEnabled(false);
        confirm.setEnabled(false);
        sharedPreferences = getSharedPreferences(AppLockConstants.MyPREFERENCES, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordstr="";
                isfirsttime=true;
                isSecondtime=false;
                retry.setEnabled(false);
                confirm.setEnabled(false);
                message.setText("Draw Pattern");
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString(AppLockConstants.PASSWORD,passwordstr);
                editor.apply();
                editor.commit();
                FragmentManager fm = getSupportFragmentManager();
                CustomDialog custom = new CustomDialog();
                custom.show(fm,"");


            }
        });
        lock9View.setCallBack(new Lock9View.CallBack() {

            @Override
            public void onFinish(String password) {
                if(isfirsttime){
                    passwordstr=password;
                    retry.setEnabled(true);
                    isfirsttime=false;
                    isSecondtime=true;
                    message.setText("Redraw Pattern");
                    Toast.makeText(PasswordSetActivity.this, "your Pattern recorded enter again to confirm", Toast.LENGTH_SHORT).show();
                }
                else if(isSecondtime){
                    if(password.equals(passwordstr)){
                        confirm.setEnabled(true);
                    }
                    else {
                        isfirsttime=true;
                        confirm.setEnabled(false);
                        isfirsttime=true;
                        isSecondtime=false;
                        message.setText("Draw Pattern again");
                        Toast.makeText(PasswordSetActivity.this, "Both Pattern did not match - Try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        GoogleAnalytics.getInstance(getApplicationContext()).reportActivityStart(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        GoogleAnalytics.getInstance(getApplicationContext()).reportActivityStop(this);
        super.onStop();
        super.onStop();
    }
}

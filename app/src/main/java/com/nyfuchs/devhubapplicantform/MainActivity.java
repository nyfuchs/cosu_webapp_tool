package com.nyfuchs.devhubapplicantform;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothClass;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

    WebView mWebView;
    DevicePolicyManager mDevicePolicyManager;
    ActivityManager mActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webview);
        mDevicePolicyManager = (DevicePolicyManager) getSystemService(
                Context.DEVICE_POLICY_SERVICE);
        mActivityManager = (ActivityManager) getSystemService(
                Context.ACTIVITY_SERVICE);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        mWebView.loadUrl(
                "https://docs.google.com/forms/d/1FlWbaPwq0eEY0uEOXaMFugYcJEMecnchAeSbXs3-Gf8/viewform");
    }

    @Override
    protected void onStart(){
        super.onStart();

        if(mDevicePolicyManager.isLockTaskPermitted(this.getPackageName())){
            if(mActivityManager.getLockTaskModeState() == ActivityManager.LOCK_TASK_MODE_NONE) {
                startLockTask();
            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        if(mDevicePolicyManager.isLockTaskPermitted(this.getPackageName())){
            if(mActivityManager.getLockTaskModeState() == ActivityManager.LOCK_TASK_MODE_NONE) {
                startLockTask();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_stop_lock_task) {
            stopLockTask();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.injectmotion;

import android.app.Instrumentation;
import android.hardware.input.InputManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    //    .setAction("Action", null).show();
            //    injectClickSelf();
              injectClickOther();
            }
        });
       //InputManager inputManager =  (InputManager) getSystemService(INPUT_SERVICE)
        //inputManager.inj

    }
    void injectClickSelf() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                Instrumentation instrumentation = new Instrumentation();
                final long now = SystemClock.uptimeMillis();
                int action = MotionEvent.ACTION_DOWN;
                float x = 583;
                float y = 1350;
                MotionEvent clickDown = MotionEvent.obtain(now,now,action,x,y,0);
                instrumentation.sendPointerSync(clickDown);
                action = MotionEvent.ACTION_UP;
                MotionEvent clickUp = MotionEvent.obtain(now,now,action,x,y,0);
                instrumentation.sendPointerSync(clickUp);
            }
        }).start();
    }
    void injectClickOther() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i= 0;i < 50 ;i++) {
                    Instrumentation instrumentation = new Instrumentation();
                    final long now = SystemClock.uptimeMillis();
                    int action = MotionEvent.ACTION_DOWN;
                    float x = 583;
                    float y = 1350;
                    MotionEvent clickDown = MotionEvent.obtain(now,now,action,x,y,0);
                    instrumentation.sendPointerSync(clickDown);
                    action = MotionEvent.ACTION_UP;
                    MotionEvent clickUp = MotionEvent.obtain(now,now,action,x,y,0);
                    instrumentation.sendPointerSync(clickUp);
                    try {
                        Thread.sleep(500);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("test"," onTouchEvent event = " + ev);

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
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

        return super.onOptionsItemSelected(item);
    }
}
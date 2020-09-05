package com.addv.mychatutils;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Objects;


public class ConversationActivity extends AppCompatActivity {
    private final static String ACTIVE = "Escribiendo...";
    private final static String ONLINE = "Conectado";
    private TextView status ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        String name = new String();

        if (getIntent() != null) {
            name = getIntent().getExtras().getString("name");
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView profile = toolbar.findViewById(R.id.profile);
        status = toolbar.findViewById(R.id.status);

        profile.setText(name);
            status.setText("");
            send(2);

        setSupportActionBar(toolbar);

    }

    public void send (int time) {
        time = time * 1000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                status.setText(ONLINE);
            }
        }, time);   //5 seconds
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
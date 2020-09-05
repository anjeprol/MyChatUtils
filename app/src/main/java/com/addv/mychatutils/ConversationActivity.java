package com.addv.mychatutils;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;


public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String ACTIVE = "Escribiendo...";
    private final static String ONLINE = "Conectado";
    private LinearLayout mLy_messages;
    private LinearLayout.LayoutParams params;
    private TextView status;
    private EditText messageET;

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
        mLy_messages = findViewById(R.id.ly_messages);
        params = (LinearLayout.LayoutParams) mLy_messages.getLayoutParams();
        TextView profile = toolbar.findViewById(R.id.profile);
        messageET = findViewById(R.id.message);
        messageET.requestFocus();
        messageET.setOnClickListener(this);
        messageET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    params.height = params.MATCH_PARENT;
                    params.width = params.WRAP_CONTENT;
                    mLy_messages.setLayoutParams(params);
                }
            }
        });

        status = toolbar.findViewById(R.id.status);

        profile.setText(name);
        status.setText("");
        send(2);

        setSupportActionBar(toolbar);

    }


    public void showKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void closeKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    public void send(int time) {
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        messageET.clearFocus();
        mLy_messages.requestFocus();
    }

    public void adjustSize() {
        params.height = 1450;
        params.width = params.WRAP_CONTENT;
        mLy_messages.setLayoutParams(params);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.message:
                adjustSize();
        }
    }
}
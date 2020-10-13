package com.addv.mychatutils;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String ACTIVE = "Escribiendo...";
    private final static String ONLINE = "Conectado";
    private LinearLayout mLy_messages;
    private LinearLayout.LayoutParams params;
    private TextView status;
    private EditText messageET;
    private Button mSend_bt;

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
        mLy_messages = findViewById(R.id.ly_content_messages);
        mSend_bt = findViewById(R.id.send);
        params = (LinearLayout.LayoutParams) mLy_messages.getLayoutParams();
        TextView profile = toolbar.findViewById(R.id.profile);
        messageET = findViewById(R.id.message);

        messageET.setOnClickListener(this);
        mSend_bt.setOnClickListener(this);

        /** Para la barra de mensajes **/
        mLy_messages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                params.height = params.MATCH_PARENT;
                params.width = params.WRAP_CONTENT;
                mLy_messages.setLayoutParams(params);
                //closeKeyboard();
            }
        });

        status = toolbar.findViewById(R.id.status);

        profile.setText(name);
        status.setText("");
        setStatus(2);

        setSupportActionBar(toolbar);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                sendMsg(messageET.getText().toString());
        }
    }

    public void sendMsg(String msg){
        startVisibility(msg);
    }

    public void startVisibility(String msg){
       CardView sentCV ;
       TextView textView;
        sentCV = findViewById(R.id.msg_send01);
        sentCV.setVisibility(View.VISIBLE);
        textView =findViewById(R.id.tv_send_text01);
        textView.setText(msg);
    }
    public void setStatus(int time) {
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
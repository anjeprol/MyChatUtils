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
import android.widget.ScrollView;
import android.widget.TextView;


public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String ACTIVE = "Escribiendo...";
    private final static String ONLINE = "Conectado";
    private LinearLayout mLy_messages;
    private LinearLayout.LayoutParams params;
    private TextView status;
    private TextView mMessage;
    private EditText messageET;
    private Button mSend_bt;
    private CardView sentCV ;
    private int numMessage = 0;
    private ScrollView scrollView;

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
        scrollView = findViewById(R.id.sly_texts);
        mLy_messages = findViewById(R.id.ly_content_messages);
        mSend_bt = findViewById(R.id.send);
        params = (LinearLayout.LayoutParams) mLy_messages.getLayoutParams();
        TextView profile = toolbar.findViewById(R.id.profile);
        messageET = findViewById(R.id.message);
        messageET.requestFocus();

        messageET.setOnClickListener(this);
        mSend_bt.setOnClickListener(this);

        status = toolbar.findViewById(R.id.status);

        profile.setText(name);
        status.setText("");
        setStatus(2,ONLINE);

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
        messageET.setText("");
    }

    public void startVisibility(String msg){
        numMessage++;
       int msgLy = 0;
       int msTv = 0;
       int time = 2;

       switch (numMessage) {
           case 1:
               msgLy = R.id.msg_send01;
               msTv = R.id.tv_send_text01;
               break;
           case 3:
               msgLy = R.id.msg_send02;
               msTv = R.id.tv_send_text02;
               break;
           case 5:
               msgLy = R.id.msg_send03;
               msTv = R.id.tv_send_text03;
               break;
       }

        sentCV = findViewById(msgLy);
        sentCV.setVisibility(View.VISIBLE);
        mMessage =findViewById(msTv);
        mMessage.setText(msg);
        focusOnView();
        numMessage++;

        switch (numMessage) {
            case 2:
                msgLy = R.id.msg_received01;
                msTv = R.id.tv_received_text01;
                msg =getResources().getString(R.string.msg_miguel1);
                time = 4;
                break;
            case 4:
                msgLy = R.id.msg_received02;
                msTv = R.id.tv_received_text02;
                msg =getResources().getString(R.string.msg_miguel2);
                time = 5;
                break;
            case 6:
                msgLy = R.id.msg_received03;
                msTv = R.id.tv_received_text03;
                msg =getResources().getString(R.string.msg_miguel3);
                time = 7;
                break;
        }

        sentCV = findViewById(msgLy);
        mMessage =findViewById(msTv);
        setStatus(2,ACTIVE);
        setResponse(time, msg);

    }

    public void setResponse(int time, final String msg) {
        time = time * 1000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                mMessage.setText(msg);
                sentCV.setVisibility(View.VISIBLE);
                status.setText(ONLINE);
                focusOnView();
            }
        }, time);   //5 seconds
    }

    private final void focusOnView(){
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, sentCV.getBottom());
            }
        });
    }

    public void setStatus(int time, final String type) {
        time = time * 1000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                status.setText(type);
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
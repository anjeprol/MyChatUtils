package com.addv.mychatutils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import static com.addv.mychatutils.R.drawable.sergio;


public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String ACTIVE = "Escribiendo...";
    private final static String ONLINE = "Conectado";
    private LinearLayout mLy_messages;
    private LinearLayout ll1,ll2,ll3;
    private TextView status;
    private ImageView avatar;
    private TextView mMessage;
    private EditText messageET;
    private Button mSend_bt;
    private CardView sentCV;
    private int numMessage = 0;
    private ScrollView scrollView;
    private ImageView locationIV;

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
        avatar = findViewById(R.id.avatar);
        locationIV = findViewById(R.id.locationIV);
        //mLy_messages = findViewById(R.id.ly_content_messages);
        mSend_bt = findViewById(R.id.send);
       // params = (LinearLayout.LayoutParams) mLy_messages.getLayoutParams();
        TextView profile = toolbar.findViewById(R.id.profile);
        messageET = findViewById(R.id.message);
        messageET.requestFocus();
        locationIV.setOnClickListener(this);
        mSend_bt.setOnClickListener(this);
        status = toolbar.findViewById(R.id.status);



        profile.setText(name);
        status.setText("");
        avatar.setImageResource(sergio);
        setStatus(2, ONLINE);

        setSupportActionBar(toolbar);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send:
                sendMsg(messageET.getText().toString());
                break;
            case R.id.locationIV: // Llamar aqui el fragmento
                sentCV = findViewById(R.id.msg_send02);
                showAlertDialogButtonClicked();
                break;
            case  R.id.ll_1:
                changeColor(ll1,ll2,ll3);
                break;
            case  R.id.ll_2:
                changeColor(ll2,ll1,ll3);
                break;
            case  R.id.ll_3:
                changeColor(ll3,ll2,ll1);
                break;
        }
    }

    public void changeColor(final LinearLayout ll00, final LinearLayout ll01,final LinearLayout ll02){
        ll00.setBackgroundColor(getResources().getColor(R.color.divider));
        ll01.setBackgroundColor(getResources().getColor(R.color.white));
        ll02.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void showAlertDialogButtonClicked() {
        // create an alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enviar ubicación");
        // set the custom layout
        final View customLayout = getLayoutInflater().inflate(R.layout.map_pop_up, null);
        ll1 = customLayout.findViewById(R.id.ll_1);
        ll2 = customLayout.findViewById(R.id.ll_2);
        ll3 = customLayout.findViewById(R.id.ll_3);
        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        builder.setView(customLayout);
        // add a button
        builder.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // send data from the AlertDialog to the Activity
                sentCV.setVisibility(View.VISIBLE);
                focusOnView(sentCV);
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void sendMsg(String msg) {
        int time = 3;
        if (numMessage != 2)
            startVisibility(msg);
        messageET.setText("");
    }

    public void startVisibility(String msg) {
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
        }

        sentCV = findViewById(msgLy);
        sentCV.setVisibility(View.VISIBLE);
        mMessage = findViewById(msTv);
        mMessage.setText(msg);
        focusOnView(sentCV);

        numMessage++;

        switch (numMessage) {
            case 2:
                msgLy = R.id.msg_received01;
                msTv = R.id.tv_received_text01;
                msg = getResources().getString(R.string.msg_miguel1);
                time = 4;
                break;
        }

        sentCV = findViewById(msgLy);
        mMessage = findViewById(msTv);
        setStatus(2, ACTIVE);
        setResponse(time, msg);

    }

    public void setResponse(int time, final String msg) {
        time = time * 1000;
        final int time2 = 5000;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                mMessage.setText(msg);
                sentCV.setVisibility(View.VISIBLE);
                status.setText(ONLINE);
                focusOnView(sentCV);
            }
        }, time);
        status.setText(ONLINE);
    }

    private final void focusOnView(final CardView cardView) {
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0, cardView.getBottom());
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
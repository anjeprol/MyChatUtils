package com.addv.mychatutils;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listview;

    private ArrayList<String> names;
    private ArrayList<String> msg;
    private ArrayList<String> hr;
    private Intent mIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        listview = (ListView) findViewById(R.id.simpleListView);

        names = new ArrayList<String>();
        names.add("Sergio");
        msg = new ArrayList<String>();
        msg.add("yo tambi...");
        hr = new ArrayList<String>();
        hr.add("Hoy");

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_item, names, msg, hr);
        listview.setAdapter(customAdapter);

        setSupportActionBar(toolbar);

        listview.setOnItemClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
       // Toast.makeText(MainActivity.this, "Has pulsado: " + names.get(position), Toast.LENGTH_LONG).show();
        mIntent = new Intent(this, ConversationActivity.class);
        mIntent = mIntent.putExtra("name", names.get(position));
        startActivity(mIntent);
        //mInten.putExtra(names.get(position));

    }
}
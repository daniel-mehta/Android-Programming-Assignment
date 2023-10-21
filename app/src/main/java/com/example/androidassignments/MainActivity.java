package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.app.Activity;
import android.widget.Toast;





public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Button mainButton = findViewById(R.id.button);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 10);
            }
        });

        Button startChatButton = findViewById(R.id.startChat);
        startChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(MainActivity.this, ChatWindow.class);
                startActivity(chatIntent);
                Log.i(TAG, "User clicked Start Chat");
            }
        });

        Button testToolbarButton = findViewById(R.id.testToolbar);
        testToolbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toolbarIntent = new Intent(MainActivity.this, TestToolbar.class);
                startActivity(toolbarIntent);
                Log.i(TAG, "User clicked Test Toolbar");
            }
        });



        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 10) {
            Log.i(TAG, "Returned to MainActivity.onActivityResult");

            if(resultCode == Activity.RESULT_OK && data != null) {
                String messagePassed = data.getStringExtra("Response");

                if(messagePassed != null) {
                    Toast.makeText(this, "ListItemsActivity passed: " + messagePassed, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "No message passed from ListItemsActivity", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Activity did not finish properly", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG,"onStop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        Log.d(TAG, "onRestoreInstanceState");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
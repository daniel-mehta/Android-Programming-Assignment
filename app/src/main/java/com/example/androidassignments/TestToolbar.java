package com.example.androidassignments;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.google.android.material.snackbar.Snackbar;
import android.view.LayoutInflater;
import android.widget.EditText;





public class TestToolbar extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "You selected item 1", Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_choice1) {
            Toast.makeText(this, "Choice 1 selected", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_choice2) {
            AlertDialog.Builder choice2Builder = new AlertDialog.Builder(TestToolbar.this);
            choice2Builder.setTitle(R.string.dialog_box_title)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.cancel, null);
            AlertDialog choice2Dialog = choice2Builder.create();
            choice2Dialog.show();
            return true;
        } else if (id == R.id.action_choice3) {
            AlertDialog.Builder choice3Builder = new AlertDialog.Builder(TestToolbar.this);
            View view = LayoutInflater.from(TestToolbar.this).inflate(R.layout.custom_dialog, null);
            final EditText newMessageInput = view.findViewById(R.id.new_message_input);

            choice3Builder.setView(view)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String newMessage = newMessageInput.getText().toString();
                            fab.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Snackbar.make(v, newMessage, Snackbar.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .setNegativeButton(R.string.cancel, null);
            AlertDialog choice3Dialog = choice3Builder.create();
            choice3Dialog.show();
            return true;
        } else if (id == R.id.action_about) {
            Toast.makeText(this, "Version 1.0, by Daniel Mehta", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }




}

package com.example.androidassignments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import java.util.ArrayList;
import android.content.ContentValues;



public class ChatWindow extends AppCompatActivity {
    private static final String ACTIVITY_NAME = "ChatWindow";
    private ListView chatListView;
    private EditText chatInput;
    private Button sendButton;
    private ArrayList<String> chatMessages;
    private SQLiteDatabase db;
    private ChatDatabaseHelper dbHelper;
    private ChatAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        chatListView = findViewById(R.id.chatListView);
        chatInput = findViewById(R.id.chatInput);
        sendButton = findViewById(R.id.sendButton);
        chatMessages = new ArrayList<>();

        dbHelper = new ChatDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(ChatDatabaseHelper.TABLE_MESSAGES,
                new String[]{ChatDatabaseHelper.KEY_ID, ChatDatabaseHelper.KEY_MESSAGE},
                null, null, null, null, null);

        Log.i(ACTIVITY_NAME, "Cursor’s column count =" + cursor.getColumnCount());
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Log.i(ACTIVITY_NAME, "Column: " + cursor.getColumnName(i));
        }

        while (cursor.moveToNext()) {
            String message = cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + message);
            chatMessages.add(message);
        }
        cursor.close();

        messageAdapter = new ChatAdapter(this, chatMessages);
        chatListView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = chatInput.getText().toString();
                if (!message.isEmpty()) {
                    chatMessages.add(message);
                    insertMessageIntoDatabase(message);
                    messageAdapter.notifyDataSetChanged();
                    chatInput.setText("");
                }
            }
        });

    }

    private void insertMessageIntoDatabase(String message) {
        ContentValues values = new ContentValues();
        values.put(ChatDatabaseHelper.KEY_MESSAGE, message);
        db.insert(ChatDatabaseHelper.TABLE_MESSAGES, null, values);
    }



    @Override
    protected void onDestroy() {
        db.close();
        dbHelper.close();
        super.onDestroy();
    }

    private class ChatAdapter extends ArrayAdapter<String> {
        private ArrayList<String> messages;

        public ChatAdapter(Context ctx, ArrayList<String> messages) {
            super(ctx, 0, messages);
            this.messages = messages;
        }

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public String getItem(int position) {
            return messages.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = (position % 2 == 0) ?
                    inflater.inflate(R.layout.chat_row_outgoing, parent, false) :
                    inflater.inflate(R.layout.chat_row_incoming, parent, false);
            TextView messageText = result.findViewById(R.id.messageText);
            messageText.setText(getItem(position));
            return result;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.androidassignments;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.view.MenuItem;
import androidx.core.app.NavUtils;





public class ChatWindow extends AppCompatActivity {

    private ListView chatListView;
    private EditText chatInput;
    private Button sendButton;
    private ArrayList<String> chatMessages;

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

        // Create a ChatAdapter object and set it as the adapter for chatListView
        ChatAdapter messageAdapter = new ChatAdapter(this, chatMessages);
        chatListView.setAdapter(messageAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = chatInput.getText().toString();
                if (!message.isEmpty()) {
                    chatMessages.add(message);

                    // Notify the adapter that the data set has changed
                    messageAdapter.notifyDataSetChanged();

                    // Clear the chatInput EditText for new entries
                    chatInput.setText("");
                }
            }
        });
    }


    private class ChatAdapter extends ArrayAdapter<String> {

        // List to hold the chat messages
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
            View result = null;

            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            } else {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            }


            TextView message = (TextView) result.findViewById(R.id.messageText);
            message.setText(getItem(position));  // get the string at position

            return result;
        }
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

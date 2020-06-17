package com.example.android.roomwordssample;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class UpdateWordActivity extends AppCompatActivity {


    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_ID = "com.example.android.wordlistsql.EXTRA_ID";
    public static final String EXTRA_WORD = "com.example.android.wordlistsql.WORD";
    private EditText mEditWordView;
     String word;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_word);
        mEditWordView = findViewById(R.id.update_word);

        final Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit word");
            mEditWordView.setText(intent.getStringExtra(EXTRA_WORD));
        }else{
            setTitle("New word");
        }

        Button btnUpdate = findViewById(R.id.button_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }else if(mEditWordView.getText().toString().equals(intent.getStringExtra(EXTRA_WORD))){
                    setResult(RESULT_FIRST_USER, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY,word);

                    int id = intent.getIntExtra(EXTRA_ID,-1);
                    if(id != -1){
                        replyIntent.putExtra(EXTRA_ID,id);
                    }

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }


     private void getIncomingIntent(){
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_WORD)){
            setTitle("Edit word");
            mEditWordView.setText(word);
            mEditWordView.setText(intent.getStringExtra(EXTRA_WORD));
        }else{
            setTitle("New word");

        }
    }

}

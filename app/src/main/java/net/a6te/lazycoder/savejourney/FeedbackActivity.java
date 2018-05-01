package net.a6te.lazycoder.savejourney;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FeedbackActivity extends AppCompatActivity {

    private EditText feedbackEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        feedbackEt = (EditText) findViewById(R.id.feedbackEt);
    }

    public void feedbackSend(View view) {
        if (feedbackEt.getText().toString().equals("")){
            Snackbar.make(view,"FeedbackActivity Sanded",Snackbar.LENGTH_SHORT).show();
            feedbackEt.setText("");
        }

    }
}

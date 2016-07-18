package in.technicus.studypro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FeedbackActivity extends AppCompatActivity {

    EditText mSubject;
    EditText mFeedback;
    Button mSubmitButton;
    String emailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        emailAddress = getString(R.string.email_string);
        mSubmitButton = (Button) findViewById(R.id.button_submit);
        mFeedback = (EditText) findViewById(R.id.edit_text_feedback);
        mSubject = (EditText) findViewById(R.id.edit_text_subject);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String finalMessage = mFeedback.getText().toString();
                String finalSubject = mSubject.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {emailAddress});
                intent.putExtra(Intent.EXTRA_SUBJECT, finalSubject);
                intent.putExtra(Intent.EXTRA_TEXT, finalMessage);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

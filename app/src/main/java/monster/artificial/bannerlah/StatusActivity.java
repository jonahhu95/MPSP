package monster.artificial.bannerlah;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatusActivity extends AppCompatActivity {
    private TextView mDurationTV, mLocationTV, mTitleTV, mNameTV, mPhoneTV;
    private Button mNavigateBtn, mCancelBtn, mDoneBtn, mDoneBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        mDurationTV = findViewById(R.id.durationTV);
        mLocationTV = findViewById(R.id.locationTV);
        mTitleTV = findViewById(R.id.titleTV);
        mNameTV = findViewById(R.id.nameTV);
        mPhoneTV = findViewById(R.id.phoneTV);
        mNavigateBtn = findViewById(R.id.navigateBtn);
        mCancelBtn = findViewById(R.id.cancelBtn);
        mDoneBtn = findViewById(R.id.doneBtn);
        mDoneBtn2 = findViewById(R.id.doneBtn2);

        mNavigateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("google.navigation:q=municipal+council+of+seberang+perai"));
                startActivity(mapIntent);
            }
        });
    }
}

package monster.artificial.bannerlah;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> function_mpsp = Arrays.asList("Mengesahkan Banner", "Lokasi Banner", "Aduan");
    private List<String> function_applicant = Arrays.asList("Permohonan Memasang", "Pengaktifan Lesen");
    private Button button1, button2, button3, button4;
    private int mode = 0;
    private Intent qRScanner, application, maps, report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        Button[] buttons = {button1,button2,button3, button4};
        SharedPreferences prefs = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE);
        String user = prefs.getString("userID", null);
        if (user.equals("email1@email.com")){
            mode = 0;
            int n;
            for(n = 0; n < function_mpsp.size(); n++){
                buttons[n].setText(function_mpsp.get(n));
                buttons[n].setOnClickListener(this);
            }
            while(n < buttons.length){
                buttons[n].setVisibility(View.INVISIBLE);
                n++;
            }

        }else{
            mode = 1;
            int n;
            for(n = 0; n < function_applicant.size(); n++){
                buttons[n].setText(function_applicant.get(n));
                buttons[n].setOnClickListener(this);
            }
            while(n < buttons.length){
                buttons[n].setVisibility(View.INVISIBLE);
                n++;
            }
        }


    }


    @Override
    public void onClick(View v) {
        if (mode == 0){
            switch (v.getId()){
                case R.id.button1:
                    qRScanner = new Intent(getApplicationContext() , QRScannerActivity.class);
                    qRScanner.putExtra("mode", 1);
                    startActivity(qRScanner);
                    break;
                case R.id.button2:
                    maps = new Intent(getApplicationContext(), MapActivity.class);
                    startActivity(maps);
                    break;
                case R.id.button3:
                    report = new Intent(getApplicationContext(), ReportActivity.class);
                    startActivity(report);
                    break;

                case R.id.button4:
            }
        }else{
            switch (v.getId()){
                case R.id.button1:
                    application = new Intent(getApplicationContext(), ApplicationActivity.class);
                    startActivity(application);
                    break;
                case R.id.button2:
                    qRScanner = new Intent(getApplicationContext() , QRScannerActivity.class);
                    qRScanner.putExtra("mode", 0);
                    startActivity(qRScanner);
                    break;
                case R.id.button3:
                case R.id.button4:
            }
        }

    }
}

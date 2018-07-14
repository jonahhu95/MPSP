package monster.artificial.bannerlah;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Intent menuActivity;
    private Intent signUpActivity;
    private Button loginButton;
    private EditText emailText;
    private EditText passwordText;
    private TextView signupLink;
    private MaterialDialog d;

    private String logTag = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        signUpActivity = new Intent(getApplicationContext() , SignupActivity.class);
        menuActivity = new Intent(getApplicationContext() , MenuActivity.class);
        d = new MaterialDialog.Builder(this)
                .progress(false, 0, false)
                .show();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            currentUser.getIdToken(true)
                    .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                            if (task.isSuccessful()) {
                                String idToken = task.getResult().getToken();
                                SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE).edit();
                                editor.putString("userID", currentUser.getEmail());
                                editor.apply();
                                loginSuccess();
                            } else {
                                Toast.makeText(getApplicationContext(), "Login failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
        d.dismiss();
        loginButton = (Button)findViewById(R.id.btn_login);
        emailText = (EditText)findViewById(R.id.input_email);
        passwordText = (EditText)findViewById(R.id.input_password);
        signupLink = (TextView)findViewById(R.id.link_signup);
        loginButton.setOnClickListener(this);
        signupLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                String email = String.valueOf(emailText.getText());
                String password = String.valueOf(passwordText.getText());
                if(email.isEmpty() || password.isEmpty())
                    break;
                login(email,password);
                break;
            case R.id.link_signup:
                startActivity(signUpActivity);
                finish();
                break;
        }
    }

    private void login(String email, String password){
        loginButton.setEnabled(false);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(logTag, "Email Sign In: success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            loginSuccess();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(logTag, "Email Sign In: failure");
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            loginButton.setEnabled(true);
                        }

                    }
                });
    }

    private void loginSuccess(){
        startActivity(menuActivity);
        finish();
    }

}


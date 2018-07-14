package monster.artificial.bannerlah;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

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

    private String logTag = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        signUpActivity = new Intent(getApplicationContext() , SignupActivity.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            currentUser.getIdToken(true)
                    .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                            if (task.isSuccessful()) {
                                String idToken = task.getResult().getToken();
                                // Send token to your backend via HTTPS
                                // ...
                            } else {
                                // Handle error -> task.getException();
                            }
                        }
                    });
            loginSuccess();
        }
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

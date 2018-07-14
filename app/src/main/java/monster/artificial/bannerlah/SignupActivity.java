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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Intent menuActivity;
    private Button signUpButton;
    private EditText emailText;
    private EditText passwordText;
    private EditText passwordReText;
    private EditText nameText;
    private TextView loginLink;

    private String logTag = "SignupActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        menuActivity = new Intent(getApplicationContext(), MenuActivity.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        signUpButton = (Button)findViewById(R.id.btn_signup);
        emailText = (EditText)findViewById(R.id.input_email);
        passwordText = (EditText)findViewById(R.id.input_password);
        passwordReText = (EditText)findViewById(R.id.input_reEnterPassword);
        nameText = (EditText)findViewById(R.id.input_name); //TODO = Implement saving of name
        loginLink = (TextView)findViewById(R.id.link_login);
        signUpButton.setOnClickListener(this);
        loginLink.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signup:
                String email = String.valueOf(emailText.getText());
                String password = String.valueOf(passwordText.getText());
                String rePassword = String.valueOf(passwordReText.getText());
                String name = String.valueOf(nameText.getText());
                if(email.isEmpty() || password.isEmpty() || rePassword.isEmpty() || name.isEmpty())
                    break;
                if(password.equals(rePassword))
                    signup(email, password, name);
                else
                    Toast.makeText(getApplicationContext(), "Passwords doesn't match.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.link_login:

                break;
        }
    }

    private void signup(String email, String password, String name){
        signUpButton.setEnabled(false);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(logTag, "Email Sign up: success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.shared_preferences), MODE_PRIVATE).edit();
                            editor.putString("userID", user.getUid());
                            editor.apply();
                            if(user!=null){
                                signUpSuccess();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(logTag, "Email Sign up: failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Sign up failed.",
                                    Toast.LENGTH_SHORT).show();
                            signUpButton.setEnabled(true);
                        }
                    }
                });
    }

    private void signUpSuccess(){
        startActivity(menuActivity);
        finish();
    }
}

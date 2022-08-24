package samuel.jose.mutantes_front.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import samuel.jose.mutantes_front.R;

public class LoginActivity extends AppCompatActivity {
    private EditText loginInput;
    private EditText passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(samuel.jose.mutantes_front.R.layout.activity_login);
        loginInput = findViewById(R.id.loginInput);
        passwordInput = findViewById(R.id.passwordInput);
    }

    public void doLogin(View view) {
        String loginText = loginInput.getText().toString();
        String passwordText = passwordInput.getText().toString();

        if (loginText.isEmpty() || passwordText.isEmpty()) {
            Toast.makeText(this, "Fill the login form!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (loginText.equals("login") && passwordText.equals("123")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Invalid login!", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
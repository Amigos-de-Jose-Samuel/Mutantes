package samuel.jose.mutantes_front.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.jose.mutantes_front.R;
import samuel.jose.mutantes_front.apiMutante.RetrofitConfig;
import samuel.jose.mutantes_front.model.APIResponse;
import samuel.jose.mutantes_front.model.ListaMutantes;
import samuel.jose.mutantes_front.model.Login;
import samuel.jose.mutantes_front.model.Mutante;

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

        Context context = this;

        if (loginText.isEmpty() || passwordText.isEmpty()) {
            Toast.makeText(this, "Fill the login form!", Toast.LENGTH_SHORT).show();
            return;
        }

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logando...");
        progressDialog.show();

        Call<APIResponse> callLogin = new RetrofitConfig().getMutanteService().login(new Login(loginText, passwordText));
        callLogin.enqueue(new Callback<APIResponse>() {

            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                progressDialog.dismiss();

                if (response.isSuccessful()) {
                    if (response.body().isSucesso()) {
                        startActivity(new Intent(context, MainActivity.class));
                        finish();
                        return;
                    }
                    Toast.makeText(context, "Invalid login!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
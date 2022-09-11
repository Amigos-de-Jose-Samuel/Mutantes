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
import samuel.jose.mutantes_front.model.DashboardResponse;
import samuel.jose.mutantes_front.model.DefaultResponse;
import samuel.jose.mutantes_front.model.LoginBody;

public class LoginActivity extends AppCompatActivity {
    private EditText loginInput;
    private EditText passwordInput;

    int quantidade;
    String habilidadeUm, habilidadeDois, habilidadeTres;

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

        Call<DashboardResponse> callDashboard = new RetrofitConfig().getMutanteService().dashboard();
        callDashboard.enqueue(new Callback<DashboardResponse>() {
            @Override
            public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSucesso()) {
                        DashboardResponse dashboardResponse = response.body();
                        quantidade = dashboardResponse.getQuantidadeMutantes();
                        DashboardResponse.Habilidade[] habilidades = dashboardResponse.getHabilidades();
                        habilidadeUm = habilidades[0].getHabilidade();
                        habilidadeDois = habilidades[1].getHabilidade();
                        habilidadeTres = habilidades[2].getHabilidade();


                        Call<DefaultResponse> callLogin = new RetrofitConfig().getMutanteService().login(new LoginBody(loginText, passwordText));
                        callLogin.enqueue(new Callback<DefaultResponse>() {

                            @Override
                            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                                progressDialog.dismiss();

                                if (response.isSuccessful()) {
                                    if (response.body().isSucesso()) {
                                        Intent it = new Intent(context, MainActivity.class);
                                        it.putExtra("quantidade", quantidade);
                                        it.putExtra("habilidadeUm", habilidadeUm);
                                        it.putExtra("habilidadeDois", habilidadeDois);
                                        it.putExtra("habilidadeTres", habilidadeTres);
                                        startActivity(it);
                                        finish();
                                        return;
                                    }
                                    Toast.makeText(context, "Invalid login!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });


                    }
                }
            }

            @Override
            public void onFailure(Call<DashboardResponse> call, Throwable t) {

            }
        });


    }
}
package samuel.jose.mutantes_front.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.jose.mutantes_front.R;
import samuel.jose.mutantes_front.apiMutante.RetrofitConfig;
import samuel.jose.mutantes_front.model.DashboardResponse;
import samuel.jose.mutantes_front.model.ListarMutantesResponse;
import samuel.jose.mutantes_front.utils.DownloadTask;

public class MainActivity extends AppCompatActivity {

    TextView quantidadeMutantes, habilidadeUmTextView, habilidadeDoisTextView, habilidadeTresTextView;
    EditText inputPesquisar;

    int quantidade;
    String habilidadeUm, habilidadeDois, habilidadeTres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantidadeMutantes = findViewById(R.id.quantidadeMutantes);
        habilidadeUmTextView = findViewById(R.id.habilidadeUm);
        habilidadeDoisTextView = findViewById(R.id.habilidadeDois);
        habilidadeTresTextView = findViewById(R.id.habilidadeTres);
        inputPesquisar = findViewById(R.id.inputPesquisar);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Carregando...");
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

                        quantidadeMutantes.setText("" + quantidade);
                        habilidadeUmTextView.setText(habilidadeUm);
                        habilidadeDoisTextView.setText(habilidadeDois);
                        habilidadeTresTextView.setText(habilidadeTres);

                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<DashboardResponse> call, Throwable t) {

            }
        });
    }

    public void cadastrarMutante(View view) {
        Intent it = new Intent(this, CadastroMutanteActivity.class);
        it.putExtra("context", (Parcelable) MainActivity.this);
        startActivity(it);
    }

    public void buscarMutantes(View view) {
        Intent it = new Intent(this, ListarTodosActivity.class);
        startActivity(it);
    }

    public void pesquisarPoder(View view) {
        Intent it = new Intent(this, ListarTodosPoderActivity.class);
        it.putExtra("poder", inputPesquisar.getText().toString());
        startActivity(it);
    }

    public void exitApp(View view) {
        finish();
        System.exit(0);
    }
}
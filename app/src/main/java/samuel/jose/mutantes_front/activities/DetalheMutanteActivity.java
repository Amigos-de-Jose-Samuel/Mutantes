package samuel.jose.mutantes_front.activities;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.jose.mutantes_front.R;
import samuel.jose.mutantes_front.apiMutante.RetrofitConfig;
import samuel.jose.mutantes_front.model.DashboardResponse;
import samuel.jose.mutantes_front.model.DefaultResponse;
import samuel.jose.mutantes_front.model.DetalheResponse;
import samuel.jose.mutantes_front.model.ListarMutantesResponse;
import samuel.jose.mutantes_front.model.LoginBody;
import samuel.jose.mutantes_front.model.Mutante;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetalheMutanteActivity extends AppCompatActivity {

    ImageView image;
    EditText editNome, editHabilidadeUm, editHabilidadeDois, editHabilidadeTres;
    TextView usuarioNome;
    int idMutante;
    int quantidade;
    String habilidadeUm, habilidadeDois, habilidadeTres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_mutante);

        image = findViewById(R.id.image);
        editNome = findViewById(R.id.editNome);
        editHabilidadeUm = findViewById(R.id.editHabilidadeUm);
        editHabilidadeDois = findViewById(R.id.editHabilidadeDois);
        editHabilidadeTres = findViewById(R.id.editHabilidadeTres);
        usuarioNome = findViewById(R.id.nomeUsuario);

        Intent it = getIntent();
        Bundle params = it.getExtras();
        if (it != null) {
            int id = params.getInt("id");
            idMutante = id;
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Buscando detalhes mutante ...");
            progressDialog.show();

            Call<DetalheResponse> callMutantes = new RetrofitConfig().getMutanteService().detalhe(id);
            callMutantes.enqueue(new Callback<DetalheResponse>() {

                @Override
                public void onResponse(Call<DetalheResponse> call, Response<DetalheResponse> response) {
                    if (response.isSuccessful()) {
                        DetalheResponse detalheResponse = response.body();
                        editNome.setText(detalheResponse.getMutante().getNome());
                        DetalheResponse.Habilidade[] habilidades = detalheResponse.getHabilidades();
                        switch (habilidades.length) {
                            case 1:
                                editHabilidadeUm.setText(habilidades[0].getDescricao());
                                break;
                            case 2:
                                editHabilidadeUm.setText(habilidades[0].getDescricao());
                                editHabilidadeDois.setText(habilidades[1].getDescricao());
                                break;
                            case 3:
                                editHabilidadeUm.setText(habilidades[0].getDescricao());
                                editHabilidadeDois.setText(habilidades[1].getDescricao());
                                editHabilidadeTres.setText(habilidades[2].getDescricao());
                                break;
                            default:
                        }
                        usuarioNome.setText(detalheResponse.getMutante().getLoginUsuarioCadastro());
                        //image.setImageResource(detalheResponse.getMutante().getFoto());
                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<DetalheResponse> call, Throwable t) { }
            });
        }
    }

    public void alterarMutante(View view) {
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("mensagem", "Mutante alterado com sucesso");
        startActivity(it);
    }

    public void alterarFoto(View view) {

    }

    public void removerMutante(View view) {
        Context context = this;

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Removendo mutante ...");
        progressDialog.show();

        Call<DefaultResponse> callMutantes = new RetrofitConfig().getMutanteService().deletar(idMutante);
        callMutantes.enqueue(new Callback<DefaultResponse>() {

            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    DefaultResponse defaultResponse = response.body();
                    progressDialog.dismiss();


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
                                    Intent it = new Intent(context, MainActivity.class);
                                    it.putExtra("mensagem", defaultResponse.getMensagem());
                                    it.putExtra("quantidade", quantidade);
                                    it.putExtra("habilidadeUm", habilidadeUm);
                                    it.putExtra("habilidadeDois", habilidadeDois);
                                    it.putExtra("habilidadeTres", habilidadeTres);
                                    startActivity(it);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DashboardResponse> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) { }
        });
    }
}
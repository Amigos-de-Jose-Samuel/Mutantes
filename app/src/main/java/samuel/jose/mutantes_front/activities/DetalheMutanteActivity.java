package samuel.jose.mutantes_front.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.jose.mutantes_front.R;
import samuel.jose.mutantes_front.apiMutante.RetrofitConfig;
import samuel.jose.mutantes_front.model.DashboardResponse;
import samuel.jose.mutantes_front.model.DefaultResponse;
import samuel.jose.mutantes_front.model.DetalheResponse;
import samuel.jose.mutantes_front.model.EditarBody;
import samuel.jose.mutantes_front.model.ListarMutantesResponse;
import samuel.jose.mutantes_front.model.LoginBody;
import samuel.jose.mutantes_front.model.Mutante;
import samuel.jose.mutantes_front.model.MutanteDB;
import samuel.jose.mutantes_front.model.NovoMutanteBody;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DetalheMutanteActivity extends AppCompatActivity {

    ImageView image;
    Uri uriImagem;
    EditText editNome, editHabilidadeUm, editHabilidadeDois, editHabilidadeTres;
    TextView usuarioNome;
    int idMutante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_mutante);

        image = findViewById(R.id.imageView2);
        editNome = findViewById(R.id.editNome);
        editHabilidadeUm = findViewById(R.id.editHabilidadeUm);
        editHabilidadeDois = findViewById(R.id.editHabilidadeDois);
        editHabilidadeTres = findViewById(R.id.editHabilidadeTres);
        usuarioNome = findViewById(R.id.nomeUsuario);

        ((Button) findViewById(R.id.alterar_foto)).setOnClickListener((View.OnClickListener) view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 0);
        });

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

    public void alterarMutante(View view) throws IOException {
        if (editNome.length() == 0) {
            Toast.makeText(this, "Deve preencher nome do mutante!!!", Toast.LENGTH_SHORT).show();
        } else if (editHabilidadeUm.length() <= 2 && editHabilidadeDois.length() <= 2 && editHabilidadeTres.length() <= 2) {
            Toast.makeText(this, "Deve preencher pelo menos uma habilidade!!!", Toast.LENGTH_SHORT).show();
        } else {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Cadastrando...");
            progressDialog.show();

            byte[] byteImage = null;
            if (uriImagem != null) {
                InputStream iStream = getContentResolver().openInputStream(uriImagem);
                byteImage = getBytes(iStream);
            }

            MutanteDB mutanteDB = new MutanteDB(idMutante, editNome.getText().toString(), byteImage);

            List<String> habilidadesList = new ArrayList<>();
            if (editHabilidadeUm.length() > 0) {
                habilidadesList.add(editHabilidadeUm.getText().toString());
            }
            if (editHabilidadeDois.length() > 0) {
                habilidadesList.add(editHabilidadeDois.getText().toString());
            }
            if (editHabilidadeTres.length() > 0) {
                habilidadesList.add(editHabilidadeTres.getText().toString());
            }
            String[] habilidadesArray = habilidadesList.toArray(new String[0]);

            Call<DefaultResponse> callDashboard = new RetrofitConfig().getMutanteService().editar(new EditarBody(mutanteDB, habilidadesArray));
            callDashboard.enqueue(new Callback<DefaultResponse>() {
                @Override
                public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getMensagem(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful() && response.body().isSucesso()) {
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<DefaultResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    public void alterarFoto(View view) {

    }

    public void removerMutante(View view) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Removendo mutante ...");
        progressDialog.show();

        Call<DefaultResponse> callMutantes = new RetrofitConfig().getMutanteService().deletar(idMutante);
        callMutantes.enqueue(new Callback<DefaultResponse>() {

            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), response.body().getMensagem(), Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) { }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0) {
            uriImagem = data.getData();
            image.setImageURI(uriImagem);
        }
    }

    private byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
}
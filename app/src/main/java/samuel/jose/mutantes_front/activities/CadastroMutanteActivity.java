package samuel.jose.mutantes_front.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.jose.mutantes_front.R;
import samuel.jose.mutantes_front.apiMutante.RetrofitConfig;
import samuel.jose.mutantes_front.model.DefaultResponse;
import samuel.jose.mutantes_front.model.Mutante;
import samuel.jose.mutantes_front.model.MutanteDB;
import samuel.jose.mutantes_front.model.NovoMutanteBody;

public class CadastroMutanteActivity extends AppCompatActivity {

    EditText nomeMutante, habilidadeUm, habilidadeDois, habilidadeTres;
    ImageView imageView;
    Uri uriImagem;
    String username;
    byte[] byteImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_mutante);

        nomeMutante = findViewById(R.id.inputNomeMutante);
        habilidadeUm = findViewById(R.id.inputHabilidadeUm);
        habilidadeDois = findViewById(R.id.inputHabilidadeDois);
        habilidadeTres = findViewById(R.id.inputHabilidadeTres);
        imageView = findViewById(R.id.inputImagem);
        username = getIntent().getStringExtra("username");

        ((Button) findViewById(R.id.carregarImagem)).setOnClickListener((View.OnClickListener) view -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0) {
            try {
                uriImagem = data.getData();
//                imageView.setImageURI(uriImagem);

                Bitmap input_bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriImagem);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                input_bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteImage = stream.toByteArray();

                Bitmap output_bmp = BitmapFactory.decodeByteArray(byteImage, 0, byteImage.length);
                imageView.setImageBitmap(Bitmap.createScaledBitmap(output_bmp, 300, 300, false));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void cadastrar(View view) throws IOException {
        if (nomeMutante.length() == 0) {
            Toast.makeText(this, "Deve preencher nome do mutante!!!", Toast.LENGTH_SHORT).show();
        } else if (habilidadeUm.length() <= 2 && habilidadeDois.length() <= 2 && habilidadeTres.length() <= 2) {
            Toast.makeText(this, "Deve preencher pelo menos uma habilidade!!!", Toast.LENGTH_SHORT).show();
        } else {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Cadastrando...");
            progressDialog.show();

            MutanteDB mutanteDB = new MutanteDB(nomeMutante.getText().toString(), username, byteImage);

            List<String> habilidadesList = new ArrayList<>();
            if (habilidadeUm.length() > 0) {
                habilidadesList.add(habilidadeUm.getText().toString());
            }
            if (habilidadeDois.length() > 0) {
                habilidadesList.add(habilidadeDois.getText().toString());
            }
            if (habilidadeTres.length() > 0) {
                habilidadesList.add(habilidadeTres.getText().toString());
            }
            String[] habilidadesArray = habilidadesList.toArray(new String[0]);

            Call<DefaultResponse> callDashboard = new RetrofitConfig().getMutanteService().novoMutante(new NovoMutanteBody(mutanteDB, habilidadesArray));
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
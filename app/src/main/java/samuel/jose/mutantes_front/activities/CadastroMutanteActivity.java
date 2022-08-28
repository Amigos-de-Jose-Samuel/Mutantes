package samuel.jose.mutantes_front.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import samuel.jose.mutantes_front.R;

public class CadastroMutanteActivity extends AppCompatActivity {

    EditText nomeMutante, habilidadeUm, habilidadeDois, habilidadeTres;
    ImageView imageView;
    Uri uriImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_mutante);

        nomeMutante = findViewById(R.id.inputNomeMutante);
        habilidadeUm = findViewById(R.id.inputHabilidadeUm);
        habilidadeDois = findViewById(R.id.inputHabilidadeDois);
        habilidadeTres = findViewById(R.id.inputHabilidadeTres);
        imageView = findViewById(R.id.inputImagem);

        ((Button) findViewById(R.id.carregarImagem)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0) {
            uriImagem = data.getData();
            imageView.setImageURI(uriImagem);
        }
    }

    public void cadastrar(View view) {
        if(habilidadeUm.length() <= 2 && habilidadeDois.length() <= 2 && habilidadeTres.length() <= 2) {
            Toast.makeText(this, "Deve preencher pelo menos uma habilidade!!!", Toast.LENGTH_SHORT).show();
        } else {

            Intent it = new Intent(this, MainActivity.class);
            it.putExtra("mensagem", "Mutante cadatrado com sucesso");
            startActivity(it);
        }
    }
}
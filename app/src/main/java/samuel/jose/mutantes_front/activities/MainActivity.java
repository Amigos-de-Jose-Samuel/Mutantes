package samuel.jose.mutantes_front.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import samuel.jose.mutantes_front.R;
import samuel.jose.mutantes_front.utils.DownloadTask;

public class MainActivity extends AppCompatActivity {

    TextView quantidadeMutantes, habilidadeUm, habilidadeDois, habilidadeTres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantidadeMutantes = findViewById(R.id.quantidadeMutantes);
        habilidadeUm = findViewById(R.id.habilidadeUm);
        habilidadeDois = findViewById(R.id.habilidadeDois);
        habilidadeTres = findViewById(R.id.habilidadeTres);

        habilidadeUm.setText("Força");
        habilidadeDois.setText("Voar");
        habilidadeTres.setText("Velocidade");

        Intent it = getIntent();
        Bundle params = it.getExtras();
        if (it != null && params != null) {
            String mensagem = params.getString("mensagem");
            Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
        }

    }

    public void cadastrarMutante(View view) {
        Intent it = new Intent(this, CadastroMutanteActivity.class);
        startActivity(it);
    }

    public void buscarMutantes(View view) {
        Intent it = new Intent(this, ListarTodosActivity.class);
        startActivity(it);
    }

    public void pesquisarPoder(View view) {
        Intent it = new Intent(this, MainActivity.class);
        startActivity(it);
    }
}
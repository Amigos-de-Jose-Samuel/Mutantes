package samuel.jose.mutantes_front.activities;

import androidx.appcompat.app.AppCompatActivity;
import samuel.jose.mutantes_front.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalheMutanteActivity extends AppCompatActivity {

    ImageView image;
    EditText editNome, editHabilidadeUm, editHabilidadeDois, editHabilidadeTres;
    TextView usuarioNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_mutante);

        image = findViewById(R.id.image);
        editNome = findViewById(R.id.editNome);
        editHabilidadeUm = findViewById(R.id.editHabilidadeUm);
        editHabilidadeDois = findViewById(R.id.editHabilidadeDois);
        editHabilidadeTres = findViewById(R.id.editHabilidadeTres);
        usuarioNome = findViewById(R.id.editHabilidadeTres);

        Intent it = getIntent();
        Bundle params = it.getExtras();
        if (it != null) {
            String nome = params.getString("nome");
            int media = params.getInt("image");
            String habilidadeUm = params.getString("habilidadeUm");
            String habilidadeDois = params.getString("habilidadeDois");
            String habilidadeTres = params.getString("habilidadeTres");
            String usuario = params.getString("usuarioNome");
            editNome.setText(nome);
            editHabilidadeUm.setText(habilidadeUm);
            editHabilidadeDois.setText(habilidadeDois);
            editHabilidadeTres.setText(habilidadeTres);
            usuarioNome.setText(usuario);
            image.setImageResource(media);
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
        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("mensagem", "Mutante removido com sucesso");
        startActivity(it);
    }
}
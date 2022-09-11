package samuel.jose.mutantes_front.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

    TextView quantidadeMutantes, habilidadeUm, habilidadeDois, habilidadeTres;
    EditText inputPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;

        quantidadeMutantes = findViewById(R.id.quantidadeMutantes);
        habilidadeUm = findViewById(R.id.habilidadeUm);
        habilidadeDois = findViewById(R.id.habilidadeDois);
        habilidadeTres = findViewById(R.id.habilidadeTres);
        inputPesquisar = findViewById(R.id.inputPesquisar);

        Intent it = getIntent();
        Bundle params = it.getExtras();
        if (it != null && params != null) {
            String mensagem = params.getString("mensagem");
            if(mensagem != null)
                Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            int quantidade = params.getInt("quantidade");
            quantidadeMutantes.setText("" + quantidade);
            habilidadeUm.setText(params.getString("habilidadeUm"));
            habilidadeDois.setText(params.getString("habilidadeDois"));
            habilidadeTres.setText(params.getString("habilidadeTres"));
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
        Intent it = new Intent(this, ListarTodosPoderActivity.class);
        it.putExtra("poder", inputPesquisar.getText().toString());
        startActivity(it);
    }
}
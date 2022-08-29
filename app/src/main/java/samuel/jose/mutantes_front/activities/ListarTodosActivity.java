package samuel.jose.mutantes_front.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import samuel.jose.mutantes_front.R;
import samuel.jose.mutantes_front.adapter.AdapterMutantes;
import samuel.jose.mutantes_front.apiMutante.RetrofitConfig;
import samuel.jose.mutantes_front.model.ListaMutantes;
import samuel.jose.mutantes_front.model.Mutante;
import samuel.jose.mutantes_front.utils.RecyclerItemClickListener;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ListarTodosActivity extends AppCompatActivity {
    private RecyclerView recyclerViewMutantes;
    private List<Mutante> mutantes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_todos);

        Context context = this;

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Buscando mutantes ...");
        progressDialog.show();

        Call<ListaMutantes> callMutantes = new RetrofitConfig().getMutanteService().getAllMutantes();
        callMutantes.enqueue(new Callback<ListaMutantes>() {

            @Override
            public void onResponse(Call<ListaMutantes> call, Response<ListaMutantes> response) {
                if(response.isSuccessful()) {
                    ListaMutantes listaMutantes = response.body();

                    for(Mutante mutante: listaMutantes.getMutantes()) {
                        mutantes.add(mutante);
                    }
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<ListaMutantes> call, Throwable t) {

            }
        });

        //Mutante obj;
        //obj = new Mutante(0, "Teste", "voo", "correr", "força");
        //mutantes.add(obj);
        //obj = new Mutante(0, "Aaaaa", "morrer", "reviver", "virar água");
        //mutantes.add(obj);
        //obj = new Mutante(0, "Wolverine", "regenerar", "força", "esqueleto afiado");
        //mutantes.add(obj);

        recyclerViewMutantes = findViewById(R.id.recyclerViewMutantes);

        AdapterMutantes adapter = new AdapterMutantes(mutantes);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewMutantes.setLayoutManager(layoutManager);
        recyclerViewMutantes.setHasFixedSize(true);
        recyclerViewMutantes.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewMutantes.setAdapter(adapter);
        recyclerViewMutantes.addOnItemTouchListener(
            new RecyclerItemClickListener(
                getApplicationContext(),
                    recyclerViewMutantes,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Mutante obj = mutantes.get(position);

                        Intent it = new Intent(context, DetalheMutanteActivity.class);
                        it.putExtra("nome", obj.getNome());
                        it.putExtra("image", obj.getImg());
                        it.putExtra("habilidadeUm", obj.getHabilidadeUm());
                        it.putExtra("habilidadeDois", obj.getHabilidadeDois());
                        it.putExtra("habilidadeTres", obj.getHabilidadeTres());
                        startActivity(it);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
            )
        );
    }
}
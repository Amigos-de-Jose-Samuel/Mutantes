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
import samuel.jose.mutantes_front.model.DetalheResponse;
import samuel.jose.mutantes_front.model.ListarMutantesResponse;
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


public class ListarTodosPoderActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMutantes;
    private List<Mutante> mutantes;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_todos_poder);
        context = this;
        getMutantes(context);
    }

    public void getMutantes(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Buscando mutantes com poder fornecido ...");
        progressDialog.show();

        mutantes = new ArrayList<>();

        Intent it = getIntent();
        Bundle params = it.getExtras();
        if (it != null) {
            String poder = params.getString("poder");

            Call<ListarMutantesResponse> callPoderMutantes = new RetrofitConfig().getMutanteService().pesquisar(poder);
            callPoderMutantes.enqueue(new Callback<ListarMutantesResponse>() {

                @Override
                public void onResponse(Call<ListarMutantesResponse> call, Response<ListarMutantesResponse> response) {
                    if(response.isSuccessful()) {
                        ListarMutantesResponse listarMutantesResponse = response.body();

                        for(Mutante mutante: listarMutantesResponse.getMutantes()) {
                            mutantes.add(mutante);
                        }

                        recyclerViewMutantes = findViewById(R.id.recyclerViewMutantesPoder);

                        AdapterMutantes adapter = new AdapterMutantes(mutantes);

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerViewMutantes.setLayoutManager(layoutManager);
                        recyclerViewMutantes.setHasFixedSize(true);
                        recyclerViewMutantes.addItemDecoration(new DividerItemDecoration(context, LinearLayout.VERTICAL));
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
                                                it.putExtra("id", obj.getId());
                                                startActivity(it);
                                                finish();
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

                        progressDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<ListarMutantesResponse> call, Throwable t) {

                }
            });
        }
    }
}
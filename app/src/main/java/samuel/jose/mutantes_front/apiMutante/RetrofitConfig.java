package samuel.jose.mutantes_front.apiMutante;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://localhost:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MutanteService getMutanteService() {
        return this.retrofit.create(MutanteService.class);
    }
}

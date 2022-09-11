package samuel.jose.mutantes_front.apiMutante;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public MutanteService getMutanteService() {
        return this.retrofit.create(MutanteService.class);
    }
}

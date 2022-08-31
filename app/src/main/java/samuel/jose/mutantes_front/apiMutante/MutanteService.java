package samuel.jose.mutantes_front.apiMutante;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import samuel.jose.mutantes_front.model.DashboardResponse;
import samuel.jose.mutantes_front.model.DefaultResponse;
import samuel.jose.mutantes_front.model.DetalheResponse;
import samuel.jose.mutantes_front.model.ListaMutantes;
import samuel.jose.mutantes_front.model.LoginBody;
import samuel.jose.mutantes_front.model.NovoMutanteBody;

public interface MutanteService {

    @POST("/login")
    Call<DefaultResponse> login(@Body LoginBody loginBody);

    @GET("/dashboard")
    Call<DashboardResponse> dashboard();

    @POST("/novo-mutante")
    Call<DefaultResponse> novoMutante(@Body NovoMutanteBody novoMutanteBody);

    @GET("/listar")
    Call<ListaMutantes> getAllMutantes();

    @GET("/detalhe/{id}")
    Call<DetalheResponse> detalhe(@Path("id") int id);
}

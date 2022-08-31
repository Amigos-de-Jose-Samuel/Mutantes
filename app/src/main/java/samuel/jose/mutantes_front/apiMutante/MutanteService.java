package samuel.jose.mutantes_front.apiMutante;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import samuel.jose.mutantes_front.model.APIResponse;
import samuel.jose.mutantes_front.model.ListaMutantes;
import samuel.jose.mutantes_front.model.Login;

public interface MutanteService {

    @POST("/login")
    Call<APIResponse> login(@Body Login login);

    @GET("/listar")
    Call<ListaMutantes> getAllMutantes();
}

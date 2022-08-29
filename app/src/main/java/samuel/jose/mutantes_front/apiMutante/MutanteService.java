package samuel.jose.mutantes_front.apiMutante;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import samuel.jose.mutantes_front.model.ListaMutantes;

public interface MutanteService {

    @GET("listar")
    Call<ListaMutantes> getAllMutantes();
}

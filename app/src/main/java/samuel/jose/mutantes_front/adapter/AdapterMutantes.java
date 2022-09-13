package samuel.jose.mutantes_front.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import samuel.jose.mutantes_front.R;

import samuel.jose.mutantes_front.model.Mutante;

public class AdapterMutantes extends RecyclerView.Adapter<AdapterMutantes.MyViewHolder> {

    private List<Mutante> mutantes;

    public AdapterMutantes(List<Mutante> list) {
        this.mutantes = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome, habilidadeUm, habilidadeDois, habilidadeTres;
        ImageView img;

        public MyViewHolder(View view) {
            super(view);
            nome = view.findViewById(R.id.textViewName);
            habilidadeUm = view.findViewById(R.id.textHabilidadeUm);
            //habilidadeDois = view.findViewById(R.id.textHabilidadeDois);
            //habilidadeTres = view.findViewById(R.id.textHabilidadeTres);
            img = view.findViewById(R.id.imageViewMutante);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_mutante, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Mutante obj = mutantes.get(position);
        holder.nome.setText(obj.getNome());
        if (obj.getFoto() != null) {
            try {
                byte[] encodeByte = Base64.decode(obj.getFoto(),Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                holder.img.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 300, 300, false));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return mutantes.size();
    }
}

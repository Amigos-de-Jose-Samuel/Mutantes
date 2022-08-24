package samuel.jose.mutantes_front;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import samuel.jose.mutantes_front.utils.DownloadTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);
        DownloadTask task = new DownloadTask(this, imageView);
        task.execute("https://yt3.ggpht.com/ytc/AMLnZu94v_ipnhXrgafSpoGb_vY8A0zNXwDrzqMa7UiWoA=s900-c-k-c0x00ffffff-no-rj");
    }
}
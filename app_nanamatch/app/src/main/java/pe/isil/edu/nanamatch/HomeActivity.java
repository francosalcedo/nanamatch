package pe.isil.edu.nanamatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.Iterator;

import pe.isil.edu.nanamatch.entity.Client;

public class HomeActivity extends AppCompatActivity {

    private ImageView fotoNinera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        fotoNinera = findViewById(R.id.fotoNinera);

        Picasso.with(this).load("http://i.imgur.com/UvcD8QO.jpg").into(fotoNinera);

        // Obteniendo datos del cliente
        Client client = getIntent().getParcelableExtra("client");
        try{
            JSONObject nanas = new JSONObject(getIntent().getExtras().getString("nanas"));

            Iterator keys = nanas.keys();
            while(keys.hasNext()) {
                String dynamicKey = (String)keys.next();
                JSONObject line = nanas.getJSONObject(dynamicKey);

                // Aqui estamos recorriendo los datos de las niñeras

                String name         = line.getString("name");
                String email        = line.getString("email");
                String last_name    = line.getString("last_name");
                String info         = line.getString("info");
                String img          = line.getString("img");
                int id              = line.getInt("id");
                int id_distrit      = line.getInt("id_distrit");

                Toast.makeText(getApplicationContext(), "Nana: "+name, Toast.LENGTH_SHORT).show();

            }

        }catch (Exception e){
            Log.d("Error","en el json de nanas");
            e.printStackTrace();
        }



        Toast.makeText(getApplicationContext(), "hola: " + client.getName(), Toast.LENGTH_SHORT).show();

    }
}

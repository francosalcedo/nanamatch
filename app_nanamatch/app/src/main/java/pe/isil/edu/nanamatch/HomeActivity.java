package pe.isil.edu.nanamatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.Iterator;

import pe.isil.edu.nanamatch.entity.Client;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView fotoNinera;

    private TextView txtNombreNinera;
    private TextView txtDistritoNinera;

    //botones
    private Button btnAnteriorN;
    private Button btnSiguienteN;
    private Button btnEscogerN;

    private int idS;

    private nanass[] onanass = new nanass[5];

    private class nanass{
        private String img;
        private String name;
        private String distrit;

        public nanass(){}

        public String getImg(){return img;}
        public void setImg(String img){this.img = img;}

        public String getName() {return name;}
        public void setName(String name) {this.name = name;}

        public String getDistrit() {return distrit;}
        public void setDistrit(String distrit) {
            if (distrit.equals("1")){distrit = "Miraflores";}
            this.distrit = distrit;}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        fotoNinera          = findViewById(R.id.fotoNinera);
        txtNombreNinera     = findViewById(R.id.txtNombreNinera);
        txtDistritoNinera   = findViewById(R.id.txtDistritoNinera);

        //Botones
        btnAnteriorN    = findViewById(R.id.btnAnteriorN);
        btnSiguienteN   = findViewById(R.id.btnSiguienteN);
        btnEscogerN     = findViewById(R.id.btnEscogerN);

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

                //Mas webadas que hizo ernesto
                int i = Integer.parseInt(dynamicKey);
                //ident[i] = line.getString("img");

                onanass[i] = new nanass();
                onanass[i].setImg(img);
                onanass[i].setName(name);
                onanass[i].setDistrit(Integer.toString(id_distrit));

            }
        }catch (Exception e){
            Log.d("Error","en el json de nanas");
            e.printStackTrace();
        }
        idS = 0;
        Picasso.with(this).load(onanass[idS].getImg()).into(fotoNinera);

        //Toast.makeText(getApplicationContext(), "hola: " + client.getName(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),"bitch: "+ident[idS], Toast.LENGTH_SHORT).show();

        btnAnteriorN.setOnClickListener(this);
        btnSiguienteN.setOnClickListener(this);
        btnEscogerN.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAnteriorN:
                if (idS >= 1) idS = idS - 1;

                Picasso.with(this).load(onanass[idS].getImg()).into(fotoNinera);
                txtNombreNinera.setText(onanass[idS].getName());
                txtDistritoNinera.setText(onanass[idS].getDistrit());

                break;
            case R.id.btnSiguienteN:
                if (idS >= 0 && idS < onanass.length-1) idS = idS + 1;

                Picasso.with(this).load(onanass[idS].getImg()).into(fotoNinera);
                txtNombreNinera.setText(onanass[idS].getName());
                txtDistritoNinera.setText(onanass[idS].getDistrit());
                break;
            case R.id.btnEscogerN:
                Toast.makeText(getApplicationContext(),
                                "Escogiste a: " + onanass[idS].getName(),
                                Toast.LENGTH_SHORT
                ).show();
                break;
        }
    }
}

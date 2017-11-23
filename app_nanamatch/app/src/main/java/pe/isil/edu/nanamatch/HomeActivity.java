package pe.isil.edu.nanamatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import pe.isil.edu.nanamatch.entity.Client;

import pe.isil.edu.nanamatch.entity.Nana;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView fotoNinera;

    private TextView txtNombreNinera;
    private TextView txtDistritoNinera;

    //botones
    private Button btnAnteriorN;
    private Button btnSiguienteN;
    private Button btnEscogerN;

    private int idS;

    private String todos;

    ArrayList<Nana> onanas = new ArrayList<Nana>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        fotoNinera = (ImageView)findViewById(R.id.fotoNinera);
        txtNombreNinera = (TextView)findViewById(R.id.txtNombreNinera);
        txtDistritoNinera =(TextView)findViewById(R.id.txtDistritoNinera);

        //Botones
        btnAnteriorN = (Button)findViewById(R.id.btnAnteriorN);
        btnSiguienteN =(Button)findViewById(R.id.btnSiguienteN);
        btnEscogerN = (Button)findViewById(R.id.btnEscogerN);

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

                final Nana nana = new Nana();
                nana.setId(id);
                nana.setName(name);
                nana.setLast_name(last_name);
                nana.setEmail(email);
                nana.setInfo(info);
                nana.setImg(img);
                nana.setDistrit(id_distrit);

                onanas.add(nana);

                //ident[i] = line.getString("img");
                /*onanass[i] = new nanass();
                onanass[i].setImg(img);
                onanass[i].setName(name);
                onanass[i].setDistrit(Integer.toString(id_distrit));*/
            }
        }catch (Exception e){
            Log.d("Error","en el json de nanas");
            e.printStackTrace();
        }
        idS = 0;
        Picasso.with(this).load(onanas.get(idS).getImg()).into(fotoNinera);
        txtNombreNinera.setText(onanas.get(idS).getName().toString());
        txtDistritoNinera.setText(onanas.get(idS).getDistrit().toString());

        //Toast.makeText(getApplicationContext(), "hola: " + client.getName(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),"bitch: "+ident[idS], Toast.LENGTH_SHORT).show();

        /*for (Nana s : onanas){
            todos = todos + s.getId() + "\n";

        }Toast.makeText(getApplicationContext(),todos,Toast.LENGTH_SHORT).show();*/
        btnAnteriorN.setOnClickListener(this);
        btnSiguienteN.setOnClickListener(this);
        btnEscogerN.setOnClickListener(this);
        fotoNinera.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAnteriorN:
                if (idS > 0){idS--;}else {idS = onanas.size()-1;}
                Picasso.with(this).load(onanas.get(idS).getImg()).into(fotoNinera);
                txtNombreNinera.setText(onanas.get(idS).getName().toString());
                txtDistritoNinera.setText(onanas.get(idS).getDistrit().toString());
                break;
            case R.id.btnSiguienteN:
                if (idS < onanas.size()-1){idS++;}else {idS = 0;}
                Picasso.with(this).load(onanas.get(idS).getImg()).into(fotoNinera);
                txtNombreNinera.setText(onanas.get(idS).getName().toString());
                txtDistritoNinera.setText(onanas.get(idS).getDistrit().toString());
                break;
            case R.id.btnEscogerN:
                idS = idS;
                break;
            case R.id.fotoNinera:
                Intent intent = new Intent(view.getContext(), NanaDetailActivity.class);
                intent.putExtra("oNanas", onanas);
                intent.putExtra("Ids", idS);
                startActivityForResult(intent, 0);
                break;
        }
    }
}

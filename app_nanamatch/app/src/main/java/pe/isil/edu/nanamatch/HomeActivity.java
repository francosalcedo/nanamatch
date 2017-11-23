package pe.isil.edu.nanamatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    private Button btnProfile;

    private int idS;
    ArrayList<Nana> onanas = new ArrayList<Nana>();
    Client client = new Client();

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

        btnProfile  = (Button)findViewById(R.id.btnProfile);

        // Obteniendo datos del cliente
        client = getIntent().getParcelableExtra("client");

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

                //

                final Nana nana = new Nana();
                nana.setId(id);
                nana.setName(name);
                nana.setLast_name(last_name);
                nana.setEmail(email);
                nana.setInfo(info);
                nana.setImg(img);
                nana.setDistrit(id_distrit);
                nana.setDistritName(nana.getDistrit());

                onanas.add(nana);
            }

        }catch (Exception e){
            Log.d("Error","en el json de nanas");
            e.printStackTrace();
        }
        idS = 0;
        Picasso.with(this).load(onanas.get(idS).getImg()).into(fotoNinera);
        txtNombreNinera.setText(onanas.get(idS).getName());
        txtDistritoNinera.setText(onanas.get(idS).getDistrit());

        btnAnteriorN.setOnClickListener(this);
        btnSiguienteN.setOnClickListener(this);
        btnEscogerN.setOnClickListener(this);
        fotoNinera.setOnClickListener(this);

        btnProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAnteriorN:
                if (idS > 0){idS--;}else {idS = onanas.size()-1;}
                Picasso.with(this).load(onanas.get(idS).getImg()).into(fotoNinera);
                txtNombreNinera.setText(onanas.get(idS).getName());
                txtDistritoNinera.setText(onanas.get(idS).getDistrit());
                break;

            case R.id.btnSiguienteN:
                if (idS < onanas.size()-1){idS++;}else {idS = 0;}
                Picasso.with(this).load(onanas.get(idS).getImg()).into(fotoNinera);
                txtNombreNinera.setText(onanas.get(idS).getName());
                txtDistritoNinera.setText(onanas.get(idS).getDistrit());
                break;

            case R.id.btnEscogerN:
                // idS = idS;
                btnEscogerN.startAnimation(AnimationUtils.loadAnimation(this, R.animator.anim_shake));
                Toast.makeText(getApplicationContext(), "Match!", Toast.LENGTH_SHORT).show();

                Nana nana = new Nana();
                nana.setDistritName(onanas.get(idS).getDistrit());
                nana.setDistrit(onanas.get(idS).getDistritInt());
                nana.setEmail(onanas.get(idS).getEmail());
                nana.setId(onanas.get(idS).getId());
                nana.setImg(onanas.get(idS).getImg());
                nana.setInfo(onanas.get(idS).getInfo());
                nana.setLast_name(onanas.get(idS).getLast_name());
                nana.setName(onanas.get(idS).getName());

                Intent goMatch = new Intent(view.getContext(), MatchActivity.class);
                goMatch.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                goMatch.putExtra("nana", nana);
                goMatch.putExtra("client", client);
                startActivity(goMatch);
                overridePendingTransition(R.animator.slide_up, R.anim.stay);

                break;

            case R.id.fotoNinera:
                Intent intent = new Intent(view.getContext(), NanaDetailActivity.class);
                intent.putExtra("oNanas", onanas);
                intent.putExtra("Ids", idS);
                startActivity(intent);
                break;

            case R.id.btnProfile:
                Intent intent1 = new Intent(view.getContext(), ProfileActivity.class);
                intent1.putExtra("Client", client);
                startActivity(intent1);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
    }

}
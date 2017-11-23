package pe.isil.edu.nanamatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pe.isil.edu.nanamatch.entity.Nana;


public class NanaDetailActivity extends AppCompatActivity {

    private ImageView imgNanaProfile;
    private TextView txtNanaName;
    private TextView txtNanaDistrit;
    private TextView txtNanaRating;
    private TextView txtNanaDescription;
    private TextView txtServicesCount;
    private TextView txtDateService;
    private TextView txtNanaComentary;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ninera_detalle);
        getSupportActionBar().hide();

        imgNanaProfile = (ImageView)findViewById(R.id.imgNanaProfile);
        txtNanaName    = (TextView)findViewById(R.id.txtNanaName);
        txtNanaDistrit = (TextView)findViewById(R.id.txtNanaDistrit);
        txtNanaRating  = (TextView)findViewById(R.id.txtNanaRating);
        txtNanaDescription = (TextView)findViewById(R.id.txtNanaDescription);
        txtServicesCount = (TextView)findViewById(R.id.txtServicesCount);
        txtDateService = (TextView)findViewById(R.id.txtDateService);
        txtNanaComentary = (TextView)findViewById(R.id.txtNanaComentary);

        ArrayList<Nana> onanas = (ArrayList<Nana>)getIntent().getSerializableExtra("oNanas");
        int idS = (int)getIntent().getSerializableExtra("Ids");

        Picasso.with(this).load(onanas.get(idS).getImg()).into(imgNanaProfile);
        txtNanaName.setText(onanas.get(idS).getName().toString());
        txtNanaDistrit.setText(onanas.get(idS).getDistrit().toString());
        txtNanaName.setText(onanas.get(idS).getName().toString());
        //txtNanaRating.setText(onanas.get(idS).getRating().toString());
        txtNanaDescription.setText(onanas.get(idS).getInfo().toString());
        //txtServicesCount.setText(onanas.get(idS).getName().toString());
        //txtDateService.setText(onanas.get(idS).getName().toString());
        //txtNanaComentary.setText(onanas.get(idS).getName().toString());

    }

}

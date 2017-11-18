package pe.isil.edu.nanamatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    private ImageView fotoNinera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();

        fotoNinera = (ImageView)findViewById(R.id.fotoNinera);

        Picasso.with(this).load("http://i.imgur.com/UvcD8QO.jpg").into(fotoNinera);
    }
}

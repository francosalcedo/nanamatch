package pe.isil.edu.nanamatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pe.isil.edu.nanamatch.util.CheckNetwork;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Widgets
    private Button btnIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // verificando conexion a internet
        if(!CheckNetwork.isInternetAvailable(getApplicationContext())){
            Toast.makeText(getApplicationContext(),"No hay conecion a internet", Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"Se necesita internet para usar la app", Toast.LENGTH_LONG).show();
        }


        // Assign id
        btnIntro = (Button)findViewById(R.id.btnIntro);

        // Set button listener
        btnIntro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), LoginActivity.class);
        startActivityForResult(intent, 0);
    }
}

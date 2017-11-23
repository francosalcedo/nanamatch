package pe.isil.edu.nanamatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Widgets
    private Button btnIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

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

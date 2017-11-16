package pe.isil.edu.nanamatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    // Widgets
    private Button  btnBack;
    private Button  btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        // Assign id
        btnBack     = (Button)findViewById(R.id.btnBack);
        btnRegister = (Button)findViewById(R.id.btnRegister);

        // Set button listener
        btnBack.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        // Animations

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                this.finish();
                break;
            case R.id.btnRegister:
                Toast.makeText(getApplicationContext(), "¡Registro completo!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

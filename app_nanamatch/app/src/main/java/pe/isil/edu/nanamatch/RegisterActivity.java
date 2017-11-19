package pe.isil.edu.nanamatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import pe.isil.edu.nanamatch.util.Register;
import pe.isil.edu.nanamatch.util.RegisterListener;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RegisterListener {

    // Widgets
    private Button  btnBack;
    private Button  btnRegister;

    private EditText txtUser;
    private EditText txtPass;
    private EditText lblName;
    private EditText lblLastName;
    private EditText lblAddress;
    private EditText lblPhone;

    private Button  btnMan;
    private Button  btnWoman;

    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        // Assign id
        btnBack     = (Button)findViewById(R.id.btnBack);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        txtUser     = (EditText)findViewById(R.id.txtUser);
        txtPass     = (EditText)findViewById(R.id.txtPass);
        lblName     = (EditText)findViewById(R.id.lblName);
        lblLastName = (EditText)findViewById(R.id.lblLastName);
        lblAddress  = (EditText)findViewById(R.id.lblAddress);
        lblPhone    = (EditText)findViewById(R.id.lblPhone);

        btnMan      = (Button)findViewById(R.id.btnMan);
        btnWoman    = (Button)findViewById(R.id.btnWoman);

        // Set button listener
        btnBack.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        btnMan.setOnClickListener(this);
        btnWoman.setOnClickListener(this);

        // Animations

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                this.finish();
                break;
            case R.id.btnRegister:
                Register register = new Register();
                register.addConnectionListener(this);
                register.register(
                        getApplicationContext(),
                        txtUser.getText().toString(),
                        txtPass.getText().toString(),
                        lblName.getText().toString(),
                        lblLastName.getText().toString(),
                        gender,
                        lblAddress.getText().toString(),
                        lblPhone.getText().toString(),
                        "2"
                );
                break;

            case R.id.btnMan:
                gender = "1";
                break;
            case R.id.btnWoman:
                gender = "2";
                break;
        }
    }

    @Override
    public void onRegisterIntent(Register connection) {
        if (connection.returnRegisterSatus() == 1)
        {
            JSONObject data = connection.returnRegisterData();
            Toast("Registrado correctamente");
            this.finish();
        } else {
            Toast("Error al registrar");
        }
    }

    public void Toast(String m) {
        Toast.makeText(getApplicationContext(), m, Toast.LENGTH_SHORT).show();
    }
}

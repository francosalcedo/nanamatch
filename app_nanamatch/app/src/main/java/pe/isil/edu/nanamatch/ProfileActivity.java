package pe.isil.edu.nanamatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import pe.isil.edu.nanamatch.entity.Client;

public class ProfileActivity extends AppCompatActivity {

    private TextView txtClientName;
    private TextView txtClientAddress;
    private TextView txtClientPhone;

    Client client = new Client();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        client = getIntent().getParcelableExtra("Client");

        txtClientName = (TextView)findViewById(R.id.txtClientName);
        txtClientAddress = (TextView)findViewById(R.id.txtClientAddress);
        txtClientPhone = (TextView)findViewById(R.id.txtClientPhone);

        txtClientName.setText(client.getName() + " " + client.getLast_name());
        txtClientAddress.setText(client.getId_distric() + ", " + client.getAddress());
        txtClientPhone.setText(client.getPhone_number() + "");
    }
}

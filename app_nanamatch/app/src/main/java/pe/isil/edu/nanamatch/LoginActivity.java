package pe.isil.edu.nanamatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import pe.isil.edu.nanamatch.util.ApiCallback;
import pe.isil.edu.nanamatch.rest.List;


import pe.isil.edu.nanamatch.entity.Nana;
import pe.isil.edu.nanamatch.util.Login;
import pe.isil.edu.nanamatch.util.LoginListener;
import pe.isil.edu.nanamatch.entity.Client;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginListener {

    // Widgets
    private ImageView       imgLogo2;
    private RelativeLayout  layoutLogin2;
    private Button          btnLogin;
    private TextView        txtRegister;
    private EditText        txtUser;
    private EditText        txtPass;

    private int LoginStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        // Assign id
        layoutLogin2 = (RelativeLayout)findViewById(R.id.layoutLogin2);
        imgLogo2     = (ImageView)findViewById(R.id.imgLogo2);
        txtUser     = (EditText)findViewById(R.id.txtUser);
        txtPass     = (EditText)findViewById(R.id.txtPass);
        txtRegister = (TextView)findViewById(R.id.txtRegister);
        btnLogin    = (Button)findViewById(R.id.btnLogin);

        // Set button listener
        btnLogin.setOnClickListener(this);
        txtRegister.setOnClickListener(this);

        // Animations
        imgLogo2.startAnimation(AnimationUtils.loadAnimation(this, R.animator.anim_login));
        layoutLogin2.startAnimation(AnimationUtils.loadAnimation(this, R.animator.anim_login2));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Login login = new Login();
                login.addConnectionListener(this);
                login.login(getApplicationContext(),txtUser.getText().toString(), txtPass.getText().toString());


                break;
            case R.id.txtRegister:
                Intent intent2 = new Intent(view.getContext(), RegisterActivity.class);
                startActivityForResult(intent2, 0);
                break;
        }
    }

    @Override
    public void onLoginIntent(Login connection)
    {
        if(connection.returnLoginSatus() == 1)
        {
            LoginStatus = connection.returnLoginSatus();
            JSONObject data = connection.returnLoginData();
            try{

                /*
                *
                *           Ya se puede setear todos los datos del usuario
                *
                */

                String user_name = data.getString("name");
                toast("Bienvenido " + user_name);

                final Client client = new Client();
                client.setName(user_name);
                client.setAddress(data.getString("address"));
                client.setEmail(data.getString("email"));
                client.setGender(data.getInt("gender"));
                client.setId(data.getInt("id"));
                client.setId_distric(data.getInt("id_distrit"));
                client.setLast_name(data.getString("last_name"));
                client.setPhone_number(data.getInt("phone_number"));


                final Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                List list = new List();
                list.list(getApplicationContext(), "nana", new ApiCallback() {
                    @Override
                    public void onSuccess(String result) {
                        /*
                            seteamos dentro del onSuccess porque esto es un metodo asyncronico
                        */
                        Log.d("CRJJJ::", result);

                        intent.putExtra("nanas", result);
                        intent.putExtra("client", client);
                        startActivity(intent);

                    }
                });


            }catch (Exception e){}
        }else{
            btnLogin.startAnimation(AnimationUtils.loadAnimation(this, R.animator.anim_shake));
            toast("Login Fallido, intente nuevamente");
        }
    }

    public void toast( String m)
    {
        Toast.makeText(getApplicationContext(), m, Toast.LENGTH_SHORT).show();
    }

}

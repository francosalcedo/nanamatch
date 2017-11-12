package pe.isil.edu.nanamatch;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Widgets
    private RelativeLayout layoutIntro;
    private RelativeLayout layoutLogin;
    private RelativeLayout layoutLogin2;
    private ImageView imgLogo2;
    private TextView txtTitle;
    private TextView txtDescription;
    private TextView txtLogin;
    private TextView txtUsername;
    private TextView txtPassword;
    private TextView txtRegister;
    private EditText txtUser;
    private EditText txtPass;
    private Button   btnIntro;
    private Button   btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // Import fonts
        final Typeface montMedium    = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Medium.ttf");
        final Typeface montRegular   = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");
        final Typeface montSemiBold  = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-SemiBold.ttf");

        // Assign id
        layoutIntro     = (RelativeLayout)findViewById(R.id.layoutIntro);
        layoutLogin     = (RelativeLayout)findViewById(R.id.layoutLogin);
        layoutLogin2    = (RelativeLayout)findViewById(R.id.layoutLogin2);
        imgLogo2        = (ImageView)findViewById(R.id.imgLogo2);
        txtTitle        = (TextView)findViewById(R.id.txtTitle);
        txtDescription  = (TextView)findViewById(R.id.txtDescription);
        txtLogin        = (TextView)findViewById(R.id.txtLogin);
        txtUsername     = (TextView)findViewById(R.id.txtUsername);
        txtPassword     = (TextView)findViewById(R.id.txtPassword);
        txtRegister     = (TextView)findViewById(R.id.txtRegister);
        txtUser         = (EditText)findViewById(R.id.txtUser);
        txtPass         = (EditText)findViewById(R.id.txtPass);
        btnIntro        = (Button)findViewById(R.id.btnIntro);
        btnLogin        = (Button)findViewById(R.id.btnLogin);

        // Set fonts
        txtTitle.setTypeface(montMedium);
        txtDescription.setTypeface(montRegular);
        txtLogin.setTypeface(montSemiBold);
        txtUsername.setTypeface(montRegular);
        txtPassword.setTypeface(montRegular);
        txtRegister.setTypeface(montRegular);
        txtUser.setTypeface(montRegular);
        txtPass.setTypeface(montRegular);

        // Set button listener
        btnIntro.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        txtRegister.setOnClickListener(this);

        layoutIntro.setVisibility(View.VISIBLE);
        layoutLogin.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnIntro:
                layoutIntro.startAnimation(AnimationUtils.loadAnimation(this, R.animator.anim_intro));
                layoutIntro.setVisibility(view.GONE);
                layoutLogin.setVisibility(view.VISIBLE);
                imgLogo2.startAnimation(AnimationUtils.loadAnimation(this, R.animator.anim_login));
                layoutLogin2.startAnimation(AnimationUtils.loadAnimation(this, R.animator.anim_login2));
                break;
            case R.id.btnLogin:
                break;
            case R.id.txtRegister:
                Intent intent = new Intent (view.getContext(), RegisterActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
    }
}

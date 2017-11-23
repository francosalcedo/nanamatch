package pe.isil.edu.nanamatch;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import pe.isil.edu.nanamatch.entity.Client;
import pe.isil.edu.nanamatch.entity.Nana;
import pe.isil.edu.nanamatch.rest.createService;
import pe.isil.edu.nanamatch.util.ApiCallback;

public class MatchActivity extends Activity implements View.OnClickListener{

    public Button cancel;
    public Button take;
    public Button end;
    public TextView viewMessage1;
    public TextView showName;
    public EditText endText;
    public TextView viewMessage;


    public Client client = new Client();
    public  Nana nana     = new Nana();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        client = getIntent().getParcelableExtra("client");
        nana   = getIntent().getParcelableExtra("nana");


        cancel  = findViewById(R.id.cancel);
        take    = findViewById(R.id.take);
        showName = findViewById(R.id.showName);
        viewMessage1 = findViewById(R.id.viewMessage1);

        end      = findViewById(R.id.end);
        endText  = findViewById(R.id.endText);
        viewMessage = findViewById(R.id.viewMessage);

        end.setVisibility(View.GONE);
        endText.setVisibility(View.GONE);
        viewMessage.setVisibility(View.GONE);


        showName.setText(nana.getName() + " " + nana.getLast_name());

        cancel.setOnClickListener(this);
        take.setOnClickListener(this);
        end.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel:
                finish();
                break;

            case R.id.take:
                cancel.setVisibility(View.GONE);
                take.setVisibility(View.GONE);
                showName.setVisibility(View.GONE);
                viewMessage1.setVisibility(View.GONE);

                end.setVisibility(View.VISIBLE);
                endText.setVisibility(View.VISIBLE);
                viewMessage.setVisibility(View.VISIBLE);

                break;
            case R.id.end:
                createService send = new createService();
                String id_client = String.valueOf(client.getId());
                String id_nana   = String.valueOf(nana.getId());

                Log.d("ddd", "PTMMMMM");

                send.createService(getApplicationContext(),
                        id_client,
                        id_nana,
                        endText.getText().toString(),
                        new ApiCallback(){
                    @Override
                    public void onSuccess(String result) {

                        Log.d("AQUIIII::", result);


                    }
                });

                break;
        }
    }
}

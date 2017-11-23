package pe.isil.edu.nanamatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import pe.isil.edu.nanamatch.entity.Nana;
import pe.isil.edu.nanamatch.rest.ListService;
import pe.isil.edu.nanamatch.util.ApiCallback;


public class NanaDetailActivity extends AppCompatActivity {

    private ImageView imgNanaProfile;
    private TextView txtNanaName;
    private TextView txtNanaDistrit;
    private TextView txtNanaRating;
    private TextView txtNanaDescription;
    private TextView txtServicesCount;
    private TextView txtDateService;
    private TextView txtNanaComentary;

    public TextView todo;

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

        todo = findViewById(R.id.todo);


        ArrayList<Nana> onanas = (ArrayList<Nana>)getIntent().getSerializableExtra("oNanas");
        int idS = (int)getIntent().getSerializableExtra("Ids");


        ListService list = new ListService();
        String idd = String.valueOf(onanas.get(idS).getId());

        Log.d("KKEEE", "JEJDIDJD");

        list.list(getApplicationContext(), idd, new ApiCallback() {
            @Override
            public void onSuccess(String result) {

                try{
                    JSONObject r = new JSONObject(result);
                    int status = Integer.parseInt(r.getString("status"));
                    if(status == 1){
                        JSONObject data = new JSONObject(r.getString("data"));

                        String all = "";
                        Iterator keys = data.keys();
                        while(keys.hasNext()) {
                            String dynamicKey = (String)keys.next();
                            JSONObject line = data.getJSONObject(dynamicKey);


                            String comment = line.getString("comment");
                            all += "\nComentario:\n"+ comment +"\n";

                        }

                        todo.setText(all);



                    }else{
                        Toast.makeText(getApplicationContext(), "Error.", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Log.e("errorrrrrr", e.getMessage());
                }
            }
        });


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

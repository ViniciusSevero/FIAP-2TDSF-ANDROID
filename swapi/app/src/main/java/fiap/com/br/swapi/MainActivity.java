package fiap.com.br.swapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import fiap.com.br.swapi.entity.People;
import fiap.com.br.swapi.util.SwapiRequestInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView name, height, mass, hair_color, skin_color, eye_color, birth_year, gender, homeworld;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.name = (TextView) findViewById(R.id.name);
        this.height = (TextView) findViewById(R.id.height);
        this.mass = (TextView) findViewById(R.id.mass);
        this.hair_color = (TextView) findViewById(R.id.hair_color);
        this.skin_color = (TextView) findViewById(R.id.skin_color);
        this.eye_color = (TextView) findViewById(R.id.eye_color);
        this.birth_year = (TextView) findViewById(R.id.birth_year);
        this.gender = (TextView) findViewById(R.id.gender);
        this.homeworld = (TextView) findViewById(R.id.homeworld);

        this.ivImage = (ImageView) findViewById(R.id.loading);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(ivImage);
        Glide.with(this)
                .load(R.drawable.tiefighter)
                .into(imageViewTarget);

        carregarPeoples();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void carregarPeoples(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://swapi.co/api/people/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SwapiRequestInterface api = retrofit
                .create(SwapiRequestInterface.class);

        api.getPeople().enqueue(new Callback<People>() {
            @Override
            public void onResponse(Call<People> call, Response<People> response) {
                People people = response.body();
                preencherTela(people);

            }

            @Override
            public void onFailure(Call<People> call, Throwable t) {
                Log.i("Erro: ", t.getMessage());
                t.printStackTrace();
            }


        });
    }

    public void preencherTela(People people){
        this.name.setText(people.getName());
        this.height.setText(people.getHeight());
        this.mass.setText(people.getMass());
        this.hair_color.setText(people.getHairColor());
        this.skin_color.setText(people.getSkinColor());
        this.eye_color.setText(people.getEyeColor());
        this.birth_year.setText(people.getBirthYear());
        this.gender.setText(people.getGender());
        this.homeworld.setText(people.getHomeworld());

        this.ivImage.setVisibility(View.INVISIBLE);
    }
}

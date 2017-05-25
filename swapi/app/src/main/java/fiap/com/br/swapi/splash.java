package fiap.com.br.swapi;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView ivImage= (ImageView) findViewById(R.id.gif);
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(ivImage);
        Glide.with(this)
                .load(R.drawable.splash)
                .into(imageViewTarget);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                chamarMain();
            }
        },4000);
    }

    private void chamarMain(){
        Intent toMain = new Intent(this, MainActivity.class);
        startActivity(toMain);
        finish();
    }
}

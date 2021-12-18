import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import dev.yawa.weather.R;

public class IntroductoryActivity extends AppCompatActivity {

    ImageView couple;
    TextView yawa, fullform;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        couple = findViewById(R.id.imageView);
        yawa = findViewById(R.id.textView);
        fullform = findViewById(R.id.textView2);
        lottieAnimationView = findViewById(R.id.lottie);

        couple.animate().translationX(-1500).setDuration(900).setStartDelay(4000);
        yawa.animate().translationX(1400).setDuration(900).setStartDelay(4000);
        fullform.animate().translationX(-1500).setDuration(900).setStartDelay(4000);
        lottieAnimationView.animate().translationY(-1500).setDuration(900).setStartDelay(4000);




    }
}

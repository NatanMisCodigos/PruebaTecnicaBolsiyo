package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;

import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.R;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.interfaces.WeatherInterfaces;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.ImageApi;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.Weather;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.adapters.WeatherAdapter;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.presenter.WeatherPresenter;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.rest.Endpoints;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.rest.Utils;

public class WeatherActivityView extends AppCompatActivity implements WeatherInterfaces.View {

    private Context context;
    private TextView title;
    private TextView locationType;
    private TextView temperature;
    private ImageView back;
    private LottieAnimationView statusLocation;
    private List<Weather> weather;
    private WeatherAdapter weatherAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ImageApi imageApi = (ImageApi) getIntent().getSerializableExtra("imageApi");
        initItems(imageApi);
    }

    @Override
    public void onBackPressed() {}

    @Override
    protected void onStart() {
        super.onStart();
        Utils.hideNotificationBar(this);
    }

    @Override
    public void showUserPosts(List<Weather> weather) {
        this.weather = weather;

        String temp = "" + weather.get(0).getThe_temp();
        temperature.setText( temp.substring( 0, 2 ) + "º" );

        Glide.with(context)
                .load(Endpoints.GET_IMAGE +
                        weather.get(0).getWeather_state_abbr() + ".png")
                .into(statusLocation);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        weatherAdapter = new WeatherAdapter(this.weather, context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(weatherAdapter);

        //Utils.dismissDialog();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show();
        //Utils.dismissDialog();
    }

    private void initItems(ImageApi imageApi) {
        context = getApplicationContext();

        title = findViewById(R.id.name);
        locationType = findViewById(R.id.typeLocation);
        temperature = findViewById(R.id.temperature_today);
        recyclerView = findViewById(R.id.recyclerViewWeather);
        statusLocation = findViewById(R.id.lottie_view);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        WeatherInterfaces.Presenter presenter = new WeatherPresenter(this, context);
        Calendar fecha = Calendar.getInstance();
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
    }

}

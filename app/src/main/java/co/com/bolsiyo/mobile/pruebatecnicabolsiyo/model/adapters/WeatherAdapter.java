package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pruebatecnicabold.R;
import com.example.pruebatecnicabold.model.Weather;
import com.example.pruebatecnicabold.rest.Endpoints;
import com.example.pruebatecnicabold.rest.Utils;

import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<Weather> weathers;
    private Context context;

    public WeatherAdapter(List<Weather> weathers, Context context){
        this.weathers = weathers;
        this.context = context;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_list_item, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        holder.bindData(weathers.get(position + 1));
    }

    @Override
    public int getItemCount() {
        return weathers.size() - 1;
    }

    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        TextView day, temperature, description;
        ImageView imageWeather;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
            temperature = itemView.findViewById(R.id.temperature);
            imageWeather = itemView.findViewById(R.id.imageWeather);
            description = itemView.findViewById(R.id.description);
        }

        void bindData(Weather weather){
            Glide.with(context).load(Endpoints.GET_IMAGE +
                    weather.getWeather_state_abbr() + ".png").into(imageWeather);

            int year = Integer.parseInt( weather.getApplicable_date().substring( 0, 3 ) );
            int month = Integer.parseInt( weather.getApplicable_date().substring( 5, 6 ) );
            int dayMonth = Integer.parseInt( weather.getApplicable_date().substring( 8 ) );
            day.setText(Utils.diaSemana( dayMonth, month, year ) );

            String temp = "" + weather.getThe_temp();
            temperature.setText( temp.substring( 0, 2 ) + "º" );

            description.setText( weather.getWeather_state_name() );
        }

    }
}

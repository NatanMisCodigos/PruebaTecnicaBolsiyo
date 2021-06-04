package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import java.util.ArrayList;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.R;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.interfaces.LocationInterfaces;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.Location;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.adapters.LocationAdapter;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.presenter.LocationPresenter;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.rest.Utils;

public class LocationActivityView extends Activity implements LocationInterfaces.View {

    private Context context = this;
    private EditText editTextSearch;
    private RecyclerView recyclerViewSearchResults;
    private LocationInterfaces.Presenter presenter = new LocationPresenter(this, context);
    private LocationAdapter locationAdapter;
    private ArrayList<Location> usersList;
    private ConstraintLayout constraintSplash;
    private ConstraintLayout constraintLocation;
    private LottieAnimationView imageEmpty;
    private LottieAnimationView error404;
    private TextView messageEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initItems();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Utils.hideNotificationBar(this);
    }

    @Override
    public void showUsers(ArrayList<Location> usersList) {
        this.usersList = usersList;

        error404.setVisibility(View.GONE);
        imageEmpty.setVisibility(View.GONE);
        messageEmpty.setVisibility(View.GONE);
        recyclerViewSearchResults.setVisibility(View.VISIBLE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        locationAdapter = new LocationAdapter(this.usersList, context);
        recyclerViewSearchResults.setLayoutManager(linearLayoutManager);
        recyclerViewSearchResults.setAdapter(locationAdapter);
        Utils.dismissDialog();
    }

    @Override
    public void showMessage(String message) {
        System.out.println("" + message);
        error404.setVisibility(View.VISIBLE);

        imageEmpty.setVisibility(View.GONE);
        messageEmpty.setVisibility(View.GONE);
        recyclerViewSearchResults.setVisibility(View.GONE);
    }

    @Override
    public void emptyList(String message) {
        messageEmpty.setText(message);

        error404.setVisibility(View.GONE);
        imageEmpty.setVisibility(View.VISIBLE);
        messageEmpty.setVisibility(View.VISIBLE);
        recyclerViewSearchResults.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void initItems() {

        constraintSplash = findViewById(R.id.constrain_splash);
        constraintLocation = findViewById(R.id.constrain_location);

        imageEmpty = findViewById(R.id.image_empty);
        error404 = findViewById(R.id.error);
        messageEmpty = findViewById(R.id.message_empty);

        editTextSearch = findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                presenter.getUsersFromApi(editTextSearch.getText().toString().trim());
                //openDialog();
            }
        });

        recyclerViewSearchResults = findViewById(R.id.recyclerViewSearchResults);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showConstrainLocation();
            }
        }, 4000);
    }

    void showConstrainLocation(){
        constraintSplash.setVisibility(View.GONE);
        constraintLocation.setVisibility(View.VISIBLE);
    }

    public void openDialog() {
        Utils.showDialog(context);
    }

}
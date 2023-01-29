package kis.kis.kinopoisk20;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kis.kis.kinopoisk20.pojo.Movie;
import kis.kis.kinopoisk20.viewModels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    // my token kinopoisk:  7D2G2GJ-5PWMMVV-PXJ80HC-8DMAAG7
    private final String TAG = "mainActivity";
    private MainViewModel mainViewModel;

    private MainRecycleViewAdapter recycleViewAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycle();
        initViewModelLoad();

    }

    private void initViewModelLoad() {
        // init view model
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getListMovies().observe(this, movies -> {
            Log.d(TAG, movies.toString());
            recycleViewAdapter.setMovies(movies);
        });
        mainViewModel.loadMovies();
        // call back if recycle view end
        recycleViewAdapter.setOnReachEndListener(() -> mainViewModel.loadMovies());
    }

    private void initRecycle() {
        // create recycle view
        recyclerView = findViewById(R.id.recycleMovie);
        recycleViewAdapter = new MainRecycleViewAdapter();
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
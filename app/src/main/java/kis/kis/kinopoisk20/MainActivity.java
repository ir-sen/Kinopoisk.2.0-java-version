package kis.kis.kinopoisk20;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import kis.kis.kinopoisk20.pojo.Movie;
import kis.kis.kinopoisk20.viewModels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    // my token kinopoisk:  7D2G2GJ-5PWMMVV-PXJ80HC-8DMAAG7
    private final String TAG = "mainActivity";
    private MainViewModel mainViewModel;

    private MainRecycleViewAdapter recycleViewAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initRecycle();
        initViewModelLoad();

    }

    private void initViewModelLoad() {
        progressBar = findViewById(R.id.progress_bar);
        // init view model
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getListMovies().observe(this, movies -> {
            Log.d(TAG, movies.toString());
            recycleViewAdapter.setMovies(movies);
        });
        // call back if recycle view end
        recycleViewAdapter.setOnReachEndListener(() -> mainViewModel.loadMovies());
        // init progress bar
        mainViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        recycleViewAdapter.setOnItemClickListener(new MainRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(Movie movie) {
                Intent detailIntent = MovieDetailActivity.newIntent(MainActivity.this, movie);
                startActivity(detailIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemMenuOne) {
            Intent intent = Favourites.newIntent(this);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecycle() {
        // create recycle view
        recyclerView = findViewById(R.id.recycleMovie);
        recycleViewAdapter = new MainRecycleViewAdapter();
        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
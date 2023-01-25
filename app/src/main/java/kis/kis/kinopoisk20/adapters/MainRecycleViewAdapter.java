package kis.kis.kinopoisk20.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kis.kis.kinopoisk20.R;
import kis.kis.kinopoisk20.pojo.Movie;

public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder>{

    private List<Movie> movies = new ArrayList<>();

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Glide.with(holder.imageInView)
                .load(movie.getPoster().getUrl())
                .into(holder.imageInView);
        holder.textInView.setText(movie.getRating().getRatingKp());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textInView;
        private ImageView imageInView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textInView = itemView.findViewById(R.id.rating_textView);
            imageInView = itemView.findViewById(R.id.image_view);

        }
    }
}

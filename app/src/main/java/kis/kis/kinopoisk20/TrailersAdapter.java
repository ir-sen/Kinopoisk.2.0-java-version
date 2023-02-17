package kis.kis.kinopoisk20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kis.kis.kinopoisk20.pojo.Trailer;

public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.TrailersViewHolder>{

    private List<Trailer> listTrailers = new ArrayList<>();

    OnTrailerItemClickListener onTrailerItemClickListener;

    public void setOnTrailerItemClickListener(OnTrailerItemClickListener onTrailerItemClickListener) {
        this.onTrailerItemClickListener = onTrailerItemClickListener;
    }

    public void setListTrailers(List<Trailer> listTrailers) {
        this.listTrailers = listTrailers;
        notifyDataSetChanged();
    }
    // return layout holder
    @NonNull
    @Override
    public TrailersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.trailear_item,
                parent,
                false
        );
        return new TrailersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailersViewHolder holder, int position) {
        Trailer trailer = listTrailers.get(position);
        holder.trailerName.setText(trailer.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTrailerItemClickListener != null) {
                    onTrailerItemClickListener.onItemClick(trailer);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTrailers.size();
    }

    // first stem create adapter and require text view what we use to be
    static class TrailersViewHolder extends RecyclerView.ViewHolder {

        private final TextView trailerName;

        public TrailersViewHolder(@NonNull View itemView) {
            super(itemView);
            trailerName = itemView.findViewById(R.id.trailerNameTv);

        }
    }

    interface OnTrailerItemClickListener {
        void onItemClick(Trailer url);
    }

}

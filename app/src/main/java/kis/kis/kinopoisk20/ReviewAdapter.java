package kis.kis.kinopoisk20;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kis.kis.kinopoisk20.pojo.ReviewItem;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {

    private static final String TYPE_POSITIVE = "Позитивный";
    private static final String TYPE_NEGATIVE = "Негативный";
    private static final String TYPE_NEUTRAL = "Нейтральный";
    private List<ReviewItem> listReview = new ArrayList<>();


    public void setListReview(List<ReviewItem> listReview) {
        this.listReview = listReview;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );
        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        ReviewItem reviewItem = listReview.get(position);
        holder.author.setText(reviewItem.getAuthor());
        holder.reviewText.setText(reviewItem.getReview());
        String type = reviewItem.getType();
        int backColor = android.R.color.holo_red_light;
        switch (type) {
            case TYPE_POSITIVE:
                backColor = android.R.color.holo_green_light;
                break;
            case TYPE_NEUTRAL:
                backColor = android.R.color.holo_orange_light;
                break;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(), backColor);
        holder.backLayout.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return listReview.size();
    }

    static class ReviewHolder extends RecyclerView.ViewHolder {

        LinearLayout backLayout;
        TextView author;
        TextView reviewText;

        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            backLayout = itemView.findViewById(R.id.background_Ll);
            author = itemView.findViewById(R.id.authorTv);
            reviewText = itemView.findViewById(R.id.reviewTextTv);
        }
    }
}

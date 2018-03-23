package anime.hanad.com.anime.AnimeList.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import anime.hanad.com.anime.MainActivity;
import anime.hanad.com.anime.R;
import anime.hanad.com.anime.network.model.Result;
import io.realm.Realm;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.MyViewHolder> {

    private Context applicationContext;
    private int row;
    private List<Result> results;
    // private Consumer<ClassicMusic> consumer;
    Realm realm;


    public AnimeAdapter(Context applicationContext, List<Result> results, int row) {
        this.applicationContext = applicationContext;
        this.row = row;
        this.results = results;
        //this.consumer = consumer;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        initRealm();
        // realm = Realm.getDefaultInstance();
        //  RealmResults<>


        return new AnimeAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row, parent, false));
    }

    public void initRealm() {
        realm = Realm.getDefaultInstance();
        //realmHelper = new RealmHelper((realm));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.mArtistName.setText(results.get(position).getTitle());
        holder.mDis.setText(results.get(position).getSynonyms());
        holder.mType.setText(results.get(position).getType());
        //holder.mTitle.setText(results.get(position).getTitle().toString());
        holder.mSynopsis.setText(results.get(position).getSynopsis());
        //holder.mRelatedAnime.setText((CharSequence) results.get(position).getRelatedAnime());
        holder.mRating.setText(results.get(position).getRating());
        holder.mPremiered.setText(results.get(position).getPremiered());
        holder.mId.setText(results.get(position).getId());
        holder.mGenres.setText(results.get(position).getGenres());
        holder.mDuration.setText(results.get(position).getDuration());
        holder.mDate.setText(results.get(position).getDate());
        holder.mAired.setText(results.get(position).getAired());

        Context context = holder.mArtwork.getContext();
        Picasso.with(applicationContext)
                .load(results.get(position).getImage())
                .resize(400, 400)
                .centerCrop()
                .into(holder.mArtwork);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mArtistName;
        private ImageView mArtwork;
        private TextView mDis;
        private TextView mType;
        private TextView mTitle;
        private TextView mSynopsis;
        private TextView mRelatedAnime;
        private TextView mRating;
        private TextView mPremiered;
        private TextView mId;
        private TextView mGenres;
        private TextView mDuration;
        private TextView mDate;
        private TextView mAired;


        public MyViewHolder(View itemView) {
            super(itemView);
            mArtistName = itemView.findViewById(R.id.name);
            mArtwork = itemView.findViewById(R.id.AnimeImage);
            mDis = itemView.findViewById(R.id.mDis);
            mType = itemView.findViewById(R.id.mType);
            mTitle = itemView.findViewById(R.id.mTitle);
            mSynopsis = itemView.findViewById(R.id.mSynopsis);
            //mRelatedAnime = itemView.findViewById(R.id.mRelatedAnime);
            mRating = itemView.findViewById(R.id.mRating);
            mPremiered = itemView.findViewById(R.id.mPremiered);
            mId = itemView.findViewById(R.id.mId);
            mGenres = itemView.findViewById(R.id.mGenres);
            mDuration = itemView.findViewById(R.id.mDuration);
            mDate = itemView.findViewById(R.id.mDate);
            mAired = itemView.findViewById(R.id.mAired);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(applicationContext, "Anime List click", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(applicationContext, results.get(getPosition()).getPreviewUrl(), Toast.LENGTH_SHORT).show();

                    int pos = getAdapterPosition();
                    String id = results.get(pos).getId();

                    if (pos != RecyclerView.NO_POSITION) {
                        Result clickDataItem = results.get(pos);

                        MainActivity.diplayEpisode(id);
                    }
                }
            });
        }
    }
}
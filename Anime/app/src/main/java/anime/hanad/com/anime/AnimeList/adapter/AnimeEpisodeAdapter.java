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

import anime.hanad.com.anime.R;
import anime.hanad.com.anime.network.model.Result;
import anime.hanad.com.anime.network.modelDetail.Data;
import anime.hanad.com.anime.network.modelDetail.Video;
import io.realm.Realm;

public class AnimeEpisodeAdapter extends RecyclerView.Adapter<AnimeEpisodeAdapter.MyViewHolder> {

    private Context applicationContext;
    private int row;
    private List<Video> results;
    private Data data;

   // private Consumer<ClassicMusic> consumer;
    Realm realm;


    public AnimeEpisodeAdapter(Context applicationContext, List<Video> results,Data data, int row) {
        this.applicationContext = applicationContext;
        this.row = row;
        this.results = results;
        this.data = data;
        //this.consumer = consumer;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        initRealm();
       // realm = Realm.getDefaultInstance();
      //  RealmResults<>


        return new AnimeEpisodeAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row, parent, false));
    }

    public void initRealm() {
        realm = Realm.getDefaultInstance();
        //realmHelper = new RealmHelper((realm));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.mArtistName.setText(results.get(position).getDate());// .getTitle());

//        Context context = holder.mArtwork.getContext();
//        Picasso.with(applicationContext)
//                .load(results.get(position)getImage())
//                .resize(400, 400)
//                .centerCrop()
//                .into(holder.mArtwork);
    }



    @Override
    public int getItemCount() {
        return results.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mArtistName;
        private ImageView mArtwork;


        public MyViewHolder(View itemView) {
            super(itemView);
            mArtistName = itemView.findViewById(R.id.name);
            mArtwork = itemView.findViewById(R.id.AnimeImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(applicationContext, "music click", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(applicationContext, results.get(getPosition()).getPreviewUrl(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
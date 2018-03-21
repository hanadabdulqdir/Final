package anime.hanad.com.anime.AnimeList.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import anime.hanad.com.anime.R;
import anime.hanad.com.anime.realm_database.model.AnimeModel;

public class AnimeRealmAdapter extends RecyclerView.Adapter<AnimeRealmAdapter.MyViewHolder>{

    private FragmentActivity activity;
    private ArrayList<AnimeModel> realmDb;
    private int row;


    public AnimeRealmAdapter(FragmentActivity activity, ArrayList<AnimeModel> realmDb, int row) {
        this.activity = activity;
        this.realmDb = realmDb;
        this.row = row;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AnimeRealmAdapter.MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(row, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.mArtistName.setText(realmDb.get(position).getArtistName());
        holder.mCollectionName.setText(realmDb.get(position).getCollectionName());
        holder.mTrackPrice.setText(realmDb.get(position).getArtistName());

    }

    @Override
    public int getItemCount() {
        return realmDb.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView mCollectionName, mArtistName, mTrackPrice;
        private ImageView mArtwork;


        public MyViewHolder(View itemView) {
            super(itemView);
            mArtistName = itemView.findViewById(R.id.name);
            mArtwork = itemView.findViewById(R.id.AnimeImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    String id = realmDb.get(pos).getArtistName();
                    String name = realmDb.get(pos).getArtistName();
                    String collection = realmDb.get(pos).getCollectionName();
                    String artWork = realmDb.get(pos).getArtworkUrl60();
                    //GET IN BUNDLE ETC

                    //check if item still exits
                    if(pos != RecyclerView.NO_POSITION)
                    {
                      //  Result clickedDataItem = realmDb.get(pos);
                        //code to play music
                       // MainActivity.playMusic(id, name, collection, artWork);
                    }
                }
            });
        }
    }

}


package anime.hanad.com.anime.AnimeList.controller;


import java.util.ArrayList;

import anime.hanad.com.anime.realm_database.model.AnimeModel;
import io.realm.Realm;
import io.realm.RealmResults;


public class RealmHelper {

    Realm realm;


    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void saveClassicMusic(final AnimeModel classicAnimeModel)

            //Async
    {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(classicAnimeModel);
                }
            });
    }

    public ArrayList<AnimeModel> getAnimeModle()
    {
        ArrayList<AnimeModel> classicAnimeModelArrayList = new ArrayList<>();
        RealmResults<AnimeModel> realmResults = realm.where(AnimeModel.class).findAll();

        for(AnimeModel classicAnimeModel : realmResults){
            classicAnimeModelArrayList.add(classicAnimeModel);
        }
        return classicAnimeModelArrayList;
    }

}

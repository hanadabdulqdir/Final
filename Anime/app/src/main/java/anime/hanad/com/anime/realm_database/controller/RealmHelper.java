package anime.hanad.com.anime.realm_database.controller;


import java.util.ArrayList;

import anime.hanad.com.anime.realm_database.model.AnimeModel;
import io.realm.Realm;
import io.realm.RealmResults;


public class RealmHelper {

    private Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    public void storeData(final AnimeModel animeModel){
        if (animeModel != null){
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealm(animeModel);
                }
            });
        }
    }

    public void deleteRealmData(){
        RealmResults<AnimeModel> realmResults = realm.where(AnimeModel.class).findAll();
        realmResults.deleteAllFromRealm();
    }

    public ArrayList<AnimeModel> getAnimeModle(){
        ArrayList<AnimeModel> animeModels = new ArrayList<>();

        RealmResults<AnimeModel> realmResults = realm.where(AnimeModel.class).findAll();
        for (AnimeModel onlineMusic: realmResults) {
            animeModels.add(onlineMusic);
        }

        return animeModels;
    }


}

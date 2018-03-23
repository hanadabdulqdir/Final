package anime.hanad.com.anime.network;


import java.util.List;

import anime.hanad.com.anime.AnimeList.adapter.AnimeEpisodeAdapter;
import anime.hanad.com.anime.network.model.AnimeList;
import anime.hanad.com.anime.network.modelDetail.Data;
import io.reactivex.Observable;

public class AppDataManager implements DataManager{

    private IApiHelper iApiHelper;


    public AppDataManager() {
        iApiHelper = new AppApiHelper();
    }

    @Override
    public Observable<AnimeList> getAnimeList() {
        return iApiHelper.getAnimeList();
    }

    @Override
    public Observable<Data> getData(String id) {
        return iApiHelper.getData(id);
    }
}

package anime.hanad.com.anime.network;


import anime.hanad.com.anime.AnimeList.adapter.AnimeEpisodeAdapter;
import anime.hanad.com.anime.network.model.AnimeList;
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
    public Observable<AnimeEpisodeAdapter> getAnimeEpisodeAdapter() {
        return iApiHelper.getAnimeEpisodeAdapter();
    }

}

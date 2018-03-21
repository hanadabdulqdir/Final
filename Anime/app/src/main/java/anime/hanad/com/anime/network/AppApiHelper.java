package anime.hanad.com.anime.network;


import anime.hanad.com.anime.AnimeList.adapter.AnimeEpisodeAdapter;
import anime.hanad.com.anime.network.model.AnimeList;
import anime.hanad.com.anime.network.service.IRequestInterface;
import anime.hanad.com.anime.network.service.ServiceConnection;
import io.reactivex.Observable;

public class AppApiHelper implements IApiHelper{

    private IRequestInterface iRequestInterface;


    public AppApiHelper() {
        iRequestInterface = ServiceConnection.getConnection();
    }

    @Override
    public Observable<AnimeList> getAnimeList() {
        return iRequestInterface.getAnimeList();
    }

    @Override
    public Observable<AnimeEpisodeAdapter> getAnimeEpisodeAdapter() {
        return iRequestInterface.getAnimeEpisodeAdapter();
    }


}

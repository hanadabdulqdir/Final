package anime.hanad.com.anime.network;


import anime.hanad.com.anime.AnimeList.adapter.AnimeEpisodeAdapter;
import anime.hanad.com.anime.network.model.AnimeList;
import io.reactivex.Observable;

public interface IApiHelper {

    Observable<AnimeList> getAnimeList();

    Observable<AnimeEpisodeAdapter> getAnimeEpisodeAdapter();

}

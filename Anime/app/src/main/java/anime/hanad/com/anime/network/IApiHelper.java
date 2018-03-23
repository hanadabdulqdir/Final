package anime.hanad.com.anime.network;


import java.util.List;

import anime.hanad.com.anime.AnimeList.adapter.AnimeEpisodeAdapter;
import anime.hanad.com.anime.network.model.AnimeList;
import anime.hanad.com.anime.network.modelDetail.Data;
import io.reactivex.Observable;

public interface IApiHelper {

    Observable<AnimeList> getAnimeList();

    Observable<Data> getData(String id);

}

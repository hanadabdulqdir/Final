package anime.hanad.com.anime.network.service;


import anime.hanad.com.anime.AnimeList.adapter.AnimeEpisodeAdapter;
import anime.hanad.com.anime.network.model.AnimeList;
import anime.hanad.com.anime.network.modelDetail.Data;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IRequestInterface {

    @GET(ApiList.Anime_API)
    Observable<AnimeList> getAnimeList();

    @GET(ApiList.Episode_ApI)
    Observable<Data> getData(@Path("id") int id);

    //Observable<AnimeDetailAdapter> getAnimeEpisodeAdapter(@Path("id") int id, @Query("api_key") String apiKey);

    // @GET(ApiList.Genre_ApI)
    // Observable<AnimeGenreAdapter> getAnimeGenreAdapter();

    //@GET(ApiList.Slug_ApI)
    //Observable<AnimeSlugAdapter> getAnimeSlugAdapter();

    //@GET(ApiList.Search_ApI)
     //Observable<AnimeSearchAdapter> getAnimeSearchAdapter();
}

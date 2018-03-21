package anime.hanad.com.anime.AnimeList.mvp;


import anime.hanad.com.anime.network.model.AnimeList;
import anime.hanad.com.anime.ui.base.MvpView;

public interface IAnimeMvpView extends MvpView {

    void onFetchDataProgress();
    void onFetchDataSuccess(AnimeList classicMusic);
    void onFetchDataError(String error);
}

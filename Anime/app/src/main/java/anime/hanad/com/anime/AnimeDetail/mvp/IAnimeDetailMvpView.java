package anime.hanad.com.anime.AnimeDetail.mvp;


import java.util.List;

import anime.hanad.com.anime.network.model.AnimeList;
import anime.hanad.com.anime.network.modelDetail.Data;
import anime.hanad.com.anime.ui.base.MvpView;

public interface IAnimeDetailMvpView extends MvpView {

    void onFetchDataProgress();
    void onFetchDataSuccess(Data data);
    void onFetchDataError(String error);
}

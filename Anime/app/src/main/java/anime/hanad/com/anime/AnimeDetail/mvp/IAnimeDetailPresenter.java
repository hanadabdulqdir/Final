package anime.hanad.com.anime.AnimeDetail.mvp;


import anime.hanad.com.anime.ui.base.MvpPresenter;

public interface IAnimeDetailPresenter<V extends IAnimeDetailMvpView> extends MvpPresenter<V> {

    void loadEpisodeList(String id);
}

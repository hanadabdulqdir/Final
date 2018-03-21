package anime.hanad.com.anime.AnimeList.mvp;


import anime.hanad.com.anime.ui.base.MvpPresenter;

public interface IAnimePresenter<V extends IAnimeMvpView> extends MvpPresenter<V> {

    void loadMusicList();
}

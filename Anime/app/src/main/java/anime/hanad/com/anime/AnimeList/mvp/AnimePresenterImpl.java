package anime.hanad.com.anime.AnimeList.mvp;


import anime.hanad.com.anime.network.DataManager;
import anime.hanad.com.anime.network.model.AnimeList;
import anime.hanad.com.anime.ui.base.BasePresenter;
import anime.hanad.com.anime.ui.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;


public class AnimePresenterImpl<V extends IAnimeMvpView>
extends BasePresenter<V>
implements IAnimePresenter<V> {


    public AnimePresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadMusicList() {

        getDataManager().getAnimeList()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<AnimeList>() {
                               @Override
                               public void accept(AnimeList animeList) throws Exception {

                                   getMvpView().onFetchDataSuccess(animeList);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );

    }
}

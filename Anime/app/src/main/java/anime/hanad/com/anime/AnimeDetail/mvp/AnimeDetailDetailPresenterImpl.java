package anime.hanad.com.anime.AnimeDetail.mvp;


import java.util.List;

import anime.hanad.com.anime.network.DataManager;
import anime.hanad.com.anime.network.model.AnimeList;
import anime.hanad.com.anime.network.modelDetail.Data;
import anime.hanad.com.anime.ui.base.BasePresenter;
import anime.hanad.com.anime.ui.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;


public class AnimeDetailDetailPresenterImpl<V extends IAnimeDetailMvpView>
extends BasePresenter<V>
implements IAnimeDetailPresenter<V> {


    public AnimeDetailDetailPresenterImpl(DataManager dataManager, SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadEpisodeList(String id) {

        getDataManager().getData(id)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Data>() {
                               @Override
                               public void accept(Data data) throws Exception {

                                   getMvpView().onFetchDataSuccess(data);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           }
                );

    }
}

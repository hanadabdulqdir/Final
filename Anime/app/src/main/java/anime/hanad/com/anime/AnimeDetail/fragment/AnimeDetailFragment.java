package anime.hanad.com.anime.AnimeDetail.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork;

import java.util.List;

import anime.hanad.com.anime.AnimeDetail.fragment.AnimeDetailFragment;
import anime.hanad.com.anime.AnimeDetail.mvp.AnimeDetailDetailPresenterImpl;
import anime.hanad.com.anime.AnimeDetail.mvp.IAnimeDetailMvpView;
import anime.hanad.com.anime.AnimeList.adapter.AnimeEpisodeAdapter;
import anime.hanad.com.anime.AnimeList.adapter.AnimeRealmAdapter;
import anime.hanad.com.anime.MainActivity;
import anime.hanad.com.anime.R;
import anime.hanad.com.anime.network.AppDataManager;
import anime.hanad.com.anime.network.model.Result;
import anime.hanad.com.anime.network.modelDetail.Data;
import anime.hanad.com.anime.ui.base.BaseFragment;
import anime.hanad.com.anime.ui.utils.rx.AppSchedulerProvider;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimeDetailFragment extends BaseFragment implements IAnimeDetailMvpView {

    private String id;
    SharedPreferences sharedPref;
    private AnimeDetailDetailPresenterImpl<AnimeDetailFragment> classicMusicClassicMusicPresenter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    public AnimeDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // iRequestInterface = ServiceConnection.getConnection();
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout = view.findViewById(R.id.swiperefresh);


        //initializing presenter class objects
        classicMusicClassicMusicPresenter = new AnimeDetailDetailPresenterImpl<>
                (new AppDataManager(), new AppSchedulerProvider(),
                        new CompositeDisposable());
        classicMusicClassicMusicPresenter.onAttach(this);

        callService();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                callService();
            }
        });
    }

    public void callService()
    {
        ReactiveNetwork.observeInternetConnectivity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isConnectedToInternet) throws Exception {
                        if (isConnectedToInternet)
                        {
                            id = sharedPref.getString("id",id);
                            //get data
                            //displayClassicMusic();
                            classicMusicClassicMusicPresenter.loadEpisodeList(id);
                            MainActivity.deleteRealmDatabase();
                            //DatabaseResults(classicMusic.getResults());
                        }
                        else
                        {
                            AlertNetwork();

                            recyclerView.setAdapter(new AnimeRealmAdapter(getActivity(), MainActivity.getRealmDatabase(), R.layout.row));

                            //if there is no internet connection then it will get from the realm backup
                            //get data from realm backup
                            //displayClassicMusicBackup();

                        }
                    }
                });
    }

    public void AlertNetwork()
    {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
        a_builder.setMessage("There is no internet connecting ")
                .setCancelable(false)
                .setPositiveButton("Close the App", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                    }
                }).setNegativeButton("Continue using the App", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                //displayClassicMusic();
            }
        });

        AlertDialog alert = a_builder.create();
        alert.setTitle("Connection status");
        alert.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void DatabaseResults(List<Result> classicMusicResult) {
        for (Result classicMusic : classicMusicResult) {
            MainActivity.saveRealm(
                    classicMusic.getAired().toString(),
                    classicMusic.getImage(),
                    classicMusic.getGenres(),
                    classicMusic.getTitle()
            );
        }
    }


    @Override
    public void onFetchDataProgress() {

        showLoading();
    }

    @Override
    public void onFetchDataSuccess(Data data) {
       recyclerView.setAdapter(new AnimeEpisodeAdapter(getActivity(), data.getVideos(), data,R.layout.row));//AnimeEpisodeAdapter
        Toast.makeText(getActivity(), "MOVIE" + data.getVideos().get(0).getDate(), Toast.LENGTH_LONG).show();

        refreshLayout.setRefreshing(false);
        hideLoading();
    }

    @Override
    public void onFetchDataError(String error) {

    }
}

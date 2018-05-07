package hu.bme.aut.android.spotifydemo.ui.artists;

import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.android.spotifydemo.di.Network;
import hu.bme.aut.android.spotifydemo.interactor.artists.ArtistsInteractor;
import hu.bme.aut.android.spotifydemo.model.Item;
import hu.bme.aut.android.spotifydemo.ui.RxPresenter;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

import static hu.bme.aut.android.spotifydemo.SpotifyDemoApplication.injector;

public class ArtistsPresenter extends RxPresenter<ArtistsScreen> {

    @Inject
    @Network
    Scheduler scheduler;

    @Inject
    ArtistsInteractor artistsInteractor;

    @Inject
    public ArtistsPresenter() {
        injector.inject(this);
    }

    public void refreshArtists(final String artistQuery) {
        subscribe(single(() -> artistsInteractor.getArtists(artistQuery))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(scheduler)
                .subscribe(this::onArtistsReceived, this::onError));
    }


    private void onArtistsReceived(List<Item> artists) {
        if (screen != null) {
            screen.showArtists(artists);
        }
    }


    private void onError(Throwable throwable) {
        if (screen != null) {
            screen.showNetworkError(throwable.getMessage());
        }

    }

}

package hu.bme.aut.android.spotifydemo.ui.main;

import javax.inject.Inject;

import hu.bme.aut.android.spotifydemo.ui.RxPresenter;

public class MainPresenter extends RxPresenter<MainScreen> {

    @Inject
    public MainPresenter() {
    }

    @Override
    public void attach(MainScreen screen) {
        super.attach(screen);
    }

    @Override
    public void detach() {
        super.detach();
    }

    public void showArtistsSearchList(String artistSearchTerm) {
        screen.showArtists(artistSearchTerm);
    }
}

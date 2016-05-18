package hu.bme.aut.android.spotifydemo.ui.artists;

import java.util.List;

import hu.bme.aut.android.spotifydemo.model.Item;
import hu.bme.aut.android.spotifydemo.ui.Screen;

public interface ArtistsScreen extends Screen {
    void showArtists(List<Item> artists);

    void showNetworkError(String errorMsg);
}

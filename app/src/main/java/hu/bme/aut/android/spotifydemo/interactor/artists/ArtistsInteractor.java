package hu.bme.aut.android.spotifydemo.interactor.artists;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.android.spotifydemo.SpotifyDemoApplication;
import hu.bme.aut.android.spotifydemo.model.ArtistsResult;
import hu.bme.aut.android.spotifydemo.model.Item;
import hu.bme.aut.android.spotifydemo.network.ArtistsApi;
import retrofit2.Call;
import retrofit2.Response;

public class ArtistsInteractor {

    @Inject
    ArtistsApi artistsApi;

    public ArtistsInteractor() {
        SpotifyDemoApplication.injector.inject(this);
    }

    public List<Item> getArtists(String artistQuery) {
        Call<ArtistsResult> artistsQueryCall = artistsApi.getArtists(artistQuery, "artist", 0, 3);
        Response<ArtistsResult> response;
        try {
            response = artistsQueryCall.execute();
            if (response.code() != 200) {
                throw new RuntimeException("Result code is not 200");
            }
            return response.body().getArtists().getItems();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}

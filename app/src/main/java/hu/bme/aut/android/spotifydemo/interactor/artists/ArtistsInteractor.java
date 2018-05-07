package hu.bme.aut.android.spotifydemo.interactor.artists;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.android.spotifydemo.SpotifyDemoApplication;
import hu.bme.aut.android.spotifydemo.model.ArtistsResult;
import hu.bme.aut.android.spotifydemo.model.Item;
import hu.bme.aut.android.spotifydemo.model.Token;
import hu.bme.aut.android.spotifydemo.network.ArtistsApi;
import hu.bme.aut.android.spotifydemo.network.TokenApi;
import retrofit2.Call;
import retrofit2.Response;

public class ArtistsInteractor {

    @Inject
    ArtistsApi artistsApi;

    @Inject
    TokenApi tokenApi;

    public ArtistsInteractor() {
        SpotifyDemoApplication.injector.inject(this);
    }

    public List<Item> getArtists(String artistQuery) {

        Call<Token> tokenCall = tokenApi.getToken("client_credentials", "Basic MDM0MjY2ODU0OTI1NGZkOWFiMzdmMzNlZjRkNjRkYjA6ODM1MWFiMjVjYzVmNDBhMjg5OGI5N2U5ZjQyMmNkMDk=");

        try {
            Token token = tokenCall.execute().body();
            Call<ArtistsResult> artistsQueryCall = artistsApi.getArtists("Bearer " + token.getToken(), artistQuery, "artist", 0, 3);
            Response<ArtistsResult> response;
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

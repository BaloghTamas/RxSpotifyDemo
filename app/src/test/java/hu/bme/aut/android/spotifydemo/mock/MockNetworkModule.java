package hu.bme.aut.android.spotifydemo.mock;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.android.spotifydemo.network.ArtistsApi;
import hu.bme.aut.android.spotifydemo.network.TokenApi;

@Module
public class MockNetworkModule {


    @Provides
    @Singleton
    public ArtistsApi provideArtistsApi() {
        return new MockArtistApi();
    }


    @Provides
    @Singleton
    public TokenApi provideTokenApi() {
        return new MockTokenApi();
    }

}

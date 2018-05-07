package hu.bme.aut.android.spotifydemo.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public ArtistsApi provideArtistsApi() {

        return new Retrofit.Builder()
                .baseUrl(NetworkConfig.API_ENDPOINT_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ArtistsApi.class);
    }

    @Provides
    @Singleton
    public TokenApi provideTokenApi() {

        return new Retrofit.Builder()
                .baseUrl(NetworkConfig.TOKEN_ENDPOINT_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TokenApi.class);
    }
}

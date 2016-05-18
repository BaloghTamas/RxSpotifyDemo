package hu.bme.aut.android.spotifydemo;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.android.spotifydemo.di.Network;
import hu.bme.aut.android.spotifydemo.ui.artists.ArtistsPresenter;
import hu.bme.aut.android.spotifydemo.ui.main.MainPresenter;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

@Module
public class TestModule {

	private Context context;

	public TestModule(Context context) {
		this.context = context;
	}

	@Provides
	public Context provideContext() {
		return context;
	}

	@Provides
	@Singleton
	@Network
	public Scheduler provideNetworkScheduler() {
		return AndroidSchedulers.mainThread();
	}


}

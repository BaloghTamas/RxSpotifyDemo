package hu.bme.aut.android.spotifydemo;

import android.content.Context;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.android.spotifydemo.di.Network;
import hu.bme.aut.android.spotifydemo.ui.UIModule;
import rx.Scheduler;
import rx.schedulers.Schedulers;

@Module
public class AndroidTestModule {

    private final UIModule UIModule;

    public AndroidTestModule(Context context) {

        this.UIModule = new UIModule(context);
    }

    @Provides
    public Context provideContext() {
        return UIModule.provideContext();
    }

    @Provides
    @Singleton
    @Network
    public Scheduler provideNetworkScheduler() {
        return Schedulers.from(Executors.newFixedThreadPool(1));
    }

}

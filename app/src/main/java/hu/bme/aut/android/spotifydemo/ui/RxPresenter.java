package hu.bme.aut.android.spotifydemo.ui;


import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class RxPresenter<S extends Screen> extends Presenter<S> {
    protected CompositeDisposable compositeSubscription;

    protected static <T> Flowable<T> observable(Callable<T> callable) {
        return Flowable.defer(() -> {
            T result = callable.call();
            return Flowable.just(result);
        });
    }

    protected static <T> Single<T> single(Callable<T> callable) {
        return Single.defer(() -> {
            T result = callable.call();
            return Single.just(result);
        });
    }

    @Override
    public void attach(S screen) {
        super.attach(screen);
        if (compositeSubscription != null && !compositeSubscription.isDisposed()) {
            compositeSubscription.dispose();
        }
        compositeSubscription = new CompositeDisposable();
    }

    @Override
    public void detach() {
        if (compositeSubscription != null && !compositeSubscription.isDisposed()) {
            compositeSubscription.dispose();
        }
        super.detach();
    }

    protected void subscribe(Disposable disposable) {
        compositeSubscription.add(disposable);
    }
}

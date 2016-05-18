package hu.bme.aut.android.spotifydemo.ui;

import rx.Observable;
import rx.Single;
import rx.Subscription;
import rx.functions.Func0;
import rx.subscriptions.CompositeSubscription;

public class RxPresenter<S extends Screen> extends Presenter<S> {
    protected CompositeSubscription compositeSubscription;

    protected static <T> Observable<T> observable(Func0<T> func0) {
        return Observable.defer(() -> {
            T result = func0.call();
            return Observable.just(result);
        });
    }

    protected static <T> Single<T> single(Func0<T> func0) {
        return Single.defer(() -> {
            T result = func0.call();
            return Single.just(result);
        });
    }

    @Override
    public void attach(S screen) {
        super.attach(screen);
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detach() {
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
        super.detach();
    }

    protected void subscribe(Subscription subscription) {
        compositeSubscription.add(subscription);
    }
}

package id.web.runup.fice.mvp;

public abstract class AbstractPresenter implements IPresenter {
    protected String TAG = getClass().getSimpleName();
    private IView mView;

    public AbstractPresenter(){

    }

    @Override
    public void onStop() {
//        mCompositeSubscription.clear();
    }

    @Override
    public void onStart() {

    }
}

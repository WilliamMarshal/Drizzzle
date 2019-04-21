package com.example.android.drizzzle.mvp.impl;

import android.support.annotation.Nullable;

import com.example.android.drizzzle.mvp.BasePresenter;
import com.example.android.drizzzle.mvp.BaseView;


public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    private V view;

    public BasePresenterImpl() {
    }

    @Override
    public void onAttach(V view) {
        this.view = view;
    }

    @Override
    public void onDetach() {
        view = null;
    }

    @Override
    public void onSwipeRefresh() {
    }

    protected final V getView() {
        return view;
    }

    protected final boolean isViewAttached() {
        return getView() != null;
    }

    protected final void checkViewAttached() {
        if (!isViewAttached()) {
            throw new ViewNotAttachedException();
        }
    }

    public static class ViewNotAttachedException extends RuntimeException {
        public ViewNotAttachedException() {
            super("Please call Presenter.onAttach() before" +
                    " requesting data to the Presenter");
        }
    }
}

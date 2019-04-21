package com.example.android.drizzzle.mvp;

public interface BasePresenter<V extends BaseView> {

    void onAttach(V view);

    void onDetach();

    void onSwipeRefresh();
}

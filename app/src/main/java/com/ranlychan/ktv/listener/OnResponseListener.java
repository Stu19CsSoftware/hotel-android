package com.ranlychan.ktv.listener;

public interface OnResponseListener<T> {
    void onQueryComplete(T response);

    void onQueryError(Throwable throwable);
}

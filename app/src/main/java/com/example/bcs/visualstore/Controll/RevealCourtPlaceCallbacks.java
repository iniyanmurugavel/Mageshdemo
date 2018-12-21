package com.example.bcs.visualstore.Controll;

import android.support.annotation.NonNull;

public interface RevealCourtPlaceCallbacks {

    void onSuccess(@NonNull String value);

    void onError(@NonNull Throwable throwable);
}

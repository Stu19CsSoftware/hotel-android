package com.ranlychan.hotel.idling;

import androidx.test.espresso.IdlingResource;

public class EspressoIdlingResource {
    private static final String RESOURCE = "GLOBAL";

    private static HomepageGetRoomsIdlingResource mCountingIdlingResource =
            new HomepageGetRoomsIdlingResource(RESOURCE);

    public static void increment() {
        mCountingIdlingResource.increment();
    }

    public static void decrement() {
        mCountingIdlingResource.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }
}
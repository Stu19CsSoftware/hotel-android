package com.ranlychan.ktv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SafeAreaFrameLayout extends FrameLayout {
    public SafeAreaFrameLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public SafeAreaFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOnApplyWindowInsetsListener(new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                setPadding(0, insets.getSystemWindowInsetTop(), 0, 0);
                return insets;
            }
        });
    }
}

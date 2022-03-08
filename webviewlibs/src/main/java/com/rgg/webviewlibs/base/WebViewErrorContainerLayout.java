package com.rgg.webviewlibs.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rgg.webviewlibs.R;

public class WebViewErrorContainerLayout extends FrameLayout {

    ViewGroup contentLayout;
    View errorLayout;
    ImageView errorPageImage;
    TextView errorPageText;
    ProgressBar pgLoading;

    public WebViewErrorContainerLayout(@NonNull Context context) {
        this(context, null);
    }

    public WebViewErrorContainerLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebViewErrorContainerLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.web_view_error_container_layout, this);
        contentLayout = view.findViewById(R.id.content_layout);
        errorLayout = view.findViewById(R.id.error_page_layout);
        errorPageImage = view.findViewById(R.id.error_page_image);
        errorPageText = view.findViewById(R.id.error_page_text);
        pgLoading = view.findViewById(R.id.progress);
    }

    public void addContentLayout(View view) {
        contentLayout.removeAllViews();
        contentLayout.addView(view);
    }

    public void showContent() {
        contentLayout.setVisibility(VISIBLE);
        errorLayout.setVisibility(GONE);
//        dismissLoading();
    }

    public void showError(int errorImage, int errorMessage) {
        errorPageImage.setImageResource(errorImage);
        errorPageText.setText(errorMessage);
        errorLayout.setVisibility(View.VISIBLE);
        contentLayout.setVisibility(View.GONE);
//        dismissLoading();
    }

    public void showError(Drawable errorImage, CharSequence errorMessage) {
        errorPageImage.setImageDrawable(errorImage);
        errorPageText.setText(errorMessage);
        errorLayout.setVisibility(View.VISIBLE);
        contentLayout.setVisibility(View.GONE);
//        dismissLoading();
    }

    public void showLoadFailedError() {
        showError(R.drawable.ic_error404, R.string.error_404);
//        dismissLoading();
    }

    public void showNetworkError() {
        showError(R.drawable.ic_no_internet, R.string.error_network_string);
//        dismissLoading();
    }

    public void showErrorMessage(int errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
//        dismissLoading();
    }

    public void setRefreshOnClickListener(OnClickListener listener) {
        findViewById(R.id.error_page_refresh_button).setOnClickListener(listener);
    }

    public void showLoading() {
        pgLoading.setVisibility(View.VISIBLE);
    }

    public void dismissLoading() {
        pgLoading.setVisibility(View.GONE);
    }

    public View getContentView() {
        if (contentLayout.getChildCount() > 0) {
            return contentLayout.getChildAt(0);
        } else {
            return null;
        }
    }

    public void setProgress(int progress) {
        pgLoading.setProgress(progress);
        if (progress ==100){
            dismissLoading();
        }
    }
}

package com.zkuzan.mvvm.util.image

import android.graphics.drawable.Drawable
import android.widget.ImageView


interface ImageHelper {

    fun loadImage(view: ImageView?, path: Any?)

    fun loadImage(view: ImageView?, url: Any?, isFitCenter: Boolean)

    fun loadImageGrayScale(view: ImageView?, url: Any?)

    fun loadImageBlur(
        view: ImageView?,
        url: Any?,
        successListener: OnLoadSuccessListener? = null,
        failedListener: OnLoadFailedListener? = null,
        radius: Int = 20,
        sampling: Int = 2
    )

    fun loadThumbnail(view: ImageView?, path: Any?, resId: Int)

    fun loadAvatar(
        view: ImageView?,
        url: Any?,
        successListener: OnLoadSuccessListener? = null,
        failedListener: OnLoadFailedListener? = null
    )

    fun loadBanner(view: ImageView?, url: Any?)

    fun loadImageDetail(
        view: ImageView?,
        url: Any?,
        isFitCenter: Boolean,
        sizeMultiplier: Float,
        successListener: OnLoadSuccessListener? = null,
        failedListener: OnLoadFailedListener? = null
    )


    interface OnLoadFailedListener {
        fun onFailed(e: Exception)
    }

    interface OnLoadSuccessListener {
        fun onSuccess(drawable: Drawable)
    }
}

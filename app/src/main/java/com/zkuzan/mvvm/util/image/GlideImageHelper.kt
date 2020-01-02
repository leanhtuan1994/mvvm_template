package com.zkuzan.mvvm.util.image

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.zkuzan.mvvm.R
import com.zkuzan.mvvm.util.image.gt.BlurTransformation
import com.zkuzan.mvvm.util.image.gt.GrayscaleTransformation
import timber.log.Timber


class GlideImageHelper(private val mContext: Context) : ImageHelper {

    private val centerCrop: RequestOptions
    private val fitCenter: RequestOptions
    private val avatar: RequestOptions

    init {
        fitCenter = RequestOptions()
            .fitCenter()
            .placeholder(R.drawable.no_image)
            .error(R.drawable.no_image)
            .priority(Priority.HIGH)

        centerCrop = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.no_image)
            .error(R.drawable.no_image)
            .priority(Priority.HIGH)

        avatar = RequestOptions()
            .fitCenter()
            .placeholder(R.drawable.no_image)
            .error(R.drawable.no_image)
            .priority(Priority.HIGH)
    }

    override fun loadAvatar(
        view: ImageView?,
        url: Any?,
        successListener: ImageHelper.OnLoadSuccessListener?,
        failedListener: ImageHelper.OnLoadFailedListener?
    ) {
        if (view == null) return

        Glide.with(view)
            .load(url)
            .apply(avatar)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    failedListener?.onFailed(e!!)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    successListener?.onSuccess(resource)
                    return false
                }
            })
            .into(view)

    }

    override fun loadImageGrayScale(view: ImageView?, url: Any?) {
        if (view == null) return

        Glide.with(view)
            .load(url)
            .apply(RequestOptions().transform(GrayscaleTransformation()))
            .into(view)
    }

    @SuppressLint("CheckResult")
    override fun loadThumbnail(view: ImageView?, path: Any?, resId: Int) {
        if (resId != -1) {
            fitCenter.error(resId)
                .placeholder(resId)
        }
        loadImageDetail(view, path, true, 0.5f)
    }

    override fun loadImage(view: ImageView?, path: Any?) {
        loadImageDetail(view, path, true, 1.0f)
    }

    override fun loadImage(view: ImageView?, url: Any?, isFitCenter: Boolean) {
        loadImageDetail(view, url, isFitCenter, 1.0f)
    }

    override fun loadBanner(view: ImageView?, url: Any?) {
        loadImageDetail(view, url, false, 1.0f)
    }

    override fun loadImageDetail(
        view: ImageView?,
        url: Any?,
        isFitCenter: Boolean,
        sizeMultiplier: Float,
        successListener: ImageHelper.OnLoadSuccessListener?,
        failedListener: ImageHelper.OnLoadFailedListener?
    ) {
        if (view == null) return

        val context = view.context
        Glide.with(context)
            .load(url)
            .apply(if (isFitCenter) fitCenter else centerCrop)
            .thumbnail(sizeMultiplier)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    failedListener?.onFailed(e!!)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    fitCenter.placeholder(R.drawable.no_image)
                        .error(R.drawable.no_image)

                    successListener?.onSuccess(resource)
                    return false
                }
            })
            .into(view)
    }

    override fun loadImageBlur(
        view: ImageView?,
        url: Any?,
        successListener: ImageHelper.OnLoadSuccessListener?,
        failedListener: ImageHelper.OnLoadFailedListener?,
        radius: Int,
        sampling: Int
    ) {
        if (view == null) return
        Glide.with(view)
            .load(url)
            .apply(RequestOptions().transform(BlurTransformation(radius, sampling)))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Timber.e(e)
                    failedListener?.onFailed(e!!)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    successListener?.onSuccess(resource)
                    return false
                }
            })
            .into(view)
    }
}

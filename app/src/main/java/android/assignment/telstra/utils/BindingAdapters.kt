package android.assignment.telstra.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class BindingAdapters
{
    companion object
    {
        //set image from url to city info provider item
        @BindingAdapter("android:setimage")
        @JvmStatic
        fun setImageToCityInfoProvider(itemImage : ImageView,imageUrl : String)
        {
            //set profile image
            Glide.with(itemImage.context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(itemImage)
        }
    }
}
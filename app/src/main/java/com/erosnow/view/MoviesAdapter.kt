package com.erosnow.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.erosnow.R
import com.erosnow.services.RetrofitClientInstance
import com.erosnow.services.model.Movie
import com.erosnow.utils.Utils
import java.util.*

class MoviesAdapter(private val context: Context, private val movies: ArrayList<Movie>?, private val onItemClicked: OnItemClicked, private val mActualWidth: Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mActualHeight = -1

    val TYPE_MOVIE = 0
    val TYPE_LOAD = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(context)
        return if (viewType == TYPE_MOVIE) {
            MyViewHolder(inflater.inflate(R.layout.item_movie, parent, false))
        } else {
            LoadHolder(inflater.inflate(R.layout.row_load, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is MyViewHolder) {
            holder.bindData(movies!![position])
        }


    }

    override fun getItemCount(): Int {

        return movies?.size ?: 0

    }

    override fun getItemViewType(position: Int): Int {
        return if (movies!![position] == null) TYPE_LOAD else TYPE_MOVIE

    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var image: ImageView
        internal var favImage: ImageView
        internal var constraintLayout: ConstraintLayout

        init {
            image = itemView.findViewById<View>(R.id.image) as ImageView
            favImage = itemView.findViewById<View>(R.id.fav_icon_iv) as ImageView
            constraintLayout = itemView.findViewById<View>(R.id.constraintLayout) as ConstraintLayout
        }

        internal fun bindData(movie: Movie) {
            val options = RequestOptions()
                    .fitCenter()
                    .placeholder(R.drawable.placeholder)
                    .error(R.mipmap.ic_launcher)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .priority(Priority.HIGH)

            Glide.with(context).load(RetrofitClientInstance.IMAGE_URL + movie.poster_path)
                    .apply(options).listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {

                            if (onItemClicked is MovieFragment) {
                                favImage.visibility = View.GONE

                            }

                            return false
                        }

                        override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {

                            if (mActualHeight < 0) {
                                mActualHeight = Utils.getActualHeight(mActualWidth / 2, resource.intrinsicHeight, resource.intrinsicWidth)
                            }
                            image.layoutParams.height = mActualHeight
                            constraintLayout.layoutParams.height = mActualHeight


                            if (onItemClicked is MovieFragment) {
                                favImage.visibility = View.VISIBLE
                            }

                            return false
                        }
                    }).into(image)



            image.setOnClickListener { onItemClicked.invoke(movie, "detail") }



            favImage.setOnClickListener { onItemClicked.invoke(movie, "") }
        }
    }

    private inner class LoadHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var progressBar: ProgressBar

        init {
            progressBar = itemView.findViewById(R.id.progressBar)
        }
    }


}


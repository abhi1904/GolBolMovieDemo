package com.erosnow.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.erosnow.R

import com.erosnow.services.model.Movie

class MovieDetailActivity : AppCompatActivity() {
    private var mMovie: Movie? = null
    private var tvTitle: TextView? = null
    private var tvVoteCount: TextView? = null
    private var tvOrignLang: TextView? = null
    private var tvOrignTitle: TextView? = null
    private var tvBackDropPath: TextView? = null
    private var tvOverview: TextView? = null
    private var tvReleaseDate: TextView? = null
    private val TAG = MovieDetailActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.title = TAG

        setContentView(R.layout.movie_details)
        mMovie = intent.getParcelableExtra("movie")


        tvTitle = findViewById(R.id.title)
        tvVoteCount = findViewById(R.id.vote_count)
        tvOrignLang = findViewById(R.id.original_language)
        tvOrignTitle = findViewById(R.id.original_title)
        tvBackDropPath = findViewById(R.id.backdrop_path)
        tvOverview = findViewById(R.id.overview)
        tvReleaseDate = findViewById(R.id.release_date)


        tvTitle!!.text = "Title:" + mMovie!!.title
        tvVoteCount!!.text = "Vote Count:" + mMovie!!.vote_count
        tvOrignLang!!.text = "Original Language:" + mMovie!!.original_language
        tvOrignTitle!!.text = "Original Title:" + mMovie!!.original_title
        tvBackDropPath!!.text = "BackDrop Path:" + mMovie!!.backdrop_path
        tvOverview!!.text = "Overview:" + mMovie!!.overview
        tvReleaseDate!!.text = "Release Date:" + mMovie!!.release_date


    }
}

package com.moviedetail.view

import com.moviedetail.services.model.Movie


interface OnItemClicked {

    operator fun invoke(movie: Movie, type: String)
}

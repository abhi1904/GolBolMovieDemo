package com.erosnow.view

import com.erosnow.services.model.Movie


interface OnItemClicked {

    operator fun invoke(movie: Movie, type: String)
}

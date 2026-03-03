package woowacourse.movie.domain

sealed interface CancelError {
    object IsNotBooked: CancelError
}
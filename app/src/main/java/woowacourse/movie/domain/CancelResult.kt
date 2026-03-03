package woowacourse.movie.domain

sealed class CancelResult {
    data class Error(val type: CancelError) : CancelResult()
    data class Success(val ticket: MovieTicket) : CancelResult()
}
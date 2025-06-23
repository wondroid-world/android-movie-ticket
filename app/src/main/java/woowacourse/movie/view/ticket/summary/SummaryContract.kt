package woowacourse.movie.view.ticket.summary

import woowacourse.movie.intentmodel.SummaryIntentModel
import woowacourse.movie.uimodel.SummaryUiModel

interface SummaryContract {
    interface View {
        fun showData(summary: SummaryUiModel)
    }

    interface Presenter {
        fun getData(data: SummaryIntentModel)
    }
}

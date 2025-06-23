package woowacourse.movie.view.ticket.summary

import woowacourse.movie.intentmodel.SummaryIntentModel
import woowacourse.movie.uimodel.SummaryUiModel

class SummaryPresenter(
    private val view: SummaryContract.View,
) : SummaryContract.Presenter {
    override fun getData(data: SummaryIntentModel) {
        val summary =
            SummaryUiModel(
                id = data.id,
                title = data.title,
                screeningDateTime = data.screeningDateTime,
                peopleCount = data.peopleCount,
                totalAmount = data.totalAmount,
            )
        view.showData(summary)
    }
}

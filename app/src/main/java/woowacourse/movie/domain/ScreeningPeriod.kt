package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class ScreeningPeriod(
    val start: LocalDate,
    val end: LocalDate,
) {
    fun isWeekend(day: LocalDate): Boolean =
        when (day.dayOfWeek) {
            DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> true
            else -> false
        }

    // 날짜 반환
    fun availableDates(day: LocalDate): List<LocalDate> {
        if (end.isBefore(day)) return emptyList()

        val availableDate = mutableListOf<LocalDate>()
        var standardDay = if (start.isAfter(day)) start else day

        while (!end.isBefore(standardDay)) {
            availableDate.add(standardDay)
            standardDay = standardDay.plusDays(1)
        }

        return availableDate
    }

    // 시간 반환
    fun availableTime(
        day: LocalDate,
        time: LocalDateTime,
    ): List<LocalDateTime> {
        if (day.isBefore(time.toLocalDate())) return emptyList()

        val availableDateTime = mutableListOf<LocalDateTime>()
        var startTime = if (isWeekend(day)) 9 else 10

        if (day.isEqual(time.toLocalDate()) &&
            LocalTime
                .of(startTime, 0)
                .isBefore(time.toLocalTime())
        ) {
            val someTime = time.hour + if (time.minute > 0) 1 else 0
            if (isWeekend(day) && someTime % 2 == 1) {
                startTime =
                    someTime
            } else if (isWeekend(day) && someTime % 2 == 0) {
                startTime =
                    someTime + 1
            } else if (!isWeekend(day) && someTime % 2 == 0) {
                startTime =
                    someTime
            } else {
                startTime = someTime + 1
            }
        }

        var startDateTime = LocalDateTime.of(day.year, day.monthValue, day.dayOfMonth, startTime, 0)
        val endDateTime = LocalDateTime.of(day.year, day.monthValue, day.dayOfMonth.plus(1), 0, 0)

        while (endDateTime.isAfter(startDateTime)) {
            availableDateTime.add(startDateTime)
            startDateTime = startDateTime.plusHours(2)
        }
        return availableDateTime
    }
}
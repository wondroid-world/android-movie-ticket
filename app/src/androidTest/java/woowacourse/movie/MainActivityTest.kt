package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    // 테스트 대상 Activity 지정
    /* MainActivity::class.java의 의미
    MainActivity::class -> MainActivity class의 KClass 객체를 가져와
    .java -> KClass를 Java Class로 변경
    */
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun 앱바에_Movie가_표시된다() {
        // given, when: 앱 실행시
        // then: 화면에 Movie가 표시된다.

    }
}
package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import kotlin.reflect.KClass

object BuildVersion {
    fun <T : Parcelable> getParcelableClass(
        intent: Intent,
        name: String,
        clazz: KClass<T>,
    ): T =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(name, clazz.java)
                ?: throw IllegalStateException("Intent에서 ${clazz}가 전달이 되지 않았습니다.")
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(name) as? T
                ?: throw IllegalStateException("Intent에서 ${clazz}가 전달이 되지 않았습니다.")
        }
}
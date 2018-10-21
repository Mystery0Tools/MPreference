package vip.mystery0.mpreference.mpreferenceAnnotation

import vip.mystery0.mpreference.base.BaseMPreference
import vip.mystery0.mpreference.base.BaseMPreferenceViewHolder
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DeclareMPreference(val bindMPreferenceViewHolder: KClass<out BaseMPreferenceViewHolder<out BaseMPreference>>)
package kr.alpha93.lyra.internal


/**
 * Specifies that this function should not be called directly without inlining
 *
 * For replacement, will be replaced after [kotlin.internal.InlineOnly] becomes
 * available in public. For now, it doesn't have any effect.
 *
 * @see kotlin.internal.InlineOnly
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.BINARY)
internal annotation class InlineOnly

package kr.alpha93.lyra

import kr.alpha93.lyra.internal.InlineOnly
import kotlin.reflect.KClass


/**
 * Attempts to run [block], return [orElse] if [T] was thrown
 *
 * @param T The throwable which is ignored
 * @param R The type of return
 * @param orElse The fallback value.
 * @param throwable Throwable class to catch
 * @param block Actual code to run.
 */
@InlineOnly
public inline fun <reified T : Throwable, R> tryExcept(
    throwable: KClass<T> = T::class,
    orElse: R? = null,
    block: () -> R,
): R? {
    try {
        return block()
    } catch (t: Throwable) {
        if (t !is T) throw t
        return orElse
    }
}

/**
 * Attempts to run [block], return [orElse] if [T] was thrown
 *
 * @receiver The object to run
 * @param T The throwable which is ignored
 * @param R The type of return
 * @param V The type of receiver
 * @param orElse The fallback value.
 * @param throwable The KClass of the throwable
 * @param block Actual code to run.
 */
@InlineOnly
public inline fun <reified T : Throwable, R, V> V.attempt(
    throwable: KClass<T> = T::class,
    orElse: R? = null,
    block: V.() -> R,
): R? = tryExcept<T, R>(orElse = orElse) { this.block() }

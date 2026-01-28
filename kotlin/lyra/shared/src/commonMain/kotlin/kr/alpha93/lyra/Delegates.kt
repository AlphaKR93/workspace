package kr.alpha93.lyra

/**
 * A property delegate that allows a non-nullable property to be initialised lazily.
 *
 * @constructor Creates a new instance of [Holder].
 * @param T The type of the property.
 * @exception IllegalStateException Thrown when the property is modified after it has been initialised.
 */
class Holder<T : Any> {

    private lateinit var instance: T

    operator fun getValue(ignored: Any?, ignored1: Any?): T {
        return instance
    }

    operator fun setValue(ignored: Any?, ignored1: Any?, value: T) {
        check(!this::instance.isInitialized) { "Field cannot be modified" }
        instance = value
    }

}

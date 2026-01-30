package kr.alpha93.lyra

import kr.alpha93.lyra.primitives.toBooleanExact


/**
 * Gets the system property indicated by the specified [key].
 *
 * @param key The name of the system property.
 * @return The string value of the system property, or `null` if there is no property with that [key].
 * @throws IllegalArgumentException If the [key] is empty.
 * @see System.getProperty
 */
public fun getString(key: String): String? =
    System.getProperty(key)

/**
 * Gets the system property indicated by the specified [key].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The string value of the system property, or [default] if there is no property with that [key].
 * @throws IllegalArgumentException If the [key] is empty.
 * @see System.getProperty
 */
public fun getString(key: String, default: String): String =
    System.getProperty(key, default)

// region === Boolean Properties ===

/**
 * Determines the [Boolean] value of the system property with the specified [key].
 *
 * Returns [Boolean] if and only if the system property named by the [key]
 * exists and is equal to, ignoring a case, the string `"true"` or `"false"`.
 * If there is no property with the specified [key], `null` is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The `boolean` value of the system property.
 * @see getBooleanStrict
 * @see java.lang.Boolean.getBoolean
 */
public fun getBoolean(key: String): Boolean? =
    getString(key)?.toBoolean()

/**
 * Determines the [Boolean] value of the system property with the specified [key].
 *
 * Returns `true` if and only if the system property named by the [key] exists
 * and is equal to, ignoring a case, the string `"true"`.
 * Otherwise, the provided [default] value is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The `boolean` value of the system property.
 * @see getBooleanStrict
 * @see java.lang.Boolean.getBoolean
 */
public fun getBoolean(key: String, default: Boolean): Boolean =
    getString(key)?.toBoolean() ?: default

/**
 * Determines the [Boolean] value of the system property with the specified [key].
 *
 * Returns [Boolean] if and only if the system property named by the [key]
 * exists and is equal to, ignoring a case, the string `"true"` or `"false"`.
 * If there is no property with the specified [key], `null` is returned.
 *
 * If the system property has an invalid value,
 * an [IllegalArgumentException] is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The `boolean` value of the system property.
 * @throws IllegalArgumentException If the system property has an invalid value.
 * @see getBoolean
 * @see toBooleanExact
 */
public fun getBooleanStrict(key: String): Boolean? =
    getString(key)?.toBooleanExact()

/**
 * Determines the [Boolean] value of the system property with the specified [key].
 *
 * Returns [Boolean] if and only if the system property named by the [key]
 * exists and is equal to, ignoring a case, the string `"true"` or `"false"`.
 * If there is no property with the specified [key], the provided [default] value
 * is returned.
 *
 * If the system property has an invalid value,
 * an [IllegalArgumentException] is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The `boolean` value of the system property.
 * @throws IllegalArgumentException If the system property has an invalid value.
 * @see getBoolean
 * @see toBooleanExact
 */
public fun getBooleanStrict(key: String, default: Boolean): Boolean =
    getString(key)?.toBooleanExact() ?: default

// endregion

// region === Byte Properties ===

/**
 * Determines the [Byte] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Byte] value is returned. If there is no property with
 * the specified [key], or the property does not have the correct numeric format,
 * then `null` is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The [Byte] value of the system property.
 * @see getByteExact
 */
public fun getByte(key: String): Byte? =
    getString(key)?.attempt(NumberFormatException::class, null, String::toByte)

/**
 * Determines the [Byte] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Byte] value is returned. If there is no property with
 * the specified [key], or the property does not have the correct numeric format,
 * then the provided [default] value is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The [Byte] value of the system property.
 * @see getByteExact
 */
public fun getByte(key: String, default: Byte): Byte =
    getString(key)?.attempt(NumberFormatException::class, null, String::toByte) ?: default

/**
 * Determines the [Byte] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Byte] value is returned. If there is no property with
 * the specified [key], `null` is returned. If the system property does not have
 * the correct numeric format, an [NumberFormatException] is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The [Byte] value of the system property.
 * @see getByte
 */
public fun getByteExact(key: String): Byte? =
    getString(key)?.toByte()

/**
 * Determines the [Byte] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Byte] value is returned. If there is no property with
 * the specified [key], the provided [default] value is returned. If the system
 * property does not have the correct numeric format, an [NumberFormatException]
 * is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The [Byte] value of the system property.
 * @see getByte
 */
public fun getByteExact(key: String, default: Byte): Byte =
    getString(key)?.toByte() ?: default

// endregion

// region === Short Properties ===

/**
 * Determines the [Short] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Short] value is returned. If there is no property with
 * the specified [key], or the property does not have the correct numeric format,
 * then `null` is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The [Short] value of the system property.
 * @see getShortExact
 */
public fun getShort(key: String): Short? =
    getString(key)?.attempt(NumberFormatException::class, null, String::toShort)

/**
 * Determines the [Short] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Short] value is returned. If there is no property with
 * the specified [key], or the property does not have the correct numeric format,
 * then the provided [default] value is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The [Short] value of the system property.
 * @see getShortExact
 */
public fun getShort(key: String, default: Short): Short =
    getString(key)?.attempt(NumberFormatException::class, null, String::toShort) ?: default

/**
 * Determines the [Short] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Short] value is returned. If there is no property with
 * the specified [key], `null` is returned. If the system property does not have
 * the correct numeric format, an [NumberFormatException] is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The [Short] value of the system property.
 * @see getShort
 */
public fun getShortExact(key: String): Short? =
    getString(key)?.toShort()

/**
 * Determines the [Short] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Short] value is returned. If there is no property with
 * the specified [key], the provided [default] value is returned. If the system
 * property does not have the correct numeric format, an [NumberFormatException]
 * is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The [Short] value of the system property.
 * @see getShort
 */
public fun getShortExact(key: String, default: Short): Short =
    getString(key)?.toShort() ?: default

// endregion

// region === Integer Properties ===

/**
 * Determines the [Int] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Int] value is returned. If there is no property with
 * the specified [key], or the property does not have the correct numeric format,
 * then `null` is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The [Int] value of the system property.
 * @see getIntExact
 * @see Integer.getInteger
 */
public fun getInt(key: String): Int? =
    getString(key)?.attempt(NumberFormatException::class, null, String::toInt)

/**
 * Determines the [Int] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Int] value is returned. If there is no property with
 * the specified [key], or the property does not have the correct numeric format,
 * then the provided [default] value is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The [Int] value of the system property.
 * @see getIntExact
 * @see Integer.getInteger
 */
public fun getInt(key: String, default: Int): Int =
    getString(key)?.attempt(NumberFormatException::class, null, String::toInt) ?: default

/**
 * Determines the [Int] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Int] value is returned. If there is no property with
 * the specified [key], `null` is returned. If the system property does not have
 * the correct numeric format, an [NumberFormatException] is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The [Int] value of the system property.
 * @see getInt
 * @see Integer.getInteger
 */
public fun getIntExact(key: String): Int? =
    getString(key)?.toInt()

/**
 * Determines the [Int] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Int] value is returned. If there is no property with
 * the specified [key], the provided [default] value is returned. If the system
 * property does not have the correct numeric format, an [NumberFormatException]
 * is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The [Int] value of the system property.
 * @see getInt
 * @see Integer.getInteger
 */
public fun getIntExact(key: String, default: Int): Int =
    getString(key)?.toInt() ?: default

// endregion

// region === Long Properties ===

/**
 * Determines the [Long] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Long] value is returned. If there is no property with
 * the specified [key], or the property does not have the correct numeric format,
 * then `null` is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The [Long] value of the system property.
 * @see getLongExact
 * @see java.lang.Long.getLong
 */
public fun getLong(key: String): Long? =
    getString(key)?.attempt(NumberFormatException::class, null, String::toLong)

/**
 * Determines the [Long] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Long] value is returned. If there is no property with
 * the specified [key], or the property does not have the correct numeric format,
 * then the provided [default] value is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The [Long] value of the system property.
 * @see getLongExact
 * @see java.lang.Long.getLong
 */
public fun getLong(key: String, default: Long): Long =
    getString(key)?.attempt(NumberFormatException::class, null, String::toLong) ?: default

/**
 * Determines the [Long] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Long] value is returned. If there is no property with
 * the specified [key], `null` is returned. If the system property does not have
 * the correct numeric format, an [NumberFormatException] is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The [Long] value of the system property.
 * @see getLong
 * @see java.lang.Long.getLong
 */
public fun getLongExact(key: String): Long? =
    getString(key)?.toLong()

/**
 * Determines the [Long] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Long] value is returned. If there is no property with
 * the specified [key], the provided [default] value is returned. If the system
 * property does not have the correct numeric format, an [NumberFormatException]
 * is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The [Long] value of the system property.
 * @see getLong
 * @see java.lang.Long.getLong
 */
public fun getLongExact(key: String, default: Long): Long =
    getString(key)?.toLong() ?: default

// endregion

// region === Float Properties ===

/**
 * Determines the [Float] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Float] value is returned. If there is no property with
 * the specified [key], or the property does not have the correct numeric format,
 * then `null` is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The [Float] value of the system property.
 * @see getFloatExact
 */
public fun getFloat(key: String): Float? =
    getString(key)?.attempt(NumberFormatException::class, null, String::toFloat)

/**
 * Determines the [Float] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Float] value is returned. If there is no property with
 * the specified [key], or the property does not have the correct numeric format,
 * then the provided [default] value is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The [Float] value of the system property.
 * @see getFloatExact
 */
public fun getFloat(key: String, default: Float): Float =
    getString(key)?.attempt(NumberFormatException::class, null, String::toFloat) ?: default

/**
 * Determines the [Float] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Float] value is returned. If there is no property with
 * the specified [key], `null` is returned. If the system property does not have
 * the correct numeric format, an [NumberFormatException] is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The [Float] value of the system property.
 * @see getFloat
 */
public fun getFloatExact(key: String): Float? =
    getString(key)?.toFloat()

/**
 * Determines the [Float] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Float] value is returned. If there is no property with
 * the specified [key], the provided [default] value is returned. If the system
 * property does not have the correct numeric format, an [NumberFormatException]
 * is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The [Float] value of the system property.
 * @see getFloat
 */
public fun getFloatExact(key: String, default: Float): Float =
    getString(key)?.toFloat() ?: default

// endregion

// region === Double Properties ===

/**
 * Determines the [Double] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Double] value is returned. If there is no property with
 * the specified [key], or the property does not have the correct numeric format,
 * then `null` is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The [Double] value of the system property.
 * @see getDoubleExact
 */
public fun getDouble(key: String): Double? =
    getString(key)?.attempt(NumberFormatException::class, null, String::toDouble)

/**
 * Determines the [Double] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Double] value is returned. If there is no property with
 * the specified [key], or the property does not have the correct numeric format,
 * then the provided [default] value is returned.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The [Double] value of the system property.
 * @see getDoubleExact
 */
public fun getDouble(key: String, default: Double): Double =
    getString(key)?.attempt(NumberFormatException::class, null, String::toDouble) ?: default

/**
 * Determines the [Double] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Double] value is returned. If there is no property with
 * the specified [key], `null` is returned. If the system property does not have
 * the correct numeric format, an [NumberFormatException] is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @return The [Double] value of the system property.
 * @see getDouble
 */
public fun getDoubleExact(key: String): Double? =
    getString(key)?.toDouble()

/**
 * Determines the [Double] value of the system property with the specified [key].
 *
 * If the system property named by the [key] exists and have the correct numeric
 * format, then the parsed [Double] value is returned. If there is no property with
 * the specified [key], the provided [default] value is returned. If the system
 * property does not have the correct numeric format, an [NumberFormatException]
 * is thrown.
 *
 * A system property is accessible through [getString].
 *
 * @param key The name of the system property.
 * @param default The default value of the system property.
 * @return The [Double] value of the system property.
 * @see getDouble
 */
public fun getDoubleExact(key: String, default: Double): Double =
    getString(key)?.toDouble() ?: default

// endregion

package kr.alpha93.lyra.math.random

import kr.alpha93.lyra.tryExcept
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom as JvmSecureRandom
import kotlin.experimental.and


/**
 * Kotlin implementation of [java.security.SecureRandom].
 *
 * @constructor Constructs a secure random number generator (RNG) implementing the given random number algorithm.
 * @see java.security.SecureRandom
 */
public open class SecureRandom(private val actual: JvmSecureRandom) : ExtendedRandom() {

    public companion object {

        private const val FULL_BYTE: Byte = 0xFF.toByte()

        /**
         * [SecureRandom] object that was selected
         * by using the algorithms/providers specified in the
         * `securerandom.strongAlgorithms` [java.security.Security] property.
         */
        @JvmStatic
        public val STRONG: SecureRandom =
            SecureRandom(tryExcept(NoSuchAlgorithmException::class) { JvmSecureRandom.getInstanceStrong() }
                ?: JvmSecureRandom())

    }

    public constructor() : this(tryExcept(NoSuchAlgorithmException::class) { JvmSecureRandom.getInstanceStrong() } ?: JvmSecureRandom())

    public constructor(algorithm: String) : this(JvmSecureRandom.getInstance(algorithm))

    final override fun nextBits(bitCount: Int): Int {
        // Copied from java.security.SecureRandom.next(int)
        if (bitCount == 0) return 0

        val byteCount = (bitCount + 7) / 8
        val byteArray = nextBytes(byteCount)

        var next = 0
        for (i in 0 until byteCount) next = (next shl 8) + (byteArray[i] and FULL_BYTE)

        return next ushr ((byteCount * 8) - bitCount)
    }

    override fun nextInt(): Int = actual.nextInt()
    override fun nextInt(until: Int): Int = actual.nextInt(until)
    override fun nextInt(from: Int, until: Int): Int = actual.nextInt(from, until)
    override fun nextLong(): Long = actual.nextLong()
    override fun nextLong(until: Long): Long = actual.nextLong(until)
    override fun nextLong(from: Long, until: Long): Long = actual.nextLong(from, until)
    override fun nextBoolean(): Boolean = actual.nextBoolean()
    override fun nextDouble(): Double = actual.nextDouble()
    override fun nextDouble(until: Double): Double = actual.nextDouble(until)
    override fun nextDouble(from: Double, until: Double): Double = actual.nextDouble(from, until)
    override fun nextFloat(): Float = actual.nextFloat()
    override fun nextFloat(until: Float): Float = actual.nextFloat(until)
    override fun nextFloat(from: Float, until: Float): Float = actual.nextFloat(from, until)
    override fun nextBytes(array: ByteArray): ByteArray = array.apply(actual::nextBytes)

    override fun nextExponential(): Double = actual.nextExponential()
    override fun nextGaussian(): Double = actual.nextGaussian()
    override fun nextGaussian(mean: Double, stdDev: Double): Double = actual.nextGaussian(mean, stdDev)

}

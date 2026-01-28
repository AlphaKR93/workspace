package kr.alpha93.lyra.math.random

import kotlin.random.Random


public abstract class ExtendedRandom : Random() {

    /**
     * Gets the next random non-negative [Float] from the random number generator
     * less than the specified [until] bound.
     *
     * Generates a [Float] random value uniformly distributed between
     * 0 (inclusive) and [until] (exclusive).
     *
     * @param until the upper bound (exclusive) for the returned value. Must be positive.
     * @return a [Float] random value uniformly distributed between 0 (inclusive) and [until] (exclusive).
     * @throws IllegalArgumentException if [until] is negative or zero.
     */
    public open fun nextFloat(until: Float): Float = this.nextFloat(0F, until)

    /**
     * Gets the next random [Float] from the random number generator in the
     * specified range.
     *
     * Generates a [Float] random value uniformly distributed between the
     * specified [from] (inclusive) and [until] (exclusive) bounds.
     *
     * [from] and [until] must be finite otherwise the behavior is unspecified.
     *
     * @param from the least value that can be returned
     * @param until the upper bound (exclusive) for the returned value
     * @return a [Float] random value uniformly distributed between [from] (inclusive) and [until] (exclusive).
     * @throws IllegalArgumentException if [from] is greater than or equal to [until].
     */
    public open fun nextFloat(from: Float, until: Float): Float = this.nextBits(24) * 5.9604645E-8F

    /**
     * Returns a nonnegative [Double] value pseudorandomly chosen from an
     * exponential distribution whose mean is 1.
     */
    public abstract fun nextExponential(): Double

    /**
     * Returns a [Double] value pseudorandomly chosen from a Gaussian (normal)
     * distribution whose mean is 0 and whose standard deviation is 1
     *
     * @return a [Double] value pseudorandomly chosen from a Gaussian distribution
     */
    public abstract fun nextGaussian(): Double

    /**
     * Returns a [Double] value pseudorandomly chosen from a Gaussian (normal)
     * distribution with a mean and standard deviation specified by the arguments.
     *
     * @param mean the mean of the Gaussian distribution to be drawn from
     * @param stdDev the standard deviation (square root of the variance) of the Gaussian distribution to be drawn from
     * @return a [Double] value pseudorandomly chosen from the specified Gaussian distribution
     * @throws IllegalArgumentException if [stdDev] is negative
     */
    public abstract fun nextGaussian(mean: Double, stdDev: Double): Double

}

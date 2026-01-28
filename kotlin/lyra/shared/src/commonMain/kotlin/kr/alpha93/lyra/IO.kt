package kr.alpha93.lyra


/**
 * Returns the current time in milliseconds. Note that while the unit of time of
 * the return value is a millisecond, the granularity of the value depends on
 * the underlying operating system and may be larger. For example, many
 * operating systems measure time in units of tens of milliseconds.
 *
 * See the description of the class [java.util.Date] for a discussion of slight
 * discrepancies that may arise between "computer time" and
 * coordinated universal time (UTC).
 *
 * @see NOW_NANOS
 */
public expect val NOW: Long

/**
 * Returns the current value of the running Java Virtual Machine's
 * high-resolution time source, in nanoseconds.
 *
 * This method can only be used to measure elapsed time and is not related to
 * any other notion of system or wall-clock time. The value returned represents
 * nanoseconds since some fixed but arbitrary *origin* time (perhaps in the
 * future, so values may be negative). The same origin is used by all
 * invocations of this method in an instance of a Java virtual machine; other
 * virtual machine instances are likely to use a different origin.
 *
 * This method provides nanosecond precision, but not necessarily nanosecond
 * resolution (that is, how frequently the value changes) &ndash; no guarantees
 * are made except that the resolution is at least as good as that of [NOW].
 *
 * Differences in successive calls that span greater than approximately 292
 * years (2<sup>63</sup> nanoseconds) will not correctly compute elapsed time
 * due to numerical overflow.
 *
 * The values returned by this method become meaningful only when the difference
 * between two such values, obtained within the same instance of
 * a Java virtual machine, is computed.
 *
 * For example, to measure how long some code takes to execute:
 * ```
 * val startTime = NOW_NANOS
 * // ... the code being measured ...
 * val elapsedNanos = NOW_NANOS - startTime
 * ```
 *
 * To compare elapsed time against a timeout, use:
 * ```
 * if (NOW_NANOS - startTime >= timeoutNanos) // ...
 * ```
 *
 * instead of:
 * ```
 * if (NOW_NANOS >= startTime + timeoutNanos) ...
 * ```
 * because of the possibility of numerical overflow.
 *
 * @see NOW
 */
public expect val NOW_NANOS: Long

/**
 * Runs the garbage collector in the Java Virtual Machine.
 *
 * Calling the [gc] method suggests that the Java Virtual Machine expend effort
 * toward recycling unused objects to make the memory they currently occupy
 * available for reuse by the Java Virtual Machine.
 *
 * When control returns from the method call, the Java Virtual Machine has made
 * the best effort to reclaim space from all unused objects. There is no
 * guarantee that this effort will recycle any particular number of unused
 * objects, reclaim any particular amount of space, or complete at any
 * particular time, if at all, before the method returns or ever. There is also
 * no guarantee that this effort will determine the change of reachability in
 * any particular number of objects, or that any particular number of
 * [java.lang.ref.Reference] objects will be cleared and enqueued.
 *
 * The call of [gc] is effectively equivalent to the call:
 * ```
 * Runtime.getRuntime().gc()
 * ```
 */
public expect fun gc()

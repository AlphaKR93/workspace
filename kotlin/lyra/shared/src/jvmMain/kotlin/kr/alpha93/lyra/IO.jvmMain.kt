package kr.alpha93.lyra


/**
 * The runtime object associated with the current Java application.
 * Most of the methods of class [Runtime] are instance methods and must be
 * invoked with respect to the current runtime object.
 *
 * @see Runtime.getRuntime
 */
public val RUNTIME: Runtime
    inline get() = Runtime.getRuntime()

public actual val NOW: Long
    inline get() = System.currentTimeMillis()

public actual val NOW_NANOS: Long
    inline get() = System.nanoTime()

public actual fun gc(): Unit = System.gc()

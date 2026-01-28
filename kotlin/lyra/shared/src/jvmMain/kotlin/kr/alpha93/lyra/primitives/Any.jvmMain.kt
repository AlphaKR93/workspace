package kr.alpha93.lyra.primitives


public actual val Any?.hashCodeExact: Int
    inline get() = System.identityHashCode(this)

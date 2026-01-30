package kr.alpha93.lyra.reflect

import kr.alpha93.lyra.tryExcept
import java.lang.reflect.Field


public val Field.public: Field
    get() = this.also { it.isAccessible = true }

public operator fun Class<*>.contains(name: String): Boolean =
    tryExcept(NoSuchFieldException::class) { this.getDeclaredField(name) } != null

public operator fun Class<*>.get(name: String): Field = this.getDeclaredField(name)

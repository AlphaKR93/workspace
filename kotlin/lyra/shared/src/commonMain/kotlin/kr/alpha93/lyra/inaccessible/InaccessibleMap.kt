package kr.alpha93.lyra.inaccessible


/**
 * Represents the map that should not be accessed
 *
 * @throws UnsupportedOperationException Attempted to access to the map
 */
public object InaccessibleMap : Map<Nothing, Nothing> {

    override val size: Int
        get() = throw UnsupportedOperationException("This map should not be accessed")

    override val keys: Set<Nothing>
        get() = throw UnsupportedOperationException("This map should not be accessed")

    override val values: Collection<Nothing>
        get() = throw UnsupportedOperationException("This map should not be accessed")

    override val entries: Set<Map.Entry<Nothing, Nothing>>
        get() = throw UnsupportedOperationException("This map should not be accessed")

    override fun isEmpty(): Boolean =
        throw UnsupportedOperationException("This map should not be accessed")

    override fun containsKey(key: Nothing): Boolean =
        throw UnsupportedOperationException("This map should not be accessed")

    override fun containsValue(value: Nothing): Boolean =
        throw UnsupportedOperationException("This map should not be accessed")

    override fun get(key: Nothing): Nothing =
        throw UnsupportedOperationException("This map should not be accessed")

}

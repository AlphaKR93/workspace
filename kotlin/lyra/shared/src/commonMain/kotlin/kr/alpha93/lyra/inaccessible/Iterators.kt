package kr.alpha93.lyra.inaccessible


/**
 * Represents the iterator that has no elements.
 */
public object EmptyIterator : Iterator<Nothing> {
    override fun next(): Nothing = throw NoSuchElementException()
    override fun hasNext(): Boolean = false
}

/**
 * Represents the iterator that has no elements.
 */
public object EmptyMutableIterator : MutableIterator<Nothing> {
    override fun next(): Nothing = throw NoSuchElementException()
    override fun hasNext(): Boolean = false
    override fun remove(): Nothing = throw NoSuchElementException()
}

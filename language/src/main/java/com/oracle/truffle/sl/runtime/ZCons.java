package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.interop.InvalidArrayIndexException;

/**
 * The type Z10, list.
 * Represented as a linked list, with a head and a tail.
 * This class is only used for “well-formed” lists,
 * which do not have any keys besides Z10K1 head and Z10K2 tail,
 * and where the tail is in turn a well-formed list or {@link ZNil nil}.
 */
public final class ZCons extends ZList {

    private final Object head;
    private final ZList tail;
    private final long length; // cached for efficiency

    public ZCons(Object head, ZList tail) {
        this.head = head;
        this.tail = tail;
        this.length = tail.getArraySize() + 1;
    }

    /**
     * {@link ZList} values are interpreted as arrays by other languages,
     * with the length being the tail’s length plus one.
     */
    @Override
    public long getArraySize() {
        return length;
    }

    /**
     * {@link ZList} values are interpreted as arrays by other languages,
     * with the first element given by the head and the rest by the tail (shifted by one).
     */
    @Override
    public Object readArrayElement(long index) throws InvalidArrayIndexException {
        if (!isArrayElementReadable(index)) {
            throw InvalidArrayIndexException.create(index);
        }
        if (index == 0) {
            return head;
        } else {
            return tail.readArrayElement(index - 1);
        }
    }

}

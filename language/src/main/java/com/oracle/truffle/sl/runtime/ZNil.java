package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.InvalidArrayIndexException;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;

/**
 * The singleton value for the object Z13, nil.
 * It is used to represent the empty list
 * (and, in the tail position of a list, terminates the list).
 * For interop, it doubles as the {@code null} value.
 */ // TODO what about Z23, nothing? should that be the null value instead?
@ExportLibrary(InteropLibrary.class)
public final class ZNil extends ZList implements TruffleObject {

    /**
     * The canonical value to represent {@code nil}.
     */
    public static final ZNil SINGLETON = new ZNil();

    /**
     * Disallow instantiation from outside to ensure that the {@link #SINGLETON} is the only
     * instance.
     */
    private ZNil() {
    }

    /**
     * This method is, e.g., called when using the {@code nil} value in a string concatenation. So
     * changing it has an effect on SL programs.
     */ // TODO that’s a comment copy+pasted from SimpleLanguage, not yet verified
    @Override
    public String toString() {
        return "nil";
    }

    /**
     * {@link ZNil} values are interpreted as null values by other languages.
     */
    @ExportMessage
    public boolean isNull() {
        return true;
    }

    /**
     * {@link ZNil} values are interpreted as empty arrays by other languages.
     */
    @Override
    public long getArraySize() {
        return 0;
    }

    /**
     * {@link ZNil} values are interpreted as empty arrays by other languages.
     */
    @Override
    public Object readArrayElement(long index) throws InvalidArrayIndexException {
        throw InvalidArrayIndexException.create(index);
    }
}

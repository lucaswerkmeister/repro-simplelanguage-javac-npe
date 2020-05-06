package com.oracle.truffle.sl.runtime;

import com.oracle.truffle.api.interop.InteropLibrary;
import com.oracle.truffle.api.interop.InvalidArrayIndexException;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.library.ExportLibrary;
import com.oracle.truffle.api.library.ExportMessage;

/**
 * An object that behaves like a list in other languages:
 * the list constructor (with head and tail), or the nil value.
 */
@ExportLibrary(InteropLibrary.class)
public abstract class ZList implements TruffleObject {

    ZList() {
        // package-private constructor to allow only our two subclasses
    }

    @ExportMessage
    public final boolean hasArrayElements() {
        return true;
    }

    @ExportMessage
    public abstract Object readArrayElement(long index) throws InvalidArrayIndexException;

    @ExportMessage
    public abstract long getArraySize();

    @ExportMessage
    public final boolean isArrayElementReadable(long index) {
        return index >= 0 && index < getArraySize();
    }

}

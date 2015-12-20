/*
    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the license, or (at your option) any later version.
*/

package org.gjt.jclasslib.structures.constants;

import org.gjt.jclasslib.structures.CPInfo;
import org.gjt.jclasslib.structures.ConstantType;
import org.gjt.jclasslib.structures.InvalidByteCodeException;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
    Describes a <tt>CONSTANT_String_info</tt> constant pool data structure.

    @author <a href="mailto:jclasslib@ej-technologies.com">Ingo Kegel</a>
*/
public class ConstantStringInfo extends CPInfo {

    private int stringIndex;
    
    public ConstantType getConstantType() {
        return ConstantType.CONSTANT_STRING;
    }

    public String getVerbose() throws InvalidByteCodeException {
        return getClassFile().getConstantPoolEntryName(stringIndex);
    }

    /**
        Get the index of the constant pool entry containing the
        string of this entry.
        @return the index
     */
    public int getStringIndex() {
        return stringIndex;
    }

    /**
        Set the index of the constant pool entry containing the
        string of this entry.
        @param stringIndex the index
     */
    public void setStringIndex(int stringIndex) {
        this.stringIndex = stringIndex;
    }

    public void read(DataInput in)
        throws InvalidByteCodeException, IOException {
            
        stringIndex = in.readUnsignedShort();
        if (isDebug()) debug("read ");
    }
    
    public void write(DataOutput out)
        throws InvalidByteCodeException, IOException {

        out.writeByte(ConstantType.CONSTANT_STRING.getTag());
        out.writeShort(stringIndex);
        if (isDebug()) debug("wrote ");
    }

    protected void debug(String message) {
        super.debug(message + getConstantType() + " with string_index " + stringIndex);
    }

    public boolean equals(Object object) {
        if (!(object instanceof ConstantStringInfo)) {
            return false;
        }
        ConstantStringInfo constantStringInfo = (ConstantStringInfo)object;
        return super.equals(object) && constantStringInfo.stringIndex == stringIndex;
    }

    public int hashCode() {
        return super.hashCode() ^ stringIndex;
    }
    
}

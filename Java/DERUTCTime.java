/*
 * Copyright (c) 2000 The Legion Of The Bouncy Castle
 * (http://www.bouncycastle.org)
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT.  IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */

package org.bouncycastle.asn1;

import java.io.*;
import java.util.*;
import java.io.*;
import java.text.*;

/**
 * UTC time object.
 */
public class DERUTCTime
    extends DERObject
{
    String      time;

    /**
     * return an UTC Time from the passed in object.
     *
     * @exception IllegalArgumentException if the object cannot be converted.
     */
    public static DERUTCTime getInstance(
        Object  obj)
    {
        if (obj == null || obj instanceof DERUTCTime)
        {
            return (DERUTCTime)obj;
        }

        if (obj instanceof ASN1OctetString)
        {
            return new DERUTCTime(((ASN1OctetString)obj).getOctets());
        }

        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    /**
     * return an UTC Time from a tagged object.
     *
     * @param obj the tagged object holding the object we want
     * @param explicit true if the object is meant to be explicitly
     *              tagged false otherwise.
     * @exception IllegalArgumentException if the tagged object cannot
     *               be converted.
     */
    public static DERUTCTime getInstance(
        ASN1TaggedObject obj,
        boolean          explicit)
    {
        return getInstance(obj.getObject());
    }
    
    /**
     * The correct format for this is YYMMDDHHMMSSZ (it used to be that seconds were
     * never encoded. When you're creating one of these objects from scratch, that's
     * what you want to use, otherwise we'll try to deal with whatever gets read from
     * the input stream... (this is why the input format is different from the getTime()
     * method output).
     * <p>
     *
     * @param time the time string.
     */
    public DERUTCTime(
        String  time)
    {
        this.time = time;
    }

    /**
     * base constructer from a java.util.date object
     */
    public DERUTCTime(
       Date time)
    {
        SimpleDateFormat dateF = new SimpleDateFormat("yyMMddHHmmss'Z'");

        dateF.setTimeZone(new SimpleTimeZone(0,"Z"));

        this.time = dateF.format(time);
    }

    DERUTCTime(
        byte[]  bytes)
    {
        //
        // explicitly convert to characters
        //
        char[]  dateC = new char[bytes.length];

        for (int i = 0; i != dateC.length; i++)
        {
            dateC[i] = (char)(bytes[i] & 0xff);
        }

        this.time = new String(dateC);
    }

    /**
     * return the time - always in the form of 
     *  YYMMDDhhmmssGMT(+hh:mm|-hh:mm).
     * <p>
     * Normally in a certificate we would expect "Z" rather than "GMT",
     * however adding the "GMT" means we can just use:
     * <pre>
     *     dateF = new SimpleDateFormat("yyMMddHHmmssz");
     * </pre>
     * To read in the time and get a date which is compatible with our local
     * time zone.
     * <p>
     * <b>Note:</b> In some cases, due to the local date processing, this
     * may lead to unexpected results. If you want to stick the normal
     * convention of 1950 to 2049 use the getAdjustedTime() method.
     */
    public String getTime()
    {
        //
        // standardise the format.
        //
        if (time.length() == 11)
        {
            return time.substring(0, 10) + "00GMT+00:00";
        }
        else if (time.length() == 13)
        {
            return time.substring(0, 12) + "GMT+00:00";
        }
        else if (time.length() == 17)
        {
            return time.substring(0, 12) + "GMT" + time.substring(12, 15) + ":" + time.substring(15, 17);
        }

        return time;
    }

    /**
     * return the time as an adjusted date with a 4 digit year. This goes
     * in the range of 1950 - 2049.
     */
    public String getAdjustedTime()
    {
        String   d = this.getTime();

        if (d.charAt(0) < '5')
        {
            return "20" + d;
        }
        else
        {
            return "19" + d;
        }
    }

    private byte[] getOctets()
    {
        char[]  cs = time.toCharArray();
        byte[]  bs = new byte[cs.length];

        for (int i = 0; i != cs.length; i++)
        {
            bs[i] = (byte)cs[i];
        }

        return bs;
    }

    void encode(
        DEROutputStream  out)
        throws IOException
    {
        out.writeEncoded(UTC_TIME, this.getOctets());
    }
    
    public boolean equals(
        Object  o)
    {
        if ((o == null) || !(o instanceof DERUTCTime))
        {
            return false;
        }

        return time.equals(((DERUTCTime)o).time);
    }
}
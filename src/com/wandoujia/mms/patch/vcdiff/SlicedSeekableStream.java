package com.wandoujia.mms.patch.vcdiff;

import java.io.IOException;

/**
 * Wraps a SeekableStream with given offset and length.
 *
 * @author dongliu
 *
 */
public class SlicedSeekableStream implements SeekableStream {
    
    private SeekableStream ss;
    private long sOffset;
    private long slength;
    
    /**
     * Constructs a new RandomAccessFileSeekableSource.
     * @param raf
     */
    public SlicedSeekableStream(SeekableStream ss, long offset, long length) {
        if (ss == null) {
            throw new NullPointerException();
        }
        this.ss = ss;
        this.sOffset = offset;
        this.slength = length;
    }

    public void seek(long pos) throws IOException {
        ss.seek(sOffset + pos);
    }
    
    public long pos() throws IOException{
        return ss.pos() - sOffset;
    }

    @Override
    public int read(byte[] data, int offset, int length) throws IOException {
        return ss.read(data, offset, length);
    }

    @Override
    public long length() throws IOException {
        return this.slength;
    }

    @Override
    public void close() throws IOException {
        ss.close();
    }

    @Override
    public void write(byte[] data, int offset, int length) throws IOException {
        this.ss.write(data, offset, length);
    }

    @Override
    public void write(byte b) throws IOException {
        this.ss.write(b);
    }
    
}
package android.filterfw.core;

import android.filterfw.format.ObjectFormat;
import android.graphics.Bitmap;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class SerializedFrame extends Frame {
  private static final int INITIAL_CAPACITY = 64;
  
  private DirectByteOutputStream mByteOutputStream;
  
  private ObjectOutputStream mObjectOut;
  
  SerializedFrame(FrameFormat paramFrameFormat, FrameManager paramFrameManager) {
    super(paramFrameFormat, paramFrameManager);
    setReusable(false);
    try {
      DirectByteOutputStream directByteOutputStream = new DirectByteOutputStream();
      this(this, 64);
      this.mByteOutputStream = directByteOutputStream;
      ObjectOutputStream objectOutputStream = new ObjectOutputStream();
      this(this.mByteOutputStream);
      this.mObjectOut = objectOutputStream;
      this.mByteOutputStream.markHeaderEnd();
      return;
    } catch (IOException iOException) {
      throw new RuntimeException("Could not create serialization streams for SerializedFrame!", iOException);
    } 
  }
  
  private final Object deserializeObjectValue() {
    try {
      DirectByteInputStream directByteInputStream = this.mByteOutputStream.getInputStream();
      ObjectInputStream objectInputStream = new ObjectInputStream();
      this(directByteInputStream);
      return objectInputStream.readObject();
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Could not deserialize object in ");
      stringBuilder.append(this);
      stringBuilder.append("!");
      throw new RuntimeException(stringBuilder.toString(), iOException);
    } catch (ClassNotFoundException classNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to deserialize object of unknown class in ");
      stringBuilder.append(this);
      stringBuilder.append("!");
      throw new RuntimeException(stringBuilder.toString(), classNotFoundException);
    } 
  }
  
  private final void serializeObjectValue(Object paramObject) {
    try {
      this.mByteOutputStream.reset();
      this.mObjectOut.writeObject(paramObject);
      this.mObjectOut.flush();
      this.mObjectOut.close();
      return;
    } catch (IOException iOException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Could not serialize object ");
      stringBuilder.append(paramObject);
      stringBuilder.append(" in ");
      stringBuilder.append(this);
      stringBuilder.append("!");
      throw new RuntimeException(stringBuilder.toString(), iOException);
    } 
  }
  
  static SerializedFrame wrapObject(Object paramObject, FrameManager paramFrameManager) {
    SerializedFrame serializedFrame = new SerializedFrame(ObjectFormat.fromObject(paramObject, 1), paramFrameManager);
    serializedFrame.setObjectValue(paramObject);
    return serializedFrame;
  }
  
  public Bitmap getBitmap() {
    Object object = deserializeObjectValue();
    if (object instanceof Bitmap) {
      object = object;
    } else {
      object = null;
    } 
    return (Bitmap)object;
  }
  
  public ByteBuffer getData() {
    Object object = deserializeObjectValue();
    if (object instanceof ByteBuffer) {
      object = object;
    } else {
      object = null;
    } 
    return (ByteBuffer)object;
  }
  
  public float[] getFloats() {
    Object object = deserializeObjectValue();
    if (object instanceof float[]) {
      object = object;
    } else {
      object = null;
    } 
    return (float[])object;
  }
  
  public int[] getInts() {
    Object object = deserializeObjectValue();
    if (object instanceof int[]) {
      object = object;
    } else {
      object = null;
    } 
    return (int[])object;
  }
  
  public Object getObjectValue() {
    return deserializeObjectValue();
  }
  
  protected boolean hasNativeAllocation() {
    return false;
  }
  
  protected void releaseNativeAllocation() {}
  
  public void setBitmap(Bitmap paramBitmap) {
    assertFrameMutable();
    setGenericObjectValue(paramBitmap);
  }
  
  public void setData(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2) {
    assertFrameMutable();
    setGenericObjectValue(ByteBuffer.wrap(paramByteBuffer.array(), paramInt1, paramInt2));
  }
  
  public void setFloats(float[] paramArrayOffloat) {
    assertFrameMutable();
    setGenericObjectValue(paramArrayOffloat);
  }
  
  protected void setGenericObjectValue(Object paramObject) {
    serializeObjectValue(paramObject);
  }
  
  public void setInts(int[] paramArrayOfint) {
    assertFrameMutable();
    setGenericObjectValue(paramArrayOfint);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SerializedFrame (");
    stringBuilder.append(getFormat());
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  private class DirectByteInputStream extends InputStream {
    private byte[] mBuffer;
    
    private int mPos = 0;
    
    private int mSize;
    
    public DirectByteInputStream(byte[] param1ArrayOfbyte, int param1Int) {
      this.mBuffer = param1ArrayOfbyte;
      this.mSize = param1Int;
    }
    
    public final int available() {
      return this.mSize - this.mPos;
    }
    
    public final int read() {
      int i = this.mPos;
      if (i < this.mSize) {
        byte[] arrayOfByte = this.mBuffer;
        this.mPos = i + 1;
        i = arrayOfByte[i] & 0xFF;
      } else {
        i = -1;
      } 
      return i;
    }
    
    public final int read(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      int i = this.mPos;
      int j = this.mSize;
      if (i >= j)
        return -1; 
      int k = param1Int2;
      if (i + param1Int2 > j)
        k = j - i; 
      System.arraycopy(this.mBuffer, this.mPos, param1ArrayOfbyte, param1Int1, k);
      this.mPos += k;
      return k;
    }
    
    public final long skip(long param1Long) {
      int i = this.mPos;
      long l1 = i;
      int j = this.mSize;
      long l2 = param1Long;
      if (l1 + param1Long > j)
        l2 = (j - i); 
      if (l2 < 0L)
        return 0L; 
      this.mPos = (int)(this.mPos + l2);
      return l2;
    }
  }
  
  private class DirectByteOutputStream extends OutputStream {
    private byte[] mBuffer = null;
    
    private int mDataOffset = 0;
    
    private int mOffset = 0;
    
    public DirectByteOutputStream(int param1Int) {
      this.mBuffer = new byte[param1Int];
    }
    
    private final void ensureFit(int param1Int) {
      int i = this.mOffset;
      byte[] arrayOfByte = this.mBuffer;
      if (i + param1Int > arrayOfByte.length) {
        byte[] arrayOfByte1 = this.mBuffer;
        arrayOfByte = new byte[Math.max(i + param1Int, arrayOfByte.length * 2)];
        this.mBuffer = arrayOfByte;
        System.arraycopy(arrayOfByte1, 0, arrayOfByte, 0, this.mOffset);
      } 
    }
    
    public byte[] getByteArray() {
      return this.mBuffer;
    }
    
    public final SerializedFrame.DirectByteInputStream getInputStream() {
      return new SerializedFrame.DirectByteInputStream(this.mBuffer, this.mOffset);
    }
    
    public final int getSize() {
      return this.mOffset;
    }
    
    public final void markHeaderEnd() {
      this.mDataOffset = this.mOffset;
    }
    
    public final void reset() {
      this.mOffset = this.mDataOffset;
    }
    
    public final void write(int param1Int) {
      ensureFit(1);
      byte[] arrayOfByte = this.mBuffer;
      int i = this.mOffset;
      this.mOffset = i + 1;
      arrayOfByte[i] = (byte)(byte)param1Int;
    }
    
    public final void write(byte[] param1ArrayOfbyte) {
      write(param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
    }
    
    public final void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) {
      ensureFit(param1Int2);
      System.arraycopy(param1ArrayOfbyte, param1Int1, this.mBuffer, this.mOffset, param1Int2);
      this.mOffset += param1Int2;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/SerializedFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
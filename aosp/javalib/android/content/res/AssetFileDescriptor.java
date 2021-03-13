package android.content.res;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class AssetFileDescriptor implements Parcelable, Closeable {
  public static final Parcelable.Creator<AssetFileDescriptor> CREATOR = new Parcelable.Creator<AssetFileDescriptor>() {
      public AssetFileDescriptor createFromParcel(Parcel param1Parcel) {
        return new AssetFileDescriptor(param1Parcel);
      }
      
      public AssetFileDescriptor[] newArray(int param1Int) {
        return new AssetFileDescriptor[param1Int];
      }
    };
  
  public static final long UNKNOWN_LENGTH = -1L;
  
  private final Bundle mExtras;
  
  private final ParcelFileDescriptor mFd;
  
  private final long mLength;
  
  private final long mStartOffset;
  
  AssetFileDescriptor(Parcel paramParcel) {
    this.mFd = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramParcel);
    this.mStartOffset = paramParcel.readLong();
    this.mLength = paramParcel.readLong();
    if (paramParcel.readInt() != 0) {
      this.mExtras = paramParcel.readBundle();
    } else {
      this.mExtras = null;
    } 
  }
  
  public AssetFileDescriptor(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong1, long paramLong2) {
    this(paramParcelFileDescriptor, paramLong1, paramLong2, null);
  }
  
  public AssetFileDescriptor(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong1, long paramLong2, Bundle paramBundle) {
    if (paramParcelFileDescriptor != null) {
      if (paramLong2 >= 0L || paramLong1 == 0L) {
        this.mFd = paramParcelFileDescriptor;
        this.mStartOffset = paramLong1;
        this.mLength = paramLong2;
        this.mExtras = paramBundle;
        return;
      } 
      throw new IllegalArgumentException("startOffset must be 0 when using UNKNOWN_LENGTH");
    } 
    throw new IllegalArgumentException("fd must not be null");
  }
  
  public void close() throws IOException {
    this.mFd.close();
  }
  
  public FileInputStream createInputStream() throws IOException {
    return (FileInputStream)((this.mLength < 0L) ? new ParcelFileDescriptor.AutoCloseInputStream(this.mFd) : new AutoCloseInputStream(this));
  }
  
  public FileOutputStream createOutputStream() throws IOException {
    return (FileOutputStream)((this.mLength < 0L) ? new ParcelFileDescriptor.AutoCloseOutputStream(this.mFd) : new AutoCloseOutputStream(this));
  }
  
  public int describeContents() {
    return this.mFd.describeContents();
  }
  
  public long getDeclaredLength() {
    return this.mLength;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public FileDescriptor getFileDescriptor() {
    return this.mFd.getFileDescriptor();
  }
  
  public long getLength() {
    long l = this.mLength;
    if (l >= 0L)
      return l; 
    l = this.mFd.getStatSize();
    if (l < 0L)
      l = -1L; 
    return l;
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor() {
    return this.mFd;
  }
  
  public long getStartOffset() {
    return this.mStartOffset;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{AssetFileDescriptor: ");
    stringBuilder.append(this.mFd);
    stringBuilder.append(" start=");
    stringBuilder.append(this.mStartOffset);
    stringBuilder.append(" len=");
    stringBuilder.append(this.mLength);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    this.mFd.writeToParcel(paramParcel, paramInt);
    paramParcel.writeLong(this.mStartOffset);
    paramParcel.writeLong(this.mLength);
    if (this.mExtras != null) {
      paramParcel.writeInt(1);
      paramParcel.writeBundle(this.mExtras);
    } else {
      paramParcel.writeInt(0);
    } 
  }
  
  public static class AutoCloseInputStream extends ParcelFileDescriptor.AutoCloseInputStream {
    private long mRemaining;
    
    public AutoCloseInputStream(AssetFileDescriptor param1AssetFileDescriptor) throws IOException {
      super(param1AssetFileDescriptor.getParcelFileDescriptor());
      super.skip(param1AssetFileDescriptor.getStartOffset());
      this.mRemaining = (int)param1AssetFileDescriptor.getLength();
    }
    
    public int available() throws IOException {
      int i;
      long l = this.mRemaining;
      if (l >= 0L) {
        if (l < 2147483647L) {
          i = (int)l;
        } else {
          i = Integer.MAX_VALUE;
        } 
      } else {
        i = super.available();
      } 
      return i;
    }
    
    public void mark(int param1Int) {
      if (this.mRemaining >= 0L)
        return; 
      super.mark(param1Int);
    }
    
    public boolean markSupported() {
      return (this.mRemaining >= 0L) ? false : super.markSupported();
    }
    
    public int read() throws IOException {
      byte[] arrayOfByte = new byte[1];
      int i = read(arrayOfByte, 0, 1);
      int j = -1;
      if (i != -1)
        j = arrayOfByte[0] & 0xFF; 
      return j;
    }
    
    public int read(byte[] param1ArrayOfbyte) throws IOException {
      return read(param1ArrayOfbyte, 0, param1ArrayOfbyte.length);
    }
    
    public int read(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      long l = this.mRemaining;
      if (l >= 0L) {
        if (l == 0L)
          return -1; 
        int i = param1Int2;
        if (param1Int2 > l)
          i = (int)l; 
        param1Int1 = super.read(param1ArrayOfbyte, param1Int1, i);
        if (param1Int1 >= 0)
          this.mRemaining -= param1Int1; 
        return param1Int1;
      } 
      return super.read(param1ArrayOfbyte, param1Int1, param1Int2);
    }
    
    public void reset() throws IOException {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mRemaining : J
      //   6: lstore_1
      //   7: lload_1
      //   8: lconst_0
      //   9: lcmp
      //   10: iflt -> 16
      //   13: aload_0
      //   14: monitorexit
      //   15: return
      //   16: aload_0
      //   17: invokespecial reset : ()V
      //   20: aload_0
      //   21: monitorexit
      //   22: return
      //   23: astore_3
      //   24: aload_0
      //   25: monitorexit
      //   26: aload_3
      //   27: athrow
      // Exception table:
      //   from	to	target	type
      //   2	7	23	finally
      //   16	20	23	finally
    }
    
    public long skip(long param1Long) throws IOException {
      long l = this.mRemaining;
      if (l >= 0L) {
        if (l == 0L)
          return -1L; 
        long l1 = param1Long;
        if (param1Long > l)
          l1 = this.mRemaining; 
        param1Long = super.skip(l1);
        if (param1Long >= 0L)
          this.mRemaining -= param1Long; 
        return param1Long;
      } 
      return super.skip(param1Long);
    }
  }
  
  public static class AutoCloseOutputStream extends ParcelFileDescriptor.AutoCloseOutputStream {
    private long mRemaining;
    
    public AutoCloseOutputStream(AssetFileDescriptor param1AssetFileDescriptor) throws IOException {
      super(param1AssetFileDescriptor.getParcelFileDescriptor());
      if (param1AssetFileDescriptor.getParcelFileDescriptor().seekTo(param1AssetFileDescriptor.getStartOffset()) >= 0L) {
        this.mRemaining = (int)param1AssetFileDescriptor.getLength();
        return;
      } 
      throw new IOException("Unable to seek");
    }
    
    public void write(int param1Int) throws IOException {
      long l = this.mRemaining;
      if (l >= 0L) {
        if (l == 0L)
          return; 
        super.write(param1Int);
        this.mRemaining--;
        return;
      } 
      super.write(param1Int);
    }
    
    public void write(byte[] param1ArrayOfbyte) throws IOException {
      long l = this.mRemaining;
      if (l >= 0L) {
        if (l == 0L)
          return; 
        int i = param1ArrayOfbyte.length;
        int j = i;
        if (i > l)
          j = (int)l; 
        super.write(param1ArrayOfbyte);
        this.mRemaining -= j;
        return;
      } 
      super.write(param1ArrayOfbyte);
    }
    
    public void write(byte[] param1ArrayOfbyte, int param1Int1, int param1Int2) throws IOException {
      long l = this.mRemaining;
      if (l >= 0L) {
        if (l == 0L)
          return; 
        int i = param1Int2;
        if (param1Int2 > l)
          i = (int)l; 
        super.write(param1ArrayOfbyte, param1Int1, i);
        this.mRemaining -= i;
        return;
      } 
      super.write(param1ArrayOfbyte, param1Int1, param1Int2);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/res/AssetFileDescriptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.annotation.IntRange;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pools;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.Preconditions;

public final class NoteOpEvent implements Parcelable {
  public static final Parcelable.Creator<NoteOpEvent> CREATOR = new Parcelable.Creator<NoteOpEvent>() {
      public AppOpsManager.NoteOpEvent createFromParcel(Parcel param2Parcel) {
        return new AppOpsManager.NoteOpEvent(param2Parcel);
      }
      
      public AppOpsManager.NoteOpEvent[] newArray(int param2Int) {
        return new AppOpsManager.NoteOpEvent[param2Int];
      }
    };
  
  private long mDuration;
  
  private long mNoteTime;
  
  private AppOpsManager.OpEventProxyInfo mProxy;
  
  public NoteOpEvent(long paramLong1, long paramLong2, AppOpsManager.OpEventProxyInfo paramOpEventProxyInfo) {
    this.mNoteTime = paramLong1;
    AnnotationValidations.validate(IntRange.class, null, paramLong1, "from", 0L);
    this.mDuration = paramLong2;
    AnnotationValidations.validate(IntRange.class, null, paramLong2, "from", -1L);
    this.mProxy = paramOpEventProxyInfo;
  }
  
  public NoteOpEvent(NoteOpEvent paramNoteOpEvent) {
    this(l1, l2, (AppOpsManager.OpEventProxyInfo)paramNoteOpEvent);
  }
  
  NoteOpEvent(Parcel paramParcel) {
    AppOpsManager.OpEventProxyInfo opEventProxyInfo;
    byte b = paramParcel.readByte();
    long l1 = paramParcel.readLong();
    long l2 = paramParcel.readLong();
    if ((b & 0x4) == 0) {
      paramParcel = null;
    } else {
      opEventProxyInfo = (AppOpsManager.OpEventProxyInfo)paramParcel.readTypedObject(AppOpsManager.OpEventProxyInfo.CREATOR);
    } 
    this.mNoteTime = l1;
    AnnotationValidations.validate(IntRange.class, null, l1, "from", 0L);
    this.mDuration = l2;
    AnnotationValidations.validate(IntRange.class, null, l2, "from", -1L);
    this.mProxy = opEventProxyInfo;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getDuration() {
    return this.mDuration;
  }
  
  public long getNoteTime() {
    return this.mNoteTime;
  }
  
  public AppOpsManager.OpEventProxyInfo getProxy() {
    return this.mProxy;
  }
  
  public void reinit(long paramLong1, long paramLong2, AppOpsManager.OpEventProxyInfo paramOpEventProxyInfo, Pools.Pool<AppOpsManager.OpEventProxyInfo> paramPool) {
    this.mNoteTime = Preconditions.checkArgumentNonnegative(paramLong1);
    this.mDuration = Preconditions.checkArgumentInRange(paramLong2, -1L, Long.MAX_VALUE, "duration");
    AppOpsManager.OpEventProxyInfo opEventProxyInfo = this.mProxy;
    if (opEventProxyInfo != null)
      paramPool.release(opEventProxyInfo); 
    this.mProxy = paramOpEventProxyInfo;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    byte b1 = 0;
    byte b2 = b1;
    if (this.mProxy != null) {
      b1 = (byte)(0x0 | 0x4);
      b2 = b1;
    } 
    paramParcel.writeByte(b2);
    paramParcel.writeLong(this.mNoteTime);
    paramParcel.writeLong(this.mDuration);
    AppOpsManager.OpEventProxyInfo opEventProxyInfo = this.mProxy;
    if (opEventProxyInfo != null)
      paramParcel.writeTypedObject(opEventProxyInfo, paramInt); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$NoteOpEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
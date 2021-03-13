package android.app;

import android.annotation.IntRange;
import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import android.util.LongSparseArray;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.Parcelling;

@SystemApi
public final class AttributedOpEntry implements Parcelable {
  public static final Parcelable.Creator<AttributedOpEntry> CREATOR;
  
  static Parcelling<LongSparseArray<AppOpsManager.NoteOpEvent>> sParcellingForAccessEvents;
  
  static Parcelling<LongSparseArray<AppOpsManager.NoteOpEvent>> sParcellingForRejectEvents;
  
  private final LongSparseArray<AppOpsManager.NoteOpEvent> mAccessEvents;
  
  private final int mOp;
  
  private final LongSparseArray<AppOpsManager.NoteOpEvent> mRejectEvents;
  
  private final boolean mRunning;
  
  static {
    Parcelling<LongSparseArray<AppOpsManager.NoteOpEvent>> parcelling = Parcelling.Cache.get(LongSparseArrayParceling.class);
    sParcellingForAccessEvents = parcelling;
    if (parcelling == null)
      sParcellingForAccessEvents = Parcelling.Cache.put(new LongSparseArrayParceling()); 
    parcelling = Parcelling.Cache.get(LongSparseArrayParceling.class);
    sParcellingForRejectEvents = parcelling;
    if (parcelling == null)
      sParcellingForRejectEvents = Parcelling.Cache.put(new LongSparseArrayParceling()); 
    CREATOR = new Parcelable.Creator<AttributedOpEntry>() {
        public AppOpsManager.AttributedOpEntry createFromParcel(Parcel param2Parcel) {
          return new AppOpsManager.AttributedOpEntry(param2Parcel);
        }
        
        public AppOpsManager.AttributedOpEntry[] newArray(int param2Int) {
          return new AppOpsManager.AttributedOpEntry[param2Int];
        }
      };
  }
  
  public AttributedOpEntry(int paramInt, boolean paramBoolean, LongSparseArray<AppOpsManager.NoteOpEvent> paramLongSparseArray1, LongSparseArray<AppOpsManager.NoteOpEvent> paramLongSparseArray2) {
    this.mOp = paramInt;
    AnnotationValidations.validate(IntRange.class, null, paramInt, "from", 0L, "to", 102L);
    this.mRunning = paramBoolean;
    this.mAccessEvents = paramLongSparseArray1;
    this.mRejectEvents = paramLongSparseArray2;
  }
  
  AttributedOpEntry(Parcel paramParcel) {
    boolean bool;
    if ((paramParcel.readByte() & 0x2) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    int i = paramParcel.readInt();
    LongSparseArray<AppOpsManager.NoteOpEvent> longSparseArray2 = (LongSparseArray)sParcellingForAccessEvents.unparcel(paramParcel);
    LongSparseArray<AppOpsManager.NoteOpEvent> longSparseArray1 = (LongSparseArray)sParcellingForRejectEvents.unparcel(paramParcel);
    this.mOp = i;
    AnnotationValidations.validate(IntRange.class, null, i, "from", 0L, "to", 102L);
    this.mRunning = bool;
    this.mAccessEvents = longSparseArray2;
    this.mRejectEvents = longSparseArray1;
  }
  
  private AppOpsManager.NoteOpEvent getLastAccessEvent(int paramInt1, int paramInt2, int paramInt3) {
    return AppOpsManager.access$400(this.mAccessEvents, paramInt1, paramInt2, paramInt3);
  }
  
  private AppOpsManager.NoteOpEvent getLastRejectEvent(int paramInt1, int paramInt2, int paramInt3) {
    return AppOpsManager.access$400(this.mRejectEvents, paramInt1, paramInt2, paramInt3);
  }
  
  public ArraySet<Long> collectKeys() {
    ArraySet<Long> arraySet = new ArraySet();
    LongSparseArray<AppOpsManager.NoteOpEvent> longSparseArray = this.mAccessEvents;
    if (longSparseArray != null) {
      int i = longSparseArray.size();
      for (byte b = 0; b < i; b++)
        arraySet.add(Long.valueOf(this.mAccessEvents.keyAt(b))); 
    } 
    longSparseArray = this.mRejectEvents;
    if (longSparseArray != null) {
      int i = longSparseArray.size();
      for (byte b = 0; b < i; b++)
        arraySet.add(Long.valueOf(this.mRejectEvents.keyAt(b))); 
    } 
    return arraySet;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long getLastAccessBackgroundTime(int paramInt) {
    return getLastAccessTime(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, paramInt);
  }
  
  public long getLastAccessForegroundTime(int paramInt) {
    return getLastAccessTime(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), paramInt);
  }
  
  public long getLastAccessTime(int paramInt) {
    return getLastAccessTime(100, 700, paramInt);
  }
  
  public long getLastAccessTime(int paramInt1, int paramInt2, int paramInt3) {
    AppOpsManager.NoteOpEvent noteOpEvent = getLastAccessEvent(paramInt1, paramInt2, paramInt3);
    return (noteOpEvent == null) ? -1L : noteOpEvent.getNoteTime();
  }
  
  public long getLastBackgroundDuration(int paramInt) {
    return getLastDuration(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, paramInt);
  }
  
  public AppOpsManager.OpEventProxyInfo getLastBackgroundProxyInfo(int paramInt) {
    return getLastProxyInfo(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, paramInt);
  }
  
  public long getLastDuration(int paramInt) {
    return getLastDuration(100, 700, paramInt);
  }
  
  public long getLastDuration(int paramInt1, int paramInt2, int paramInt3) {
    AppOpsManager.NoteOpEvent noteOpEvent = getLastAccessEvent(paramInt1, paramInt2, paramInt3);
    return (noteOpEvent == null) ? -1L : noteOpEvent.getDuration();
  }
  
  public long getLastForegroundDuration(int paramInt) {
    return getLastDuration(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), paramInt);
  }
  
  public AppOpsManager.OpEventProxyInfo getLastForegroundProxyInfo(int paramInt) {
    return getLastProxyInfo(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), paramInt);
  }
  
  public AppOpsManager.OpEventProxyInfo getLastProxyInfo(int paramInt) {
    return getLastProxyInfo(100, 700, paramInt);
  }
  
  public AppOpsManager.OpEventProxyInfo getLastProxyInfo(int paramInt1, int paramInt2, int paramInt3) {
    AppOpsManager.NoteOpEvent noteOpEvent = getLastAccessEvent(paramInt1, paramInt2, paramInt3);
    return (noteOpEvent == null) ? null : noteOpEvent.getProxy();
  }
  
  public long getLastRejectBackgroundTime(int paramInt) {
    return getLastRejectTime(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, paramInt);
  }
  
  public long getLastRejectForegroundTime(int paramInt) {
    return getLastRejectTime(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), paramInt);
  }
  
  public long getLastRejectTime(int paramInt) {
    return getLastRejectTime(100, 700, paramInt);
  }
  
  public long getLastRejectTime(int paramInt1, int paramInt2, int paramInt3) {
    AppOpsManager.NoteOpEvent noteOpEvent = getLastRejectEvent(paramInt1, paramInt2, paramInt3);
    return (noteOpEvent == null) ? -1L : noteOpEvent.getNoteTime();
  }
  
  public boolean isRunning() {
    return this.mRunning;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    byte b1 = 0;
    if (this.mRunning)
      b1 = (byte)(0x0 | 0x2); 
    byte b2 = b1;
    if (this.mAccessEvents != null)
      b2 = (byte)(b1 | 0x4); 
    byte b3 = b2;
    if (this.mRejectEvents != null) {
      b1 = (byte)(b2 | 0x8);
      b3 = b1;
    } 
    paramParcel.writeByte(b3);
    paramParcel.writeInt(this.mOp);
    sParcellingForAccessEvents.parcel(this.mAccessEvents, paramParcel, paramInt);
    sParcellingForRejectEvents.parcel(this.mRejectEvents, paramParcel, paramInt);
  }
  
  private static class LongSparseArrayParceling implements Parcelling<LongSparseArray<AppOpsManager.NoteOpEvent>> {
    private LongSparseArrayParceling() {}
    
    public void parcel(LongSparseArray<AppOpsManager.NoteOpEvent> param2LongSparseArray, Parcel param2Parcel, int param2Int) {
      if (param2LongSparseArray == null) {
        param2Parcel.writeInt(-1);
        return;
      } 
      int i = param2LongSparseArray.size();
      param2Parcel.writeInt(i);
      for (byte b = 0; b < i; b++) {
        param2Parcel.writeLong(param2LongSparseArray.keyAt(b));
        param2Parcel.writeParcelable((Parcelable)param2LongSparseArray.valueAt(b), param2Int);
      } 
    }
    
    public LongSparseArray<AppOpsManager.NoteOpEvent> unparcel(Parcel param2Parcel) {
      int i = param2Parcel.readInt();
      if (i == -1)
        return null; 
      LongSparseArray<AppOpsManager.NoteOpEvent> longSparseArray = new LongSparseArray(i);
      for (byte b = 0; b < i; b++)
        longSparseArray.put(param2Parcel.readLong(), param2Parcel.readParcelable(null)); 
      return longSparseArray;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$AttributedOpEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
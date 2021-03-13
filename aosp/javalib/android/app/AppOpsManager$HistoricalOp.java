package android.app;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.LongSparseArray;
import android.util.LongSparseLongArray;
import java.util.Objects;
import java.util.function.Supplier;

@SystemApi
public final class HistoricalOp implements Parcelable {
  public static final Parcelable.Creator<HistoricalOp> CREATOR = new Parcelable.Creator<HistoricalOp>() {
      public AppOpsManager.HistoricalOp createFromParcel(Parcel param2Parcel) {
        return new AppOpsManager.HistoricalOp(param2Parcel);
      }
      
      public AppOpsManager.HistoricalOp[] newArray(int param2Int) {
        return new AppOpsManager.HistoricalOp[param2Int];
      }
    };
  
  private LongSparseLongArray mAccessCount;
  
  private LongSparseLongArray mAccessDuration;
  
  private final int mOp;
  
  private LongSparseLongArray mRejectCount;
  
  public HistoricalOp(int paramInt) {
    this.mOp = paramInt;
  }
  
  private HistoricalOp(HistoricalOp paramHistoricalOp) {
    this.mOp = paramHistoricalOp.mOp;
    LongSparseLongArray longSparseLongArray2 = paramHistoricalOp.mAccessCount;
    if (longSparseLongArray2 != null)
      this.mAccessCount = longSparseLongArray2.clone(); 
    longSparseLongArray2 = paramHistoricalOp.mRejectCount;
    if (longSparseLongArray2 != null)
      this.mRejectCount = longSparseLongArray2.clone(); 
    LongSparseLongArray longSparseLongArray1 = paramHistoricalOp.mAccessDuration;
    if (longSparseLongArray1 != null)
      this.mAccessDuration = longSparseLongArray1.clone(); 
  }
  
  private HistoricalOp(Parcel paramParcel) {
    this.mOp = paramParcel.readInt();
    this.mAccessCount = AppOpsManager.access$5000(paramParcel);
    this.mRejectCount = AppOpsManager.access$5000(paramParcel);
    this.mAccessDuration = AppOpsManager.access$5000(paramParcel);
  }
  
  private void accept(AppOpsManager.HistoricalOpsVisitor paramHistoricalOpsVisitor) {
    paramHistoricalOpsVisitor.visitHistoricalOp(this);
  }
  
  private void filter(double paramDouble) {
    scale(this.mAccessCount, paramDouble);
    scale(this.mRejectCount, paramDouble);
    scale(this.mAccessDuration, paramDouble);
  }
  
  private LongSparseLongArray getOrCreateAccessCount() {
    if (this.mAccessCount == null)
      this.mAccessCount = new LongSparseLongArray(); 
    return this.mAccessCount;
  }
  
  private LongSparseLongArray getOrCreateAccessDuration() {
    if (this.mAccessDuration == null)
      this.mAccessDuration = new LongSparseLongArray(); 
    return this.mAccessDuration;
  }
  
  private LongSparseLongArray getOrCreateRejectCount() {
    if (this.mRejectCount == null)
      this.mRejectCount = new LongSparseLongArray(); 
    return this.mRejectCount;
  }
  
  private boolean hasData(LongSparseLongArray paramLongSparseLongArray) {
    boolean bool;
    if (paramLongSparseLongArray != null && paramLongSparseLongArray.size() > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void increaseAccessCount(int paramInt1, int paramInt2, long paramLong) {
    increaseCount(getOrCreateAccessCount(), paramInt1, paramInt2, paramLong);
  }
  
  private void increaseAccessDuration(int paramInt1, int paramInt2, long paramLong) {
    increaseCount(getOrCreateAccessDuration(), paramInt1, paramInt2, paramLong);
  }
  
  private void increaseCount(LongSparseLongArray paramLongSparseLongArray, int paramInt1, int paramInt2, long paramLong) {
    while (paramInt2 != 0) {
      int i = 1 << Integer.numberOfTrailingZeros(paramInt2);
      paramInt2 &= i;
      long l = AppOpsManager.makeKey(paramInt1, i);
      paramLongSparseLongArray.put(l, paramLongSparseLongArray.get(l) + paramLong);
    } 
  }
  
  private void increaseRejectCount(int paramInt1, int paramInt2, long paramLong) {
    increaseCount(getOrCreateRejectCount(), paramInt1, paramInt2, paramLong);
  }
  
  private boolean isEmpty() {
    boolean bool;
    if (!hasData(this.mAccessCount) && !hasData(this.mRejectCount) && !hasData(this.mAccessDuration)) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private void merge(HistoricalOp paramHistoricalOp) {
    merge(new _$$Lambda$AppOpsManager$HistoricalOp$HUOLFYs8TiaQIOXcrq6JzjxA6gs(this), paramHistoricalOp.mAccessCount);
    merge(new _$$Lambda$AppOpsManager$HistoricalOp$DkVcBvqB32SMHlxw0sWQPh3GL1A(this), paramHistoricalOp.mRejectCount);
    merge(new _$$Lambda$AppOpsManager$HistoricalOp$Vs6pDL0wjOBTquwNnreWVbPQrn4(this), paramHistoricalOp.mAccessDuration);
  }
  
  private static void merge(Supplier<LongSparseLongArray> paramSupplier, LongSparseLongArray paramLongSparseLongArray) {
    if (paramLongSparseLongArray != null) {
      int i = paramLongSparseLongArray.size();
      for (byte b = 0; b < i; b++) {
        LongSparseLongArray longSparseLongArray = paramSupplier.get();
        long l1 = paramLongSparseLongArray.keyAt(b);
        long l2 = paramLongSparseLongArray.valueAt(b);
        longSparseLongArray.put(l1, longSparseLongArray.get(l1) + l2);
      } 
    } 
  }
  
  private static void scale(LongSparseLongArray paramLongSparseLongArray, double paramDouble) {
    if (paramLongSparseLongArray != null) {
      int i = paramLongSparseLongArray.size();
      for (byte b = 0; b < i; b++)
        paramLongSparseLongArray.put(paramLongSparseLongArray.keyAt(b), (long)AppOpsManager.HistoricalOps.round(paramLongSparseLongArray.valueAt(b) * paramDouble)); 
    } 
  }
  
  private HistoricalOp splice(double paramDouble) {
    HistoricalOp historicalOp = new HistoricalOp(this.mOp);
    LongSparseLongArray longSparseLongArray = this.mAccessCount;
    Objects.requireNonNull(historicalOp);
    splice(longSparseLongArray, new _$$Lambda$AppOpsManager$HistoricalOp$HUOLFYs8TiaQIOXcrq6JzjxA6gs(historicalOp), paramDouble);
    longSparseLongArray = this.mRejectCount;
    Objects.requireNonNull(historicalOp);
    splice(longSparseLongArray, new _$$Lambda$AppOpsManager$HistoricalOp$DkVcBvqB32SMHlxw0sWQPh3GL1A(historicalOp), paramDouble);
    longSparseLongArray = this.mAccessDuration;
    Objects.requireNonNull(historicalOp);
    splice(longSparseLongArray, new _$$Lambda$AppOpsManager$HistoricalOp$Vs6pDL0wjOBTquwNnreWVbPQrn4(historicalOp), paramDouble);
    return historicalOp;
  }
  
  private static void splice(LongSparseLongArray paramLongSparseLongArray, Supplier<LongSparseLongArray> paramSupplier, double paramDouble) {
    if (paramLongSparseLongArray != null) {
      int i = paramLongSparseLongArray.size();
      for (byte b = 0; b < i; b++) {
        long l1 = paramLongSparseLongArray.keyAt(b);
        long l2 = paramLongSparseLongArray.valueAt(b);
        long l3 = Math.round(l2 * paramDouble);
        if (l3 > 0L) {
          ((LongSparseLongArray)paramSupplier.get()).put(l1, l3);
          paramLongSparseLongArray.put(l1, l2 - l3);
        } 
      } 
    } 
  }
  
  public LongSparseArray<Object> collectKeys() {
    LongSparseArray longSparseArray = AppOpsManager.access$5400(this.mAccessCount, null);
    longSparseArray = AppOpsManager.access$5400(this.mRejectCount, longSparseArray);
    return AppOpsManager.access$5400(this.mAccessDuration, longSparseArray);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    return (this.mOp != ((HistoricalOp)paramObject).mOp) ? false : (!AppOpsManager.access$5300(this.mAccessCount, ((HistoricalOp)paramObject).mAccessCount) ? false : (!AppOpsManager.access$5300(this.mRejectCount, ((HistoricalOp)paramObject).mRejectCount) ? false : AppOpsManager.access$5300(this.mAccessDuration, ((HistoricalOp)paramObject).mAccessDuration)));
  }
  
  public long getAccessCount(int paramInt1, int paramInt2, int paramInt3) {
    return AppOpsManager.access$5100(this.mAccessCount, paramInt1, paramInt2, paramInt3);
  }
  
  public long getAccessDuration(int paramInt1, int paramInt2, int paramInt3) {
    return AppOpsManager.access$5100(this.mAccessDuration, paramInt1, paramInt2, paramInt3);
  }
  
  public long getBackgroundAccessCount(int paramInt) {
    return AppOpsManager.access$5100(this.mAccessCount, AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, paramInt);
  }
  
  public long getBackgroundAccessDuration(int paramInt) {
    return AppOpsManager.access$5100(this.mAccessDuration, AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, paramInt);
  }
  
  public long getBackgroundRejectCount(int paramInt) {
    return AppOpsManager.access$5100(this.mRejectCount, AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, paramInt);
  }
  
  public long getForegroundAccessCount(int paramInt) {
    return AppOpsManager.access$5100(this.mAccessCount, 100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), paramInt);
  }
  
  public long getForegroundAccessDuration(int paramInt) {
    return AppOpsManager.access$5100(this.mAccessDuration, 100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), paramInt);
  }
  
  public long getForegroundRejectCount(int paramInt) {
    return AppOpsManager.access$5100(this.mRejectCount, 100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), paramInt);
  }
  
  public int getOpCode() {
    return this.mOp;
  }
  
  public String getOpName() {
    return AppOpsManager.access$600()[this.mOp];
  }
  
  public long getRejectCount(int paramInt1, int paramInt2, int paramInt3) {
    return AppOpsManager.access$5100(this.mRejectCount, paramInt1, paramInt2, paramInt3);
  }
  
  public int hashCode() {
    return ((this.mOp * 31 + Objects.hashCode(this.mAccessCount)) * 31 + Objects.hashCode(this.mRejectCount)) * 31 + Objects.hashCode(this.mAccessDuration);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mOp);
    AppOpsManager.access$5200(this.mAccessCount, paramParcel);
    AppOpsManager.access$5200(this.mRejectCount, paramParcel);
    AppOpsManager.access$5200(this.mAccessDuration, paramParcel);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$HistoricalOp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
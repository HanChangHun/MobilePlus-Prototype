package android.app;

import android.annotation.SystemApi;
import android.content.pm.ParceledListSlice;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@SystemApi
public final class HistoricalOps implements Parcelable {
  public static final Parcelable.Creator<HistoricalOps> CREATOR = new Parcelable.Creator<HistoricalOps>() {
      public AppOpsManager.HistoricalOps createFromParcel(Parcel param2Parcel) {
        return new AppOpsManager.HistoricalOps(param2Parcel);
      }
      
      public AppOpsManager.HistoricalOps[] newArray(int param2Int) {
        return new AppOpsManager.HistoricalOps[param2Int];
      }
    };
  
  private long mBeginTimeMillis;
  
  private long mEndTimeMillis;
  
  private SparseArray<AppOpsManager.HistoricalUidOps> mHistoricalUidOps;
  
  public HistoricalOps(long paramLong1, long paramLong2) {
    boolean bool;
    if (paramLong1 <= paramLong2) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool);
    this.mBeginTimeMillis = paramLong1;
    this.mEndTimeMillis = paramLong2;
  }
  
  public HistoricalOps(HistoricalOps paramHistoricalOps) {
    boolean bool;
    long l1 = paramHistoricalOps.mBeginTimeMillis;
    this.mBeginTimeMillis = l1;
    long l2 = paramHistoricalOps.mEndTimeMillis;
    this.mEndTimeMillis = l2;
    if (l1 <= l2) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool);
    if (paramHistoricalOps.mHistoricalUidOps != null) {
      int i = paramHistoricalOps.getUidCount();
      for (byte b = 0; b < i; b++) {
        AppOpsManager.HistoricalUidOps historicalUidOps = new AppOpsManager.HistoricalUidOps(paramHistoricalOps.getUidOpsAt(b), null);
        if (this.mHistoricalUidOps == null)
          this.mHistoricalUidOps = new SparseArray(i); 
        this.mHistoricalUidOps.put(historicalUidOps.getUid(), historicalUidOps);
      } 
    } 
  }
  
  private HistoricalOps(Parcel paramParcel) {
    this.mBeginTimeMillis = paramParcel.readLong();
    this.mEndTimeMillis = paramParcel.readLong();
    int[] arrayOfInt = paramParcel.createIntArray();
    if (!ArrayUtils.isEmpty(arrayOfInt)) {
      ParceledListSlice<AppOpsManager.HistoricalUidOps> parceledListSlice = (ParceledListSlice)paramParcel.readParcelable(HistoricalOps.class.getClassLoader());
      if (parceledListSlice != null) {
        List list = parceledListSlice.getList();
      } else {
        parceledListSlice = null;
      } 
      if (parceledListSlice == null)
        return; 
      for (byte b = 0; b < arrayOfInt.length; b++) {
        if (this.mHistoricalUidOps == null)
          this.mHistoricalUidOps = new SparseArray(); 
        this.mHistoricalUidOps.put(arrayOfInt[b], parceledListSlice.get(b));
      } 
    } 
  }
  
  private AppOpsManager.HistoricalUidOps getOrCreateHistoricalUidOps(int paramInt) {
    if (this.mHistoricalUidOps == null)
      this.mHistoricalUidOps = new SparseArray(); 
    AppOpsManager.HistoricalUidOps historicalUidOps1 = (AppOpsManager.HistoricalUidOps)this.mHistoricalUidOps.get(paramInt);
    AppOpsManager.HistoricalUidOps historicalUidOps2 = historicalUidOps1;
    if (historicalUidOps1 == null) {
      historicalUidOps2 = new AppOpsManager.HistoricalUidOps(paramInt);
      this.mHistoricalUidOps.put(paramInt, historicalUidOps2);
    } 
    return historicalUidOps2;
  }
  
  public static double round(double paramDouble) {
    return (new BigDecimal(paramDouble)).setScale(0, RoundingMode.HALF_UP).doubleValue();
  }
  
  private HistoricalOps splice(double paramDouble, boolean paramBoolean) {
    long l1;
    long l2;
    if (paramBoolean) {
      l1 = this.mBeginTimeMillis;
      l2 = (long)(this.mBeginTimeMillis + getDurationMillis() * paramDouble);
      this.mBeginTimeMillis = l2;
    } else {
      l1 = (long)(this.mEndTimeMillis - getDurationMillis() * paramDouble);
      l2 = this.mEndTimeMillis;
      this.mEndTimeMillis = l1;
    } 
    HistoricalOps historicalOps = null;
    int i = getUidCount();
    byte b = 0;
    while (b < i) {
      AppOpsManager.HistoricalUidOps historicalUidOps = AppOpsManager.HistoricalUidOps.access$1100(getUidOpsAt(b), paramDouble);
      HistoricalOps historicalOps1 = historicalOps;
      if (historicalUidOps != null) {
        historicalOps1 = historicalOps;
        if (historicalOps == null)
          historicalOps1 = new HistoricalOps(l1, l2); 
        if (historicalOps1.mHistoricalUidOps == null)
          historicalOps1.mHistoricalUidOps = new SparseArray(); 
        historicalOps1.mHistoricalUidOps.put(historicalUidOps.getUid(), historicalUidOps);
      } 
      b++;
      historicalOps = historicalOps1;
    } 
    return historicalOps;
  }
  
  public void accept(AppOpsManager.HistoricalOpsVisitor paramHistoricalOpsVisitor) {
    paramHistoricalOpsVisitor.visitHistoricalOps(this);
    int i = getUidCount();
    for (byte b = 0; b < i; b++)
      AppOpsManager.HistoricalUidOps.access$1900(getUidOpsAt(b), paramHistoricalOpsVisitor); 
  }
  
  public void clearHistory(int paramInt, String paramString) {
    AppOpsManager.HistoricalUidOps historicalUidOps = getOrCreateHistoricalUidOps(paramInt);
    AppOpsManager.HistoricalUidOps.access$1800(historicalUidOps, paramString);
    if (AppOpsManager.HistoricalUidOps.access$1400(historicalUidOps))
      this.mHistoricalUidOps.remove(paramInt); 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object<AppOpsManager.HistoricalUidOps> paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    HistoricalOps historicalOps = (HistoricalOps)paramObject;
    if (this.mBeginTimeMillis != historicalOps.mBeginTimeMillis)
      return false; 
    if (this.mEndTimeMillis != historicalOps.mEndTimeMillis)
      return false; 
    paramObject = (Object<AppOpsManager.HistoricalUidOps>)this.mHistoricalUidOps;
    if (paramObject == null) {
      if (historicalOps.mHistoricalUidOps != null)
        return false; 
    } else if (!paramObject.equals(historicalOps.mHistoricalUidOps)) {
      return false;
    } 
    return true;
  }
  
  public void filter(int paramInt1, String paramString1, String paramString2, String[] paramArrayOfString, int paramInt2, long paramLong1, long paramLong2) {
    long l = getDurationMillis();
    this.mBeginTimeMillis = Math.max(this.mBeginTimeMillis, paramLong1);
    this.mEndTimeMillis = Math.min(this.mEndTimeMillis, paramLong2);
    double d = Math.min((paramLong2 - paramLong1) / l, 1.0D);
    for (int i = getUidCount() - 1; i >= 0; i--) {
      AppOpsManager.HistoricalUidOps historicalUidOps = (AppOpsManager.HistoricalUidOps)this.mHistoricalUidOps.valueAt(i);
      if ((paramInt2 & 0x1) != 0 && paramInt1 != historicalUidOps.getUid()) {
        this.mHistoricalUidOps.removeAt(i);
      } else {
        AppOpsManager.HistoricalUidOps.access$1300(historicalUidOps, paramString1, paramString2, paramArrayOfString, paramInt2, d);
        if (historicalUidOps.getPackageCount() == 0)
          this.mHistoricalUidOps.removeAt(i); 
      } 
    } 
  }
  
  public long getBeginTimeMillis() {
    return this.mBeginTimeMillis;
  }
  
  public long getDurationMillis() {
    return this.mEndTimeMillis - this.mBeginTimeMillis;
  }
  
  public long getEndTimeMillis() {
    return this.mEndTimeMillis;
  }
  
  public int getUidCount() {
    SparseArray<AppOpsManager.HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
    return (sparseArray == null) ? 0 : sparseArray.size();
  }
  
  public AppOpsManager.HistoricalUidOps getUidOps(int paramInt) {
    SparseArray<AppOpsManager.HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
    return (sparseArray == null) ? null : (AppOpsManager.HistoricalUidOps)sparseArray.get(paramInt);
  }
  
  public AppOpsManager.HistoricalUidOps getUidOpsAt(int paramInt) {
    SparseArray<AppOpsManager.HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
    if (sparseArray != null)
      return (AppOpsManager.HistoricalUidOps)sparseArray.valueAt(paramInt); 
    throw new IndexOutOfBoundsException();
  }
  
  public int hashCode() {
    long l = this.mBeginTimeMillis;
    return (int)(l ^ l >>> 32L) * 31 + this.mHistoricalUidOps.hashCode();
  }
  
  public void increaseAccessCount(int paramInt1, int paramInt2, String paramString1, String paramString2, int paramInt3, int paramInt4, long paramLong) {
    AppOpsManager.HistoricalUidOps.access$1500(getOrCreateHistoricalUidOps(paramInt2), paramInt1, paramString1, paramString2, paramInt3, paramInt4, paramLong);
  }
  
  public void increaseAccessDuration(int paramInt1, int paramInt2, String paramString1, String paramString2, int paramInt3, int paramInt4, long paramLong) {
    AppOpsManager.HistoricalUidOps.access$1700(getOrCreateHistoricalUidOps(paramInt2), paramInt1, paramString1, paramString2, paramInt3, paramInt4, paramLong);
  }
  
  public void increaseRejectCount(int paramInt1, int paramInt2, String paramString1, String paramString2, int paramInt3, int paramInt4, long paramLong) {
    AppOpsManager.HistoricalUidOps.access$1600(getOrCreateHistoricalUidOps(paramInt2), paramInt1, paramString1, paramString2, paramInt3, paramInt4, paramLong);
  }
  
  public boolean isEmpty() {
    if (getBeginTimeMillis() >= getEndTimeMillis())
      return true; 
    for (int i = getUidCount() - 1; i >= 0; i--) {
      if (!AppOpsManager.HistoricalUidOps.access$1400((AppOpsManager.HistoricalUidOps)this.mHistoricalUidOps.valueAt(i)))
        return false; 
    } 
    return true;
  }
  
  public void merge(HistoricalOps paramHistoricalOps) {
    this.mBeginTimeMillis = Math.min(this.mBeginTimeMillis, paramHistoricalOps.mBeginTimeMillis);
    this.mEndTimeMillis = Math.max(this.mEndTimeMillis, paramHistoricalOps.mEndTimeMillis);
    int i = paramHistoricalOps.getUidCount();
    for (byte b = 0; b < i; b++) {
      AppOpsManager.HistoricalUidOps historicalUidOps1 = paramHistoricalOps.getUidOpsAt(b);
      AppOpsManager.HistoricalUidOps historicalUidOps2 = getUidOps(historicalUidOps1.getUid());
      if (historicalUidOps2 != null) {
        AppOpsManager.HistoricalUidOps.access$1200(historicalUidOps2, historicalUidOps1);
      } else {
        if (this.mHistoricalUidOps == null)
          this.mHistoricalUidOps = new SparseArray(); 
        this.mHistoricalUidOps.put(historicalUidOps1.getUid(), historicalUidOps1);
      } 
    } 
  }
  
  public void offsetBeginAndEndTime(long paramLong) {
    this.mBeginTimeMillis += paramLong;
    this.mEndTimeMillis += paramLong;
  }
  
  public void setBeginAndEndTime(long paramLong1, long paramLong2) {
    this.mBeginTimeMillis = paramLong1;
    this.mEndTimeMillis = paramLong2;
  }
  
  public void setBeginTime(long paramLong) {
    this.mBeginTimeMillis = paramLong;
  }
  
  public void setEndTime(long paramLong) {
    this.mEndTimeMillis = paramLong;
  }
  
  public HistoricalOps spliceFromBeginning(double paramDouble) {
    return splice(paramDouble, true);
  }
  
  public HistoricalOps spliceFromEnd(double paramDouble) {
    return splice(paramDouble, false);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getClass().getSimpleName());
    stringBuilder.append("[from:");
    stringBuilder.append(this.mBeginTimeMillis);
    stringBuilder.append(" to:");
    stringBuilder.append(this.mEndTimeMillis);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeLong(this.mBeginTimeMillis);
    paramParcel.writeLong(this.mEndTimeMillis);
    SparseArray<AppOpsManager.HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
    if (sparseArray != null) {
      int i = sparseArray.size();
      paramParcel.writeInt(i);
      byte b;
      for (b = 0; b < i; b++)
        paramParcel.writeInt(this.mHistoricalUidOps.keyAt(b)); 
      ArrayList<AppOpsManager.HistoricalUidOps> arrayList = new ArrayList(i);
      for (b = 0; b < i; b++)
        arrayList.add((AppOpsManager.HistoricalUidOps)this.mHistoricalUidOps.valueAt(b)); 
      paramParcel.writeParcelable((Parcelable)new ParceledListSlice(arrayList), paramInt);
    } else {
      paramParcel.writeInt(-1);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$HistoricalOps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
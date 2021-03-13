package android.app;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;

@SystemApi
public final class HistoricalUidOps implements Parcelable {
  public static final Parcelable.Creator<HistoricalUidOps> CREATOR = new Parcelable.Creator<HistoricalUidOps>() {
      public AppOpsManager.HistoricalUidOps createFromParcel(Parcel param2Parcel) {
        return new AppOpsManager.HistoricalUidOps(param2Parcel);
      }
      
      public AppOpsManager.HistoricalUidOps[] newArray(int param2Int) {
        return new AppOpsManager.HistoricalUidOps[param2Int];
      }
    };
  
  private ArrayMap<String, AppOpsManager.HistoricalPackageOps> mHistoricalPackageOps;
  
  private final int mUid;
  
  public HistoricalUidOps(int paramInt) {
    this.mUid = paramInt;
  }
  
  private HistoricalUidOps(HistoricalUidOps paramHistoricalUidOps) {
    this.mUid = paramHistoricalUidOps.mUid;
    int i = paramHistoricalUidOps.getPackageCount();
    for (byte b = 0; b < i; b++) {
      AppOpsManager.HistoricalPackageOps historicalPackageOps = new AppOpsManager.HistoricalPackageOps(paramHistoricalUidOps.getPackageOpsAt(b), null);
      if (this.mHistoricalPackageOps == null)
        this.mHistoricalPackageOps = new ArrayMap(i); 
      this.mHistoricalPackageOps.put(historicalPackageOps.getPackageName(), historicalPackageOps);
    } 
  }
  
  private HistoricalUidOps(Parcel paramParcel) {
    this.mUid = paramParcel.readInt();
    this.mHistoricalPackageOps = paramParcel.createTypedArrayMap(AppOpsManager.HistoricalPackageOps.CREATOR);
  }
  
  private void accept(AppOpsManager.HistoricalOpsVisitor paramHistoricalOpsVisitor) {
    paramHistoricalOpsVisitor.visitHistoricalUidOps(this);
    int i = getPackageCount();
    for (byte b = 0; b < i; b++)
      AppOpsManager.HistoricalPackageOps.access$2900(getPackageOpsAt(b), paramHistoricalOpsVisitor); 
  }
  
  private void clearHistory(String paramString) {
    ArrayMap<String, AppOpsManager.HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
    if (arrayMap != null)
      arrayMap.remove(paramString); 
  }
  
  private void filter(String paramString1, String paramString2, String[] paramArrayOfString, int paramInt, double paramDouble) {
    for (int i = getPackageCount() - 1; i >= 0; i--) {
      AppOpsManager.HistoricalPackageOps historicalPackageOps = getPackageOpsAt(i);
      if ((paramInt & 0x2) != 0 && !paramString1.equals(historicalPackageOps.getPackageName())) {
        this.mHistoricalPackageOps.removeAt(i);
      } else {
        AppOpsManager.HistoricalPackageOps.access$2400(historicalPackageOps, paramString2, paramArrayOfString, paramInt, paramDouble);
        if (historicalPackageOps.getAttributedOpsCount() == 0)
          this.mHistoricalPackageOps.removeAt(i); 
      } 
    } 
  }
  
  private AppOpsManager.HistoricalPackageOps getOrCreateHistoricalPackageOps(String paramString) {
    if (this.mHistoricalPackageOps == null)
      this.mHistoricalPackageOps = new ArrayMap(); 
    AppOpsManager.HistoricalPackageOps historicalPackageOps1 = (AppOpsManager.HistoricalPackageOps)this.mHistoricalPackageOps.get(paramString);
    AppOpsManager.HistoricalPackageOps historicalPackageOps2 = historicalPackageOps1;
    if (historicalPackageOps1 == null) {
      historicalPackageOps2 = new AppOpsManager.HistoricalPackageOps(paramString);
      this.mHistoricalPackageOps.put(paramString, historicalPackageOps2);
    } 
    return historicalPackageOps2;
  }
  
  private void increaseAccessCount(int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3, long paramLong) {
    AppOpsManager.HistoricalPackageOps.access$2600(getOrCreateHistoricalPackageOps(paramString1), paramInt1, paramString2, paramInt2, paramInt3, paramLong);
  }
  
  private void increaseAccessDuration(int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3, long paramLong) {
    AppOpsManager.HistoricalPackageOps.access$2800(getOrCreateHistoricalPackageOps(paramString1), paramInt1, paramString2, paramInt2, paramInt3, paramLong);
  }
  
  private void increaseRejectCount(int paramInt1, String paramString1, String paramString2, int paramInt2, int paramInt3, long paramLong) {
    AppOpsManager.HistoricalPackageOps.access$2700(getOrCreateHistoricalPackageOps(paramString1), paramInt1, paramString2, paramInt2, paramInt3, paramLong);
  }
  
  private boolean isEmpty() {
    for (int i = getPackageCount() - 1; i >= 0; i--) {
      if (!AppOpsManager.HistoricalPackageOps.access$2500((AppOpsManager.HistoricalPackageOps)this.mHistoricalPackageOps.valueAt(i)))
        return false; 
    } 
    return true;
  }
  
  private void merge(HistoricalUidOps paramHistoricalUidOps) {
    int i = paramHistoricalUidOps.getPackageCount();
    for (byte b = 0; b < i; b++) {
      AppOpsManager.HistoricalPackageOps historicalPackageOps1 = paramHistoricalUidOps.getPackageOpsAt(b);
      AppOpsManager.HistoricalPackageOps historicalPackageOps2 = getPackageOps(historicalPackageOps1.getPackageName());
      if (historicalPackageOps2 != null) {
        AppOpsManager.HistoricalPackageOps.access$2300(historicalPackageOps2, historicalPackageOps1);
      } else {
        if (this.mHistoricalPackageOps == null)
          this.mHistoricalPackageOps = new ArrayMap(); 
        this.mHistoricalPackageOps.put(historicalPackageOps1.getPackageName(), historicalPackageOps1);
      } 
    } 
  }
  
  private HistoricalUidOps splice(double paramDouble) {
    HistoricalUidOps historicalUidOps = null;
    int i = getPackageCount();
    byte b = 0;
    while (b < i) {
      AppOpsManager.HistoricalPackageOps historicalPackageOps = AppOpsManager.HistoricalPackageOps.access$2200(getPackageOpsAt(b), paramDouble);
      HistoricalUidOps historicalUidOps1 = historicalUidOps;
      if (historicalPackageOps != null) {
        historicalUidOps1 = historicalUidOps;
        if (historicalUidOps == null)
          historicalUidOps1 = new HistoricalUidOps(this.mUid); 
        if (historicalUidOps1.mHistoricalPackageOps == null)
          historicalUidOps1.mHistoricalPackageOps = new ArrayMap(); 
        historicalUidOps1.mHistoricalPackageOps.put(historicalPackageOps.getPackageName(), historicalPackageOps);
      } 
      b++;
      historicalUidOps = historicalUidOps1;
    } 
    return historicalUidOps;
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
    if (this.mUid != ((HistoricalUidOps)paramObject).mUid)
      return false; 
    ArrayMap<String, AppOpsManager.HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
    if (arrayMap == null) {
      if (((HistoricalUidOps)paramObject).mHistoricalPackageOps != null)
        return false; 
    } else if (!arrayMap.equals(((HistoricalUidOps)paramObject).mHistoricalPackageOps)) {
      return false;
    } 
    return true;
  }
  
  public int getPackageCount() {
    ArrayMap<String, AppOpsManager.HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
    return (arrayMap == null) ? 0 : arrayMap.size();
  }
  
  public AppOpsManager.HistoricalPackageOps getPackageOps(String paramString) {
    ArrayMap<String, AppOpsManager.HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
    return (arrayMap == null) ? null : (AppOpsManager.HistoricalPackageOps)arrayMap.get(paramString);
  }
  
  public AppOpsManager.HistoricalPackageOps getPackageOpsAt(int paramInt) {
    ArrayMap<String, AppOpsManager.HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
    if (arrayMap != null)
      return (AppOpsManager.HistoricalPackageOps)arrayMap.valueAt(paramInt); 
    throw new IndexOutOfBoundsException();
  }
  
  public int getUid() {
    return this.mUid;
  }
  
  public int hashCode() {
    byte b;
    int i = this.mUid;
    ArrayMap<String, AppOpsManager.HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
    if (arrayMap != null) {
      b = arrayMap.hashCode();
    } else {
      b = 0;
    } 
    return i * 31 + b;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mUid);
    paramParcel.writeTypedArrayMap(this.mHistoricalPackageOps, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$HistoricalUidOps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
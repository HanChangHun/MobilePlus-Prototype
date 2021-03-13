package android.app;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import java.util.Objects;

@SystemApi
public final class HistoricalPackageOps implements Parcelable {
  public static final Parcelable.Creator<HistoricalPackageOps> CREATOR = new Parcelable.Creator<HistoricalPackageOps>() {
      public AppOpsManager.HistoricalPackageOps createFromParcel(Parcel param2Parcel) {
        return new AppOpsManager.HistoricalPackageOps(param2Parcel);
      }
      
      public AppOpsManager.HistoricalPackageOps[] newArray(int param2Int) {
        return new AppOpsManager.HistoricalPackageOps[param2Int];
      }
    };
  
  private ArrayMap<String, AppOpsManager.AttributedHistoricalOps> mAttributedHistoricalOps;
  
  private final String mPackageName;
  
  private HistoricalPackageOps(HistoricalPackageOps paramHistoricalPackageOps) {
    this.mPackageName = paramHistoricalPackageOps.mPackageName;
    int i = paramHistoricalPackageOps.getAttributedOpsCount();
    for (byte b = 0; b < i; b++) {
      AppOpsManager.AttributedHistoricalOps attributedHistoricalOps = new AppOpsManager.AttributedHistoricalOps(paramHistoricalPackageOps.getAttributedOpsAt(b), null);
      if (this.mAttributedHistoricalOps == null)
        this.mAttributedHistoricalOps = new ArrayMap(i); 
      this.mAttributedHistoricalOps.put(attributedHistoricalOps.getTag(), attributedHistoricalOps);
    } 
  }
  
  private HistoricalPackageOps(Parcel paramParcel) {
    this.mPackageName = paramParcel.readString();
    this.mAttributedHistoricalOps = paramParcel.createTypedArrayMap(AppOpsManager.AttributedHistoricalOps.CREATOR);
  }
  
  public HistoricalPackageOps(String paramString) {
    this.mPackageName = paramString;
  }
  
  private void accept(AppOpsManager.HistoricalOpsVisitor paramHistoricalOpsVisitor) {
    paramHistoricalOpsVisitor.visitHistoricalPackageOps(this);
    int i = getAttributedOpsCount();
    for (byte b = 0; b < i; b++)
      AppOpsManager.AttributedHistoricalOps.access$3500(getAttributedOpsAt(b), paramHistoricalOpsVisitor); 
  }
  
  private void filter(String paramString, String[] paramArrayOfString, int paramInt, double paramDouble) {
    for (int i = getAttributedOpsCount() - 1; i >= 0; i--) {
      AppOpsManager.AttributedHistoricalOps attributedHistoricalOps = getAttributedOpsAt(i);
      if ((paramInt & 0x4) != 0 && !Objects.equals(paramString, attributedHistoricalOps.getTag())) {
        this.mAttributedHistoricalOps.removeAt(i);
      } else {
        AppOpsManager.AttributedHistoricalOps.access$3400(attributedHistoricalOps, paramArrayOfString, paramInt, paramDouble);
        if (attributedHistoricalOps.getOpCount() == 0)
          this.mAttributedHistoricalOps.removeAt(i); 
      } 
    } 
  }
  
  private AppOpsManager.AttributedHistoricalOps getOrCreateAttributedHistoricalOps(String paramString) {
    if (this.mAttributedHistoricalOps == null)
      this.mAttributedHistoricalOps = new ArrayMap(); 
    AppOpsManager.AttributedHistoricalOps attributedHistoricalOps1 = (AppOpsManager.AttributedHistoricalOps)this.mAttributedHistoricalOps.get(paramString);
    AppOpsManager.AttributedHistoricalOps attributedHistoricalOps2 = attributedHistoricalOps1;
    if (attributedHistoricalOps1 == null) {
      attributedHistoricalOps2 = new AppOpsManager.AttributedHistoricalOps(paramString);
      this.mAttributedHistoricalOps.put(paramString, attributedHistoricalOps2);
    } 
    return attributedHistoricalOps2;
  }
  
  private void increaseAccessCount(int paramInt1, String paramString, int paramInt2, int paramInt3, long paramLong) {
    AppOpsManager.AttributedHistoricalOps.access$3700(getOrCreateAttributedHistoricalOps(paramString), paramInt1, paramInt2, paramInt3, paramLong);
  }
  
  private void increaseAccessDuration(int paramInt1, String paramString, int paramInt2, int paramInt3, long paramLong) {
    AppOpsManager.AttributedHistoricalOps.access$3900(getOrCreateAttributedHistoricalOps(paramString), paramInt1, paramInt2, paramInt3, paramLong);
  }
  
  private void increaseRejectCount(int paramInt1, String paramString, int paramInt2, int paramInt3, long paramLong) {
    AppOpsManager.AttributedHistoricalOps.access$3800(getOrCreateAttributedHistoricalOps(paramString), paramInt1, paramInt2, paramInt3, paramLong);
  }
  
  private boolean isEmpty() {
    for (int i = getAttributedOpsCount() - 1; i >= 0; i--) {
      if (!AppOpsManager.AttributedHistoricalOps.access$3600((AppOpsManager.AttributedHistoricalOps)this.mAttributedHistoricalOps.valueAt(i)))
        return false; 
    } 
    return true;
  }
  
  private void merge(HistoricalPackageOps paramHistoricalPackageOps) {
    int i = paramHistoricalPackageOps.getAttributedOpsCount();
    for (byte b = 0; b < i; b++) {
      AppOpsManager.AttributedHistoricalOps attributedHistoricalOps1 = paramHistoricalPackageOps.getAttributedOpsAt(b);
      AppOpsManager.AttributedHistoricalOps attributedHistoricalOps2 = getAttributedOps(attributedHistoricalOps1.getTag());
      if (attributedHistoricalOps2 != null) {
        AppOpsManager.AttributedHistoricalOps.access$3300(attributedHistoricalOps2, attributedHistoricalOps1);
      } else {
        if (this.mAttributedHistoricalOps == null)
          this.mAttributedHistoricalOps = new ArrayMap(); 
        this.mAttributedHistoricalOps.put(attributedHistoricalOps1.getTag(), attributedHistoricalOps1);
      } 
    } 
  }
  
  private HistoricalPackageOps splice(double paramDouble) {
    HistoricalPackageOps historicalPackageOps = null;
    int i = getAttributedOpsCount();
    byte b = 0;
    while (b < i) {
      AppOpsManager.AttributedHistoricalOps attributedHistoricalOps = AppOpsManager.AttributedHistoricalOps.access$3200(getAttributedOpsAt(b), paramDouble);
      HistoricalPackageOps historicalPackageOps1 = historicalPackageOps;
      if (attributedHistoricalOps != null) {
        historicalPackageOps1 = historicalPackageOps;
        if (historicalPackageOps == null)
          historicalPackageOps1 = new HistoricalPackageOps(this.mPackageName); 
        if (historicalPackageOps1.mAttributedHistoricalOps == null)
          historicalPackageOps1.mAttributedHistoricalOps = new ArrayMap(); 
        historicalPackageOps1.mAttributedHistoricalOps.put(attributedHistoricalOps.getTag(), attributedHistoricalOps);
      } 
      b++;
      historicalPackageOps = historicalPackageOps1;
    } 
    return historicalPackageOps;
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
    if (!this.mPackageName.equals(((HistoricalPackageOps)paramObject).mPackageName))
      return false; 
    ArrayMap<String, AppOpsManager.AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
    if (arrayMap == null) {
      if (((HistoricalPackageOps)paramObject).mAttributedHistoricalOps != null)
        return false; 
    } else if (!arrayMap.equals(((HistoricalPackageOps)paramObject).mAttributedHistoricalOps)) {
      return false;
    } 
    return true;
  }
  
  public AppOpsManager.AttributedHistoricalOps getAttributedOps(String paramString) {
    ArrayMap<String, AppOpsManager.AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
    return (arrayMap == null) ? null : (AppOpsManager.AttributedHistoricalOps)arrayMap.get(paramString);
  }
  
  public AppOpsManager.AttributedHistoricalOps getAttributedOpsAt(int paramInt) {
    ArrayMap<String, AppOpsManager.AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
    if (arrayMap != null)
      return (AppOpsManager.AttributedHistoricalOps)arrayMap.valueAt(paramInt); 
    throw new IndexOutOfBoundsException();
  }
  
  public int getAttributedOpsCount() {
    ArrayMap<String, AppOpsManager.AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
    return (arrayMap == null) ? 0 : arrayMap.size();
  }
  
  public AppOpsManager.HistoricalOp getOp(String paramString) {
    if (this.mAttributedHistoricalOps == null)
      return null; 
    AppOpsManager.HistoricalOp historicalOp = null;
    int i = getAttributedOpsCount();
    byte b = 0;
    while (b < i) {
      AppOpsManager.HistoricalOp historicalOp1 = getAttributedOpsAt(b).getOp(paramString);
      AppOpsManager.HistoricalOp historicalOp2 = historicalOp;
      if (historicalOp1 != null)
        if (historicalOp == null) {
          historicalOp2 = new AppOpsManager.HistoricalOp(historicalOp1, null);
        } else {
          AppOpsManager.HistoricalOp.access$4100(historicalOp, historicalOp1);
          historicalOp2 = historicalOp;
        }  
      b++;
      historicalOp = historicalOp2;
    } 
    return historicalOp;
  }
  
  public AppOpsManager.HistoricalOp getOpAt(int paramInt) {
    int i = 0;
    int j = getAttributedOpsCount();
    byte b = 0;
    while (b < 103) {
      int k;
      String str = AppOpsManager.opToPublicName(b);
      byte b1 = 0;
      while (true) {
        k = i;
        if (b1 < j) {
          if (getAttributedOpsAt(b1).getOp(str) != null) {
            if (i == paramInt)
              return getOp(str); 
            k = i + 1;
            break;
          } 
          b1++;
          continue;
        } 
        break;
      } 
      b++;
      i = k;
    } 
    throw new IndexOutOfBoundsException();
  }
  
  public int getOpCount() {
    int i = 0;
    int j = getAttributedOpsCount();
    byte b = 0;
    while (b < 103) {
      int k;
      String str = AppOpsManager.opToPublicName(b);
      byte b1 = 0;
      while (true) {
        k = i;
        if (b1 < j) {
          if (getAttributedOpsAt(b1).getOp(str) != null) {
            k = i + 1;
            break;
          } 
          b1++;
          continue;
        } 
        break;
      } 
      b++;
      i = k;
    } 
    return i;
  }
  
  public String getPackageName() {
    return this.mPackageName;
  }
  
  public int hashCode() {
    byte b;
    String str = this.mPackageName;
    int i = 0;
    if (str != null) {
      b = str.hashCode();
    } else {
      b = 0;
    } 
    ArrayMap<String, AppOpsManager.AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
    if (arrayMap != null)
      i = arrayMap.hashCode(); 
    return b * 31 + i;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mPackageName);
    paramParcel.writeTypedArrayMap(this.mAttributedHistoricalOps, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$HistoricalPackageOps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
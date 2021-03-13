package android.app;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import com.android.internal.util.ArrayUtils;
import java.util.Map;
import java.util.Objects;

@SystemApi
public final class AttributedHistoricalOps implements Parcelable {
  public static final Parcelable.Creator<AttributedHistoricalOps> CREATOR = new Parcelable.Creator<AttributedHistoricalOps>() {
      public AppOpsManager.AttributedHistoricalOps createFromParcel(Parcel param2Parcel) {
        return new AppOpsManager.AttributedHistoricalOps(param2Parcel);
      }
      
      public AppOpsManager.AttributedHistoricalOps[] newArray(int param2Int) {
        return new AppOpsManager.AttributedHistoricalOps[param2Int];
      }
    };
  
  private ArrayMap<String, AppOpsManager.HistoricalOp> mHistoricalOps;
  
  private final String mTag;
  
  private AttributedHistoricalOps(AttributedHistoricalOps paramAttributedHistoricalOps) {
    this.mTag = paramAttributedHistoricalOps.mTag;
    int i = paramAttributedHistoricalOps.getOpCount();
    for (byte b = 0; b < i; b++) {
      AppOpsManager.HistoricalOp historicalOp = new AppOpsManager.HistoricalOp(paramAttributedHistoricalOps.getOpAt(b), null);
      if (this.mHistoricalOps == null)
        this.mHistoricalOps = new ArrayMap(i); 
      this.mHistoricalOps.put(historicalOp.getOpName(), historicalOp);
    } 
  }
  
  AttributedHistoricalOps(Parcel paramParcel) {
    String str;
    byte b = paramParcel.readByte();
    if ((b & 0x1) == 0) {
      str = null;
    } else {
      str = paramParcel.readString();
    } 
    ArrayMap<String, AppOpsManager.HistoricalOp> arrayMap = null;
    if ((b & 0x2) != 0) {
      arrayMap = new ArrayMap();
      paramParcel.readMap((Map)arrayMap, AppOpsManager.HistoricalOp.class.getClassLoader());
    } 
    this.mTag = str;
    this.mHistoricalOps = arrayMap;
  }
  
  public AttributedHistoricalOps(String paramString) {
    this.mTag = paramString;
  }
  
  public AttributedHistoricalOps(String paramString, ArrayMap<String, AppOpsManager.HistoricalOp> paramArrayMap) {
    this.mTag = paramString;
    this.mHistoricalOps = paramArrayMap;
  }
  
  private void accept(AppOpsManager.HistoricalOpsVisitor paramHistoricalOpsVisitor) {
    paramHistoricalOpsVisitor.visitHistoricalAttributionOps(this);
    int i = getOpCount();
    for (byte b = 0; b < i; b++)
      AppOpsManager.HistoricalOp.access$4900(getOpAt(b), paramHistoricalOpsVisitor); 
  }
  
  private void filter(String[] paramArrayOfString, int paramInt, double paramDouble) {
    for (int i = getOpCount() - 1; i >= 0; i--) {
      AppOpsManager.HistoricalOp historicalOp = (AppOpsManager.HistoricalOp)this.mHistoricalOps.valueAt(i);
      if ((paramInt & 0x8) != 0 && !ArrayUtils.contains((Object[])paramArrayOfString, historicalOp.getOpName())) {
        this.mHistoricalOps.removeAt(i);
      } else {
        AppOpsManager.HistoricalOp.access$4400(historicalOp, paramDouble);
      } 
    } 
  }
  
  private AppOpsManager.HistoricalOp getOrCreateHistoricalOp(int paramInt) {
    if (this.mHistoricalOps == null)
      this.mHistoricalOps = new ArrayMap(); 
    String str = AppOpsManager.access$600()[paramInt];
    AppOpsManager.HistoricalOp historicalOp1 = (AppOpsManager.HistoricalOp)this.mHistoricalOps.get(str);
    AppOpsManager.HistoricalOp historicalOp2 = historicalOp1;
    if (historicalOp1 == null) {
      historicalOp2 = new AppOpsManager.HistoricalOp(paramInt);
      this.mHistoricalOps.put(str, historicalOp2);
    } 
    return historicalOp2;
  }
  
  private void increaseAccessCount(int paramInt1, int paramInt2, int paramInt3, long paramLong) {
    AppOpsManager.HistoricalOp.access$4600(getOrCreateHistoricalOp(paramInt1), paramInt2, paramInt3, paramLong);
  }
  
  private void increaseAccessDuration(int paramInt1, int paramInt2, int paramInt3, long paramLong) {
    AppOpsManager.HistoricalOp.access$4800(getOrCreateHistoricalOp(paramInt1), paramInt2, paramInt3, paramLong);
  }
  
  private void increaseRejectCount(int paramInt1, int paramInt2, int paramInt3, long paramLong) {
    AppOpsManager.HistoricalOp.access$4700(getOrCreateHistoricalOp(paramInt1), paramInt2, paramInt3, paramLong);
  }
  
  private boolean isEmpty() {
    for (int i = getOpCount() - 1; i >= 0; i--) {
      if (!AppOpsManager.HistoricalOp.access$4500((AppOpsManager.HistoricalOp)this.mHistoricalOps.valueAt(i)))
        return false; 
    } 
    return true;
  }
  
  private void merge(AttributedHistoricalOps paramAttributedHistoricalOps) {
    int i = paramAttributedHistoricalOps.getOpCount();
    for (byte b = 0; b < i; b++) {
      AppOpsManager.HistoricalOp historicalOp1 = paramAttributedHistoricalOps.getOpAt(b);
      AppOpsManager.HistoricalOp historicalOp2 = getOp(historicalOp1.getOpName());
      if (historicalOp2 != null) {
        AppOpsManager.HistoricalOp.access$4100(historicalOp2, historicalOp1);
      } else {
        if (this.mHistoricalOps == null)
          this.mHistoricalOps = new ArrayMap(); 
        this.mHistoricalOps.put(historicalOp1.getOpName(), historicalOp1);
      } 
    } 
  }
  
  private AttributedHistoricalOps splice(double paramDouble) {
    AttributedHistoricalOps attributedHistoricalOps = null;
    int i = getOpCount();
    byte b = 0;
    while (b < i) {
      AppOpsManager.HistoricalOp historicalOp = AppOpsManager.HistoricalOp.access$4300(getOpAt(b), paramDouble);
      AttributedHistoricalOps attributedHistoricalOps1 = attributedHistoricalOps;
      if (historicalOp != null) {
        attributedHistoricalOps1 = attributedHistoricalOps;
        if (attributedHistoricalOps == null)
          attributedHistoricalOps1 = new AttributedHistoricalOps(this.mTag, null); 
        if (attributedHistoricalOps1.mHistoricalOps == null)
          attributedHistoricalOps1.mHistoricalOps = new ArrayMap(); 
        attributedHistoricalOps1.mHistoricalOps.put(historicalOp.getOpName(), historicalOp);
      } 
      b++;
      attributedHistoricalOps = attributedHistoricalOps1;
    } 
    return attributedHistoricalOps;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = true;
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    if (!Objects.equals(this.mTag, ((AttributedHistoricalOps)paramObject).mTag) || !Objects.equals(this.mHistoricalOps, ((AttributedHistoricalOps)paramObject).mHistoricalOps))
      bool = false; 
    return bool;
  }
  
  public AppOpsManager.HistoricalOp getOp(String paramString) {
    ArrayMap<String, AppOpsManager.HistoricalOp> arrayMap = this.mHistoricalOps;
    return (arrayMap == null) ? null : (AppOpsManager.HistoricalOp)arrayMap.get(paramString);
  }
  
  public AppOpsManager.HistoricalOp getOpAt(int paramInt) {
    ArrayMap<String, AppOpsManager.HistoricalOp> arrayMap = this.mHistoricalOps;
    if (arrayMap != null)
      return (AppOpsManager.HistoricalOp)arrayMap.valueAt(paramInt); 
    throw new IndexOutOfBoundsException();
  }
  
  public int getOpCount() {
    ArrayMap<String, AppOpsManager.HistoricalOp> arrayMap = this.mHistoricalOps;
    return (arrayMap == null) ? 0 : arrayMap.size();
  }
  
  public String getTag() {
    return this.mTag;
  }
  
  public int hashCode() {
    return (1 * 31 + Objects.hashCode(this.mTag)) * 31 + Objects.hashCode(this.mHistoricalOps);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = 0;
    if (this.mTag != null)
      paramInt = (byte)(false | true); 
    int i = paramInt;
    if (this.mHistoricalOps != null) {
      paramInt = (byte)(paramInt | 0x2);
      i = paramInt;
    } 
    paramParcel.writeByte(i);
    String str = this.mTag;
    if (str != null)
      paramParcel.writeString(str); 
    ArrayMap<String, AppOpsManager.HistoricalOp> arrayMap = this.mHistoricalOps;
    if (arrayMap != null)
      paramParcel.writeMap((Map)arrayMap); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$AttributedHistoricalOps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
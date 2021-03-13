package android.app;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

public class ResultInfo implements Parcelable {
  public static final Parcelable.Creator<ResultInfo> CREATOR = new Parcelable.Creator<ResultInfo>() {
      public ResultInfo createFromParcel(Parcel param1Parcel) {
        return new ResultInfo(param1Parcel);
      }
      
      public ResultInfo[] newArray(int param1Int) {
        return new ResultInfo[param1Int];
      }
    };
  
  public final Intent mData;
  
  public final int mRequestCode;
  
  public final int mResultCode;
  
  public final String mResultWho;
  
  public ResultInfo(Parcel paramParcel) {
    this.mResultWho = paramParcel.readString();
    this.mRequestCode = paramParcel.readInt();
    this.mResultCode = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      this.mData = (Intent)Intent.CREATOR.createFromParcel(paramParcel);
    } else {
      this.mData = null;
    } 
  }
  
  public ResultInfo(String paramString, int paramInt1, int paramInt2, Intent paramIntent) {
    this.mResultWho = paramString;
    this.mRequestCode = paramInt1;
    this.mResultCode = paramInt2;
    this.mData = paramIntent;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool;
    boolean bool1 = false;
    if (paramObject == null || !(paramObject instanceof ResultInfo))
      return false; 
    paramObject = paramObject;
    Intent intent = this.mData;
    if (intent == null) {
      if (((ResultInfo)paramObject).mData == null) {
        bool = true;
      } else {
        bool = false;
      } 
    } else {
      bool = intent.filterEquals(((ResultInfo)paramObject).mData);
    } 
    boolean bool2 = bool1;
    if (bool) {
      bool2 = bool1;
      if (Objects.equals(this.mResultWho, ((ResultInfo)paramObject).mResultWho)) {
        bool2 = bool1;
        if (this.mResultCode == ((ResultInfo)paramObject).mResultCode) {
          bool2 = bool1;
          if (this.mRequestCode == ((ResultInfo)paramObject).mRequestCode)
            bool2 = true; 
        } 
      } 
    } 
    return bool2;
  }
  
  public int hashCode() {
    int i = ((17 * 31 + this.mRequestCode) * 31 + this.mResultCode) * 31 + Objects.hashCode(this.mResultWho);
    Intent intent = this.mData;
    int j = i;
    if (intent != null)
      j = i * 31 + intent.filterHashCode(); 
    return j;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ResultInfo{who=");
    stringBuilder.append(this.mResultWho);
    stringBuilder.append(", request=");
    stringBuilder.append(this.mRequestCode);
    stringBuilder.append(", result=");
    stringBuilder.append(this.mResultCode);
    stringBuilder.append(", data=");
    stringBuilder.append(this.mData);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mResultWho);
    paramParcel.writeInt(this.mRequestCode);
    paramParcel.writeInt(this.mResultCode);
    if (this.mData != null) {
      paramParcel.writeInt(1);
      this.mData.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ResultInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public final class FeatureGroupInfo implements Parcelable {
  public static final Parcelable.Creator<FeatureGroupInfo> CREATOR = new Parcelable.Creator<FeatureGroupInfo>() {
      public FeatureGroupInfo createFromParcel(Parcel param1Parcel) {
        FeatureGroupInfo featureGroupInfo = new FeatureGroupInfo();
        featureGroupInfo.features = (FeatureInfo[])param1Parcel.createTypedArray(FeatureInfo.CREATOR);
        return featureGroupInfo;
      }
      
      public FeatureGroupInfo[] newArray(int param1Int) {
        return new FeatureGroupInfo[param1Int];
      }
    };
  
  public FeatureInfo[] features;
  
  public FeatureGroupInfo() {}
  
  public FeatureGroupInfo(FeatureGroupInfo paramFeatureGroupInfo) {
    this.features = paramFeatureGroupInfo.features;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeTypedArray((Parcelable[])this.features, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/FeatureGroupInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;

public class ConfigurationInfo implements Parcelable {
  public static final Parcelable.Creator<ConfigurationInfo> CREATOR = new Parcelable.Creator<ConfigurationInfo>() {
      public ConfigurationInfo createFromParcel(Parcel param1Parcel) {
        return new ConfigurationInfo(param1Parcel);
      }
      
      public ConfigurationInfo[] newArray(int param1Int) {
        return new ConfigurationInfo[param1Int];
      }
    };
  
  public static final int GL_ES_VERSION_UNDEFINED = 0;
  
  public static final int INPUT_FEATURE_FIVE_WAY_NAV = 2;
  
  public static final int INPUT_FEATURE_HARD_KEYBOARD = 1;
  
  public int reqGlEsVersion;
  
  public int reqInputFeatures = 0;
  
  public int reqKeyboardType;
  
  public int reqNavigation;
  
  public int reqTouchScreen;
  
  public ConfigurationInfo() {}
  
  public ConfigurationInfo(ConfigurationInfo paramConfigurationInfo) {
    this.reqTouchScreen = paramConfigurationInfo.reqTouchScreen;
    this.reqKeyboardType = paramConfigurationInfo.reqKeyboardType;
    this.reqNavigation = paramConfigurationInfo.reqNavigation;
    this.reqInputFeatures = paramConfigurationInfo.reqInputFeatures;
    this.reqGlEsVersion = paramConfigurationInfo.reqGlEsVersion;
  }
  
  private ConfigurationInfo(Parcel paramParcel) {
    this.reqTouchScreen = paramParcel.readInt();
    this.reqKeyboardType = paramParcel.readInt();
    this.reqNavigation = paramParcel.readInt();
    this.reqInputFeatures = paramParcel.readInt();
    this.reqGlEsVersion = paramParcel.readInt();
  }
  
  public int describeContents() {
    return 0;
  }
  
  public String getGlEsVersion() {
    int i = this.reqGlEsVersion;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(String.valueOf((0xFFFF0000 & i) >> 16));
    stringBuilder.append(".");
    stringBuilder.append(String.valueOf(i & 0xFFFF));
    return stringBuilder.toString();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ConfigurationInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" touchscreen = ");
    stringBuilder.append(this.reqTouchScreen);
    stringBuilder.append(" inputMethod = ");
    stringBuilder.append(this.reqKeyboardType);
    stringBuilder.append(" navigation = ");
    stringBuilder.append(this.reqNavigation);
    stringBuilder.append(" reqInputFeatures = ");
    stringBuilder.append(this.reqInputFeatures);
    stringBuilder.append(" reqGlEsVersion = ");
    stringBuilder.append(this.reqGlEsVersion);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.reqTouchScreen);
    paramParcel.writeInt(this.reqKeyboardType);
    paramParcel.writeInt(this.reqNavigation);
    paramParcel.writeInt(this.reqInputFeatures);
    paramParcel.writeInt(this.reqGlEsVersion);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ConfigurationInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
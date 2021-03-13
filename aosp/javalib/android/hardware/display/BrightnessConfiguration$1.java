package android.hardware.display;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BrightnessConfiguration> {
  public BrightnessConfiguration createFromParcel(Parcel paramParcel) {
    BrightnessConfiguration.Builder builder = new BrightnessConfiguration.Builder(paramParcel.createFloatArray(), paramParcel.createFloatArray());
    int i = paramParcel.readInt();
    byte b;
    for (b = 0; b < i; b++)
      builder.addCorrectionByPackageName(paramParcel.readString(), (BrightnessCorrection)BrightnessCorrection.CREATOR.createFromParcel(paramParcel)); 
    i = paramParcel.readInt();
    for (b = 0; b < i; b++)
      builder.addCorrectionByCategory(paramParcel.readInt(), (BrightnessCorrection)BrightnessCorrection.CREATOR.createFromParcel(paramParcel)); 
    builder.setDescription(paramParcel.readString());
    builder.setShouldCollectColorSamples(paramParcel.readBoolean());
    builder.setShortTermModelTimeoutMillis(paramParcel.readLong());
    builder.setShortTermModelLowerLuxMultiplier(paramParcel.readFloat());
    builder.setShortTermModelUpperLuxMultiplier(paramParcel.readFloat());
    return builder.build();
  }
  
  public BrightnessConfiguration[] newArray(int paramInt) {
    return new BrightnessConfiguration[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/BrightnessConfiguration$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
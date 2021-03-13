package android.hardware.radio;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;

public final class Builder {
  private final Bundle mBundle = new Bundle();
  
  public Builder() {}
  
  public Builder(RadioMetadata paramRadioMetadata) {}
  
  public Builder(RadioMetadata paramRadioMetadata, int paramInt) {
    this(paramRadioMetadata);
    for (String str : this.mBundle.keySet()) {
      Object object = this.mBundle.get(str);
      if (object != null && object instanceof Bitmap) {
        object = object;
        if (object.getHeight() > paramInt || object.getWidth() > paramInt)
          putBitmap(str, scaleBitmap((Bitmap)object, paramInt)); 
      } 
    } 
  }
  
  private Bitmap scaleBitmap(Bitmap paramBitmap, int paramInt) {
    float f = paramInt;
    f = Math.min(f / paramBitmap.getWidth(), f / paramBitmap.getHeight());
    paramInt = (int)(paramBitmap.getHeight() * f);
    return Bitmap.createScaledBitmap(paramBitmap, (int)(paramBitmap.getWidth() * f), paramInt, true);
  }
  
  public RadioMetadata build() {
    return new RadioMetadata(this.mBundle, null);
  }
  
  public Builder putBitmap(String paramString, Bitmap paramBitmap) {
    if (RadioMetadata.access$300().containsKey(paramString) && ((Integer)RadioMetadata.access$300().get(paramString)).intValue() == 2) {
      this.mBundle.putParcelable(paramString, (Parcelable)paramBitmap);
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("The ");
    stringBuilder.append(paramString);
    stringBuilder.append(" key cannot be used to put a Bitmap");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Builder putClock(String paramString, long paramLong, int paramInt) {
    if (RadioMetadata.access$300().containsKey(paramString) && ((Integer)RadioMetadata.access$300().get(paramString)).intValue() == 3) {
      this.mBundle.putParcelable(paramString, new RadioMetadata.Clock(paramLong, paramInt));
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("The ");
    stringBuilder.append(paramString);
    stringBuilder.append(" key cannot be used to put a RadioMetadata.Clock.");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Builder putInt(String paramString, int paramInt) {
    RadioMetadata.access$400(this.mBundle, paramString, paramInt);
    return this;
  }
  
  public Builder putString(String paramString1, String paramString2) {
    if (RadioMetadata.access$300().containsKey(paramString1) && ((Integer)RadioMetadata.access$300().get(paramString1)).intValue() == 1) {
      this.mBundle.putString(paramString1, paramString2);
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("The ");
    stringBuilder.append(paramString1);
    stringBuilder.append(" key cannot be used to put a String");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioMetadata$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
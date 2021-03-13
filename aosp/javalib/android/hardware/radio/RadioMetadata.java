package android.hardware.radio;

import android.annotation.SystemApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

@SystemApi
public final class RadioMetadata implements Parcelable {
  public static final Parcelable.Creator<RadioMetadata> CREATOR;
  
  private static final ArrayMap<String, Integer> METADATA_KEYS_TYPE;
  
  public static final String METADATA_KEY_ALBUM = "android.hardware.radio.metadata.ALBUM";
  
  public static final String METADATA_KEY_ART = "android.hardware.radio.metadata.ART";
  
  public static final String METADATA_KEY_ARTIST = "android.hardware.radio.metadata.ARTIST";
  
  public static final String METADATA_KEY_CLOCK = "android.hardware.radio.metadata.CLOCK";
  
  public static final String METADATA_KEY_DAB_COMPONENT_NAME = "android.hardware.radio.metadata.DAB_COMPONENT_NAME";
  
  public static final String METADATA_KEY_DAB_COMPONENT_NAME_SHORT = "android.hardware.radio.metadata.DAB_COMPONENT_NAME_SHORT";
  
  public static final String METADATA_KEY_DAB_ENSEMBLE_NAME = "android.hardware.radio.metadata.DAB_ENSEMBLE_NAME";
  
  public static final String METADATA_KEY_DAB_ENSEMBLE_NAME_SHORT = "android.hardware.radio.metadata.DAB_ENSEMBLE_NAME_SHORT";
  
  public static final String METADATA_KEY_DAB_SERVICE_NAME = "android.hardware.radio.metadata.DAB_SERVICE_NAME";
  
  public static final String METADATA_KEY_DAB_SERVICE_NAME_SHORT = "android.hardware.radio.metadata.DAB_SERVICE_NAME_SHORT";
  
  public static final String METADATA_KEY_GENRE = "android.hardware.radio.metadata.GENRE";
  
  public static final String METADATA_KEY_ICON = "android.hardware.radio.metadata.ICON";
  
  public static final String METADATA_KEY_PROGRAM_NAME = "android.hardware.radio.metadata.PROGRAM_NAME";
  
  public static final String METADATA_KEY_RBDS_PTY = "android.hardware.radio.metadata.RBDS_PTY";
  
  public static final String METADATA_KEY_RDS_PI = "android.hardware.radio.metadata.RDS_PI";
  
  public static final String METADATA_KEY_RDS_PS = "android.hardware.radio.metadata.RDS_PS";
  
  public static final String METADATA_KEY_RDS_PTY = "android.hardware.radio.metadata.RDS_PTY";
  
  public static final String METADATA_KEY_RDS_RT = "android.hardware.radio.metadata.RDS_RT";
  
  public static final String METADATA_KEY_TITLE = "android.hardware.radio.metadata.TITLE";
  
  private static final int METADATA_TYPE_BITMAP = 2;
  
  private static final int METADATA_TYPE_CLOCK = 3;
  
  private static final int METADATA_TYPE_INT = 0;
  
  private static final int METADATA_TYPE_INVALID = -1;
  
  private static final int METADATA_TYPE_TEXT = 1;
  
  private static final int NATIVE_KEY_ALBUM = 7;
  
  private static final int NATIVE_KEY_ART = 10;
  
  private static final int NATIVE_KEY_ARTIST = 6;
  
  private static final int NATIVE_KEY_CLOCK = 11;
  
  private static final int NATIVE_KEY_GENRE = 8;
  
  private static final int NATIVE_KEY_ICON = 9;
  
  private static final int NATIVE_KEY_INVALID = -1;
  
  private static final SparseArray<String> NATIVE_KEY_MAPPING;
  
  private static final int NATIVE_KEY_RBDS_PTY = 3;
  
  private static final int NATIVE_KEY_RDS_PI = 0;
  
  private static final int NATIVE_KEY_RDS_PS = 1;
  
  private static final int NATIVE_KEY_RDS_PTY = 2;
  
  private static final int NATIVE_KEY_RDS_RT = 4;
  
  private static final int NATIVE_KEY_TITLE = 5;
  
  private static final String TAG = "BroadcastRadio.metadata";
  
  private final Bundle mBundle = new Bundle();
  
  private Integer mHashCode;
  
  static {
    ArrayMap<String, Integer> arrayMap1 = new ArrayMap();
    METADATA_KEYS_TYPE = arrayMap1;
    Integer integer2 = Integer.valueOf(0);
    arrayMap1.put("android.hardware.radio.metadata.RDS_PI", integer2);
    ArrayMap<String, Integer> arrayMap3 = METADATA_KEYS_TYPE;
    Integer integer1 = Integer.valueOf(1);
    arrayMap3.put("android.hardware.radio.metadata.RDS_PS", integer1);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.RDS_PTY", integer2);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.RBDS_PTY", integer2);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.RDS_RT", integer1);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.TITLE", integer1);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.ARTIST", integer1);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.ALBUM", integer1);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.GENRE", integer1);
    ArrayMap<String, Integer> arrayMap2 = METADATA_KEYS_TYPE;
    Integer integer3 = Integer.valueOf(2);
    arrayMap2.put("android.hardware.radio.metadata.ICON", integer3);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.ART", integer3);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.CLOCK", Integer.valueOf(3));
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.PROGRAM_NAME", integer1);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.DAB_ENSEMBLE_NAME", integer1);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.DAB_ENSEMBLE_NAME_SHORT", integer1);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.DAB_SERVICE_NAME", integer1);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.DAB_SERVICE_NAME_SHORT", integer1);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.DAB_COMPONENT_NAME", integer1);
    METADATA_KEYS_TYPE.put("android.hardware.radio.metadata.DAB_COMPONENT_NAME_SHORT", integer1);
    SparseArray<String> sparseArray = new SparseArray();
    NATIVE_KEY_MAPPING = sparseArray;
    sparseArray.put(0, "android.hardware.radio.metadata.RDS_PI");
    NATIVE_KEY_MAPPING.put(1, "android.hardware.radio.metadata.RDS_PS");
    NATIVE_KEY_MAPPING.put(2, "android.hardware.radio.metadata.RDS_PTY");
    NATIVE_KEY_MAPPING.put(3, "android.hardware.radio.metadata.RBDS_PTY");
    NATIVE_KEY_MAPPING.put(4, "android.hardware.radio.metadata.RDS_RT");
    NATIVE_KEY_MAPPING.put(5, "android.hardware.radio.metadata.TITLE");
    NATIVE_KEY_MAPPING.put(6, "android.hardware.radio.metadata.ARTIST");
    NATIVE_KEY_MAPPING.put(7, "android.hardware.radio.metadata.ALBUM");
    NATIVE_KEY_MAPPING.put(8, "android.hardware.radio.metadata.GENRE");
    NATIVE_KEY_MAPPING.put(9, "android.hardware.radio.metadata.ICON");
    NATIVE_KEY_MAPPING.put(10, "android.hardware.radio.metadata.ART");
    NATIVE_KEY_MAPPING.put(11, "android.hardware.radio.metadata.CLOCK");
    CREATOR = new Parcelable.Creator<RadioMetadata>() {
        public RadioMetadata createFromParcel(Parcel param1Parcel) {
          return new RadioMetadata(param1Parcel);
        }
        
        public RadioMetadata[] newArray(int param1Int) {
          return new RadioMetadata[param1Int];
        }
      };
  }
  
  RadioMetadata() {}
  
  private RadioMetadata(Bundle paramBundle) {}
  
  private RadioMetadata(Parcel paramParcel) {}
  
  public static String getKeyFromNativeKey(int paramInt) {
    return (String)NATIVE_KEY_MAPPING.get(paramInt, null);
  }
  
  private static void putInt(Bundle paramBundle, String paramString, int paramInt) {
    int i = ((Integer)METADATA_KEYS_TYPE.getOrDefault(paramString, Integer.valueOf(-1))).intValue();
    if (i == 0 || i == 2) {
      paramBundle.putInt(paramString, paramInt);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("The ");
    stringBuilder.append(paramString);
    stringBuilder.append(" key cannot be used to put an int");
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public boolean containsKey(String paramString) {
    return this.mBundle.containsKey(paramString);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof RadioMetadata))
      return false; 
    paramObject = ((RadioMetadata)paramObject).mBundle;
    if (!this.mBundle.keySet().equals(paramObject.keySet()))
      return false; 
    for (String str : this.mBundle.keySet()) {
      if (!this.mBundle.get(str).equals(paramObject.get(str)))
        return false; 
    } 
    return true;
  }
  
  @Deprecated
  public Bitmap getBitmap(String paramString) {
    Exception exception2 = null;
    try {
      Bitmap bitmap = (Bitmap)this.mBundle.getParcelable(paramString);
    } catch (Exception exception1) {
      Log.w("BroadcastRadio.metadata", "Failed to retrieve a key as Bitmap.", exception1);
      exception1 = exception2;
    } 
    return (Bitmap)exception1;
  }
  
  public int getBitmapId(String paramString) {
    return (!"android.hardware.radio.metadata.ICON".equals(paramString) && !"android.hardware.radio.metadata.ART".equals(paramString)) ? 0 : getInt(paramString);
  }
  
  public Clock getClock(String paramString) {
    Exception exception2 = null;
    try {
      Clock clock = (Clock)this.mBundle.getParcelable(paramString);
    } catch (Exception exception1) {
      Log.w("BroadcastRadio.metadata", "Failed to retrieve a key as Clock.", exception1);
      exception1 = exception2;
    } 
    return (Clock)exception1;
  }
  
  public int getInt(String paramString) {
    return this.mBundle.getInt(paramString, 0);
  }
  
  public String getString(String paramString) {
    return this.mBundle.getString(paramString);
  }
  
  public int hashCode() {
    if (this.mHashCode == null) {
      ArrayList<String> arrayList = new ArrayList(this.mBundle.keySet());
      arrayList.sort(null);
      Object[] arrayOfObject = new Object[arrayList.size() * 2];
      for (byte b = 0; b < arrayList.size(); b++) {
        arrayOfObject[b * 2] = arrayList.get(b);
        arrayOfObject[b * 2 + 1] = this.mBundle.get(arrayList.get(b));
      } 
      this.mHashCode = Integer.valueOf(Arrays.hashCode(arrayOfObject));
    } 
    return this.mHashCode.intValue();
  }
  
  public Set<String> keySet() {
    return this.mBundle.keySet();
  }
  
  int putBitmapFromNative(int paramInt, byte[] paramArrayOfbyte) {
    String str = getKeyFromNativeKey(paramInt);
    if (!METADATA_KEYS_TYPE.containsKey(str) || ((Integer)METADATA_KEYS_TYPE.get(str)).intValue() != 2)
      return -1; 
    try {
      Bitmap bitmap = BitmapFactory.decodeByteArray(paramArrayOfbyte, 0, paramArrayOfbyte.length);
      if (bitmap != null) {
        this.mBundle.putParcelable(str, (Parcelable)bitmap);
        this.mHashCode = null;
        return 0;
      } 
    } catch (Exception exception) {}
    return -1;
  }
  
  int putClockFromNative(int paramInt1, long paramLong, int paramInt2) {
    String str = getKeyFromNativeKey(paramInt1);
    if (!METADATA_KEYS_TYPE.containsKey(str) || ((Integer)METADATA_KEYS_TYPE.get(str)).intValue() != 3)
      return -1; 
    this.mBundle.putParcelable(str, new Clock(paramLong, paramInt2));
    this.mHashCode = null;
    return 0;
  }
  
  int putIntFromNative(int paramInt1, int paramInt2) {
    String str = getKeyFromNativeKey(paramInt1);
    try {
      putInt(this.mBundle, str, paramInt2);
      this.mHashCode = null;
      return 0;
    } catch (IllegalArgumentException illegalArgumentException) {
      return -1;
    } 
  }
  
  int putStringFromNative(int paramInt, String paramString) {
    String str = getKeyFromNativeKey(paramInt);
    if (!METADATA_KEYS_TYPE.containsKey(str) || ((Integer)METADATA_KEYS_TYPE.get(str)).intValue() != 1)
      return -1; 
    this.mBundle.putString(str, paramString);
    this.mHashCode = null;
    return 0;
  }
  
  public int size() {
    return this.mBundle.size();
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder("RadioMetadata[");
    boolean bool = true;
    for (String str1 : this.mBundle.keySet()) {
      if (bool) {
        bool = false;
      } else {
        stringBuilder.append(", ");
      } 
      String str2 = str1;
      if (str1.startsWith("android.hardware.radio.metadata"))
        str2 = str1.substring("android.hardware.radio.metadata".length()); 
      stringBuilder.append(str2);
      stringBuilder.append('=');
      stringBuilder.append(this.mBundle.get(str1));
    } 
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeBundle(this.mBundle);
  }
  
  public static final class Builder {
    private final Bundle mBundle = new Bundle();
    
    public Builder() {}
    
    public Builder(RadioMetadata param1RadioMetadata) {}
    
    public Builder(RadioMetadata param1RadioMetadata, int param1Int) {
      this(param1RadioMetadata);
      for (String str : this.mBundle.keySet()) {
        Object object = this.mBundle.get(str);
        if (object != null && object instanceof Bitmap) {
          object = object;
          if (object.getHeight() > param1Int || object.getWidth() > param1Int)
            putBitmap(str, scaleBitmap((Bitmap)object, param1Int)); 
        } 
      } 
    }
    
    private Bitmap scaleBitmap(Bitmap param1Bitmap, int param1Int) {
      float f = param1Int;
      f = Math.min(f / param1Bitmap.getWidth(), f / param1Bitmap.getHeight());
      param1Int = (int)(param1Bitmap.getHeight() * f);
      return Bitmap.createScaledBitmap(param1Bitmap, (int)(param1Bitmap.getWidth() * f), param1Int, true);
    }
    
    public RadioMetadata build() {
      return new RadioMetadata(this.mBundle);
    }
    
    public Builder putBitmap(String param1String, Bitmap param1Bitmap) {
      if (RadioMetadata.METADATA_KEYS_TYPE.containsKey(param1String) && ((Integer)RadioMetadata.METADATA_KEYS_TYPE.get(param1String)).intValue() == 2) {
        this.mBundle.putParcelable(param1String, (Parcelable)param1Bitmap);
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("The ");
      stringBuilder.append(param1String);
      stringBuilder.append(" key cannot be used to put a Bitmap");
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Builder putClock(String param1String, long param1Long, int param1Int) {
      if (RadioMetadata.METADATA_KEYS_TYPE.containsKey(param1String) && ((Integer)RadioMetadata.METADATA_KEYS_TYPE.get(param1String)).intValue() == 3) {
        this.mBundle.putParcelable(param1String, new RadioMetadata.Clock(param1Long, param1Int));
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("The ");
      stringBuilder.append(param1String);
      stringBuilder.append(" key cannot be used to put a RadioMetadata.Clock.");
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    public Builder putInt(String param1String, int param1Int) {
      RadioMetadata.putInt(this.mBundle, param1String, param1Int);
      return this;
    }
    
    public Builder putString(String param1String1, String param1String2) {
      if (RadioMetadata.METADATA_KEYS_TYPE.containsKey(param1String1) && ((Integer)RadioMetadata.METADATA_KEYS_TYPE.get(param1String1)).intValue() == 1) {
        this.mBundle.putString(param1String1, param1String2);
        return this;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("The ");
      stringBuilder.append(param1String1);
      stringBuilder.append(" key cannot be used to put a String");
      throw new IllegalArgumentException(stringBuilder.toString());
    }
  }
  
  @SystemApi
  public static final class Clock implements Parcelable {
    public static final Parcelable.Creator<Clock> CREATOR = new Parcelable.Creator<Clock>() {
        public RadioMetadata.Clock createFromParcel(Parcel param2Parcel) {
          return new RadioMetadata.Clock(param2Parcel);
        }
        
        public RadioMetadata.Clock[] newArray(int param2Int) {
          return new RadioMetadata.Clock[param2Int];
        }
      };
    
    private final int mTimezoneOffsetMinutes;
    
    private final long mUtcEpochSeconds;
    
    public Clock(long param1Long, int param1Int) {
      this.mUtcEpochSeconds = param1Long;
      this.mTimezoneOffsetMinutes = param1Int;
    }
    
    private Clock(Parcel param1Parcel) {
      this.mUtcEpochSeconds = param1Parcel.readLong();
      this.mTimezoneOffsetMinutes = param1Parcel.readInt();
    }
    
    public int describeContents() {
      return 0;
    }
    
    public int getTimezoneOffsetMinutes() {
      return this.mTimezoneOffsetMinutes;
    }
    
    public long getUtcEpochSeconds() {
      return this.mUtcEpochSeconds;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeLong(this.mUtcEpochSeconds);
      param1Parcel.writeInt(this.mTimezoneOffsetMinutes);
    }
  }
  
  class null implements Parcelable.Creator<Clock> {
    public RadioMetadata.Clock createFromParcel(Parcel param1Parcel) {
      return new RadioMetadata.Clock(param1Parcel);
    }
    
    public RadioMetadata.Clock[] newArray(int param1Int) {
      return new RadioMetadata.Clock[param1Int];
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
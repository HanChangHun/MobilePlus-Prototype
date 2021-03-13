package android.hardware.radio;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@SystemApi
public final class Announcement implements Parcelable {
  public static final Parcelable.Creator<Announcement> CREATOR = new Parcelable.Creator<Announcement>() {
      public Announcement createFromParcel(Parcel param1Parcel) {
        return new Announcement(param1Parcel);
      }
      
      public Announcement[] newArray(int param1Int) {
        return new Announcement[param1Int];
      }
    };
  
  public static final int TYPE_EMERGENCY = 1;
  
  public static final int TYPE_EVENT = 6;
  
  public static final int TYPE_MISC = 8;
  
  public static final int TYPE_NEWS = 5;
  
  public static final int TYPE_SPORT = 7;
  
  public static final int TYPE_TRAFFIC = 3;
  
  public static final int TYPE_WARNING = 2;
  
  public static final int TYPE_WEATHER = 4;
  
  private final ProgramSelector mSelector;
  
  private final int mType;
  
  private final Map<String, String> mVendorInfo;
  
  public Announcement(ProgramSelector paramProgramSelector, int paramInt, Map<String, String> paramMap) {
    Objects.requireNonNull(paramProgramSelector);
    this.mSelector = paramProgramSelector;
    Integer integer = Integer.valueOf(paramInt);
    Objects.requireNonNull(integer);
    this.mType = integer.intValue();
    Objects.requireNonNull(paramMap);
    this.mVendorInfo = paramMap;
  }
  
  private Announcement(Parcel paramParcel) {
    this.mSelector = (ProgramSelector)paramParcel.readTypedObject(ProgramSelector.CREATOR);
    this.mType = paramParcel.readInt();
    this.mVendorInfo = Utils.readStringMap(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public ProgramSelector getSelector() {
    return this.mSelector;
  }
  
  public int getType() {
    return this.mType;
  }
  
  public Map<String, String> getVendorInfo() {
    return this.mVendorInfo;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeTypedObject(this.mSelector, 0);
    paramParcel.writeInt(this.mType);
    Utils.writeStringMap(paramParcel, this.mVendorInfo);
  }
  
  public static interface OnListUpdatedListener {
    void onListUpdated(Collection<Announcement> param1Collection);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Type {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/Announcement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
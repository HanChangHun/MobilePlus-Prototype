package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;

public class StringParceledListSlice extends BaseParceledListSlice<String> {
  public static final Parcelable.ClassLoaderCreator<StringParceledListSlice> CREATOR = new Parcelable.ClassLoaderCreator<StringParceledListSlice>() {
      public StringParceledListSlice createFromParcel(Parcel param1Parcel) {
        return new StringParceledListSlice(param1Parcel, null);
      }
      
      public StringParceledListSlice createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
        return new StringParceledListSlice(param1Parcel, param1ClassLoader);
      }
      
      public StringParceledListSlice[] newArray(int param1Int) {
        return new StringParceledListSlice[param1Int];
      }
    };
  
  private StringParceledListSlice(Parcel paramParcel, ClassLoader paramClassLoader) {
    super(paramParcel, paramClassLoader);
  }
  
  public StringParceledListSlice(List<String> paramList) {
    super(paramList);
  }
  
  public static StringParceledListSlice emptyList() {
    return new StringParceledListSlice(Collections.emptyList());
  }
  
  public int describeContents() {
    return 0;
  }
  
  protected Parcelable.Creator<?> readParcelableCreator(Parcel paramParcel, ClassLoader paramClassLoader) {
    return Parcel.STRING_CREATOR;
  }
  
  protected void writeElement(String paramString, Parcel paramParcel, int paramInt) {
    paramParcel.writeString(paramString);
  }
  
  protected void writeParcelableCreator(String paramString, Parcel paramParcel) {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/StringParceledListSlice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
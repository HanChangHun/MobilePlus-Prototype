package android.content.pm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;

public class ParceledListSlice<T extends Parcelable> extends BaseParceledListSlice<T> {
  public static final Parcelable.ClassLoaderCreator<ParceledListSlice> CREATOR = new Parcelable.ClassLoaderCreator<ParceledListSlice>() {
      public ParceledListSlice createFromParcel(Parcel param1Parcel) {
        return new ParceledListSlice<>(param1Parcel, null);
      }
      
      public ParceledListSlice createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
        return new ParceledListSlice<>(param1Parcel, param1ClassLoader);
      }
      
      public ParceledListSlice[] newArray(int param1Int) {
        return new ParceledListSlice[param1Int];
      }
    };
  
  private ParceledListSlice(Parcel paramParcel, ClassLoader paramClassLoader) {
    super(paramParcel, paramClassLoader);
  }
  
  public ParceledListSlice(List<T> paramList) {
    super(paramList);
  }
  
  public static <T extends Parcelable> ParceledListSlice<T> emptyList() {
    return new ParceledListSlice<>(Collections.emptyList());
  }
  
  public int describeContents() {
    int i = 0;
    List<Parcelable> list = getList();
    for (byte b = 0; b < list.size(); b++)
      i |= ((Parcelable)list.get(b)).describeContents(); 
    return i;
  }
  
  protected Parcelable.Creator<?> readParcelableCreator(Parcel paramParcel, ClassLoader paramClassLoader) {
    return paramParcel.readParcelableCreator(paramClassLoader);
  }
  
  protected void writeElement(T paramT, Parcel paramParcel, int paramInt) {
    paramT.writeToParcel(paramParcel, paramInt);
  }
  
  protected void writeParcelableCreator(T paramT, Parcel paramParcel) {
    paramParcel.writeParcelableCreator((Parcelable)paramT);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ParceledListSlice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
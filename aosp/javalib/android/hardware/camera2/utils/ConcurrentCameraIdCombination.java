package android.hardware.camera2.utils;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ConcurrentCameraIdCombination implements Parcelable {
  public static final Parcelable.Creator<ConcurrentCameraIdCombination> CREATOR = new Parcelable.Creator<ConcurrentCameraIdCombination>() {
      public ConcurrentCameraIdCombination createFromParcel(Parcel param1Parcel) {
        return new ConcurrentCameraIdCombination(param1Parcel);
      }
      
      public ConcurrentCameraIdCombination[] newArray(int param1Int) {
        return new ConcurrentCameraIdCombination[param1Int];
      }
    };
  
  private Set<String> mConcurrentCameraIds = new HashSet<>();
  
  private ConcurrentCameraIdCombination(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Set<String> getConcurrentCameraIdCombination() {
    return this.mConcurrentCameraIds;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    this.mConcurrentCameraIds.clear();
    int i = paramParcel.readInt();
    if (i >= 0) {
      byte b = 0;
      while (b < i) {
        String str = paramParcel.readString();
        if (str != null) {
          this.mConcurrentCameraIds.add(str);
          b++;
          continue;
        } 
        throw new RuntimeException("Failed to read camera id from Parcel");
      } 
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("cameraCombinationSize ");
    stringBuilder.append(i);
    stringBuilder.append(" should not be negative");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mConcurrentCameraIds.size());
    Iterator<String> iterator = this.mConcurrentCameraIds.iterator();
    while (iterator.hasNext())
      paramParcel.writeString(iterator.next()); 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/ConcurrentCameraIdCombination.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
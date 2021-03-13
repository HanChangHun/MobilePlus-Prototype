package android.content.pm;

import android.os.Parcel;
import java.util.ArrayList;

public class ReadHelper extends Parcel.ReadWriteHelper {
  private final Parcel mParcel;
  
  private final ArrayList<String> mStrings = new ArrayList<>();
  
  public ReadHelper(Parcel paramParcel) {
    this.mParcel = paramParcel;
  }
  
  public String readString(Parcel paramParcel) {
    return this.mStrings.get(paramParcel.readInt());
  }
  
  public String readString16(Parcel paramParcel) {
    return readString(paramParcel);
  }
  
  public String readString8(Parcel paramParcel) {
    return readString(paramParcel);
  }
  
  public void startAndInstall() {
    this.mStrings.clear();
    int i = this.mParcel.readInt();
    int j = this.mParcel.dataPosition();
    this.mParcel.setDataPosition(i);
    this.mParcel.readStringList(this.mStrings);
    this.mParcel.setDataPosition(j);
    this.mParcel.setReadWriteHelper(this);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParserCacheHelper$ReadHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
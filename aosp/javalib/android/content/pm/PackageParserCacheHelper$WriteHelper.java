package android.content.pm;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.HashMap;

public class WriteHelper extends Parcel.ReadWriteHelper {
  private final HashMap<String, Integer> mIndexes = new HashMap<>();
  
  private final Parcel mParcel;
  
  private final int mStartPos;
  
  private final ArrayList<String> mStrings = new ArrayList<>();
  
  public WriteHelper(Parcel paramParcel) {
    this.mParcel = paramParcel;
    this.mStartPos = paramParcel.dataPosition();
    this.mParcel.writeInt(0);
    this.mParcel.setReadWriteHelper(this);
  }
  
  public void finishAndUninstall() {
    this.mParcel.setReadWriteHelper(null);
    int i = this.mParcel.dataPosition();
    this.mParcel.writeStringList(this.mStrings);
    this.mParcel.setDataPosition(this.mStartPos);
    this.mParcel.writeInt(i);
    Parcel parcel = this.mParcel;
    parcel.setDataPosition(parcel.dataSize());
  }
  
  public void writeString(Parcel paramParcel, String paramString) {
    Integer integer = this.mIndexes.get(paramString);
    if (integer != null) {
      paramParcel.writeInt(integer.intValue());
    } else {
      int i = this.mStrings.size();
      this.mIndexes.put(paramString, Integer.valueOf(i));
      this.mStrings.add(paramString);
      paramParcel.writeInt(i);
    } 
  }
  
  public void writeString16(Parcel paramParcel, String paramString) {
    writeString(paramParcel, paramString);
  }
  
  public void writeString8(Parcel paramParcel, String paramString) {
    writeString(paramParcel, paramString);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParserCacheHelper$WriteHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
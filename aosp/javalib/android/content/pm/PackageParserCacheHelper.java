package android.content.pm;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.HashMap;

public class PackageParserCacheHelper {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "PackageParserCacheHelper";
  
  public static class ReadHelper extends Parcel.ReadWriteHelper {
    private final Parcel mParcel;
    
    private final ArrayList<String> mStrings = new ArrayList<>();
    
    public ReadHelper(Parcel param1Parcel) {
      this.mParcel = param1Parcel;
    }
    
    public String readString(Parcel param1Parcel) {
      return this.mStrings.get(param1Parcel.readInt());
    }
    
    public String readString16(Parcel param1Parcel) {
      return readString(param1Parcel);
    }
    
    public String readString8(Parcel param1Parcel) {
      return readString(param1Parcel);
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
  
  public static class WriteHelper extends Parcel.ReadWriteHelper {
    private final HashMap<String, Integer> mIndexes = new HashMap<>();
    
    private final Parcel mParcel;
    
    private final int mStartPos;
    
    private final ArrayList<String> mStrings = new ArrayList<>();
    
    public WriteHelper(Parcel param1Parcel) {
      this.mParcel = param1Parcel;
      this.mStartPos = param1Parcel.dataPosition();
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
    
    public void writeString(Parcel param1Parcel, String param1String) {
      Integer integer = this.mIndexes.get(param1String);
      if (integer != null) {
        param1Parcel.writeInt(integer.intValue());
      } else {
        int i = this.mStrings.size();
        this.mIndexes.put(param1String, Integer.valueOf(i));
        this.mStrings.add(param1String);
        param1Parcel.writeInt(i);
      } 
    }
    
    public void writeString16(Parcel param1Parcel, String param1String) {
      writeString(param1Parcel, param1String);
    }
    
    public void writeString8(Parcel param1Parcel, String param1String) {
      writeString(param1Parcel, param1String);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageParserCacheHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.graphics;

import android.os.Parcel;
import android.os.Process;
import android.util.ArrayMap;
import java.util.ArrayList;

public class LeakyTypefaceStorage {
  private static final Object sLock = new Object();
  
  private static final ArrayList<Typeface> sStorage = new ArrayList<>();
  
  private static final ArrayMap<Typeface, Integer> sTypefaceMap = new ArrayMap();
  
  public static Typeface readTypefaceFromParcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    if (i != Process.myPid())
      return null; 
    synchronized (sLock) {
      return sStorage.get(j);
    } 
  }
  
  public static void writeTypefaceToParcel(Typeface paramTypeface, Parcel paramParcel) {
    paramParcel.writeInt(Process.myPid());
    synchronized (sLock) {
      int i;
      Integer integer = (Integer)sTypefaceMap.get(paramTypeface);
      if (integer != null) {
        i = integer.intValue();
      } else {
        i = sStorage.size();
        sStorage.add(paramTypeface);
        sTypefaceMap.put(paramTypeface, Integer.valueOf(i));
      } 
      paramParcel.writeInt(i);
      return;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/graphics/LeakyTypefaceStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
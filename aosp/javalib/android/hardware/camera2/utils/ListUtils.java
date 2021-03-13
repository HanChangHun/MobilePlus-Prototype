package android.hardware.camera2.utils;

import java.util.Iterator;
import java.util.List;

public class ListUtils {
  private ListUtils() {
    throw new AssertionError();
  }
  
  public static <T> boolean listContains(List<T> paramList, T paramT) {
    return (paramList == null) ? false : paramList.contains(paramT);
  }
  
  public static <T> boolean listElementsEqualTo(List<T> paramList, T paramT) {
    boolean bool1 = false;
    if (paramList == null)
      return false; 
    boolean bool2 = bool1;
    if (paramList.size() == 1) {
      bool2 = bool1;
      if (paramList.contains(paramT))
        bool2 = true; 
    } 
    return bool2;
  }
  
  public static <T> T listSelectFirstFrom(List<T> paramList, T[] paramArrayOfT) {
    if (paramList == null)
      return null; 
    int i = paramArrayOfT.length;
    for (byte b = 0; b < i; b++) {
      T t = paramArrayOfT[b];
      if (paramList.contains(t))
        return t; 
    } 
    return null;
  }
  
  public static <T> String listToString(List<T> paramList) {
    if (paramList == null)
      return null; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append('[');
    int i = paramList.size();
    byte b = 0;
    Iterator<T> iterator = paramList.iterator();
    while (iterator.hasNext()) {
      stringBuilder.append(iterator.next());
      if (b != i - 1)
        stringBuilder.append(','); 
      b++;
    } 
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/ListUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
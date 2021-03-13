package android.hardware.camera2.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ArrayUtils {
  private static final boolean DEBUG = false;
  
  private static final String TAG = "ArrayUtils";
  
  private ArrayUtils() {
    throw new AssertionError();
  }
  
  public static boolean contains(int[] paramArrayOfint, int paramInt) {
    boolean bool;
    if (getArrayIndex(paramArrayOfint, paramInt) != -1) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static <T> boolean contains(T[] paramArrayOfT, T paramT) {
    boolean bool;
    if (getArrayIndex(paramArrayOfT, paramT) != -1) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static int[] convertStringListToIntArray(List<String> paramList, String[] paramArrayOfString, int[] paramArrayOfint) {
    if (paramList == null)
      return null; 
    List<Integer> list = convertStringListToIntList(paramList, paramArrayOfString, paramArrayOfint);
    int[] arrayOfInt = new int[list.size()];
    for (byte b = 0; b < arrayOfInt.length; b++)
      arrayOfInt[b] = ((Integer)list.get(b)).intValue(); 
    return arrayOfInt;
  }
  
  public static List<Integer> convertStringListToIntList(List<String> paramList, String[] paramArrayOfString, int[] paramArrayOfint) {
    if (paramList == null)
      return null; 
    ArrayList<Integer> arrayList = new ArrayList(paramList.size());
    Iterator<String> iterator = paramList.iterator();
    while (iterator.hasNext()) {
      int i = getArrayIndex(paramArrayOfString, iterator.next());
      if (i >= 0 && i < paramArrayOfint.length)
        arrayList.add(Integer.valueOf(paramArrayOfint[i])); 
    } 
    return arrayList;
  }
  
  public static int getArrayIndex(int[] paramArrayOfint, int paramInt) {
    if (paramArrayOfint == null)
      return -1; 
    for (byte b = 0; b < paramArrayOfint.length; b++) {
      if (paramArrayOfint[b] == paramInt)
        return b; 
    } 
    return -1;
  }
  
  public static <T> int getArrayIndex(T[] paramArrayOfT, T paramT) {
    if (paramArrayOfT == null)
      return -1; 
    byte b1 = 0;
    int i = paramArrayOfT.length;
    for (byte b2 = 0; b2 < i; b2++) {
      if (Objects.equals(paramArrayOfT[b2], paramT))
        return b1; 
      b1++;
    } 
    return -1;
  }
  
  public static int[] toIntArray(List<Integer> paramList) {
    if (paramList == null)
      return null; 
    int[] arrayOfInt = new int[paramList.size()];
    byte b = 0;
    Iterator<Integer> iterator = paramList.iterator();
    while (iterator.hasNext()) {
      arrayOfInt[b] = ((Integer)iterator.next()).intValue();
      b++;
    } 
    return arrayOfInt;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/utils/ArrayUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
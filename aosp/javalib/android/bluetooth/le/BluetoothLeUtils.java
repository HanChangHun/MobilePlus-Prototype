package android.bluetooth.le;

import android.bluetooth.BluetoothAdapter;
import android.util.SparseArray;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class BluetoothLeUtils {
  static void checkAdapterStateOn(BluetoothAdapter paramBluetoothAdapter) {
    if (paramBluetoothAdapter != null && paramBluetoothAdapter.isLeEnabled())
      return; 
    throw new IllegalStateException("BT Adapter is not turned ON");
  }
  
  static boolean equals(SparseArray<byte[]> paramSparseArray1, SparseArray<byte[]> paramSparseArray2) {
    if (paramSparseArray1 == paramSparseArray2)
      return true; 
    if (paramSparseArray1 == null || paramSparseArray2 == null)
      return false; 
    if (paramSparseArray1.size() != paramSparseArray2.size())
      return false; 
    for (byte b = 0; b < paramSparseArray1.size(); b++) {
      if (paramSparseArray1.keyAt(b) != paramSparseArray2.keyAt(b) || !Arrays.equals((byte[])paramSparseArray1.valueAt(b), (byte[])paramSparseArray2.valueAt(b)))
        return false; 
    } 
    return true;
  }
  
  static <T> boolean equals(Map<T, byte[]> paramMap1, Map<T, byte[]> paramMap2) {
    if (paramMap1 == paramMap2)
      return true; 
    if (paramMap1 == null || paramMap2 == null)
      return false; 
    if (paramMap1.size() != paramMap2.size())
      return false; 
    set = paramMap1.keySet();
    if (!set.equals(paramMap2.keySet()))
      return false; 
    for (Set<T> set : set) {
      if (!Objects.deepEquals(paramMap1.get(set), paramMap2.get(set)))
        return false; 
    } 
    return true;
  }
  
  static String toString(SparseArray<byte[]> paramSparseArray) {
    if (paramSparseArray == null)
      return "null"; 
    if (paramSparseArray.size() == 0)
      return "{}"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append('{');
    for (byte b = 0; b < paramSparseArray.size(); b++) {
      stringBuilder.append(paramSparseArray.keyAt(b));
      stringBuilder.append("=");
      stringBuilder.append(Arrays.toString((byte[])paramSparseArray.valueAt(b)));
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  static <T> String toString(Map<T, byte[]> paramMap) {
    if (paramMap == null)
      return "null"; 
    if (paramMap.isEmpty())
      return "{}"; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append('{');
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    while (iterator.hasNext()) {
      Object object = ((Map.Entry)iterator.next()).getKey();
      stringBuilder.append(object);
      stringBuilder.append("=");
      stringBuilder.append(Arrays.toString(paramMap.get(object)));
      if (iterator.hasNext())
        stringBuilder.append(", "); 
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/BluetoothLeUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
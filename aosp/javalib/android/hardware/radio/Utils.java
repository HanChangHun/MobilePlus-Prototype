package android.hardware.radio;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

final class Utils {
  private static final String TAG = "BroadcastRadio.utils";
  
  static void close(ICloseHandle paramICloseHandle) {
    try {
      paramICloseHandle.close();
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } 
  }
  
  static Set<Integer> createIntSet(Parcel paramParcel) {
    int i = paramParcel.readInt();
    HashSet<Integer> hashSet = new HashSet(i);
    while (i > 0) {
      hashSet.add(Integer.valueOf(paramParcel.readInt()));
      i--;
    } 
    return hashSet;
  }
  
  static <T> Set<T> createSet(Parcel paramParcel, Parcelable.Creator<T> paramCreator) {
    int i = paramParcel.readInt();
    HashSet<Object> hashSet = new HashSet(i);
    while (i > 0) {
      hashSet.add(paramParcel.readTypedObject(paramCreator));
      i--;
    } 
    return hashSet;
  }
  
  static Map<String, Integer> readStringIntMap(Parcel paramParcel) {
    int i = paramParcel.readInt();
    HashMap<Object, Object> hashMap = new HashMap<>(i);
    while (i > 0) {
      hashMap.put(paramParcel.readString(), Integer.valueOf(paramParcel.readInt()));
      i--;
    } 
    return (Map)hashMap;
  }
  
  static Map<String, String> readStringMap(Parcel paramParcel) {
    int i = paramParcel.readInt();
    HashMap<Object, Object> hashMap = new HashMap<>(i);
    while (i > 0) {
      hashMap.put(paramParcel.readString(), paramParcel.readString());
      i--;
    } 
    return (Map)hashMap;
  }
  
  static void writeIntSet(Parcel paramParcel, Set<Integer> paramSet) {
    if (paramSet == null) {
      paramParcel.writeInt(0);
      return;
    } 
    paramParcel.writeInt(paramSet.size());
    paramSet.stream().forEach(new _$$Lambda$Utils$CpgxAbBJVMfl2IUCmgGvKDeq9_U(paramParcel));
  }
  
  static <T extends Parcelable> void writeSet(Parcel paramParcel, Set<T> paramSet) {
    if (paramSet == null) {
      paramParcel.writeInt(0);
      return;
    } 
    paramParcel.writeInt(paramSet.size());
    paramSet.stream().forEach(new _$$Lambda$Utils$Cu3trYWUZE7O75pNHuKMUbHskAY(paramParcel));
  }
  
  static void writeStringIntMap(Parcel paramParcel, Map<String, Integer> paramMap) {
    if (paramMap == null) {
      paramParcel.writeInt(0);
      return;
    } 
    paramParcel.writeInt(paramMap.size());
    for (Map.Entry<String, Integer> entry : paramMap.entrySet()) {
      paramParcel.writeString((String)entry.getKey());
      paramParcel.writeInt(((Integer)entry.getValue()).intValue());
    } 
  }
  
  static void writeStringMap(Parcel paramParcel, Map<String, String> paramMap) {
    if (paramMap == null) {
      paramParcel.writeInt(0);
      return;
    } 
    paramParcel.writeInt(paramMap.size());
    for (Map.Entry<String, String> entry : paramMap.entrySet()) {
      paramParcel.writeString((String)entry.getKey());
      paramParcel.writeString((String)entry.getValue());
    } 
  }
  
  static <T extends Parcelable> void writeTypedCollection(Parcel paramParcel, Collection<T> paramCollection) {
    ArrayList<T> arrayList = null;
    if (paramCollection != null)
      if (paramCollection instanceof ArrayList) {
        arrayList = (ArrayList)paramCollection;
      } else {
        arrayList = new ArrayList<>(paramCollection);
      }  
    paramParcel.writeTypedList(arrayList);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
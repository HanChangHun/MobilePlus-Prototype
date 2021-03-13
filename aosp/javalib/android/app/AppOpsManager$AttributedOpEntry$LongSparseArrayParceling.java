package android.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.LongSparseArray;
import com.android.internal.util.Parcelling;

class LongSparseArrayParceling implements Parcelling<LongSparseArray<AppOpsManager.NoteOpEvent>> {
  private LongSparseArrayParceling() {}
  
  public void parcel(LongSparseArray<AppOpsManager.NoteOpEvent> paramLongSparseArray, Parcel paramParcel, int paramInt) {
    if (paramLongSparseArray == null) {
      paramParcel.writeInt(-1);
      return;
    } 
    int i = paramLongSparseArray.size();
    paramParcel.writeInt(i);
    for (byte b = 0; b < i; b++) {
      paramParcel.writeLong(paramLongSparseArray.keyAt(b));
      paramParcel.writeParcelable((Parcelable)paramLongSparseArray.valueAt(b), paramInt);
    } 
  }
  
  public LongSparseArray<AppOpsManager.NoteOpEvent> unparcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    if (i == -1)
      return null; 
    LongSparseArray<AppOpsManager.NoteOpEvent> longSparseArray = new LongSparseArray(i);
    for (byte b = 0; b < i; b++)
      longSparseArray.put(paramParcel.readLong(), paramParcel.readParcelable(null)); 
    return longSparseArray;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/AppOpsManager$AttributedOpEntry$LongSparseArrayParceling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
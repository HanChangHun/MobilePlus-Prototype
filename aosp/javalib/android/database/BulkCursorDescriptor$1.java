package android.database;

import android.os.Parcel;
import android.os.Parcelable;

class null implements Parcelable.Creator<BulkCursorDescriptor> {
  public BulkCursorDescriptor createFromParcel(Parcel paramParcel) {
    BulkCursorDescriptor bulkCursorDescriptor = new BulkCursorDescriptor();
    bulkCursorDescriptor.readFromParcel(paramParcel);
    return bulkCursorDescriptor;
  }
  
  public BulkCursorDescriptor[] newArray(int paramInt) {
    return new BulkCursorDescriptor[paramInt];
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/BulkCursorDescriptor$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
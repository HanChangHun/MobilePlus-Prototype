package android.database;

import android.os.Parcel;
import android.os.Parcelable;

public final class BulkCursorDescriptor implements Parcelable {
  public static final Parcelable.Creator<BulkCursorDescriptor> CREATOR = new Parcelable.Creator<BulkCursorDescriptor>() {
      public BulkCursorDescriptor createFromParcel(Parcel param1Parcel) {
        BulkCursorDescriptor bulkCursorDescriptor = new BulkCursorDescriptor();
        bulkCursorDescriptor.readFromParcel(param1Parcel);
        return bulkCursorDescriptor;
      }
      
      public BulkCursorDescriptor[] newArray(int param1Int) {
        return new BulkCursorDescriptor[param1Int];
      }
    };
  
  public String[] columnNames;
  
  public int count;
  
  public IBulkCursor cursor;
  
  public boolean wantsAllOnMoveCalls;
  
  public CursorWindow window;
  
  public int describeContents() {
    return 0;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    boolean bool;
    this.cursor = BulkCursorNative.asInterface(paramParcel.readStrongBinder());
    this.columnNames = paramParcel.readStringArray();
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.wantsAllOnMoveCalls = bool;
    this.count = paramParcel.readInt();
    if (paramParcel.readInt() != 0)
      this.window = (CursorWindow)CursorWindow.CREATOR.createFromParcel(paramParcel); 
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeStrongBinder(this.cursor.asBinder());
    paramParcel.writeStringArray(this.columnNames);
    paramParcel.writeInt(this.wantsAllOnMoveCalls);
    paramParcel.writeInt(this.count);
    if (this.window != null) {
      paramParcel.writeInt(1);
      this.window.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/database/BulkCursorDescriptor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
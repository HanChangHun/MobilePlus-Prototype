package android.app.assist;

import android.os.BadParcelableException;
import android.os.IBinder;
import android.os.Parcel;
import android.os.PooledStringReader;

final class ParcelTransferReader {
  private final IBinder mChannel;
  
  private Parcel mCurParcel;
  
  int mNumReadViews;
  
  int mNumReadWindows;
  
  PooledStringReader mStringReader;
  
  final float[] mTmpMatrix = new float[9];
  
  private IBinder mTransferToken;
  
  ParcelTransferReader(IBinder paramIBinder) {
    this.mChannel = paramIBinder;
  }
  
  private void fetchData() {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.app.AssistStructure");
      parcel.writeStrongBinder(this.mTransferToken);
      if (this.mCurParcel != null)
        this.mCurParcel.recycle(); 
      Parcel parcel1 = Parcel.obtain();
      this.mCurParcel = parcel1;
    } finally {
      parcel.recycle();
    } 
  }
  
  void go() {
    fetchData();
    AssistStructure.access$102(AssistStructure.this, this.mCurParcel.readInt());
    AssistStructure.access$202(AssistStructure.this, this.mCurParcel.readInt());
    AssistStructure.access$302(AssistStructure.this, this.mCurParcel.readLong());
    AssistStructure.access$402(AssistStructure.this, this.mCurParcel.readLong());
    int i = this.mCurParcel.readInt();
    if (i > 0) {
      this.mStringReader = new PooledStringReader(this.mCurParcel);
      for (byte b = 0; b < i; b++)
        AssistStructure.access$500(AssistStructure.this).add(new AssistStructure.WindowNode(this)); 
    } 
    this.mCurParcel.recycle();
    this.mCurParcel = null;
  }
  
  Parcel readParcel(int paramInt1, int paramInt2) {
    paramInt2 = this.mCurParcel.readInt();
    if (paramInt2 != 0) {
      if (paramInt2 == paramInt1)
        return this.mCurParcel; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Got token ");
      stringBuilder.append(Integer.toHexString(paramInt2));
      stringBuilder.append(", expected token ");
      stringBuilder.append(Integer.toHexString(paramInt1));
      throw new BadParcelableException(stringBuilder.toString());
    } 
    IBinder iBinder = this.mCurParcel.readStrongBinder();
    this.mTransferToken = iBinder;
    if (iBinder != null) {
      fetchData();
      this.mStringReader = new PooledStringReader(this.mCurParcel);
      this.mCurParcel.readInt();
      return this.mCurParcel;
    } 
    throw new IllegalStateException("Reached end of partial data without transfer token");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistStructure$ParcelTransferReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
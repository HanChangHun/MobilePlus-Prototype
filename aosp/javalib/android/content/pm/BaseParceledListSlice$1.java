package android.content.pm;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

class null extends Binder {
  protected boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1)
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
    paramInt2 = paramParcel1.readInt();
    paramInt1 = paramInt2;
    if (BaseParceledListSlice.access$000()) {
      String str = BaseParceledListSlice.access$100();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Writing more @");
      stringBuilder.append(paramInt2);
      stringBuilder.append(" of ");
      stringBuilder.append(N);
      Log.d(str, stringBuilder.toString());
      paramInt1 = paramInt2;
    } 
    while (paramInt1 < N && paramParcel2.dataSize() < 65536) {
      paramParcel2.writeInt(1);
      paramParcel1 = BaseParceledListSlice.access$200(BaseParceledListSlice.this).get(paramInt1);
      BaseParceledListSlice.access$300(listElementClass, paramParcel1.getClass());
      BaseParceledListSlice.this.writeElement(paramParcel1, paramParcel2, callFlags);
      if (BaseParceledListSlice.access$000()) {
        String str = BaseParceledListSlice.access$100();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Wrote extra #");
        stringBuilder.append(paramInt1);
        stringBuilder.append(": ");
        stringBuilder.append(BaseParceledListSlice.access$200(BaseParceledListSlice.this).get(paramInt1));
        Log.d(str, stringBuilder.toString());
      } 
      paramInt1++;
    } 
    if (paramInt1 < N) {
      if (BaseParceledListSlice.access$000()) {
        String str = BaseParceledListSlice.access$100();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Breaking @");
        stringBuilder.append(paramInt1);
        stringBuilder.append(" of ");
        stringBuilder.append(N);
        Log.d(str, stringBuilder.toString());
      } 
      paramParcel2.writeInt(0);
    } 
    return true;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/BaseParceledListSlice$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app.assist;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

final class SendChannel extends Binder {
  volatile AssistStructure mAssistStructure;
  
  SendChannel(AssistStructure paramAssistStructure) {
    this.mAssistStructure = paramAssistStructure;
  }
  
  protected boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    IBinder iBinder;
    StringBuilder stringBuilder;
    if (paramInt1 == 2) {
      AssistStructure assistStructure = this.mAssistStructure;
      if (assistStructure == null)
        return true; 
      paramParcel1.enforceInterface("android.app.AssistStructure");
      iBinder = paramParcel1.readStrongBinder();
      if (iBinder != null) {
        if (iBinder instanceof AssistStructure.ParcelTransferWriter) {
          ((AssistStructure.ParcelTransferWriter)iBinder).writeToParcel(assistStructure, paramParcel2);
          return true;
        } 
        stringBuilder = new StringBuilder();
        stringBuilder.append("Caller supplied bad token type: ");
        stringBuilder.append(iBinder);
        Log.w("AssistStructure", stringBuilder.toString());
        return true;
      } 
      (new AssistStructure.ParcelTransferWriter(assistStructure, (Parcel)stringBuilder)).writeToParcel(assistStructure, (Parcel)stringBuilder);
      return true;
    } 
    return super.onTransact(paramInt1, (Parcel)iBinder, (Parcel)stringBuilder, paramInt2);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistStructure$SendChannel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
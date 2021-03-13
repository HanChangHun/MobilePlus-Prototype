package android.content;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IIntentSender {
  public static IIntentSender sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.IIntentSender";
  }
  
  public void send(int paramInt, Intent paramIntent, String paramString1, IBinder paramIBinder, IIntentReceiver paramIIntentReceiver, String paramString2, Bundle paramBundle) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.IIntentSender");
      try {
        parcel.writeInt(paramInt);
        if (paramIntent != null) {
          parcel.writeInt(1);
          paramIntent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          parcel.writeString(paramString1);
          try {
            IBinder iBinder;
            parcel.writeStrongBinder(paramIBinder);
            if (paramIIntentReceiver != null) {
              iBinder = paramIIntentReceiver.asBinder();
            } else {
              iBinder = null;
            } 
            parcel.writeStrongBinder(iBinder);
            try {
              parcel.writeString(paramString2);
              if (paramBundle != null) {
                parcel.writeInt(1);
                paramBundle.writeToParcel(parcel, 0);
              } else {
                parcel.writeInt(0);
              } 
              if (!this.mRemote.transact(1, parcel, null, 1) && IIntentSender.Stub.getDefaultImpl() != null) {
                IIntentSender.Stub.getDefaultImpl().send(paramInt, paramIntent, paramString1, paramIBinder, paramIIntentReceiver, paramString2, paramBundle);
                parcel.recycle();
                return;
              } 
              parcel.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel.recycle();
    throw paramIntent;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IIntentSender$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
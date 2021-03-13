package android.content;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class Proxy implements IIntentReceiver {
  public static IIntentReceiver sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.IIntentReceiver";
  }
  
  public void performReceive(Intent paramIntent, int paramInt1, String paramString, Bundle paramBundle, boolean paramBoolean1, boolean paramBoolean2, int paramInt2) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.content.IIntentReceiver");
      boolean bool = false;
      if (paramIntent != null) {
        parcel.writeInt(1);
        paramIntent.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      try {
        parcel.writeInt(paramInt1);
        try {
          parcel.writeString(paramString);
          if (paramBundle != null) {
            parcel.writeInt(1);
            paramBundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (paramBoolean1) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          parcel.writeInt(bool1);
          boolean bool1 = bool;
          if (paramBoolean2)
            bool1 = true; 
          parcel.writeInt(bool1);
          try {
            parcel.writeInt(paramInt2);
            try {
              if (!this.mRemote.transact(1, parcel, null, 1) && IIntentReceiver.Stub.getDefaultImpl() != null) {
                IIntentReceiver.Stub.getDefaultImpl().performReceive(paramIntent, paramInt1, paramString, paramBundle, paramBoolean1, paramBoolean2, paramInt2);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/IIntentReceiver$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
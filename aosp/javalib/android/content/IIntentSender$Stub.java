package android.content;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IIntentSender {
  private static final String DESCRIPTOR = "android.content.IIntentSender";
  
  static final int TRANSACTION_send = 1;
  
  public Stub() {
    attachInterface(this, "android.content.IIntentSender");
  }
  
  public static IIntentSender asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.IIntentSender");
    return (iInterface != null && iInterface instanceof IIntentSender) ? (IIntentSender)iInterface : new Proxy(paramIBinder);
  }
  
  public static IIntentSender getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "send";
  }
  
  public static boolean setDefaultImpl(IIntentSender paramIIntentSender) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIIntentSender != null) {
        Proxy.sDefaultImpl = paramIIntentSender;
        return true;
      } 
      return false;
    } 
    throw new IllegalStateException("setDefaultImpl() called twice");
  }
  
  public IBinder asBinder() {
    return (IBinder)this;
  }
  
  public String getTransactionName(int paramInt) {
    return getDefaultTransactionName(paramInt);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.content.IIntentSender");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.IIntentSender");
    paramInt1 = paramParcel1.readInt();
    if (paramParcel1.readInt() != 0) {
      Intent intent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    String str1 = paramParcel1.readString();
    IBinder iBinder = paramParcel1.readStrongBinder();
    IIntentReceiver iIntentReceiver = IIntentReceiver.Stub.asInterface(paramParcel1.readStrongBinder());
    String str2 = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    send(paramInt1, (Intent)paramParcel2, str1, iBinder, iIntentReceiver, str2, (Bundle)paramParcel1);
    return true;
  }
  
  private static class Proxy implements IIntentSender {
    public static IIntentSender sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.IIntentSender";
    }
    
    public void send(int param2Int, Intent param2Intent, String param2String1, IBinder param2IBinder, IIntentReceiver param2IIntentReceiver, String param2String2, Bundle param2Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.IIntentSender");
        try {
          parcel.writeInt(param2Int);
          if (param2Intent != null) {
            parcel.writeInt(1);
            param2Intent.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          try {
            parcel.writeString(param2String1);
            try {
              IBinder iBinder;
              parcel.writeStrongBinder(param2IBinder);
              if (param2IIntentReceiver != null) {
                iBinder = param2IIntentReceiver.asBinder();
              } else {
                iBinder = null;
              } 
              parcel.writeStrongBinder(iBinder);
              try {
                parcel.writeString(param2String2);
                if (param2Bundle != null) {
                  parcel.writeInt(1);
                  param2Bundle.writeToParcel(parcel, 0);
                } else {
                  parcel.writeInt(0);
                } 
                if (!this.mRemote.transact(1, parcel, null, 1) && IIntentSender.Stub.getDefaultImpl() != null) {
                  IIntentSender.Stub.getDefaultImpl().send(param2Int, param2Intent, param2String1, param2IBinder, param2IIntentReceiver, param2String2, param2Bundle);
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
      throw param2Intent;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IIntentSender$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
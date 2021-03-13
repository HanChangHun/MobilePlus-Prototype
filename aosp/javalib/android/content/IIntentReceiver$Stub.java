package android.content;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IIntentReceiver {
  private static final String DESCRIPTOR = "android.content.IIntentReceiver";
  
  static final int TRANSACTION_performReceive = 1;
  
  public Stub() {
    attachInterface(this, "android.content.IIntentReceiver");
  }
  
  public static IIntentReceiver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.IIntentReceiver");
    return (iInterface != null && iInterface instanceof IIntentReceiver) ? (IIntentReceiver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IIntentReceiver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "performReceive";
  }
  
  public static boolean setDefaultImpl(IIntentReceiver paramIIntentReceiver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIIntentReceiver != null) {
        Proxy.sDefaultImpl = paramIIntentReceiver;
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
    Bundle bundle;
    boolean bool1;
    boolean bool2;
    if (paramInt1 != 1) {
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.content.IIntentReceiver");
      return true;
    } 
    paramParcel1.enforceInterface("android.content.IIntentReceiver");
    if (paramParcel1.readInt() != 0) {
      Intent intent = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel2 = null;
    } 
    paramInt1 = paramParcel1.readInt();
    String str = paramParcel1.readString();
    if (paramParcel1.readInt() != 0) {
      bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      bundle = null;
    } 
    if (paramParcel1.readInt() != 0) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (paramParcel1.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    performReceive((Intent)paramParcel2, paramInt1, str, bundle, bool1, bool2, paramParcel1.readInt());
    return true;
  }
  
  private static class Proxy implements IIntentReceiver {
    public static IIntentReceiver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.IIntentReceiver";
    }
    
    public void performReceive(Intent param2Intent, int param2Int1, String param2String, Bundle param2Bundle, boolean param2Boolean1, boolean param2Boolean2, int param2Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.IIntentReceiver");
        boolean bool = false;
        if (param2Intent != null) {
          parcel.writeInt(1);
          param2Intent.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        try {
          parcel.writeInt(param2Int1);
          try {
            parcel.writeString(param2String);
            if (param2Bundle != null) {
              parcel.writeInt(1);
              param2Bundle.writeToParcel(parcel, 0);
            } else {
              parcel.writeInt(0);
            } 
            if (param2Boolean1) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            parcel.writeInt(bool1);
            boolean bool1 = bool;
            if (param2Boolean2)
              bool1 = true; 
            parcel.writeInt(bool1);
            try {
              parcel.writeInt(param2Int2);
              try {
                if (!this.mRemote.transact(1, parcel, null, 1) && IIntentReceiver.Stub.getDefaultImpl() != null) {
                  IIntentReceiver.Stub.getDefaultImpl().performReceive(param2Intent, param2Int1, param2String, param2Bundle, param2Boolean1, param2Boolean2, param2Int2);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/IIntentReceiver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
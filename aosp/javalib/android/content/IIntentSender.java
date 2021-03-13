package android.content;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IIntentSender extends IInterface {
  void send(int paramInt, Intent paramIntent, String paramString1, IBinder paramIBinder, IIntentReceiver paramIIntentReceiver, String paramString2, Bundle paramBundle) throws RemoteException;
  
  public static class Default implements IIntentSender {
    public IBinder asBinder() {
      return null;
    }
    
    public void send(int param1Int, Intent param1Intent, String param1String1, IBinder param1IBinder, IIntentReceiver param1IIntentReceiver, String param1String2, Bundle param1Bundle) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IIntentSender {
    private static final String DESCRIPTOR = "android.content.IIntentSender";
    
    static final int TRANSACTION_send = 1;
    
    public Stub() {
      attachInterface(this, "android.content.IIntentSender");
    }
    
    public static IIntentSender asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.IIntentSender");
      return (iInterface != null && iInterface instanceof IIntentSender) ? (IIntentSender)iInterface : new Proxy(param1IBinder);
    }
    
    public static IIntentSender getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "send";
    }
    
    public static boolean setDefaultImpl(IIntentSender param1IIntentSender) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IIntentSender != null) {
          Proxy.sDefaultImpl = param1IIntentSender;
          return true;
        } 
        return false;
      } 
      throw new IllegalStateException("setDefaultImpl() called twice");
    }
    
    public IBinder asBinder() {
      return (IBinder)this;
    }
    
    public String getTransactionName(int param1Int) {
      return getDefaultTransactionName(param1Int);
    }
    
    public boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      if (param1Int1 != 1) {
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.content.IIntentSender");
        return true;
      } 
      param1Parcel1.enforceInterface("android.content.IIntentSender");
      param1Int1 = param1Parcel1.readInt();
      if (param1Parcel1.readInt() != 0) {
        Intent intent = (Intent)Intent.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel2 = null;
      } 
      String str1 = param1Parcel1.readString();
      IBinder iBinder = param1Parcel1.readStrongBinder();
      IIntentReceiver iIntentReceiver = IIntentReceiver.Stub.asInterface(param1Parcel1.readStrongBinder());
      String str2 = param1Parcel1.readString();
      if (param1Parcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      send(param1Int1, (Intent)param1Parcel2, str1, iBinder, iIntentReceiver, str2, (Bundle)param1Parcel1);
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
  
  private static class Proxy implements IIntentSender {
    public static IIntentSender sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.content.IIntentSender";
    }
    
    public void send(int param1Int, Intent param1Intent, String param1String1, IBinder param1IBinder, IIntentReceiver param1IIntentReceiver, String param1String2, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.content.IIntentSender");
        try {
          parcel.writeInt(param1Int);
          if (param1Intent != null) {
            parcel.writeInt(1);
            param1Intent.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          try {
            parcel.writeString(param1String1);
            try {
              IBinder iBinder;
              parcel.writeStrongBinder(param1IBinder);
              if (param1IIntentReceiver != null) {
                iBinder = param1IIntentReceiver.asBinder();
              } else {
                iBinder = null;
              } 
              parcel.writeStrongBinder(iBinder);
              try {
                parcel.writeString(param1String2);
                if (param1Bundle != null) {
                  parcel.writeInt(1);
                  param1Bundle.writeToParcel(parcel, 0);
                } else {
                  parcel.writeInt(0);
                } 
                if (!this.mRemote.transact(1, parcel, null, 1) && IIntentSender.Stub.getDefaultImpl() != null) {
                  IIntentSender.Stub.getDefaultImpl().send(param1Int, param1Intent, param1String1, param1IBinder, param1IIntentReceiver, param1String2, param1Bundle);
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
      throw param1Intent;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/IIntentSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
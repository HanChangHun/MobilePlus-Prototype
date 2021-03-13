package android.app.timezone;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface IRulesManager extends IInterface {
  RulesState getRulesState() throws RemoteException;
  
  int requestInstall(ParcelFileDescriptor paramParcelFileDescriptor, byte[] paramArrayOfbyte, ICallback paramICallback) throws RemoteException;
  
  void requestNothing(byte[] paramArrayOfbyte, boolean paramBoolean) throws RemoteException;
  
  int requestUninstall(byte[] paramArrayOfbyte, ICallback paramICallback) throws RemoteException;
  
  public static class Default implements IRulesManager {
    public IBinder asBinder() {
      return null;
    }
    
    public RulesState getRulesState() throws RemoteException {
      return null;
    }
    
    public int requestInstall(ParcelFileDescriptor param1ParcelFileDescriptor, byte[] param1ArrayOfbyte, ICallback param1ICallback) throws RemoteException {
      return 0;
    }
    
    public void requestNothing(byte[] param1ArrayOfbyte, boolean param1Boolean) throws RemoteException {}
    
    public int requestUninstall(byte[] param1ArrayOfbyte, ICallback param1ICallback) throws RemoteException {
      return 0;
    }
  }
  
  public static abstract class Stub extends Binder implements IRulesManager {
    private static final String DESCRIPTOR = "android.app.timezone.IRulesManager";
    
    static final int TRANSACTION_getRulesState = 1;
    
    static final int TRANSACTION_requestInstall = 2;
    
    static final int TRANSACTION_requestNothing = 4;
    
    static final int TRANSACTION_requestUninstall = 3;
    
    public Stub() {
      attachInterface(this, "android.app.timezone.IRulesManager");
    }
    
    public static IRulesManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.timezone.IRulesManager");
      return (iInterface != null && iInterface instanceof IRulesManager) ? (IRulesManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IRulesManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? null : "requestNothing") : "requestUninstall") : "requestInstall") : "getRulesState";
    }
    
    public static boolean setDefaultImpl(IRulesManager param1IRulesManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IRulesManager != null) {
          Proxy.sDefaultImpl = param1IRulesManager;
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
      boolean bool = false;
      if (param1Int1 != 1) {
        ParcelFileDescriptor parcelFileDescriptor;
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 4) {
              if (param1Int1 != 1598968902)
                return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
              param1Parcel2.writeString("android.app.timezone.IRulesManager");
              return true;
            } 
            param1Parcel1.enforceInterface("android.app.timezone.IRulesManager");
            parcelFileDescriptor = (ParcelFileDescriptor)param1Parcel1.createByteArray();
            if (param1Parcel1.readInt() != 0)
              bool = true; 
            requestNothing((byte[])parcelFileDescriptor, bool);
            param1Parcel2.writeNoException();
            return true;
          } 
          param1Parcel1.enforceInterface("android.app.timezone.IRulesManager");
          param1Int1 = requestUninstall(param1Parcel1.createByteArray(), ICallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
          param1Parcel2.writeNoException();
          param1Parcel2.writeInt(param1Int1);
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.timezone.IRulesManager");
        if (param1Parcel1.readInt() != 0) {
          parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(param1Parcel1);
        } else {
          parcelFileDescriptor = null;
        } 
        param1Int1 = requestInstall(parcelFileDescriptor, param1Parcel1.createByteArray(), ICallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
        param1Parcel2.writeNoException();
        param1Parcel2.writeInt(param1Int1);
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.timezone.IRulesManager");
      RulesState rulesState = getRulesState();
      param1Parcel2.writeNoException();
      if (rulesState != null) {
        param1Parcel2.writeInt(1);
        rulesState.writeToParcel(param1Parcel2, 1);
      } else {
        param1Parcel2.writeInt(0);
      } 
      return true;
    }
    
    private static class Proxy implements IRulesManager {
      public static IRulesManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.timezone.IRulesManager";
      }
      
      public RulesState getRulesState() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          RulesState rulesState;
          parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null) {
            rulesState = IRulesManager.Stub.getDefaultImpl().getRulesState();
            return rulesState;
          } 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            rulesState = (RulesState)RulesState.CREATOR.createFromParcel(parcel2);
          } else {
            rulesState = null;
          } 
          return rulesState;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int requestInstall(ParcelFileDescriptor param2ParcelFileDescriptor, byte[] param2ArrayOfbyte, ICallback param2ICallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
          if (param2ParcelFileDescriptor != null) {
            parcel1.writeInt(1);
            param2ParcelFileDescriptor.writeToParcel(parcel1, 0);
          } else {
            parcel1.writeInt(0);
          } 
          parcel1.writeByteArray(param2ArrayOfbyte);
          if (param2ICallback != null) {
            iBinder = param2ICallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null)
            return IRulesManager.Stub.getDefaultImpl().requestInstall(param2ParcelFileDescriptor, param2ArrayOfbyte, param2ICallback); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void requestNothing(byte[] param2ArrayOfbyte, boolean param2Boolean) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          boolean bool;
          parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
          parcel1.writeByteArray(param2ArrayOfbyte);
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null) {
            IRulesManager.Stub.getDefaultImpl().requestNothing(param2ArrayOfbyte, param2Boolean);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public int requestUninstall(byte[] param2ArrayOfbyte, ICallback param2ICallback) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
          parcel1.writeByteArray(param2ArrayOfbyte);
          if (param2ICallback != null) {
            iBinder = param2ICallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel1.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null)
            return IRulesManager.Stub.getDefaultImpl().requestUninstall(param2ArrayOfbyte, param2ICallback); 
          parcel2.readException();
          return parcel2.readInt();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IRulesManager {
    public static IRulesManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.timezone.IRulesManager";
    }
    
    public RulesState getRulesState() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        RulesState rulesState;
        parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null) {
          rulesState = IRulesManager.Stub.getDefaultImpl().getRulesState();
          return rulesState;
        } 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          rulesState = (RulesState)RulesState.CREATOR.createFromParcel(parcel2);
        } else {
          rulesState = null;
        } 
        return rulesState;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int requestInstall(ParcelFileDescriptor param1ParcelFileDescriptor, byte[] param1ArrayOfbyte, ICallback param1ICallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
        if (param1ParcelFileDescriptor != null) {
          parcel1.writeInt(1);
          param1ParcelFileDescriptor.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (param1ICallback != null) {
          iBinder = param1ICallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null)
          return IRulesManager.Stub.getDefaultImpl().requestInstall(param1ParcelFileDescriptor, param1ArrayOfbyte, param1ICallback); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void requestNothing(byte[] param1ArrayOfbyte, boolean param1Boolean) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        boolean bool;
        parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel1.writeInt(bool);
        if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null) {
          IRulesManager.Stub.getDefaultImpl().requestNothing(param1ArrayOfbyte, param1Boolean);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public int requestUninstall(byte[] param1ArrayOfbyte, ICallback param1ICallback) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel1.writeInterfaceToken("android.app.timezone.IRulesManager");
        parcel1.writeByteArray(param1ArrayOfbyte);
        if (param1ICallback != null) {
          iBinder = param1ICallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IRulesManager.Stub.getDefaultImpl() != null)
          return IRulesManager.Stub.getDefaultImpl().requestUninstall(param1ArrayOfbyte, param1ICallback); 
        parcel2.readException();
        return parcel2.readInt();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/timezone/IRulesManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
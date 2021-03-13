package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IConsumerIrService extends IInterface {
  int[] getCarrierFrequencies() throws RemoteException;
  
  boolean hasIrEmitter() throws RemoteException;
  
  void transmit(String paramString, int paramInt, int[] paramArrayOfint) throws RemoteException;
  
  public static class Default implements IConsumerIrService {
    public IBinder asBinder() {
      return null;
    }
    
    public int[] getCarrierFrequencies() throws RemoteException {
      return null;
    }
    
    public boolean hasIrEmitter() throws RemoteException {
      return false;
    }
    
    public void transmit(String param1String, int param1Int, int[] param1ArrayOfint) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IConsumerIrService {
    private static final String DESCRIPTOR = "android.hardware.IConsumerIrService";
    
    static final int TRANSACTION_getCarrierFrequencies = 3;
    
    static final int TRANSACTION_hasIrEmitter = 1;
    
    static final int TRANSACTION_transmit = 2;
    
    public Stub() {
      attachInterface(this, "android.hardware.IConsumerIrService");
    }
    
    public static IConsumerIrService asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.IConsumerIrService");
      return (iInterface != null && iInterface instanceof IConsumerIrService) ? (IConsumerIrService)iInterface : new Proxy(param1IBinder);
    }
    
    public static IConsumerIrService getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? null : "getCarrierFrequencies") : "transmit") : "hasIrEmitter";
    }
    
    public static boolean setDefaultImpl(IConsumerIrService param1IConsumerIrService) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IConsumerIrService != null) {
          Proxy.sDefaultImpl = param1IConsumerIrService;
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
      int[] arrayOfInt;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            if (param1Int1 != 1598968902)
              return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
            param1Parcel2.writeString("android.hardware.IConsumerIrService");
            return true;
          } 
          param1Parcel1.enforceInterface("android.hardware.IConsumerIrService");
          arrayOfInt = getCarrierFrequencies();
          param1Parcel2.writeNoException();
          param1Parcel2.writeIntArray(arrayOfInt);
          return true;
        } 
        arrayOfInt.enforceInterface("android.hardware.IConsumerIrService");
        transmit(arrayOfInt.readString(), arrayOfInt.readInt(), arrayOfInt.createIntArray());
        param1Parcel2.writeNoException();
        return true;
      } 
      arrayOfInt.enforceInterface("android.hardware.IConsumerIrService");
      boolean bool = hasIrEmitter();
      param1Parcel2.writeNoException();
      param1Parcel2.writeInt(bool);
      return true;
    }
    
    private static class Proxy implements IConsumerIrService {
      public static IConsumerIrService sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public int[] getCarrierFrequencies() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.IConsumerIrService");
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IConsumerIrService.Stub.getDefaultImpl() != null)
            return IConsumerIrService.Stub.getDefaultImpl().getCarrierFrequencies(); 
          parcel2.readException();
          return parcel2.createIntArray();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.IConsumerIrService";
      }
      
      public boolean hasIrEmitter() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.IConsumerIrService");
          IBinder iBinder = this.mRemote;
          boolean bool = false;
          if (!iBinder.transact(1, parcel1, parcel2, 0) && IConsumerIrService.Stub.getDefaultImpl() != null) {
            bool = IConsumerIrService.Stub.getDefaultImpl().hasIrEmitter();
            return bool;
          } 
          parcel2.readException();
          int i = parcel2.readInt();
          if (i != 0)
            bool = true; 
          return bool;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public void transmit(String param2String, int param2Int, int[] param2ArrayOfint) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.IConsumerIrService");
          parcel1.writeString(param2String);
          parcel1.writeInt(param2Int);
          parcel1.writeIntArray(param2ArrayOfint);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IConsumerIrService.Stub.getDefaultImpl() != null) {
            IConsumerIrService.Stub.getDefaultImpl().transmit(param2String, param2Int, param2ArrayOfint);
            return;
          } 
          parcel2.readException();
          return;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IConsumerIrService {
    public static IConsumerIrService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public int[] getCarrierFrequencies() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.IConsumerIrService");
        if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IConsumerIrService.Stub.getDefaultImpl() != null)
          return IConsumerIrService.Stub.getDefaultImpl().getCarrierFrequencies(); 
        parcel2.readException();
        return parcel2.createIntArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.IConsumerIrService";
    }
    
    public boolean hasIrEmitter() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.IConsumerIrService");
        IBinder iBinder = this.mRemote;
        boolean bool = false;
        if (!iBinder.transact(1, parcel1, parcel2, 0) && IConsumerIrService.Stub.getDefaultImpl() != null) {
          bool = IConsumerIrService.Stub.getDefaultImpl().hasIrEmitter();
          return bool;
        } 
        parcel2.readException();
        int i = parcel2.readInt();
        if (i != 0)
          bool = true; 
        return bool;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public void transmit(String param1String, int param1Int, int[] param1ArrayOfint) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.IConsumerIrService");
        parcel1.writeString(param1String);
        parcel1.writeInt(param1Int);
        parcel1.writeIntArray(param1ArrayOfint);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IConsumerIrService.Stub.getDefaultImpl() != null) {
          IConsumerIrService.Stub.getDefaultImpl().transmit(param1String, param1Int, param1ArrayOfint);
          return;
        } 
        parcel2.readException();
        return;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/IConsumerIrService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
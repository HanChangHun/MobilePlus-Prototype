package android.hardware;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface ISerialManager extends IInterface {
  String[] getSerialPorts() throws RemoteException;
  
  ParcelFileDescriptor openSerialPort(String paramString) throws RemoteException;
  
  public static class Default implements ISerialManager {
    public IBinder asBinder() {
      return null;
    }
    
    public String[] getSerialPorts() throws RemoteException {
      return null;
    }
    
    public ParcelFileDescriptor openSerialPort(String param1String) throws RemoteException {
      return null;
    }
  }
  
  public static abstract class Stub extends Binder implements ISerialManager {
    private static final String DESCRIPTOR = "android.hardware.ISerialManager";
    
    static final int TRANSACTION_getSerialPorts = 1;
    
    static final int TRANSACTION_openSerialPort = 2;
    
    public Stub() {
      attachInterface(this, "android.hardware.ISerialManager");
    }
    
    public static ISerialManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.ISerialManager");
      return (iInterface != null && iInterface instanceof ISerialManager) ? (ISerialManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static ISerialManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "openSerialPort") : "getSerialPorts";
    }
    
    public static boolean setDefaultImpl(ISerialManager param1ISerialManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1ISerialManager != null) {
          Proxy.sDefaultImpl = param1ISerialManager;
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
      ParcelFileDescriptor parcelFileDescriptor;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.hardware.ISerialManager");
          return true;
        } 
        param1Parcel1.enforceInterface("android.hardware.ISerialManager");
        parcelFileDescriptor = openSerialPort(param1Parcel1.readString());
        param1Parcel2.writeNoException();
        if (parcelFileDescriptor != null) {
          param1Parcel2.writeInt(1);
          parcelFileDescriptor.writeToParcel(param1Parcel2, 1);
        } else {
          param1Parcel2.writeInt(0);
        } 
        return true;
      } 
      parcelFileDescriptor.enforceInterface("android.hardware.ISerialManager");
      String[] arrayOfString = getSerialPorts();
      param1Parcel2.writeNoException();
      param1Parcel2.writeStringArray(arrayOfString);
      return true;
    }
    
    private static class Proxy implements ISerialManager {
      public static ISerialManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.hardware.ISerialManager";
      }
      
      public String[] getSerialPorts() throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.ISerialManager");
          if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISerialManager.Stub.getDefaultImpl() != null)
            return ISerialManager.Stub.getDefaultImpl().getSerialPorts(); 
          parcel2.readException();
          return parcel2.createStringArray();
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
      
      public ParcelFileDescriptor openSerialPort(String param2String) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        Parcel parcel2 = Parcel.obtain();
        try {
          parcel1.writeInterfaceToken("android.hardware.ISerialManager");
          parcel1.writeString(param2String);
          if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISerialManager.Stub.getDefaultImpl() != null)
            return ISerialManager.Stub.getDefaultImpl().openSerialPort(param2String); 
          parcel2.readException();
          if (parcel2.readInt() != 0) {
            ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
          } else {
            param2String = null;
          } 
          return (ParcelFileDescriptor)param2String;
        } finally {
          parcel2.recycle();
          parcel1.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements ISerialManager {
    public static ISerialManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.ISerialManager";
    }
    
    public String[] getSerialPorts() throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ISerialManager");
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && ISerialManager.Stub.getDefaultImpl() != null)
          return ISerialManager.Stub.getDefaultImpl().getSerialPorts(); 
        parcel2.readException();
        return parcel2.createStringArray();
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
    
    public ParcelFileDescriptor openSerialPort(String param1String) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.ISerialManager");
        parcel1.writeString(param1String);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && ISerialManager.Stub.getDefaultImpl() != null)
          return ISerialManager.Stub.getDefaultImpl().openSerialPort(param1String); 
        parcel2.readException();
        if (parcel2.readInt() != 0) {
          ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(parcel2);
        } else {
          param1String = null;
        } 
        return (ParcelFileDescriptor)param1String;
      } finally {
        parcel2.recycle();
        parcel1.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/ISerialManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.bluetooth.le;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IScannerCallback extends IInterface {
  void onBatchScanResults(List<ScanResult> paramList) throws RemoteException;
  
  void onFoundOrLost(boolean paramBoolean, ScanResult paramScanResult) throws RemoteException;
  
  void onScanManagerErrorCallback(int paramInt) throws RemoteException;
  
  void onScanResult(ScanResult paramScanResult) throws RemoteException;
  
  void onScannerRegistered(int paramInt1, int paramInt2) throws RemoteException;
  
  public static class Default implements IScannerCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onBatchScanResults(List<ScanResult> param1List) throws RemoteException {}
    
    public void onFoundOrLost(boolean param1Boolean, ScanResult param1ScanResult) throws RemoteException {}
    
    public void onScanManagerErrorCallback(int param1Int) throws RemoteException {}
    
    public void onScanResult(ScanResult param1ScanResult) throws RemoteException {}
    
    public void onScannerRegistered(int param1Int1, int param1Int2) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IScannerCallback {
    private static final String DESCRIPTOR = "android.bluetooth.le.IScannerCallback";
    
    static final int TRANSACTION_onBatchScanResults = 3;
    
    static final int TRANSACTION_onFoundOrLost = 4;
    
    static final int TRANSACTION_onScanManagerErrorCallback = 5;
    
    static final int TRANSACTION_onScanResult = 2;
    
    static final int TRANSACTION_onScannerRegistered = 1;
    
    public Stub() {
      attachInterface(this, "android.bluetooth.le.IScannerCallback");
    }
    
    public static IScannerCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.bluetooth.le.IScannerCallback");
      return (iInterface != null && iInterface instanceof IScannerCallback) ? (IScannerCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IScannerCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? ((param1Int != 5) ? null : "onScanManagerErrorCallback") : "onFoundOrLost") : "onBatchScanResults") : "onScanResult") : "onScannerRegistered";
    }
    
    public static boolean setDefaultImpl(IScannerCallback param1IScannerCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IScannerCallback != null) {
          Proxy.sDefaultImpl = param1IScannerCallback;
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
        if (param1Int1 != 2) {
          if (param1Int1 != 3) {
            boolean bool;
            if (param1Int1 != 4) {
              if (param1Int1 != 5) {
                if (param1Int1 != 1598968902)
                  return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
                param1Parcel2.writeString("android.bluetooth.le.IScannerCallback");
                return true;
              } 
              param1Parcel1.enforceInterface("android.bluetooth.le.IScannerCallback");
              onScanManagerErrorCallback(param1Parcel1.readInt());
              return true;
            } 
            param1Parcel1.enforceInterface("android.bluetooth.le.IScannerCallback");
            if (param1Parcel1.readInt() != 0) {
              bool = true;
            } else {
              bool = false;
            } 
            if (param1Parcel1.readInt() != 0) {
              ScanResult scanResult = (ScanResult)ScanResult.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            onFoundOrLost(bool, (ScanResult)param1Parcel1);
            return true;
          } 
          param1Parcel1.enforceInterface("android.bluetooth.le.IScannerCallback");
          onBatchScanResults(param1Parcel1.createTypedArrayList(ScanResult.CREATOR));
          return true;
        } 
        param1Parcel1.enforceInterface("android.bluetooth.le.IScannerCallback");
        if (param1Parcel1.readInt() != 0) {
          ScanResult scanResult = (ScanResult)ScanResult.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        onScanResult((ScanResult)param1Parcel1);
        return true;
      } 
      param1Parcel1.enforceInterface("android.bluetooth.le.IScannerCallback");
      onScannerRegistered(param1Parcel1.readInt(), param1Parcel1.readInt());
      return true;
    }
    
    private static class Proxy implements IScannerCallback {
      public static IScannerCallback sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.bluetooth.le.IScannerCallback";
      }
      
      public void onBatchScanResults(List<ScanResult> param2List) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
          parcel.writeTypedList(param2List);
          if (!this.mRemote.transact(3, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
            IScannerCallback.Stub.getDefaultImpl().onBatchScanResults(param2List);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onFoundOrLost(boolean param2Boolean, ScanResult param2ScanResult) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          boolean bool;
          parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
          if (param2Boolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (param2ScanResult != null) {
            parcel.writeInt(1);
            param2ScanResult.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(4, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
            IScannerCallback.Stub.getDefaultImpl().onFoundOrLost(param2Boolean, param2ScanResult);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onScanManagerErrorCallback(int param2Int) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
          parcel.writeInt(param2Int);
          if (!this.mRemote.transact(5, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
            IScannerCallback.Stub.getDefaultImpl().onScanManagerErrorCallback(param2Int);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onScanResult(ScanResult param2ScanResult) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
          if (param2ScanResult != null) {
            parcel.writeInt(1);
            param2ScanResult.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
            IScannerCallback.Stub.getDefaultImpl().onScanResult(param2ScanResult);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onScannerRegistered(int param2Int1, int param2Int2) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (!this.mRemote.transact(1, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
            IScannerCallback.Stub.getDefaultImpl().onScannerRegistered(param2Int1, param2Int2);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IScannerCallback {
    public static IScannerCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.bluetooth.le.IScannerCallback";
    }
    
    public void onBatchScanResults(List<ScanResult> param1List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
        parcel.writeTypedList(param1List);
        if (!this.mRemote.transact(3, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
          IScannerCallback.Stub.getDefaultImpl().onBatchScanResults(param1List);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onFoundOrLost(boolean param1Boolean, ScanResult param1ScanResult) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
        if (param1Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param1ScanResult != null) {
          parcel.writeInt(1);
          param1ScanResult.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(4, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
          IScannerCallback.Stub.getDefaultImpl().onFoundOrLost(param1Boolean, param1ScanResult);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onScanManagerErrorCallback(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(5, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
          IScannerCallback.Stub.getDefaultImpl().onScanManagerErrorCallback(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onScanResult(ScanResult param1ScanResult) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
        if (param1ScanResult != null) {
          parcel.writeInt(1);
          param1ScanResult.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
          IScannerCallback.Stub.getDefaultImpl().onScanResult(param1ScanResult);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onScannerRegistered(int param1Int1, int param1Int2) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.bluetooth.le.IScannerCallback");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (!this.mRemote.transact(1, parcel, null, 1) && IScannerCallback.Stub.getDefaultImpl() != null) {
          IScannerCallback.Stub.getDefaultImpl().onScannerRegistered(param1Int1, param1Int2);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/IScannerCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
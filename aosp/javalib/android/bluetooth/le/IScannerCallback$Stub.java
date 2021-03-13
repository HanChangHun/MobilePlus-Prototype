package android.bluetooth.le;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IScannerCallback {
  private static final String DESCRIPTOR = "android.bluetooth.le.IScannerCallback";
  
  static final int TRANSACTION_onBatchScanResults = 3;
  
  static final int TRANSACTION_onFoundOrLost = 4;
  
  static final int TRANSACTION_onScanManagerErrorCallback = 5;
  
  static final int TRANSACTION_onScanResult = 2;
  
  static final int TRANSACTION_onScannerRegistered = 1;
  
  public Stub() {
    attachInterface(this, "android.bluetooth.le.IScannerCallback");
  }
  
  public static IScannerCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.bluetooth.le.IScannerCallback");
    return (iInterface != null && iInterface instanceof IScannerCallback) ? (IScannerCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IScannerCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "onScanManagerErrorCallback") : "onFoundOrLost") : "onBatchScanResults") : "onScanResult") : "onScannerRegistered";
  }
  
  public static boolean setDefaultImpl(IScannerCallback paramIScannerCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIScannerCallback != null) {
        Proxy.sDefaultImpl = paramIScannerCallback;
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
      if (paramInt1 != 2) {
        if (paramInt1 != 3) {
          boolean bool;
          if (paramInt1 != 4) {
            if (paramInt1 != 5) {
              if (paramInt1 != 1598968902)
                return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
              paramParcel2.writeString("android.bluetooth.le.IScannerCallback");
              return true;
            } 
            paramParcel1.enforceInterface("android.bluetooth.le.IScannerCallback");
            onScanManagerErrorCallback(paramParcel1.readInt());
            return true;
          } 
          paramParcel1.enforceInterface("android.bluetooth.le.IScannerCallback");
          if (paramParcel1.readInt() != 0) {
            bool = true;
          } else {
            bool = false;
          } 
          if (paramParcel1.readInt() != 0) {
            ScanResult scanResult = (ScanResult)ScanResult.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          onFoundOrLost(bool, (ScanResult)paramParcel1);
          return true;
        } 
        paramParcel1.enforceInterface("android.bluetooth.le.IScannerCallback");
        onBatchScanResults(paramParcel1.createTypedArrayList(ScanResult.CREATOR));
        return true;
      } 
      paramParcel1.enforceInterface("android.bluetooth.le.IScannerCallback");
      if (paramParcel1.readInt() != 0) {
        ScanResult scanResult = (ScanResult)ScanResult.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      onScanResult((ScanResult)paramParcel1);
      return true;
    } 
    paramParcel1.enforceInterface("android.bluetooth.le.IScannerCallback");
    onScannerRegistered(paramParcel1.readInt(), paramParcel1.readInt());
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


/* Location:              /home/chun/Desktop/temp/!/android/bluetooth/le/IScannerCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
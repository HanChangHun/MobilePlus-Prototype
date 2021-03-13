package android.telephony.data;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IDataServiceCallback {
  private static final String DESCRIPTOR = "android.telephony.data.IDataServiceCallback";
  
  static final int TRANSACTION_onDataCallListChanged = 6;
  
  static final int TRANSACTION_onDeactivateDataCallComplete = 2;
  
  static final int TRANSACTION_onRequestDataCallListComplete = 5;
  
  static final int TRANSACTION_onSetDataProfileComplete = 4;
  
  static final int TRANSACTION_onSetInitialAttachApnComplete = 3;
  
  static final int TRANSACTION_onSetupDataCallComplete = 1;
  
  public Stub() {
    attachInterface(this, "android.telephony.data.IDataServiceCallback");
  }
  
  public static IDataServiceCallback asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.telephony.data.IDataServiceCallback");
    return (iInterface != null && iInterface instanceof IDataServiceCallback) ? (IDataServiceCallback)iInterface : new Proxy(paramIBinder);
  }
  
  public static IDataServiceCallback getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 6:
        return "onDataCallListChanged";
      case 5:
        return "onRequestDataCallListComplete";
      case 4:
        return "onSetDataProfileComplete";
      case 3:
        return "onSetInitialAttachApnComplete";
      case 2:
        return "onDeactivateDataCallComplete";
      case 1:
        break;
    } 
    return "onSetupDataCallComplete";
  }
  
  public static boolean setDefaultImpl(IDataServiceCallback paramIDataServiceCallback) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIDataServiceCallback != null) {
        Proxy.sDefaultImpl = paramIDataServiceCallback;
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
    if (paramInt1 != 1598968902) {
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 6:
          paramParcel1.enforceInterface("android.telephony.data.IDataServiceCallback");
          onDataCallListChanged(paramParcel1.createTypedArrayList(DataCallResponse.CREATOR));
          return true;
        case 5:
          paramParcel1.enforceInterface("android.telephony.data.IDataServiceCallback");
          onRequestDataCallListComplete(paramParcel1.readInt(), paramParcel1.createTypedArrayList(DataCallResponse.CREATOR));
          return true;
        case 4:
          paramParcel1.enforceInterface("android.telephony.data.IDataServiceCallback");
          onSetDataProfileComplete(paramParcel1.readInt());
          return true;
        case 3:
          paramParcel1.enforceInterface("android.telephony.data.IDataServiceCallback");
          onSetInitialAttachApnComplete(paramParcel1.readInt());
          return true;
        case 2:
          paramParcel1.enforceInterface("android.telephony.data.IDataServiceCallback");
          onDeactivateDataCallComplete(paramParcel1.readInt());
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.telephony.data.IDataServiceCallback");
      paramInt1 = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0) {
        DataCallResponse dataCallResponse = (DataCallResponse)DataCallResponse.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      onSetupDataCallComplete(paramInt1, (DataCallResponse)paramParcel1);
      return true;
    } 
    paramParcel2.writeString("android.telephony.data.IDataServiceCallback");
    return true;
  }
  
  private static class Proxy implements IDataServiceCallback {
    public static IDataServiceCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.telephony.data.IDataServiceCallback";
    }
    
    public void onDataCallListChanged(List<DataCallResponse> param2List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
        parcel.writeTypedList(param2List);
        if (!this.mRemote.transact(6, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
          IDataServiceCallback.Stub.getDefaultImpl().onDataCallListChanged(param2List);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDeactivateDataCallComplete(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
          IDataServiceCallback.Stub.getDefaultImpl().onDeactivateDataCallComplete(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRequestDataCallListComplete(int param2Int, List<DataCallResponse> param2List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
        parcel.writeInt(param2Int);
        parcel.writeTypedList(param2List);
        if (!this.mRemote.transact(5, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
          IDataServiceCallback.Stub.getDefaultImpl().onRequestDataCallListComplete(param2Int, param2List);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSetDataProfileComplete(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(4, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
          IDataServiceCallback.Stub.getDefaultImpl().onSetDataProfileComplete(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSetInitialAttachApnComplete(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
          IDataServiceCallback.Stub.getDefaultImpl().onSetInitialAttachApnComplete(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSetupDataCallComplete(int param2Int, DataCallResponse param2DataCallResponse) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
        parcel.writeInt(param2Int);
        if (param2DataCallResponse != null) {
          parcel.writeInt(1);
          param2DataCallResponse.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
          IDataServiceCallback.Stub.getDefaultImpl().onSetupDataCallComplete(param2Int, param2DataCallResponse);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IDataServiceCallback$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
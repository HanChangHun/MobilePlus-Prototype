package android.telephony.data;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IDataServiceCallback extends IInterface {
  void onDataCallListChanged(List<DataCallResponse> paramList) throws RemoteException;
  
  void onDeactivateDataCallComplete(int paramInt) throws RemoteException;
  
  void onRequestDataCallListComplete(int paramInt, List<DataCallResponse> paramList) throws RemoteException;
  
  void onSetDataProfileComplete(int paramInt) throws RemoteException;
  
  void onSetInitialAttachApnComplete(int paramInt) throws RemoteException;
  
  void onSetupDataCallComplete(int paramInt, DataCallResponse paramDataCallResponse) throws RemoteException;
  
  public static class Default implements IDataServiceCallback {
    public IBinder asBinder() {
      return null;
    }
    
    public void onDataCallListChanged(List<DataCallResponse> param1List) throws RemoteException {}
    
    public void onDeactivateDataCallComplete(int param1Int) throws RemoteException {}
    
    public void onRequestDataCallListComplete(int param1Int, List<DataCallResponse> param1List) throws RemoteException {}
    
    public void onSetDataProfileComplete(int param1Int) throws RemoteException {}
    
    public void onSetInitialAttachApnComplete(int param1Int) throws RemoteException {}
    
    public void onSetupDataCallComplete(int param1Int, DataCallResponse param1DataCallResponse) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IDataServiceCallback {
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
    
    public static IDataServiceCallback asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.telephony.data.IDataServiceCallback");
      return (iInterface != null && iInterface instanceof IDataServiceCallback) ? (IDataServiceCallback)iInterface : new Proxy(param1IBinder);
    }
    
    public static IDataServiceCallback getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
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
    
    public static boolean setDefaultImpl(IDataServiceCallback param1IDataServiceCallback) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IDataServiceCallback != null) {
          Proxy.sDefaultImpl = param1IDataServiceCallback;
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
      if (param1Int1 != 1598968902) {
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 6:
            param1Parcel1.enforceInterface("android.telephony.data.IDataServiceCallback");
            onDataCallListChanged(param1Parcel1.createTypedArrayList(DataCallResponse.CREATOR));
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.telephony.data.IDataServiceCallback");
            onRequestDataCallListComplete(param1Parcel1.readInt(), param1Parcel1.createTypedArrayList(DataCallResponse.CREATOR));
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.telephony.data.IDataServiceCallback");
            onSetDataProfileComplete(param1Parcel1.readInt());
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.telephony.data.IDataServiceCallback");
            onSetInitialAttachApnComplete(param1Parcel1.readInt());
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.telephony.data.IDataServiceCallback");
            onDeactivateDataCallComplete(param1Parcel1.readInt());
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.telephony.data.IDataServiceCallback");
        param1Int1 = param1Parcel1.readInt();
        if (param1Parcel1.readInt() != 0) {
          DataCallResponse dataCallResponse = (DataCallResponse)DataCallResponse.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        onSetupDataCallComplete(param1Int1, (DataCallResponse)param1Parcel1);
        return true;
      } 
      param1Parcel2.writeString("android.telephony.data.IDataServiceCallback");
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
  
  private static class Proxy implements IDataServiceCallback {
    public static IDataServiceCallback sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.telephony.data.IDataServiceCallback";
    }
    
    public void onDataCallListChanged(List<DataCallResponse> param1List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
        parcel.writeTypedList(param1List);
        if (!this.mRemote.transact(6, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
          IDataServiceCallback.Stub.getDefaultImpl().onDataCallListChanged(param1List);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onDeactivateDataCallComplete(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
          IDataServiceCallback.Stub.getDefaultImpl().onDeactivateDataCallComplete(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onRequestDataCallListComplete(int param1Int, List<DataCallResponse> param1List) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
        parcel.writeInt(param1Int);
        parcel.writeTypedList(param1List);
        if (!this.mRemote.transact(5, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
          IDataServiceCallback.Stub.getDefaultImpl().onRequestDataCallListComplete(param1Int, param1List);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSetDataProfileComplete(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(4, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
          IDataServiceCallback.Stub.getDefaultImpl().onSetDataProfileComplete(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSetInitialAttachApnComplete(int param1Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
        parcel.writeInt(param1Int);
        if (!this.mRemote.transact(3, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
          IDataServiceCallback.Stub.getDefaultImpl().onSetInitialAttachApnComplete(param1Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onSetupDataCallComplete(int param1Int, DataCallResponse param1DataCallResponse) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
        parcel.writeInt(param1Int);
        if (param1DataCallResponse != null) {
          parcel.writeInt(1);
          param1DataCallResponse.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
          IDataServiceCallback.Stub.getDefaultImpl().onSetupDataCallComplete(param1Int, param1DataCallResponse);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IDataServiceCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
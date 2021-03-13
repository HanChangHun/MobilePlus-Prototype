package android.telephony.data;

import android.net.LinkProperties;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

public abstract class Stub extends Binder implements IDataService {
  private static final String DESCRIPTOR = "android.telephony.data.IDataService";
  
  static final int TRANSACTION_createDataServiceProvider = 1;
  
  static final int TRANSACTION_deactivateDataCall = 4;
  
  static final int TRANSACTION_registerForDataCallListChanged = 8;
  
  static final int TRANSACTION_removeDataServiceProvider = 2;
  
  static final int TRANSACTION_requestDataCallList = 7;
  
  static final int TRANSACTION_setDataProfile = 6;
  
  static final int TRANSACTION_setInitialAttachApn = 5;
  
  static final int TRANSACTION_setupDataCall = 3;
  
  static final int TRANSACTION_unregisterForDataCallListChanged = 9;
  
  public Stub() {
    attachInterface(this, "android.telephony.data.IDataService");
  }
  
  public static IDataService asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.telephony.data.IDataService");
    return (iInterface != null && iInterface instanceof IDataService) ? (IDataService)iInterface : new Proxy(paramIBinder);
  }
  
  public static IDataService getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 9:
        return "unregisterForDataCallListChanged";
      case 8:
        return "registerForDataCallListChanged";
      case 7:
        return "requestDataCallList";
      case 6:
        return "setDataProfile";
      case 5:
        return "setInitialAttachApn";
      case 4:
        return "deactivateDataCall";
      case 3:
        return "setupDataCall";
      case 2:
        return "removeDataServiceProvider";
      case 1:
        break;
    } 
    return "createDataServiceProvider";
  }
  
  public static boolean setDefaultImpl(IDataService paramIDataService) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIDataService != null) {
        Proxy.sDefaultImpl = paramIDataService;
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
    ArrayList<DataProfile> arrayList;
    if (paramInt1 != 1598968902) {
      int i;
      LinkProperties linkProperties;
      boolean bool1 = false;
      boolean bool2 = false;
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 9:
          paramParcel1.enforceInterface("android.telephony.data.IDataService");
          unregisterForDataCallListChanged(paramParcel1.readInt(), IDataServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 8:
          paramParcel1.enforceInterface("android.telephony.data.IDataService");
          registerForDataCallListChanged(paramParcel1.readInt(), IDataServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 7:
          paramParcel1.enforceInterface("android.telephony.data.IDataService");
          requestDataCallList(paramParcel1.readInt(), IDataServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 6:
          paramParcel1.enforceInterface("android.telephony.data.IDataService");
          paramInt1 = paramParcel1.readInt();
          arrayList = paramParcel1.createTypedArrayList(DataProfile.CREATOR);
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          setDataProfile(paramInt1, arrayList, bool2, IDataServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 5:
          paramParcel1.enforceInterface("android.telephony.data.IDataService");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            DataProfile dataProfile = (DataProfile)DataProfile.CREATOR.createFromParcel(paramParcel1);
          } else {
            arrayList = null;
          } 
          bool2 = bool1;
          if (paramParcel1.readInt() != 0)
            bool2 = true; 
          setInitialAttachApn(paramInt1, (DataProfile)arrayList, bool2, IDataServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 4:
          paramParcel1.enforceInterface("android.telephony.data.IDataService");
          deactivateDataCall(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readInt(), IDataServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 3:
          paramParcel1.enforceInterface("android.telephony.data.IDataService");
          paramInt1 = paramParcel1.readInt();
          i = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            DataProfile dataProfile = (DataProfile)DataProfile.CREATOR.createFromParcel(paramParcel1);
          } else {
            arrayList = null;
          } 
          if (paramParcel1.readInt() != 0) {
            bool2 = true;
          } else {
            bool2 = false;
          } 
          if (paramParcel1.readInt() != 0) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          paramInt2 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            linkProperties = (LinkProperties)LinkProperties.CREATOR.createFromParcel(paramParcel1);
          } else {
            linkProperties = null;
          } 
          setupDataCall(paramInt1, i, (DataProfile)arrayList, bool2, bool1, paramInt2, linkProperties, IDataServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 2:
          paramParcel1.enforceInterface("android.telephony.data.IDataService");
          removeDataServiceProvider(paramParcel1.readInt());
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.telephony.data.IDataService");
      createDataServiceProvider(paramParcel1.readInt());
      return true;
    } 
    arrayList.writeString("android.telephony.data.IDataService");
    return true;
  }
  
  private static class Proxy implements IDataService {
    public static IDataService sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void createDataServiceProvider(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(1, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().createDataServiceProvider(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void deactivateDataCall(int param2Int1, int param2Int2, int param2Int3, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param2Int1);
        parcel.writeInt(param2Int2);
        parcel.writeInt(param2Int3);
        if (param2IDataServiceCallback != null) {
          iBinder = param2IDataServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(4, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().deactivateDataCall(param2Int1, param2Int2, param2Int3, param2IDataServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.telephony.data.IDataService";
    }
    
    public void registerForDataCallListChanged(int param2Int, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param2Int);
        if (param2IDataServiceCallback != null) {
          iBinder = param2IDataServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(8, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().registerForDataCallListChanged(param2Int, param2IDataServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void removeDataServiceProvider(int param2Int) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param2Int);
        if (!this.mRemote.transact(2, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().removeDataServiceProvider(param2Int);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void requestDataCallList(int param2Int, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param2Int);
        if (param2IDataServiceCallback != null) {
          iBinder = param2IDataServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(7, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().requestDataCallList(param2Int, param2IDataServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setDataProfile(int param2Int, List<DataProfile> param2List, boolean param2Boolean, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        boolean bool;
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param2Int);
        parcel.writeTypedList(param2List);
        if (param2Boolean) {
          bool = true;
        } else {
          bool = false;
        } 
        parcel.writeInt(bool);
        if (param2IDataServiceCallback != null) {
          iBinder = param2IDataServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(6, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().setDataProfile(param2Int, param2List, param2Boolean, param2IDataServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setInitialAttachApn(int param2Int, DataProfile param2DataProfile, boolean param2Boolean, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param2Int);
        boolean bool = false;
        if (param2DataProfile != null) {
          parcel.writeInt(1);
          param2DataProfile.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param2Boolean)
          bool = true; 
        parcel.writeInt(bool);
        if (param2IDataServiceCallback != null) {
          iBinder = param2IDataServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(5, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().setInitialAttachApn(param2Int, param2DataProfile, param2Boolean, param2IDataServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void setupDataCall(int param2Int1, int param2Int2, DataProfile param2DataProfile, boolean param2Boolean1, boolean param2Boolean2, int param2Int3, LinkProperties param2LinkProperties, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        try {
          parcel.writeInt(param2Int1);
          try {
            boolean bool;
            parcel.writeInt(param2Int2);
            if (param2DataProfile != null) {
              parcel.writeInt(1);
              param2DataProfile.writeToParcel(parcel, 0);
            } else {
              parcel.writeInt(0);
            } 
            if (param2Boolean1) {
              bool = true;
            } else {
              bool = false;
            } 
            parcel.writeInt(bool);
            if (param2Boolean2) {
              bool = true;
            } else {
              bool = false;
            } 
            parcel.writeInt(bool);
            try {
              IBinder iBinder;
              parcel.writeInt(param2Int3);
              if (param2LinkProperties != null) {
                parcel.writeInt(1);
                param2LinkProperties.writeToParcel(parcel, 0);
              } else {
                parcel.writeInt(0);
              } 
              if (param2IDataServiceCallback != null) {
                iBinder = param2IDataServiceCallback.asBinder();
              } else {
                iBinder = null;
              } 
              parcel.writeStrongBinder(iBinder);
              if (!this.mRemote.transact(3, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
                IDataService.Stub.getDefaultImpl().setupDataCall(param2Int1, param2Int2, param2DataProfile, param2Boolean1, param2Boolean2, param2Int3, param2LinkProperties, param2IDataServiceCallback);
                parcel.recycle();
                return;
              } 
              parcel.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
      parcel.recycle();
      throw param2DataProfile;
    }
    
    public void unregisterForDataCallListChanged(int param2Int, IDataServiceCallback param2IDataServiceCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.telephony.data.IDataService");
        parcel.writeInt(param2Int);
        if (param2IDataServiceCallback != null) {
          iBinder = param2IDataServiceCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(9, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
          IDataService.Stub.getDefaultImpl().unregisterForDataCallListChanged(param2Int, param2IDataServiceCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IDataService$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.telephony.data;

import android.net.LinkProperties;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IDataService {
  public static IDataService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void createDataServiceProvider(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.telephony.data.IDataService");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(1, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
        IDataService.Stub.getDefaultImpl().createDataServiceProvider(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void deactivateDataCall(int paramInt1, int paramInt2, int paramInt3, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.telephony.data.IDataService");
      parcel.writeInt(paramInt1);
      parcel.writeInt(paramInt2);
      parcel.writeInt(paramInt3);
      if (paramIDataServiceCallback != null) {
        iBinder = paramIDataServiceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(4, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
        IDataService.Stub.getDefaultImpl().deactivateDataCall(paramInt1, paramInt2, paramInt3, paramIDataServiceCallback);
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
  
  public void registerForDataCallListChanged(int paramInt, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.telephony.data.IDataService");
      parcel.writeInt(paramInt);
      if (paramIDataServiceCallback != null) {
        iBinder = paramIDataServiceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(8, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
        IDataService.Stub.getDefaultImpl().registerForDataCallListChanged(paramInt, paramIDataServiceCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void removeDataServiceProvider(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.telephony.data.IDataService");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
        IDataService.Stub.getDefaultImpl().removeDataServiceProvider(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void requestDataCallList(int paramInt, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.telephony.data.IDataService");
      parcel.writeInt(paramInt);
      if (paramIDataServiceCallback != null) {
        iBinder = paramIDataServiceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(7, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
        IDataService.Stub.getDefaultImpl().requestDataCallList(paramInt, paramIDataServiceCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void setDataProfile(int paramInt, List<DataProfile> paramList, boolean paramBoolean, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      boolean bool;
      IBinder iBinder;
      parcel.writeInterfaceToken("android.telephony.data.IDataService");
      parcel.writeInt(paramInt);
      parcel.writeTypedList(paramList);
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel.writeInt(bool);
      if (paramIDataServiceCallback != null) {
        iBinder = paramIDataServiceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(6, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
        IDataService.Stub.getDefaultImpl().setDataProfile(paramInt, paramList, paramBoolean, paramIDataServiceCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void setInitialAttachApn(int paramInt, DataProfile paramDataProfile, boolean paramBoolean, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.telephony.data.IDataService");
      parcel.writeInt(paramInt);
      boolean bool = false;
      if (paramDataProfile != null) {
        parcel.writeInt(1);
        paramDataProfile.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (paramBoolean)
        bool = true; 
      parcel.writeInt(bool);
      if (paramIDataServiceCallback != null) {
        iBinder = paramIDataServiceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(5, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
        IDataService.Stub.getDefaultImpl().setInitialAttachApn(paramInt, paramDataProfile, paramBoolean, paramIDataServiceCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void setupDataCall(int paramInt1, int paramInt2, DataProfile paramDataProfile, boolean paramBoolean1, boolean paramBoolean2, int paramInt3, LinkProperties paramLinkProperties, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.telephony.data.IDataService");
      try {
        parcel.writeInt(paramInt1);
        try {
          boolean bool;
          parcel.writeInt(paramInt2);
          if (paramDataProfile != null) {
            parcel.writeInt(1);
            paramDataProfile.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (paramBoolean1) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          if (paramBoolean2) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel.writeInt(bool);
          try {
            IBinder iBinder;
            parcel.writeInt(paramInt3);
            if (paramLinkProperties != null) {
              parcel.writeInt(1);
              paramLinkProperties.writeToParcel(parcel, 0);
            } else {
              parcel.writeInt(0);
            } 
            if (paramIDataServiceCallback != null) {
              iBinder = paramIDataServiceCallback.asBinder();
            } else {
              iBinder = null;
            } 
            parcel.writeStrongBinder(iBinder);
            if (!this.mRemote.transact(3, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
              IDataService.Stub.getDefaultImpl().setupDataCall(paramInt1, paramInt2, paramDataProfile, paramBoolean1, paramBoolean2, paramInt3, paramLinkProperties, paramIDataServiceCallback);
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
    throw paramDataProfile;
  }
  
  public void unregisterForDataCallListChanged(int paramInt, IDataServiceCallback paramIDataServiceCallback) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel.writeInterfaceToken("android.telephony.data.IDataService");
      parcel.writeInt(paramInt);
      if (paramIDataServiceCallback != null) {
        iBinder = paramIDataServiceCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(9, parcel, null, 1) && IDataService.Stub.getDefaultImpl() != null) {
        IDataService.Stub.getDefaultImpl().unregisterForDataCallListChanged(paramInt, paramIDataServiceCallback);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IDataService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
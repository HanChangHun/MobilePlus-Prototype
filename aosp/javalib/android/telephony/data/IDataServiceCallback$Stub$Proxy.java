package android.telephony.data;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IDataServiceCallback {
  public static IDataServiceCallback sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getInterfaceDescriptor() {
    return "android.telephony.data.IDataServiceCallback";
  }
  
  public void onDataCallListChanged(List<DataCallResponse> paramList) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
      parcel.writeTypedList(paramList);
      if (!this.mRemote.transact(6, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
        IDataServiceCallback.Stub.getDefaultImpl().onDataCallListChanged(paramList);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onDeactivateDataCallComplete(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(2, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
        IDataServiceCallback.Stub.getDefaultImpl().onDeactivateDataCallComplete(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onRequestDataCallListComplete(int paramInt, List<DataCallResponse> paramList) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
      parcel.writeInt(paramInt);
      parcel.writeTypedList(paramList);
      if (!this.mRemote.transact(5, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
        IDataServiceCallback.Stub.getDefaultImpl().onRequestDataCallListComplete(paramInt, paramList);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSetDataProfileComplete(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
        IDataServiceCallback.Stub.getDefaultImpl().onSetDataProfileComplete(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSetInitialAttachApnComplete(int paramInt) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
      parcel.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
        IDataServiceCallback.Stub.getDefaultImpl().onSetInitialAttachApnComplete(paramInt);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
  
  public void onSetupDataCallComplete(int paramInt, DataCallResponse paramDataCallResponse) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      parcel.writeInterfaceToken("android.telephony.data.IDataServiceCallback");
      parcel.writeInt(paramInt);
      if (paramDataCallResponse != null) {
        parcel.writeInt(1);
        paramDataCallResponse.writeToParcel(parcel, 0);
      } else {
        parcel.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel, null, 1) && IDataServiceCallback.Stub.getDefaultImpl() != null) {
        IDataServiceCallback.Stub.getDefaultImpl().onSetupDataCallComplete(paramInt, paramDataCallResponse);
        return;
      } 
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/telephony/data/IDataServiceCallback$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
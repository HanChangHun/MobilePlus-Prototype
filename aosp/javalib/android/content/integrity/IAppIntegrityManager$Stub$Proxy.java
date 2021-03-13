package android.content.integrity;

import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IAppIntegrityManager {
  public static IAppIntegrityManager sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public String getCurrentRuleSetProvider() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.integrity.IAppIntegrityManager");
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IAppIntegrityManager.Stub.getDefaultImpl() != null)
        return IAppIntegrityManager.Stub.getDefaultImpl().getCurrentRuleSetProvider(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getCurrentRuleSetVersion() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.integrity.IAppIntegrityManager");
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IAppIntegrityManager.Stub.getDefaultImpl() != null)
        return IAppIntegrityManager.Stub.getDefaultImpl().getCurrentRuleSetVersion(); 
      parcel2.readException();
      return parcel2.readString();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public ParceledListSlice<Rule> getCurrentRules() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      ParceledListSlice parceledListSlice;
      parcel1.writeInterfaceToken("android.content.integrity.IAppIntegrityManager");
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IAppIntegrityManager.Stub.getDefaultImpl() != null) {
        parceledListSlice = IAppIntegrityManager.Stub.getDefaultImpl().getCurrentRules();
        return parceledListSlice;
      } 
      parcel2.readException();
      if (parcel2.readInt() != 0) {
        parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel(parcel2);
      } else {
        parceledListSlice = null;
      } 
      return parceledListSlice;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.content.integrity.IAppIntegrityManager";
  }
  
  public List<String> getWhitelistedRuleProviders() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.integrity.IAppIntegrityManager");
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IAppIntegrityManager.Stub.getDefaultImpl() != null)
        return IAppIntegrityManager.Stub.getDefaultImpl().getWhitelistedRuleProviders(); 
      parcel2.readException();
      return parcel2.createStringArrayList();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void updateRuleSet(String paramString, ParceledListSlice<Rule> paramParceledListSlice, IntentSender paramIntentSender) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.content.integrity.IAppIntegrityManager");
      parcel1.writeString(paramString);
      if (paramParceledListSlice != null) {
        parcel1.writeInt(1);
        paramParceledListSlice.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (paramIntentSender != null) {
        parcel1.writeInt(1);
        paramIntentSender.writeToParcel(parcel1, 0);
      } else {
        parcel1.writeInt(0);
      } 
      if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAppIntegrityManager.Stub.getDefaultImpl() != null) {
        IAppIntegrityManager.Stub.getDefaultImpl().updateRuleSet(paramString, paramParceledListSlice, paramIntentSender);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/IAppIntegrityManager$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.integrity;

import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class Stub extends Binder implements IAppIntegrityManager {
  private static final String DESCRIPTOR = "android.content.integrity.IAppIntegrityManager";
  
  static final int TRANSACTION_getCurrentRuleSetProvider = 3;
  
  static final int TRANSACTION_getCurrentRuleSetVersion = 2;
  
  static final int TRANSACTION_getCurrentRules = 4;
  
  static final int TRANSACTION_getWhitelistedRuleProviders = 5;
  
  static final int TRANSACTION_updateRuleSet = 1;
  
  public Stub() {
    attachInterface(this, "android.content.integrity.IAppIntegrityManager");
  }
  
  public static IAppIntegrityManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.content.integrity.IAppIntegrityManager");
    return (iInterface != null && iInterface instanceof IAppIntegrityManager) ? (IAppIntegrityManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAppIntegrityManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? null : "getWhitelistedRuleProviders") : "getCurrentRules") : "getCurrentRuleSetProvider") : "getCurrentRuleSetVersion") : "updateRuleSet";
  }
  
  public static boolean setDefaultImpl(IAppIntegrityManager paramIAppIntegrityManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAppIntegrityManager != null) {
        Proxy.sDefaultImpl = paramIAppIntegrityManager;
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
    String str1;
    ParceledListSlice parceledListSlice;
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        ParceledListSlice<Rule> parceledListSlice1;
        if (paramInt1 != 3) {
          List<String> list;
          if (paramInt1 != 4) {
            if (paramInt1 != 5) {
              if (paramInt1 != 1598968902)
                return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
              paramParcel2.writeString("android.content.integrity.IAppIntegrityManager");
              return true;
            } 
            paramParcel1.enforceInterface("android.content.integrity.IAppIntegrityManager");
            list = getWhitelistedRuleProviders();
            paramParcel2.writeNoException();
            paramParcel2.writeStringList(list);
            return true;
          } 
          list.enforceInterface("android.content.integrity.IAppIntegrityManager");
          parceledListSlice1 = getCurrentRules();
          paramParcel2.writeNoException();
          if (parceledListSlice1 != null) {
            paramParcel2.writeInt(1);
            parceledListSlice1.writeToParcel(paramParcel2, 1);
          } else {
            paramParcel2.writeInt(0);
          } 
          return true;
        } 
        parceledListSlice1.enforceInterface("android.content.integrity.IAppIntegrityManager");
        str1 = getCurrentRuleSetProvider();
        paramParcel2.writeNoException();
        paramParcel2.writeString(str1);
        return true;
      } 
      str1.enforceInterface("android.content.integrity.IAppIntegrityManager");
      str1 = getCurrentRuleSetVersion();
      paramParcel2.writeNoException();
      paramParcel2.writeString(str1);
      return true;
    } 
    str1.enforceInterface("android.content.integrity.IAppIntegrityManager");
    String str2 = str1.readString();
    if (str1.readInt() != 0) {
      parceledListSlice = (ParceledListSlice)ParceledListSlice.CREATOR.createFromParcel((Parcel)str1);
    } else {
      parceledListSlice = null;
    } 
    if (str1.readInt() != 0) {
      IntentSender intentSender = (IntentSender)IntentSender.CREATOR.createFromParcel((Parcel)str1);
    } else {
      str1 = null;
    } 
    updateRuleSet(str2, parceledListSlice, (IntentSender)str1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IAppIntegrityManager {
    public static IAppIntegrityManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
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
    
    public void updateRuleSet(String param2String, ParceledListSlice<Rule> param2ParceledListSlice, IntentSender param2IntentSender) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.integrity.IAppIntegrityManager");
        parcel1.writeString(param2String);
        if (param2ParceledListSlice != null) {
          parcel1.writeInt(1);
          param2ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param2IntentSender != null) {
          parcel1.writeInt(1);
          param2IntentSender.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAppIntegrityManager.Stub.getDefaultImpl() != null) {
          IAppIntegrityManager.Stub.getDefaultImpl().updateRuleSet(param2String, param2ParceledListSlice, param2IntentSender);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/IAppIntegrityManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
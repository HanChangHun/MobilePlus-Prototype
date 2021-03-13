package android.content.integrity;

import android.content.IntentSender;
import android.content.pm.ParceledListSlice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IAppIntegrityManager extends IInterface {
  String getCurrentRuleSetProvider() throws RemoteException;
  
  String getCurrentRuleSetVersion() throws RemoteException;
  
  ParceledListSlice<Rule> getCurrentRules() throws RemoteException;
  
  List<String> getWhitelistedRuleProviders() throws RemoteException;
  
  void updateRuleSet(String paramString, ParceledListSlice<Rule> paramParceledListSlice, IntentSender paramIntentSender) throws RemoteException;
  
  public static class Default implements IAppIntegrityManager {
    public IBinder asBinder() {
      return null;
    }
    
    public String getCurrentRuleSetProvider() throws RemoteException {
      return null;
    }
    
    public String getCurrentRuleSetVersion() throws RemoteException {
      return null;
    }
    
    public ParceledListSlice<Rule> getCurrentRules() throws RemoteException {
      return null;
    }
    
    public List<String> getWhitelistedRuleProviders() throws RemoteException {
      return null;
    }
    
    public void updateRuleSet(String param1String, ParceledListSlice<Rule> param1ParceledListSlice, IntentSender param1IntentSender) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IAppIntegrityManager {
    private static final String DESCRIPTOR = "android.content.integrity.IAppIntegrityManager";
    
    static final int TRANSACTION_getCurrentRuleSetProvider = 3;
    
    static final int TRANSACTION_getCurrentRuleSetVersion = 2;
    
    static final int TRANSACTION_getCurrentRules = 4;
    
    static final int TRANSACTION_getWhitelistedRuleProviders = 5;
    
    static final int TRANSACTION_updateRuleSet = 1;
    
    public Stub() {
      attachInterface(this, "android.content.integrity.IAppIntegrityManager");
    }
    
    public static IAppIntegrityManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.content.integrity.IAppIntegrityManager");
      return (iInterface != null && iInterface instanceof IAppIntegrityManager) ? (IAppIntegrityManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAppIntegrityManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? ((param1Int != 3) ? ((param1Int != 4) ? ((param1Int != 5) ? null : "getWhitelistedRuleProviders") : "getCurrentRules") : "getCurrentRuleSetProvider") : "getCurrentRuleSetVersion") : "updateRuleSet";
    }
    
    public static boolean setDefaultImpl(IAppIntegrityManager param1IAppIntegrityManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAppIntegrityManager != null) {
          Proxy.sDefaultImpl = param1IAppIntegrityManager;
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
      String str1;
      ParceledListSlice parceledListSlice;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          ParceledListSlice<Rule> parceledListSlice1;
          if (param1Int1 != 3) {
            List<String> list;
            if (param1Int1 != 4) {
              if (param1Int1 != 5) {
                if (param1Int1 != 1598968902)
                  return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
                param1Parcel2.writeString("android.content.integrity.IAppIntegrityManager");
                return true;
              } 
              param1Parcel1.enforceInterface("android.content.integrity.IAppIntegrityManager");
              list = getWhitelistedRuleProviders();
              param1Parcel2.writeNoException();
              param1Parcel2.writeStringList(list);
              return true;
            } 
            list.enforceInterface("android.content.integrity.IAppIntegrityManager");
            parceledListSlice1 = getCurrentRules();
            param1Parcel2.writeNoException();
            if (parceledListSlice1 != null) {
              param1Parcel2.writeInt(1);
              parceledListSlice1.writeToParcel(param1Parcel2, 1);
            } else {
              param1Parcel2.writeInt(0);
            } 
            return true;
          } 
          parceledListSlice1.enforceInterface("android.content.integrity.IAppIntegrityManager");
          str1 = getCurrentRuleSetProvider();
          param1Parcel2.writeNoException();
          param1Parcel2.writeString(str1);
          return true;
        } 
        str1.enforceInterface("android.content.integrity.IAppIntegrityManager");
        str1 = getCurrentRuleSetVersion();
        param1Parcel2.writeNoException();
        param1Parcel2.writeString(str1);
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
      param1Parcel2.writeNoException();
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
  
  private static class Proxy implements IAppIntegrityManager {
    public static IAppIntegrityManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
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
    
    public void updateRuleSet(String param1String, ParceledListSlice<Rule> param1ParceledListSlice, IntentSender param1IntentSender) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.content.integrity.IAppIntegrityManager");
        parcel1.writeString(param1String);
        if (param1ParceledListSlice != null) {
          parcel1.writeInt(1);
          param1ParceledListSlice.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (param1IntentSender != null) {
          parcel1.writeInt(1);
          param1IntentSender.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IAppIntegrityManager.Stub.getDefaultImpl() != null) {
          IAppIntegrityManager.Stub.getDefaultImpl().updateRuleSet(param1String, param1ParceledListSlice, param1IntentSender);
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


/* Location:              /home/chun/Desktop/temp/!/android/content/integrity/IAppIntegrityManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
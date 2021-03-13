package android.app.contentsuggestions;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.os.IResultReceiver;

public interface IContentSuggestionsManager extends IInterface {
  void classifyContentSelections(int paramInt, ClassificationsRequest paramClassificationsRequest, IClassificationsCallback paramIClassificationsCallback) throws RemoteException;
  
  void isEnabled(int paramInt, IResultReceiver paramIResultReceiver) throws RemoteException;
  
  void notifyInteraction(int paramInt, String paramString, Bundle paramBundle) throws RemoteException;
  
  void provideContextBitmap(int paramInt, Bitmap paramBitmap, Bundle paramBundle) throws RemoteException;
  
  void provideContextImage(int paramInt1, int paramInt2, Bundle paramBundle) throws RemoteException;
  
  void suggestContentSelections(int paramInt, SelectionsRequest paramSelectionsRequest, ISelectionsCallback paramISelectionsCallback) throws RemoteException;
  
  public static class Default implements IContentSuggestionsManager {
    public IBinder asBinder() {
      return null;
    }
    
    public void classifyContentSelections(int param1Int, ClassificationsRequest param1ClassificationsRequest, IClassificationsCallback param1IClassificationsCallback) throws RemoteException {}
    
    public void isEnabled(int param1Int, IResultReceiver param1IResultReceiver) throws RemoteException {}
    
    public void notifyInteraction(int param1Int, String param1String, Bundle param1Bundle) throws RemoteException {}
    
    public void provideContextBitmap(int param1Int, Bitmap param1Bitmap, Bundle param1Bundle) throws RemoteException {}
    
    public void provideContextImage(int param1Int1, int param1Int2, Bundle param1Bundle) throws RemoteException {}
    
    public void suggestContentSelections(int param1Int, SelectionsRequest param1SelectionsRequest, ISelectionsCallback param1ISelectionsCallback) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IContentSuggestionsManager {
    private static final String DESCRIPTOR = "android.app.contentsuggestions.IContentSuggestionsManager";
    
    static final int TRANSACTION_classifyContentSelections = 4;
    
    static final int TRANSACTION_isEnabled = 6;
    
    static final int TRANSACTION_notifyInteraction = 5;
    
    static final int TRANSACTION_provideContextBitmap = 2;
    
    static final int TRANSACTION_provideContextImage = 1;
    
    static final int TRANSACTION_suggestContentSelections = 3;
    
    public Stub() {
      attachInterface(this, "android.app.contentsuggestions.IContentSuggestionsManager");
    }
    
    public static IContentSuggestionsManager asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.contentsuggestions.IContentSuggestionsManager");
      return (iInterface != null && iInterface instanceof IContentSuggestionsManager) ? (IContentSuggestionsManager)iInterface : new Proxy(param1IBinder);
    }
    
    public static IContentSuggestionsManager getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      switch (param1Int) {
        default:
          return null;
        case 6:
          return "isEnabled";
        case 5:
          return "notifyInteraction";
        case 4:
          return "classifyContentSelections";
        case 3:
          return "suggestContentSelections";
        case 2:
          return "provideContextBitmap";
        case 1:
          break;
      } 
      return "provideContextImage";
    }
    
    public static boolean setDefaultImpl(IContentSuggestionsManager param1IContentSuggestionsManager) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IContentSuggestionsManager != null) {
          Proxy.sDefaultImpl = param1IContentSuggestionsManager;
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
      String str;
      if (param1Int1 != 1598968902) {
        switch (param1Int1) {
          default:
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2);
          case 6:
            param1Parcel1.enforceInterface("android.app.contentsuggestions.IContentSuggestionsManager");
            isEnabled(param1Parcel1.readInt(), IResultReceiver.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 5:
            param1Parcel1.enforceInterface("android.app.contentsuggestions.IContentSuggestionsManager");
            param1Int1 = param1Parcel1.readInt();
            str = param1Parcel1.readString();
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            notifyInteraction(param1Int1, str, (Bundle)param1Parcel1);
            return true;
          case 4:
            param1Parcel1.enforceInterface("android.app.contentsuggestions.IContentSuggestionsManager");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              ClassificationsRequest classificationsRequest = (ClassificationsRequest)ClassificationsRequest.CREATOR.createFromParcel(param1Parcel1);
            } else {
              str = null;
            } 
            classifyContentSelections(param1Int1, (ClassificationsRequest)str, IClassificationsCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 3:
            param1Parcel1.enforceInterface("android.app.contentsuggestions.IContentSuggestionsManager");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              SelectionsRequest selectionsRequest = (SelectionsRequest)SelectionsRequest.CREATOR.createFromParcel(param1Parcel1);
            } else {
              str = null;
            } 
            suggestContentSelections(param1Int1, (SelectionsRequest)str, ISelectionsCallback.Stub.asInterface(param1Parcel1.readStrongBinder()));
            return true;
          case 2:
            param1Parcel1.enforceInterface("android.app.contentsuggestions.IContentSuggestionsManager");
            param1Int1 = param1Parcel1.readInt();
            if (param1Parcel1.readInt() != 0) {
              Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(param1Parcel1);
            } else {
              str = null;
            } 
            if (param1Parcel1.readInt() != 0) {
              Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
            } else {
              param1Parcel1 = null;
            } 
            provideContextBitmap(param1Int1, (Bitmap)str, (Bundle)param1Parcel1);
            return true;
          case 1:
            break;
        } 
        param1Parcel1.enforceInterface("android.app.contentsuggestions.IContentSuggestionsManager");
        param1Int2 = param1Parcel1.readInt();
        param1Int1 = param1Parcel1.readInt();
        if (param1Parcel1.readInt() != 0) {
          Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        provideContextImage(param1Int2, param1Int1, (Bundle)param1Parcel1);
        return true;
      } 
      str.writeString("android.app.contentsuggestions.IContentSuggestionsManager");
      return true;
    }
    
    private static class Proxy implements IContentSuggestionsManager {
      public static IContentSuggestionsManager sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public void classifyContentSelections(int param2Int, ClassificationsRequest param2ClassificationsRequest, IClassificationsCallback param2IClassificationsCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
          parcel.writeInt(param2Int);
          if (param2ClassificationsRequest != null) {
            parcel.writeInt(1);
            param2ClassificationsRequest.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2IClassificationsCallback != null) {
            iBinder = param2IClassificationsCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(4, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
            IContentSuggestionsManager.Stub.getDefaultImpl().classifyContentSelections(param2Int, param2ClassificationsRequest, param2IClassificationsCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.contentsuggestions.IContentSuggestionsManager";
      }
      
      public void isEnabled(int param2Int, IResultReceiver param2IResultReceiver) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
          parcel.writeInt(param2Int);
          if (param2IResultReceiver != null) {
            iBinder = param2IResultReceiver.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(6, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
            IContentSuggestionsManager.Stub.getDefaultImpl().isEnabled(param2Int, param2IResultReceiver);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void notifyInteraction(int param2Int, String param2String, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
          parcel.writeInt(param2Int);
          parcel.writeString(param2String);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(5, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
            IContentSuggestionsManager.Stub.getDefaultImpl().notifyInteraction(param2Int, param2String, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void provideContextBitmap(int param2Int, Bitmap param2Bitmap, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
          parcel.writeInt(param2Int);
          if (param2Bitmap != null) {
            parcel.writeInt(1);
            param2Bitmap.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
            IContentSuggestionsManager.Stub.getDefaultImpl().provideContextBitmap(param2Int, param2Bitmap, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void provideContextImage(int param2Int1, int param2Int2, Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
          parcel.writeInt(param2Int1);
          parcel.writeInt(param2Int2);
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
            IContentSuggestionsManager.Stub.getDefaultImpl().provideContextImage(param2Int1, param2Int2, param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void suggestContentSelections(int param2Int, SelectionsRequest param2SelectionsRequest, ISelectionsCallback param2ISelectionsCallback) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          IBinder iBinder;
          parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
          parcel.writeInt(param2Int);
          if (param2SelectionsRequest != null) {
            parcel.writeInt(1);
            param2SelectionsRequest.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (param2ISelectionsCallback != null) {
            iBinder = param2ISelectionsCallback.asBinder();
          } else {
            iBinder = null;
          } 
          parcel.writeStrongBinder(iBinder);
          if (!this.mRemote.transact(3, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
            IContentSuggestionsManager.Stub.getDefaultImpl().suggestContentSelections(param2Int, param2SelectionsRequest, param2ISelectionsCallback);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IContentSuggestionsManager {
    public static IContentSuggestionsManager sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public void classifyContentSelections(int param1Int, ClassificationsRequest param1ClassificationsRequest, IClassificationsCallback param1IClassificationsCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
        parcel.writeInt(param1Int);
        if (param1ClassificationsRequest != null) {
          parcel.writeInt(1);
          param1ClassificationsRequest.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1IClassificationsCallback != null) {
          iBinder = param1IClassificationsCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(4, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
          IContentSuggestionsManager.Stub.getDefaultImpl().classifyContentSelections(param1Int, param1ClassificationsRequest, param1IClassificationsCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.contentsuggestions.IContentSuggestionsManager";
    }
    
    public void isEnabled(int param1Int, IResultReceiver param1IResultReceiver) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
        parcel.writeInt(param1Int);
        if (param1IResultReceiver != null) {
          iBinder = param1IResultReceiver.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(6, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
          IContentSuggestionsManager.Stub.getDefaultImpl().isEnabled(param1Int, param1IResultReceiver);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void notifyInteraction(int param1Int, String param1String, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
        parcel.writeInt(param1Int);
        parcel.writeString(param1String);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(5, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
          IContentSuggestionsManager.Stub.getDefaultImpl().notifyInteraction(param1Int, param1String, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void provideContextBitmap(int param1Int, Bitmap param1Bitmap, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
        parcel.writeInt(param1Int);
        if (param1Bitmap != null) {
          parcel.writeInt(1);
          param1Bitmap.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
          IContentSuggestionsManager.Stub.getDefaultImpl().provideContextBitmap(param1Int, param1Bitmap, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void provideContextImage(int param1Int1, int param1Int2, Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
        parcel.writeInt(param1Int1);
        parcel.writeInt(param1Int2);
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
          IContentSuggestionsManager.Stub.getDefaultImpl().provideContextImage(param1Int1, param1Int2, param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void suggestContentSelections(int param1Int, SelectionsRequest param1SelectionsRequest, ISelectionsCallback param1ISelectionsCallback) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        IBinder iBinder;
        parcel.writeInterfaceToken("android.app.contentsuggestions.IContentSuggestionsManager");
        parcel.writeInt(param1Int);
        if (param1SelectionsRequest != null) {
          parcel.writeInt(1);
          param1SelectionsRequest.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (param1ISelectionsCallback != null) {
          iBinder = param1ISelectionsCallback.asBinder();
        } else {
          iBinder = null;
        } 
        parcel.writeStrongBinder(iBinder);
        if (!this.mRemote.transact(3, parcel, null, 1) && IContentSuggestionsManager.Stub.getDefaultImpl() != null) {
          IContentSuggestionsManager.Stub.getDefaultImpl().suggestContentSelections(param1Int, param1SelectionsRequest, param1ISelectionsCallback);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/IContentSuggestionsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app.contentsuggestions;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.internal.os.IResultReceiver;

public abstract class Stub extends Binder implements IContentSuggestionsManager {
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
  
  public static IContentSuggestionsManager asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.contentsuggestions.IContentSuggestionsManager");
    return (iInterface != null && iInterface instanceof IContentSuggestionsManager) ? (IContentSuggestionsManager)iInterface : new Proxy(paramIBinder);
  }
  
  public static IContentSuggestionsManager getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    switch (paramInt) {
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
  
  public static boolean setDefaultImpl(IContentSuggestionsManager paramIContentSuggestionsManager) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIContentSuggestionsManager != null) {
        Proxy.sDefaultImpl = paramIContentSuggestionsManager;
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
    String str;
    if (paramInt1 != 1598968902) {
      switch (paramInt1) {
        default:
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
        case 6:
          paramParcel1.enforceInterface("android.app.contentsuggestions.IContentSuggestionsManager");
          isEnabled(paramParcel1.readInt(), IResultReceiver.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 5:
          paramParcel1.enforceInterface("android.app.contentsuggestions.IContentSuggestionsManager");
          paramInt1 = paramParcel1.readInt();
          str = paramParcel1.readString();
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          notifyInteraction(paramInt1, str, (Bundle)paramParcel1);
          return true;
        case 4:
          paramParcel1.enforceInterface("android.app.contentsuggestions.IContentSuggestionsManager");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            ClassificationsRequest classificationsRequest = (ClassificationsRequest)ClassificationsRequest.CREATOR.createFromParcel(paramParcel1);
          } else {
            str = null;
          } 
          classifyContentSelections(paramInt1, (ClassificationsRequest)str, IClassificationsCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 3:
          paramParcel1.enforceInterface("android.app.contentsuggestions.IContentSuggestionsManager");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            SelectionsRequest selectionsRequest = (SelectionsRequest)SelectionsRequest.CREATOR.createFromParcel(paramParcel1);
          } else {
            str = null;
          } 
          suggestContentSelections(paramInt1, (SelectionsRequest)str, ISelectionsCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        case 2:
          paramParcel1.enforceInterface("android.app.contentsuggestions.IContentSuggestionsManager");
          paramInt1 = paramParcel1.readInt();
          if (paramParcel1.readInt() != 0) {
            Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(paramParcel1);
          } else {
            str = null;
          } 
          if (paramParcel1.readInt() != 0) {
            Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
          } else {
            paramParcel1 = null;
          } 
          provideContextBitmap(paramInt1, (Bitmap)str, (Bundle)paramParcel1);
          return true;
        case 1:
          break;
      } 
      paramParcel1.enforceInterface("android.app.contentsuggestions.IContentSuggestionsManager");
      paramInt2 = paramParcel1.readInt();
      paramInt1 = paramParcel1.readInt();
      if (paramParcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      provideContextImage(paramInt2, paramInt1, (Bundle)paramParcel1);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/contentsuggestions/IContentSuggestionsManager$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
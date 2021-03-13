package android.app;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IAssistDataReceiver extends IInterface {
  void onHandleAssistData(Bundle paramBundle) throws RemoteException;
  
  void onHandleAssistScreenshot(Bitmap paramBitmap) throws RemoteException;
  
  public static class Default implements IAssistDataReceiver {
    public IBinder asBinder() {
      return null;
    }
    
    public void onHandleAssistData(Bundle param1Bundle) throws RemoteException {}
    
    public void onHandleAssistScreenshot(Bitmap param1Bitmap) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IAssistDataReceiver {
    private static final String DESCRIPTOR = "android.app.IAssistDataReceiver";
    
    static final int TRANSACTION_onHandleAssistData = 1;
    
    static final int TRANSACTION_onHandleAssistScreenshot = 2;
    
    public Stub() {
      attachInterface(this, "android.app.IAssistDataReceiver");
    }
    
    public static IAssistDataReceiver asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.app.IAssistDataReceiver");
      return (iInterface != null && iInterface instanceof IAssistDataReceiver) ? (IAssistDataReceiver)iInterface : new Proxy(param1IBinder);
    }
    
    public static IAssistDataReceiver getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : "onHandleAssistScreenshot") : "onHandleAssistData";
    }
    
    public static boolean setDefaultImpl(IAssistDataReceiver param1IAssistDataReceiver) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IAssistDataReceiver != null) {
          Proxy.sDefaultImpl = param1IAssistDataReceiver;
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
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          if (param1Int1 != 1598968902)
            return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
          param1Parcel2.writeString("android.app.IAssistDataReceiver");
          return true;
        } 
        param1Parcel1.enforceInterface("android.app.IAssistDataReceiver");
        if (param1Parcel1.readInt() != 0) {
          Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(param1Parcel1);
        } else {
          param1Parcel1 = null;
        } 
        onHandleAssistScreenshot((Bitmap)param1Parcel1);
        return true;
      } 
      param1Parcel1.enforceInterface("android.app.IAssistDataReceiver");
      if (param1Parcel1.readInt() != 0) {
        Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onHandleAssistData((Bundle)param1Parcel1);
      return true;
    }
    
    private static class Proxy implements IAssistDataReceiver {
      public static IAssistDataReceiver sDefaultImpl;
      
      private IBinder mRemote;
      
      Proxy(IBinder param2IBinder) {
        this.mRemote = param2IBinder;
      }
      
      public IBinder asBinder() {
        return this.mRemote;
      }
      
      public String getInterfaceDescriptor() {
        return "android.app.IAssistDataReceiver";
      }
      
      public void onHandleAssistData(Bundle param2Bundle) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IAssistDataReceiver");
          if (param2Bundle != null) {
            parcel.writeInt(1);
            param2Bundle.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(1, parcel, null, 1) && IAssistDataReceiver.Stub.getDefaultImpl() != null) {
            IAssistDataReceiver.Stub.getDefaultImpl().onHandleAssistData(param2Bundle);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
      
      public void onHandleAssistScreenshot(Bitmap param2Bitmap) throws RemoteException {
        Parcel parcel = Parcel.obtain();
        try {
          parcel.writeInterfaceToken("android.app.IAssistDataReceiver");
          if (param2Bitmap != null) {
            parcel.writeInt(1);
            param2Bitmap.writeToParcel(parcel, 0);
          } else {
            parcel.writeInt(0);
          } 
          if (!this.mRemote.transact(2, parcel, null, 1) && IAssistDataReceiver.Stub.getDefaultImpl() != null) {
            IAssistDataReceiver.Stub.getDefaultImpl().onHandleAssistScreenshot(param2Bitmap);
            return;
          } 
          return;
        } finally {
          parcel.recycle();
        } 
      }
    }
  }
  
  private static class Proxy implements IAssistDataReceiver {
    public static IAssistDataReceiver sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.app.IAssistDataReceiver";
    }
    
    public void onHandleAssistData(Bundle param1Bundle) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IAssistDataReceiver");
        if (param1Bundle != null) {
          parcel.writeInt(1);
          param1Bundle.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel, null, 1) && IAssistDataReceiver.Stub.getDefaultImpl() != null) {
          IAssistDataReceiver.Stub.getDefaultImpl().onHandleAssistData(param1Bundle);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
    
    public void onHandleAssistScreenshot(Bitmap param1Bitmap) throws RemoteException {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.IAssistDataReceiver");
        if (param1Bitmap != null) {
          parcel.writeInt(1);
          param1Bitmap.writeToParcel(parcel, 0);
        } else {
          parcel.writeInt(0);
        } 
        if (!this.mRemote.transact(2, parcel, null, 1) && IAssistDataReceiver.Stub.getDefaultImpl() != null) {
          IAssistDataReceiver.Stub.getDefaultImpl().onHandleAssistScreenshot(param1Bitmap);
          return;
        } 
        return;
      } finally {
        parcel.recycle();
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/IAssistDataReceiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
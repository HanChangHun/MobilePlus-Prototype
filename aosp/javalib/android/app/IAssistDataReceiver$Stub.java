package android.app;

import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IAssistDataReceiver {
  private static final String DESCRIPTOR = "android.app.IAssistDataReceiver";
  
  static final int TRANSACTION_onHandleAssistData = 1;
  
  static final int TRANSACTION_onHandleAssistScreenshot = 2;
  
  public Stub() {
    attachInterface(this, "android.app.IAssistDataReceiver");
  }
  
  public static IAssistDataReceiver asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.app.IAssistDataReceiver");
    return (iInterface != null && iInterface instanceof IAssistDataReceiver) ? (IAssistDataReceiver)iInterface : new Proxy(paramIBinder);
  }
  
  public static IAssistDataReceiver getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? null : "onHandleAssistScreenshot") : "onHandleAssistData";
  }
  
  public static boolean setDefaultImpl(IAssistDataReceiver paramIAssistDataReceiver) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIAssistDataReceiver != null) {
        Proxy.sDefaultImpl = paramIAssistDataReceiver;
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
    if (paramInt1 != 1) {
      if (paramInt1 != 2) {
        if (paramInt1 != 1598968902)
          return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
        paramParcel2.writeString("android.app.IAssistDataReceiver");
        return true;
      } 
      paramParcel1.enforceInterface("android.app.IAssistDataReceiver");
      if (paramParcel1.readInt() != 0) {
        Bitmap bitmap = (Bitmap)Bitmap.CREATOR.createFromParcel(paramParcel1);
      } else {
        paramParcel1 = null;
      } 
      onHandleAssistScreenshot((Bitmap)paramParcel1);
      return true;
    } 
    paramParcel1.enforceInterface("android.app.IAssistDataReceiver");
    if (paramParcel1.readInt() != 0) {
      Bundle bundle = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onHandleAssistData((Bundle)paramParcel1);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/IAssistDataReceiver$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
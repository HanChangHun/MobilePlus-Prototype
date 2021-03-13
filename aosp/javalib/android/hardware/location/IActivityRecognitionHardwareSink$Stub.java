package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class Stub extends Binder implements IActivityRecognitionHardwareSink {
  private static final String DESCRIPTOR = "android.hardware.location.IActivityRecognitionHardwareSink";
  
  static final int TRANSACTION_onActivityChanged = 1;
  
  public Stub() {
    attachInterface(this, "android.hardware.location.IActivityRecognitionHardwareSink");
  }
  
  public static IActivityRecognitionHardwareSink asInterface(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("android.hardware.location.IActivityRecognitionHardwareSink");
    return (iInterface != null && iInterface instanceof IActivityRecognitionHardwareSink) ? (IActivityRecognitionHardwareSink)iInterface : new Proxy(paramIBinder);
  }
  
  public static IActivityRecognitionHardwareSink getDefaultImpl() {
    return Proxy.sDefaultImpl;
  }
  
  public static String getDefaultTransactionName(int paramInt) {
    return (paramInt != 1) ? null : "onActivityChanged";
  }
  
  public static boolean setDefaultImpl(IActivityRecognitionHardwareSink paramIActivityRecognitionHardwareSink) {
    if (Proxy.sDefaultImpl == null) {
      if (paramIActivityRecognitionHardwareSink != null) {
        Proxy.sDefaultImpl = paramIActivityRecognitionHardwareSink;
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
      if (paramInt1 != 1598968902)
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2); 
      paramParcel2.writeString("android.hardware.location.IActivityRecognitionHardwareSink");
      return true;
    } 
    paramParcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardwareSink");
    if (paramParcel1.readInt() != 0) {
      ActivityChangedEvent activityChangedEvent = (ActivityChangedEvent)ActivityChangedEvent.CREATOR.createFromParcel(paramParcel1);
    } else {
      paramParcel1 = null;
    } 
    onActivityChanged((ActivityChangedEvent)paramParcel1);
    paramParcel2.writeNoException();
    return true;
  }
  
  private static class Proxy implements IActivityRecognitionHardwareSink {
    public static IActivityRecognitionHardwareSink sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param2IBinder) {
      this.mRemote = param2IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IActivityRecognitionHardwareSink";
    }
    
    public void onActivityChanged(ActivityChangedEvent param2ActivityChangedEvent) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareSink");
        if (param2ActivityChangedEvent != null) {
          parcel1.writeInt(1);
          param2ActivityChangedEvent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityRecognitionHardwareSink.Stub.getDefaultImpl() != null) {
          IActivityRecognitionHardwareSink.Stub.getDefaultImpl().onActivityChanged(param2ActivityChangedEvent);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardwareSink$Stub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
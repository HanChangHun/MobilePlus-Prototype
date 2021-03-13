package android.hardware.location;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IActivityRecognitionHardwareSink extends IInterface {
  void onActivityChanged(ActivityChangedEvent paramActivityChangedEvent) throws RemoteException;
  
  public static class Default implements IActivityRecognitionHardwareSink {
    public IBinder asBinder() {
      return null;
    }
    
    public void onActivityChanged(ActivityChangedEvent param1ActivityChangedEvent) throws RemoteException {}
  }
  
  public static abstract class Stub extends Binder implements IActivityRecognitionHardwareSink {
    private static final String DESCRIPTOR = "android.hardware.location.IActivityRecognitionHardwareSink";
    
    static final int TRANSACTION_onActivityChanged = 1;
    
    public Stub() {
      attachInterface(this, "android.hardware.location.IActivityRecognitionHardwareSink");
    }
    
    public static IActivityRecognitionHardwareSink asInterface(IBinder param1IBinder) {
      if (param1IBinder == null)
        return null; 
      IInterface iInterface = param1IBinder.queryLocalInterface("android.hardware.location.IActivityRecognitionHardwareSink");
      return (iInterface != null && iInterface instanceof IActivityRecognitionHardwareSink) ? (IActivityRecognitionHardwareSink)iInterface : new Proxy(param1IBinder);
    }
    
    public static IActivityRecognitionHardwareSink getDefaultImpl() {
      return Proxy.sDefaultImpl;
    }
    
    public static String getDefaultTransactionName(int param1Int) {
      return (param1Int != 1) ? null : "onActivityChanged";
    }
    
    public static boolean setDefaultImpl(IActivityRecognitionHardwareSink param1IActivityRecognitionHardwareSink) {
      if (Proxy.sDefaultImpl == null) {
        if (param1IActivityRecognitionHardwareSink != null) {
          Proxy.sDefaultImpl = param1IActivityRecognitionHardwareSink;
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
        if (param1Int1 != 1598968902)
          return super.onTransact(param1Int1, param1Parcel1, param1Parcel2, param1Int2); 
        param1Parcel2.writeString("android.hardware.location.IActivityRecognitionHardwareSink");
        return true;
      } 
      param1Parcel1.enforceInterface("android.hardware.location.IActivityRecognitionHardwareSink");
      if (param1Parcel1.readInt() != 0) {
        ActivityChangedEvent activityChangedEvent = (ActivityChangedEvent)ActivityChangedEvent.CREATOR.createFromParcel(param1Parcel1);
      } else {
        param1Parcel1 = null;
      } 
      onActivityChanged((ActivityChangedEvent)param1Parcel1);
      param1Parcel2.writeNoException();
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
  
  private static class Proxy implements IActivityRecognitionHardwareSink {
    public static IActivityRecognitionHardwareSink sDefaultImpl;
    
    private IBinder mRemote;
    
    Proxy(IBinder param1IBinder) {
      this.mRemote = param1IBinder;
    }
    
    public IBinder asBinder() {
      return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
      return "android.hardware.location.IActivityRecognitionHardwareSink";
    }
    
    public void onActivityChanged(ActivityChangedEvent param1ActivityChangedEvent) throws RemoteException {
      Parcel parcel1 = Parcel.obtain();
      Parcel parcel2 = Parcel.obtain();
      try {
        parcel1.writeInterfaceToken("android.hardware.location.IActivityRecognitionHardwareSink");
        if (param1ActivityChangedEvent != null) {
          parcel1.writeInt(1);
          param1ActivityChangedEvent.writeToParcel(parcel1, 0);
        } else {
          parcel1.writeInt(0);
        } 
        if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IActivityRecognitionHardwareSink.Stub.getDefaultImpl() != null) {
          IActivityRecognitionHardwareSink.Stub.getDefaultImpl().onActivityChanged(param1ActivityChangedEvent);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/location/IActivityRecognitionHardwareSink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
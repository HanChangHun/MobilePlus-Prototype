package android.hardware.face;

import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IFaceService {
  public static IFaceService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addLockoutResetCallback(IBiometricServiceLockoutResetCallback paramIBiometricServiceLockoutResetCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      if (paramIBiometricServiceLockoutResetCallback != null) {
        iBinder = paramIBiometricServiceLockoutResetCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        IFaceService.Stub.getDefaultImpl().addLockoutResetCallback(paramIBiometricServiceLockoutResetCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public IBinder asBinder() {
    return this.mRemote;
  }
  
  public void authenticate(IBinder paramIBinder, long paramLong, int paramInt1, IFaceServiceReceiver paramIFaceServiceReceiver, int paramInt2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      try {
        parcel1.writeStrongBinder(paramIBinder);
        try {
          parcel1.writeLong(paramLong);
          try {
            IBinder iBinder;
            parcel1.writeInt(paramInt1);
            if (paramIFaceServiceReceiver != null) {
              iBinder = paramIFaceServiceReceiver.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            try {
              parcel1.writeInt(paramInt2);
              parcel1.writeString(paramString);
              if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
                IFaceService.Stub.getDefaultImpl().authenticate(paramIBinder, paramLong, paramInt1, paramIFaceServiceReceiver, paramInt2, paramString);
                parcel2.recycle();
                parcel1.recycle();
                return;
              } 
              parcel2.readException();
              parcel2.recycle();
              parcel1.recycle();
              return;
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIBinder;
  }
  
  public void cancelAuthentication(IBinder paramIBinder, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        IFaceService.Stub.getDefaultImpl().cancelAuthentication(paramIBinder, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelAuthenticationFromService(IBinder paramIBinder, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      try {
        parcel1.writeStrongBinder(paramIBinder);
        try {
          parcel1.writeString(paramString);
          try {
            parcel1.writeInt(paramInt1);
            try {
              parcel1.writeInt(paramInt2);
              try {
                boolean bool;
                parcel1.writeInt(paramInt3);
                if (paramBoolean) {
                  bool = true;
                } else {
                  bool = false;
                } 
                parcel1.writeInt(bool);
                try {
                  if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
                    IFaceService.Stub.getDefaultImpl().cancelAuthenticationFromService(paramIBinder, paramString, paramInt1, paramInt2, paramInt3, paramBoolean);
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } 
                  parcel2.readException();
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIBinder;
  }
  
  public void cancelEnrollment(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        IFaceService.Stub.getDefaultImpl().cancelEnrollment(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void enroll(int paramInt, IBinder paramIBinder, byte[] paramArrayOfbyte, IFaceServiceReceiver paramIFaceServiceReceiver, String paramString, int[] paramArrayOfint) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      try {
        parcel1.writeInt(paramInt);
        try {
          parcel1.writeStrongBinder(paramIBinder);
          try {
            IBinder iBinder;
            parcel1.writeByteArray(paramArrayOfbyte);
            if (paramIFaceServiceReceiver != null) {
              iBinder = paramIFaceServiceReceiver.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            try {
              parcel1.writeString(paramString);
              try {
                parcel1.writeIntArray(paramArrayOfint);
                try {
                  if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
                    IFaceService.Stub.getDefaultImpl().enroll(paramInt, paramIBinder, paramArrayOfbyte, paramIFaceServiceReceiver, paramString, paramArrayOfint);
                    parcel2.recycle();
                    parcel1.recycle();
                    return;
                  } 
                  parcel2.readException();
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } finally {}
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIBinder;
  }
  
  public void enumerate(IBinder paramIBinder, int paramInt, IFaceServiceReceiver paramIFaceServiceReceiver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt);
      if (paramIFaceServiceReceiver != null) {
        iBinder = paramIFaceServiceReceiver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        IFaceService.Stub.getDefaultImpl().enumerate(paramIBinder, paramInt, paramIFaceServiceReceiver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long generateChallenge(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null)
        return IFaceService.Stub.getDefaultImpl().generateChallenge(paramIBinder); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getAuthenticatorId(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null)
        return IFaceService.Stub.getDefaultImpl().getAuthenticatorId(paramInt); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<Face> getEnrolledFaces(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null)
        return IFaceService.Stub.getDefaultImpl().getEnrolledFaces(paramInt, paramString); 
      parcel2.readException();
      return parcel2.createTypedArrayList(Face.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void getFeature(int paramInt1, int paramInt2, IFaceServiceReceiver paramIFaceServiceReceiver, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramIFaceServiceReceiver != null) {
        iBinder = paramIFaceServiceReceiver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        IFaceService.Stub.getDefaultImpl().getFeature(paramInt1, paramInt2, paramIFaceServiceReceiver, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.face.IFaceService";
  }
  
  public boolean hasEnrolledFaces(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(14, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        bool = IFaceService.Stub.getDefaultImpl().hasEnrolledFaces(paramInt, paramString);
        return bool;
      } 
      parcel2.readException();
      paramInt = parcel2.readInt();
      if (paramInt != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void initConfiguredStrength(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        IFaceService.Stub.getDefaultImpl().initConfiguredStrength(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isHardwareDetected(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(11, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        bool = IFaceService.Stub.getDefaultImpl().isHardwareDetected(paramString);
        return bool;
      } 
      parcel2.readException();
      int i = parcel2.readInt();
      if (i != 0)
        bool = true; 
      return bool;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void prepareForAuthentication(boolean paramBoolean, IBinder paramIBinder, long paramLong, int paramInt1, IBiometricServiceReceiverInternal paramIBiometricServiceReceiverInternal, String paramString, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      boolean bool;
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      if (paramBoolean) {
        bool = true;
      } else {
        bool = false;
      } 
      parcel1.writeInt(bool);
      try {
        IBinder iBinder;
        parcel1.writeStrongBinder(paramIBinder);
        parcel1.writeLong(paramLong);
        parcel1.writeInt(paramInt1);
        if (paramIBiometricServiceReceiverInternal != null) {
          iBinder = paramIBiometricServiceReceiverInternal.asBinder();
        } else {
          iBinder = null;
        } 
        parcel1.writeStrongBinder(iBinder);
        parcel1.writeString(paramString);
        parcel1.writeInt(paramInt2);
        parcel1.writeInt(paramInt3);
        parcel1.writeInt(paramInt4);
        parcel1.writeInt(paramInt5);
        if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
          IFaceService.Stub.getDefaultImpl().prepareForAuthentication(paramBoolean, paramIBinder, paramLong, paramInt1, paramIBiometricServiceReceiverInternal, paramString, paramInt2, paramInt3, paramInt4, paramInt5);
          parcel2.recycle();
          parcel1.recycle();
          return;
        } 
        parcel2.readException();
        parcel2.recycle();
        parcel1.recycle();
        return;
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramIBinder;
  }
  
  public void remove(IBinder paramIBinder, int paramInt1, int paramInt2, IFaceServiceReceiver paramIFaceServiceReceiver, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      if (paramIFaceServiceReceiver != null) {
        iBinder = paramIFaceServiceReceiver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        IFaceService.Stub.getDefaultImpl().remove(paramIBinder, paramInt1, paramInt2, paramIFaceServiceReceiver, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void rename(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        IFaceService.Stub.getDefaultImpl().rename(paramInt, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void resetLockout(byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(16, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        IFaceService.Stub.getDefaultImpl().resetLockout(paramArrayOfbyte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public int revokeChallenge(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(13, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null)
        return IFaceService.Stub.getDefaultImpl().revokeChallenge(paramIBinder); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setActiveUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        IFaceService.Stub.getDefaultImpl().setActiveUser(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setFeature(int paramInt1, int paramInt2, boolean paramBoolean, byte[] paramArrayOfbyte, IFaceServiceReceiver paramIFaceServiceReceiver, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      try {
        parcel1.writeInt(paramInt1);
        try {
          boolean bool;
          parcel1.writeInt(paramInt2);
          if (paramBoolean) {
            bool = true;
          } else {
            bool = false;
          } 
          parcel1.writeInt(bool);
          try {
            IBinder iBinder;
            parcel1.writeByteArray(paramArrayOfbyte);
            if (paramIFaceServiceReceiver != null) {
              iBinder = paramIFaceServiceReceiver.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            try {
              parcel1.writeString(paramString);
              try {
                if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
                  IFaceService.Stub.getDefaultImpl().setFeature(paramInt1, paramInt2, paramBoolean, paramArrayOfbyte, paramIFaceServiceReceiver, paramString);
                  parcel2.recycle();
                  parcel1.recycle();
                  return;
                } 
                parcel2.readException();
                parcel2.recycle();
                parcel1.recycle();
                return;
              } finally {}
            } finally {}
          } finally {}
        } finally {}
      } finally {}
    } finally {}
    parcel2.recycle();
    parcel1.recycle();
    throw paramArrayOfbyte;
  }
  
  public void startPreparedClient(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        IFaceService.Stub.getDefaultImpl().startPreparedClient(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void userActivity() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.face.IFaceService");
      if (!this.mRemote.transact(22, parcel1, parcel2, 0) && IFaceService.Stub.getDefaultImpl() != null) {
        IFaceService.Stub.getDefaultImpl().userActivity();
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/face/IFaceService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.hardware.fingerprint;

import android.hardware.biometrics.IBiometricServiceLockoutResetCallback;
import android.hardware.biometrics.IBiometricServiceReceiverInternal;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

class Proxy implements IFingerprintService {
  public static IFingerprintService sDefaultImpl;
  
  private IBinder mRemote;
  
  Proxy(IBinder paramIBinder) {
    this.mRemote = paramIBinder;
  }
  
  public void addClientActiveCallback(IFingerprintClientActiveCallback paramIFingerprintClientActiveCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      if (paramIFingerprintClientActiveCallback != null) {
        iBinder = paramIFingerprintClientActiveCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(23, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().addClientActiveCallback(paramIFingerprintClientActiveCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void addLockoutResetCallback(IBiometricServiceLockoutResetCallback paramIBiometricServiceLockoutResetCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      if (paramIBiometricServiceLockoutResetCallback != null) {
        iBinder = paramIBiometricServiceLockoutResetCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(19, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().addLockoutResetCallback(paramIBiometricServiceLockoutResetCallback);
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
  
  public void authenticate(IBinder paramIBinder, long paramLong, int paramInt1, IFingerprintServiceReceiver paramIFingerprintServiceReceiver, int paramInt2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      try {
        parcel1.writeStrongBinder(paramIBinder);
        try {
          parcel1.writeLong(paramLong);
          try {
            IBinder iBinder;
            parcel1.writeInt(paramInt1);
            if (paramIFingerprintServiceReceiver != null) {
              iBinder = paramIFingerprintServiceReceiver.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            try {
              parcel1.writeInt(paramInt2);
              parcel1.writeString(paramString);
              if (!this.mRemote.transact(1, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
                IFingerprintService.Stub.getDefaultImpl().authenticate(paramIBinder, paramLong, paramInt1, paramIFingerprintServiceReceiver, paramInt2, paramString);
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
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(5, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().cancelAuthentication(paramIBinder, paramString);
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
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
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
                  if (!this.mRemote.transact(7, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
                    IFingerprintService.Stub.getDefaultImpl().cancelAuthenticationFromService(paramIBinder, paramString, paramInt1, paramInt2, paramInt3, paramBoolean);
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
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(9, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().cancelEnrollment(paramIBinder);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void cancelFingerprintDetect(IBinder paramIBinder, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(6, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().cancelFingerprintDetect(paramIBinder, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void detectFingerprint(IBinder paramIBinder, int paramInt, IFingerprintServiceReceiver paramIFingerprintServiceReceiver, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt);
      if (paramIFingerprintServiceReceiver != null) {
        iBinder = paramIFingerprintServiceReceiver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(2, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().detectFingerprint(paramIBinder, paramInt, paramIFingerprintServiceReceiver, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void enroll(IBinder paramIBinder, byte[] paramArrayOfbyte, int paramInt1, IFingerprintServiceReceiver paramIFingerprintServiceReceiver, int paramInt2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      try {
        parcel1.writeStrongBinder(paramIBinder);
        try {
          parcel1.writeByteArray(paramArrayOfbyte);
          try {
            IBinder iBinder;
            parcel1.writeInt(paramInt1);
            if (paramIFingerprintServiceReceiver != null) {
              iBinder = paramIFingerprintServiceReceiver.asBinder();
            } else {
              iBinder = null;
            } 
            parcel1.writeStrongBinder(iBinder);
            try {
              parcel1.writeInt(paramInt2);
              try {
                parcel1.writeString(paramString);
                try {
                  if (!this.mRemote.transact(8, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
                    IFingerprintService.Stub.getDefaultImpl().enroll(paramIBinder, paramArrayOfbyte, paramInt1, paramIFingerprintServiceReceiver, paramInt2, paramString);
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
  
  public void enumerate(IBinder paramIBinder, int paramInt, IFingerprintServiceReceiver paramIFingerprintServiceReceiver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt);
      if (paramIFingerprintServiceReceiver != null) {
        iBinder = paramIFingerprintServiceReceiver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(21, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().enumerate(paramIBinder, paramInt, paramIFingerprintServiceReceiver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long getAuthenticatorId(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(17, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null)
        return IFingerprintService.Stub.getDefaultImpl().getAuthenticatorId(paramInt); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public List<Fingerprint> getEnrolledFingerprints(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(12, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null)
        return IFingerprintService.Stub.getDefaultImpl().getEnrolledFingerprints(paramInt, paramString); 
      parcel2.readException();
      return parcel2.createTypedArrayList(Fingerprint.CREATOR);
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public String getInterfaceDescriptor() {
    return "android.hardware.fingerprint.IFingerprintService";
  }
  
  public boolean hasEnrolledFingerprints(int paramInt, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeInt(paramInt);
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(16, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        bool = IFingerprintService.Stub.getDefaultImpl().hasEnrolledFingerprints(paramInt, paramString);
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
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(25, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().initConfiguredStrength(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public boolean isClientActive() throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(22, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        bool = IFingerprintService.Stub.getDefaultImpl().isClientActive();
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
  
  public boolean isHardwareDetected(String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeString(paramString);
      IBinder iBinder = this.mRemote;
      boolean bool = false;
      if (!iBinder.transact(13, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        bool = IFingerprintService.Stub.getDefaultImpl().isHardwareDetected(paramString);
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
  
  public int postEnroll(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(15, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null)
        return IFingerprintService.Stub.getDefaultImpl().postEnroll(paramIBinder); 
      parcel2.readException();
      return parcel2.readInt();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public long preEnroll(IBinder paramIBinder) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeStrongBinder(paramIBinder);
      if (!this.mRemote.transact(14, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null)
        return IFingerprintService.Stub.getDefaultImpl().preEnroll(paramIBinder); 
      parcel2.readException();
      return parcel2.readLong();
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void prepareForAuthentication(IBinder paramIBinder, long paramLong, int paramInt1, IBiometricServiceReceiverInternal paramIBiometricServiceReceiverInternal, String paramString, int paramInt2, int paramInt3, int paramInt4, int paramInt5) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      try {
        parcel1.writeStrongBinder(paramIBinder);
        parcel1.writeLong(paramLong);
        try {
          IBinder iBinder;
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
          if (!this.mRemote.transact(3, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
            IFingerprintService.Stub.getDefaultImpl().prepareForAuthentication(paramIBinder, paramLong, paramInt1, paramIBiometricServiceReceiverInternal, paramString, paramInt2, paramInt3, paramInt4, paramInt5);
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
    parcel2.recycle();
    parcel1.recycle();
    throw paramIBinder;
  }
  
  public void remove(IBinder paramIBinder, int paramInt1, int paramInt2, int paramInt3, IFingerprintServiceReceiver paramIFingerprintServiceReceiver) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeStrongBinder(paramIBinder);
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeInt(paramInt3);
      if (paramIFingerprintServiceReceiver != null) {
        iBinder = paramIFingerprintServiceReceiver.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(10, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().remove(paramIBinder, paramInt1, paramInt2, paramInt3, paramIFingerprintServiceReceiver);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void removeClientActiveCallback(IFingerprintClientActiveCallback paramIFingerprintClientActiveCallback) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      IBinder iBinder;
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      if (paramIFingerprintClientActiveCallback != null) {
        iBinder = paramIFingerprintClientActiveCallback.asBinder();
      } else {
        iBinder = null;
      } 
      parcel1.writeStrongBinder(iBinder);
      if (!this.mRemote.transact(24, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().removeClientActiveCallback(paramIFingerprintClientActiveCallback);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void rename(int paramInt1, int paramInt2, String paramString) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeInt(paramInt1);
      parcel1.writeInt(paramInt2);
      parcel1.writeString(paramString);
      if (!this.mRemote.transact(11, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().rename(paramInt1, paramInt2, paramString);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void resetTimeout(byte[] paramArrayOfbyte) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeByteArray(paramArrayOfbyte);
      if (!this.mRemote.transact(18, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().resetTimeout(paramArrayOfbyte);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void setActiveUser(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(20, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().setActiveUser(paramInt);
        return;
      } 
      parcel2.readException();
      return;
    } finally {
      parcel2.recycle();
      parcel1.recycle();
    } 
  }
  
  public void startPreparedClient(int paramInt) throws RemoteException {
    Parcel parcel1 = Parcel.obtain();
    Parcel parcel2 = Parcel.obtain();
    try {
      parcel1.writeInterfaceToken("android.hardware.fingerprint.IFingerprintService");
      parcel1.writeInt(paramInt);
      if (!this.mRemote.transact(4, parcel1, parcel2, 0) && IFingerprintService.Stub.getDefaultImpl() != null) {
        IFingerprintService.Stub.getDefaultImpl().startPreparedClient(paramInt);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/fingerprint/IFingerprintService$Stub$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
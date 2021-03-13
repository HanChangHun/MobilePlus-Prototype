package android.hardware.radio.V1_0;

import android.internal.hidl.base.V1_0.DebugInfo;
import android.os.HidlSupport;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Objects;

public final class Proxy implements ISapCallback {
  private IHwBinder mRemote;
  
  public Proxy(IHwBinder paramIHwBinder) {
    Objects.requireNonNull(paramIHwBinder);
    this.mRemote = paramIHwBinder;
  }
  
  public void apduResponse(int paramInt1, int paramInt2, ArrayList<Byte> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeInt8Vector(paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(4, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public IHwBinder asBinder() {
    return this.mRemote;
  }
  
  public void connectResponse(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeInt32(paramInt3);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(1, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hidl.base@1.0::IBase");
    hwParcel2.writeNativeHandle(paramNativeHandle);
    hwParcel2.writeStringVector(paramArrayList);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(256131655, hwParcel2, hwParcel1, 0);
      hwParcel1.verifySuccess();
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void disconnectIndication(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(3, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void disconnectResponse(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(2, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public final boolean equals(Object paramObject) {
    return HidlSupport.interfacesEqual((IHwInterface)this, paramObject);
  }
  
  public void errorResponse(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(10, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public DebugInfo getDebugInfo() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hidl.base@1.0::IBase");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(257049926, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      DebugInfo debugInfo = new DebugInfo();
      this();
      debugInfo.readFromParcel(hwParcel);
      return debugInfo;
    } finally {
      hwParcel.release();
    } 
  }
  
  public ArrayList<byte[]> getHashChain() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hidl.base@1.0::IBase");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(256398152, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      ArrayList<byte[]> arrayList = new ArrayList();
      this();
      HwBlob hwBlob = hwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = hwParcel.readEmbeddedBuffer((i * 32), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        byte[] arrayOfByte = new byte[32];
        hwBlob.copyToInt8Array((b * 32), arrayOfByte, 32);
        arrayList.add(arrayOfByte);
      } 
      return arrayList;
    } finally {
      hwParcel.release();
    } 
  }
  
  public final int hashCode() {
    return asBinder().hashCode();
  }
  
  public ArrayList<String> interfaceChain() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hidl.base@1.0::IBase");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(256067662, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readStringVector();
    } finally {
      hwParcel.release();
    } 
  }
  
  public String interfaceDescriptor() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hidl.base@1.0::IBase");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(256136003, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readString();
    } finally {
      hwParcel.release();
    } 
  }
  
  public boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException {
    return this.mRemote.linkToDeath(paramDeathRecipient, paramLong);
  }
  
  public void notifySyspropsChanged() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hidl.base@1.0::IBase");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(257120595, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void ping() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hidl.base@1.0::IBase");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(256921159, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void powerResponse(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(6, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void resetSimResponse(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(7, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setHALInstrumentation() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hidl.base@1.0::IBase");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(256462420, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void statusIndication(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(8, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public String toString() {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(interfaceDescriptor());
      stringBuilder.append("@Proxy");
      return stringBuilder.toString();
    } catch (RemoteException remoteException) {
      return "[class or subclass of android.hardware.radio@1.0::ISapCallback]@Proxy";
    } 
  }
  
  public void transferAtrResponse(int paramInt1, int paramInt2, ArrayList<Byte> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeInt8Vector(paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(5, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void transferCardReaderStatusResponse(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeInt32(paramInt3);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(9, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void transferProtocolResponse(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::ISapCallback");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(11, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException {
    return this.mRemote.unlinkToDeath(paramDeathRecipient);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/ISapCallback$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
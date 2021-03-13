package android.hardware.cas.V1_2;

import android.hardware.cas.V1_0.ICas;
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

public final class Proxy implements ICas {
  private IHwBinder mRemote;
  
  public Proxy(IHwBinder paramIHwBinder) {
    Objects.requireNonNull(paramIHwBinder);
    this.mRemote = paramIHwBinder;
  }
  
  public IHwBinder asBinder() {
    return this.mRemote;
  }
  
  public int closeSession(ArrayList<Byte> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
    null.writeInt8Vector(paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(3, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readInt32();
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
  
  public final boolean equals(Object paramObject) {
    return HidlSupport.interfacesEqual((IHwInterface)this, paramObject);
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
  
  public void openSession(ICas.openSessionCallback paramopenSessionCallback) throws RemoteException {
    HwParcel hwParcel1 = new HwParcel();
    hwParcel1.writeInterfaceToken("android.hardware.cas@1.0::ICas");
    HwParcel hwParcel2 = new HwParcel();
    try {
      this.mRemote.transact(2, hwParcel1, hwParcel2, 0);
      hwParcel2.verifySuccess();
      hwParcel1.releaseTemporaryStorage();
      paramopenSessionCallback.onValues(hwParcel2.readInt32(), hwParcel2.readInt8Vector());
      return;
    } finally {
      hwParcel2.release();
    } 
  }
  
  public void openSession_1_2(int paramInt1, int paramInt2, ICas.openSession_1_2Callback paramopenSession_1_2Callback) throws RemoteException {
    HwParcel hwParcel1 = new HwParcel();
    hwParcel1.writeInterfaceToken("android.hardware.cas@1.2::ICas");
    hwParcel1.writeInt32(paramInt1);
    hwParcel1.writeInt32(paramInt2);
    HwParcel hwParcel2 = new HwParcel();
    try {
      this.mRemote.transact(12, hwParcel1, hwParcel2, 0);
      hwParcel2.verifySuccess();
      hwParcel1.releaseTemporaryStorage();
      paramopenSession_1_2Callback.onValues(hwParcel2.readInt32(), hwParcel2.readInt8Vector());
      return;
    } finally {
      hwParcel2.release();
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
  
  public int processEcm(ArrayList<Byte> paramArrayList1, ArrayList<Byte> paramArrayList2) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.cas@1.0::ICas");
    hwParcel2.writeInt8Vector(paramArrayList1);
    hwParcel2.writeInt8Vector(paramArrayList2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(5, hwParcel2, hwParcel1, 0);
      hwParcel1.verifySuccess();
      hwParcel2.releaseTemporaryStorage();
      return hwParcel1.readInt32();
    } finally {
      hwParcel1.release();
    } 
  }
  
  public int processEmm(ArrayList<Byte> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
    null.writeInt8Vector(paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(6, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readInt32();
    } finally {
      hwParcel.release();
    } 
  }
  
  public int provision(String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(8, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readInt32();
    } finally {
      hwParcel.release();
    } 
  }
  
  public int refreshEntitlements(int paramInt, ArrayList<Byte> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
    null.writeInt32(paramInt);
    null.writeInt8Vector(paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(9, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      paramInt = hwParcel.readInt32();
      return paramInt;
    } finally {
      hwParcel.release();
    } 
  }
  
  public int release() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(10, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readInt32();
    } finally {
      hwParcel.release();
    } 
  }
  
  public int sendEvent(int paramInt1, int paramInt2, ArrayList<Byte> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeInt8Vector(paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(7, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      paramInt1 = hwParcel.readInt32();
      return paramInt1;
    } finally {
      hwParcel.release();
    } 
  }
  
  public int sendSessionEvent(ArrayList<Byte> paramArrayList1, int paramInt1, int paramInt2, ArrayList<Byte> paramArrayList2) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.cas@1.1::ICas");
    hwParcel2.writeInt8Vector(paramArrayList1);
    hwParcel2.writeInt32(paramInt1);
    hwParcel2.writeInt32(paramInt2);
    hwParcel2.writeInt8Vector(paramArrayList2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(11, hwParcel2, hwParcel1, 0);
      hwParcel1.verifySuccess();
      hwParcel2.releaseTemporaryStorage();
      paramInt1 = hwParcel1.readInt32();
      return paramInt1;
    } finally {
      hwParcel1.release();
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
  
  public int setPrivateData(ArrayList<Byte> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.cas@1.0::ICas");
    null.writeInt8Vector(paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(1, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readInt32();
    } finally {
      hwParcel.release();
    } 
  }
  
  public int setSessionPrivateData(ArrayList<Byte> paramArrayList1, ArrayList<Byte> paramArrayList2) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.cas@1.0::ICas");
    hwParcel2.writeInt8Vector(paramArrayList1);
    hwParcel2.writeInt8Vector(paramArrayList2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(4, hwParcel2, hwParcel1, 0);
      hwParcel1.verifySuccess();
      hwParcel2.releaseTemporaryStorage();
      return hwParcel1.readInt32();
    } finally {
      hwParcel1.release();
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
      return "[class or subclass of android.hardware.cas@1.2::ICas]@Proxy";
    } 
  }
  
  public boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException {
    return this.mRemote.unlinkToDeath(paramDeathRecipient);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/cas/V1_2/ICas$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
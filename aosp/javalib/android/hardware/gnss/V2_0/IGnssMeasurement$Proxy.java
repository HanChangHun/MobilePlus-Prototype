package android.hardware.gnss.V2_0;

import android.hardware.gnss.V1_0.IGnssMeasurementCallback;
import android.hardware.gnss.V1_1.IGnssMeasurementCallback;
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

public final class Proxy implements IGnssMeasurement {
  private IHwBinder mRemote;
  
  public Proxy(IHwBinder paramIHwBinder) {
    Objects.requireNonNull(paramIHwBinder);
    this.mRemote = paramIHwBinder;
  }
  
  public IHwBinder asBinder() {
    return this.mRemote;
  }
  
  public void close() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssMeasurement");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(2, null, hwParcel, 0);
      hwParcel.verifySuccess();
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
  
  public int setCallback(IGnssMeasurementCallback paramIGnssMeasurementCallback) throws RemoteException {
    IHwBinder iHwBinder;
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssMeasurement");
    if (paramIGnssMeasurementCallback == null) {
      paramIGnssMeasurementCallback = null;
    } else {
      iHwBinder = paramIGnssMeasurementCallback.asBinder();
    } 
    null.writeStrongBinder(iHwBinder);
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
  
  public int setCallback_1_1(IGnssMeasurementCallback paramIGnssMeasurementCallback, boolean paramBoolean) throws RemoteException {
    IHwBinder iHwBinder;
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.1::IGnssMeasurement");
    if (paramIGnssMeasurementCallback == null) {
      paramIGnssMeasurementCallback = null;
    } else {
      iHwBinder = paramIGnssMeasurementCallback.asBinder();
    } 
    null.writeStrongBinder(iHwBinder);
    null.writeBool(paramBoolean);
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
  
  public int setCallback_2_0(IGnssMeasurementCallback paramIGnssMeasurementCallback, boolean paramBoolean) throws RemoteException {
    IHwBinder iHwBinder;
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.0::IGnssMeasurement");
    if (paramIGnssMeasurementCallback == null) {
      paramIGnssMeasurementCallback = null;
    } else {
      iHwBinder = paramIGnssMeasurementCallback.asBinder();
    } 
    null.writeStrongBinder(iHwBinder);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(4, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readInt32();
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
  
  public String toString() {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(interfaceDescriptor());
      stringBuilder.append("@Proxy");
      return stringBuilder.toString();
    } catch (RemoteException remoteException) {
      return "[class or subclass of android.hardware.gnss@2.0::IGnssMeasurement]@Proxy";
    } 
  }
  
  public boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException {
    return this.mRemote.unlinkToDeath(paramDeathRecipient);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_0/IGnssMeasurement$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
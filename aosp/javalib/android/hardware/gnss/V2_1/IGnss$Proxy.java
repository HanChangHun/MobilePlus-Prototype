package android.hardware.gnss.V2_1;

import android.hardware.gnss.V1_0.GnssLocation;
import android.hardware.gnss.V1_0.IAGnss;
import android.hardware.gnss.V1_0.IAGnssRil;
import android.hardware.gnss.V1_0.IGnssBatching;
import android.hardware.gnss.V1_0.IGnssCallback;
import android.hardware.gnss.V1_0.IGnssConfiguration;
import android.hardware.gnss.V1_0.IGnssDebug;
import android.hardware.gnss.V1_0.IGnssGeofencing;
import android.hardware.gnss.V1_0.IGnssMeasurement;
import android.hardware.gnss.V1_0.IGnssNavigationMessage;
import android.hardware.gnss.V1_0.IGnssNi;
import android.hardware.gnss.V1_0.IGnssXtra;
import android.hardware.gnss.V1_1.IGnssCallback;
import android.hardware.gnss.V1_1.IGnssConfiguration;
import android.hardware.gnss.V1_1.IGnssMeasurement;
import android.hardware.gnss.V2_0.GnssLocation;
import android.hardware.gnss.V2_0.IAGnss;
import android.hardware.gnss.V2_0.IAGnssRil;
import android.hardware.gnss.V2_0.IGnssBatching;
import android.hardware.gnss.V2_0.IGnssCallback;
import android.hardware.gnss.V2_0.IGnssConfiguration;
import android.hardware.gnss.V2_0.IGnssDebug;
import android.hardware.gnss.V2_0.IGnssMeasurement;
import android.hardware.gnss.measurement_corrections.V1_0.IMeasurementCorrections;
import android.hardware.gnss.measurement_corrections.V1_1.IMeasurementCorrections;
import android.hardware.gnss.visibility_control.V1_0.IGnssVisibilityControl;
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

public final class Proxy implements IGnss {
  private IHwBinder mRemote;
  
  public Proxy(IHwBinder paramIHwBinder) {
    Objects.requireNonNull(paramIHwBinder);
    this.mRemote = paramIHwBinder;
  }
  
  public IHwBinder asBinder() {
    return this.mRemote;
  }
  
  public void cleanup() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(4, null, hwParcel, 0);
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
  
  public void deleteAidingData(short paramShort) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    null.writeInt16(paramShort);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(7, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
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
  
  public IAGnss getExtensionAGnss() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(11, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IAGnss.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IAGnssRil getExtensionAGnssRil() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(9, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IAGnssRil.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IAGnssRil getExtensionAGnssRil_2_0() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(28, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IAGnssRil.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IAGnss getExtensionAGnss_2_0() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(27, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IAGnss.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssAntennaInfo getExtensionGnssAntennaInfo() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.1::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(38, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssAntennaInfo.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssBatching getExtensionGnssBatching() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(18, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssBatching.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssBatching getExtensionGnssBatching_2_0() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(32, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssBatching.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssConfiguration getExtensionGnssConfiguration() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(16, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssConfiguration.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssConfiguration getExtensionGnssConfiguration_1_1() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.1::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(21, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssConfiguration.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssConfiguration getExtensionGnssConfiguration_2_0() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(25, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssConfiguration.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssConfiguration getExtensionGnssConfiguration_2_1() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.1::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(36, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssConfiguration.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssDebug getExtensionGnssDebug() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(17, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssDebug.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssDebug getExtensionGnssDebug_2_0() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(26, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssDebug.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssGeofencing getExtensionGnssGeofencing() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(10, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssGeofencing.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssMeasurement getExtensionGnssMeasurement() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(13, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssMeasurement.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssMeasurement getExtensionGnssMeasurement_1_1() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.1::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(22, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssMeasurement.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssMeasurement getExtensionGnssMeasurement_2_0() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(29, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssMeasurement.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssMeasurement getExtensionGnssMeasurement_2_1() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.1::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(35, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssMeasurement.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssNavigationMessage getExtensionGnssNavigationMessage() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(14, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssNavigationMessage.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssNi getExtensionGnssNi() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(12, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssNi.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IMeasurementCorrections getExtensionMeasurementCorrections() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(30, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IMeasurementCorrections.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IMeasurementCorrections getExtensionMeasurementCorrections_1_1() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.1::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(37, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IMeasurementCorrections.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssVisibilityControl getExtensionVisibilityControl() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(31, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssVisibilityControl.asInterface(hwParcel.readStrongBinder());
    } finally {
      hwParcel.release();
    } 
  }
  
  public IGnssXtra getExtensionXtra() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(15, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return IGnssXtra.asInterface(hwParcel.readStrongBinder());
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
      HwBlob hwBlob1 = hwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = hwParcel.readEmbeddedBuffer((i * 32), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        byte[] arrayOfByte = new byte[32];
        hwBlob2.copyToInt8Array((b * 32), arrayOfByte, 32);
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
  
  public boolean injectBestLocation(GnssLocation paramGnssLocation) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.1::IGnss");
    paramGnssLocation.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(23, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readBool();
    } finally {
      hwParcel.release();
    } 
  }
  
  public boolean injectBestLocation_2_0(GnssLocation paramGnssLocation) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.0::IGnss");
    paramGnssLocation.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(33, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readBool();
    } finally {
      hwParcel.release();
    } 
  }
  
  public boolean injectLocation(double paramDouble1, double paramDouble2, float paramFloat) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    null.writeDouble(paramDouble1);
    null.writeDouble(paramDouble2);
    null.writeFloat(paramFloat);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(6, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readBool();
    } finally {
      hwParcel.release();
    } 
  }
  
  public boolean injectTime(long paramLong1, long paramLong2, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    null.writeInt64(paramLong1);
    null.writeInt64(paramLong2);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(5, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readBool();
    } finally {
      hwParcel.release();
    } 
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
  
  public boolean setCallback(IGnssCallback paramIGnssCallback) throws RemoteException {
    IHwBinder iHwBinder;
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    if (paramIGnssCallback == null) {
      paramIGnssCallback = null;
    } else {
      iHwBinder = paramIGnssCallback.asBinder();
    } 
    null.writeStrongBinder(iHwBinder);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(1, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readBool();
    } finally {
      hwParcel.release();
    } 
  }
  
  public boolean setCallback_1_1(IGnssCallback paramIGnssCallback) throws RemoteException {
    IHwBinder iHwBinder;
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.1::IGnss");
    if (paramIGnssCallback == null) {
      paramIGnssCallback = null;
    } else {
      iHwBinder = paramIGnssCallback.asBinder();
    } 
    null.writeStrongBinder(iHwBinder);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(19, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readBool();
    } finally {
      hwParcel.release();
    } 
  }
  
  public boolean setCallback_2_0(IGnssCallback paramIGnssCallback) throws RemoteException {
    IHwBinder iHwBinder;
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.0::IGnss");
    if (paramIGnssCallback == null) {
      paramIGnssCallback = null;
    } else {
      iHwBinder = paramIGnssCallback.asBinder();
    } 
    null.writeStrongBinder(iHwBinder);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(24, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readBool();
    } finally {
      hwParcel.release();
    } 
  }
  
  public boolean setCallback_2_1(IGnssCallback paramIGnssCallback) throws RemoteException {
    IHwBinder iHwBinder;
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@2.1::IGnss");
    if (paramIGnssCallback == null) {
      paramIGnssCallback = null;
    } else {
      iHwBinder = paramIGnssCallback.asBinder();
    } 
    null.writeStrongBinder(iHwBinder);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(34, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readBool();
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
  
  public boolean setPositionMode(byte paramByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    null.writeInt8(paramByte);
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeInt32(paramInt3);
    null.writeInt32(paramInt4);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(8, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readBool();
    } finally {
      hwParcel.release();
    } 
  }
  
  public boolean setPositionMode_1_1(byte paramByte, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.1::IGnss");
    null.writeInt8(paramByte);
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeInt32(paramInt3);
    null.writeInt32(paramInt4);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(20, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      paramBoolean = hwParcel.readBool();
      return paramBoolean;
    } finally {
      hwParcel.release();
    } 
  }
  
  public boolean start() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(2, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readBool();
    } finally {
      hwParcel.release();
    } 
  }
  
  public boolean stop() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.gnss@1.0::IGnss");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(3, null, hwParcel, 0);
      hwParcel.verifySuccess();
      null.releaseTemporaryStorage();
      return hwParcel.readBool();
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
      return "[class or subclass of android.hardware.gnss@2.1::IGnss]@Proxy";
    } 
  }
  
  public boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException {
    return this.mRemote.unlinkToDeath(paramDeathRecipient);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V2_1/IGnss$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
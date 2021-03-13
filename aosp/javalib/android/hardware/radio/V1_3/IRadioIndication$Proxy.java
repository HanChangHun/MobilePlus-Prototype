package android.hardware.radio.V1_3;

import android.hardware.radio.V1_0.CdmaCallWaiting;
import android.hardware.radio.V1_0.CdmaInformationRecords;
import android.hardware.radio.V1_0.CdmaSignalInfoRecord;
import android.hardware.radio.V1_0.CdmaSmsMessage;
import android.hardware.radio.V1_0.CellInfo;
import android.hardware.radio.V1_0.HardwareConfig;
import android.hardware.radio.V1_0.LceDataInfo;
import android.hardware.radio.V1_0.PcoDataInfo;
import android.hardware.radio.V1_0.RadioCapability;
import android.hardware.radio.V1_0.SetupDataCallResult;
import android.hardware.radio.V1_0.SignalStrength;
import android.hardware.radio.V1_0.SimRefreshResult;
import android.hardware.radio.V1_0.StkCcUnsolSsResult;
import android.hardware.radio.V1_0.SuppSvcNotification;
import android.hardware.radio.V1_1.KeepaliveStatus;
import android.hardware.radio.V1_1.NetworkScanResult;
import android.hardware.radio.V1_2.CellInfo;
import android.hardware.radio.V1_2.LinkCapacityEstimate;
import android.hardware.radio.V1_2.NetworkScanResult;
import android.hardware.radio.V1_2.PhysicalChannelConfig;
import android.hardware.radio.V1_2.SignalStrength;
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

public final class Proxy implements IRadioIndication {
  private IHwBinder mRemote;
  
  public Proxy(IHwBinder paramIHwBinder) {
    Objects.requireNonNull(paramIHwBinder);
    this.mRemote = paramIHwBinder;
  }
  
  public IHwBinder asBinder() {
    return this.mRemote;
  }
  
  public void callRing(int paramInt, boolean paramBoolean, CdmaSignalInfoRecord paramCdmaSignalInfoRecord) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    paramCdmaSignalInfoRecord.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(18, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void callStateChanged(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
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
  
  public void carrierInfoForImsiEncryption(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.1::IRadioIndication");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(46, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void cdmaCallWaiting(int paramInt, CdmaCallWaiting paramCdmaCallWaiting) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    paramCdmaCallWaiting.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(25, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void cdmaInfoRec(int paramInt, CdmaInformationRecords paramCdmaInformationRecords) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    paramCdmaInformationRecords.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(27, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void cdmaNewSms(int paramInt, CdmaSmsMessage paramCdmaSmsMessage) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    paramCdmaSmsMessage.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(20, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void cdmaOtaProvisionStatus(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(26, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void cdmaPrlChanged(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(31, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void cdmaRuimSmsStorageFull(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(22, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void cdmaSubscriptionSourceChanged(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(30, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void cellInfoList(int paramInt, ArrayList<CellInfo> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    CellInfo.writeVectorToParcel(null, paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(35, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void cellInfoList_1_2(int paramInt, ArrayList<CellInfo> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.2::IRadioIndication");
    null.writeInt32(paramInt);
    CellInfo.writeVectorToParcel(null, paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(50, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void currentLinkCapacityEstimate(int paramInt, LinkCapacityEstimate paramLinkCapacityEstimate) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.2::IRadioIndication");
    null.writeInt32(paramInt);
    paramLinkCapacityEstimate.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(51, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void currentPhysicalChannelConfigs(int paramInt, ArrayList<PhysicalChannelConfig> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.2::IRadioIndication");
    null.writeInt32(paramInt);
    PhysicalChannelConfig.writeVectorToParcel(null, paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(52, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void currentSignalStrength(int paramInt, SignalStrength paramSignalStrength) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    paramSignalStrength.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(9, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void currentSignalStrength_1_2(int paramInt, SignalStrength paramSignalStrength) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.2::IRadioIndication");
    null.writeInt32(paramInt);
    paramSignalStrength.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(53, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void dataCallListChanged(int paramInt, ArrayList<SetupDataCallResult> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    SetupDataCallResult.writeVectorToParcel(null, paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(10, null, hwParcel, 1);
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
  
  public void enterEmergencyCallbackMode(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(24, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public final boolean equals(Object paramObject) {
    return HidlSupport.interfacesEqual((IHwInterface)this, paramObject);
  }
  
  public void exitEmergencyCallbackMode(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(32, null, hwParcel, 1);
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
  
  public void hardwareConfigChanged(int paramInt, ArrayList<HardwareConfig> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    HardwareConfig.writeVectorToParcel(null, paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(39, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public final int hashCode() {
    return asBinder().hashCode();
  }
  
  public void imsNetworkStateChanged(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(36, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void indicateRingbackTone(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(28, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
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
  
  public void keepaliveStatus(int paramInt, KeepaliveStatus paramKeepaliveStatus) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.1::IRadioIndication");
    null.writeInt32(paramInt);
    paramKeepaliveStatus.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(48, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void lceData(int paramInt, LceDataInfo paramLceDataInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    paramLceDataInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(43, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException {
    return this.mRemote.linkToDeath(paramDeathRecipient, paramLong);
  }
  
  public void modemReset(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(45, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void networkScanResult(int paramInt, NetworkScanResult paramNetworkScanResult) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.1::IRadioIndication");
    null.writeInt32(paramInt);
    paramNetworkScanResult.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(47, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void networkScanResult_1_2(int paramInt, NetworkScanResult paramNetworkScanResult) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.2::IRadioIndication");
    null.writeInt32(paramInt);
    paramNetworkScanResult.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(49, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void networkStateChanged(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(3, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void newBroadcastSms(int paramInt, ArrayList<Byte> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    null.writeInt8Vector(paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(21, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void newSms(int paramInt, ArrayList<Byte> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
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
  
  public void newSmsOnSim(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
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
  
  public void newSmsStatusReport(int paramInt, ArrayList<Byte> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
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
  
  public void nitzTimeReceived(int paramInt, String paramString, long paramLong) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    null.writeInt64(paramLong);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(8, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
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
  
  public void onSupplementaryServiceIndication(int paramInt, StkCcUnsolSsResult paramStkCcUnsolSsResult) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    paramStkCcUnsolSsResult.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(41, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void onUssd(int paramInt1, int paramInt2, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(7, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void pcoData(int paramInt, PcoDataInfo paramPcoDataInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    paramPcoDataInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(44, null, hwParcel, 1);
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
  
  public void radioCapabilityIndication(int paramInt, RadioCapability paramRadioCapability) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    paramRadioCapability.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(40, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void radioStateChanged(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(1, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void resendIncallMute(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(29, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void restrictedStateChanged(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(23, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void rilConnected(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(33, null, hwParcel, 1);
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
  
  public void simRefresh(int paramInt, SimRefreshResult paramSimRefreshResult) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    paramSimRefreshResult.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(17, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void simSmsStorageFull(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(16, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void simStatusChanged(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(19, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void srvccStateNotify(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(38, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void stkCallControlAlphaNotify(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(42, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void stkCallSetup(int paramInt, long paramLong) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    null.writeInt64(paramLong);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(15, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void stkEventNotify(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(14, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void stkProactiveCommand(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(13, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void stkSessionEnd(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(12, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void subscriptionStatusChanged(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(37, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void suppSvcNotify(int paramInt, SuppSvcNotification paramSuppSvcNotification) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt);
    paramSuppSvcNotification.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(11, null, hwParcel, 1);
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
      return "[class or subclass of android.hardware.radio@1.3::IRadioIndication]@Proxy";
    } 
  }
  
  public boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException {
    return this.mRemote.unlinkToDeath(paramDeathRecipient);
  }
  
  public void voiceRadioTechChanged(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioIndication");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(34, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_3/IRadioIndication$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
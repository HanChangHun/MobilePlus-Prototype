package android.hardware.radio.V1_2;

import android.hardware.radio.V1_0.CallForwardInfo;
import android.hardware.radio.V1_0.CarrierRestrictions;
import android.hardware.radio.V1_0.CdmaBroadcastSmsConfigInfo;
import android.hardware.radio.V1_0.CdmaSmsAck;
import android.hardware.radio.V1_0.CdmaSmsMessage;
import android.hardware.radio.V1_0.CdmaSmsWriteArgs;
import android.hardware.radio.V1_0.DataProfileInfo;
import android.hardware.radio.V1_0.Dial;
import android.hardware.radio.V1_0.GsmBroadcastSmsConfigInfo;
import android.hardware.radio.V1_0.GsmSmsMessage;
import android.hardware.radio.V1_0.IRadioIndication;
import android.hardware.radio.V1_0.IRadioResponse;
import android.hardware.radio.V1_0.IccIo;
import android.hardware.radio.V1_0.ImsSmsMessage;
import android.hardware.radio.V1_0.NvWriteItem;
import android.hardware.radio.V1_0.RadioCapability;
import android.hardware.radio.V1_0.SelectUiccSub;
import android.hardware.radio.V1_0.SimApdu;
import android.hardware.radio.V1_0.SmsWriteArgs;
import android.hardware.radio.V1_1.ImsiEncryptionInfo;
import android.hardware.radio.V1_1.KeepaliveRequest;
import android.hardware.radio.V1_1.NetworkScanRequest;
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

public final class Proxy implements IRadio {
  private IHwBinder mRemote;
  
  public Proxy(IHwBinder paramIHwBinder) {
    Objects.requireNonNull(paramIHwBinder);
    this.mRemote = paramIHwBinder;
  }
  
  public void acceptCall(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(39, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void acknowledgeIncomingGsmSmsWithPdu(int paramInt, boolean paramBoolean, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(97, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void acknowledgeLastIncomingCdmaSms(int paramInt, CdmaSmsAck paramCdmaSmsAck) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramCdmaSmsAck.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(79, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void acknowledgeLastIncomingGsmSms(int paramInt1, boolean paramBoolean, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeBool(paramBoolean);
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
  
  public IHwBinder asBinder() {
    return this.mRemote;
  }
  
  public void cancelPendingUssd(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(31, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void changeIccPin2ForApp(int paramInt, String paramString1, String paramString2, String paramString3) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    hwParcel2.writeInt32(paramInt);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeString(paramString2);
    hwParcel2.writeString(paramString3);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(8, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void changeIccPinForApp(int paramInt, String paramString1, String paramString2, String paramString3) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    hwParcel2.writeInt32(paramInt);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeString(paramString2);
    hwParcel2.writeString(paramString3);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(7, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void conference(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(17, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void deactivateDataCall(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(40, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void deactivateDataCall_1_2(int paramInt1, int paramInt2, int paramInt3) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.2::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeInt32(paramInt3);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(142, null, hwParcel, 1);
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
  
  public void deleteSmsOnRuim(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(88, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void deleteSmsOnSim(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(58, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void dial(int paramInt, Dial paramDial) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramDial.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(11, null, hwParcel, 1);
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
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(90, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void explicitCallTransfer(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(64, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getAllowedCarriers(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(126, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getAvailableBandModes(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(60, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getAvailableNetworks(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(47, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getBasebandVersion(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(50, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getCDMASubscription(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(86, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getCallForwardStatus(int paramInt, CallForwardInfo paramCallForwardInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramCallForwardInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(34, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getCallWaiting(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(36, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getCdmaBroadcastConfig(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(83, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getCdmaRoamingPreference(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(71, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getCdmaSubscriptionSource(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(95, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getCellInfoList(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(100, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getClip(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(54, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getClir(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
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
  
  public void getCurrentCalls(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
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
  
  public void getDataCallList(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(55, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getDataRegistrationState(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
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
  
  public void getDeviceIdentity(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(89, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getFacilityLockForApp(int paramInt1, String paramString1, String paramString2, int paramInt2, String paramString3) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    hwParcel2.writeInt32(paramInt1);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeString(paramString2);
    hwParcel2.writeInt32(paramInt2);
    hwParcel2.writeString(paramString3);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(41, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getGsmBroadcastConfig(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(80, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getHardwareConfig(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(115, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
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
  
  public void getIccCardStatus(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
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
  
  public void getImsRegistrationState(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(103, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getImsiForApp(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(12, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getLastCallFailCause(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
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
  
  public void getModemActivityInfo(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(124, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getMute(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(53, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getNeighboringCids(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(67, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getNetworkSelectionMode(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(44, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getOperator(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(23, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getPreferredNetworkType(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(66, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getPreferredVoicePrivacy(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(75, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getRadioCapability(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(119, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getSignalStrength(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(20, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getSmscAddress(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(91, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getTTYMode(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(73, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getVoiceRadioTechnology(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(99, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getVoiceRegistrationState(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(21, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void handleStkCallSetupRequestFromSim(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(63, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void hangup(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(13, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void hangupForegroundResumeBackground(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(15, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void hangupWaitingOrBackground(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(14, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public final int hashCode() {
    return asBinder().hashCode();
  }
  
  public void iccCloseLogicalChannel(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(107, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void iccIOForApp(int paramInt, IccIo paramIccIo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramIccIo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(29, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void iccOpenLogicalChannel(int paramInt1, String paramString, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeString(paramString);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(106, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void iccTransmitApduBasicChannel(int paramInt, SimApdu paramSimApdu) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramSimApdu.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(105, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void iccTransmitApduLogicalChannel(int paramInt, SimApdu paramSimApdu) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramSimApdu.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(108, null, hwParcel, 1);
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
  
  public void nvReadItem(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(109, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void nvResetConfig(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(112, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void nvWriteCdmaPrl(int paramInt, ArrayList<Byte> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeInt8Vector(paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(111, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void nvWriteItem(int paramInt, NvWriteItem paramNvWriteItem) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramNvWriteItem.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(110, null, hwParcel, 1);
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
  
  public void pullLceData(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(123, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void rejectCall(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(18, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void reportSmsMemoryStatus(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(93, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void reportStkServiceIsRunning(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(94, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void requestIccSimAuthentication(int paramInt1, int paramInt2, String paramString1, String paramString2) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    hwParcel2.writeInt32(paramInt1);
    hwParcel2.writeInt32(paramInt2);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeString(paramString2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(116, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void requestIsimAuthentication(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(96, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void requestShutdown(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(118, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void responseAcknowledgement() throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(130, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendBurstDtmf(int paramInt1, String paramString, int paramInt2, int paramInt3) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeString(paramString);
    null.writeInt32(paramInt2);
    null.writeInt32(paramInt3);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(77, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendCDMAFeatureCode(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(76, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendCdmaSms(int paramInt, CdmaSmsMessage paramCdmaSmsMessage) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramCdmaSmsMessage.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(78, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendDeviceState(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(127, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendDtmf(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(25, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendEnvelope(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(61, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendEnvelopeWithStatus(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(98, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendImsSms(int paramInt, ImsSmsMessage paramImsSmsMessage) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramImsSmsMessage.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(104, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendSMSExpectMore(int paramInt, GsmSmsMessage paramGsmSmsMessage) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramGsmSmsMessage.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(27, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendSms(int paramInt, GsmSmsMessage paramGsmSmsMessage) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramGsmSmsMessage.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(26, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendTerminalResponseToSim(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(62, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendUssd(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(30, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void separateConnection(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(51, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setAllowedCarriers(int paramInt, boolean paramBoolean, CarrierRestrictions paramCarrierRestrictions) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    paramCarrierRestrictions.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(125, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setBandMode(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(59, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setBarringPassword(int paramInt, String paramString1, String paramString2, String paramString3) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    hwParcel2.writeInt32(paramInt);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeString(paramString2);
    hwParcel2.writeString(paramString3);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(43, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void setCallForward(int paramInt, CallForwardInfo paramCallForwardInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramCallForwardInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(35, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCallWaiting(int paramInt1, boolean paramBoolean, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeBool(paramBoolean);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(37, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCarrierInfoForImsiEncryption(int paramInt, ImsiEncryptionInfo paramImsiEncryptionInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.1::IRadio");
    null.writeInt32(paramInt);
    paramImsiEncryptionInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(131, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCdmaBroadcastActivation(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(85, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCdmaBroadcastConfig(int paramInt, ArrayList<CdmaBroadcastSmsConfigInfo> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    CdmaBroadcastSmsConfigInfo.writeVectorToParcel(null, paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(84, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCdmaRoamingPreference(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(70, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCdmaSubscriptionSource(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(69, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCellInfoListRate(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(101, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setClir(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(33, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setDataAllowed(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(114, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setDataProfile(int paramInt, ArrayList<DataProfileInfo> paramArrayList, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    DataProfileInfo.writeVectorToParcel(null, paramArrayList);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(117, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setFacilityLockForApp(int paramInt1, String paramString1, boolean paramBoolean, String paramString2, int paramInt2, String paramString3) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    hwParcel2.writeInt32(paramInt1);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeBool(paramBoolean);
    hwParcel2.writeString(paramString2);
    hwParcel2.writeInt32(paramInt2);
    hwParcel2.writeString(paramString3);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(42, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void setGsmBroadcastActivation(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(82, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setGsmBroadcastConfig(int paramInt, ArrayList<GsmBroadcastSmsConfigInfo> paramArrayList) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    GsmBroadcastSmsConfigInfo.writeVectorToParcel(null, paramArrayList);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(81, null, hwParcel, 1);
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
  
  public void setIndicationFilter(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(128, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setIndicationFilter_1_2(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.2::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(138, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setInitialAttachApn(int paramInt, DataProfileInfo paramDataProfileInfo, boolean paramBoolean1, boolean paramBoolean2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramDataProfileInfo.writeToParcel(null);
    null.writeBool(paramBoolean1);
    null.writeBool(paramBoolean2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(102, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setLinkCapacityReportingCriteria(int paramInt1, int paramInt2, int paramInt3, int paramInt4, ArrayList<Integer> paramArrayList1, ArrayList<Integer> paramArrayList2, int paramInt5) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.2::IRadio");
    hwParcel2.writeInt32(paramInt1);
    hwParcel2.writeInt32(paramInt2);
    hwParcel2.writeInt32(paramInt3);
    hwParcel2.writeInt32(paramInt4);
    hwParcel2.writeInt32Vector(paramArrayList1);
    hwParcel2.writeInt32Vector(paramArrayList2);
    hwParcel2.writeInt32(paramInt5);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(140, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void setLocationUpdates(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(68, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setMute(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(52, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setNetworkSelectionModeAutomatic(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(45, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setNetworkSelectionModeManual(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(46, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setPreferredNetworkType(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(65, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setPreferredVoicePrivacy(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(74, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setRadioCapability(int paramInt, RadioCapability paramRadioCapability) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramRadioCapability.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(120, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setRadioPower(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(24, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setResponseFunctions(IRadioResponse paramIRadioResponse, IRadioIndication paramIRadioIndication) throws RemoteException {
    IHwBinder iHwBinder1;
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    IHwBinder iHwBinder2 = null;
    if (paramIRadioResponse == null) {
      paramIRadioResponse = null;
    } else {
      iHwBinder1 = paramIRadioResponse.asBinder();
    } 
    hwParcel2.writeStrongBinder(iHwBinder1);
    if (paramIRadioIndication == null) {
      iHwBinder1 = iHwBinder2;
    } else {
      iHwBinder1 = paramIRadioIndication.asBinder();
    } 
    hwParcel2.writeStrongBinder(iHwBinder1);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(1, hwParcel2, hwParcel1, 0);
      hwParcel1.verifySuccess();
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void setSignalStrengthReportingCriteria(int paramInt1, int paramInt2, int paramInt3, ArrayList<Integer> paramArrayList, int paramInt4) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.2::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeInt32(paramInt3);
    null.writeInt32Vector(paramArrayList);
    null.writeInt32(paramInt4);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(139, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setSimCardPower(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(129, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setSimCardPower_1_1(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.1::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(132, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setSmscAddress(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(92, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setSuppServiceNotifications(int paramInt, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(56, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setTTYMode(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(72, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setUiccSubscription(int paramInt, SelectUiccSub paramSelectUiccSub) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramSelectUiccSub.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(113, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setupDataCall(int paramInt1, int paramInt2, DataProfileInfo paramDataProfileInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    paramDataProfileInfo.writeToParcel(null);
    null.writeBool(paramBoolean1);
    null.writeBool(paramBoolean2);
    null.writeBool(paramBoolean3);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(28, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setupDataCall_1_2(int paramInt1, int paramInt2, DataProfileInfo paramDataProfileInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt3, ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.2::IRadio");
    hwParcel2.writeInt32(paramInt1);
    hwParcel2.writeInt32(paramInt2);
    paramDataProfileInfo.writeToParcel(hwParcel2);
    hwParcel2.writeBool(paramBoolean1);
    hwParcel2.writeBool(paramBoolean2);
    hwParcel2.writeBool(paramBoolean3);
    hwParcel2.writeInt32(paramInt3);
    hwParcel2.writeStringVector(paramArrayList1);
    hwParcel2.writeStringVector(paramArrayList2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(141, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void startDtmf(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(48, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void startKeepalive(int paramInt, KeepaliveRequest paramKeepaliveRequest) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.1::IRadio");
    null.writeInt32(paramInt);
    paramKeepaliveRequest.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(135, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void startLceService(int paramInt1, int paramInt2, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(121, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void startNetworkScan(int paramInt, NetworkScanRequest paramNetworkScanRequest) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.1::IRadio");
    null.writeInt32(paramInt);
    paramNetworkScanRequest.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(133, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void startNetworkScan_1_2(int paramInt, NetworkScanRequest paramNetworkScanRequest) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.2::IRadio");
    null.writeInt32(paramInt);
    paramNetworkScanRequest.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(137, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void stopDtmf(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(49, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void stopKeepalive(int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.1::IRadio");
    null.writeInt32(paramInt1);
    null.writeInt32(paramInt2);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(136, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void stopLceService(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(122, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void stopNetworkScan(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.1::IRadio");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(134, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void supplyIccPin2ForApp(int paramInt, String paramString1, String paramString2) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    hwParcel2.writeInt32(paramInt);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeString(paramString2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(5, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void supplyIccPinForApp(int paramInt, String paramString1, String paramString2) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    hwParcel2.writeInt32(paramInt);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeString(paramString2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(3, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void supplyIccPuk2ForApp(int paramInt, String paramString1, String paramString2, String paramString3) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    hwParcel2.writeInt32(paramInt);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeString(paramString2);
    hwParcel2.writeString(paramString3);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(6, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void supplyIccPukForApp(int paramInt, String paramString1, String paramString2, String paramString3) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    hwParcel2.writeInt32(paramInt);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeString(paramString2);
    hwParcel2.writeString(paramString3);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(4, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void supplyNetworkDepersonalization(int paramInt, String paramString) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    null.writeString(paramString);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(9, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void switchWaitingOrHoldingAndActive(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
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
  
  public String toString() {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append(interfaceDescriptor());
      stringBuilder.append("@Proxy");
      return stringBuilder.toString();
    } catch (RemoteException remoteException) {
      return "[class or subclass of android.hardware.radio@1.2::IRadio]@Proxy";
    } 
  }
  
  public boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException {
    return this.mRemote.unlinkToDeath(paramDeathRecipient);
  }
  
  public void writeSmsToRuim(int paramInt, CdmaSmsWriteArgs paramCdmaSmsWriteArgs) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramCdmaSmsWriteArgs.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(87, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void writeSmsToSim(int paramInt, SmsWriteArgs paramSmsWriteArgs) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
    null.writeInt32(paramInt);
    paramSmsWriteArgs.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(57, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/IRadio$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
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

public final class Proxy implements IRadioResponse {
  private IHwBinder mRemote;
  
  public Proxy(IHwBinder paramIHwBinder) {
    Objects.requireNonNull(paramIHwBinder);
    this.mRemote = paramIHwBinder;
  }
  
  public void acceptCallResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(38, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void acknowledgeIncomingGsmSmsWithPduResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(96, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void acknowledgeLastIncomingCdmaSmsResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(78, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void acknowledgeLastIncomingGsmSmsResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(37, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void acknowledgeRequest(int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(129, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public IHwBinder asBinder() {
    return this.mRemote;
  }
  
  public void cancelPendingUssdResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(30, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void changeIccPin2ForAppResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(7, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void changeIccPinForAppResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(6, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void conferenceResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(16, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void deactivateDataCallResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(39, null, hwParcel, 1);
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
  
  public void deleteSmsOnRuimResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(87, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void deleteSmsOnSimResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(57, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void dialResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(10, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public final boolean equals(Object paramObject) {
    return HidlSupport.interfacesEqual((IHwInterface)this, paramObject);
  }
  
  public void exitEmergencyCallbackModeResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(89, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void explicitCallTransferResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(63, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getAllowedCarriersResponse(RadioResponseInfo paramRadioResponseInfo, boolean paramBoolean, CarrierRestrictions paramCarrierRestrictions) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    hwParcel2.writeBool(paramBoolean);
    paramCarrierRestrictions.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(125, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getAvailableBandModesResponse(RadioResponseInfo paramRadioResponseInfo, ArrayList<Integer> paramArrayList) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    hwParcel2.writeInt32Vector(paramArrayList);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(59, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getAvailableNetworksResponse(RadioResponseInfo paramRadioResponseInfo, ArrayList<OperatorInfo> paramArrayList) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    OperatorInfo.writeVectorToParcel(hwParcel2, paramArrayList);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(46, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getBasebandVersionResponse(RadioResponseInfo paramRadioResponseInfo, String paramString) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    hwParcel2.writeString(paramString);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(49, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getCDMASubscriptionResponse(RadioResponseInfo paramRadioResponseInfo, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeString(paramString2);
    hwParcel2.writeString(paramString3);
    hwParcel2.writeString(paramString4);
    hwParcel2.writeString(paramString5);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(85, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getCallForwardStatusResponse(RadioResponseInfo paramRadioResponseInfo, ArrayList<CallForwardInfo> paramArrayList) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    CallForwardInfo.writeVectorToParcel(hwParcel2, paramArrayList);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(33, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getCallWaitingResponse(RadioResponseInfo paramRadioResponseInfo, boolean paramBoolean, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeBool(paramBoolean);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(35, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getCdmaBroadcastConfigResponse(RadioResponseInfo paramRadioResponseInfo, ArrayList<CdmaBroadcastSmsConfigInfo> paramArrayList) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    CdmaBroadcastSmsConfigInfo.writeVectorToParcel(hwParcel2, paramArrayList);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(82, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getCdmaRoamingPreferenceResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(70, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getCdmaSubscriptionSourceResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
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
  
  public void getCellInfoListResponse(RadioResponseInfo paramRadioResponseInfo, ArrayList<CellInfo> paramArrayList) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    CellInfo.writeVectorToParcel(hwParcel2, paramArrayList);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(99, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getClipResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
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
  
  public void getClirResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt1, int paramInt2) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
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
  
  public void getCurrentCallsResponse(RadioResponseInfo paramRadioResponseInfo, ArrayList<Call> paramArrayList) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    Call.writeVectorToParcel(hwParcel2, paramArrayList);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(9, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getDataCallListResponse(RadioResponseInfo paramRadioResponseInfo, ArrayList<SetupDataCallResult> paramArrayList) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    SetupDataCallResult.writeVectorToParcel(hwParcel2, paramArrayList);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(54, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getDataRegistrationStateResponse(RadioResponseInfo paramRadioResponseInfo, DataRegStateResult paramDataRegStateResult) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramDataRegStateResult.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(21, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
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
  
  public void getDeviceIdentityResponse(RadioResponseInfo paramRadioResponseInfo, String paramString1, String paramString2, String paramString3, String paramString4) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeString(paramString2);
    hwParcel2.writeString(paramString3);
    hwParcel2.writeString(paramString4);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(88, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getFacilityLockForAppResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(40, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getGsmBroadcastConfigResponse(RadioResponseInfo paramRadioResponseInfo, ArrayList<GsmBroadcastSmsConfigInfo> paramArrayList) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    GsmBroadcastSmsConfigInfo.writeVectorToParcel(hwParcel2, paramArrayList);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(79, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getHardwareConfigResponse(RadioResponseInfo paramRadioResponseInfo, ArrayList<HardwareConfig> paramArrayList) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    HardwareConfig.writeVectorToParcel(hwParcel2, paramArrayList);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(114, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
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
  
  public void getIMSIForAppResponse(RadioResponseInfo paramRadioResponseInfo, String paramString) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    hwParcel2.writeString(paramString);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(11, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getIccCardStatusResponse(RadioResponseInfo paramRadioResponseInfo, CardStatus paramCardStatus) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramCardStatus.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(1, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getImsRegistrationStateResponse(RadioResponseInfo paramRadioResponseInfo, boolean paramBoolean, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeBool(paramBoolean);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(102, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getLastCallFailCauseResponse(RadioResponseInfo paramRadioResponseInfo, LastCallFailCauseInfo paramLastCallFailCauseInfo) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramLastCallFailCauseInfo.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(18, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getModemActivityInfoResponse(RadioResponseInfo paramRadioResponseInfo, ActivityStatsInfo paramActivityStatsInfo) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramActivityStatsInfo.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(123, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getMuteResponse(RadioResponseInfo paramRadioResponseInfo, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
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
  
  public void getNeighboringCidsResponse(RadioResponseInfo paramRadioResponseInfo, ArrayList<NeighboringCell> paramArrayList) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    NeighboringCell.writeVectorToParcel(hwParcel2, paramArrayList);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(66, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getNetworkSelectionModeResponse(RadioResponseInfo paramRadioResponseInfo, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeBool(paramBoolean);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(43, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getOperatorResponse(RadioResponseInfo paramRadioResponseInfo, String paramString1, String paramString2, String paramString3) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    hwParcel2.writeString(paramString1);
    hwParcel2.writeString(paramString2);
    hwParcel2.writeString(paramString3);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(22, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getPreferredNetworkTypeResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(65, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getPreferredVoicePrivacyResponse(RadioResponseInfo paramRadioResponseInfo, boolean paramBoolean) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
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
  
  public void getRadioCapabilityResponse(RadioResponseInfo paramRadioResponseInfo, RadioCapability paramRadioCapability) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramRadioCapability.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(118, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getSignalStrengthResponse(RadioResponseInfo paramRadioResponseInfo, SignalStrength paramSignalStrength) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramSignalStrength.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(19, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getSmscAddressResponse(RadioResponseInfo paramRadioResponseInfo, String paramString) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    hwParcel2.writeString(paramString);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(90, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void getTTYModeResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(72, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getVoiceRadioTechnologyResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(98, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void getVoiceRegistrationStateResponse(RadioResponseInfo paramRadioResponseInfo, VoiceRegStateResult paramVoiceRegStateResult) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramVoiceRegStateResult.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(20, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void handleStkCallSetupRequestFromSimResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(62, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void hangupConnectionResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(12, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void hangupForegroundResumeBackgroundResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(14, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void hangupWaitingOrBackgroundResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(13, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public final int hashCode() {
    return asBinder().hashCode();
  }
  
  public void iccCloseLogicalChannelResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(106, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void iccIOForAppResponse(RadioResponseInfo paramRadioResponseInfo, IccIoResult paramIccIoResult) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramIccIoResult.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(28, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void iccOpenLogicalChannelResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt, ArrayList<Byte> paramArrayList) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    hwParcel2.writeInt32(paramInt);
    hwParcel2.writeInt8Vector(paramArrayList);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(105, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void iccTransmitApduBasicChannelResponse(RadioResponseInfo paramRadioResponseInfo, IccIoResult paramIccIoResult) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramIccIoResult.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(104, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void iccTransmitApduLogicalChannelResponse(RadioResponseInfo paramRadioResponseInfo, IccIoResult paramIccIoResult) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramIccIoResult.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(107, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
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
  
  public void nvReadItemResponse(RadioResponseInfo paramRadioResponseInfo, String paramString) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    hwParcel2.writeString(paramString);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(108, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void nvResetConfigResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(111, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void nvWriteCdmaPrlResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(110, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void nvWriteItemResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(109, null, hwParcel, 1);
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
  
  public void pullLceDataResponse(RadioResponseInfo paramRadioResponseInfo, LceDataInfo paramLceDataInfo) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramLceDataInfo.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(122, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void rejectCallResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(17, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void reportSmsMemoryStatusResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(92, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void reportStkServiceIsRunningResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(93, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void requestIccSimAuthenticationResponse(RadioResponseInfo paramRadioResponseInfo, IccIoResult paramIccIoResult) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramIccIoResult.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(115, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void requestIsimAuthenticationResponse(RadioResponseInfo paramRadioResponseInfo, String paramString) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    hwParcel2.writeString(paramString);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(95, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void requestShutdownResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(117, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendBurstDtmfResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(76, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendCDMAFeatureCodeResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(75, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendCdmaSmsResponse(RadioResponseInfo paramRadioResponseInfo, SendSmsResult paramSendSmsResult) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramSendSmsResult.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(77, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void sendDeviceStateResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(126, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendDtmfResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(24, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendEnvelopeResponse(RadioResponseInfo paramRadioResponseInfo, String paramString) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    hwParcel2.writeString(paramString);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(60, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void sendEnvelopeWithStatusResponse(RadioResponseInfo paramRadioResponseInfo, IccIoResult paramIccIoResult) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramIccIoResult.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(97, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void sendImsSmsResponse(RadioResponseInfo paramRadioResponseInfo, SendSmsResult paramSendSmsResult) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramSendSmsResult.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(103, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void sendSMSExpectMoreResponse(RadioResponseInfo paramRadioResponseInfo, SendSmsResult paramSendSmsResult) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramSendSmsResult.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(26, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void sendSmsResponse(RadioResponseInfo paramRadioResponseInfo, SendSmsResult paramSendSmsResult) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramSendSmsResult.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(25, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void sendTerminalResponseToSimResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(61, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void sendUssdResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(29, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void separateConnectionResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(50, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setAllowedCarriersResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
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
  
  public void setBandModeResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(58, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setBarringPasswordResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(42, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCallForwardResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(34, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCallWaitingResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(36, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCdmaBroadcastActivationResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(84, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCdmaBroadcastConfigResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(83, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCdmaRoamingPreferenceResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(69, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCdmaSubscriptionSourceResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(68, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setCellInfoListRateResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(100, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setClirResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(32, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setDataAllowedResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(113, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setDataProfileResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(116, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setFacilityLockForAppResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(41, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setGsmBroadcastActivationResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(81, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setGsmBroadcastConfigResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(80, null, hwParcel, 1);
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
  
  public void setIndicationFilterResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(127, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setInitialAttachApnResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(101, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setLocationUpdatesResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(67, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setMuteResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(51, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setNetworkSelectionModeAutomaticResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(44, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setNetworkSelectionModeManualResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(45, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setPreferredNetworkTypeResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(64, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setPreferredVoicePrivacyResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(73, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setRadioCapabilityResponse(RadioResponseInfo paramRadioResponseInfo, RadioCapability paramRadioCapability) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramRadioCapability.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(119, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void setRadioPowerResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(23, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setSimCardPowerResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(128, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setSmscAddressResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(91, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setSuppServiceNotificationsResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(55, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setTTYModeResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(71, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setUiccSubscriptionResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(112, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void setupDataCallResponse(RadioResponseInfo paramRadioResponseInfo, SetupDataCallResult paramSetupDataCallResult) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramSetupDataCallResult.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(27, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void startDtmfResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(47, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void startLceServiceResponse(RadioResponseInfo paramRadioResponseInfo, LceStatusInfo paramLceStatusInfo) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramLceStatusInfo.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(120, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void stopDtmfResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(48, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void stopLceServiceResponse(RadioResponseInfo paramRadioResponseInfo, LceStatusInfo paramLceStatusInfo) throws RemoteException {
    HwParcel hwParcel2 = new HwParcel();
    hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(hwParcel2);
    paramLceStatusInfo.writeToParcel(hwParcel2);
    HwParcel hwParcel1 = new HwParcel();
    try {
      this.mRemote.transact(121, hwParcel2, hwParcel1, 1);
      hwParcel2.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel1.release();
    } 
  }
  
  public void supplyIccPin2ForAppResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(4, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void supplyIccPinForAppResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
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
  
  public void supplyIccPuk2ForAppResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(5, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void supplyIccPukForAppResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
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
  
  public void supplyNetworkDepersonalizationResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(8, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
  
  public void switchWaitingOrHoldingAndActiveResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(15, null, hwParcel, 1);
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
      return "[class or subclass of android.hardware.radio@1.0::IRadioResponse]@Proxy";
    } 
  }
  
  public boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException {
    return this.mRemote.unlinkToDeath(paramDeathRecipient);
  }
  
  public void writeSmsToRuimResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
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
  
  public void writeSmsToSimResponse(RadioResponseInfo paramRadioResponseInfo, int paramInt) throws RemoteException {
    null = new HwParcel();
    null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
    paramRadioResponseInfo.writeToParcel(null);
    null.writeInt32(paramInt);
    HwParcel hwParcel = new HwParcel();
    try {
      this.mRemote.transact(56, null, hwParcel, 1);
      null.releaseTemporaryStorage();
      return;
    } finally {
      hwParcel.release();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_0/IRadioResponse$Proxy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
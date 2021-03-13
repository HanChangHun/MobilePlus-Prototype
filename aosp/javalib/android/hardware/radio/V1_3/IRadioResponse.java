package android.hardware.radio.V1_3;

import android.hardware.radio.V1_0.ActivityStatsInfo;
import android.hardware.radio.V1_0.Call;
import android.hardware.radio.V1_0.CallForwardInfo;
import android.hardware.radio.V1_0.CardStatus;
import android.hardware.radio.V1_0.CarrierRestrictions;
import android.hardware.radio.V1_0.CdmaBroadcastSmsConfigInfo;
import android.hardware.radio.V1_0.CellInfo;
import android.hardware.radio.V1_0.DataRegStateResult;
import android.hardware.radio.V1_0.GsmBroadcastSmsConfigInfo;
import android.hardware.radio.V1_0.HardwareConfig;
import android.hardware.radio.V1_0.IccIoResult;
import android.hardware.radio.V1_0.LastCallFailCauseInfo;
import android.hardware.radio.V1_0.LceDataInfo;
import android.hardware.radio.V1_0.LceStatusInfo;
import android.hardware.radio.V1_0.NeighboringCell;
import android.hardware.radio.V1_0.OperatorInfo;
import android.hardware.radio.V1_0.RadioCapability;
import android.hardware.radio.V1_0.RadioResponseInfo;
import android.hardware.radio.V1_0.SendSmsResult;
import android.hardware.radio.V1_0.SetupDataCallResult;
import android.hardware.radio.V1_0.SignalStrength;
import android.hardware.radio.V1_0.VoiceRegStateResult;
import android.hardware.radio.V1_1.KeepaliveStatus;
import android.hardware.radio.V1_2.Call;
import android.hardware.radio.V1_2.CardStatus;
import android.hardware.radio.V1_2.CellInfo;
import android.hardware.radio.V1_2.DataRegStateResult;
import android.hardware.radio.V1_2.IRadioResponse;
import android.hardware.radio.V1_2.SignalStrength;
import android.hardware.radio.V1_2.VoiceRegStateResult;
import android.internal.hidl.base.V1_0.DebugInfo;
import android.os.HidlSupport;
import android.os.HwBinder;
import android.os.HwBlob;
import android.os.HwParcel;
import android.os.IHwBinder;
import android.os.IHwInterface;
import android.os.NativeHandle;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public interface IRadioResponse extends IRadioResponse {
  public static final String kInterfaceName = "android.hardware.radio@1.3::IRadioResponse";
  
  static IRadioResponse asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.radio@1.3::IRadioResponse");
    if (iHwInterface != null && iHwInterface instanceof IRadioResponse)
      return (IRadioResponse)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.radio@1.3::IRadioResponse");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IRadioResponse castFrom(IHwInterface paramIHwInterface) {
    IRadioResponse iRadioResponse;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iRadioResponse = asInterface(paramIHwInterface.asBinder());
    } 
    return iRadioResponse;
  }
  
  static IRadioResponse getService() throws RemoteException {
    return getService("default");
  }
  
  static IRadioResponse getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.radio@1.3::IRadioResponse", paramString));
  }
  
  static IRadioResponse getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.radio@1.3::IRadioResponse", paramString, paramBoolean));
  }
  
  static IRadioResponse getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  void enableModemResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  void getModemStackStatusResponse(RadioResponseInfo paramRadioResponseInfo, boolean paramBoolean) throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  void setSystemSelectionChannelsResponse(RadioResponseInfo paramRadioResponseInfo) throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class Proxy implements IRadioResponse {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public void acceptCallResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(38, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void acknowledgeIncomingGsmSmsWithPduResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(96, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void acknowledgeLastIncomingCdmaSmsResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(78, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void acknowledgeLastIncomingGsmSmsResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(37, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void acknowledgeRequest(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      null.writeInt32(param1Int);
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
    
    public void cancelPendingUssdResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(30, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void changeIccPin2ForAppResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(7, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void changeIccPinForAppResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(6, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void conferenceResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(16, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void deactivateDataCallResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(39, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void debug(NativeHandle param1NativeHandle, ArrayList<String> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hidl.base@1.0::IBase");
      hwParcel2.writeNativeHandle(param1NativeHandle);
      hwParcel2.writeStringVector(param1ArrayList);
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
    
    public void deleteSmsOnRuimResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(87, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void deleteSmsOnSimResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(57, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void dialResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(10, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void enableModemResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.3::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(145, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public final boolean equals(Object param1Object) {
      return HidlSupport.interfacesEqual((IHwInterface)this, param1Object);
    }
    
    public void exitEmergencyCallbackModeResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(89, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void explicitCallTransferResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(63, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getAllowedCarriersResponse(RadioResponseInfo param1RadioResponseInfo, boolean param1Boolean, CarrierRestrictions param1CarrierRestrictions) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      hwParcel2.writeBool(param1Boolean);
      param1CarrierRestrictions.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(125, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getAvailableBandModesResponse(RadioResponseInfo param1RadioResponseInfo, ArrayList<Integer> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      hwParcel2.writeInt32Vector(param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(59, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getAvailableNetworksResponse(RadioResponseInfo param1RadioResponseInfo, ArrayList<OperatorInfo> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      OperatorInfo.writeVectorToParcel(hwParcel2, param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(46, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getBasebandVersionResponse(RadioResponseInfo param1RadioResponseInfo, String param1String) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      hwParcel2.writeString(param1String);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(49, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getCDMASubscriptionResponse(RadioResponseInfo param1RadioResponseInfo, String param1String1, String param1String2, String param1String3, String param1String4, String param1String5) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeString(param1String2);
      hwParcel2.writeString(param1String3);
      hwParcel2.writeString(param1String4);
      hwParcel2.writeString(param1String5);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(85, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getCallForwardStatusResponse(RadioResponseInfo param1RadioResponseInfo, ArrayList<CallForwardInfo> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      CallForwardInfo.writeVectorToParcel(hwParcel2, param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(33, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getCallWaitingResponse(RadioResponseInfo param1RadioResponseInfo, boolean param1Boolean, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeBool(param1Boolean);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(35, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getCdmaBroadcastConfigResponse(RadioResponseInfo param1RadioResponseInfo, ArrayList<CdmaBroadcastSmsConfigInfo> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      CdmaBroadcastSmsConfigInfo.writeVectorToParcel(hwParcel2, param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(82, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getCdmaRoamingPreferenceResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(70, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getCdmaSubscriptionSourceResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(94, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getCellInfoListResponse(RadioResponseInfo param1RadioResponseInfo, ArrayList<CellInfo> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      CellInfo.writeVectorToParcel(hwParcel2, param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(99, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getCellInfoListResponse_1_2(RadioResponseInfo param1RadioResponseInfo, ArrayList<CellInfo> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.2::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      CellInfo.writeVectorToParcel(hwParcel2, param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(136, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getClipResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(53, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getClirResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(31, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getCurrentCallsResponse(RadioResponseInfo param1RadioResponseInfo, ArrayList<Call> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      Call.writeVectorToParcel(hwParcel2, param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(9, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getCurrentCallsResponse_1_2(RadioResponseInfo param1RadioResponseInfo, ArrayList<Call> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.2::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      Call.writeVectorToParcel(hwParcel2, param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(140, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getDataCallListResponse(RadioResponseInfo param1RadioResponseInfo, ArrayList<SetupDataCallResult> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      SetupDataCallResult.writeVectorToParcel(hwParcel2, param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(54, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getDataRegistrationStateResponse(RadioResponseInfo param1RadioResponseInfo, DataRegStateResult param1DataRegStateResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1DataRegStateResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(21, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getDataRegistrationStateResponse_1_2(RadioResponseInfo param1RadioResponseInfo, DataRegStateResult param1DataRegStateResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.2::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1DataRegStateResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(143, hwParcel2, hwParcel1, 1);
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
    
    public void getDeviceIdentityResponse(RadioResponseInfo param1RadioResponseInfo, String param1String1, String param1String2, String param1String3, String param1String4) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeString(param1String2);
      hwParcel2.writeString(param1String3);
      hwParcel2.writeString(param1String4);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(88, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getFacilityLockForAppResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(40, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getGsmBroadcastConfigResponse(RadioResponseInfo param1RadioResponseInfo, ArrayList<GsmBroadcastSmsConfigInfo> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      GsmBroadcastSmsConfigInfo.writeVectorToParcel(hwParcel2, param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(79, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getHardwareConfigResponse(RadioResponseInfo param1RadioResponseInfo, ArrayList<HardwareConfig> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      HardwareConfig.writeVectorToParcel(hwParcel2, param1ArrayList);
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
    
    public void getIMSIForAppResponse(RadioResponseInfo param1RadioResponseInfo, String param1String) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      hwParcel2.writeString(param1String);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(11, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getIccCardStatusResponse(RadioResponseInfo param1RadioResponseInfo, CardStatus param1CardStatus) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1CardStatus.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(1, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getIccCardStatusResponse_1_2(RadioResponseInfo param1RadioResponseInfo, CardStatus param1CardStatus) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.2::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1CardStatus.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(137, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getImsRegistrationStateResponse(RadioResponseInfo param1RadioResponseInfo, boolean param1Boolean, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeBool(param1Boolean);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(102, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getLastCallFailCauseResponse(RadioResponseInfo param1RadioResponseInfo, LastCallFailCauseInfo param1LastCallFailCauseInfo) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1LastCallFailCauseInfo.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(18, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getModemActivityInfoResponse(RadioResponseInfo param1RadioResponseInfo, ActivityStatsInfo param1ActivityStatsInfo) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1ActivityStatsInfo.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(123, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getModemStackStatusResponse(RadioResponseInfo param1RadioResponseInfo, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.3::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(146, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getMuteResponse(RadioResponseInfo param1RadioResponseInfo, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(52, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getNeighboringCidsResponse(RadioResponseInfo param1RadioResponseInfo, ArrayList<NeighboringCell> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      NeighboringCell.writeVectorToParcel(hwParcel2, param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(66, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getNetworkSelectionModeResponse(RadioResponseInfo param1RadioResponseInfo, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(43, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getOperatorResponse(RadioResponseInfo param1RadioResponseInfo, String param1String1, String param1String2, String param1String3) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeString(param1String2);
      hwParcel2.writeString(param1String3);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(22, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getPreferredNetworkTypeResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(65, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getPreferredVoicePrivacyResponse(RadioResponseInfo param1RadioResponseInfo, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(74, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getRadioCapabilityResponse(RadioResponseInfo param1RadioResponseInfo, RadioCapability param1RadioCapability) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1RadioCapability.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(118, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getSignalStrengthResponse(RadioResponseInfo param1RadioResponseInfo, SignalStrength param1SignalStrength) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1SignalStrength.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(19, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getSignalStrengthResponse_1_2(RadioResponseInfo param1RadioResponseInfo, SignalStrength param1SignalStrength) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.2::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1SignalStrength.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(141, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getSmscAddressResponse(RadioResponseInfo param1RadioResponseInfo, String param1String) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      hwParcel2.writeString(param1String);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(90, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getTTYModeResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(72, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getVoiceRadioTechnologyResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(98, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getVoiceRegistrationStateResponse(RadioResponseInfo param1RadioResponseInfo, VoiceRegStateResult param1VoiceRegStateResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1VoiceRegStateResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(20, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getVoiceRegistrationStateResponse_1_2(RadioResponseInfo param1RadioResponseInfo, VoiceRegStateResult param1VoiceRegStateResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.2::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1VoiceRegStateResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(142, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void handleStkCallSetupRequestFromSimResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(62, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void hangupConnectionResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(12, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void hangupForegroundResumeBackgroundResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(14, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void hangupWaitingOrBackgroundResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
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
    
    public void iccCloseLogicalChannelResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(106, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void iccIOForAppResponse(RadioResponseInfo param1RadioResponseInfo, IccIoResult param1IccIoResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1IccIoResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(28, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void iccOpenLogicalChannelResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int, ArrayList<Byte> param1ArrayList) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      hwParcel2.writeInt32(param1Int);
      hwParcel2.writeInt8Vector(param1ArrayList);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(105, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void iccTransmitApduBasicChannelResponse(RadioResponseInfo param1RadioResponseInfo, IccIoResult param1IccIoResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1IccIoResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(104, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void iccTransmitApduLogicalChannelResponse(RadioResponseInfo param1RadioResponseInfo, IccIoResult param1IccIoResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1IccIoResult.writeToParcel(hwParcel2);
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
    
    public boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) throws RemoteException {
      return this.mRemote.linkToDeath(param1DeathRecipient, param1Long);
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
    
    public void nvReadItemResponse(RadioResponseInfo param1RadioResponseInfo, String param1String) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      hwParcel2.writeString(param1String);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(108, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void nvResetConfigResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(111, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void nvWriteCdmaPrlResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(110, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void nvWriteItemResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
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
    
    public void pullLceDataResponse(RadioResponseInfo param1RadioResponseInfo, LceDataInfo param1LceDataInfo) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1LceDataInfo.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(122, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void rejectCallResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(17, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void reportSmsMemoryStatusResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(92, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void reportStkServiceIsRunningResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(93, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void requestIccSimAuthenticationResponse(RadioResponseInfo param1RadioResponseInfo, IccIoResult param1IccIoResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1IccIoResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(115, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void requestIsimAuthenticationResponse(RadioResponseInfo param1RadioResponseInfo, String param1String) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      hwParcel2.writeString(param1String);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(95, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void requestShutdownResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(117, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendBurstDtmfResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(76, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendCDMAFeatureCodeResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(75, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendCdmaSmsResponse(RadioResponseInfo param1RadioResponseInfo, SendSmsResult param1SendSmsResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1SendSmsResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(77, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void sendDeviceStateResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(126, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendDtmfResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(24, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendEnvelopeResponse(RadioResponseInfo param1RadioResponseInfo, String param1String) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      hwParcel2.writeString(param1String);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(60, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void sendEnvelopeWithStatusResponse(RadioResponseInfo param1RadioResponseInfo, IccIoResult param1IccIoResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1IccIoResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(97, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void sendImsSmsResponse(RadioResponseInfo param1RadioResponseInfo, SendSmsResult param1SendSmsResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1SendSmsResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(103, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void sendSMSExpectMoreResponse(RadioResponseInfo param1RadioResponseInfo, SendSmsResult param1SendSmsResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1SendSmsResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(26, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void sendSmsResponse(RadioResponseInfo param1RadioResponseInfo, SendSmsResult param1SendSmsResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1SendSmsResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(25, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void sendTerminalResponseToSimResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(61, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendUssdResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(29, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void separateConnectionResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(50, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setAllowedCarriersResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(124, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setBandModeResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(58, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setBarringPasswordResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(42, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCallForwardResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(34, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCallWaitingResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(36, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCarrierInfoForImsiEncryptionResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(130, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCdmaBroadcastActivationResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(84, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCdmaBroadcastConfigResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(83, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCdmaRoamingPreferenceResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(69, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCdmaSubscriptionSourceResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(68, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCellInfoListRateResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(100, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setClirResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(32, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setDataAllowedResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(113, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setDataProfileResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(116, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setFacilityLockForAppResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(41, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setGsmBroadcastActivationResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(81, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setGsmBroadcastConfigResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
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
    
    public void setIndicationFilterResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(127, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setInitialAttachApnResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(101, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setLinkCapacityReportingCriteriaResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.2::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(139, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setLocationUpdatesResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(67, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setMuteResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(51, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setNetworkSelectionModeAutomaticResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(44, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setNetworkSelectionModeManualResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(45, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setPreferredNetworkTypeResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(64, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setPreferredVoicePrivacyResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(73, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setRadioCapabilityResponse(RadioResponseInfo param1RadioResponseInfo, RadioCapability param1RadioCapability) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1RadioCapability.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(119, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void setRadioPowerResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(23, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setSignalStrengthReportingCriteriaResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.2::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(138, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setSimCardPowerResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(128, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setSimCardPowerResponse_1_1(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(131, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setSmscAddressResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(91, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setSuppServiceNotificationsResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(55, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setSystemSelectionChannelsResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.3::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(144, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setTTYModeResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(71, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setUiccSubscriptionResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(112, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setupDataCallResponse(RadioResponseInfo param1RadioResponseInfo, SetupDataCallResult param1SetupDataCallResult) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1SetupDataCallResult.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(27, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void startDtmfResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(47, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void startKeepaliveResponse(RadioResponseInfo param1RadioResponseInfo, KeepaliveStatus param1KeepaliveStatus) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.1::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1KeepaliveStatus.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(134, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void startLceServiceResponse(RadioResponseInfo param1RadioResponseInfo, LceStatusInfo param1LceStatusInfo) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1LceStatusInfo.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(120, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void startNetworkScanResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(132, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void stopDtmfResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(48, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void stopKeepaliveResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(135, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void stopLceServiceResponse(RadioResponseInfo param1RadioResponseInfo, LceStatusInfo param1LceStatusInfo) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(hwParcel2);
      param1LceStatusInfo.writeToParcel(hwParcel2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(121, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void stopNetworkScanResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(133, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void supplyIccPin2ForAppResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(4, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void supplyIccPinForAppResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(2, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void supplyIccPuk2ForAppResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(5, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void supplyIccPukForAppResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(3, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void supplyNetworkDepersonalizationResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(8, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void switchWaitingOrHoldingAndActiveResponse(RadioResponseInfo param1RadioResponseInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
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
        return "[class or subclass of android.hardware.radio@1.3::IRadioResponse]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
    
    public void writeSmsToRuimResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(86, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void writeSmsToSimResponse(RadioResponseInfo param1RadioResponseInfo, int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadioResponse");
      param1RadioResponseInfo.writeToParcel(null);
      null.writeInt32(param1Int);
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
  
  public static abstract class Stub extends HwBinder implements IRadioResponse {
    public IHwBinder asBinder() {
      return (IHwBinder)this;
    }
    
    public void debug(NativeHandle param1NativeHandle, ArrayList<String> param1ArrayList) {}
    
    public final DebugInfo getDebugInfo() {
      DebugInfo debugInfo = new DebugInfo();
      debugInfo.pid = HidlSupport.getPidIfSharable();
      debugInfo.ptr = 0L;
      debugInfo.arch = 0;
      return debugInfo;
    }
    
    public final ArrayList<byte[]> getHashChain() {
      byte[] arrayOfByte1 = { 
          -11, -5, -28, -14, -118, -98, 52, 107, -29, 96, 
          99, -20, -92, -26, -56, 100, 17, 74, 26, 111, 
          -74, 72, -124, -37, 3, -3, -40, 37, 121, 26, 
          -39, -72 };
      byte[] arrayOfByte2 = { 
          5, -86, 61, -26, 19, 10, -105, -120, -3, -74, 
          -12, -45, -52, 87, -61, -22, -112, -16, 103, -25, 
          122, 94, 9, -42, -89, 114, -20, Byte.MAX_VALUE, 107, -54, 
          51, -46 };
      byte[] arrayOfByte3 = { 
          -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
          -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
          -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
          -54, 76 };
      return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { arrayOfByte1, { 
                -38, -116, 106, -23, -111, -58, -92, -78, -124, -52, 
                110, 68, 83, 50, -32, 100, -30, -114, -24, -96, 
                -108, -126, -19, 90, -1, -7, -47, 89, -20, 102, 
                -108, -73 }, arrayOfByte2, { 
                29, 74, 87, 118, 97, 76, 8, -75, -41, -108, 
                -91, -20, 90, -80, 70, -105, 38, 12, -67, 75, 
                52, 65, -43, -109, 92, -43, 62, -25, 29, 25, 
                -38, 2 }, arrayOfByte3 }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.radio@1.3::IRadioResponse", "android.hardware.radio@1.2::IRadioResponse", "android.hardware.radio@1.1::IRadioResponse", "android.hardware.radio@1.0::IRadioResponse", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.radio@1.3::IRadioResponse";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      DebugInfo debugInfo;
      ArrayList<byte[]> arrayList1;
      String str;
      ArrayList<String> arrayList;
      RadioResponseInfo radioResponseInfo12;
      DataRegStateResult dataRegStateResult;
      RadioResponseInfo radioResponseInfo11;
      SignalStrength signalStrength;
      RadioResponseInfo radioResponseInfo10;
      CardStatus cardStatus;
      RadioResponseInfo radioResponseInfo9;
      KeepaliveStatus keepaliveStatus;
      RadioResponseInfo radioResponseInfo8;
      ActivityStatsInfo activityStatsInfo;
      LceDataInfo lceDataInfo;
      LceStatusInfo lceStatusInfo1;
      RadioResponseInfo radioResponseInfo7;
      RadioCapability radioCapability;
      RadioResponseInfo radioResponseInfo6;
      IccIoResult iccIoResult1;
      RadioResponseInfo radioResponseInfo5;
      SendSmsResult sendSmsResult2;
      RadioResponseInfo radioResponseInfo4;
      SetupDataCallResult setupDataCallResult;
      RadioResponseInfo radioResponseInfo3;
      SendSmsResult sendSmsResult1;
      RadioResponseInfo radioResponseInfo2;
      LastCallFailCauseInfo lastCallFailCauseInfo;
      HwBlob hwBlob1;
      RadioResponseInfo radioResponseInfo21;
      VoiceRegStateResult voiceRegStateResult1;
      RadioResponseInfo radioResponseInfo20;
      CarrierRestrictions carrierRestrictions;
      RadioResponseInfo radioResponseInfo19;
      LceStatusInfo lceStatusInfo2;
      RadioResponseInfo radioResponseInfo18;
      IccIoResult iccIoResult4;
      RadioResponseInfo radioResponseInfo17;
      SendSmsResult sendSmsResult4;
      IccIoResult iccIoResult3;
      RadioResponseInfo radioResponseInfo16;
      IccIoResult iccIoResult2;
      RadioResponseInfo radioResponseInfo15;
      SendSmsResult sendSmsResult3;
      RadioResponseInfo radioResponseInfo14;
      DataRegStateResult dataRegStateResult1;
      VoiceRegStateResult voiceRegStateResult;
      SignalStrength signalStrength1;
      RadioResponseInfo radioResponseInfo13;
      HwBlob hwBlob2;
      boolean bool;
      switch (param1Int1) {
        default:
          switch (param1Int1) {
            default:
              return;
            case 257120595:
              param1HwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
              notifySyspropsChanged();
            case 257049926:
              param1HwParcel1.enforceInterface("android.hidl.base@1.0::IBase");
              debugInfo = getDebugInfo();
              param1HwParcel2.writeStatus(0);
              debugInfo.writeToParcel(param1HwParcel2);
              param1HwParcel2.send();
            case 256921159:
              debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
              ping();
              param1HwParcel2.writeStatus(0);
              param1HwParcel2.send();
            case 256462420:
              debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
              setHALInstrumentation();
            case 256398152:
              debugInfo.enforceInterface("android.hidl.base@1.0::IBase");
              arrayList1 = getHashChain();
              param1HwParcel2.writeStatus(0);
              hwBlob1 = new HwBlob(16);
              param1Int2 = arrayList1.size();
              hwBlob1.putInt32(8L, param1Int2);
              hwBlob1.putBool(12L, false);
              hwBlob2 = new HwBlob(param1Int2 * 32);
              param1Int1 = 0;
              while (param1Int1 < param1Int2) {
                long l = (param1Int1 * 32);
                byte[] arrayOfByte = arrayList1.get(param1Int1);
                if (arrayOfByte != null && arrayOfByte.length == 32) {
                  hwBlob2.putInt8Array(l, arrayOfByte);
                  param1Int1++;
                  continue;
                } 
                throw new IllegalArgumentException("Array element is not of the expected length");
              } 
              hwBlob1.putBlob(0L, hwBlob2);
              param1HwParcel2.writeBuffer(hwBlob1);
              param1HwParcel2.send();
            case 256136003:
              arrayList1.enforceInterface("android.hidl.base@1.0::IBase");
              str = interfaceDescriptor();
              param1HwParcel2.writeStatus(0);
              param1HwParcel2.writeString(str);
              param1HwParcel2.send();
            case 256131655:
              str.enforceInterface("android.hidl.base@1.0::IBase");
              debug(str.readNativeHandle(), str.readStringVector());
              param1HwParcel2.writeStatus(0);
              param1HwParcel2.send();
            case 256067662:
              break;
          } 
          str.enforceInterface("android.hidl.base@1.0::IBase");
          arrayList = interfaceChain();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeStringVector(arrayList);
          param1HwParcel2.send();
        case 146:
          arrayList.enforceInterface("android.hardware.radio@1.3::IRadioResponse");
          radioResponseInfo12 = new RadioResponseInfo();
          radioResponseInfo12.readFromParcel((HwParcel)arrayList);
          getModemStackStatusResponse(radioResponseInfo12, arrayList.readBool());
        case 145:
          arrayList.enforceInterface("android.hardware.radio@1.3::IRadioResponse");
          radioResponseInfo12 = new RadioResponseInfo();
          radioResponseInfo12.readFromParcel((HwParcel)arrayList);
          enableModemResponse(radioResponseInfo12);
        case 144:
          arrayList.enforceInterface("android.hardware.radio@1.3::IRadioResponse");
          radioResponseInfo12 = new RadioResponseInfo();
          radioResponseInfo12.readFromParcel((HwParcel)arrayList);
          setSystemSelectionChannelsResponse(radioResponseInfo12);
        case 143:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
          radioResponseInfo21 = new RadioResponseInfo();
          radioResponseInfo21.readFromParcel((HwParcel)arrayList);
          dataRegStateResult = new DataRegStateResult();
          dataRegStateResult.readFromParcel((HwParcel)arrayList);
          getDataRegistrationStateResponse_1_2(radioResponseInfo21, dataRegStateResult);
        case 142:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
          radioResponseInfo11 = new RadioResponseInfo();
          radioResponseInfo11.readFromParcel((HwParcel)arrayList);
          voiceRegStateResult1 = new VoiceRegStateResult();
          voiceRegStateResult1.readFromParcel((HwParcel)arrayList);
          getVoiceRegistrationStateResponse_1_2(radioResponseInfo11, voiceRegStateResult1);
        case 141:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
          radioResponseInfo20 = new RadioResponseInfo();
          radioResponseInfo20.readFromParcel((HwParcel)arrayList);
          signalStrength = new SignalStrength();
          signalStrength.readFromParcel((HwParcel)arrayList);
          getSignalStrengthResponse_1_2(radioResponseInfo20, signalStrength);
        case 140:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
          radioResponseInfo10 = new RadioResponseInfo();
          radioResponseInfo10.readFromParcel((HwParcel)arrayList);
          getCurrentCallsResponse_1_2(radioResponseInfo10, Call.readVectorFromParcel((HwParcel)arrayList));
        case 139:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
          radioResponseInfo10 = new RadioResponseInfo();
          radioResponseInfo10.readFromParcel((HwParcel)arrayList);
          setLinkCapacityReportingCriteriaResponse(radioResponseInfo10);
        case 138:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
          radioResponseInfo10 = new RadioResponseInfo();
          radioResponseInfo10.readFromParcel((HwParcel)arrayList);
          setSignalStrengthReportingCriteriaResponse(radioResponseInfo10);
        case 137:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
          radioResponseInfo20 = new RadioResponseInfo();
          radioResponseInfo20.readFromParcel((HwParcel)arrayList);
          cardStatus = new CardStatus();
          cardStatus.readFromParcel((HwParcel)arrayList);
          getIccCardStatusResponse_1_2(radioResponseInfo20, cardStatus);
        case 136:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadioResponse");
          radioResponseInfo9 = new RadioResponseInfo();
          radioResponseInfo9.readFromParcel((HwParcel)arrayList);
          getCellInfoListResponse_1_2(radioResponseInfo9, CellInfo.readVectorFromParcel((HwParcel)arrayList));
        case 135:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadioResponse");
          radioResponseInfo9 = new RadioResponseInfo();
          radioResponseInfo9.readFromParcel((HwParcel)arrayList);
          stopKeepaliveResponse(radioResponseInfo9);
        case 134:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadioResponse");
          radioResponseInfo20 = new RadioResponseInfo();
          radioResponseInfo20.readFromParcel((HwParcel)arrayList);
          keepaliveStatus = new KeepaliveStatus();
          keepaliveStatus.readFromParcel((HwParcel)arrayList);
          startKeepaliveResponse(radioResponseInfo20, keepaliveStatus);
        case 133:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadioResponse");
          radioResponseInfo8 = new RadioResponseInfo();
          radioResponseInfo8.readFromParcel((HwParcel)arrayList);
          stopNetworkScanResponse(radioResponseInfo8);
        case 132:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadioResponse");
          radioResponseInfo8 = new RadioResponseInfo();
          radioResponseInfo8.readFromParcel((HwParcel)arrayList);
          startNetworkScanResponse(radioResponseInfo8);
        case 131:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadioResponse");
          radioResponseInfo8 = new RadioResponseInfo();
          radioResponseInfo8.readFromParcel((HwParcel)arrayList);
          setSimCardPowerResponse_1_1(radioResponseInfo8);
        case 130:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadioResponse");
          radioResponseInfo8 = new RadioResponseInfo();
          radioResponseInfo8.readFromParcel((HwParcel)arrayList);
          setCarrierInfoForImsiEncryptionResponse(radioResponseInfo8);
        case 129:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          acknowledgeRequest(arrayList.readInt32());
        case 128:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo8 = new RadioResponseInfo();
          radioResponseInfo8.readFromParcel((HwParcel)arrayList);
          setSimCardPowerResponse(radioResponseInfo8);
        case 127:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo8 = new RadioResponseInfo();
          radioResponseInfo8.readFromParcel((HwParcel)arrayList);
          setIndicationFilterResponse(radioResponseInfo8);
        case 126:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo8 = new RadioResponseInfo();
          radioResponseInfo8.readFromParcel((HwParcel)arrayList);
          sendDeviceStateResponse(radioResponseInfo8);
        case 125:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo8 = new RadioResponseInfo();
          radioResponseInfo8.readFromParcel((HwParcel)arrayList);
          bool = arrayList.readBool();
          carrierRestrictions = new CarrierRestrictions();
          carrierRestrictions.readFromParcel((HwParcel)arrayList);
          getAllowedCarriersResponse(radioResponseInfo8, bool, carrierRestrictions);
        case 124:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo8 = new RadioResponseInfo();
          radioResponseInfo8.readFromParcel((HwParcel)arrayList);
          setAllowedCarriersResponse(radioResponseInfo8, arrayList.readInt32());
        case 123:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo19 = new RadioResponseInfo();
          radioResponseInfo19.readFromParcel((HwParcel)arrayList);
          activityStatsInfo = new ActivityStatsInfo();
          activityStatsInfo.readFromParcel((HwParcel)arrayList);
          getModemActivityInfoResponse(radioResponseInfo19, activityStatsInfo);
        case 122:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo19 = new RadioResponseInfo();
          radioResponseInfo19.readFromParcel((HwParcel)arrayList);
          lceDataInfo = new LceDataInfo();
          lceDataInfo.readFromParcel((HwParcel)arrayList);
          pullLceDataResponse(radioResponseInfo19, lceDataInfo);
        case 121:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo19 = new RadioResponseInfo();
          radioResponseInfo19.readFromParcel((HwParcel)arrayList);
          lceStatusInfo1 = new LceStatusInfo();
          lceStatusInfo1.readFromParcel((HwParcel)arrayList);
          stopLceServiceResponse(radioResponseInfo19, lceStatusInfo1);
        case 120:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo7 = new RadioResponseInfo();
          radioResponseInfo7.readFromParcel((HwParcel)arrayList);
          lceStatusInfo2 = new LceStatusInfo();
          lceStatusInfo2.readFromParcel((HwParcel)arrayList);
          startLceServiceResponse(radioResponseInfo7, lceStatusInfo2);
        case 119:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo18 = new RadioResponseInfo();
          radioResponseInfo18.readFromParcel((HwParcel)arrayList);
          radioCapability = new RadioCapability();
          radioCapability.readFromParcel((HwParcel)arrayList);
          setRadioCapabilityResponse(radioResponseInfo18, radioCapability);
        case 118:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo18 = new RadioResponseInfo();
          radioResponseInfo18.readFromParcel((HwParcel)arrayList);
          radioCapability = new RadioCapability();
          radioCapability.readFromParcel((HwParcel)arrayList);
          getRadioCapabilityResponse(radioResponseInfo18, radioCapability);
        case 117:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          requestShutdownResponse(radioResponseInfo6);
        case 116:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          setDataProfileResponse(radioResponseInfo6);
        case 115:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          iccIoResult4 = new IccIoResult();
          iccIoResult4.readFromParcel((HwParcel)arrayList);
          requestIccSimAuthenticationResponse(radioResponseInfo6, iccIoResult4);
        case 114:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          getHardwareConfigResponse(radioResponseInfo6, HardwareConfig.readVectorFromParcel((HwParcel)arrayList));
        case 113:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          setDataAllowedResponse(radioResponseInfo6);
        case 112:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          setUiccSubscriptionResponse(radioResponseInfo6);
        case 111:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          nvResetConfigResponse(radioResponseInfo6);
        case 110:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          nvWriteCdmaPrlResponse(radioResponseInfo6);
        case 109:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          nvWriteItemResponse(radioResponseInfo6);
        case 108:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          nvReadItemResponse(radioResponseInfo6, arrayList.readString());
        case 107:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          iccIoResult4 = new IccIoResult();
          iccIoResult4.readFromParcel((HwParcel)arrayList);
          iccTransmitApduLogicalChannelResponse(radioResponseInfo6, iccIoResult4);
        case 106:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          iccCloseLogicalChannelResponse(radioResponseInfo6);
        case 105:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo6 = new RadioResponseInfo();
          radioResponseInfo6.readFromParcel((HwParcel)arrayList);
          iccOpenLogicalChannelResponse(radioResponseInfo6, arrayList.readInt32(), arrayList.readInt8Vector());
        case 104:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo17 = new RadioResponseInfo();
          radioResponseInfo17.readFromParcel((HwParcel)arrayList);
          iccIoResult1 = new IccIoResult();
          iccIoResult1.readFromParcel((HwParcel)arrayList);
          iccTransmitApduBasicChannelResponse(radioResponseInfo17, iccIoResult1);
        case 103:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          sendSmsResult4 = new SendSmsResult();
          sendSmsResult4.readFromParcel((HwParcel)arrayList);
          sendImsSmsResponse(radioResponseInfo5, sendSmsResult4);
        case 102:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          getImsRegistrationStateResponse(radioResponseInfo5, arrayList.readBool(), arrayList.readInt32());
        case 101:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          setInitialAttachApnResponse(radioResponseInfo5);
        case 100:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          setCellInfoListRateResponse(radioResponseInfo5);
        case 99:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          getCellInfoListResponse(radioResponseInfo5, CellInfo.readVectorFromParcel((HwParcel)arrayList));
        case 98:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          getVoiceRadioTechnologyResponse(radioResponseInfo5, arrayList.readInt32());
        case 97:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          iccIoResult3 = new IccIoResult();
          iccIoResult3.readFromParcel((HwParcel)arrayList);
          sendEnvelopeWithStatusResponse(radioResponseInfo5, iccIoResult3);
        case 96:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          acknowledgeIncomingGsmSmsWithPduResponse(radioResponseInfo5);
        case 95:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          requestIsimAuthenticationResponse(radioResponseInfo5, arrayList.readString());
        case 94:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          getCdmaSubscriptionSourceResponse(radioResponseInfo5, arrayList.readInt32());
        case 93:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          reportStkServiceIsRunningResponse(radioResponseInfo5);
        case 92:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          reportSmsMemoryStatusResponse(radioResponseInfo5);
        case 91:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          setSmscAddressResponse(radioResponseInfo5);
        case 90:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          getSmscAddressResponse(radioResponseInfo5, arrayList.readString());
        case 89:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          exitEmergencyCallbackModeResponse(radioResponseInfo5);
        case 88:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          getDeviceIdentityResponse(radioResponseInfo5, arrayList.readString(), arrayList.readString(), arrayList.readString(), arrayList.readString());
        case 87:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          deleteSmsOnRuimResponse(radioResponseInfo5);
        case 86:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          writeSmsToRuimResponse(radioResponseInfo5, arrayList.readInt32());
        case 85:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          getCDMASubscriptionResponse(radioResponseInfo5, arrayList.readString(), arrayList.readString(), arrayList.readString(), arrayList.readString(), arrayList.readString());
        case 84:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          setCdmaBroadcastActivationResponse(radioResponseInfo5);
        case 83:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          setCdmaBroadcastConfigResponse(radioResponseInfo5);
        case 82:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          getCdmaBroadcastConfigResponse(radioResponseInfo5, CdmaBroadcastSmsConfigInfo.readVectorFromParcel((HwParcel)arrayList));
        case 81:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          setGsmBroadcastActivationResponse(radioResponseInfo5);
        case 80:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          setGsmBroadcastConfigResponse(radioResponseInfo5);
        case 79:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          getGsmBroadcastConfigResponse(radioResponseInfo5, GsmBroadcastSmsConfigInfo.readVectorFromParcel((HwParcel)arrayList));
        case 78:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo5 = new RadioResponseInfo();
          radioResponseInfo5.readFromParcel((HwParcel)arrayList);
          acknowledgeLastIncomingCdmaSmsResponse(radioResponseInfo5);
        case 77:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo16 = new RadioResponseInfo();
          radioResponseInfo16.readFromParcel((HwParcel)arrayList);
          sendSmsResult2 = new SendSmsResult();
          sendSmsResult2.readFromParcel((HwParcel)arrayList);
          sendCdmaSmsResponse(radioResponseInfo16, sendSmsResult2);
        case 76:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          sendBurstDtmfResponse(radioResponseInfo4);
        case 75:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          sendCDMAFeatureCodeResponse(radioResponseInfo4);
        case 74:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getPreferredVoicePrivacyResponse(radioResponseInfo4, arrayList.readBool());
        case 73:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setPreferredVoicePrivacyResponse(radioResponseInfo4);
        case 72:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getTTYModeResponse(radioResponseInfo4, arrayList.readInt32());
        case 71:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setTTYModeResponse(radioResponseInfo4);
        case 70:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getCdmaRoamingPreferenceResponse(radioResponseInfo4, arrayList.readInt32());
        case 69:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setCdmaRoamingPreferenceResponse(radioResponseInfo4);
        case 68:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setCdmaSubscriptionSourceResponse(radioResponseInfo4);
        case 67:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setLocationUpdatesResponse(radioResponseInfo4);
        case 66:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getNeighboringCidsResponse(radioResponseInfo4, NeighboringCell.readVectorFromParcel((HwParcel)arrayList));
        case 65:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getPreferredNetworkTypeResponse(radioResponseInfo4, arrayList.readInt32());
        case 64:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setPreferredNetworkTypeResponse(radioResponseInfo4);
        case 63:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          explicitCallTransferResponse(radioResponseInfo4);
        case 62:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          handleStkCallSetupRequestFromSimResponse(radioResponseInfo4);
        case 61:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          sendTerminalResponseToSimResponse(radioResponseInfo4);
        case 60:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          sendEnvelopeResponse(radioResponseInfo4, arrayList.readString());
        case 59:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getAvailableBandModesResponse(radioResponseInfo4, arrayList.readInt32Vector());
        case 58:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setBandModeResponse(radioResponseInfo4);
        case 57:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          deleteSmsOnSimResponse(radioResponseInfo4);
        case 56:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          writeSmsToSimResponse(radioResponseInfo4, arrayList.readInt32());
        case 55:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setSuppServiceNotificationsResponse(radioResponseInfo4);
        case 54:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getDataCallListResponse(radioResponseInfo4, SetupDataCallResult.readVectorFromParcel((HwParcel)arrayList));
        case 53:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getClipResponse(radioResponseInfo4, arrayList.readInt32());
        case 52:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getMuteResponse(radioResponseInfo4, arrayList.readBool());
        case 51:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setMuteResponse(radioResponseInfo4);
        case 50:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          separateConnectionResponse(radioResponseInfo4);
        case 49:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getBasebandVersionResponse(radioResponseInfo4, arrayList.readString());
        case 48:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          stopDtmfResponse(radioResponseInfo4);
        case 47:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          startDtmfResponse(radioResponseInfo4);
        case 46:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getAvailableNetworksResponse(radioResponseInfo4, OperatorInfo.readVectorFromParcel((HwParcel)arrayList));
        case 45:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setNetworkSelectionModeManualResponse(radioResponseInfo4);
        case 44:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setNetworkSelectionModeAutomaticResponse(radioResponseInfo4);
        case 43:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getNetworkSelectionModeResponse(radioResponseInfo4, arrayList.readBool());
        case 42:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setBarringPasswordResponse(radioResponseInfo4);
        case 41:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setFacilityLockForAppResponse(radioResponseInfo4, arrayList.readInt32());
        case 40:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getFacilityLockForAppResponse(radioResponseInfo4, arrayList.readInt32());
        case 39:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          deactivateDataCallResponse(radioResponseInfo4);
        case 38:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          acceptCallResponse(radioResponseInfo4);
        case 37:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          acknowledgeLastIncomingGsmSmsResponse(radioResponseInfo4);
        case 36:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setCallWaitingResponse(radioResponseInfo4);
        case 35:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getCallWaitingResponse(radioResponseInfo4, arrayList.readBool(), arrayList.readInt32());
        case 34:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setCallForwardResponse(radioResponseInfo4);
        case 33:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getCallForwardStatusResponse(radioResponseInfo4, CallForwardInfo.readVectorFromParcel((HwParcel)arrayList));
        case 32:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          setClirResponse(radioResponseInfo4);
        case 31:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          getClirResponse(radioResponseInfo4, arrayList.readInt32(), arrayList.readInt32());
        case 30:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          cancelPendingUssdResponse(radioResponseInfo4);
        case 29:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          sendUssdResponse(radioResponseInfo4);
        case 28:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo4 = new RadioResponseInfo();
          radioResponseInfo4.readFromParcel((HwParcel)arrayList);
          iccIoResult2 = new IccIoResult();
          iccIoResult2.readFromParcel((HwParcel)arrayList);
          iccIOForAppResponse(radioResponseInfo4, iccIoResult2);
        case 27:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo15 = new RadioResponseInfo();
          radioResponseInfo15.readFromParcel((HwParcel)arrayList);
          setupDataCallResult = new SetupDataCallResult();
          setupDataCallResult.readFromParcel((HwParcel)arrayList);
          setupDataCallResponse(radioResponseInfo15, setupDataCallResult);
        case 26:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo3 = new RadioResponseInfo();
          radioResponseInfo3.readFromParcel((HwParcel)arrayList);
          sendSmsResult3 = new SendSmsResult();
          sendSmsResult3.readFromParcel((HwParcel)arrayList);
          sendSMSExpectMoreResponse(radioResponseInfo3, sendSmsResult3);
        case 25:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo14 = new RadioResponseInfo();
          radioResponseInfo14.readFromParcel((HwParcel)arrayList);
          sendSmsResult1 = new SendSmsResult();
          sendSmsResult1.readFromParcel((HwParcel)arrayList);
          sendSmsResponse(radioResponseInfo14, sendSmsResult1);
        case 24:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo2 = new RadioResponseInfo();
          radioResponseInfo2.readFromParcel((HwParcel)arrayList);
          sendDtmfResponse(radioResponseInfo2);
        case 23:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo2 = new RadioResponseInfo();
          radioResponseInfo2.readFromParcel((HwParcel)arrayList);
          setRadioPowerResponse(radioResponseInfo2);
        case 22:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo2 = new RadioResponseInfo();
          radioResponseInfo2.readFromParcel((HwParcel)arrayList);
          getOperatorResponse(radioResponseInfo2, arrayList.readString(), arrayList.readString(), arrayList.readString());
        case 21:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo2 = new RadioResponseInfo();
          radioResponseInfo2.readFromParcel((HwParcel)arrayList);
          dataRegStateResult1 = new DataRegStateResult();
          dataRegStateResult1.readFromParcel((HwParcel)arrayList);
          getDataRegistrationStateResponse(radioResponseInfo2, dataRegStateResult1);
        case 20:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo2 = new RadioResponseInfo();
          radioResponseInfo2.readFromParcel((HwParcel)arrayList);
          voiceRegStateResult = new VoiceRegStateResult();
          voiceRegStateResult.readFromParcel((HwParcel)arrayList);
          getVoiceRegistrationStateResponse(radioResponseInfo2, voiceRegStateResult);
        case 19:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo2 = new RadioResponseInfo();
          radioResponseInfo2.readFromParcel((HwParcel)arrayList);
          signalStrength1 = new SignalStrength();
          signalStrength1.readFromParcel((HwParcel)arrayList);
          getSignalStrengthResponse(radioResponseInfo2, signalStrength1);
        case 18:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo13 = new RadioResponseInfo();
          radioResponseInfo13.readFromParcel((HwParcel)arrayList);
          lastCallFailCauseInfo = new LastCallFailCauseInfo();
          lastCallFailCauseInfo.readFromParcel((HwParcel)arrayList);
          getLastCallFailCauseResponse(radioResponseInfo13, lastCallFailCauseInfo);
        case 17:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          rejectCallResponse(radioResponseInfo1);
        case 16:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          conferenceResponse(radioResponseInfo1);
        case 15:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          switchWaitingOrHoldingAndActiveResponse(radioResponseInfo1);
        case 14:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          hangupForegroundResumeBackgroundResponse(radioResponseInfo1);
        case 13:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          hangupWaitingOrBackgroundResponse(radioResponseInfo1);
        case 12:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          hangupConnectionResponse(radioResponseInfo1);
        case 11:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          getIMSIForAppResponse(radioResponseInfo1, arrayList.readString());
        case 10:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          dialResponse(radioResponseInfo1);
        case 9:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          getCurrentCallsResponse(radioResponseInfo1, Call.readVectorFromParcel((HwParcel)arrayList));
        case 8:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          supplyNetworkDepersonalizationResponse(radioResponseInfo1, arrayList.readInt32());
        case 7:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          changeIccPin2ForAppResponse(radioResponseInfo1, arrayList.readInt32());
        case 6:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          changeIccPinForAppResponse(radioResponseInfo1, arrayList.readInt32());
        case 5:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          supplyIccPuk2ForAppResponse(radioResponseInfo1, arrayList.readInt32());
        case 4:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          supplyIccPin2ForAppResponse(radioResponseInfo1, arrayList.readInt32());
        case 3:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          supplyIccPukForAppResponse(radioResponseInfo1, arrayList.readInt32());
        case 2:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
          radioResponseInfo1 = new RadioResponseInfo();
          radioResponseInfo1.readFromParcel((HwParcel)arrayList);
          supplyIccPinForAppResponse(radioResponseInfo1, arrayList.readInt32());
        case 1:
          break;
      } 
      arrayList.enforceInterface("android.hardware.radio@1.0::IRadioResponse");
      RadioResponseInfo radioResponseInfo1 = new RadioResponseInfo();
      radioResponseInfo1.readFromParcel((HwParcel)arrayList);
      CardStatus cardStatus1 = new CardStatus();
      cardStatus1.readFromParcel((HwParcel)arrayList);
      getIccCardStatusResponse(radioResponseInfo1, cardStatus1);
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.radio@1.3::IRadioResponse".equals(param1String) ? this : null);
    }
    
    public void registerAsService(String param1String) throws RemoteException {
      registerService(param1String);
    }
    
    public final void setHALInstrumentation() {}
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(interfaceDescriptor());
      stringBuilder.append("@Stub");
      return stringBuilder.toString();
    }
    
    public final boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) {
      return true;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_3/IRadioResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
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
import android.hardware.radio.V1_1.IRadio;
import android.hardware.radio.V1_1.ImsiEncryptionInfo;
import android.hardware.radio.V1_1.KeepaliveRequest;
import android.hardware.radio.V1_1.NetworkScanRequest;
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

public interface IRadio extends IRadio {
  public static final String kInterfaceName = "android.hardware.radio@1.2::IRadio";
  
  static IRadio asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.radio@1.2::IRadio");
    if (iHwInterface != null && iHwInterface instanceof IRadio)
      return (IRadio)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.radio@1.2::IRadio");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IRadio castFrom(IHwInterface paramIHwInterface) {
    IRadio iRadio;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iRadio = asInterface(paramIHwInterface.asBinder());
    } 
    return iRadio;
  }
  
  static IRadio getService() throws RemoteException {
    return getService("default");
  }
  
  static IRadio getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.radio@1.2::IRadio", paramString));
  }
  
  static IRadio getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.radio@1.2::IRadio", paramString, paramBoolean));
  }
  
  static IRadio getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void deactivateDataCall_1_2(int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  void setIndicationFilter_1_2(int paramInt1, int paramInt2) throws RemoteException;
  
  void setLinkCapacityReportingCriteria(int paramInt1, int paramInt2, int paramInt3, int paramInt4, ArrayList<Integer> paramArrayList1, ArrayList<Integer> paramArrayList2, int paramInt5) throws RemoteException;
  
  void setSignalStrengthReportingCriteria(int paramInt1, int paramInt2, int paramInt3, ArrayList<Integer> paramArrayList, int paramInt4) throws RemoteException;
  
  void setupDataCall_1_2(int paramInt1, int paramInt2, DataProfileInfo paramDataProfileInfo, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt3, ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2) throws RemoteException;
  
  void startNetworkScan_1_2(int paramInt, NetworkScanRequest paramNetworkScanRequest) throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class Proxy implements IRadio {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public void acceptCall(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(39, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void acknowledgeIncomingGsmSmsWithPdu(int param1Int, boolean param1Boolean, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(97, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void acknowledgeLastIncomingCdmaSms(int param1Int, CdmaSmsAck param1CdmaSmsAck) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1CdmaSmsAck.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(79, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void acknowledgeLastIncomingGsmSms(int param1Int1, boolean param1Boolean, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeBool(param1Boolean);
      null.writeInt32(param1Int2);
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
    
    public void cancelPendingUssd(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(31, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void changeIccPin2ForApp(int param1Int, String param1String1, String param1String2, String param1String3) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      hwParcel2.writeInt32(param1Int);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeString(param1String2);
      hwParcel2.writeString(param1String3);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(8, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void changeIccPinForApp(int param1Int, String param1String1, String param1String2, String param1String3) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      hwParcel2.writeInt32(param1Int);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeString(param1String2);
      hwParcel2.writeString(param1String3);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(7, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void conference(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(17, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void deactivateDataCall(int param1Int1, int param1Int2, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(40, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void deactivateDataCall_1_2(int param1Int1, int param1Int2, int param1Int3) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.2::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeInt32(param1Int3);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(142, null, hwParcel, 1);
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
    
    public void deleteSmsOnRuim(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(88, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void deleteSmsOnSim(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(58, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void dial(int param1Int, Dial param1Dial) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1Dial.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(11, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public final boolean equals(Object param1Object) {
      return HidlSupport.interfacesEqual((IHwInterface)this, param1Object);
    }
    
    public void exitEmergencyCallbackMode(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(90, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void explicitCallTransfer(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(64, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getAllowedCarriers(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(126, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getAvailableBandModes(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(60, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getAvailableNetworks(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(47, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getBasebandVersion(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(50, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getCDMASubscription(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
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
    
    public void getCallForwardStatus(int param1Int, CallForwardInfo param1CallForwardInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1CallForwardInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(34, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getCallWaiting(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(36, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getCdmaBroadcastConfig(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(83, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getCdmaRoamingPreference(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(71, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getCdmaSubscriptionSource(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(95, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getCellInfoList(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(100, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getClip(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(54, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getClir(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(32, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getCurrentCalls(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(10, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getDataCallList(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(55, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getDataRegistrationState(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
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
    
    public void getDeviceIdentity(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(89, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getFacilityLockForApp(int param1Int1, String param1String1, String param1String2, int param1Int2, String param1String3) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      hwParcel2.writeInt32(param1Int1);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeString(param1String2);
      hwParcel2.writeInt32(param1Int2);
      hwParcel2.writeString(param1String3);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(41, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void getGsmBroadcastConfig(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(80, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getHardwareConfig(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
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
    
    public void getIccCardStatus(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
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
    
    public void getImsRegistrationState(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(103, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getImsiForApp(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(12, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getLastCallFailCause(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(19, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getModemActivityInfo(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
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
    
    public void getMute(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
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
    
    public void getNeighboringCids(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(67, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getNetworkSelectionMode(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(44, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getOperator(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(23, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getPreferredNetworkType(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(66, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getPreferredVoicePrivacy(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(75, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getRadioCapability(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(119, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getSignalStrength(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(20, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getSmscAddress(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(91, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getTTYMode(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(73, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getVoiceRadioTechnology(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(99, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void getVoiceRegistrationState(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(21, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void handleStkCallSetupRequestFromSim(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(63, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void hangup(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(13, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void hangupForegroundResumeBackground(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(15, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void hangupWaitingOrBackground(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
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
    
    public void iccCloseLogicalChannel(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(107, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void iccIOForApp(int param1Int, IccIo param1IccIo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1IccIo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(29, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void iccOpenLogicalChannel(int param1Int1, String param1String, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeString(param1String);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(106, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void iccTransmitApduBasicChannel(int param1Int, SimApdu param1SimApdu) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1SimApdu.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(105, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void iccTransmitApduLogicalChannel(int param1Int, SimApdu param1SimApdu) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1SimApdu.writeToParcel(null);
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
    
    public void nvReadItem(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(109, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void nvResetConfig(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(112, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void nvWriteCdmaPrl(int param1Int, ArrayList<Byte> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeInt8Vector(param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(111, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void nvWriteItem(int param1Int, NvWriteItem param1NvWriteItem) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1NvWriteItem.writeToParcel(null);
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
    
    public void pullLceData(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(123, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void rejectCall(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(18, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void reportSmsMemoryStatus(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(93, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void reportStkServiceIsRunning(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
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
    
    public void requestIccSimAuthentication(int param1Int1, int param1Int2, String param1String1, String param1String2) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      hwParcel2.writeInt32(param1Int1);
      hwParcel2.writeInt32(param1Int2);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeString(param1String2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(116, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void requestIsimAuthentication(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(96, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void requestShutdown(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
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
    
    public void sendBurstDtmf(int param1Int1, String param1String, int param1Int2, int param1Int3) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeString(param1String);
      null.writeInt32(param1Int2);
      null.writeInt32(param1Int3);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(77, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendCDMAFeatureCode(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(76, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendCdmaSms(int param1Int, CdmaSmsMessage param1CdmaSmsMessage) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1CdmaSmsMessage.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(78, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendDeviceState(int param1Int1, int param1Int2, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(127, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendDtmf(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(25, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendEnvelope(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(61, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendEnvelopeWithStatus(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(98, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendImsSms(int param1Int, ImsSmsMessage param1ImsSmsMessage) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1ImsSmsMessage.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(104, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendSMSExpectMore(int param1Int, GsmSmsMessage param1GsmSmsMessage) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1GsmSmsMessage.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(27, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendSms(int param1Int, GsmSmsMessage param1GsmSmsMessage) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1GsmSmsMessage.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(26, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendTerminalResponseToSim(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(62, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void sendUssd(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(30, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void separateConnection(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(51, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setAllowedCarriers(int param1Int, boolean param1Boolean, CarrierRestrictions param1CarrierRestrictions) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      param1CarrierRestrictions.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(125, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setBandMode(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(59, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setBarringPassword(int param1Int, String param1String1, String param1String2, String param1String3) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      hwParcel2.writeInt32(param1Int);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeString(param1String2);
      hwParcel2.writeString(param1String3);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(43, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void setCallForward(int param1Int, CallForwardInfo param1CallForwardInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1CallForwardInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(35, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCallWaiting(int param1Int1, boolean param1Boolean, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeBool(param1Boolean);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(37, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCarrierInfoForImsiEncryption(int param1Int, ImsiEncryptionInfo param1ImsiEncryptionInfo) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadio");
      null.writeInt32(param1Int);
      param1ImsiEncryptionInfo.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(131, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCdmaBroadcastActivation(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(85, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCdmaBroadcastConfig(int param1Int, ArrayList<CdmaBroadcastSmsConfigInfo> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      CdmaBroadcastSmsConfigInfo.writeVectorToParcel(null, param1ArrayList);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(84, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCdmaRoamingPreference(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(70, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCdmaSubscriptionSource(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(69, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setCellInfoListRate(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(101, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setClir(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(33, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setDataAllowed(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(114, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setDataProfile(int param1Int, ArrayList<DataProfileInfo> param1ArrayList, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      DataProfileInfo.writeVectorToParcel(null, param1ArrayList);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(117, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setFacilityLockForApp(int param1Int1, String param1String1, boolean param1Boolean, String param1String2, int param1Int2, String param1String3) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      hwParcel2.writeInt32(param1Int1);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeBool(param1Boolean);
      hwParcel2.writeString(param1String2);
      hwParcel2.writeInt32(param1Int2);
      hwParcel2.writeString(param1String3);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(42, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void setGsmBroadcastActivation(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(82, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setGsmBroadcastConfig(int param1Int, ArrayList<GsmBroadcastSmsConfigInfo> param1ArrayList) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      GsmBroadcastSmsConfigInfo.writeVectorToParcel(null, param1ArrayList);
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
    
    public void setIndicationFilter(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(128, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setIndicationFilter_1_2(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.2::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(138, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setInitialAttachApn(int param1Int, DataProfileInfo param1DataProfileInfo, boolean param1Boolean1, boolean param1Boolean2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1DataProfileInfo.writeToParcel(null);
      null.writeBool(param1Boolean1);
      null.writeBool(param1Boolean2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(102, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setLinkCapacityReportingCriteria(int param1Int1, int param1Int2, int param1Int3, int param1Int4, ArrayList<Integer> param1ArrayList1, ArrayList<Integer> param1ArrayList2, int param1Int5) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.2::IRadio");
      hwParcel2.writeInt32(param1Int1);
      hwParcel2.writeInt32(param1Int2);
      hwParcel2.writeInt32(param1Int3);
      hwParcel2.writeInt32(param1Int4);
      hwParcel2.writeInt32Vector(param1ArrayList1);
      hwParcel2.writeInt32Vector(param1ArrayList2);
      hwParcel2.writeInt32(param1Int5);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(140, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void setLocationUpdates(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(68, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setMute(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
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
    
    public void setNetworkSelectionModeAutomatic(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(45, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setNetworkSelectionModeManual(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(46, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setPreferredNetworkType(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(65, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setPreferredVoicePrivacy(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
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
    
    public void setRadioCapability(int param1Int, RadioCapability param1RadioCapability) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1RadioCapability.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(120, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setRadioPower(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(24, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setResponseFunctions(IRadioResponse param1IRadioResponse, IRadioIndication param1IRadioIndication) throws RemoteException {
      IHwBinder iHwBinder1;
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      IHwBinder iHwBinder2 = null;
      if (param1IRadioResponse == null) {
        param1IRadioResponse = null;
      } else {
        iHwBinder1 = param1IRadioResponse.asBinder();
      } 
      hwParcel2.writeStrongBinder(iHwBinder1);
      if (param1IRadioIndication == null) {
        iHwBinder1 = iHwBinder2;
      } else {
        iHwBinder1 = param1IRadioIndication.asBinder();
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
    
    public void setSignalStrengthReportingCriteria(int param1Int1, int param1Int2, int param1Int3, ArrayList<Integer> param1ArrayList, int param1Int4) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.2::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeInt32(param1Int3);
      null.writeInt32Vector(param1ArrayList);
      null.writeInt32(param1Int4);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(139, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setSimCardPower(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(129, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setSimCardPower_1_1(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(132, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setSmscAddress(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(92, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setSuppServiceNotifications(int param1Int, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(56, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setTTYMode(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(72, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setUiccSubscription(int param1Int, SelectUiccSub param1SelectUiccSub) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1SelectUiccSub.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(113, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setupDataCall(int param1Int1, int param1Int2, DataProfileInfo param1DataProfileInfo, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      param1DataProfileInfo.writeToParcel(null);
      null.writeBool(param1Boolean1);
      null.writeBool(param1Boolean2);
      null.writeBool(param1Boolean3);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(28, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void setupDataCall_1_2(int param1Int1, int param1Int2, DataProfileInfo param1DataProfileInfo, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, int param1Int3, ArrayList<String> param1ArrayList1, ArrayList<String> param1ArrayList2) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.2::IRadio");
      hwParcel2.writeInt32(param1Int1);
      hwParcel2.writeInt32(param1Int2);
      param1DataProfileInfo.writeToParcel(hwParcel2);
      hwParcel2.writeBool(param1Boolean1);
      hwParcel2.writeBool(param1Boolean2);
      hwParcel2.writeBool(param1Boolean3);
      hwParcel2.writeInt32(param1Int3);
      hwParcel2.writeStringVector(param1ArrayList1);
      hwParcel2.writeStringVector(param1ArrayList2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(141, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void startDtmf(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(48, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void startKeepalive(int param1Int, KeepaliveRequest param1KeepaliveRequest) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadio");
      null.writeInt32(param1Int);
      param1KeepaliveRequest.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(135, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void startLceService(int param1Int1, int param1Int2, boolean param1Boolean) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      null.writeBool(param1Boolean);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(121, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void startNetworkScan(int param1Int, NetworkScanRequest param1NetworkScanRequest) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadio");
      null.writeInt32(param1Int);
      param1NetworkScanRequest.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(133, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void startNetworkScan_1_2(int param1Int, NetworkScanRequest param1NetworkScanRequest) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.2::IRadio");
      null.writeInt32(param1Int);
      param1NetworkScanRequest.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(137, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void stopDtmf(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(49, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void stopKeepalive(int param1Int1, int param1Int2) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadio");
      null.writeInt32(param1Int1);
      null.writeInt32(param1Int2);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(136, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void stopLceService(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(122, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void stopNetworkScan(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.1::IRadio");
      null.writeInt32(param1Int);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(134, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void supplyIccPin2ForApp(int param1Int, String param1String1, String param1String2) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      hwParcel2.writeInt32(param1Int);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeString(param1String2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(5, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void supplyIccPinForApp(int param1Int, String param1String1, String param1String2) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      hwParcel2.writeInt32(param1Int);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeString(param1String2);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(3, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void supplyIccPuk2ForApp(int param1Int, String param1String1, String param1String2, String param1String3) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      hwParcel2.writeInt32(param1Int);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeString(param1String2);
      hwParcel2.writeString(param1String3);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(6, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void supplyIccPukForApp(int param1Int, String param1String1, String param1String2, String param1String3) throws RemoteException {
      HwParcel hwParcel2 = new HwParcel();
      hwParcel2.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      hwParcel2.writeInt32(param1Int);
      hwParcel2.writeString(param1String1);
      hwParcel2.writeString(param1String2);
      hwParcel2.writeString(param1String3);
      HwParcel hwParcel1 = new HwParcel();
      try {
        this.mRemote.transact(4, hwParcel2, hwParcel1, 1);
        hwParcel2.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel1.release();
      } 
    }
    
    public void supplyNetworkDepersonalization(int param1Int, String param1String) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      null.writeString(param1String);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(9, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void switchWaitingOrHoldingAndActive(int param1Int) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
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
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
    
    public void writeSmsToRuim(int param1Int, CdmaSmsWriteArgs param1CdmaSmsWriteArgs) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1CdmaSmsWriteArgs.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(87, null, hwParcel, 1);
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public void writeSmsToSim(int param1Int, SmsWriteArgs param1SmsWriteArgs) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.radio@1.0::IRadio");
      null.writeInt32(param1Int);
      param1SmsWriteArgs.writeToParcel(null);
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
  
  public static abstract class Stub extends HwBinder implements IRadio {
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
      return (ArrayList)new ArrayList<>(Arrays.asList((byte[])new byte[][] { { 
                29, 25, 114, 13, 79, -45, -117, 16, -107, -16, 
                -11, 85, -92, -67, -110, -77, -79, 44, -101, 29, 
                15, 86, 11, 14, -102, 71, 76, -42, -36, -62, 
                13, -74 }, { 
                -9, -98, -33, 80, -93, 120, -87, -55, -69, 115, 
                Byte.MAX_VALUE, -109, -14, 5, -38, -71, 27, 76, 99, -22, 
                73, 114, 58, -4, 111, -123, 108, 19, -126, 3, 
                -22, -127 }, { 
                -101, 90, -92, -103, -20, 59, 66, 38, -15, 95, 
                72, -11, -19, 8, -119, 110, 47, -64, 103, 111, 
                -105, -116, -98, 25, -100, 29, -94, 29, -86, -16, 
                2, -90 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.radio@1.2::IRadio", "android.hardware.radio@1.1::IRadio", "android.hardware.radio@1.0::IRadio", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.radio@1.2::IRadio";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      DebugInfo debugInfo;
      HwBlob hwBlob1;
      String str;
      ArrayList<String> arrayList;
      DataProfileInfo dataProfileInfo3;
      NetworkScanRequest networkScanRequest1;
      KeepaliveRequest keepaliveRequest;
      NetworkScanRequest networkScanRequest;
      ImsiEncryptionInfo imsiEncryptionInfo;
      CarrierRestrictions carrierRestrictions;
      RadioCapability radioCapability;
      SelectUiccSub selectUiccSub;
      NvWriteItem nvWriteItem;
      SimApdu simApdu;
      ImsSmsMessage imsSmsMessage;
      DataProfileInfo dataProfileInfo2;
      CdmaSmsWriteArgs cdmaSmsWriteArgs;
      CdmaSmsAck cdmaSmsAck;
      CdmaSmsMessage cdmaSmsMessage;
      SmsWriteArgs smsWriteArgs;
      CallForwardInfo callForwardInfo;
      IccIo iccIo;
      DataProfileInfo dataProfileInfo1;
      GsmSmsMessage gsmSmsMessage;
      Dial dial;
      ArrayList<byte[]> arrayList1;
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
              hwBlob2 = new HwBlob(16);
              param1Int2 = arrayList1.size();
              hwBlob2.putInt32(8L, param1Int2);
              hwBlob2.putBool(12L, false);
              hwBlob1 = new HwBlob(param1Int2 * 32);
              param1Int1 = 0;
              while (param1Int1 < param1Int2) {
                long l = (param1Int1 * 32);
                byte[] arrayOfByte = arrayList1.get(param1Int1);
                if (arrayOfByte != null && arrayOfByte.length == 32) {
                  hwBlob1.putInt8Array(l, arrayOfByte);
                  param1Int1++;
                  continue;
                } 
                throw new IllegalArgumentException("Array element is not of the expected length");
              } 
              hwBlob2.putBlob(0L, hwBlob1);
              param1HwParcel2.writeBuffer(hwBlob2);
              param1HwParcel2.send();
            case 256136003:
              hwBlob1.enforceInterface("android.hidl.base@1.0::IBase");
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
        case 142:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadio");
          deactivateDataCall_1_2(arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt32());
        case 141:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadio");
          param1Int1 = arrayList.readInt32();
          param1Int2 = arrayList.readInt32();
          dataProfileInfo3 = new DataProfileInfo();
          dataProfileInfo3.readFromParcel((HwParcel)arrayList);
          setupDataCall_1_2(param1Int1, param1Int2, dataProfileInfo3, arrayList.readBool(), arrayList.readBool(), arrayList.readBool(), arrayList.readInt32(), arrayList.readStringVector(), arrayList.readStringVector());
        case 140:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadio");
          setLinkCapacityReportingCriteria(arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt32Vector(), arrayList.readInt32Vector(), arrayList.readInt32());
        case 139:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadio");
          setSignalStrengthReportingCriteria(arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt32(), arrayList.readInt32Vector(), arrayList.readInt32());
        case 138:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadio");
          setIndicationFilter_1_2(arrayList.readInt32(), arrayList.readInt32());
        case 137:
          arrayList.enforceInterface("android.hardware.radio@1.2::IRadio");
          param1Int1 = arrayList.readInt32();
          networkScanRequest1 = new NetworkScanRequest();
          networkScanRequest1.readFromParcel((HwParcel)arrayList);
          startNetworkScan_1_2(param1Int1, networkScanRequest1);
        case 136:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadio");
          stopKeepalive(arrayList.readInt32(), arrayList.readInt32());
        case 135:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadio");
          param1Int1 = arrayList.readInt32();
          keepaliveRequest = new KeepaliveRequest();
          keepaliveRequest.readFromParcel((HwParcel)arrayList);
          startKeepalive(param1Int1, keepaliveRequest);
        case 134:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadio");
          stopNetworkScan(arrayList.readInt32());
        case 133:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadio");
          param1Int1 = arrayList.readInt32();
          networkScanRequest = new NetworkScanRequest();
          networkScanRequest.readFromParcel((HwParcel)arrayList);
          startNetworkScan(param1Int1, networkScanRequest);
        case 132:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadio");
          setSimCardPower_1_1(arrayList.readInt32(), arrayList.readInt32());
        case 131:
          arrayList.enforceInterface("android.hardware.radio@1.1::IRadio");
          param1Int1 = arrayList.readInt32();
          imsiEncryptionInfo = new ImsiEncryptionInfo();
          imsiEncryptionInfo.readFromParcel((HwParcel)arrayList);
          setCarrierInfoForImsiEncryption(param1Int1, imsiEncryptionInfo);
        case 130:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          responseAcknowledgement();
        case 129:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setSimCardPower(arrayList.readInt32(), arrayList.readBool());
        case 128:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setIndicationFilter(arrayList.readInt32(), arrayList.readInt32());
        case 127:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          sendDeviceState(arrayList.readInt32(), arrayList.readInt32(), arrayList.readBool());
        case 126:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getAllowedCarriers(arrayList.readInt32());
        case 125:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          bool = arrayList.readBool();
          carrierRestrictions = new CarrierRestrictions();
          carrierRestrictions.readFromParcel((HwParcel)arrayList);
          setAllowedCarriers(param1Int1, bool, carrierRestrictions);
        case 124:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getModemActivityInfo(arrayList.readInt32());
        case 123:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          pullLceData(arrayList.readInt32());
        case 122:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          stopLceService(arrayList.readInt32());
        case 121:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          startLceService(arrayList.readInt32(), arrayList.readInt32(), arrayList.readBool());
        case 120:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          radioCapability = new RadioCapability();
          radioCapability.readFromParcel((HwParcel)arrayList);
          setRadioCapability(param1Int1, radioCapability);
        case 119:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getRadioCapability(arrayList.readInt32());
        case 118:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          requestShutdown(arrayList.readInt32());
        case 117:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setDataProfile(arrayList.readInt32(), DataProfileInfo.readVectorFromParcel((HwParcel)arrayList), arrayList.readBool());
        case 116:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          requestIccSimAuthentication(arrayList.readInt32(), arrayList.readInt32(), arrayList.readString(), arrayList.readString());
        case 115:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getHardwareConfig(arrayList.readInt32());
        case 114:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setDataAllowed(arrayList.readInt32(), arrayList.readBool());
        case 113:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          selectUiccSub = new SelectUiccSub();
          selectUiccSub.readFromParcel((HwParcel)arrayList);
          setUiccSubscription(param1Int1, selectUiccSub);
        case 112:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          nvResetConfig(arrayList.readInt32(), arrayList.readInt32());
        case 111:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          nvWriteCdmaPrl(arrayList.readInt32(), arrayList.readInt8Vector());
        case 110:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          nvWriteItem = new NvWriteItem();
          nvWriteItem.readFromParcel((HwParcel)arrayList);
          nvWriteItem(param1Int1, nvWriteItem);
        case 109:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          nvReadItem(arrayList.readInt32(), arrayList.readInt32());
        case 108:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          simApdu = new SimApdu();
          simApdu.readFromParcel((HwParcel)arrayList);
          iccTransmitApduLogicalChannel(param1Int1, simApdu);
        case 107:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          iccCloseLogicalChannel(arrayList.readInt32(), arrayList.readInt32());
        case 106:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          iccOpenLogicalChannel(arrayList.readInt32(), arrayList.readString(), arrayList.readInt32());
        case 105:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          simApdu = new SimApdu();
          simApdu.readFromParcel((HwParcel)arrayList);
          iccTransmitApduBasicChannel(param1Int1, simApdu);
        case 104:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          imsSmsMessage = new ImsSmsMessage();
          imsSmsMessage.readFromParcel((HwParcel)arrayList);
          sendImsSms(param1Int1, imsSmsMessage);
        case 103:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getImsRegistrationState(arrayList.readInt32());
        case 102:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          dataProfileInfo2 = new DataProfileInfo();
          dataProfileInfo2.readFromParcel((HwParcel)arrayList);
          setInitialAttachApn(param1Int1, dataProfileInfo2, arrayList.readBool(), arrayList.readBool());
        case 101:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setCellInfoListRate(arrayList.readInt32(), arrayList.readInt32());
        case 100:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getCellInfoList(arrayList.readInt32());
        case 99:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getVoiceRadioTechnology(arrayList.readInt32());
        case 98:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          sendEnvelopeWithStatus(arrayList.readInt32(), arrayList.readString());
        case 97:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          acknowledgeIncomingGsmSmsWithPdu(arrayList.readInt32(), arrayList.readBool(), arrayList.readString());
        case 96:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          requestIsimAuthentication(arrayList.readInt32(), arrayList.readString());
        case 95:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getCdmaSubscriptionSource(arrayList.readInt32());
        case 94:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          reportStkServiceIsRunning(arrayList.readInt32());
        case 93:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          reportSmsMemoryStatus(arrayList.readInt32(), arrayList.readBool());
        case 92:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setSmscAddress(arrayList.readInt32(), arrayList.readString());
        case 91:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getSmscAddress(arrayList.readInt32());
        case 90:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          exitEmergencyCallbackMode(arrayList.readInt32());
        case 89:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getDeviceIdentity(arrayList.readInt32());
        case 88:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          deleteSmsOnRuim(arrayList.readInt32(), arrayList.readInt32());
        case 87:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          cdmaSmsWriteArgs = new CdmaSmsWriteArgs();
          cdmaSmsWriteArgs.readFromParcel((HwParcel)arrayList);
          writeSmsToRuim(param1Int1, cdmaSmsWriteArgs);
        case 86:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getCDMASubscription(arrayList.readInt32());
        case 85:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setCdmaBroadcastActivation(arrayList.readInt32(), arrayList.readBool());
        case 84:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setCdmaBroadcastConfig(arrayList.readInt32(), CdmaBroadcastSmsConfigInfo.readVectorFromParcel((HwParcel)arrayList));
        case 83:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getCdmaBroadcastConfig(arrayList.readInt32());
        case 82:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setGsmBroadcastActivation(arrayList.readInt32(), arrayList.readBool());
        case 81:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setGsmBroadcastConfig(arrayList.readInt32(), GsmBroadcastSmsConfigInfo.readVectorFromParcel((HwParcel)arrayList));
        case 80:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getGsmBroadcastConfig(arrayList.readInt32());
        case 79:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          cdmaSmsAck = new CdmaSmsAck();
          cdmaSmsAck.readFromParcel((HwParcel)arrayList);
          acknowledgeLastIncomingCdmaSms(param1Int1, cdmaSmsAck);
        case 78:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          cdmaSmsMessage = new CdmaSmsMessage();
          cdmaSmsMessage.readFromParcel((HwParcel)arrayList);
          sendCdmaSms(param1Int1, cdmaSmsMessage);
        case 77:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          sendBurstDtmf(arrayList.readInt32(), arrayList.readString(), arrayList.readInt32(), arrayList.readInt32());
        case 76:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          sendCDMAFeatureCode(arrayList.readInt32(), arrayList.readString());
        case 75:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getPreferredVoicePrivacy(arrayList.readInt32());
        case 74:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setPreferredVoicePrivacy(arrayList.readInt32(), arrayList.readBool());
        case 73:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getTTYMode(arrayList.readInt32());
        case 72:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setTTYMode(arrayList.readInt32(), arrayList.readInt32());
        case 71:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getCdmaRoamingPreference(arrayList.readInt32());
        case 70:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setCdmaRoamingPreference(arrayList.readInt32(), arrayList.readInt32());
        case 69:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setCdmaSubscriptionSource(arrayList.readInt32(), arrayList.readInt32());
        case 68:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setLocationUpdates(arrayList.readInt32(), arrayList.readBool());
        case 67:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getNeighboringCids(arrayList.readInt32());
        case 66:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getPreferredNetworkType(arrayList.readInt32());
        case 65:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setPreferredNetworkType(arrayList.readInt32(), arrayList.readInt32());
        case 64:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          explicitCallTransfer(arrayList.readInt32());
        case 63:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          handleStkCallSetupRequestFromSim(arrayList.readInt32(), arrayList.readBool());
        case 62:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          sendTerminalResponseToSim(arrayList.readInt32(), arrayList.readString());
        case 61:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          sendEnvelope(arrayList.readInt32(), arrayList.readString());
        case 60:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getAvailableBandModes(arrayList.readInt32());
        case 59:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setBandMode(arrayList.readInt32(), arrayList.readInt32());
        case 58:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          deleteSmsOnSim(arrayList.readInt32(), arrayList.readInt32());
        case 57:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          smsWriteArgs = new SmsWriteArgs();
          smsWriteArgs.readFromParcel((HwParcel)arrayList);
          writeSmsToSim(param1Int1, smsWriteArgs);
        case 56:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setSuppServiceNotifications(arrayList.readInt32(), arrayList.readBool());
        case 55:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getDataCallList(arrayList.readInt32());
        case 54:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getClip(arrayList.readInt32());
        case 53:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getMute(arrayList.readInt32());
        case 52:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setMute(arrayList.readInt32(), arrayList.readBool());
        case 51:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          separateConnection(arrayList.readInt32(), arrayList.readInt32());
        case 50:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getBasebandVersion(arrayList.readInt32());
        case 49:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          stopDtmf(arrayList.readInt32());
        case 48:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          startDtmf(arrayList.readInt32(), arrayList.readString());
        case 47:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getAvailableNetworks(arrayList.readInt32());
        case 46:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setNetworkSelectionModeManual(arrayList.readInt32(), arrayList.readString());
        case 45:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setNetworkSelectionModeAutomatic(arrayList.readInt32());
        case 44:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getNetworkSelectionMode(arrayList.readInt32());
        case 43:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setBarringPassword(arrayList.readInt32(), arrayList.readString(), arrayList.readString(), arrayList.readString());
        case 42:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setFacilityLockForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readBool(), arrayList.readString(), arrayList.readInt32(), arrayList.readString());
        case 41:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getFacilityLockForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString(), arrayList.readInt32(), arrayList.readString());
        case 40:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          deactivateDataCall(arrayList.readInt32(), arrayList.readInt32(), arrayList.readBool());
        case 39:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          acceptCall(arrayList.readInt32());
        case 38:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          acknowledgeLastIncomingGsmSms(arrayList.readInt32(), arrayList.readBool(), arrayList.readInt32());
        case 37:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setCallWaiting(arrayList.readInt32(), arrayList.readBool(), arrayList.readInt32());
        case 36:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getCallWaiting(arrayList.readInt32(), arrayList.readInt32());
        case 35:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          callForwardInfo = new CallForwardInfo();
          callForwardInfo.readFromParcel((HwParcel)arrayList);
          setCallForward(param1Int1, callForwardInfo);
        case 34:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          callForwardInfo = new CallForwardInfo();
          callForwardInfo.readFromParcel((HwParcel)arrayList);
          getCallForwardStatus(param1Int1, callForwardInfo);
        case 33:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setClir(arrayList.readInt32(), arrayList.readInt32());
        case 32:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getClir(arrayList.readInt32());
        case 31:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          cancelPendingUssd(arrayList.readInt32());
        case 30:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          sendUssd(arrayList.readInt32(), arrayList.readString());
        case 29:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          iccIo = new IccIo();
          iccIo.readFromParcel((HwParcel)arrayList);
          iccIOForApp(param1Int1, iccIo);
        case 28:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int2 = arrayList.readInt32();
          param1Int1 = arrayList.readInt32();
          dataProfileInfo1 = new DataProfileInfo();
          dataProfileInfo1.readFromParcel((HwParcel)arrayList);
          setupDataCall(param1Int2, param1Int1, dataProfileInfo1, arrayList.readBool(), arrayList.readBool(), arrayList.readBool());
        case 27:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          gsmSmsMessage = new GsmSmsMessage();
          gsmSmsMessage.readFromParcel((HwParcel)arrayList);
          sendSMSExpectMore(param1Int1, gsmSmsMessage);
        case 26:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          gsmSmsMessage = new GsmSmsMessage();
          gsmSmsMessage.readFromParcel((HwParcel)arrayList);
          sendSms(param1Int1, gsmSmsMessage);
        case 25:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          sendDtmf(arrayList.readInt32(), arrayList.readString());
        case 24:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          setRadioPower(arrayList.readInt32(), arrayList.readBool());
        case 23:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getOperator(arrayList.readInt32());
        case 22:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getDataRegistrationState(arrayList.readInt32());
        case 21:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getVoiceRegistrationState(arrayList.readInt32());
        case 20:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getSignalStrength(arrayList.readInt32());
        case 19:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getLastCallFailCause(arrayList.readInt32());
        case 18:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          rejectCall(arrayList.readInt32());
        case 17:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          conference(arrayList.readInt32());
        case 16:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          switchWaitingOrHoldingAndActive(arrayList.readInt32());
        case 15:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          hangupForegroundResumeBackground(arrayList.readInt32());
        case 14:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          hangupWaitingOrBackground(arrayList.readInt32());
        case 13:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          hangup(arrayList.readInt32(), arrayList.readInt32());
        case 12:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getImsiForApp(arrayList.readInt32(), arrayList.readString());
        case 11:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          param1Int1 = arrayList.readInt32();
          dial = new Dial();
          dial.readFromParcel((HwParcel)arrayList);
          dial(param1Int1, dial);
        case 10:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getCurrentCalls(arrayList.readInt32());
        case 9:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          supplyNetworkDepersonalization(arrayList.readInt32(), arrayList.readString());
        case 8:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          changeIccPin2ForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString(), arrayList.readString());
        case 7:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          changeIccPinForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString(), arrayList.readString());
        case 6:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          supplyIccPuk2ForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString(), arrayList.readString());
        case 5:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          supplyIccPin2ForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString());
        case 4:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          supplyIccPukForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString(), arrayList.readString());
        case 3:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          supplyIccPinForApp(arrayList.readInt32(), arrayList.readString(), arrayList.readString());
        case 2:
          arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
          getIccCardStatus(arrayList.readInt32());
        case 1:
          break;
      } 
      arrayList.enforceInterface("android.hardware.radio@1.0::IRadio");
      setResponseFunctions(IRadioResponse.asInterface(arrayList.readStrongBinder()), IRadioIndication.asInterface(arrayList.readStrongBinder()));
      dial.writeStatus(0);
      dial.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.radio@1.2::IRadio".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/V1_2/IRadio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
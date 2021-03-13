package android.hardware.gnss.V1_1;

import android.hardware.gnss.V1_0.IGnssMeasurementCallback;
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

public interface IGnssMeasurementCallback extends IGnssMeasurementCallback {
  public static final String kInterfaceName = "android.hardware.gnss@1.1::IGnssMeasurementCallback";
  
  static IGnssMeasurementCallback asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@1.1::IGnssMeasurementCallback");
    if (iHwInterface != null && iHwInterface instanceof IGnssMeasurementCallback)
      return (IGnssMeasurementCallback)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@1.1::IGnssMeasurementCallback");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IGnssMeasurementCallback castFrom(IHwInterface paramIHwInterface) {
    IGnssMeasurementCallback iGnssMeasurementCallback;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iGnssMeasurementCallback = asInterface(paramIHwInterface.asBinder());
    } 
    return iGnssMeasurementCallback;
  }
  
  static IGnssMeasurementCallback getService() throws RemoteException {
    return getService("default");
  }
  
  static IGnssMeasurementCallback getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.1::IGnssMeasurementCallback", paramString));
  }
  
  static IGnssMeasurementCallback getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.1::IGnssMeasurementCallback", paramString, paramBoolean));
  }
  
  static IGnssMeasurementCallback getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  void gnssMeasurementCb(GnssData paramGnssData) throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class GnssAccumulatedDeltaRangeState {
    public static final short ADR_STATE_CYCLE_SLIP = 4;
    
    public static final short ADR_STATE_HALF_CYCLE_RESOLVED = 8;
    
    public static final short ADR_STATE_RESET = 2;
    
    public static final short ADR_STATE_UNKNOWN = 0;
    
    public static final short ADR_STATE_VALID = 1;
    
    public static final String dumpBitfield(short param1Short) {
      ArrayList<String> arrayList = new ArrayList();
      short s1 = 0;
      arrayList.add("ADR_STATE_UNKNOWN");
      if ((param1Short & 0x1) == 1) {
        arrayList.add("ADR_STATE_VALID");
        s1 = (short)(false | true);
      } 
      short s2 = s1;
      if ((param1Short & 0x2) == 2) {
        arrayList.add("ADR_STATE_RESET");
        s2 = (short)(s1 | 0x2);
      } 
      s1 = s2;
      if ((param1Short & 0x4) == 4) {
        arrayList.add("ADR_STATE_CYCLE_SLIP");
        s1 = (short)(s2 | 0x4);
      } 
      s2 = s1;
      if ((param1Short & 0x8) == 8) {
        arrayList.add("ADR_STATE_HALF_CYCLE_RESOLVED");
        s2 = (short)(s1 | 0x8);
      } 
      if (param1Short != s2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Short.toUnsignedInt((short)(s2 & param1Short))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(short param1Short) {
      if (param1Short == 0)
        return "ADR_STATE_UNKNOWN"; 
      if (param1Short == 1)
        return "ADR_STATE_VALID"; 
      if (param1Short == 2)
        return "ADR_STATE_RESET"; 
      if (param1Short == 4)
        return "ADR_STATE_CYCLE_SLIP"; 
      if (param1Short == 8)
        return "ADR_STATE_HALF_CYCLE_RESOLVED"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Short.toUnsignedInt(param1Short)));
      return stringBuilder.toString();
    }
  }
  
  public static final class GnssData {
    public IGnssMeasurementCallback.GnssClock clock = new IGnssMeasurementCallback.GnssClock();
    
    public ArrayList<IGnssMeasurementCallback.GnssMeasurement> measurements = new ArrayList<>();
    
    public static final ArrayList<GnssData> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<GnssData> arrayList = new ArrayList();
      HwBlob hwBlob = param1HwParcel.readBuffer(16L);
      int i = hwBlob.getInt32(8L);
      hwBlob = param1HwParcel.readEmbeddedBuffer((i * 88), hwBlob.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        GnssData gnssData = new GnssData();
        gnssData.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 88));
        arrayList.add(gnssData);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<GnssData> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 88);
      for (byte b = 0; b < i; b++)
        ((GnssData)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 88)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != GnssData.class)
        return false; 
      param1Object = param1Object;
      return !HidlSupport.deepEquals(this.measurements, ((GnssData)param1Object).measurements) ? false : (!!HidlSupport.deepEquals(this.clock, ((GnssData)param1Object).clock));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.measurements)), Integer.valueOf(HidlSupport.deepHashCode(this.clock)) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      int i = param1HwBlob.getInt32(param1Long + 0L + 8L);
      HwBlob hwBlob = param1HwParcel.readEmbeddedBuffer((i * 152), param1HwBlob.handle(), param1Long + 0L + 0L, true);
      this.measurements.clear();
      for (byte b = 0; b < i; b++) {
        IGnssMeasurementCallback.GnssMeasurement gnssMeasurement = new IGnssMeasurementCallback.GnssMeasurement();
        gnssMeasurement.readEmbeddedFromParcel(param1HwParcel, hwBlob, (b * 152));
        this.measurements.add(gnssMeasurement);
      } 
      this.clock.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, param1Long + 16L);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(88L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".measurements = ");
      stringBuilder.append(this.measurements);
      stringBuilder.append(", .clock = ");
      stringBuilder.append(this.clock);
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      int i = this.measurements.size();
      param1HwBlob.putInt32(param1Long + 0L + 8L, i);
      param1HwBlob.putBool(param1Long + 0L + 12L, false);
      HwBlob hwBlob = new HwBlob(i * 152);
      for (byte b = 0; b < i; b++)
        ((IGnssMeasurementCallback.GnssMeasurement)this.measurements.get(b)).writeEmbeddedToBlob(hwBlob, (b * 152)); 
      param1HwBlob.putBlob(param1Long + 0L + 0L, hwBlob);
      this.clock.writeEmbeddedToBlob(param1HwBlob, 16L + param1Long);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(88);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class GnssMeasurement {
    public short accumulatedDeltaRangeState;
    
    public IGnssMeasurementCallback.GnssMeasurement v1_0 = new IGnssMeasurementCallback.GnssMeasurement();
    
    public static final ArrayList<GnssMeasurement> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<GnssMeasurement> arrayList = new ArrayList();
      HwBlob hwBlob1 = param1HwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 152), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        GnssMeasurement gnssMeasurement = new GnssMeasurement();
        gnssMeasurement.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 152));
        arrayList.add(gnssMeasurement);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<GnssMeasurement> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 152);
      for (byte b = 0; b < i; b++)
        ((GnssMeasurement)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 152)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != GnssMeasurement.class)
        return false; 
      param1Object = param1Object;
      return !HidlSupport.deepEquals(this.v1_0, ((GnssMeasurement)param1Object).v1_0) ? false : (!!HidlSupport.deepEquals(Short.valueOf(this.accumulatedDeltaRangeState), Short.valueOf(((GnssMeasurement)param1Object).accumulatedDeltaRangeState)));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(this.v1_0)), Integer.valueOf(HidlSupport.deepHashCode(Short.valueOf(this.accumulatedDeltaRangeState))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.v1_0.readEmbeddedFromParcel(param1HwParcel, param1HwBlob, 0L + param1Long);
      this.accumulatedDeltaRangeState = param1HwBlob.getInt16(144L + param1Long);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(152L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".v1_0 = ");
      stringBuilder.append(this.v1_0);
      stringBuilder.append(", .accumulatedDeltaRangeState = ");
      stringBuilder.append(IGnssMeasurementCallback.GnssAccumulatedDeltaRangeState.dumpBitfield(this.accumulatedDeltaRangeState));
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      this.v1_0.writeEmbeddedToBlob(param1HwBlob, 0L + param1Long);
      param1HwBlob.putInt16(144L + param1Long, this.accumulatedDeltaRangeState);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(152);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class Proxy implements IGnssMeasurementCallback {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
    }
    
    public void GnssMeasurementCb(IGnssMeasurementCallback.GnssData param1GnssData) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssMeasurementCallback");
      param1GnssData.writeToParcel(null);
      HwParcel hwParcel = new HwParcel();
      try {
        this.mRemote.transact(1, null, hwParcel, 0);
        hwParcel.verifySuccess();
        null.releaseTemporaryStorage();
        return;
      } finally {
        hwParcel.release();
      } 
    }
    
    public IHwBinder asBinder() {
      return this.mRemote;
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
    
    public final boolean equals(Object param1Object) {
      return HidlSupport.interfacesEqual((IHwInterface)this, param1Object);
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
    
    public void gnssMeasurementCb(IGnssMeasurementCallback.GnssData param1GnssData) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.1::IGnssMeasurementCallback");
      param1GnssData.writeToParcel(null);
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
        return "[class or subclass of android.hardware.gnss@1.1::IGnssMeasurementCallback]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IGnssMeasurementCallback {
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
                -125, -25, -95, 15, -13, 112, 33, 71, -67, Byte.MAX_VALUE, 
                -6, 4, 86, 123, 32, -44, 7, -93, -79, 107, 
                -69, 119, 5, 100, 74, -12, 77, -111, -102, -2, 
                -111, 3 }, { 
                -41, 2, -5, 1, -36, 42, 7, 51, -86, -126, 
                11, 126, -74, 84, 53, -18, 51, 52, -9, 86, 
                50, -17, -120, 11, -81, -46, -5, -120, 3, -94, 
                10, 88 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@1.1::IGnssMeasurementCallback", "android.hardware.gnss@1.0::IGnssMeasurementCallback", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@1.1::IGnssMeasurementCallback";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      ArrayList<String> arrayList;
      if (param1Int1 != 1) {
        if (param1Int1 != 2) {
          DebugInfo debugInfo;
          ArrayList<byte[]> arrayList1;
          String str;
          HwBlob hwBlob1;
          HwBlob hwBlob2;
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
        } 
        arrayList.enforceInterface("android.hardware.gnss@1.1::IGnssMeasurementCallback");
        IGnssMeasurementCallback.GnssData gnssData1 = new IGnssMeasurementCallback.GnssData();
        gnssData1.readFromParcel((HwParcel)arrayList);
        gnssMeasurementCb(gnssData1);
        param1HwParcel2.writeStatus(0);
        param1HwParcel2.send();
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssMeasurementCallback");
      IGnssMeasurementCallback.GnssData gnssData = new IGnssMeasurementCallback.GnssData();
      gnssData.readFromParcel((HwParcel)arrayList);
      GnssMeasurementCb(gnssData);
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@1.1::IGnssMeasurementCallback".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_1/IGnssMeasurementCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
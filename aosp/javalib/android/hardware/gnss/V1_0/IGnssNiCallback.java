package android.hardware.gnss.V1_0;

import android.internal.hidl.base.V1_0.DebugInfo;
import android.internal.hidl.base.V1_0.IBase;
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

public interface IGnssNiCallback extends IBase {
  public static final String kInterfaceName = "android.hardware.gnss@1.0::IGnssNiCallback";
  
  static IGnssNiCallback asInterface(IHwBinder paramIHwBinder) {
    if (paramIHwBinder == null)
      return null; 
    IHwInterface iHwInterface = paramIHwBinder.queryLocalInterface("android.hardware.gnss@1.0::IGnssNiCallback");
    if (iHwInterface != null && iHwInterface instanceof IGnssNiCallback)
      return (IGnssNiCallback)iHwInterface; 
    Proxy proxy = new Proxy(paramIHwBinder);
    try {
      Iterator<String> iterator = proxy.interfaceChain().iterator();
      while (iterator.hasNext()) {
        boolean bool = ((String)iterator.next()).equals("android.hardware.gnss@1.0::IGnssNiCallback");
        if (bool)
          return proxy; 
      } 
    } catch (RemoteException remoteException) {}
    return null;
  }
  
  static IGnssNiCallback castFrom(IHwInterface paramIHwInterface) {
    IGnssNiCallback iGnssNiCallback;
    if (paramIHwInterface == null) {
      paramIHwInterface = null;
    } else {
      iGnssNiCallback = asInterface(paramIHwInterface.asBinder());
    } 
    return iGnssNiCallback;
  }
  
  static IGnssNiCallback getService() throws RemoteException {
    return getService("default");
  }
  
  static IGnssNiCallback getService(String paramString) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IGnssNiCallback", paramString));
  }
  
  static IGnssNiCallback getService(String paramString, boolean paramBoolean) throws RemoteException {
    return asInterface(HwBinder.getService("android.hardware.gnss@1.0::IGnssNiCallback", paramString, paramBoolean));
  }
  
  static IGnssNiCallback getService(boolean paramBoolean) throws RemoteException {
    return getService("default", paramBoolean);
  }
  
  IHwBinder asBinder();
  
  void debug(NativeHandle paramNativeHandle, ArrayList<String> paramArrayList) throws RemoteException;
  
  DebugInfo getDebugInfo() throws RemoteException;
  
  ArrayList<byte[]> getHashChain() throws RemoteException;
  
  ArrayList<String> interfaceChain() throws RemoteException;
  
  String interfaceDescriptor() throws RemoteException;
  
  boolean linkToDeath(IHwBinder.DeathRecipient paramDeathRecipient, long paramLong) throws RemoteException;
  
  void niNotifyCb(GnssNiNotification paramGnssNiNotification) throws RemoteException;
  
  void notifySyspropsChanged() throws RemoteException;
  
  void ping() throws RemoteException;
  
  void setHALInstrumentation() throws RemoteException;
  
  boolean unlinkToDeath(IHwBinder.DeathRecipient paramDeathRecipient) throws RemoteException;
  
  public static final class GnssNiEncodingType {
    public static final int ENC_NONE = 0;
    
    public static final int ENC_SUPL_GSM_DEFAULT = 1;
    
    public static final int ENC_SUPL_UCS2 = 3;
    
    public static final int ENC_SUPL_UTF8 = 2;
    
    public static final int ENC_UNKNOWN = -1;
    
    public static final String dumpBitfield(int param1Int) {
      ArrayList<String> arrayList = new ArrayList();
      int i = 0;
      arrayList.add("ENC_NONE");
      if ((param1Int & 0x1) == 1) {
        arrayList.add("ENC_SUPL_GSM_DEFAULT");
        i = false | true;
      } 
      int j = i;
      if ((param1Int & 0x2) == 2) {
        arrayList.add("ENC_SUPL_UTF8");
        j = i | 0x2;
      } 
      i = j;
      if ((param1Int & 0x3) == 3) {
        arrayList.add("ENC_SUPL_UCS2");
        i = j | 0x3;
      } 
      j = i;
      if ((param1Int & 0xFFFFFFFF) == -1) {
        arrayList.add("ENC_UNKNOWN");
        j = i | 0xFFFFFFFF;
      } 
      if (param1Int != j) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(j & param1Int));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(int param1Int) {
      if (param1Int == 0)
        return "ENC_NONE"; 
      if (param1Int == 1)
        return "ENC_SUPL_GSM_DEFAULT"; 
      if (param1Int == 2)
        return "ENC_SUPL_UTF8"; 
      if (param1Int == 3)
        return "ENC_SUPL_UCS2"; 
      if (param1Int == -1)
        return "ENC_UNKNOWN"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(param1Int));
      return stringBuilder.toString();
    }
  }
  
  public static final class GnssNiNotification {
    public byte defaultResponse = (byte)0;
    
    public byte niType = (byte)0;
    
    public int notificationId = 0;
    
    public int notificationIdEncoding = 0;
    
    public String notificationMessage = new String();
    
    public int notifyFlags;
    
    public String requestorId = new String();
    
    public int requestorIdEncoding = 0;
    
    public int timeoutSec = 0;
    
    public static final ArrayList<GnssNiNotification> readVectorFromParcel(HwParcel param1HwParcel) {
      ArrayList<GnssNiNotification> arrayList = new ArrayList();
      HwBlob hwBlob1 = param1HwParcel.readBuffer(16L);
      int i = hwBlob1.getInt32(8L);
      HwBlob hwBlob2 = param1HwParcel.readEmbeddedBuffer((i * 64), hwBlob1.handle(), 0L, true);
      arrayList.clear();
      for (byte b = 0; b < i; b++) {
        GnssNiNotification gnssNiNotification = new GnssNiNotification();
        gnssNiNotification.readEmbeddedFromParcel(param1HwParcel, hwBlob2, (b * 64));
        arrayList.add(gnssNiNotification);
      } 
      return arrayList;
    }
    
    public static final void writeVectorToParcel(HwParcel param1HwParcel, ArrayList<GnssNiNotification> param1ArrayList) {
      HwBlob hwBlob1 = new HwBlob(16);
      int i = param1ArrayList.size();
      hwBlob1.putInt32(8L, i);
      hwBlob1.putBool(12L, false);
      HwBlob hwBlob2 = new HwBlob(i * 64);
      for (byte b = 0; b < i; b++)
        ((GnssNiNotification)param1ArrayList.get(b)).writeEmbeddedToBlob(hwBlob2, (b * 64)); 
      hwBlob1.putBlob(0L, hwBlob2);
      param1HwParcel.writeBuffer(hwBlob1);
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (param1Object == null)
        return false; 
      if (param1Object.getClass() != GnssNiNotification.class)
        return false; 
      param1Object = param1Object;
      return (this.notificationId != ((GnssNiNotification)param1Object).notificationId) ? false : ((this.niType != ((GnssNiNotification)param1Object).niType) ? false : (!HidlSupport.deepEquals(Integer.valueOf(this.notifyFlags), Integer.valueOf(((GnssNiNotification)param1Object).notifyFlags)) ? false : ((this.timeoutSec != ((GnssNiNotification)param1Object).timeoutSec) ? false : ((this.defaultResponse != ((GnssNiNotification)param1Object).defaultResponse) ? false : (!HidlSupport.deepEquals(this.requestorId, ((GnssNiNotification)param1Object).requestorId) ? false : (!HidlSupport.deepEquals(this.notificationMessage, ((GnssNiNotification)param1Object).notificationMessage) ? false : ((this.requestorIdEncoding != ((GnssNiNotification)param1Object).requestorIdEncoding) ? false : (!(this.notificationIdEncoding != ((GnssNiNotification)param1Object).notificationIdEncoding)))))))));
    }
    
    public final int hashCode() {
      return Objects.hash(new Object[] { Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.notificationId))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.niType))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.notifyFlags))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.timeoutSec))), Integer.valueOf(HidlSupport.deepHashCode(Byte.valueOf(this.defaultResponse))), Integer.valueOf(HidlSupport.deepHashCode(this.requestorId)), Integer.valueOf(HidlSupport.deepHashCode(this.notificationMessage)), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.requestorIdEncoding))), Integer.valueOf(HidlSupport.deepHashCode(Integer.valueOf(this.notificationIdEncoding))) });
    }
    
    public final void readEmbeddedFromParcel(HwParcel param1HwParcel, HwBlob param1HwBlob, long param1Long) {
      this.notificationId = param1HwBlob.getInt32(param1Long + 0L);
      this.niType = param1HwBlob.getInt8(param1Long + 4L);
      this.notifyFlags = param1HwBlob.getInt32(param1Long + 8L);
      this.timeoutSec = param1HwBlob.getInt32(param1Long + 12L);
      this.defaultResponse = param1HwBlob.getInt8(param1Long + 16L);
      String str = param1HwBlob.getString(param1Long + 24L);
      this.requestorId = str;
      param1HwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), param1HwBlob.handle(), param1Long + 24L + 0L, false);
      str = param1HwBlob.getString(param1Long + 40L);
      this.notificationMessage = str;
      param1HwParcel.readEmbeddedBuffer(((str.getBytes()).length + 1), param1HwBlob.handle(), param1Long + 40L + 0L, false);
      this.requestorIdEncoding = param1HwBlob.getInt32(param1Long + 56L);
      this.notificationIdEncoding = param1HwBlob.getInt32(param1Long + 60L);
    }
    
    public final void readFromParcel(HwParcel param1HwParcel) {
      readEmbeddedFromParcel(param1HwParcel, param1HwParcel.readBuffer(64L), 0L);
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("{");
      stringBuilder.append(".notificationId = ");
      stringBuilder.append(this.notificationId);
      stringBuilder.append(", .niType = ");
      stringBuilder.append(IGnssNiCallback.GnssNiType.toString(this.niType));
      stringBuilder.append(", .notifyFlags = ");
      stringBuilder.append(IGnssNiCallback.GnssNiNotifyFlags.dumpBitfield(this.notifyFlags));
      stringBuilder.append(", .timeoutSec = ");
      stringBuilder.append(this.timeoutSec);
      stringBuilder.append(", .defaultResponse = ");
      stringBuilder.append(IGnssNiCallback.GnssUserResponseType.toString(this.defaultResponse));
      stringBuilder.append(", .requestorId = ");
      stringBuilder.append(this.requestorId);
      stringBuilder.append(", .notificationMessage = ");
      stringBuilder.append(this.notificationMessage);
      stringBuilder.append(", .requestorIdEncoding = ");
      stringBuilder.append(IGnssNiCallback.GnssNiEncodingType.toString(this.requestorIdEncoding));
      stringBuilder.append(", .notificationIdEncoding = ");
      stringBuilder.append(IGnssNiCallback.GnssNiEncodingType.toString(this.notificationIdEncoding));
      stringBuilder.append("}");
      return stringBuilder.toString();
    }
    
    public final void writeEmbeddedToBlob(HwBlob param1HwBlob, long param1Long) {
      param1HwBlob.putInt32(0L + param1Long, this.notificationId);
      param1HwBlob.putInt8(4L + param1Long, this.niType);
      param1HwBlob.putInt32(8L + param1Long, this.notifyFlags);
      param1HwBlob.putInt32(12L + param1Long, this.timeoutSec);
      param1HwBlob.putInt8(16L + param1Long, this.defaultResponse);
      param1HwBlob.putString(24L + param1Long, this.requestorId);
      param1HwBlob.putString(40L + param1Long, this.notificationMessage);
      param1HwBlob.putInt32(56L + param1Long, this.requestorIdEncoding);
      param1HwBlob.putInt32(60L + param1Long, this.notificationIdEncoding);
    }
    
    public final void writeToParcel(HwParcel param1HwParcel) {
      HwBlob hwBlob = new HwBlob(64);
      writeEmbeddedToBlob(hwBlob, 0L);
      param1HwParcel.writeBuffer(hwBlob);
    }
  }
  
  public static final class GnssNiNotifyFlags {
    public static final int NEED_NOTIFY = 1;
    
    public static final int NEED_VERIFY = 2;
    
    public static final int PRIVACY_OVERRIDE = 4;
    
    public static final String dumpBitfield(int param1Int) {
      ArrayList<String> arrayList = new ArrayList();
      int i = 0;
      if ((param1Int & 0x1) == 1) {
        arrayList.add("NEED_NOTIFY");
        i = false | true;
      } 
      int j = i;
      if ((param1Int & 0x2) == 2) {
        arrayList.add("NEED_VERIFY");
        j = i | 0x2;
      } 
      i = j;
      if ((param1Int & 0x4) == 4) {
        arrayList.add("PRIVACY_OVERRIDE");
        i = j | 0x4;
      } 
      if (param1Int != i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(i & param1Int));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(int param1Int) {
      if (param1Int == 1)
        return "NEED_NOTIFY"; 
      if (param1Int == 2)
        return "NEED_VERIFY"; 
      if (param1Int == 4)
        return "PRIVACY_OVERRIDE"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(param1Int));
      return stringBuilder.toString();
    }
  }
  
  public static final class GnssNiType {
    public static final byte EMERGENCY_SUPL = 4;
    
    public static final byte UMTS_CTRL_PLANE = 3;
    
    public static final byte UMTS_SUPL = 2;
    
    public static final byte VOICE = 1;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("VOICE");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("UMTS_SUPL");
        b2 = (byte)(b1 | 0x2);
      } 
      b1 = b2;
      if ((param1Byte & 0x3) == 3) {
        arrayList.add("UMTS_CTRL_PLANE");
        b1 = (byte)(b2 | 0x3);
      } 
      b2 = b1;
      if ((param1Byte & 0x4) == 4) {
        arrayList.add("EMERGENCY_SUPL");
        b2 = (byte)(b1 | 0x4);
      } 
      if (param1Byte != b2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b2 & param1Byte))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(byte param1Byte) {
      if (param1Byte == 1)
        return "VOICE"; 
      if (param1Byte == 2)
        return "UMTS_SUPL"; 
      if (param1Byte == 3)
        return "UMTS_CTRL_PLANE"; 
      if (param1Byte == 4)
        return "EMERGENCY_SUPL"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class GnssUserResponseType {
    public static final byte RESPONSE_ACCEPT = 1;
    
    public static final byte RESPONSE_DENY = 2;
    
    public static final byte RESPONSE_NORESP = 3;
    
    public static final String dumpBitfield(byte param1Byte) {
      ArrayList<String> arrayList = new ArrayList();
      byte b1 = 0;
      if ((param1Byte & 0x1) == 1) {
        arrayList.add("RESPONSE_ACCEPT");
        b1 = (byte)(false | true);
      } 
      byte b2 = b1;
      if ((param1Byte & 0x2) == 2) {
        arrayList.add("RESPONSE_DENY");
        b2 = (byte)(b1 | 0x2);
      } 
      b1 = b2;
      if ((param1Byte & 0x3) == 3) {
        arrayList.add("RESPONSE_NORESP");
        b1 = (byte)(b2 | 0x3);
      } 
      if (param1Byte != b1) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0x");
        stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt((byte)(b1 & param1Byte))));
        arrayList.add(stringBuilder.toString());
      } 
      return String.join(" | ", (Iterable)arrayList);
    }
    
    public static final String toString(byte param1Byte) {
      if (param1Byte == 1)
        return "RESPONSE_ACCEPT"; 
      if (param1Byte == 2)
        return "RESPONSE_DENY"; 
      if (param1Byte == 3)
        return "RESPONSE_NORESP"; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("0x");
      stringBuilder.append(Integer.toHexString(Byte.toUnsignedInt(param1Byte)));
      return stringBuilder.toString();
    }
  }
  
  public static final class Proxy implements IGnssNiCallback {
    private IHwBinder mRemote;
    
    public Proxy(IHwBinder param1IHwBinder) {
      Objects.requireNonNull(param1IHwBinder);
      this.mRemote = param1IHwBinder;
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
    
    public void niNotifyCb(IGnssNiCallback.GnssNiNotification param1GnssNiNotification) throws RemoteException {
      null = new HwParcel();
      null.writeInterfaceToken("android.hardware.gnss@1.0::IGnssNiCallback");
      param1GnssNiNotification.writeToParcel(null);
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
        return "[class or subclass of android.hardware.gnss@1.0::IGnssNiCallback]@Proxy";
      } 
    }
    
    public boolean unlinkToDeath(IHwBinder.DeathRecipient param1DeathRecipient) throws RemoteException {
      return this.mRemote.unlinkToDeath(param1DeathRecipient);
    }
  }
  
  public static abstract class Stub extends HwBinder implements IGnssNiCallback {
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
                -57, -127, -73, -79, 37, -10, -117, -27, -37, -118, 
                -116, 61, 65, 45, 82, 106, -51, -67, -9, 125, 
                -52, 89, 42, 76, 14, -41, 11, -116, -28, -2, 
                108, 73 }, { 
                -20, Byte.MAX_VALUE, -41, -98, -48, 45, -6, -123, -68, 73, 
                -108, 38, -83, -82, 62, -66, 35, -17, 5, 36, 
                -13, -51, 105, 87, 19, -109, 36, -72, 59, 24, 
                -54, 76 } }));
    }
    
    public final ArrayList<String> interfaceChain() {
      return new ArrayList<>(Arrays.asList(new String[] { "android.hardware.gnss@1.0::IGnssNiCallback", "android.hidl.base@1.0::IBase" }));
    }
    
    public final String interfaceDescriptor() {
      return "android.hardware.gnss@1.0::IGnssNiCallback";
    }
    
    public final boolean linkToDeath(IHwBinder.DeathRecipient param1DeathRecipient, long param1Long) {
      return true;
    }
    
    public final void notifySyspropsChanged() {
      HwBinder.enableInstrumentation();
    }
    
    public void onTransact(int param1Int1, HwParcel param1HwParcel1, HwParcel param1HwParcel2, int param1Int2) throws RemoteException {
      DebugInfo debugInfo;
      byte[] arrayOfByte;
      String str;
      ArrayList<String> arrayList;
      ArrayList<byte[]> arrayList1;
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
            arrayOfByte = arrayList1.get(param1Int1);
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
          arrayOfByte.enforceInterface("android.hidl.base@1.0::IBase");
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
          str.enforceInterface("android.hidl.base@1.0::IBase");
          arrayList = interfaceChain();
          param1HwParcel2.writeStatus(0);
          param1HwParcel2.writeStringVector(arrayList);
          param1HwParcel2.send();
        case 1:
          break;
      } 
      arrayList.enforceInterface("android.hardware.gnss@1.0::IGnssNiCallback");
      IGnssNiCallback.GnssNiNotification gnssNiNotification = new IGnssNiCallback.GnssNiNotification();
      gnssNiNotification.readFromParcel((HwParcel)arrayList);
      niNotifyCb(gnssNiNotification);
      param1HwParcel2.writeStatus(0);
      param1HwParcel2.send();
    }
    
    public final void ping() {}
    
    public IHwInterface queryLocalInterface(String param1String) {
      return (IHwInterface)("android.hardware.gnss@1.0::IGnssNiCallback".equals(param1String) ? this : null);
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


/* Location:              /home/chun/Desktop/temp/!/android/hardware/gnss/V1_0/IGnssNiCallback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.companion;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.net.wifi.ScanResult;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;
import java.util.regex.Pattern;

public class BluetoothDeviceFilterUtils {
  private static final boolean DEBUG = false;
  
  private static final String LOG_TAG = "BluetoothDeviceFilterUtils";
  
  private static void debugLogMatchResult(boolean paramBoolean, BluetoothDevice paramBluetoothDevice, Object paramObject) {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getDeviceDisplayNameInternal(paramBluetoothDevice));
    if (paramBoolean) {
      str = " ~ ";
    } else {
      str = " !~ ";
    } 
    stringBuilder.append(str);
    stringBuilder.append(paramObject);
    Log.i("BluetoothDeviceFilterUtils", stringBuilder.toString());
  }
  
  private static void debugLogMatchResult(boolean paramBoolean, ScanResult paramScanResult, Object paramObject) {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getDeviceDisplayNameInternal(paramScanResult));
    if (paramBoolean) {
      str = " ~ ";
    } else {
      str = " !~ ";
    } 
    stringBuilder.append(str);
    stringBuilder.append(paramObject);
    Log.i("BluetoothDeviceFilterUtils", stringBuilder.toString());
  }
  
  public static String getDeviceDisplayNameInternal(BluetoothDevice paramBluetoothDevice) {
    return TextUtils.firstNotEmpty(paramBluetoothDevice.getAlias(), paramBluetoothDevice.getAddress());
  }
  
  public static String getDeviceDisplayNameInternal(ScanResult paramScanResult) {
    return TextUtils.firstNotEmpty(paramScanResult.SSID, paramScanResult.BSSID);
  }
  
  public static String getDeviceMacAddress(Parcelable paramParcelable) {
    if (paramParcelable instanceof BluetoothDevice)
      return ((BluetoothDevice)paramParcelable).getAddress(); 
    if (paramParcelable instanceof ScanResult)
      return ((ScanResult)paramParcelable).BSSID; 
    if (paramParcelable instanceof ScanResult)
      return getDeviceMacAddress((Parcelable)((ScanResult)paramParcelable).getDevice()); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown device type: ");
    stringBuilder.append(paramParcelable);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  static boolean matchesAddress(String paramString, BluetoothDevice paramBluetoothDevice) {
    return (paramString == null || (paramBluetoothDevice != null && paramString.equals(paramBluetoothDevice.getAddress())));
  }
  
  static boolean matchesName(Pattern paramPattern, BluetoothDevice paramBluetoothDevice) {
    boolean bool;
    if (paramPattern == null) {
      bool = true;
    } else if (paramBluetoothDevice == null) {
      bool = false;
    } else {
      String str = paramBluetoothDevice.getName();
      if (str != null && paramPattern.matcher(str).find()) {
        bool = true;
      } else {
        bool = false;
      } 
    } 
    return bool;
  }
  
  static boolean matchesName(Pattern paramPattern, ScanResult paramScanResult) {
    boolean bool;
    if (paramPattern == null) {
      bool = true;
    } else if (paramScanResult == null) {
      bool = false;
    } else {
      String str = paramScanResult.SSID;
      if (str != null && paramPattern.matcher(str).find()) {
        bool = true;
      } else {
        bool = false;
      } 
    } 
    return bool;
  }
  
  static boolean matchesServiceUuid(ParcelUuid paramParcelUuid1, ParcelUuid paramParcelUuid2, BluetoothDevice paramBluetoothDevice) {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual getUuids : ()[Landroid/os/ParcelUuid;
    //   4: astore_2
    //   5: aload_0
    //   6: ifnull -> 42
    //   9: aload_2
    //   10: ifnonnull -> 20
    //   13: invokestatic emptyList : ()Ljava/util/List;
    //   16: astore_2
    //   17: goto -> 25
    //   20: aload_2
    //   21: invokestatic asList : ([Ljava/lang/Object;)Ljava/util/List;
    //   24: astore_2
    //   25: aload_0
    //   26: aload_1
    //   27: aload_2
    //   28: invokestatic matchesServiceUuids : (Landroid/os/ParcelUuid;Landroid/os/ParcelUuid;Ljava/util/List;)Z
    //   31: ifeq -> 37
    //   34: goto -> 42
    //   37: iconst_0
    //   38: istore_3
    //   39: goto -> 44
    //   42: iconst_1
    //   43: istore_3
    //   44: iload_3
    //   45: ireturn
  }
  
  static boolean matchesServiceUuids(List<ParcelUuid> paramList1, List<ParcelUuid> paramList2, BluetoothDevice paramBluetoothDevice) {
    for (byte b = 0; b < paramList1.size(); b++) {
      if (!matchesServiceUuid(paramList1.get(b), paramList2.get(b), paramBluetoothDevice))
        return false; 
    } 
    return true;
  }
  
  static Pattern patternFromString(String paramString) {
    Pattern pattern;
    if (paramString == null) {
      paramString = null;
    } else {
      pattern = Pattern.compile(paramString);
    } 
    return pattern;
  }
  
  static String patternToString(Pattern paramPattern) {
    String str;
    if (paramPattern == null) {
      paramPattern = null;
    } else {
      str = paramPattern.pattern();
    } 
    return str;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/companion/BluetoothDeviceFilterUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app.admin;

import android.content.ComponentName;
import android.stats.devicepolicy.nano.StringList;
import com.android.framework.protobuf.nano.MessageNano;
import com.android.internal.util.FrameworkStatsLog;
import java.util.Arrays;
import java.util.Objects;

public class DevicePolicyEventLogger {
  private String mAdminPackageName;
  
  private boolean mBooleanValue;
  
  private final int mEventId;
  
  private int mIntValue;
  
  private String[] mStringArrayValue;
  
  private long mTimePeriodMs;
  
  private DevicePolicyEventLogger(int paramInt) {
    this.mEventId = paramInt;
  }
  
  public static DevicePolicyEventLogger createEvent(int paramInt) {
    return new DevicePolicyEventLogger(paramInt);
  }
  
  private static byte[] stringArrayValueToBytes(String[] paramArrayOfString) {
    if (paramArrayOfString == null)
      return null; 
    StringList stringList = new StringList();
    stringList.stringValue = paramArrayOfString;
    return MessageNano.toByteArray((MessageNano)stringList);
  }
  
  public String getAdminPackageName() {
    return this.mAdminPackageName;
  }
  
  public boolean getBoolean() {
    return this.mBooleanValue;
  }
  
  public int getEventId() {
    return this.mEventId;
  }
  
  public int getInt() {
    return this.mIntValue;
  }
  
  public String[] getStringArray() {
    String[] arrayOfString = this.mStringArrayValue;
    return (arrayOfString == null) ? null : Arrays.<String>copyOf(arrayOfString, arrayOfString.length);
  }
  
  public long getTimePeriod() {
    return this.mTimePeriodMs;
  }
  
  public DevicePolicyEventLogger setAdmin(ComponentName paramComponentName) {
    if (paramComponentName != null) {
      String str = paramComponentName.getPackageName();
    } else {
      paramComponentName = null;
    } 
    this.mAdminPackageName = (String)paramComponentName;
    return this;
  }
  
  public DevicePolicyEventLogger setAdmin(String paramString) {
    this.mAdminPackageName = paramString;
    return this;
  }
  
  public DevicePolicyEventLogger setBoolean(boolean paramBoolean) {
    this.mBooleanValue = paramBoolean;
    return this;
  }
  
  public DevicePolicyEventLogger setInt(int paramInt) {
    this.mIntValue = paramInt;
    return this;
  }
  
  public DevicePolicyEventLogger setStrings(String paramString1, String paramString2, String[] paramArrayOfString) {
    Objects.requireNonNull(paramArrayOfString, "values parameter cannot be null");
    String[] arrayOfString = new String[paramArrayOfString.length + 2];
    this.mStringArrayValue = arrayOfString;
    arrayOfString[0] = paramString1;
    arrayOfString[1] = paramString2;
    System.arraycopy(paramArrayOfString, 0, arrayOfString, 2, paramArrayOfString.length);
    return this;
  }
  
  public DevicePolicyEventLogger setStrings(String paramString, String[] paramArrayOfString) {
    Objects.requireNonNull(paramArrayOfString, "values parameter cannot be null");
    String[] arrayOfString = new String[paramArrayOfString.length + 1];
    this.mStringArrayValue = arrayOfString;
    arrayOfString[0] = paramString;
    System.arraycopy(paramArrayOfString, 0, arrayOfString, 1, paramArrayOfString.length);
    return this;
  }
  
  public DevicePolicyEventLogger setStrings(String... paramVarArgs) {
    this.mStringArrayValue = paramVarArgs;
    return this;
  }
  
  public DevicePolicyEventLogger setTimePeriod(long paramLong) {
    this.mTimePeriodMs = paramLong;
    return this;
  }
  
  public void write() {
    byte[] arrayOfByte = stringArrayValueToBytes(this.mStringArrayValue);
    FrameworkStatsLog.write(103, this.mEventId, this.mAdminPackageName, this.mIntValue, this.mBooleanValue, this.mTimePeriodMs, arrayOfByte);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/admin/DevicePolicyEventLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Printer;
import com.android.internal.util.FastPrintWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ApplicationErrorReport implements Parcelable {
  public static final Parcelable.Creator<ApplicationErrorReport> CREATOR = new Parcelable.Creator<ApplicationErrorReport>() {
      public ApplicationErrorReport createFromParcel(Parcel param1Parcel) {
        return new ApplicationErrorReport(param1Parcel);
      }
      
      public ApplicationErrorReport[] newArray(int param1Int) {
        return new ApplicationErrorReport[param1Int];
      }
    };
  
  static final String DEFAULT_ERROR_RECEIVER_PROPERTY = "ro.error.receiver.default";
  
  static final String SYSTEM_APPS_ERROR_RECEIVER_PROPERTY = "ro.error.receiver.system.apps";
  
  public static final int TYPE_ANR = 2;
  
  public static final int TYPE_BATTERY = 3;
  
  public static final int TYPE_CRASH = 1;
  
  public static final int TYPE_NONE = 0;
  
  public static final int TYPE_RUNNING_SERVICE = 5;
  
  public AnrInfo anrInfo;
  
  public BatteryInfo batteryInfo;
  
  public CrashInfo crashInfo;
  
  public String installerPackageName;
  
  public String packageName;
  
  public String processName;
  
  public RunningServiceInfo runningServiceInfo;
  
  public boolean systemApp;
  
  public long time;
  
  public int type;
  
  public ApplicationErrorReport() {}
  
  ApplicationErrorReport(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public static ComponentName getErrorReportReceiver(Context paramContext, String paramString, int paramInt) {
    String str;
    if (Settings.Global.getInt(paramContext.getContentResolver(), "send_action_app_error", 0) == 0)
      return null; 
    PackageManager packageManager = paramContext.getPackageManager();
    paramContext = null;
    try {
      String str1 = packageManager.getInstallerPackageName(paramString);
      str = str1;
    } catch (IllegalArgumentException illegalArgumentException) {}
    if (str != null) {
      ComponentName componentName = getErrorReportReceiver(packageManager, paramString, str);
      if (componentName != null)
        return componentName; 
    } 
    if ((paramInt & 0x1) != 0) {
      ComponentName componentName = getErrorReportReceiver(packageManager, paramString, SystemProperties.get("ro.error.receiver.system.apps"));
      if (componentName != null)
        return componentName; 
    } 
    return getErrorReportReceiver(packageManager, paramString, SystemProperties.get("ro.error.receiver.default"));
  }
  
  static ComponentName getErrorReportReceiver(PackageManager paramPackageManager, String paramString1, String paramString2) {
    if (paramString2 == null || paramString2.length() == 0)
      return null; 
    if (paramString2.equals(paramString1))
      return null; 
    Intent intent = new Intent("android.intent.action.APP_ERROR");
    intent.setPackage(paramString2);
    ResolveInfo resolveInfo = paramPackageManager.resolveActivity(intent, 0);
    return (resolveInfo == null || resolveInfo.activityInfo == null) ? null : new ComponentName(paramString2, resolveInfo.activityInfo.name);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("type: ");
    stringBuilder.append(this.type);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("packageName: ");
    stringBuilder.append(this.packageName);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("installerPackageName: ");
    stringBuilder.append(this.installerPackageName);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("processName: ");
    stringBuilder.append(this.processName);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("time: ");
    stringBuilder.append(this.time);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("systemApp: ");
    stringBuilder.append(this.systemApp);
    paramPrinter.println(stringBuilder.toString());
    int i = this.type;
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          if (i == 5)
            this.runningServiceInfo.dump(paramPrinter, paramString); 
        } else {
          this.batteryInfo.dump(paramPrinter, paramString);
        } 
      } else {
        this.anrInfo.dump(paramPrinter, paramString);
      } 
    } else {
      this.crashInfo.dump(paramPrinter, paramString);
    } 
  }
  
  public void readFromParcel(Parcel paramParcel) {
    boolean bool2;
    this.type = paramParcel.readInt();
    this.packageName = paramParcel.readString();
    this.installerPackageName = paramParcel.readString();
    this.processName = paramParcel.readString();
    this.time = paramParcel.readLong();
    int i = paramParcel.readInt();
    boolean bool1 = false;
    if (i == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.systemApp = bool2;
    if (paramParcel.readInt() == 1)
      bool1 = true; 
    i = this.type;
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          if (i == 5) {
            this.batteryInfo = null;
            this.anrInfo = null;
            this.crashInfo = null;
            this.runningServiceInfo = new RunningServiceInfo(paramParcel);
          } 
        } else {
          this.batteryInfo = new BatteryInfo(paramParcel);
          this.anrInfo = null;
          this.crashInfo = null;
          this.runningServiceInfo = null;
        } 
      } else {
        this.anrInfo = new AnrInfo(paramParcel);
        this.crashInfo = null;
        this.batteryInfo = null;
        this.runningServiceInfo = null;
      } 
    } else {
      if (bool1) {
        CrashInfo crashInfo = new CrashInfo(paramParcel);
      } else {
        paramParcel = null;
      } 
      this.crashInfo = (CrashInfo)paramParcel;
      this.anrInfo = null;
      this.batteryInfo = null;
      this.runningServiceInfo = null;
    } 
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.type);
    paramParcel.writeString(this.packageName);
    paramParcel.writeString(this.installerPackageName);
    paramParcel.writeString(this.processName);
    paramParcel.writeLong(this.time);
    paramParcel.writeInt(this.systemApp);
    if (this.crashInfo != null) {
      i = 1;
    } else {
      i = 0;
    } 
    paramParcel.writeInt(i);
    int i = this.type;
    if (i != 1) {
      if (i != 2) {
        if (i != 3) {
          if (i == 5)
            this.runningServiceInfo.writeToParcel(paramParcel, paramInt); 
        } else {
          this.batteryInfo.writeToParcel(paramParcel, paramInt);
        } 
      } else {
        this.anrInfo.writeToParcel(paramParcel, paramInt);
      } 
    } else {
      CrashInfo crashInfo = this.crashInfo;
      if (crashInfo != null)
        crashInfo.writeToParcel(paramParcel, paramInt); 
    } 
  }
  
  public static class AnrInfo {
    public String activity;
    
    public String cause;
    
    public String info;
    
    public AnrInfo() {}
    
    public AnrInfo(Parcel param1Parcel) {
      this.activity = param1Parcel.readString();
      this.cause = param1Parcel.readString();
      this.info = param1Parcel.readString();
    }
    
    public void dump(Printer param1Printer, String param1String) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("activity: ");
      stringBuilder.append(this.activity);
      param1Printer.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("cause: ");
      stringBuilder.append(this.cause);
      param1Printer.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("info: ");
      stringBuilder.append(this.info);
      param1Printer.println(stringBuilder.toString());
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeString(this.activity);
      param1Parcel.writeString(this.cause);
      param1Parcel.writeString(this.info);
    }
  }
  
  public static class BatteryInfo {
    public String checkinDetails;
    
    public long durationMicros;
    
    public String usageDetails;
    
    public int usagePercent;
    
    public BatteryInfo() {}
    
    public BatteryInfo(Parcel param1Parcel) {
      this.usagePercent = param1Parcel.readInt();
      this.durationMicros = param1Parcel.readLong();
      this.usageDetails = param1Parcel.readString();
      this.checkinDetails = param1Parcel.readString();
    }
    
    public void dump(Printer param1Printer, String param1String) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("usagePercent: ");
      stringBuilder.append(this.usagePercent);
      param1Printer.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("durationMicros: ");
      stringBuilder.append(this.durationMicros);
      param1Printer.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("usageDetails: ");
      stringBuilder.append(this.usageDetails);
      param1Printer.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("checkinDetails: ");
      stringBuilder.append(this.checkinDetails);
      param1Printer.println(stringBuilder.toString());
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.usagePercent);
      param1Parcel.writeLong(this.durationMicros);
      param1Parcel.writeString(this.usageDetails);
      param1Parcel.writeString(this.checkinDetails);
    }
  }
  
  public static class CrashInfo {
    public String crashTag;
    
    public String exceptionClassName;
    
    public String exceptionMessage;
    
    public String stackTrace;
    
    public String throwClassName;
    
    public String throwFileName;
    
    public int throwLineNumber;
    
    public String throwMethodName;
    
    public CrashInfo() {}
    
    public CrashInfo(Parcel param1Parcel) {
      this.exceptionClassName = param1Parcel.readString();
      this.exceptionMessage = param1Parcel.readString();
      this.throwFileName = param1Parcel.readString();
      this.throwClassName = param1Parcel.readString();
      this.throwMethodName = param1Parcel.readString();
      this.throwLineNumber = param1Parcel.readInt();
      this.stackTrace = param1Parcel.readString();
      this.crashTag = param1Parcel.readString();
    }
    
    public CrashInfo(Throwable param1Throwable) {
      StringWriter stringWriter = new StringWriter();
      FastPrintWriter fastPrintWriter = new FastPrintWriter(stringWriter, false, 256);
      param1Throwable.printStackTrace((PrintWriter)fastPrintWriter);
      fastPrintWriter.flush();
      this.stackTrace = sanitizeString(stringWriter.toString());
      this.exceptionMessage = param1Throwable.getMessage();
      Throwable throwable = param1Throwable;
      while (param1Throwable.getCause() != null) {
        param1Throwable = param1Throwable.getCause();
        Throwable throwable1 = throwable;
        if (param1Throwable.getStackTrace() != null) {
          throwable1 = throwable;
          if ((param1Throwable.getStackTrace()).length > 0)
            throwable1 = param1Throwable; 
        } 
        String str = param1Throwable.getMessage();
        if (str != null && str.length() > 0)
          this.exceptionMessage = str; 
        throwable = throwable1;
      } 
      this.exceptionClassName = throwable.getClass().getName();
      if ((throwable.getStackTrace()).length > 0) {
        StackTraceElement stackTraceElement = throwable.getStackTrace()[0];
        this.throwFileName = stackTraceElement.getFileName();
        this.throwClassName = stackTraceElement.getClassName();
        this.throwMethodName = stackTraceElement.getMethodName();
        this.throwLineNumber = stackTraceElement.getLineNumber();
      } else {
        this.throwFileName = "unknown";
        this.throwClassName = "unknown";
        this.throwMethodName = "unknown";
        this.throwLineNumber = 0;
      } 
      this.exceptionMessage = sanitizeString(this.exceptionMessage);
    }
    
    private String sanitizeString(String param1String) {
      int i = 10240 + 10240;
      if (param1String != null && param1String.length() > i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n[TRUNCATED ");
        stringBuilder.append(param1String.length() - i);
        stringBuilder.append(" CHARS]\n");
        String str = stringBuilder.toString();
        stringBuilder = new StringBuilder(str.length() + i);
        stringBuilder.append(param1String.substring(0, 10240));
        stringBuilder.append(str);
        stringBuilder.append(param1String.substring(param1String.length() - 10240));
        return stringBuilder.toString();
      } 
      return param1String;
    }
    
    public void appendStackTrace(String param1String) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(this.stackTrace);
      stringBuilder.append(param1String);
      this.stackTrace = sanitizeString(stringBuilder.toString());
    }
    
    public void dump(Printer param1Printer, String param1String) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("exceptionClassName: ");
      stringBuilder.append(this.exceptionClassName);
      param1Printer.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("exceptionMessage: ");
      stringBuilder.append(this.exceptionMessage);
      param1Printer.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("throwFileName: ");
      stringBuilder.append(this.throwFileName);
      param1Printer.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("throwClassName: ");
      stringBuilder.append(this.throwClassName);
      param1Printer.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("throwMethodName: ");
      stringBuilder.append(this.throwMethodName);
      param1Printer.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("throwLineNumber: ");
      stringBuilder.append(this.throwLineNumber);
      param1Printer.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("stackTrace: ");
      stringBuilder.append(this.stackTrace);
      param1Printer.println(stringBuilder.toString());
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.dataPosition();
      param1Parcel.writeString(this.exceptionClassName);
      param1Parcel.writeString(this.exceptionMessage);
      param1Parcel.writeString(this.throwFileName);
      param1Parcel.writeString(this.throwClassName);
      param1Parcel.writeString(this.throwMethodName);
      param1Parcel.writeInt(this.throwLineNumber);
      param1Parcel.writeString(this.stackTrace);
      param1Parcel.writeString(this.crashTag);
      param1Parcel.dataPosition();
    }
  }
  
  public static class ParcelableCrashInfo extends CrashInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableCrashInfo> CREATOR = new Parcelable.Creator<ParcelableCrashInfo>() {
        public ApplicationErrorReport.ParcelableCrashInfo createFromParcel(Parcel param2Parcel) {
          return new ApplicationErrorReport.ParcelableCrashInfo(param2Parcel);
        }
        
        public ApplicationErrorReport.ParcelableCrashInfo[] newArray(int param2Int) {
          return new ApplicationErrorReport.ParcelableCrashInfo[param2Int];
        }
      };
    
    public ParcelableCrashInfo() {}
    
    public ParcelableCrashInfo(Parcel param1Parcel) {
      super(param1Parcel);
    }
    
    public ParcelableCrashInfo(Throwable param1Throwable) {
      super(param1Throwable);
    }
    
    public int describeContents() {
      return 0;
    }
  }
  
  class null implements Parcelable.Creator<ParcelableCrashInfo> {
    public ApplicationErrorReport.ParcelableCrashInfo createFromParcel(Parcel param1Parcel) {
      return new ApplicationErrorReport.ParcelableCrashInfo(param1Parcel);
    }
    
    public ApplicationErrorReport.ParcelableCrashInfo[] newArray(int param1Int) {
      return new ApplicationErrorReport.ParcelableCrashInfo[param1Int];
    }
  }
  
  public static class RunningServiceInfo {
    public long durationMillis;
    
    public String serviceDetails;
    
    public RunningServiceInfo() {}
    
    public RunningServiceInfo(Parcel param1Parcel) {
      this.durationMillis = param1Parcel.readLong();
      this.serviceDetails = param1Parcel.readString();
    }
    
    public void dump(Printer param1Printer, String param1String) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("durationMillis: ");
      stringBuilder.append(this.durationMillis);
      param1Printer.println(stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(param1String);
      stringBuilder.append("serviceDetails: ");
      stringBuilder.append(this.serviceDetails);
      param1Printer.println(stringBuilder.toString());
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeLong(this.durationMillis);
      param1Parcel.writeString(this.serviceDetails);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationErrorReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
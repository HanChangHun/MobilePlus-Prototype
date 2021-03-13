package android.app;

import android.os.Parcel;
import android.util.Printer;
import com.android.internal.util.FastPrintWriter;
import java.io.PrintWriter;
import java.io.StringWriter;

public class CrashInfo {
  public String crashTag;
  
  public String exceptionClassName;
  
  public String exceptionMessage;
  
  public String stackTrace;
  
  public String throwClassName;
  
  public String throwFileName;
  
  public int throwLineNumber;
  
  public String throwMethodName;
  
  public CrashInfo() {}
  
  public CrashInfo(Parcel paramParcel) {
    this.exceptionClassName = paramParcel.readString();
    this.exceptionMessage = paramParcel.readString();
    this.throwFileName = paramParcel.readString();
    this.throwClassName = paramParcel.readString();
    this.throwMethodName = paramParcel.readString();
    this.throwLineNumber = paramParcel.readInt();
    this.stackTrace = paramParcel.readString();
    this.crashTag = paramParcel.readString();
  }
  
  public CrashInfo(Throwable paramThrowable) {
    StringWriter stringWriter = new StringWriter();
    FastPrintWriter fastPrintWriter = new FastPrintWriter(stringWriter, false, 256);
    paramThrowable.printStackTrace((PrintWriter)fastPrintWriter);
    fastPrintWriter.flush();
    this.stackTrace = sanitizeString(stringWriter.toString());
    this.exceptionMessage = paramThrowable.getMessage();
    Throwable throwable = paramThrowable;
    while (paramThrowable.getCause() != null) {
      paramThrowable = paramThrowable.getCause();
      Throwable throwable1 = throwable;
      if (paramThrowable.getStackTrace() != null) {
        throwable1 = throwable;
        if ((paramThrowable.getStackTrace()).length > 0)
          throwable1 = paramThrowable; 
      } 
      String str = paramThrowable.getMessage();
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
  
  private String sanitizeString(String paramString) {
    int i = 10240 + 10240;
    if (paramString != null && paramString.length() > i) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("\n[TRUNCATED ");
      stringBuilder.append(paramString.length() - i);
      stringBuilder.append(" CHARS]\n");
      String str = stringBuilder.toString();
      stringBuilder = new StringBuilder(str.length() + i);
      stringBuilder.append(paramString.substring(0, 10240));
      stringBuilder.append(str);
      stringBuilder.append(paramString.substring(paramString.length() - 10240));
      return stringBuilder.toString();
    } 
    return paramString;
  }
  
  public void appendStackTrace(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(this.stackTrace);
    stringBuilder.append(paramString);
    this.stackTrace = sanitizeString(stringBuilder.toString());
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("exceptionClassName: ");
    stringBuilder.append(this.exceptionClassName);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("exceptionMessage: ");
    stringBuilder.append(this.exceptionMessage);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("throwFileName: ");
    stringBuilder.append(this.throwFileName);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("throwClassName: ");
    stringBuilder.append(this.throwClassName);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("throwMethodName: ");
    stringBuilder.append(this.throwMethodName);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("throwLineNumber: ");
    stringBuilder.append(this.throwLineNumber);
    paramPrinter.println(stringBuilder.toString());
    stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("stackTrace: ");
    stringBuilder.append(this.stackTrace);
    paramPrinter.println(stringBuilder.toString());
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.dataPosition();
    paramParcel.writeString(this.exceptionClassName);
    paramParcel.writeString(this.exceptionMessage);
    paramParcel.writeString(this.throwFileName);
    paramParcel.writeString(this.throwClassName);
    paramParcel.writeString(this.throwMethodName);
    paramParcel.writeInt(this.throwLineNumber);
    paramParcel.writeString(this.stackTrace);
    paramParcel.writeString(this.crashTag);
    paramParcel.dataPosition();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ApplicationErrorReport$CrashInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
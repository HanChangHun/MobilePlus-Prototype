package android.content;

import android.app.ActivityThread;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import android.util.Log;
import android.view.contentcapture.ContentCaptureManager;
import java.io.PrintWriter;

public final class ContentCaptureOptions implements Parcelable {
  public static final Parcelable.Creator<ContentCaptureOptions> CREATOR;
  
  private static final String TAG = ContentCaptureOptions.class.getSimpleName();
  
  public final int idleFlushingFrequencyMs;
  
  public final boolean lite;
  
  public final int logHistorySize;
  
  public final int loggingLevel;
  
  public final int maxBufferSize;
  
  public final int textChangeFlushingFrequencyMs;
  
  public final ArraySet<ComponentName> whitelistedComponents;
  
  static {
    CREATOR = new Parcelable.Creator<ContentCaptureOptions>() {
        public ContentCaptureOptions createFromParcel(Parcel param1Parcel) {
          boolean bool = param1Parcel.readBoolean();
          int i = param1Parcel.readInt();
          return bool ? new ContentCaptureOptions(i) : new ContentCaptureOptions(i, param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readInt(), param1Parcel.readArraySet(null));
        }
        
        public ContentCaptureOptions[] newArray(int param1Int) {
          return new ContentCaptureOptions[param1Int];
        }
      };
  }
  
  public ContentCaptureOptions(int paramInt) {
    this(true, paramInt, 0, 0, 0, 0, null);
  }
  
  public ContentCaptureOptions(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, ArraySet<ComponentName> paramArraySet) {
    this(false, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramArraySet);
  }
  
  public ContentCaptureOptions(ArraySet<ComponentName> paramArraySet) {
    this(2, 500, 5000, 1000, 10, paramArraySet);
  }
  
  private ContentCaptureOptions(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, ArraySet<ComponentName> paramArraySet) {
    this.lite = paramBoolean;
    this.loggingLevel = paramInt1;
    this.maxBufferSize = paramInt2;
    this.idleFlushingFrequencyMs = paramInt3;
    this.textChangeFlushingFrequencyMs = paramInt4;
    this.logHistorySize = paramInt5;
    this.whitelistedComponents = paramArraySet;
  }
  
  public static ContentCaptureOptions forWhitelistingItself() {
    ActivityThread activityThread = ActivityThread.currentActivityThread();
    if (activityThread != null) {
      String str1 = activityThread.getApplication().getPackageName();
      if ("android.contentcaptureservice.cts".equals(str1)) {
        ContentCaptureOptions contentCaptureOptions = new ContentCaptureOptions(null);
        String str = TAG;
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("forWhitelistingItself(");
        stringBuilder1.append(str1);
        stringBuilder1.append("): ");
        stringBuilder1.append(contentCaptureOptions);
        Log.i(str, stringBuilder1.toString());
        return contentCaptureOptions;
      } 
      String str2 = TAG;
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("forWhitelistingItself(): called by ");
      stringBuilder.append(str1);
      Log.e(str2, stringBuilder.toString());
      throw new SecurityException("Thou shall not pass!");
    } 
    throw new IllegalStateException("No ActivityThread");
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dumpShort(PrintWriter paramPrintWriter) {
    paramPrintWriter.print("logLvl=");
    paramPrintWriter.print(this.loggingLevel);
    if (this.lite) {
      paramPrintWriter.print(", lite");
      return;
    } 
    paramPrintWriter.print(", bufferSize=");
    paramPrintWriter.print(this.maxBufferSize);
    paramPrintWriter.print(", idle=");
    paramPrintWriter.print(this.idleFlushingFrequencyMs);
    paramPrintWriter.print(", textIdle=");
    paramPrintWriter.print(this.textChangeFlushingFrequencyMs);
    paramPrintWriter.print(", logSize=");
    paramPrintWriter.print(this.logHistorySize);
    if (this.whitelistedComponents != null) {
      paramPrintWriter.print(", whitelisted=");
      paramPrintWriter.print(this.whitelistedComponents);
    } 
  }
  
  public boolean isWhitelisted(Context paramContext) {
    StringBuilder stringBuilder;
    if (this.whitelistedComponents == null)
      return true; 
    ContentCaptureManager.ContentCaptureClient contentCaptureClient = paramContext.getContentCaptureClient();
    if (contentCaptureClient == null) {
      String str = TAG;
      stringBuilder = new StringBuilder();
      stringBuilder.append("isWhitelisted(): no ContentCaptureClient on ");
      stringBuilder.append(paramContext);
      Log.w(str, stringBuilder.toString());
      return false;
    } 
    return this.whitelistedComponents.contains(stringBuilder.contentCaptureClientGetComponentName());
  }
  
  public String toString() {
    if (this.lite) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("ContentCaptureOptions [loggingLevel=");
      stringBuilder1.append(this.loggingLevel);
      stringBuilder1.append(" (lite)]");
      return stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder("ContentCaptureOptions [");
    stringBuilder.append("loggingLevel=");
    stringBuilder.append(this.loggingLevel);
    stringBuilder.append(", maxBufferSize=");
    stringBuilder.append(this.maxBufferSize);
    stringBuilder.append(", idleFlushingFrequencyMs=");
    stringBuilder.append(this.idleFlushingFrequencyMs);
    stringBuilder.append(", textChangeFlushingFrequencyMs=");
    stringBuilder.append(this.textChangeFlushingFrequencyMs);
    stringBuilder.append(", logHistorySize=");
    stringBuilder.append(this.logHistorySize);
    if (this.whitelistedComponents != null) {
      stringBuilder.append(", whitelisted=");
      stringBuilder.append(this.whitelistedComponents);
    } 
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeBoolean(this.lite);
    paramParcel.writeInt(this.loggingLevel);
    if (this.lite)
      return; 
    paramParcel.writeInt(this.maxBufferSize);
    paramParcel.writeInt(this.idleFlushingFrequencyMs);
    paramParcel.writeInt(this.textChangeFlushingFrequencyMs);
    paramParcel.writeInt(this.logHistorySize);
    paramParcel.writeArraySet(this.whitelistedComponents);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentCaptureOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
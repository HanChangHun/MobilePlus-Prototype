package android.content.pm;

import android.os.BaseBundle;
import android.os.PersistableBundle;
import android.util.Slog;
import java.io.IOException;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public final class SuspendParams {
  private static final String TAG_APP_EXTRAS = "app-extras";
  
  private static final String TAG_DIALOG_INFO = "dialog-info";
  
  private static final String TAG_LAUNCHER_EXTRAS = "launcher-extras";
  
  public PersistableBundle appExtras;
  
  public SuspendDialogInfo dialogInfo;
  
  public PersistableBundle launcherExtras;
  
  public static SuspendParams getInstanceOrNull(SuspendDialogInfo paramSuspendDialogInfo, PersistableBundle paramPersistableBundle1, PersistableBundle paramPersistableBundle2) {
    if (paramSuspendDialogInfo == null && paramPersistableBundle1 == null && paramPersistableBundle2 == null)
      return null; 
    SuspendParams suspendParams = new SuspendParams();
    suspendParams.dialogInfo = paramSuspendDialogInfo;
    suspendParams.appExtras = paramPersistableBundle1;
    suspendParams.launcherExtras = paramPersistableBundle2;
    return suspendParams;
  }
  
  public static SuspendParams restoreFromXml(XmlPullParser paramXmlPullParser) throws IOException {
    SuspendDialogInfo suspendDialogInfo = null;
    PersistableBundle persistableBundle1 = null;
    PersistableBundle persistableBundle2 = null;
    int i = paramXmlPullParser.getDepth();
    try {
      while (true) {
        int j = paramXmlPullParser.next();
        if (j != 1 && (j != 3 || paramXmlPullParser.getDepth() > i)) {
          if (j == 3 || j == 4)
            continue; 
          String str = paramXmlPullParser.getName();
          j = -1;
          int k = str.hashCode();
          if (k != -538220657) {
            if (k != -22768109) {
              if (k == 1627485488 && str.equals("launcher-extras"))
                j = 2; 
            } else if (str.equals("dialog-info")) {
              j = 0;
            } 
          } else if (str.equals("app-extras")) {
            j = 1;
          } 
          if (j != 0) {
            if (j != 1) {
              if (j != 2) {
                StringBuilder stringBuilder = new StringBuilder();
                this();
                stringBuilder.append("Unknown tag ");
                stringBuilder.append(paramXmlPullParser.getName());
                stringBuilder.append(" in SuspendParams. Ignoring");
                Slog.w("PackageUserState", stringBuilder.toString());
                continue;
              } 
              PersistableBundle persistableBundle3 = PersistableBundle.restoreFromXml(paramXmlPullParser);
              persistableBundle2 = persistableBundle3;
              continue;
            } 
            PersistableBundle persistableBundle = PersistableBundle.restoreFromXml(paramXmlPullParser);
            persistableBundle1 = persistableBundle;
            continue;
          } 
          SuspendDialogInfo suspendDialogInfo1 = SuspendDialogInfo.restoreFromXml(paramXmlPullParser);
          suspendDialogInfo = suspendDialogInfo1;
          continue;
        } 
        break;
      } 
    } catch (XmlPullParserException xmlPullParserException) {
      Slog.e("PackageUserState", "Exception while trying to parse SuspendParams, some fields may default", (Throwable)xmlPullParserException);
    } 
    return getInstanceOrNull(suspendDialogInfo, persistableBundle1, persistableBundle2);
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof SuspendParams))
      return false; 
    paramObject = paramObject;
    return !Objects.equals(this.dialogInfo, ((SuspendParams)paramObject).dialogInfo) ? false : (!BaseBundle.kindofEquals((BaseBundle)this.appExtras, (BaseBundle)((SuspendParams)paramObject).appExtras) ? false : (!!BaseBundle.kindofEquals((BaseBundle)this.launcherExtras, (BaseBundle)((SuspendParams)paramObject).launcherExtras)));
  }
  
  public int hashCode() {
    byte b;
    int i = Objects.hashCode(this.dialogInfo);
    PersistableBundle persistableBundle = this.appExtras;
    int j = 0;
    if (persistableBundle != null) {
      b = persistableBundle.size();
    } else {
      b = 0;
    } 
    persistableBundle = this.launcherExtras;
    if (persistableBundle != null)
      j = persistableBundle.size(); 
    return (i * 31 + b) * 31 + j;
  }
  
  public void saveToXml(XmlSerializer paramXmlSerializer) throws IOException {
    if (this.dialogInfo != null) {
      paramXmlSerializer.startTag(null, "dialog-info");
      this.dialogInfo.saveToXml(paramXmlSerializer);
      paramXmlSerializer.endTag(null, "dialog-info");
    } 
    if (this.appExtras != null) {
      paramXmlSerializer.startTag(null, "app-extras");
      try {
        this.appExtras.saveToXml(paramXmlSerializer);
      } catch (XmlPullParserException xmlPullParserException) {
        Slog.e("PackageUserState", "Exception while trying to write appExtras. Will be lost on reboot", (Throwable)xmlPullParserException);
      } 
      paramXmlSerializer.endTag(null, "app-extras");
    } 
    if (this.launcherExtras != null) {
      paramXmlSerializer.startTag(null, "launcher-extras");
      try {
        this.launcherExtras.saveToXml(paramXmlSerializer);
      } catch (XmlPullParserException xmlPullParserException) {
        Slog.e("PackageUserState", "Exception while trying to write launcherExtras. Will be lost on reboot", (Throwable)xmlPullParserException);
      } 
      paramXmlSerializer.endTag(null, "launcher-extras");
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageUserState$SuspendParams.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
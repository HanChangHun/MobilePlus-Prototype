package android.content.pm;

import android.content.ComponentName;
import android.content.pm.parsing.component.ParsedMainComponent;
import android.os.BaseBundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Pair;
import android.util.Slog;
import com.android.internal.util.ArrayUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class PackageUserState {
  private static final boolean DEBUG = false;
  
  private static final String LOG_TAG = "PackageUserState";
  
  public int appLinkGeneration;
  
  private String[] cachedOverlayPaths;
  
  public int categoryHint = -1;
  
  public long ceDataInode;
  
  private ArrayMap<ComponentName, Pair<String, Integer>> componentLabelIconOverrideMap;
  
  public ArraySet<String> disabledComponents;
  
  public int distractionFlags;
  
  public int domainVerificationStatus;
  
  public int enabled;
  
  public ArraySet<String> enabledComponents;
  
  public String harmfulAppWarning;
  
  public boolean hidden;
  
  public int installReason;
  
  public boolean installed;
  
  public boolean instantApp;
  
  public String lastDisableAppCaller;
  
  public boolean notLaunched;
  
  private String[] overlayPaths;
  
  private ArrayMap<String, String[]> sharedLibraryOverlayPaths;
  
  public boolean stopped;
  
  public ArrayMap<String, SuspendParams> suspendParams;
  
  public boolean suspended;
  
  public int uninstallReason;
  
  public boolean virtualPreload;
  
  public PackageUserState() {
    this.installed = true;
    this.hidden = false;
    this.suspended = false;
    this.enabled = 0;
    this.domainVerificationStatus = 0;
    this.installReason = 0;
    this.uninstallReason = 0;
  }
  
  public PackageUserState(PackageUserState paramPackageUserState) {
    this.ceDataInode = paramPackageUserState.ceDataInode;
    this.installed = paramPackageUserState.installed;
    this.stopped = paramPackageUserState.stopped;
    this.notLaunched = paramPackageUserState.notLaunched;
    this.hidden = paramPackageUserState.hidden;
    this.distractionFlags = paramPackageUserState.distractionFlags;
    this.suspended = paramPackageUserState.suspended;
    this.suspendParams = new ArrayMap(paramPackageUserState.suspendParams);
    this.instantApp = paramPackageUserState.instantApp;
    this.virtualPreload = paramPackageUserState.virtualPreload;
    this.enabled = paramPackageUserState.enabled;
    this.lastDisableAppCaller = paramPackageUserState.lastDisableAppCaller;
    this.domainVerificationStatus = paramPackageUserState.domainVerificationStatus;
    this.appLinkGeneration = paramPackageUserState.appLinkGeneration;
    this.categoryHint = paramPackageUserState.categoryHint;
    this.installReason = paramPackageUserState.installReason;
    this.uninstallReason = paramPackageUserState.uninstallReason;
    this.disabledComponents = ArrayUtils.cloneOrNull(paramPackageUserState.disabledComponents);
    this.enabledComponents = ArrayUtils.cloneOrNull(paramPackageUserState.enabledComponents);
    String[] arrayOfString = paramPackageUserState.overlayPaths;
    if (arrayOfString == null) {
      arrayOfString = null;
    } else {
      arrayOfString = Arrays.<String>copyOf(arrayOfString, arrayOfString.length);
    } 
    this.overlayPaths = arrayOfString;
    if (paramPackageUserState.sharedLibraryOverlayPaths != null)
      this.sharedLibraryOverlayPaths = new ArrayMap(paramPackageUserState.sharedLibraryOverlayPaths); 
    this.harmfulAppWarning = paramPackageUserState.harmfulAppWarning;
    if (paramPackageUserState.componentLabelIconOverrideMap != null)
      this.componentLabelIconOverrideMap = new ArrayMap(paramPackageUserState.componentLabelIconOverrideMap); 
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof PackageUserState))
      return false; 
    paramObject = paramObject;
    if (this.ceDataInode != ((PackageUserState)paramObject).ceDataInode)
      return false; 
    if (this.installed != ((PackageUserState)paramObject).installed)
      return false; 
    if (this.stopped != ((PackageUserState)paramObject).stopped)
      return false; 
    if (this.notLaunched != ((PackageUserState)paramObject).notLaunched)
      return false; 
    if (this.hidden != ((PackageUserState)paramObject).hidden)
      return false; 
    if (this.distractionFlags != ((PackageUserState)paramObject).distractionFlags)
      return false; 
    boolean bool = this.suspended;
    if (bool != ((PackageUserState)paramObject).suspended)
      return false; 
    if (bool && !Objects.equals(this.suspendParams, ((PackageUserState)paramObject).suspendParams))
      return false; 
    if (this.instantApp != ((PackageUserState)paramObject).instantApp)
      return false; 
    if (this.virtualPreload != ((PackageUserState)paramObject).virtualPreload)
      return false; 
    if (this.enabled != ((PackageUserState)paramObject).enabled)
      return false; 
    if (this.lastDisableAppCaller != null || ((PackageUserState)paramObject).lastDisableAppCaller == null) {
      String str = this.lastDisableAppCaller;
      if (str == null || str.equals(((PackageUserState)paramObject).lastDisableAppCaller)) {
        if (this.domainVerificationStatus != ((PackageUserState)paramObject).domainVerificationStatus)
          return false; 
        if (this.appLinkGeneration != ((PackageUserState)paramObject).appLinkGeneration)
          return false; 
        if (this.categoryHint != ((PackageUserState)paramObject).categoryHint)
          return false; 
        if (this.installReason != ((PackageUserState)paramObject).installReason)
          return false; 
        if (this.uninstallReason != ((PackageUserState)paramObject).uninstallReason)
          return false; 
        if ((this.disabledComponents == null && ((PackageUserState)paramObject).disabledComponents != null) || (this.disabledComponents != null && ((PackageUserState)paramObject).disabledComponents == null))
          return false; 
        ArraySet<String> arraySet = this.disabledComponents;
        if (arraySet != null) {
          if (arraySet.size() != ((PackageUserState)paramObject).disabledComponents.size())
            return false; 
          for (int i = this.disabledComponents.size() - 1; i >= 0; i--) {
            if (!((PackageUserState)paramObject).disabledComponents.contains(this.disabledComponents.valueAt(i)))
              return false; 
          } 
        } 
        if ((this.enabledComponents == null && ((PackageUserState)paramObject).enabledComponents != null) || (this.enabledComponents != null && ((PackageUserState)paramObject).enabledComponents == null))
          return false; 
        arraySet = this.enabledComponents;
        if (arraySet != null) {
          if (arraySet.size() != ((PackageUserState)paramObject).enabledComponents.size())
            return false; 
          for (int i = this.enabledComponents.size() - 1; i >= 0; i--) {
            if (!((PackageUserState)paramObject).enabledComponents.contains(this.enabledComponents.valueAt(i)))
              return false; 
          } 
        } 
        if (this.harmfulAppWarning != null || ((PackageUserState)paramObject).harmfulAppWarning == null) {
          String str1 = this.harmfulAppWarning;
          if (str1 == null || str1.equals(((PackageUserState)paramObject).harmfulAppWarning))
            return true; 
        } 
        return false;
      } 
    } 
    return false;
  }
  
  public String[] getAllOverlayPaths() {
    if (this.overlayPaths == null && this.sharedLibraryOverlayPaths == null)
      return null; 
    String[] arrayOfString2 = this.cachedOverlayPaths;
    if (arrayOfString2 != null)
      return arrayOfString2; 
    LinkedHashSet<String> linkedHashSet = new LinkedHashSet();
    String[] arrayOfString3 = this.overlayPaths;
    if (arrayOfString3 != null) {
      int i = arrayOfString3.length;
      for (byte b = 0; b < i; b++)
        linkedHashSet.add(this.overlayPaths[b]); 
    } 
    ArrayMap<String, String[]> arrayMap = this.sharedLibraryOverlayPaths;
    if (arrayMap != null)
      for (String[] arrayOfString : arrayMap.values()) {
        if (arrayOfString != null) {
          int i = arrayOfString.length;
          for (byte b = 0; b < i; b++)
            linkedHashSet.add(arrayOfString[b]); 
        } 
      }  
    String[] arrayOfString1 = (String[])linkedHashSet.toArray((Object[])new String[0]);
    this.cachedOverlayPaths = arrayOfString1;
    return arrayOfString1;
  }
  
  public String[] getOverlayPaths() {
    return this.overlayPaths;
  }
  
  public Pair<String, Integer> getOverrideLabelIconForComponent(ComponentName paramComponentName) {
    return ArrayUtils.isEmpty((Map)this.componentLabelIconOverrideMap) ? null : (Pair<String, Integer>)this.componentLabelIconOverrideMap.get(paramComponentName);
  }
  
  public Map<String, String[]> getSharedLibraryOverlayPaths() {
    return (Map<String, String[]>)this.sharedLibraryOverlayPaths;
  }
  
  public int hashCode() {
    return ((((((((((((((((((Long.hashCode(this.ceDataInode) * 31 + Boolean.hashCode(this.installed)) * 31 + Boolean.hashCode(this.stopped)) * 31 + Boolean.hashCode(this.notLaunched)) * 31 + Boolean.hashCode(this.hidden)) * 31 + this.distractionFlags) * 31 + Boolean.hashCode(this.suspended)) * 31 + Objects.hashCode(this.suspendParams)) * 31 + Boolean.hashCode(this.instantApp)) * 31 + Boolean.hashCode(this.virtualPreload)) * 31 + this.enabled) * 31 + Objects.hashCode(this.lastDisableAppCaller)) * 31 + this.domainVerificationStatus) * 31 + this.appLinkGeneration) * 31 + this.categoryHint) * 31 + this.installReason) * 31 + this.uninstallReason) * 31 + Objects.hashCode(this.disabledComponents)) * 31 + Objects.hashCode(this.enabledComponents)) * 31 + Objects.hashCode(this.harmfulAppWarning);
  }
  
  public boolean isAvailable(int paramInt) {
    boolean bool2;
    boolean bool1 = true;
    if ((0x400000 & paramInt) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if ((paramInt & 0x2000) != 0) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    boolean bool3 = bool1;
    if (!bool2) {
      if (this.installed) {
        bool3 = bool1;
        if (this.hidden) {
          if (paramInt != 0)
            return bool1; 
        } else {
          return bool3;
        } 
      } 
      bool3 = false;
    } 
    return bool3;
  }
  
  public boolean isEnabled(ComponentInfo paramComponentInfo, int paramInt) {
    return isEnabled(paramComponentInfo.applicationInfo.enabled, paramComponentInfo.enabled, paramComponentInfo.name, paramInt);
  }
  
  public boolean isEnabled(boolean paramBoolean, ParsedMainComponent paramParsedMainComponent, int paramInt) {
    return isEnabled(paramBoolean, paramParsedMainComponent.isEnabled(), paramParsedMainComponent.getName(), paramInt);
  }
  
  public boolean isEnabled(boolean paramBoolean1, boolean paramBoolean2, String paramString, int paramInt) {
    if ((paramInt & 0x200) != 0)
      return true; 
    int i = this.enabled;
    if (i != 0)
      if (i != 2 && i != 3) {
        if (i != 4)
          return ArrayUtils.contains((Collection)this.enabledComponents, paramString) ? true : (ArrayUtils.contains((Collection)this.disabledComponents, paramString) ? false : paramBoolean2); 
        if ((0x8000 & paramInt) == 0)
          return false; 
      } else {
        return false;
      }  
    return !paramBoolean1 ? false : (ArrayUtils.contains((Collection)this.enabledComponents, paramString) ? true : (ArrayUtils.contains((Collection)this.disabledComponents, paramString) ? false : paramBoolean2));
  }
  
  public boolean isMatch(ComponentInfo paramComponentInfo, int paramInt) {
    return isMatch(paramComponentInfo.applicationInfo.isSystemApp(), paramComponentInfo.applicationInfo.enabled, paramComponentInfo.enabled, paramComponentInfo.directBootAware, paramComponentInfo.name, paramInt);
  }
  
  public boolean isMatch(boolean paramBoolean1, boolean paramBoolean2, ParsedMainComponent paramParsedMainComponent, int paramInt) {
    return isMatch(paramBoolean1, paramBoolean2, paramParsedMainComponent.isEnabled(), paramParsedMainComponent.isDirectBootAware(), paramParsedMainComponent.getName(), paramInt);
  }
  
  public boolean isMatch(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, String paramString, int paramInt) {
    boolean bool2;
    boolean bool3;
    boolean bool1 = true;
    if ((0x402000 & paramInt) != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if (!isAvailable(paramInt) && (!paramBoolean1 || !bool2))
      return reportIfDebug(false, paramInt); 
    if (!isEnabled(paramBoolean2, paramBoolean3, paramString, paramInt))
      return reportIfDebug(false, paramInt); 
    if ((0x100000 & paramInt) != 0 && !paramBoolean1)
      return reportIfDebug(false, paramInt); 
    if ((0x40000 & paramInt) != 0 && !paramBoolean4) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    if ((0x80000 & paramInt) != 0 && paramBoolean4) {
      bool3 = true;
    } else {
      bool3 = false;
    } 
    paramBoolean1 = bool1;
    if (!bool2)
      if (bool3) {
        paramBoolean1 = bool1;
      } else {
        paramBoolean1 = false;
      }  
    return reportIfDebug(paramBoolean1, paramInt);
  }
  
  public boolean overrideLabelAndIcon(ComponentName paramComponentName, String paramString, Integer paramInteger) {
    boolean bool;
    String str1 = null;
    Integer integer1 = null;
    ArrayMap<ComponentName, Pair<String, Integer>> arrayMap = this.componentLabelIconOverrideMap;
    String str2 = str1;
    Integer integer2 = integer1;
    if (arrayMap != null) {
      Pair pair = (Pair)arrayMap.get(paramComponentName);
      str2 = str1;
      integer2 = integer1;
      if (pair != null) {
        str2 = (String)pair.first;
        integer2 = (Integer)pair.second;
      } 
    } 
    if (!TextUtils.equals(str2, paramString) || !Objects.equals(integer2, paramInteger)) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool)
      if (paramString == null && paramInteger == null) {
        this.componentLabelIconOverrideMap.remove(paramComponentName);
        if (this.componentLabelIconOverrideMap.isEmpty())
          this.componentLabelIconOverrideMap = null; 
      } else {
        if (this.componentLabelIconOverrideMap == null)
          this.componentLabelIconOverrideMap = new ArrayMap(1); 
        this.componentLabelIconOverrideMap.put(paramComponentName, Pair.create(paramString, paramInteger));
      }  
    return bool;
  }
  
  public boolean reportIfDebug(boolean paramBoolean, int paramInt) {
    return paramBoolean;
  }
  
  public void resetOverrideComponentLabelIcon() {
    this.componentLabelIconOverrideMap = null;
  }
  
  public void setOverlayPaths(String[] paramArrayOfString) {
    this.overlayPaths = paramArrayOfString;
    this.cachedOverlayPaths = null;
  }
  
  public void setSharedLibraryOverlayPaths(String paramString, String[] paramArrayOfString) {
    if (this.sharedLibraryOverlayPaths == null)
      this.sharedLibraryOverlayPaths = new ArrayMap(); 
    this.sharedLibraryOverlayPaths.put(paramString, paramArrayOfString);
    this.cachedOverlayPaths = null;
  }
  
  public static final class SuspendParams {
    private static final String TAG_APP_EXTRAS = "app-extras";
    
    private static final String TAG_DIALOG_INFO = "dialog-info";
    
    private static final String TAG_LAUNCHER_EXTRAS = "launcher-extras";
    
    public PersistableBundle appExtras;
    
    public SuspendDialogInfo dialogInfo;
    
    public PersistableBundle launcherExtras;
    
    public static SuspendParams getInstanceOrNull(SuspendDialogInfo param1SuspendDialogInfo, PersistableBundle param1PersistableBundle1, PersistableBundle param1PersistableBundle2) {
      if (param1SuspendDialogInfo == null && param1PersistableBundle1 == null && param1PersistableBundle2 == null)
        return null; 
      SuspendParams suspendParams = new SuspendParams();
      suspendParams.dialogInfo = param1SuspendDialogInfo;
      suspendParams.appExtras = param1PersistableBundle1;
      suspendParams.launcherExtras = param1PersistableBundle2;
      return suspendParams;
    }
    
    public static SuspendParams restoreFromXml(XmlPullParser param1XmlPullParser) throws IOException {
      SuspendDialogInfo suspendDialogInfo = null;
      PersistableBundle persistableBundle1 = null;
      PersistableBundle persistableBundle2 = null;
      int i = param1XmlPullParser.getDepth();
      try {
        while (true) {
          int j = param1XmlPullParser.next();
          if (j != 1 && (j != 3 || param1XmlPullParser.getDepth() > i)) {
            if (j == 3 || j == 4)
              continue; 
            String str = param1XmlPullParser.getName();
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
                  stringBuilder.append(param1XmlPullParser.getName());
                  stringBuilder.append(" in SuspendParams. Ignoring");
                  Slog.w("PackageUserState", stringBuilder.toString());
                  continue;
                } 
                PersistableBundle persistableBundle3 = PersistableBundle.restoreFromXml(param1XmlPullParser);
                persistableBundle2 = persistableBundle3;
                continue;
              } 
              PersistableBundle persistableBundle = PersistableBundle.restoreFromXml(param1XmlPullParser);
              persistableBundle1 = persistableBundle;
              continue;
            } 
            SuspendDialogInfo suspendDialogInfo1 = SuspendDialogInfo.restoreFromXml(param1XmlPullParser);
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
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof SuspendParams))
        return false; 
      param1Object = param1Object;
      return !Objects.equals(this.dialogInfo, ((SuspendParams)param1Object).dialogInfo) ? false : (!BaseBundle.kindofEquals((BaseBundle)this.appExtras, (BaseBundle)((SuspendParams)param1Object).appExtras) ? false : (!!BaseBundle.kindofEquals((BaseBundle)this.launcherExtras, (BaseBundle)((SuspendParams)param1Object).launcherExtras)));
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
    
    public void saveToXml(XmlSerializer param1XmlSerializer) throws IOException {
      if (this.dialogInfo != null) {
        param1XmlSerializer.startTag(null, "dialog-info");
        this.dialogInfo.saveToXml(param1XmlSerializer);
        param1XmlSerializer.endTag(null, "dialog-info");
      } 
      if (this.appExtras != null) {
        param1XmlSerializer.startTag(null, "app-extras");
        try {
          this.appExtras.saveToXml(param1XmlSerializer);
        } catch (XmlPullParserException xmlPullParserException) {
          Slog.e("PackageUserState", "Exception while trying to write appExtras. Will be lost on reboot", (Throwable)xmlPullParserException);
        } 
        param1XmlSerializer.endTag(null, "app-extras");
      } 
      if (this.launcherExtras != null) {
        param1XmlSerializer.startTag(null, "launcher-extras");
        try {
          this.launcherExtras.saveToXml(param1XmlSerializer);
        } catch (XmlPullParserException xmlPullParserException) {
          Slog.e("PackageUserState", "Exception while trying to write launcherExtras. Will be lost on reboot", (Throwable)xmlPullParserException);
        } 
        param1XmlSerializer.endTag(null, "launcher-extras");
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/PackageUserState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
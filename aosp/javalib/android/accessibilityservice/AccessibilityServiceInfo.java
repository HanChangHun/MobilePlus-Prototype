package android.accessibilityservice;

import android.accessibilityservice.util.AccessibilityUtils;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.Xml;
import android.view.accessibility.AccessibilityEvent;
import com.android.internal.R;
import com.android.internal.compat.IPlatformCompat;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AccessibilityServiceInfo implements Parcelable {
  public static final int CAPABILITY_CAN_CONTROL_MAGNIFICATION = 16;
  
  public static final int CAPABILITY_CAN_PERFORM_GESTURES = 32;
  
  public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
  
  public static final int CAPABILITY_CAN_REQUEST_FILTER_KEY_EVENTS = 8;
  
  public static final int CAPABILITY_CAN_REQUEST_FINGERPRINT_GESTURES = 64;
  
  public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
  
  public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
  
  public static final int CAPABILITY_CAN_TAKE_SCREENSHOT = 128;
  
  public static final Parcelable.Creator<AccessibilityServiceInfo> CREATOR = new Parcelable.Creator<AccessibilityServiceInfo>() {
      public AccessibilityServiceInfo createFromParcel(Parcel param1Parcel) {
        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
        accessibilityServiceInfo.initFromParcel(param1Parcel);
        return accessibilityServiceInfo;
      }
      
      public AccessibilityServiceInfo[] newArray(int param1Int) {
        return new AccessibilityServiceInfo[param1Int];
      }
    };
  
  public static final int DEFAULT = 1;
  
  public static final int FEEDBACK_ALL_MASK = -1;
  
  public static final int FEEDBACK_AUDIBLE = 4;
  
  public static final int FEEDBACK_BRAILLE = 32;
  
  public static final int FEEDBACK_GENERIC = 16;
  
  public static final int FEEDBACK_HAPTIC = 2;
  
  public static final int FEEDBACK_SPOKEN = 1;
  
  public static final int FEEDBACK_VISUAL = 8;
  
  public static final int FLAG_ENABLE_ACCESSIBILITY_VOLUME = 128;
  
  public static final int FLAG_FORCE_DIRECT_BOOT_AWARE = 65536;
  
  public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
  
  public static final int FLAG_REPORT_VIEW_IDS = 16;
  
  public static final int FLAG_REQUEST_ACCESSIBILITY_BUTTON = 256;
  
  public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
  
  public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
  
  public static final int FLAG_REQUEST_FINGERPRINT_GESTURES = 512;
  
  public static final int FLAG_REQUEST_MULTI_FINGER_GESTURES = 4096;
  
  public static final int FLAG_REQUEST_SHORTCUT_WARNING_DIALOG_SPOKEN_FEEDBACK = 1024;
  
  public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
  
  public static final int FLAG_RETRIEVE_INTERACTIVE_WINDOWS = 64;
  
  public static final int FLAG_SERVICE_HANDLES_DOUBLE_TAP = 2048;
  
  private static final long REQUEST_ACCESSIBILITY_BUTTON_CHANGE = 136293963L;
  
  private static final String TAG_ACCESSIBILITY_SERVICE = "accessibility-service";
  
  private static SparseArray<CapabilityInfo> sAvailableCapabilityInfos;
  
  public boolean crashed;
  
  public int eventTypes;
  
  public int feedbackType;
  
  public int flags;
  
  private int mAnimatedImageRes;
  
  private int mCapabilities;
  
  private ComponentName mComponentName;
  
  private int mDescriptionResId;
  
  private int mHtmlDescriptionRes;
  
  private int mInteractiveUiTimeout;
  
  private int mNonInteractiveUiTimeout;
  
  private String mNonLocalizedDescription;
  
  private String mNonLocalizedSummary;
  
  private ResolveInfo mResolveInfo;
  
  private String mSettingsActivityName;
  
  private int mSummaryResId;
  
  public long notificationTimeout;
  
  public String[] packageNames;
  
  public AccessibilityServiceInfo() {}
  
  public AccessibilityServiceInfo(ResolveInfo paramResolveInfo, Context paramContext) throws XmlPullParserException, IOException {
    XmlResourceParser xmlResourceParser1;
    ServiceInfo serviceInfo = paramResolveInfo.serviceInfo;
    this.mComponentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
    this.mResolveInfo = paramResolveInfo;
    XmlResourceParser xmlResourceParser2 = null;
    ResolveInfo resolveInfo = null;
    paramResolveInfo = resolveInfo;
    XmlResourceParser xmlResourceParser3 = xmlResourceParser2;
    try {
      PackageManager packageManager = paramContext.getPackageManager();
      paramResolveInfo = resolveInfo;
      xmlResourceParser3 = xmlResourceParser2;
      XmlResourceParser xmlResourceParser = serviceInfo.loadXmlMetaData(packageManager, "android.accessibilityservice");
      if (xmlResourceParser == null) {
        if (xmlResourceParser != null)
          xmlResourceParser.close(); 
        return;
      } 
      int i;
      for (i = 0; i != 1 && i != 2; i = xmlResourceParser.next()) {
        XmlResourceParser xmlResourceParser4 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
      } 
      xmlResourceParser1 = xmlResourceParser;
      xmlResourceParser3 = xmlResourceParser;
      if ("accessibility-service".equals(xmlResourceParser.getName())) {
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)xmlResourceParser);
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        TypedArray typedArray = packageManager.getResourcesForApplication(serviceInfo.applicationInfo).obtainAttributes(attributeSet, R.styleable.AccessibilityService);
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        this.eventTypes = typedArray.getInt(3, 0);
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        String str = typedArray.getString(4);
        if (str != null) {
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          this.packageNames = str.split("(\\s)*,(\\s)*");
        } 
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        this.feedbackType = typedArray.getInt(5, 0);
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        this.notificationTimeout = typedArray.getInt(6, 0);
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        this.mNonInteractiveUiTimeout = typedArray.getInt(15, 0);
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        this.mInteractiveUiTimeout = typedArray.getInt(16, 0);
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        this.flags = typedArray.getInt(7, 0);
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        this.mSettingsActivityName = typedArray.getString(2);
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        if (typedArray.getBoolean(8, false)) {
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          this.mCapabilities |= 0x1;
        } 
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        if (typedArray.getBoolean(9, false)) {
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          this.mCapabilities = 0x2 | this.mCapabilities;
        } 
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        if (typedArray.getBoolean(11, false)) {
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          this.mCapabilities |= 0x8;
        } 
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        if (typedArray.getBoolean(12, false)) {
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          this.mCapabilities |= 0x10;
        } 
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        if (typedArray.getBoolean(13, false)) {
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          this.mCapabilities |= 0x20;
        } 
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        if (typedArray.getBoolean(14, false)) {
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          this.mCapabilities |= 0x40;
        } 
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        if (typedArray.getBoolean(19, false)) {
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          this.mCapabilities |= 0x80;
        } 
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        TypedValue typedValue = typedArray.peekValue(0);
        if (typedValue != null) {
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          this.mDescriptionResId = typedValue.resourceId;
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          CharSequence charSequence = typedValue.coerceToString();
          if (charSequence != null) {
            xmlResourceParser1 = xmlResourceParser;
            xmlResourceParser3 = xmlResourceParser;
            this.mNonLocalizedDescription = charSequence.toString().trim();
          } 
        } 
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        typedValue = typedArray.peekValue(1);
        if (typedValue != null) {
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          this.mSummaryResId = typedValue.resourceId;
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          CharSequence charSequence = typedValue.coerceToString();
          if (charSequence != null) {
            xmlResourceParser1 = xmlResourceParser;
            xmlResourceParser3 = xmlResourceParser;
            this.mNonLocalizedSummary = charSequence.toString().trim();
          } 
        } 
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        typedValue = typedArray.peekValue(17);
        if (typedValue != null) {
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          this.mAnimatedImageRes = typedValue.resourceId;
        } 
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        typedValue = typedArray.peekValue(18);
        if (typedValue != null) {
          xmlResourceParser1 = xmlResourceParser;
          xmlResourceParser3 = xmlResourceParser;
          this.mHtmlDescriptionRes = typedValue.resourceId;
        } 
        xmlResourceParser1 = xmlResourceParser;
        xmlResourceParser3 = xmlResourceParser;
        typedArray.recycle();
        if (xmlResourceParser != null)
          xmlResourceParser.close(); 
        return;
      } 
      xmlResourceParser1 = xmlResourceParser;
      xmlResourceParser3 = xmlResourceParser;
      XmlPullParserException xmlPullParserException = new XmlPullParserException();
      xmlResourceParser1 = xmlResourceParser;
      xmlResourceParser3 = xmlResourceParser;
      this("Meta-data does not start withaccessibility-service tag");
      xmlResourceParser1 = xmlResourceParser;
      xmlResourceParser3 = xmlResourceParser;
      throw xmlPullParserException;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      xmlResourceParser1 = xmlResourceParser3;
      XmlPullParserException xmlPullParserException = new XmlPullParserException();
      xmlResourceParser1 = xmlResourceParser3;
      StringBuilder stringBuilder = new StringBuilder();
      xmlResourceParser1 = xmlResourceParser3;
      this();
      xmlResourceParser1 = xmlResourceParser3;
      stringBuilder.append("Unable to create context for: ");
      xmlResourceParser1 = xmlResourceParser3;
      stringBuilder.append(serviceInfo.packageName);
      xmlResourceParser1 = xmlResourceParser3;
      this(stringBuilder.toString());
      xmlResourceParser1 = xmlResourceParser3;
      throw xmlPullParserException;
    } finally {}
    if (xmlResourceParser1 != null)
      xmlResourceParser1.close(); 
    throw paramContext;
  }
  
  private static void appendCapabilities(StringBuilder paramStringBuilder, int paramInt) {
    paramStringBuilder.append("capabilities:");
    paramStringBuilder.append("[");
    while (paramInt != 0) {
      int i = 1 << Integer.numberOfTrailingZeros(paramInt);
      paramStringBuilder.append(capabilityToString(i));
      paramInt &= i;
      if (paramInt != 0)
        paramStringBuilder.append(", "); 
    } 
    paramStringBuilder.append("]");
  }
  
  private static void appendEventTypes(StringBuilder paramStringBuilder, int paramInt) {
    paramStringBuilder.append("eventTypes:");
    paramStringBuilder.append("[");
    while (paramInt != 0) {
      int i = 1 << Integer.numberOfTrailingZeros(paramInt);
      paramStringBuilder.append(AccessibilityEvent.eventTypeToString(i));
      paramInt &= i;
      if (paramInt != 0)
        paramStringBuilder.append(", "); 
    } 
    paramStringBuilder.append("]");
  }
  
  private static void appendFeedbackTypes(StringBuilder paramStringBuilder, int paramInt) {
    paramStringBuilder.append("feedbackTypes:");
    paramStringBuilder.append("[");
    while (paramInt != 0) {
      int i = 1 << Integer.numberOfTrailingZeros(paramInt);
      paramStringBuilder.append(feedbackTypeToString(i));
      paramInt &= i;
      if (paramInt != 0)
        paramStringBuilder.append(", "); 
    } 
    paramStringBuilder.append("]");
  }
  
  private static void appendFlags(StringBuilder paramStringBuilder, int paramInt) {
    paramStringBuilder.append("flags:");
    paramStringBuilder.append("[");
    while (paramInt != 0) {
      int i = 1 << Integer.numberOfTrailingZeros(paramInt);
      paramStringBuilder.append(flagToString(i));
      paramInt &= i;
      if (paramInt != 0)
        paramStringBuilder.append(", "); 
    } 
    paramStringBuilder.append("]");
  }
  
  private static void appendPackageNames(StringBuilder paramStringBuilder, String[] paramArrayOfString) {
    paramStringBuilder.append("packageNames:");
    paramStringBuilder.append("[");
    if (paramArrayOfString != null) {
      int i = paramArrayOfString.length;
      for (byte b = 0; b < i; b++) {
        paramStringBuilder.append(paramArrayOfString[b]);
        if (b < i - 1)
          paramStringBuilder.append(", "); 
      } 
    } 
    paramStringBuilder.append("]");
  }
  
  public static String capabilityToString(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 4) ? ((paramInt != 8) ? ((paramInt != 16) ? ((paramInt != 32) ? ((paramInt != 64) ? ((paramInt != 128) ? "UNKNOWN" : "CAPABILITY_CAN_TAKE_SCREENSHOT") : "CAPABILITY_CAN_REQUEST_FINGERPRINT_GESTURES") : "CAPABILITY_CAN_PERFORM_GESTURES") : "CAPABILITY_CAN_CONTROL_MAGNIFICATION") : "CAPABILITY_CAN_REQUEST_FILTER_KEY_EVENTS") : "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY") : "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION") : "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
  }
  
  public static String feedbackTypeToString(int paramInt) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    while (paramInt != 0) {
      int i = 1 << Integer.numberOfTrailingZeros(paramInt);
      paramInt &= i;
      if (i != 1) {
        if (i != 2) {
          if (i != 4) {
            if (i != 8) {
              if (i != 16) {
                if (i != 32)
                  continue; 
                if (stringBuilder.length() > 1)
                  stringBuilder.append(", "); 
                stringBuilder.append("FEEDBACK_BRAILLE");
                continue;
              } 
              if (stringBuilder.length() > 1)
                stringBuilder.append(", "); 
              stringBuilder.append("FEEDBACK_GENERIC");
              continue;
            } 
            if (stringBuilder.length() > 1)
              stringBuilder.append(", "); 
            stringBuilder.append("FEEDBACK_VISUAL");
            continue;
          } 
          if (stringBuilder.length() > 1)
            stringBuilder.append(", "); 
          stringBuilder.append("FEEDBACK_AUDIBLE");
          continue;
        } 
        if (stringBuilder.length() > 1)
          stringBuilder.append(", "); 
        stringBuilder.append("FEEDBACK_HAPTIC");
        continue;
      } 
      if (stringBuilder.length() > 1)
        stringBuilder.append(", "); 
      stringBuilder.append("FEEDBACK_SPOKEN");
    } 
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  private static boolean fingerprintAvailable(Context paramContext) {
    boolean bool;
    if (paramContext.getPackageManager().hasSystemFeature("android.hardware.fingerprint") && ((FingerprintManager)paramContext.getSystemService(FingerprintManager.class)).isHardwareDetected()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static String flagToString(int paramInt) {
    if (paramInt != 1) {
      if (paramInt != 2) {
        switch (paramInt) {
          default:
            return null;
          case 4096:
            return "FLAG_REQUEST_MULTI_FINGER_GESTURES";
          case 2048:
            return "FLAG_SERVICE_HANDLES_DOUBLE_TAP";
          case 1024:
            return "FLAG_REQUEST_SHORTCUT_WARNING_DIALOG_SPOKEN_FEEDBACK";
          case 512:
            return "FLAG_REQUEST_FINGERPRINT_GESTURES";
          case 256:
            return "FLAG_REQUEST_ACCESSIBILITY_BUTTON";
          case 128:
            return "FLAG_ENABLE_ACCESSIBILITY_VOLUME";
          case 64:
            return "FLAG_RETRIEVE_INTERACTIVE_WINDOWS";
          case 32:
            return "FLAG_REQUEST_FILTER_KEY_EVENTS";
          case 16:
            return "FLAG_REPORT_VIEW_IDS";
          case 8:
            return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
          case 4:
            break;
        } 
        return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
      } 
      return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
    } 
    return "DEFAULT";
  }
  
  private static SparseArray<CapabilityInfo> getCapabilityInfoSparseArray(Context paramContext) {
    if (sAvailableCapabilityInfos == null) {
      SparseArray<CapabilityInfo> sparseArray = new SparseArray();
      sAvailableCapabilityInfos = sparseArray;
      sparseArray.put(1, new CapabilityInfo(1, 17039788, 17039781));
      sAvailableCapabilityInfos.put(2, new CapabilityInfo(2, 17039787, 17039780));
      sAvailableCapabilityInfos.put(8, new CapabilityInfo(8, 17039786, 17039779));
      sAvailableCapabilityInfos.put(16, new CapabilityInfo(16, 17039784, 17039777));
      sAvailableCapabilityInfos.put(32, new CapabilityInfo(32, 17039785, 17039778));
      sAvailableCapabilityInfos.put(128, new CapabilityInfo(128, 17039789, 17039782));
      if (paramContext == null || fingerprintAvailable(paramContext))
        sAvailableCapabilityInfos.put(64, new CapabilityInfo(64, 17039783, 17039776)); 
    } 
    return sAvailableCapabilityInfos;
  }
  
  private void initFromParcel(Parcel paramParcel) {
    boolean bool;
    this.eventTypes = paramParcel.readInt();
    this.packageNames = paramParcel.readStringArray();
    this.feedbackType = paramParcel.readInt();
    this.notificationTimeout = paramParcel.readLong();
    this.mNonInteractiveUiTimeout = paramParcel.readInt();
    this.mInteractiveUiTimeout = paramParcel.readInt();
    this.flags = paramParcel.readInt();
    if (paramParcel.readInt() != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.crashed = bool;
    this.mComponentName = (ComponentName)paramParcel.readParcelable(getClass().getClassLoader());
    this.mResolveInfo = (ResolveInfo)paramParcel.readParcelable(null);
    this.mSettingsActivityName = paramParcel.readString();
    this.mCapabilities = paramParcel.readInt();
    this.mSummaryResId = paramParcel.readInt();
    this.mNonLocalizedSummary = paramParcel.readString();
    this.mDescriptionResId = paramParcel.readInt();
    this.mAnimatedImageRes = paramParcel.readInt();
    this.mHtmlDescriptionRes = paramParcel.readInt();
    this.mNonLocalizedDescription = paramParcel.readString();
  }
  
  private boolean isRequestAccessibilityButtonChangeEnabled(IPlatformCompat paramIPlatformCompat) {
    ResolveInfo resolveInfo = this.mResolveInfo;
    boolean bool = true;
    if (resolveInfo == null)
      return true; 
    if (paramIPlatformCompat != null)
      try {
        return paramIPlatformCompat.isChangeEnabled(136293963L, resolveInfo.serviceInfo.applicationInfo);
      } catch (RemoteException remoteException) {} 
    if (this.mResolveInfo.serviceInfo.applicationInfo.targetSdkVersion <= 29)
      bool = false; 
    return bool;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (getClass() != paramObject.getClass())
      return false; 
    paramObject = paramObject;
    ComponentName componentName = this.mComponentName;
    if (componentName == null) {
      if (((AccessibilityServiceInfo)paramObject).mComponentName != null)
        return false; 
    } else if (!componentName.equals(((AccessibilityServiceInfo)paramObject).mComponentName)) {
      return false;
    } 
    return true;
  }
  
  public int getAnimatedImageRes() {
    return this.mAnimatedImageRes;
  }
  
  public boolean getCanRetrieveWindowContent() {
    int i = this.mCapabilities;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public int getCapabilities() {
    return this.mCapabilities;
  }
  
  public List<CapabilityInfo> getCapabilityInfos() {
    return getCapabilityInfos(null);
  }
  
  public List<CapabilityInfo> getCapabilityInfos(Context paramContext) {
    if (this.mCapabilities == 0)
      return Collections.emptyList(); 
    int i = this.mCapabilities;
    ArrayList<CapabilityInfo> arrayList = new ArrayList();
    SparseArray<CapabilityInfo> sparseArray = getCapabilityInfoSparseArray(paramContext);
    while (i != 0) {
      int j = 1 << Integer.numberOfTrailingZeros(i);
      i &= j;
      CapabilityInfo capabilityInfo = (CapabilityInfo)sparseArray.get(j);
      if (capabilityInfo != null)
        arrayList.add(capabilityInfo); 
    } 
    return arrayList;
  }
  
  public ComponentName getComponentName() {
    return this.mComponentName;
  }
  
  public String getDescription() {
    return this.mNonLocalizedDescription;
  }
  
  public String getId() {
    return this.mComponentName.flattenToShortString();
  }
  
  public int getInteractiveUiTimeoutMillis() {
    return this.mInteractiveUiTimeout;
  }
  
  public int getNonInteractiveUiTimeoutMillis() {
    return this.mNonInteractiveUiTimeout;
  }
  
  public ResolveInfo getResolveInfo() {
    return this.mResolveInfo;
  }
  
  public String getSettingsActivityName() {
    return this.mSettingsActivityName;
  }
  
  public int hashCode() {
    int i;
    ComponentName componentName = this.mComponentName;
    if (componentName == null) {
      i = 0;
    } else {
      i = componentName.hashCode();
    } 
    return i + 31;
  }
  
  public boolean isDirectBootAware() {
    return ((this.flags & 0x10000) != 0 || this.mResolveInfo.serviceInfo.directBootAware);
  }
  
  public Drawable loadAnimatedImage(Context paramContext) {
    return (this.mAnimatedImageRes == 0) ? null : AccessibilityUtils.loadSafeAnimatedImage(paramContext, this.mResolveInfo.serviceInfo.applicationInfo, this.mAnimatedImageRes);
  }
  
  public String loadDescription(PackageManager paramPackageManager) {
    if (this.mDescriptionResId == 0)
      return this.mNonLocalizedDescription; 
    ServiceInfo serviceInfo = this.mResolveInfo.serviceInfo;
    CharSequence charSequence = paramPackageManager.getText(serviceInfo.packageName, this.mDescriptionResId, serviceInfo.applicationInfo);
    return (charSequence != null) ? charSequence.toString().trim() : null;
  }
  
  public String loadHtmlDescription(PackageManager paramPackageManager) {
    if (this.mHtmlDescriptionRes == 0)
      return null; 
    ServiceInfo serviceInfo = this.mResolveInfo.serviceInfo;
    CharSequence charSequence = paramPackageManager.getText(serviceInfo.packageName, this.mHtmlDescriptionRes, serviceInfo.applicationInfo);
    return (charSequence != null) ? AccessibilityUtils.getFilteredHtmlText(charSequence.toString().trim()) : null;
  }
  
  public CharSequence loadSummary(PackageManager paramPackageManager) {
    if (this.mSummaryResId == 0)
      return this.mNonLocalizedSummary; 
    ServiceInfo serviceInfo = this.mResolveInfo.serviceInfo;
    CharSequence charSequence = paramPackageManager.getText(serviceInfo.packageName, this.mSummaryResId, serviceInfo.applicationInfo);
    return (charSequence != null) ? charSequence.toString().trim() : null;
  }
  
  public void setCapabilities(int paramInt) {
    this.mCapabilities = paramInt;
  }
  
  public void setComponentName(ComponentName paramComponentName) {
    this.mComponentName = paramComponentName;
  }
  
  public void setInteractiveUiTimeoutMillis(int paramInt) {
    this.mInteractiveUiTimeout = paramInt;
  }
  
  public void setNonInteractiveUiTimeoutMillis(int paramInt) {
    this.mNonInteractiveUiTimeout = paramInt;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    appendEventTypes(stringBuilder, this.eventTypes);
    stringBuilder.append(", ");
    appendPackageNames(stringBuilder, this.packageNames);
    stringBuilder.append(", ");
    appendFeedbackTypes(stringBuilder, this.feedbackType);
    stringBuilder.append(", ");
    stringBuilder.append("notificationTimeout: ");
    stringBuilder.append(this.notificationTimeout);
    stringBuilder.append(", ");
    stringBuilder.append("nonInteractiveUiTimeout: ");
    stringBuilder.append(this.mNonInteractiveUiTimeout);
    stringBuilder.append(", ");
    stringBuilder.append("interactiveUiTimeout: ");
    stringBuilder.append(this.mInteractiveUiTimeout);
    stringBuilder.append(", ");
    appendFlags(stringBuilder, this.flags);
    stringBuilder.append(", ");
    stringBuilder.append("id: ");
    stringBuilder.append(getId());
    stringBuilder.append(", ");
    stringBuilder.append("resolveInfo: ");
    stringBuilder.append(this.mResolveInfo);
    stringBuilder.append(", ");
    stringBuilder.append("settingsActivityName: ");
    stringBuilder.append(this.mSettingsActivityName);
    stringBuilder.append(", ");
    stringBuilder.append("summary: ");
    stringBuilder.append(this.mNonLocalizedSummary);
    stringBuilder.append(", ");
    appendCapabilities(stringBuilder, this.mCapabilities);
    return stringBuilder.toString();
  }
  
  public void updateDynamicallyConfigurableProperties(IPlatformCompat paramIPlatformCompat, AccessibilityServiceInfo paramAccessibilityServiceInfo) {
    if (isRequestAccessibilityButtonChangeEnabled(paramIPlatformCompat)) {
      int i = paramAccessibilityServiceInfo.flags & 0xFFFFFEFF;
      paramAccessibilityServiceInfo.flags = i;
      paramAccessibilityServiceInfo.flags = i | this.flags & 0x100;
    } 
    this.eventTypes = paramAccessibilityServiceInfo.eventTypes;
    this.packageNames = paramAccessibilityServiceInfo.packageNames;
    this.feedbackType = paramAccessibilityServiceInfo.feedbackType;
    this.notificationTimeout = paramAccessibilityServiceInfo.notificationTimeout;
    this.mNonInteractiveUiTimeout = paramAccessibilityServiceInfo.mNonInteractiveUiTimeout;
    this.mInteractiveUiTimeout = paramAccessibilityServiceInfo.mInteractiveUiTimeout;
    this.flags = paramAccessibilityServiceInfo.flags;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.eventTypes);
    paramParcel.writeStringArray(this.packageNames);
    paramParcel.writeInt(this.feedbackType);
    paramParcel.writeLong(this.notificationTimeout);
    paramParcel.writeInt(this.mNonInteractiveUiTimeout);
    paramParcel.writeInt(this.mInteractiveUiTimeout);
    paramParcel.writeInt(this.flags);
    paramParcel.writeInt(this.crashed);
    paramParcel.writeParcelable((Parcelable)this.mComponentName, paramInt);
    paramParcel.writeParcelable((Parcelable)this.mResolveInfo, 0);
    paramParcel.writeString(this.mSettingsActivityName);
    paramParcel.writeInt(this.mCapabilities);
    paramParcel.writeInt(this.mSummaryResId);
    paramParcel.writeString(this.mNonLocalizedSummary);
    paramParcel.writeInt(this.mDescriptionResId);
    paramParcel.writeInt(this.mAnimatedImageRes);
    paramParcel.writeInt(this.mHtmlDescriptionRes);
    paramParcel.writeString(this.mNonLocalizedDescription);
  }
  
  public static final class CapabilityInfo {
    public final int capability;
    
    public final int descResId;
    
    public final int titleResId;
    
    public CapabilityInfo(int param1Int1, int param1Int2, int param1Int3) {
      this.capability = param1Int1;
      this.titleResId = param1Int2;
      this.descResId = param1Int3;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface FeedbackType {}
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityServiceInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
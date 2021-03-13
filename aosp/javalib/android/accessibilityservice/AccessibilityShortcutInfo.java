package android.accessibilityservice;

import android.accessibilityservice.util.AccessibilityUtils;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Xml;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class AccessibilityShortcutInfo {
  public static final String META_DATA = "android.accessibilityshortcut.target";
  
  private static final String TAG_ACCESSIBILITY_SHORTCUT = "accessibility-shortcut-target";
  
  private final ActivityInfo mActivityInfo;
  
  private final int mAnimatedImageRes;
  
  private final ComponentName mComponentName;
  
  private final int mDescriptionResId;
  
  private final int mHtmlDescriptionRes;
  
  private String mSettingsActivityName;
  
  private final int mSummaryResId;
  
  public AccessibilityShortcutInfo(Context paramContext, ActivityInfo paramActivityInfo) throws XmlPullParserException, IOException {
    PackageManager packageManager = paramContext.getPackageManager();
    this.mComponentName = paramActivityInfo.getComponentName();
    this.mActivityInfo = paramActivityInfo;
    try {
      XmlResourceParser xmlResourceParser = paramActivityInfo.loadXmlMetaData(packageManager, "android.accessibilityshortcut.target");
      if (xmlResourceParser != null) {
        int i = 0;
        while (true) {
          if (i != 1 && i != 2)
            try {
              i = xmlResourceParser.next();
            } finally {
              if (xmlResourceParser != null)
                try {
                  xmlResourceParser.close();
                } finally {
                  xmlResourceParser = null;
                }  
            }  
          if ("accessibility-shortcut-target".equals(xmlResourceParser.getName())) {
            AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)xmlResourceParser);
            TypedArray typedArray = packageManager.getResourcesForApplication(this.mActivityInfo.applicationInfo).obtainAttributes(attributeSet, R.styleable.AccessibilityShortcutTarget);
            this.mDescriptionResId = typedArray.getResourceId(0, 0);
            this.mSummaryResId = typedArray.getResourceId(1, 0);
            this.mAnimatedImageRes = typedArray.getResourceId(3, 0);
            this.mHtmlDescriptionRes = typedArray.getResourceId(4, 0);
            this.mSettingsActivityName = typedArray.getString(2);
            typedArray.recycle();
            if (this.mDescriptionResId != 0 || this.mHtmlDescriptionRes != 0) {
              i = this.mSummaryResId;
              if (i != 0) {
                if (xmlResourceParser != null)
                  xmlResourceParser.close(); 
                return;
              } 
            } 
            XmlPullParserException xmlPullParserException2 = new XmlPullParserException();
            this("No description or summary in meta-data");
            throw xmlPullParserException2;
          } 
          XmlPullParserException xmlPullParserException1 = new XmlPullParserException();
          this("Meta-data does not start withaccessibility-shortcut-target tag");
          throw xmlPullParserException1;
        } 
      } 
      XmlPullParserException xmlPullParserException = new XmlPullParserException();
      this("Meta-data accessibility-shortcut-target does not exist");
      throw xmlPullParserException;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unable to create context for: ");
      stringBuilder.append(this.mActivityInfo.packageName);
      throw new XmlPullParserException(stringBuilder.toString());
    } 
  }
  
  private String loadResourceString(PackageManager paramPackageManager, ActivityInfo paramActivityInfo, int paramInt) {
    if (paramInt == 0)
      return null; 
    CharSequence charSequence = paramPackageManager.getText(paramActivityInfo.packageName, paramInt, paramActivityInfo.applicationInfo);
    return (charSequence != null) ? charSequence.toString().trim() : null;
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
      if (((AccessibilityShortcutInfo)paramObject).mComponentName != null)
        return false; 
    } else if (!componentName.equals(((AccessibilityShortcutInfo)paramObject).mComponentName)) {
      return false;
    } 
    return true;
  }
  
  public ActivityInfo getActivityInfo() {
    return this.mActivityInfo;
  }
  
  public int getAnimatedImageRes() {
    return this.mAnimatedImageRes;
  }
  
  public ComponentName getComponentName() {
    return this.mComponentName;
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
  
  public Drawable loadAnimatedImage(Context paramContext) {
    return (this.mAnimatedImageRes == 0) ? null : AccessibilityUtils.loadSafeAnimatedImage(paramContext, this.mActivityInfo.applicationInfo, this.mAnimatedImageRes);
  }
  
  public String loadDescription(PackageManager paramPackageManager) {
    return loadResourceString(paramPackageManager, this.mActivityInfo, this.mDescriptionResId);
  }
  
  public String loadHtmlDescription(PackageManager paramPackageManager) {
    String str = loadResourceString(paramPackageManager, this.mActivityInfo, this.mHtmlDescriptionRes);
    return (str != null) ? AccessibilityUtils.getFilteredHtmlText(str) : null;
  }
  
  public String loadSummary(PackageManager paramPackageManager) {
    return loadResourceString(paramPackageManager, this.mActivityInfo, this.mSummaryResId);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AccessibilityShortcutInfo[");
    stringBuilder.append("activityInfo: ");
    stringBuilder.append(this.mActivityInfo);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/accessibilityservice/AccessibilityShortcutInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
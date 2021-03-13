package android.appwidget;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ResourceId;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.UserHandle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AppWidgetProviderInfo implements Parcelable {
  public static final Parcelable.Creator<AppWidgetProviderInfo> CREATOR = new Parcelable.Creator<AppWidgetProviderInfo>() {
      public AppWidgetProviderInfo createFromParcel(Parcel param1Parcel) {
        return new AppWidgetProviderInfo(param1Parcel);
      }
      
      public AppWidgetProviderInfo[] newArray(int param1Int) {
        return new AppWidgetProviderInfo[param1Int];
      }
    };
  
  public static final int RESIZE_BOTH = 3;
  
  public static final int RESIZE_HORIZONTAL = 1;
  
  public static final int RESIZE_NONE = 0;
  
  public static final int RESIZE_VERTICAL = 2;
  
  public static final int WIDGET_CATEGORY_HOME_SCREEN = 1;
  
  public static final int WIDGET_CATEGORY_KEYGUARD = 2;
  
  public static final int WIDGET_CATEGORY_SEARCHBOX = 4;
  
  public static final int WIDGET_FEATURE_HIDE_FROM_PICKER = 2;
  
  public static final int WIDGET_FEATURE_RECONFIGURABLE = 1;
  
  public int autoAdvanceViewId;
  
  public ComponentName configure;
  
  public int icon;
  
  public int initialKeyguardLayout;
  
  public int initialLayout;
  
  @Deprecated
  public String label;
  
  public int minHeight;
  
  public int minResizeHeight;
  
  public int minResizeWidth;
  
  public int minWidth;
  
  public int previewImage;
  
  public ComponentName provider;
  
  public ActivityInfo providerInfo;
  
  public int resizeMode;
  
  public int updatePeriodMillis;
  
  public int widgetCategory;
  
  public int widgetFeatures;
  
  public AppWidgetProviderInfo() {}
  
  public AppWidgetProviderInfo(Parcel paramParcel) {
    this.provider = (ComponentName)paramParcel.readTypedObject(ComponentName.CREATOR);
    this.minWidth = paramParcel.readInt();
    this.minHeight = paramParcel.readInt();
    this.minResizeWidth = paramParcel.readInt();
    this.minResizeHeight = paramParcel.readInt();
    this.updatePeriodMillis = paramParcel.readInt();
    this.initialLayout = paramParcel.readInt();
    this.initialKeyguardLayout = paramParcel.readInt();
    this.configure = (ComponentName)paramParcel.readTypedObject(ComponentName.CREATOR);
    this.label = paramParcel.readString();
    this.icon = paramParcel.readInt();
    this.previewImage = paramParcel.readInt();
    this.autoAdvanceViewId = paramParcel.readInt();
    this.resizeMode = paramParcel.readInt();
    this.widgetCategory = paramParcel.readInt();
    this.providerInfo = (ActivityInfo)paramParcel.readTypedObject(ActivityInfo.CREATOR);
    this.widgetFeatures = paramParcel.readInt();
  }
  
  private Drawable loadDrawable(Context paramContext, int paramInt1, int paramInt2, boolean paramBoolean) {
    Drawable drawable = null;
    try {
      Resources resources = paramContext.getPackageManager().getResourcesForApplication(this.providerInfo.applicationInfo);
      if (ResourceId.isValid(paramInt2)) {
        int i = paramInt1;
        if (paramInt1 < 0)
          i = 0; 
        return resources.getDrawableForDensity(paramInt2, i, null);
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException|android.content.res.Resources.NotFoundException nameNotFoundException) {}
    if (paramBoolean)
      drawable = this.providerInfo.loadIcon(paramContext.getPackageManager()); 
    return drawable;
  }
  
  public AppWidgetProviderInfo clone() {
    AppWidgetProviderInfo appWidgetProviderInfo = new AppWidgetProviderInfo();
    ComponentName componentName1 = this.provider;
    ComponentName componentName2 = null;
    if (componentName1 == null) {
      componentName1 = null;
    } else {
      componentName1 = componentName1.clone();
    } 
    appWidgetProviderInfo.provider = componentName1;
    appWidgetProviderInfo.minWidth = this.minWidth;
    appWidgetProviderInfo.minHeight = this.minHeight;
    int i = this.minResizeHeight;
    appWidgetProviderInfo.minResizeWidth = i;
    appWidgetProviderInfo.minResizeHeight = i;
    appWidgetProviderInfo.updatePeriodMillis = this.updatePeriodMillis;
    appWidgetProviderInfo.initialLayout = this.initialLayout;
    appWidgetProviderInfo.initialKeyguardLayout = this.initialKeyguardLayout;
    componentName1 = this.configure;
    if (componentName1 == null) {
      componentName1 = componentName2;
    } else {
      componentName1 = componentName1.clone();
    } 
    appWidgetProviderInfo.configure = componentName1;
    appWidgetProviderInfo.label = this.label;
    appWidgetProviderInfo.icon = this.icon;
    appWidgetProviderInfo.previewImage = this.previewImage;
    appWidgetProviderInfo.autoAdvanceViewId = this.autoAdvanceViewId;
    appWidgetProviderInfo.resizeMode = this.resizeMode;
    appWidgetProviderInfo.widgetCategory = this.widgetCategory;
    appWidgetProviderInfo.providerInfo = this.providerInfo;
    appWidgetProviderInfo.widgetFeatures = this.widgetFeatures;
    return appWidgetProviderInfo;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public final UserHandle getProfile() {
    return new UserHandle(UserHandle.getUserId(this.providerInfo.applicationInfo.uid));
  }
  
  public final Drawable loadIcon(Context paramContext, int paramInt) {
    return loadDrawable(paramContext, paramInt, this.providerInfo.getIconResource(), true);
  }
  
  public final String loadLabel(PackageManager paramPackageManager) {
    CharSequence charSequence = this.providerInfo.loadLabel(paramPackageManager);
    return (charSequence != null) ? charSequence.toString().trim() : null;
  }
  
  public final Drawable loadPreviewImage(Context paramContext, int paramInt) {
    return loadDrawable(paramContext, paramInt, this.previewImage, false);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("AppWidgetProviderInfo(");
    stringBuilder.append(getProfile());
    stringBuilder.append('/');
    stringBuilder.append(this.provider);
    stringBuilder.append(')');
    return stringBuilder.toString();
  }
  
  public void updateDimensions(DisplayMetrics paramDisplayMetrics) {
    this.minWidth = TypedValue.complexToDimensionPixelSize(this.minWidth, paramDisplayMetrics);
    this.minHeight = TypedValue.complexToDimensionPixelSize(this.minHeight, paramDisplayMetrics);
    this.minResizeWidth = TypedValue.complexToDimensionPixelSize(this.minResizeWidth, paramDisplayMetrics);
    this.minResizeHeight = TypedValue.complexToDimensionPixelSize(this.minResizeHeight, paramDisplayMetrics);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeTypedObject((Parcelable)this.provider, paramInt);
    paramParcel.writeInt(this.minWidth);
    paramParcel.writeInt(this.minHeight);
    paramParcel.writeInt(this.minResizeWidth);
    paramParcel.writeInt(this.minResizeHeight);
    paramParcel.writeInt(this.updatePeriodMillis);
    paramParcel.writeInt(this.initialLayout);
    paramParcel.writeInt(this.initialKeyguardLayout);
    paramParcel.writeTypedObject((Parcelable)this.configure, paramInt);
    paramParcel.writeString(this.label);
    paramParcel.writeInt(this.icon);
    paramParcel.writeInt(this.previewImage);
    paramParcel.writeInt(this.autoAdvanceViewId);
    paramParcel.writeInt(this.resizeMode);
    paramParcel.writeInt(this.widgetCategory);
    paramParcel.writeTypedObject((Parcelable)this.providerInfo, paramInt);
    paramParcel.writeInt(this.widgetFeatures);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface CategoryFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface FeatureFlags {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ResizeModeFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/appwidget/AppWidgetProviderInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
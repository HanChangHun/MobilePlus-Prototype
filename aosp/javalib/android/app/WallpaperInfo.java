package android.app;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Printer;
import android.util.Xml;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class WallpaperInfo implements Parcelable {
  public static final Parcelable.Creator<WallpaperInfo> CREATOR = new Parcelable.Creator<WallpaperInfo>() {
      public WallpaperInfo createFromParcel(Parcel param1Parcel) {
        return new WallpaperInfo(param1Parcel);
      }
      
      public WallpaperInfo[] newArray(int param1Int) {
        return new WallpaperInfo[param1Int];
      }
    };
  
  static final String TAG = "WallpaperInfo";
  
  final int mAuthorResource;
  
  final int mContextDescriptionResource;
  
  final int mContextUriResource;
  
  final int mDescriptionResource;
  
  final ResolveInfo mService;
  
  final String mSettingsActivityName;
  
  final String mSettingsSliceUri;
  
  final boolean mShowMetadataInPreview;
  
  final boolean mSupportMultipleDisplays;
  
  final boolean mSupportsAmbientMode;
  
  final int mThumbnailResource;
  
  public WallpaperInfo(Context paramContext, ResolveInfo paramResolveInfo) throws XmlPullParserException, IOException {
    XmlResourceParser xmlResourceParser1;
    XmlResourceParser xmlResourceParser2;
    this.mService = paramResolveInfo;
    ServiceInfo serviceInfo = paramResolveInfo.serviceInfo;
    PackageManager packageManager = paramContext.getPackageManager();
    paramResolveInfo = null;
    paramContext = null;
    try {
      XmlResourceParser xmlResourceParser = serviceInfo.loadXmlMetaData(packageManager, "android.service.wallpaper");
      if (xmlResourceParser != null) {
        XmlResourceParser xmlResourceParser3 = xmlResourceParser;
        XmlResourceParser xmlResourceParser4 = xmlResourceParser;
        Resources resources = packageManager.getResourcesForApplication(serviceInfo.applicationInfo);
        xmlResourceParser3 = xmlResourceParser;
        xmlResourceParser4 = xmlResourceParser;
        AttributeSet attributeSet = Xml.asAttributeSet((XmlPullParser)xmlResourceParser);
        while (true) {
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          int i = xmlResourceParser.next();
          if (i != 1 && i != 2)
            continue; 
          break;
        } 
        xmlResourceParser3 = xmlResourceParser;
        xmlResourceParser4 = xmlResourceParser;
        if ("wallpaper".equals(xmlResourceParser.getName())) {
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          TypedArray typedArray = resources.obtainAttributes(attributeSet, R.styleable.Wallpaper);
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          this.mSettingsActivityName = typedArray.getString(1);
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          this.mThumbnailResource = typedArray.getResourceId(2, -1);
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          this.mAuthorResource = typedArray.getResourceId(3, -1);
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          this.mDescriptionResource = typedArray.getResourceId(0, -1);
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          this.mContextUriResource = typedArray.getResourceId(4, -1);
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          this.mContextDescriptionResource = typedArray.getResourceId(5, -1);
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          this.mShowMetadataInPreview = typedArray.getBoolean(6, false);
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          this.mSupportsAmbientMode = typedArray.getBoolean(7, false);
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          this.mSettingsSliceUri = typedArray.getString(8);
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          this.mSupportMultipleDisplays = typedArray.getBoolean(9, false);
          xmlResourceParser3 = xmlResourceParser;
          xmlResourceParser4 = xmlResourceParser;
          typedArray.recycle();
          if (xmlResourceParser != null)
            xmlResourceParser.close(); 
          return;
        } 
        xmlResourceParser3 = xmlResourceParser;
        xmlResourceParser4 = xmlResourceParser;
        XmlPullParserException xmlPullParserException1 = new XmlPullParserException();
        xmlResourceParser3 = xmlResourceParser;
        xmlResourceParser4 = xmlResourceParser;
        this("Meta-data does not start with wallpaper tag");
        xmlResourceParser3 = xmlResourceParser;
        xmlResourceParser4 = xmlResourceParser;
        throw xmlPullParserException1;
      } 
      xmlResourceParser1 = xmlResourceParser;
      xmlResourceParser2 = xmlResourceParser;
      XmlPullParserException xmlPullParserException = new XmlPullParserException();
      xmlResourceParser1 = xmlResourceParser;
      xmlResourceParser2 = xmlResourceParser;
      this("No android.service.wallpaper meta-data");
      xmlResourceParser1 = xmlResourceParser;
      xmlResourceParser2 = xmlResourceParser;
      throw xmlPullParserException;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      xmlResourceParser1 = xmlResourceParser2;
      XmlPullParserException xmlPullParserException = new XmlPullParserException();
      xmlResourceParser1 = xmlResourceParser2;
      StringBuilder stringBuilder = new StringBuilder();
      xmlResourceParser1 = xmlResourceParser2;
      this();
      xmlResourceParser1 = xmlResourceParser2;
      stringBuilder.append("Unable to create context for: ");
      xmlResourceParser1 = xmlResourceParser2;
      stringBuilder.append(serviceInfo.packageName);
      xmlResourceParser1 = xmlResourceParser2;
      this(stringBuilder.toString());
      xmlResourceParser1 = xmlResourceParser2;
      throw xmlPullParserException;
    } finally {}
    if (xmlResourceParser1 != null)
      xmlResourceParser1.close(); 
    throw xmlResourceParser2;
  }
  
  WallpaperInfo(Parcel paramParcel) {
    boolean bool2;
    this.mSettingsActivityName = paramParcel.readString();
    this.mThumbnailResource = paramParcel.readInt();
    this.mAuthorResource = paramParcel.readInt();
    this.mDescriptionResource = paramParcel.readInt();
    this.mContextUriResource = paramParcel.readInt();
    this.mContextDescriptionResource = paramParcel.readInt();
    int i = paramParcel.readInt();
    boolean bool1 = true;
    if (i != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mShowMetadataInPreview = bool2;
    if (paramParcel.readInt() != 0) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mSupportsAmbientMode = bool2;
    this.mSettingsSliceUri = paramParcel.readString();
    if (paramParcel.readInt() != 0) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.mSupportMultipleDisplays = bool2;
    this.mService = (ResolveInfo)ResolveInfo.CREATOR.createFromParcel(paramParcel);
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append(paramString);
    stringBuilder2.append("Service:");
    paramPrinter.println(stringBuilder2.toString());
    ResolveInfo resolveInfo = this.mService;
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append(paramString);
    stringBuilder3.append("  ");
    resolveInfo.dump(paramPrinter, stringBuilder3.toString());
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append(paramString);
    stringBuilder1.append("mSettingsActivityName=");
    stringBuilder1.append(this.mSettingsActivityName);
    paramPrinter.println(stringBuilder1.toString());
  }
  
  public ComponentName getComponent() {
    return new ComponentName(this.mService.serviceInfo.packageName, this.mService.serviceInfo.name);
  }
  
  public String getPackageName() {
    return this.mService.serviceInfo.packageName;
  }
  
  public ServiceInfo getServiceInfo() {
    return this.mService.serviceInfo;
  }
  
  public String getServiceName() {
    return this.mService.serviceInfo.name;
  }
  
  public String getSettingsActivity() {
    return this.mSettingsActivityName;
  }
  
  public Uri getSettingsSliceUri() {
    String str = this.mSettingsSliceUri;
    return (str == null) ? null : Uri.parse(str);
  }
  
  public boolean getShowMetadataInPreview() {
    return this.mShowMetadataInPreview;
  }
  
  public CharSequence loadAuthor(PackageManager paramPackageManager) throws Resources.NotFoundException {
    if (this.mAuthorResource > 0) {
      String str1 = this.mService.resolvePackageName;
      ApplicationInfo applicationInfo = null;
      String str2 = str1;
      if (str1 == null) {
        str2 = this.mService.serviceInfo.packageName;
        applicationInfo = this.mService.serviceInfo.applicationInfo;
      } 
      return paramPackageManager.getText(str2, this.mAuthorResource, applicationInfo);
    } 
    throw new Resources.NotFoundException();
  }
  
  public CharSequence loadContextDescription(PackageManager paramPackageManager) throws Resources.NotFoundException {
    if (this.mContextDescriptionResource > 0) {
      String str1 = this.mService.resolvePackageName;
      ApplicationInfo applicationInfo = null;
      String str2 = str1;
      if (str1 == null) {
        str2 = this.mService.serviceInfo.packageName;
        applicationInfo = this.mService.serviceInfo.applicationInfo;
      } 
      return paramPackageManager.getText(str2, this.mContextDescriptionResource, applicationInfo).toString();
    } 
    throw new Resources.NotFoundException();
  }
  
  public Uri loadContextUri(PackageManager paramPackageManager) throws Resources.NotFoundException {
    if (this.mContextUriResource > 0) {
      String str2 = this.mService.resolvePackageName;
      ApplicationInfo applicationInfo = null;
      String str3 = str2;
      if (str2 == null) {
        str3 = this.mService.serviceInfo.packageName;
        applicationInfo = this.mService.serviceInfo.applicationInfo;
      } 
      String str1 = paramPackageManager.getText(str3, this.mContextUriResource, applicationInfo).toString();
      return (str1 == null) ? null : Uri.parse(str1);
    } 
    throw new Resources.NotFoundException();
  }
  
  public CharSequence loadDescription(PackageManager paramPackageManager) throws Resources.NotFoundException {
    String str1 = this.mService.resolvePackageName;
    ApplicationInfo applicationInfo = null;
    String str2 = str1;
    if (str1 == null) {
      str2 = this.mService.serviceInfo.packageName;
      applicationInfo = this.mService.serviceInfo.applicationInfo;
    } 
    if (this.mService.serviceInfo.descriptionRes != 0)
      return paramPackageManager.getText(str2, this.mService.serviceInfo.descriptionRes, applicationInfo); 
    int i = this.mDescriptionResource;
    if (i > 0)
      return paramPackageManager.getText(str2, i, this.mService.serviceInfo.applicationInfo); 
    throw new Resources.NotFoundException();
  }
  
  public Drawable loadIcon(PackageManager paramPackageManager) {
    return this.mService.loadIcon(paramPackageManager);
  }
  
  public CharSequence loadLabel(PackageManager paramPackageManager) {
    return this.mService.loadLabel(paramPackageManager);
  }
  
  public Drawable loadThumbnail(PackageManager paramPackageManager) {
    return (this.mThumbnailResource < 0) ? null : paramPackageManager.getDrawable(this.mService.serviceInfo.packageName, this.mThumbnailResource, this.mService.serviceInfo.applicationInfo);
  }
  
  @SystemApi
  public boolean supportsAmbientMode() {
    return this.mSupportsAmbientMode;
  }
  
  public boolean supportsMultipleDisplays() {
    return this.mSupportMultipleDisplays;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("WallpaperInfo{");
    stringBuilder.append(this.mService.serviceInfo.name);
    stringBuilder.append(", settings: ");
    stringBuilder.append(this.mSettingsActivityName);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mSettingsActivityName);
    paramParcel.writeInt(this.mThumbnailResource);
    paramParcel.writeInt(this.mAuthorResource);
    paramParcel.writeInt(this.mDescriptionResource);
    paramParcel.writeInt(this.mContextUriResource);
    paramParcel.writeInt(this.mContextDescriptionResource);
    paramParcel.writeInt(this.mShowMetadataInPreview);
    paramParcel.writeInt(this.mSupportsAmbientMode);
    paramParcel.writeString(this.mSettingsSliceUri);
    paramParcel.writeInt(this.mSupportMultipleDisplays);
    this.mService.writeToParcel(paramParcel, paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WallpaperInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
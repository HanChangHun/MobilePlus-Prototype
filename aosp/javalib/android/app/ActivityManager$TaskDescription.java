package android.app;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.text.TextUtils;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

public class TaskDescription implements Parcelable {
  private static final String ATTR_TASKDESCRIPTIONCOLOR_BACKGROUND = "task_description_colorBackground";
  
  private static final String ATTR_TASKDESCRIPTIONCOLOR_PRIMARY = "task_description_color";
  
  private static final String ATTR_TASKDESCRIPTIONICON_FILENAME = "task_description_icon_filename";
  
  private static final String ATTR_TASKDESCRIPTIONICON_RESOURCE = "task_description_icon_resource";
  
  private static final String ATTR_TASKDESCRIPTIONICON_RESOURCE_PACKAGE = "task_description_icon_package";
  
  private static final String ATTR_TASKDESCRIPTIONLABEL = "task_description_label";
  
  public static final String ATTR_TASKDESCRIPTION_PREFIX = "task_description_";
  
  public static final Parcelable.Creator<TaskDescription> CREATOR = new Parcelable.Creator<TaskDescription>() {
      public ActivityManager.TaskDescription createFromParcel(Parcel param2Parcel) {
        return new ActivityManager.TaskDescription(param2Parcel);
      }
      
      public ActivityManager.TaskDescription[] newArray(int param2Int) {
        return new ActivityManager.TaskDescription[param2Int];
      }
    };
  
  private int mColorBackground;
  
  private int mColorPrimary;
  
  private boolean mEnsureNavigationBarContrastWhenTransparent;
  
  private boolean mEnsureStatusBarContrastWhenTransparent;
  
  private Icon mIcon;
  
  private String mIconFilename;
  
  private String mLabel;
  
  private int mMinHeight;
  
  private int mMinWidth;
  
  private int mNavigationBarColor;
  
  private int mResizeMode;
  
  private int mStatusBarColor;
  
  public TaskDescription() {
    this(null, null, 0, 0, 0, 0, false, false, 2, -1, -1);
  }
  
  public TaskDescription(TaskDescription paramTaskDescription) {
    copyFrom(paramTaskDescription);
  }
  
  private TaskDescription(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public TaskDescription(String paramString) {
    this(paramString, null, 0, 0, 0, 0, false, false, 2, -1, -1);
  }
  
  public TaskDescription(String paramString, int paramInt) {
    this(paramString, Icon.createWithResource(ActivityThread.currentPackageName(), paramInt), 0, 0, 0, 0, false, false, 2, -1, -1);
  }
  
  public TaskDescription(String paramString, int paramInt1, int paramInt2) {
    this(paramString, Icon.createWithResource(ActivityThread.currentPackageName(), paramInt1), paramInt2, 0, 0, 0, false, false, 2, -1, -1);
    if (paramInt2 == 0 || Color.alpha(paramInt2) == 255)
      return; 
    throw new RuntimeException("A TaskDescription's primary color should be opaque");
  }
  
  @Deprecated
  public TaskDescription(String paramString, Bitmap paramBitmap) {
    this(paramString, (Icon)paramBitmap, 0, 0, 0, 0, false, false, 2, -1, -1);
  }
  
  @Deprecated
  public TaskDescription(String paramString, Bitmap paramBitmap, int paramInt) {
    this(paramString, (Icon)paramBitmap, paramInt, 0, 0, 0, false, false, 2, -1, -1);
    if (paramInt == 0 || Color.alpha(paramInt) == 255)
      return; 
    throw new RuntimeException("A TaskDescription's primary color should be opaque");
  }
  
  public TaskDescription(String paramString, Icon paramIcon, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, int paramInt5, int paramInt6, int paramInt7) {
    this.mLabel = paramString;
    this.mIcon = paramIcon;
    this.mColorPrimary = paramInt1;
    this.mColorBackground = paramInt2;
    this.mStatusBarColor = paramInt3;
    this.mNavigationBarColor = paramInt4;
    this.mEnsureStatusBarContrastWhenTransparent = paramBoolean1;
    this.mEnsureNavigationBarContrastWhenTransparent = paramBoolean2;
    this.mResizeMode = paramInt5;
    this.mMinWidth = paramInt6;
    this.mMinHeight = paramInt7;
  }
  
  public static boolean equals(TaskDescription paramTaskDescription1, TaskDescription paramTaskDescription2) {
    return (paramTaskDescription1 == null && paramTaskDescription2 == null) ? true : ((paramTaskDescription1 != null && paramTaskDescription2 != null) ? paramTaskDescription1.equals(paramTaskDescription2) : false);
  }
  
  public static Bitmap loadTaskDescriptionIcon(String paramString, int paramInt) {
    if (paramString != null)
      try {
        return ActivityManager.access$000().getTaskDescriptionIcon(paramString, paramInt);
      } catch (RemoteException remoteException) {
        throw remoteException.rethrowFromSystemServer();
      }  
    return null;
  }
  
  public void copyFrom(TaskDescription paramTaskDescription) {
    this.mLabel = paramTaskDescription.mLabel;
    this.mIcon = paramTaskDescription.mIcon;
    this.mIconFilename = paramTaskDescription.mIconFilename;
    this.mColorPrimary = paramTaskDescription.mColorPrimary;
    this.mColorBackground = paramTaskDescription.mColorBackground;
    this.mStatusBarColor = paramTaskDescription.mStatusBarColor;
    this.mNavigationBarColor = paramTaskDescription.mNavigationBarColor;
    this.mEnsureStatusBarContrastWhenTransparent = paramTaskDescription.mEnsureStatusBarContrastWhenTransparent;
    this.mEnsureNavigationBarContrastWhenTransparent = paramTaskDescription.mEnsureNavigationBarContrastWhenTransparent;
    this.mResizeMode = paramTaskDescription.mResizeMode;
    this.mMinWidth = paramTaskDescription.mMinWidth;
    this.mMinHeight = paramTaskDescription.mMinHeight;
  }
  
  public void copyFromPreserveHiddenFields(TaskDescription paramTaskDescription) {
    this.mLabel = paramTaskDescription.mLabel;
    this.mIcon = paramTaskDescription.mIcon;
    this.mIconFilename = paramTaskDescription.mIconFilename;
    this.mColorPrimary = paramTaskDescription.mColorPrimary;
    int i = paramTaskDescription.mColorBackground;
    if (i != 0)
      this.mColorBackground = i; 
    i = paramTaskDescription.mStatusBarColor;
    if (i != 0)
      this.mStatusBarColor = i; 
    i = paramTaskDescription.mNavigationBarColor;
    if (i != 0)
      this.mNavigationBarColor = i; 
    this.mEnsureStatusBarContrastWhenTransparent = paramTaskDescription.mEnsureStatusBarContrastWhenTransparent;
    this.mEnsureNavigationBarContrastWhenTransparent = paramTaskDescription.mEnsureNavigationBarContrastWhenTransparent;
    i = paramTaskDescription.mResizeMode;
    if (i != 2)
      this.mResizeMode = i; 
    i = paramTaskDescription.mMinWidth;
    if (i != -1)
      this.mMinWidth = i; 
    i = paramTaskDescription.mMinHeight;
    if (i != -1)
      this.mMinHeight = i; 
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = paramObject instanceof TaskDescription;
    boolean bool1 = false;
    if (!bool)
      return false; 
    paramObject = paramObject;
    if (TextUtils.equals(this.mLabel, ((TaskDescription)paramObject).mLabel) && TextUtils.equals(this.mIconFilename, ((TaskDescription)paramObject).mIconFilename) && this.mIcon == ((TaskDescription)paramObject).mIcon && this.mColorPrimary == ((TaskDescription)paramObject).mColorPrimary && this.mColorBackground == ((TaskDescription)paramObject).mColorBackground && this.mStatusBarColor == ((TaskDescription)paramObject).mStatusBarColor && this.mNavigationBarColor == ((TaskDescription)paramObject).mNavigationBarColor && this.mEnsureStatusBarContrastWhenTransparent == ((TaskDescription)paramObject).mEnsureStatusBarContrastWhenTransparent && this.mEnsureNavigationBarContrastWhenTransparent == ((TaskDescription)paramObject).mEnsureNavigationBarContrastWhenTransparent && this.mResizeMode == ((TaskDescription)paramObject).mResizeMode && this.mMinWidth == ((TaskDescription)paramObject).mMinWidth && this.mMinHeight == ((TaskDescription)paramObject).mMinHeight)
      bool1 = true; 
    return bool1;
  }
  
  public int getBackgroundColor() {
    return this.mColorBackground;
  }
  
  public boolean getEnsureNavigationBarContrastWhenTransparent() {
    return this.mEnsureNavigationBarContrastWhenTransparent;
  }
  
  public boolean getEnsureStatusBarContrastWhenTransparent() {
    return this.mEnsureStatusBarContrastWhenTransparent;
  }
  
  @Deprecated
  public Bitmap getIcon() {
    Bitmap bitmap = getInMemoryIcon();
    return (bitmap != null) ? bitmap : loadTaskDescriptionIcon(this.mIconFilename, UserHandle.myUserId());
  }
  
  public String getIconFilename() {
    return this.mIconFilename;
  }
  
  public int getIconResource() {
    Icon icon = this.mIcon;
    return (icon != null && icon.getType() == 2) ? this.mIcon.getResId() : 0;
  }
  
  public String getIconResourcePackage() {
    Icon icon = this.mIcon;
    return (icon != null && icon.getType() == 2) ? this.mIcon.getResPackage() : "";
  }
  
  public Bitmap getInMemoryIcon() {
    Icon icon = this.mIcon;
    return (icon != null && icon.getType() == 1) ? this.mIcon.getBitmap() : null;
  }
  
  public String getLabel() {
    return this.mLabel;
  }
  
  public int getMinHeight() {
    return this.mMinHeight;
  }
  
  public int getMinWidth() {
    return this.mMinWidth;
  }
  
  public int getNavigationBarColor() {
    return this.mNavigationBarColor;
  }
  
  public int getPrimaryColor() {
    return this.mColorPrimary;
  }
  
  public Icon getRawIcon() {
    return this.mIcon;
  }
  
  public int getResizeMode() {
    return this.mResizeMode;
  }
  
  public int getStatusBarColor() {
    return this.mStatusBarColor;
  }
  
  public Icon loadIcon() {
    Icon icon = this.mIcon;
    if (icon != null)
      return icon; 
    Bitmap bitmap = loadTaskDescriptionIcon(this.mIconFilename, UserHandle.myUserId());
    return (bitmap != null) ? Icon.createWithBitmap(bitmap) : null;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    int i = paramParcel.readInt();
    String str1 = null;
    if (i > 0) {
      str2 = paramParcel.readString();
    } else {
      str2 = null;
    } 
    this.mLabel = str2;
    if (paramParcel.readInt() > 0)
      this.mIcon = (Icon)Icon.CREATOR.createFromParcel(paramParcel); 
    this.mColorPrimary = paramParcel.readInt();
    this.mColorBackground = paramParcel.readInt();
    this.mStatusBarColor = paramParcel.readInt();
    this.mNavigationBarColor = paramParcel.readInt();
    this.mEnsureStatusBarContrastWhenTransparent = paramParcel.readBoolean();
    this.mEnsureNavigationBarContrastWhenTransparent = paramParcel.readBoolean();
    this.mResizeMode = paramParcel.readInt();
    this.mMinWidth = paramParcel.readInt();
    this.mMinHeight = paramParcel.readInt();
    String str2 = str1;
    if (paramParcel.readInt() > 0)
      str2 = paramParcel.readString(); 
    this.mIconFilename = str2;
  }
  
  public void restoreFromXml(XmlPullParser paramXmlPullParser) {
    String str2 = paramXmlPullParser.getAttributeValue(null, "task_description_label");
    if (str2 != null)
      setLabel(str2); 
    str2 = paramXmlPullParser.getAttributeValue(null, "task_description_color");
    if (str2 != null)
      setPrimaryColor((int)Long.parseLong(str2, 16)); 
    str2 = paramXmlPullParser.getAttributeValue(null, "task_description_colorBackground");
    if (str2 != null)
      setBackgroundColor((int)Long.parseLong(str2, 16)); 
    str2 = paramXmlPullParser.getAttributeValue(null, "task_description_icon_filename");
    if (str2 != null)
      setIconFilename(str2); 
    str2 = paramXmlPullParser.getAttributeValue(null, "task_description_icon_resource");
    String str1 = paramXmlPullParser.getAttributeValue(null, "task_description_icon_package");
    if (str2 != null && str1 != null)
      setIcon(Icon.createWithResource(str1, Integer.parseInt(str2, 10))); 
  }
  
  public void saveToXml(XmlSerializer paramXmlSerializer) throws IOException {
    String str = this.mLabel;
    if (str != null)
      paramXmlSerializer.attribute(null, "task_description_label", str); 
    int i = this.mColorPrimary;
    if (i != 0)
      paramXmlSerializer.attribute(null, "task_description_color", Integer.toHexString(i)); 
    i = this.mColorBackground;
    if (i != 0)
      paramXmlSerializer.attribute(null, "task_description_colorBackground", Integer.toHexString(i)); 
    str = this.mIconFilename;
    if (str != null)
      paramXmlSerializer.attribute(null, "task_description_icon_filename", str); 
    Icon icon = this.mIcon;
    if (icon != null && icon.getType() == 2) {
      paramXmlSerializer.attribute(null, "task_description_icon_resource", Integer.toString(this.mIcon.getResId()));
      paramXmlSerializer.attribute(null, "task_description_icon_package", this.mIcon.getResPackage());
    } 
  }
  
  public void setBackgroundColor(int paramInt) {
    if (paramInt == 0 || Color.alpha(paramInt) == 255) {
      this.mColorBackground = paramInt;
      return;
    } 
    throw new RuntimeException("A TaskDescription's background color should be opaque");
  }
  
  public void setEnsureNavigationBarContrastWhenTransparent(boolean paramBoolean) {
    this.mEnsureNavigationBarContrastWhenTransparent = paramBoolean;
  }
  
  public void setEnsureStatusBarContrastWhenTransparent(boolean paramBoolean) {
    this.mEnsureStatusBarContrastWhenTransparent = paramBoolean;
  }
  
  public void setIcon(Icon paramIcon) {
    this.mIcon = paramIcon;
  }
  
  public void setIconFilename(String paramString) {
    this.mIconFilename = paramString;
    if (paramString != null)
      this.mIcon = null; 
  }
  
  public void setLabel(String paramString) {
    this.mLabel = paramString;
  }
  
  public void setMinHeight(int paramInt) {
    this.mMinHeight = paramInt;
  }
  
  public void setMinWidth(int paramInt) {
    this.mMinWidth = paramInt;
  }
  
  public void setNavigationBarColor(int paramInt) {
    this.mNavigationBarColor = paramInt;
  }
  
  public void setPrimaryColor(int paramInt) {
    if (paramInt == 0 || Color.alpha(paramInt) == 255) {
      this.mColorPrimary = paramInt;
      return;
    } 
    throw new RuntimeException("A TaskDescription's primary color should be opaque");
  }
  
  public void setResizeMode(int paramInt) {
    this.mResizeMode = paramInt;
  }
  
  public void setStatusBarColor(int paramInt) {
    this.mStatusBarColor = paramInt;
  }
  
  public String toString() {
    String str2;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("TaskDescription Label: ");
    stringBuilder.append(this.mLabel);
    stringBuilder.append(" Icon: ");
    stringBuilder.append(this.mIcon);
    stringBuilder.append(" IconFilename: ");
    stringBuilder.append(this.mIconFilename);
    stringBuilder.append(" colorPrimary: ");
    stringBuilder.append(this.mColorPrimary);
    stringBuilder.append(" colorBackground: ");
    stringBuilder.append(this.mColorBackground);
    stringBuilder.append(" statusBarColor: ");
    stringBuilder.append(this.mStatusBarColor);
    boolean bool = this.mEnsureStatusBarContrastWhenTransparent;
    String str1 = " (contrast when transparent)";
    if (bool) {
      str2 = " (contrast when transparent)";
    } else {
      str2 = "";
    } 
    stringBuilder.append(str2);
    stringBuilder.append(" navigationBarColor: ");
    stringBuilder.append(this.mNavigationBarColor);
    if (this.mEnsureNavigationBarContrastWhenTransparent) {
      str2 = str1;
    } else {
      str2 = "";
    } 
    stringBuilder.append(str2);
    stringBuilder.append(" resizeMode: ");
    stringBuilder.append(ActivityInfo.resizeModeToString(this.mResizeMode));
    stringBuilder.append(" minWidth: ");
    stringBuilder.append(this.mMinWidth);
    stringBuilder.append(" minHeight: ");
    stringBuilder.append(this.mMinHeight);
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (this.mLabel == null) {
      paramParcel.writeInt(0);
    } else {
      paramParcel.writeInt(1);
      paramParcel.writeString(this.mLabel);
    } 
    Bitmap bitmap = getInMemoryIcon();
    if (this.mIcon == null || (bitmap != null && bitmap.isRecycled())) {
      paramParcel.writeInt(0);
    } else {
      paramParcel.writeInt(1);
      this.mIcon.writeToParcel(paramParcel, 0);
    } 
    paramParcel.writeInt(this.mColorPrimary);
    paramParcel.writeInt(this.mColorBackground);
    paramParcel.writeInt(this.mStatusBarColor);
    paramParcel.writeInt(this.mNavigationBarColor);
    paramParcel.writeBoolean(this.mEnsureStatusBarContrastWhenTransparent);
    paramParcel.writeBoolean(this.mEnsureNavigationBarContrastWhenTransparent);
    paramParcel.writeInt(this.mResizeMode);
    paramParcel.writeInt(this.mMinWidth);
    paramParcel.writeInt(this.mMinHeight);
    if (this.mIconFilename == null) {
      paramParcel.writeInt(0);
    } else {
      paramParcel.writeInt(1);
      paramParcel.writeString(this.mIconFilename);
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActivityManager$TaskDescription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
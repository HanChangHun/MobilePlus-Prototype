package android.content.pm;

import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Printer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ActivityInfo extends ComponentInfo implements Parcelable {
  public static final int COLOR_MODE_DEFAULT = 0;
  
  public static final int COLOR_MODE_HDR = 2;
  
  public static final int COLOR_MODE_WIDE_COLOR_GAMUT = 1;
  
  public static final int CONFIG_ASSETS_PATHS = -2147483648;
  
  public static final int CONFIG_COLOR_MODE = 16384;
  
  public static final int CONFIG_DENSITY = 4096;
  
  public static final int CONFIG_FONT_SCALE = 1073741824;
  
  public static final int CONFIG_KEYBOARD = 16;
  
  public static final int CONFIG_KEYBOARD_HIDDEN = 32;
  
  public static final int CONFIG_LAYOUT_DIRECTION = 8192;
  
  public static final int CONFIG_LOCALE = 4;
  
  public static final int CONFIG_MCC = 1;
  
  public static final int CONFIG_MNC = 2;
  
  public static int[] CONFIG_NATIVE_BITS = new int[] { 
      2, 1, 4, 8, 16, 32, 64, 128, 2048, 4096, 
      512, 8192, 256, 16384, 65536 };
  
  public static final int CONFIG_NAVIGATION = 64;
  
  public static final int CONFIG_ORIENTATION = 128;
  
  public static final int CONFIG_SCREEN_LAYOUT = 256;
  
  public static final int CONFIG_SCREEN_SIZE = 1024;
  
  public static final int CONFIG_SMALLEST_SCREEN_SIZE = 2048;
  
  public static final int CONFIG_TOUCHSCREEN = 8;
  
  public static final int CONFIG_UI_MODE = 512;
  
  public static final int CONFIG_WINDOW_CONFIGURATION = 536870912;
  
  public static final Parcelable.Creator<ActivityInfo> CREATOR = new Parcelable.Creator<ActivityInfo>() {
      public ActivityInfo createFromParcel(Parcel param1Parcel) {
        return new ActivityInfo(param1Parcel);
      }
      
      public ActivityInfo[] newArray(int param1Int) {
        return new ActivityInfo[param1Int];
      }
    };
  
  public static final int DOCUMENT_LAUNCH_ALWAYS = 2;
  
  public static final int DOCUMENT_LAUNCH_INTO_EXISTING = 1;
  
  public static final int DOCUMENT_LAUNCH_NEVER = 3;
  
  public static final int DOCUMENT_LAUNCH_NONE = 0;
  
  public static final int FLAG_ALLOW_EMBEDDED = -2147483648;
  
  public static final int FLAG_ALLOW_TASK_REPARENTING = 64;
  
  public static final int FLAG_ALWAYS_FOCUSABLE = 262144;
  
  public static final int FLAG_ALWAYS_RETAIN_TASK_STATE = 8;
  
  public static final int FLAG_AUTO_REMOVE_FROM_RECENTS = 8192;
  
  public static final int FLAG_CLEAR_TASK_ON_LAUNCH = 4;
  
  public static final int FLAG_ENABLE_VR_MODE = 32768;
  
  public static final int FLAG_EXCLUDE_FROM_RECENTS = 32;
  
  public static final int FLAG_FINISH_ON_CLOSE_SYSTEM_DIALOGS = 256;
  
  public static final int FLAG_FINISH_ON_TASK_LAUNCH = 2;
  
  public static final int FLAG_HARDWARE_ACCELERATED = 512;
  
  public static final int FLAG_IMMERSIVE = 2048;
  
  public static final int FLAG_IMPLICITLY_VISIBLE_TO_INSTANT_APP = 2097152;
  
  public static final int FLAG_INHERIT_SHOW_WHEN_LOCKED = 1;
  
  public static final int FLAG_MULTIPROCESS = 1;
  
  public static final int FLAG_NO_HISTORY = 128;
  
  public static final int FLAG_PREFER_MINIMAL_POST_PROCESSING = 33554432;
  
  public static final int FLAG_RELINQUISH_TASK_IDENTITY = 4096;
  
  public static final int FLAG_RESUME_WHILE_PAUSING = 16384;
  
  public static final int FLAG_SHOW_FOR_ALL_USERS = 1024;
  
  public static final int FLAG_SHOW_WHEN_LOCKED = 8388608;
  
  public static final int FLAG_SINGLE_USER = 1073741824;
  
  public static final int FLAG_STATE_NOT_NEEDED = 16;
  
  public static final int FLAG_SUPPORTS_PICTURE_IN_PICTURE = 4194304;
  
  public static final int FLAG_SYSTEM_USER_ONLY = 536870912;
  
  public static final int FLAG_TURN_SCREEN_ON = 16777216;
  
  public static final int FLAG_VISIBLE_TO_INSTANT_APP = 1048576;
  
  public static final int LAUNCH_MULTIPLE = 0;
  
  public static final int LAUNCH_SINGLE_INSTANCE = 3;
  
  public static final int LAUNCH_SINGLE_TASK = 2;
  
  public static final int LAUNCH_SINGLE_TOP = 1;
  
  public static final int LOCK_TASK_LAUNCH_MODE_ALWAYS = 2;
  
  public static final int LOCK_TASK_LAUNCH_MODE_DEFAULT = 0;
  
  public static final int LOCK_TASK_LAUNCH_MODE_IF_ALLOWLISTED = 3;
  
  public static final int LOCK_TASK_LAUNCH_MODE_NEVER = 1;
  
  public static final int PERSIST_ACROSS_REBOOTS = 2;
  
  public static final int PERSIST_NEVER = 1;
  
  public static final int PERSIST_ROOT_ONLY = 0;
  
  public static final int RESIZE_MODE_FORCE_RESIZABLE_LANDSCAPE_ONLY = 5;
  
  public static final int RESIZE_MODE_FORCE_RESIZABLE_PORTRAIT_ONLY = 6;
  
  public static final int RESIZE_MODE_FORCE_RESIZABLE_PRESERVE_ORIENTATION = 7;
  
  public static final int RESIZE_MODE_FORCE_RESIZEABLE = 4;
  
  public static final int RESIZE_MODE_RESIZEABLE = 2;
  
  public static final int RESIZE_MODE_RESIZEABLE_AND_PIPABLE_DEPRECATED = 3;
  
  public static final int RESIZE_MODE_RESIZEABLE_VIA_SDK_VERSION = 1;
  
  public static final int RESIZE_MODE_UNRESIZEABLE = 0;
  
  public static final int SCREEN_ORIENTATION_BEHIND = 3;
  
  public static final int SCREEN_ORIENTATION_FULL_SENSOR = 10;
  
  public static final int SCREEN_ORIENTATION_FULL_USER = 13;
  
  public static final int SCREEN_ORIENTATION_LANDSCAPE = 0;
  
  public static final int SCREEN_ORIENTATION_LOCKED = 14;
  
  public static final int SCREEN_ORIENTATION_NOSENSOR = 5;
  
  public static final int SCREEN_ORIENTATION_PORTRAIT = 1;
  
  public static final int SCREEN_ORIENTATION_REVERSE_LANDSCAPE = 8;
  
  public static final int SCREEN_ORIENTATION_REVERSE_PORTRAIT = 9;
  
  public static final int SCREEN_ORIENTATION_SENSOR = 4;
  
  public static final int SCREEN_ORIENTATION_SENSOR_LANDSCAPE = 6;
  
  public static final int SCREEN_ORIENTATION_SENSOR_PORTRAIT = 7;
  
  public static final int SCREEN_ORIENTATION_UNSET = -2;
  
  public static final int SCREEN_ORIENTATION_UNSPECIFIED = -1;
  
  public static final int SCREEN_ORIENTATION_USER = 2;
  
  public static final int SCREEN_ORIENTATION_USER_LANDSCAPE = 11;
  
  public static final int SCREEN_ORIENTATION_USER_PORTRAIT = 12;
  
  public static final int UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW = 1;
  
  public int colorMode = 0;
  
  public int configChanges;
  
  public int documentLaunchMode;
  
  public int flags;
  
  public int launchMode;
  
  public String launchToken;
  
  public int lockTaskLaunchMode;
  
  public float maxAspectRatio;
  
  public int maxRecents;
  
  public float minAspectRatio;
  
  public String parentActivityName;
  
  public String permission;
  
  public int persistableMode;
  
  public int privateFlags;
  
  public String requestedVrComponent;
  
  public int resizeMode = 2;
  
  public int rotationAnimation = -1;
  
  public int screenOrientation = -1;
  
  public int softInputMode;
  
  public boolean supportsSizeChanges;
  
  public String targetActivity;
  
  public String taskAffinity;
  
  public int theme;
  
  public int uiOptions = 0;
  
  public WindowLayout windowLayout;
  
  public ActivityInfo() {}
  
  public ActivityInfo(ActivityInfo paramActivityInfo) {
    super(paramActivityInfo);
    this.theme = paramActivityInfo.theme;
    this.launchMode = paramActivityInfo.launchMode;
    this.documentLaunchMode = paramActivityInfo.documentLaunchMode;
    this.permission = paramActivityInfo.permission;
    this.taskAffinity = paramActivityInfo.taskAffinity;
    this.targetActivity = paramActivityInfo.targetActivity;
    this.flags = paramActivityInfo.flags;
    this.privateFlags = paramActivityInfo.privateFlags;
    this.screenOrientation = paramActivityInfo.screenOrientation;
    this.configChanges = paramActivityInfo.configChanges;
    this.softInputMode = paramActivityInfo.softInputMode;
    this.uiOptions = paramActivityInfo.uiOptions;
    this.parentActivityName = paramActivityInfo.parentActivityName;
    this.maxRecents = paramActivityInfo.maxRecents;
    this.lockTaskLaunchMode = paramActivityInfo.lockTaskLaunchMode;
    this.windowLayout = paramActivityInfo.windowLayout;
    this.resizeMode = paramActivityInfo.resizeMode;
    this.requestedVrComponent = paramActivityInfo.requestedVrComponent;
    this.rotationAnimation = paramActivityInfo.rotationAnimation;
    this.colorMode = paramActivityInfo.colorMode;
    this.maxAspectRatio = paramActivityInfo.maxAspectRatio;
    this.minAspectRatio = paramActivityInfo.minAspectRatio;
    this.supportsSizeChanges = paramActivityInfo.supportsSizeChanges;
  }
  
  private ActivityInfo(Parcel paramParcel) {
    super(paramParcel);
    this.theme = paramParcel.readInt();
    this.launchMode = paramParcel.readInt();
    this.documentLaunchMode = paramParcel.readInt();
    this.permission = paramParcel.readString8();
    this.taskAffinity = paramParcel.readString8();
    this.targetActivity = paramParcel.readString8();
    this.launchToken = paramParcel.readString8();
    this.flags = paramParcel.readInt();
    this.privateFlags = paramParcel.readInt();
    this.screenOrientation = paramParcel.readInt();
    this.configChanges = paramParcel.readInt();
    this.softInputMode = paramParcel.readInt();
    this.uiOptions = paramParcel.readInt();
    this.parentActivityName = paramParcel.readString8();
    this.persistableMode = paramParcel.readInt();
    this.maxRecents = paramParcel.readInt();
    this.lockTaskLaunchMode = paramParcel.readInt();
    if (paramParcel.readInt() == 1)
      this.windowLayout = new WindowLayout(paramParcel); 
    this.resizeMode = paramParcel.readInt();
    this.requestedVrComponent = paramParcel.readString8();
    this.rotationAnimation = paramParcel.readInt();
    this.colorMode = paramParcel.readInt();
    this.maxAspectRatio = paramParcel.readFloat();
    this.minAspectRatio = paramParcel.readFloat();
    this.supportsSizeChanges = paramParcel.readBoolean();
  }
  
  public static int activityInfoConfigJavaToNative(int paramInt) {
    int i = 0;
    byte b = 0;
    while (true) {
      int[] arrayOfInt = CONFIG_NATIVE_BITS;
      if (b < arrayOfInt.length) {
        int j = i;
        if ((1 << b & paramInt) != 0)
          j = i | arrayOfInt[b]; 
        b++;
        i = j;
        continue;
      } 
      return i;
    } 
  }
  
  public static int activityInfoConfigNativeToJava(int paramInt) {
    int i = 0;
    byte b = 0;
    while (true) {
      int[] arrayOfInt = CONFIG_NATIVE_BITS;
      if (b < arrayOfInt.length) {
        int j = i;
        if ((arrayOfInt[b] & paramInt) != 0)
          j = i | 1 << b; 
        b++;
        i = j;
        continue;
      } 
      return i;
    } 
  }
  
  public static String colorModeToString(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? Integer.toString(paramInt) : "COLOR_MODE_HDR") : "COLOR_MODE_WIDE_COLOR_GAMUT") : "COLOR_MODE_DEFAULT";
  }
  
  public static boolean isFixedOrientationLandscape(int paramInt) {
    return (paramInt == 0 || paramInt == 6 || paramInt == 8 || paramInt == 11);
  }
  
  public static boolean isFixedOrientationPortrait(int paramInt) {
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 1) {
      bool2 = bool1;
      if (paramInt != 7) {
        bool2 = bool1;
        if (paramInt != 9)
          if (paramInt == 12) {
            bool2 = bool1;
          } else {
            bool2 = false;
          }  
      } 
    } 
    return bool2;
  }
  
  public static boolean isPreserveOrientationMode(int paramInt) {
    return (paramInt == 6 || paramInt == 5 || paramInt == 7);
  }
  
  public static boolean isResizeableMode(int paramInt) {
    boolean bool1 = true;
    boolean bool2 = bool1;
    if (paramInt != 2) {
      bool2 = bool1;
      if (paramInt != 4) {
        bool2 = bool1;
        if (paramInt != 6) {
          bool2 = bool1;
          if (paramInt != 5) {
            bool2 = bool1;
            if (paramInt != 7)
              if (paramInt == 1) {
                bool2 = bool1;
              } else {
                bool2 = false;
              }  
          } 
        } 
      } 
    } 
    return bool2;
  }
  
  public static boolean isTranslucentOrFloating(TypedArray paramTypedArray) {
    boolean bool = false;
    boolean bool1 = paramTypedArray.getBoolean(5, false);
    if (paramTypedArray.getBoolean(4, false) || bool1)
      bool = true; 
    return bool;
  }
  
  public static final String lockTaskLaunchModeToString(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("unknown=");
            stringBuilder.append(paramInt);
            return stringBuilder.toString();
          } 
          return "LOCK_TASK_LAUNCH_MODE_IF_ALLOWLISTED";
        } 
        return "LOCK_TASK_LAUNCH_MODE_ALWAYS";
      } 
      return "LOCK_TASK_LAUNCH_MODE_NEVER";
    } 
    return "LOCK_TASK_LAUNCH_MODE_DEFAULT";
  }
  
  private String persistableModeToString() {
    int i = this.persistableMode;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("UNKNOWN=");
          stringBuilder.append(this.persistableMode);
          return stringBuilder.toString();
        } 
        return "PERSIST_ACROSS_REBOOTS";
      } 
      return "PERSIST_NEVER";
    } 
    return "PERSIST_ROOT_ONLY";
  }
  
  public static String resizeModeToString(int paramInt) {
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 4) {
            if (paramInt != 5) {
              if (paramInt != 6) {
                if (paramInt != 7) {
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("unknown=");
                  stringBuilder.append(paramInt);
                  return stringBuilder.toString();
                } 
                return "RESIZE_MODE_FORCE_RESIZABLE_PRESERVE_ORIENTATION";
              } 
              return "RESIZE_MODE_FORCE_RESIZABLE_PORTRAIT_ONLY";
            } 
            return "RESIZE_MODE_FORCE_RESIZABLE_LANDSCAPE_ONLY";
          } 
          return "RESIZE_MODE_FORCE_RESIZEABLE";
        } 
        return "RESIZE_MODE_RESIZEABLE";
      } 
      return "RESIZE_MODE_RESIZEABLE_VIA_SDK_VERSION";
    } 
    return "RESIZE_MODE_UNRESIZEABLE";
  }
  
  public static String screenOrientationToString(int paramInt) {
    switch (paramInt) {
      default:
        return Integer.toString(paramInt);
      case 14:
        return "SCREEN_ORIENTATION_LOCKED";
      case 13:
        return "SCREEN_ORIENTATION_FULL_USER";
      case 12:
        return "SCREEN_ORIENTATION_USER_PORTRAIT";
      case 11:
        return "SCREEN_ORIENTATION_USER_LANDSCAPE";
      case 10:
        return "SCREEN_ORIENTATION_FULL_SENSOR";
      case 9:
        return "SCREEN_ORIENTATION_REVERSE_PORTRAIT";
      case 8:
        return "SCREEN_ORIENTATION_REVERSE_LANDSCAPE";
      case 7:
        return "SCREEN_ORIENTATION_SENSOR_PORTRAIT";
      case 6:
        return "SCREEN_ORIENTATION_SENSOR_LANDSCAPE";
      case 5:
        return "SCREEN_ORIENTATION_NOSENSOR";
      case 4:
        return "SCREEN_ORIENTATION_SENSOR";
      case 3:
        return "SCREEN_ORIENTATION_BEHIND";
      case 2:
        return "SCREEN_ORIENTATION_USER";
      case 1:
        return "SCREEN_ORIENTATION_PORTRAIT";
      case 0:
        return "SCREEN_ORIENTATION_LANDSCAPE";
      case -1:
        return "SCREEN_ORIENTATION_UNSPECIFIED";
      case -2:
        break;
    } 
    return "SCREEN_ORIENTATION_UNSET";
  }
  
  public int describeContents() {
    return 0;
  }
  
  public void dump(Printer paramPrinter, String paramString) {
    dump(paramPrinter, paramString, 3);
  }
  
  public void dump(Printer paramPrinter, String paramString, int paramInt) {
    dumpFront(paramPrinter, paramString);
    if (this.permission != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("permission=");
      stringBuilder1.append(this.permission);
      paramPrinter.println(stringBuilder1.toString());
    } 
    if ((paramInt & 0x1) != 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("taskAffinity=");
      stringBuilder1.append(this.taskAffinity);
      stringBuilder1.append(" targetActivity=");
      stringBuilder1.append(this.targetActivity);
      stringBuilder1.append(" persistableMode=");
      stringBuilder1.append(persistableModeToString());
      paramPrinter.println(stringBuilder1.toString());
    } 
    if (this.launchMode != 0 || this.flags != 0 || this.privateFlags != 0 || this.theme != 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("launchMode=");
      stringBuilder1.append(this.launchMode);
      stringBuilder1.append(" flags=0x");
      stringBuilder1.append(Integer.toHexString(this.flags));
      stringBuilder1.append(" privateFlags=0x");
      stringBuilder1.append(Integer.toHexString(this.privateFlags));
      stringBuilder1.append(" theme=0x");
      stringBuilder1.append(Integer.toHexString(this.theme));
      paramPrinter.println(stringBuilder1.toString());
    } 
    if (this.screenOrientation != -1 || this.configChanges != 0 || this.softInputMode != 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("screenOrientation=");
      stringBuilder1.append(this.screenOrientation);
      stringBuilder1.append(" configChanges=0x");
      stringBuilder1.append(Integer.toHexString(this.configChanges));
      stringBuilder1.append(" softInputMode=0x");
      stringBuilder1.append(Integer.toHexString(this.softInputMode));
      paramPrinter.println(stringBuilder1.toString());
    } 
    if (this.uiOptions != 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append(" uiOptions=0x");
      stringBuilder1.append(Integer.toHexString(this.uiOptions));
      paramPrinter.println(stringBuilder1.toString());
    } 
    if ((paramInt & 0x1) != 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("lockTaskLaunchMode=");
      stringBuilder1.append(lockTaskLaunchModeToString(this.lockTaskLaunchMode));
      paramPrinter.println(stringBuilder1.toString());
    } 
    if (this.windowLayout != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("windowLayout=");
      stringBuilder1.append(this.windowLayout.width);
      stringBuilder1.append("|");
      stringBuilder1.append(this.windowLayout.widthFraction);
      stringBuilder1.append(", ");
      stringBuilder1.append(this.windowLayout.height);
      stringBuilder1.append("|");
      stringBuilder1.append(this.windowLayout.heightFraction);
      stringBuilder1.append(", ");
      stringBuilder1.append(this.windowLayout.gravity);
      paramPrinter.println(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("resizeMode=");
    stringBuilder.append(resizeModeToString(this.resizeMode));
    paramPrinter.println(stringBuilder.toString());
    if (this.requestedVrComponent != null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("requestedVrComponent=");
      stringBuilder.append(this.requestedVrComponent);
      paramPrinter.println(stringBuilder.toString());
    } 
    if (this.maxAspectRatio != 0.0F) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("maxAspectRatio=");
      stringBuilder.append(this.maxAspectRatio);
      paramPrinter.println(stringBuilder.toString());
    } 
    if (this.minAspectRatio != 0.0F) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("minAspectRatio=");
      stringBuilder.append(this.minAspectRatio);
      paramPrinter.println(stringBuilder.toString());
    } 
    if (this.supportsSizeChanges) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("supportsSizeChanges=true");
      paramPrinter.println(stringBuilder.toString());
    } 
    dumpBack(paramPrinter, paramString, paramInt);
  }
  
  public int getRealConfigChanged() {
    int i;
    if (this.applicationInfo.targetSdkVersion < 13) {
      i = this.configChanges | 0x400 | 0x800;
    } else {
      i = this.configChanges;
    } 
    return i;
  }
  
  public final int getThemeResource() {
    int i = this.theme;
    if (i == 0)
      i = this.applicationInfo.theme; 
    return i;
  }
  
  public boolean hasFixedAspectRatio() {
    return (this.maxAspectRatio != 0.0F || this.minAspectRatio != 0.0F);
  }
  
  public boolean isFixedOrientation() {
    return (isFixedOrientationLandscape() || isFixedOrientationPortrait() || this.screenOrientation == 14);
  }
  
  boolean isFixedOrientationLandscape() {
    return isFixedOrientationLandscape(this.screenOrientation);
  }
  
  boolean isFixedOrientationPortrait() {
    return isFixedOrientationPortrait(this.screenOrientation);
  }
  
  public boolean supportsPictureInPicture() {
    boolean bool;
    if ((this.flags & 0x400000) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("ActivityInfo{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" ");
    stringBuilder.append(this.name);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeInt(this.theme);
    paramParcel.writeInt(this.launchMode);
    paramParcel.writeInt(this.documentLaunchMode);
    paramParcel.writeString8(this.permission);
    paramParcel.writeString8(this.taskAffinity);
    paramParcel.writeString8(this.targetActivity);
    paramParcel.writeString8(this.launchToken);
    paramParcel.writeInt(this.flags);
    paramParcel.writeInt(this.privateFlags);
    paramParcel.writeInt(this.screenOrientation);
    paramParcel.writeInt(this.configChanges);
    paramParcel.writeInt(this.softInputMode);
    paramParcel.writeInt(this.uiOptions);
    paramParcel.writeString8(this.parentActivityName);
    paramParcel.writeInt(this.persistableMode);
    paramParcel.writeInt(this.maxRecents);
    paramParcel.writeInt(this.lockTaskLaunchMode);
    if (this.windowLayout != null) {
      paramParcel.writeInt(1);
      this.windowLayout.writeToParcel(paramParcel);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeInt(this.resizeMode);
    paramParcel.writeString8(this.requestedVrComponent);
    paramParcel.writeInt(this.rotationAnimation);
    paramParcel.writeInt(this.colorMode);
    paramParcel.writeFloat(this.maxAspectRatio);
    paramParcel.writeFloat(this.minAspectRatio);
    paramParcel.writeBoolean(this.supportsSizeChanges);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ColorMode {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Config {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ScreenOrientation {}
  
  public static final class WindowLayout {
    public final int gravity;
    
    public final int height;
    
    public final float heightFraction;
    
    public final int minHeight;
    
    public final int minWidth;
    
    public final int width;
    
    public final float widthFraction;
    
    public String windowLayoutAffinity;
    
    public WindowLayout(int param1Int1, float param1Float1, int param1Int2, float param1Float2, int param1Int3, int param1Int4, int param1Int5) {
      this.width = param1Int1;
      this.widthFraction = param1Float1;
      this.height = param1Int2;
      this.heightFraction = param1Float2;
      this.gravity = param1Int3;
      this.minWidth = param1Int4;
      this.minHeight = param1Int5;
    }
    
    public WindowLayout(Parcel param1Parcel) {
      this.width = param1Parcel.readInt();
      this.widthFraction = param1Parcel.readFloat();
      this.height = param1Parcel.readInt();
      this.heightFraction = param1Parcel.readFloat();
      this.gravity = param1Parcel.readInt();
      this.minWidth = param1Parcel.readInt();
      this.minHeight = param1Parcel.readInt();
      this.windowLayoutAffinity = param1Parcel.readString8();
    }
    
    public boolean hasSpecifiedSize() {
      return (this.width >= 0 || this.height >= 0 || this.widthFraction >= 0.0F || this.heightFraction >= 0.0F);
    }
    
    public void writeToParcel(Parcel param1Parcel) {
      param1Parcel.writeInt(this.width);
      param1Parcel.writeFloat(this.widthFraction);
      param1Parcel.writeInt(this.height);
      param1Parcel.writeFloat(this.heightFraction);
      param1Parcel.writeInt(this.gravity);
      param1Parcel.writeInt(this.minWidth);
      param1Parcel.writeInt(this.minHeight);
      param1Parcel.writeString8(this.windowLayoutAffinity);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ActivityInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
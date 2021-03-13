package android.app;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.proto.ProtoInputStream;
import android.util.proto.ProtoOutputStream;
import android.util.proto.WireTypeMismatchException;
import android.view.Surface;
import java.io.IOException;

public class WindowConfiguration implements Parcelable, Comparable<WindowConfiguration> {
  public static final int ACTIVITY_TYPE_ASSISTANT = 4;
  
  public static final int ACTIVITY_TYPE_DREAM = 5;
  
  public static final int ACTIVITY_TYPE_HOME = 2;
  
  public static final int ACTIVITY_TYPE_RECENTS = 3;
  
  public static final int ACTIVITY_TYPE_STANDARD = 1;
  
  public static final int ACTIVITY_TYPE_UNDEFINED = 0;
  
  private static final int ALWAYS_ON_TOP_OFF = 2;
  
  private static final int ALWAYS_ON_TOP_ON = 1;
  
  private static final int ALWAYS_ON_TOP_UNDEFINED = 0;
  
  public static final Parcelable.Creator<WindowConfiguration> CREATOR = new Parcelable.Creator<WindowConfiguration>() {
      public WindowConfiguration createFromParcel(Parcel param1Parcel) {
        return new WindowConfiguration(param1Parcel);
      }
      
      public WindowConfiguration[] newArray(int param1Int) {
        return new WindowConfiguration[param1Int];
      }
    };
  
  public static final int PINNED_WINDOWING_MODE_ELEVATION_IN_DIP = 5;
  
  public static final int ROTATION_UNDEFINED = -1;
  
  public static final int WINDOWING_MODE_FREEFORM = 5;
  
  public static final int WINDOWING_MODE_FULLSCREEN = 1;
  
  public static final int WINDOWING_MODE_FULLSCREEN_OR_SPLIT_SCREEN_SECONDARY = 4;
  
  public static final int WINDOWING_MODE_MULTI_WINDOW = 6;
  
  public static final int WINDOWING_MODE_PINNED = 2;
  
  public static final int WINDOWING_MODE_SPLIT_SCREEN_PRIMARY = 3;
  
  public static final int WINDOWING_MODE_SPLIT_SCREEN_SECONDARY = 4;
  
  public static final int WINDOWING_MODE_UNDEFINED = 0;
  
  public static final int WINDOW_CONFIG_ACTIVITY_TYPE = 8;
  
  public static final int WINDOW_CONFIG_ALWAYS_ON_TOP = 16;
  
  public static final int WINDOW_CONFIG_APP_BOUNDS = 2;
  
  public static final int WINDOW_CONFIG_BOUNDS = 1;
  
  public static final int WINDOW_CONFIG_DISPLAY_WINDOWING_MODE = 64;
  
  public static final int WINDOW_CONFIG_ROTATION = 32;
  
  public static final int WINDOW_CONFIG_WINDOWING_MODE = 4;
  
  private int mActivityType;
  
  private int mAlwaysOnTop;
  
  private Rect mAppBounds;
  
  private Rect mBounds = new Rect();
  
  private int mDisplayWindowingMode;
  
  private int mRotation = -1;
  
  private int mWindowingMode;
  
  public WindowConfiguration() {
    unset();
  }
  
  public WindowConfiguration(WindowConfiguration paramWindowConfiguration) {
    setTo(paramWindowConfiguration);
  }
  
  private WindowConfiguration(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public static String activityTypeToString(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? ((paramInt != 4) ? ((paramInt != 5) ? String.valueOf(paramInt) : "dream") : "assistant") : "recents") : "home") : "standard") : "undefined";
  }
  
  public static String alwaysOnTopToString(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? ((paramInt != 2) ? String.valueOf(paramInt) : "off") : "on") : "undefined";
  }
  
  public static boolean inMultiWindowMode(int paramInt) {
    boolean bool = true;
    if (paramInt == 1 || paramInt == 0)
      bool = false; 
    return bool;
  }
  
  public static boolean isFloating(int paramInt) {
    return (paramInt == 5 || paramInt == 2);
  }
  
  public static boolean isSplitScreenWindowingMode(int paramInt) {
    return (paramInt == 3 || paramInt == 4);
  }
  
  private void readFromParcel(Parcel paramParcel) {
    this.mBounds = (Rect)paramParcel.readParcelable(Rect.class.getClassLoader());
    this.mAppBounds = (Rect)paramParcel.readParcelable(Rect.class.getClassLoader());
    this.mWindowingMode = paramParcel.readInt();
    this.mActivityType = paramParcel.readInt();
    this.mAlwaysOnTop = paramParcel.readInt();
    this.mRotation = paramParcel.readInt();
    this.mDisplayWindowingMode = paramParcel.readInt();
  }
  
  private void setAlwaysOnTop(int paramInt) {
    this.mAlwaysOnTop = paramInt;
  }
  
  public static boolean supportSplitScreenWindowingMode(int paramInt) {
    boolean bool;
    if (paramInt != 4 && paramInt != 5) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static String windowingModeToString(int paramInt) {
    switch (paramInt) {
      default:
        return String.valueOf(paramInt);
      case 6:
        return "multi-window";
      case 5:
        return "freeform";
      case 4:
        return "split-screen-secondary";
      case 3:
        return "split-screen-primary";
      case 2:
        return "pinned";
      case 1:
        return "fullscreen";
      case 0:
        break;
    } 
    return "undefined";
  }
  
  public boolean canReceiveKeys() {
    boolean bool;
    if (this.mWindowingMode != 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean canResizeTask() {
    int i = this.mWindowingMode;
    return (i == 5 || i == 6);
  }
  
  public int compareTo(WindowConfiguration paramWindowConfiguration) {
    if (this.mAppBounds == null && paramWindowConfiguration.mAppBounds != null)
      return 1; 
    if (this.mAppBounds != null && paramWindowConfiguration.mAppBounds == null)
      return -1; 
    Rect rect = this.mAppBounds;
    if (rect != null && paramWindowConfiguration.mAppBounds != null) {
      int j = rect.left - paramWindowConfiguration.mAppBounds.left;
      if (j != 0)
        return j; 
      j = this.mAppBounds.top - paramWindowConfiguration.mAppBounds.top;
      if (j != 0)
        return j; 
      j = this.mAppBounds.right - paramWindowConfiguration.mAppBounds.right;
      if (j != 0)
        return j; 
      j = this.mAppBounds.bottom - paramWindowConfiguration.mAppBounds.bottom;
      if (j != 0)
        return j; 
    } 
    int i = this.mBounds.left - paramWindowConfiguration.mBounds.left;
    if (i != 0)
      return i; 
    i = this.mBounds.top - paramWindowConfiguration.mBounds.top;
    if (i != 0)
      return i; 
    i = this.mBounds.right - paramWindowConfiguration.mBounds.right;
    if (i != 0)
      return i; 
    i = this.mBounds.bottom - paramWindowConfiguration.mBounds.bottom;
    if (i != 0)
      return i; 
    i = this.mWindowingMode - paramWindowConfiguration.mWindowingMode;
    if (i != 0)
      return i; 
    i = this.mActivityType - paramWindowConfiguration.mActivityType;
    if (i != 0)
      return i; 
    i = this.mAlwaysOnTop - paramWindowConfiguration.mAlwaysOnTop;
    if (i != 0)
      return i; 
    i = this.mRotation - paramWindowConfiguration.mRotation;
    if (i != 0)
      return i; 
    i = this.mDisplayWindowingMode - paramWindowConfiguration.mDisplayWindowingMode;
    return (i != 0) ? i : i;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public long diff(WindowConfiguration paramWindowConfiguration, boolean paramBoolean) {
    // Byte code:
    //   0: lconst_0
    //   1: lstore_3
    //   2: aload_0
    //   3: getfield mBounds : Landroid/graphics/Rect;
    //   6: aload_1
    //   7: getfield mBounds : Landroid/graphics/Rect;
    //   10: invokevirtual equals : (Ljava/lang/Object;)Z
    //   13: ifne -> 20
    //   16: lconst_0
    //   17: lconst_1
    //   18: lor
    //   19: lstore_3
    //   20: iload_2
    //   21: ifne -> 34
    //   24: lload_3
    //   25: lstore #5
    //   27: aload_1
    //   28: getfield mAppBounds : Landroid/graphics/Rect;
    //   31: ifnull -> 81
    //   34: aload_0
    //   35: getfield mAppBounds : Landroid/graphics/Rect;
    //   38: astore #7
    //   40: aload_1
    //   41: getfield mAppBounds : Landroid/graphics/Rect;
    //   44: astore #8
    //   46: lload_3
    //   47: lstore #5
    //   49: aload #7
    //   51: aload #8
    //   53: if_acmpeq -> 81
    //   56: aload #7
    //   58: ifnull -> 74
    //   61: lload_3
    //   62: lstore #5
    //   64: aload #7
    //   66: aload #8
    //   68: invokevirtual equals : (Ljava/lang/Object;)Z
    //   71: ifne -> 81
    //   74: lload_3
    //   75: ldc2_w 2
    //   78: lor
    //   79: lstore #5
    //   81: iload_2
    //   82: ifne -> 95
    //   85: lload #5
    //   87: lstore_3
    //   88: aload_1
    //   89: getfield mWindowingMode : I
    //   92: ifeq -> 116
    //   95: lload #5
    //   97: lstore_3
    //   98: aload_0
    //   99: getfield mWindowingMode : I
    //   102: aload_1
    //   103: getfield mWindowingMode : I
    //   106: if_icmpeq -> 116
    //   109: lload #5
    //   111: ldc2_w 4
    //   114: lor
    //   115: lstore_3
    //   116: iload_2
    //   117: ifne -> 130
    //   120: lload_3
    //   121: lstore #9
    //   123: aload_1
    //   124: getfield mActivityType : I
    //   127: ifeq -> 151
    //   130: lload_3
    //   131: lstore #9
    //   133: aload_0
    //   134: getfield mActivityType : I
    //   137: aload_1
    //   138: getfield mActivityType : I
    //   141: if_icmpeq -> 151
    //   144: lload_3
    //   145: ldc2_w 8
    //   148: lor
    //   149: lstore #9
    //   151: iload_2
    //   152: ifne -> 166
    //   155: lload #9
    //   157: lstore #5
    //   159: aload_1
    //   160: getfield mAlwaysOnTop : I
    //   163: ifeq -> 189
    //   166: lload #9
    //   168: lstore #5
    //   170: aload_0
    //   171: getfield mAlwaysOnTop : I
    //   174: aload_1
    //   175: getfield mAlwaysOnTop : I
    //   178: if_icmpeq -> 189
    //   181: lload #9
    //   183: ldc2_w 16
    //   186: lor
    //   187: lstore #5
    //   189: iload_2
    //   190: ifne -> 204
    //   193: lload #5
    //   195: lstore_3
    //   196: aload_1
    //   197: getfield mRotation : I
    //   200: iconst_m1
    //   201: if_icmpeq -> 225
    //   204: lload #5
    //   206: lstore_3
    //   207: aload_0
    //   208: getfield mRotation : I
    //   211: aload_1
    //   212: getfield mRotation : I
    //   215: if_icmpeq -> 225
    //   218: lload #5
    //   220: ldc2_w 32
    //   223: lor
    //   224: lstore_3
    //   225: iload_2
    //   226: ifne -> 239
    //   229: lload_3
    //   230: lstore #5
    //   232: aload_1
    //   233: getfield mDisplayWindowingMode : I
    //   236: ifeq -> 260
    //   239: lload_3
    //   240: lstore #5
    //   242: aload_0
    //   243: getfield mDisplayWindowingMode : I
    //   246: aload_1
    //   247: getfield mDisplayWindowingMode : I
    //   250: if_icmpeq -> 260
    //   253: lload_3
    //   254: ldc2_w 64
    //   257: lor
    //   258: lstore #5
    //   260: lload #5
    //   262: lreturn
  }
  
  public void dumpDebug(ProtoOutputStream paramProtoOutputStream, long paramLong) {
    paramLong = paramProtoOutputStream.start(paramLong);
    Rect rect = this.mAppBounds;
    if (rect != null)
      rect.dumpDebug(paramProtoOutputStream, 1146756268033L); 
    paramProtoOutputStream.write(1120986464258L, this.mWindowingMode);
    paramProtoOutputStream.write(1120986464259L, this.mActivityType);
    rect = this.mBounds;
    if (rect != null)
      rect.dumpDebug(paramProtoOutputStream, 1146756268036L); 
    paramProtoOutputStream.end(paramLong);
  }
  
  public boolean equals(Object paramObject) {
    boolean bool = false;
    if (paramObject == null)
      return false; 
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof WindowConfiguration))
      return false; 
    if (compareTo((WindowConfiguration)paramObject) == 0)
      bool = true; 
    return bool;
  }
  
  public int getActivityType() {
    return this.mActivityType;
  }
  
  public Rect getAppBounds() {
    return this.mAppBounds;
  }
  
  public Rect getBounds() {
    return this.mBounds;
  }
  
  public int getDisplayWindowingMode() {
    return this.mDisplayWindowingMode;
  }
  
  public int getRotation() {
    return this.mRotation;
  }
  
  public int getWindowingMode() {
    return this.mWindowingMode;
  }
  
  public boolean hasMovementAnimations() {
    boolean bool;
    if (this.mWindowingMode != 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean hasWindowDecorCaption() {
    int i = this.mActivityType;
    boolean bool = true;
    if (i != 1 || (this.mWindowingMode != 5 && this.mDisplayWindowingMode != 5))
      bool = false; 
    return bool;
  }
  
  public boolean hasWindowShadow() {
    boolean bool;
    if (this.mWindowingMode != 6 && tasksAreFloating()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int hashCode() {
    int i = 0;
    Rect rect = this.mAppBounds;
    if (rect != null)
      i = 0 * 31 + rect.hashCode(); 
    return (((((i * 31 + this.mBounds.hashCode()) * 31 + this.mWindowingMode) * 31 + this.mActivityType) * 31 + this.mAlwaysOnTop) * 31 + this.mRotation) * 31 + this.mDisplayWindowingMode;
  }
  
  public boolean isAlwaysOnTop() {
    int i = this.mWindowingMode;
    boolean bool1 = true;
    if (i == 2)
      return true; 
    if (this.mActivityType == 5)
      return true; 
    if (this.mAlwaysOnTop != 1)
      return false; 
    boolean bool2 = bool1;
    if (i != 5)
      if (i == 6) {
        bool2 = bool1;
      } else {
        bool2 = false;
      }  
    return bool2;
  }
  
  public boolean keepVisibleDeadAppWindowOnScreen() {
    boolean bool;
    if (this.mWindowingMode != 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public boolean persistTaskBounds() {
    boolean bool;
    if (this.mWindowingMode == 5) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void readFromProto(ProtoInputStream paramProtoInputStream, long paramLong) throws IOException, WireTypeMismatchException {
    paramLong = paramProtoInputStream.start(paramLong);
    try {
      while (paramProtoInputStream.nextField() != -1) {
        int i = paramProtoInputStream.getFieldNumber();
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              if (i != 4)
                continue; 
              Rect rect1 = new Rect();
              this();
              this.mBounds = rect1;
              rect1.readFromProto(paramProtoInputStream, 1146756268036L);
              continue;
            } 
            this.mActivityType = paramProtoInputStream.readInt(1120986464259L);
            continue;
          } 
          this.mWindowingMode = paramProtoInputStream.readInt(1120986464258L);
          continue;
        } 
        Rect rect = new Rect();
        this();
        this.mAppBounds = rect;
        rect.readFromProto(paramProtoInputStream, 1146756268033L);
      } 
      return;
    } finally {
      paramProtoInputStream.end(paramLong);
    } 
  }
  
  public void setActivityType(int paramInt) {
    if (this.mActivityType == paramInt)
      return; 
    if (!ActivityThread.isSystem() || this.mActivityType == 0 || paramInt == 0) {
      this.mActivityType = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Can't change activity type once set: ");
    stringBuilder.append(this);
    stringBuilder.append(" activityType=");
    stringBuilder.append(activityTypeToString(paramInt));
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  public void setAlwaysOnTop(boolean paramBoolean) {
    byte b;
    if (paramBoolean) {
      b = 1;
    } else {
      b = 2;
    } 
    this.mAlwaysOnTop = b;
  }
  
  public void setAppBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.mAppBounds == null)
      this.mAppBounds = new Rect(); 
    this.mAppBounds.set(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setAppBounds(Rect paramRect) {
    if (paramRect == null) {
      this.mAppBounds = null;
      return;
    } 
    setAppBounds(paramRect.left, paramRect.top, paramRect.right, paramRect.bottom);
  }
  
  public void setBounds(Rect paramRect) {
    if (paramRect == null) {
      this.mBounds.setEmpty();
      return;
    } 
    this.mBounds.set(paramRect);
  }
  
  public void setDisplayWindowingMode(int paramInt) {
    this.mDisplayWindowingMode = paramInt;
  }
  
  public void setRotation(int paramInt) {
    this.mRotation = paramInt;
  }
  
  public void setTo(WindowConfiguration paramWindowConfiguration) {
    setBounds(paramWindowConfiguration.mBounds);
    setAppBounds(paramWindowConfiguration.mAppBounds);
    setWindowingMode(paramWindowConfiguration.mWindowingMode);
    setActivityType(paramWindowConfiguration.mActivityType);
    setAlwaysOnTop(paramWindowConfiguration.mAlwaysOnTop);
    setRotation(paramWindowConfiguration.mRotation);
    setDisplayWindowingMode(paramWindowConfiguration.mDisplayWindowingMode);
  }
  
  public void setTo(WindowConfiguration paramWindowConfiguration, int paramInt) {
    if ((paramInt & 0x1) != 0)
      setBounds(paramWindowConfiguration.mBounds); 
    if ((paramInt & 0x2) != 0)
      setAppBounds(paramWindowConfiguration.mAppBounds); 
    if ((paramInt & 0x4) != 0)
      setWindowingMode(paramWindowConfiguration.mWindowingMode); 
    if ((paramInt & 0x8) != 0)
      setActivityType(paramWindowConfiguration.mActivityType); 
    if ((paramInt & 0x10) != 0)
      setAlwaysOnTop(paramWindowConfiguration.mAlwaysOnTop); 
    if ((paramInt & 0x20) != 0)
      setRotation(paramWindowConfiguration.mRotation); 
    if ((paramInt & 0x40) != 0)
      setDisplayWindowingMode(paramWindowConfiguration.mDisplayWindowingMode); 
  }
  
  public void setToDefaults() {
    setAppBounds(null);
    setBounds(null);
    setWindowingMode(0);
    setActivityType(0);
    setAlwaysOnTop(0);
    setRotation(-1);
    setDisplayWindowingMode(0);
  }
  
  public void setWindowingMode(int paramInt) {
    this.mWindowingMode = paramInt;
  }
  
  public boolean supportSplitScreenWindowingMode() {
    return supportSplitScreenWindowingMode(this.mActivityType);
  }
  
  public boolean tasksAreFloating() {
    return isFloating(this.mWindowingMode);
  }
  
  public String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("{ mBounds=");
    stringBuilder.append(this.mBounds);
    stringBuilder.append(" mAppBounds=");
    stringBuilder.append(this.mAppBounds);
    stringBuilder.append(" mWindowingMode=");
    stringBuilder.append(windowingModeToString(this.mWindowingMode));
    stringBuilder.append(" mDisplayWindowingMode=");
    stringBuilder.append(windowingModeToString(this.mDisplayWindowingMode));
    stringBuilder.append(" mActivityType=");
    stringBuilder.append(activityTypeToString(this.mActivityType));
    stringBuilder.append(" mAlwaysOnTop=");
    stringBuilder.append(alwaysOnTopToString(this.mAlwaysOnTop));
    stringBuilder.append(" mRotation=");
    int i = this.mRotation;
    if (i == -1) {
      str = "undefined";
    } else {
      str = Surface.rotationToString(i);
    } 
    stringBuilder.append(str);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public void unset() {
    setToDefaults();
  }
  
  public int updateFrom(WindowConfiguration paramWindowConfiguration) {
    int i = 0;
    int j = i;
    if (!paramWindowConfiguration.mBounds.isEmpty()) {
      j = i;
      if (!paramWindowConfiguration.mBounds.equals(this.mBounds)) {
        j = false | true;
        setBounds(paramWindowConfiguration.mBounds);
      } 
    } 
    Rect rect = paramWindowConfiguration.mAppBounds;
    i = j;
    if (rect != null) {
      i = j;
      if (!rect.equals(this.mAppBounds)) {
        i = j | 0x2;
        setAppBounds(paramWindowConfiguration.mAppBounds);
      } 
    } 
    int k = paramWindowConfiguration.mWindowingMode;
    j = i;
    if (k != 0) {
      j = i;
      if (this.mWindowingMode != k) {
        j = i | 0x4;
        setWindowingMode(k);
      } 
    } 
    k = paramWindowConfiguration.mActivityType;
    i = j;
    if (k != 0) {
      i = j;
      if (this.mActivityType != k) {
        i = j | 0x8;
        setActivityType(k);
      } 
    } 
    j = paramWindowConfiguration.mAlwaysOnTop;
    k = i;
    if (j != 0) {
      k = i;
      if (this.mAlwaysOnTop != j) {
        k = i | 0x10;
        setAlwaysOnTop(j);
      } 
    } 
    i = paramWindowConfiguration.mRotation;
    j = k;
    if (i != -1) {
      j = k;
      if (i != this.mRotation) {
        j = k | 0x20;
        setRotation(i);
      } 
    } 
    k = paramWindowConfiguration.mDisplayWindowingMode;
    i = j;
    if (k != 0) {
      i = j;
      if (this.mDisplayWindowingMode != k) {
        i = j | 0x40;
        setDisplayWindowingMode(k);
      } 
    } 
    return i;
  }
  
  public boolean useWindowFrameForBackdrop() {
    int i = this.mWindowingMode;
    return (i == 5 || i == 2);
  }
  
  public boolean windowsAreScaleable() {
    boolean bool;
    if (this.mWindowingMode == 2) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeParcelable((Parcelable)this.mBounds, paramInt);
    paramParcel.writeParcelable((Parcelable)this.mAppBounds, paramInt);
    paramParcel.writeInt(this.mWindowingMode);
    paramParcel.writeInt(this.mActivityType);
    paramParcel.writeInt(this.mAlwaysOnTop);
    paramParcel.writeInt(this.mRotation);
    paramParcel.writeInt(this.mDisplayWindowingMode);
  }
  
  public static @interface ActivityType {}
  
  private static @interface AlwaysOnTop {}
  
  public static @interface WindowConfig {}
  
  public static @interface WindowingMode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/WindowConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app.assist;

import android.annotation.SystemApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PooledStringReader;
import android.os.PooledStringWriter;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.ViewStructure;
import android.view.WindowManagerGlobal;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AssistStructure implements Parcelable {
  public static final Parcelable.Creator<AssistStructure> CREATOR = new Parcelable.Creator<AssistStructure>() {
      public AssistStructure createFromParcel(Parcel param1Parcel) {
        return new AssistStructure(param1Parcel);
      }
      
      public AssistStructure[] newArray(int param1Int) {
        return new AssistStructure[param1Int];
      }
    };
  
  private static final boolean DEBUG_PARCEL = false;
  
  private static final boolean DEBUG_PARCEL_CHILDREN = false;
  
  private static final boolean DEBUG_PARCEL_TREE = false;
  
  private static final String DESCRIPTOR = "android.app.AssistStructure";
  
  private static final String TAG = "AssistStructure";
  
  private static final int TRANSACTION_XFER = 2;
  
  private static final int VALIDATE_VIEW_TOKEN = 572662306;
  
  private static final int VALIDATE_WINDOW_TOKEN = 286331153;
  
  private long mAcquisitionEndTime;
  
  private long mAcquisitionStartTime;
  
  private ComponentName mActivityComponent;
  
  private int mAutofillFlags;
  
  private int mFlags;
  
  private boolean mHaveData;
  
  private boolean mIsHomeActivity;
  
  private final ArrayList<ViewNodeBuilder> mPendingAsyncChildren = new ArrayList<>();
  
  private IBinder mReceiveChannel;
  
  private boolean mSanitizeOnWrite;
  
  private SendChannel mSendChannel;
  
  private int mTaskId;
  
  private Rect mTmpRect = new Rect();
  
  private final ArrayList<WindowNode> mWindowNodes = new ArrayList<>();
  
  public AssistStructure() {
    this.mSanitizeOnWrite = false;
    this.mHaveData = true;
    this.mFlags = 0;
  }
  
  public AssistStructure(Activity paramActivity, boolean paramBoolean, int paramInt) {
    this.mSanitizeOnWrite = false;
    this.mHaveData = true;
    this.mFlags = paramInt;
    ArrayList<ViewRootImpl> arrayList = WindowManagerGlobal.getInstance().getRootViews(paramActivity.getActivityToken());
    for (byte b = 0; b < arrayList.size(); b++) {
      ViewRootImpl viewRootImpl = arrayList.get(b);
      if (viewRootImpl.getView() == null) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Skipping window with dettached view: ");
        stringBuilder.append(viewRootImpl.getTitle());
        Log.w("AssistStructure", stringBuilder.toString());
      } else {
        this.mWindowNodes.add(new WindowNode(this, viewRootImpl, paramBoolean, paramInt));
      } 
    } 
  }
  
  public AssistStructure(Parcel paramParcel) {
    boolean bool = false;
    this.mSanitizeOnWrite = false;
    this.mTaskId = paramParcel.readInt();
    this.mActivityComponent = ComponentName.readFromParcel(paramParcel);
    if (paramParcel.readInt() == 1)
      bool = true; 
    this.mIsHomeActivity = bool;
    this.mReceiveChannel = paramParcel.readStrongBinder();
  }
  
  public void clearSendChannel() {
    SendChannel sendChannel = this.mSendChannel;
    if (sendChannel != null)
      sendChannel.mAssistStructure = null; 
  }
  
  public int describeContents() {
    return 0;
  }
  
  void dump(String paramString, ViewNode paramViewNode, boolean paramBoolean) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramString);
    stringBuilder.append("View [");
    stringBuilder.append(paramViewNode.getLeft());
    stringBuilder.append(",");
    stringBuilder.append(paramViewNode.getTop());
    stringBuilder.append(" ");
    stringBuilder.append(paramViewNode.getWidth());
    stringBuilder.append("x");
    stringBuilder.append(paramViewNode.getHeight());
    stringBuilder.append("] ");
    stringBuilder.append(paramViewNode.getClassName());
    Log.i("AssistStructure", stringBuilder.toString());
    int i = paramViewNode.getId();
    if (i != 0) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("  ID: #");
      stringBuilder1.append(Integer.toHexString(i));
      String str = paramViewNode.getIdEntry();
      if (str != null) {
        String str1 = paramViewNode.getIdType();
        String str2 = paramViewNode.getIdPackage();
        stringBuilder1.append(" ");
        stringBuilder1.append(str2);
        stringBuilder1.append(":");
        stringBuilder1.append(str1);
        stringBuilder1.append("/");
        stringBuilder1.append(str);
      } 
      Log.i("AssistStructure", stringBuilder1.toString());
    } 
    int j = paramViewNode.getScrollX();
    i = paramViewNode.getScrollY();
    if (j != 0 || i != 0) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("  Scroll: ");
      stringBuilder.append(j);
      stringBuilder.append(",");
      stringBuilder.append(i);
      Log.i("AssistStructure", stringBuilder.toString());
    } 
    Matrix matrix = paramViewNode.getTransformation();
    if (matrix != null) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("  Transformation: ");
      stringBuilder.append(matrix);
      Log.i("AssistStructure", stringBuilder.toString());
    } 
    float f = paramViewNode.getElevation();
    if (f != 0.0F) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("  Elevation: ");
      stringBuilder.append(f);
      Log.i("AssistStructure", stringBuilder.toString());
    } 
    if (paramViewNode.getAlpha() != 0.0F) {
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramString);
      stringBuilder.append("  Alpha: ");
      stringBuilder.append(f);
      Log.i("AssistStructure", stringBuilder.toString());
    } 
    CharSequence charSequence1 = paramViewNode.getContentDescription();
    if (charSequence1 != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("  Content description: ");
      stringBuilder1.append(charSequence1);
      Log.i("AssistStructure", stringBuilder1.toString());
    } 
    CharSequence charSequence2 = paramViewNode.getText();
    if (charSequence2 != null) {
      if (paramViewNode.isSanitized() || paramBoolean) {
        charSequence1 = charSequence2.toString();
      } else {
        charSequence1 = new StringBuilder();
        charSequence1.append("REDACTED[");
        charSequence1.append(charSequence2.length());
        charSequence1.append(" chars]");
        charSequence1 = charSequence1.toString();
      } 
      charSequence2 = new StringBuilder();
      charSequence2.append(paramString);
      charSequence2.append("  Text (sel ");
      charSequence2.append(paramViewNode.getTextSelectionStart());
      charSequence2.append("-");
      charSequence2.append(paramViewNode.getTextSelectionEnd());
      charSequence2.append("): ");
      charSequence2.append((String)charSequence1);
      Log.i("AssistStructure", charSequence2.toString());
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append("  Text size: ");
      charSequence1.append(paramViewNode.getTextSize());
      charSequence1.append(" , style: #");
      charSequence1.append(paramViewNode.getTextStyle());
      Log.i("AssistStructure", charSequence1.toString());
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append("  Text color fg: #");
      charSequence1.append(Integer.toHexString(paramViewNode.getTextColor()));
      charSequence1.append(", bg: #");
      charSequence1.append(Integer.toHexString(paramViewNode.getTextBackgroundColor()));
      Log.i("AssistStructure", charSequence1.toString());
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append("  Input type: ");
      charSequence1.append(paramViewNode.getInputType());
      Log.i("AssistStructure", charSequence1.toString());
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append("  Resource id: ");
      charSequence1.append(paramViewNode.getTextIdEntry());
      Log.i("AssistStructure", charSequence1.toString());
    } 
    charSequence1 = paramViewNode.getWebDomain();
    if (charSequence1 != null) {
      charSequence2 = new StringBuilder();
      charSequence2.append(paramString);
      charSequence2.append("  Web domain: ");
      charSequence2.append((String)charSequence1);
      Log.i("AssistStructure", charSequence2.toString());
    } 
    ViewStructure.HtmlInfo htmlInfo = paramViewNode.getHtmlInfo();
    if (htmlInfo != null) {
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append("  HtmlInfo: tag=");
      charSequence1.append(htmlInfo.getTag());
      charSequence1.append(", attr=");
      charSequence1.append(htmlInfo.getAttributes());
      Log.i("AssistStructure", charSequence1.toString());
    } 
    LocaleList localeList = paramViewNode.getLocaleList();
    if (localeList != null) {
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append("  LocaleList: ");
      charSequence1.append(localeList);
      Log.i("AssistStructure", charSequence1.toString());
    } 
    charSequence1 = paramViewNode.getHint();
    if (charSequence1 != null) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString);
      stringBuilder1.append("  Hint: ");
      stringBuilder1.append((String)charSequence1);
      Log.i("AssistStructure", stringBuilder1.toString());
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append("  Resource id: ");
      charSequence1.append(paramViewNode.getHintIdEntry());
      Log.i("AssistStructure", charSequence1.toString());
    } 
    Bundle bundle = paramViewNode.getExtras();
    if (bundle != null) {
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append("  Extras: ");
      charSequence1.append(bundle);
      Log.i("AssistStructure", charSequence1.toString());
    } 
    if (paramViewNode.isAssistBlocked()) {
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append("  BLOCKED");
      Log.i("AssistStructure", charSequence1.toString());
    } 
    AutofillId autofillId = paramViewNode.getAutofillId();
    if (autofillId == null) {
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append(" NO autofill ID");
      Log.i("AssistStructure", charSequence1.toString());
    } else {
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append("  Autofill info: id= ");
      charSequence1.append(autofillId);
      charSequence1.append(", type=");
      charSequence1.append(paramViewNode.getAutofillType());
      charSequence1.append(", options=");
      charSequence1.append(Arrays.toString((Object[])paramViewNode.getAutofillOptions()));
      charSequence1.append(", hints=");
      charSequence1.append(Arrays.toString((Object[])paramViewNode.getAutofillHints()));
      charSequence1.append(", value=");
      charSequence1.append(paramViewNode.getAutofillValue());
      charSequence1.append(", sanitized=");
      charSequence1.append(paramViewNode.isSanitized());
      charSequence1.append(", important=");
      charSequence1.append(paramViewNode.getImportantForAutofill());
      Log.i("AssistStructure", charSequence1.toString());
    } 
    j = paramViewNode.getChildCount();
    if (j > 0) {
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append("  Children:");
      Log.i("AssistStructure", charSequence1.toString());
      charSequence1 = new StringBuilder();
      charSequence1.append(paramString);
      charSequence1.append("    ");
      paramString = charSequence1.toString();
      for (i = 0; i < j; i++)
        dump(paramString, paramViewNode.getChildAt(i), paramBoolean); 
    } 
  }
  
  public void dump(boolean paramBoolean) {
    if (this.mActivityComponent == null) {
      Log.i("AssistStructure", "dump(): calling ensureData() first");
      ensureData();
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Task id: ");
    stringBuilder2.append(this.mTaskId);
    Log.i("AssistStructure", stringBuilder2.toString());
    StringBuilder stringBuilder3 = new StringBuilder();
    stringBuilder3.append("Activity: ");
    ComponentName componentName = this.mActivityComponent;
    if (componentName != null) {
      String str = componentName.flattenToShortString();
    } else {
      componentName = null;
    } 
    stringBuilder3.append((String)componentName);
    Log.i("AssistStructure", stringBuilder3.toString());
    StringBuilder stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Sanitize on write: ");
    stringBuilder1.append(this.mSanitizeOnWrite);
    Log.i("AssistStructure", stringBuilder1.toString());
    stringBuilder1 = new StringBuilder();
    stringBuilder1.append("Flags: ");
    stringBuilder1.append(this.mFlags);
    Log.i("AssistStructure", stringBuilder1.toString());
    int i = getWindowNodeCount();
    for (byte b = 0; b < i; b++) {
      WindowNode windowNode = getWindowNodeAt(b);
      stringBuilder3 = new StringBuilder();
      stringBuilder3.append("Window #");
      stringBuilder3.append(b);
      stringBuilder3.append(" [");
      stringBuilder3.append(windowNode.getLeft());
      stringBuilder3.append(",");
      stringBuilder3.append(windowNode.getTop());
      stringBuilder3.append(" ");
      stringBuilder3.append(windowNode.getWidth());
      stringBuilder3.append("x");
      stringBuilder3.append(windowNode.getHeight());
      stringBuilder3.append("] ");
      stringBuilder3.append(windowNode.getTitle());
      Log.i("AssistStructure", stringBuilder3.toString());
      dump("  ", windowNode.getRootViewNode(), paramBoolean);
    } 
  }
  
  public void ensureData() {
    if (this.mHaveData)
      return; 
    this.mHaveData = true;
    (new ParcelTransferReader(this.mReceiveChannel)).go();
  }
  
  public void ensureDataForAutofill() {
    if (this.mHaveData)
      return; 
    this.mHaveData = true;
    Binder.allowBlocking(this.mReceiveChannel);
    try {
      ParcelTransferReader parcelTransferReader = new ParcelTransferReader();
      this(this, this.mReceiveChannel);
      parcelTransferReader.go();
      return;
    } finally {
      Binder.defaultBlocking(this.mReceiveChannel);
    } 
  }
  
  public long getAcquisitionEndTime() {
    ensureData();
    return this.mAcquisitionEndTime;
  }
  
  public long getAcquisitionStartTime() {
    ensureData();
    return this.mAcquisitionStartTime;
  }
  
  public ComponentName getActivityComponent() {
    return this.mActivityComponent;
  }
  
  public int getFlags() {
    return this.mFlags;
  }
  
  public int getTaskId() {
    return this.mTaskId;
  }
  
  public WindowNode getWindowNodeAt(int paramInt) {
    ensureData();
    return this.mWindowNodes.get(paramInt);
  }
  
  public int getWindowNodeCount() {
    ensureData();
    return this.mWindowNodes.size();
  }
  
  public boolean isHomeActivity() {
    return this.mIsHomeActivity;
  }
  
  public void sanitizeForParceling(boolean paramBoolean) {
    this.mSanitizeOnWrite = paramBoolean;
  }
  
  public void setAcquisitionEndTime(long paramLong) {
    this.mAcquisitionEndTime = paramLong;
  }
  
  public void setAcquisitionStartTime(long paramLong) {
    this.mAcquisitionStartTime = paramLong;
  }
  
  public void setActivityComponent(ComponentName paramComponentName) {
    this.mActivityComponent = paramComponentName;
  }
  
  public void setHomeActivity(boolean paramBoolean) {
    this.mIsHomeActivity = paramBoolean;
  }
  
  public void setTaskId(int paramInt) {
    this.mTaskId = paramInt;
  }
  
  boolean waitForReady() {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: invokestatic uptimeMillis : ()J
    //   7: ldc2_w 5000
    //   10: ladd
    //   11: lstore_2
    //   12: aload_0
    //   13: getfield mPendingAsyncChildren : Ljava/util/ArrayList;
    //   16: invokevirtual size : ()I
    //   19: ifle -> 50
    //   22: invokestatic uptimeMillis : ()J
    //   25: lstore #4
    //   27: lload #4
    //   29: lload_2
    //   30: lcmp
    //   31: ifge -> 50
    //   34: aload_0
    //   35: lload_2
    //   36: lload #4
    //   38: lsub
    //   39: invokevirtual wait : (J)V
    //   42: goto -> 12
    //   45: astore #6
    //   47: goto -> 42
    //   50: aload_0
    //   51: getfield mPendingAsyncChildren : Ljava/util/ArrayList;
    //   54: invokevirtual size : ()I
    //   57: ifle -> 114
    //   60: new java/lang/StringBuilder
    //   63: astore #6
    //   65: aload #6
    //   67: invokespecial <init> : ()V
    //   70: aload #6
    //   72: ldc_w 'Skipping assist structure, waiting too long for async children (have '
    //   75: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: aload #6
    //   81: aload_0
    //   82: getfield mPendingAsyncChildren : Ljava/util/ArrayList;
    //   85: invokevirtual size : ()I
    //   88: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   91: pop
    //   92: aload #6
    //   94: ldc_w ' remaining'
    //   97: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: pop
    //   101: ldc 'AssistStructure'
    //   103: aload #6
    //   105: invokevirtual toString : ()Ljava/lang/String;
    //   108: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   111: pop
    //   112: iconst_1
    //   113: istore_1
    //   114: aload_0
    //   115: monitorexit
    //   116: iload_1
    //   117: iconst_1
    //   118: ixor
    //   119: ireturn
    //   120: astore #6
    //   122: aload_0
    //   123: monitorexit
    //   124: aload #6
    //   126: athrow
    // Exception table:
    //   from	to	target	type
    //   4	12	120	finally
    //   12	27	120	finally
    //   34	42	45	java/lang/InterruptedException
    //   34	42	120	finally
    //   50	112	120	finally
    //   114	116	120	finally
    //   122	124	120	finally
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeInt(this.mTaskId);
    ComponentName.writeToParcel(this.mActivityComponent, paramParcel);
    paramParcel.writeInt(this.mIsHomeActivity);
    if (this.mHaveData) {
      if (this.mSendChannel == null)
        this.mSendChannel = new SendChannel(this); 
      paramParcel.writeStrongBinder((IBinder)this.mSendChannel);
    } else {
      paramParcel.writeStrongBinder(this.mReceiveChannel);
    } 
  }
  
  public static class AutofillOverlay {
    public boolean focused;
    
    public AutofillValue value;
  }
  
  private static final class HtmlInfoNode extends ViewStructure.HtmlInfo implements Parcelable {
    public static final Parcelable.Creator<HtmlInfoNode> CREATOR = new Parcelable.Creator<HtmlInfoNode>() {
        public AssistStructure.HtmlInfoNode createFromParcel(Parcel param2Parcel) {
          AssistStructure.HtmlInfoNodeBuilder htmlInfoNodeBuilder = new AssistStructure.HtmlInfoNodeBuilder(param2Parcel.readString());
          String[] arrayOfString2 = param2Parcel.readStringArray();
          String[] arrayOfString1 = param2Parcel.readStringArray();
          if (arrayOfString2 != null && arrayOfString1 != null)
            if (arrayOfString2.length != arrayOfString1.length) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("HtmlInfo attributes mismatch: names=");
              stringBuilder.append(arrayOfString2.length);
              stringBuilder.append(", values=");
              stringBuilder.append(arrayOfString1.length);
              Log.w("AssistStructure", stringBuilder.toString());
            } else {
              for (byte b = 0; b < arrayOfString2.length; b++)
                htmlInfoNodeBuilder.addAttribute(arrayOfString2[b], arrayOfString1[b]); 
            }  
          return htmlInfoNodeBuilder.build();
        }
        
        public AssistStructure.HtmlInfoNode[] newArray(int param2Int) {
          return new AssistStructure.HtmlInfoNode[param2Int];
        }
      };
    
    private ArrayList<Pair<String, String>> mAttributes;
    
    private final String[] mNames;
    
    private final String mTag;
    
    private final String[] mValues;
    
    private HtmlInfoNode(AssistStructure.HtmlInfoNodeBuilder param1HtmlInfoNodeBuilder) {
      this.mTag = param1HtmlInfoNodeBuilder.mTag;
      if (param1HtmlInfoNodeBuilder.mNames == null) {
        this.mNames = null;
        this.mValues = null;
      } else {
        this.mNames = new String[param1HtmlInfoNodeBuilder.mNames.size()];
        this.mValues = new String[param1HtmlInfoNodeBuilder.mValues.size()];
        param1HtmlInfoNodeBuilder.mNames.toArray((Object[])this.mNames);
        param1HtmlInfoNodeBuilder.mValues.toArray((Object[])this.mValues);
      } 
    }
    
    public int describeContents() {
      return 0;
    }
    
    public List<Pair<String, String>> getAttributes() {
      if (this.mAttributes == null && this.mNames != null) {
        this.mAttributes = new ArrayList<>(this.mNames.length);
        byte b = 0;
        while (true) {
          String[] arrayOfString = this.mNames;
          if (b < arrayOfString.length) {
            Pair<String, String> pair = new Pair(arrayOfString[b], this.mValues[b]);
            this.mAttributes.add(b, pair);
            b++;
            continue;
          } 
          break;
        } 
      } 
      return this.mAttributes;
    }
    
    public String getTag() {
      return this.mTag;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeString(this.mTag);
      param1Parcel.writeStringArray(this.mNames);
      param1Parcel.writeStringArray(this.mValues);
    }
  }
  
  class null implements Parcelable.Creator<HtmlInfoNode> {
    public AssistStructure.HtmlInfoNode createFromParcel(Parcel param1Parcel) {
      AssistStructure.HtmlInfoNodeBuilder htmlInfoNodeBuilder = new AssistStructure.HtmlInfoNodeBuilder(param1Parcel.readString());
      String[] arrayOfString2 = param1Parcel.readStringArray();
      String[] arrayOfString1 = param1Parcel.readStringArray();
      if (arrayOfString2 != null && arrayOfString1 != null)
        if (arrayOfString2.length != arrayOfString1.length) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("HtmlInfo attributes mismatch: names=");
          stringBuilder.append(arrayOfString2.length);
          stringBuilder.append(", values=");
          stringBuilder.append(arrayOfString1.length);
          Log.w("AssistStructure", stringBuilder.toString());
        } else {
          for (byte b = 0; b < arrayOfString2.length; b++)
            htmlInfoNodeBuilder.addAttribute(arrayOfString2[b], arrayOfString1[b]); 
        }  
      return htmlInfoNodeBuilder.build();
    }
    
    public AssistStructure.HtmlInfoNode[] newArray(int param1Int) {
      return new AssistStructure.HtmlInfoNode[param1Int];
    }
  }
  
  private static final class HtmlInfoNodeBuilder extends ViewStructure.HtmlInfo.Builder {
    private ArrayList<String> mNames;
    
    private final String mTag;
    
    private ArrayList<String> mValues;
    
    HtmlInfoNodeBuilder(String param1String) {
      this.mTag = param1String;
    }
    
    public ViewStructure.HtmlInfo.Builder addAttribute(String param1String1, String param1String2) {
      if (this.mNames == null) {
        this.mNames = new ArrayList<>();
        this.mValues = new ArrayList<>();
      } 
      this.mNames.add(param1String1);
      this.mValues.add(param1String2);
      return this;
    }
    
    public AssistStructure.HtmlInfoNode build() {
      return new AssistStructure.HtmlInfoNode(this);
    }
  }
  
  final class ParcelTransferReader {
    private final IBinder mChannel;
    
    private Parcel mCurParcel;
    
    int mNumReadViews;
    
    int mNumReadWindows;
    
    PooledStringReader mStringReader;
    
    final float[] mTmpMatrix = new float[9];
    
    private IBinder mTransferToken;
    
    ParcelTransferReader(IBinder param1IBinder) {
      this.mChannel = param1IBinder;
    }
    
    private void fetchData() {
      Parcel parcel = Parcel.obtain();
      try {
        parcel.writeInterfaceToken("android.app.AssistStructure");
        parcel.writeStrongBinder(this.mTransferToken);
        if (this.mCurParcel != null)
          this.mCurParcel.recycle(); 
        Parcel parcel1 = Parcel.obtain();
        this.mCurParcel = parcel1;
      } finally {
        parcel.recycle();
      } 
    }
    
    void go() {
      fetchData();
      AssistStructure.access$102(AssistStructure.this, this.mCurParcel.readInt());
      AssistStructure.access$202(AssistStructure.this, this.mCurParcel.readInt());
      AssistStructure.access$302(AssistStructure.this, this.mCurParcel.readLong());
      AssistStructure.access$402(AssistStructure.this, this.mCurParcel.readLong());
      int i = this.mCurParcel.readInt();
      if (i > 0) {
        this.mStringReader = new PooledStringReader(this.mCurParcel);
        for (byte b = 0; b < i; b++)
          AssistStructure.this.mWindowNodes.add(new AssistStructure.WindowNode(this)); 
      } 
      this.mCurParcel.recycle();
      this.mCurParcel = null;
    }
    
    Parcel readParcel(int param1Int1, int param1Int2) {
      param1Int2 = this.mCurParcel.readInt();
      if (param1Int2 != 0) {
        if (param1Int2 == param1Int1)
          return this.mCurParcel; 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got token ");
        stringBuilder.append(Integer.toHexString(param1Int2));
        stringBuilder.append(", expected token ");
        stringBuilder.append(Integer.toHexString(param1Int1));
        throw new BadParcelableException(stringBuilder.toString());
      } 
      IBinder iBinder = this.mCurParcel.readStrongBinder();
      this.mTransferToken = iBinder;
      if (iBinder != null) {
        fetchData();
        this.mStringReader = new PooledStringReader(this.mCurParcel);
        this.mCurParcel.readInt();
        return this.mCurParcel;
      } 
      throw new IllegalStateException("Reached end of partial data without transfer token");
    }
  }
  
  static final class ParcelTransferWriter extends Binder {
    AssistStructure.ViewStackEntry mCurViewStackEntry;
    
    int mCurViewStackPos;
    
    int mCurWindow;
    
    int mNumWindows;
    
    int mNumWrittenViews;
    
    int mNumWrittenWindows;
    
    final boolean mSanitizeOnWrite;
    
    final float[] mTmpMatrix = new float[9];
    
    final ArrayList<AssistStructure.ViewStackEntry> mViewStack = new ArrayList<>();
    
    final boolean mWriteStructure;
    
    ParcelTransferWriter(AssistStructure param1AssistStructure, Parcel param1Parcel) {
      this.mSanitizeOnWrite = param1AssistStructure.mSanitizeOnWrite;
      this.mWriteStructure = param1AssistStructure.waitForReady();
      param1Parcel.writeInt(param1AssistStructure.mFlags);
      param1Parcel.writeInt(param1AssistStructure.mAutofillFlags);
      param1Parcel.writeLong(param1AssistStructure.mAcquisitionStartTime);
      param1Parcel.writeLong(param1AssistStructure.mAcquisitionEndTime);
      int i = param1AssistStructure.mWindowNodes.size();
      this.mNumWindows = i;
      if (this.mWriteStructure && i > 0) {
        param1Parcel.writeInt(i);
      } else {
        param1Parcel.writeInt(0);
      } 
    }
    
    void pushViewStackEntry(AssistStructure.ViewNode param1ViewNode, int param1Int) {
      AssistStructure.ViewStackEntry viewStackEntry;
      if (param1Int >= this.mViewStack.size()) {
        viewStackEntry = new AssistStructure.ViewStackEntry();
        this.mViewStack.add(viewStackEntry);
      } else {
        viewStackEntry = this.mViewStack.get(param1Int);
      } 
      viewStackEntry.node = param1ViewNode;
      viewStackEntry.numChildren = param1ViewNode.getChildCount();
      viewStackEntry.curChild = 0;
      this.mCurViewStackEntry = viewStackEntry;
    }
    
    boolean writeNextEntryToParcel(AssistStructure param1AssistStructure, Parcel param1Parcel, PooledStringWriter param1PooledStringWriter) {
      AssistStructure.ViewStackEntry viewStackEntry1;
      AssistStructure.ViewStackEntry viewStackEntry2 = this.mCurViewStackEntry;
      if (viewStackEntry2 != null) {
        if (viewStackEntry2.curChild < this.mCurViewStackEntry.numChildren) {
          AssistStructure.ViewNode viewNode = this.mCurViewStackEntry.node.mChildren[this.mCurViewStackEntry.curChild];
          viewStackEntry1 = this.mCurViewStackEntry;
          viewStackEntry1.curChild++;
          writeView(viewNode, param1Parcel, param1PooledStringWriter, 1);
          return true;
        } 
        do {
          int j = this.mCurViewStackPos - 1;
          this.mCurViewStackPos = j;
          if (j < 0) {
            this.mCurViewStackEntry = null;
            break;
          } 
          viewStackEntry1 = this.mViewStack.get(j);
          this.mCurViewStackEntry = viewStackEntry1;
        } while (viewStackEntry1.curChild >= this.mCurViewStackEntry.numChildren);
        return true;
      } 
      int i = this.mCurWindow;
      if (i < this.mNumWindows) {
        AssistStructure.WindowNode windowNode = ((AssistStructure)viewStackEntry1).mWindowNodes.get(i);
        this.mCurWindow++;
        param1Parcel.writeInt(286331153);
        windowNode.writeSelfToParcel(param1Parcel, param1PooledStringWriter, this.mTmpMatrix);
        this.mNumWrittenWindows++;
        AssistStructure.ViewNode viewNode = windowNode.mRoot;
        this.mCurViewStackPos = 0;
        writeView(viewNode, param1Parcel, param1PooledStringWriter, 0);
        return true;
      } 
      return false;
    }
    
    void writeToParcel(AssistStructure param1AssistStructure, Parcel param1Parcel) {
      String str;
      int i = param1Parcel.dataPosition();
      this.mNumWrittenWindows = 0;
      this.mNumWrittenViews = 0;
      boolean bool = writeToParcelInner(param1AssistStructure, param1Parcel);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Flattened ");
      if (bool) {
        str = "partial";
      } else {
        str = "final";
      } 
      stringBuilder.append(str);
      stringBuilder.append(" assist data: ");
      stringBuilder.append(param1Parcel.dataPosition() - i);
      stringBuilder.append(" bytes, containing ");
      stringBuilder.append(this.mNumWrittenWindows);
      stringBuilder.append(" windows, ");
      stringBuilder.append(this.mNumWrittenViews);
      stringBuilder.append(" views");
      Log.i("AssistStructure", stringBuilder.toString());
    }
    
    boolean writeToParcelInner(AssistStructure param1AssistStructure, Parcel param1Parcel) {
      if (this.mNumWindows == 0)
        return false; 
      PooledStringWriter pooledStringWriter = new PooledStringWriter(param1Parcel);
      while (writeNextEntryToParcel(param1AssistStructure, param1Parcel, pooledStringWriter)) {
        if (param1Parcel.dataSize() > 65536) {
          param1Parcel.writeInt(0);
          param1Parcel.writeStrongBinder((IBinder)this);
          pooledStringWriter.finish();
          return true;
        } 
      } 
      pooledStringWriter.finish();
      this.mViewStack.clear();
      return false;
    }
    
    void writeView(AssistStructure.ViewNode param1ViewNode, Parcel param1Parcel, PooledStringWriter param1PooledStringWriter, int param1Int) {
      param1Parcel.writeInt(572662306);
      param1Int = param1ViewNode.writeSelfToParcel(param1Parcel, param1PooledStringWriter, this.mSanitizeOnWrite, this.mTmpMatrix);
      this.mNumWrittenViews++;
      if ((0x100000 & param1Int) != 0) {
        param1Parcel.writeInt(param1ViewNode.mChildren.length);
        param1Int = this.mCurViewStackPos + 1;
        this.mCurViewStackPos = param1Int;
        pushViewStackEntry(param1ViewNode, param1Int);
      } 
    }
  }
  
  static final class SendChannel extends Binder {
    volatile AssistStructure mAssistStructure;
    
    SendChannel(AssistStructure param1AssistStructure) {
      this.mAssistStructure = param1AssistStructure;
    }
    
    protected boolean onTransact(int param1Int1, Parcel param1Parcel1, Parcel param1Parcel2, int param1Int2) throws RemoteException {
      IBinder iBinder;
      StringBuilder stringBuilder;
      if (param1Int1 == 2) {
        AssistStructure assistStructure = this.mAssistStructure;
        if (assistStructure == null)
          return true; 
        param1Parcel1.enforceInterface("android.app.AssistStructure");
        iBinder = param1Parcel1.readStrongBinder();
        if (iBinder != null) {
          if (iBinder instanceof AssistStructure.ParcelTransferWriter) {
            ((AssistStructure.ParcelTransferWriter)iBinder).writeToParcel(assistStructure, param1Parcel2);
            return true;
          } 
          stringBuilder = new StringBuilder();
          stringBuilder.append("Caller supplied bad token type: ");
          stringBuilder.append(iBinder);
          Log.w("AssistStructure", stringBuilder.toString());
          return true;
        } 
        (new AssistStructure.ParcelTransferWriter(assistStructure, (Parcel)stringBuilder)).writeToParcel(assistStructure, (Parcel)stringBuilder);
        return true;
      } 
      return super.onTransact(param1Int1, (Parcel)iBinder, (Parcel)stringBuilder, param1Int2);
    }
  }
  
  public static class ViewNode {
    static final int AUTOFILL_FLAGS_HAS_AUTOFILL_HINTS = 16;
    
    static final int AUTOFILL_FLAGS_HAS_AUTOFILL_OPTIONS = 32;
    
    static final int AUTOFILL_FLAGS_HAS_AUTOFILL_SESSION_ID = 2048;
    
    static final int AUTOFILL_FLAGS_HAS_AUTOFILL_TYPE = 8;
    
    static final int AUTOFILL_FLAGS_HAS_AUTOFILL_VALUE = 4;
    
    static final int AUTOFILL_FLAGS_HAS_AUTOFILL_VIEW_ID = 1;
    
    static final int AUTOFILL_FLAGS_HAS_AUTOFILL_VIRTUAL_VIEW_ID = 2;
    
    static final int AUTOFILL_FLAGS_HAS_HINT_ID_ENTRY = 4096;
    
    static final int AUTOFILL_FLAGS_HAS_HTML_INFO = 64;
    
    static final int AUTOFILL_FLAGS_HAS_MAX_TEXT_EMS = 512;
    
    static final int AUTOFILL_FLAGS_HAS_MAX_TEXT_LENGTH = 1024;
    
    static final int AUTOFILL_FLAGS_HAS_MIN_TEXT_EMS = 256;
    
    static final int AUTOFILL_FLAGS_HAS_TEXT_ID_ENTRY = 128;
    
    static final int FLAGS_ACCESSIBILITY_FOCUSED = 4096;
    
    static final int FLAGS_ACTIVATED = 8192;
    
    static final int FLAGS_ALL_CONTROL = -1048576;
    
    static final int FLAGS_ASSIST_BLOCKED = 128;
    
    static final int FLAGS_CHECKABLE = 256;
    
    static final int FLAGS_CHECKED = 512;
    
    static final int FLAGS_CLICKABLE = 1024;
    
    static final int FLAGS_CONTEXT_CLICKABLE = 16384;
    
    static final int FLAGS_DISABLED = 1;
    
    static final int FLAGS_FOCUSABLE = 16;
    
    static final int FLAGS_FOCUSED = 32;
    
    static final int FLAGS_HAS_ALPHA = 536870912;
    
    static final int FLAGS_HAS_CHILDREN = 1048576;
    
    static final int FLAGS_HAS_COMPLEX_TEXT = 8388608;
    
    static final int FLAGS_HAS_CONTENT_DESCRIPTION = 33554432;
    
    static final int FLAGS_HAS_ELEVATION = 268435456;
    
    static final int FLAGS_HAS_EXTRAS = 4194304;
    
    static final int FLAGS_HAS_ID = 2097152;
    
    static final int FLAGS_HAS_INPUT_TYPE = 262144;
    
    static final int FLAGS_HAS_LARGE_COORDS = 67108864;
    
    static final int FLAGS_HAS_LOCALE_LIST = 65536;
    
    static final int FLAGS_HAS_MATRIX = 1073741824;
    
    static final int FLAGS_HAS_SCROLL = 134217728;
    
    static final int FLAGS_HAS_TEXT = 16777216;
    
    static final int FLAGS_HAS_URL_DOMAIN = 524288;
    
    static final int FLAGS_HAS_URL_SCHEME = 131072;
    
    static final int FLAGS_LONG_CLICKABLE = 2048;
    
    static final int FLAGS_OPAQUE = 32768;
    
    static final int FLAGS_SELECTED = 64;
    
    static final int FLAGS_VISIBILITY_MASK = 12;
    
    public static final int TEXT_COLOR_UNDEFINED = 1;
    
    public static final int TEXT_STYLE_BOLD = 1;
    
    public static final int TEXT_STYLE_ITALIC = 2;
    
    public static final int TEXT_STYLE_STRIKE_THRU = 8;
    
    public static final int TEXT_STYLE_UNDERLINE = 4;
    
    float mAlpha;
    
    int mAutofillFlags;
    
    String[] mAutofillHints;
    
    AutofillId mAutofillId;
    
    CharSequence[] mAutofillOptions;
    
    AssistStructure.AutofillOverlay mAutofillOverlay;
    
    int mAutofillType;
    
    AutofillValue mAutofillValue;
    
    ViewNode[] mChildren;
    
    String mClassName;
    
    CharSequence mContentDescription;
    
    float mElevation;
    
    Bundle mExtras;
    
    int mFlags;
    
    int mHeight;
    
    String mHintIdEntry;
    
    ViewStructure.HtmlInfo mHtmlInfo;
    
    int mId = -1;
    
    String mIdEntry;
    
    String mIdPackage;
    
    String mIdType;
    
    int mImportantForAutofill;
    
    int mInputType;
    
    LocaleList mLocaleList;
    
    Matrix mMatrix;
    
    int mMaxEms;
    
    int mMaxLength;
    
    int mMinEms;
    
    boolean mSanitized;
    
    int mScrollX;
    
    int mScrollY;
    
    AssistStructure.ViewNodeText mText;
    
    String mTextIdEntry;
    
    String mWebDomain;
    
    String mWebScheme;
    
    int mWidth;
    
    int mX;
    
    int mY;
    
    @SystemApi
    public ViewNode() {
      this.mAutofillType = 0;
      this.mMinEms = -1;
      this.mMaxEms = -1;
      this.mMaxLength = -1;
      this.mAlpha = 1.0F;
    }
    
    ViewNode(AssistStructure.ParcelTransferReader param1ParcelTransferReader, int param1Int) {
      boolean bool = false;
      this.mAutofillType = 0;
      this.mMinEms = -1;
      this.mMaxEms = -1;
      this.mMaxLength = -1;
      this.mAlpha = 1.0F;
      Parcel parcel = param1ParcelTransferReader.readParcel(572662306, param1Int);
      param1ParcelTransferReader.mNumReadViews++;
      PooledStringReader pooledStringReader = param1ParcelTransferReader.mStringReader;
      this.mClassName = pooledStringReader.readString();
      this.mFlags = parcel.readInt();
      int i = this.mFlags;
      this.mAutofillFlags = parcel.readInt();
      int j = this.mAutofillFlags;
      if ((0x200000 & i) != 0) {
        int k = parcel.readInt();
        this.mId = k;
        if (k != -1) {
          String str = pooledStringReader.readString();
          this.mIdEntry = str;
          if (str != null) {
            this.mIdType = pooledStringReader.readString();
            this.mIdPackage = pooledStringReader.readString();
          } 
        } 
      } 
      if (j != 0) {
        boolean bool1;
        if (parcel.readInt() == 1) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        this.mSanitized = bool1;
        this.mImportantForAutofill = parcel.readInt();
        if ((j & 0x1) != 0) {
          int k = parcel.readInt();
          if ((j & 0x2) != 0) {
            this.mAutofillId = new AutofillId(k, parcel.readInt());
          } else {
            this.mAutofillId = new AutofillId(k);
          } 
          if ((j & 0x800) != 0)
            this.mAutofillId.setSessionId(parcel.readInt()); 
        } 
        if ((j & 0x8) != 0)
          this.mAutofillType = parcel.readInt(); 
        if ((j & 0x10) != 0)
          this.mAutofillHints = parcel.readStringArray(); 
        if ((j & 0x4) != 0)
          this.mAutofillValue = (AutofillValue)parcel.readParcelable(null); 
        if ((j & 0x20) != 0)
          this.mAutofillOptions = parcel.readCharSequenceArray(); 
        if ((j & 0x40) != 0)
          this.mHtmlInfo = (ViewStructure.HtmlInfo)parcel.readParcelable(null); 
        if ((j & 0x100) != 0)
          this.mMinEms = parcel.readInt(); 
        if ((j & 0x200) != 0)
          this.mMaxEms = parcel.readInt(); 
        if ((j & 0x400) != 0)
          this.mMaxLength = parcel.readInt(); 
        if ((j & 0x80) != 0)
          this.mTextIdEntry = pooledStringReader.readString(); 
        if ((j & 0x1000) != 0)
          this.mHintIdEntry = pooledStringReader.readString(); 
      } 
      if ((0x4000000 & i) != 0) {
        this.mX = parcel.readInt();
        this.mY = parcel.readInt();
        this.mWidth = parcel.readInt();
        this.mHeight = parcel.readInt();
      } else {
        j = parcel.readInt();
        this.mX = j & 0x7FFF;
        this.mY = j >> 16 & 0x7FFF;
        j = parcel.readInt();
        this.mWidth = j & 0x7FFF;
        this.mHeight = j >> 16 & 0x7FFF;
      } 
      if ((0x8000000 & i) != 0) {
        this.mScrollX = parcel.readInt();
        this.mScrollY = parcel.readInt();
      } 
      if ((0x40000000 & i) != 0) {
        this.mMatrix = new Matrix();
        parcel.readFloatArray(param1ParcelTransferReader.mTmpMatrix);
        this.mMatrix.setValues(param1ParcelTransferReader.mTmpMatrix);
      } 
      if ((0x10000000 & i) != 0)
        this.mElevation = parcel.readFloat(); 
      if ((0x20000000 & i) != 0)
        this.mAlpha = parcel.readFloat(); 
      if ((0x2000000 & i) != 0)
        this.mContentDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel); 
      if ((0x1000000 & i) != 0) {
        boolean bool1 = bool;
        if ((0x800000 & i) == 0)
          bool1 = true; 
        this.mText = new AssistStructure.ViewNodeText(parcel, bool1);
      } 
      if ((0x40000 & i) != 0)
        this.mInputType = parcel.readInt(); 
      if ((0x20000 & i) != 0)
        this.mWebScheme = parcel.readString(); 
      if ((0x80000 & i) != 0)
        this.mWebDomain = parcel.readString(); 
      if ((0x10000 & i) != 0)
        this.mLocaleList = (LocaleList)parcel.readParcelable(null); 
      if ((0x400000 & i) != 0)
        this.mExtras = parcel.readBundle(); 
      if ((0x100000 & i) != 0) {
        j = parcel.readInt();
        this.mChildren = new ViewNode[j];
        for (i = 0; i < j; i++)
          this.mChildren[i] = new ViewNode(param1ParcelTransferReader, param1Int + 1); 
      } 
    }
    
    public float getAlpha() {
      return this.mAlpha;
    }
    
    public String[] getAutofillHints() {
      return this.mAutofillHints;
    }
    
    public AutofillId getAutofillId() {
      return this.mAutofillId;
    }
    
    public CharSequence[] getAutofillOptions() {
      return this.mAutofillOptions;
    }
    
    public int getAutofillType() {
      return this.mAutofillType;
    }
    
    public AutofillValue getAutofillValue() {
      return this.mAutofillValue;
    }
    
    public ViewNode getChildAt(int param1Int) {
      return this.mChildren[param1Int];
    }
    
    public int getChildCount() {
      boolean bool;
      ViewNode[] arrayOfViewNode = this.mChildren;
      if (arrayOfViewNode != null) {
        bool = arrayOfViewNode.length;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public String getClassName() {
      return this.mClassName;
    }
    
    public CharSequence getContentDescription() {
      return this.mContentDescription;
    }
    
    public float getElevation() {
      return this.mElevation;
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public int getHeight() {
      return this.mHeight;
    }
    
    public String getHint() {
      AssistStructure.ViewNodeText viewNodeText = this.mText;
      if (viewNodeText != null) {
        String str = viewNodeText.mHint;
      } else {
        viewNodeText = null;
      } 
      return (String)viewNodeText;
    }
    
    public String getHintIdEntry() {
      return this.mHintIdEntry;
    }
    
    public ViewStructure.HtmlInfo getHtmlInfo() {
      return this.mHtmlInfo;
    }
    
    public int getId() {
      return this.mId;
    }
    
    public String getIdEntry() {
      return this.mIdEntry;
    }
    
    public String getIdPackage() {
      return this.mIdPackage;
    }
    
    public String getIdType() {
      return this.mIdType;
    }
    
    public int getImportantForAutofill() {
      return this.mImportantForAutofill;
    }
    
    public int getInputType() {
      return this.mInputType;
    }
    
    public int getLeft() {
      return this.mX;
    }
    
    public LocaleList getLocaleList() {
      return this.mLocaleList;
    }
    
    public int getMaxTextEms() {
      return this.mMaxEms;
    }
    
    public int getMaxTextLength() {
      return this.mMaxLength;
    }
    
    public int getMinTextEms() {
      return this.mMinEms;
    }
    
    public int getScrollX() {
      return this.mScrollX;
    }
    
    public int getScrollY() {
      return this.mScrollY;
    }
    
    public CharSequence getText() {
      AssistStructure.ViewNodeText viewNodeText = this.mText;
      if (viewNodeText != null) {
        CharSequence charSequence = viewNodeText.mText;
      } else {
        viewNodeText = null;
      } 
      return (CharSequence)viewNodeText;
    }
    
    public int getTextBackgroundColor() {
      boolean bool;
      AssistStructure.ViewNodeText viewNodeText = this.mText;
      if (viewNodeText != null) {
        bool = viewNodeText.mTextBackgroundColor;
      } else {
        bool = true;
      } 
      return bool;
    }
    
    public int getTextColor() {
      boolean bool;
      AssistStructure.ViewNodeText viewNodeText = this.mText;
      if (viewNodeText != null) {
        bool = viewNodeText.mTextColor;
      } else {
        bool = true;
      } 
      return bool;
    }
    
    public String getTextIdEntry() {
      return this.mTextIdEntry;
    }
    
    public int[] getTextLineBaselines() {
      AssistStructure.ViewNodeText viewNodeText = this.mText;
      if (viewNodeText != null) {
        int[] arrayOfInt = viewNodeText.mLineBaselines;
      } else {
        viewNodeText = null;
      } 
      return (int[])viewNodeText;
    }
    
    public int[] getTextLineCharOffsets() {
      AssistStructure.ViewNodeText viewNodeText = this.mText;
      if (viewNodeText != null) {
        int[] arrayOfInt = viewNodeText.mLineCharOffsets;
      } else {
        viewNodeText = null;
      } 
      return (int[])viewNodeText;
    }
    
    public int getTextSelectionEnd() {
      byte b;
      AssistStructure.ViewNodeText viewNodeText = this.mText;
      if (viewNodeText != null) {
        b = viewNodeText.mTextSelectionEnd;
      } else {
        b = -1;
      } 
      return b;
    }
    
    public int getTextSelectionStart() {
      byte b;
      AssistStructure.ViewNodeText viewNodeText = this.mText;
      if (viewNodeText != null) {
        b = viewNodeText.mTextSelectionStart;
      } else {
        b = -1;
      } 
      return b;
    }
    
    public float getTextSize() {
      float f;
      AssistStructure.ViewNodeText viewNodeText = this.mText;
      if (viewNodeText != null) {
        f = viewNodeText.mTextSize;
      } else {
        f = 0.0F;
      } 
      return f;
    }
    
    public int getTextStyle() {
      boolean bool;
      AssistStructure.ViewNodeText viewNodeText = this.mText;
      if (viewNodeText != null) {
        bool = viewNodeText.mTextStyle;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public int getTop() {
      return this.mY;
    }
    
    public Matrix getTransformation() {
      return this.mMatrix;
    }
    
    public int getVisibility() {
      return this.mFlags & 0xC;
    }
    
    public String getWebDomain() {
      return this.mWebDomain;
    }
    
    public String getWebScheme() {
      return this.mWebScheme;
    }
    
    public int getWidth() {
      return this.mWidth;
    }
    
    public boolean isAccessibilityFocused() {
      boolean bool;
      if ((this.mFlags & 0x1000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isActivated() {
      boolean bool;
      if ((this.mFlags & 0x2000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isAssistBlocked() {
      boolean bool;
      if ((this.mFlags & 0x80) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isCheckable() {
      boolean bool;
      if ((this.mFlags & 0x100) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isChecked() {
      boolean bool;
      if ((this.mFlags & 0x200) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isClickable() {
      boolean bool;
      if ((this.mFlags & 0x400) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isContextClickable() {
      boolean bool;
      if ((this.mFlags & 0x4000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isEnabled() {
      int i = this.mFlags;
      boolean bool = true;
      if ((i & 0x1) != 0)
        bool = false; 
      return bool;
    }
    
    public boolean isFocusable() {
      boolean bool;
      if ((this.mFlags & 0x10) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isFocused() {
      boolean bool;
      if ((this.mFlags & 0x20) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isLongClickable() {
      boolean bool;
      if ((this.mFlags & 0x800) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isOpaque() {
      boolean bool;
      if ((this.mFlags & 0x8000) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isSanitized() {
      return this.mSanitized;
    }
    
    public boolean isSelected() {
      boolean bool;
      if ((this.mFlags & 0x40) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public void setAutofillOverlay(AssistStructure.AutofillOverlay param1AutofillOverlay) {
      this.mAutofillOverlay = param1AutofillOverlay;
    }
    
    public void setWebDomain(String param1String) {
      if (param1String == null)
        return; 
      Uri uri = Uri.parse(param1String);
      if (uri == null) {
        Log.w("AssistStructure", "Failed to parse web domain");
        return;
      } 
      String str = uri.getScheme();
      this.mWebScheme = str;
      if (str == null) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://");
        stringBuilder.append(param1String);
        uri = Uri.parse(stringBuilder.toString());
      } 
      this.mWebDomain = uri.getHost();
    }
    
    public void updateAutofillValue(AutofillValue param1AutofillValue) {
      this.mAutofillValue = param1AutofillValue;
      if (param1AutofillValue.isText()) {
        if (this.mText == null)
          this.mText = new AssistStructure.ViewNodeText(); 
        this.mText.mText = param1AutofillValue.getTextValue();
      } 
    }
    
    int writeSelfToParcel(Parcel param1Parcel, PooledStringWriter param1PooledStringWriter, boolean param1Boolean, float[] param1ArrayOffloat) {
      // Byte code:
      //   0: iconst_1
      //   1: istore #5
      //   3: aload_0
      //   4: getfield mFlags : I
      //   7: ldc_w 1048575
      //   10: iand
      //   11: istore #6
      //   13: iconst_0
      //   14: istore #7
      //   16: iload #6
      //   18: istore #8
      //   20: aload_0
      //   21: getfield mId : I
      //   24: iconst_m1
      //   25: if_icmpeq -> 35
      //   28: iload #6
      //   30: ldc 2097152
      //   32: ior
      //   33: istore #8
      //   35: aload_0
      //   36: getfield mX : I
      //   39: sipush #-32768
      //   42: iand
      //   43: ifne -> 109
      //   46: aload_0
      //   47: getfield mY : I
      //   50: sipush #-32768
      //   53: iand
      //   54: ifne -> 109
      //   57: aload_0
      //   58: getfield mWidth : I
      //   61: sipush #-32768
      //   64: iand
      //   65: ifeq -> 74
      //   68: iconst_1
      //   69: istore #9
      //   71: goto -> 77
      //   74: iconst_0
      //   75: istore #9
      //   77: aload_0
      //   78: getfield mHeight : I
      //   81: sipush #-32768
      //   84: iand
      //   85: ifeq -> 94
      //   88: iconst_1
      //   89: istore #10
      //   91: goto -> 97
      //   94: iconst_0
      //   95: istore #10
      //   97: iload #8
      //   99: istore #6
      //   101: iload #9
      //   103: iload #10
      //   105: ior
      //   106: ifeq -> 116
      //   109: iload #8
      //   111: ldc 67108864
      //   113: ior
      //   114: istore #6
      //   116: aload_0
      //   117: getfield mScrollX : I
      //   120: ifne -> 134
      //   123: iload #6
      //   125: istore #8
      //   127: aload_0
      //   128: getfield mScrollY : I
      //   131: ifeq -> 141
      //   134: iload #6
      //   136: ldc 134217728
      //   138: ior
      //   139: istore #8
      //   141: iload #8
      //   143: istore #6
      //   145: aload_0
      //   146: getfield mMatrix : Landroid/graphics/Matrix;
      //   149: ifnull -> 159
      //   152: iload #8
      //   154: ldc 1073741824
      //   156: ior
      //   157: istore #6
      //   159: iload #6
      //   161: istore #8
      //   163: aload_0
      //   164: getfield mElevation : F
      //   167: fconst_0
      //   168: fcmpl
      //   169: ifeq -> 179
      //   172: iload #6
      //   174: ldc 268435456
      //   176: ior
      //   177: istore #8
      //   179: iload #8
      //   181: istore #9
      //   183: aload_0
      //   184: getfield mAlpha : F
      //   187: fconst_1
      //   188: fcmpl
      //   189: ifeq -> 199
      //   192: iload #8
      //   194: ldc 536870912
      //   196: ior
      //   197: istore #9
      //   199: iload #9
      //   201: istore #6
      //   203: aload_0
      //   204: getfield mContentDescription : Ljava/lang/CharSequence;
      //   207: ifnull -> 217
      //   210: iload #9
      //   212: ldc 33554432
      //   214: ior
      //   215: istore #6
      //   217: aload_0
      //   218: getfield mText : Landroid/app/assist/AssistStructure$ViewNodeText;
      //   221: astore #11
      //   223: iload #6
      //   225: istore #8
      //   227: aload #11
      //   229: ifnull -> 258
      //   232: iload #6
      //   234: ldc 16777216
      //   236: ior
      //   237: istore #6
      //   239: iload #6
      //   241: istore #8
      //   243: aload #11
      //   245: invokevirtual isSimple : ()Z
      //   248: ifne -> 258
      //   251: iload #6
      //   253: ldc 8388608
      //   255: ior
      //   256: istore #8
      //   258: iload #8
      //   260: istore #9
      //   262: aload_0
      //   263: getfield mInputType : I
      //   266: ifeq -> 276
      //   269: iload #8
      //   271: ldc 262144
      //   273: ior
      //   274: istore #9
      //   276: iload #9
      //   278: istore #6
      //   280: aload_0
      //   281: getfield mWebScheme : Ljava/lang/String;
      //   284: ifnull -> 294
      //   287: iload #9
      //   289: ldc 131072
      //   291: ior
      //   292: istore #6
      //   294: iload #6
      //   296: istore #8
      //   298: aload_0
      //   299: getfield mWebDomain : Ljava/lang/String;
      //   302: ifnull -> 312
      //   305: iload #6
      //   307: ldc 524288
      //   309: ior
      //   310: istore #8
      //   312: iload #8
      //   314: istore #6
      //   316: aload_0
      //   317: getfield mLocaleList : Landroid/os/LocaleList;
      //   320: ifnull -> 330
      //   323: iload #8
      //   325: ldc 65536
      //   327: ior
      //   328: istore #6
      //   330: iload #6
      //   332: istore #8
      //   334: aload_0
      //   335: getfield mExtras : Landroid/os/Bundle;
      //   338: ifnull -> 348
      //   341: iload #6
      //   343: ldc 4194304
      //   345: ior
      //   346: istore #8
      //   348: iload #8
      //   350: istore #6
      //   352: aload_0
      //   353: getfield mChildren : [Landroid/app/assist/AssistStructure$ViewNode;
      //   356: ifnull -> 366
      //   359: iload #8
      //   361: ldc 1048576
      //   363: ior
      //   364: istore #6
      //   366: aload_0
      //   367: getfield mAutofillId : Landroid/view/autofill/AutofillId;
      //   370: astore #11
      //   372: iload #7
      //   374: istore #8
      //   376: aload #11
      //   378: ifnull -> 426
      //   381: iconst_0
      //   382: iconst_1
      //   383: ior
      //   384: istore #8
      //   386: iload #8
      //   388: istore #9
      //   390: aload #11
      //   392: invokevirtual isVirtualInt : ()Z
      //   395: ifeq -> 404
      //   398: iload #8
      //   400: iconst_2
      //   401: ior
      //   402: istore #9
      //   404: iload #9
      //   406: istore #8
      //   408: aload_0
      //   409: getfield mAutofillId : Landroid/view/autofill/AutofillId;
      //   412: invokevirtual hasSession : ()Z
      //   415: ifeq -> 426
      //   418: iload #9
      //   420: sipush #2048
      //   423: ior
      //   424: istore #8
      //   426: iload #8
      //   428: istore #9
      //   430: aload_0
      //   431: getfield mAutofillValue : Landroid/view/autofill/AutofillValue;
      //   434: ifnull -> 443
      //   437: iload #8
      //   439: iconst_4
      //   440: ior
      //   441: istore #9
      //   443: iload #9
      //   445: istore #8
      //   447: aload_0
      //   448: getfield mAutofillType : I
      //   451: ifeq -> 461
      //   454: iload #9
      //   456: bipush #8
      //   458: ior
      //   459: istore #8
      //   461: iload #8
      //   463: istore #9
      //   465: aload_0
      //   466: getfield mAutofillHints : [Ljava/lang/String;
      //   469: ifnull -> 479
      //   472: iload #8
      //   474: bipush #16
      //   476: ior
      //   477: istore #9
      //   479: iload #9
      //   481: istore #10
      //   483: aload_0
      //   484: getfield mAutofillOptions : [Ljava/lang/CharSequence;
      //   487: ifnull -> 497
      //   490: iload #9
      //   492: bipush #32
      //   494: ior
      //   495: istore #10
      //   497: iload #10
      //   499: istore #8
      //   501: aload_0
      //   502: getfield mHtmlInfo : Landroid/view/ViewStructure$HtmlInfo;
      //   505: instanceof android/os/Parcelable
      //   508: ifeq -> 518
      //   511: iload #10
      //   513: bipush #64
      //   515: ior
      //   516: istore #8
      //   518: iload #8
      //   520: istore #9
      //   522: aload_0
      //   523: getfield mMinEms : I
      //   526: iconst_m1
      //   527: if_icmple -> 538
      //   530: iload #8
      //   532: sipush #256
      //   535: ior
      //   536: istore #9
      //   538: iload #9
      //   540: istore #10
      //   542: aload_0
      //   543: getfield mMaxEms : I
      //   546: iconst_m1
      //   547: if_icmple -> 558
      //   550: iload #9
      //   552: sipush #512
      //   555: ior
      //   556: istore #10
      //   558: iload #10
      //   560: istore #8
      //   562: aload_0
      //   563: getfield mMaxLength : I
      //   566: iconst_m1
      //   567: if_icmple -> 578
      //   570: iload #10
      //   572: sipush #1024
      //   575: ior
      //   576: istore #8
      //   578: iload #8
      //   580: istore #9
      //   582: aload_0
      //   583: getfield mTextIdEntry : Ljava/lang/String;
      //   586: ifnull -> 597
      //   589: iload #8
      //   591: sipush #128
      //   594: ior
      //   595: istore #9
      //   597: iload #9
      //   599: istore #10
      //   601: aload_0
      //   602: getfield mHintIdEntry : Ljava/lang/String;
      //   605: ifnull -> 616
      //   608: iload #9
      //   610: sipush #4096
      //   613: ior
      //   614: istore #10
      //   616: aload_2
      //   617: aload_0
      //   618: getfield mClassName : Ljava/lang/String;
      //   621: invokevirtual writeString : (Ljava/lang/String;)V
      //   624: iload #6
      //   626: istore #9
      //   628: iload #9
      //   630: istore #8
      //   632: iload #10
      //   634: ifeq -> 660
      //   637: aload_0
      //   638: getfield mSanitized : Z
      //   641: ifne -> 652
      //   644: iload #9
      //   646: istore #8
      //   648: iload_3
      //   649: ifne -> 660
      //   652: iload #6
      //   654: sipush #-513
      //   657: iand
      //   658: istore #8
      //   660: aload_0
      //   661: getfield mAutofillOverlay : Landroid/app/assist/AssistStructure$AutofillOverlay;
      //   664: astore #11
      //   666: iload #8
      //   668: istore #9
      //   670: aload #11
      //   672: ifnull -> 700
      //   675: aload #11
      //   677: getfield focused : Z
      //   680: ifeq -> 693
      //   683: iload #8
      //   685: bipush #32
      //   687: ior
      //   688: istore #9
      //   690: goto -> 700
      //   693: iload #8
      //   695: bipush #-33
      //   697: iand
      //   698: istore #9
      //   700: aload_1
      //   701: iload #9
      //   703: invokevirtual writeInt : (I)V
      //   706: aload_1
      //   707: iload #10
      //   709: invokevirtual writeInt : (I)V
      //   712: ldc 2097152
      //   714: iload #6
      //   716: iand
      //   717: ifeq -> 767
      //   720: aload_1
      //   721: aload_0
      //   722: getfield mId : I
      //   725: invokevirtual writeInt : (I)V
      //   728: aload_0
      //   729: getfield mId : I
      //   732: iconst_m1
      //   733: if_icmpeq -> 767
      //   736: aload_2
      //   737: aload_0
      //   738: getfield mIdEntry : Ljava/lang/String;
      //   741: invokevirtual writeString : (Ljava/lang/String;)V
      //   744: aload_0
      //   745: getfield mIdEntry : Ljava/lang/String;
      //   748: ifnull -> 767
      //   751: aload_2
      //   752: aload_0
      //   753: getfield mIdType : Ljava/lang/String;
      //   756: invokevirtual writeString : (Ljava/lang/String;)V
      //   759: aload_2
      //   760: aload_0
      //   761: getfield mIdPackage : Ljava/lang/String;
      //   764: invokevirtual writeString : (Ljava/lang/String;)V
      //   767: iload #10
      //   769: ifeq -> 1085
      //   772: aload_1
      //   773: aload_0
      //   774: getfield mSanitized : Z
      //   777: invokevirtual writeInt : (I)V
      //   780: aload_1
      //   781: aload_0
      //   782: getfield mImportantForAutofill : I
      //   785: invokevirtual writeInt : (I)V
      //   788: aload_0
      //   789: getfield mSanitized : Z
      //   792: ifne -> 807
      //   795: iload_3
      //   796: ifne -> 802
      //   799: goto -> 807
      //   802: iconst_0
      //   803: istore_3
      //   804: goto -> 809
      //   807: iconst_1
      //   808: istore_3
      //   809: iload #10
      //   811: iconst_1
      //   812: iand
      //   813: ifeq -> 865
      //   816: aload_1
      //   817: aload_0
      //   818: getfield mAutofillId : Landroid/view/autofill/AutofillId;
      //   821: invokevirtual getViewId : ()I
      //   824: invokevirtual writeInt : (I)V
      //   827: iload #10
      //   829: iconst_2
      //   830: iand
      //   831: ifeq -> 845
      //   834: aload_1
      //   835: aload_0
      //   836: getfield mAutofillId : Landroid/view/autofill/AutofillId;
      //   839: invokevirtual getVirtualChildIntId : ()I
      //   842: invokevirtual writeInt : (I)V
      //   845: iload #10
      //   847: sipush #2048
      //   850: iand
      //   851: ifeq -> 865
      //   854: aload_1
      //   855: aload_0
      //   856: getfield mAutofillId : Landroid/view/autofill/AutofillId;
      //   859: invokevirtual getSessionId : ()I
      //   862: invokevirtual writeInt : (I)V
      //   865: iload #10
      //   867: bipush #8
      //   869: iand
      //   870: ifeq -> 881
      //   873: aload_1
      //   874: aload_0
      //   875: getfield mAutofillType : I
      //   878: invokevirtual writeInt : (I)V
      //   881: iload #10
      //   883: bipush #16
      //   885: iand
      //   886: ifeq -> 897
      //   889: aload_1
      //   890: aload_0
      //   891: getfield mAutofillHints : [Ljava/lang/String;
      //   894: invokevirtual writeStringArray : ([Ljava/lang/String;)V
      //   897: iload #10
      //   899: iconst_4
      //   900: iand
      //   901: ifeq -> 958
      //   904: iload_3
      //   905: ifeq -> 917
      //   908: aload_0
      //   909: getfield mAutofillValue : Landroid/view/autofill/AutofillValue;
      //   912: astore #11
      //   914: goto -> 951
      //   917: aload_0
      //   918: getfield mAutofillOverlay : Landroid/app/assist/AssistStructure$AutofillOverlay;
      //   921: astore #11
      //   923: aload #11
      //   925: ifnull -> 948
      //   928: aload #11
      //   930: getfield value : Landroid/view/autofill/AutofillValue;
      //   933: ifnull -> 948
      //   936: aload_0
      //   937: getfield mAutofillOverlay : Landroid/app/assist/AssistStructure$AutofillOverlay;
      //   940: getfield value : Landroid/view/autofill/AutofillValue;
      //   943: astore #11
      //   945: goto -> 951
      //   948: aconst_null
      //   949: astore #11
      //   951: aload_1
      //   952: aload #11
      //   954: iconst_0
      //   955: invokevirtual writeParcelable : (Landroid/os/Parcelable;I)V
      //   958: iload #10
      //   960: bipush #32
      //   962: iand
      //   963: ifeq -> 974
      //   966: aload_1
      //   967: aload_0
      //   968: getfield mAutofillOptions : [Ljava/lang/CharSequence;
      //   971: invokevirtual writeCharSequenceArray : ([Ljava/lang/CharSequence;)V
      //   974: iload #10
      //   976: bipush #64
      //   978: iand
      //   979: ifeq -> 994
      //   982: aload_1
      //   983: aload_0
      //   984: getfield mHtmlInfo : Landroid/view/ViewStructure$HtmlInfo;
      //   987: checkcast android/os/Parcelable
      //   990: iconst_0
      //   991: invokevirtual writeParcelable : (Landroid/os/Parcelable;I)V
      //   994: iload #10
      //   996: sipush #256
      //   999: iand
      //   1000: ifeq -> 1011
      //   1003: aload_1
      //   1004: aload_0
      //   1005: getfield mMinEms : I
      //   1008: invokevirtual writeInt : (I)V
      //   1011: iload #10
      //   1013: sipush #512
      //   1016: iand
      //   1017: ifeq -> 1028
      //   1020: aload_1
      //   1021: aload_0
      //   1022: getfield mMaxEms : I
      //   1025: invokevirtual writeInt : (I)V
      //   1028: iload #10
      //   1030: sipush #1024
      //   1033: iand
      //   1034: ifeq -> 1045
      //   1037: aload_1
      //   1038: aload_0
      //   1039: getfield mMaxLength : I
      //   1042: invokevirtual writeInt : (I)V
      //   1045: iload #10
      //   1047: sipush #128
      //   1050: iand
      //   1051: ifeq -> 1062
      //   1054: aload_2
      //   1055: aload_0
      //   1056: getfield mTextIdEntry : Ljava/lang/String;
      //   1059: invokevirtual writeString : (Ljava/lang/String;)V
      //   1062: iload_3
      //   1063: istore #5
      //   1065: iload #10
      //   1067: sipush #4096
      //   1070: iand
      //   1071: ifeq -> 1085
      //   1074: aload_2
      //   1075: aload_0
      //   1076: getfield mHintIdEntry : Ljava/lang/String;
      //   1079: invokevirtual writeString : (Ljava/lang/String;)V
      //   1082: iload_3
      //   1083: istore #5
      //   1085: iload #6
      //   1087: ldc 67108864
      //   1089: iand
      //   1090: ifeq -> 1128
      //   1093: aload_1
      //   1094: aload_0
      //   1095: getfield mX : I
      //   1098: invokevirtual writeInt : (I)V
      //   1101: aload_1
      //   1102: aload_0
      //   1103: getfield mY : I
      //   1106: invokevirtual writeInt : (I)V
      //   1109: aload_1
      //   1110: aload_0
      //   1111: getfield mWidth : I
      //   1114: invokevirtual writeInt : (I)V
      //   1117: aload_1
      //   1118: aload_0
      //   1119: getfield mHeight : I
      //   1122: invokevirtual writeInt : (I)V
      //   1125: goto -> 1160
      //   1128: aload_1
      //   1129: aload_0
      //   1130: getfield mY : I
      //   1133: bipush #16
      //   1135: ishl
      //   1136: aload_0
      //   1137: getfield mX : I
      //   1140: ior
      //   1141: invokevirtual writeInt : (I)V
      //   1144: aload_1
      //   1145: aload_0
      //   1146: getfield mHeight : I
      //   1149: bipush #16
      //   1151: ishl
      //   1152: aload_0
      //   1153: getfield mWidth : I
      //   1156: ior
      //   1157: invokevirtual writeInt : (I)V
      //   1160: iload #6
      //   1162: ldc 134217728
      //   1164: iand
      //   1165: ifeq -> 1184
      //   1168: aload_1
      //   1169: aload_0
      //   1170: getfield mScrollX : I
      //   1173: invokevirtual writeInt : (I)V
      //   1176: aload_1
      //   1177: aload_0
      //   1178: getfield mScrollY : I
      //   1181: invokevirtual writeInt : (I)V
      //   1184: iload #6
      //   1186: ldc 1073741824
      //   1188: iand
      //   1189: ifeq -> 1207
      //   1192: aload_0
      //   1193: getfield mMatrix : Landroid/graphics/Matrix;
      //   1196: aload #4
      //   1198: invokevirtual getValues : ([F)V
      //   1201: aload_1
      //   1202: aload #4
      //   1204: invokevirtual writeFloatArray : ([F)V
      //   1207: iload #6
      //   1209: ldc 268435456
      //   1211: iand
      //   1212: ifeq -> 1223
      //   1215: aload_1
      //   1216: aload_0
      //   1217: getfield mElevation : F
      //   1220: invokevirtual writeFloat : (F)V
      //   1223: iload #6
      //   1225: ldc 536870912
      //   1227: iand
      //   1228: ifeq -> 1239
      //   1231: aload_1
      //   1232: aload_0
      //   1233: getfield mAlpha : F
      //   1236: invokevirtual writeFloat : (F)V
      //   1239: iload #6
      //   1241: ldc 33554432
      //   1243: iand
      //   1244: ifeq -> 1256
      //   1247: aload_0
      //   1248: getfield mContentDescription : Ljava/lang/CharSequence;
      //   1251: aload_1
      //   1252: iconst_0
      //   1253: invokestatic writeToParcel : (Ljava/lang/CharSequence;Landroid/os/Parcel;I)V
      //   1256: iload #6
      //   1258: ldc 16777216
      //   1260: iand
      //   1261: ifeq -> 1292
      //   1264: aload_0
      //   1265: getfield mText : Landroid/app/assist/AssistStructure$ViewNodeText;
      //   1268: astore_2
      //   1269: iload #6
      //   1271: ldc 8388608
      //   1273: iand
      //   1274: ifne -> 1282
      //   1277: iconst_1
      //   1278: istore_3
      //   1279: goto -> 1284
      //   1282: iconst_0
      //   1283: istore_3
      //   1284: aload_2
      //   1285: aload_1
      //   1286: iload_3
      //   1287: iload #5
      //   1289: invokevirtual writeToParcel : (Landroid/os/Parcel;ZZ)V
      //   1292: iload #6
      //   1294: ldc 262144
      //   1296: iand
      //   1297: ifeq -> 1308
      //   1300: aload_1
      //   1301: aload_0
      //   1302: getfield mInputType : I
      //   1305: invokevirtual writeInt : (I)V
      //   1308: iload #6
      //   1310: ldc 131072
      //   1312: iand
      //   1313: ifeq -> 1324
      //   1316: aload_1
      //   1317: aload_0
      //   1318: getfield mWebScheme : Ljava/lang/String;
      //   1321: invokevirtual writeString : (Ljava/lang/String;)V
      //   1324: iload #6
      //   1326: ldc 524288
      //   1328: iand
      //   1329: ifeq -> 1340
      //   1332: aload_1
      //   1333: aload_0
      //   1334: getfield mWebDomain : Ljava/lang/String;
      //   1337: invokevirtual writeString : (Ljava/lang/String;)V
      //   1340: iload #6
      //   1342: ldc 65536
      //   1344: iand
      //   1345: ifeq -> 1357
      //   1348: aload_1
      //   1349: aload_0
      //   1350: getfield mLocaleList : Landroid/os/LocaleList;
      //   1353: iconst_0
      //   1354: invokevirtual writeParcelable : (Landroid/os/Parcelable;I)V
      //   1357: iload #6
      //   1359: ldc 4194304
      //   1361: iand
      //   1362: ifeq -> 1373
      //   1365: aload_1
      //   1366: aload_0
      //   1367: getfield mExtras : Landroid/os/Bundle;
      //   1370: invokevirtual writeBundle : (Landroid/os/Bundle;)V
      //   1373: iload #6
      //   1375: ireturn
    }
  }
  
  static class ViewNodeBuilder extends ViewStructure {
    final AssistStructure mAssist;
    
    final boolean mAsync;
    
    final AssistStructure.ViewNode mNode;
    
    ViewNodeBuilder(AssistStructure param1AssistStructure, AssistStructure.ViewNode param1ViewNode, boolean param1Boolean) {
      this.mAssist = param1AssistStructure;
      this.mNode = param1ViewNode;
      this.mAsync = param1Boolean;
    }
    
    private final AssistStructure.ViewNodeText getNodeText() {
      if (this.mNode.mText != null)
        return this.mNode.mText; 
      this.mNode.mText = new AssistStructure.ViewNodeText();
      return this.mNode.mText;
    }
    
    public int addChildCount(int param1Int) {
      if (this.mNode.mChildren == null) {
        setChildCount(param1Int);
        return 0;
      } 
      int i = this.mNode.mChildren.length;
      AssistStructure.ViewNode[] arrayOfViewNode = new AssistStructure.ViewNode[i + param1Int];
      System.arraycopy(this.mNode.mChildren, 0, arrayOfViewNode, 0, i);
      this.mNode.mChildren = arrayOfViewNode;
      return i;
    }
    
    public void asyncCommit() {
      synchronized (this.mAssist) {
        if (this.mAsync) {
          if (this.mAssist.mPendingAsyncChildren.remove(this)) {
            this.mAssist.notifyAll();
            return;
          } 
          IllegalStateException illegalStateException1 = new IllegalStateException();
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("Child ");
          stringBuilder1.append(this);
          stringBuilder1.append(" already committed");
          this(stringBuilder1.toString());
          throw illegalStateException1;
        } 
        IllegalStateException illegalStateException = new IllegalStateException();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Child ");
        stringBuilder.append(this);
        stringBuilder.append(" was not created with ViewStructure.asyncNewChild");
        this(stringBuilder.toString());
        throw illegalStateException;
      } 
    }
    
    public ViewStructure asyncNewChild(int param1Int) {
      synchronized (this.mAssist) {
        AssistStructure.ViewNode viewNode = new AssistStructure.ViewNode();
        this();
        this.mNode.mChildren[param1Int] = viewNode;
        ViewNodeBuilder viewNodeBuilder = new ViewNodeBuilder();
        this(this.mAssist, viewNode, true);
        this.mAssist.mPendingAsyncChildren.add(viewNodeBuilder);
        return viewNodeBuilder;
      } 
    }
    
    public AutofillId getAutofillId() {
      return this.mNode.mAutofillId;
    }
    
    public int getChildCount() {
      boolean bool;
      if (this.mNode.mChildren != null) {
        bool = this.mNode.mChildren.length;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public Bundle getExtras() {
      if (this.mNode.mExtras != null)
        return this.mNode.mExtras; 
      this.mNode.mExtras = new Bundle();
      return this.mNode.mExtras;
    }
    
    public CharSequence getHint() {
      CharSequence charSequence;
      if (this.mNode.mText != null) {
        charSequence = this.mNode.mText.mHint;
      } else {
        charSequence = null;
      } 
      return charSequence;
    }
    
    public Rect getTempRect() {
      return this.mAssist.mTmpRect;
    }
    
    public CharSequence getText() {
      CharSequence charSequence;
      if (this.mNode.mText != null) {
        charSequence = this.mNode.mText.mText;
      } else {
        charSequence = null;
      } 
      return charSequence;
    }
    
    public int getTextSelectionEnd() {
      byte b;
      if (this.mNode.mText != null) {
        b = this.mNode.mText.mTextSelectionEnd;
      } else {
        b = -1;
      } 
      return b;
    }
    
    public int getTextSelectionStart() {
      byte b;
      if (this.mNode.mText != null) {
        b = this.mNode.mText.mTextSelectionStart;
      } else {
        b = -1;
      } 
      return b;
    }
    
    public boolean hasExtras() {
      boolean bool;
      if (this.mNode.mExtras != null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public ViewStructure newChild(int param1Int) {
      AssistStructure.ViewNode viewNode = new AssistStructure.ViewNode();
      this.mNode.mChildren[param1Int] = viewNode;
      return new ViewNodeBuilder(this.mAssist, viewNode, false);
    }
    
    public ViewStructure.HtmlInfo.Builder newHtmlInfoBuilder(String param1String) {
      return new AssistStructure.HtmlInfoNodeBuilder(param1String);
    }
    
    public void setAccessibilityFocused(boolean param1Boolean) {
      boolean bool;
      AssistStructure.ViewNode viewNode = this.mNode;
      int i = viewNode.mFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      viewNode.mFlags = i & 0xFFFFEFFF | bool;
    }
    
    public void setActivated(boolean param1Boolean) {
      boolean bool;
      AssistStructure.ViewNode viewNode = this.mNode;
      int i = viewNode.mFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      viewNode.mFlags = i & 0xFFFFDFFF | bool;
    }
    
    public void setAlpha(float param1Float) {
      this.mNode.mAlpha = param1Float;
    }
    
    public void setAssistBlocked(boolean param1Boolean) {
      boolean bool;
      AssistStructure.ViewNode viewNode = this.mNode;
      int i = viewNode.mFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      viewNode.mFlags = i & 0xFFFFFF7F | bool;
    }
    
    public void setAutofillHints(String[] param1ArrayOfString) {
      this.mNode.mAutofillHints = param1ArrayOfString;
    }
    
    public void setAutofillId(AutofillId param1AutofillId) {
      this.mNode.mAutofillId = param1AutofillId;
    }
    
    public void setAutofillId(AutofillId param1AutofillId, int param1Int) {
      this.mNode.mAutofillId = new AutofillId(param1AutofillId, param1Int);
    }
    
    public void setAutofillOptions(CharSequence[] param1ArrayOfCharSequence) {
      this.mNode.mAutofillOptions = param1ArrayOfCharSequence;
    }
    
    public void setAutofillType(int param1Int) {
      this.mNode.mAutofillType = param1Int;
    }
    
    public void setAutofillValue(AutofillValue param1AutofillValue) {
      this.mNode.mAutofillValue = param1AutofillValue;
    }
    
    public void setCheckable(boolean param1Boolean) {
      boolean bool;
      AssistStructure.ViewNode viewNode = this.mNode;
      int i = viewNode.mFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      viewNode.mFlags = i & 0xFFFFFEFF | bool;
    }
    
    public void setChecked(boolean param1Boolean) {
      boolean bool;
      AssistStructure.ViewNode viewNode = this.mNode;
      int i = viewNode.mFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      viewNode.mFlags = i & 0xFFFFFDFF | bool;
    }
    
    public void setChildCount(int param1Int) {
      this.mNode.mChildren = new AssistStructure.ViewNode[param1Int];
    }
    
    public void setClassName(String param1String) {
      this.mNode.mClassName = param1String;
    }
    
    public void setClickable(boolean param1Boolean) {
      boolean bool;
      AssistStructure.ViewNode viewNode = this.mNode;
      int i = viewNode.mFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      viewNode.mFlags = i & 0xFFFFFBFF | bool;
    }
    
    public void setContentDescription(CharSequence param1CharSequence) {
      this.mNode.mContentDescription = param1CharSequence;
    }
    
    public void setContextClickable(boolean param1Boolean) {
      boolean bool;
      AssistStructure.ViewNode viewNode = this.mNode;
      int i = viewNode.mFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      viewNode.mFlags = i & 0xFFFFBFFF | bool;
    }
    
    public void setDataIsSensitive(boolean param1Boolean) {
      this.mNode.mSanitized = param1Boolean ^ true;
    }
    
    public void setDimens(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6) {
      this.mNode.mX = param1Int1;
      this.mNode.mY = param1Int2;
      this.mNode.mScrollX = param1Int3;
      this.mNode.mScrollY = param1Int4;
      this.mNode.mWidth = param1Int5;
      this.mNode.mHeight = param1Int6;
    }
    
    public void setElevation(float param1Float) {
      this.mNode.mElevation = param1Float;
    }
    
    public void setEnabled(boolean param1Boolean) {
      AssistStructure.ViewNode viewNode = this.mNode;
      viewNode.mFlags = viewNode.mFlags & 0xFFFFFFFE | param1Boolean ^ true;
    }
    
    public void setFocusable(boolean param1Boolean) {
      boolean bool;
      AssistStructure.ViewNode viewNode = this.mNode;
      int i = viewNode.mFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      viewNode.mFlags = i & 0xFFFFFFEF | bool;
    }
    
    public void setFocused(boolean param1Boolean) {
      boolean bool;
      AssistStructure.ViewNode viewNode = this.mNode;
      int i = viewNode.mFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      viewNode.mFlags = i & 0xFFFFFFDF | bool;
    }
    
    public void setHint(CharSequence param1CharSequence) {
      AssistStructure.ViewNodeText viewNodeText = getNodeText();
      if (param1CharSequence != null) {
        param1CharSequence = param1CharSequence.toString();
      } else {
        param1CharSequence = null;
      } 
      viewNodeText.mHint = (String)param1CharSequence;
    }
    
    public void setHintIdEntry(String param1String) {
      AssistStructure.ViewNode viewNode = this.mNode;
      Objects.requireNonNull(param1String);
      viewNode.mHintIdEntry = param1String;
    }
    
    public void setHtmlInfo(ViewStructure.HtmlInfo param1HtmlInfo) {
      this.mNode.mHtmlInfo = param1HtmlInfo;
    }
    
    public void setId(int param1Int, String param1String1, String param1String2, String param1String3) {
      this.mNode.mId = param1Int;
      this.mNode.mIdPackage = param1String1;
      this.mNode.mIdType = param1String2;
      this.mNode.mIdEntry = param1String3;
    }
    
    public void setImportantForAutofill(int param1Int) {
      this.mNode.mImportantForAutofill = param1Int;
    }
    
    public void setInputType(int param1Int) {
      this.mNode.mInputType = param1Int;
    }
    
    public void setLocaleList(LocaleList param1LocaleList) {
      this.mNode.mLocaleList = param1LocaleList;
    }
    
    public void setLongClickable(boolean param1Boolean) {
      boolean bool;
      AssistStructure.ViewNode viewNode = this.mNode;
      int i = viewNode.mFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      viewNode.mFlags = i & 0xFFFFF7FF | bool;
    }
    
    public void setMaxTextEms(int param1Int) {
      this.mNode.mMaxEms = param1Int;
    }
    
    public void setMaxTextLength(int param1Int) {
      this.mNode.mMaxLength = param1Int;
    }
    
    public void setMinTextEms(int param1Int) {
      this.mNode.mMinEms = param1Int;
    }
    
    public void setOpaque(boolean param1Boolean) {
      boolean bool;
      AssistStructure.ViewNode viewNode = this.mNode;
      int i = viewNode.mFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      viewNode.mFlags = i & 0xFFFF7FFF | bool;
    }
    
    public void setSelected(boolean param1Boolean) {
      boolean bool;
      AssistStructure.ViewNode viewNode = this.mNode;
      int i = viewNode.mFlags;
      if (param1Boolean) {
        bool = true;
      } else {
        bool = false;
      } 
      viewNode.mFlags = i & 0xFFFFFFBF | bool;
    }
    
    public void setText(CharSequence param1CharSequence) {
      AssistStructure.ViewNodeText viewNodeText = getNodeText();
      viewNodeText.mText = TextUtils.trimNoCopySpans(param1CharSequence);
      viewNodeText.mTextSelectionEnd = -1;
      viewNodeText.mTextSelectionStart = -1;
    }
    
    public void setText(CharSequence param1CharSequence, int param1Int1, int param1Int2) {
      AssistStructure.ViewNodeText viewNodeText = getNodeText();
      viewNodeText.mText = TextUtils.trimNoCopySpans(param1CharSequence);
      viewNodeText.mTextSelectionStart = param1Int1;
      viewNodeText.mTextSelectionEnd = param1Int2;
    }
    
    public void setTextIdEntry(String param1String) {
      AssistStructure.ViewNode viewNode = this.mNode;
      Objects.requireNonNull(param1String);
      viewNode.mTextIdEntry = param1String;
    }
    
    public void setTextLines(int[] param1ArrayOfint1, int[] param1ArrayOfint2) {
      AssistStructure.ViewNodeText viewNodeText = getNodeText();
      viewNodeText.mLineCharOffsets = param1ArrayOfint1;
      viewNodeText.mLineBaselines = param1ArrayOfint2;
    }
    
    public void setTextStyle(float param1Float, int param1Int1, int param1Int2, int param1Int3) {
      AssistStructure.ViewNodeText viewNodeText = getNodeText();
      viewNodeText.mTextColor = param1Int1;
      viewNodeText.mTextBackgroundColor = param1Int2;
      viewNodeText.mTextSize = param1Float;
      viewNodeText.mTextStyle = param1Int3;
    }
    
    public void setTransformation(Matrix param1Matrix) {
      if (param1Matrix == null) {
        this.mNode.mMatrix = null;
      } else {
        this.mNode.mMatrix = new Matrix(param1Matrix);
      } 
    }
    
    public void setVisibility(int param1Int) {
      AssistStructure.ViewNode viewNode = this.mNode;
      viewNode.mFlags = viewNode.mFlags & 0xFFFFFFF3 | param1Int & 0xC;
    }
    
    public void setWebDomain(String param1String) {
      this.mNode.setWebDomain(param1String);
    }
  }
  
  static final class ViewNodeText {
    String mHint;
    
    int[] mLineBaselines;
    
    int[] mLineCharOffsets;
    
    CharSequence mText;
    
    int mTextBackgroundColor = 1;
    
    int mTextColor = 1;
    
    int mTextSelectionEnd;
    
    int mTextSelectionStart;
    
    float mTextSize;
    
    int mTextStyle;
    
    ViewNodeText() {}
    
    ViewNodeText(Parcel param1Parcel, boolean param1Boolean) {
      this.mText = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(param1Parcel);
      this.mTextSize = param1Parcel.readFloat();
      this.mTextStyle = param1Parcel.readInt();
      this.mTextColor = param1Parcel.readInt();
      if (!param1Boolean) {
        this.mTextBackgroundColor = param1Parcel.readInt();
        this.mTextSelectionStart = param1Parcel.readInt();
        this.mTextSelectionEnd = param1Parcel.readInt();
        this.mLineCharOffsets = param1Parcel.createIntArray();
        this.mLineBaselines = param1Parcel.createIntArray();
        this.mHint = param1Parcel.readString();
      } 
    }
    
    boolean isSimple() {
      int i = this.mTextBackgroundColor;
      boolean bool = true;
      if (i != 1 || this.mTextSelectionStart != 0 || this.mTextSelectionEnd != 0 || this.mLineCharOffsets != null || this.mLineBaselines != null || this.mHint != null)
        bool = false; 
      return bool;
    }
    
    void writeToParcel(Parcel param1Parcel, boolean param1Boolean1, boolean param1Boolean2) {
      CharSequence charSequence;
      if (param1Boolean2) {
        charSequence = this.mText;
      } else {
        charSequence = "";
      } 
      TextUtils.writeToParcel(charSequence, param1Parcel, 0);
      param1Parcel.writeFloat(this.mTextSize);
      param1Parcel.writeInt(this.mTextStyle);
      param1Parcel.writeInt(this.mTextColor);
      if (!param1Boolean1) {
        param1Parcel.writeInt(this.mTextBackgroundColor);
        param1Parcel.writeInt(this.mTextSelectionStart);
        param1Parcel.writeInt(this.mTextSelectionEnd);
        param1Parcel.writeIntArray(this.mLineCharOffsets);
        param1Parcel.writeIntArray(this.mLineBaselines);
        param1Parcel.writeString(this.mHint);
      } 
    }
  }
  
  static final class ViewStackEntry {
    int curChild;
    
    AssistStructure.ViewNode node;
    
    int numChildren;
  }
  
  public static class WindowNode {
    final int mDisplayId;
    
    final int mHeight;
    
    final AssistStructure.ViewNode mRoot;
    
    final CharSequence mTitle;
    
    final int mWidth;
    
    final int mX;
    
    final int mY;
    
    WindowNode(AssistStructure.ParcelTransferReader param1ParcelTransferReader) {
      Parcel parcel = param1ParcelTransferReader.readParcel(286331153, 0);
      param1ParcelTransferReader.mNumReadWindows++;
      this.mX = parcel.readInt();
      this.mY = parcel.readInt();
      this.mWidth = parcel.readInt();
      this.mHeight = parcel.readInt();
      this.mTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
      this.mDisplayId = parcel.readInt();
      this.mRoot = new AssistStructure.ViewNode(param1ParcelTransferReader, 0);
    }
    
    WindowNode(AssistStructure param1AssistStructure, ViewRootImpl param1ViewRootImpl, boolean param1Boolean, int param1Int) {
      View view = param1ViewRootImpl.getView();
      Rect rect = new Rect();
      view.getBoundsOnScreen(rect);
      this.mX = rect.left - view.getLeft();
      this.mY = rect.top - view.getTop();
      this.mWidth = rect.width();
      this.mHeight = rect.height();
      this.mTitle = param1ViewRootImpl.getTitle();
      this.mDisplayId = param1ViewRootImpl.getDisplayId();
      AssistStructure.ViewNode viewNode = new AssistStructure.ViewNode();
      this.mRoot = viewNode;
      AssistStructure.ViewNodeBuilder viewNodeBuilder = new AssistStructure.ViewNodeBuilder(param1AssistStructure, viewNode, false);
      if ((param1ViewRootImpl.getWindowFlags() & 0x2000) != 0)
        if (param1Boolean) {
          view.onProvideAutofillStructure(viewNodeBuilder, resolveViewAutofillFlags(view.getContext(), param1Int));
        } else {
          view.onProvideStructure(viewNodeBuilder);
          viewNodeBuilder.setAssistBlocked(true);
          return;
        }  
      if (param1Boolean) {
        view.dispatchProvideAutofillStructure(viewNodeBuilder, resolveViewAutofillFlags(view.getContext(), param1Int));
      } else {
        view.dispatchProvideStructure(viewNodeBuilder);
      } 
    }
    
    public int getDisplayId() {
      return this.mDisplayId;
    }
    
    public int getHeight() {
      return this.mHeight;
    }
    
    public int getLeft() {
      return this.mX;
    }
    
    public AssistStructure.ViewNode getRootViewNode() {
      return this.mRoot;
    }
    
    public CharSequence getTitle() {
      return this.mTitle;
    }
    
    public int getTop() {
      return this.mY;
    }
    
    public int getWidth() {
      return this.mWidth;
    }
    
    int resolveViewAutofillFlags(Context param1Context, int param1Int) {
      return ((param1Int & 0x1) != 0 || param1Context.isAutofillCompatibilityEnabled()) ? 1 : 0;
    }
    
    void writeSelfToParcel(Parcel param1Parcel, PooledStringWriter param1PooledStringWriter, float[] param1ArrayOffloat) {
      param1Parcel.writeInt(this.mX);
      param1Parcel.writeInt(this.mY);
      param1Parcel.writeInt(this.mWidth);
      param1Parcel.writeInt(this.mHeight);
      TextUtils.writeToParcel(this.mTitle, param1Parcel, 0);
      param1Parcel.writeInt(this.mDisplayId);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/assist/AssistStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.app;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;

public class Action implements Parcelable {
  public static final Parcelable.Creator<Action> CREATOR = new Parcelable.Creator<Action>() {
      public Notification.Action createFromParcel(Parcel param2Parcel) {
        return new Notification.Action(param2Parcel);
      }
      
      public Notification.Action[] newArray(int param2Int) {
        return new Notification.Action[param2Int];
      }
    };
  
  private static final String EXTRA_DATA_ONLY_INPUTS = "android.extra.DATA_ONLY_INPUTS";
  
  public static final int SEMANTIC_ACTION_ARCHIVE = 5;
  
  public static final int SEMANTIC_ACTION_CALL = 10;
  
  public static final int SEMANTIC_ACTION_DELETE = 4;
  
  public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
  
  public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
  
  public static final int SEMANTIC_ACTION_MUTE = 6;
  
  public static final int SEMANTIC_ACTION_NONE = 0;
  
  public static final int SEMANTIC_ACTION_REPLY = 1;
  
  public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
  
  public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
  
  public static final int SEMANTIC_ACTION_UNMUTE = 7;
  
  public PendingIntent actionIntent;
  
  @Deprecated
  public int icon;
  
  private boolean mAllowGeneratedReplies;
  
  private final Bundle mExtras;
  
  private Icon mIcon;
  
  private final boolean mIsContextual;
  
  private final RemoteInput[] mRemoteInputs;
  
  private final int mSemanticAction;
  
  public CharSequence title;
  
  @Deprecated
  public Action(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent) {
    this(Icon.createWithResource("", paramInt), paramCharSequence, paramPendingIntent, new Bundle(), null, true, 0, false);
  }
  
  private Action(Icon paramIcon, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle, RemoteInput[] paramArrayOfRemoteInput, boolean paramBoolean1, int paramInt, boolean paramBoolean2) {
    this.mAllowGeneratedReplies = true;
    this.mIcon = paramIcon;
    if (paramIcon != null && paramIcon.getType() == 2)
      this.icon = paramIcon.getResId(); 
    this.title = paramCharSequence;
    this.actionIntent = paramPendingIntent;
    if (paramBundle == null)
      paramBundle = new Bundle(); 
    this.mExtras = paramBundle;
    this.mRemoteInputs = paramArrayOfRemoteInput;
    this.mAllowGeneratedReplies = paramBoolean1;
    this.mSemanticAction = paramInt;
    this.mIsContextual = paramBoolean2;
  }
  
  private Action(Parcel paramParcel) {
    boolean bool2;
    boolean bool1 = true;
    this.mAllowGeneratedReplies = true;
    if (paramParcel.readInt() != 0) {
      Icon icon = (Icon)Icon.CREATOR.createFromParcel(paramParcel);
      this.mIcon = icon;
      if (icon.getType() == 2)
        this.icon = this.mIcon.getResId(); 
    } 
    this.title = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    if (paramParcel.readInt() == 1)
      this.actionIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(paramParcel); 
    this.mExtras = Bundle.setDefusable(paramParcel.readBundle(), true);
    this.mRemoteInputs = (RemoteInput[])paramParcel.createTypedArray(RemoteInput.CREATOR);
    if (paramParcel.readInt() == 1) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    this.mAllowGeneratedReplies = bool2;
    this.mSemanticAction = paramParcel.readInt();
    if (paramParcel.readInt() == 1) {
      bool2 = bool1;
    } else {
      bool2 = false;
    } 
    this.mIsContextual = bool2;
  }
  
  public Action clone() {
    Bundle bundle;
    Icon icon = getIcon();
    CharSequence charSequence = this.title;
    PendingIntent pendingIntent = this.actionIntent;
    if (this.mExtras == null) {
      bundle = new Bundle();
    } else {
      bundle = new Bundle(this.mExtras);
    } 
    return new Action(icon, charSequence, pendingIntent, bundle, getRemoteInputs(), getAllowGeneratedReplies(), getSemanticAction(), isContextual());
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean getAllowGeneratedReplies() {
    return this.mAllowGeneratedReplies;
  }
  
  public RemoteInput[] getDataOnlyRemoteInputs() {
    return (RemoteInput[])Notification.access$000(this.mExtras, "android.extra.DATA_ONLY_INPUTS", RemoteInput.class);
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public Icon getIcon() {
    if (this.mIcon == null) {
      int i = this.icon;
      if (i != 0)
        this.mIcon = Icon.createWithResource("", i); 
    } 
    return this.mIcon;
  }
  
  public RemoteInput[] getRemoteInputs() {
    return this.mRemoteInputs;
  }
  
  public int getSemanticAction() {
    return this.mSemanticAction;
  }
  
  public boolean isContextual() {
    return this.mIsContextual;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    Icon icon = getIcon();
    if (icon != null) {
      paramParcel.writeInt(1);
      icon.writeToParcel(paramParcel, 0);
    } else {
      paramParcel.writeInt(0);
    } 
    TextUtils.writeToParcel(this.title, paramParcel, paramInt);
    if (this.actionIntent != null) {
      paramParcel.writeInt(1);
      this.actionIntent.writeToParcel(paramParcel, paramInt);
    } else {
      paramParcel.writeInt(0);
    } 
    paramParcel.writeBundle(this.mExtras);
    paramParcel.writeTypedArray((Parcelable[])this.mRemoteInputs, paramInt);
    paramParcel.writeInt(this.mAllowGeneratedReplies);
    paramParcel.writeInt(this.mSemanticAction);
    paramParcel.writeInt(this.mIsContextual);
  }
  
  public static final class Builder {
    private boolean mAllowGeneratedReplies = true;
    
    private final Bundle mExtras;
    
    private final Icon mIcon;
    
    private final PendingIntent mIntent;
    
    private boolean mIsContextual;
    
    private ArrayList<RemoteInput> mRemoteInputs;
    
    private int mSemanticAction;
    
    private final CharSequence mTitle;
    
    @Deprecated
    public Builder(int param2Int, CharSequence param2CharSequence, PendingIntent param2PendingIntent) {
      this(Icon.createWithResource("", param2Int), param2CharSequence, param2PendingIntent);
    }
    
    public Builder(Notification.Action param2Action) {
      this(param2Action.getIcon(), param2Action.title, param2Action.actionIntent, new Bundle(param2Action.mExtras), param2Action.getRemoteInputs(), param2Action.getAllowGeneratedReplies(), param2Action.getSemanticAction());
    }
    
    public Builder(Icon param2Icon, CharSequence param2CharSequence, PendingIntent param2PendingIntent) {
      this(param2Icon, param2CharSequence, param2PendingIntent, new Bundle(), null, true, 0);
    }
    
    private Builder(Icon param2Icon, CharSequence param2CharSequence, PendingIntent param2PendingIntent, Bundle param2Bundle, RemoteInput[] param2ArrayOfRemoteInput, boolean param2Boolean, int param2Int) {
      this.mIcon = param2Icon;
      this.mTitle = param2CharSequence;
      this.mIntent = param2PendingIntent;
      this.mExtras = param2Bundle;
      if (param2ArrayOfRemoteInput != null) {
        ArrayList<RemoteInput> arrayList = new ArrayList(param2ArrayOfRemoteInput.length);
        this.mRemoteInputs = arrayList;
        Collections.addAll(arrayList, param2ArrayOfRemoteInput);
      } 
      this.mAllowGeneratedReplies = param2Boolean;
      this.mSemanticAction = param2Int;
    }
    
    private void checkContextualActionNullFields() {
      if (!this.mIsContextual)
        return; 
      if (this.mIcon != null) {
        if (this.mIntent != null)
          return; 
        throw new NullPointerException("Contextual Actions must contain a valid PendingIntent");
      } 
      throw new NullPointerException("Contextual Actions must contain a valid icon");
    }
    
    public Builder addExtras(Bundle param2Bundle) {
      if (param2Bundle != null)
        this.mExtras.putAll(param2Bundle); 
      return this;
    }
    
    public Builder addRemoteInput(RemoteInput param2RemoteInput) {
      if (this.mRemoteInputs == null)
        this.mRemoteInputs = new ArrayList<>(); 
      this.mRemoteInputs.add(param2RemoteInput);
      return this;
    }
    
    public Notification.Action build() {
      RemoteInput[] arrayOfRemoteInput1;
      checkContextualActionNullFields();
      ArrayList<RemoteInput> arrayList1 = new ArrayList();
      RemoteInput[] arrayOfRemoteInput2 = (RemoteInput[])Notification.access$000(this.mExtras, "android.extra.DATA_ONLY_INPUTS", RemoteInput.class);
      if (arrayOfRemoteInput2 != null) {
        int i = arrayOfRemoteInput2.length;
        for (byte b = 0; b < i; b++)
          arrayList1.add(arrayOfRemoteInput2[b]); 
      } 
      ArrayList<RemoteInput> arrayList2 = new ArrayList();
      ArrayList<RemoteInput> arrayList3 = this.mRemoteInputs;
      if (arrayList3 != null)
        for (RemoteInput remoteInput : arrayList3) {
          if (remoteInput.isDataOnly()) {
            arrayList1.add(remoteInput);
            continue;
          } 
          arrayList2.add(remoteInput);
        }  
      if (!arrayList1.isEmpty()) {
        RemoteInput[] arrayOfRemoteInput = arrayList1.<RemoteInput>toArray(new RemoteInput[arrayList1.size()]);
        this.mExtras.putParcelableArray("android.extra.DATA_ONLY_INPUTS", (Parcelable[])arrayOfRemoteInput);
      } 
      if (arrayList2.isEmpty()) {
        arrayList2 = null;
      } else {
        arrayOfRemoteInput1 = arrayList2.<RemoteInput>toArray(new RemoteInput[arrayList2.size()]);
      } 
      return new Notification.Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, arrayOfRemoteInput1, this.mAllowGeneratedReplies, this.mSemanticAction, this.mIsContextual);
    }
    
    public Builder extend(Notification.Action.Extender param2Extender) {
      param2Extender.extend(this);
      return this;
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public Builder setAllowGeneratedReplies(boolean param2Boolean) {
      this.mAllowGeneratedReplies = param2Boolean;
      return this;
    }
    
    public Builder setContextual(boolean param2Boolean) {
      this.mIsContextual = param2Boolean;
      return this;
    }
    
    public Builder setSemanticAction(int param2Int) {
      this.mSemanticAction = param2Int;
      return this;
    }
  }
  
  public static interface Extender {
    Notification.Action.Builder extend(Notification.Action.Builder param2Builder);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SemanticAction {}
  
  public static final class WearableExtender implements Extender {
    private static final int DEFAULT_FLAGS = 1;
    
    private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
    
    private static final int FLAG_AVAILABLE_OFFLINE = 1;
    
    private static final int FLAG_HINT_DISPLAY_INLINE = 4;
    
    private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
    
    private static final String KEY_CANCEL_LABEL = "cancelLabel";
    
    private static final String KEY_CONFIRM_LABEL = "confirmLabel";
    
    private static final String KEY_FLAGS = "flags";
    
    private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
    
    private CharSequence mCancelLabel;
    
    private CharSequence mConfirmLabel;
    
    private int mFlags = 1;
    
    private CharSequence mInProgressLabel;
    
    public WearableExtender() {}
    
    public WearableExtender(Notification.Action param2Action) {
      Bundle bundle = param2Action.getExtras().getBundle("android.wearable.EXTENSIONS");
      if (bundle != null) {
        this.mFlags = bundle.getInt("flags", 1);
        this.mInProgressLabel = bundle.getCharSequence("inProgressLabel");
        this.mConfirmLabel = bundle.getCharSequence("confirmLabel");
        this.mCancelLabel = bundle.getCharSequence("cancelLabel");
      } 
    }
    
    private void setFlag(int param2Int, boolean param2Boolean) {
      if (param2Boolean) {
        this.mFlags |= param2Int;
      } else {
        this.mFlags &= param2Int;
      } 
    }
    
    public WearableExtender clone() {
      WearableExtender wearableExtender = new WearableExtender();
      wearableExtender.mFlags = this.mFlags;
      wearableExtender.mInProgressLabel = this.mInProgressLabel;
      wearableExtender.mConfirmLabel = this.mConfirmLabel;
      wearableExtender.mCancelLabel = this.mCancelLabel;
      return wearableExtender;
    }
    
    public Notification.Action.Builder extend(Notification.Action.Builder param2Builder) {
      Bundle bundle = new Bundle();
      int i = this.mFlags;
      if (i != 1)
        bundle.putInt("flags", i); 
      CharSequence charSequence = this.mInProgressLabel;
      if (charSequence != null)
        bundle.putCharSequence("inProgressLabel", charSequence); 
      charSequence = this.mConfirmLabel;
      if (charSequence != null)
        bundle.putCharSequence("confirmLabel", charSequence); 
      charSequence = this.mCancelLabel;
      if (charSequence != null)
        bundle.putCharSequence("cancelLabel", charSequence); 
      param2Builder.getExtras().putBundle("android.wearable.EXTENSIONS", bundle);
      return param2Builder;
    }
    
    @Deprecated
    public CharSequence getCancelLabel() {
      return this.mCancelLabel;
    }
    
    @Deprecated
    public CharSequence getConfirmLabel() {
      return this.mConfirmLabel;
    }
    
    public boolean getHintDisplayActionInline() {
      boolean bool;
      if ((this.mFlags & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean getHintLaunchesActivity() {
      boolean bool;
      if ((this.mFlags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    @Deprecated
    public CharSequence getInProgressLabel() {
      return this.mInProgressLabel;
    }
    
    public boolean isAvailableOffline() {
      int i = this.mFlags;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public WearableExtender setAvailableOffline(boolean param2Boolean) {
      setFlag(1, param2Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setCancelLabel(CharSequence param2CharSequence) {
      this.mCancelLabel = param2CharSequence;
      return this;
    }
    
    @Deprecated
    public WearableExtender setConfirmLabel(CharSequence param2CharSequence) {
      this.mConfirmLabel = param2CharSequence;
      return this;
    }
    
    public WearableExtender setHintDisplayActionInline(boolean param2Boolean) {
      setFlag(4, param2Boolean);
      return this;
    }
    
    public WearableExtender setHintLaunchesActivity(boolean param2Boolean) {
      setFlag(2, param2Boolean);
      return this;
    }
    
    @Deprecated
    public WearableExtender setInProgressLabel(CharSequence param2CharSequence) {
      this.mInProgressLabel = param2CharSequence;
      return this;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Notification$Action.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
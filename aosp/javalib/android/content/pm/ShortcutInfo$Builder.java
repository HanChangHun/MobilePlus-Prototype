package android.content.pm;

import android.app.Person;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.LocusId;
import android.graphics.drawable.Icon;
import android.os.PersistableBundle;
import com.android.internal.util.Preconditions;
import java.util.Objects;
import java.util.Set;

public class Builder {
  private ComponentName mActivity;
  
  private Set<String> mCategories;
  
  private final Context mContext;
  
  private CharSequence mDisabledMessage;
  
  private int mDisabledMessageResId;
  
  private PersistableBundle mExtras;
  
  private Icon mIcon;
  
  private String mId;
  
  private Intent[] mIntents;
  
  private boolean mIsLongLived;
  
  private LocusId mLocusId;
  
  private Person[] mPersons;
  
  private int mRank = Integer.MAX_VALUE;
  
  private CharSequence mText;
  
  private int mTextResId;
  
  private CharSequence mTitle;
  
  private int mTitleResId;
  
  @Deprecated
  public Builder(Context paramContext) {
    this.mContext = paramContext;
  }
  
  public Builder(Context paramContext, String paramString) {
    this.mContext = paramContext;
    this.mId = (String)Preconditions.checkStringNotEmpty(paramString, "id cannot be empty");
  }
  
  public ShortcutInfo build() {
    return new ShortcutInfo(this, null);
  }
  
  public Builder setActivity(ComponentName paramComponentName) {
    Objects.requireNonNull(paramComponentName, "activity cannot be null");
    this.mActivity = paramComponentName;
    return this;
  }
  
  public Builder setCategories(Set<String> paramSet) {
    this.mCategories = paramSet;
    return this;
  }
  
  public Builder setDisabledMessage(CharSequence paramCharSequence) {
    boolean bool;
    if (this.mDisabledMessageResId == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool, "disabledMessageResId already set");
    this.mDisabledMessage = Preconditions.checkStringNotEmpty(paramCharSequence, "disabledMessage cannot be empty");
    return this;
  }
  
  @Deprecated
  public Builder setDisabledMessageResId(int paramInt) {
    boolean bool;
    if (this.mDisabledMessage == null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool, "disabledMessage already set");
    this.mDisabledMessageResId = paramInt;
    return this;
  }
  
  public Builder setExtras(PersistableBundle paramPersistableBundle) {
    this.mExtras = paramPersistableBundle;
    return this;
  }
  
  public Builder setIcon(Icon paramIcon) {
    this.mIcon = ShortcutInfo.validateIcon(paramIcon);
    return this;
  }
  
  @Deprecated
  public Builder setId(String paramString) {
    this.mId = (String)Preconditions.checkStringNotEmpty(paramString, "id cannot be empty");
    return this;
  }
  
  public Builder setIntent(Intent paramIntent) {
    return setIntents(new Intent[] { paramIntent });
  }
  
  public Builder setIntents(Intent[] paramArrayOfIntent) {
    Objects.requireNonNull(paramArrayOfIntent, "intents cannot be null");
    Objects.requireNonNull(Integer.valueOf(paramArrayOfIntent.length), "intents cannot be empty");
    int i = paramArrayOfIntent.length;
    for (byte b = 0; b < i; b++) {
      Intent intent = paramArrayOfIntent[b];
      Objects.requireNonNull(intent, "intents cannot contain null");
      Objects.requireNonNull(intent.getAction(), "intent's action must be set");
    } 
    this.mIntents = ShortcutInfo.access$1700(paramArrayOfIntent);
    return this;
  }
  
  public Builder setLocusId(LocusId paramLocusId) {
    Objects.requireNonNull(paramLocusId, "locusId cannot be null");
    this.mLocusId = paramLocusId;
    return this;
  }
  
  public Builder setLongLabel(CharSequence paramCharSequence) {
    boolean bool;
    if (this.mTextResId == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool, "longLabelResId already set");
    this.mText = Preconditions.checkStringNotEmpty(paramCharSequence, "longLabel cannot be empty");
    return this;
  }
  
  @Deprecated
  public Builder setLongLabelResId(int paramInt) {
    boolean bool;
    if (this.mText == null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool, "longLabel already set");
    this.mTextResId = paramInt;
    return this;
  }
  
  public Builder setLongLived(boolean paramBoolean) {
    this.mIsLongLived = paramBoolean;
    return this;
  }
  
  public Builder setPerson(Person paramPerson) {
    return setPersons(new Person[] { paramPerson });
  }
  
  public Builder setPersons(Person[] paramArrayOfPerson) {
    Objects.requireNonNull(paramArrayOfPerson, "persons cannot be null");
    Objects.requireNonNull(Integer.valueOf(paramArrayOfPerson.length), "persons cannot be empty");
    int i = paramArrayOfPerson.length;
    for (byte b = 0; b < i; b++)
      Objects.requireNonNull(paramArrayOfPerson[b], "persons cannot contain null"); 
    this.mPersons = ShortcutInfo.access$1800(paramArrayOfPerson);
    return this;
  }
  
  public Builder setRank(int paramInt) {
    boolean bool;
    if (paramInt >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "Rank cannot be negative or bigger than MAX_RANK");
    this.mRank = paramInt;
    return this;
  }
  
  public Builder setShortLabel(CharSequence paramCharSequence) {
    boolean bool;
    if (this.mTitleResId == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool, "shortLabelResId already set");
    this.mTitle = Preconditions.checkStringNotEmpty(paramCharSequence, "shortLabel cannot be empty");
    return this;
  }
  
  @Deprecated
  public Builder setShortLabelResId(int paramInt) {
    boolean bool;
    if (this.mTitle == null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkState(bool, "shortLabel already set");
    this.mTitleResId = paramInt;
    return this;
  }
  
  @Deprecated
  public Builder setText(CharSequence paramCharSequence) {
    return setLongLabel(paramCharSequence);
  }
  
  @Deprecated
  public Builder setTextResId(int paramInt) {
    return setLongLabelResId(paramInt);
  }
  
  @Deprecated
  public Builder setTitle(CharSequence paramCharSequence) {
    return setShortLabel(paramCharSequence);
  }
  
  @Deprecated
  public Builder setTitleResId(int paramInt) {
    return setShortLabelResId(paramInt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ShortcutInfo$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
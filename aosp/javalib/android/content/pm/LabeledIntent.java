package android.content.pm;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class LabeledIntent extends Intent {
  public static final Parcelable.Creator<LabeledIntent> CREATOR = new Parcelable.Creator<LabeledIntent>() {
      public LabeledIntent createFromParcel(Parcel param1Parcel) {
        return new LabeledIntent(param1Parcel);
      }
      
      public LabeledIntent[] newArray(int param1Int) {
        return new LabeledIntent[param1Int];
      }
    };
  
  private int mIcon;
  
  private int mLabelRes;
  
  private CharSequence mNonLocalizedLabel;
  
  private String mSourcePackage;
  
  public LabeledIntent(Intent paramIntent, String paramString, int paramInt1, int paramInt2) {
    super(paramIntent);
    this.mSourcePackage = paramString;
    this.mLabelRes = paramInt1;
    this.mNonLocalizedLabel = null;
    this.mIcon = paramInt2;
  }
  
  public LabeledIntent(Intent paramIntent, String paramString, CharSequence paramCharSequence, int paramInt) {
    super(paramIntent);
    this.mSourcePackage = paramString;
    this.mLabelRes = 0;
    this.mNonLocalizedLabel = paramCharSequence;
    this.mIcon = paramInt;
  }
  
  protected LabeledIntent(Parcel paramParcel) {
    readFromParcel(paramParcel);
  }
  
  public LabeledIntent(String paramString, int paramInt1, int paramInt2) {
    this.mSourcePackage = paramString;
    this.mLabelRes = paramInt1;
    this.mNonLocalizedLabel = null;
    this.mIcon = paramInt2;
  }
  
  public LabeledIntent(String paramString, CharSequence paramCharSequence, int paramInt) {
    this.mSourcePackage = paramString;
    this.mLabelRes = 0;
    this.mNonLocalizedLabel = paramCharSequence;
    this.mIcon = paramInt;
  }
  
  public int getIconResource() {
    return this.mIcon;
  }
  
  public int getLabelResource() {
    return this.mLabelRes;
  }
  
  public CharSequence getNonLocalizedLabel() {
    return this.mNonLocalizedLabel;
  }
  
  public String getSourcePackage() {
    return this.mSourcePackage;
  }
  
  public Drawable loadIcon(PackageManager paramPackageManager) {
    int i = this.mIcon;
    if (i != 0) {
      String str = this.mSourcePackage;
      if (str != null) {
        Drawable drawable = paramPackageManager.getDrawable(str, i, null);
        if (drawable != null)
          return drawable; 
      } 
    } 
    return null;
  }
  
  public CharSequence loadLabel(PackageManager paramPackageManager) {
    CharSequence charSequence = this.mNonLocalizedLabel;
    if (charSequence != null)
      return charSequence; 
    int i = this.mLabelRes;
    if (i != 0) {
      charSequence = this.mSourcePackage;
      if (charSequence != null) {
        CharSequence charSequence1 = paramPackageManager.getText((String)charSequence, i, null);
        if (charSequence1 != null)
          return charSequence1; 
      } 
    } 
    return null;
  }
  
  public void readFromParcel(Parcel paramParcel) {
    super.readFromParcel(paramParcel);
    this.mSourcePackage = paramParcel.readString();
    this.mLabelRes = paramParcel.readInt();
    this.mNonLocalizedLabel = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel);
    this.mIcon = paramParcel.readInt();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.mSourcePackage);
    paramParcel.writeInt(this.mLabelRes);
    TextUtils.writeToParcel(this.mNonLocalizedLabel, paramParcel, paramInt);
    paramParcel.writeInt(this.mIcon);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/LabeledIntent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
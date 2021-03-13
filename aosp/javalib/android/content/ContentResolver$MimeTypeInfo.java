package android.content;

import android.graphics.drawable.Icon;
import java.util.Objects;

public final class MimeTypeInfo {
  private final CharSequence mContentDescription;
  
  private final Icon mIcon;
  
  private final CharSequence mLabel;
  
  public MimeTypeInfo(Icon paramIcon, CharSequence paramCharSequence1, CharSequence paramCharSequence2) {
    Objects.requireNonNull(paramIcon);
    this.mIcon = paramIcon;
    Objects.requireNonNull(paramCharSequence1);
    this.mLabel = paramCharSequence1;
    Objects.requireNonNull(paramCharSequence2);
    this.mContentDescription = paramCharSequence2;
  }
  
  public CharSequence getContentDescription() {
    return this.mContentDescription;
  }
  
  public Icon getIcon() {
    return this.mIcon;
  }
  
  public CharSequence getLabel() {
    return this.mLabel;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentResolver$MimeTypeInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
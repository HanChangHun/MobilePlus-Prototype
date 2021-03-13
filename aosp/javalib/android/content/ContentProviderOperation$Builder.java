package android.content;

import android.net.Uri;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.SparseArray;
import java.util.Objects;

public class Builder {
  private final String mArg;
  
  private boolean mExceptionAllowed;
  
  private Integer mExpectedCount;
  
  private ArrayMap<String, Object> mExtras;
  
  private final String mMethod;
  
  private String mSelection;
  
  private SparseArray<Object> mSelectionArgs;
  
  private final int mType;
  
  private final Uri mUri;
  
  private ArrayMap<String, Object> mValues;
  
  private boolean mYieldAllowed;
  
  private Builder(int paramInt, Uri paramUri) {
    this(paramInt, paramUri, null, null);
  }
  
  private Builder(int paramInt, Uri paramUri, String paramString1, String paramString2) {
    this.mType = paramInt;
    Objects.requireNonNull(paramUri);
    this.mUri = paramUri;
    this.mMethod = paramString1;
    this.mArg = paramString2;
  }
  
  private void assertExtrasAllowed() {
    int i = this.mType;
    if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Extras not supported for ");
    stringBuilder.append(ContentProviderOperation.typeToString(this.mType));
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private void assertSelectionAllowed() {
    int i = this.mType;
    if (i == 2 || i == 3 || i == 4)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Selection not supported for ");
    stringBuilder.append(ContentProviderOperation.typeToString(this.mType));
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private void assertValuesAllowed() {
    int i = this.mType;
    if (i == 1 || i == 2 || i == 4)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Values not supported for ");
    stringBuilder.append(ContentProviderOperation.typeToString(this.mType));
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  private void ensureExtras() {
    if (this.mExtras == null)
      this.mExtras = new ArrayMap(); 
  }
  
  private void ensureSelectionArgs() {
    if (this.mSelectionArgs == null)
      this.mSelectionArgs = new SparseArray(); 
  }
  
  private void ensureValues() {
    if (this.mValues == null)
      this.mValues = new ArrayMap(); 
  }
  
  private void setExtra(String paramString, Object paramObject) {
    ensureExtras();
    boolean bool1 = this.mExtras.get(paramString) instanceof ContentProviderOperation.BackReference;
    boolean bool2 = paramObject instanceof ContentProviderOperation.BackReference;
    if (!bool1 || bool2)
      this.mExtras.put(paramString, paramObject); 
  }
  
  private void setSelectionArg(int paramInt, Object paramObject) {
    ensureSelectionArgs();
    boolean bool1 = this.mSelectionArgs.get(paramInt) instanceof ContentProviderOperation.BackReference;
    boolean bool2 = paramObject instanceof ContentProviderOperation.BackReference;
    if (!bool1 || bool2)
      this.mSelectionArgs.put(paramInt, paramObject); 
  }
  
  private void setValue(String paramString, Object paramObject) {
    ensureValues();
    boolean bool1 = this.mValues.get(paramString) instanceof ContentProviderOperation.BackReference;
    boolean bool2 = paramObject instanceof ContentProviderOperation.BackReference;
    if (!bool1 || bool2)
      this.mValues.put(paramString, paramObject); 
  }
  
  public ContentProviderOperation build() {
    if (this.mType == 2) {
      ArrayMap<String, Object> arrayMap = this.mValues;
      if (arrayMap == null || arrayMap.isEmpty())
        throw new IllegalArgumentException("Empty values"); 
    } 
    if (this.mType == 4) {
      ArrayMap<String, Object> arrayMap = this.mValues;
      if ((arrayMap == null || arrayMap.isEmpty()) && this.mExpectedCount == null)
        throw new IllegalArgumentException("Empty values"); 
    } 
    return new ContentProviderOperation(this, null);
  }
  
  public Builder withExceptionAllowed(boolean paramBoolean) {
    this.mExceptionAllowed = paramBoolean;
    return this;
  }
  
  public Builder withExpectedCount(int paramInt) {
    int i = this.mType;
    if (i == 2 || i == 3 || i == 4) {
      this.mExpectedCount = Integer.valueOf(paramInt);
      return this;
    } 
    throw new IllegalArgumentException("only updates, deletes, and asserts can have expected counts");
  }
  
  public Builder withExtra(String paramString, Object paramObject) {
    assertExtrasAllowed();
    setExtra(paramString, paramObject);
    return this;
  }
  
  public Builder withExtraBackReference(String paramString, int paramInt) {
    assertExtrasAllowed();
    setExtra(paramString, new ContentProviderOperation.BackReference(paramInt, null, null));
    return this;
  }
  
  public Builder withExtraBackReference(String paramString1, int paramInt, String paramString2) {
    assertExtrasAllowed();
    setExtra(paramString1, new ContentProviderOperation.BackReference(paramInt, paramString2, null));
    return this;
  }
  
  public Builder withExtras(Bundle paramBundle) {
    assertExtrasAllowed();
    ensureExtras();
    for (String str : paramBundle.keySet())
      setExtra(str, paramBundle.get(str)); 
    return this;
  }
  
  public Builder withFailureAllowed(boolean paramBoolean) {
    return withExceptionAllowed(paramBoolean);
  }
  
  public Builder withSelection(String paramString, String[] paramArrayOfString) {
    assertSelectionAllowed();
    this.mSelection = paramString;
    if (paramArrayOfString != null) {
      ensureSelectionArgs();
      for (byte b = 0; b < paramArrayOfString.length; b++)
        setSelectionArg(b, paramArrayOfString[b]); 
    } 
    return this;
  }
  
  public Builder withSelectionBackReference(int paramInt1, int paramInt2) {
    assertSelectionAllowed();
    setSelectionArg(paramInt1, new ContentProviderOperation.BackReference(paramInt2, null, null));
    return this;
  }
  
  public Builder withSelectionBackReference(int paramInt1, int paramInt2, String paramString) {
    assertSelectionAllowed();
    setSelectionArg(paramInt1, new ContentProviderOperation.BackReference(paramInt2, paramString, null));
    return this;
  }
  
  public Builder withValue(String paramString, Object paramObject) {
    assertValuesAllowed();
    if (ContentValues.isSupportedValue(paramObject)) {
      setValue(paramString, paramObject);
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("bad value type: ");
    stringBuilder.append(paramObject.getClass().getName());
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public Builder withValueBackReference(String paramString, int paramInt) {
    assertValuesAllowed();
    setValue(paramString, new ContentProviderOperation.BackReference(paramInt, null, null));
    return this;
  }
  
  public Builder withValueBackReference(String paramString1, int paramInt, String paramString2) {
    assertValuesAllowed();
    setValue(paramString1, new ContentProviderOperation.BackReference(paramInt, paramString2, null));
    return this;
  }
  
  public Builder withValueBackReferences(ContentValues paramContentValues) {
    assertValuesAllowed();
    ArrayMap<String, Object> arrayMap = paramContentValues.getValues();
    for (byte b = 0; b < arrayMap.size(); b++)
      setValue((String)arrayMap.keyAt(b), new ContentProviderOperation.BackReference(((Integer)arrayMap.valueAt(b)).intValue(), null, null)); 
    return this;
  }
  
  public Builder withValues(ContentValues paramContentValues) {
    assertValuesAllowed();
    ensureValues();
    ArrayMap<String, Object> arrayMap = paramContentValues.getValues();
    for (byte b = 0; b < arrayMap.size(); b++)
      setValue((String)arrayMap.keyAt(b), arrayMap.valueAt(b)); 
    return this;
  }
  
  public Builder withYieldAllowed(boolean paramBoolean) {
    this.mYieldAllowed = paramBoolean;
    return this;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/ContentProviderOperation$Builder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
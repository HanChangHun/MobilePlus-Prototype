package android.app;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class RemoteInput implements Parcelable {
  public static final Parcelable.Creator<RemoteInput> CREATOR = new Parcelable.Creator<RemoteInput>() {
      public RemoteInput createFromParcel(Parcel param1Parcel) {
        return new RemoteInput(param1Parcel);
      }
      
      public RemoteInput[] newArray(int param1Int) {
        return new RemoteInput[param1Int];
      }
    };
  
  private static final int DEFAULT_FLAGS = 1;
  
  public static final int EDIT_CHOICES_BEFORE_SENDING_AUTO = 0;
  
  public static final int EDIT_CHOICES_BEFORE_SENDING_DISABLED = 1;
  
  public static final int EDIT_CHOICES_BEFORE_SENDING_ENABLED = 2;
  
  private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
  
  public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
  
  private static final String EXTRA_RESULTS_SOURCE = "android.remoteinput.resultsSource";
  
  private static final int FLAG_ALLOW_FREE_FORM_INPUT = 1;
  
  public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
  
  public static final int SOURCE_CHOICE = 1;
  
  public static final int SOURCE_FREE_FORM_INPUT = 0;
  
  private final ArraySet<String> mAllowedDataTypes;
  
  private final CharSequence[] mChoices;
  
  private final int mEditChoicesBeforeSending;
  
  private final Bundle mExtras;
  
  private final int mFlags;
  
  private final CharSequence mLabel;
  
  private final String mResultKey;
  
  private RemoteInput(Parcel paramParcel) {
    this.mResultKey = paramParcel.readString();
    this.mLabel = paramParcel.readCharSequence();
    this.mChoices = paramParcel.readCharSequenceArray();
    this.mFlags = paramParcel.readInt();
    this.mEditChoicesBeforeSending = paramParcel.readInt();
    this.mExtras = paramParcel.readBundle();
    this.mAllowedDataTypes = paramParcel.readArraySet(null);
  }
  
  private RemoteInput(String paramString, CharSequence paramCharSequence, CharSequence[] paramArrayOfCharSequence, int paramInt1, int paramInt2, Bundle paramBundle, ArraySet<String> paramArraySet) {
    this.mResultKey = paramString;
    this.mLabel = paramCharSequence;
    this.mChoices = paramArrayOfCharSequence;
    this.mFlags = paramInt1;
    this.mEditChoicesBeforeSending = paramInt2;
    this.mExtras = paramBundle;
    this.mAllowedDataTypes = paramArraySet;
    if (getEditChoicesBeforeSending() != 2 || getAllowFreeFormInput())
      return; 
    throw new IllegalArgumentException("setEditChoicesBeforeSending requires setAllowFreeFormInput");
  }
  
  public static void addDataResultToIntent(RemoteInput paramRemoteInput, Intent paramIntent, Map<String, Uri> paramMap) {
    Intent intent1 = getClipDataIntentFromIntent(paramIntent);
    Intent intent2 = intent1;
    if (intent1 == null)
      intent2 = new Intent(); 
    for (Map.Entry<String, Uri> entry : paramMap.entrySet()) {
      String str = (String)entry.getKey();
      Uri uri = (Uri)entry.getValue();
      if (str == null)
        continue; 
      Bundle bundle2 = intent2.getBundleExtra(getExtraResultsKeyForData(str));
      Bundle bundle1 = bundle2;
      if (bundle2 == null)
        bundle1 = new Bundle(); 
      bundle1.putString(paramRemoteInput.getResultKey(), uri.toString());
      intent2.putExtra(getExtraResultsKeyForData(str), bundle1);
    } 
    paramIntent.setClipData(ClipData.newIntent("android.remoteinput.results", intent2));
  }
  
  public static void addResultsToIntent(RemoteInput[] paramArrayOfRemoteInput, Intent paramIntent, Bundle paramBundle) {
    Intent intent1 = getClipDataIntentFromIntent(paramIntent);
    Intent intent2 = intent1;
    if (intent1 == null)
      intent2 = new Intent(); 
    Bundle bundle2 = intent2.getBundleExtra("android.remoteinput.resultsData");
    Bundle bundle1 = bundle2;
    if (bundle2 == null)
      bundle1 = new Bundle(); 
    int i = paramArrayOfRemoteInput.length;
    for (byte b = 0; b < i; b++) {
      RemoteInput remoteInput = paramArrayOfRemoteInput[b];
      Object object = paramBundle.get(remoteInput.getResultKey());
      if (object instanceof CharSequence)
        bundle1.putCharSequence(remoteInput.getResultKey(), (CharSequence)object); 
    } 
    intent2.putExtra("android.remoteinput.resultsData", bundle1);
    paramIntent.setClipData(ClipData.newIntent("android.remoteinput.results", intent2));
  }
  
  private static Intent getClipDataIntentFromIntent(Intent paramIntent) {
    ClipData clipData = paramIntent.getClipData();
    if (clipData == null)
      return null; 
    ClipDescription clipDescription = clipData.getDescription();
    return !clipDescription.hasMimeType("text/vnd.android.intent") ? null : (!clipDescription.getLabel().equals("android.remoteinput.results") ? null : clipData.getItemAt(0).getIntent());
  }
  
  public static Map<String, Uri> getDataResultsFromIntent(Intent paramIntent, String paramString) {
    HashMap<Object, Object> hashMap1;
    Intent intent = getClipDataIntentFromIntent(paramIntent);
    paramIntent = null;
    if (intent == null)
      return null; 
    HashMap<Object, Object> hashMap2 = new HashMap<>();
    for (String str : intent.getExtras().keySet()) {
      if (str.startsWith("android.remoteinput.dataTypeResultsData")) {
        String str1 = str.substring("android.remoteinput.dataTypeResultsData".length());
        if (str1 == null || str1.isEmpty())
          continue; 
        str = intent.getBundleExtra(str).getString(paramString);
        if (str == null || str.isEmpty())
          continue; 
        hashMap2.put(str1, Uri.parse(str));
      } 
    } 
    if (!hashMap2.isEmpty())
      hashMap1 = hashMap2; 
    return (Map)hashMap1;
  }
  
  private static String getExtraResultsKeyForData(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("android.remoteinput.dataTypeResultsData");
    stringBuilder.append(paramString);
    return stringBuilder.toString();
  }
  
  public static Bundle getResultsFromIntent(Intent paramIntent) {
    paramIntent = getClipDataIntentFromIntent(paramIntent);
    return (paramIntent == null) ? null : (Bundle)paramIntent.getExtras().getParcelable("android.remoteinput.resultsData");
  }
  
  public static int getResultsSource(Intent paramIntent) {
    paramIntent = getClipDataIntentFromIntent(paramIntent);
    return (paramIntent == null) ? 0 : paramIntent.getExtras().getInt("android.remoteinput.resultsSource", 0);
  }
  
  public static void setResultsSource(Intent paramIntent, int paramInt) {
    Intent intent1 = getClipDataIntentFromIntent(paramIntent);
    Intent intent2 = intent1;
    if (intent1 == null)
      intent2 = new Intent(); 
    intent2.putExtra("android.remoteinput.resultsSource", paramInt);
    paramIntent.setClipData(ClipData.newIntent("android.remoteinput.results", intent2));
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean getAllowFreeFormInput() {
    int i = this.mFlags;
    boolean bool = true;
    if ((i & 0x1) == 0)
      bool = false; 
    return bool;
  }
  
  public Set<String> getAllowedDataTypes() {
    return (Set<String>)this.mAllowedDataTypes;
  }
  
  public CharSequence[] getChoices() {
    return this.mChoices;
  }
  
  public int getEditChoicesBeforeSending() {
    return this.mEditChoicesBeforeSending;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public CharSequence getLabel() {
    return this.mLabel;
  }
  
  public String getResultKey() {
    return this.mResultKey;
  }
  
  public boolean isDataOnly() {
    boolean bool;
    if (!getAllowFreeFormInput() && (getChoices() == null || (getChoices()).length == 0) && !getAllowedDataTypes().isEmpty()) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeString(this.mResultKey);
    paramParcel.writeCharSequence(this.mLabel);
    paramParcel.writeCharSequenceArray(this.mChoices);
    paramParcel.writeInt(this.mFlags);
    paramParcel.writeInt(this.mEditChoicesBeforeSending);
    paramParcel.writeBundle(this.mExtras);
    paramParcel.writeArraySet(this.mAllowedDataTypes);
  }
  
  public static final class Builder {
    private final ArraySet<String> mAllowedDataTypes = new ArraySet();
    
    private CharSequence[] mChoices;
    
    private int mEditChoicesBeforeSending = 0;
    
    private final Bundle mExtras = new Bundle();
    
    private int mFlags = 1;
    
    private CharSequence mLabel;
    
    private final String mResultKey;
    
    public Builder(String param1String) {
      if (param1String != null) {
        this.mResultKey = param1String;
        return;
      } 
      throw new IllegalArgumentException("Result key can't be null");
    }
    
    private void setFlag(int param1Int, boolean param1Boolean) {
      if (param1Boolean) {
        this.mFlags |= param1Int;
      } else {
        this.mFlags &= param1Int;
      } 
    }
    
    public Builder addExtras(Bundle param1Bundle) {
      if (param1Bundle != null)
        this.mExtras.putAll(param1Bundle); 
      return this;
    }
    
    public RemoteInput build() {
      return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mFlags, this.mEditChoicesBeforeSending, this.mExtras, this.mAllowedDataTypes);
    }
    
    public Bundle getExtras() {
      return this.mExtras;
    }
    
    public Builder setAllowDataType(String param1String, boolean param1Boolean) {
      if (param1Boolean) {
        this.mAllowedDataTypes.add(param1String);
      } else {
        this.mAllowedDataTypes.remove(param1String);
      } 
      return this;
    }
    
    public Builder setAllowFreeFormInput(boolean param1Boolean) {
      setFlag(1, param1Boolean);
      return this;
    }
    
    public Builder setChoices(CharSequence[] param1ArrayOfCharSequence) {
      if (param1ArrayOfCharSequence == null) {
        this.mChoices = null;
      } else {
        this.mChoices = new CharSequence[param1ArrayOfCharSequence.length];
        for (byte b = 0; b < param1ArrayOfCharSequence.length; b++)
          this.mChoices[b] = Notification.safeCharSequence(param1ArrayOfCharSequence[b]); 
      } 
      return this;
    }
    
    public Builder setEditChoicesBeforeSending(int param1Int) {
      this.mEditChoicesBeforeSending = param1Int;
      return this;
    }
    
    public Builder setLabel(CharSequence param1CharSequence) {
      this.mLabel = Notification.safeCharSequence(param1CharSequence);
      return this;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface EditChoicesBeforeSending {}
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Source {}
}


/* Location:              /home/chun/Desktop/temp/!/android/app/RemoteInput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
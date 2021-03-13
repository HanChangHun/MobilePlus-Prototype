package android.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.DebugUtils;

public class Prompt implements Parcelable {
  public static final Parcelable.Creator<Prompt> CREATOR = new Parcelable.Creator<Prompt>() {
      public VoiceInteractor.Prompt createFromParcel(Parcel param2Parcel) {
        return new VoiceInteractor.Prompt(param2Parcel);
      }
      
      public VoiceInteractor.Prompt[] newArray(int param2Int) {
        return new VoiceInteractor.Prompt[param2Int];
      }
    };
  
  private final CharSequence mVisualPrompt;
  
  private final CharSequence[] mVoicePrompts;
  
  Prompt(Parcel paramParcel) {
    this.mVoicePrompts = paramParcel.readCharSequenceArray();
    this.mVisualPrompt = paramParcel.readCharSequence();
  }
  
  public Prompt(CharSequence paramCharSequence) {
    this.mVoicePrompts = new CharSequence[] { paramCharSequence };
    this.mVisualPrompt = paramCharSequence;
  }
  
  public Prompt(CharSequence[] paramArrayOfCharSequence, CharSequence paramCharSequence) {
    if (paramArrayOfCharSequence != null) {
      if (paramArrayOfCharSequence.length != 0) {
        if (paramCharSequence != null) {
          this.mVoicePrompts = paramArrayOfCharSequence;
          this.mVisualPrompt = paramCharSequence;
          return;
        } 
        throw new NullPointerException("visualPrompt must not be null");
      } 
      throw new IllegalArgumentException("voicePrompts must not be empty");
    } 
    throw new NullPointerException("voicePrompts must not be null");
  }
  
  public int countVoicePrompts() {
    return this.mVoicePrompts.length;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public CharSequence getVisualPrompt() {
    return this.mVisualPrompt;
  }
  
  public CharSequence getVoicePromptAt(int paramInt) {
    return this.mVoicePrompts[paramInt];
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    DebugUtils.buildShortClassTag(this, stringBuilder);
    CharSequence charSequence = this.mVisualPrompt;
    if (charSequence != null) {
      CharSequence[] arrayOfCharSequence = this.mVoicePrompts;
      if (arrayOfCharSequence != null && arrayOfCharSequence.length == 1 && charSequence.equals(arrayOfCharSequence[0])) {
        stringBuilder.append(" ");
        stringBuilder.append(this.mVisualPrompt);
        stringBuilder.append('}');
        return stringBuilder.toString();
      } 
    } 
    if (this.mVisualPrompt != null) {
      stringBuilder.append(" visual=");
      stringBuilder.append(this.mVisualPrompt);
    } 
    if (this.mVoicePrompts != null) {
      stringBuilder.append(", voice=");
      for (byte b = 0; b < this.mVoicePrompts.length; b++) {
        if (b > 0)
          stringBuilder.append(" | "); 
        stringBuilder.append(this.mVoicePrompts[b]);
      } 
    } 
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeCharSequenceArray(this.mVoicePrompts);
    paramParcel.writeCharSequence(this.mVisualPrompt);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VoiceInteractor$Prompt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
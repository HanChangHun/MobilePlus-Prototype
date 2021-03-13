package android.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class Option implements Parcelable {
  public static final Parcelable.Creator<Option> CREATOR = new Parcelable.Creator<Option>() {
      public VoiceInteractor.PickOptionRequest.Option createFromParcel(Parcel param3Parcel) {
        return new VoiceInteractor.PickOptionRequest.Option(param3Parcel);
      }
      
      public VoiceInteractor.PickOptionRequest.Option[] newArray(int param3Int) {
        return new VoiceInteractor.PickOptionRequest.Option[param3Int];
      }
    };
  
  Bundle mExtras;
  
  final int mIndex;
  
  final CharSequence mLabel;
  
  ArrayList<CharSequence> mSynonyms;
  
  Option(Parcel paramParcel) {
    this.mLabel = paramParcel.readCharSequence();
    this.mIndex = paramParcel.readInt();
    this.mSynonyms = paramParcel.readCharSequenceList();
    this.mExtras = paramParcel.readBundle();
  }
  
  public Option(CharSequence paramCharSequence) {
    this.mLabel = paramCharSequence;
    this.mIndex = -1;
  }
  
  public Option(CharSequence paramCharSequence, int paramInt) {
    this.mLabel = paramCharSequence;
    this.mIndex = paramInt;
  }
  
  public Option addSynonym(CharSequence paramCharSequence) {
    if (this.mSynonyms == null)
      this.mSynonyms = new ArrayList<>(); 
    this.mSynonyms.add(paramCharSequence);
    return this;
  }
  
  public int countSynonyms() {
    boolean bool;
    ArrayList<CharSequence> arrayList = this.mSynonyms;
    if (arrayList != null) {
      bool = arrayList.size();
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public Bundle getExtras() {
    return this.mExtras;
  }
  
  public int getIndex() {
    return this.mIndex;
  }
  
  public CharSequence getLabel() {
    return this.mLabel;
  }
  
  public CharSequence getSynonymAt(int paramInt) {
    ArrayList<CharSequence> arrayList = this.mSynonyms;
    if (arrayList != null) {
      CharSequence charSequence = arrayList.get(paramInt);
    } else {
      arrayList = null;
    } 
    return (CharSequence)arrayList;
  }
  
  public void setExtras(Bundle paramBundle) {
    this.mExtras = paramBundle;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramParcel.writeCharSequence(this.mLabel);
    paramParcel.writeInt(this.mIndex);
    paramParcel.writeCharSequenceList(this.mSynonyms);
    paramParcel.writeBundle(this.mExtras);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VoiceInteractor$PickOptionRequest$Option.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
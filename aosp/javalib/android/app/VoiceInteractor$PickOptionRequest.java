package android.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.android.internal.app.IVoiceInteractor;
import com.android.internal.app.IVoiceInteractorCallback;
import com.android.internal.app.IVoiceInteractorRequest;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PickOptionRequest extends VoiceInteractor.Request {
  final Bundle mExtras;
  
  final Option[] mOptions;
  
  final VoiceInteractor.Prompt mPrompt;
  
  public PickOptionRequest(VoiceInteractor.Prompt paramPrompt, Option[] paramArrayOfOption, Bundle paramBundle) {
    this.mPrompt = paramPrompt;
    this.mOptions = paramArrayOfOption;
    this.mExtras = paramBundle;
  }
  
  public PickOptionRequest(CharSequence paramCharSequence, Option[] paramArrayOfOption, Bundle paramBundle) {
    if (paramCharSequence != null) {
      VoiceInteractor.Prompt prompt = new VoiceInteractor.Prompt(paramCharSequence);
    } else {
      paramCharSequence = null;
    } 
    this.mPrompt = (VoiceInteractor.Prompt)paramCharSequence;
    this.mOptions = paramArrayOfOption;
    this.mExtras = paramBundle;
  }
  
  void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mPrompt=");
    paramPrintWriter.println(this.mPrompt);
    if (this.mOptions != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Options:");
      byte b = 0;
      while (true) {
        Option[] arrayOfOption = this.mOptions;
        if (b < arrayOfOption.length) {
          Option option = arrayOfOption[b];
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(b);
          paramPrintWriter.println(":");
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("    mLabel=");
          paramPrintWriter.println(option.mLabel);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("    mIndex=");
          paramPrintWriter.println(option.mIndex);
          if (option.mSynonyms != null && option.mSynonyms.size() > 0) {
            paramPrintWriter.print(paramString);
            paramPrintWriter.println("    Synonyms:");
            for (byte b1 = 0; b1 < option.mSynonyms.size(); b1++) {
              paramPrintWriter.print(paramString);
              paramPrintWriter.print("      #");
              paramPrintWriter.print(b1);
              paramPrintWriter.print(": ");
              paramPrintWriter.println(option.mSynonyms.get(b1));
            } 
          } 
          if (option.mExtras != null) {
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("    mExtras=");
            paramPrintWriter.println(option.mExtras);
          } 
          b++;
          continue;
        } 
        break;
      } 
    } 
    if (this.mExtras != null) {
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("mExtras=");
      paramPrintWriter.println(this.mExtras);
    } 
  }
  
  String getRequestTypeName() {
    return "PickOption";
  }
  
  public void onPickOptionResult(boolean paramBoolean, Option[] paramArrayOfOption, Bundle paramBundle) {}
  
  IVoiceInteractorRequest submit(IVoiceInteractor paramIVoiceInteractor, String paramString, IVoiceInteractorCallback paramIVoiceInteractorCallback) throws RemoteException {
    return paramIVoiceInteractor.startPickOption(paramString, paramIVoiceInteractorCallback, this.mPrompt, this.mOptions, this.mExtras);
  }
  
  public static final class Option implements Parcelable {
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
    
    Option(Parcel param2Parcel) {
      this.mLabel = param2Parcel.readCharSequence();
      this.mIndex = param2Parcel.readInt();
      this.mSynonyms = param2Parcel.readCharSequenceList();
      this.mExtras = param2Parcel.readBundle();
    }
    
    public Option(CharSequence param2CharSequence) {
      this.mLabel = param2CharSequence;
      this.mIndex = -1;
    }
    
    public Option(CharSequence param2CharSequence, int param2Int) {
      this.mLabel = param2CharSequence;
      this.mIndex = param2Int;
    }
    
    public Option addSynonym(CharSequence param2CharSequence) {
      if (this.mSynonyms == null)
        this.mSynonyms = new ArrayList<>(); 
      this.mSynonyms.add(param2CharSequence);
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
    
    public CharSequence getSynonymAt(int param2Int) {
      ArrayList<CharSequence> arrayList = this.mSynonyms;
      if (arrayList != null) {
        CharSequence charSequence = arrayList.get(param2Int);
      } else {
        arrayList = null;
      } 
      return (CharSequence)arrayList;
    }
    
    public void setExtras(Bundle param2Bundle) {
      this.mExtras = param2Bundle;
    }
    
    public void writeToParcel(Parcel param2Parcel, int param2Int) {
      param2Parcel.writeCharSequence(this.mLabel);
      param2Parcel.writeInt(this.mIndex);
      param2Parcel.writeCharSequenceList(this.mSynonyms);
      param2Parcel.writeBundle(this.mExtras);
    }
  }
  
  class null implements Parcelable.Creator<Option> {
    public VoiceInteractor.PickOptionRequest.Option createFromParcel(Parcel param2Parcel) {
      return new VoiceInteractor.PickOptionRequest.Option(param2Parcel);
    }
    
    public VoiceInteractor.PickOptionRequest.Option[] newArray(int param2Int) {
      return new VoiceInteractor.PickOptionRequest.Option[param2Int];
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/VoiceInteractor$PickOptionRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
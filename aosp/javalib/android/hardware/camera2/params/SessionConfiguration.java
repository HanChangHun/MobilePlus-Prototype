package android.hardware.camera2.params;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.utils.HashCodeHelpers;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public final class SessionConfiguration implements Parcelable {
  public static final Parcelable.Creator<SessionConfiguration> CREATOR = new Parcelable.Creator<SessionConfiguration>() {
      public SessionConfiguration createFromParcel(Parcel param1Parcel) {
        try {
          return new SessionConfiguration(param1Parcel);
        } catch (Exception exception) {
          Log.e("SessionConfiguration", "Exception creating SessionConfiguration from parcel", exception);
          return null;
        } 
      }
      
      public SessionConfiguration[] newArray(int param1Int) {
        return new SessionConfiguration[param1Int];
      }
    };
  
  public static final int SESSION_HIGH_SPEED = 1;
  
  public static final int SESSION_REGULAR = 0;
  
  public static final int SESSION_VENDOR_START = 32768;
  
  private static final String TAG = "SessionConfiguration";
  
  private Executor mExecutor = null;
  
  private InputConfiguration mInputConfig = null;
  
  private List<OutputConfiguration> mOutputConfigurations;
  
  private CaptureRequest mSessionParameters = null;
  
  private int mSessionType;
  
  private CameraCaptureSession.StateCallback mStateCallback;
  
  public SessionConfiguration(int paramInt, List<OutputConfiguration> paramList, Executor paramExecutor, CameraCaptureSession.StateCallback paramStateCallback) {
    this.mSessionType = paramInt;
    this.mOutputConfigurations = Collections.unmodifiableList(new ArrayList<>(paramList));
    this.mStateCallback = paramStateCallback;
    this.mExecutor = paramExecutor;
  }
  
  private SessionConfiguration(Parcel paramParcel) {
    int i = paramParcel.readInt();
    int j = paramParcel.readInt();
    int k = paramParcel.readInt();
    int m = paramParcel.readInt();
    ArrayList<OutputConfiguration> arrayList = new ArrayList();
    paramParcel.readTypedList(arrayList, OutputConfiguration.CREATOR);
    if (j > 0 && k > 0 && m != -1)
      this.mInputConfig = new InputConfiguration(j, k, m); 
    this.mSessionType = i;
    this.mOutputConfigurations = arrayList;
  }
  
  public int describeContents() {
    return 0;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == null)
      return false; 
    if (this == paramObject)
      return true; 
    if (paramObject instanceof SessionConfiguration) {
      paramObject = paramObject;
      if (this.mInputConfig != ((SessionConfiguration)paramObject).mInputConfig || this.mSessionType != ((SessionConfiguration)paramObject).mSessionType || this.mOutputConfigurations.size() != ((SessionConfiguration)paramObject).mOutputConfigurations.size())
        return false; 
      for (byte b = 0; b < this.mOutputConfigurations.size(); b++) {
        if (!((OutputConfiguration)this.mOutputConfigurations.get(b)).equals(((SessionConfiguration)paramObject).mOutputConfigurations.get(b)))
          return false; 
      } 
      return true;
    } 
    return false;
  }
  
  public Executor getExecutor() {
    return this.mExecutor;
  }
  
  public InputConfiguration getInputConfiguration() {
    return this.mInputConfig;
  }
  
  public List<OutputConfiguration> getOutputConfigurations() {
    return this.mOutputConfigurations;
  }
  
  public CaptureRequest getSessionParameters() {
    return this.mSessionParameters;
  }
  
  public int getSessionType() {
    return this.mSessionType;
  }
  
  public CameraCaptureSession.StateCallback getStateCallback() {
    return this.mStateCallback;
  }
  
  public int hashCode() {
    return HashCodeHelpers.hashCode(new int[] { this.mOutputConfigurations.hashCode(), this.mInputConfig.hashCode(), this.mSessionType });
  }
  
  public void setInputConfiguration(InputConfiguration paramInputConfiguration) {
    if (this.mSessionType != 1) {
      this.mInputConfig = paramInputConfiguration;
      return;
    } 
    throw new UnsupportedOperationException("Method not supported for high speed session types");
  }
  
  public void setSessionParameters(CaptureRequest paramCaptureRequest) {
    this.mSessionParameters = paramCaptureRequest;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    if (paramParcel != null) {
      paramParcel.writeInt(this.mSessionType);
      InputConfiguration inputConfiguration = this.mInputConfig;
      if (inputConfiguration != null) {
        paramParcel.writeInt(inputConfiguration.getWidth());
        paramParcel.writeInt(this.mInputConfig.getHeight());
        paramParcel.writeInt(this.mInputConfig.getFormat());
      } else {
        paramParcel.writeInt(0);
        paramParcel.writeInt(0);
        paramParcel.writeInt(-1);
      } 
      paramParcel.writeTypedList(this.mOutputConfigurations);
      return;
    } 
    throw new IllegalArgumentException("dest must not be null");
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface SessionMode {}
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/SessionConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
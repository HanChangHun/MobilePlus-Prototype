package android.hardware.radio;

import android.annotation.SystemApi;
import android.content.Context;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@SystemApi
public class RadioManager {
  public static final int BAND_AM = 0;
  
  public static final int BAND_AM_HD = 3;
  
  public static final int BAND_FM = 1;
  
  public static final int BAND_FM_HD = 2;
  
  public static final int BAND_INVALID = -1;
  
  public static final int CLASS_AM_FM = 0;
  
  public static final int CLASS_DT = 2;
  
  public static final int CLASS_SAT = 1;
  
  public static final int CONFIG_DAB_DAB_LINKING = 6;
  
  public static final int CONFIG_DAB_DAB_SOFT_LINKING = 8;
  
  public static final int CONFIG_DAB_FM_LINKING = 7;
  
  public static final int CONFIG_DAB_FM_SOFT_LINKING = 9;
  
  public static final int CONFIG_FORCE_ANALOG = 2;
  
  public static final int CONFIG_FORCE_DIGITAL = 3;
  
  public static final int CONFIG_FORCE_MONO = 1;
  
  public static final int CONFIG_RDS_AF = 4;
  
  public static final int CONFIG_RDS_REG = 5;
  
  public static final int REGION_ITU_1 = 0;
  
  public static final int REGION_ITU_2 = 1;
  
  public static final int REGION_JAPAN = 3;
  
  public static final int REGION_KOREA = 4;
  
  public static final int REGION_OIRT = 2;
  
  public static final int STATUS_BAD_VALUE = -22;
  
  public static final int STATUS_DEAD_OBJECT = -32;
  
  public static final int STATUS_ERROR = -2147483648;
  
  public static final int STATUS_INVALID_OPERATION = -38;
  
  public static final int STATUS_NO_INIT = -19;
  
  public static final int STATUS_OK = 0;
  
  public static final int STATUS_PERMISSION_DENIED = -1;
  
  public static final int STATUS_TIMED_OUT = -110;
  
  private static final String TAG = "BroadcastRadio.manager";
  
  private final Map<Announcement.OnListUpdatedListener, ICloseHandle> mAnnouncementListeners = new HashMap<>();
  
  private final Context mContext;
  
  private final IRadioService mService;
  
  public RadioManager(Context paramContext) throws ServiceManager.ServiceNotFoundException {
    this.mContext = paramContext;
    this.mService = IRadioService.Stub.asInterface(ServiceManager.getServiceOrThrow("broadcastradio"));
  }
  
  private native int nativeListModules(List<ModuleProperties> paramList);
  
  public void addAnnouncementListener(Set<Integer> paramSet, Announcement.OnListUpdatedListener paramOnListUpdatedListener) {
    addAnnouncementListener((Executor)_$$Lambda$RadioManager$cfMLnpQqL72UMrjmCGbrhAOHHgg.INSTANCE, paramSet, paramOnListUpdatedListener);
  }
  
  public void addAnnouncementListener(final Executor executor, Set<Integer> paramSet, final Announcement.OnListUpdatedListener listener) {
    Objects.requireNonNull(executor);
    Objects.requireNonNull(listener);
    int[] arrayOfInt = paramSet.stream().mapToInt((ToIntFunction)_$$Lambda$UV1wDVoVlbcxpr8zevj_aMFtUGw.INSTANCE).toArray();
    IAnnouncementListener.Stub stub = new IAnnouncementListener.Stub() {
        public void onListUpdated(List<Announcement> param1List) {
          executor.execute(new _$$Lambda$RadioManager$1$yOwq8CG0kiZcgKFclFSIrjag008(listener, param1List));
        }
      };
    Map<Announcement.OnListUpdatedListener, ICloseHandle> map = this.mAnnouncementListeners;
    /* monitor enter ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/Map<[InnerObjectType{ObjectType{android/hardware/radio/Announcement}.Landroid/hardware/radio/Announcement$OnListUpdatedListener;}, ObjectType{android/hardware/radio/ICloseHandle}]>}, name=null} */
    executor = null;
    try {
      ICloseHandle iCloseHandle2 = this.mService.addAnnouncementListener(arrayOfInt, stub);
      ICloseHandle iCloseHandle1 = iCloseHandle2;
    } catch (RemoteException remoteException) {
      remoteException.rethrowFromSystemServer();
    } finally {}
    Objects.requireNonNull(executor);
    ICloseHandle iCloseHandle = (ICloseHandle)this.mAnnouncementListeners.put(listener, executor);
    if (iCloseHandle != null)
      Utils.close(iCloseHandle); 
    /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{java/util/Map<[InnerObjectType{ObjectType{android/hardware/radio/Announcement}.Landroid/hardware/radio/Announcement$OnListUpdatedListener;}, ObjectType{android/hardware/radio/ICloseHandle}]>}, name=null} */
  }
  
  public int listModules(List<ModuleProperties> paramList) {
    if (paramList == null) {
      Log.e("BroadcastRadio.manager", "the output list must not be empty");
      return -22;
    } 
    Log.d("BroadcastRadio.manager", "Listing available tuners...");
    try {
      List<ModuleProperties> list = this.mService.listModules();
      if (list == null) {
        Log.e("BroadcastRadio.manager", "Returned list was a null");
        return Integer.MIN_VALUE;
      } 
      paramList.addAll(list);
      return 0;
    } catch (RemoteException remoteException) {
      Log.e("BroadcastRadio.manager", "Failed listing available tuners", (Throwable)remoteException);
      return -32;
    } 
  }
  
  public RadioTuner openTuner(int paramInt, BandConfig paramBandConfig, boolean paramBoolean, RadioTuner.Callback paramCallback, Handler paramHandler) {
    if (paramCallback != null) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Opening tuner ");
      stringBuilder.append(paramInt);
      stringBuilder.append("...");
      Log.d("BroadcastRadio.manager", stringBuilder.toString());
      TunerCallbackAdapter tunerCallbackAdapter = new TunerCallbackAdapter(paramCallback, paramHandler);
      try {
        ITuner iTuner = this.mService.openTuner(paramInt, paramBandConfig, paramBoolean, tunerCallbackAdapter);
        if (iTuner == null) {
          Log.e("BroadcastRadio.manager", "Failed to open tuner");
          return null;
        } 
        if (paramBandConfig != null) {
          paramInt = paramBandConfig.getType();
        } else {
          paramInt = -1;
        } 
        return new TunerAdapter(iTuner, tunerCallbackAdapter, paramInt);
      } catch (RemoteException|IllegalArgumentException|IllegalStateException remoteException) {
        Log.e("BroadcastRadio.manager", "Failed to open tuner", (Throwable)remoteException);
        return null;
      } 
    } 
    throw new IllegalArgumentException("callback must not be empty");
  }
  
  public void removeAnnouncementListener(Announcement.OnListUpdatedListener paramOnListUpdatedListener) {
    Objects.requireNonNull(paramOnListUpdatedListener);
    synchronized (this.mAnnouncementListeners) {
      ICloseHandle iCloseHandle = this.mAnnouncementListeners.remove(paramOnListUpdatedListener);
      if (iCloseHandle != null)
        Utils.close(iCloseHandle); 
      return;
    } 
  }
  
  public static class AmBandConfig extends BandConfig {
    public static final Parcelable.Creator<AmBandConfig> CREATOR = new Parcelable.Creator<AmBandConfig>() {
        public RadioManager.AmBandConfig createFromParcel(Parcel param2Parcel) {
          return new RadioManager.AmBandConfig(param2Parcel);
        }
        
        public RadioManager.AmBandConfig[] newArray(int param2Int) {
          return new RadioManager.AmBandConfig[param2Int];
        }
      };
    
    private final boolean mStereo;
    
    AmBandConfig(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, boolean param1Boolean) {
      super(param1Int1, param1Int2, param1Int3, param1Int4, param1Int5);
      this.mStereo = param1Boolean;
    }
    
    public AmBandConfig(RadioManager.AmBandDescriptor param1AmBandDescriptor) {
      super(param1AmBandDescriptor);
      this.mStereo = param1AmBandDescriptor.isStereoSupported();
    }
    
    private AmBandConfig(Parcel param1Parcel) {
      super(param1Parcel);
      byte b = param1Parcel.readByte();
      boolean bool = true;
      if (b != 1)
        bool = false; 
      this.mStereo = bool;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!super.equals(param1Object))
        return false; 
      if (!(param1Object instanceof AmBandConfig))
        return false; 
      param1Object = param1Object;
      return !(this.mStereo != param1Object.getStereo());
    }
    
    public boolean getStereo() {
      return this.mStereo;
    }
    
    public int hashCode() {
      return super.hashCode() * 31 + this.mStereo;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("AmBandConfig [");
      stringBuilder.append(super.toString());
      stringBuilder.append(", mStereo=");
      stringBuilder.append(this.mStereo);
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeByte((byte)this.mStereo);
    }
    
    public static class Builder {
      private final RadioManager.BandDescriptor mDescriptor;
      
      private boolean mStereo;
      
      public Builder(RadioManager.AmBandConfig param2AmBandConfig) {
        this.mDescriptor = new RadioManager.BandDescriptor(param2AmBandConfig.getRegion(), param2AmBandConfig.getType(), param2AmBandConfig.getLowerLimit(), param2AmBandConfig.getUpperLimit(), param2AmBandConfig.getSpacing());
        this.mStereo = param2AmBandConfig.getStereo();
      }
      
      public Builder(RadioManager.AmBandDescriptor param2AmBandDescriptor) {
        this.mDescriptor = new RadioManager.BandDescriptor(param2AmBandDescriptor.getRegion(), param2AmBandDescriptor.getType(), param2AmBandDescriptor.getLowerLimit(), param2AmBandDescriptor.getUpperLimit(), param2AmBandDescriptor.getSpacing());
        this.mStereo = param2AmBandDescriptor.isStereoSupported();
      }
      
      public RadioManager.AmBandConfig build() {
        return new RadioManager.AmBandConfig(this.mDescriptor.getRegion(), this.mDescriptor.getType(), this.mDescriptor.getLowerLimit(), this.mDescriptor.getUpperLimit(), this.mDescriptor.getSpacing(), this.mStereo);
      }
      
      public Builder setStereo(boolean param2Boolean) {
        this.mStereo = param2Boolean;
        return this;
      }
    }
  }
  
  class null implements Parcelable.Creator<AmBandConfig> {
    public RadioManager.AmBandConfig createFromParcel(Parcel param1Parcel) {
      return new RadioManager.AmBandConfig(param1Parcel);
    }
    
    public RadioManager.AmBandConfig[] newArray(int param1Int) {
      return new RadioManager.AmBandConfig[param1Int];
    }
  }
  
  public static class Builder {
    private final RadioManager.BandDescriptor mDescriptor;
    
    private boolean mStereo;
    
    public Builder(RadioManager.AmBandConfig param1AmBandConfig) {
      this.mDescriptor = new RadioManager.BandDescriptor(param1AmBandConfig.getRegion(), param1AmBandConfig.getType(), param1AmBandConfig.getLowerLimit(), param1AmBandConfig.getUpperLimit(), param1AmBandConfig.getSpacing());
      this.mStereo = param1AmBandConfig.getStereo();
    }
    
    public Builder(RadioManager.AmBandDescriptor param1AmBandDescriptor) {
      this.mDescriptor = new RadioManager.BandDescriptor(param1AmBandDescriptor.getRegion(), param1AmBandDescriptor.getType(), param1AmBandDescriptor.getLowerLimit(), param1AmBandDescriptor.getUpperLimit(), param1AmBandDescriptor.getSpacing());
      this.mStereo = param1AmBandDescriptor.isStereoSupported();
    }
    
    public RadioManager.AmBandConfig build() {
      return new RadioManager.AmBandConfig(this.mDescriptor.getRegion(), this.mDescriptor.getType(), this.mDescriptor.getLowerLimit(), this.mDescriptor.getUpperLimit(), this.mDescriptor.getSpacing(), this.mStereo);
    }
    
    public Builder setStereo(boolean param1Boolean) {
      this.mStereo = param1Boolean;
      return this;
    }
  }
  
  public static class AmBandDescriptor extends BandDescriptor {
    public static final Parcelable.Creator<AmBandDescriptor> CREATOR = new Parcelable.Creator<AmBandDescriptor>() {
        public RadioManager.AmBandDescriptor createFromParcel(Parcel param2Parcel) {
          return new RadioManager.AmBandDescriptor(param2Parcel);
        }
        
        public RadioManager.AmBandDescriptor[] newArray(int param2Int) {
          return new RadioManager.AmBandDescriptor[param2Int];
        }
      };
    
    private final boolean mStereo;
    
    public AmBandDescriptor(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, boolean param1Boolean) {
      super(param1Int1, param1Int2, param1Int3, param1Int4, param1Int5);
      this.mStereo = param1Boolean;
    }
    
    private AmBandDescriptor(Parcel param1Parcel) {
      super(param1Parcel);
      byte b = param1Parcel.readByte();
      boolean bool = true;
      if (b != 1)
        bool = false; 
      this.mStereo = bool;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!super.equals(param1Object))
        return false; 
      if (!(param1Object instanceof AmBandDescriptor))
        return false; 
      param1Object = param1Object;
      return !(this.mStereo != param1Object.isStereoSupported());
    }
    
    public int hashCode() {
      return super.hashCode() * 31 + this.mStereo;
    }
    
    public boolean isStereoSupported() {
      return this.mStereo;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("AmBandDescriptor [ ");
      stringBuilder.append(super.toString());
      stringBuilder.append(" mStereo=");
      stringBuilder.append(this.mStereo);
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeByte((byte)this.mStereo);
    }
  }
  
  class null implements Parcelable.Creator<AmBandDescriptor> {
    public RadioManager.AmBandDescriptor createFromParcel(Parcel param1Parcel) {
      return new RadioManager.AmBandDescriptor(param1Parcel);
    }
    
    public RadioManager.AmBandDescriptor[] newArray(int param1Int) {
      return new RadioManager.AmBandDescriptor[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Band {}
  
  public static class BandConfig implements Parcelable {
    public static final Parcelable.Creator<BandConfig> CREATOR = new Parcelable.Creator<BandConfig>() {
        public RadioManager.BandConfig createFromParcel(Parcel param2Parcel) {
          StringBuilder stringBuilder;
          int i = RadioManager.BandDescriptor.lookupTypeFromParcel(param2Parcel);
          if (i != 0)
            if (i != 1 && i != 2) {
              if (i != 3) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unsupported band: ");
                stringBuilder.append(i);
                throw new IllegalArgumentException(stringBuilder.toString());
              } 
            } else {
              return new RadioManager.FmBandConfig((Parcel)stringBuilder);
            }  
          return new RadioManager.AmBandConfig((Parcel)stringBuilder);
        }
        
        public RadioManager.BandConfig[] newArray(int param2Int) {
          return new RadioManager.BandConfig[param2Int];
        }
      };
    
    final RadioManager.BandDescriptor mDescriptor;
    
    BandConfig(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5) {
      this.mDescriptor = new RadioManager.BandDescriptor(param1Int1, param1Int2, param1Int3, param1Int4, param1Int5);
    }
    
    BandConfig(RadioManager.BandDescriptor param1BandDescriptor) {
      Objects.requireNonNull(param1BandDescriptor);
      this.mDescriptor = param1BandDescriptor;
    }
    
    private BandConfig(Parcel param1Parcel) {
      this.mDescriptor = new RadioManager.BandDescriptor(param1Parcel);
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      boolean bool1;
      boolean bool2;
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof BandConfig))
        return false; 
      param1Object = ((BandConfig)param1Object).getDescriptor();
      if (this.mDescriptor == null) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      if (param1Object == null) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      if (bool1 != bool2)
        return false; 
      RadioManager.BandDescriptor bandDescriptor = this.mDescriptor;
      return !(bandDescriptor != null && !bandDescriptor.equals(param1Object));
    }
    
    RadioManager.BandDescriptor getDescriptor() {
      return this.mDescriptor;
    }
    
    public int getLowerLimit() {
      return this.mDescriptor.getLowerLimit();
    }
    
    public int getRegion() {
      return this.mDescriptor.getRegion();
    }
    
    public int getSpacing() {
      return this.mDescriptor.getSpacing();
    }
    
    public int getType() {
      return this.mDescriptor.getType();
    }
    
    public int getUpperLimit() {
      return this.mDescriptor.getUpperLimit();
    }
    
    public int hashCode() {
      return 1 * 31 + this.mDescriptor.hashCode();
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("BandConfig [ ");
      stringBuilder.append(this.mDescriptor.toString());
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      this.mDescriptor.writeToParcel(param1Parcel, param1Int);
    }
  }
  
  class null implements Parcelable.Creator<BandConfig> {
    public RadioManager.BandConfig createFromParcel(Parcel param1Parcel) {
      StringBuilder stringBuilder;
      int i = RadioManager.BandDescriptor.lookupTypeFromParcel(param1Parcel);
      if (i != 0)
        if (i != 1 && i != 2) {
          if (i != 3) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unsupported band: ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
          } 
        } else {
          return new RadioManager.FmBandConfig((Parcel)stringBuilder);
        }  
      return new RadioManager.AmBandConfig((Parcel)stringBuilder);
    }
    
    public RadioManager.BandConfig[] newArray(int param1Int) {
      return new RadioManager.BandConfig[param1Int];
    }
  }
  
  public static class BandDescriptor implements Parcelable {
    public static final Parcelable.Creator<BandDescriptor> CREATOR = new Parcelable.Creator<BandDescriptor>() {
        public RadioManager.BandDescriptor createFromParcel(Parcel param2Parcel) {
          StringBuilder stringBuilder;
          int i = RadioManager.BandDescriptor.lookupTypeFromParcel(param2Parcel);
          if (i != 0)
            if (i != 1 && i != 2) {
              if (i != 3) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("Unsupported band: ");
                stringBuilder.append(i);
                throw new IllegalArgumentException(stringBuilder.toString());
              } 
            } else {
              return new RadioManager.FmBandDescriptor((Parcel)stringBuilder);
            }  
          return new RadioManager.AmBandDescriptor((Parcel)stringBuilder);
        }
        
        public RadioManager.BandDescriptor[] newArray(int param2Int) {
          return new RadioManager.BandDescriptor[param2Int];
        }
      };
    
    private final int mLowerLimit;
    
    private final int mRegion;
    
    private final int mSpacing;
    
    private final int mType;
    
    private final int mUpperLimit;
    
    BandDescriptor(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5) {
      if (param1Int2 == 0 || param1Int2 == 1 || param1Int2 == 2 || param1Int2 == 3) {
        this.mRegion = param1Int1;
        this.mType = param1Int2;
        this.mLowerLimit = param1Int3;
        this.mUpperLimit = param1Int4;
        this.mSpacing = param1Int5;
        return;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unsupported band: ");
      stringBuilder.append(param1Int2);
      throw new IllegalArgumentException(stringBuilder.toString());
    }
    
    private BandDescriptor(Parcel param1Parcel) {
      this.mRegion = param1Parcel.readInt();
      this.mType = param1Parcel.readInt();
      this.mLowerLimit = param1Parcel.readInt();
      this.mUpperLimit = param1Parcel.readInt();
      this.mSpacing = param1Parcel.readInt();
    }
    
    private static int lookupTypeFromParcel(Parcel param1Parcel) {
      int i = param1Parcel.dataPosition();
      param1Parcel.readInt();
      int j = param1Parcel.readInt();
      param1Parcel.setDataPosition(i);
      return j;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof BandDescriptor))
        return false; 
      param1Object = param1Object;
      return (this.mRegion != param1Object.getRegion()) ? false : ((this.mType != param1Object.getType()) ? false : ((this.mLowerLimit != param1Object.getLowerLimit()) ? false : ((this.mUpperLimit != param1Object.getUpperLimit()) ? false : (!(this.mSpacing != param1Object.getSpacing())))));
    }
    
    public int getLowerLimit() {
      return this.mLowerLimit;
    }
    
    public int getRegion() {
      return this.mRegion;
    }
    
    public int getSpacing() {
      return this.mSpacing;
    }
    
    public int getType() {
      return this.mType;
    }
    
    public int getUpperLimit() {
      return this.mUpperLimit;
    }
    
    public int hashCode() {
      return ((((1 * 31 + this.mRegion) * 31 + this.mType) * 31 + this.mLowerLimit) * 31 + this.mUpperLimit) * 31 + this.mSpacing;
    }
    
    public boolean isAmBand() {
      int i = this.mType;
      return (i == 0 || i == 3);
    }
    
    public boolean isFmBand() {
      int i = this.mType;
      boolean bool1 = true;
      boolean bool2 = bool1;
      if (i != 1)
        if (i == 2) {
          bool2 = bool1;
        } else {
          bool2 = false;
        }  
      return bool2;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("BandDescriptor [mRegion=");
      stringBuilder.append(this.mRegion);
      stringBuilder.append(", mType=");
      stringBuilder.append(this.mType);
      stringBuilder.append(", mLowerLimit=");
      stringBuilder.append(this.mLowerLimit);
      stringBuilder.append(", mUpperLimit=");
      stringBuilder.append(this.mUpperLimit);
      stringBuilder.append(", mSpacing=");
      stringBuilder.append(this.mSpacing);
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.mRegion);
      param1Parcel.writeInt(this.mType);
      param1Parcel.writeInt(this.mLowerLimit);
      param1Parcel.writeInt(this.mUpperLimit);
      param1Parcel.writeInt(this.mSpacing);
    }
  }
  
  class null implements Parcelable.Creator<BandDescriptor> {
    public RadioManager.BandDescriptor createFromParcel(Parcel param1Parcel) {
      StringBuilder stringBuilder;
      int i = RadioManager.BandDescriptor.lookupTypeFromParcel(param1Parcel);
      if (i != 0)
        if (i != 1 && i != 2) {
          if (i != 3) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unsupported band: ");
            stringBuilder.append(i);
            throw new IllegalArgumentException(stringBuilder.toString());
          } 
        } else {
          return new RadioManager.FmBandDescriptor((Parcel)stringBuilder);
        }  
      return new RadioManager.AmBandDescriptor((Parcel)stringBuilder);
    }
    
    public RadioManager.BandDescriptor[] newArray(int param1Int) {
      return new RadioManager.BandDescriptor[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ConfigFlag {}
  
  public static class FmBandConfig extends BandConfig {
    public static final Parcelable.Creator<FmBandConfig> CREATOR = new Parcelable.Creator<FmBandConfig>() {
        public RadioManager.FmBandConfig createFromParcel(Parcel param2Parcel) {
          return new RadioManager.FmBandConfig(param2Parcel);
        }
        
        public RadioManager.FmBandConfig[] newArray(int param2Int) {
          return new RadioManager.FmBandConfig[param2Int];
        }
      };
    
    private final boolean mAf;
    
    private final boolean mEa;
    
    private final boolean mRds;
    
    private final boolean mStereo;
    
    private final boolean mTa;
    
    FmBandConfig(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4, boolean param1Boolean5) {
      super(param1Int1, param1Int2, param1Int3, param1Int4, param1Int5);
      this.mStereo = param1Boolean1;
      this.mRds = param1Boolean2;
      this.mTa = param1Boolean3;
      this.mAf = param1Boolean4;
      this.mEa = param1Boolean5;
    }
    
    public FmBandConfig(RadioManager.FmBandDescriptor param1FmBandDescriptor) {
      super(param1FmBandDescriptor);
      this.mStereo = param1FmBandDescriptor.isStereoSupported();
      this.mRds = param1FmBandDescriptor.isRdsSupported();
      this.mTa = param1FmBandDescriptor.isTaSupported();
      this.mAf = param1FmBandDescriptor.isAfSupported();
      this.mEa = param1FmBandDescriptor.isEaSupported();
    }
    
    private FmBandConfig(Parcel param1Parcel) {
      super(param1Parcel);
      byte b = param1Parcel.readByte();
      boolean bool1 = false;
      if (b == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mStereo = bool2;
      if (param1Parcel.readByte() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mRds = bool2;
      if (param1Parcel.readByte() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mTa = bool2;
      if (param1Parcel.readByte() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mAf = bool2;
      boolean bool2 = bool1;
      if (param1Parcel.readByte() == 1)
        bool2 = true; 
      this.mEa = bool2;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!super.equals(param1Object))
        return false; 
      if (!(param1Object instanceof FmBandConfig))
        return false; 
      param1Object = param1Object;
      return (this.mStereo != ((FmBandConfig)param1Object).mStereo) ? false : ((this.mRds != ((FmBandConfig)param1Object).mRds) ? false : ((this.mTa != ((FmBandConfig)param1Object).mTa) ? false : ((this.mAf != ((FmBandConfig)param1Object).mAf) ? false : (!(this.mEa != ((FmBandConfig)param1Object).mEa)))));
    }
    
    public boolean getAf() {
      return this.mAf;
    }
    
    public boolean getEa() {
      return this.mEa;
    }
    
    public boolean getRds() {
      return this.mRds;
    }
    
    public boolean getStereo() {
      return this.mStereo;
    }
    
    public boolean getTa() {
      return this.mTa;
    }
    
    public int hashCode() {
      return ((((super.hashCode() * 31 + this.mStereo) * 31 + this.mRds) * 31 + this.mTa) * 31 + this.mAf) * 31 + this.mEa;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("FmBandConfig [");
      stringBuilder.append(super.toString());
      stringBuilder.append(", mStereo=");
      stringBuilder.append(this.mStereo);
      stringBuilder.append(", mRds=");
      stringBuilder.append(this.mRds);
      stringBuilder.append(", mTa=");
      stringBuilder.append(this.mTa);
      stringBuilder.append(", mAf=");
      stringBuilder.append(this.mAf);
      stringBuilder.append(", mEa =");
      stringBuilder.append(this.mEa);
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeByte((byte)this.mStereo);
      param1Parcel.writeByte((byte)this.mRds);
      param1Parcel.writeByte((byte)this.mTa);
      param1Parcel.writeByte((byte)this.mAf);
      param1Parcel.writeByte((byte)this.mEa);
    }
    
    public static class Builder {
      private boolean mAf;
      
      private final RadioManager.BandDescriptor mDescriptor;
      
      private boolean mEa;
      
      private boolean mRds;
      
      private boolean mStereo;
      
      private boolean mTa;
      
      public Builder(RadioManager.FmBandConfig param2FmBandConfig) {
        this.mDescriptor = new RadioManager.BandDescriptor(param2FmBandConfig.getRegion(), param2FmBandConfig.getType(), param2FmBandConfig.getLowerLimit(), param2FmBandConfig.getUpperLimit(), param2FmBandConfig.getSpacing());
        this.mStereo = param2FmBandConfig.getStereo();
        this.mRds = param2FmBandConfig.getRds();
        this.mTa = param2FmBandConfig.getTa();
        this.mAf = param2FmBandConfig.getAf();
        this.mEa = param2FmBandConfig.getEa();
      }
      
      public Builder(RadioManager.FmBandDescriptor param2FmBandDescriptor) {
        this.mDescriptor = new RadioManager.BandDescriptor(param2FmBandDescriptor.getRegion(), param2FmBandDescriptor.getType(), param2FmBandDescriptor.getLowerLimit(), param2FmBandDescriptor.getUpperLimit(), param2FmBandDescriptor.getSpacing());
        this.mStereo = param2FmBandDescriptor.isStereoSupported();
        this.mRds = param2FmBandDescriptor.isRdsSupported();
        this.mTa = param2FmBandDescriptor.isTaSupported();
        this.mAf = param2FmBandDescriptor.isAfSupported();
        this.mEa = param2FmBandDescriptor.isEaSupported();
      }
      
      public RadioManager.FmBandConfig build() {
        return new RadioManager.FmBandConfig(this.mDescriptor.getRegion(), this.mDescriptor.getType(), this.mDescriptor.getLowerLimit(), this.mDescriptor.getUpperLimit(), this.mDescriptor.getSpacing(), this.mStereo, this.mRds, this.mTa, this.mAf, this.mEa);
      }
      
      public Builder setAf(boolean param2Boolean) {
        this.mAf = param2Boolean;
        return this;
      }
      
      public Builder setEa(boolean param2Boolean) {
        this.mEa = param2Boolean;
        return this;
      }
      
      public Builder setRds(boolean param2Boolean) {
        this.mRds = param2Boolean;
        return this;
      }
      
      public Builder setStereo(boolean param2Boolean) {
        this.mStereo = param2Boolean;
        return this;
      }
      
      public Builder setTa(boolean param2Boolean) {
        this.mTa = param2Boolean;
        return this;
      }
    }
  }
  
  class null implements Parcelable.Creator<FmBandConfig> {
    public RadioManager.FmBandConfig createFromParcel(Parcel param1Parcel) {
      return new RadioManager.FmBandConfig(param1Parcel);
    }
    
    public RadioManager.FmBandConfig[] newArray(int param1Int) {
      return new RadioManager.FmBandConfig[param1Int];
    }
  }
  
  public static class Builder {
    private boolean mAf;
    
    private final RadioManager.BandDescriptor mDescriptor;
    
    private boolean mEa;
    
    private boolean mRds;
    
    private boolean mStereo;
    
    private boolean mTa;
    
    public Builder(RadioManager.FmBandConfig param1FmBandConfig) {
      this.mDescriptor = new RadioManager.BandDescriptor(param1FmBandConfig.getRegion(), param1FmBandConfig.getType(), param1FmBandConfig.getLowerLimit(), param1FmBandConfig.getUpperLimit(), param1FmBandConfig.getSpacing());
      this.mStereo = param1FmBandConfig.getStereo();
      this.mRds = param1FmBandConfig.getRds();
      this.mTa = param1FmBandConfig.getTa();
      this.mAf = param1FmBandConfig.getAf();
      this.mEa = param1FmBandConfig.getEa();
    }
    
    public Builder(RadioManager.FmBandDescriptor param1FmBandDescriptor) {
      this.mDescriptor = new RadioManager.BandDescriptor(param1FmBandDescriptor.getRegion(), param1FmBandDescriptor.getType(), param1FmBandDescriptor.getLowerLimit(), param1FmBandDescriptor.getUpperLimit(), param1FmBandDescriptor.getSpacing());
      this.mStereo = param1FmBandDescriptor.isStereoSupported();
      this.mRds = param1FmBandDescriptor.isRdsSupported();
      this.mTa = param1FmBandDescriptor.isTaSupported();
      this.mAf = param1FmBandDescriptor.isAfSupported();
      this.mEa = param1FmBandDescriptor.isEaSupported();
    }
    
    public RadioManager.FmBandConfig build() {
      return new RadioManager.FmBandConfig(this.mDescriptor.getRegion(), this.mDescriptor.getType(), this.mDescriptor.getLowerLimit(), this.mDescriptor.getUpperLimit(), this.mDescriptor.getSpacing(), this.mStereo, this.mRds, this.mTa, this.mAf, this.mEa);
    }
    
    public Builder setAf(boolean param1Boolean) {
      this.mAf = param1Boolean;
      return this;
    }
    
    public Builder setEa(boolean param1Boolean) {
      this.mEa = param1Boolean;
      return this;
    }
    
    public Builder setRds(boolean param1Boolean) {
      this.mRds = param1Boolean;
      return this;
    }
    
    public Builder setStereo(boolean param1Boolean) {
      this.mStereo = param1Boolean;
      return this;
    }
    
    public Builder setTa(boolean param1Boolean) {
      this.mTa = param1Boolean;
      return this;
    }
  }
  
  public static class FmBandDescriptor extends BandDescriptor {
    public static final Parcelable.Creator<FmBandDescriptor> CREATOR = new Parcelable.Creator<FmBandDescriptor>() {
        public RadioManager.FmBandDescriptor createFromParcel(Parcel param2Parcel) {
          return new RadioManager.FmBandDescriptor(param2Parcel);
        }
        
        public RadioManager.FmBandDescriptor[] newArray(int param2Int) {
          return new RadioManager.FmBandDescriptor[param2Int];
        }
      };
    
    private final boolean mAf;
    
    private final boolean mEa;
    
    private final boolean mRds;
    
    private final boolean mStereo;
    
    private final boolean mTa;
    
    public FmBandDescriptor(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, boolean param1Boolean1, boolean param1Boolean2, boolean param1Boolean3, boolean param1Boolean4, boolean param1Boolean5) {
      super(param1Int1, param1Int2, param1Int3, param1Int4, param1Int5);
      this.mStereo = param1Boolean1;
      this.mRds = param1Boolean2;
      this.mTa = param1Boolean3;
      this.mAf = param1Boolean4;
      this.mEa = param1Boolean5;
    }
    
    private FmBandDescriptor(Parcel param1Parcel) {
      super(param1Parcel);
      byte b = param1Parcel.readByte();
      boolean bool1 = false;
      if (b == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mStereo = bool2;
      if (param1Parcel.readByte() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mRds = bool2;
      if (param1Parcel.readByte() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mTa = bool2;
      if (param1Parcel.readByte() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mAf = bool2;
      boolean bool2 = bool1;
      if (param1Parcel.readByte() == 1)
        bool2 = true; 
      this.mEa = bool2;
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!super.equals(param1Object))
        return false; 
      if (!(param1Object instanceof FmBandDescriptor))
        return false; 
      param1Object = param1Object;
      return (this.mStereo != param1Object.isStereoSupported()) ? false : ((this.mRds != param1Object.isRdsSupported()) ? false : ((this.mTa != param1Object.isTaSupported()) ? false : ((this.mAf != param1Object.isAfSupported()) ? false : (!(this.mEa != param1Object.isEaSupported())))));
    }
    
    public int hashCode() {
      return ((((super.hashCode() * 31 + this.mStereo) * 31 + this.mRds) * 31 + this.mTa) * 31 + this.mAf) * 31 + this.mEa;
    }
    
    public boolean isAfSupported() {
      return this.mAf;
    }
    
    public boolean isEaSupported() {
      return this.mEa;
    }
    
    public boolean isRdsSupported() {
      return this.mRds;
    }
    
    public boolean isStereoSupported() {
      return this.mStereo;
    }
    
    public boolean isTaSupported() {
      return this.mTa;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("FmBandDescriptor [ ");
      stringBuilder.append(super.toString());
      stringBuilder.append(" mStereo=");
      stringBuilder.append(this.mStereo);
      stringBuilder.append(", mRds=");
      stringBuilder.append(this.mRds);
      stringBuilder.append(", mTa=");
      stringBuilder.append(this.mTa);
      stringBuilder.append(", mAf=");
      stringBuilder.append(this.mAf);
      stringBuilder.append(", mEa =");
      stringBuilder.append(this.mEa);
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeByte((byte)this.mStereo);
      param1Parcel.writeByte((byte)this.mRds);
      param1Parcel.writeByte((byte)this.mTa);
      param1Parcel.writeByte((byte)this.mAf);
      param1Parcel.writeByte((byte)this.mEa);
    }
  }
  
  class null implements Parcelable.Creator<FmBandDescriptor> {
    public RadioManager.FmBandDescriptor createFromParcel(Parcel param1Parcel) {
      return new RadioManager.FmBandDescriptor(param1Parcel);
    }
    
    public RadioManager.FmBandDescriptor[] newArray(int param1Int) {
      return new RadioManager.FmBandDescriptor[param1Int];
    }
  }
  
  public static class ModuleProperties implements Parcelable {
    public static final Parcelable.Creator<ModuleProperties> CREATOR = new Parcelable.Creator<ModuleProperties>() {
        public RadioManager.ModuleProperties createFromParcel(Parcel param2Parcel) {
          return new RadioManager.ModuleProperties(param2Parcel);
        }
        
        public RadioManager.ModuleProperties[] newArray(int param2Int) {
          return new RadioManager.ModuleProperties[param2Int];
        }
      };
    
    private final RadioManager.BandDescriptor[] mBands;
    
    private final int mClassId;
    
    private final Map<String, Integer> mDabFrequencyTable;
    
    private final int mId;
    
    private final String mImplementor;
    
    private final boolean mIsBgScanSupported;
    
    private final boolean mIsCaptureSupported;
    
    private final boolean mIsInitializationRequired;
    
    private final int mNumAudioSources;
    
    private final int mNumTuners;
    
    private final String mProduct;
    
    private final String mSerial;
    
    private final String mServiceName;
    
    private final Set<Integer> mSupportedIdentifierTypes;
    
    private final Set<Integer> mSupportedProgramTypes;
    
    private final Map<String, String> mVendorInfo;
    
    private final String mVersion;
    
    public ModuleProperties(int param1Int1, String param1String1, int param1Int2, String param1String2, String param1String3, String param1String4, String param1String5, int param1Int3, int param1Int4, boolean param1Boolean1, boolean param1Boolean2, RadioManager.BandDescriptor[] param1ArrayOfBandDescriptor, boolean param1Boolean3, int[] param1ArrayOfint1, int[] param1ArrayOfint2, Map<String, Integer> param1Map, Map<String, String> param1Map1) {
      Map<String, String> map;
      this.mId = param1Int1;
      if (TextUtils.isEmpty(param1String1))
        param1String1 = "default"; 
      this.mServiceName = param1String1;
      this.mClassId = param1Int2;
      this.mImplementor = param1String2;
      this.mProduct = param1String3;
      this.mVersion = param1String4;
      this.mSerial = param1String5;
      this.mNumTuners = param1Int3;
      this.mNumAudioSources = param1Int4;
      this.mIsInitializationRequired = param1Boolean1;
      this.mIsCaptureSupported = param1Boolean2;
      this.mBands = param1ArrayOfBandDescriptor;
      this.mIsBgScanSupported = param1Boolean3;
      this.mSupportedProgramTypes = arrayToSet(param1ArrayOfint1);
      this.mSupportedIdentifierTypes = arrayToSet(param1ArrayOfint2);
      if (param1Map != null)
        for (Map.Entry<String, Integer> entry : param1Map.entrySet()) {
          Objects.requireNonNull((String)entry.getKey());
          Objects.requireNonNull((Integer)entry.getValue());
        }  
      this.mDabFrequencyTable = param1Map;
      if (param1Map1 == null) {
        HashMap<Object, Object> hashMap = new HashMap<>();
      } else {
        map = param1Map1;
      } 
      this.mVendorInfo = map;
    }
    
    private ModuleProperties(Parcel param1Parcel) {
      this.mId = param1Parcel.readInt();
      String str = param1Parcel.readString();
      if (TextUtils.isEmpty(str))
        str = "default"; 
      this.mServiceName = str;
      this.mClassId = param1Parcel.readInt();
      this.mImplementor = param1Parcel.readString();
      this.mProduct = param1Parcel.readString();
      this.mVersion = param1Parcel.readString();
      this.mSerial = param1Parcel.readString();
      this.mNumTuners = param1Parcel.readInt();
      this.mNumAudioSources = param1Parcel.readInt();
      int i = param1Parcel.readInt();
      boolean bool1 = false;
      if (i == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mIsInitializationRequired = bool2;
      if (param1Parcel.readInt() == 1) {
        bool2 = true;
      } else {
        bool2 = false;
      } 
      this.mIsCaptureSupported = bool2;
      Parcelable[] arrayOfParcelable = param1Parcel.readParcelableArray(RadioManager.BandDescriptor.class.getClassLoader());
      this.mBands = new RadioManager.BandDescriptor[arrayOfParcelable.length];
      for (i = 0; i < arrayOfParcelable.length; i++)
        this.mBands[i] = (RadioManager.BandDescriptor)arrayOfParcelable[i]; 
      boolean bool2 = bool1;
      if (param1Parcel.readInt() == 1)
        bool2 = true; 
      this.mIsBgScanSupported = bool2;
      this.mSupportedProgramTypes = arrayToSet(param1Parcel.createIntArray());
      this.mSupportedIdentifierTypes = arrayToSet(param1Parcel.createIntArray());
      this.mDabFrequencyTable = Utils.readStringIntMap(param1Parcel);
      this.mVendorInfo = Utils.readStringMap(param1Parcel);
    }
    
    private static Set<Integer> arrayToSet(int[] param1ArrayOfint) {
      return Arrays.stream(param1ArrayOfint).boxed().collect((Collector)Collectors.toSet());
    }
    
    private static int[] setToArray(Set<Integer> param1Set) {
      return param1Set.stream().mapToInt((ToIntFunction)_$$Lambda$UV1wDVoVlbcxpr8zevj_aMFtUGw.INSTANCE).toArray();
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof ModuleProperties))
        return false; 
      param1Object = param1Object;
      return (this.mId != param1Object.getId()) ? false : (!TextUtils.equals(this.mServiceName, ((ModuleProperties)param1Object).mServiceName) ? false : ((this.mClassId != ((ModuleProperties)param1Object).mClassId) ? false : (!Objects.equals(this.mImplementor, ((ModuleProperties)param1Object).mImplementor) ? false : (!Objects.equals(this.mProduct, ((ModuleProperties)param1Object).mProduct) ? false : (!Objects.equals(this.mVersion, ((ModuleProperties)param1Object).mVersion) ? false : (!Objects.equals(this.mSerial, ((ModuleProperties)param1Object).mSerial) ? false : ((this.mNumTuners != ((ModuleProperties)param1Object).mNumTuners) ? false : ((this.mNumAudioSources != ((ModuleProperties)param1Object).mNumAudioSources) ? false : ((this.mIsInitializationRequired != ((ModuleProperties)param1Object).mIsInitializationRequired) ? false : ((this.mIsCaptureSupported != ((ModuleProperties)param1Object).mIsCaptureSupported) ? false : (!Objects.equals(this.mBands, ((ModuleProperties)param1Object).mBands) ? false : ((this.mIsBgScanSupported != ((ModuleProperties)param1Object).mIsBgScanSupported) ? false : (!Objects.equals(this.mDabFrequencyTable, ((ModuleProperties)param1Object).mDabFrequencyTable) ? false : (!!Objects.equals(this.mVendorInfo, ((ModuleProperties)param1Object).mVendorInfo)))))))))))))));
    }
    
    public RadioManager.BandDescriptor[] getBands() {
      return this.mBands;
    }
    
    public int getClassId() {
      return this.mClassId;
    }
    
    public Map<String, Integer> getDabFrequencyTable() {
      return this.mDabFrequencyTable;
    }
    
    public int getId() {
      return this.mId;
    }
    
    public String getImplementor() {
      return this.mImplementor;
    }
    
    public int getNumAudioSources() {
      return this.mNumAudioSources;
    }
    
    public int getNumTuners() {
      return this.mNumTuners;
    }
    
    public String getProduct() {
      return this.mProduct;
    }
    
    public String getSerial() {
      return this.mSerial;
    }
    
    public String getServiceName() {
      return this.mServiceName;
    }
    
    public Map<String, String> getVendorInfo() {
      return this.mVendorInfo;
    }
    
    public String getVersion() {
      return this.mVersion;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { 
            Integer.valueOf(this.mId), this.mServiceName, Integer.valueOf(this.mClassId), this.mImplementor, this.mProduct, this.mVersion, this.mSerial, Integer.valueOf(this.mNumTuners), Integer.valueOf(this.mNumAudioSources), Boolean.valueOf(this.mIsInitializationRequired), 
            Boolean.valueOf(this.mIsCaptureSupported), this.mBands, Boolean.valueOf(this.mIsBgScanSupported), this.mDabFrequencyTable, this.mVendorInfo });
    }
    
    public boolean isBackgroundScanningSupported() {
      return this.mIsBgScanSupported;
    }
    
    public boolean isCaptureSupported() {
      return this.mIsCaptureSupported;
    }
    
    public boolean isInitializationRequired() {
      return this.mIsInitializationRequired;
    }
    
    public boolean isProgramIdentifierSupported(int param1Int) {
      return this.mSupportedIdentifierTypes.contains(Integer.valueOf(param1Int));
    }
    
    public boolean isProgramTypeSupported(int param1Int) {
      return this.mSupportedProgramTypes.contains(Integer.valueOf(param1Int));
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ModuleProperties [mId=");
      stringBuilder.append(this.mId);
      stringBuilder.append(", mServiceName=");
      stringBuilder.append(this.mServiceName);
      stringBuilder.append(", mClassId=");
      stringBuilder.append(this.mClassId);
      stringBuilder.append(", mImplementor=");
      stringBuilder.append(this.mImplementor);
      stringBuilder.append(", mProduct=");
      stringBuilder.append(this.mProduct);
      stringBuilder.append(", mVersion=");
      stringBuilder.append(this.mVersion);
      stringBuilder.append(", mSerial=");
      stringBuilder.append(this.mSerial);
      stringBuilder.append(", mNumTuners=");
      stringBuilder.append(this.mNumTuners);
      stringBuilder.append(", mNumAudioSources=");
      stringBuilder.append(this.mNumAudioSources);
      stringBuilder.append(", mIsInitializationRequired=");
      stringBuilder.append(this.mIsInitializationRequired);
      stringBuilder.append(", mIsCaptureSupported=");
      stringBuilder.append(this.mIsCaptureSupported);
      stringBuilder.append(", mIsBgScanSupported=");
      stringBuilder.append(this.mIsBgScanSupported);
      stringBuilder.append(", mBands=");
      stringBuilder.append(Arrays.toString((Object[])this.mBands));
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeInt(this.mId);
      param1Parcel.writeString(this.mServiceName);
      param1Parcel.writeInt(this.mClassId);
      param1Parcel.writeString(this.mImplementor);
      param1Parcel.writeString(this.mProduct);
      param1Parcel.writeString(this.mVersion);
      param1Parcel.writeString(this.mSerial);
      param1Parcel.writeInt(this.mNumTuners);
      param1Parcel.writeInt(this.mNumAudioSources);
      param1Parcel.writeInt(this.mIsInitializationRequired);
      param1Parcel.writeInt(this.mIsCaptureSupported);
      param1Parcel.writeParcelableArray((Parcelable[])this.mBands, param1Int);
      param1Parcel.writeInt(this.mIsBgScanSupported);
      param1Parcel.writeIntArray(setToArray(this.mSupportedProgramTypes));
      param1Parcel.writeIntArray(setToArray(this.mSupportedIdentifierTypes));
      Utils.writeStringIntMap(param1Parcel, this.mDabFrequencyTable);
      Utils.writeStringMap(param1Parcel, this.mVendorInfo);
    }
  }
  
  class null implements Parcelable.Creator<ModuleProperties> {
    public RadioManager.ModuleProperties createFromParcel(Parcel param1Parcel) {
      return new RadioManager.ModuleProperties(param1Parcel);
    }
    
    public RadioManager.ModuleProperties[] newArray(int param1Int) {
      return new RadioManager.ModuleProperties[param1Int];
    }
  }
  
  public static class ProgramInfo implements Parcelable {
    public static final Parcelable.Creator<ProgramInfo> CREATOR = new Parcelable.Creator<ProgramInfo>() {
        public RadioManager.ProgramInfo createFromParcel(Parcel param2Parcel) {
          return new RadioManager.ProgramInfo(param2Parcel);
        }
        
        public RadioManager.ProgramInfo[] newArray(int param2Int) {
          return new RadioManager.ProgramInfo[param2Int];
        }
      };
    
    private static final int FLAG_LIVE = 1;
    
    private static final int FLAG_MUTED = 2;
    
    private static final int FLAG_STEREO = 32;
    
    private static final int FLAG_TRAFFIC_ANNOUNCEMENT = 8;
    
    private static final int FLAG_TRAFFIC_PROGRAM = 4;
    
    private static final int FLAG_TUNED = 16;
    
    private final int mInfoFlags;
    
    private final ProgramSelector.Identifier mLogicallyTunedTo;
    
    private final RadioMetadata mMetadata;
    
    private final ProgramSelector.Identifier mPhysicallyTunedTo;
    
    private final Collection<ProgramSelector.Identifier> mRelatedContent;
    
    private final ProgramSelector mSelector;
    
    private final int mSignalQuality;
    
    private final Map<String, String> mVendorInfo;
    
    public ProgramInfo(ProgramSelector param1ProgramSelector, ProgramSelector.Identifier param1Identifier1, ProgramSelector.Identifier param1Identifier2, Collection<ProgramSelector.Identifier> param1Collection, int param1Int1, int param1Int2, RadioMetadata param1RadioMetadata, Map<String, String> param1Map) {
      Map<String, String> map;
      Objects.requireNonNull(param1ProgramSelector);
      this.mSelector = param1ProgramSelector;
      this.mLogicallyTunedTo = param1Identifier1;
      this.mPhysicallyTunedTo = param1Identifier2;
      if (param1Collection == null) {
        this.mRelatedContent = Collections.emptyList();
      } else {
        Preconditions.checkCollectionElementsNotNull(param1Collection, "relatedContent");
        this.mRelatedContent = param1Collection;
      } 
      this.mInfoFlags = param1Int1;
      this.mSignalQuality = param1Int2;
      this.mMetadata = param1RadioMetadata;
      if (param1Map == null) {
        HashMap<Object, Object> hashMap = new HashMap<>();
      } else {
        map = param1Map;
      } 
      this.mVendorInfo = map;
    }
    
    private ProgramInfo(Parcel param1Parcel) {
      ProgramSelector programSelector = (ProgramSelector)param1Parcel.readTypedObject(ProgramSelector.CREATOR);
      Objects.requireNonNull(programSelector);
      this.mSelector = programSelector;
      this.mLogicallyTunedTo = (ProgramSelector.Identifier)param1Parcel.readTypedObject(ProgramSelector.Identifier.CREATOR);
      this.mPhysicallyTunedTo = (ProgramSelector.Identifier)param1Parcel.readTypedObject(ProgramSelector.Identifier.CREATOR);
      this.mRelatedContent = param1Parcel.createTypedArrayList(ProgramSelector.Identifier.CREATOR);
      this.mInfoFlags = param1Parcel.readInt();
      this.mSignalQuality = param1Parcel.readInt();
      this.mMetadata = (RadioMetadata)param1Parcel.readTypedObject(RadioMetadata.CREATOR);
      this.mVendorInfo = Utils.readStringMap(param1Parcel);
    }
    
    public int describeContents() {
      return 0;
    }
    
    public boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof ProgramInfo))
        return false; 
      param1Object = param1Object;
      return !Objects.equals(this.mSelector, ((ProgramInfo)param1Object).mSelector) ? false : (!Objects.equals(this.mLogicallyTunedTo, ((ProgramInfo)param1Object).mLogicallyTunedTo) ? false : (!Objects.equals(this.mPhysicallyTunedTo, ((ProgramInfo)param1Object).mPhysicallyTunedTo) ? false : (!Objects.equals(this.mRelatedContent, ((ProgramInfo)param1Object).mRelatedContent) ? false : ((this.mInfoFlags != ((ProgramInfo)param1Object).mInfoFlags) ? false : ((this.mSignalQuality != ((ProgramInfo)param1Object).mSignalQuality) ? false : (!Objects.equals(this.mMetadata, ((ProgramInfo)param1Object).mMetadata) ? false : (!!Objects.equals(this.mVendorInfo, ((ProgramInfo)param1Object).mVendorInfo))))))));
    }
    
    @Deprecated
    public int getChannel() {
      try {
        long l = this.mSelector.getFirstId(1);
        return (int)l;
      } catch (IllegalArgumentException illegalArgumentException) {
        Log.w("BroadcastRadio.manager", "Not an AM/FM program");
        return 0;
      } 
    }
    
    public ProgramSelector.Identifier getLogicallyTunedTo() {
      return this.mLogicallyTunedTo;
    }
    
    public RadioMetadata getMetadata() {
      return this.mMetadata;
    }
    
    public ProgramSelector.Identifier getPhysicallyTunedTo() {
      return this.mPhysicallyTunedTo;
    }
    
    public Collection<ProgramSelector.Identifier> getRelatedContent() {
      return this.mRelatedContent;
    }
    
    public ProgramSelector getSelector() {
      return this.mSelector;
    }
    
    public int getSignalStrength() {
      return this.mSignalQuality;
    }
    
    @Deprecated
    public int getSubChannel() {
      try {
        long l = this.mSelector.getFirstId(4);
        return (int)l + 1;
      } catch (IllegalArgumentException illegalArgumentException) {
        return 0;
      } 
    }
    
    public Map<String, String> getVendorInfo() {
      return this.mVendorInfo;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { this.mSelector, this.mLogicallyTunedTo, this.mPhysicallyTunedTo, this.mRelatedContent, Integer.valueOf(this.mInfoFlags), Integer.valueOf(this.mSignalQuality), this.mMetadata, this.mVendorInfo });
    }
    
    @Deprecated
    public boolean isDigital() {
      ProgramSelector.Identifier identifier1 = this.mLogicallyTunedTo;
      ProgramSelector.Identifier identifier2 = identifier1;
      if (identifier1 == null)
        identifier2 = this.mSelector.getPrimaryId(); 
      int i = identifier2.getType();
      boolean bool = true;
      if (i == 1 || i == 2)
        bool = false; 
      return bool;
    }
    
    public boolean isLive() {
      int i = this.mInfoFlags;
      boolean bool = true;
      if ((i & 0x1) == 0)
        bool = false; 
      return bool;
    }
    
    public boolean isMuted() {
      boolean bool;
      if ((this.mInfoFlags & 0x2) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isStereo() {
      boolean bool;
      if ((this.mInfoFlags & 0x20) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isTrafficAnnouncementActive() {
      boolean bool;
      if ((this.mInfoFlags & 0x8) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isTrafficProgram() {
      boolean bool;
      if ((this.mInfoFlags & 0x4) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public boolean isTuned() {
      boolean bool;
      if ((this.mInfoFlags & 0x10) != 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ProgramInfo [selector=");
      stringBuilder.append(this.mSelector);
      stringBuilder.append(", logicallyTunedTo=");
      stringBuilder.append(Objects.toString(this.mLogicallyTunedTo));
      stringBuilder.append(", physicallyTunedTo=");
      stringBuilder.append(Objects.toString(this.mPhysicallyTunedTo));
      stringBuilder.append(", relatedContent=");
      stringBuilder.append(this.mRelatedContent.size());
      stringBuilder.append(", infoFlags=");
      stringBuilder.append(this.mInfoFlags);
      stringBuilder.append(", mSignalQuality=");
      stringBuilder.append(this.mSignalQuality);
      stringBuilder.append(", mMetadata=");
      stringBuilder.append(Objects.toString(this.mMetadata));
      stringBuilder.append("]");
      return stringBuilder.toString();
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeTypedObject(this.mSelector, param1Int);
      param1Parcel.writeTypedObject(this.mLogicallyTunedTo, param1Int);
      param1Parcel.writeTypedObject(this.mPhysicallyTunedTo, param1Int);
      Utils.writeTypedCollection(param1Parcel, this.mRelatedContent);
      param1Parcel.writeInt(this.mInfoFlags);
      param1Parcel.writeInt(this.mSignalQuality);
      param1Parcel.writeTypedObject(this.mMetadata, param1Int);
      Utils.writeStringMap(param1Parcel, this.mVendorInfo);
    }
  }
  
  class null implements Parcelable.Creator<ProgramInfo> {
    public RadioManager.ProgramInfo createFromParcel(Parcel param1Parcel) {
      return new RadioManager.ProgramInfo(param1Parcel);
    }
    
    public RadioManager.ProgramInfo[] newArray(int param1Int) {
      return new RadioManager.ProgramInfo[param1Int];
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/radio/RadioManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
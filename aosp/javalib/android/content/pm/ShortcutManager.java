package android.content.pm;

import android.annotation.SystemApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class ShortcutManager {
  public static final int FLAG_MATCH_CACHED = 8;
  
  public static final int FLAG_MATCH_DYNAMIC = 2;
  
  public static final int FLAG_MATCH_MANIFEST = 1;
  
  public static final int FLAG_MATCH_PINNED = 4;
  
  private static final String TAG = "ShortcutManager";
  
  private final Context mContext;
  
  private final IShortcutService mService;
  
  public ShortcutManager(Context paramContext) {
    this(paramContext, IShortcutService.Stub.asInterface(ServiceManager.getService("shortcut")));
  }
  
  public ShortcutManager(Context paramContext, IShortcutService paramIShortcutService) {
    this.mContext = paramContext;
    this.mService = paramIShortcutService;
  }
  
  public boolean addDynamicShortcuts(List<ShortcutInfo> paramList) {
    try {
      IShortcutService iShortcutService = this.mService;
      String str = this.mContext.getPackageName();
      ParceledListSlice<Parcelable> parceledListSlice = new ParceledListSlice<>();
      this((List)paramList);
      return iShortcutService.addDynamicShortcuts(str, parceledListSlice, injectMyUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Intent createShortcutResultIntent(ShortcutInfo paramShortcutInfo) {
    try {
      return this.mService.createShortcutResultIntent(this.mContext.getPackageName(), paramShortcutInfo, injectMyUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void disableShortcuts(List<String> paramList) {
    try {
      this.mService.disableShortcuts(this.mContext.getPackageName(), paramList, null, 0, injectMyUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void disableShortcuts(List<String> paramList, int paramInt) {
    try {
      this.mService.disableShortcuts(this.mContext.getPackageName(), paramList, null, paramInt, injectMyUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void disableShortcuts(List<String> paramList, CharSequence paramCharSequence) {
    try {
      this.mService.disableShortcuts(this.mContext.getPackageName(), paramList, paramCharSequence, 0, injectMyUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void disableShortcuts(List<String> paramList, String paramString) {
    disableShortcuts(paramList, paramString);
  }
  
  public void enableShortcuts(List<String> paramList) {
    try {
      this.mService.enableShortcuts(this.mContext.getPackageName(), paramList, injectMyUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ShortcutInfo> getDynamicShortcuts() {
    try {
      return this.mService.getShortcuts(this.mContext.getPackageName(), 2, injectMyUserId()).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getIconMaxHeight() {
    try {
      return this.mService.getIconMaxDimensions(this.mContext.getPackageName(), injectMyUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getIconMaxWidth() {
    try {
      return this.mService.getIconMaxDimensions(this.mContext.getPackageName(), injectMyUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ShortcutInfo> getManifestShortcuts() {
    try {
      return this.mService.getShortcuts(this.mContext.getPackageName(), 1, injectMyUserId()).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getMaxShortcutCountForActivity() {
    return getMaxShortcutCountPerActivity();
  }
  
  public int getMaxShortcutCountPerActivity() {
    try {
      return this.mService.getMaxShortcutCountPerActivity(this.mContext.getPackageName(), injectMyUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ShortcutInfo> getPinnedShortcuts() {
    try {
      return this.mService.getShortcuts(this.mContext.getPackageName(), 4, injectMyUserId()).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public long getRateLimitResetTime() {
    try {
      return this.mService.getRateLimitResetTime(this.mContext.getPackageName(), injectMyUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public int getRemainingCallCount() {
    try {
      return this.mService.getRemainingCallCount(this.mContext.getPackageName(), injectMyUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public List<ShareShortcutInfo> getShareTargets(IntentFilter paramIntentFilter) {
    try {
      return this.mService.getShareTargets(this.mContext.getPackageName(), paramIntentFilter, injectMyUserId()).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<ShortcutInfo> getShortcuts(int paramInt) {
    try {
      return this.mService.getShortcuts(this.mContext.getPackageName(), paramInt, injectMyUserId()).getList();
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public boolean hasShareTargets(String paramString) {
    try {
      return this.mService.hasShareTargets(this.mContext.getPackageName(), paramString, injectMyUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  protected int injectMyUserId() {
    return this.mContext.getUserId();
  }
  
  public boolean isRateLimitingActive() {
    try {
      boolean bool;
      int i = this.mService.getRemainingCallCount(this.mContext.getPackageName(), injectMyUserId());
      if (i == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean isRequestPinShortcutSupported() {
    try {
      return this.mService.isRequestPinItemSupported(injectMyUserId(), 1);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void onApplicationActive(String paramString, int paramInt) {
    try {
      this.mService.onApplicationActive(paramString, paramInt);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void pushDynamicShortcut(ShortcutInfo paramShortcutInfo) {
    try {
      this.mService.pushDynamicShortcut(this.mContext.getPackageName(), paramShortcutInfo, injectMyUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeAllDynamicShortcuts() {
    try {
      this.mService.removeAllDynamicShortcuts(this.mContext.getPackageName(), injectMyUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeDynamicShortcuts(List<String> paramList) {
    try {
      this.mService.removeDynamicShortcuts(this.mContext.getPackageName(), paramList, injectMyUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void removeLongLivedShortcuts(List<String> paramList) {
    try {
      this.mService.removeLongLivedShortcuts(this.mContext.getPackageName(), paramList, injectMyUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void reportShortcutUsed(String paramString) {
    try {
      this.mService.reportShortcutUsed(this.mContext.getPackageName(), paramString, injectMyUserId());
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean requestPinShortcut(ShortcutInfo paramShortcutInfo, IntentSender paramIntentSender) {
    try {
      return this.mService.requestPinShortcut(this.mContext.getPackageName(), paramShortcutInfo, paramIntentSender, injectMyUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean setDynamicShortcuts(List<ShortcutInfo> paramList) {
    try {
      IShortcutService iShortcutService = this.mService;
      String str = this.mContext.getPackageName();
      ParceledListSlice<Parcelable> parceledListSlice = new ParceledListSlice<>();
      this((List)paramList);
      return iShortcutService.setDynamicShortcuts(str, parceledListSlice, injectMyUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean updateShortcuts(List<ShortcutInfo> paramList) {
    try {
      IShortcutService iShortcutService = this.mService;
      String str = this.mContext.getPackageName();
      ParceledListSlice<Parcelable> parceledListSlice = new ParceledListSlice<>();
      this((List)paramList);
      return iShortcutService.updateShortcuts(str, parceledListSlice, injectMyUserId());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  @SystemApi
  public static final class ShareShortcutInfo implements Parcelable {
    public static final Parcelable.Creator<ShareShortcutInfo> CREATOR = new Parcelable.Creator<ShareShortcutInfo>() {
        public ShortcutManager.ShareShortcutInfo createFromParcel(Parcel param2Parcel) {
          return new ShortcutManager.ShareShortcutInfo(param2Parcel);
        }
        
        public ShortcutManager.ShareShortcutInfo[] newArray(int param2Int) {
          return new ShortcutManager.ShareShortcutInfo[param2Int];
        }
      };
    
    private final ShortcutInfo mShortcutInfo;
    
    private final ComponentName mTargetComponent;
    
    public ShareShortcutInfo(ShortcutInfo param1ShortcutInfo, ComponentName param1ComponentName) {
      if (param1ShortcutInfo != null) {
        if (param1ComponentName != null) {
          this.mShortcutInfo = param1ShortcutInfo;
          this.mTargetComponent = param1ComponentName;
          return;
        } 
        throw new NullPointerException("target component is null");
      } 
      throw new NullPointerException("shortcut info is null");
    }
    
    private ShareShortcutInfo(Parcel param1Parcel) {
      this.mShortcutInfo = (ShortcutInfo)param1Parcel.readParcelable(ShortcutInfo.class.getClassLoader());
      this.mTargetComponent = (ComponentName)param1Parcel.readParcelable(ComponentName.class.getClassLoader());
    }
    
    public int describeContents() {
      return 0;
    }
    
    public ShortcutInfo getShortcutInfo() {
      return this.mShortcutInfo;
    }
    
    public ComponentName getTargetComponent() {
      return this.mTargetComponent;
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      param1Parcel.writeParcelable(this.mShortcutInfo, param1Int);
      param1Parcel.writeParcelable((Parcelable)this.mTargetComponent, param1Int);
    }
  }
  
  class null implements Parcelable.Creator<ShareShortcutInfo> {
    public ShortcutManager.ShareShortcutInfo createFromParcel(Parcel param1Parcel) {
      return new ShortcutManager.ShareShortcutInfo(param1Parcel);
    }
    
    public ShortcutManager.ShareShortcutInfo[] newArray(int param1Int) {
      return new ShortcutManager.ShareShortcutInfo[param1Int];
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ShortcutMatchFlags {}
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/ShortcutManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
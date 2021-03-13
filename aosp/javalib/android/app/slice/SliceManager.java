package android.app.slice;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.util.ArraySet;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class SliceManager {
  public static final String ACTION_REQUEST_SLICE_PERMISSION = "com.android.intent.action.REQUEST_SLICE_PERMISSION";
  
  public static final String CATEGORY_SLICE = "android.app.slice.category.SLICE";
  
  public static final String SLICE_METADATA_KEY = "android.metadata.SLICE_URI";
  
  private static final String TAG = "SliceManager";
  
  private final Context mContext;
  
  private final ISliceManager mService;
  
  private final IBinder mToken = (IBinder)new Binder();
  
  public SliceManager(Context paramContext, Handler paramHandler) throws ServiceManager.ServiceNotFoundException {
    this.mContext = paramContext;
    this.mService = ISliceManager.Stub.asInterface(ServiceManager.getServiceOrThrow("slice"));
  }
  
  private String getAuthority(Intent paramIntent) {
    paramIntent = new Intent(paramIntent);
    if (!paramIntent.hasCategory("android.app.slice.category.SLICE"))
      paramIntent.addCategory("android.app.slice.category.SLICE"); 
    List list = this.mContext.getPackageManager().queryIntentContentProviders(paramIntent, 0);
    if (list != null && !list.isEmpty()) {
      String str = ((ResolveInfo)list.get(0)).providerInfo.authority;
    } else {
      list = null;
    } 
    return (String)list;
  }
  
  private Uri resolveStatic(Intent paramIntent, ContentResolver paramContentResolver) {
    boolean bool;
    Objects.requireNonNull(paramIntent, "intent");
    if (paramIntent.getComponent() != null || paramIntent.getPackage() != null || paramIntent.getData() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "Slice intent must be explicit %s", new Object[] { paramIntent });
    Uri uri = paramIntent.getData();
    if (uri != null && "vnd.android.slice".equals(paramContentResolver.getType(uri)))
      return uri; 
    ResolveInfo resolveInfo = this.mContext.getPackageManager().resolveActivity(paramIntent, 128);
    return (resolveInfo != null && resolveInfo.activityInfo != null && resolveInfo.activityInfo.metaData != null && resolveInfo.activityInfo.metaData.containsKey("android.metadata.SLICE_URI")) ? Uri.parse(resolveInfo.activityInfo.metaData.getString("android.metadata.SLICE_URI")) : null;
  }
  
  @Deprecated
  public Slice bindSlice(Intent paramIntent, List<SliceSpec> paramList) {
    return bindSlice(paramIntent, (Set<SliceSpec>)new ArraySet(paramList));
  }
  
  public Slice bindSlice(Intent paramIntent, Set<SliceSpec> paramSet) {
    boolean bool;
    Objects.requireNonNull(paramIntent, "intent");
    if (paramIntent.getComponent() != null || paramIntent.getPackage() != null || paramIntent.getData() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "Slice intent must be explicit %s", new Object[] { paramIntent });
    ContentResolver contentResolver = this.mContext.getContentResolver();
    Uri uri2 = resolveStatic(paramIntent, contentResolver);
    if (uri2 != null)
      return bindSlice(uri2, paramSet); 
    String str = getAuthority(paramIntent);
    if (str == null)
      return null; 
    Uri uri1 = (new Uri.Builder()).scheme("content").authority(str).build();
    try {
      ContentProviderClient contentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri1);
      if (contentProviderClient == null)
        try {
          Log.w("SliceManager", String.format("Unknown URI: %s", new Object[] { uri1 }));
          return null;
        } finally {
          if (contentProviderClient != null)
            try {
              contentProviderClient.close();
            } finally {
              paramSet = null;
            }  
        }  
      Bundle bundle2 = new Bundle();
      this();
      bundle2.putParcelable("slice_intent", (Parcelable)paramIntent);
      ArrayList arrayList = new ArrayList();
      this((Collection)paramSet);
      bundle2.putParcelableArrayList("supported_specs", arrayList);
      Bundle bundle1 = contentProviderClient.call("map_slice", null, bundle2);
      if (bundle1 == null) {
        if (contentProviderClient != null)
          contentProviderClient.close(); 
        return null;
      } 
      Slice slice = (Slice)bundle1.getParcelable("slice");
      if (contentProviderClient != null)
        contentProviderClient.close(); 
      return slice;
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  @Deprecated
  public Slice bindSlice(Uri paramUri, List<SliceSpec> paramList) {
    return bindSlice(paramUri, (Set<SliceSpec>)new ArraySet(paramList));
  }
  
  public Slice bindSlice(Uri paramUri, Set<SliceSpec> paramSet) {
    Objects.requireNonNull(paramUri, "uri");
    ContentResolver contentResolver = this.mContext.getContentResolver();
    try {
      ContentProviderClient contentProviderClient = contentResolver.acquireUnstableContentProviderClient(paramUri);
      if (contentProviderClient == null)
        try {
          Log.w("SliceManager", String.format("Unknown URI: %s", new Object[] { paramUri }));
          return null;
        } finally {
          if (contentProviderClient != null)
            try {
              contentProviderClient.close();
            } finally {
              paramSet = null;
            }  
        }  
      Bundle bundle2 = new Bundle();
      this();
      bundle2.putParcelable("slice_uri", (Parcelable)paramUri);
      ArrayList arrayList = new ArrayList();
      this((Collection)paramSet);
      bundle2.putParcelableArrayList("supported_specs", arrayList);
      Bundle bundle1 = contentProviderClient.call("bind_slice", null, bundle2);
      Bundle.setDefusable(bundle1, true);
      if (bundle1 == null) {
        if (contentProviderClient != null)
          contentProviderClient.close(); 
        return null;
      } 
      Slice slice = (Slice)bundle1.getParcelable("slice");
      if (contentProviderClient != null)
        contentProviderClient.close(); 
      return slice;
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  public int checkSlicePermission(Uri paramUri, int paramInt1, int paramInt2) {
    try {
      return this.mService.checkSlicePermission(paramUri, this.mContext.getPackageName(), null, paramInt1, paramInt2, null);
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void enforceSlicePermission(Uri paramUri, String paramString, int paramInt1, int paramInt2, String[] paramArrayOfString) {
    try {
      if (UserHandle.isSameApp(paramInt2, Process.myUid()))
        return; 
      if (paramString != null) {
        if (this.mService.checkSlicePermission(paramUri, this.mContext.getPackageName(), paramString, paramInt1, paramInt2, paramArrayOfString) != -1)
          return; 
        SecurityException securityException1 = new SecurityException();
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("User ");
        stringBuilder.append(paramInt2);
        stringBuilder.append(" does not have slice permission for ");
        stringBuilder.append(paramUri);
        stringBuilder.append(".");
        this(stringBuilder.toString());
        throw securityException1;
      } 
      SecurityException securityException = new SecurityException();
      this("No pkg specified");
      throw securityException;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public List<Uri> getPinnedSlices() {
    try {
      return Arrays.asList(this.mService.getPinnedSlices(this.mContext.getPackageName()));
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Set<SliceSpec> getPinnedSpecs(Uri paramUri) {
    try {
      return (Set<SliceSpec>)new ArraySet(Arrays.asList(this.mService.getPinnedSpecs(paramUri, this.mContext.getPackageName())));
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Collection<Uri> getSliceDescendants(Uri paramUri) {
    ContentResolver contentResolver = this.mContext.getContentResolver();
    try {
      ContentProviderClient contentProviderClient = contentResolver.acquireUnstableContentProviderClient(paramUri);
      try {
        Bundle bundle = new Bundle();
        this();
        bundle.putParcelable("slice_uri", (Parcelable)paramUri);
        return contentProviderClient.call("get_descendants", null, bundle).getParcelableArrayList("slice_descendants");
      } finally {
        if (contentProviderClient != null)
          try {
            contentProviderClient.close();
          } finally {
            contentProviderClient = null;
          }  
      } 
    } catch (RemoteException remoteException) {
      Log.e("SliceManager", "Unable to get slice descendants", (Throwable)remoteException);
      return Collections.emptyList();
    } 
  }
  
  public void grantPermissionFromUser(Uri paramUri, String paramString, boolean paramBoolean) {
    try {
      this.mService.grantPermissionFromUser(paramUri, paramString, this.mContext.getPackageName(), paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void grantSlicePermission(String paramString, Uri paramUri) {
    try {
      this.mService.grantSlicePermission(this.mContext.getPackageName(), paramString, paramUri);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public boolean hasSliceAccess() {
    try {
      return this.mService.hasSliceAccess(this.mContext.getPackageName());
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public Uri mapIntentToUri(Intent paramIntent) {
    ContentResolver contentResolver = this.mContext.getContentResolver();
    Uri uri2 = resolveStatic(paramIntent, contentResolver);
    if (uri2 != null)
      return uri2; 
    String str = getAuthority(paramIntent);
    if (str == null)
      return null; 
    Uri uri1 = (new Uri.Builder()).scheme("content").authority(str).build();
    try {
      ContentProviderClient contentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri1);
      if (contentProviderClient == null)
        try {
          Log.w("SliceManager", String.format("Unknown URI: %s", new Object[] { uri1 }));
          return null;
        } finally {
          if (contentProviderClient != null)
            try {
              contentProviderClient.close();
            } finally {
              contentProviderClient = null;
            }  
        }  
      Bundle bundle2 = new Bundle();
      this();
      bundle2.putParcelable("slice_intent", (Parcelable)paramIntent);
      Bundle bundle1 = contentProviderClient.call("map_only", null, bundle2);
      if (bundle1 == null) {
        if (contentProviderClient != null)
          contentProviderClient.close(); 
        return null;
      } 
      Uri uri = (Uri)bundle1.getParcelable("slice");
      if (contentProviderClient != null)
        contentProviderClient.close(); 
      return uri;
    } catch (RemoteException remoteException) {
      return null;
    } 
  }
  
  @Deprecated
  public void pinSlice(Uri paramUri, List<SliceSpec> paramList) {
    pinSlice(paramUri, (Set<SliceSpec>)new ArraySet(paramList));
  }
  
  public void pinSlice(Uri paramUri, Set<SliceSpec> paramSet) {
    try {
      this.mService.pinSlice(this.mContext.getPackageName(), paramUri, paramSet.<SliceSpec>toArray(new SliceSpec[paramSet.size()]), this.mToken);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void revokeSlicePermission(String paramString, Uri paramUri) {
    try {
      this.mService.revokeSlicePermission(this.mContext.getPackageName(), paramString, paramUri);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
  
  public void unpinSlice(Uri paramUri) {
    try {
      this.mService.unpinSlice(this.mContext.getPackageName(), paramUri, this.mToken);
      return;
    } catch (RemoteException remoteException) {
      throw remoteException.rethrowFromSystemServer();
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/SliceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
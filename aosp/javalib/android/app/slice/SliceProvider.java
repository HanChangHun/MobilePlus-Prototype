package android.app.slice;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Parcelable;
import android.os.Process;
import android.os.StrictMode;
import android.util.ArraySet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class SliceProvider extends ContentProvider {
  private static final boolean DEBUG = false;
  
  public static final String EXTRA_BIND_URI = "slice_uri";
  
  public static final String EXTRA_INTENT = "slice_intent";
  
  public static final String EXTRA_PKG = "pkg";
  
  public static final String EXTRA_PROVIDER_PKG = "provider_pkg";
  
  public static final String EXTRA_RESULT = "result";
  
  public static final String EXTRA_SLICE = "slice";
  
  public static final String EXTRA_SLICE_DESCENDANTS = "slice_descendants";
  
  public static final String EXTRA_SUPPORTED_SPECS = "supported_specs";
  
  public static final String METHOD_GET_DESCENDANTS = "get_descendants";
  
  public static final String METHOD_GET_PERMISSIONS = "get_permissions";
  
  public static final String METHOD_MAP_INTENT = "map_slice";
  
  public static final String METHOD_MAP_ONLY_INTENT = "map_only";
  
  public static final String METHOD_PIN = "pin";
  
  public static final String METHOD_SLICE = "bind_slice";
  
  public static final String METHOD_UNPIN = "unpin";
  
  private static final long SLICE_BIND_ANR = 2000L;
  
  public static final String SLICE_TYPE = "vnd.android.slice";
  
  private static final String TAG = "SliceProvider";
  
  private final Runnable mAnr = new _$$Lambda$SliceProvider$bIgM5f4PsMvz_YYWEeFTjvTqevw(this);
  
  private final String[] mAutoGrantPermissions = new String[0];
  
  private String mCallback;
  
  private SliceManager mSliceManager;
  
  public SliceProvider() {}
  
  public SliceProvider(String... paramVarArgs) {}
  
  public static PendingIntent createPermissionIntent(Context paramContext, Uri paramUri, String paramString) {
    Intent intent = new Intent("com.android.intent.action.REQUEST_SLICE_PERMISSION");
    intent.setComponent(ComponentName.unflattenFromString(paramContext.getResources().getString(17039948)));
    intent.putExtra("slice_uri", (Parcelable)paramUri);
    intent.putExtra("pkg", paramString);
    intent.putExtra("provider_pkg", paramContext.getPackageName());
    intent.setData(paramUri.buildUpon().appendQueryParameter("package", paramString).build());
    return PendingIntent.getActivity(paramContext, 0, intent, 0);
  }
  
  public static CharSequence getPermissionString(Context paramContext, String paramString) {
    PackageManager packageManager = paramContext.getPackageManager();
    try {
      return paramContext.getString(17041262, new Object[] { packageManager.getApplicationInfo(paramString, 0).loadLabel(packageManager), paramContext.getApplicationInfo().loadLabel(packageManager) });
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      throw new RuntimeException("Unknown calling app", nameNotFoundException);
    } 
  }
  
  private Slice handleBindSlice(Uri paramUri, List<SliceSpec> paramList, String paramString, int paramInt1, int paramInt2) {
    if (paramString == null)
      paramString = getContext().getPackageManager().getNameForUid(paramInt1); 
    try {
      this.mSliceManager.enforceSlicePermission(paramUri, paramString, paramInt2, paramInt1, this.mAutoGrantPermissions);
      this.mCallback = "onBindSlice";
      Handler.getMain().postDelayed(this.mAnr, 2000L);
      try {
        return onBindSliceStrict(paramUri, paramList);
      } finally {
        Handler.getMain().removeCallbacks(this.mAnr);
      } 
    } catch (SecurityException securityException) {
      return createPermissionSlice(getContext(), paramUri, paramString);
    } 
  }
  
  private Collection<Uri> handleGetDescendants(Uri paramUri) {
    this.mCallback = "onGetSliceDescendants";
    return onGetSliceDescendants(paramUri);
  }
  
  private void handlePinSlice(Uri paramUri) {
    this.mCallback = "onSlicePinned";
    Handler.getMain().postDelayed(this.mAnr, 2000L);
    try {
      onSlicePinned(paramUri);
      return;
    } finally {
      Handler.getMain().removeCallbacks(this.mAnr);
    } 
  }
  
  private void handleUnpinSlice(Uri paramUri) {
    this.mCallback = "onSliceUnpinned";
    Handler.getMain().postDelayed(this.mAnr, 2000L);
    try {
      onSliceUnpinned(paramUri);
      return;
    } finally {
      Handler.getMain().removeCallbacks(this.mAnr);
    } 
  }
  
  private Slice onBindSliceStrict(Uri paramUri, List<SliceSpec> paramList) {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
    try {
      StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder();
      this();
      StrictMode.setThreadPolicy(builder.detectAll().penaltyDeath().build());
      ArraySet arraySet = new ArraySet();
      this(paramList);
      return onBindSlice(paramUri, (Set<SliceSpec>)arraySet);
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
  
  private Uri validateIncomingUriOrNull(Uri paramUri) {
    if (paramUri == null) {
      paramUri = null;
    } else {
      paramUri = validateIncomingUri(paramUri);
    } 
    return paramUri;
  }
  
  public void attachInfo(Context paramContext, ProviderInfo paramProviderInfo) {
    super.attachInfo(paramContext, paramProviderInfo);
    this.mSliceManager = (SliceManager)paramContext.getSystemService(SliceManager.class);
  }
  
  public Bundle call(String paramString1, String paramString2, Bundle paramBundle) {
    Slice slice;
    Uri uri1;
    Bundle bundle;
    Uri uri2;
    ArrayList<SliceSpec> arrayList;
    if (paramString1.equals("bind_slice")) {
      slice = handleBindSlice(getUriWithoutUserId(validateIncomingUriOrNull((Uri)paramBundle.getParcelable("slice_uri"))), paramBundle.getParcelableArrayList("supported_specs"), getCallingPackage(), Binder.getCallingUid(), Binder.getCallingPid());
      Bundle bundle1 = new Bundle();
      bundle1.putParcelable("slice", slice);
      return bundle1;
    } 
    if (slice.equals("map_slice")) {
      Intent intent = (Intent)paramBundle.getParcelable("slice_intent");
      if (intent == null)
        return null; 
      uri1 = validateIncomingUriOrNull(onMapIntentToUri(intent));
      arrayList = paramBundle.getParcelableArrayList("supported_specs");
      Bundle bundle1 = new Bundle();
      if (uri1 != null) {
        bundle1.putParcelable("slice", handleBindSlice(uri1, arrayList, getCallingPackage(), Binder.getCallingUid(), Binder.getCallingPid()));
      } else {
        bundle1.putParcelable("slice", null);
      } 
      return bundle1;
    } 
    if (uri1.equals("map_only")) {
      Intent intent = (Intent)arrayList.getParcelable("slice_intent");
      if (intent == null)
        return null; 
      uri2 = validateIncomingUriOrNull(onMapIntentToUri(intent));
      bundle = new Bundle();
      bundle.putParcelable("slice", (Parcelable)uri2);
      return bundle;
    } 
    if (bundle.equals("pin")) {
      Uri uri = getUriWithoutUserId(validateIncomingUriOrNull((Uri)arrayList.getParcelable("slice_uri")));
      if (Binder.getCallingUid() == 1000) {
        handlePinSlice(uri);
      } else {
        throw new SecurityException("Only the system can pin/unpin slices");
      } 
    } else if (bundle.equals("unpin")) {
      Uri uri = getUriWithoutUserId(validateIncomingUriOrNull((Uri)arrayList.getParcelable("slice_uri")));
      if (Binder.getCallingUid() == 1000) {
        handleUnpinSlice(uri);
      } else {
        throw new SecurityException("Only the system can pin/unpin slices");
      } 
    } else {
      if (bundle.equals("get_descendants")) {
        uri2 = getUriWithoutUserId(validateIncomingUriOrNull((Uri)arrayList.getParcelable("slice_uri")));
        bundle = new Bundle();
        bundle.putParcelableArrayList("slice_descendants", new ArrayList<>(handleGetDescendants(uri2)));
        return bundle;
      } 
      if (bundle.equals("get_permissions")) {
        if (Binder.getCallingUid() == 1000) {
          bundle = new Bundle();
          bundle.putStringArray("result", this.mAutoGrantPermissions);
          return bundle;
        } 
        throw new SecurityException("Only the system can get permissions");
      } 
    } 
    return super.call((String)bundle, (String)uri2, (Bundle)arrayList);
  }
  
  public Slice createPermissionSlice(Context paramContext, Uri paramUri, String paramString) {
    this.mCallback = "onCreatePermissionRequest";
    Handler.getMain().postDelayed(this.mAnr, 2000L);
    try {
      PendingIntent pendingIntent = onCreatePermissionRequest(paramUri);
      Handler.getMain().removeCallbacks(this.mAnr);
      Slice.Builder builder1 = new Slice.Builder(paramUri);
      Slice.Builder builder2 = (new Slice.Builder(builder1)).addIcon(Icon.createWithResource(paramContext, 17302789), null, Collections.emptyList()).addHints(Arrays.asList(new String[] { "title", "shortcut" })).addAction(pendingIntent, (new Slice.Builder(builder1)).build(), null);
      TypedValue typedValue = new TypedValue();
      (new ContextThemeWrapper(paramContext, 16974123)).getTheme().resolveAttribute(16843829, typedValue, true);
      int i = typedValue.data;
      return builder1.addHints(Arrays.asList(new String[] { "permission_request" })).build();
    } finally {
      Handler.getMain().removeCallbacks(this.mAnr);
    } 
  }
  
  public final int delete(Uri paramUri, String paramString, String[] paramArrayOfString) {
    return 0;
  }
  
  public final String getType(Uri paramUri) {
    return "vnd.android.slice";
  }
  
  public final Uri insert(Uri paramUri, ContentValues paramContentValues) {
    return null;
  }
  
  @Deprecated
  public Slice onBindSlice(Uri paramUri, List<SliceSpec> paramList) {
    return null;
  }
  
  public Slice onBindSlice(Uri paramUri, Set<SliceSpec> paramSet) {
    return onBindSlice(paramUri, new ArrayList<>(paramSet));
  }
  
  public PendingIntent onCreatePermissionRequest(Uri paramUri) {
    return createPermissionIntent(getContext(), paramUri, getCallingPackage());
  }
  
  public Collection<Uri> onGetSliceDescendants(Uri paramUri) {
    return Collections.emptyList();
  }
  
  public Uri onMapIntentToUri(Intent paramIntent) {
    throw new UnsupportedOperationException("This provider has not implemented intent to uri mapping");
  }
  
  public void onSlicePinned(Uri paramUri) {}
  
  public void onSliceUnpinned(Uri paramUri) {}
  
  public final Cursor query(Uri paramUri, String[] paramArrayOfString, Bundle paramBundle, CancellationSignal paramCancellationSignal) {
    return null;
  }
  
  public final Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2) {
    return null;
  }
  
  public final Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2, CancellationSignal paramCancellationSignal) {
    return null;
  }
  
  public final int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString) {
    return 0;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/slice/SliceProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
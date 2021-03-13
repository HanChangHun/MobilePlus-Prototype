package android.content;

import android.content.pm.RegisteredServicesCache;
import android.content.pm.XmlSerializerAndParser;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.util.SparseArray;
import com.android.internal.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class SyncAdaptersCache extends RegisteredServicesCache<SyncAdapterType> {
  private static final String ATTRIBUTES_NAME = "sync-adapter";
  
  private static final String SERVICE_INTERFACE = "android.content.SyncAdapter";
  
  private static final String SERVICE_META_DATA = "android.content.SyncAdapter";
  
  private static final String TAG = "Account";
  
  private static final MySerializer sSerializer = new MySerializer();
  
  private SparseArray<ArrayMap<String, String[]>> mAuthorityToSyncAdapters = new SparseArray();
  
  public SyncAdaptersCache(Context paramContext) {
    super(paramContext, "android.content.SyncAdapter", "android.content.SyncAdapter", "sync-adapter", sSerializer);
  }
  
  public String[] getSyncAdapterPackagesForAuthority(String paramString, int paramInt) {
    synchronized (this.mServicesLock) {
      String[] arrayOfString1;
      ArrayMap arrayMap1 = (ArrayMap)this.mAuthorityToSyncAdapters.get(paramInt);
      ArrayMap arrayMap2 = arrayMap1;
      if (arrayMap1 == null) {
        arrayMap2 = new ArrayMap();
        this();
        this.mAuthorityToSyncAdapters.put(paramInt, arrayMap2);
      } 
      if (arrayMap2.containsKey(paramString)) {
        arrayOfString1 = (String[])arrayMap2.get(paramString);
        return arrayOfString1;
      } 
      Collection collection = getAllServices(paramInt);
      ArrayList<String> arrayList = new ArrayList();
      this();
      for (RegisteredServicesCache.ServiceInfo serviceInfo : collection) {
        if (arrayOfString1.equals(((SyncAdapterType)serviceInfo.type).authority) && serviceInfo.componentName != null)
          arrayList.add(serviceInfo.componentName.getPackageName()); 
      } 
      String[] arrayOfString2 = new String[arrayList.size()];
      arrayList.toArray(arrayOfString2);
      arrayMap2.put(arrayOfString1, arrayOfString2);
      return arrayOfString2;
    } 
  }
  
  protected void onServicesChangedLocked(int paramInt) {
    synchronized (this.mServicesLock) {
      ArrayMap arrayMap = (ArrayMap)this.mAuthorityToSyncAdapters.get(paramInt);
      if (arrayMap != null)
        arrayMap.clear(); 
      super.onServicesChangedLocked(paramInt);
      return;
    } 
  }
  
  protected void onUserRemoved(int paramInt) {
    synchronized (this.mServicesLock) {
      this.mAuthorityToSyncAdapters.remove(paramInt);
      super.onUserRemoved(paramInt);
      return;
    } 
  }
  
  public SyncAdapterType parseServiceAttributes(Resources paramResources, String paramString, AttributeSet paramAttributeSet) {
    TypedArray typedArray = paramResources.obtainAttributes(paramAttributeSet, R.styleable.SyncAdapter);
    try {
      String str1 = typedArray.getString(2);
      String str2 = typedArray.getString(1);
      if (TextUtils.isEmpty(str1) || TextUtils.isEmpty(str2))
        return null; 
      boolean bool1 = typedArray.getBoolean(3, true);
      boolean bool2 = typedArray.getBoolean(4, true);
      boolean bool3 = typedArray.getBoolean(6, false);
      boolean bool4 = typedArray.getBoolean(5, false);
      return new SyncAdapterType(str1, str2, bool1, bool2, bool3, bool4, typedArray.getString(0), paramString);
    } finally {
      typedArray.recycle();
    } 
  }
  
  static class MySerializer implements XmlSerializerAndParser<SyncAdapterType> {
    public SyncAdapterType createFromXml(XmlPullParser param1XmlPullParser) throws IOException, XmlPullParserException {
      return SyncAdapterType.newKey(param1XmlPullParser.getAttributeValue(null, "authority"), param1XmlPullParser.getAttributeValue(null, "accountType"));
    }
    
    public void writeAsXml(SyncAdapterType param1SyncAdapterType, XmlSerializer param1XmlSerializer) throws IOException {
      param1XmlSerializer.attribute(null, "authority", param1SyncAdapterType.authority);
      param1XmlSerializer.attribute(null, "accountType", param1SyncAdapterType.accountType);
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/SyncAdaptersCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
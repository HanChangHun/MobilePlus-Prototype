package android.content;

import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class LoggingContentInterface implements ContentInterface {
  private final ContentInterface delegate;
  
  private final String tag;
  
  public LoggingContentInterface(String paramString, ContentInterface paramContentInterface) {
    this.tag = paramString;
    this.delegate = paramContentInterface;
  }
  
  public ContentProviderResult[] applyBatch(String paramString, ArrayList<ContentProviderOperation> paramArrayList) throws RemoteException, OperationApplicationException {
    Logger logger = new Logger("applyBatch", new Object[] { paramString, paramArrayList });
    try {
      ContentProviderResult[] arrayOfContentProviderResult = logger.<ContentProviderResult[]>setResult(this.delegate.applyBatch(paramString, paramArrayList));
      logger.close();
      return arrayOfContentProviderResult;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      paramArrayList = null;
    } 
  }
  
  public int bulkInsert(Uri paramUri, ContentValues[] paramArrayOfContentValues) throws RemoteException {
    Logger logger = new Logger("bulkInsert", new Object[] { paramUri, paramArrayOfContentValues });
    try {
      int i = ((Integer)logger.<Integer>setResult(Integer.valueOf(this.delegate.bulkInsert(paramUri, paramArrayOfContentValues)))).intValue();
      logger.close();
      return i;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      paramArrayOfContentValues = null;
    } 
  }
  
  public Bundle call(String paramString1, String paramString2, String paramString3, Bundle paramBundle) throws RemoteException {
    Logger logger = new Logger("call", new Object[] { paramString1, paramString2, paramString3, paramBundle });
    try {
      Bundle bundle = logger.<Bundle>setResult(this.delegate.call(paramString1, paramString2, paramString3, paramBundle));
      logger.close();
      return bundle;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      paramString2 = null;
    } 
  }
  
  public Uri canonicalize(Uri paramUri) throws RemoteException {
    Logger logger = new Logger("canonicalize", new Object[] { paramUri });
    try {
      paramUri = logger.<Uri>setResult(this.delegate.canonicalize(paramUri));
      logger.close();
      return paramUri;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      logger = null;
    } 
  }
  
  public int checkUriPermission(Uri paramUri, int paramInt1, int paramInt2) throws RemoteException {
    Logger logger = new Logger("checkUriPermission", new Object[] { paramUri, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
    try {
      paramInt1 = ((Integer)logger.<Integer>setResult(Integer.valueOf(this.delegate.checkUriPermission(paramUri, paramInt1, paramInt2)))).intValue();
      logger.close();
      return paramInt1;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      logger = null;
    } 
  }
  
  public int delete(Uri paramUri, Bundle paramBundle) throws RemoteException {
    Logger logger = new Logger("delete", new Object[] { paramUri, paramBundle });
    try {
      int i = ((Integer)logger.<Integer>setResult(Integer.valueOf(this.delegate.delete(paramUri, paramBundle)))).intValue();
      logger.close();
      return i;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      paramBundle = null;
    } 
  }
  
  public String[] getStreamTypes(Uri paramUri, String paramString) throws RemoteException {
    Logger logger = new Logger("getStreamTypes", new Object[] { paramUri, paramString });
    try {
      String[] arrayOfString = logger.<String[]>setResult(this.delegate.getStreamTypes(paramUri, paramString));
      logger.close();
      return arrayOfString;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      paramString = null;
    } 
  }
  
  public String getType(Uri paramUri) throws RemoteException {
    Logger logger = new Logger("getType", new Object[] { paramUri });
    try {
      String str = logger.<String>setResult(this.delegate.getType(paramUri));
      logger.close();
      return str;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      logger = null;
    } 
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) throws RemoteException {
    Logger logger = new Logger("insert", new Object[] { paramUri, paramContentValues, paramBundle });
    try {
      paramUri = logger.<Uri>setResult(this.delegate.insert(paramUri, paramContentValues, paramBundle));
      logger.close();
      return paramUri;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      paramContentValues = null;
    } 
  }
  
  public AssetFileDescriptor openAssetFile(Uri paramUri, String paramString, CancellationSignal paramCancellationSignal) throws RemoteException, FileNotFoundException {
    Logger logger = new Logger("openAssetFile", new Object[] { paramUri, paramString, paramCancellationSignal });
    try {
      AssetFileDescriptor assetFileDescriptor = logger.<AssetFileDescriptor>setResult(this.delegate.openAssetFile(paramUri, paramString, paramCancellationSignal));
      logger.close();
      return assetFileDescriptor;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      paramString = null;
    } 
  }
  
  public ParcelFileDescriptor openFile(Uri paramUri, String paramString, CancellationSignal paramCancellationSignal) throws RemoteException, FileNotFoundException {
    Logger logger = new Logger("openFile", new Object[] { paramUri, paramString, paramCancellationSignal });
    try {
      ParcelFileDescriptor parcelFileDescriptor = logger.<ParcelFileDescriptor>setResult(this.delegate.openFile(paramUri, paramString, paramCancellationSignal));
      logger.close();
      return parcelFileDescriptor;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      paramString = null;
    } 
  }
  
  public AssetFileDescriptor openTypedAssetFile(Uri paramUri, String paramString, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws RemoteException, FileNotFoundException {
    Logger logger = new Logger("openTypedAssetFile", new Object[] { paramUri, paramString, paramBundle, paramCancellationSignal });
    try {
      AssetFileDescriptor assetFileDescriptor = logger.<AssetFileDescriptor>setResult(this.delegate.openTypedAssetFile(paramUri, paramString, paramBundle, paramCancellationSignal));
      logger.close();
      return assetFileDescriptor;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      paramString = null;
    } 
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws RemoteException {
    Logger logger = new Logger("query", new Object[] { paramUri, paramArrayOfString, paramBundle, paramCancellationSignal });
    try {
      Cursor cursor = logger.<Cursor>setResult(this.delegate.query(paramUri, paramArrayOfString, paramBundle, paramCancellationSignal));
      logger.close();
      return cursor;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      paramArrayOfString = null;
    } 
  }
  
  public boolean refresh(Uri paramUri, Bundle paramBundle, CancellationSignal paramCancellationSignal) throws RemoteException {
    Logger logger = new Logger("refresh", new Object[] { paramUri, paramBundle, paramCancellationSignal });
    try {
      boolean bool = ((Boolean)logger.<Boolean>setResult(Boolean.valueOf(this.delegate.refresh(paramUri, paramBundle, paramCancellationSignal)))).booleanValue();
      logger.close();
      return bool;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      paramBundle = null;
    } 
  }
  
  public Uri uncanonicalize(Uri paramUri) throws RemoteException {
    Logger logger = new Logger("uncanonicalize", new Object[] { paramUri });
    try {
      paramUri = logger.<Uri>setResult(this.delegate.uncanonicalize(paramUri));
      logger.close();
      return paramUri;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      logger = null;
    } 
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, Bundle paramBundle) throws RemoteException {
    Logger logger = new Logger("update", new Object[] { paramUri, paramContentValues, paramBundle });
    try {
      int i = ((Integer)logger.<Integer>setResult(Integer.valueOf(this.delegate.update(paramUri, paramContentValues, paramBundle)))).intValue();
      logger.close();
      return i;
    } catch (Exception exception) {
      logger.setResult(exception);
      throw exception;
    } finally {}
    try {
      logger.close();
    } finally {
      paramContentValues = null;
    } 
  }
  
  private class Logger implements AutoCloseable {
    private final StringBuilder sb = new StringBuilder();
    
    public Logger(String param1String, Object... param1VarArgs) {
      int i = param1VarArgs.length;
      for (byte b = 0; b < i; b++) {
        Object object = param1VarArgs[b];
        if (object instanceof Bundle)
          ((Bundle)object).size(); 
      } 
      StringBuilder stringBuilder = this.sb;
      stringBuilder.append("callingUid=");
      stringBuilder.append(Binder.getCallingUid());
      stringBuilder.append(' ');
      this.sb.append(param1String);
      stringBuilder = this.sb;
      stringBuilder.append('(');
      stringBuilder.append(deepToString(param1VarArgs));
      stringBuilder.append(')');
    }
    
    private String deepToString(Object param1Object) {
      return (param1Object != null && param1Object.getClass().isArray()) ? Arrays.deepToString((Object[])param1Object) : String.valueOf(param1Object);
    }
    
    public void close() {
      Log.v(LoggingContentInterface.this.tag, this.sb.toString());
    }
    
    public <T> T setResult(T param1T) {
      if (param1T instanceof Cursor) {
        this.sb.append('\n');
        DatabaseUtils.dumpCursor((Cursor)param1T, this.sb);
      } else {
        StringBuilder stringBuilder = this.sb;
        stringBuilder.append(" = ");
        stringBuilder.append(deepToString(param1T));
      } 
      return param1T;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/LoggingContentInterface.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
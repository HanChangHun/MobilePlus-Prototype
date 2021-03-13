package android.app.backup;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.system.ErrnoException;
import android.system.Os;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class FullBackup {
  public static final String APK_TREE_TOKEN = "a";
  
  public static final String APPS_PREFIX = "apps/";
  
  public static final String CACHE_TREE_TOKEN = "c";
  
  public static final String CONF_TOKEN_INTENT_EXTRA = "conftoken";
  
  public static final String DATABASE_TREE_TOKEN = "db";
  
  public static final String DEVICE_CACHE_TREE_TOKEN = "d_c";
  
  public static final String DEVICE_DATABASE_TREE_TOKEN = "d_db";
  
  public static final String DEVICE_FILES_TREE_TOKEN = "d_f";
  
  public static final String DEVICE_NO_BACKUP_TREE_TOKEN = "d_nb";
  
  public static final String DEVICE_ROOT_TREE_TOKEN = "d_r";
  
  public static final String DEVICE_SHAREDPREFS_TREE_TOKEN = "d_sp";
  
  public static final String FILES_TREE_TOKEN = "f";
  
  public static final String FLAG_REQUIRED_CLIENT_SIDE_ENCRYPTION = "clientSideEncryption";
  
  public static final String FLAG_REQUIRED_DEVICE_TO_DEVICE_TRANSFER = "deviceToDeviceTransfer";
  
  public static final String FLAG_REQUIRED_FAKE_CLIENT_SIDE_ENCRYPTION = "fakeClientSideEncryption";
  
  public static final String FULL_BACKUP_INTENT_ACTION = "fullback";
  
  public static final String FULL_RESTORE_INTENT_ACTION = "fullrest";
  
  public static final String KEY_VALUE_DATA_TOKEN = "k";
  
  public static final String MANAGED_EXTERNAL_TREE_TOKEN = "ef";
  
  public static final String NO_BACKUP_TREE_TOKEN = "nb";
  
  public static final String OBB_TREE_TOKEN = "obb";
  
  public static final String ROOT_TREE_TOKEN = "r";
  
  public static final String SHAREDPREFS_TREE_TOKEN = "sp";
  
  public static final String SHARED_PREFIX = "shared/";
  
  public static final String SHARED_STORAGE_TOKEN = "shared";
  
  static final String TAG = "FullBackup";
  
  static final String TAG_XML_PARSER = "BackupXmlParserLogging";
  
  private static final Map<String, BackupScheme> kPackageBackupSchemeMap = (Map<String, BackupScheme>)new ArrayMap();
  
  public static native int backupToTar(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, FullBackupDataOutput paramFullBackupDataOutput);
  
  static BackupScheme getBackupScheme(Context paramContext) {
    // Byte code:
    //   0: ldc android/app/backup/FullBackup
    //   2: monitorenter
    //   3: getstatic android/app/backup/FullBackup.kPackageBackupSchemeMap : Ljava/util/Map;
    //   6: aload_0
    //   7: invokevirtual getPackageName : ()Ljava/lang/String;
    //   10: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   15: checkcast android/app/backup/FullBackup$BackupScheme
    //   18: astore_1
    //   19: aload_1
    //   20: astore_2
    //   21: aload_1
    //   22: ifnonnull -> 48
    //   25: new android/app/backup/FullBackup$BackupScheme
    //   28: astore_2
    //   29: aload_2
    //   30: aload_0
    //   31: invokespecial <init> : (Landroid/content/Context;)V
    //   34: getstatic android/app/backup/FullBackup.kPackageBackupSchemeMap : Ljava/util/Map;
    //   37: aload_0
    //   38: invokevirtual getPackageName : ()Ljava/lang/String;
    //   41: aload_2
    //   42: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   47: pop
    //   48: ldc android/app/backup/FullBackup
    //   50: monitorexit
    //   51: aload_2
    //   52: areturn
    //   53: astore_0
    //   54: ldc android/app/backup/FullBackup
    //   56: monitorexit
    //   57: aload_0
    //   58: athrow
    // Exception table:
    //   from	to	target	type
    //   3	19	53	finally
    //   25	34	53	finally
    //   34	48	53	finally
  }
  
  public static BackupScheme getBackupSchemeForTest(Context paramContext) {
    BackupScheme backupScheme = new BackupScheme(paramContext);
    backupScheme.mExcludes = new ArraySet();
    backupScheme.mIncludes = (Map<String, Set<BackupScheme.PathWithRequiredFlags>>)new ArrayMap();
    return backupScheme;
  }
  
  public static void restoreFile(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong1, int paramInt, long paramLong2, long paramLong3, File paramFile) throws IOException {
    if (paramInt == 2) {
      if (paramFile != null)
        paramFile.mkdirs(); 
    } else {
      StringBuilder stringBuilder2;
      StringBuilder stringBuilder1 = null;
      File file = null;
      if (paramFile != null)
        try {
          file = paramFile.getParentFile();
          if (!file.exists())
            file.mkdirs(); 
          FileOutputStream fileOutputStream = new FileOutputStream();
          this(paramFile);
        } catch (IOException iOException) {
          stringBuilder2 = new StringBuilder();
          stringBuilder2.append("Unable to create/open file ");
          stringBuilder2.append(paramFile.getPath());
          Log.e("FullBackup", stringBuilder2.toString(), iOException);
          stringBuilder2 = stringBuilder1;
        }  
      byte[] arrayOfByte = new byte[32768];
      FileInputStream fileInputStream = new FileInputStream(paramParcelFileDescriptor.getFileDescriptor());
      long l = paramLong1;
      while (l > 0L) {
        if (l > arrayOfByte.length) {
          paramInt = arrayOfByte.length;
        } else {
          paramInt = (int)l;
        } 
        paramInt = fileInputStream.read(arrayOfByte, 0, paramInt);
        if (paramInt <= 0) {
          StringBuilder stringBuilder3 = new StringBuilder();
          stringBuilder3.append("Incomplete read: expected ");
          stringBuilder3.append(l);
          stringBuilder3.append(" but got ");
          stringBuilder3.append(paramLong1 - l);
          Log.w("FullBackup", stringBuilder3.toString());
          break;
        } 
        StringBuilder stringBuilder = stringBuilder2;
        if (stringBuilder2 != null)
          try {
            stringBuilder2.write(arrayOfByte, 0, paramInt);
            stringBuilder = stringBuilder2;
          } catch (IOException iOException) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("Unable to write to file ");
            stringBuilder.append(paramFile.getPath());
            Log.e("FullBackup", stringBuilder.toString(), iOException);
            stringBuilder2.close();
            paramFile.delete();
            stringBuilder = null;
          }  
        l -= paramInt;
        stringBuilder2 = stringBuilder;
      } 
      if (stringBuilder2 != null)
        stringBuilder2.close(); 
    } 
    if (paramLong2 >= 0L && paramFile != null) {
      try {
        Os.chmod(paramFile.getPath(), (int)(paramLong2 & 0x1C0L));
      } catch (ErrnoException errnoException) {
        errnoException.rethrowAsIOException();
      } 
      paramFile.setLastModified(paramLong3);
    } 
  }
  
  public static class BackupScheme {
    private static final String TAG_EXCLUDE = "exclude";
    
    private static final String TAG_INCLUDE = "include";
    
    private final File CACHE_DIR;
    
    private final File DATABASE_DIR;
    
    private final File DEVICE_CACHE_DIR;
    
    private final File DEVICE_DATABASE_DIR;
    
    private final File DEVICE_FILES_DIR;
    
    private final File DEVICE_NOBACKUP_DIR;
    
    private final File DEVICE_ROOT_DIR;
    
    private final File DEVICE_SHAREDPREF_DIR;
    
    private final File EXTERNAL_DIR;
    
    private final File FILES_DIR;
    
    private final File NOBACKUP_DIR;
    
    private final File ROOT_DIR;
    
    private final File SHAREDPREF_DIR;
    
    ArraySet<PathWithRequiredFlags> mExcludes;
    
    final int mFullBackupContent;
    
    Map<String, Set<PathWithRequiredFlags>> mIncludes;
    
    final PackageManager mPackageManager;
    
    final String mPackageName;
    
    final StorageManager mStorageManager;
    
    private StorageVolume[] mVolumes = null;
    
    BackupScheme(Context param1Context) {
      this.mFullBackupContent = (param1Context.getApplicationInfo()).fullBackupContent;
      this.mStorageManager = (StorageManager)param1Context.getSystemService("storage");
      this.mPackageManager = param1Context.getPackageManager();
      this.mPackageName = param1Context.getPackageName();
      Context context = param1Context.createCredentialProtectedStorageContext();
      this.FILES_DIR = context.getFilesDir();
      this.DATABASE_DIR = context.getDatabasePath("foo").getParentFile();
      this.ROOT_DIR = context.getDataDir();
      this.SHAREDPREF_DIR = context.getSharedPreferencesPath("foo").getParentFile();
      this.CACHE_DIR = context.getCacheDir();
      this.NOBACKUP_DIR = context.getNoBackupFilesDir();
      context = param1Context.createDeviceProtectedStorageContext();
      this.DEVICE_FILES_DIR = context.getFilesDir();
      this.DEVICE_DATABASE_DIR = context.getDatabasePath("foo").getParentFile();
      this.DEVICE_ROOT_DIR = context.getDataDir();
      this.DEVICE_SHAREDPREF_DIR = context.getSharedPreferencesPath("foo").getParentFile();
      this.DEVICE_CACHE_DIR = context.getCacheDir();
      this.DEVICE_NOBACKUP_DIR = context.getNoBackupFilesDir();
      if (Process.myUid() != 1000) {
        this.EXTERNAL_DIR = param1Context.getExternalFilesDir(null);
      } else {
        this.EXTERNAL_DIR = null;
      } 
    }
    
    private File extractCanonicalFile(File param1File, String param1String) {
      String str = param1String;
      if (param1String == null)
        str = ""; 
      if (str.contains("..")) {
        if (Log.isLoggable("BackupXmlParserLogging", 2)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("...resolved \"");
          stringBuilder.append(param1File.getPath());
          stringBuilder.append(" ");
          stringBuilder.append(str);
          stringBuilder.append("\", but the \"..\" path is not permitted; skipping.");
          Log.v("BackupXmlParserLogging", stringBuilder.toString());
        } 
        return null;
      } 
      if (str.contains("//")) {
        if (Log.isLoggable("BackupXmlParserLogging", 2)) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("...resolved \"");
          stringBuilder.append(param1File.getPath());
          stringBuilder.append(" ");
          stringBuilder.append(str);
          stringBuilder.append("\", which contains the invalid \"//\" sequence; skipping.");
          Log.v("BackupXmlParserLogging", stringBuilder.toString());
        } 
        return null;
      } 
      return new File(param1File, str);
    }
    
    private File getDirectoryForCriteriaDomain(String param1String) {
      return TextUtils.isEmpty(param1String) ? null : ("file".equals(param1String) ? this.FILES_DIR : ("database".equals(param1String) ? this.DATABASE_DIR : ("root".equals(param1String) ? this.ROOT_DIR : ("sharedpref".equals(param1String) ? this.SHAREDPREF_DIR : ("device_file".equals(param1String) ? this.DEVICE_FILES_DIR : ("device_database".equals(param1String) ? this.DEVICE_DATABASE_DIR : ("device_root".equals(param1String) ? this.DEVICE_ROOT_DIR : ("device_sharedpref".equals(param1String) ? this.DEVICE_SHAREDPREF_DIR : ("external".equals(param1String) ? this.EXTERNAL_DIR : null)))))))));
    }
    
    private int getRequiredFlagsFromString(String param1String) {
      int i = 0;
      if (param1String == null || param1String.length() == 0)
        return 0; 
      String[] arrayOfString = param1String.split("\\|");
      int j = arrayOfString.length;
      byte b = 0;
      while (b < j) {
        String str = arrayOfString[b];
        int k = -1;
        int m = str.hashCode();
        if (m != 482744282) {
          if (m != 1499007205) {
            if (m == 1935925810 && str.equals("deviceToDeviceTransfer"))
              k = 1; 
          } else if (str.equals("clientSideEncryption")) {
            k = 0;
          } 
        } else if (str.equals("fakeClientSideEncryption")) {
          k = 2;
        } 
        if (k != 0) {
          if (k != 1) {
            if (k != 2) {
              k = i;
            } else {
              k = i | Integer.MIN_VALUE;
            } 
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Unrecognized requiredFlag provided, value: \"");
            stringBuilder.append(str);
            stringBuilder.append("\"");
            Log.w("FullBackup", stringBuilder.toString());
          } else {
            k = i | 0x2;
          } 
        } else {
          k = i | 0x1;
        } 
        b++;
        i = k;
      } 
      return i;
    }
    
    private String getTokenForXmlDomain(String param1String) {
      return "root".equals(param1String) ? "r" : ("file".equals(param1String) ? "f" : ("database".equals(param1String) ? "db" : ("sharedpref".equals(param1String) ? "sp" : ("device_root".equals(param1String) ? "d_r" : ("device_file".equals(param1String) ? "d_f" : ("device_database".equals(param1String) ? "d_db" : ("device_sharedpref".equals(param1String) ? "d_sp" : ("external".equals(param1String) ? "ef" : null))))))));
    }
    
    private StorageVolume[] getVolumeList() {
      StorageManager storageManager = this.mStorageManager;
      if (storageManager != null) {
        if (this.mVolumes == null)
          this.mVolumes = storageManager.getVolumeList(); 
      } else {
        Log.e("FullBackup", "Unable to access Storage Manager");
      } 
      return this.mVolumes;
    }
    
    private void maybeParseBackupSchemeLocked() throws IOException, XmlPullParserException {
      this.mIncludes = (Map<String, Set<PathWithRequiredFlags>>)new ArrayMap();
      this.mExcludes = new ArraySet();
      if (this.mFullBackupContent == 0) {
        if (Log.isLoggable("BackupXmlParserLogging", 2))
          Log.v("BackupXmlParserLogging", "android:fullBackupContent - \"true\""); 
      } else {
        if (Log.isLoggable("BackupXmlParserLogging", 2))
          Log.v("BackupXmlParserLogging", "android:fullBackupContent - found xml resource"); 
        XmlResourceParser xmlResourceParser1 = null;
        XmlResourceParser xmlResourceParser2 = null;
        try {
          XmlResourceParser xmlResourceParser = this.mPackageManager.getResourcesForApplication(this.mPackageName).getXml(this.mFullBackupContent);
          xmlResourceParser2 = xmlResourceParser;
          xmlResourceParser1 = xmlResourceParser;
          parseBackupSchemeFromXmlLocked((XmlPullParser)xmlResourceParser, (Set<PathWithRequiredFlags>)this.mExcludes, this.mIncludes);
          if (xmlResourceParser != null)
            xmlResourceParser.close(); 
          return;
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
          xmlResourceParser2 = xmlResourceParser1;
          IOException iOException = new IOException();
          xmlResourceParser2 = xmlResourceParser1;
          this((Throwable)nameNotFoundException);
          xmlResourceParser2 = xmlResourceParser1;
          throw iOException;
        } finally {}
        if (xmlResourceParser2 != null)
          xmlResourceParser2.close(); 
        throw xmlResourceParser1;
      } 
    }
    
    private Set<PathWithRequiredFlags> parseCurrentTagForDomain(XmlPullParser param1XmlPullParser, Set<PathWithRequiredFlags> param1Set, Map<String, Set<PathWithRequiredFlags>> param1Map, String param1String) throws XmlPullParserException {
      ArraySet arraySet;
      if ("include".equals(param1XmlPullParser.getName())) {
        param1String = getTokenForXmlDomain(param1String);
        param1Set = param1Map.get(param1String);
        Set<PathWithRequiredFlags> set = param1Set;
        if (param1Set == null) {
          arraySet = new ArraySet();
          param1Map.put(param1String, arraySet);
        } 
        return (Set<PathWithRequiredFlags>)arraySet;
      } 
      if ("exclude".equals(arraySet.getName()))
        return param1Set; 
      if (Log.isLoggable("BackupXmlParserLogging", 2)) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append("Invalid tag found in xml \"");
        stringBuilder1.append(arraySet.getName());
        stringBuilder1.append("\"; aborting operation.");
        Log.v("BackupXmlParserLogging", stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unrecognised tag in backup criteria xml (");
      stringBuilder.append(arraySet.getName());
      stringBuilder.append(")");
      throw new XmlPullParserException(stringBuilder.toString());
    }
    
    private String sharedDomainToPath(String param1String) throws IOException {
      String str = param1String.substring("shared/".length());
      StorageVolume[] arrayOfStorageVolume = getVolumeList();
      int i = Integer.parseInt(str);
      return (i < this.mVolumes.length) ? arrayOfStorageVolume[i].getPathFile().getCanonicalPath() : null;
    }
    
    private void validateInnerTagContents(XmlPullParser param1XmlPullParser) throws XmlPullParserException {
      if (param1XmlPullParser == null)
        return; 
      String str = param1XmlPullParser.getName();
      byte b = -1;
      int i = str.hashCode();
      if (i != -1321148966) {
        if (i == 1942574248 && str.equals("include"))
          b = 0; 
      } else if (str.equals("exclude")) {
        b = 1;
      } 
      if (b != 0) {
        if (b == 1) {
          if (param1XmlPullParser.getAttributeCount() > 2)
            throw new XmlPullParserException("At most 2 tag attributes allowed for \"exclude\" tag (\"domain\" & \"path\"."); 
        } else {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("A valid tag is one of \"<include/>\" or \"<exclude/>. You provided \"");
          stringBuilder.append(param1XmlPullParser.getName());
          stringBuilder.append("\"");
          throw new XmlPullParserException(stringBuilder.toString());
        } 
      } else if (param1XmlPullParser.getAttributeCount() > 3) {
        throw new XmlPullParserException("At most 3 tag attributes allowed for \"include\" tag (\"domain\" & \"path\" & optional \"requiredFlags\").");
      } 
    }
    
    boolean isFullBackupContentEnabled() {
      if (this.mFullBackupContent < 0) {
        if (Log.isLoggable("BackupXmlParserLogging", 2))
          Log.v("BackupXmlParserLogging", "android:fullBackupContent - \"false\""); 
        return false;
      } 
      return true;
    }
    
    public ArraySet<PathWithRequiredFlags> maybeParseAndGetCanonicalExcludePaths() throws IOException, XmlPullParserException {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mExcludes : Landroid/util/ArraySet;
      //   6: ifnonnull -> 13
      //   9: aload_0
      //   10: invokespecial maybeParseBackupSchemeLocked : ()V
      //   13: aload_0
      //   14: getfield mExcludes : Landroid/util/ArraySet;
      //   17: astore_1
      //   18: aload_0
      //   19: monitorexit
      //   20: aload_1
      //   21: areturn
      //   22: astore_1
      //   23: aload_0
      //   24: monitorexit
      //   25: aload_1
      //   26: athrow
      // Exception table:
      //   from	to	target	type
      //   2	13	22	finally
      //   13	18	22	finally
    }
    
    public Map<String, Set<PathWithRequiredFlags>> maybeParseAndGetCanonicalIncludePaths() throws IOException, XmlPullParserException {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield mIncludes : Ljava/util/Map;
      //   6: ifnonnull -> 13
      //   9: aload_0
      //   10: invokespecial maybeParseBackupSchemeLocked : ()V
      //   13: aload_0
      //   14: getfield mIncludes : Ljava/util/Map;
      //   17: astore_1
      //   18: aload_0
      //   19: monitorexit
      //   20: aload_1
      //   21: areturn
      //   22: astore_1
      //   23: aload_0
      //   24: monitorexit
      //   25: aload_1
      //   26: athrow
      // Exception table:
      //   from	to	target	type
      //   2	13	22	finally
      //   13	18	22	finally
    }
    
    public void parseBackupSchemeFromXmlLocked(XmlPullParser param1XmlPullParser, Set<PathWithRequiredFlags> param1Set, Map<String, Set<PathWithRequiredFlags>> param1Map) throws IOException, XmlPullParserException {
      int i;
      for (i = param1XmlPullParser.getEventType(); i != 2; i = param1XmlPullParser.next());
      if ("full-backup-content".equals(param1XmlPullParser.getName())) {
        if (Log.isLoggable("BackupXmlParserLogging", 2)) {
          Log.v("BackupXmlParserLogging", "\n");
          Log.v("BackupXmlParserLogging", "====================================================");
          Log.v("BackupXmlParserLogging", "Found valid fullBackupContent; parsing xml resource.");
          Log.v("BackupXmlParserLogging", "====================================================");
          Log.v("BackupXmlParserLogging", "");
        } 
        while (true) {
          XmlPullParser xmlPullParser = param1XmlPullParser;
          BackupScheme backupScheme = this;
          i = param1XmlPullParser.next();
          if (i != 1) {
            StringBuilder stringBuilder1;
            if (i != 2)
              continue; 
            validateInnerTagContents(param1XmlPullParser);
            String str = xmlPullParser.getAttributeValue(null, "domain");
            File file = backupScheme.getDirectoryForCriteriaDomain(str);
            if (file == null) {
              if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append("...parsing \"");
                stringBuilder1.append(param1XmlPullParser.getName());
                stringBuilder1.append("\": domain=\"");
                stringBuilder1.append(str);
                stringBuilder1.append("\" invalid; skipping");
                Log.v("BackupXmlParserLogging", stringBuilder1.toString());
              } 
              continue;
            } 
            file = stringBuilder1.extractCanonicalFile(file, xmlPullParser.getAttributeValue(null, "path"));
            if (file == null)
              continue; 
            i = 0;
            if ("include".equals(param1XmlPullParser.getName()))
              i = stringBuilder1.getRequiredFlagsFromString(xmlPullParser.getAttributeValue(null, "requireFlags")); 
            Set<PathWithRequiredFlags> set = stringBuilder1.parseCurrentTagForDomain(xmlPullParser, param1Set, param1Map, str);
            set.add(new PathWithRequiredFlags(file.getCanonicalPath(), i));
            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append("...parsed ");
              stringBuilder2.append(file.getCanonicalPath());
              stringBuilder2.append(" for domain \"");
              stringBuilder2.append(str);
              stringBuilder2.append("\", requiredFlags + \"");
              stringBuilder2.append(i);
              stringBuilder2.append("\"");
              Log.v("BackupXmlParserLogging", stringBuilder2.toString());
            } 
            if ("database".equals(str) && !file.isDirectory()) {
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append(file.getCanonicalPath());
              stringBuilder2.append("-journal");
              String str2 = stringBuilder2.toString();
              set.add(new PathWithRequiredFlags(str2, i));
              if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append("...automatically generated ");
                stringBuilder2.append(str2);
                stringBuilder2.append(". Ignore if nonexistent.");
                Log.v("BackupXmlParserLogging", stringBuilder2.toString());
              } 
              stringBuilder2 = new StringBuilder();
              stringBuilder2.append(file.getCanonicalPath());
              stringBuilder2.append("-wal");
              String str1 = stringBuilder2.toString();
              set.add(new PathWithRequiredFlags(str1, i));
              if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("...automatically generated ");
                stringBuilder3.append(str1);
                stringBuilder3.append(". Ignore if nonexistent.");
                Log.v("BackupXmlParserLogging", stringBuilder3.toString());
              } 
            } 
            if ("sharedpref".equals(str) && !file.isDirectory() && !file.getCanonicalPath().endsWith(".xml")) {
              StringBuilder stringBuilder2 = new StringBuilder();
              stringBuilder2.append(file.getCanonicalPath());
              stringBuilder2.append(".xml");
              String str1 = stringBuilder2.toString();
              set.add(new PathWithRequiredFlags(str1, i));
              if (Log.isLoggable("BackupXmlParserLogging", 2)) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("...automatically generated ");
                stringBuilder3.append(str1);
                stringBuilder3.append(". Ignore if nonexistent.");
                Log.v("BackupXmlParserLogging", stringBuilder3.toString());
              } 
            } 
            continue;
          } 
          if (Log.isLoggable("BackupXmlParserLogging", 2)) {
            Log.v("BackupXmlParserLogging", "\n");
            Log.v("BackupXmlParserLogging", "Xml resource parsing complete.");
            Log.v("BackupXmlParserLogging", "Final tally.");
            Log.v("BackupXmlParserLogging", "Includes:");
            if (param1Map.isEmpty()) {
              Log.v("BackupXmlParserLogging", "  ...nothing specified (This means the entirety of app data minus excludes)");
            } else {
              for (Map.Entry<String, Set<PathWithRequiredFlags>> entry : param1Map.entrySet()) {
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append("  domain=");
                stringBuilder1.append((String)entry.getKey());
                Log.v("BackupXmlParserLogging", stringBuilder1.toString());
                for (PathWithRequiredFlags pathWithRequiredFlags : entry.getValue()) {
                  StringBuilder stringBuilder2 = new StringBuilder();
                  stringBuilder2.append(" path: ");
                  stringBuilder2.append(pathWithRequiredFlags.getPath());
                  stringBuilder2.append(" requiredFlags: ");
                  stringBuilder2.append(pathWithRequiredFlags.getRequiredFlags());
                  Log.v("BackupXmlParserLogging", stringBuilder2.toString());
                } 
              } 
            } 
            Log.v("BackupXmlParserLogging", "Excludes:");
            if (param1Set.isEmpty()) {
              Log.v("BackupXmlParserLogging", "  ...nothing to exclude.");
            } else {
              for (PathWithRequiredFlags pathWithRequiredFlags : param1Set) {
                StringBuilder stringBuilder1 = new StringBuilder();
                stringBuilder1.append(" path: ");
                stringBuilder1.append(pathWithRequiredFlags.getPath());
                stringBuilder1.append(" requiredFlags: ");
                stringBuilder1.append(pathWithRequiredFlags.getRequiredFlags());
                Log.v("BackupXmlParserLogging", stringBuilder1.toString());
              } 
            } 
            Log.v("BackupXmlParserLogging", "  ");
            Log.v("BackupXmlParserLogging", "====================================================");
            Log.v("BackupXmlParserLogging", "\n");
          } 
          return;
        } 
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Xml file didn't start with correct tag (<full-backup-content>). Found \"");
      stringBuilder.append(param1XmlPullParser.getName());
      stringBuilder.append("\"");
      throw new XmlPullParserException(stringBuilder.toString());
    }
    
    String tokenToDirectoryPath(String param1String) {
      try {
        if (param1String.equals("f"))
          return this.FILES_DIR.getCanonicalPath(); 
        if (param1String.equals("db"))
          return this.DATABASE_DIR.getCanonicalPath(); 
        if (param1String.equals("r"))
          return this.ROOT_DIR.getCanonicalPath(); 
        if (param1String.equals("sp"))
          return this.SHAREDPREF_DIR.getCanonicalPath(); 
        if (param1String.equals("c"))
          return this.CACHE_DIR.getCanonicalPath(); 
        if (param1String.equals("nb"))
          return this.NOBACKUP_DIR.getCanonicalPath(); 
        if (param1String.equals("d_f"))
          return this.DEVICE_FILES_DIR.getCanonicalPath(); 
        if (param1String.equals("d_db"))
          return this.DEVICE_DATABASE_DIR.getCanonicalPath(); 
        if (param1String.equals("d_r"))
          return this.DEVICE_ROOT_DIR.getCanonicalPath(); 
        if (param1String.equals("d_sp"))
          return this.DEVICE_SHAREDPREF_DIR.getCanonicalPath(); 
        if (param1String.equals("d_c"))
          return this.DEVICE_CACHE_DIR.getCanonicalPath(); 
        if (param1String.equals("d_nb"))
          return this.DEVICE_NOBACKUP_DIR.getCanonicalPath(); 
        if (param1String.equals("ef"))
          return (this.EXTERNAL_DIR != null) ? this.EXTERNAL_DIR.getCanonicalPath() : null; 
        if (param1String.startsWith("shared/"))
          return sharedDomainToPath(param1String); 
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("Unrecognized domain ");
        stringBuilder.append(param1String);
        Log.i("FullBackup", stringBuilder.toString());
        return null;
      } catch (Exception exception) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Error reading directory for domain: ");
        stringBuilder.append(param1String);
        Log.i("FullBackup", stringBuilder.toString());
        return null;
      } 
    }
    
    public static class PathWithRequiredFlags {
      private final String mPath;
      
      private final int mRequiredFlags;
      
      public PathWithRequiredFlags(String param2String, int param2Int) {
        this.mPath = param2String;
        this.mRequiredFlags = param2Int;
      }
      
      public String getPath() {
        return this.mPath;
      }
      
      public int getRequiredFlags() {
        return this.mRequiredFlags;
      }
    }
  }
  
  public static class PathWithRequiredFlags {
    private final String mPath;
    
    private final int mRequiredFlags;
    
    public PathWithRequiredFlags(String param1String, int param1Int) {
      this.mPath = param1String;
      this.mRequiredFlags = param1Int;
    }
    
    public String getPath() {
      return this.mPath;
    }
    
    public int getRequiredFlags() {
      return this.mRequiredFlags;
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/FullBackup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
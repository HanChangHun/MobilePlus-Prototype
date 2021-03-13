package android.app.backup;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.XmlResourceParser;
import android.os.Process;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class BackupScheme {
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
  
  BackupScheme(Context paramContext) {
    this.mFullBackupContent = (paramContext.getApplicationInfo()).fullBackupContent;
    this.mStorageManager = (StorageManager)paramContext.getSystemService("storage");
    this.mPackageManager = paramContext.getPackageManager();
    this.mPackageName = paramContext.getPackageName();
    Context context = paramContext.createCredentialProtectedStorageContext();
    this.FILES_DIR = context.getFilesDir();
    this.DATABASE_DIR = context.getDatabasePath("foo").getParentFile();
    this.ROOT_DIR = context.getDataDir();
    this.SHAREDPREF_DIR = context.getSharedPreferencesPath("foo").getParentFile();
    this.CACHE_DIR = context.getCacheDir();
    this.NOBACKUP_DIR = context.getNoBackupFilesDir();
    context = paramContext.createDeviceProtectedStorageContext();
    this.DEVICE_FILES_DIR = context.getFilesDir();
    this.DEVICE_DATABASE_DIR = context.getDatabasePath("foo").getParentFile();
    this.DEVICE_ROOT_DIR = context.getDataDir();
    this.DEVICE_SHAREDPREF_DIR = context.getSharedPreferencesPath("foo").getParentFile();
    this.DEVICE_CACHE_DIR = context.getCacheDir();
    this.DEVICE_NOBACKUP_DIR = context.getNoBackupFilesDir();
    if (Process.myUid() != 1000) {
      this.EXTERNAL_DIR = paramContext.getExternalFilesDir(null);
    } else {
      this.EXTERNAL_DIR = null;
    } 
  }
  
  private File extractCanonicalFile(File paramFile, String paramString) {
    String str = paramString;
    if (paramString == null)
      str = ""; 
    if (str.contains("..")) {
      if (Log.isLoggable("BackupXmlParserLogging", 2)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("...resolved \"");
        stringBuilder.append(paramFile.getPath());
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
        stringBuilder.append(paramFile.getPath());
        stringBuilder.append(" ");
        stringBuilder.append(str);
        stringBuilder.append("\", which contains the invalid \"//\" sequence; skipping.");
        Log.v("BackupXmlParserLogging", stringBuilder.toString());
      } 
      return null;
    } 
    return new File(paramFile, str);
  }
  
  private File getDirectoryForCriteriaDomain(String paramString) {
    return TextUtils.isEmpty(paramString) ? null : ("file".equals(paramString) ? this.FILES_DIR : ("database".equals(paramString) ? this.DATABASE_DIR : ("root".equals(paramString) ? this.ROOT_DIR : ("sharedpref".equals(paramString) ? this.SHAREDPREF_DIR : ("device_file".equals(paramString) ? this.DEVICE_FILES_DIR : ("device_database".equals(paramString) ? this.DEVICE_DATABASE_DIR : ("device_root".equals(paramString) ? this.DEVICE_ROOT_DIR : ("device_sharedpref".equals(paramString) ? this.DEVICE_SHAREDPREF_DIR : ("external".equals(paramString) ? this.EXTERNAL_DIR : null)))))))));
  }
  
  private int getRequiredFlagsFromString(String paramString) {
    int i = 0;
    if (paramString == null || paramString.length() == 0)
      return 0; 
    String[] arrayOfString = paramString.split("\\|");
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
  
  private String getTokenForXmlDomain(String paramString) {
    return "root".equals(paramString) ? "r" : ("file".equals(paramString) ? "f" : ("database".equals(paramString) ? "db" : ("sharedpref".equals(paramString) ? "sp" : ("device_root".equals(paramString) ? "d_r" : ("device_file".equals(paramString) ? "d_f" : ("device_database".equals(paramString) ? "d_db" : ("device_sharedpref".equals(paramString) ? "d_sp" : ("external".equals(paramString) ? "ef" : null))))))));
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
  
  private Set<PathWithRequiredFlags> parseCurrentTagForDomain(XmlPullParser paramXmlPullParser, Set<PathWithRequiredFlags> paramSet, Map<String, Set<PathWithRequiredFlags>> paramMap, String paramString) throws XmlPullParserException {
    ArraySet arraySet;
    if ("include".equals(paramXmlPullParser.getName())) {
      paramString = getTokenForXmlDomain(paramString);
      paramSet = paramMap.get(paramString);
      Set<PathWithRequiredFlags> set = paramSet;
      if (paramSet == null) {
        arraySet = new ArraySet();
        paramMap.put(paramString, arraySet);
      } 
      return (Set<PathWithRequiredFlags>)arraySet;
    } 
    if ("exclude".equals(arraySet.getName()))
      return paramSet; 
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
  
  private String sharedDomainToPath(String paramString) throws IOException {
    String str = paramString.substring("shared/".length());
    StorageVolume[] arrayOfStorageVolume = getVolumeList();
    int i = Integer.parseInt(str);
    return (i < this.mVolumes.length) ? arrayOfStorageVolume[i].getPathFile().getCanonicalPath() : null;
  }
  
  private void validateInnerTagContents(XmlPullParser paramXmlPullParser) throws XmlPullParserException {
    if (paramXmlPullParser == null)
      return; 
    String str = paramXmlPullParser.getName();
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
        if (paramXmlPullParser.getAttributeCount() > 2)
          throw new XmlPullParserException("At most 2 tag attributes allowed for \"exclude\" tag (\"domain\" & \"path\"."); 
      } else {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("A valid tag is one of \"<include/>\" or \"<exclude/>. You provided \"");
        stringBuilder.append(paramXmlPullParser.getName());
        stringBuilder.append("\"");
        throw new XmlPullParserException(stringBuilder.toString());
      } 
    } else if (paramXmlPullParser.getAttributeCount() > 3) {
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
  
  public void parseBackupSchemeFromXmlLocked(XmlPullParser paramXmlPullParser, Set<PathWithRequiredFlags> paramSet, Map<String, Set<PathWithRequiredFlags>> paramMap) throws IOException, XmlPullParserException {
    int i;
    for (i = paramXmlPullParser.getEventType(); i != 2; i = paramXmlPullParser.next());
    if ("full-backup-content".equals(paramXmlPullParser.getName())) {
      if (Log.isLoggable("BackupXmlParserLogging", 2)) {
        Log.v("BackupXmlParserLogging", "\n");
        Log.v("BackupXmlParserLogging", "====================================================");
        Log.v("BackupXmlParserLogging", "Found valid fullBackupContent; parsing xml resource.");
        Log.v("BackupXmlParserLogging", "====================================================");
        Log.v("BackupXmlParserLogging", "");
      } 
      while (true) {
        XmlPullParser xmlPullParser = paramXmlPullParser;
        BackupScheme backupScheme = this;
        i = paramXmlPullParser.next();
        if (i != 1) {
          StringBuilder stringBuilder1;
          if (i != 2)
            continue; 
          validateInnerTagContents(paramXmlPullParser);
          String str = xmlPullParser.getAttributeValue(null, "domain");
          File file = backupScheme.getDirectoryForCriteriaDomain(str);
          if (file == null) {
            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
              stringBuilder1 = new StringBuilder();
              stringBuilder1.append("...parsing \"");
              stringBuilder1.append(paramXmlPullParser.getName());
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
          if ("include".equals(paramXmlPullParser.getName()))
            i = stringBuilder1.getRequiredFlagsFromString(xmlPullParser.getAttributeValue(null, "requireFlags")); 
          Set<PathWithRequiredFlags> set = stringBuilder1.parseCurrentTagForDomain(xmlPullParser, paramSet, paramMap, str);
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
          if (paramMap.isEmpty()) {
            Log.v("BackupXmlParserLogging", "  ...nothing specified (This means the entirety of app data minus excludes)");
          } else {
            for (Map.Entry<String, Set<PathWithRequiredFlags>> entry : paramMap.entrySet()) {
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
          if (paramSet.isEmpty()) {
            Log.v("BackupXmlParserLogging", "  ...nothing to exclude.");
          } else {
            for (PathWithRequiredFlags pathWithRequiredFlags : paramSet) {
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
    stringBuilder.append(paramXmlPullParser.getName());
    stringBuilder.append("\"");
    throw new XmlPullParserException(stringBuilder.toString());
  }
  
  String tokenToDirectoryPath(String paramString) {
    try {
      if (paramString.equals("f"))
        return this.FILES_DIR.getCanonicalPath(); 
      if (paramString.equals("db"))
        return this.DATABASE_DIR.getCanonicalPath(); 
      if (paramString.equals("r"))
        return this.ROOT_DIR.getCanonicalPath(); 
      if (paramString.equals("sp"))
        return this.SHAREDPREF_DIR.getCanonicalPath(); 
      if (paramString.equals("c"))
        return this.CACHE_DIR.getCanonicalPath(); 
      if (paramString.equals("nb"))
        return this.NOBACKUP_DIR.getCanonicalPath(); 
      if (paramString.equals("d_f"))
        return this.DEVICE_FILES_DIR.getCanonicalPath(); 
      if (paramString.equals("d_db"))
        return this.DEVICE_DATABASE_DIR.getCanonicalPath(); 
      if (paramString.equals("d_r"))
        return this.DEVICE_ROOT_DIR.getCanonicalPath(); 
      if (paramString.equals("d_sp"))
        return this.DEVICE_SHAREDPREF_DIR.getCanonicalPath(); 
      if (paramString.equals("d_c"))
        return this.DEVICE_CACHE_DIR.getCanonicalPath(); 
      if (paramString.equals("d_nb"))
        return this.DEVICE_NOBACKUP_DIR.getCanonicalPath(); 
      if (paramString.equals("ef"))
        return (this.EXTERNAL_DIR != null) ? this.EXTERNAL_DIR.getCanonicalPath() : null; 
      if (paramString.startsWith("shared/"))
        return sharedDomainToPath(paramString); 
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Unrecognized domain ");
      stringBuilder.append(paramString);
      Log.i("FullBackup", stringBuilder.toString());
      return null;
    } catch (Exception exception) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Error reading directory for domain: ");
      stringBuilder.append(paramString);
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


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/FullBackup$BackupScheme.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
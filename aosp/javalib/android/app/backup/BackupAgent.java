package android.app.backup;

import android.app.IBackupAgent;
import android.app.QueuedWork;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import android.util.ArraySet;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import libcore.io.IoUtils;
import org.xmlpull.v1.XmlPullParserException;

public abstract class BackupAgent extends ContextWrapper {
  private static final boolean DEBUG = false;
  
  public static final int FLAG_CLIENT_SIDE_ENCRYPTION_ENABLED = 1;
  
  public static final int FLAG_DEVICE_TO_DEVICE_TRANSFER = 2;
  
  public static final int FLAG_FAKE_CLIENT_SIDE_ENCRYPTION_ENABLED = -2147483648;
  
  public static final int RESULT_ERROR = -1;
  
  public static final int RESULT_SUCCESS = 0;
  
  private static final String TAG = "BackupAgent";
  
  public static final int TYPE_DIRECTORY = 2;
  
  public static final int TYPE_EOF = 0;
  
  public static final int TYPE_FILE = 1;
  
  public static final int TYPE_SYMLINK = 3;
  
  private final IBinder mBinder = (new BackupServiceBinder()).asBinder();
  
  Handler mHandler = null;
  
  private UserHandle mUser;
  
  public BackupAgent() {
    super(null);
  }
  
  private void applyXmlFiltersAndDoFullBackupForDomain(String paramString1, String paramString2, Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> paramMap, ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> paramArraySet, ArraySet<String> paramArraySet1, FullBackupDataOutput paramFullBackupDataOutput) throws IOException {
    if (paramMap == null || paramMap.size() == 0) {
      fullBackupFileTree(paramString1, paramString2, FullBackup.getBackupScheme((Context)this).tokenToDirectoryPath(paramString2), paramArraySet, paramArraySet1, paramFullBackupDataOutput);
      return;
    } 
    if (paramMap.get(paramString2) != null)
      for (FullBackup.BackupScheme.PathWithRequiredFlags pathWithRequiredFlags : paramMap.get(paramString2)) {
        if (areIncludeRequiredTransportFlagsSatisfied(pathWithRequiredFlags.getRequiredFlags(), paramFullBackupDataOutput.getTransportFlags()))
          fullBackupFileTree(paramString1, paramString2, pathWithRequiredFlags.getPath(), paramArraySet, paramArraySet1, paramFullBackupDataOutput); 
      }  
  }
  
  private boolean areIncludeRequiredTransportFlagsSatisfied(int paramInt1, int paramInt2) {
    boolean bool;
    if ((paramInt2 & paramInt1) == paramInt1) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private int getBackupUserId() {
    int i;
    UserHandle userHandle = this.mUser;
    if (userHandle == null) {
      i = getUserId();
    } else {
      i = userHandle.getIdentifier();
    } 
    return i;
  }
  
  private boolean isFileEligibleForRestore(File paramFile) throws IOException {
    FullBackup.BackupScheme backupScheme = FullBackup.getBackupScheme((Context)this);
    if (!backupScheme.isFullBackupContentEnabled()) {
      if (Log.isLoggable("BackupXmlParserLogging", 2)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRestoreFile \"");
        stringBuilder.append(paramFile.getCanonicalPath());
        stringBuilder.append("\" : fullBackupContent not enabled for ");
        stringBuilder.append(getPackageName());
        Log.v("BackupXmlParserLogging", stringBuilder.toString());
      } 
      return false;
    } 
    String str = paramFile.getCanonicalPath();
    try {
      StringBuilder stringBuilder;
      Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> map = backupScheme.maybeParseAndGetCanonicalIncludePaths();
      ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> arraySet = backupScheme.maybeParseAndGetCanonicalExcludePaths();
      if (arraySet != null && BackupUtils.isFileSpecifiedInPathList(paramFile, (Collection<FullBackup.BackupScheme.PathWithRequiredFlags>)arraySet)) {
        if (Log.isLoggable("BackupXmlParserLogging", 2)) {
          stringBuilder = new StringBuilder();
          stringBuilder.append("onRestoreFile: \"");
          stringBuilder.append(str);
          stringBuilder.append("\": listed in excludes; skipping.");
          Log.v("BackupXmlParserLogging", stringBuilder.toString());
        } 
        return false;
      } 
      if (map != null && !map.isEmpty()) {
        boolean bool2;
        boolean bool1 = false;
        Iterator<Set> iterator = map.values().iterator();
        while (true) {
          bool2 = bool1;
          if (iterator.hasNext()) {
            Set<FullBackup.BackupScheme.PathWithRequiredFlags> set = iterator.next();
            bool1 |= BackupUtils.isFileSpecifiedInPathList((File)stringBuilder, set);
            if (bool1) {
              bool2 = bool1;
              break;
            } 
            continue;
          } 
          break;
        } 
        if (!bool2) {
          if (Log.isLoggable("BackupXmlParserLogging", 2)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("onRestoreFile: Trying to restore \"");
            stringBuilder.append(str);
            stringBuilder.append("\" but it isn't specified in the included files; skipping.");
            Log.v("BackupXmlParserLogging", stringBuilder.toString());
          } 
          return false;
        } 
      } 
      return true;
    } catch (XmlPullParserException xmlPullParserException) {
      if (Log.isLoggable("BackupXmlParserLogging", 2)) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRestoreFile \"");
        stringBuilder.append(str);
        stringBuilder.append("\" : Exception trying to parse fullBackupContent xml file! Aborting onRestoreFile.");
        Log.v("BackupXmlParserLogging", stringBuilder.toString(), (Throwable)xmlPullParserException);
      } 
      return false;
    } 
  }
  
  private boolean manifestExcludesContainFilePath(ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> paramArraySet, String paramString) {
    Iterator<FullBackup.BackupScheme.PathWithRequiredFlags> iterator = paramArraySet.iterator();
    while (iterator.hasNext()) {
      String str = ((FullBackup.BackupScheme.PathWithRequiredFlags)iterator.next()).getPath();
      if (str != null && str.equals(paramString))
        return true; 
    } 
    return false;
  }
  
  private void waitForSharedPrefs() {
    Handler handler = getHandler();
    SharedPrefsSynchronizer sharedPrefsSynchronizer = new SharedPrefsSynchronizer();
    handler.postAtFrontOfQueue(sharedPrefsSynchronizer);
    try {
      sharedPrefsSynchronizer.mLatch.await();
    } catch (InterruptedException interruptedException) {}
  }
  
  public void attach(Context paramContext) {
    attachBaseContext(paramContext);
  }
  
  public final void fullBackupFile(File paramFile, FullBackupDataOutput paramFullBackupDataOutput) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   4: astore_3
    //   5: aload_0
    //   6: invokevirtual createCredentialProtectedStorageContext : ()Landroid/content/Context;
    //   9: astore #4
    //   11: aload #4
    //   13: invokevirtual getDataDir : ()Ljava/io/File;
    //   16: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   19: astore #5
    //   21: aload #4
    //   23: invokevirtual getFilesDir : ()Ljava/io/File;
    //   26: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   29: astore #6
    //   31: aload #4
    //   33: invokevirtual getNoBackupFilesDir : ()Ljava/io/File;
    //   36: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   39: astore #7
    //   41: aload #4
    //   43: ldc_w 'foo'
    //   46: invokevirtual getDatabasePath : (Ljava/lang/String;)Ljava/io/File;
    //   49: invokevirtual getParentFile : ()Ljava/io/File;
    //   52: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   55: astore #8
    //   57: aload #4
    //   59: ldc_w 'foo'
    //   62: invokevirtual getSharedPreferencesPath : (Ljava/lang/String;)Ljava/io/File;
    //   65: invokevirtual getParentFile : ()Ljava/io/File;
    //   68: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   71: astore #9
    //   73: aload #4
    //   75: invokevirtual getCacheDir : ()Ljava/io/File;
    //   78: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   81: astore #10
    //   83: aload #4
    //   85: invokevirtual getCodeCacheDir : ()Ljava/io/File;
    //   88: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   91: astore #11
    //   93: aload_0
    //   94: invokevirtual createDeviceProtectedStorageContext : ()Landroid/content/Context;
    //   97: astore #4
    //   99: aload #4
    //   101: invokevirtual getDataDir : ()Ljava/io/File;
    //   104: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   107: astore #12
    //   109: aload #4
    //   111: invokevirtual getFilesDir : ()Ljava/io/File;
    //   114: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   117: astore #13
    //   119: aload #4
    //   121: invokevirtual getNoBackupFilesDir : ()Ljava/io/File;
    //   124: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   127: astore #14
    //   129: aload #4
    //   131: ldc_w 'foo'
    //   134: invokevirtual getDatabasePath : (Ljava/lang/String;)Ljava/io/File;
    //   137: invokevirtual getParentFile : ()Ljava/io/File;
    //   140: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   143: astore #15
    //   145: aload #4
    //   147: ldc_w 'foo'
    //   150: invokevirtual getSharedPreferencesPath : (Ljava/lang/String;)Ljava/io/File;
    //   153: invokevirtual getParentFile : ()Ljava/io/File;
    //   156: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   159: astore #16
    //   161: aload #4
    //   163: invokevirtual getCacheDir : ()Ljava/io/File;
    //   166: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   169: astore #17
    //   171: aload #4
    //   173: invokevirtual getCodeCacheDir : ()Ljava/io/File;
    //   176: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   179: astore #18
    //   181: aload_3
    //   182: getfield nativeLibraryDir : Ljava/lang/String;
    //   185: ifnonnull -> 194
    //   188: aconst_null
    //   189: astore #4
    //   191: goto -> 215
    //   194: new java/io/File
    //   197: astore #4
    //   199: aload #4
    //   201: aload_3
    //   202: getfield nativeLibraryDir : Ljava/lang/String;
    //   205: invokespecial <init> : (Ljava/lang/String;)V
    //   208: aload #4
    //   210: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   213: astore #4
    //   215: invokestatic myUid : ()I
    //   218: istore #19
    //   220: iload #19
    //   222: sipush #1000
    //   225: if_icmpeq -> 250
    //   228: aload_0
    //   229: aconst_null
    //   230: invokevirtual getExternalFilesDir : (Ljava/lang/String;)Ljava/io/File;
    //   233: astore_3
    //   234: aload_3
    //   235: ifnull -> 250
    //   238: aload_3
    //   239: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   242: astore_3
    //   243: goto -> 252
    //   246: astore_1
    //   247: goto -> 599
    //   250: aconst_null
    //   251: astore_3
    //   252: aload_1
    //   253: invokevirtual getCanonicalPath : ()Ljava/lang/String;
    //   256: astore #20
    //   258: aload #20
    //   260: aload #10
    //   262: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   265: ifne -> 580
    //   268: aload #20
    //   270: aload #11
    //   272: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   275: ifne -> 577
    //   278: aload #20
    //   280: aload #7
    //   282: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   285: ifne -> 574
    //   288: aload #20
    //   290: aload #17
    //   292: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   295: ifne -> 571
    //   298: aload #20
    //   300: aload #18
    //   302: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   305: ifne -> 568
    //   308: aload #20
    //   310: aload #14
    //   312: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   315: ifne -> 565
    //   318: aload #20
    //   320: aload #4
    //   322: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   325: ifeq -> 331
    //   328: goto -> 580
    //   331: aload #20
    //   333: aload #8
    //   335: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   338: ifeq -> 351
    //   341: ldc_w 'db'
    //   344: astore_1
    //   345: aload #8
    //   347: astore_3
    //   348: goto -> 508
    //   351: aload #20
    //   353: aload #9
    //   355: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   358: ifeq -> 371
    //   361: ldc_w 'sp'
    //   364: astore_1
    //   365: aload #9
    //   367: astore_3
    //   368: goto -> 508
    //   371: aload #20
    //   373: aload #6
    //   375: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   378: ifeq -> 391
    //   381: ldc_w 'f'
    //   384: astore_1
    //   385: aload #6
    //   387: astore_3
    //   388: goto -> 508
    //   391: aload #20
    //   393: aload #5
    //   395: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   398: ifeq -> 411
    //   401: ldc_w 'r'
    //   404: astore_1
    //   405: aload #5
    //   407: astore_3
    //   408: goto -> 508
    //   411: aload #20
    //   413: aload #15
    //   415: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   418: ifeq -> 431
    //   421: ldc_w 'd_db'
    //   424: astore_1
    //   425: aload #15
    //   427: astore_3
    //   428: goto -> 508
    //   431: aload #20
    //   433: aload #16
    //   435: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   438: ifeq -> 451
    //   441: ldc_w 'd_sp'
    //   444: astore_1
    //   445: aload #16
    //   447: astore_3
    //   448: goto -> 508
    //   451: aload #20
    //   453: aload #13
    //   455: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   458: ifeq -> 471
    //   461: ldc_w 'd_f'
    //   464: astore_1
    //   465: aload #13
    //   467: astore_3
    //   468: goto -> 508
    //   471: aload #20
    //   473: aload #12
    //   475: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   478: ifeq -> 491
    //   481: ldc_w 'd_r'
    //   484: astore_1
    //   485: aload #12
    //   487: astore_3
    //   488: goto -> 508
    //   491: aload_3
    //   492: ifnull -> 523
    //   495: aload #20
    //   497: aload_3
    //   498: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   501: ifeq -> 523
    //   504: ldc_w 'ef'
    //   507: astore_1
    //   508: aload_0
    //   509: invokevirtual getPackageName : ()Ljava/lang/String;
    //   512: aload_1
    //   513: aconst_null
    //   514: aload_3
    //   515: aload #20
    //   517: aload_2
    //   518: invokestatic backupToTar : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/app/backup/FullBackupDataOutput;)I
    //   521: pop
    //   522: return
    //   523: new java/lang/StringBuilder
    //   526: dup
    //   527: invokespecial <init> : ()V
    //   530: astore_1
    //   531: aload_1
    //   532: ldc_w 'File '
    //   535: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   538: pop
    //   539: aload_1
    //   540: aload #20
    //   542: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: pop
    //   546: aload_1
    //   547: ldc_w ' is in an unsupported location; skipping'
    //   550: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   553: pop
    //   554: ldc 'BackupAgent'
    //   556: aload_1
    //   557: invokevirtual toString : ()Ljava/lang/String;
    //   560: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   563: pop
    //   564: return
    //   565: goto -> 580
    //   568: goto -> 580
    //   571: goto -> 580
    //   574: goto -> 580
    //   577: goto -> 580
    //   580: ldc 'BackupAgent'
    //   582: ldc_w 'lib, cache, code_cache, and no_backup files are not backed up'
    //   585: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   588: pop
    //   589: return
    //   590: astore_1
    //   591: goto -> 599
    //   594: astore_1
    //   595: goto -> 599
    //   598: astore_1
    //   599: ldc 'BackupAgent'
    //   601: ldc_w 'Unable to obtain canonical paths'
    //   604: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   607: pop
    //   608: return
    // Exception table:
    //   from	to	target	type
    //   5	181	598	java/io/IOException
    //   181	188	594	java/io/IOException
    //   194	215	594	java/io/IOException
    //   215	220	594	java/io/IOException
    //   228	234	246	java/io/IOException
    //   238	243	246	java/io/IOException
    //   252	258	590	java/io/IOException
  }
  
  protected final void fullBackupFileTree(String paramString1, String paramString2, String paramString3, ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> paramArraySet, ArraySet<String> paramArraySet1, FullBackupDataOutput paramFullBackupDataOutput) {
    String str = FullBackup.getBackupScheme((Context)this).tokenToDirectoryPath(paramString2);
    if (str == null)
      return; 
    File file = new File(paramString3);
    if (file.exists()) {
      LinkedList<File> linkedList = new LinkedList();
      linkedList.add(file);
      while (true) {
        ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> arraySet = paramArraySet;
        if (linkedList.size() > 0) {
          File file1 = linkedList.remove(0);
          try {
            StructStat structStat = Os.lstat(file1.getPath());
            if (!OsConstants.S_ISREG(structStat.st_mode) && !OsConstants.S_ISDIR(structStat.st_mode))
              continue; 
            String str1 = file1.getCanonicalPath();
            if ((arraySet != null && manifestExcludesContainFilePath(arraySet, str1)) || (paramArraySet1 != null && paramArraySet1.contains(str1)))
              continue; 
            if (OsConstants.S_ISDIR(structStat.st_mode)) {
              File[] arrayOfFile = file1.listFiles();
              if (arrayOfFile != null) {
                int i = arrayOfFile.length;
                for (byte b = 0; b < i; b++)
                  linkedList.add(0, arrayOfFile[b]); 
              } 
            } 
            FullBackup.backupToTar(paramString1, paramString2, null, str, str1, paramFullBackupDataOutput);
          } catch (IOException iOException) {
            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Error canonicalizing path of ");
              stringBuilder.append(file1);
              Log.v("BackupXmlParserLogging", stringBuilder.toString());
            } 
          } catch (ErrnoException errnoException) {
            if (Log.isLoggable("BackupXmlParserLogging", 2)) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Error scanning file ");
              stringBuilder.append(file1);
              stringBuilder.append(" : ");
              stringBuilder.append(errnoException);
              Log.v("BackupXmlParserLogging", stringBuilder.toString());
            } 
          } 
          continue;
        } 
        break;
      } 
    } 
  }
  
  Handler getHandler() {
    if (this.mHandler == null)
      this.mHandler = new Handler(Looper.getMainLooper()); 
    return this.mHandler;
  }
  
  public abstract void onBackup(ParcelFileDescriptor paramParcelFileDescriptor1, BackupDataOutput paramBackupDataOutput, ParcelFileDescriptor paramParcelFileDescriptor2) throws IOException;
  
  public final IBinder onBind() {
    return this.mBinder;
  }
  
  public void onCreate() {}
  
  public void onCreate(UserHandle paramUserHandle) {
    onCreate();
    this.mUser = paramUserHandle;
  }
  
  public void onDestroy() {}
  
  public void onFullBackup(FullBackupDataOutput paramFullBackupDataOutput) throws IOException {
    FullBackup.BackupScheme backupScheme = FullBackup.getBackupScheme((Context)this);
    if (!backupScheme.isFullBackupContentEnabled())
      return; 
    try {
      Map<String, Set<FullBackup.BackupScheme.PathWithRequiredFlags>> map = backupScheme.maybeParseAndGetCanonicalIncludePaths();
      ArraySet<FullBackup.BackupScheme.PathWithRequiredFlags> arraySet = backupScheme.maybeParseAndGetCanonicalExcludePaths();
      String str1 = getPackageName();
      ApplicationInfo applicationInfo = getApplicationInfo();
      Context context1 = createCredentialProtectedStorageContext();
      String str3 = context1.getDataDir().getCanonicalPath();
      String str4 = context1.getFilesDir().getCanonicalPath();
      String str5 = context1.getNoBackupFilesDir().getCanonicalPath();
      String str6 = context1.getDatabasePath("foo").getParentFile().getCanonicalPath();
      String str7 = context1.getSharedPreferencesPath("foo").getParentFile().getCanonicalPath();
      String str8 = context1.getCacheDir().getCanonicalPath();
      String str9 = context1.getCodeCacheDir().getCanonicalPath();
      Context context2 = createDeviceProtectedStorageContext();
      String str11 = context2.getDataDir().getCanonicalPath();
      String str12 = context2.getFilesDir().getCanonicalPath();
      String str13 = context2.getNoBackupFilesDir().getCanonicalPath();
      String str14 = context2.getDatabasePath("foo").getParentFile().getCanonicalPath();
      String str2 = context2.getSharedPreferencesPath("foo").getParentFile().getCanonicalPath();
      String str15 = context2.getCacheDir().getCanonicalPath();
      String str10 = context2.getCodeCacheDir().getCanonicalPath();
      if (applicationInfo.nativeLibraryDir != null) {
        String str = (new File(applicationInfo.nativeLibraryDir)).getCanonicalPath();
      } else {
        applicationInfo = null;
      } 
      ArraySet<String> arraySet1 = new ArraySet();
      arraySet1.add(str4);
      arraySet1.add(str5);
      arraySet1.add(str6);
      arraySet1.add(str7);
      arraySet1.add(str8);
      arraySet1.add(str9);
      arraySet1.add(str12);
      arraySet1.add(str13);
      arraySet1.add(str14);
      arraySet1.add(str2);
      arraySet1.add(str15);
      arraySet1.add(str10);
      if (applicationInfo != null)
        arraySet1.add(applicationInfo); 
      applyXmlFiltersAndDoFullBackupForDomain(str1, "r", map, arraySet, arraySet1, paramFullBackupDataOutput);
      arraySet1.add(str3);
      applyXmlFiltersAndDoFullBackupForDomain(str1, "d_r", map, arraySet, arraySet1, paramFullBackupDataOutput);
      arraySet1.add(str11);
      arraySet1.remove(str4);
      applyXmlFiltersAndDoFullBackupForDomain(str1, "f", map, arraySet, arraySet1, paramFullBackupDataOutput);
      arraySet1.add(str4);
      arraySet1.remove(str12);
      applyXmlFiltersAndDoFullBackupForDomain(str1, "d_f", map, arraySet, arraySet1, paramFullBackupDataOutput);
      arraySet1.add(str12);
      arraySet1.remove(str6);
      applyXmlFiltersAndDoFullBackupForDomain(str1, "db", map, arraySet, arraySet1, paramFullBackupDataOutput);
      arraySet1.add(str6);
      arraySet1.remove(str14);
      applyXmlFiltersAndDoFullBackupForDomain(str1, "d_db", map, arraySet, arraySet1, paramFullBackupDataOutput);
      arraySet1.add(str14);
      arraySet1.remove(str7);
      applyXmlFiltersAndDoFullBackupForDomain(str1, "sp", map, arraySet, arraySet1, paramFullBackupDataOutput);
      arraySet1.add(str7);
      arraySet1.remove(str2);
      applyXmlFiltersAndDoFullBackupForDomain(str1, "d_sp", map, arraySet, arraySet1, paramFullBackupDataOutput);
      arraySet1.add(str2);
      if (Process.myUid() != 1000 && getExternalFilesDir(null) != null)
        applyXmlFiltersAndDoFullBackupForDomain(str1, "ef", map, arraySet, arraySet1, paramFullBackupDataOutput); 
      return;
    } catch (IOException|XmlPullParserException iOException) {
      if (Log.isLoggable("BackupXmlParserLogging", 2))
        Log.v("BackupXmlParserLogging", "Exception trying to parse fullBackupContent xml file! Aborting full backup.", iOException); 
      return;
    } 
  }
  
  public void onQuotaExceeded(long paramLong1, long paramLong2) {}
  
  public abstract void onRestore(BackupDataInput paramBackupDataInput, int paramInt, ParcelFileDescriptor paramParcelFileDescriptor) throws IOException;
  
  public void onRestore(BackupDataInput paramBackupDataInput, long paramLong, ParcelFileDescriptor paramParcelFileDescriptor) throws IOException {
    onRestore(paramBackupDataInput, (int)paramLong, paramParcelFileDescriptor);
  }
  
  public void onRestore(BackupDataInput paramBackupDataInput, long paramLong, ParcelFileDescriptor paramParcelFileDescriptor, Set<String> paramSet) throws IOException {
    onRestore(paramBackupDataInput, paramLong, paramParcelFileDescriptor);
  }
  
  protected void onRestoreFile(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong1, int paramInt, String paramString1, String paramString2, long paramLong2, long paramLong3) throws IOException {
    String str = FullBackup.getBackupScheme((Context)this).tokenToDirectoryPath(paramString1);
    if (paramString1.equals("ef"))
      paramLong2 = -1L; 
    if (str != null) {
      File file = new File(str, paramString2);
      paramString2 = file.getCanonicalPath();
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str);
      stringBuilder.append(File.separatorChar);
      if (paramString2.startsWith(stringBuilder.toString())) {
        onRestoreFile(paramParcelFileDescriptor, paramLong1, file, paramInt, paramLong2, paramLong3);
        return;
      } 
    } 
    FullBackup.restoreFile(paramParcelFileDescriptor, paramLong1, paramInt, paramLong2, paramLong3, null);
  }
  
  public void onRestoreFile(ParcelFileDescriptor paramParcelFileDescriptor, long paramLong1, File paramFile, int paramInt, long paramLong2, long paramLong3) throws IOException {
    if (!isFileEligibleForRestore(paramFile))
      paramFile = null; 
    FullBackup.restoreFile(paramParcelFileDescriptor, paramLong1, paramInt, paramLong2, paramLong3, paramFile);
  }
  
  public void onRestoreFinished() {}
  
  private class BackupServiceBinder extends IBackupAgent.Stub {
    private static final String TAG = "BackupServiceBinder";
    
    private BackupServiceBinder() {}
    
    private void doRestoreInternal(ParcelFileDescriptor param1ParcelFileDescriptor1, long param1Long, ParcelFileDescriptor param1ParcelFileDescriptor2, int param1Int, IBackupManager param1IBackupManager, List<String> param1List) throws RemoteException {
      // Byte code:
      //   0: invokestatic clearCallingIdentity : ()J
      //   3: lstore #8
      //   5: aload_0
      //   6: getfield this$0 : Landroid/app/backup/BackupAgent;
      //   9: invokestatic access$100 : (Landroid/app/backup/BackupAgent;)V
      //   12: new android/app/backup/BackupDataInput
      //   15: dup
      //   16: aload_1
      //   17: invokevirtual getFileDescriptor : ()Ljava/io/FileDescriptor;
      //   20: invokespecial <init> : (Ljava/io/FileDescriptor;)V
      //   23: astore #10
      //   25: aload_0
      //   26: getfield this$0 : Landroid/app/backup/BackupAgent;
      //   29: astore #11
      //   31: aload #7
      //   33: ifnull -> 60
      //   36: new java/util/HashSet
      //   39: astore #12
      //   41: aload #12
      //   43: aload #7
      //   45: invokespecial <init> : (Ljava/util/Collection;)V
      //   48: aload #12
      //   50: astore #7
      //   52: goto -> 65
      //   55: astore #7
      //   57: goto -> 295
      //   60: invokestatic emptySet : ()Ljava/util/Set;
      //   63: astore #7
      //   65: aload #11
      //   67: aload #10
      //   69: lload_2
      //   70: aload #4
      //   72: aload #7
      //   74: invokevirtual onRestore : (Landroid/app/backup/BackupDataInput;JLandroid/os/ParcelFileDescriptor;Ljava/util/Set;)V
      //   77: aload_0
      //   78: getfield this$0 : Landroid/app/backup/BackupAgent;
      //   81: invokevirtual reloadSharedPreferences : ()V
      //   84: lload #8
      //   86: invokestatic restoreCallingIdentity : (J)V
      //   89: aload #6
      //   91: aload_0
      //   92: getfield this$0 : Landroid/app/backup/BackupAgent;
      //   95: invokestatic access$200 : (Landroid/app/backup/BackupAgent;)I
      //   98: iload #5
      //   100: lconst_0
      //   101: invokeinterface opCompleteForUser : (IIJ)V
      //   106: goto -> 111
      //   109: astore #6
      //   111: invokestatic getCallingPid : ()I
      //   114: invokestatic myPid : ()I
      //   117: if_icmpeq -> 129
      //   120: aload_1
      //   121: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
      //   124: aload #4
      //   126: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
      //   129: return
      //   130: astore #7
      //   132: goto -> 156
      //   135: astore #7
      //   137: goto -> 142
      //   140: astore #7
      //   142: ldc ') threw'
      //   144: astore #12
      //   146: goto -> 220
      //   149: astore #7
      //   151: goto -> 295
      //   154: astore #7
      //   156: new java/lang/StringBuilder
      //   159: astore #12
      //   161: aload #12
      //   163: invokespecial <init> : ()V
      //   166: aload #12
      //   168: ldc 'onRestore ('
      //   170: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   173: pop
      //   174: aload #12
      //   176: aload_0
      //   177: getfield this$0 : Landroid/app/backup/BackupAgent;
      //   180: invokevirtual getClass : ()Ljava/lang/Class;
      //   183: invokevirtual getName : ()Ljava/lang/String;
      //   186: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   189: pop
      //   190: aload #12
      //   192: ldc ') threw'
      //   194: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   197: pop
      //   198: ldc 'BackupServiceBinder'
      //   200: aload #12
      //   202: invokevirtual toString : ()Ljava/lang/String;
      //   205: aload #7
      //   207: invokestatic d : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   210: pop
      //   211: aload #7
      //   213: athrow
      //   214: astore #7
      //   216: ldc ') threw'
      //   218: astore #12
      //   220: new java/lang/StringBuilder
      //   223: astore #10
      //   225: aload #10
      //   227: invokespecial <init> : ()V
      //   230: aload #10
      //   232: ldc 'onRestore ('
      //   234: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   237: pop
      //   238: aload #10
      //   240: aload_0
      //   241: getfield this$0 : Landroid/app/backup/BackupAgent;
      //   244: invokevirtual getClass : ()Ljava/lang/Class;
      //   247: invokevirtual getName : ()Ljava/lang/String;
      //   250: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   253: pop
      //   254: aload #10
      //   256: aload #12
      //   258: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   261: pop
      //   262: ldc 'BackupServiceBinder'
      //   264: aload #10
      //   266: invokevirtual toString : ()Ljava/lang/String;
      //   269: aload #7
      //   271: invokestatic d : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   274: pop
      //   275: new java/lang/RuntimeException
      //   278: astore #12
      //   280: aload #12
      //   282: aload #7
      //   284: invokespecial <init> : (Ljava/lang/Throwable;)V
      //   287: aload #12
      //   289: athrow
      //   290: astore #7
      //   292: goto -> 151
      //   295: aload_0
      //   296: getfield this$0 : Landroid/app/backup/BackupAgent;
      //   299: invokevirtual reloadSharedPreferences : ()V
      //   302: lload #8
      //   304: invokestatic restoreCallingIdentity : (J)V
      //   307: aload #6
      //   309: aload_0
      //   310: getfield this$0 : Landroid/app/backup/BackupAgent;
      //   313: invokestatic access$200 : (Landroid/app/backup/BackupAgent;)I
      //   316: iload #5
      //   318: lconst_0
      //   319: invokeinterface opCompleteForUser : (IIJ)V
      //   324: goto -> 329
      //   327: astore #6
      //   329: invokestatic getCallingPid : ()I
      //   332: invokestatic myPid : ()I
      //   335: if_icmpeq -> 347
      //   338: aload_1
      //   339: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
      //   342: aload #4
      //   344: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
      //   347: aload #7
      //   349: athrow
      // Exception table:
      //   from	to	target	type
      //   25	31	214	java/io/IOException
      //   25	31	154	java/lang/RuntimeException
      //   25	31	149	finally
      //   36	48	214	java/io/IOException
      //   36	48	154	java/lang/RuntimeException
      //   36	48	55	finally
      //   60	65	140	java/io/IOException
      //   60	65	154	java/lang/RuntimeException
      //   60	65	149	finally
      //   65	77	135	java/io/IOException
      //   65	77	130	java/lang/RuntimeException
      //   65	77	290	finally
      //   89	106	109	android/os/RemoteException
      //   156	211	290	finally
      //   211	214	290	finally
      //   220	290	290	finally
      //   307	324	327	android/os/RemoteException
    }
    
    public void doBackup(ParcelFileDescriptor param1ParcelFileDescriptor1, ParcelFileDescriptor param1ParcelFileDescriptor2, ParcelFileDescriptor param1ParcelFileDescriptor3, long param1Long, IBackupCallback param1IBackupCallback, int param1Int) throws RemoteException {
      long l = Binder.clearCallingIdentity();
      BackupDataOutput backupDataOutput = new BackupDataOutput(param1ParcelFileDescriptor2.getFileDescriptor(), param1Long, param1Int);
      try {
        BackupAgent backupAgent = BackupAgent.this;
        try {
          backupAgent.onBackup(param1ParcelFileDescriptor1, backupDataOutput, param1ParcelFileDescriptor3);
          BackupAgent.this.waitForSharedPrefs();
          Binder.restoreCallingIdentity(l);
          try {
            param1IBackupCallback.operationComplete(0L);
          } catch (RemoteException remoteException) {}
          if (Binder.getCallingPid() != Process.myPid()) {
            IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor1);
            IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor2);
            IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor3);
          } 
          return;
        } catch (IOException null) {
        
        } catch (RuntimeException null) {
        
        } finally {}
      } catch (IOException null) {
      
      } catch (RuntimeException null) {
        try {
          StringBuilder stringBuilder1 = new StringBuilder();
          this();
          stringBuilder1.append("onBackup (");
          stringBuilder1.append(BackupAgent.this.getClass().getName());
          stringBuilder1.append(") threw");
          Log.d("BackupServiceBinder", stringBuilder1.toString(), exception);
          throw exception;
        } finally {}
      } finally {}
      StringBuilder stringBuilder = new StringBuilder();
      this();
      Exception exception;
      stringBuilder.append("onBackup (");
      stringBuilder.append(BackupAgent.this.getClass().getName());
      stringBuilder.append(") threw");
      Log.d("BackupServiceBinder", stringBuilder.toString(), exception);
      RuntimeException runtimeException = new RuntimeException();
      this(exception);
      throw runtimeException;
    }
    
    public void doFullBackup(ParcelFileDescriptor param1ParcelFileDescriptor, long param1Long, int param1Int1, IBackupManager param1IBackupManager, int param1Int2) {
      long l = Binder.clearCallingIdentity();
      BackupAgent.this.waitForSharedPrefs();
      try {
        BackupAgent backupAgent = BackupAgent.this;
        FullBackupDataOutput fullBackupDataOutput = new FullBackupDataOutput();
        try {
          this(param1ParcelFileDescriptor, param1Long, param1Int2);
          backupAgent.onFullBackup(fullBackupDataOutput);
          BackupAgent.this.waitForSharedPrefs();
          try {
            FileOutputStream fileOutputStream = new FileOutputStream();
            this(param1ParcelFileDescriptor.getFileDescriptor());
            fileOutputStream.write(new byte[4]);
          } catch (IOException null) {
            Log.e("BackupServiceBinder", "Unable to finalize backup stream!");
          } 
          Binder.restoreCallingIdentity(l);
          try {
            param1IBackupManager.opCompleteForUser(BackupAgent.this.getBackupUserId(), param1Int1, 0L);
          } catch (RemoteException remoteException) {}
          if (Binder.getCallingPid() != Process.myPid())
            IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor); 
          return;
        } catch (IOException null) {
        
        } catch (RuntimeException null) {
        
        } finally {}
      } catch (IOException null) {
      
      } catch (RuntimeException null) {
        StringBuilder stringBuilder1 = new StringBuilder();
        this();
        stringBuilder1.append("onFullBackup (");
        stringBuilder1.append(BackupAgent.this.getClass().getName());
        stringBuilder1.append(") threw");
        Log.d("BackupServiceBinder", stringBuilder1.toString(), exception);
        throw exception;
      } finally {}
      StringBuilder stringBuilder = new StringBuilder();
      this();
      Exception exception;
      stringBuilder.append("onFullBackup (");
      stringBuilder.append(BackupAgent.this.getClass().getName());
      stringBuilder.append(") threw");
      Log.d("BackupServiceBinder", stringBuilder.toString(), exception);
      RuntimeException runtimeException = new RuntimeException();
      this(exception);
      throw runtimeException;
    }
    
    public void doMeasureFullBackup(long param1Long, int param1Int1, IBackupManager param1IBackupManager, int param1Int2) {
      Exception exception;
      long l = Binder.clearCallingIdentity();
      FullBackupDataOutput fullBackupDataOutput = new FullBackupDataOutput(param1Long, param1Int2);
      BackupAgent.this.waitForSharedPrefs();
      try {
        BackupAgent.this.onFullBackup(fullBackupDataOutput);
        Binder.restoreCallingIdentity(l);
        try {
          param1IBackupManager.opCompleteForUser(BackupAgent.this.getBackupUserId(), param1Int1, fullBackupDataOutput.getSize());
        } catch (RemoteException remoteException) {}
        return;
      } catch (IOException null) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("onFullBackup[M] (");
        stringBuilder.append(BackupAgent.this.getClass().getName());
        stringBuilder.append(") threw");
        Log.d("BackupServiceBinder", stringBuilder.toString(), exception);
        RuntimeException runtimeException = new RuntimeException();
        this(exception);
        throw runtimeException;
      } catch (RuntimeException runtimeException) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("onFullBackup[M] (");
        stringBuilder.append(BackupAgent.this.getClass().getName());
        stringBuilder.append(") threw");
        Log.d("BackupServiceBinder", stringBuilder.toString(), runtimeException);
        throw runtimeException;
      } finally {}
      Binder.restoreCallingIdentity(l);
      try {
        remoteException.opCompleteForUser(BackupAgent.this.getBackupUserId(), param1Int1, fullBackupDataOutput.getSize());
      } catch (RemoteException remoteException1) {}
      throw exception;
    }
    
    public void doQuotaExceeded(long param1Long1, long param1Long2, IBackupCallback param1IBackupCallback) {
      Exception exception;
      long l = Binder.clearCallingIdentity();
      try {
        BackupAgent.this.onQuotaExceeded(param1Long1, param1Long2);
        BackupAgent.this.waitForSharedPrefs();
        Binder.restoreCallingIdentity(l);
        try {
          param1IBackupCallback.operationComplete(0L);
        } catch (RemoteException remoteException) {}
        return;
      } catch (Exception exception1) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("onQuotaExceeded(");
        stringBuilder.append(BackupAgent.this.getClass().getName());
        stringBuilder.append(") threw");
        Log.d("BackupServiceBinder", stringBuilder.toString(), exception1);
        throw exception1;
      } finally {}
      BackupAgent.this.waitForSharedPrefs();
      Binder.restoreCallingIdentity(l);
      try {
        remoteException.operationComplete(-1L);
      } catch (RemoteException remoteException1) {}
      throw exception;
    }
    
    public void doRestore(ParcelFileDescriptor param1ParcelFileDescriptor1, long param1Long, ParcelFileDescriptor param1ParcelFileDescriptor2, int param1Int, IBackupManager param1IBackupManager) throws RemoteException {
      doRestoreInternal(param1ParcelFileDescriptor1, param1Long, param1ParcelFileDescriptor2, param1Int, param1IBackupManager, null);
    }
    
    public void doRestoreFile(ParcelFileDescriptor param1ParcelFileDescriptor, long param1Long1, int param1Int1, String param1String1, String param1String2, long param1Long2, long param1Long3, int param1Int2, IBackupManager param1IBackupManager) throws RemoteException {
      long l = Binder.clearCallingIdentity();
      try {
        BackupAgent.this.onRestoreFile(param1ParcelFileDescriptor, param1Long1, param1Int1, param1String1, param1String2, param1Long2, param1Long3);
        BackupAgent.this.waitForSharedPrefs();
        BackupAgent.this.reloadSharedPreferences();
        Binder.restoreCallingIdentity(l);
        try {
          param1IBackupManager.opCompleteForUser(BackupAgent.this.getBackupUserId(), param1Int2, 0L);
        } catch (RemoteException remoteException) {}
        if (Binder.getCallingPid() != Process.myPid())
          IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor); 
        return;
      } catch (IOException iOException) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("onRestoreFile (");
        stringBuilder.append(BackupAgent.this.getClass().getName());
        stringBuilder.append(") threw");
        Log.d("BackupServiceBinder", stringBuilder.toString(), iOException);
        RuntimeException runtimeException = new RuntimeException();
        this(iOException);
        throw runtimeException;
      } finally {}
      BackupAgent.this.waitForSharedPrefs();
      BackupAgent.this.reloadSharedPreferences();
      Binder.restoreCallingIdentity(l);
      try {
        param1IBackupManager.opCompleteForUser(BackupAgent.this.getBackupUserId(), param1Int2, 0L);
      } catch (RemoteException remoteException) {}
      if (Binder.getCallingPid() != Process.myPid())
        IoUtils.closeQuietly((AutoCloseable)param1ParcelFileDescriptor); 
      throw param1String1;
    }
    
    public void doRestoreFinished(int param1Int, IBackupManager param1IBackupManager) {
      Exception exception;
      long l = Binder.clearCallingIdentity();
      try {
        BackupAgent.this.onRestoreFinished();
        BackupAgent.this.waitForSharedPrefs();
        Binder.restoreCallingIdentity(l);
        try {
          param1IBackupManager.opCompleteForUser(BackupAgent.this.getBackupUserId(), param1Int, 0L);
        } catch (RemoteException remoteException) {}
        return;
      } catch (Exception null) {
        StringBuilder stringBuilder = new StringBuilder();
        this();
        stringBuilder.append("onRestoreFinished (");
        stringBuilder.append(BackupAgent.this.getClass().getName());
        stringBuilder.append(") threw");
        Log.d("BackupServiceBinder", stringBuilder.toString(), exception);
        throw exception;
      } finally {}
      BackupAgent.this.waitForSharedPrefs();
      Binder.restoreCallingIdentity(l);
      try {
        remoteException.opCompleteForUser(BackupAgent.this.getBackupUserId(), param1Int, 0L);
      } catch (RemoteException remoteException1) {}
      throw exception;
    }
    
    public void doRestoreWithExcludedKeys(ParcelFileDescriptor param1ParcelFileDescriptor1, long param1Long, ParcelFileDescriptor param1ParcelFileDescriptor2, int param1Int, IBackupManager param1IBackupManager, List<String> param1List) throws RemoteException {
      doRestoreInternal(param1ParcelFileDescriptor1, param1Long, param1ParcelFileDescriptor2, param1Int, param1IBackupManager, param1List);
    }
    
    public void fail(String param1String) {
      BackupAgent.this.getHandler().post(new BackupAgent.FailRunnable(param1String));
    }
  }
  
  static class FailRunnable implements Runnable {
    private String mMessage;
    
    FailRunnable(String param1String) {
      this.mMessage = param1String;
    }
    
    public void run() {
      throw new IllegalStateException(this.mMessage);
    }
  }
  
  class SharedPrefsSynchronizer implements Runnable {
    public final CountDownLatch mLatch = new CountDownLatch(1);
    
    public void run() {
      QueuedWork.waitToFinish();
      this.mLatch.countDown();
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/backup/BackupAgent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
package android.content.pm.dex;

import android.content.pm.PackageParser;
import android.util.ArrayMap;
import android.util.jar.StrictJarFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DexMetadataHelper {
  private static final String DEX_METADATA_FILE_EXTENSION = ".dm";
  
  public static String buildDexMetadataPathForApk(String paramString) {
    if (PackageParser.isApkPath(paramString)) {
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append(paramString.substring(0, paramString.length() - ".apk".length()));
      stringBuilder1.append(".dm");
      return stringBuilder1.toString();
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Corrupted package. Code path is not an apk ");
    stringBuilder.append(paramString);
    throw new IllegalStateException(stringBuilder.toString());
  }
  
  private static String buildDexMetadataPathForFile(File paramFile) {
    String str;
    if (PackageParser.isApkFile(paramFile)) {
      str = buildDexMetadataPathForApk(paramFile.getPath());
    } else {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(str.getPath());
      stringBuilder.append(".dm");
      str = stringBuilder.toString();
    } 
    return str;
  }
  
  public static Map<String, String> buildPackageApkToDexMetadataMap(List<String> paramList) {
    ArrayMap arrayMap = new ArrayMap();
    for (int i = paramList.size() - 1; i >= 0; i--) {
      String str1 = paramList.get(i);
      String str2 = buildDexMetadataPathForFile(new File(str1));
      if (Files.exists(Paths.get(str2, new String[0]), new java.nio.file.LinkOption[0]))
        arrayMap.put(str1, str2); 
    } 
    return (Map<String, String>)arrayMap;
  }
  
  public static File findDexMetadataForFile(File paramFile) {
    paramFile = new File(buildDexMetadataPathForFile(paramFile));
    if (!paramFile.exists())
      paramFile = null; 
    return paramFile;
  }
  
  private static Map<String, String> getPackageDexMetadata(PackageParser.PackageLite paramPackageLite) {
    return buildPackageApkToDexMetadataMap(paramPackageLite.getAllCodePaths());
  }
  
  public static long getPackageDexMetadataSize(PackageParser.PackageLite paramPackageLite) {
    long l = 0L;
    Iterator<String> iterator = getPackageDexMetadata(paramPackageLite).values().iterator();
    while (iterator.hasNext())
      l += (new File(iterator.next())).length(); 
    return l;
  }
  
  public static boolean isDexMetadataFile(File paramFile) {
    return isDexMetadataPath(paramFile.getName());
  }
  
  private static boolean isDexMetadataPath(String paramString) {
    return paramString.endsWith(".dm");
  }
  
  public static void validateDexMetadataFile(String paramString) throws PackageParser.PackageParserException {
    Exception exception;
    try {
      StrictJarFile strictJarFile = new StrictJarFile(paramString, false, false);
      try {
        strictJarFile.close();
      } catch (IOException iOException) {}
      return;
    } catch (IOException iOException1) {
      PackageParser.PackageParserException packageParserException = new PackageParser.PackageParserException();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      stringBuilder.append("Error opening ");
      stringBuilder.append((String)iOException);
      this(-117, stringBuilder.toString(), iOException1);
      throw packageParserException;
    } finally {}
    if (false)
      try {
        throw new NullPointerException();
      } catch (IOException iOException1) {} 
    throw exception;
  }
  
  public static void validateDexPaths(String[] paramArrayOfString) {
    ArrayList<String> arrayList1 = new ArrayList();
    byte b;
    for (b = 0; b < paramArrayOfString.length; b++) {
      if (PackageParser.isApkPath(paramArrayOfString[b]))
        arrayList1.add(paramArrayOfString[b]); 
    } 
    ArrayList<String> arrayList2 = new ArrayList();
    for (b = 0; b < paramArrayOfString.length; b++) {
      String str = paramArrayOfString[b];
      if (isDexMetadataPath(str)) {
        boolean bool2;
        boolean bool1 = false;
        int i = arrayList1.size() - 1;
        while (true) {
          bool2 = bool1;
          if (i >= 0) {
            if (str.equals(buildDexMetadataPathForFile(new File(arrayList1.get(i))))) {
              bool2 = true;
              break;
            } 
            i--;
            continue;
          } 
          break;
        } 
        if (!bool2)
          arrayList2.add(str); 
      } 
    } 
    if (arrayList2.isEmpty())
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unmatched .dm files: ");
    stringBuilder.append(arrayList2);
    throw new IllegalStateException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/content/pm/dex/DexMetadataHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
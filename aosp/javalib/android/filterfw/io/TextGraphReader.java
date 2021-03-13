package android.filterfw.io;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterFactory;
import android.filterfw.core.FilterGraph;
import android.filterfw.core.KeyValueMap;
import android.filterfw.core.ProtocolException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class TextGraphReader extends GraphReader {
  private KeyValueMap mBoundReferences;
  
  private ArrayList<Command> mCommands = new ArrayList<>();
  
  private Filter mCurrentFilter;
  
  private FilterGraph mCurrentGraph;
  
  private FilterFactory mFactory;
  
  private KeyValueMap mSettings;
  
  private void applySettings() throws GraphIOException {
    for (String str : this.mSettings.keySet()) {
      StringBuilder stringBuilder;
      Object object = this.mSettings.get(str);
      if (str.equals("autoBranch")) {
        expectSettingClass(str, object, String.class);
        if (object.equals("synced")) {
          this.mCurrentGraph.setAutoBranchMode(1);
          continue;
        } 
        if (object.equals("unsynced")) {
          this.mCurrentGraph.setAutoBranchMode(2);
          continue;
        } 
        if (object.equals("off")) {
          this.mCurrentGraph.setAutoBranchMode(0);
          continue;
        } 
        stringBuilder = new StringBuilder();
        stringBuilder.append("Unknown autobranch setting: ");
        stringBuilder.append(object);
        stringBuilder.append("!");
        throw new GraphIOException(stringBuilder.toString());
      } 
      if (stringBuilder.equals("discardUnconnectedOutputs")) {
        expectSettingClass((String)stringBuilder, object, Boolean.class);
        this.mCurrentGraph.setDiscardUnconnectedOutputs(((Boolean)object).booleanValue());
        continue;
      } 
      object = new StringBuilder();
      object.append("Unknown @setting '");
      object.append((String)stringBuilder);
      object.append("'!");
      throw new GraphIOException(object.toString());
    } 
  }
  
  private void bindExternal(String paramString) throws GraphIOException {
    if (this.mReferences.containsKey(paramString)) {
      Object object = this.mReferences.get(paramString);
      this.mBoundReferences.put(paramString, object);
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unknown external variable '");
    stringBuilder.append(paramString);
    stringBuilder.append("'! You must add a reference to this external in the host program using addReference(...)!");
    throw new GraphIOException(stringBuilder.toString());
  }
  
  private void checkReferences() throws GraphIOException {
    Iterator<String> iterator = this.mReferences.keySet().iterator();
    while (iterator.hasNext()) {
      String str = iterator.next();
      if (this.mBoundReferences.containsKey(str))
        continue; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Host program specifies reference to '");
      stringBuilder.append(str);
      stringBuilder.append("', which is not declared @external in graph file!");
      throw new GraphIOException(stringBuilder.toString());
    } 
  }
  
  private void executeCommands() throws GraphIOException {
    Iterator<Command> iterator = this.mCommands.iterator();
    while (iterator.hasNext())
      ((Command)iterator.next()).execute(this); 
  }
  
  private void expectSettingClass(String paramString, Object paramObject, Class<?> paramClass) throws GraphIOException {
    if (paramObject.getClass() == paramClass)
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Setting '");
    stringBuilder.append(paramString);
    stringBuilder.append("' must have a value of type ");
    stringBuilder.append(paramClass.getSimpleName());
    stringBuilder.append(", but found a value of type ");
    stringBuilder.append(paramObject.getClass().getSimpleName());
    stringBuilder.append("!");
    throw new GraphIOException(stringBuilder.toString());
  }
  
  private void parseString(String paramString) throws GraphIOException {
    Pattern pattern1 = Pattern.compile("@[a-zA-Z]+");
    Pattern pattern2 = Pattern.compile("\\}");
    Pattern pattern3 = Pattern.compile("\\{");
    Pattern pattern4 = Pattern.compile("(\\s+|//[^\\n]*\\n)+");
    Pattern pattern5 = Pattern.compile("[a-zA-Z\\.]+");
    Pattern pattern6 = Pattern.compile("[a-zA-Z\\./:]+");
    Pattern pattern7 = Pattern.compile("\\[[a-zA-Z0-9\\-_]+\\]");
    Pattern pattern8 = Pattern.compile("=>");
    String str1 = ";";
    Pattern pattern9 = Pattern.compile(";");
    Pattern pattern10 = Pattern.compile("[a-zA-Z0-9\\-_]+");
    byte b = 0;
    PatternScanner patternScanner = new PatternScanner(paramString, pattern4);
    String str2 = null;
    String str3 = null;
    pattern4 = null;
    paramString = null;
    while (!patternScanner.atEnd()) {
      String str4;
      KeyValueMap keyValueMap2;
      String str6;
      KeyValueMap keyValueMap1;
      ArrayList<Command> arrayList;
      switch (b) {
        default:
          continue;
        case true:
          patternScanner.eat(pattern9, str1);
          b = 0;
          continue;
        case true:
          keyValueMap2 = readKeyValueAssignments(patternScanner, pattern9);
          this.mSettings.putAll((Map)keyValueMap2);
          b = 16;
          continue;
        case true:
          bindExternal(patternScanner.eat(pattern10, "<external-identifier>"));
          b = 16;
          continue;
        case true:
          keyValueMap2 = readKeyValueAssignments(patternScanner, pattern9);
          this.mBoundReferences.putAll((Map)keyValueMap2);
          b = 16;
          continue;
        case true:
          str6 = patternScanner.eat(pattern7, "[<target-port-name>]");
          str6 = str6.substring(1, str6.length() - 1);
          this.mCommands.add(new ConnectCommand(str2, str3, (String)pattern4, str6));
          b = 16;
          continue;
        case true:
          str4 = patternScanner.eat(pattern10, "<target-filter-name>");
          b = 12;
          continue;
        case true:
          patternScanner.eat(pattern8, "=>");
          b = 11;
          continue;
        case true:
          str3 = patternScanner.eat(pattern7, "[<source-port-name>]");
          str3 = str3.substring(1, str3.length() - 1);
          b = 10;
          continue;
        case true:
          str2 = patternScanner.eat(pattern10, "<source-filter-name>");
          b = 9;
          continue;
        case true:
          patternScanner.eat(pattern2, "}");
          b = 0;
          continue;
        case true:
          keyValueMap1 = readKeyValueAssignments(patternScanner, pattern2);
          this.mCommands.add(new InitFilterCommand(keyValueMap1));
          b = 7;
          continue;
        case true:
          patternScanner.eat(pattern3, "{");
          b = 6;
          continue;
        case true:
          str5 = patternScanner.eat(pattern10, "<filter-name>");
          arrayList = this.mCommands;
          arrayList.add(new AllocateFilterCommand(paramString, str5));
          b = 5;
          continue;
        case true:
          paramString = patternScanner.eat(pattern10, "<class-name>");
          b = 4;
          continue;
        case true:
          str5 = patternScanner.eat(pattern6, "<library-name>");
          this.mCommands.add(new AddLibraryCommand(str5));
          b = 16;
          continue;
        case true:
          str5 = patternScanner.eat(pattern5, "<package-name>");
          this.mCommands.add(new ImportPackageCommand(str5));
          b = 16;
          continue;
        case false:
          break;
      } 
      String str5 = patternScanner.eat(pattern1, "<command>");
      if (str5.equals("@import")) {
        b = 1;
        continue;
      } 
      if (str5.equals("@library")) {
        b = 2;
        continue;
      } 
      if (str5.equals("@filter")) {
        b = 3;
        continue;
      } 
      if (str5.equals("@connect")) {
        b = 8;
        continue;
      } 
      if (str5.equals("@set")) {
        b = 13;
        continue;
      } 
      if (str5.equals("@external")) {
        b = 14;
        continue;
      } 
      if (str5.equals("@setting")) {
        b = 15;
        continue;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Unknown command '");
      stringBuilder.append(str5);
      stringBuilder.append("'!");
      throw new GraphIOException(stringBuilder.toString());
    } 
    if (b == 16 || b == 0)
      return; 
    throw new GraphIOException("Unexpected end of input!");
  }
  
  private KeyValueMap readKeyValueAssignments(PatternScanner paramPatternScanner, Pattern paramPattern) throws GraphIOException {
    StringBuilder stringBuilder1;
    boolean bool = true;
    byte b1 = 2;
    byte b2 = 3;
    Pattern pattern1 = Pattern.compile("=");
    String str1 = ";";
    Pattern pattern2 = Pattern.compile(";");
    Pattern pattern3 = Pattern.compile("[a-zA-Z]+[a-zA-Z0-9]*");
    Pattern pattern4 = Pattern.compile("'[^']*'|\\\"[^\\\"]*\\\"");
    Pattern pattern5 = Pattern.compile("[0-9]+");
    Pattern pattern6 = Pattern.compile("[0-9]*\\.[0-9]+f?");
    Pattern pattern7 = Pattern.compile("\\$[a-zA-Z]+[a-zA-Z0-9]");
    Pattern pattern8 = Pattern.compile("true|false");
    byte b3 = 0;
    KeyValueMap keyValueMap = new KeyValueMap();
    String str2 = null;
    while (!paramPatternScanner.atEnd() && (paramPattern == null || !paramPatternScanner.peek(paramPattern))) {
      if (b3) {
        if (b3 != 1) {
          if (b3 != 2) {
            if (b3 != 3)
              continue; 
            paramPatternScanner.eat(pattern2, str1);
            b3 = 0;
            continue;
          } 
          String str = paramPatternScanner.tryEat(pattern4);
          if (str != null) {
            keyValueMap.put(str2, str.substring(1, str.length() - 1));
          } else {
            KeyValueMap keyValueMap1 = keyValueMap;
            str = paramPatternScanner.tryEat(pattern7);
            if (str != null) {
              String str3 = str.substring(1, str.length());
              KeyValueMap keyValueMap2 = this.mBoundReferences;
              if (keyValueMap2 != null) {
                Object object = keyValueMap2.get(str3);
              } else {
                keyValueMap2 = null;
              } 
              if (keyValueMap2 != null) {
                keyValueMap1.put(str2, keyValueMap2);
              } else {
                stringBuilder1 = new StringBuilder();
                stringBuilder1.append("Unknown object reference to '");
                stringBuilder1.append(str3);
                stringBuilder1.append("'!");
                throw new GraphIOException(stringBuilder1.toString());
              } 
            } else {
              str = stringBuilder1.tryEat(pattern8);
              if (str != null) {
                keyValueMap1.put(str2, Boolean.valueOf(Boolean.parseBoolean(str)));
              } else {
                str = stringBuilder1.tryEat(pattern6);
                if (str != null) {
                  keyValueMap1.put(str2, Float.valueOf(Float.parseFloat(str)));
                } else {
                  str = stringBuilder1.tryEat(pattern5);
                  if (str != null) {
                    keyValueMap1.put(str2, Integer.valueOf(Integer.parseInt(str)));
                  } else {
                    throw new GraphIOException(stringBuilder1.unexpectedTokenMessage("<value>"));
                  } 
                } 
              } 
            } 
          } 
          b3 = 3;
          continue;
        } 
        stringBuilder1.eat(pattern1, "=");
        b3 = 2;
        continue;
      } 
      str2 = stringBuilder1.eat(pattern3, "<identifier>");
      b3 = 1;
    } 
    if (b3 == 0 || b3 == 3)
      return keyValueMap; 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Unexpected end of assignments on line ");
    stringBuilder2.append(stringBuilder1.lineNo());
    stringBuilder2.append("!");
    throw new GraphIOException(stringBuilder2.toString());
  }
  
  private void reset() {
    this.mCurrentGraph = null;
    this.mCurrentFilter = null;
    this.mCommands.clear();
    this.mBoundReferences = new KeyValueMap();
    this.mSettings = new KeyValueMap();
    this.mFactory = new FilterFactory();
  }
  
  public FilterGraph readGraphString(String paramString) throws GraphIOException {
    FilterGraph filterGraph = new FilterGraph();
    reset();
    this.mCurrentGraph = filterGraph;
    parseString(paramString);
    applySettings();
    executeCommands();
    reset();
    return filterGraph;
  }
  
  public KeyValueMap readKeyValueAssignments(String paramString) throws GraphIOException {
    return readKeyValueAssignments(new PatternScanner(paramString, Pattern.compile("\\s+")), null);
  }
  
  private class AddLibraryCommand implements Command {
    private String mLibraryName;
    
    public AddLibraryCommand(String param1String) {
      this.mLibraryName = param1String;
    }
    
    public void execute(TextGraphReader param1TextGraphReader) {
      param1TextGraphReader.mFactory;
      FilterFactory.addFilterLibrary(this.mLibraryName);
    }
  }
  
  private class AllocateFilterCommand implements Command {
    private String mClassName;
    
    private String mFilterName;
    
    public AllocateFilterCommand(String param1String1, String param1String2) {
      this.mClassName = param1String1;
      this.mFilterName = param1String2;
    }
    
    public void execute(TextGraphReader param1TextGraphReader) throws GraphIOException {
      try {
        Filter filter = param1TextGraphReader.mFactory.createFilterByClassName(this.mClassName, this.mFilterName);
        TextGraphReader.access$102(param1TextGraphReader, filter);
        return;
      } catch (IllegalArgumentException illegalArgumentException) {
        throw new GraphIOException(illegalArgumentException.getMessage());
      } 
    }
  }
  
  private static interface Command {
    void execute(TextGraphReader param1TextGraphReader) throws GraphIOException;
  }
  
  private class ConnectCommand implements Command {
    private String mSourceFilter;
    
    private String mSourcePort;
    
    private String mTargetFilter;
    
    private String mTargetName;
    
    public ConnectCommand(String param1String1, String param1String2, String param1String3, String param1String4) {
      this.mSourceFilter = param1String1;
      this.mSourcePort = param1String2;
      this.mTargetFilter = param1String3;
      this.mTargetName = param1String4;
    }
    
    public void execute(TextGraphReader param1TextGraphReader) {
      param1TextGraphReader.mCurrentGraph.connect(this.mSourceFilter, this.mSourcePort, this.mTargetFilter, this.mTargetName);
    }
  }
  
  private class ImportPackageCommand implements Command {
    private String mPackageName;
    
    public ImportPackageCommand(String param1String) {
      this.mPackageName = param1String;
    }
    
    public void execute(TextGraphReader param1TextGraphReader) throws GraphIOException {
      try {
        param1TextGraphReader.mFactory.addPackage(this.mPackageName);
        return;
      } catch (IllegalArgumentException illegalArgumentException) {
        throw new GraphIOException(illegalArgumentException.getMessage());
      } 
    }
  }
  
  private class InitFilterCommand implements Command {
    private KeyValueMap mParams;
    
    public InitFilterCommand(KeyValueMap param1KeyValueMap) {
      this.mParams = param1KeyValueMap;
    }
    
    public void execute(TextGraphReader param1TextGraphReader) throws GraphIOException {
      Filter filter = param1TextGraphReader.mCurrentFilter;
      try {
        filter.initWithValueMap(this.mParams);
        param1TextGraphReader.mCurrentGraph.addFilter(TextGraphReader.this.mCurrentFilter);
        return;
      } catch (ProtocolException protocolException) {
        throw new GraphIOException(protocolException.getMessage());
      } 
    }
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/io/TextGraphReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
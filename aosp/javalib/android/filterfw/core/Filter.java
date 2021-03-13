package android.filterfw.core;

import android.filterfw.format.ObjectFormat;
import android.filterfw.io.GraphIOException;
import android.filterfw.io.TextGraphReader;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class Filter {
  static final int STATUS_ERROR = 6;
  
  static final int STATUS_FINISHED = 5;
  
  static final int STATUS_PREINIT = 0;
  
  static final int STATUS_PREPARED = 2;
  
  static final int STATUS_PROCESSING = 3;
  
  static final int STATUS_RELEASED = 7;
  
  static final int STATUS_SLEEPING = 4;
  
  static final int STATUS_UNPREPARED = 1;
  
  private static final String TAG = "Filter";
  
  private long mCurrentTimestamp;
  
  private HashSet<Frame> mFramesToRelease;
  
  private HashMap<String, Frame> mFramesToSet;
  
  private int mInputCount = -1;
  
  private HashMap<String, InputPort> mInputPorts;
  
  private boolean mIsOpen = false;
  
  private boolean mLogVerbose;
  
  private String mName;
  
  private int mOutputCount = -1;
  
  private HashMap<String, OutputPort> mOutputPorts;
  
  private int mSleepDelay;
  
  private int mStatus = 0;
  
  public Filter(String paramString) {
    this.mName = paramString;
    this.mFramesToRelease = new HashSet<>();
    this.mFramesToSet = new HashMap<>();
    this.mStatus = 0;
    this.mLogVerbose = Log.isLoggable("Filter", 2);
  }
  
  private final void addAndSetFinalPorts(KeyValueMap paramKeyValueMap) {
    for (Field field : getClass().getDeclaredFields()) {
      GenerateFinalPort generateFinalPort = (GenerateFinalPort)field.getAnnotation((Class)GenerateFinalPort.class);
      if (generateFinalPort != null) {
        String str;
        GenerateFinalPort generateFinalPort1 = generateFinalPort;
        if (generateFinalPort1.name().isEmpty()) {
          str = field.getName();
        } else {
          str = generateFinalPort1.name();
        } 
        addFieldPort(str, field, generateFinalPort1.hasDefault(), true);
        if (paramKeyValueMap.containsKey(str)) {
          setImmediateInputValue(str, paramKeyValueMap.get(str));
          paramKeyValueMap.remove(str);
        } else if (!generateFinalPort1.hasDefault()) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("No value specified for final input port '");
          stringBuilder.append(str);
          stringBuilder.append("' of filter ");
          stringBuilder.append(this);
          stringBuilder.append("!");
          throw new RuntimeException(stringBuilder.toString());
        } 
      } 
    } 
  }
  
  private final void addAnnotatedPorts() {
    for (Field field : getClass().getDeclaredFields()) {
      GenerateFieldPort generateFieldPort = (GenerateFieldPort)field.getAnnotation((Class)GenerateFieldPort.class);
      if (generateFieldPort != null) {
        addFieldGenerator(generateFieldPort, field);
      } else {
        generateFieldPort = field.getAnnotation(GenerateProgramPort.class);
        if (generateFieldPort != null) {
          addProgramGenerator((GenerateProgramPort)generateFieldPort, field);
        } else {
          generateFieldPort = field.getAnnotation(GenerateProgramPorts.class);
          if (generateFieldPort != null) {
            GenerateProgramPort[] arrayOfGenerateProgramPort = ((GenerateProgramPorts)generateFieldPort).value();
            int i = arrayOfGenerateProgramPort.length;
            for (byte b = 0; b < i; b++)
              addProgramGenerator(arrayOfGenerateProgramPort[b], field); 
          } 
        } 
      } 
    } 
  }
  
  private final void addFieldGenerator(GenerateFieldPort paramGenerateFieldPort, Field paramField) {
    String str;
    if (paramGenerateFieldPort.name().isEmpty()) {
      str = paramField.getName();
    } else {
      str = paramGenerateFieldPort.name();
    } 
    addFieldPort(str, paramField, paramGenerateFieldPort.hasDefault(), false);
  }
  
  private final void addProgramGenerator(GenerateProgramPort paramGenerateProgramPort, Field paramField) {
    String str2;
    String str1 = paramGenerateProgramPort.name();
    if (paramGenerateProgramPort.variableName().isEmpty()) {
      str2 = str1;
    } else {
      str2 = paramGenerateProgramPort.variableName();
    } 
    addProgramPort(str1, str2, paramField, paramGenerateProgramPort.type(), paramGenerateProgramPort.hasDefault());
  }
  
  private final void closePorts() {
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Closing all ports on ");
      stringBuilder.append(this);
      stringBuilder.append("!");
      Log.v("Filter", stringBuilder.toString());
    } 
    Iterator<InputPort> iterator = this.mInputPorts.values().iterator();
    while (iterator.hasNext())
      ((InputPort)iterator.next()).close(); 
    iterator = this.mOutputPorts.values().iterator();
    while (iterator.hasNext())
      ((OutputPort)iterator.next()).close(); 
  }
  
  private final boolean filterMustClose() {
    Iterator<InputPort> iterator = this.mInputPorts.values().iterator();
    while (iterator.hasNext()) {
      InputPort inputPort = iterator.next();
      if (inputPort.filterMustClose()) {
        if (this.mLogVerbose) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Filter ");
          stringBuilder.append(this);
          stringBuilder.append(" must close due to port ");
          stringBuilder.append(inputPort);
          Log.v("Filter", stringBuilder.toString());
        } 
        return true;
      } 
    } 
    iterator = this.mOutputPorts.values().iterator();
    while (iterator.hasNext()) {
      OutputPort outputPort = (OutputPort)iterator.next();
      if (outputPort.filterMustClose()) {
        if (this.mLogVerbose) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Filter ");
          stringBuilder.append(this);
          stringBuilder.append(" must close due to port ");
          stringBuilder.append(outputPort);
          Log.v("Filter", stringBuilder.toString());
        } 
        return true;
      } 
    } 
    return false;
  }
  
  private final void initFinalPorts(KeyValueMap paramKeyValueMap) {
    this.mInputPorts = new HashMap<>();
    this.mOutputPorts = new HashMap<>();
    addAndSetFinalPorts(paramKeyValueMap);
  }
  
  private final void initRemainingPorts(KeyValueMap paramKeyValueMap) {
    addAnnotatedPorts();
    setupPorts();
    setInitialInputValues(paramKeyValueMap);
  }
  
  private final boolean inputConditionsMet() {
    Iterator<FilterPort> iterator = this.mInputPorts.values().iterator();
    while (iterator.hasNext()) {
      FilterPort filterPort = iterator.next();
      if (!filterPort.isReady()) {
        if (this.mLogVerbose) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Input condition not met: ");
          stringBuilder.append(filterPort);
          stringBuilder.append("!");
          Log.v("Filter", stringBuilder.toString());
        } 
        return false;
      } 
    } 
    return true;
  }
  
  public static final boolean isAvailable(String paramString) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    try {
      Class<?> clazz = classLoader.loadClass(paramString);
      try {
        clazz.asSubclass(Filter.class);
        return true;
      } catch (ClassCastException classCastException) {
        return false;
      } 
    } catch (ClassNotFoundException classNotFoundException) {
      return false;
    } 
  }
  
  private final boolean outputConditionsMet() {
    Iterator<FilterPort> iterator = this.mOutputPorts.values().iterator();
    while (iterator.hasNext()) {
      FilterPort filterPort = iterator.next();
      if (!filterPort.isReady()) {
        if (this.mLogVerbose) {
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Output condition not met: ");
          stringBuilder.append(filterPort);
          stringBuilder.append("!");
          Log.v("Filter", stringBuilder.toString());
        } 
        return false;
      } 
    } 
    return true;
  }
  
  private final void releasePulledFrames(FilterContext paramFilterContext) {
    for (Frame frame : this.mFramesToRelease)
      paramFilterContext.getFrameManager().releaseFrame(frame); 
    this.mFramesToRelease.clear();
  }
  
  private final void setImmediateInputValue(String paramString, Object paramObject) {
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Setting immediate value ");
      stringBuilder.append(paramObject);
      stringBuilder.append(" for port ");
      stringBuilder.append(paramString);
      stringBuilder.append("!");
      Log.v("Filter", stringBuilder.toString());
    } 
    InputPort inputPort = getInputPort(paramString);
    inputPort.open();
    inputPort.setFrame(SimpleFrame.wrapObject(paramObject, null));
  }
  
  private final void setInitialInputValues(KeyValueMap paramKeyValueMap) {
    for (Map.Entry<String, Object> entry : paramKeyValueMap.entrySet())
      setInputValue((String)entry.getKey(), entry.getValue()); 
  }
  
  private final void transferInputFrames(FilterContext paramFilterContext) {
    Iterator<InputPort> iterator = this.mInputPorts.values().iterator();
    while (iterator.hasNext())
      ((InputPort)iterator.next()).transfer(paramFilterContext); 
  }
  
  private final Frame wrapInputValue(String paramString, Object paramObject) {
    SimpleFrame simpleFrame;
    boolean bool = true;
    MutableFrameFormat mutableFrameFormat = ObjectFormat.fromObject(paramObject, 1);
    if (paramObject == null) {
      Class clazz;
      FrameFormat frameFormat = getInputPort(paramString).getPortFormat();
      if (frameFormat == null) {
        frameFormat = null;
      } else {
        clazz = frameFormat.getObjectClass();
      } 
      mutableFrameFormat.setObjectClass(clazz);
    } 
    if (paramObject instanceof Number || paramObject instanceof Boolean || paramObject instanceof String || !(paramObject instanceof java.io.Serializable))
      bool = false; 
    if (bool) {
      SerializedFrame serializedFrame = new SerializedFrame(mutableFrameFormat, null);
    } else {
      simpleFrame = new SimpleFrame(mutableFrameFormat, null);
    } 
    simpleFrame.setObjectValue(paramObject);
    return simpleFrame;
  }
  
  protected void addFieldPort(String paramString, Field paramField, boolean paramBoolean1, boolean paramBoolean2) {
    FieldPort fieldPort;
    paramField.setAccessible(true);
    if (paramBoolean2) {
      fieldPort = new FinalPort(this, paramString, paramField, paramBoolean1);
    } else {
      fieldPort = new FieldPort(this, paramString, paramField, paramBoolean1);
    } 
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Filter ");
      stringBuilder.append(this);
      stringBuilder.append(" adding ");
      stringBuilder.append(fieldPort);
      Log.v("Filter", stringBuilder.toString());
    } 
    fieldPort.setPortFormat(ObjectFormat.fromClass(paramField.getType(), 1));
    this.mInputPorts.put(paramString, fieldPort);
  }
  
  protected void addInputPort(String paramString) {
    addMaskedInputPort(paramString, null);
  }
  
  protected void addMaskedInputPort(String paramString, FrameFormat paramFrameFormat) {
    StreamPort streamPort = new StreamPort(this, paramString);
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Filter ");
      stringBuilder.append(this);
      stringBuilder.append(" adding ");
      stringBuilder.append(streamPort);
      Log.v("Filter", stringBuilder.toString());
    } 
    this.mInputPorts.put(paramString, streamPort);
    streamPort.setPortFormat(paramFrameFormat);
  }
  
  protected void addOutputBasedOnInput(String paramString1, String paramString2) {
    OutputPort outputPort = new OutputPort(this, paramString1);
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Filter ");
      stringBuilder.append(this);
      stringBuilder.append(" adding ");
      stringBuilder.append(outputPort);
      Log.v("Filter", stringBuilder.toString());
    } 
    outputPort.setBasePort(getInputPort(paramString2));
    this.mOutputPorts.put(paramString1, outputPort);
  }
  
  protected void addOutputPort(String paramString, FrameFormat paramFrameFormat) {
    OutputPort outputPort = new OutputPort(this, paramString);
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Filter ");
      stringBuilder.append(this);
      stringBuilder.append(" adding ");
      stringBuilder.append(outputPort);
      Log.v("Filter", stringBuilder.toString());
    } 
    outputPort.setPortFormat(paramFrameFormat);
    this.mOutputPorts.put(paramString, outputPort);
  }
  
  protected void addProgramPort(String paramString1, String paramString2, Field paramField, Class paramClass, boolean paramBoolean) {
    paramField.setAccessible(true);
    ProgramPort programPort = new ProgramPort(this, paramString1, paramString2, paramField, paramBoolean);
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Filter ");
      stringBuilder.append(this);
      stringBuilder.append(" adding ");
      stringBuilder.append(programPort);
      Log.v("Filter", stringBuilder.toString());
    } 
    programPort.setPortFormat(ObjectFormat.fromClass(paramClass, 1));
    this.mInputPorts.put(paramString1, programPort);
  }
  
  final boolean canProcess() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mLogVerbose : Z
    //   6: ifeq -> 66
    //   9: new java/lang/StringBuilder
    //   12: astore_1
    //   13: aload_1
    //   14: invokespecial <init> : ()V
    //   17: aload_1
    //   18: ldc_w 'Checking if can process: '
    //   21: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: pop
    //   25: aload_1
    //   26: aload_0
    //   27: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   30: pop
    //   31: aload_1
    //   32: ldc_w ' ('
    //   35: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload_1
    //   40: aload_0
    //   41: getfield mStatus : I
    //   44: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   47: pop
    //   48: aload_1
    //   49: ldc_w ').'
    //   52: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: pop
    //   56: ldc 'Filter'
    //   58: aload_1
    //   59: invokevirtual toString : ()Ljava/lang/String;
    //   62: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   65: pop
    //   66: aload_0
    //   67: getfield mStatus : I
    //   70: istore_2
    //   71: iconst_0
    //   72: istore_3
    //   73: iload_2
    //   74: iconst_3
    //   75: if_icmpgt -> 110
    //   78: iload_3
    //   79: istore #4
    //   81: aload_0
    //   82: invokespecial inputConditionsMet : ()Z
    //   85: ifeq -> 105
    //   88: aload_0
    //   89: invokespecial outputConditionsMet : ()Z
    //   92: istore #5
    //   94: iload_3
    //   95: istore #4
    //   97: iload #5
    //   99: ifeq -> 105
    //   102: iconst_1
    //   103: istore #4
    //   105: aload_0
    //   106: monitorexit
    //   107: iload #4
    //   109: ireturn
    //   110: aload_0
    //   111: monitorexit
    //   112: iconst_0
    //   113: ireturn
    //   114: astore_1
    //   115: aload_0
    //   116: monitorexit
    //   117: aload_1
    //   118: athrow
    // Exception table:
    //   from	to	target	type
    //   2	66	114	finally
    //   66	71	114	finally
    //   81	94	114	finally
  }
  
  final void clearInputs() {
    Iterator<InputPort> iterator = this.mInputPorts.values().iterator();
    while (iterator.hasNext())
      ((InputPort)iterator.next()).clear(); 
  }
  
  final void clearOutputs() {
    Iterator<OutputPort> iterator = this.mOutputPorts.values().iterator();
    while (iterator.hasNext())
      ((OutputPort)iterator.next()).clear(); 
  }
  
  public void close(FilterContext paramFilterContext) {}
  
  protected void closeOutputPort(String paramString) {
    getOutputPort(paramString).close();
  }
  
  protected void delayNextProcess(int paramInt) {
    this.mSleepDelay = paramInt;
    this.mStatus = 4;
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {}
  
  public String getFilterClassName() {
    return getClass().getSimpleName();
  }
  
  public final FrameFormat getInputFormat(String paramString) {
    return getInputPort(paramString).getSourceFormat();
  }
  
  public final InputPort getInputPort(String paramString) {
    HashMap<String, InputPort> hashMap = this.mInputPorts;
    if (hashMap != null) {
      InputPort inputPort = hashMap.get(paramString);
      if (inputPort != null)
        return inputPort; 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Unknown input port '");
      stringBuilder1.append(paramString);
      stringBuilder1.append("' on filter ");
      stringBuilder1.append(this);
      stringBuilder1.append("!");
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Attempting to access input port '");
    stringBuilder.append(paramString);
    stringBuilder.append("' of ");
    stringBuilder.append(this);
    stringBuilder.append(" before Filter has been initialized!");
    throw new NullPointerException(stringBuilder.toString());
  }
  
  final Collection<InputPort> getInputPorts() {
    return this.mInputPorts.values();
  }
  
  public final String getName() {
    return this.mName;
  }
  
  public final int getNumberOfConnectedInputs() {
    int i = 0;
    Iterator<InputPort> iterator = this.mInputPorts.values().iterator();
    while (iterator.hasNext()) {
      int j = i;
      if (((InputPort)iterator.next()).isConnected())
        j = i + 1; 
      i = j;
    } 
    return i;
  }
  
  public final int getNumberOfConnectedOutputs() {
    int i = 0;
    Iterator<OutputPort> iterator = this.mOutputPorts.values().iterator();
    while (iterator.hasNext()) {
      int j = i;
      if (((OutputPort)iterator.next()).isConnected())
        j = i + 1; 
      i = j;
    } 
    return i;
  }
  
  public final int getNumberOfInputs() {
    int i;
    if (this.mOutputPorts == null) {
      i = 0;
    } else {
      i = this.mInputPorts.size();
    } 
    return i;
  }
  
  public final int getNumberOfOutputs() {
    int i;
    if (this.mInputPorts == null) {
      i = 0;
    } else {
      i = this.mOutputPorts.size();
    } 
    return i;
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return null;
  }
  
  public final OutputPort getOutputPort(String paramString) {
    if (this.mInputPorts != null) {
      OutputPort outputPort = this.mOutputPorts.get(paramString);
      if (outputPort != null)
        return outputPort; 
      StringBuilder stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Unknown output port '");
      stringBuilder1.append(paramString);
      stringBuilder1.append("' on filter ");
      stringBuilder1.append(this);
      stringBuilder1.append("!");
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Attempting to access output port '");
    stringBuilder.append(paramString);
    stringBuilder.append("' of ");
    stringBuilder.append(this);
    stringBuilder.append(" before Filter has been initialized!");
    throw new NullPointerException(stringBuilder.toString());
  }
  
  final Collection<OutputPort> getOutputPorts() {
    return this.mOutputPorts.values();
  }
  
  public final int getSleepDelay() {
    return 250;
  }
  
  final int getStatus() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mStatus : I
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	11	finally
  }
  
  public final void init() throws ProtocolException {
    initWithValueMap(new KeyValueMap());
  }
  
  protected void initProgramInputs(Program paramProgram, FilterContext paramFilterContext) {
    if (paramProgram != null)
      for (InputPort inputPort : this.mInputPorts.values()) {
        if (inputPort.getTarget() == paramProgram)
          inputPort.transfer(paramFilterContext); 
      }  
  }
  
  public final void initWithAssignmentList(Object... paramVarArgs) {
    KeyValueMap keyValueMap = new KeyValueMap();
    keyValueMap.setKeyValues(paramVarArgs);
    initWithValueMap(keyValueMap);
  }
  
  public final void initWithAssignmentString(String paramString) {
    try {
      TextGraphReader textGraphReader = new TextGraphReader();
      this();
      initWithValueMap(textGraphReader.readKeyValueAssignments(paramString));
      return;
    } catch (GraphIOException graphIOException) {
      throw new IllegalArgumentException(graphIOException.getMessage());
    } 
  }
  
  public final void initWithValueMap(KeyValueMap paramKeyValueMap) {
    initFinalPorts(paramKeyValueMap);
    initRemainingPorts(paramKeyValueMap);
    this.mStatus = 1;
  }
  
  public boolean isOpen() {
    return this.mIsOpen;
  }
  
  final void notifyFieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    int i = this.mStatus;
    if (i == 3 || i == 2)
      fieldPortValueUpdated(paramString, paramFilterContext); 
  }
  
  public void open(FilterContext paramFilterContext) {}
  
  final void openOutputs() {
    if (this.mLogVerbose) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Opening all output ports on ");
      stringBuilder.append(this);
      stringBuilder.append("!");
      Log.v("Filter", stringBuilder.toString());
    } 
    for (OutputPort outputPort : this.mOutputPorts.values()) {
      if (!outputPort.isOpen())
        outputPort.open(); 
    } 
  }
  
  protected void parametersUpdated(Set<String> paramSet) {}
  
  final void performClose(FilterContext paramFilterContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mIsOpen : Z
    //   6: ifeq -> 67
    //   9: aload_0
    //   10: getfield mLogVerbose : Z
    //   13: ifeq -> 48
    //   16: new java/lang/StringBuilder
    //   19: astore_2
    //   20: aload_2
    //   21: invokespecial <init> : ()V
    //   24: aload_2
    //   25: ldc_w 'Closing '
    //   28: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload_2
    //   33: aload_0
    //   34: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   37: pop
    //   38: ldc 'Filter'
    //   40: aload_2
    //   41: invokevirtual toString : ()Ljava/lang/String;
    //   44: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   47: pop
    //   48: aload_0
    //   49: iconst_0
    //   50: putfield mIsOpen : Z
    //   53: aload_0
    //   54: iconst_2
    //   55: putfield mStatus : I
    //   58: aload_0
    //   59: aload_1
    //   60: invokevirtual close : (Landroid/filterfw/core/FilterContext;)V
    //   63: aload_0
    //   64: invokespecial closePorts : ()V
    //   67: aload_0
    //   68: monitorexit
    //   69: return
    //   70: astore_1
    //   71: aload_0
    //   72: monitorexit
    //   73: aload_1
    //   74: athrow
    // Exception table:
    //   from	to	target	type
    //   2	48	70	finally
    //   48	67	70	finally
  }
  
  final void performOpen(FilterContext paramFilterContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mIsOpen : Z
    //   6: ifne -> 199
    //   9: aload_0
    //   10: getfield mStatus : I
    //   13: iconst_1
    //   14: if_icmpne -> 66
    //   17: aload_0
    //   18: getfield mLogVerbose : Z
    //   21: ifeq -> 56
    //   24: new java/lang/StringBuilder
    //   27: astore_2
    //   28: aload_2
    //   29: invokespecial <init> : ()V
    //   32: aload_2
    //   33: ldc_w 'Preparing '
    //   36: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload_2
    //   41: aload_0
    //   42: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   45: pop
    //   46: ldc 'Filter'
    //   48: aload_2
    //   49: invokevirtual toString : ()Ljava/lang/String;
    //   52: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   55: pop
    //   56: aload_0
    //   57: aload_1
    //   58: invokevirtual prepare : (Landroid/filterfw/core/FilterContext;)V
    //   61: aload_0
    //   62: iconst_2
    //   63: putfield mStatus : I
    //   66: aload_0
    //   67: getfield mStatus : I
    //   70: iconst_2
    //   71: if_icmpne -> 123
    //   74: aload_0
    //   75: getfield mLogVerbose : Z
    //   78: ifeq -> 113
    //   81: new java/lang/StringBuilder
    //   84: astore_2
    //   85: aload_2
    //   86: invokespecial <init> : ()V
    //   89: aload_2
    //   90: ldc_w 'Opening '
    //   93: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: aload_2
    //   98: aload_0
    //   99: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   102: pop
    //   103: ldc 'Filter'
    //   105: aload_2
    //   106: invokevirtual toString : ()Ljava/lang/String;
    //   109: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   112: pop
    //   113: aload_0
    //   114: aload_1
    //   115: invokevirtual open : (Landroid/filterfw/core/FilterContext;)V
    //   118: aload_0
    //   119: iconst_3
    //   120: putfield mStatus : I
    //   123: aload_0
    //   124: getfield mStatus : I
    //   127: iconst_3
    //   128: if_icmpne -> 139
    //   131: aload_0
    //   132: iconst_1
    //   133: putfield mIsOpen : Z
    //   136: goto -> 199
    //   139: new java/lang/RuntimeException
    //   142: astore_1
    //   143: new java/lang/StringBuilder
    //   146: astore_2
    //   147: aload_2
    //   148: invokespecial <init> : ()V
    //   151: aload_2
    //   152: ldc 'Filter '
    //   154: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: pop
    //   158: aload_2
    //   159: aload_0
    //   160: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   163: pop
    //   164: aload_2
    //   165: ldc_w ' was brought into invalid state during opening (state: '
    //   168: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   171: pop
    //   172: aload_2
    //   173: aload_0
    //   174: getfield mStatus : I
    //   177: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   180: pop
    //   181: aload_2
    //   182: ldc_w ')!'
    //   185: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   188: pop
    //   189: aload_1
    //   190: aload_2
    //   191: invokevirtual toString : ()Ljava/lang/String;
    //   194: invokespecial <init> : (Ljava/lang/String;)V
    //   197: aload_1
    //   198: athrow
    //   199: aload_0
    //   200: monitorexit
    //   201: return
    //   202: astore_1
    //   203: aload_0
    //   204: monitorexit
    //   205: aload_1
    //   206: athrow
    // Exception table:
    //   from	to	target	type
    //   2	56	202	finally
    //   56	66	202	finally
    //   66	113	202	finally
    //   113	123	202	finally
    //   123	136	202	finally
    //   139	199	202	finally
  }
  
  final void performProcess(FilterContext paramFilterContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mStatus : I
    //   6: bipush #7
    //   8: if_icmpeq -> 100
    //   11: aload_0
    //   12: aload_1
    //   13: invokespecial transferInputFrames : (Landroid/filterfw/core/FilterContext;)V
    //   16: aload_0
    //   17: getfield mStatus : I
    //   20: iconst_3
    //   21: if_icmpge -> 29
    //   24: aload_0
    //   25: aload_1
    //   26: invokevirtual performOpen : (Landroid/filterfw/core/FilterContext;)V
    //   29: aload_0
    //   30: getfield mLogVerbose : Z
    //   33: ifeq -> 68
    //   36: new java/lang/StringBuilder
    //   39: astore_2
    //   40: aload_2
    //   41: invokespecial <init> : ()V
    //   44: aload_2
    //   45: ldc_w 'Processing '
    //   48: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: pop
    //   52: aload_2
    //   53: aload_0
    //   54: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   57: pop
    //   58: ldc 'Filter'
    //   60: aload_2
    //   61: invokevirtual toString : ()Ljava/lang/String;
    //   64: invokestatic v : (Ljava/lang/String;Ljava/lang/String;)I
    //   67: pop
    //   68: aload_0
    //   69: ldc2_w -1
    //   72: putfield mCurrentTimestamp : J
    //   75: aload_0
    //   76: aload_1
    //   77: invokevirtual process : (Landroid/filterfw/core/FilterContext;)V
    //   80: aload_0
    //   81: aload_1
    //   82: invokespecial releasePulledFrames : (Landroid/filterfw/core/FilterContext;)V
    //   85: aload_0
    //   86: invokespecial filterMustClose : ()Z
    //   89: ifeq -> 97
    //   92: aload_0
    //   93: aload_1
    //   94: invokevirtual performClose : (Landroid/filterfw/core/FilterContext;)V
    //   97: aload_0
    //   98: monitorexit
    //   99: return
    //   100: new java/lang/RuntimeException
    //   103: astore_1
    //   104: new java/lang/StringBuilder
    //   107: astore_2
    //   108: aload_2
    //   109: invokespecial <init> : ()V
    //   112: aload_2
    //   113: ldc 'Filter '
    //   115: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: aload_2
    //   120: aload_0
    //   121: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   124: pop
    //   125: aload_2
    //   126: ldc_w ' is already torn down!'
    //   129: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: pop
    //   133: aload_1
    //   134: aload_2
    //   135: invokevirtual toString : ()Ljava/lang/String;
    //   138: invokespecial <init> : (Ljava/lang/String;)V
    //   141: aload_1
    //   142: athrow
    //   143: astore_1
    //   144: aload_0
    //   145: monitorexit
    //   146: aload_1
    //   147: athrow
    // Exception table:
    //   from	to	target	type
    //   2	29	143	finally
    //   29	68	143	finally
    //   68	97	143	finally
    //   100	143	143	finally
  }
  
  final void performTearDown(FilterContext paramFilterContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual performClose : (Landroid/filterfw/core/FilterContext;)V
    //   7: aload_0
    //   8: getfield mStatus : I
    //   11: bipush #7
    //   13: if_icmpeq -> 27
    //   16: aload_0
    //   17: aload_1
    //   18: invokevirtual tearDown : (Landroid/filterfw/core/FilterContext;)V
    //   21: aload_0
    //   22: bipush #7
    //   24: putfield mStatus : I
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   2	27	30	finally
  }
  
  protected void prepare(FilterContext paramFilterContext) {}
  
  public abstract void process(FilterContext paramFilterContext);
  
  protected final Frame pullInput(String paramString) {
    Frame frame = getInputPort(paramString).pullFrame();
    if (this.mCurrentTimestamp == -1L) {
      this.mCurrentTimestamp = frame.getTimestamp();
      if (this.mLogVerbose) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Default-setting current timestamp from input port ");
        stringBuilder.append(paramString);
        stringBuilder.append(" to ");
        stringBuilder.append(this.mCurrentTimestamp);
        Log.v("Filter", stringBuilder.toString());
      } 
    } 
    this.mFramesToRelease.add(frame);
    return frame;
  }
  
  final void pushInputFrame(String paramString, Frame paramFrame) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual getInputPort : (Ljava/lang/String;)Landroid/filterfw/core/InputPort;
    //   7: astore_1
    //   8: aload_1
    //   9: invokevirtual isOpen : ()Z
    //   12: ifne -> 19
    //   15: aload_1
    //   16: invokevirtual open : ()V
    //   19: aload_1
    //   20: aload_2
    //   21: invokevirtual pushFrame : (Landroid/filterfw/core/Frame;)V
    //   24: aload_0
    //   25: monitorexit
    //   26: return
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: athrow
    // Exception table:
    //   from	to	target	type
    //   2	19	27	finally
    //   19	24	27	finally
  }
  
  final void pushInputValue(String paramString, Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_0
    //   5: aload_1
    //   6: aload_2
    //   7: invokespecial wrapInputValue : (Ljava/lang/String;Ljava/lang/Object;)Landroid/filterfw/core/Frame;
    //   10: invokevirtual pushInputFrame : (Ljava/lang/String;Landroid/filterfw/core/Frame;)V
    //   13: aload_0
    //   14: monitorexit
    //   15: return
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	16	finally
  }
  
  protected final void pushOutput(String paramString, Frame paramFrame) {
    if (paramFrame.getTimestamp() == -2L) {
      if (this.mLogVerbose) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Default-setting output Frame timestamp on port ");
        stringBuilder.append(paramString);
        stringBuilder.append(" to ");
        stringBuilder.append(this.mCurrentTimestamp);
        Log.v("Filter", stringBuilder.toString());
      } 
      paramFrame.setTimestamp(this.mCurrentTimestamp);
    } 
    getOutputPort(paramString).pushFrame(paramFrame);
  }
  
  public void setInputFrame(String paramString, Frame paramFrame) {
    InputPort inputPort = getInputPort(paramString);
    if (!inputPort.isOpen())
      inputPort.open(); 
    inputPort.setFrame(paramFrame);
  }
  
  public final void setInputValue(String paramString, Object paramObject) {
    setInputFrame(paramString, wrapInputValue(paramString, paramObject));
  }
  
  protected void setWaitsOnInputPort(String paramString, boolean paramBoolean) {
    getInputPort(paramString).setBlocking(paramBoolean);
  }
  
  protected void setWaitsOnOutputPort(String paramString, boolean paramBoolean) {
    getOutputPort(paramString).setBlocking(paramBoolean);
  }
  
  public abstract void setupPorts();
  
  public void tearDown(FilterContext paramFilterContext) {}
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("'");
    stringBuilder.append(getName());
    stringBuilder.append("' (");
    stringBuilder.append(getFilterClassName());
    stringBuilder.append(")");
    return stringBuilder.toString();
  }
  
  protected void transferInputPortFrame(String paramString, FilterContext paramFilterContext) {
    getInputPort(paramString).transfer(paramFilterContext);
  }
  
  final void unsetStatus(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield mStatus : I
    //   7: iload_1
    //   8: iand
    //   9: putfield mStatus : I
    //   12: aload_0
    //   13: monitorexit
    //   14: return
    //   15: astore_2
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_2
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	15	finally
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/Filter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
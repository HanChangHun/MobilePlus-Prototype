package android.filterfw.core;

import android.filterpacks.base.FrameBranch;
import android.filterpacks.base.NullFilter;
import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class FilterGraph {
  public static final int AUTOBRANCH_OFF = 0;
  
  public static final int AUTOBRANCH_SYNCED = 1;
  
  public static final int AUTOBRANCH_UNSYNCED = 2;
  
  public static final int TYPECHECK_DYNAMIC = 1;
  
  public static final int TYPECHECK_OFF = 0;
  
  public static final int TYPECHECK_STRICT = 2;
  
  private String TAG = "FilterGraph";
  
  private int mAutoBranchMode = 0;
  
  private boolean mDiscardUnconnectedOutputs = false;
  
  private HashSet<Filter> mFilters = new HashSet<>();
  
  private boolean mIsReady = false;
  
  private boolean mLogVerbose = Log.isLoggable("FilterGraph", 2);
  
  private HashMap<String, Filter> mNameMap = new HashMap<>();
  
  private HashMap<OutputPort, LinkedList<InputPort>> mPreconnections = new HashMap<>();
  
  private int mTypeCheckMode = 2;
  
  private void checkConnections() {}
  
  private void connectPorts() {
    byte b = 1;
    Iterator<Map.Entry> iterator = this.mPreconnections.entrySet().iterator();
    while (iterator.hasNext()) {
      Iterator<InputPort> iterator1;
      Map.Entry entry = iterator.next();
      OutputPort outputPort = (OutputPort)entry.getKey();
      LinkedList<InputPort> linkedList = (LinkedList)entry.getValue();
      if (linkedList.size() == 1) {
        outputPort.connectTo(linkedList.get(0));
        continue;
      } 
      if (this.mAutoBranchMode != 0) {
        if (this.mLogVerbose) {
          String str = this.TAG;
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Creating branch for ");
          stringBuilder1.append(outputPort);
          stringBuilder1.append("!");
          Log.v(str, stringBuilder1.toString());
        } 
        if (this.mAutoBranchMode == 1) {
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("branch");
          stringBuilder1.append(b);
          FrameBranch frameBranch = new FrameBranch(stringBuilder1.toString());
          new KeyValueMap();
          frameBranch.initWithAssignmentList(new Object[] { "outputs", Integer.valueOf(linkedList.size()) });
          addFilter((Filter)frameBranch);
          outputPort.connectTo(frameBranch.getInputPort("in"));
          iterator1 = linkedList.iterator();
          Iterator<OutputPort> iterator2 = frameBranch.getOutputPorts().iterator();
          while (iterator2.hasNext())
            ((OutputPort)iterator2.next()).connectTo(iterator1.next()); 
          b++;
          continue;
        } 
        throw new RuntimeException("TODO: Unsynced branches not implemented yet!");
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Attempting to connect ");
      stringBuilder.append(iterator1);
      stringBuilder.append(" to multiple filter ports! Enable auto-branching to allow this.");
      throw new RuntimeException(stringBuilder.toString());
    } 
    this.mPreconnections.clear();
  }
  
  private void discardUnconnectedOutputs() {
    LinkedList<NullFilter> linkedList = new LinkedList();
    for (Filter filter : this.mFilters) {
      int i = 0;
      for (OutputPort outputPort : filter.getOutputPorts()) {
        int j = i;
        if (!outputPort.isConnected()) {
          if (this.mLogVerbose) {
            String str = this.TAG;
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Autoconnecting unconnected ");
            stringBuilder1.append(outputPort);
            stringBuilder1.append(" to Null filter.");
            Log.v(str, stringBuilder1.toString());
          } 
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append(filter.getName());
          stringBuilder.append("ToNull");
          stringBuilder.append(i);
          NullFilter nullFilter = new NullFilter(stringBuilder.toString());
          nullFilter.init();
          linkedList.add(nullFilter);
          outputPort.connectTo(nullFilter.getInputPort("frame"));
          j = i + 1;
        } 
        i = j;
      } 
    } 
    Iterator<NullFilter> iterator = linkedList.iterator();
    while (iterator.hasNext())
      addFilter((Filter)iterator.next()); 
  }
  
  private HashSet<Filter> getSourceFilters() {
    HashSet<Filter> hashSet = new HashSet();
    for (Filter filter : getFilters()) {
      if (filter.getNumberOfConnectedInputs() == 0) {
        if (this.mLogVerbose) {
          String str = this.TAG;
          StringBuilder stringBuilder = new StringBuilder();
          stringBuilder.append("Found source filter: ");
          stringBuilder.append(filter);
          Log.v(str, stringBuilder.toString());
        } 
        hashSet.add(filter);
      } 
    } 
    return hashSet;
  }
  
  private void preconnect(OutputPort paramOutputPort, InputPort paramInputPort) {
    LinkedList<InputPort> linkedList1 = this.mPreconnections.get(paramOutputPort);
    LinkedList<InputPort> linkedList2 = linkedList1;
    if (linkedList1 == null) {
      linkedList2 = new LinkedList();
      this.mPreconnections.put(paramOutputPort, linkedList2);
    } 
    linkedList2.add(paramInputPort);
  }
  
  private boolean readyForProcessing(Filter paramFilter, Set<Filter> paramSet) {
    if (paramSet.contains(paramFilter))
      return false; 
    Iterator<InputPort> iterator = paramFilter.getInputPorts().iterator();
    while (iterator.hasNext()) {
      Filter filter = ((InputPort)iterator.next()).getSourceFilter();
      if (filter != null && !paramSet.contains(filter))
        return false; 
    } 
    return true;
  }
  
  private void removeFilter(Filter paramFilter) {
    this.mFilters.remove(paramFilter);
    this.mNameMap.remove(paramFilter.getName());
  }
  
  private void runTypeCheck() {
    Stack<Filter> stack = new Stack();
    HashSet<Filter> hashSet = new HashSet();
    stack.addAll(getSourceFilters());
    while (!stack.empty()) {
      Filter filter = stack.pop();
      hashSet.add(filter);
      updateOutputs(filter);
      if (this.mLogVerbose) {
        String str = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Running type check on ");
        stringBuilder.append(filter);
        stringBuilder.append("...");
        Log.v(str, stringBuilder.toString());
      } 
      runTypeCheckOn(filter);
      Iterator<OutputPort> iterator = filter.getOutputPorts().iterator();
      while (iterator.hasNext()) {
        Filter filter1 = ((OutputPort)iterator.next()).getTargetFilter();
        if (filter1 != null && readyForProcessing(filter1, hashSet))
          stack.push(filter1); 
      } 
    } 
    if (hashSet.size() == getFilters().size())
      return; 
    throw new RuntimeException("Could not schedule all filters! Is your graph malformed?");
  }
  
  private void runTypeCheckOn(Filter paramFilter) {
    Iterator<InputPort> iterator = paramFilter.getInputPorts().iterator();
    while (iterator.hasNext()) {
      InputPort inputPort = iterator.next();
      if (this.mLogVerbose) {
        String str = this.TAG;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Type checking port ");
        stringBuilder.append(inputPort);
        Log.v(str, stringBuilder.toString());
      } 
      FrameFormat frameFormat2 = inputPort.getSourceFormat();
      FrameFormat frameFormat1 = inputPort.getPortFormat();
      if (frameFormat2 != null && frameFormat1 != null) {
        if (this.mLogVerbose) {
          String str = this.TAG;
          StringBuilder stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Checking ");
          stringBuilder1.append(frameFormat2);
          stringBuilder1.append(" against ");
          stringBuilder1.append(frameFormat1);
          stringBuilder1.append(".");
          Log.v(str, stringBuilder1.toString());
        } 
        boolean bool = true;
        int i = this.mTypeCheckMode;
        if (i != 0) {
          if (i != 1) {
            if (i == 2) {
              bool = frameFormat2.isCompatibleWith(frameFormat1);
              inputPort.setChecksType(false);
            } 
          } else {
            bool = frameFormat2.mayBeCompatibleWith(frameFormat1);
            inputPort.setChecksType(true);
          } 
        } else {
          inputPort.setChecksType(false);
        } 
        if (bool)
          continue; 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Type mismatch: Filter ");
        stringBuilder.append(paramFilter);
        stringBuilder.append(" expects a format of type ");
        stringBuilder.append(frameFormat1);
        stringBuilder.append(" but got a format of type ");
        stringBuilder.append(frameFormat2);
        stringBuilder.append("!");
        throw new RuntimeException(stringBuilder.toString());
      } 
    } 
  }
  
  private void updateOutputs(Filter paramFilter) {
    for (OutputPort outputPort : paramFilter.getOutputPorts()) {
      InputPort inputPort = outputPort.getBasePort();
      if (inputPort != null) {
        FrameFormat frameFormat = inputPort.getSourceFormat();
        frameFormat = paramFilter.getOutputFormat(outputPort.getName(), frameFormat);
        if (frameFormat != null) {
          outputPort.setPortFormat(frameFormat);
          continue;
        } 
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Filter did not return an output format for ");
        stringBuilder.append(outputPort);
        stringBuilder.append("!");
        throw new RuntimeException(stringBuilder.toString());
      } 
    } 
  }
  
  public boolean addFilter(Filter paramFilter) {
    if (!containsFilter(paramFilter)) {
      this.mFilters.add(paramFilter);
      this.mNameMap.put(paramFilter.getName(), paramFilter);
      return true;
    } 
    return false;
  }
  
  public void beginProcessing() {
    if (this.mLogVerbose)
      Log.v(this.TAG, "Opening all filter connections..."); 
    Iterator<Filter> iterator = this.mFilters.iterator();
    while (iterator.hasNext())
      ((Filter)iterator.next()).openOutputs(); 
    this.mIsReady = true;
  }
  
  public void closeFilters(FilterContext paramFilterContext) {
    if (this.mLogVerbose)
      Log.v(this.TAG, "Closing all filters..."); 
    Iterator<Filter> iterator = this.mFilters.iterator();
    while (iterator.hasNext())
      ((Filter)iterator.next()).performClose(paramFilterContext); 
    this.mIsReady = false;
  }
  
  public void connect(Filter paramFilter1, String paramString1, Filter paramFilter2, String paramString2) {
    if (paramFilter1 != null && paramFilter2 != null) {
      if (containsFilter(paramFilter1) && containsFilter(paramFilter2)) {
        StringBuilder stringBuilder1;
        OutputPort outputPort = paramFilter1.getOutputPort(paramString1);
        InputPort inputPort = paramFilter2.getInputPort(paramString2);
        if (outputPort != null) {
          if (inputPort != null) {
            preconnect(outputPort, inputPort);
            return;
          } 
          stringBuilder1 = new StringBuilder();
          stringBuilder1.append("Unknown input port '");
          stringBuilder1.append(paramString2);
          stringBuilder1.append("' on Filter ");
          stringBuilder1.append(paramFilter2);
          stringBuilder1.append("!");
          throw new RuntimeException(stringBuilder1.toString());
        } 
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("Unknown output port '");
        stringBuilder2.append(paramString1);
        stringBuilder2.append("' on Filter ");
        stringBuilder2.append(stringBuilder1);
        stringBuilder2.append("!");
        throw new RuntimeException(stringBuilder2.toString());
      } 
      throw new RuntimeException("Attempting to connect filter not in graph!");
    } 
    throw new IllegalArgumentException("Passing null Filter in connect()!");
  }
  
  public void connect(String paramString1, String paramString2, String paramString3, String paramString4) {
    StringBuilder stringBuilder1;
    Filter filter1 = getFilter(paramString1);
    Filter filter2 = getFilter(paramString3);
    if (filter1 != null) {
      if (filter2 != null) {
        connect(filter1, paramString2, filter2, paramString4);
        return;
      } 
      stringBuilder1 = new StringBuilder();
      stringBuilder1.append("Attempting to connect unknown target filter '");
      stringBuilder1.append(paramString3);
      stringBuilder1.append("'!");
      throw new RuntimeException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder2 = new StringBuilder();
    stringBuilder2.append("Attempting to connect unknown source filter '");
    stringBuilder2.append((String)stringBuilder1);
    stringBuilder2.append("'!");
    throw new RuntimeException(stringBuilder2.toString());
  }
  
  public boolean containsFilter(Filter paramFilter) {
    return this.mFilters.contains(paramFilter);
  }
  
  public void flushFrames() {
    Iterator<Filter> iterator = this.mFilters.iterator();
    while (iterator.hasNext())
      ((Filter)iterator.next()).clearOutputs(); 
  }
  
  public Filter getFilter(String paramString) {
    return this.mNameMap.get(paramString);
  }
  
  public Set<Filter> getFilters() {
    return this.mFilters;
  }
  
  public boolean isReady() {
    return this.mIsReady;
  }
  
  public void setAutoBranchMode(int paramInt) {
    this.mAutoBranchMode = paramInt;
  }
  
  public void setDiscardUnconnectedOutputs(boolean paramBoolean) {
    this.mDiscardUnconnectedOutputs = paramBoolean;
  }
  
  public void setTypeCheckMode(int paramInt) {
    this.mTypeCheckMode = paramInt;
  }
  
  void setupFilters() {
    if (this.mDiscardUnconnectedOutputs)
      discardUnconnectedOutputs(); 
    connectPorts();
    checkConnections();
    runTypeCheck();
  }
  
  public void tearDown(FilterContext paramFilterContext) {
    if (!this.mFilters.isEmpty()) {
      flushFrames();
      Iterator<Filter> iterator = this.mFilters.iterator();
      while (iterator.hasNext())
        ((Filter)iterator.next()).performTearDown(paramFilterContext); 
      this.mFilters.clear();
      this.mNameMap.clear();
      this.mIsReady = false;
    } 
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/FilterGraph.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
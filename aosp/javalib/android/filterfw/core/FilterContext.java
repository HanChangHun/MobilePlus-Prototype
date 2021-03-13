package android.filterfw.core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FilterContext {
  private FrameManager mFrameManager;
  
  private GLEnvironment mGLEnvironment;
  
  private Set<FilterGraph> mGraphs = new HashSet<>();
  
  private HashMap<String, Frame> mStoredFrames = new HashMap<>();
  
  final void addGraph(FilterGraph paramFilterGraph) {
    this.mGraphs.add(paramFilterGraph);
  }
  
  public Frame fetchFrame(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mStoredFrames : Ljava/util/HashMap;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast android/filterfw/core/Frame
    //   13: astore_1
    //   14: aload_1
    //   15: ifnull -> 22
    //   18: aload_1
    //   19: invokevirtual onFrameFetch : ()V
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: areturn
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	26	finally
    //   18	22	26	finally
  }
  
  public FrameManager getFrameManager() {
    return this.mFrameManager;
  }
  
  public GLEnvironment getGLEnvironment() {
    return this.mGLEnvironment;
  }
  
  public void initGLEnvironment(GLEnvironment paramGLEnvironment) {
    if (this.mGLEnvironment == null) {
      this.mGLEnvironment = paramGLEnvironment;
      return;
    } 
    throw new RuntimeException("Attempting to re-initialize GL Environment for FilterContext!");
  }
  
  public void removeFrame(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mStoredFrames : Ljava/util/HashMap;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast android/filterfw/core/Frame
    //   13: astore_2
    //   14: aload_2
    //   15: ifnull -> 32
    //   18: aload_0
    //   19: getfield mStoredFrames : Ljava/util/HashMap;
    //   22: aload_1
    //   23: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   26: pop
    //   27: aload_2
    //   28: invokevirtual release : ()Landroid/filterfw/core/Frame;
    //   31: pop
    //   32: aload_0
    //   33: monitorexit
    //   34: return
    //   35: astore_1
    //   36: aload_0
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	35	finally
    //   18	32	35	finally
  }
  
  public void setFrameManager(FrameManager paramFrameManager) {
    if (paramFrameManager != null) {
      if (paramFrameManager.getContext() == null) {
        this.mFrameManager = paramFrameManager;
        paramFrameManager.setContext(this);
        return;
      } 
      throw new IllegalArgumentException("Attempting to set FrameManager which is already bound to another FilterContext!");
    } 
    throw new NullPointerException("Attempting to set null FrameManager!");
  }
  
  public void storeFrame(String paramString, Frame paramFrame) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual fetchFrame : (Ljava/lang/String;)Landroid/filterfw/core/Frame;
    //   7: astore_3
    //   8: aload_3
    //   9: ifnull -> 17
    //   12: aload_3
    //   13: invokevirtual release : ()Landroid/filterfw/core/Frame;
    //   16: pop
    //   17: aload_2
    //   18: invokevirtual onFrameStore : ()V
    //   21: aload_0
    //   22: getfield mStoredFrames : Ljava/util/HashMap;
    //   25: aload_1
    //   26: aload_2
    //   27: invokevirtual retain : ()Landroid/filterfw/core/Frame;
    //   30: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   33: pop
    //   34: aload_0
    //   35: monitorexit
    //   36: return
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   2	8	37	finally
    //   12	17	37	finally
    //   17	34	37	finally
  }
  
  public void tearDown() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mStoredFrames : Ljava/util/HashMap;
    //   6: invokevirtual values : ()Ljava/util/Collection;
    //   9: invokeinterface iterator : ()Ljava/util/Iterator;
    //   14: astore_1
    //   15: aload_1
    //   16: invokeinterface hasNext : ()Z
    //   21: ifeq -> 40
    //   24: aload_1
    //   25: invokeinterface next : ()Ljava/lang/Object;
    //   30: checkcast android/filterfw/core/Frame
    //   33: invokevirtual release : ()Landroid/filterfw/core/Frame;
    //   36: pop
    //   37: goto -> 15
    //   40: aload_0
    //   41: getfield mStoredFrames : Ljava/util/HashMap;
    //   44: invokevirtual clear : ()V
    //   47: aload_0
    //   48: getfield mGraphs : Ljava/util/Set;
    //   51: invokeinterface iterator : ()Ljava/util/Iterator;
    //   56: astore_1
    //   57: aload_1
    //   58: invokeinterface hasNext : ()Z
    //   63: ifeq -> 82
    //   66: aload_1
    //   67: invokeinterface next : ()Ljava/lang/Object;
    //   72: checkcast android/filterfw/core/FilterGraph
    //   75: aload_0
    //   76: invokevirtual tearDown : (Landroid/filterfw/core/FilterContext;)V
    //   79: goto -> 57
    //   82: aload_0
    //   83: getfield mGraphs : Ljava/util/Set;
    //   86: invokeinterface clear : ()V
    //   91: aload_0
    //   92: getfield mFrameManager : Landroid/filterfw/core/FrameManager;
    //   95: ifnull -> 110
    //   98: aload_0
    //   99: getfield mFrameManager : Landroid/filterfw/core/FrameManager;
    //   102: invokevirtual tearDown : ()V
    //   105: aload_0
    //   106: aconst_null
    //   107: putfield mFrameManager : Landroid/filterfw/core/FrameManager;
    //   110: aload_0
    //   111: getfield mGLEnvironment : Landroid/filterfw/core/GLEnvironment;
    //   114: ifnull -> 129
    //   117: aload_0
    //   118: getfield mGLEnvironment : Landroid/filterfw/core/GLEnvironment;
    //   121: invokevirtual tearDown : ()V
    //   124: aload_0
    //   125: aconst_null
    //   126: putfield mGLEnvironment : Landroid/filterfw/core/GLEnvironment;
    //   129: aload_0
    //   130: monitorexit
    //   131: return
    //   132: astore_1
    //   133: aload_0
    //   134: monitorexit
    //   135: aload_1
    //   136: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	132	finally
    //   15	37	132	finally
    //   40	57	132	finally
    //   57	79	132	finally
    //   82	110	132	finally
    //   110	129	132	finally
  }
  
  public static interface OnFrameReceivedListener {
    void onFrameReceived(Filter param1Filter, Frame param1Frame, Object param1Object);
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/FilterContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
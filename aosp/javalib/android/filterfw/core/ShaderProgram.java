package android.filterfw.core;

import android.filterfw.geometry.Quad;
import android.opengl.GLES20;

public class ShaderProgram extends Program {
  private GLEnvironment mGLEnvironment;
  
  private int mMaxTileSize = 0;
  
  private StopWatchMap mTimer = null;
  
  private int shaderProgramId;
  
  static {
    System.loadLibrary("filterfw");
  }
  
  private ShaderProgram() {}
  
  public ShaderProgram(FilterContext paramFilterContext, String paramString) {
    GLEnvironment gLEnvironment = getGLEnvironment(paramFilterContext);
    this.mGLEnvironment = gLEnvironment;
    allocate(gLEnvironment, null, paramString);
    if (compileAndLink()) {
      setTimer();
      return;
    } 
    throw new RuntimeException("Could not compile and link shader!");
  }
  
  public ShaderProgram(FilterContext paramFilterContext, String paramString1, String paramString2) {
    GLEnvironment gLEnvironment = getGLEnvironment(paramFilterContext);
    this.mGLEnvironment = gLEnvironment;
    allocate(gLEnvironment, paramString1, paramString2);
    if (compileAndLink()) {
      setTimer();
      return;
    } 
    throw new RuntimeException("Could not compile and link shader!");
  }
  
  private ShaderProgram(NativeAllocatorTag paramNativeAllocatorTag) {}
  
  private native boolean allocate(GLEnvironment paramGLEnvironment, String paramString1, String paramString2);
  
  private native boolean beginShaderDrawing();
  
  private native boolean compileAndLink();
  
  public static ShaderProgram createIdentity(FilterContext paramFilterContext) {
    ShaderProgram shaderProgram = nativeCreateIdentity(getGLEnvironment(paramFilterContext));
    shaderProgram.setTimer();
    return shaderProgram;
  }
  
  private native boolean deallocate();
  
  private static GLEnvironment getGLEnvironment(FilterContext paramFilterContext) {
    if (paramFilterContext != null) {
      GLEnvironment gLEnvironment = paramFilterContext.getGLEnvironment();
    } else {
      paramFilterContext = null;
    } 
    if (paramFilterContext != null)
      return (GLEnvironment)paramFilterContext; 
    throw new NullPointerException("Attempting to create ShaderProgram with no GL environment in place!");
  }
  
  private native Object getUniformValue(String paramString);
  
  private static native ShaderProgram nativeCreateIdentity(GLEnvironment paramGLEnvironment);
  
  private native boolean setShaderAttributeValues(String paramString, float[] paramArrayOffloat, int paramInt);
  
  private native boolean setShaderAttributeVertexFrame(String paramString, VertexFrame paramVertexFrame, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean);
  
  private native boolean setShaderBlendEnabled(boolean paramBoolean);
  
  private native boolean setShaderBlendFunc(int paramInt1, int paramInt2);
  
  private native boolean setShaderClearColor(float paramFloat1, float paramFloat2, float paramFloat3);
  
  private native boolean setShaderClearsOutput(boolean paramBoolean);
  
  private native boolean setShaderDrawMode(int paramInt);
  
  private native boolean setShaderTileCounts(int paramInt1, int paramInt2);
  
  private native boolean setShaderVertexCount(int paramInt);
  
  private native boolean setTargetRegion(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8);
  
  private void setTimer() {
    this.mTimer = new StopWatchMap();
  }
  
  private native boolean setUniformValue(String paramString, Object paramObject);
  
  private native boolean shaderProcess(GLFrame[] paramArrayOfGLFrame, GLFrame paramGLFrame);
  
  public void beginDrawing() {
    if (beginShaderDrawing())
      return; 
    throw new RuntimeException("Could not prepare shader-program for drawing!");
  }
  
  protected void finalize() throws Throwable {
    deallocate();
  }
  
  public GLEnvironment getGLEnvironment() {
    return this.mGLEnvironment;
  }
  
  public Object getHostValue(String paramString) {
    return getUniformValue(paramString);
  }
  
  public void process(Frame[] paramArrayOfFrame, Frame paramFrame) {
    if (this.mTimer.LOG_MFF_RUNNING_TIMES) {
      this.mTimer.start("glFinish");
      GLES20.glFinish();
      this.mTimer.stop("glFinish");
    } 
    GLFrame[] arrayOfGLFrame = new GLFrame[paramArrayOfFrame.length];
    int i = 0;
    while (i < paramArrayOfFrame.length) {
      if (paramArrayOfFrame[i] instanceof GLFrame) {
        arrayOfGLFrame[i] = (GLFrame)paramArrayOfFrame[i];
        i++;
        continue;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("ShaderProgram got non-GL frame as input ");
      stringBuilder.append(i);
      stringBuilder.append("!");
      throw new RuntimeException(stringBuilder.toString());
    } 
    if (paramFrame instanceof GLFrame) {
      GLFrame gLFrame = (GLFrame)paramFrame;
      if (this.mMaxTileSize > 0) {
        int j = paramFrame.getFormat().getWidth();
        i = this.mMaxTileSize;
        j = (j + i - 1) / i;
        i = paramFrame.getFormat().getHeight();
        int k = this.mMaxTileSize;
        setShaderTileCounts(j, (i + k - 1) / k);
      } 
      if (shaderProcess(arrayOfGLFrame, gLFrame)) {
        if (this.mTimer.LOG_MFF_RUNNING_TIMES)
          GLES20.glFinish(); 
        return;
      } 
      throw new RuntimeException("Error executing ShaderProgram!");
    } 
    throw new RuntimeException("ShaderProgram got non-GL output frame!");
  }
  
  public void setAttributeValues(String paramString, VertexFrame paramVertexFrame, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
    if (setShaderAttributeVertexFrame(paramString, paramVertexFrame, paramInt1, paramInt2, paramInt3, paramInt4, paramBoolean))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error setting attribute value for attribute '");
    stringBuilder.append(paramString);
    stringBuilder.append("'!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setAttributeValues(String paramString, float[] paramArrayOffloat, int paramInt) {
    if (setShaderAttributeValues(paramString, paramArrayOffloat, paramInt))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Error setting attribute value for attribute '");
    stringBuilder.append(paramString);
    stringBuilder.append("'!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setBlendEnabled(boolean paramBoolean) {
    if (setShaderBlendEnabled(paramBoolean))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not set Blending ");
    stringBuilder.append(paramBoolean);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setBlendFunc(int paramInt1, int paramInt2) {
    if (setShaderBlendFunc(paramInt1, paramInt2))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not set BlendFunc ");
    stringBuilder.append(paramInt1);
    stringBuilder.append(",");
    stringBuilder.append(paramInt2);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setClearColor(float paramFloat1, float paramFloat2, float paramFloat3) {
    if (setShaderClearColor(paramFloat1, paramFloat2, paramFloat3))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not set clear color to ");
    stringBuilder.append(paramFloat1);
    stringBuilder.append(",");
    stringBuilder.append(paramFloat2);
    stringBuilder.append(",");
    stringBuilder.append(paramFloat3);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setClearsOutput(boolean paramBoolean) {
    if (setShaderClearsOutput(paramBoolean))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not set clears-output flag to ");
    stringBuilder.append(paramBoolean);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setDrawMode(int paramInt) {
    if (setShaderDrawMode(paramInt))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not set GL draw-mode to ");
    stringBuilder.append(paramInt);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setHostValue(String paramString, Object paramObject) {
    if (setUniformValue(paramString, paramObject))
      return; 
    paramObject = new StringBuilder();
    paramObject.append("Error setting uniform value for variable '");
    paramObject.append(paramString);
    paramObject.append("'!");
    throw new RuntimeException(paramObject.toString());
  }
  
  public void setMaximumTileSize(int paramInt) {
    this.mMaxTileSize = paramInt;
  }
  
  public void setSourceRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    setSourceRegion(paramFloat1, paramFloat2, paramFloat1 + paramFloat3, paramFloat2, paramFloat1, paramFloat2 + paramFloat4, paramFloat1 + paramFloat3, paramFloat2 + paramFloat4);
  }
  
  public void setSourceRegion(Quad paramQuad) {
    setSourceRegion(paramQuad.p0.x, paramQuad.p0.y, paramQuad.p1.x, paramQuad.p1.y, paramQuad.p2.x, paramQuad.p2.y, paramQuad.p3.x, paramQuad.p3.y);
  }
  
  public native boolean setSourceRegion(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7, float paramFloat8);
  
  public void setTargetRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4) {
    setTargetRegion(paramFloat1, paramFloat2, paramFloat1 + paramFloat3, paramFloat2, paramFloat1, paramFloat2 + paramFloat4, paramFloat1 + paramFloat3, paramFloat2 + paramFloat4);
  }
  
  public void setTargetRegion(Quad paramQuad) {
    setTargetRegion(paramQuad.p0.x, paramQuad.p0.y, paramQuad.p1.x, paramQuad.p1.y, paramQuad.p2.x, paramQuad.p2.y, paramQuad.p3.x, paramQuad.p3.y);
  }
  
  public void setVertexCount(int paramInt) {
    if (setShaderVertexCount(paramInt))
      return; 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Could not set GL vertex count to ");
    stringBuilder.append(paramInt);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/ShaderProgram.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
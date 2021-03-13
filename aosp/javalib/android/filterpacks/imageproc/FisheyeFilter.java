package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.Program;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;

public class FisheyeFilter extends Filter {
  private static final String TAG = "FisheyeFilter";
  
  private static final String mFisheyeShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform vec2 scale;\nuniform float alpha;\nuniform float radius2;\nuniform float factor;\nvarying vec2 v_texcoord;\nvoid main() {\n  const float m_pi_2 = 1.570963;\n  const float min_dist = 0.01;\n  vec2 coord = v_texcoord - vec2(0.5, 0.5);\n  float dist = length(coord * scale);\n  dist = max(dist, min_dist);\n  float radian = m_pi_2 - atan(alpha * sqrt(radius2 - dist * dist), dist);\n  float scalar = radian * factor / dist;\n  vec2 new_coord = coord * scalar + vec2(0.5, 0.5);\n  gl_FragColor = texture2D(tex_sampler_0, new_coord);\n}\n";
  
  private int mHeight = 0;
  
  private Program mProgram;
  
  @GenerateFieldPort(hasDefault = true, name = "scale")
  private float mScale = 0.0F;
  
  private int mTarget = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "tile_size")
  private int mTileSize = 640;
  
  private int mWidth = 0;
  
  public FisheyeFilter(String paramString) {
    super(paramString);
  }
  
  private void updateFrameSize(int paramInt1, int paramInt2) {
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    updateProgramParams();
  }
  
  private void updateProgramParams() {
    float[] arrayOfFloat = new float[2];
    int i = this.mWidth;
    int j = this.mHeight;
    if (i > j) {
      arrayOfFloat[0] = 1.0F;
      arrayOfFloat[1] = j / i;
    } else {
      arrayOfFloat[0] = i / j;
      arrayOfFloat[1] = 1.0F;
    } 
    float f1 = this.mScale * 2.0F + 0.75F;
    float f2 = (arrayOfFloat[0] * arrayOfFloat[0] + arrayOfFloat[1] * arrayOfFloat[1]) * 0.25F;
    float f3 = (float)Math.sqrt(f2);
    float f4 = 1.15F * f3;
    f4 *= f4;
    f3 /= 1.5707964F - (float)Math.atan((f1 / f3 * (float)Math.sqrt((f4 - f2))));
    this.mProgram.setHostValue("scale", arrayOfFloat);
    this.mProgram.setHostValue("radius2", Float.valueOf(f4));
    this.mProgram.setHostValue("factor", Float.valueOf(f3));
    this.mProgram.setHostValue("alpha", Float.valueOf(f1));
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (this.mProgram != null)
      updateProgramParams(); 
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void initProgram(FilterContext paramFilterContext, int paramInt) {
    if (paramInt == 3) {
      ShaderProgram shaderProgram = new ShaderProgram(paramFilterContext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform vec2 scale;\nuniform float alpha;\nuniform float radius2;\nuniform float factor;\nvarying vec2 v_texcoord;\nvoid main() {\n  const float m_pi_2 = 1.570963;\n  const float min_dist = 0.01;\n  vec2 coord = v_texcoord - vec2(0.5, 0.5);\n  float dist = length(coord * scale);\n  dist = max(dist, min_dist);\n  float radian = m_pi_2 - atan(alpha * sqrt(radius2 - dist * dist), dist);\n  float scalar = radian * factor / dist;\n  vec2 new_coord = coord * scalar + vec2(0.5, 0.5);\n  gl_FragColor = texture2D(tex_sampler_0, new_coord);\n}\n");
      shaderProgram.setMaximumTileSize(this.mTileSize);
      this.mProgram = (Program)shaderProgram;
      this.mTarget = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Filter FisheyeFilter does not support frames of target ");
    stringBuilder.append(paramInt);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame1 = pullInput("image");
    FrameFormat frameFormat = frame1.getFormat();
    Frame frame2 = paramFilterContext.getFrameManager().newFrame(frameFormat);
    if (this.mProgram == null || frameFormat.getTarget() != this.mTarget)
      initProgram(paramFilterContext, frameFormat.getTarget()); 
    if (frameFormat.getWidth() != this.mWidth || frameFormat.getHeight() != this.mHeight)
      updateFrameSize(frameFormat.getWidth(), frameFormat.getHeight()); 
    this.mProgram.process(frame1, frame2);
    pushOutput("image", frame2);
    frame2.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/FisheyeFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
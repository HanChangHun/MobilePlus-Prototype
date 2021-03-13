package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.Program;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;
import java.util.Date;
import java.util.Random;

public class BlackWhiteFilter extends Filter {
  @GenerateFieldPort(hasDefault = true, name = "black")
  private float mBlack = 0.0F;
  
  private final String mBlackWhiteShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform vec2 seed;\nuniform float black;\nuniform float scale;\nuniform float stepsize;\nvarying vec2 v_texcoord;\nfloat rand(vec2 loc) {\n  float theta1 = dot(loc, vec2(0.9898, 0.233));\n  float theta2 = dot(loc, vec2(12.0, 78.0));\n  float value = cos(theta1) * sin(theta2) + sin(theta1) * cos(theta2);\n  float temp = mod(197.0 * value, 1.0) + value;\n  float part1 = mod(220.0 * temp, 1.0) + temp;\n  float part2 = value * 0.5453;\n  float part3 = cos(theta1 + theta2) * 0.43758;\n  return fract(part1 + part2 + part3);\n}\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float dither = rand(v_texcoord + seed);\n  vec3 xform = clamp((color.rgb - black) * scale, 0.0, 1.0);\n  vec3 temp = clamp((color.rgb + stepsize - black) * scale, 0.0, 1.0);\n  vec3 new_color = clamp(xform + (temp - xform) * (dither - 0.5), 0.0, 1.0);\n  gl_FragColor = vec4(new_color, color.a);\n}\n";
  
  private Program mProgram;
  
  private Random mRandom = new Random((new Date()).getTime());
  
  private int mTarget = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "tile_size")
  private int mTileSize = 640;
  
  @GenerateFieldPort(hasDefault = true, name = "white")
  private float mWhite = 1.0F;
  
  public BlackWhiteFilter(String paramString) {
    super(paramString);
  }
  
  private void updateParameters() {
    float f1 = this.mBlack;
    float f2 = this.mWhite;
    if (f1 != f2) {
      f2 = 1.0F / (f2 - f1);
    } else {
      f2 = 2000.0F;
    } 
    this.mProgram.setHostValue("black", Float.valueOf(this.mBlack));
    this.mProgram.setHostValue("scale", Float.valueOf(f2));
    this.mProgram.setHostValue("stepsize", Float.valueOf(0.003921569F));
    f1 = this.mRandom.nextFloat();
    f2 = this.mRandom.nextFloat();
    this.mProgram.setHostValue("seed", new float[] { f1, f2 });
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (this.mProgram != null)
      updateParameters(); 
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void initProgram(FilterContext paramFilterContext, int paramInt) {
    if (paramInt == 3) {
      ShaderProgram shaderProgram = new ShaderProgram(paramFilterContext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform vec2 seed;\nuniform float black;\nuniform float scale;\nuniform float stepsize;\nvarying vec2 v_texcoord;\nfloat rand(vec2 loc) {\n  float theta1 = dot(loc, vec2(0.9898, 0.233));\n  float theta2 = dot(loc, vec2(12.0, 78.0));\n  float value = cos(theta1) * sin(theta2) + sin(theta1) * cos(theta2);\n  float temp = mod(197.0 * value, 1.0) + value;\n  float part1 = mod(220.0 * temp, 1.0) + temp;\n  float part2 = value * 0.5453;\n  float part3 = cos(theta1 + theta2) * 0.43758;\n  return fract(part1 + part2 + part3);\n}\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float dither = rand(v_texcoord + seed);\n  vec3 xform = clamp((color.rgb - black) * scale, 0.0, 1.0);\n  vec3 temp = clamp((color.rgb + stepsize - black) * scale, 0.0, 1.0);\n  vec3 new_color = clamp(xform + (temp - xform) * (dither - 0.5), 0.0, 1.0);\n  gl_FragColor = vec4(new_color, color.a);\n}\n");
      shaderProgram.setMaximumTileSize(this.mTileSize);
      this.mProgram = (Program)shaderProgram;
      updateParameters();
      this.mTarget = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Filter Sharpen does not support frames of target ");
    stringBuilder.append(paramInt);
    stringBuilder.append("!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void process(FilterContext paramFilterContext) {
    Frame frame2 = pullInput("image");
    FrameFormat frameFormat = frame2.getFormat();
    if (this.mProgram == null || frameFormat.getTarget() != this.mTarget)
      initProgram(paramFilterContext, frameFormat.getTarget()); 
    Frame frame1 = paramFilterContext.getFrameManager().newFrame(frameFormat);
    this.mProgram.process(frame2, frame1);
    pushOutput("image", frame1);
    frame1.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/BlackWhiteFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
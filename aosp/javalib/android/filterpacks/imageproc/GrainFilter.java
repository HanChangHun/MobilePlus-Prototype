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

public class GrainFilter extends Filter {
  private static final int RAND_THRESHOLD = 128;
  
  private Program mGrainProgram;
  
  private final String mGrainShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform float scale;\nuniform float stepX;\nuniform float stepY;\nvarying vec2 v_texcoord;\nvoid main() {\n  float noise = texture2D(tex_sampler_1, v_texcoord + vec2(-stepX, -stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, v_texcoord + vec2(-stepX, stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, v_texcoord + vec2(stepX, -stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, v_texcoord + vec2(stepX, stepY)).r * 0.224;\n  noise += 0.4448;\n  noise *= scale;\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float energy = 0.33333 * color.r + 0.33333 * color.g + 0.33333 * color.b;\n  float mask = (1.0 - sqrt(energy));\n  float weight = 1.0 - 1.333 * mask * noise;\n  gl_FragColor = vec4(color.rgb * weight, color.a);\n}\n";
  
  private int mHeight = 0;
  
  private Program mNoiseProgram;
  
  private final String mNoiseShader = "precision mediump float;\nuniform vec2 seed;\nvarying vec2 v_texcoord;\nfloat rand(vec2 loc) {\n  float theta1 = dot(loc, vec2(0.9898, 0.233));\n  float theta2 = dot(loc, vec2(12.0, 78.0));\n  float value = cos(theta1) * sin(theta2) + sin(theta1) * cos(theta2);\n  float temp = mod(197.0 * value, 1.0) + value;\n  float part1 = mod(220.0 * temp, 1.0) + temp;\n  float part2 = value * 0.5453;\n  float part3 = cos(theta1 + theta2) * 0.43758;\n  return fract(part1 + part2 + part3);\n}\nvoid main() {\n  gl_FragColor = vec4(rand(v_texcoord + seed), 0.0, 0.0, 1.0);\n}\n";
  
  private Random mRandom = new Random((new Date()).getTime());
  
  @GenerateFieldPort(hasDefault = true, name = "strength")
  private float mScale = 0.0F;
  
  private int mTarget = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "tile_size")
  private int mTileSize = 640;
  
  private int mWidth = 0;
  
  public GrainFilter(String paramString) {
    super(paramString);
  }
  
  private void updateFrameSize(int paramInt1, int paramInt2) {
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
    Program program = this.mGrainProgram;
    if (program != null) {
      program.setHostValue("stepX", Float.valueOf(0.5F / paramInt1));
      this.mGrainProgram.setHostValue("stepY", Float.valueOf(0.5F / this.mHeight));
      updateParameters();
    } 
  }
  
  private void updateParameters() {
    float f1 = this.mRandom.nextFloat();
    float f2 = this.mRandom.nextFloat();
    this.mNoiseProgram.setHostValue("seed", new float[] { f1, f2 });
    this.mGrainProgram.setHostValue("scale", Float.valueOf(this.mScale));
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (this.mGrainProgram != null && this.mNoiseProgram != null)
      updateParameters(); 
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void initProgram(FilterContext paramFilterContext, int paramInt) {
    if (paramInt == 3) {
      ShaderProgram shaderProgram2 = new ShaderProgram(paramFilterContext, "precision mediump float;\nuniform vec2 seed;\nvarying vec2 v_texcoord;\nfloat rand(vec2 loc) {\n  float theta1 = dot(loc, vec2(0.9898, 0.233));\n  float theta2 = dot(loc, vec2(12.0, 78.0));\n  float value = cos(theta1) * sin(theta2) + sin(theta1) * cos(theta2);\n  float temp = mod(197.0 * value, 1.0) + value;\n  float part1 = mod(220.0 * temp, 1.0) + temp;\n  float part2 = value * 0.5453;\n  float part3 = cos(theta1 + theta2) * 0.43758;\n  return fract(part1 + part2 + part3);\n}\nvoid main() {\n  gl_FragColor = vec4(rand(v_texcoord + seed), 0.0, 0.0, 1.0);\n}\n");
      shaderProgram2.setMaximumTileSize(this.mTileSize);
      this.mNoiseProgram = (Program)shaderProgram2;
      ShaderProgram shaderProgram1 = new ShaderProgram(paramFilterContext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform sampler2D tex_sampler_1;\nuniform float scale;\nuniform float stepX;\nuniform float stepY;\nvarying vec2 v_texcoord;\nvoid main() {\n  float noise = texture2D(tex_sampler_1, v_texcoord + vec2(-stepX, -stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, v_texcoord + vec2(-stepX, stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, v_texcoord + vec2(stepX, -stepY)).r * 0.224;\n  noise += texture2D(tex_sampler_1, v_texcoord + vec2(stepX, stepY)).r * 0.224;\n  noise += 0.4448;\n  noise *= scale;\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float energy = 0.33333 * color.r + 0.33333 * color.g + 0.33333 * color.b;\n  float mask = (1.0 - sqrt(energy));\n  float weight = 1.0 - 1.333 * mask * noise;\n  gl_FragColor = vec4(color.rgb * weight, color.a);\n}\n");
      shaderProgram1.setMaximumTileSize(this.mTileSize);
      this.mGrainProgram = (Program)shaderProgram1;
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
    Frame frame1 = pullInput("image");
    FrameFormat frameFormat = frame1.getFormat();
    ImageFormat.create(frameFormat.getWidth() / 2, frameFormat.getHeight() / 2, 3, 3);
    Frame frame2 = paramFilterContext.getFrameManager().newFrame(frameFormat);
    Frame frame3 = paramFilterContext.getFrameManager().newFrame(frameFormat);
    if (this.mNoiseProgram == null || this.mGrainProgram == null || frameFormat.getTarget() != this.mTarget) {
      initProgram(paramFilterContext, frameFormat.getTarget());
      updateParameters();
    } 
    if (frameFormat.getWidth() != this.mWidth || frameFormat.getHeight() != this.mHeight)
      updateFrameSize(frameFormat.getWidth(), frameFormat.getHeight()); 
    this.mNoiseProgram.process(new Frame[0], frame2);
    this.mGrainProgram.process(new Frame[] { frame1, frame2 }, frame3);
    pushOutput("image", frame3);
    frame3.release();
    frame2.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/GrainFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
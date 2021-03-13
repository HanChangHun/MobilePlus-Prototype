package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.Program;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;

public class SaturateFilter extends Filter {
  private Program mBenProgram;
  
  private final String mBenSaturateShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform float scale;\nuniform float shift;\nuniform vec3 weights;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float kv = dot(color.rgb, weights) + shift;\n  vec3 new_color = scale * color.rgb + (1.0 - scale) * kv;\n  gl_FragColor = vec4(new_color, color.a);\n}\n";
  
  private Program mHerfProgram;
  
  private final String mHerfSaturateShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform vec3 weights;\nuniform vec3 exponents;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float de = dot(color.rgb, weights);\n  float inv_de = 1.0 / de;\n  vec3 new_color = de * pow(color.rgb * inv_de, exponents);\n  float max_color = max(max(max(new_color.r, new_color.g), new_color.b), 1.0);\n  gl_FragColor = vec4(new_color / max_color, color.a);\n}\n";
  
  @GenerateFieldPort(hasDefault = true, name = "scale")
  private float mScale = 0.0F;
  
  private int mTarget = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "tile_size")
  private int mTileSize = 640;
  
  public SaturateFilter(String paramString) {
    super(paramString);
  }
  
  private void initParameters() {
    float[] arrayOfFloat = new float[3];
    arrayOfFloat[0] = 0.25F;
    arrayOfFloat[1] = 0.625F;
    arrayOfFloat[2] = 0.125F;
    this.mBenProgram.setHostValue("weights", arrayOfFloat);
    this.mBenProgram.setHostValue("shift", Float.valueOf(0.003921569F));
    this.mHerfProgram.setHostValue("weights", arrayOfFloat);
    updateParameters();
  }
  
  private void updateParameters() {
    float f = this.mScale;
    if (f > 0.0F) {
      this.mHerfProgram.setHostValue("exponents", new float[] { 0.9F * f + 1.0F, 2.1F * f + 1.0F, f * 2.7F + 1.0F });
    } else {
      this.mBenProgram.setHostValue("scale", Float.valueOf(f + 1.0F));
    } 
  }
  
  public void fieldPortValueUpdated(String paramString, FilterContext paramFilterContext) {
    if (this.mBenProgram != null && this.mHerfProgram != null)
      updateParameters(); 
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void initProgram(FilterContext paramFilterContext, int paramInt) {
    if (paramInt == 3) {
      ShaderProgram shaderProgram2 = new ShaderProgram(paramFilterContext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform float scale;\nuniform float shift;\nuniform vec3 weights;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float kv = dot(color.rgb, weights) + shift;\n  vec3 new_color = scale * color.rgb + (1.0 - scale) * kv;\n  gl_FragColor = vec4(new_color, color.a);\n}\n");
      shaderProgram2.setMaximumTileSize(this.mTileSize);
      this.mBenProgram = (Program)shaderProgram2;
      ShaderProgram shaderProgram1 = new ShaderProgram(paramFilterContext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform vec3 weights;\nuniform vec3 exponents;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float de = dot(color.rgb, weights);\n  float inv_de = 1.0 / de;\n  vec3 new_color = de * pow(color.rgb * inv_de, exponents);\n  float max_color = max(max(max(new_color.r, new_color.g), new_color.b), 1.0);\n  gl_FragColor = vec4(new_color / max_color, color.a);\n}\n");
      shaderProgram1.setMaximumTileSize(this.mTileSize);
      this.mHerfProgram = (Program)shaderProgram1;
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
    if (this.mBenProgram == null || frameFormat.getTarget() != this.mTarget) {
      initProgram(paramFilterContext, frameFormat.getTarget());
      initParameters();
    } 
    Frame frame1 = paramFilterContext.getFrameManager().newFrame(frameFormat);
    if (this.mScale > 0.0F) {
      this.mHerfProgram.process(frame2, frame1);
    } else {
      this.mBenProgram.process(frame2, frame1);
    } 
    pushOutput("image", frame1);
    frame1.release();
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/SaturateFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
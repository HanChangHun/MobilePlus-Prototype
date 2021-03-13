package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.Program;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;

public class ColorTemperatureFilter extends Filter {
  private final String mColorTemperatureShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform float scale;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  vec3 new_color = color.rgb;\n  new_color.r = color.r + color.r * ( 1.0 - color.r) * scale;\n  new_color.b = color.b - color.b * ( 1.0 - color.b) * scale;\n  if (scale > 0.0) { \n    new_color.g = color.g + color.g * ( 1.0 - color.g) * scale * 0.25;\n  }\n  float max_value = max(new_color.r, max(new_color.g, new_color.b));\n  if (max_value > 1.0) { \n     new_color /= max_value;\n  } \n  gl_FragColor = vec4(new_color, color.a);\n}\n";
  
  private Program mProgram;
  
  @GenerateFieldPort(hasDefault = true, name = "scale")
  private float mScale = 0.5F;
  
  private int mTarget = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "tile_size")
  private int mTileSize = 640;
  
  public ColorTemperatureFilter(String paramString) {
    super(paramString);
  }
  
  private void updateParameters() {
    this.mProgram.setHostValue("scale", Float.valueOf(this.mScale * 2.0F - 1.0F));
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
      ShaderProgram shaderProgram = new ShaderProgram(paramFilterContext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nuniform float scale;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  vec3 new_color = color.rgb;\n  new_color.r = color.r + color.r * ( 1.0 - color.r) * scale;\n  new_color.b = color.b - color.b * ( 1.0 - color.b) * scale;\n  if (scale > 0.0) { \n    new_color.g = color.g + color.g * ( 1.0 - color.g) * scale * 0.25;\n  }\n  float max_value = max(new_color.r, max(new_color.g, new_color.b));\n  if (max_value > 1.0) { \n     new_color /= max_value;\n  } \n  gl_FragColor = vec4(new_color, color.a);\n}\n");
      shaderProgram.setMaximumTileSize(this.mTileSize);
      this.mProgram = (Program)shaderProgram;
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
    if (this.mProgram == null || frameFormat.getTarget() != this.mTarget) {
      initProgram(paramFilterContext, frameFormat.getTarget());
      updateParameters();
    } 
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


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/ColorTemperatureFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
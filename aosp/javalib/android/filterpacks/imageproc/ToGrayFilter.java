package android.filterpacks.imageproc;

import android.filterfw.core.FilterContext;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.MutableFrameFormat;
import android.filterfw.core.Program;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;

public class ToGrayFilter extends SimpleImageFilter {
  private static final String mColorToGray4Shader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float y = dot(color, vec4(0.299, 0.587, 0.114, 0));\n  gl_FragColor = vec4(y, y, y, color.a);\n}\n";
  
  @GenerateFieldPort(hasDefault = true, name = "invertSource")
  private boolean mInvertSource = false;
  
  private MutableFrameFormat mOutputFormat;
  
  @GenerateFieldPort(hasDefault = true, name = "tile_size")
  private int mTileSize = 640;
  
  public ToGrayFilter(String paramString) {
    super(paramString, (String)null);
  }
  
  protected Program getNativeProgram(FilterContext paramFilterContext) {
    throw new RuntimeException("Native toGray not implemented yet!");
  }
  
  protected Program getShaderProgram(FilterContext paramFilterContext) {
    int i = getInputFormat("image").getBytesPerSample();
    if (i == 4) {
      ShaderProgram shaderProgram = new ShaderProgram(paramFilterContext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  float y = dot(color, vec4(0.299, 0.587, 0.114, 0));\n  gl_FragColor = vec4(y, y, y, color.a);\n}\n");
      shaderProgram.setMaximumTileSize(this.mTileSize);
      if (this.mInvertSource)
        shaderProgram.setSourceRect(0.0F, 1.0F, 1.0F, -1.0F); 
      return (Program)shaderProgram;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Unsupported GL input channels: ");
    stringBuilder.append(i);
    stringBuilder.append("! Channels must be 4!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public void setupPorts() {
    addMaskedInputPort("image", (FrameFormat)ImageFormat.create(3, 3));
    addOutputBasedOnInput("image", "image");
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/ToGrayFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
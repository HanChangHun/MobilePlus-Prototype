package android.filterpacks.imageproc;

import android.filterfw.core.Filter;
import android.filterfw.core.FilterContext;
import android.filterfw.core.Frame;
import android.filterfw.core.FrameFormat;
import android.filterfw.core.GenerateFieldPort;
import android.filterfw.core.Program;
import android.filterfw.core.ShaderProgram;
import android.filterfw.format.ImageFormat;

public class CrossProcessFilter extends Filter {
  private final String mCrossProcessShader = "precision mediump float;\nuniform sampler2D tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  vec3 ncolor = vec3(0.0, 0.0, 0.0);\n  float value;\n  if (color.r < 0.5) {\n    value = color.r;\n  } else {\n    value = 1.0 - color.r;\n  }\n  float red = 4.0 * value * value * value;\n  if (color.r < 0.5) {\n    ncolor.r = red;\n  } else {\n    ncolor.r = 1.0 - red;\n  }\n  if (color.g < 0.5) {\n    value = color.g;\n  } else {\n    value = 1.0 - color.g;\n  }\n  float green = 2.0 * value * value;\n  if (color.g < 0.5) {\n    ncolor.g = green;\n  } else {\n    ncolor.g = 1.0 - green;\n  }\n  ncolor.b = color.b * 0.5 + 0.25;\n  gl_FragColor = vec4(ncolor.rgb, color.a);\n}\n";
  
  private Program mProgram;
  
  private int mTarget = 0;
  
  @GenerateFieldPort(hasDefault = true, name = "tile_size")
  private int mTileSize = 640;
  
  public CrossProcessFilter(String paramString) {
    super(paramString);
  }
  
  public FrameFormat getOutputFormat(String paramString, FrameFormat paramFrameFormat) {
    return paramFrameFormat;
  }
  
  public void initProgram(FilterContext paramFilterContext, int paramInt) {
    if (paramInt == 3) {
      ShaderProgram shaderProgram = new ShaderProgram(paramFilterContext, "precision mediump float;\nuniform sampler2D tex_sampler_0;\nvarying vec2 v_texcoord;\nvoid main() {\n  vec4 color = texture2D(tex_sampler_0, v_texcoord);\n  vec3 ncolor = vec3(0.0, 0.0, 0.0);\n  float value;\n  if (color.r < 0.5) {\n    value = color.r;\n  } else {\n    value = 1.0 - color.r;\n  }\n  float red = 4.0 * value * value * value;\n  if (color.r < 0.5) {\n    ncolor.r = red;\n  } else {\n    ncolor.r = 1.0 - red;\n  }\n  if (color.g < 0.5) {\n    value = color.g;\n  } else {\n    value = 1.0 - color.g;\n  }\n  float green = 2.0 * value * value;\n  if (color.g < 0.5) {\n    ncolor.g = green;\n  } else {\n    ncolor.g = 1.0 - green;\n  }\n  ncolor.b = color.b * 0.5 + 0.25;\n  gl_FragColor = vec4(ncolor.rgb, color.a);\n}\n");
      shaderProgram.setMaximumTileSize(this.mTileSize);
      this.mProgram = (Program)shaderProgram;
      this.mTarget = paramInt;
      return;
    } 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Filter CrossProcess does not support frames of target ");
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


/* Location:              /home/chun/Desktop/temp/!/android/filterpacks/imageproc/CrossProcessFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
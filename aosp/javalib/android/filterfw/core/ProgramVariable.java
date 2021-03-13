package android.filterfw.core;

public class ProgramVariable {
  private Program mProgram;
  
  private String mVarName;
  
  public ProgramVariable(Program paramProgram, String paramString) {
    this.mProgram = paramProgram;
    this.mVarName = paramString;
  }
  
  public Program getProgram() {
    return this.mProgram;
  }
  
  public Object getValue() {
    Program program = this.mProgram;
    if (program != null)
      return program.getHostValue(this.mVarName); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Attempting to get program variable '");
    stringBuilder.append(this.mVarName);
    stringBuilder.append("' but the program is null!");
    throw new RuntimeException(stringBuilder.toString());
  }
  
  public String getVariableName() {
    return this.mVarName;
  }
  
  public void setValue(Object paramObject) {
    Program program = this.mProgram;
    if (program != null) {
      program.setHostValue(this.mVarName, paramObject);
      return;
    } 
    paramObject = new StringBuilder();
    paramObject.append("Attempting to set program variable '");
    paramObject.append(this.mVarName);
    paramObject.append("' but the program is null!");
    throw new RuntimeException(paramObject.toString());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/filterfw/core/ProgramVariable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
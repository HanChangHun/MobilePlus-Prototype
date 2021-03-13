package android.hardware.camera2.params;

final class StreamCombinationTemplate {
  public String mDescription;
  
  public MandatoryStreamCombination.ReprocessType mReprocessType;
  
  public MandatoryStreamCombination.StreamTemplate[] mStreamTemplates;
  
  public StreamCombinationTemplate(MandatoryStreamCombination.StreamTemplate[] paramArrayOfStreamTemplate, String paramString) {
    this(paramArrayOfStreamTemplate, paramString, MandatoryStreamCombination.ReprocessType.NONE);
  }
  
  public StreamCombinationTemplate(MandatoryStreamCombination.StreamTemplate[] paramArrayOfStreamTemplate, String paramString, MandatoryStreamCombination.ReprocessType paramReprocessType) {
    this.mStreamTemplates = paramArrayOfStreamTemplate;
    this.mReprocessType = paramReprocessType;
    this.mDescription = paramString;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/params/MandatoryStreamCombination$StreamCombinationTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
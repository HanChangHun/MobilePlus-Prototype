package android.hardware.display;

import android.os.Parcel;
import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;

interface BrightnessCorrectionImplementation {
  float apply(float paramFloat);
  
  void saveToXml(XmlSerializer paramXmlSerializer) throws IOException;
  
  String toString();
  
  void writeToParcel(Parcel paramParcel);
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/display/BrightnessCorrection$BrightnessCorrectionImplementation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
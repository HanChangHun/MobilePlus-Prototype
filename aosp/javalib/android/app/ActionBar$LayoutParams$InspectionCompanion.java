package android.app;

import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;

public final class ActionBar$LayoutParams$InspectionCompanion implements InspectionCompanion<ActionBar.LayoutParams> {
  private int mLayout_gravityId;
  
  private boolean mPropertiesMapped = false;
  
  public void mapProperties(PropertyMapper paramPropertyMapper) {
    this.mLayout_gravityId = paramPropertyMapper.mapGravity("layout_gravity", 16842931);
    this.mPropertiesMapped = true;
  }
  
  public void readProperties(ActionBar.LayoutParams paramLayoutParams, PropertyReader paramPropertyReader) {
    if (this.mPropertiesMapped) {
      paramPropertyReader.readGravity(this.mLayout_gravityId, paramLayoutParams.gravity);
      return;
    } 
    throw new InspectionCompanion.UninitializedPropertyMapException();
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/ActionBar$LayoutParams$InspectionCompanion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
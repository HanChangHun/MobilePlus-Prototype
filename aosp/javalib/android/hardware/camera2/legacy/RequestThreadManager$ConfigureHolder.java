package android.hardware.camera2.legacy;

import android.os.ConditionVariable;
import android.util.Pair;
import android.util.Size;
import android.view.Surface;
import java.util.Collection;

class ConfigureHolder {
  public final ConditionVariable condition;
  
  public final Collection<Pair<Surface, Size>> surfaces;
  
  public ConfigureHolder(ConditionVariable paramConditionVariable, Collection<Pair<Surface, Size>> paramCollection) {
    this.condition = paramConditionVariable;
    this.surfaces = paramCollection;
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/hardware/camera2/legacy/RequestThreadManager$ConfigureHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
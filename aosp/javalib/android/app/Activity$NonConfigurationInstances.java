package android.app;

import android.util.ArrayMap;
import java.util.HashMap;

final class NonConfigurationInstances {
  Object activity;
  
  HashMap<String, Object> children;
  
  FragmentManagerNonConfig fragments;
  
  ArrayMap<String, LoaderManager> loaders;
  
  VoiceInteractor voiceInteractor;
}


/* Location:              /home/chun/Desktop/temp/!/android/app/Activity$NonConfigurationInstances.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
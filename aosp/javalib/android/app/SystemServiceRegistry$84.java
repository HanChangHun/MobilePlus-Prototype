package android.app;

import android.media.midi.IMidiManager;
import android.media.midi.MidiManager;
import android.os.ServiceManager;

class null extends SystemServiceRegistry.CachedServiceFetcher<MidiManager> {
  public MidiManager createService(ContextImpl paramContextImpl) throws ServiceManager.ServiceNotFoundException {
    return new MidiManager(IMidiManager.Stub.asInterface(ServiceManager.getServiceOrThrow("midi")));
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$84.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
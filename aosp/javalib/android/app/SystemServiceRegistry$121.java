package android.app;

class null extends SystemServiceRegistry.CachedServiceFetcher<TServiceClass> {
  public TServiceClass createService(ContextImpl paramContextImpl) {
    return serviceProducer.createService(paramContextImpl.getOuterContext());
  }
}


/* Location:              /home/chun/Desktop/temp/!/android/app/SystemServiceRegistry$121.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
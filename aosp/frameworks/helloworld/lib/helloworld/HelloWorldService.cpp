#include <binder/IServiceManager.h>
#include <binder/IPCThreadState.h>
#include <helloworld/BnHelloWorldService.h>
#include <helloworld/HelloWorldService.h>
#include <utils/Log.h>

namespace android {

void HelloWorldService::instantiate() {
   defaultServiceManager()->addService(
   String16("android.helloworld.IHelloWorldService"), new HelloWorldService());
}

status_t HelloWorldService::helloWorld(const char* str) {
   ALOG(LOG_INFO, LOG_TAG, "%s\n", str);
   printf("%s\n", str);
   return NO_ERROR;
}

HelloWorldService::HelloWorldService(){
   ALOG(LOG_INFO, LOG_TAG, "HelloWorldService is created");
}

HelloWorldService::~HelloWorldService(){
   ALOG(LOG_INFO, LOG_TAG, "HelloWorldService is destroyed");
}
// ------------------------------------------------------------

status_t HelloWorldService::onTransact(
      uint32_t code, const Parcel& data, Parcel* reply, uint32_t flags)
{
   return BnHelloWorldService::onTransact(code, data, reply, flags);
}
// ------------------------------------------------------------

}; // namespace android

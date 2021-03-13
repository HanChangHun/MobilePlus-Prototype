#define LOG_TAG "BpHelloWorldService"

#include <binder/Parcel.h>
#include <helloworld/BpHelloWorldService.h>

namespace android{

status_t BpHelloWorldService::helloWorld(const char *str) {
   Parcel data, reply;
   data.writeInterfaceToken(
      IHelloWorldService::getInterfaceDescriptor());
   data.writeCString(str);
   status_t status = remote()->transact(HW_HELLOWORLD, data, &reply);
   if (status != NO_ERROR) {
      ALOG(LOG_ERROR, LOG_TAG, "print helloworld error: %s", strerror(-status));
   } else {
      status = reply.readInt32();
   }
   return status;
}

BpHelloWorldService::BpHelloWorldService (const sp<IBinder>& impl)
   : BpInterface<IHelloWorldService>(impl)
{}

}; // namespace android

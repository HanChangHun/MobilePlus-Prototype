#pragma once

#include <binder/Parcel.h>
#include <helloworld/BnHelloWorldService.h>
#include <utils/Log.h>

namespace android {
class HelloWorldService : public BnHelloWorldService
{
public:
   static void instantiate();
   virtual status_t helloWorld(const char *str);
   virtual status_t onTransact(
               uint32_t code,
               const Parcel& data,
               Parcel* reply,
               uint32_t flags);
private:
   HelloWorldService();
   virtual ~HelloWorldService();
};
};// namespace android

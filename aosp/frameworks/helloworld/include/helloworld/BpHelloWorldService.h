#pragma once

#include <binder/Parcel.h>
#include <helloworld/IHelloWorldService.h>

namespace android{
class BpHelloWorldService: public BpInterface<IHelloWorldService>
{
public:
   BpHelloWorldService (const sp<IBinder>& impl);
   virtual status_t helloWorld(const char *str);
};

}; // namespace android
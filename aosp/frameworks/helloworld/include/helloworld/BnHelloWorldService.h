#pragma once

#include <binder/Parcel.h>

#include <helloworld/IHelloWorldService.h>

namespace android {
class BnHelloWorldService : public BnInterface<IHelloWorldService>
{
   public:
   virtual status_t onTransact( uint32_t code,
                  const Parcel& data,
                  Parcel* reply,
                  uint32_t flags = 0);
};
}; // namespace android

#pragma once

#include <binder/IInterface.h>

namespace android {

enum {
HW_HELLOWORLD = IBinder::FIRST_CALL_TRANSACTION,
};

class IHelloWorldService: public IInterface {

   public:
   DECLARE_META_INTERFACE(HelloWorldService);
   virtual status_t helloWorld(const char *str)=0;
};
}; // namespace android

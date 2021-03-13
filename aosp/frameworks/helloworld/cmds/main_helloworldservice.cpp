#define LOG_TAG "main_helloworldservice"

#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>
#include <utils/Log.h>

#include <helloworld/HelloWorldService.h>

using namespace android;

int main(int argc, char *argv[])
{
   printf("Hello?");
   HelloWorldService::instantiate();
   ProcessState::self()->startThreadPool();
   ALOG(LOG_INFO, LOG_TAG, "HelloWorldService is starting now");
   IPCThreadState::self()->joinThreadPool();
   return 0;
}
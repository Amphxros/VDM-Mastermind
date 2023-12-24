#include <jni.h>
#include <string>

#ifndef NATIVE_LIB_SHA_H
#define NATIVE_LIB_SHA_H
extern "C"
JNIEXPORT jstring JNICALL
Java_com_mastermind_androidengine_src_main_cpp_SHA(JNIEnv *env, jobject thiz, jstring file);

#endif
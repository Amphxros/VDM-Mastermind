#include <jni.h>
#include <string>

#ifndef NATIVE_LIB_SHA_H
#define NATIVE_LIB_SHA_H

extern "C"
JNIEXPORT jstring JNICALL
Java_mastermind_androidengine_AndroidFileManager_Hash(JNIEnv *env, jclass clazz,
                                                              jstring data);
#endif
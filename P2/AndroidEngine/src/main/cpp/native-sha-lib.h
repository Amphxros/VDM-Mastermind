#include <jni.h>
#include <string>

#ifndef NATIVE_LIB_SHA_H
#define NATIVE_LIB_SHA_H

// Declara una funcion como externa a C para implementarla en la ruta especificada
// JNIenv contiene la interfaz a la maquina virtual de java
extern "C"
JNIEXPORT jstring JNICALL
Java_mastermind_androidengine_AndroidFileManager_Hash(JNIEnv *env, jclass clazz,
                                                              jstring data);
#endif
#ifndef MASTERMIND_JNI_SHA_H
#define MASTERMIND_JNI_SHA_H

#include <jni.h>
#include <string>

class SHA {

    // Definition in external class of the java using JNI
    extern "C"
    JNIEXPORT jstring JNICALL
    // JNI requires of the package route and provides access by the JNIEnv object
    Java_com_mastermind_androidengine_src_main_cpp_SHA(JNIEnv *env, jobject aux, jstring name){

        // the class MUST open a file, read it and parse the content encrypted or no to the Java class

        // compute the result and convert it to jint before passing back to Java
        jint result = static_cast<jint>(computeFibonacci(n));

        // construct an instance of FibonacciResult object defined in Java code
        jclass resultClass = env->FindClass("com/techyourchance/android/ndk/FibonacciResult");
        jmethodID constructor = env->GetMethodID(resultClass, "<init>", "(II)V");
        jobject resultObj = env->NewObject(resultClass, constructor, n, result);

        return resultObj;
    };
};

#endif //MASTERMIND_SHA_H

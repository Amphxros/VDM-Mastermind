#include "picosha2.h"
#include "native-sha-lib.h"
using namespace std;
using namespace picosha2;

extern "C" JNIEXPORT jstring JNICALL    // Allows for the code to be called from the java side
// The JNIEnv variable contains the parameters of the java side
Java_mastermind_androidengine_AndroidFileManager_Hash(JNIEnv *env, jclass clazz,
                                                              jstring data) {

    // Convert jstring to const char*
    const char *convertedValue = env->GetStringUTFChars(data, nullptr);

    // Generate SHA-256 from the input string
    vector<unsigned char> hash(k_digest_size);
    picosha2::hash256(convertedValue, convertedValue + strlen(convertedValue), hash.begin(), hash.end());

    // Release the JNI string
    env->ReleaseStringUTFChars(data, convertedValue);

    // Convert the hash to a hex string
    string hex_str = picosha2::bytes_to_hex_string(hash.begin(), hash.end());

    // Return the result as a Java string
    return env->NewStringUTF(hex_str.c_str());
}
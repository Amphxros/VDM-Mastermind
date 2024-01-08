#include "picosha2.h"
#include "native-sha-lib.h"
using namespace std;
using namespace picosha2;

extern "C"
JNIEXPORT jstring JNICALL
Java_mastermind_androidengine_AndroidFileManager_Hash(JNIEnv *env, jclass clazz,
                                                              jstring data) {

    // Convierte Jstring de entrada a const char*
    const char *convertedValue = env->GetStringUTFChars(data, nullptr);

    // Genera SHA-256 del string de entrada (Toda la informacion del save)
    vector<unsigned char> hash(k_digest_size);
    picosha2::hash256(convertedValue, convertedValue + strlen(convertedValue), hash.begin(), hash.end());

    // Hay que liberar la memoria manualmente, esto no es Java
    env->ReleaseStringUTFChars(data, convertedValue);

    // Convierte el SHA a String hexadecimal
    string hex_str = picosha2::bytes_to_hex_string(hash.begin(), hash.end());

    // Lo devuelve como un string de Java
    return env->NewStringUTF(hex_str.c_str());
}
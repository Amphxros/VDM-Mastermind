cmake_minimum_required(VERSION 3.18.1)

add_library(
        native-sha-lib
        SHARED  #shared object (.so)
)

set(NATIVE_SHA_DIR src/main/cpp)

# cpp files
target_sources(
        native-sha-lib
        PRIVATE
        ${NATIVE_SRC_DIR}/native-sha-lib.cpp
)

# header files
target_include_directories(
        native-sha-lib
        PRIVATE
        ${NATIVE_SRC_DIR}/SHA/
        ${NATIVE_SRC_DIR}/native-sha-lib/
)
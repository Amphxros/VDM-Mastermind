cmake_minimum_required(VERSION 3.18.1)

project(nativeSHA)

# includes the library in the project
add_library(
        native-sha-lib
        SHARED
        src/main/cpp/native-sha-lib.cpp
)

# Headers
target_include_directories(
        native-sha-lib
        PRIVATE
        src/main/cpp/SHA
        src/main/cpp/native-sha-lib
)
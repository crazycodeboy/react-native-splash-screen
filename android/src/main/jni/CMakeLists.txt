cmake_minimum_required(VERSION 3.13)
set(CMAKE_VERBOSE_MAKEFILE ON)

set(LIB_LITERAL rnsplashscreen)
set(LIB_TARGET_NAME react_codegen_${LIB_LITERAL})

set(LIB_ANDROID_DIR ${CMAKE_CURRENT_SOURCE_DIR}/../../..)
set(LIB_ANDROID_GENERATED_JNI_DIR ${LIB_ANDROID_DIR}/build/generated/source/codegen/jni)
set(LIB_ANDROID_GENERATED_COMPONENTS_DIR ${LIB_ANDROID_GENERATED_JNI_DIR}/react/renderer/components/${LIB_LITERAL})

file(
  GLOB
  LIB_CODEGEN_SRCS
  CONFIGURE_DEPENDS
  ${LIB_ANDROID_GENERATED_JNI_DIR}/*.cpp
  ${LIB_ANDROID_GENERATED_COMPONENTS_DIR}/*.cpp
)

add_library(
  ${LIB_TARGET_NAME}
  SHARED
  ${LIB_CODEGEN_SRCS}
)

target_include_directories(
  ${LIB_TARGET_NAME}
  PUBLIC
  ${LIB_ANDROID_GENERATED_JNI_DIR}
  ${LIB_ANDROID_GENERATED_COMPONENTS_DIR}
)

if (REACTNATIVE_MERGED_SO)
  target_link_libraries(
    ${LIB_TARGET_NAME}
    fbjni
    jsi
    reactnative
  )
else()
  target_link_libraries(
    ${LIB_TARGET_NAME}
    fbjni
    folly_runtime
    glog
    jsi
    react_codegen_rncore
    react_debug
    react_nativemodule_core
    react_render_core
    react_render_debug
    react_render_graphics
    react_render_mapbuffer
    react_render_componentregistry
    react_utils
    rrc_view
    turbomodulejsijni
    yoga
  )
endif()

if(ReactAndroid_VERSION_MINOR GREATER_EQUAL 80)
  target_compile_reactnative_options(${LIB_TARGET_NAME} PUBLIC)
else()
  target_compile_options(
    ${LIB_TARGET_NAME}
    PRIVATE
    -fexceptions
    -frtti
    -std=c++20
    -Wall
  )
endif()

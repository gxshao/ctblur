#include <jni.h>
#include <string>
#include "boxblur.h"

extern "C" {
JNIEXPORT jstring

JNICALL
Java_com_test_opengl_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

/**
 * 高斯模糊核心算法
 * @param env
 * @param r
 * @param pix
 * @param w
 * @param h
 * @return
 */
JNIEXPORT jintArray

JNICALL
Java_com_mrsgx_graphic_ctblur_GaussianFastBlur_ctGaussianFastBlur(JNIEnv *env,
                                                                jobject,
                                                                jint r,
                                                                jintArray pix,
                                                                jint w,
                                                                jint h) {
    jint i;
    jint j;
    jint *arr = env->GetIntArrayElements(pix, false);
    while (r >= 1) {
        for (i = r; i < h - r; i++) {
            for (j = r; j < w - r; j++) {
                jint tl = arr[(i - r) * w + j - r];
                jint tr = arr[(i - r) * w + j + r];
                jint tc = arr[(i - r) * w + j];
                jint bl = arr[(i + r) * w + j - r];
                jint br = arr[(i + r) * w + j + r];
                jint bc = arr[(i + r) * w + j];
                jint cl = arr[i * w + j - r];
                jint cr = arr[i * w + j + r];

                arr[i * w + j] = -0x1000000 | (
                        (tl & 0xFF) + (tr & 0xFF) + (tc & 0xFF) + (bl & 0xFF) + (br & 0xFF) + (bc & 0xFF) +
                        (cl & 0xFF) + (cr & 0xFF) >> 3 & 0xFF) | (
                                         (tl & 0xFF00) + (tr & 0xFF00) + (tc & 0xFF00) + (bl & 0xFF00) + (br & 0xFF00) +
                                         (bc & 0xFF00) + (cl & 0xFF00) + (cr & 0xFF00) >> 3 & 0xFF00) | (
                                         (tl & 0xFF0000) + (tr & 0xFF0000) + (tc & 0xFF0000) + (bl & 0xFF0000) +
                                         (br & 0xFF0000) + (bc & 0xFF0000) + (cl & 0xFF0000) + (cr & 0xFF0000) >>
                                                                                                               3 &
                                         0xFF0000);
            }
        }
        r /= 2;
    }
    env->SetIntArrayRegion(pix, (jsize) 0, (jsize) env->GetArrayLength(pix), arr);
    return pix;
}

JNIEXPORT jintArray

JNICALL
Java_com_mrsgx_graphic_ctblur_BoxBlur_boxBlur(JNIEnv *env,
                                            jobject,
                                            jintArray pixels,
                                            jint w,
                                            jint h,
                                            jint halfRange
) {

    env->SetIntArrayRegion(pixels, (jsize) 0, (jsize) env->GetArrayLength(pixels),
                           boxblur(env->GetIntArrayElements(pixels, false), w,
                                   h, halfRange));
    return pixels;
}
}
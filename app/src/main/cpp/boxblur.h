//
// Created by Marshall on 2018/2/26.
//

#ifndef OPENGLTEST_BOXBLUR_H
#define OPENGLTEST_BOXBLUR_H
int * boxblur(int* pixels,int w,int h,int halfrange);
void boxblurHorizontal(int* pixels,int w,int h,int halfrange);
void boxblurVetical(int* pixels,int w,int h,int halfrange);
#endif //OPENGLTEST_BOXBLUR_H

//
// Created by Marshall on 2018/2/26.
//
#include "boxblur.h"
#include <memory.h>

/**
 * 方框滤波
 * @param pixels
 * @param w
 * @param h
 * @param halfrange
 * @return
 */
int *boxblur(int *pixels, int w, int h, int halfrange) {
    boxblurHorizontal(pixels, w, h, halfrange);
    boxblurVetical(pixels, w, h, halfrange);
    return pixels;
}
/**
 * 水平像素模糊
 * @param pixels
 * @param w
 * @param h
 * @param halfrange
 */
void boxblurHorizontal(int *pixels, int w, int h, int halfrange) {
    int index = 0, x, y;
    int *newColors = new int[w];
    for (y = 0; y <= h; y++) {
        int hits = 0;
        long r = 0;
        long g = 0;
        long b = 0;
        for (x = -halfrange; x < w; x++) {
            int oldPixel = x - halfrange - 1;
            if (oldPixel >= 0) {
                long color = pixels[index + oldPixel];
                if (color != 0) {
                    r -= (color >> 16) & 0xFF;
                    g -= (color >> 8) & 0xFF;
                    b -= color & 0xFF;
                }
                hits--;
            }

            int newPixel = x + halfrange;
            if (newPixel < w) {
                long color = pixels[index + newPixel];
                if (color != 0) {
                    r += (color >> 16) & 0xFF;
                    g += (color >> 8) & 0xFF;
                    b += color & 0xFF;
                }
                hits++;
            }

            if (x >= 0) {

                newColors[x] = (int) ((0xFF << 24) | ((r / hits) << 16) | ((g / hits) << 8) | (b / hits));
            }
        }
        memcpy(pixels + index, newColors, (uint) w);
        index += w;
    }
}
/**
 * 垂直像素模糊
 * @param pixels
 * @param w
 * @param h
 * @param halfRange
 */
void boxblurVetical(int *pixels, int w, int h, int halfRange) {
    int *newColors = new int[h];
    int oldPixelOffset = -(halfRange + 1) * w;
    int newPixelOffset = halfRange * w;
    int index = 0, x, y;
    for (x = 0; x < w; x++) {
        int hits = 0;
        long r = 0;
        long g = 0;
        long b = 0;
        index = -halfRange * w + x;
        for (y = -halfRange; y < h; y++) {
            int oldPixel = y - halfRange - 1;
            if (oldPixel >= 0) {
                long color = pixels[index + oldPixelOffset];
                if (color != 0) {
                    r -= (color >> 16) & 0xFF;
                    g -= (color >> 8) & 0xFF;
                    b -= color & 0xFF;
                }
                hits--;
            }

            int newPixel = y + halfRange;
            if (newPixel < h) {
                long color = pixels[index + newPixelOffset];
                if (color != 0) {
                    r += (color >> 16) & 0xFF;
                    g += (color >> 8) & 0xFF;
                    b += color & 0xFF;
                }
                hits++;
            }

            if (y >= 0) {

                newColors[y] = (int) ((0xFF << 24) | ((r / hits) << 16) | ((g / hits) << 8) | (b / hits));
            }

            index += w;
        }

        for (y = 0; y < h; y++) {
            pixels[y * w + x] = newColors[y];
        }
    }
}
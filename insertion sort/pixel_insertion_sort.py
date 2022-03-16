import cv2
import numpy as np
from datetime import datetime

def sort_img():
    img = cv2.imread(r'\morj.jpg', 1) # path to whatever image 
    for i in range(img.shape[1]):
        img[:,i] = insertion_sort_bw(img[:,i])
        # img[:,i] = insertion_sort(img[:,i])

    cv2.imshow('Image', img)

    # save option
    if cv2.waitKey(0) == ord('s'):
        save_img(img)

    cv2.waitKey(0)
    cv2.destroyAllWindows()


def save_img(img):
    cv2.imwrite(datetime.now().strftime("%Y%m%d-%H%M%S")+ ".jpg", img)

def insertion_sort_bw(series):
    for i in range(1, len(series)):
        current = sum(series[i])
        point = i - 1
        while point >= 0 and sum(series[point]) > current:
            series[point + 1] = series[point]
            point -= 1
        series[point + 1] = current
    return series

def insertion_sort(series):
    for i in range(1, len(series)):
        current = series[i]
        point = i - 1
        while point >= 0 and sum(series[point]) > sum(current):
            series[point + 1] = series[point]
            point -= 1
        series[point + 1] = current
    return series

sort_img()

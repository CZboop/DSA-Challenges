import cv2
import numpy as np
from datetime import datetime
import random
import string
import os.path
import glob

filename = "".join(random.choice(string.ascii_letters) for i in range(5))
frame_list = []

save_path = r'' # full raw path to folder to store frames as images, should be empty or have no other jpg files in it

def sort_img():
    frame_counter = 0
    # load initial image
    img = cv2.imread(r'', 1) # path to whatever image
    # iterate and sort each column, saving image once row fully sorted
    for i in range(img.shape[1]):
        frame_counter += 1
        img[:,i] = insertion_sort(img[:,i], img, frame_counter, type='clr')
        frame_list.append(img)
    # showing end result of sort in window once done
    cv2.imshow('Image', img)

    # save option on s key press for when window open w final image
    if cv2.waitKey(0) == ord('s'):
        save_img(img, "", filename='sorted_final')

    #closing everything
    cv2.waitKey(0)
    cv2.destroyAllWindows()

# save function set up mostly to save each frame but can also save from window
def save_img(img, frame_counter, filename=filename):
    full_filename = filename + "." + str(frame_counter) + ".jpg"
    complete_name = os.path.join(save_path, full_filename)
    cv2.imwrite(complete_name, img)

# sort function can sort into black and white or retain colours depending on type arg
def insertion_sort(series, img, frame_counter, type='bw'):
    for i in range(1, len(series)):
        # black and white sort
        if type=='bw':
            current = sum(series[i])
            point = i - 1
            while point >= 0 and sum(series[point]) > current:
                series[point + 1] = series[point]
                point -= 1
        # colour sort
        elif type=='clr':
            current = series[i]
            point = i - 1
            while point >= 0 and sum(series[point]) > sum(current):
                series[point + 1] = series[point]
                point -= 1
        series[point + 1] = current
        save_img(img, frame_counter, 'sortframe')
    return series

# saving frames to video
def write_video(path=save_path, name='sort_vid'):
    img_array = []
    # getting all frame files
    frame_list = glob.glob(r'*.jpg') #re-enter file path frames location, adding \*.jpg to get all jpg files in dir
    # re-sorting by number value to get all in the right order
    frame_list = sorted(frame_list, key= lambda x: int(x.split('.')[-2]))
    for filename in frame_list:
        img = cv2.imread(filename)
        height, width, layers = img.shape
        size = (width,height)
        img_array.append(img)

    # setting up video writer
    out = cv2.VideoWriter(name + '.avi',cv2.VideoWriter_fourcc(*'DIVX'), 15, size)
    # writing each frame
    for i in range(len(img_array)):
        out.write(img_array[i])
    out.release()

# running
if __name__ == "__main__":
    sort_img()
    write_video()

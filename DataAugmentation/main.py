import cv2
import os
import numpy as np

dstPath = './output'
srcPath = './input'
s = '/'

# AUX functions
def rotate_image(image, angle):
  image_center = tuple(np.array(image.shape[1::-1]) / 2)
  rot_mat = cv2.getRotationMatrix2D(image_center, angle, 1.0)
  result = cv2.warpAffine(image, rot_mat, image.shape[1::-1], flags=cv2.INTER_LINEAR)
  return result

def bw(img):
    grayImage = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    th3 = cv2.adaptiveThreshold(grayImage,300,cv2.ADAPTIVE_THRESH_GAUSSIAN_C,cv2.THRESH_BINARY,11,2)
    return th3

def augment(img):
    hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
    greenMask = cv2.inRange(hsv, (200, 200, 200), (255, 255, 255))
    hsv[:,:,1] = greenMask
    back = cv2.cvtColor(hsv, cv2.COLOR_HSV2BGR)
    return back

# Vertical flip
def tranformC(img):
    return cv2.flip(img, 0)

# Horizontal flip
def tranformD(img):
    return cv2.flip(img, 1)

# Vertical flip / Horizontal flip
def tranformE1(img, degree):
    return rotate_image(img, degree)

# Main code
for imgName in os.listdir(srcPath):
    index = 0
    listImg = []
    route = srcPath+s+imgName
    print('img laod: ', route)
    img = cv2.imread(route)

    # Aplicamos transformaciones
    #listImg.append(tranformC(img))
    #listImg.append(tranformD(img))
    listImg.append(augment(img))
    #for degree in range(0, 360, 35):
    #    listImg.append(tranformE1(img, degree))

    # Guardamos la imagen
    for imgOut in listImg:
        print(dstPath+s+str(index)+'_'+imgName)
        cv2.imwrite(dstPath+s+str(index)+'_'+imgName, imgOut)
        index += 1

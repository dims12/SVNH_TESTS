from SVHN import *
import csv
import os
import matplotlib.pyplot as plt
import matplotlib.image as mpimg

with open(table_file('cropped2', 'train'), 'r') as csvfile:
    rder = csv.DictReader(csvfile, delimiter=',')
    table = [(os.path.join(row['subset'].strip() + '_cropped2', row['imageNum'].strip() + '.png'), row['label'].strip()) for row in rder]
    for imagefile, label in table:
        img=mpimg.imread(os.path.join(corpus_dir, imagefile))
        imgplot = plt.imshow(img)
        plt.title("file = " + imagefile + ", label = " + label)
        plt.show()
        pass

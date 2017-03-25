# demonstrates how to read files by one in sync with CSV on tensorflow side

import tensorflow as tf
from SVHN import *
from matplotlib import pyplot as plt


def read_cropped2(subsetName):
    filename = table_file('cropped2', subsetName)
    filename_input = tf.train.string_input_producer([filename])
    textLineReader = tf.TextLineReader(1)
    key, value = textLineReader.read(filename_input)
    record_defaults = [[''], [''], [''], [''], [''], [''], [''], [''], ['']]
    imagePath, subset, imageNum, smallBoxCount, left, top, width, height, label = tf.decode_csv(value, record_defaults)
    imageFullPath = tf.string_join([corpus_dir, '/', subset, '_cropped2/', imageNum, '.png'])
    value = tf.read_file(imageFullPath)
    image = tf.image.decode_png(value, 3)
    return imagePath, subset, imageNum, smallBoxCount, left, top, width, height, label, image

imagePath, subset, imageNum, smallBoxCount, left, top, width, height, label, image = read_cropped2('train')
#value = read_cropped2('train')

with tf.Session() as sess:
    # Start populating the filename queue.
    coord = tf.train.Coordinator()
    threads = tf.train.start_queue_runners(coord=coord)



    for i in range(10):
        # Retrieve a single instance:
        imagePath1, subset1, imageNum1, smallBoxCount1, left1, top1, width1, height1, label1, image1 = sess.run([imagePath, subset, imageNum, smallBoxCount, left, top, width, height, label, image])
#        value1 = sess.run([value])
        plt.imshow(image1)
        plt.title("image=" + str(imagePath1) + ", label = " + str(label1))
        plt.show()
        pass

    coord.request_stop()
    coord.join(threads)
import tensorflow as tf
from PIL import Image

from SVHN import *
import numpy as np

data = table_data('cropped2', 'train')

def read_my_file():
    records = tf.train.string_input_producer([filename + ',' + label for filename, label in data])
    filename, label = tf.decode_csv(records.dequeue(), ['none', 'none'])
    reader = tf.WholeFileReader()
    key, value = reader.read(filename)
    image = tf.image.decode_png(value)
    return image, label


image, label = read_my_file()

init_op = tf.initialize_all_variables()
with tf.Session() as sess:
    sess.run(init_op)

    coord = tf.train.Coordinator()
    threads = tf.train.start_queue_runners(coord=coord)

    for i in range(10):
        image1, label1 = sess.run(image, label)
        Image.fromarray(np.asarray(image1)).show(label1)




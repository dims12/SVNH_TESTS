# bad

import tensorflow as tf
from PIL import Image

from SVHN import *
import numpy as np

data = table_data('cropped2', 'train')
data = [[os.path.join(corpus_dir, filename), label] for (filename, label) in data]



def read_my_file():

    records = tf.train.input_producer(data)
    record = records.dequeue()
    filename = record[0]
    filenames = tf.FIFOQueue(1, tf.string)
    filenames.enqueue(filename)
    label = record[1]
    reader = tf.WholeFileReader()
    key, raw = reader.read(filenames)
    image = tf.image.decode_png(raw)
    return image, label


image, label = read_my_file()

init_op = tf.initialize_all_variables()
with tf.Session() as sess:
    sess.run(init_op)

    coord = tf.train.Coordinator()
    threads = tf.train.start_queue_runners(coord=coord)

    for i in range(10):
        image1, label1 = sess.run(image, label)
        print(label1)




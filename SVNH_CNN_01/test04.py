import tensorflow as tf
from PIL import Image

from SVHN import *
import numpy as np

data = table_data('cropped2', 'train')

def read_my_file():
    records = tf.train.input_producer(data)

    return records.dequeue()


record = read_my_file()

init_op = tf.initialize_all_variables()
with tf.Session() as sess:
    sess.run(init_op)

    coord = tf.train.Coordinator()
    threads = tf.train.start_queue_runners(coord=coord)

    for i in range(10):
        record1 = sess.run(record)
        print(record1)




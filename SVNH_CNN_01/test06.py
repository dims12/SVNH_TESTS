import tensorflow as tf
from PIL import Image

from SVHN import *
import numpy as np

data = table_data('cropped2', 'train')
data = [[os.path.join(corpus_dir, filename), label] for (filename, label) in data]
filenames = [t[0] for t in data]

filenames_queue = tf.train.input_producer(filenames)
reader = tf.WholeFileReader()
key, raw = reader.read(filenames_queue)

init_op = tf.initialize_all_variables()
with tf.Session() as sess:
    sess.run(init_op)

    coord = tf.train.Coordinator()
    threads = tf.train.start_queue_runners(coord=coord)

    for i in range(10):
        key1 = sess.run(key)
        print(key1)




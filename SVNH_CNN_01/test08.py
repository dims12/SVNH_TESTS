import tensorflow as tf


def reader():
    filename_queue = tf.train.string_input_producer(["V:\\CorporaCopies\\SVHN\\train_cropped2.csv"])
    reader = tf.TextLineReader(1)
    key, value = reader.read(filename_queue)

    # Default values, in case of empty columns. Also specifies the type of the
    # decoded result.
    record_defaults = [[''], [''], [''], [''], [''], [''], [''], [''], ['']]
    col1, col2, col3, col4, col5, col6, col7, col8, col9 = tf.decode_csv(
        value, record_defaults=record_defaults)
    features = tf.stack([col1, col2, col3, col4, col5, col6, col7, col8])
    return features, col9

features, col9 = reader()

with tf.Session() as sess:
    # Start populating the filename queue.
    coord = tf.train.Coordinator()
    threads = tf.train.start_queue_runners(coord=coord)

    for i in range(1200):
        # Retrieve a single instance:
        example, label = sess.run([features, col9])

    coord.request_stop()
    coord.join(threads)
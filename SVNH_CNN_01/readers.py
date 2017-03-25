from SVHN import *
import tensorflow as tf

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
    features = tf.stack([image, imagePath, subset, imageNum, smallBoxCount, left, top, width, height])
    return features, label





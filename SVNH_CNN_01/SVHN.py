import os
from os.path import isfile, join
import csv

corpus_dir = os.environ['SVHN_LOCAL']

subset_names = ['train', 'test', 'extra']

form_names = ['', 'cropped', 'cropped2']


def subset_dir(formName, subsetName):
    if formName == '':
        return os.path.join(corpus_dir, subsetName)
    elif formName == 'cropped':
        return os.path.join(corpus_dir, subsetName + '_' + formName)
    elif formName == 'cropped2':
        return os.path.join(corpus_dir, subsetName + '_' + formName)
    else:
        raise ValueError('Unknown form %s' % formName)


def subset_files(formName, subsetName):
    d = subset_dir(formName, subsetName)
    return [f for f in os.listdir(d) if isfile(os.path.join(d, f))]


def table_file(formName, subsetName):
    filename = subsetName
    if formName != '':
        filename += '_' + formName
    filename += '.csv'
    return os.path.join(corpus_dir, filename)


def table_data(formName, subsetName):
    if formName == 'cropped2':
        with open(table_file(formName, subsetName), 'r') as csvfile:
            rder = csv.DictReader(csvfile, delimiter=',')
            table = [(os.path.join(row['subset'].strip() + '_cropped2', row['imageNum'].strip() + '.png'),
                      row['label'].strip()) for row in rder]
    else:
        raise ValueError('Not implemented formName = ' + formName)
    return table

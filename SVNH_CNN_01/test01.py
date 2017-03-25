from SVHN import *
import csv
import os

with open(table_file('cropped2', 'train'), 'r') as csvfile:
    rder = csv.reader(csvfile, delimiter=',')
    skip = True
    for row in rder:
        if skip:
            skip = not skip
        else:
            filename = os.path.join(row[1].strip() + '_cropped2', row[2].strip() + '.png')
            print(filename)
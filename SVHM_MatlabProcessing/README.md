These scripts are to provide additional processing for SVHN corpus

1) struct2csv.m - takes digitStruct.mat files from all three subset directories 
and creates tree CSV files from each of them (on level upper)

2) createcroppedcorpus.m - takes all three *_32x32.mat files and creates three *_cropped
directories from them with all image files and three *_cropped.csv files with appropriated lable information


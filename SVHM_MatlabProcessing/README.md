These scripts are to provide additional processing for SVHN corpus

1) Set **SVHN_LOCAL** variable to point to location of downloaded [SVHN corpus](http://ufldl.stanford.edu/housenumbers/)
 with the following directory structure

ROOT_DIR
|
+----train
|
+----test
|
+----extra
|
+----train_32x32.mat
+----test_32x32.mat
+----extra_32x32.mat

where **train**, **test** and **extra** are the directories with appropriate image files unpacked.
Note, that
inside these subdirectories are files **digitStruct.mat**, which contains marked regions and labels for images 
and **see_boxes.m** - Matlab script to see regions.
 
2) run script **project.m** to tie Matlab to project directory
 
3) run script **createall.m** which does all job by calling three scripts below subsequently: 

3.1) **createcsv.m** - takes **digitStruct.mat** files from all three subset directories 
and creates tree CSV **train.csv**, **test.csv** and **extra.csv** files from each of them (on level upper)

3.2) **createcroppedcorpus.m** - takes all three **\*_32x32.mat** files and creates three **\*_cropped**
directories from them with all image files extracted (**\*_32x32.mat** files contain 32x32 images inside) 
and also creates three **\*_cropped.csv** files with appropriated label information

3.3) **createcropped2corpus.m** -- merges all regions from **digitStruct.mat** into one region per image and
 cuts images by this region and put crops into appropriate **\*_cropped2** directories. Also creates 
 **\*_cropped2.csv** files with appropriated label information 
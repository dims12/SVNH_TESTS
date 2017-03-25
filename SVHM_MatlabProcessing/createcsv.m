% converts struct mat files from sets into CSV files 

dirs = {'train', 'test', 'extra'};

for i=1:length(dirs)
    dir = fullfile(getenv('SVHN_LOCAL'),dirs{i});
    digitStructFile = fullfile(dir, '/digitStruct.mat');
    load(digitStructFile);
    
    file = fullfile(getenv('SVHN_LOCAL'), [dirs{i} '.csv']);
    fileID = fopen(file,'w');
    line = sprintf('imagePath,subset,imageNum,boxNum,left,top,width,height,label');
    fprintf(fileID, '%s\n', line);
    for j=1:length(digitStruct)
        imageName = digitStruct(j).name;
        dotpos = strfind(imageName, '.');
        imageNum = str2double(imageName(1:(dotpos-1)));
        imageBbox = digitStruct(j).bbox;
        imagePath = [dirs{i} '/' imageName];
        for k=1:length(imageBbox)
            imageBboxHeight = imageBbox(k).height;
            imageBboxLeft = imageBbox(k).left;
            imageBboxTop = imageBbox(k).top;
            imageBboxWidth = imageBbox(k).width;
            imageBboxLabel = imageBbox(k).label;
%            sprintf(formatSpec, imagePath, k, imageBboxLeft, imageBboxTop, imageBboxWidth, imageBboxHeight, imageBboxLabel)
            line = sprintf('%s,%s,%d,%d,%d,%d,%d,%d,%d', imagePath, dirs{i}, imageNum, k, imageBboxLeft, imageBboxTop, imageBboxWidth, imageBboxHeight, imageBboxLabel);
            fprintf(fileID, '%s\n', line);
        end
        
    end
    fclose(fileID);
end

% converts struct mat files from sets into CSV files 

dirs = {'train', 'test', 'extra'};

for i=1:length(dirs)
    dir = fullfile(getenv('SVHN_LOCAL'),dirs{i});
    digitStructFile = fullfile(dir, '/digitStruct.mat');
    load(digitStructFile);
    
    file = fullfile(getenv('SVHN_LOCAL'), [dirs{i} '.csv']);
    fileID = fopen(file,'w');
    formatSpec = '%s,%d,%d,%d,%d,%d,%d\n';
    for j=1:length(digitStruct)
        imageName = digitStruct(j).name;
        imageBbox = digitStruct(j).bbox;
        imagePath = [dirs{i} '/' imageName];
        for k=1:length(imageBbox)
            imageBboxHeight = imageBbox(k).height;
            imageBboxLeft = imageBbox(k).left;
            imageBboxTop = imageBbox(k).top;
            imageBboxWidth = imageBbox(k).width;
            imageBboxLabel = imageBbox(k).label;
%            sprintf(formatSpec, imagePath, k, imageBboxLeft, imageBboxTop, imageBboxWidth, imageBboxHeight, imageBboxLabel)
            fprintf(fileID,formatSpec, imagePath, k, imageBboxLeft, imageBboxTop, imageBboxWidth, imageBboxHeight, imageBboxLabel);
        end
        
    end
    fclose(fileID);
end

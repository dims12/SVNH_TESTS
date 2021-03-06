% creates corpus of cropped images

croppeds = {'train', 'test', 'extra'};

for i=1:length(croppeds)
    
    croppedfile = [croppeds{i} '_32x32.mat'];
    fprintf(1, 'Processing %s\n', croppedfile);
    
    load(croppedfile);
    targetdir = fullfile(getenv('SVHN_LOCAL'), [croppeds{i} '_cropped']);
    mkdir(targetdir);
    
    targetcsv = fullfile(getenv('SVHN_LOCAL'), [croppeds{i} '_cropped.csv']);
    fileID = fopen(targetcsv,'w');
    line = sprintf('imagePath,subset,imageNo,label');
    fprintf(fileID, '%s\n', line);
    h = waitbar(0,sprintf('Processing %s', croppedfile));
    for j=1:length(X)
        
        if mod(j-1,100) == 0
            %fprintf(1, '...done %.1f%%\r', (j*100.0)/length(X));
            waitbar(double(j)/length(X));
        end
        
        image = X(:,:,:,j);
        label = y(j);
        imfilename = [sprintf('%06d', j) '_32x32.png'];
        imfile = fullfile(targetdir, imfilename);
        imwrite(image, imfile);
        line = sprintf('%s,%s,%d,%d', fullfile([croppeds{i} '_cropped'], imfilename), croppeds{i}, j, label);
        fprintf(fileID, '%s\n', line);
    end
    %fprintf(1, 'done 100%%\n');
    close(h);
    fclose(fileID);
end
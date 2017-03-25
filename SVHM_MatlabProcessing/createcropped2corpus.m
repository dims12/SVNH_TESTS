% converts struct mat files from sets into CSV files 

dirs = {'train', 'test', 'extra'};

for i=1:length(dirs)
    
    
    
    dir = fullfile(getenv('SVHN_LOCAL'),dirs{i});
    digitStructFile = fullfile(dir, '/digitStruct.mat');
    
    
    status = sprintf('Processing %s', fullfile(dirs{i}, 'digitStruct.mat'));
    fprintf(1, '%s\n', status);
    
    load(digitStructFile);
    
    targetcsv = fullfile(getenv('SVHN_LOCAL'), [dirs{i} '_cropped2.csv']);
    fileID = fopen(targetcsv,'w');
    line = sprintf('imagePath,subset,imageNum,smallBoxCount,left,top,width,height,label');
    fprintf(fileID, '%s\n', line);
    
    targetdir = fullfile(getenv('SVHN_LOCAL'), [dirs{i} '_cropped2']);
    mkdir(targetdir);
    
    h = waitbar(0, status);
    for j=1:length(digitStruct)
        
        % displaying progress
        if mod(j-1,100) == 0
            waitbar(double(j)/length(digitStruct));
        end
        
        imageName = digitStruct(j).name;
        dotpos = strfind(imageName, '.');
        imageNum = str2double(imageName(1:(dotpos-1)));
        imageBbox = digitStruct(j).bbox;
        imagePath = [dirs{i} '/' imageName];
        
        srcimagepath = fullfile(getenv('SVHN_LOCAL'), imagePath);
        dstimagepath = fullfile(targetdir, imageName);
        image = imread(srcimagepath);
        imagewidth = size(image,2);
        imageheight = size(image,1);
        
        left = 0;
        right = 0;
        top = 0;
        bottom = 0;
        number = '';
        
        
        for k=1:length(imageBbox)
            
            currentleft = imageBbox(k).left;
            currenttop = imageBbox(k).top;
            
            currentright = currentleft + imageBbox(k).width - 1;
            currentbottom = currenttop + imageBbox(k).height - 1;
            
            if k==1
                left = currentleft;
                right = currentright;
                top = currenttop;
                bottom = currentbottom;
            else 
                if currentleft < left
                    left = currentleft;
                end
                if currenttop < top
                    top = currenttop;
                end
                if currentbottom > bottom
                    bottom = currentbottom;
                end
                if currentright > right
                    right = currentright;
                end
            end
            
            number = [number int2str(imageBbox(k).label)];
            
        end
        
        if left < 0
            left=0;
        end
        if top < 0
            top=0;
        end
        if right >= imagewidth
            right = imagewidth - 1;
        end
        if bottom >= imageheight
            bottom = imageheight - 1;
        end
        
        width = right - left + 1;
        height = bottom - top + 1;
        
        line = sprintf('%s,%s,%d,%d,%d,%d,%d,%d,%s', imagePath, dirs{i}, imageNum, k, left,top, width, height, number);
        fprintf(fileID, '%s\n', line);
        
        
        % processing image
        
        
        %fprintf(1, 'Cropping %s (%d x %d) to %d..%d x %d..%d (number = %s)\n', imagePath, size(image,2), size(image,1), left, left+width-1, top, top+height-1, number);
        
        %disabled for rerun
        %cropped2image = image((top+1):(top+height),(left+1):(left+width),:);
        %imwrite(cropped2image, dstimagepath);
        
        
    end
    close(h);
    fclose(fileID);
end

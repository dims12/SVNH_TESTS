% does not work by unknown reason
% looks like Matlab does not see correct H2 database

driver = org.h2.Driver;
props = java.util.Properties;
conn = driver.connect('jdbc:h2:file:D:/Users/Dims/Design/TESTS/SVHN_DB/db/svhn', props);
stmt = conn.createStatement();

setSchemaQuery = 'SET SCHEMA PUBLIC;';
stmt.executeUpdate(setSchemaQuery);


dirs = {'train', 'test', 'extra'};

for i=1:length(dirs)
    dir = fullfile(getenv('SVHN_LOCAL'),dirs{i});
    digitStructFile = fullfile(dir, '/digitStruct.mat');
    load(digitStructFile);
    for j=1:length(digitStruct)
        imageName = digitStruct(j).name;
        imageBbox = digitStruct(j).bbox;
        imagePath = [dirs{i} '/' imageName];
        searchQuery = ['SELECT ID FROM IMAGE WHERE PATH=''' imagePath ''';'];
        rs = stmt.executeQuery(searchQuery);
        if rs.next()
            rs.close();
            imageId = rd.getLong('ID');
            insertQuery = ['INSERT INTO BBOX (IMAGE_ID, ORD, LEFT, TOP, WIDTH, HEIGHT, LABEL) VALUES(' int2str(imageId) ', ' int2str(j) ', ' imageBbox(j).left ', ' imageBbox(j).top ', ' imageBbox(j).width ', ' imageBbox(j).height ', ' imageBbox(j).label ';'];
            stmt.executeUpdate(insertQuery);
        else
            error(['Image ' imagePath ' was not found in database']);
        end
        
    end
end

stmt.close();
conn.close();


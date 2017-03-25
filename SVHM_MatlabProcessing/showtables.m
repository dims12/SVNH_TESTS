driver = org.h2.Driver;
props = java.util.Properties;
conn = driver.connect('jdbc:h2:file:D:/Users/Dims/Design/TESTS/SVHN_DB/db/svhn', props);

% stmt = conn.createStatement();
% setSchemaQuery = 'SET SCHEMA SVNH.PUBLIC;';
% stmt.executeUpdate(setSchemaQuery);
% stmt.close();


metadata = conn.getMetaData();
rs = metadata.getTables([], [], '%', []);
while rs.next()
    rs.getString(3)
end
rs.close();



conn.close();
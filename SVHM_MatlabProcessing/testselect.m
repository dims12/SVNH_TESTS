driver = org.h2.Driver;
props = java.util.Properties;
conn = driver.connect('jdbc:h2:file:D:/Users/Dims/Design/TESTS/SVHN_DB/db/svhn', props);

stmt = conn.createStatement();
query = 'SELECT ID FROM SVHN.PUBLIC.IMAGE WHERE PATH=''train/1.png''';
%query = 'SELECT * FROM INFORMATION_SCHEMA.SESSION_STATE';
%query = 'SHOW TABLES;';
%query = 'SELECT * FROM INFORMATION_SCHEMA.TABLES;';
rs = stmt.executeQuery(query);
while rs.next() 
    sprintf('%s\t%s\t%s\t%s', rs.getObject(1), rs.getObject(2), rs.getObject(3), rs.getObject(4))
end

rs.close();
stmt.close();
conn.close();
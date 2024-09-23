-- Drop user first if they exist
DROP USER if exists 'DisasterManagement'@'%' ;

-- Now create user with prop privileges
CREATE USER 'DisasterManagement'@'%' IDENTIFIED BY 'DisasterManagement';

GRANT ALL PRIVILEGES ON * . * TO 'DisasterManagement'@'%';
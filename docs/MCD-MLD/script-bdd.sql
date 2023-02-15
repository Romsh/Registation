#------------------------------------------------------------
#        Script MariaDB registation-aq-su-av-ma
#------------------------------------------------------------
drop database if exists registation;
create or replace DATABASE registation;
ALTER DATABASE registation CHARACTER SET utf8 COLLATE utf8_general_ci;
USE registation;
#------------------------------------------------------------
# Creation de l utilisateur bdd registation_user
#------------------------------------------------------------
CREATE USER if not EXISTS'registation_user'@'localhost' IDENTIFIED BY 'pwd';
grant all privileges on registation.* to 'registation_user'@'localhost';
FLUSH PRIVILEGES;

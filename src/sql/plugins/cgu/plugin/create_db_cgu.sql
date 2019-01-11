--
-- DROP TABLES BEFORE CREATE THEM
--
DROP TABLE IF EXISTS cgu_accepted_cgu;
DROP TABLE IF EXISTS cgu_version;
DROP TABLE IF EXISTS cgu_cgu;


--
-- Structure for table cgu_cgu
--
CREATE TABLE cgu_cgu (
id_cgu int AUTO_INCREMENT,
cgu_code varchar(255) NOT NULL,
description long varchar NOT NULL,
css long varchar,
PRIMARY KEY (id_cgu)
);

-- PRIMARY KEY (id_cgu, cgu_code)

--
-- Structure for table cgu_version
--
CREATE TABLE cgu_version (
id_cgu_version int AUTO_INCREMENT,
id_cgu int(11) NOT NULL,
version int(11) NOT NULL default 1,
text long varchar NOT NULL,
is_published SMALLINT default 0,
minimum_age int(3) NOT NULL,
PRIMARY KEY (id_cgu_version)
);

--
-- Structure for table cgu_accepted_cgu
--
CREATE TABLE cgu_accepted_cgu (
id_resource int(11) NOT NULL,
resource_type varchar(255) NOT NULL,
id_cgu_version int(11) NOT NULL,
PRIMARY KEY (id_resource, resource_type, id_cgu_version)
);

--
-- Add foreign keys constraints
--
ALTER TABLE cgu_version ADD CONSTRAINT fk_cgu_version FOREIGN KEY ( id_cgu ) 
REFERENCES cgu_cgu ( id_cgu );

ALTER TABLE cgu_accepted_cgu ADD CONSTRAINT fk_id_cgu_version FOREIGN KEY ( id_cgu_version ) 
REFERENCES cgu_version ( id_cgu_version );
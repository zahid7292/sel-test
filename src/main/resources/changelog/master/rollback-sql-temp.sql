-- *********************************************************************
-- SQL to roll back currently unexecuted changes
-- *********************************************************************
-- Change Log: classpath:/db/changelog/db.changelog-master.yaml
-- Ran at: 26/1/19 12:40 AM
-- Against: mdzahidraza@jdbc:postgresql://localhost:5432/seltest_master
-- Liquibase version: 3.5.4
-- *********************************************************************

-- Lock Database
UPDATE public.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = '192.168.1.103 (192.168.1.103)', LOCKGRANTED = '2019-01-26 00:40:43.208' WHERE ID = 1 AND LOCKED = FALSE;

-- Release Database Lock
UPDATE public.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;


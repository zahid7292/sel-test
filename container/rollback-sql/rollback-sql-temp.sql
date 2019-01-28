-- *********************************************************************
-- SQL to roll back currently unexecuted changes
-- *********************************************************************
-- Change Log: classpath:/changelog/tenant/changelog-v2.0.xml
-- Ran at: 28/1/19 3:08 PM
-- Against: postgres@jdbc:postgresql://localhost:8080/sel_test
-- Liquibase version: 3.5.4
-- *********************************************************************

-- Lock Database
UPDATE public.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = 'Mojahid (192.168.1.106)', LOCKGRANTED = '2019-01-28 15:08:04.806' WHERE ID = 1 AND LOCKED = FALSE;

-- Release Database Lock
UPDATE public.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;


-- *********************************************************************
-- SQL to roll back currently unexecuted changes
-- *********************************************************************
-- Change Log: classpath:/changelog/tenant/changelog-v2.0.xml
-- Ran at: 26/1/19 12:04 AM
-- Against: mdzahidraza@jdbc:postgresql://localhost:5432/sel_test
-- Liquibase version: 3.5.4
-- *********************************************************************

-- Lock Database
UPDATE public.databasechangeloglock SET LOCKED = TRUE, LOCKEDBY = '192.168.1.120 (192.168.1.120)', LOCKGRANTED = '2019-01-26 00:04:16.561' WHERE ID = 1 AND LOCKED = FALSE;

-- Release Database Lock
UPDATE public.databasechangeloglock SET LOCKED = FALSE, LOCKEDBY = NULL, LOCKGRANTED = NULL WHERE ID = 1;


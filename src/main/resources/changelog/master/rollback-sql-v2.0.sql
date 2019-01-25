
-- Rolling Back ChangeSet: db/changelog/master/db.changelog-v2.0.xml::v2.0-id6::razamd
DROP INDEX public.uk_user_email;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0-id6' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/master/db.changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/master/db.changelog-v2.0.xml::v2.0-id5::razamd
DROP INDEX public.uk_user_username;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0-id5' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/master/db.changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/master/db.changelog-v2.0.xml::v2.0-id4::razamd
DROP INDEX public.uk_tenant_tenant_id;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0-id4' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/master/db.changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/master/db.changelog-v2.0.xml::v2.0-id3::razamd
ALTER TABLE public.users DROP CONSTRAINT fk_user_tenant_id;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0-id3' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/master/db.changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/master/db.changelog-v2.0.xml::v2.0-id2::razamd
DROP TABLE public.users;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0-id2' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/master/db.changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/master/db.changelog-v2.0.xml::v2.0-id1::razamd
DROP TABLE public.tenant;

DELETE FROM public.databasechangelog WHERE ID = 'v2.0-id1' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/master/db.changelog-v2.0.xml';

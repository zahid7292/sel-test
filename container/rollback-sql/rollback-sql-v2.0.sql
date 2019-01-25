
-- Rolling Back ChangeSet: db/changelog/tenant/changelog-v2.0.xml::v0.1.0-9::razamd
ALTER TABLE public.product_history DROP CONSTRAINT fk12mjfc1nmr59yjwl3sfe2kxmb;

DELETE FROM public.databasechangelog WHERE ID = 'v0.1.0-9' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/tenant/changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/tenant/changelog-v2.0.xml::v0.1.0-8::razamd
ALTER TABLE public.product DROP CONSTRAINT fk_product_category;

DELETE FROM public.databasechangelog WHERE ID = 'v0.1.0-8' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/tenant/changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/tenant/changelog-v2.0.xml::v0.1.0-7::razamd
ALTER TABLE public.rev_info DROP CONSTRAINT rev_info_pkey;

DELETE FROM public.databasechangelog WHERE ID = 'v0.1.0-7' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/tenant/changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/tenant/changelog-v2.0.xml::v0.1.0-6::razamd
ALTER TABLE public.product_history DROP CONSTRAINT product_history_pkey;

DELETE FROM public.databasechangelog WHERE ID = 'v0.1.0-6' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/tenant/changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/tenant/changelog-v2.0.xml::v0.1.0-5::razamd
DROP TABLE public.rev_info;

DELETE FROM public.databasechangelog WHERE ID = 'v0.1.0-5' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/tenant/changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/tenant/changelog-v2.0.xml::v0.1.0-4::razamd
DROP TABLE public.product_history;

DELETE FROM public.databasechangelog WHERE ID = 'v0.1.0-4' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/tenant/changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/tenant/changelog-v2.0.xml::v0.1.0-3::razamd
DROP TABLE public.product;

DELETE FROM public.databasechangelog WHERE ID = 'v0.1.0-3' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/tenant/changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/tenant/changelog-v2.0.xml::v0.1.0-2::razamd
DROP TABLE public.category;

DELETE FROM public.databasechangelog WHERE ID = 'v0.1.0-2' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/tenant/changelog-v2.0.xml';

-- Rolling Back ChangeSet: db/changelog/tenant/changelog-v2.0.xml::v0.1.0-1::razamd
DROP SEQUENCE public.hibernate_sequence CASCADE;

DELETE FROM public.databasechangelog WHERE ID = 'v0.1.0-1' AND AUTHOR = 'razamd' AND FILENAME = 'db/changelog/tenant/changelog-v2.0.xml';

WITH mx AS (SELECT MAX(id) AS id FROM real_estate_agency.public.m_users)
SELECT setval('real_estate_agency.public.m_users_id_seq', mx.id) AS curseq
FROM mx;

WITH mx AS (SELECT MAX(id) AS id FROM real_estate_agency.public.m_roles)
SELECT setval('real_estate_agency.public.m_roles_id_seq', mx.id) AS curseq
FROM mx;

WITH mx AS (SELECT MAX(id) AS id FROM real_estate_agency.public.m_buildings)
SELECT setval('real_estate_agency.public.m_buildings_id_seq', mx.id) AS curseq
FROM mx;

WITH mx AS (SELECT MAX(id) AS id FROM real_estate_agency.public.m_real_estate_activities)
SELECT setval('real_estate_agency.public.m_real_estate_activities_id_seq', mx.id) AS curseq
FROM mx;

WITH mx AS (SELECT MAX(id) AS id FROM real_estate_agency.public.m_real_estate_activities_request)
SELECT setval('real_estate_agency.public.m_real_estate_activities_request_id_seq', mx.id) AS curseq
FROM mx;

WITH mx AS (SELECT MAX(id) AS id FROM real_estate_agency.public.m_chat)
SELECT setval('real_estate_agency.public.m_chat_id_seq', mx.id) AS curseq
FROM mx;
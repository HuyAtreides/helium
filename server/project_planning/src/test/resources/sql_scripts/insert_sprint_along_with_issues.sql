insert into project_planning.sprint (
  id,
  last_updated_by_id,
  due_date,
  creator_id,
  project_id
) values (
  '24f402bb-d5de-4886-b545-2a4cb0361631',
  'bb09b68e-d35c-4faa-a48d-490992c1f8dd',
  '2025-04-11T00:00:00Z',
  'bb09b68e-d35c-4faa-a48d-490992c1f8dd',
  '1d6846ce-0a75-4a39-a49e-dea0d4be8b40'
);

insert into project_planning.sprint (
  id,
  last_updated_by_id,
  due_date,
  creator_id,
  project_id
) values (
  '24f402bb-d5de-4886-b545-2a4cb0361632',
  'bb09b68e-d35c-4faa-a48d-490992c1f8dd',
  '2025-08-15T00:00:00Z',
  'bb09b68e-d35c-4faa-a48d-490992c1f8dd',
  '1d6846ce-0a75-4a39-a49e-dea0d4be8b40'
);

insert into project_planning.issue_status (
  id,
  name,
  project_id
) values (
  '943f5d4e-9914-4947-b26a-af049e00eb7d',
  'in_progress',
  '1d6846ce-0a75-4a39-a49e-dea0d4be8b40'
);

insert into project_planning.issue_type (
  id,
  name,
  project_id
) values (
  '7b5e626e-9bb9-45ed-b19c-51a35390ad23',
  'user_story',
  '1d6846ce-0a75-4a39-a49e-dea0d4be8b40'
);

insert into project_planning.issue (
  id,
  name,
  summary,
  last_updated_by_id,
  point_estimate,
  creator_id,
  sprint_id,
  project_id,
  dtype
) values (
  'c0d1dde6-49d7-432d-9f99-3744e7ce060e',
  'lumon-us',
  'testing issue in lumon project',
  'bb09b68e-d35c-4faa-a48d-490992c1f8dd',
  4,
  'bb09b68e-d35c-4faa-a48d-490992c1f8dd',
  '24f402bb-d5de-4886-b545-2a4cb0361632',
  '1d6846ce-0a75-4a39-a49e-dea0d4be8b40',
  'UserStory'
);
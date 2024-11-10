insert into permission (
  id,
  name,
  description
) values (
  '222b9a2b-6e1c-4ede-a482-39701bbbb4af',
  'browse_project',
  'Allows users to view the project and its issues.'
), (
  '6bf68c21-c84e-494a-b420-30bc617b50e9',
  'create_issues',
  'Allows users to create new issues in the project.'
), (
  '09fbd6d5-99ea-4401-8020-dcdfd67a588e',
  'edit_issues',
  'Permits users to edit existing issues, including fields and details.'
), (
  '85332d26-86d1-42b6-a518-b415ef359a43',
  'delete_issues',
  'Allows users to delete issues in the project.'
), (
  'b38639e9-beba-410f-84d2-3969f51c6637',
  'transition_issues',
  'Grants the ability to move issues through workflow statuses (e.g., Open, In Progress, Done).',
), (
  'fdd1d0b9-6dcf-4188-b832-fb1a0305b374',
  'assign_issues',
  'Allows users to assign issues to themselves or others.'
), (
  '74a52b3c-e253-49bb-9c74-3ffb9c5b4213',
  'set_issue_restriction',
  'Allows users to assign or modify the security level of an issue, controlling who can view it.'
), (
  '393d44d8-209c-4e4f-a754-9f33d957a9c6',
  'view_voters_and_watchers',
  'Permits users to see who is watching or voting on an issue.'
), (
  'ffde1dd9-3815-4824-9037-a35cbf3294e3',
  'manage_watchers',
  'Allows users to add or remove other users from an issue\'s watchers list.'
), (
  'af4303f3-d296-4df5-a1fb-82edfde487af',
  'add_comments',
  'Allows users to add comments to issues.'
), (
  '59801789-6939-4f50-b839-dca11ae6a5ca',
  'edit_all_comments',
  'Permits users to edit any comment on an issue.'
), (
  'df1f39e9-06dd-4f3d-ab3d-701fbc5a9d81',
  'edit_own_comments',
  'Allows users to edit only the comments they have posted.'
), (
  'a1735e8a-d1f0-4502-bfbb-a0d2d1c9e563'
  'delete_all_comments',
  'Allows users to delete any comment on an issue.'
), (
  '6f4178ca-ca1e-4b67-9c67-5988ba5be28e',
  'delete_own_comments',
  'Allows users to delete only their own comments.'
), (
  '29d277c5-ac7e-4325-9e4e-2daa3cec8cb4',
  'create_atachments',
  'Enables users to attach files to issues.'
), (
  '133d1795-ed1e-48ff-93b5-c2ba0d2254ed',
  'delete_all_attachments',
  'Allows users to delete any attachment on an issue.'
), (
  '1e284d88-4892-4fdb-8f86-77eb2076351b',
  'delete_own_attachments',
  'Allows users to delete only their own attachments.'
), (
  '731682ba-c986-4355-b22c-85dcfb4a7b24',
  'Administer Projects',
  'Provides full project-level administrative rights, including the ability to manage project components, versions, and project-specific settings.'
), (
  '06fa31a8-04b9-4857-85bf-806d2d683cda',
  'browse_users',
  'Allows users to see user lists when assigning issues or selecting users in other fields.'
);

create table project_read_only (
  id uuid primary key,
  "key" varchar(7) not null
);

create table project_member (
  project_id uuid not null foreign key references project_read_only(id),
  user_id uuid not null,
  join_date timestamp not null,
  primary key (project_id, user_id)
);

create table role (
  id uuid primary key,
  name varchar(500),
  label varchar(700) not null,
  description varchar(5000),
  created_at timestamp default (now() at time zone 'utc'),
  last_updated_at timestamp default (now() at time zone 'utc'),
  last_updated_by_id uuid not null,
  project_id foreign key references project_read_only(id)
);

create table project_member_role (
  project_id uuid not null,
  user_id uuid not null,
  role_id uuid foreign key references role(id),
  foreign key (project_id, user_id) references project_member(project_id, user_id),
  primary key (project_id, user_id, role_id)
);

create table permission (
  id uuid primary key,
  name varchar(500) not null,
  label varchar(700) not null,
  description varchar(5000),
  created_at timestamp default (now() at time zone 'utc'),
  last_updated_at timestamp default (now() at time zone 'utc'),
  last_updated_by_id uuid null
);

create table role_permission (
  role_id uuid not null foreign key references role(id),
  permission_id uuid not null foreign key references permission(id),
  primary key (role_id, permission_id)
);

create table issue_read_only (
  id uuid primary key,
  name varchar(500) unique not null
);

create table issue_restriction (
   id uuid primary key,
   dtype varchar(31),
   project_member_id uuid,
   role_id uuid,
   issue_id uuid not null foreign key references (issue_read_only)
);
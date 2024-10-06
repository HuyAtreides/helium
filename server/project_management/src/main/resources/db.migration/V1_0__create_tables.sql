create table project (
  id uuid primary key,
  name varchar(500) not null,
  icon_url varchar(200),
  description text,
  "key" varchar(7) not null,
  project_lead_id uuid,
  default_assignee uuid,
  created_at timestamp default (now() at time zone 'utc'),
  last_updated_at timestamp default (now() at time zone 'utc'),
  last_updated_by_id uuid not null
)
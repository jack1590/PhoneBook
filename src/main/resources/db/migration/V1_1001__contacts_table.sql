create table contacts (
  id varchar(36), -- UUID
  -- -------------------------------------------------
  prefix varchar(50),
  first_name varchar(50),
  middle_name varchar(50),
  last_name varchar(50),
  -- -------------------------------------------------
  phone varchar(20),
  email varchar(50),
  -- -------------------------------------------------
  version integer,
  last_updated_on timestamp,
  last_updated_by varchar(50),
  primary key (id)
);
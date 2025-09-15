CREATE TABLE IF NOT EXISTS accounts (
  id UUID PRIMARY KEY,
  owner_name varchar(255),
  currency varchar(10),
  balance numeric(20,2)
);

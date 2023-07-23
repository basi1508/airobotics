CREATE TABLE GUEST(
  GUEST_ID BIGINT PRIMARY KEY,
  FIRST_NAME VARCHAR(64),
  LAST_NAME VARCHAR(64),
  EMAIL_ADDRESS VARCHAR(64),
  ADDRESS VARCHAR(64),
  COUNTRY VARCHAR(32),
  STATE VARCHAR(12),
  PHONE_NUMBER VARCHAR(24)
);

CREATE INDEX IDX_PHONE_NUMBER_ ON GUEST(PHONE_NUMBER);

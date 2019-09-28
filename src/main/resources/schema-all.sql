DROP TABLE IF EXISTS bugs;
DROP TABLE IF EXISTS testers_devices;
DROP TABLE IF EXISTS testers;
DROP TABLE IF EXISTS devices;

CREATE TABLE IF NOT EXISTS devices (
  device_id BIGINT NOT NULL PRIMARY KEY,
  description VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS testers (
  tester_id BIGINT NOT NULL PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  country VARCHAR(10) NOT NULL,
  last_login TIMESTAMP
);

CREATE TABLE IF NOT EXISTS testers_devices (
  tester_id BIGINT NOT NULL,
  device_id BIGINT NOT NULL,
  PRIMARY KEY (tester_id, device_id),
  FOREIGN KEY (tester_id) REFERENCES testers(tester_id),
  FOREIGN KEY (device_id) REFERENCES devices(device_id)
);

CREATE TABLE IF NOT EXISTS bugs (
  bug_id BIGINT NOT NULL PRIMARY KEY,
  device_id BIGINT NOT NULL,
  tester_id BIGINT NOT NULL,
  FOREIGN KEY (device_id) REFERENCES devices(device_id),
  FOREIGN KEY (tester_id) REFERENCES testers(tester_id)
);
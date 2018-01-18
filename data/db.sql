CREATE TABLE mentors (
    first_name        TEXT NOT NULL,
    last_name         TEXT NOT NULL,
    nick_name         TEXT NOT NULL,
    phone_number      TEXT NOT NULL,
    email             TEXT NOT NULL,
    city              TEXT NOT NULL,
    favourite_number  INTEGER);

CREATE TABLE applicants (
    first_name       TEXT NOT NULL,
    last_name        TEXT NOT NULL,
    phone_number     TEXT NOT NULL,
    email            TEXT NOT NULL,
    application_code INTEGER);

CREATE TEMP TABLE ApplicantsTEMP (
    first_name,
    last_name,
    phone_number,
    email,
    application_code
);

CREATE TEMP TABLE MentorsTEMP (
    first_name,
    last_name,
    nick_name,
    phone_number,
    email,
    city,
    favourite_number
);

.mode csv
.import applicants.csv ApplicantsTEMP
.import mentors.csv MentorsTEMP

INSERT INTO Applicants (
    first_name,
    last_name,
    phone_number,
    email,
    application_code
) SELECT * FROM ApplicantsTEMP;

INSERT INTO Mentors (
    first_name,
    last_name,
    nick_name,
    phone_number,
    email,
    city,
    favourite_number
) SELECT * FROM MentorsTEMP;
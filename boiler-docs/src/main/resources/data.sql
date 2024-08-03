CREATE TABLE T_USER
(
    id         int auto_increment primary key,
    name       varchar(30)  null,
    phone      varchar(20)  null,
    hobby      varchar(20)  null,
    email      varchar(255) null,
    birth_date date         null,
    address    varchar(255) null
);

INSERT INTO T_USER(name, phone, hobby, email, birth_date, address)
VALUES ('James Smith', '123-456-7890', 'Hiking', 'james.smith@example.com', '1990-01-01',
        '350 Fifth Ave, New York, NY'),
       ('Mary Johnson', '234-567-8901', 'Swimming', 'mary.johnson@example.com', '1991-02-02',
        '500 S. Buena Vista St, Burbank, CA'),
       ('Robert Williams', '345-678-9012', 'Reading', 'robert.williams@example.com', '1992-03-03',
        '333 West 39th Street, New York, NY'),
       ('Patricia Brown', '456-789-0123', 'Cycling', 'patricia.brown@example.com', '1993-04-04',
        '1600 Pennsylvania Ave NW, Washington, DC'),
       ('Michael Jones', '567-890-1234', 'Running', 'michael.jones@example.com', '1994-05-05',
        '126 SE Stark St, Portland, OR'),
       ('Linda Miller', '678-901-2345', 'Skiing', 'linda.miller@example.com', '1995-06-06',
        '2101 E. Mariposa Ave, El Segundo, CA'),
       ('William Davis', '789-012-3456', 'Gaming', 'william.davis@example.com', '1996-07-07',
        '605 N Michigan Ave, Chicago, IL'),
       ('Elizabeth Garcia', '890-123-4567', 'Painting', 'elizabeth.garcia@example.com', '1997-08-08',
        '200 Washington St, Boston, MA'),
       ('Joseph Martin', '901-234-5678', 'Photography', 'joseph.martin@example.com', '1998-09-09',
        '333 South Hope St, Los Angeles, CA'),
       ('Jennifer Thompson', '012-345-6789', 'Dancing', 'jennifer.thompson@example.com', '1999-10-10',
        '350 Cambridge Ave, Palo Alto, CA');
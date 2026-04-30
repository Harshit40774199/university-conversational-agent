DROP DATABASE IF EXISTS chatbotuni;
CREATE DATABASE chatbotuni;
USE chatbotuni;

CREATE TABLE modules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL UNIQUE,
    slug VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    lecturer VARCHAR(255),
    credits INT,
    description TEXT,
    assessment TEXT,
    timetable VARCHAR(255),
    deadline DATE,
    book VARCHAR(255),
    moodle_url VARCHAR(500),
    resource_url VARCHAR(500)
);

CREATE TABLE knowledge_sources (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(1000) NOT NULL,
    keywords VARCHAR(1000) NOT NULL,
    summary VARCHAR(2000) NOT NULL
);

CREATE TABLE chat_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    reply TEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE USER IF NOT EXISTS 'chatbotuser'@'localhost' IDENTIFIED BY 'chatbot123';
GRANT ALL PRIVILEGES ON chatbotuni.* TO 'chatbotuser'@'localhost';
FLUSH PRIVILEGES;

INSERT INTO modules (
    code, slug, name, lecturer, credits, description, assessment,
    timetable, deadline, book, moodle_url, resource_url
)
VALUES
(
    'SET11106',
    'database-systems',
    'Database Systems',
    'Dr Smith',
    20,
    'This module covers relational databases, SQL, normalization, ER diagrams, and database design principles.',
    'Coursework 50%, Exam 50%',
    'Wednesday 2:00 PM - 4:00 PM',
    '2026-03-25',
    'Database System Concepts',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
),
(
    'SET08101',
    'web-development',
    'Web Development',
    'Prof John',
    20,
    'This module introduces HTML, CSS, JavaScript, responsive design, and practical web application development.',
    'Coursework 60%, Project 40%',
    'Monday 10:00 AM - 12:00 PM',
    '2026-03-30',
    'Learning Web Design',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
),
(
    'SET11007',
    'artificial-intelligence',
    'Artificial Intelligence',
    'Dr Lee',
    20,
    'This module introduces intelligent systems, machine learning fundamentals, search techniques, and AI applications.',
    'Coursework 40%, Final Exam 60%',
    'Friday 11:00 AM - 1:00 PM',
    '2026-04-05',
    'Artificial Intelligence: A Modern Approach',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
);

INSERT INTO knowledge_sources (category, title, url, keywords, summary)
VALUES
(
    'modules',
    'Moodle Modules',
    'https://moodle.napier.ac.uk/my/',
    'module,modules,moodle,course,courses,subject,subjects,handbook',
    'Use this source for module access, course spaces, Moodle-based module guidance, and module-related student questions.'
),
(
    'fees and funding',
    'Fees and Funding',
    'https://my.napier.ac.uk/money',
    'fees,funding,tuition,scholarship,loan,bursary,payment,payments,cost,costs,money',
    'Use this source for fees, funding, tuition payments, bursaries, and money-related student questions.'
),
(
    'timetables',
    'Student Timetable',
    'https://my.napier.ac.uk/my/',
    'timetable,timetables,class,classes,schedule,schedules,lecture,lectures,room,rooms',
    'Use this source for timetable access and schedule-related student questions.'
);DROP DATABASE IF EXISTS chatbotuni;
CREATE DATABASE chatbotuni;
USE chatbotuni;

CREATE TABLE modules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL UNIQUE,
    slug VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    lecturer VARCHAR(255),
    credits INT,
    description TEXT,
    assessment TEXT,
    timetable VARCHAR(255),
    deadline DATE,
    book VARCHAR(255),
    moodle_url VARCHAR(500),
    resource_url VARCHAR(500)
);

CREATE TABLE knowledge_sources (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(1000) NOT NULL,
    keywords VARCHAR(1000) NOT NULL,
    summary VARCHAR(2000) NOT NULL
);

CREATE TABLE chat_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    reply TEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE USER IF NOT EXISTS 'chatbotuser'@'localhost' IDENTIFIED BY 'chatbot123';
GRANT ALL PRIVILEGES ON chatbotuni.* TO 'chatbotuser'@'localhost';
FLUSH PRIVILEGES;

INSERT INTO modules (
    code, slug, name, lecturer, credits, description, assessment,
    timetable, deadline, book, moodle_url, resource_url
)
VALUES
(
    'SET11106',
    'database-systems',
    'Database Systems',
    'Dr Smith',
    20,
    'This module covers relational databases, SQL, normalization, ER diagrams, and database design principles.',
    'Coursework 50%, Exam 50%',
    'Wednesday 2:00 PM - 4:00 PM',
    '2026-03-25',
    'Database System Concepts',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
),
(
    'SET08101',
    'web-development',
    'Web Development',
    'Prof John',
    20,
    'This module introduces HTML, CSS, JavaScript, responsive design, and practical web application development.',
    'Coursework 60%, Project 40%',
    'Monday 10:00 AM - 12:00 PM',
    '2026-03-30',
    'Learning Web Design',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
),
(
    'SET11007',
    'artificial-intelligence',
    'Artificial Intelligence',
    'Dr Lee',
    20,
    'This module introduces intelligent systems, machine learning fundamentals, search techniques, and AI applications.',
    'Coursework 40%, Final Exam 60%',
    'Friday 11:00 AM - 1:00 PM',
    '2026-04-05',
    'Artificial Intelligence: A Modern Approach',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
);

INSERT INTO knowledge_sources (category, title, url, keywords, summary)
VALUES
(
    'modules',
    'Moodle Modules',
    'https://moodle.napier.ac.uk/my/',
    'module,modules,moodle,course,courses,subject,subjects,handbook',
    'Use this source for module access, course spaces, Moodle-based module guidance, and module-related student questions.'
),
(
    'fees and funding',
    'Fees and Funding',
    'https://my.napier.ac.uk/money',
    'fees,funding,tuition,scholarship,loan,bursary,payment,payments,cost,costs,money',
    'Use this source for fees, funding, tuition payments, bursaries, and money-related student questions.'
),
(
    'timetables',
    'Student Timetable',
    'https://my.napier.ac.uk/my/',
    'timetable,timetables,class,classes,schedule,schedules,lecture,lectures,room,rooms',
    'Use this source for timetable access and schedule-related student questions.'
);DROP DATABASE IF EXISTS chatbotuni;
CREATE DATABASE chatbotuni;
USE chatbotuni;

CREATE TABLE modules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL UNIQUE,
    slug VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    lecturer VARCHAR(255),
    credits INT,
    description TEXT,
    assessment TEXT,
    timetable VARCHAR(255),
    deadline DATE,
    book VARCHAR(255),
    moodle_url VARCHAR(500),
    resource_url VARCHAR(500)
);

CREATE TABLE knowledge_sources (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(1000) NOT NULL,
    keywords VARCHAR(1000) NOT NULL,
    summary VARCHAR(2000) NOT NULL
);

CREATE TABLE chat_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    reply TEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE USER IF NOT EXISTS 'chatbotuser'@'localhost' IDENTIFIED BY 'chatbot123';
GRANT ALL PRIVILEGES ON chatbotuni.* TO 'chatbotuser'@'localhost';
FLUSH PRIVILEGES;

INSERT INTO modules (
    code, slug, name, lecturer, credits, description, assessment,
    timetable, deadline, book, moodle_url, resource_url
)
VALUES
(
    'SET11106',
    'database-systems',
    'Database Systems',
    'Dr Smith',
    20,
    'This module covers relational databases, SQL, normalization, ER diagrams, and database design principles.',
    'Coursework 50%, Exam 50%',
    'Wednesday 2:00 PM - 4:00 PM',
    '2026-03-25',
    'Database System Concepts',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
),
(
    'SET08101',
    'web-development',
    'Web Development',
    'Prof John',
    20,
    'This module introduces HTML, CSS, JavaScript, responsive design, and practical web application development.',
    'Coursework 60%, Project 40%',
    'Monday 10:00 AM - 12:00 PM',
    '2026-03-30',
    'Learning Web Design',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
),
(
    'SET11007',
    'artificial-intelligence',
    'Artificial Intelligence',
    'Dr Lee',
    20,
    'This module introduces intelligent systems, machine learning fundamentals, search techniques, and AI applications.',
    'Coursework 40%, Final Exam 60%',
    'Friday 11:00 AM - 1:00 PM',
    '2026-04-05',
    'Artificial Intelligence: A Modern Approach',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
);

INSERT INTO knowledge_sources (category, title, url, keywords, summary)
VALUES
(
    'modules',
    'Moodle Modules',
    'https://moodle.napier.ac.uk/my/',
    'module,modules,moodle,course,courses,subject,subjects,handbook',
    'Use this source for module access, course spaces, Moodle-based module guidance, and module-related student questions.'
),
(
    'fees and funding',
    'Fees and Funding',
    'https://my.napier.ac.uk/money',
    'fees,funding,tuition,scholarship,loan,bursary,payment,payments,cost,costs,money',
    'Use this source for fees, funding, tuition payments, bursaries, and money-related student questions.'
),
(
    'timetables',
    'Student Timetable',
    'https://my.napier.ac.uk/my/',
    'timetable,timetables,class,classes,schedule,schedules,lecture,lectures,room,rooms',
    'Use this source for timetable access and schedule-related student questions.'
);DROP DATABASE IF EXISTS chatbotuni;
CREATE DATABASE chatbotuni;
USE chatbotuni;

CREATE TABLE modules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL UNIQUE,
    slug VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    lecturer VARCHAR(255),
    credits INT,
    description TEXT,
    assessment TEXT,
    timetable VARCHAR(255),
    deadline DATE,
    book VARCHAR(255),
    moodle_url VARCHAR(500),
    resource_url VARCHAR(500)
);

CREATE TABLE knowledge_sources (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(1000) NOT NULL,
    keywords VARCHAR(1000) NOT NULL,
    summary VARCHAR(2000) NOT NULL
);

CREATE TABLE chat_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    reply TEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE USER IF NOT EXISTS 'chatbotuser'@'localhost' IDENTIFIED BY 'chatbot123';
GRANT ALL PRIVILEGES ON chatbotuni.* TO 'chatbotuser'@'localhost';
FLUSH PRIVILEGES;

INSERT INTO modules (
    code, slug, name, lecturer, credits, description, assessment,
    timetable, deadline, book, moodle_url, resource_url
)
VALUES
(
    'SET11106',
    'database-systems',
    'Database Systems',
    'Dr Smith',
    20,
    'This module covers relational databases, SQL, normalization, ER diagrams, and database design principles.',
    'Coursework 50%, Exam 50%',
    'Wednesday 2:00 PM - 4:00 PM',
    '2026-03-25',
    'Database System Concepts',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
),
(
    'SET08101',
    'web-development',
    'Web Development',
    'Prof John',
    20,
    'This module introduces HTML, CSS, JavaScript, responsive design, and practical web application development.',
    'Coursework 60%, Project 40%',
    'Monday 10:00 AM - 12:00 PM',
    '2026-03-30',
    'Learning Web Design',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
),
(
    'SET11007',
    'artificial-intelligence',
    'Artificial Intelligence',
    'Dr Lee',
    20,
    'This module introduces intelligent systems, machine learning fundamentals, search techniques, and AI applications.',
    'Coursework 40%, Final Exam 60%',
    'Friday 11:00 AM - 1:00 PM',
    '2026-04-05',
    'Artificial Intelligence: A Modern Approach',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
);

INSERT INTO knowledge_sources (category, title, url, keywords, summary)
VALUES
(
    'modules',
    'Moodle Modules',
    'https://moodle.napier.ac.uk/my/',
    'module,modules,moodle,course,courses,subject,subjects,handbook',
    'Use this source for module access, course spaces, Moodle-based module guidance, and module-related student questions.'
),
(
    'fees and funding',
    'Fees and Funding',
    'https://my.napier.ac.uk/money',
    'fees,funding,tuition,scholarship,loan,bursary,payment,payments,cost,costs,money',
    'Use this source for fees, funding, tuition payments, bursaries, and money-related student questions.'
),
(
    'timetables',
    'Student Timetable',
    'https://my.napier.ac.uk/my/',
    'timetable,timetables,class,classes,schedule,schedules,lecture,lectures,room,rooms',
    'Use this source for timetable access and schedule-related student questions.'
);DROP DATABASE IF EXISTS chatbotuni;
CREATE DATABASE chatbotuni;
USE chatbotuni;

CREATE TABLE modules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL UNIQUE,
    slug VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    lecturer VARCHAR(255),
    credits INT,
    description TEXT,
    assessment TEXT,
    timetable VARCHAR(255),
    deadline DATE,
    book VARCHAR(255),
    moodle_url VARCHAR(500),
    resource_url VARCHAR(500)
);

CREATE TABLE knowledge_sources (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(1000) NOT NULL,
    keywords VARCHAR(1000) NOT NULL,
    summary VARCHAR(2000) NOT NULL
);

CREATE TABLE chat_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    reply TEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE USER IF NOT EXISTS 'chatbotuser'@'localhost' IDENTIFIED BY 'chatbot123';
GRANT ALL PRIVILEGES ON chatbotuni.* TO 'chatbotuser'@'localhost';
FLUSH PRIVILEGES;

INSERT INTO modules (
    code, slug, name, lecturer, credits, description, assessment,
    timetable, deadline, book, moodle_url, resource_url
)
VALUES
(
    'SET11106',
    'database-systems',
    'Database Systems',
    'Dr Smith',
    20,
    'This module covers relational databases, SQL, normalization, ER diagrams, and database design principles.',
    'Coursework 50%, Exam 50%',
    'Wednesday 2:00 PM - 4:00 PM',
    '2026-03-25',
    'Database System Concepts',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
),
(
    'SET08101',
    'web-development',
    'Web Development',
    'Prof John',
    20,
    'This module introduces HTML, CSS, JavaScript, responsive design, and practical web application development.',
    'Coursework 60%, Project 40%',
    'Monday 10:00 AM - 12:00 PM',
    '2026-03-30',
    'Learning Web Design',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
),
(
    'SET11007',
    'artificial-intelligence',
    'Artificial Intelligence',
    'Dr Lee',
    20,
    'This module introduces intelligent systems, machine learning fundamentals, search techniques, and AI applications.',
    'Coursework 40%, Final Exam 60%',
    'Friday 11:00 AM - 1:00 PM',
    '2026-04-05',
    'Artificial Intelligence: A Modern Approach',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
);

INSERT INTO knowledge_sources (category, title, url, keywords, summary)
VALUES
(
    'modules',
    'Moodle Modules',
    'https://moodle.napier.ac.uk/my/',
    'module,modules,moodle,course,courses,subject,subjects,handbook',
    'Use this source for module access, course spaces, Moodle-based module guidance, and module-related student questions.'
),
(
    'fees and funding',
    'Fees and Funding',
    'https://my.napier.ac.uk/money',
    'fees,funding,tuition,scholarship,loan,bursary,payment,payments,cost,costs,money',
    'Use this source for fees, funding, tuition payments, bursaries, and money-related student questions.'
),
(
    'timetables',
    'Student Timetable',
    'https://my.napier.ac.uk/my/',
    'timetable,timetables,class,classes,schedule,schedules,lecture,lectures,room,rooms',
    'Use this source for timetable access and schedule-related student questions.'
);DROP DATABASE IF EXISTS chatbotuni;
CREATE DATABASE chatbotuni;
USE chatbotuni;

CREATE TABLE modules (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL UNIQUE,
    slug VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    lecturer VARCHAR(255),
    credits INT,
    description TEXT,
    assessment TEXT,
    timetable VARCHAR(255),
    deadline DATE,
    book VARCHAR(255),
    moodle_url VARCHAR(500),
    resource_url VARCHAR(500)
);

CREATE TABLE knowledge_sources (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(1000) NOT NULL,
    keywords VARCHAR(1000) NOT NULL,
    summary VARCHAR(2000) NOT NULL
);

CREATE TABLE chat_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    sender VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    reply TEXT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE USER IF NOT EXISTS 'chatbotuser'@'localhost' IDENTIFIED BY 'chatbot123';
GRANT ALL PRIVILEGES ON chatbotuni.* TO 'chatbotuser'@'localhost';
FLUSH PRIVILEGES;

INSERT INTO modules (
    code, slug, name, lecturer, credits, description, assessment,
    timetable, deadline, book, moodle_url, resource_url
)
VALUES
(
    'SET11106',
    'database-systems',
    'Database Systems',
    'Dr Smith',
    20,
    'This module covers relational databases, SQL, normalization, ER diagrams, and database design principles.',
    'Coursework 50%, Exam 50%',
    'Wednesday 2:00 PM - 4:00 PM',
    '2026-03-25',
    'Database System Concepts',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
),
(
    'SET08101',
    'web-development',
    'Web Development',
    'Prof John',
    20,
    'This module introduces HTML, CSS, JavaScript, responsive design, and practical web application development.',
    'Coursework 60%, Project 40%',
    'Monday 10:00 AM - 12:00 PM',
    '2026-03-30',
    'Learning Web Design',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
),
(
    'SET11007',
    'artificial-intelligence',
    'Artificial Intelligence',
    'Dr Lee',
    20,
    'This module introduces intelligent systems, machine learning fundamentals, search techniques, and AI applications.',
    'Coursework 40%, Final Exam 60%',
    'Friday 11:00 AM - 1:00 PM',
    '2026-04-05',
    'Artificial Intelligence: A Modern Approach',
    'https://moodle.napier.ac.uk/my/',
    'https://moodle.napier.ac.uk/my/'
);

INSERT INTO knowledge_sources (category, title, url, keywords, summary)
VALUES
(
    'modules',
    'Moodle Modules',
    'https://moodle.napier.ac.uk/my/',
    'module,modules,moodle,course,courses,subject,subjects,handbook',
    'Use this source for module access, course spaces, Moodle-based module guidance, and module-related student questions.'
),
(
    'fees and funding',
    'Fees and Funding',
    'https://my.napier.ac.uk/money',
    'fees,funding,tuition,scholarship,loan,bursary,payment,payments,cost,costs,money',
    'Use this source for fees, funding, tuition payments, bursaries, and money-related student questions.'
),
(
    'timetables',
    'Student Timetable',
    'https://my.napier.ac.uk/my/',
    'timetable,timetables,class,classes,schedule,schedules,lecture,lectures,room,rooms',
    'Use this source for timetable access and schedule-related student questions.'
);
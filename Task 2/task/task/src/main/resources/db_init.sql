-- Drop the table if it already exists
DROP TABLE IF EXISTS course_details;

-- Create the course_details table
CREATE TABLE course_details (
    course_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(255) NOT NULL,
    course_duration VARCHAR(50) NOT NULL,
    mentor_name VARCHAR(255),
    author_name VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Insert test data into course_details
INSERT INTO course_details (course_name, course_duration, mentor_name, author_name, description)
VALUES 
('Introduction to Java', 'THREE_MONTH', 'Jane Smith', 'John Doe', 'A comprehensive course on Java programming language'),
('Mastering Python', 'SIX_MONTH', 'Tom Brown', 'Emily White', 'An advanced course on Python with real-world examples'),
('Web Development Bootcamp', 'TWELVE_MONTH', 'Dwight Schrute', 'Michael Scott', 'Learn full-stack web development with hands-on projects'),
('Data Science with R', 'SIX_MONTH', 'James Green', 'Sophia Turner', 'Explore the world of data science using R programming'),
('React for Beginners', 'THREE_MONTH', 'Lisa Black', 'William Jones', 'A beginner-friendly course to master React.js'),
('Machine Learning A-Z', 'SIX_MONTH', 'Andrew Ng', 'Sebastian Thrun', 'Master the foundations of machine learning with practical projects'),
('Android App Development', 'TWELVE_MONTH', 'Chris Wilson', 'Laura Taylor', 'Learn to build Android apps from scratch using Kotlin'),
('DevOps with AWS', 'SIX_MONTH', 'Danielle Lewis', 'George Clark', 'Learn the fundamentals of DevOps with hands-on AWS projects'),
('Artificial Intelligence Foundations', 'TWELVE_MONTH', 'Elon Musk', 'Jeff Bezos', 'An in-depth exploration of AI techniques and tools'),
('Advanced Data Structures', 'THREE_MONTH', 'Alan Turing', 'Grace Hopper', 'Master the advanced data structures used in competitive programming'),
('Blockchain Fundamentals', 'THREE_MONTH', 'Vitalik Buterin', 'Satoshi Nakamoto', 'Learn the basics of blockchain and its applications in cryptocurrency'),
('Cybersecurity Essentials', 'SIX_MONTH', 'Kevin Mitnick', 'Elliot Anderson', 'Understand the key principles of cybersecurity and ethical hacking'),
('Cloud Computing with Azure', 'TWELVE_MONTH', 'Satya Nadella', 'Scott Guthrie', 'Comprehensive cloud computing course using Microsoft Azure'),
('iOS App Development', 'SIX_MONTH', 'Craig Federighi', 'Johnny Ive', 'Create iOS apps using Swift and Xcode'),
('Big Data Analytics', 'TWELVE_MONTH', 'Cloudera Team', 'Hadoop Experts', 'Discover how to work with large datasets using Hadoop and Spark'),
('Quantum Computing Basics', 'SIX_MONTH', 'Richard Feynman', 'David Deutsch', 'An introduction to the concepts of quantum computing'),
('Agile Project Management', 'THREE_MONTH', 'Scrum Master', 'Agile Coach', 'Learn how to manage projects effectively using Agile methodologies'),
('UI/UX Design Fundamentals', 'SIX_MONTH', 'Don Norman', 'Steve Jobs', 'Develop an understanding of user interface and user experience design'),
('Networking and Security', 'TWELVE_MONTH', 'Linus Torvalds', 'Richard Stallman', 'Learn about computer networks and how to secure them against threats'),
('Digital Marketing Masterclass', 'THREE_MONTH', 'Neil Patel', 'Gary Vaynerchuk', 'Master the essentials of digital marketing, including SEO and social media');

-- schema.sql: create tables and sample data
CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  role ENUM('admin','student') NOT NULL DEFAULT 'student'
);

CREATE TABLE IF NOT EXISTS books (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  author VARCHAR(150),
  quantity INT NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS borrows (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  book_id INT NOT NULL,
  borrow_date DATE DEFAULT CURRENT_DATE,
  return_date DATE,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (book_id) REFERENCES books(id)
);

-- sample users (passwords are plain-text for demo only)
INSERT IGNORE INTO users (username, password, role) VALUES
('admin', 'admin123', 'admin'),
('student1', 'stud123', 'student');

-- sample books
INSERT IGNORE INTO books (title, author, quantity) VALUES
('Introduction to Java', 'Author A', 3),
('Database Systems', 'Author B', 2),
('Data Structures', 'Author C', 4);

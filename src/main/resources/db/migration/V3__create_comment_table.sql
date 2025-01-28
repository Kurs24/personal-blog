CREATE TABLE comment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    post_id INT,
    name VARCHAR(100),
    email VARCHAR(100),
    body TEXT,
    created_at BIGINT,
    FOREIGN KEY (post_id) REFERENCES post(id)
) ENGine=InnoDB DEFAULT CHARSET=UTF8MB3;
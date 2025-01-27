CREATE TABLE post (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    body VARCHAR(255),
    slug VARCHAR(255),
    is_published BOOLEAN,
    is_deleted BOOLEAN,
    created_at BIGINT,
    updated_at BIGINT,
    published_at BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
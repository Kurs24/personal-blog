CREATE TABLE post (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255),
    body VARCHAR(255),
    slug VARCHAR(255),
    isPublished BOOLEAN,
    isDeleted BOOLEAN,
    categoryId INT,
    createdAt BIGINT,
    updatedAt BIGINT,
    publishedAt BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE IF NOT EXISTS post (
    id BIGINT NOT NULL,
    title VARCHAR(50) NOT NULL,
    
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS post_comment (
    id BIGINT NOT NULL,
    review VARCHAR(100) NOT NULL,
    post_id BIGINT NOT NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (post_id) REFERENCES post (id)
);

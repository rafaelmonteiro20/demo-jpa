DELETE FROM post_comment;
DELETE FROM post;

INSERT INTO post (id, title) VALUES (1, 'JPA and Hibernate');
INSERT INTO post (id, title) VALUES (2, 'JOOQ');

INSERT INTO post_comment (id, review, post_id) VALUES (1, 'Comment N-1', 1);
INSERT INTO post_comment (id, review, post_id) VALUES (2, 'Comment N-2', 1);
INSERT INTO post_comment (id, review, post_id) VALUES (3, 'Comment N-3', 1);
INSERT INTO post_comment (id, review, post_id) VALUES (4, 'Comment N-4', 1);
INSERT INTO post_comment (id, review, post_id) VALUES (5, 'Comment N-5', 2);

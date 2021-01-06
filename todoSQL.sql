use `todos_db`;

create table `users`(
 `id` int AUTO_INCREMENT PRIMARY KEY,
`full_name` varchar(120) NOT NULL,
`user_name` varchar(150) NOT NULL,
`encrypted_password` varchar(500) NOT NULL
 
);

create table user_authorities
(
    `id`        int AUTO_INCREMENT PRIMARY KEY,
    `authority` varchar(50) not null,
    `user_id`   int NOT NULL,
    CONSTRAINT `fk_authorities` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

create table todos
(
    `id`        	bigint AUTO_INCREMENT PRIMARY KEY,
    `description`   varchar(500) NOT NULL,
    `due_date`		date NOT NULL,
    `done` 			boolean default false,
    `user_id`		int NOT NULL,
    CONSTRAINT `fk_todos` 
				FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

ALTER TABLE users AUTO_INCREMENT = 3000;
ALTER TABLE user_authorities AUTO_INCREMENT = 500;

SELECT * FROM users;
SELECT * FROM todos;
SELECT * FROM user_authorities;

DELETE FROM users WHERE user_name='mail@example.com';
CREATE TABLE ranked_list (
    id SERIAL PRIMARY KEY,
    list VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    theme VARCHAR(255) NOT NULL,
    createdAt DATE NOT NULL,
    updatedAt DATE NOT NULL
);

CREATE TABLE classement (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    ranked_list_id INT NOT NULL,
    createdAt DATE NOT NULL,
    updatedAt DATE NOT NULL,
    FOREIGN KEY(ranked_list_id) REFERENCES ranked_list(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ranked (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255),
    classement_id int NOT NULL,
    createdAt DATE NOT NULL,
    updatedAt DATE NOT NULL,
    FOREIGN KEY(classement_id) REFERENCES classement(id) ON DELETE CASCADE ON UPDATE CASCADE
);
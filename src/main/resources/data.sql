CREATE TABLE tier_list (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    theme VARCHAR(255) NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE NOT NULL
);

CREATE TABLE classement (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    tier_list_id INT NOT NULL,
    created_at DATE NOT NULL,
    updated_at DATE NOT NULL,
    FOREIGN KEY(tier_list_id) REFERENCES tier_list(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ranked (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image VARCHAR(255),
    classement_id int,
    created_at DATE NOT NULL,
    updated_at DATE NOT NULL,
    FOREIGN KEY(classement_id) REFERENCES classement(id) ON DELETE CASCADE ON UPDATE CASCADE
);
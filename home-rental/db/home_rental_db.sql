/* SQL Code for the Home Rental DataBase (MySQL Design) */
/* USER CONNECTION :
 *      - user : airboy_hrp
 *      - password : gould1in8
 *
 * DATABASE INFORMATIONS :
 *      - name : airboy_hr_db
 */

 USE airboy_hr_db;

/* User space */
CREATE TABLE user (
    user_id VARCHAR(36) NOT NULL,
    username VARCHAR(30) NOT NULL,
    name VARCHAR(30) NOT NULL,
    firstname VARCHAR(30) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(70) NOT NULL,
    role VARCHAR(12) NOT NULL,
    created TIMESTAMP DEFAULT NOW(),
    enabled BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (user_id),
    UNIQUE KEY (user_id)
)TYPE=InnoDB;

/* Property space */
CREATE TABLE property (
    property_id INT NOT NULL auto_increment,
    owner VARCHAR(36) NOT NULL,
    title VARCHAR(120) NOT NULL,
    added TIMESTAMP DEFAULT NOW(),
    modified TIMESTAMP NOT NULL,
    short_desc VARCHAR(255) NOT NULL,
    long_desc TEXT NOT NULL,
    price INT NOT NULL,
    type VARCHAR(10) NOT NULL,
    rooms INT NOT NULL,
    country VARCHAR(45) NOT NULL,
    city VARCHAR(45) NOT NULL,
    address VARCHAR(255) NOT NULL,
    coordinates VARCHAR(45),
    note INT,
    rent_period_start DATETIME NOT NULL,
    rent_period_stop DATETIME NOT NULL,
    PRIMARY KEY (property_id),
    CONSTRAINT FOREIGN KEY (owner) REFERENCES user (user_id)
)TYPE=InnoDB;

CREATE TABLE property_options (
    property_options_id INT NOT NULL auto_increment,
    target_property INT NOT NULL,
    parking BOOLEAN DEFAULT FALSE,
    swimming_pool BOOLEAN DEFAULT FALSE,
    wifi BOOLEAN DEFAULT FALSE,
    laundry BOOLEAN DEFAULT FALSE,
    /* Add more options here */
    PRIMARY KEY (property_options_id),
    CONSTRAINT FOREIGN KEY (target_property) REFERENCES property (property_id)
)TYPE=InnoDB;

CREATE TABLE picture (
    picture_id INT NOT NULL auto_increment,
    target_property INT NOT NULL,
    filename VARCHAR(50) NOT NULL,
    extension VARCHAR(4) NOT NULL,
    path TEXT NOT NULL,
    size INT NOT NULL,
    added TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (picture_id),
    CONSTRAINT FOREIGN KEY (target_property) REFERENCES property (property_id)
)TYPE=InnoDB;

CREATE TABLE evaluation (
    evaluation_id INT NOT NULL auto_increment,
    target_property INT NOT NULL,
    cleanliness INT NOT NULL,
    confort INT NOT NULL,
    qa_price INT NOT NULL,
    PRIMARY KEY (evaluation_id),
    CONSTRAINT FOREIGN KEY (target_property) REFERENCES property (property_id)
)TYPE=InnoDB;

CREATE TABLE comment (
    comment_id INT NOT NULL auto_increment,
    creator VARCHAR(36) NOT NULL,
    target_property INT NOT NULL,
    date TIMESTAMP DEFAULT NOW(),
    modified TIMESTAMP NOT NULL,
    message TEXT NOT NULL,
    PRIMARY KEY (comment_id),
    CONSTRAINT FOREIGN KEY (creator) REFERENCES user (user_id),
    CONSTRAINT FOREIGN KEY (target_property) REFERENCES property (property_id)
)TYPE=InnoDB;

/* Reservations space */
CREATE TABLE reservation (
    reservation_id INT NOT NULL auto_increment,
    target_user VARCHAR(36) NOT NULL,
    target_property INT NOT NULL,
    date_rent_start DATETIME NOT NULL,
    date_rent_stop DATETIME NOT NULL,
    hosts INT NOT NULL,
    price INT NOT NULL,
    evaluated BOOLEAN DEFAULT FALSE,
    commented BOOLEAN DEFAULT FALSE,
    note INT,
    PRIMARY KEY (reservation_id),
    CONSTRAINT FOREIGN KEY (target_user) REFERENCES user (user_id),
    CONSTRAINT FOREIGN KEY (target_property) REFERENCES property (property_id)
)TYPE=InnoDB;
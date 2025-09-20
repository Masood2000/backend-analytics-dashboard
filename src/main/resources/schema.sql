CREATE TABLE passengers (
                            passenger_id INT PRIMARY KEY,
                            survived INT,
                            pclass INT,
                            name VARCHAR(255),
                            sex VARCHAR(10),
                            age DOUBLE,
                            sib_sp INT,
                            parch INT,
                            ticket VARCHAR(50),
                            fare DOUBLE,
                            cabin VARCHAR(50),
                            embarked VARCHAR(10)
);

CREATE TABLE saved_query (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             query_text VARCHAR(1000)
);
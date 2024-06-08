-- Create a table with 10 random columns
CREATE TABLE random_data (
    id SERIAL PRIMARY KEY,
    column1 INT,
    column2 VARCHAR(255),
    column3 DATE,
    column4 BOOLEAN,
    column5 REAL,
    column6 TEXT,
    column7 TIMESTAMP,
    column8 INTEGER
);

-- Fill the table with 10 million random data
INSERT INTO random_data (column1, column2, column3, column4, column5, column6, column7, column8)
SELECT
    FLOOR(RANDOM() * 1000),
    MD5(RANDOM()::TEXT),
    DATE '2020-01-01' + RANDOM() * INTERVAL '1 year',
    RANDOM() < 0.5,
    RANDOM() * 100,
    MD5(RANDOM()::TEXT),
    NOW() + INTERVAL '1 hour' * RANDOM(),
    FLOOR(RANDOM() * 1000)
FROM
    generate_series(1, 10000000);

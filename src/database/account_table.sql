CREATE TABLE account (
    acc_no NUMBER PRIMARY KEY,
    acc_holdername VARCHAR2(50),
    balance NUMBER
);

INSERT INTO account VALUES (12345, 'Tim', 7000);
INSERT INTO account VALUES (23456, 'Steve', 1000);

COMMIT;

--liquibase formatted sql

--changeset nkozlov:accounts
CREATE TABLE accounts
(
    id                 UUID             NOT NULL PRIMARY KEY,
    name               varchar(250)     NOT NULL,
    account_number     varchar(16)      NOT NULL,
    value              NUMERIC          NOT NULL,
    pin                varchar(250)     NOT NULL
);
CREATE INDEX users_name_index ON accounts (name);
CREATE INDEX users_account_number_index ON accounts (account_number);
--comment: Добавлена таблица accounts

--changeset nkozlov:transactions
CREATE TABLE transactions
(
    id                      UUID          NOT NULL PRIMARY KEY,
    account_id              UUID          NOT NULL,
    account_transfer_to     UUID          ,
    value                   NUMERIC       NOT NULL,
    type                    varchar(10)   NOT NULL,
    status                  varchar(10)   NOT NULL,
    error                   varchar(250)  ,
    created_at              timestamp     NOT NULL,
    CONSTRAINT fk_account
          FOREIGN KEY(account_id)
    	  REFERENCES accounts(id),
    CONSTRAINT fk_account_transfer_to
              FOREIGN KEY(account_transfer_to)
        	  REFERENCES accounts(id)
);
CREATE INDEX transactions_account_id_index ON transactions (account_id);
--comment: Добавлена таблица transactions
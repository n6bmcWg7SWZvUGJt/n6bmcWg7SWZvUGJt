-- begin ONLINEMARKET_ORDER
create table ONLINEMARKET_ORDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CUSTOMER_ID varchar(36),
    DISCOUNT integer,
    ORDER_PRICE decimal(19, 2),
    NO_ varchar(255),
    SHIPMENT_ID varchar(36),
    --
    primary key (ID)
)^
-- end ONLINEMARKET_ORDER
-- begin ONLINEMARKET_CUSTOMER
create table ONLINEMARKET_CUSTOMER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    sdtype varchar(31),
    --
    STYPE varchar(255),
    FULL_NAME varchar(255),
    ADDRESS_ID varchar(36),
    EMAIL varchar(255),
    --
    primary key (ID)
)^
-- end ONLINEMARKET_CUSTOMER
-- begin DAWD
create table dawd (
    ID varchar(36) not null,
    --
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    PATRONYMIC varchar(255),
    --
    primary key (ID)
)^
-- end DAWD
-- begin DAWDD
create table dawdd (
    ID varchar(36) not null,
    --
    LEGAL_NAME varchar(255),
    --
    primary key (ID)
)^
-- end DAWDD
-- begin SEC_USER
alter table SEC_USER add column CUSTOMER_ID varchar(36) ^
alter table SEC_USER add column DTYPE varchar(100) ^
update SEC_USER set DTYPE = 'sec$User' where DTYPE is null ^
-- end SEC_USER

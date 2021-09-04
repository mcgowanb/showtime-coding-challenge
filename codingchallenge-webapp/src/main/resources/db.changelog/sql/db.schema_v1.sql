create table url_metadata
(
    id         bigint primary key not null,
    fqdn       varchar(150),
    created_at timestamptz        not null,
    updated_at timestamptz        not null
);

create sequence URL_SEQ increment by 1 start 1000000 cache 1 owned by url_metadata.id;

-- INSERT INTO public.example
-- (id, user_message, created_at, updated_at)
-- VALUES(nextval('EXAMPLE_SEQ'), 'user message from the database', NOW(), NOW());

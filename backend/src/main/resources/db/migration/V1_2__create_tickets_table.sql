CREATE TABLE tickets (
    id uuid primary key,
    support_user_id uuid null,
    subject varchar(200) not null,
    description text not null,
    status varchar(200) not null,
    created_at timestamp not null,
    created_by uuid null,
    updated_at timestamp null,
    updated_by uuid null
)
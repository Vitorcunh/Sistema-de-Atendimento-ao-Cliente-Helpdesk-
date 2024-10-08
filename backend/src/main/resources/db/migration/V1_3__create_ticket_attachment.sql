CREATE TABLE ticket_attachments (
    id uuid primary key,
    ticket_id uuid null,
    ticket_interaction_id uuid null,
    filename varchar(200) not null,
    created_at timestamp not null,
    created_by uuid null,
    updated_at timestamp null,
    updated_by uuid null
)
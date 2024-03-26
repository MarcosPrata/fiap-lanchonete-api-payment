CREATE TABLE payment (
	id bigserial NOT NULL,
    order_id bigserial NOT NULL,
    total_amount float NOT NULL,
    payment_status varchar(20) NOT NULL,
    created_at timestamp NOT NULL default now(),
    updated_at timestamp NOT NULL default now(),

	CONSTRAINT payment_pkey PRIMARY KEY (id)
);
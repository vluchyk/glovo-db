CREATE TABLE IF NOT EXISTS public.customers
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name character varying(64) NOT NULL,
    phone character varying(16),
    CONSTRAINT customers_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.addresses
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    street character varying(64) NOT NULL,
    "number" character varying(16),
    apartment_number bigint,
    CONSTRAINT addresses_pkey PRIMARY KEY (id)
    );

CREATE TABLE IF NOT EXISTS public.orders
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    "number" bigint NOT NULL,
    customer_id bigint NOT NULL,
    address_id bigint NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT address_id FOREIGN KEY (address_id)
    REFERENCES public.addresses (id) MATCH SIMPLE,
    CONSTRAINT customer_id FOREIGN KEY (customer_id)
    REFERENCES public.customers (id) MATCH SIMPLE
    );

CREATE TABLE IF NOT EXISTS public.products
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    name character varying(32) NOT NULL,
    cost double precision,
    order_id bigint NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id),
    CONSTRAINT order_id FOREIGN KEY (order_id)
    REFERENCES public.orders (id) MATCH SIMPLE
    );
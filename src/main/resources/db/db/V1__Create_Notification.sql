create table if not exists notification (
	id numeric,
	actor_username varchar(75),
	subscriber_username varchar(75),
	subscriber_email varchar(75),
	notification_message varchar(100),
	notification_process_status varchar(100),
	created_at date,
	updated_at date
);

create sequence if not exists notification_sequence;




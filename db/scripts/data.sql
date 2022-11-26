INSERT INTO db.publishers (id_number,full_name,address) VALUES
	 ('987654321','some dude','Yerushalaim'),
	 ('123456789','Ravid Gontov','Metzulot Yam 16 Givatayim');

INSERT INTO db.users (id_number,password) VALUES
	 ('987654321','$2a$12$lWO6hd814Ts4QNDoPQh.PONSEh6y.5aPyph9/BYltWMyE4JFXZ68C'),
	 ('123456789','$2a$12$SNVIPA4sxpc.n7nmAtK0AuwrFdgtYLe5P4I2Rpev1Vo6j4uIeva1K');

INSERT INTO db.garments (garment_type,publisher_id,`size`,description,price) VALUES
	 ('Pants','123456789','XL','A normal pair of pants',127.0),
	 ('Shirt','123456789','L','Ask Yaniv :)',0.09),
	 ('Socks','987654321','M','The worlds most expensive socks',9001.0),
	 ('Hat','987654321','S','Typically worn backwards',7.0),
	 ('Hoodie','987654321','XS',NULL,6.0);

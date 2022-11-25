INSERT INTO db.publishers (id_number,full_name,address) VALUES
	 ('123456782','some dude','Yerushalaim'),
	 ('305707754','Ravid Gontov','Metzulot Yam 16 Givatayim');

INSERT INTO db.garments (garment_type,publisher_id_number,`size`,description,price) VALUES
	 ('Pants','305707754','XL','A normal pair of pants',127.0),
	 ('Shirt','305707754','L','Ask Yaniv :)',0.09),
	 ('Socks','123456782','M','The worlds most expensive socks',9001.0),
	 ('Hat','123456782','S','Typically worn backwards',7.0),
	 ('Hoodie','123456782','XS',NULL,6.0);

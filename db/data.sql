INSERT INTO db.publishers (id_number,full_name,address) VALUES
	 ('987654321','some dude','Yerushalaim'),
	 ('123456789','Ravid Gontov','Metzulot Yam 16 Givatayim');

INSERT INTO db.users (id_number,password) VALUES
	 ('987654321','$2a$10$.jHP4v6v4/cyrEIfI/2gROpYwChusJDhxUPTJ5Vy2giBXCVthKV2O'), -- some123
	 ('123456789','$2a$10$0utaC1S8xT7Z0kN2vslEg.3MB.3Ki0WGzFqJG6DYj71go23h5gMCi'); -- ravid123

INSERT INTO db.garments (garment_type,publisher_id,`size`,description,price) VALUES
	 ('Pants','123456789','XL','A normal pair of pants',127.0),
	 ('Shirt','123456789','L','Ask Yaniv :)',0.09),
	 ('Socks','987654321','M','The worlds most expensive socks',9001.0),
	 ('Hat','987654321','S','Typically worn backwards',7.0),
	 ('Hoodie','987654321','XS',NULL,6.0);

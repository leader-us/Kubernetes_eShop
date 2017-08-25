--创建product表
CREATE TABLE IF NOT EXISTS `product`(
   `id` INT,
   `name` VARCHAR(20),
   `price` DECIMAL(10,2),
   `desc` VARCHAR(20),
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
update clients set discount = 2 where phone like '9%'; 
select * from clients;

select c.name_r, d.percent from clients c left join discounts d on d.id = c.discount;

select count(c.name_r), d.percent from clients c left join discounts d on d.id = c.discount group by d.percent;

select * from travels where destination in (select id from destinations where destination like '%d%');

select * from travels t 
left join destinations d on t.destination = d.id

/*Count of clients with discounts ( without NULL )*/
SELECT percent, COUNT(clients.id) FROM clients Inner join discounts ON clients.discount = discounts.id Group by discount

/*Count of clients with discounts ( with null )*/
SELECT percent, COUNT(clients.id) FROM clients Inner join discounts ON clients.discount = discounts.id Group by discount;

INSERT INTO `clients` VALUES (809, '?\"Имя человека"','Какой-то адресс','крутой телефон',NULL,NULL)

UPDATE `clients` SET travel_id = 3 WHERE adress LIKE '%днепр%';
UPDATE `clients` SET travel_id = 10 WHERE adress LIKE '%харьков%';

SELECT * from travels WHERE ID = any ( Select travel_id FROM clients );

SELECT AVG(price) FROM travels, clients WHERE clients.travel_id = travels.id;

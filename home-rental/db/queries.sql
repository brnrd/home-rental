/*
SQL Query for search engine
Find all property in a radius of 25 miles from the request location.
*/

SELECT property_id, ( 3959 * acos( cos( radians(40) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(-74) ) + sin( radians(40) ) * sin( radians( latitude ) ) ) ) AS distance FROM property HAVING distance < 25 ORDER BY distance LIMIT 0 , 20;

/* SQL Query for request with contry */
SELECT p.property_id, p.title, p.rooms*2 AS places, po.parking AS parking FROM property AS p 
JOIN property_options AS po ON p.property_id=po.target_property 
WHERE p.country='USA' 
AND p.rent_period_start<='2013-04-25 00:00:00' 
AND p.rent_period_stop>='2013-04-26 00:00:00' 
AND NOT EXISTS (SELECT avg(r.hosts) AS guests_number FROM reservation AS r WHERE p.property_id=r.target_property AND r.date_rent_start<='2013-04-25 00:00:00'
AND '2013-04-26 00:00:00'<=r.date_rent_stop HAVING places < guests_number+2)

/* SQL Query by considering lat/lng parameters */
SELECT p.property_id, p.title, p.short_desc, p.price, p.city, p.country, p.type, p.note, p.rooms*2 AS places, p.latitude, p.longitude, po.parking, po.swimming_pool AS swim, po.wifi, po.laundry, ( 3959 * acos( cos( radians(41) ) * cos( radians( p.latitude ) ) * cos( radians( p.longitude ) - radians(-74) ) + sin( radians(41) ) * sin( radians( p.latitude ) ) ) ) AS distance 
FROM property AS p
JOIN property_options AS po ON p.property_id=po.target_property 
WHERE p.rent_period_start<='2013-05-25 00:00:00'
AND p.rent_period_stop>='2013-05-26 00:00:00' 
AND p.price BETWEEN 100 AND 200
AND NOT EXISTS (SELECT avg(r.hosts) AS guests_number FROM reservation AS r WHERE p.property_id=r.target_property AND r.date_rent_start<='2013-05-25 00:00:00'
AND '2013-05-26 00:00:00'<=r.date_rent_stop HAVING places < guests_number+2)
HAVING distance < 25
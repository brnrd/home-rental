/*
SQL Query for search engine
Find all property in a radius of 25 miles from the request location.
*/

SELECT property_id, ( 3959 * acos( cos( radians(40) ) * cos( radians( latitude ) ) * cos( radians( longitude ) - radians(-74) ) + sin( radians(40) ) * sin( radians( latitude ) ) ) ) AS distance FROM property HAVING distance < 25 ORDER BY distance LIMIT 0 , 20;

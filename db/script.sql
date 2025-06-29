CREATE TABLE countries (
    name VARCHAR(255),          
    population BIGINT,         
    is_member_of_un BOOLEAN   
);

INSERT INTO countries (name, population, is_member_of_un) VALUES 
('United States', 331002651, TRUE),
('China', 1393409038, TRUE),
('India', 1380004385, TRUE),
('North Korea', 25778816, TRUE),
('Vatican City', 801, FALSE); 


DELETE FROM countries;
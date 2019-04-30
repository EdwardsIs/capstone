INSERT INTO customer (id, customer_time, customer_name, address, city, state, zip, phone, email)
VALUES (1, CURRENT_TIMESTAMP, 'Harry Wu', '1314 Oak Level Rd.', 'Palm Springs', 'CA', '90213', '5556674242', 'harrywu@yahoo.com'),
(2, CURRENT_TIMESTAMP, 'Jason Barstow', '151 Goose Trail', 'Pilot Mountain', 'NC', '27065', '5559897700', 'jbarstow@outlook.com'),
(3, CURRENT_TIMESTAMP, 'Geri Wood', '231 Mountainview Dr.', 'Mt. Airy', 'NC', '27010', '5555006001', 'geriwood27@triad.rr.com'),
(4, CURRENT_TIMESTAMP, 'Susie Smith', '400 Smith Mountain Rd.', 'Winston Salem', 'NC', '27070', '5557726633', 's.smith@smiths.com'),
(5, CURRENT_TIMESTAMP, 'Henry Jones', '67 Big Rock Dr.', 'Concord', 'NC', '27032', '5553382221', 'hjones@jonescontracting.net'),
(6, CURRENT_TIMESTAMP, 'Mary Miller', '15 Red Dirt Trail', 'Richmond', 'VA', '27098', '5552103456', 'mmiller@me.com'),
(7, CURRENT_TIMESTAMP, 'Gary Greene', '888 Old Train Ln.', 'Denver', 'NC', '27054', '5551233210', 'greeneg@triad.rr.com'),
(8, CURRENT_TIMESTAMP, 'Michael Sipes', '499 Sipes Farm Rd.', 'King', 'NC', '27019', '5559994312', 'michaelsipes@sipesfarms.org'),
(9, CURRENT_TIMESTAMP, 'Darren James', '113 Big Sky Dr., Apt 4C', 'Winston-Salem', 'NC', '27070', '5559832276', 'jamesd@gmail.com'),
(10, CURRENT_TIMESTAMP, 'Philip McCormick', '99 White House Trail', 'Kernersville', 'NC', '27048', '5559098080', 'mccormick.p@peoplesconsulting.org');
INSERT INTO employee (id, employee_name, address, city, state, zip, phone, email)
VALUES (1, 'Ben Harrington', '298 Golf Course Rd.', 'King', 'NC', '27021', '5553668820', 'bharrington@gmail.com'),
(2, 'Gary Smith', '833 Apollo Dr.', 'Winston-Salem', 'NC', '27334', '5557774545', 'gsmith@me.com'),
(3, 'Darrell Grant', '400 Yellow Brick Rd.', 'Kernersville', 'NC', '28091', '5554066040', 'grant.darrell@outlook.com'),
(4, 'Pete Carroll', '1212 State Line Dr.', 'Cana', 'VA', '27903', '5555053113', 'pete.carroll@yahoo.com'),
(5, 'Harold Snipes', '46 Snipes Rd.', 'Asheboro', 'NC', '27047', '5552118654', 'haroldsnipes@hotmail.com'),
(6, 'Jake Summers', '111 Small Farm Trail', 'Mt. Airy', 'NC', '27035', '5552082255', 'jasummers@gmail.com'),
(7, 'Mickey Marshall', '312 Blueberry Ln.', 'Pilot Mountain', 'NC', '27050', '5558382009', 'mickeymarshall001@outlook.com'),
(8, 'Tim Gordon', '86 US Hwy 52', 'Winston-Salem', 'NC', '27034', '5554430992', 'gordon.t@outlook.com'),
(9, 'Brent Kavanaugh', '636 Big Sky Rd.', 'Durham', 'NC', '27099', '5557079991', 'brentk@gmail.com');
INSERT INTO vendor (id, vendor_name, address, city, state, zip, phone, email, contact_person)
VALUES (1, 'Top Cordurouy', '45 Big Apple Ln.', 'New York', 'NY', '12030', '5557473232', 'orders@topcordurouy.com', 'Kaylee Mickels'),
(2, 'Hitchcock Buttons', '1515 Big Corral St.', 'Houston', 'TX', '60057', '5559908212', 'service@hitchcockbuttons.org', 'Thomas Curran'),
(3, 'Full Spread Materials', '900 Goldsmith Ln.', 'Sacramento', 'CA', '99088', '5552098344', 'orders@fullspread.net', 'Kim Chen'),
(4, 'The Clothing Factory', '67 Cummings Rd.', 'Big Rock', 'OK', '40406', '5559887667', 'customer.service@clothingfactory.org', 'Kim Jackson'),
(5, '21 Designers', '2121 Main St.', 'Wytheville', 'OR', '88070', '5551112333', 'customerorders@21designers.com', 'Jay Farrell'),
(6, 'Cloth Wholesale', '902 Business Ln.', 'Blacksburg', 'KY', '33002', '5553345567', 'shipping@cwholesalers.com', 'Will Myers'),
(7, 'Accessories Emporium', '4666 33rd St.', 'Glenville', 'TN', '40888', '555454532', 'orderservice@accessoriese.net', 'Calvin Bircham'),
(8, 'Rugged Resources', 'Brown Bear Trail', 'Anchorage', 'AK', '99900', '5559889996', 'customer.orders@ruggedresources.com', 'Dick DuBose'),
(9, 'Bills Manufacturing', '8800 Klein Rd.', 'Los Angeles', 'CA', '90020', '5558889920', 'service@billsmanufacturing.com', 'Kenneth Bryce'),
(10, 'Satorial Supplies', '567 Hwy 89', 'Smoky', 'WV', '29907', '5556445443', 'shipping@satorialsupplies.net', 'Carrie Underwood');
INSERT INTO inventory (id, time_created, time_updated, item_name, amount_in_stock, sku, description, price)
VALUES (1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Black Tie', 5, '00001', 'A black silk tie', 9.95),
(2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Blue Tie', 3, '00002', 'A blue silk tie', 9.95),
(3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Green Tie', 8, '00003', 'A green silk tie', 9.95),
(4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Silver Tie Clips', 12, '00004', 'Package of 6 silver tie clips', 8.49),
(5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Gold Tie Clips', 5, '00005', 'Package of 6 gold tie clips', 8.49),
(6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Black 2-pc M', 2, '00006', 'Black medium 2-piece suit', 449.99),
(7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Grey 2-pc M', 3, '00007', 'Grey medium 2-piece suit', 449.99),
(8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Navy 2-pc M', 1, '00008', 'Navy medium 2-piece suit', 449.99),
(9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Black 2-pc L', 4, '00009', 'Black large 2-piece suit', 510.99),
(10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Grey 2-pc L', 6, '00010', 'Grey large 2-piece suit', 510.99),
(11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Navy 2-pc L', 5, '00011', 'Navy large 2-piece suit', 510.99);
INSERT INTO transaction_header (id, time_created, customer_id, employee_id, subtotal_amount, tax_amount)
VALUES (1, CURRENT_TIMESTAMP, 1, 1, 9.95, 0.67),
(2, CURRENT_TIMESTAMP, 2, 2, 9.95, 0.67),
(3, CURRENT_TIMESTAMP, 3, 3, 9.95, 0.67),
(4, CURRENT_TIMESTAMP, 4, 4, 8.49, 0.57),
(5, CURRENT_TIMESTAMP, 5, 5, 8.49, 0.57),
(6, CURRENT_TIMESTAMP, 6, 6, 449.99, 30.37),
(7, CURRENT_TIMESTAMP, 7, 7, 449.99, 30.37),
(8, CURRENT_TIMESTAMP, 8, 8, 449.99, 30.37),
(9, CURRENT_TIMESTAMP, 9, 9, 510.99, 34.49),
(10, CURRENT_TIMESTAMP, 10, 3, 510.99, 34.49);
INSERT INTO transaction_detail (id, time_created, transaction_header_id, inventory_id, quantity, price)
VALUES (1, CURRENT_TIMESTAMP, 1, 1, 1, 9.95),
(2, CURRENT_TIMESTAMP, 2, 2, 1, 9.95),
(3, CURRENT_TIMESTAMP, 3, 3, 1, 9.95),
(4, CURRENT_TIMESTAMP, 4, 4, 1, 8.49),
(5, CURRENT_TIMESTAMP, 5, 5, 1, 8.49),
(6, CURRENT_TIMESTAMP, 6, 6, 1, 449.99),
(7, CURRENT_TIMESTAMP, 7, 7, 1, 449.99),
(8, CURRENT_TIMESTAMP, 8, 8, 1, 449.99),
(9, CURRENT_TIMESTAMP, 9, 9, 1, 510.99),
(10, CURRENT_TIMESTAMP, 10, 10, 1, 510.99);


INSERT INTO categories (name) VALUES
        ('Mountain Bikes'),
        ('Road Bikes'),
        ('Gravel Bikes'),
        ('Hybrid Bikes'),
        ('E-Bikes'),
        ('Bike Frames'),
        ('Wheels & Tires'),
        ('Drivetrain'),
        ('Brakes'),
        ('Handlebars & Stems'),
        ('Suspension'),
        ('Pedals'),
        ('Saddles'),
        ('Accessories'),
        ('Apparel'),
        ('Tools & Maintenance');

INSERT INTO products (name, price, category_id, description) VALUES
        ('Hudski Doggler (gravel)', 1499.99, 3, 'Do-it-all rigid mountain bike set up for gravel riding.'),
        ('Hudski Doggler (mountain)', 1699.99, 1, 'Do-it-all rigid mountain bike set up for mountain riding.'),
        ('Hudski Doggler (city)', 1499.99, 4, 'Do-it-all rigid mountain bike set up for city riding.'),
        ('Trek Marlin 7', 899.99, 1, 'Hardtail mountain bike ideal for trail riding.'),
        ('Specialized Rockhopper', 749.99, 1, 'Entry-level mountain bike with hydraulic disc brakes.'),
        ('Giant TCR Advanced 1', 2899.99, 2, 'Carbon road bike built for speed.'),
        ('Cervelo R5 Ultegra', 5899.99, 2, 'High-performance road bike for competitive racing.'),
        ('Cannondale Topstone 2', 1999.99, 3, 'Aluminum gravel bike with endurance geometry.'),
        ('Specialized Diverge E5', 1499.99, 3, 'Adventure-ready gravel bike.'),
        ('Cannondale Quick 4', 850.00, 4, 'Lightweight fitness hybrid bike.'),
        ('Trek FX 3 Disc', 899.00, 4, 'Versatile hybrid bike with disc brakes.'),
        ('Specialized Turbo Vado 4.0', 3799.99, 5, 'Class 3 electric commuter bike.'),
        ('Giant Explore E+ 2', 3199.99, 5, 'Powerful electric bike for city riding and touring.'),
        ('Santa Cruz Tallboy Frame', 2299.00, 6, 'Full-suspension carbon frame.'),
        ('Surly Ogre Frameset', 799.00, 6, 'Steel adventure touring frame.'),
        ('Maxxis Minion DHF 29x2.5', 74.99, 7, 'Aggressive mountain tire.'),
        ('WTB Venture 700x50', 64.99, 7, 'All-terrain gravel tire.'),
        ('Shimano Deore XT M8100 Cassette', 129.99, 8, '12-speed wide range cassette.'),
        ('SRAM GX Eagle Chain', 34.99, 8, 'Durable 12-speed chain.'),
        ('Shimano Deore M6100 Brakeset', 189.99, 9, 'Trail-ready hydraulic brakes.'),
        ('SRAM Level TL Brakes', 149.99, 9, 'Lightweight cross-country brakes.'),
        ('RaceFace Chester Handlebar', 49.99, 10, 'Durable alloy handlebar.'),
        ('Zipp Service Course SL Stem', 112.00, 10, 'Lightweight road stem.'),
        ('RockShox Pike Ultimate 29', 899.00, 11, 'Premium 29-inch suspension fork.'),
        ('Fox Float DPS Factory Shock', 459.00, 11, 'High-performance rear shock.'),
        ('Crankbrothers Stamp 7 Pedals', 149.99, 12, 'Durable flat pedals.'),
        ('Shimano 105 Pedals', 129.99, 12, 'Clipless road pedals.'),
        ('WTB Volt Saddle', 59.99, 13, 'Comfortable performance saddle.'),
        ('CO2 cartridge', 4.99, 16, 'A cartridge of compressed CO2');

INSERT INTO users (name, email, password, phone_number, role) VALUES
        ('Ryan Griffin', 'ryan@example.com', 'hashed_pw_1', '(555)555-0000', 'ADMIN'),
        ('Nate Grossman', 'nate.g@example.com', 'hashed_pw_2', '(555)555-0001', 'USER'),
        ('Abbey Campbell', 'abbey.c@example.com', 'hashed_pw_3', '(555)555-0002', 'USER'),
        ('Adam Lizard', 'adam@thelizard.com', 'hashed_pw_4', '(555)555-0003', 'USER'),
        ('Mel Haasch', 'mel.h@example.com', 'hashed_pw_5', '(555)555-0004', 'USER'),
        ('Tomasso Pompei', 'tomasso.p@example.com', 'hashed_pw_6', '(555)555-0005', 'USER'),
        ('Dilan Brutal', 'dilan.b@example.com', 'hashed_pw_7', '(555)555-0006', 'USER'),
        ('Kyle Kelley', 'kyle@tracko.com', 'hashed_pw_8', '(555)555-0007', 'USER'),
        ('Shawn Wolf', 'shawn.w@example.com', 'hashed_pw_9', '(555)555-0008', 'USER'),
        ('Josh Holiss', 'josh.h@example.com', 'hashed_pw_10', '(555)555-0009', 'USER'),
        ('Mick Tacos', 'mick.t@example.com', 'hashed_pw_11', '(555)555-0010', 'USER'),
        ('Sean Carabin', 'seancho@example.com', 'hashed_pw_12', '(555)555-0011', 'USER'),
        ('Bryan Nemo', 'bryan.n@example.com', 'hashed_pw_13', '(555)555-0012', 'USER'),
        ('Alicia Lopez', 'alicia.l@example.com', 'hashed_pw_14', '(555)555-0013', 'USER'),
        ('Raff Pandas', 'raff@trashpandas.com', 'hashed_pw_15', '(555)555-0014', 'USER'),
        ('Annemarie Pandas', 'annemarie@genderexpansiveride.com', 'hashed_pw_16', '(555)555-0015', 'USER'),
        ('Jonathan Straus', 'jon@hausofstraus.com', 'hashed_pw_17', '(555)555-0016', 'USER'),
        ('Jannick Frampton', 'jannick.f@example.com', 'hashed_pw_18', '(555)555-0017', 'USER'),
        ('Justin Han', 'plsfix@lametro.com', 'hashed_pw_19', '(555)555-0018', 'USER'),
        ('Tio Rick', 'tiorick@pedaltotheshin.com', 'hashed_pw_20', '(555)555-0019', 'USER'),
        ('Phillip Ramirez', 'phiiiillllll@example.com', 'hashed_pw_21', '(555)555-0020', 'USER'),
        ('Kyle Ponce', 'kyle@pizzagravel.com', 'hashed_pw_22', '(555)555-0021', 'USER'),
        ('Alex Niknejad', 'alex.n@example.com', 'hashed_pw_23', '(555)555-0022', 'USER'),
        ('Aldrich Villanueva', 'aldrich.v@example.com', 'hashed_pw_24', '(555)555-0023', 'USER'),
        ('Roy Ramirez', 'roy.r@example.com', 'hashed_pw_25', '(555)555-0024', 'USER'),
        ('Daniel Paisative', 'daniel.p@example.com', 'hashed_pw_26', '(555)555-0025', 'USER'),
        ('Will Benghauser', 'will.b@example.com', 'hashed_pw_27', '(555)555-0026', 'USER'),
        ('Jonathan F', 'jon@routinecyclist.com', 'hashed_pw_28', '(555)555-0027', 'USER'),
        ('Bryan Arita', 'bryan.a@example.com', 'hashed_pw_29', '(555)555-0028', 'USER'),
        ('Tucker XCX', 'tucker.x@example.com', 'hashed_pw_30', '(555)555-0029', 'USER'),
        ('Errin Vasquez', 'errin@area45.com', 'hashed_pw_31', '(555)555-0030', 'USER');

INSERT INTO addresses (street, city, state, zip, user_id) VALUES
        ('123 Oakwood Dr', 'Los Angeles', 'CA', '90001', 1),
        ('44 Sunset Ridge', 'Pasadena', 'CA', '91101', 2),
        ('87 Willow Lane', 'Burbank', 'CA', '91501', 3),
        ('22 Mesa Verde Ct', 'Glendale', 'CA', '91203', 4),
        ('191 Crosshill Ave', 'Long Beach', 'CA', '90802', 5),
        ('301 Harbor St', 'Santa Monica', 'CA', '90401', 6),
        ('52 Grove Terrace', 'North Hollywood', 'CA', '91601', 7),
        ('78 Castlerock Rd', 'Culver City', 'CA', '90230', 8),
        ('19 Ocean Breeze Dr', 'Redondo Beach', 'CA', '90277', 9),
        ('145 Ridgecrest Dr', 'El Segundo', 'CA', '90245', 10),
        ('82 Timberline Way', 'Inglewood', 'CA', '90301', 11),
        ('901 Pinecrest Pl', 'San Pedro', 'CA', '90731', 12),
        ('65 Maple Row', 'Downey', 'CA', '90240', 13),
        ('78 Jordana Dr', 'Whittier', 'CA', '90601', 14),
        ('230 Coastline Dr', 'Malibu', 'CA', '90265', 15),
        ('54 Greenstone Ct', 'Hawthorne', 'CA', '90250', 16),
        ('77 Moorpark Ct', 'Sherman Oaks', 'CA', '91403', 17),
        ('112 Canyon Park Ln', 'Arcadia', 'CA', '91006', 18),
        ('150 Ridgefield Ave', 'Monterey Park', 'CA', '91754', 19),
        ('220 Blue Heron Way', 'Lakewood', 'CA', '90712', 20),
        ('45 Palm Avenue', 'Anaheim', 'CA', '92801', 21),
        ('72 Valley Run Dr', 'Irvine', 'CA', '92614', 22),
        ('19 Canyon Crest Way', 'Fullerton', 'CA', '92831', 23),
        ('321 Meadow Point', 'Costa Mesa', 'CA', '92626', 24),
        ('86 Riverstone Rd', 'Huntington Beach', 'CA', '92648', 25),
        ('75 Foxtail Dr', 'Torrance', 'CA', '90501', 26),
        ('33 Crestwood Dr', 'Hermosa Beach', 'CA', '90254', 27),
        ('208 Lantern Ave', 'Manhattan Beach', 'CA', '90266', 28),
        ('974 Highland Oaks Blvd', 'Sierra Madre', 'CA', '91024', 29),
        ('602 Bayfront Walk', 'Newport Beach', 'CA', '92660', 30),
        ('442 Magnolia Ln', 'San Dimas', 'CA', '91773', 31);

INSERT INTO profiles (id, bio, date_of_birth, loyalty_points) VALUES
        (1, 'Coolest guy on two wheels! Owner of the bike shop.', '1993-04-12', 1000000),
        (2, 'Mountain bike weekend warrior.', '1990-01-07', 140),
        (3, 'Road cyclist and coffee lover.', '1995-11-22', 80),
        (4, 'Adventure touring enthusiast.', '1988-06-09', 300),
        (5, 'Casual rider exploring LA trails.', '1991-12-14', 50),
        (6, 'E-bike commuter.', '1992-03-02', 100),
        (7, 'Downhill MTB rider.', '1990-09-18', 330),
        (8, 'Gravel riding addict.', '1989-02-27', 190),
        (9, 'Triathlon amateur.', '1994-07-03', 60),
        (10, 'Road cycling hobbyist.', '1996-01-28', 70),
        (11, 'Hybrid bike commuter.', '1993-10-12', 40),
        (12, 'Bikepacker exploring SoCal.', '1987-05-11', 215),
        (13, 'Weekend cyclist.', '1990-03-14', 45),
        (14, 'Track cyclist.', '1997-12-09', 155),
        (15, 'Beach cruiser rider.', '1989-06-01', 25),
        (16, 'Bike mechanic hobbyist.', '1992-09-07', 130),
        (17, 'XC endurance cyclist.', '1986-02-15', 260),
        (18, 'Fitness cyclist.', '1998-10-30', 10),
        (19, 'Subway commuter and cyclist.', '1991-04-23', 75),
        (20, 'Night ride enthusiast.', '1988-11-03', 160),
        (21, 'Casual trail rider.', '1996-02-19', 12),
        (22, 'Road training beginner.', '1994-05-05', 42),
        (23, 'Touring cyclist.', '1990-03-18', 235),
        (24, 'Weekend adventurer.', '1993-07-08', 88),
        (25, 'Bike commuter.', '1995-09-19', 33),
        (26, 'Gravel explorer.', '1997-01-16', 144),
        (27, 'Group ride regular.', '1989-02-24', 101),
        (28, 'MTB trail builder volunteer.', '1991-11-29', 300),
        (29, 'Downhill racer.', '1987-08-10', 350),
        (30, 'Beach trail rider.', '1992-05-21', 56),
        (31, 'Urban commuter.', '1993-06-03', 28);

INSERT INTO wishlist (product_id, user_id) VALUES
        (7,1),
        (3,2), (16,2), (27,2),
        (14,3), (21,3), (29,3),
        (5,4), (6,4),
        (25,5), (8,5), (11,5), (12,5),
        (9,6), (10,6), (26,6),
        (17,7), (19,7), (22,7),
        (2,8), (15,8), (28,8),
        (4,9), (16,9), (18,9),
        (6,10), (20,10), (23,10),
        (13,11), (14,11), (19,11),
        (7,12),
        (8,20), (9,20);

INSERT INTO carts (id, date_created) VALUES
        (uuid_to_bin('11111111-1111-1111-1111-111111111111'), '2025-11-01'),
        (uuid_to_bin('22222222-2222-2222-2222-222222222222'), '2025-11-02'),
        (uuid_to_bin('33333333-3333-3333-3333-333333333333'), '2025-11-03'),
        (uuid_to_bin('44444444-4444-4444-4444-444444444444'), '2025-11-04'),
        (uuid_to_bin('55555555-5555-5555-5555-555555555555'), '2025-11-05'),
        (uuid_to_bin('66666666-6666-6666-6666-666666666666'), '2025-11-06'),
        (uuid_to_bin('77777777-7777-7777-7777-777777777777'), '2025-11-07'),
        (uuid_to_bin('88888888-8888-8888-8888-888888888888'), '2025-11-08'),
        (uuid_to_bin('99999999-9999-9999-9999-999999999999'), '2025-11-09'),
        (uuid_to_bin('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'), '2025-11-10'),
        (uuid_to_bin('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'), '2025-11-11'),
        (uuid_to_bin('cccccccc-cccc-cccc-cccc-cccccccccccc'), '2025-11-12'),
        (uuid_to_bin('dddddddd-dddd-dddd-dddd-dddddddddddd'), '2025-11-13'),
        (uuid_to_bin('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee'), '2025-11-14'),
        (uuid_to_bin('ffffffff-ffff-ffff-ffff-ffffffffffff'), '2025-11-15'),
        (uuid_to_bin('12121212-1212-1212-1212-121212121212'), '2025-11-16'),
        (uuid_to_bin('13131313-1313-1313-1313-131313131313'), '2025-11-17'),
        (uuid_to_bin('14141414-1414-1414-1414-141414141414'), '2025-11-18'),
        (uuid_to_bin('15151515-1515-1515-1515-151515151515'), '2025-11-19'),
        (uuid_to_bin('16161616-1616-1616-1616-161616161616'), '2025-11-20');

INSERT INTO cart_items (cart_id, product_id, quantity) VALUES
        (uuid_to_bin('11111111-1111-1111-1111-111111111111'), 1, 1),
        (uuid_to_bin('11111111-1111-1111-1111-111111111111'), 16, 2),
        (uuid_to_bin('11111111-1111-1111-1111-111111111111'), 17, 2),
        (uuid_to_bin('11111111-1111-1111-1111-111111111111'), 29, 10),

        (uuid_to_bin('22222222-2222-2222-2222-222222222222'), 4, 1),

        (uuid_to_bin('33333333-3333-3333-3333-333333333333'), 7, 1),
        (uuid_to_bin('33333333-3333-3333-3333-333333333333'), 28, 1),

        (uuid_to_bin('44444444-4444-4444-4444-444444444444'), 10, 1),
        (uuid_to_bin('44444444-4444-4444-4444-444444444444'), 26, 1),

        (uuid_to_bin('55555555-5555-5555-5555-555555555555'), 12, 1),

        (uuid_to_bin('66666666-6666-6666-6666-666666666666'), 3, 1),
        (uuid_to_bin('66666666-6666-6666-6666-666666666666'), 19, 1),

        (uuid_to_bin('77777777-7777-7777-7777-777777777777'), 14, 1),

        (uuid_to_bin('88888888-8888-8888-8888-888888888888'), 7, 1),
        (uuid_to_bin('88888888-8888-8888-8888-888888888888'), 9, 1),
        (uuid_to_bin('88888888-8888-8888-8888-888888888888'), 22, 1),

        (uuid_to_bin('99999999-9999-9999-9999-999999999999'), 5, 1),

        (uuid_to_bin('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'), 24, 1),
        (uuid_to_bin('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'), 17, 2),

        (uuid_to_bin('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'), 6, 1),

        (uuid_to_bin('cccccccc-cccc-cccc-cccc-cccccccccccc'), 18, 1),
        (uuid_to_bin('cccccccc-cccc-cccc-cccc-cccccccccccc'), 23, 1),

        (uuid_to_bin('dddddddd-dddd-dddd-dddd-dddddddddddd'), 2, 1),

        (uuid_to_bin('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee'), 8, 1),
        (uuid_to_bin('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee'), 7, 1),
        (uuid_to_bin('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee'), 8, 1),
        (uuid_to_bin('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee'), 20, 1),

        (uuid_to_bin('ffffffff-ffff-ffff-ffff-ffffffffffff'), 11, 1),

        (uuid_to_bin('12121212-1212-1212-1212-121212121212'), 15, 1),

        (uuid_to_bin('13131313-1313-1313-1313-131313131313'), 13, 1),
        (uuid_to_bin('13131313-1313-1313-1313-131313131313'), 27, 1),

        (uuid_to_bin('14141414-1414-1414-1414-141414141414'), 21, 1),

        (uuid_to_bin('15151515-1515-1515-1515-151515151515'), 25, 1),

        (uuid_to_bin('16161616-1616-1616-1616-161616161616'), 28, 1);

INSERT INTO orders (customer_id, status, total_price) VALUES
        (1,  'PAID',      1779.95),
        (2,  'PENDING',    899.99),
        (3,  'PAID',      5899.99),
        (4,  'PAID',      1999.99),
        (5,  'CANCELED',  799.00),
        (6,  'PAID',       749.99),
        (7,  'PAID',      3199.99),
        (8,  'PAID',       899.00),
        (9,  'PAID',      1499.99),
        (10, 'PAID',       189.99),
        (11, 'PENDING',    112.00),
        (12, 'PAID',       459.00),
        (13, 'PAID',       1053.93),
        (14, 'PAID',        64.99),
        (15, 'PAID',       149.99),
        (16, 'PAID',        34.99),
        (17, 'PAID',       129.99),
        (18, 'PAID',        59.99),
        (19, 'PAID',      3799.99),
        (20, 'PENDING',   1475.91);

INSERT INTO order_items (order_id, product_id, unit_price, quantity, total_price) VALUES
        (1, 1,  1499.99, 1, 1499.99),
        (1, 16,   74.99, 2, 149.98),
        (1, 17,   64.99, 2, 129.98),

        (2, 4,   899.99, 1, 899.99),

        (3, 7,  5899.99, 1, 5899.99),

        (4, 8,  1999.99, 1, 1999.99),

        (5, 15,  799.00, 1, 799.00),

        (6, 5,   749.99, 1, 749.99),

        (7, 13, 3199.99, 1, 3199.99),

        (8, 11,  899.00, 1, 899.00),

        (9, 3,  1499.99, 1, 1499.99),

        (10, 20, 189.99, 1, 189.99),

        (11, 23, 112.00, 1, 112.00),

        (12, 26, 459.00, 1, 459.00),

        (13, 24, 899.00, 1, 899.00),
        (13, 17,  64.99, 2,  129.98),
        (13, 29,  4.99, 5,  24.95),

        (14, 17,  64.99, 1,  64.99),

        (15, 27, 149.99, 1, 149.99),

        (16, 19,  34.99, 1,  34.99),

        (17, 18, 129.99, 1, 129.99),

        (18, 28,  59.99, 1,  59.99),

        (19, 12, 3799.99, 1, 3799.99),

        (20, 21, 189.99, 1, 189.99),
        (20, 17,  64.99, 1,  64.99),
        (20, 22, 149.99, 1, 149.99),
        (20, 23, 112.00, 8, 896.00),
        (20, 27, 149.99, 1, 149.99),
        (20, 29,  4.99, 5,  24.95);
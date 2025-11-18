-- This migration updates the passwords for the sample users created in V19__populate_database.
-- The placeholder plaintext values in V19 ('hashed_pw_<id>') were only used to seed the DB.
-- The placeholder passwords will be replaced with hashed passwords following this pattern:
--     "pw" + user_id    (e.g. user 1 → "pw1", user 2 → "pw2", etc.)

-- NOTE:
-- The application itself does NOT store plaintext passwords.
-- In normal usage, passwords are immediately hashed and inserted.

-- IMPORTANT:
-- This application is a practice/demonstration project, not a real-world production system.
-- For this reason, predictable test users and deterministic passwords are acceptable,
-- but this approach is NOT appropriate for real authentication in a production environment.

UPDATE users SET password = '$2a$10$Efk2iTQAVXMN/IlIT6NpO.DObB50/LkDpRPfO1kI4d8.j1QTm/ahS' WHERE id = 1;
UPDATE users SET password = '$2a$10$LcP6oxacfXykgWfDum1rS.VEsp5xtuIqvGIee.PwzEADKSmegNnBS' WHERE id = 2;
UPDATE users SET password = '$2a$10$boMmBv/kyZ310MUOtlQuxeTaaK4JQBdEoWKB90fPCWF47s/.NnTeW' WHERE id = 3;
UPDATE users SET password = '$2a$10$uRj0gpmgv5r87SO.pa6Mc.VSqD7E/VLvoJ9BsF5hBdlGouK20Flr.' WHERE id = 4;
UPDATE users SET password = '$2a$10$noWLiInImdWpzwuTqaYereAwPQZmivP.QZctWA/acBZiXpRwv8Yvm' WHERE id = 5;
UPDATE users SET password = '$2a$10$l9DpdVKUIRIXfjs1HLXI0e6PP8HFv.Zq/wczvsLf9O9DXrfkH7F06' WHERE id = 6;
UPDATE users SET password = '$2a$10$jZHHtwpB/DYJ7kAa01Pf5OxFBqfZZG1GcWWR0stftI4dgR1LVEz7G' WHERE id = 7;
UPDATE users SET password = '$2a$10$Z3fEEgNKeBv94wjSx9P7w.ka83R4QZ0k7qaIUgic4dVrFV4lJ8FwC' WHERE id = 8;
UPDATE users SET password = '$2a$10$3H9EaxUCTSMwXANeDstF8OziDAatTaXh9IL1BAoKQu4sOTb9TWy96' WHERE id = 9;
UPDATE users SET password = '$2a$10$nGvS9zO75SI6zMEl8IXbAem8/HzrsroXFsd3JCEJ738NeDg5vZEZ.' WHERE id = 10;
UPDATE users SET password = '$2a$10$/OUCM/G/SmaCLQ3VfHjVluT4u0/muEWyZmnbl63jEmQV7cg6MGZ9K' WHERE id = 11;
UPDATE users SET password = '$2a$10$/LXUl5uCNtbrQQolthy7PeWfqRTa8vH3dC4UfTq/ftmBM1EdKVM7e' WHERE id = 12;
UPDATE users SET password = '$2a$10$JoeCGFtPb3SkbiROssB4merHDWD3qAxnllIYYYRhzG9PG.peKeUxi' WHERE id = 13;
UPDATE users SET password = '$2a$10$R4gdcQxEjBeKHJZJOb4CSOYhhYHkpPSGjuMZWn12thjigQjxQRFMa' WHERE id = 14;
UPDATE users SET password = '$2a$10$HKtYXJFgzRXv3aG56qYd7.c1/kjRrIx6jH4YXkoONs80bf5adee76' WHERE id = 15;
UPDATE users SET password = '$2a$10$2Nn3XE9plyr0F3uSRnXlweCEwsF.K/0YQd12T5FyxyLRbUq8ifJPW' WHERE id = 16;
UPDATE users SET password = '$2a$10$BpjaRloCJHNtdm/nx0c9Nedj7uVlZwzGMLsSjis90cj1Ycf5pNZPq' WHERE id = 17;
UPDATE users SET password = '$2a$10$VF4e9lDOOcgA4ud7LL09aeE6pFoQmMXEnNvy4/5tcU.VLXaWUwAx.' WHERE id = 18;
UPDATE users SET password = '$2a$10$MYKfYL.fW1ObSjghc7PkPOBjTczphBPy7giUryzZdbh7MabQs/t62' WHERE id = 19;
UPDATE users SET password = '$2a$10$f2o/0/qXObICQL8WU84H0eUqUPYK81PZUq37sRtk5g/9/2zmPfdtS' WHERE id = 20;
UPDATE users SET password = '$2a$10$xPw7FOKiNe3QNi5XmT5UReOrQuLEEGC995Re.kolmQiJCyKRQKz4q' WHERE id = 21;
UPDATE users SET password = '$2a$10$4opV3EkWwrcAJOKlArRlY.hikFdV07a6HaLPZGF2qYg7fSWDLjLkW' WHERE id = 22;
UPDATE users SET password = '$2a$10$YYlZ8ziu38poynbZPcT7EOOldeuxqFcBMx5C2uTjJ1bLx5ujPqvcO' WHERE id = 23;
UPDATE users SET password = '$2a$10$/Urz/I.UO1ZKmtBw1LuKeeteCxmvhhn1hRY9tM8RqcEine2h0UfCu' WHERE id = 24;
UPDATE users SET password = '$2a$10$GVP7o/LMt9GbWBSwbXvm8uFfCEokQnNGnwJCBtGFOIqyAVYBnpKn.' WHERE id = 25;
UPDATE users SET password = '$2a$10$qe417A4cW5vly9UqB98.mekIsbMdXU35hltf6X4tR.18TQ5y0U1qO' WHERE id = 26;
UPDATE users SET password = '$2a$10$e.0Uy7U9n/hQ5WRkHxOoeOzCGMd.wR0udvTSjsRnIr8xiudpP5z9K' WHERE id = 27;
UPDATE users SET password = '$2a$10$hgXY6JkwDovvNYyJRQuc0e47E69/Or32luju4tRSdb5LoZyfyGC7m' WHERE id = 28;
UPDATE users SET password = '$2a$10$9jyCP.7z1zkYcABFWU06luWizVue07MTXnTzNuDo9ioiZc9tSAeke' WHERE id = 29;
UPDATE users SET password = '$2a$10$dXwLggiW/01/k/sWaaMqBen0wJuy2gEU5n0RWTQn3aAxq5bufZUc.' WHERE id = 30;
UPDATE users SET password = '$2a$10$qffzF3xCGPzxpBhEVeZOlutUaWRlhWrk998j2WXreahpUN9xAuH8O' WHERE id = 31;
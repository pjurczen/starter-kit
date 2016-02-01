insert into library (id, name) values (1, 'Politechnika Wroclawska');
insert into library (id, name) values (2, 'Capgemini');

insert into book (id, title, library_id) values (1, 'Pierwsza książka', 1);
insert into book (id, title, library_id) values (2, 'Druga książka', 1);
insert into book (id, title, library_id) values (3, 'Trzecia książka', 1);
insert into book (id, title, library_id) values (4, 'Czwarta ksiazka', 2);
insert into book (id, title, library_id) values (5, 'Piata książka', 2);

insert into author (id, first_name, last_name) values (7, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (8, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (9, 'Janusz', 'Jankowski');
insert into author (id, first_name, last_name) values (10, 'Janusz', 'Kubacki');
insert into author (id, first_name, last_name) values (11, 'Janusz', 'Zomb');

insert into book_author(book_id, author_id) values (1, 7);
insert into book_author(book_id, author_id) values (1, 9);
insert into book_author(book_id, author_id) values (2, 8);
insert into book_author(book_id, author_id) values (3, 9);
insert into book_author(book_id, author_id) values (4, 9);
insert into book_author(book_id, author_id) values (5, 9);
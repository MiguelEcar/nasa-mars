
CREATE TABLE mars_sol
(
  sol int NOT NULL,
  dateFirst timestamp without time zone NOT NULL,
  dateLast timestamp without time zone NOT NULL,
  average number(10,3) NOT NULL,
  minimum number(10,3) NOT NULL,
  maximum number(10,3) NOT NULL,

  PRIMARY KEY (sol)
);

CREATE TABLE last_fetch
(
  id integer auto_increment NOT NULL,
  fetch_date timestamp without time zone NOT NULL,

  PRIMARY KEY (id)
);

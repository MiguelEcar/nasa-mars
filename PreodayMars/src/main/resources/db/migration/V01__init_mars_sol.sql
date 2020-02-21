
CREATE TABLE mars_sol
(
  sol int NOT NULL,
  date_first timestamp without time zone NOT NULL,
  date_last timestamp without time zone NOT NULL,
  average double(3) NOT NULL,
  minimum double(3) NOT NULL,
  maximum double(3) NOT NULL,

  PRIMARY KEY (sol)
);

CREATE TABLE last_fetch
(
  id int auto_increment NOT NULL,
  fetch_date timestamp without time zone NOT NULL,

  PRIMARY KEY (id)
);

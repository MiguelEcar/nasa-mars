
CREATE TABLE mars_sol
(
  sol int NOT NULL,
  dateFirst timestamp without time zone NOT NULL,
  dateLast timestamp without time zone NOT NULL,
  average number(10,3) NOT NULL,
  minimum number(10,3) NOT NULL,
  maximum number(10,3) NOT NULL,

  CONSTRAINT mars_sol_pkey PRIMARY KEY (sol)
);

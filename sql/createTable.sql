CREATE TABLE NONVISITOR(
univid CHAR(10) PRIMARY KEY,
name CHAR(11),
type CHAR(2),
password CHAR(10),
pnumber INT
);

CREATE TABLE VISTOR(
name CHAR(11),
pnumber CHAR(10) PRIMARY KEY
);

CREATE TABLE vehicles(
lnumber CHAR(10) PRIMARY KEY,
color CHAR(10),
yr CHAR(10),
model CHAR(10),
manufacturer CHAR(10)
);

CREATE TABLE hasVehicles(
lnumber CHAR(10),
id CHAR(10),
PRIMARY KEY(lnumber),
FOREIGN KEY (lnumber) REFERENCES vehicles(lnumber) 
);

CREATE TABLE ParkingLots(
lotName VARCHAR(20) PRIMARY KEY,
address VARCHAR(30), 
zoneDes VARCHAR(30)
);

CREATE TABLE hasSpaces(
lotName VARCHAR(20),
spacenum INT,
spacetype VARCHAR(10),
zoneid CHAR(2),
status CHAR(2),
PRIMARY KEY(lotName, spacenum),
FOREIGN KEY (lotName) References ParkingLots(lotName) ON DELETE CASCADE);



CREATE TABLE PERMITS(
permitid VARCHAR(10) PRIMARY KEY,
zoneid CHAR(2),
st TIMESTAMP,
exp TIMESTAMP,
numVehicles INT,
spaceType VARCHAR(10),
lotname VARCHAR(20),
spacenum INT,
FOREIGN KEY (lotname) REFERENCES ParkingLots(lotName)
); 

CREATE TABLE hasPermits(
id CHAR(10), 
permitid VARCHAR(10),
lnumber CHAR(10), 
PRIMARY KEY(permitid, lnumber),
FOREIGN KEY (permitid) REFERENCES Permits(permitid),
FOREIGN KEY (lnumber) REFERENCES Vehicles(lnumber)
);



CREATE TABLE CITATIONS(
citnum INT PRIMARY KEY,
lnumber CHAR(10),
lotName VARCHAR(20),
citeTime TIMESTAMP, 
lastSent DATE,
duedate DATE,
paystatus VARCHAR(10),
amount INT,
zoneviolated CHAR(2),
category VARCHAR(10),
FOREIGN KEY (lnumber) REFERENCES vehicles(lnumber)
);



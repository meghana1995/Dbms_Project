INSERT INTO nonvisitor(univid, name, type, password, pnumber)
VALUES('1000', 'Johnny', 'E','1000', 9197878888);
INSERT INTO nonvisitor(univid, name, type, password, pnumber)
VALUES('1001', 'Ricky', 'S', '1001',9197878222);
INSERT INTO nonvisitor(univid, name, type, password, pnumber)
VALUES('1002', 'Johnny', 'E','1002', 9197878000);
INSERT INTO nonvisitor(univid, name, type, password,pnumber)
VALUES('1003', 'Tommy', 'S', '1003',9197878111);
INSERT INTO nonvisitor(univid, name, type, password, pnumber)
VALUES('1004', 'Johnny', 'U','1004', 9197878777);


INSERT INTO vistor(name, pnumber)
VALUES('Ellee', '9191111111');
INSERT INTO vistor(name, pnumber)
VALUES('Billy', '9192222222');
INSERT INTO vistor(name, pnumber)
VALUES('John', '9193333333');
INSERT INTO vistor(name, pnumber)
VALUES('Andrew', '9194444444');
INSERT INTO vistor(name, pnumber)
VALUES('Ramesh', '9195555555');


INSERT INTO vehicles(lnumber,color, yr, model, manufacturer)
VALUES('CDF5731', 'Red', '2018', 'Camry', 'Toyota');
INSERT INTO vehicles(lnumber,color, yr, model, manufacturer)
VALUES('AKL1732', 'Silver', '2019', 'Model X', 'Tesla');
INSERT INTO vehicles(lnumber,color, yr, model, manufacturer)
VALUES('UGY9123', 'Black', '2015', 'Maxima', 'Nissan');
INSERT INTO vehicles(lnumber,color, yr, model, manufacturer)
VALUES('TRK1093', 'Blue', '2017', 'Rio', 'Kia');
INSERT INTO vehicles(lnumber,color, yr, model, manufacturer)
VALUES('UWA1118', 'White', '2016', 'Q3', 'Audi');
INSERT INTO vehicles(lnumber,color, yr, model, manufacturer)
VALUES('UGB9020', 'Silver', '2014', 'Cruze', 'Chevrolet');
INSERT INTO vehicles(lnumber,color, yr, model, manufacturer)
VALUES('VTZ8754', 'Black', '2018', 'LEAF', 'Nissan');
INSERT INTO vehicles(lnumber,color, yr, model, manufacturer)
VALUES('TIR3487', 'White', '2017', 'X5', 'BMW');
INSERT INTO vehicles(lnumber,color, yr, model, manufacturer)
VALUES('RPU1824', 'Blue', '2016', 'Odyssey', 'Honda');
INSERT INTO vehicles(lnumber,color, yr, model, manufacturer)
VALUES('NEV9889', 'Red', '2011', 'Elantra', 'Hyundai');
INSERT INTO vehicles(lnumber,color, yr, model, manufacturer)
VALUES('KTP2003', 'Black', '2009', 'RDX', 'Acura');


INSERT INTO hasvehicles(lnumber, id)
VALUES('CDF5731', 9191111111);
INSERT INTO hasvehicles(lnumber, id)
VALUES('TRK1093', 9192222222);
INSERT INTO hasvehicles(lnumber, id)
VALUES('UGY9123', 9193333333);
INSERT INTO hasvehicles(lnumber, id)
VALUES('AKL1732', 9194444444);
INSERT INTO hasvehicles(lnumber, id)
VALUES('UWA1118', 9195555555);
INSERT INTO hasvehicles(lnumber, id)
VALUES('VTZ8754', 1000);
INSERT INTO hasvehicles(lnumber, id)
VALUES('UGB9020', 1001);
INSERT INTO hasvehicles(lnumber, id)
VALUES('TIR3487', 1002);
INSERT INTO hasvehicles(lnumber, id)
VALUES('RPU1824', 1002);
INSERT INTO hasvehicles(lnumber, id)
VALUES('NEV9889', 1003);
INSERT INTO hasvehicles(lnumber, id)
VALUES('KTP2003', 1004);


INSERT INTO parkinglots(lotname, address, zoneDes)
VALUES('Freedom Lot', '2105 Daniel Allen St, NC 27505', 'A/B/C/D');
INSERT INTO parkinglots(lotname, address, zoneDes)
VALUES('Premiere Lot', '2108 McKent St, NC 27507', 'A/B/C/D/AS/BS/CS/DS/V');
INSERT INTO parkinglots(lotname, address, zoneDes)
VALUES('Justice Lot', '2704 Ben Clark St, NC 26701', 'AS/BS/CS/DS/V');

INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Freedom Lot', 1, 'E', 'A','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Freedom Lot', 2, 'H', 'B','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Freedom Lot', 3, 'R', 'C','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Freedom Lot', 4, 'R', 'C','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Freedom Lot', 5, 'R', 'C','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Freedom Lot', 6, 'R', 'C','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Premiere Lot', 200, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Premiere Lot', 150, 'R', 'S','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Premiere Lot', 151, 'R', 'S','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Premiere Lot', 152, 'R', 'S','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Premiere Lot', 153, 'R', 'S','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Premiere Lot', 154, 'H', 'S','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 151, 'H', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 152, 'H', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 153, 'H', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 154, 'H', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 155, 'H', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 172, 'E', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 173, 'E', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 174, 'E', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 175, 'E', 'V','V');   
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 176, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 177, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 178, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 179, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 180, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 181, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 182, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 183, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 184, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 185, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 186, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 187, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 188, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 189, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 190, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 191, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 192, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 193, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 194, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 195, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 196, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 197, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 198, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 199, 'R', 'V','V');
INSERT INTO hasspaces(lotname, spacenum, spacetype, zoneid,status)
VALUES('Justice Lot', 200, 'R', 'V','V');



INSERT INTO PERMITS(permitid, zoneid, st, exp, numvehicles, spacetype,  lotname, spacenum)
VALUES('A1234', 'V', TIMESTAMP'2020-08-12 14:00:00', TIMESTAMP'2020-08-12 15:59:59', 1, 'R',  'Premiere Lot', 200);
INSERT INTO PERMITS(permitid, zoneid, st, exp, numvehicles, spacetype,  lotname, spacenum)
VALUES('A2345', 'V', TIMESTAMP'2020-08-14 11:00:00', TIMESTAMP'2020-08-14 13:59:59', 2, 'R',  'Justice Lot',  160);
INSERT INTO PERMITS(permitid, zoneid, st, exp, numvehicles, spacetype,  lotname, spacenum)
VALUES('A3456', 'V', TIMESTAMP'2020-08-17 10:10:00', TIMESTAMP'2020-08-17 12:10:00', 1, 'H',  'Justice Lot',  151);
INSERT INTO PERMITS(permitid, zoneid, st, exp, numvehicles, spacetype,  lotname, spacenum)
VALUES('A4567', 'V', TIMESTAMP'2020-08-17 11:45:00', TIMESTAMP'2020-08-17 12:45:00', 1, 'E',  'Justice Lot',  173);
INSERT INTO PERMITS(permitid, zoneid, st, exp, numvehicles, spacetype,  lotname, spacenum)
VALUES('A5678', 'V', TIMESTAMP'2020-08-19 14:50:00', TIMESTAMP'2020-08-19 16:50:00', 1, 'H',  'Justice Lot',  153);
INSERT INTO PERMITS(permitid, zoneid, st, exp, numvehicles, spacetype,  lotname, spacenum)
VALUES('A7890', 'B', TIMESTAMP'2020-08-10 00:00:00', TIMESTAMP'2021-08-09 23:59:59', 1, 'E',  'Freedom Lot',  1);
INSERT INTO PERMITS(permitid, zoneid, st, exp, numvehicles, spacetype,  lotname, spacenum)
VALUES('A8901', 'S', TIMESTAMP'2020-08-15 00:00:00', TIMESTAMP'2020-12-14 23:59:59', 1, 'H',  'Freedom Lot',  2);
INSERT INTO PERMITS(permitid, zoneid, st, exp, numvehicles, spacetype,  lotname, spacenum)
VALUES('A9012', 'D', TIMESTAMP'2020-07-10 00:00:00', TIMESTAMP'2020-07-10 23:59:59', 2, 'R',  'Freedom Lot',  3);
INSERT INTO PERMITS(permitid, zoneid, st, exp, numvehicles, spacetype,  lotname, spacenum)
VALUES('A0123', 'S', TIMESTAMP'2020-09-01 00:00:00', TIMESTAMP'2020-12-31 23:59:59', 1, 'R',  'Freedom Lot',  4);
INSERT INTO PERMITS(permitid, zoneid, st, exp, numvehicles, spacetype,  lotname, spacenum)
VALUES('A1123', 'A', TIMESTAMP'2020-07-29 00:00:00', TIMESTAMP'2021-07-28 23:59:59', 1, 'R',  'Freedom Lot',  5);


INSERT INTO haspermits(permitid, id, lnumber)
VALUES('A1234', 9191111111, 'CDF5731');
INSERT INTO haspermits(permitid, id, lnumber)
VALUES('A2345', 9192222222, 'TRK1093');
INSERT INTO haspermits(permitid, id, lnumber)
VALUES('A3456', 9193333333, 'UGY9123');
INSERT INTO haspermits(permitid, id, lnumber)
VALUES('A4567', 9194444444, 'AKL1732');
INSERT INTO haspermits(permitid, id, lnumber)
VALUES('A5678', 9195555555, 'UWA1118');
INSERT INTO haspermits(permitid, id, lnumber)
VALUES('A7890', 1000, 'VTZ8754');
INSERT INTO haspermits(permitid, id, lnumber)
VALUES('A8901', 1001, 'UGB9020');
INSERT INTO haspermits(permitid, id, lnumber)
VALUES('A9012', 1002, 'TIR3487');
INSERT INTO haspermits(permitid, id, lnumber)
VALUES('A9012', 1002, 'RPU1824');
INSERT INTO haspermits(permitid, id, lnumber)
VALUES('A0123', 1003, 'NEV9889');
INSERT INTO haspermits(permitid, id, lnumber)
VALUES('A1123', 1004, 'KTP2003');


-- Zone violated??
-- LastSent currently same as cittaion issue date
-- Visitor can have more than one permit - If not, need to delete expired permits,
Insert into CITATIONS(citnum ,lnumber ,lotName ,citeTime, lastSent,duedate ,paystatus ,amount ,zoneviolated,category) 
Values(123, 'TRK1093', 'Justice Lot', TIMESTAMP'2020-08-14 02:40:00', DATE'2020-08-14', DATE'2020-09-13', 'P', 25,'A', 'EP');
Insert into CITATIONS(citnum ,lnumber ,lotName ,citeTime, lastSent,duedate ,paystatus ,amount ,zoneviolated,category) 
Values(234, 'UGY9123', 'Justice Lot', TIMESTAMP'2020-08-17 12:55:00', DATE'2020-09-16', DATE'2020-09-16', 'U', 25, 'B','EP');
Insert into CITATIONS(citnum ,lnumber ,lotName ,citeTime, lastSent,duedate ,paystatus ,amount,zoneviolated,category) 
Values(345, 'AKL1732', 'Justice Lot', TIMESTAMP'2020-08-17 13:00:00', DATE'2020-09-16', DATE'2020-09-16', 'U', 25,'S' ,'EP');
Insert into CITATIONS(citnum ,lnumber ,lotName ,citeTime, lastSent,duedate ,paystatus ,amount,zoneviolated,category) 
Values(456, 'NEV9889', 'Justice Lot', TIMESTAMP'2020-09-10 15:50:00', DATE'2020-09-10', DATE'2020-10-09', 'U', 25,'S' ,'IP');
Insert into CITATIONS(citnum ,lnumber ,lotName ,citeTime, lastSent,duedate ,paystatus ,amount,zoneviolated,category) 
Values(678, 'TRK1093', 'Premiere Lot', TIMESTAMP'2020-09-21 14:00:00', DATE'2020-09-21', DATE'2020-10-20', 'U', 25,'S' ,'IP');

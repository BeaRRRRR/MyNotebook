DROP DATABASE IF EXISTS test;
CREATE DATABASE test;
USE test;

DROP TABLE IF EXISTS note;
CREATE TABLE note(
  id INT NOT NULL AUTO_INCREMENT,
  message VARCHAR(255) NOT NULL,
  note_date TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  is_done BOOLEAN NOT NULL DEFAULT false,
  PRIMARY KEY(id)
);

INSERT INTO Note(message,note_date,is_done) VALUES ('Note 1','2018-01-03 10:26:09',TRUE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 2','2018-01-19 03:47:45',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 3','2018-01-31 17:29:27',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 4','2018-02-20 09:19:52',TRUE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 5','2018-02-21 05:11:42',TRUE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 6','2018-02-22 11:17:27',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 7','2018-02-27 01:44:44',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 8','2018-03-09 04:07:50',TRUE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 9','2018-03-11 00:56:15',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 10','2018-03-13 20:05:44',TRUE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 11','2018-03-26 06:08:19',TRUE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 12','2018-03-30 12:51:02',TRUE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 13','2018-04-02 14:33:14',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 14','2018-04-12 09:40:23',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 15','2018-04-14 11:17:02',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 16','2018-04-15 01:00:13',TRUE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 17','2018-04-21 12:26:26',TRUE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 18','2018-04-29 05:44:20 ',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 19','2018-05-01 22:47:19',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 20','2018-05-17 08:13:10',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 21','2018-05-18 18:38:51',TRUE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 22','2018-05-25 02:09:14',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 23','2018-05-29 04:08:50',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 24','2018-06-02 14:42:35',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 25','2018-06-03 16:57:22',TRUE );
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 26','2018-06-05 15:20:27',TRUE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 27','2018-06-08 19:43:19',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 28','2018-06-09 01:34:08',FALSE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 29','2018-06-10 06:24:19',TRUE);
INSERT INTO Note(message,note_date,is_done) VALUES ('Note 30','2018-06-11 14:04:34',FALSE);
alter table Patient 
add	email varchar(10)

describe Patient;

create table test
(
	id int,
    name varchar(10)
);

insert into test values(1,"akash"),(2,"vishal");
desc Patient
select * from test;
select * from Patient
delete from Patient WHERE patient_id='patient_1'

/*check the databases and switch to shs database*/
show databases
use shs
/*adding doctor_id column in Patient Table*/
alter table Patient add doctor_id varchar(30)

/*create doctor table*/
/*more columns might be added later*/
create table Doctor
(
doctor_id varchar(30) primary key,
doctor_name varchar(30),
doctor_age int,
address varchar(100),
contact_number varchar(15),
experience float,
department_number int,
position_in_dept varchar(30),
opd_start_time TIME,
opd_end_time TIME
)
DESC Doctor
Desc Patient
CREATE table Patient
(
patient_id varchar(50) primary key,
name varchar(50),
age int,
gender varchar(10),
contact varchar(50),
address varchar(100),
password varchar(10),
email varchar(10),
doctor_id varchar(30)
)


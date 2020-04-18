-- Drop table

-- DROP TABLE dsp;

CREATE TABLE dsp (
	tipreg varchar(20) NULL,
	datapg timestamp NULL,
	codbdi varchar(20) NULL,
	codneg varchar(20) NULL,
	tpmerc varchar(20) NULL,
	nomres varchar(20) NULL,
	especi varchar(20) NULL,
	prazot int4 NULL,
	modref varchar(20) NULL,
	preabe float4 NULL,
	premax float4 NULL,
	premin float4 NULL,
	premed float4 NULL,
	preult float4 NULL,
	preofc float4 NULL,
	preofv float4 NULL,
	totneg int4 NULL,
	quatot int4 NULL,
	voltot float4 NULL,
	preexe float4 NULL,
	indopc varchar(20) NULL,
	datven varchar(20) NULL,
	fatcot varchar(20) NULL,
	ptoexe varchar(20) NULL,
	codisi varchar(20) NULL,
	dismes varchar(20) NULL
);

ALTER TABLE public.dsp ADD CONSTRAINT dsp_pk PRIMARY KEY (datapg,codneg,codbdi);

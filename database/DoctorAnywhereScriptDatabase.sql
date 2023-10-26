CREATE DATABASE DoctorAnywhere
GO

USE DoctorAnywhere
GO

------------------------------ CREATE TABLE ------------------------------
CREATE TABLE [Task]
(
    Id          BIGINT IDENTITY (1, 1) NOT NULL,
    Title       NVARCHAR(100)          NOT NULL,
    Description NVARCHAR(MAX)          NOT NULL,
    Completed   BIT                    NOT NULL
)
GO
------------------------------ CREATE CONSTRAINT ------------------------------
--- PRIMARY KEY ---
ALTER TABLE [Task]
    ADD CONSTRAINT PK_Task PRIMARY KEY (Id);

--- UNIQUE ---

-- FOREIGN KEY ---

------------------------------ INSERT VALUE ------------------------------

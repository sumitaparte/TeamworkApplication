DELIMITER $$

DROP PROCEDURE IF EXISTS spInsertSurveyResponse$$

CREATE PROCEDURE spInsertSurveyResponse (
	IN studentId VARCHAR(100),
    IN questionId INT,
    IN resValue VARCHAR(100)
)

BEGIN
insert into Response(studentID,questionID,responseValue) 
values (studentId,questionId,resValue);
END $$

DELIMITER ;
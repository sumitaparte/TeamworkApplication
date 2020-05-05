DELIMITER $$

DROP PROCEDURE IF EXISTS spInsertSurveyQuestion $$

CREATE PROCEDURE spInsertQuestion (
	IN courseId INT,
    IN questionId INT
)
BEGIN
    insert into SurveyQuestion(Course_id,Question_id)
    values (courseId,questionId);
END $$

DELIMITER ;
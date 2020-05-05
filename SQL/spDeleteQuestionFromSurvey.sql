DELIMITER $$

DROP PROCEDURE IF EXISTS spDeleteQuestionFromSurvey $$

CREATE PROCEDURE spDeleteQuestionFromSurvey (
	IN id BIGINT
)
BEGIN
	DELETE
	FROM SurveyQuestion
    WHERE SurveyQuestion.questionID = id;
END $$

DELIMITER m;
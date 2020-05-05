DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllQuestionSurvey $$

CREATE PROCEDURE spLoadAllQuestionSurvey ()
BEGIN
    SELECT questionText,questionTitle,questionID
    FROM QuestionBank;
END $$

DELIMITER ;
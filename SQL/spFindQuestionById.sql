DELIMITER $$

DROP PROCEDURE IF EXISTS spFindQuestionById $$

CREATE PROCEDURE spQuestionById (
	IN id BIGINT
)
BEGIN
	SELECT questionID, questionTitle, questionText, questiontypeID, questionOption, questionValue
	FROM QuestionBank
    WHERE QuestionBank.id = id;
END $$

DELIMITER ;
DELIMITER $$

DROP PROCEDURE IF EXISTS spLoadAllQuestions $$

CREATE PROCEDURE spLoadAllQuestions ()
BEGIN
	SELECT questionID, questionTitle, questionText, questiontypeID, questionOption, questionValue
    FROM QuestionBank
    ORDER BY questionID ASC;
END $$

DELIMITER ;
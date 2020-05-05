DELIMITER $$

DROP PROCEDURE IF EXISTS spDeleteQuestionByID $$

CREATE PROCEDURE spDeleteQuestionByID (
	IN id BIGINT
)
BEGIN
    DELETE FROM Response WHERE questionID = id;
	DELETE
	FROM QuestionBank
    WHERE QuestionBank.questionID = id;
    SELECT questionID FROM QuestionBank WHERE
        QuestionBank.questionID = id;
END $$

DELIMITER ;
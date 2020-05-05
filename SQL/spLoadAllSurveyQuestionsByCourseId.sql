DELIMITER $$
DROP PROCEDURE IF EXISTS spInsertSurveyResponse$$

CREATE PROCEDURE spInsertSurveyResponse (
IN courseID INT(11)
)
BEGIN
    SELECT Q.questionID, Q.questionText, T.questiontype, Q.questionOption, Q.questionValue
    FROM QuestionBank Q 
    JOIN SurveyQuestion S 
    ON Q.questionID = S.Question_id
    JOIN QuestionType T
    ON T.questiontypeID = Q.questiontypeID
    WHERE S.Course_id = courseID;

END $$

DELIMITER ;
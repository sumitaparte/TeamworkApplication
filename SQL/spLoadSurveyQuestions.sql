CREATE DEFINER=`CSCI5308_1_DEVINT_USER`@`%` PROCEDURE `spLoadSurveyQuestions`(
IN ID INT
)
BEGIN
      SELECT questionID, questionTitle, questionText, questiontypeID, questionOption, questionValue
	  FROM QuestionBank q JOIN SurveyQuestion s ON s.Question_id = q.questionID  WHERE s.Course_id = ID;
END
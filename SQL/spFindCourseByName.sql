DELIMITER $$

DROP PROCEDURE IF EXISTS spFindCourseByName $$

CREATE PROCEDURE spFindCourseByname (
	IN courseName VARCHAR(200)
)
BEGIN
	SELECT id, title
	FROM Course
    WHERE Course.title = courseName;
END $$

DELIMITER ;
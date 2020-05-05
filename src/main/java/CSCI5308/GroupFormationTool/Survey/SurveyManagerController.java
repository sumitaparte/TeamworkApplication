package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class SurveyManagerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SurveyManagerController.class);

    private static final String ID = "id";
    private static final String COURSE = "courseId";
    private static final String QUESTION = "question";

    @GetMapping("/survey/surveymanager")
    public String displayQus(@RequestParam(name = COURSE) int courseId, Model model) {
        ISurveyPersistence survey = SystemConfig.instance().getSurveyPersistence();
        List<Survey> surveys = survey.getallQuestions();
        model.addAttribute("surveys", surveys);
        model.addAttribute("courseId", courseId);
        return "survey/surveymanager";
    }

    @GetMapping("/survey/showsurvey")
    public String surveyEditor(@RequestParam(name = COURSE) int courseId, Model model) {
        ISurveyPersistence survey = SystemConfig.instance().getSurveyPersistence();
        if (courseId != 0) {
            List<Survey> surveys = survey.getallSurveyQuestions(courseId);
            model.addAttribute("surveys", surveys);
            model.addAttribute("courseId", courseId);
            return "survey/showsurvey";
        } else {
            List<Survey> surveys = new ArrayList<>();
            model.addAttribute("surveys", surveys);
            model.addAttribute("courseId", courseId);
            return "survey/showsurvey";
        }
    }

    @PostMapping("/survey/saveresponse")
    public String saveresponse(@RequestParam(name = QUESTION) List<Integer> question, @RequestParam(name = COURSE) int courseId, Model model,
                               RedirectAttributes redirectAttributes) {
        ISurveyPersistence survey = SystemConfig.instance().getSurveyPersistence();
        Iterator<Integer> iter = question.iterator();
        while (iter.hasNext()) {
            Survey sur = new Survey();
            sur.setQuestionId(iter.next());
            sur.setCourseId(courseId);
            survey.insertQuestion(sur);
        }
        redirectAttributes.addFlashAttribute("status", "Question Added Successfully");
        redirectAttributes.addAttribute("courseId", courseId);
        return "redirect:/survey/surveymanager";
    }

    @PostMapping("/survey/editsurveyquestion")
    public String editsurveyquestion(@RequestParam(name = QUESTION) List<Integer> question, @RequestParam(name = COURSE) int courseId, Model model,
                                     RedirectAttributes redirectAttributes) {
        ISurveyPersistence survey = SystemConfig.instance().getSurveyPersistence();
        Iterator<Integer> iter = question.iterator();
        while (iter.hasNext()) {
            Survey sur = new Survey();
            sur.setQuestionId(iter.next());
            sur.setCourseId(courseId);
            survey.deleteQuestionFromSurvey(sur);
        }
        redirectAttributes.addFlashAttribute("status", "Question Deleted Successfully");
        redirectAttributes.addAttribute("courseId", courseId);
        return "redirect:/survey/showsurvey";
    }


}

package CSCI5308.GroupFormationTool.Response;

import CSCI5308.GroupFormationTool.AccessControl.CurrentUser;
import CSCI5308.GroupFormationTool.AccessControl.User;
import CSCI5308.GroupFormationTool.Questions.QuestionBank;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ResponseController {

    private final String ID = "id";
    private final String RESPONSE = "responses";

    @GetMapping("/question/survey")
    public String displayQuestionAddForm(@RequestParam(name = ID) int courseId, Model model) {
        IResponsePersistence responseDb = SystemConfig.instance().getResponseDb();
        List<QuestionBank> surveyquestions = responseDb.getAllSurveyQuestions(courseId);
        List<Response> responses = new ArrayList<>();
        ResponseForm responseForm = new ResponseForm();
        if (courseId != 0) {
            for (QuestionBank surveyquestion : surveyquestions) {
                responses.add(new Response());
            }
            responseForm.setResponses(responses);
            model.addAttribute("surveyquestions", surveyquestions);
            model.addAttribute("responseForm", responseForm);
            return "question/survey";
        } else {
            model.addAttribute("surveyquestions", surveyquestions);
            model.addAttribute("responseForm", responseForm);
            return "question/survey";
        }
    }

    @PostMapping("/question/savesurvey")
    public String saveSurveyResponse(@ModelAttribute ResponseForm responseForm, Model model) {
        IResponsePersistence responseDB = SystemConfig.instance().getResponseDb();
        User currentUser = CurrentUser.instance().getCurrentAuthenticatedUser();
        boolean questions = responseDB.saveresponsebyId(currentUser.getBannerID(), responseForm.getResponses());
        if (questions) {
            model.addAttribute("result", "Survey Completed!!");
        } else {
            model.addAttribute("result", "Survey Not Completed!!");
        }
        return "question/survey";
    }

}

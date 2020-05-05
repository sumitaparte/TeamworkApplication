package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class QuestionManagerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionManagerController.class);

    private static final String ID = "id";
    private static final String ORDERBY = "by";

    @GetMapping("/question/questionmanager")
    public String displayQuestions(@RequestParam(name = ORDERBY) String orderBy, Model model) {
        IQuestionBankPersistence questionBankDb = SystemConfig.instance().getQuestionBankPersistence();
        List<QuestionBank> questions = questionBankDb.getallQuestions(orderBy);
        model.addAttribute("questions", questions);
        return "question/questionmanager";
    }

    @GetMapping("/question/deletequestion")
    public String deleteQuestion(Model model, @RequestParam(name = ID) int questionID, RedirectAttributes redirectAttributes) {
        IQuestionBankPersistence questionBankDb = SystemConfig.instance().getQuestionBankPersistence();
        if (questionID != 0) {
            boolean result = questionBankDb.deleteQuestionById(questionID);
            ModelAndView mod = null;
            if (result) {
                redirectAttributes.addFlashAttribute("status", "Question Deleted Successfully");
                return "redirect:/question/questionmanager?by=questionTitle";
            } else {
                redirectAttributes.addFlashAttribute("status", "Question can not be deleted. Please try again after some time.");
                return "redirect:/question/questionmanager?by=questionTitle";
            }
        } else {
            redirectAttributes.addFlashAttribute("status", "Question ID Not Present.");
            return "redirect:/question/questionmanager?by=questionTitle";
        }
    }
}

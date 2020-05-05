package CSCI5308.GroupFormationTool.Questions;

import CSCI5308.GroupFormationTool.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class QuestionEditorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionEditorController.class);
    private final String TITLE = "title";
    private final String QUESTION = "questionTxt";
    private final String TYPE = "type";

    @GetMapping("/question/questioneditor")
    public String displayQuestionAddForm(Model model) {
        Type[] types = {Type.NUMERIC, Type.MCQ_MULTIPLE, Type.MCQ_SINGLE, Type.FREE_TEXT};
        model.addAttribute("types", types);
        return "question/questioneditor";
    }

    @RequestMapping(value = "/question/questioneditoranswer", method = RequestMethod.POST)
    public String loadAnswerPage(@RequestParam(name = TITLE) String title,
                                 @RequestParam(name = QUESTION) String questionTxt,
                                 @RequestParam(name = TYPE) String type, Model model) {
        QuestionBank questionBank = new QuestionBank();
        if (title != null && questionTxt != null && type != null) {
            questionBank.setQuestionTitle(title);
            questionBank.setQuestionText(questionTxt);
            questionBank.setQuestionType(type);
            if (!type.equalsIgnoreCase(String.valueOf(Type.NUMERIC))) {
                for (int i = 0; i < 4; i++) {
                    questionBank.addOptionValue(new OptionValue());
                }
            }
            model.addAttribute("questionBank", questionBank);
            return "question/questioneditoranswer";
        } else {
            model.addAttribute("questionBank", questionBank);
            return "question/questioneditoranswer";
        }
    }

    @RequestMapping(value = "/question/saveQuesAnsData", method = RequestMethod.POST)
    public String saveQuestAnsData(@ModelAttribute QuestionBank questionBank, Model model) {

        IQuestionBankPersistence questionBankDb = SystemConfig.instance().getQuestionBankPersistence();
        boolean success = questionBankDb.insertQuestion(questionBank);
        if (success) {
            model.addAttribute("response", "Question added successfully!!");
        }
        return "question/questioneditoranswer";
    }

    @RequestMapping(value = "/question/saveQuesAnsData", params = {"addRow"})
    public String addRow(final QuestionBank questionBank, final BindingResult bindingResult) {
        questionBank.getOptionValues().add(new OptionValue());
        return "question/questioneditoranswer";
    }
}

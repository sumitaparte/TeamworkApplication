package CSCI5308.GroupFormationTool.Questions;

public enum Type {
    NUMERIC {
        public String toString() {
            return "Numeric";
        }
    },
    MCQ_MULTIPLE {
        public String toString() {
            return "MCQ multiple";
        }
    },
    MCQ_SINGLE {
        public String toString() {
            return "MCQ Single";
        }
    },
    FREE_TEXT {
        public String toString() {
            return "Free Text";
        }
    }
}
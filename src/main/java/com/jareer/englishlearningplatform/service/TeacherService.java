package com.jareer.englishlearningplatform.service;

import com.jareer.englishlearningplatform.dao.*;
import com.jareer.englishlearningplatform.domains.*;
import com.jareer.englishlearningplatform.utils.validator.GrammarValidator;
import com.jareer.englishlearningplatform.utils.validator.QuestionValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class TeacherService {
    private static final ThreadLocal<TeacherService> instance = ThreadLocal.withInitial(TeacherService::new);


    public static TeacherService getInstance() {
        return instance.get();
    }

    public Story getStoryById(int id) {
        return new StoryDAO().findById(id);
    }

    public List<Vocabulary> getVocabulariesByStory(int id) {
        return new VocabularyDAO().getVocabulariesByStoryId(getStoryById(id));
    }

    public List<Story> getAllStories() {
        return new StoryDAO().findAllStories();
    }

    public List<Grammar> getAllGrammars() {
        return new GrammarDAO().findAllGrammars();
    }

    public void saveQuestion(Questions questions) {
        QuestionDAO questionsDAO = new QuestionDAO();
        questionsDAO.save(questions);

    }

    public void saveVariant(Variants variants) {
        VariantDAO variantDAO = new VariantDAO();
        variantDAO.save(variants);
    }

    public Grammar getGrammarById(int grammarId) {


        return new GrammarDAO().findById(grammarId);
    }

    public void deleteWord(int id) {
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        Vocabulary vocabulary = vocabularyDAO.findById(id);
        vocabulary.setDeleted(true);
        vocabularyDAO.save(vocabulary);
    }

    public void updateAsDelete(Story story) {
        StoryDAO storyDAO = new StoryDAO();
        storyDAO.update(story);
    }
    public void updateAsDelete(Grammar grammar) {
        GrammarDAO grammarDAO = new GrammarDAO();
        grammarDAO.update(grammar);
    }
    public void updateStory(Story story) {
        StoryDAO storyDAO = new StoryDAO();
        storyDAO.update(story);
    }

    public List<Questions> getGrammarQuestions(Grammar grammar) {
        QuestionDAO questionDAO = new QuestionDAO();
        Questions questions = new Questions();

        return questionDAO.findAllByGrammarId(grammar.getId());

    }

    public void updateGrammar(Grammar grammar) {
        GrammarDAO grammarDAO = new GrammarDAO();
        grammarDAO.update(grammar);
    }

    public Questions getQuestionById(int questionId) {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.findById(questionId);
    }

    public Document findGrammarById(Integer id){
        DocumentDAO documentDAO = new DocumentDAO();
        return documentDAO.findById(id);
    }

    public List<Variants> getVariantsByQuestionId(int id) {
        
        return new VariantDAO().findAllByQuestionId(id);
    }

    public void updateQuestion(Questions questions) {
        QuestionDAO questionDAO = new QuestionDAO();
        questionDAO.update(questions);
    }

    public void updateVariant(Variants variants) {
        VariantDAO variantDAO = new VariantDAO();
        variantDAO.update(variants);
    }

    public void deleteVariantsByQuestionId(Integer id) {
        VariantDAO variantDAO = new VariantDAO();
        variantDAO.deleteVariantsByQuestionId(id);
    }

    public void deleteQuestion(int id) {
        QuestionDAO questionDAO = new QuestionDAO();
        Questions questions = questionDAO.findById(id);
        deleteVariantsByQuestionId(questions.getId());
        questionDAO.delete(questions);
    }

    public void validateGrammar(HttpServletRequest req) throws Exception {
        GrammarValidator validator = new GrammarValidator();
        validator.validate(req);
    }

    public void validateQuestion(HttpServletRequest req) throws Exception {
        QuestionValidator validator = new QuestionValidator();
        validator.validate(req);
    }

    public void saveStory(Story story) {
        StoryDAO storyDAO = new StoryDAO();
        storyDAO.save(story);
    }



    public Grammar saveGrammar( @NonNull Grammar grammar ) {
        GrammarDAO grammarDAO = new GrammarDAO();
        Grammar gram = null;

        try {
            gram = grammarDAO.save(grammar);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return gram;
    }

    public Grammar getGrammarWithOption(@NonNull String userLevel ) {
        GrammarDAO grammarDAO = new GrammarDAO();
        Grammar gram = null;
        try {
            gram = grammarDAO.getStoryWithOption(userLevel);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return gram;
    }

    public Story getStoryWithOption( String name, Long userId ) {
        StoryDAO storyDAO = new StoryDAO();
        return storyDAO.getStoryWithOption(name, userId);
    }

    public List<Vocabulary> getVocabulariesByStoryId(@NonNull Story storyWithOption ) {
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        return vocabularyDAO.getVocabulariesByStoryId(storyWithOption);
    }

    public Story findStoryById( int storyId ) {
        StoryDAO storyDAO = new StoryDAO();
        return storyDAO.findById(storyId);
    }

    public List<Story> getStoriesByIds(@NonNull List<Integer> storyIds ) {
        StoryDAO storyDAO = new StoryDAO();
        return storyDAO.getStoriesById(storyIds);
    }

    public Integer getVocabularyCountWithOption( Integer userId, Integer id ) {
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        return vocabularyDAO.getVocabularyCountWithOption(userId, id);
    }

    public List<Questions> findAllByGrammarId( int id ) {
        QuestionDAO questionDAO = new QuestionDAO();
        return questionDAO.findAllByGrammarId(id);
    }

    public List<Variants> findAllByQuestionId( Integer id ) {
        VariantDAO variantDAO = new VariantDAO();
        return variantDAO.findAllByQuestionId(id);
    }

    public Users findUserById( long userId ) {
        return new UserDAO().findById(userId);
    }
}

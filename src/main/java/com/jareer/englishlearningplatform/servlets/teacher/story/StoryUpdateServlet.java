package com.jareer.englishlearningplatform.servlets.teacher.story;

import com.jareer.englishlearningplatform.dao.VocabularyDAO;
import com.jareer.englishlearningplatform.domains.Document;
import com.jareer.englishlearningplatform.domains.Story;
import com.jareer.englishlearningplatform.domains.Vocabulary;
import com.jareer.englishlearningplatform.enums.Levels;
import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@WebServlet(name = "ServletStoryUpdateServlet", value = "/teacher/story/update/*")
@MultipartConfig(location = "c:\\E-Learn\\uploads\\stories")
public class StoryUpdateServlet extends HttpServlet {

    private static final Path rootPath = Path.of(System.getProperty("user.home"), "/E-Learn/uploads/stories");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String storyId = request.getPathInfo().substring(1);
        try {

            int id = Integer.parseInt(storyId);

            TeacherService teachService = TeacherService.getInstance();
            Story story = teachService.getStoryById(id);
            if (story == null) {
                response.sendError(404, "Story with id '%s' not found".formatted(storyId));
            } else {
                List<Vocabulary> vocabularyList = teachService.getVocabulariesByStory(id);
                request.getSession().setAttribute("vocabularies", vocabularyList);
//                request.setAttribute("vocabularyList", vocabularyList);
                request.setAttribute("story", story);
                List<Levels> levels = List.of(Levels.values());
                request.setAttribute("levels", levels);
                request.getRequestDispatcher("/views/teacher/story/update.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendError(404, "Story with id '%s' not found".formatted(storyId));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: 2/19/2023 update qismini yozish kerak
        String storyId = request.getPathInfo().substring(1);
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String score = request.getParameter("score");
        String level = request.getParameter("level");
        Integer userId = Integer.valueOf(request.getSession().getAttribute("user_id").toString());
        String[] enOptions = request.getParameterValues("en_options[]");
        String[] uzOptions = request.getParameterValues("uz_options[]");

        Part file = request.getPart("file");
        Document document = null;
        if (file!=null && file.getSize() > 0) {

            String originalName = file.getSubmittedFileName();
            String extension = originalName.substring(originalName.lastIndexOf("."));
            String generatedName = UUID.randomUUID() + extension;
            String mimeType = file.getContentType();

            document = Document.builder()
                    .generatedFileName(generatedName)
                    .originalFileName(originalName)
                    .filePath(rootPath + "/" + generatedName)
                    .createdBy(userId) // TODO: 2/16/2023 admin id ni qo'shish kerak   qushdim
                    .build();
            file.write(generatedName);
        }

        int id = Integer.parseInt(storyId);

        TeacherService teachService = TeacherService.getInstance();
        Story story = teachService.getStoryById(id);

        story.setTitle(Objects.requireNonNullElse(title, story.getTitle()));
        story.setAuthor(Objects.requireNonNullElse(author, story.getAuthor()));
        story.setLevel(Objects.requireNonNullElse(Levels.valueOf(level), story.getLevel()));
        story.setScore(Objects.requireNonNullElse(Integer.valueOf(score), story.getScore()));
        story.setDocument(Objects.requireNonNullElse(document, story.getDocument()));

        TeacherService.getInstance().updateStory(story);
//        new StoryDAO().update(story);
//        CompletableFuture.runAsync(() -> {
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        try {
            if (enOptions != null && uzOptions != null) {
                for (int i = 0; i < enOptions.length; i++) {
                    vocabularyDAO.save(
                            Vocabulary.builder()
                                    .word(enOptions[i])
                                    .meaning(uzOptions[i])
                                    .story(story)
                                    .createdBy(userId) // TODO: 2/16/2023 admin id ni qo'shish kerak  qushdim
                                    .build()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/teacher/story/list");
    }
}

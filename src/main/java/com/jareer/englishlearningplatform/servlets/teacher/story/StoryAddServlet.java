package com.jareer.englishlearningplatform.servlets.teacher.story;


import com.jareer.englishlearningplatform.dao.VocabularyDAO;
import com.jareer.englishlearningplatform.domains.Document;
import com.jareer.englishlearningplatform.domains.Story;
import com.jareer.englishlearningplatform.domains.Vocabulary;
import com.jareer.englishlearningplatform.enums.Levels;
import com.jareer.englishlearningplatform.service.TeacherService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@WebServlet(name = "StoryAddServlet", value = "/teacher/story/add")
@MultipartConfig(location = "c:\\E-Learn\\uploads\\stories")
public class StoryAddServlet extends HttpServlet {
    public static final String projectPath = System.getProperty("user.dir");
    private static final Path rootPath = Path.of(System.getProperty("user.home"), "/E-Learn/uploads/stories");

    @Override
    public void init() throws ServletException {
        if (!Files.exists(rootPath)) {
            try {
                Files.createDirectories(rootPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Levels> levels = List.of(Levels.values());
        request.setAttribute("levels", levels);
        request.getRequestDispatcher("/views/teacher/story/story-add.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String score = request.getParameter("score");
        String level = request.getParameter("level");

        String[] enOptions = request.getParameterValues("en_options[]");
        String[] uzOptions = request.getParameterValues("uz_options[]");

        Part file = request.getPart("file");
        String originalName = file.getSubmittedFileName();
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String generatedName = UUID.randomUUID() + extension;
        String mimeType = file.getContentType();

        Document document = Document.builder()
                .generatedFileName(generatedName)
                .originalFileName(originalName)
                .filePath("uploads/files/stories/" + generatedName)
                .createdBy(1) // TODO: 2/16/2023 admin id ni qo'shish kerak
                .build();
        file.write(generatedName);

        Story story = Story.builder()
                .title(title)
                .author(author)
                .score(Integer.valueOf(score))
                .level(Levels.valueOf(level))
                .createdBy(1) // TODO: 2/16/2023 admin id ni qo'shish kerak
                .document(document)
                .build();


        TeacherService.getInstance().saveStory(story);

//        CompletableFuture.runAsync(() -> {
            VocabularyDAO vocabularyDAO = new VocabularyDAO();
            try {
                for (int i = 0; i < enOptions.length; i++) {
                    vocabularyDAO.save(
                            Vocabulary.builder()
                                    .word(enOptions[i])
                                    .meaning(uzOptions[i])
                                    .story(story)
                                    .createdBy(1) // TODO: 2/16/2023 admin id ni qo'shish kerak
                                    .build()
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
//        });
        response.sendRedirect("/teacher/story/list");
    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Part file = request.getPart("file");
////        AsyncSupplier.run(() -> {
//        try {
//            DocumentDAO documentDAO = DocumentDAO.getInstance();
//            String originalName = file.getSubmittedFileName();
//            String extension = originalName.substring(originalName.lastIndexOf("."));
//            String generatedName = UUID.randomUUID() + extension;
//            String mimeType = file.getContentType();
//            System.out.println(mimeType);
//            long size = file.getSize();
//            Document document = Document.builder()
//                    .generatedFileName(generatedName)
//                    .originalFileName(originalName)
//                    .fileSize(size)
//                    .mimeType(mimeType)
//                    .filePath("uploads/files/stories/" + generatedName)
//                    .extension(extension)
//                    .build();
//            documentDAO.save(document);
//            file.write(generatedName);
//        } catch (Exception e) {
//            // TODO: 08/02/23 redirect to error page
//            throw new RuntimeException(e);
//        }
////        });
//        response.sendRedirect("/main.jsp");
//    }


}

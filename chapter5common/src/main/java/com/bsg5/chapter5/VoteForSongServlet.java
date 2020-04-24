package com.bsg5.chapter5;


import com.bsg5.chapter3.MusicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/vote")
public class VoteForSongServlet extends HttpServlet {
    static final long serialVersionUID = 123123;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ApplicationContext context = (ApplicationContext) req.getServletContext().getAttribute("context");
        MusicService service = context.getBean(MusicService.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String artist = req.getParameter("artist");
        String song = req.getParameter("song");

        if (artist == null || song == null) {
            log("Missing data in request: requires artist and song parameters");
            resp.setStatus(500);
        } else {
            log(String.format("Voting for artist %s for song %s", artist, song));
            service.voteForSong(artist, song);
            resp.setStatus(200);
            resp.getWriter().println(objectMapper.writeValueAsString(service.getSong(artist, song)));
        }
    }
}

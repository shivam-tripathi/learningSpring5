package com.bsg5.chapter5;

import com.bsg5.chapter3.MusicService;
import com.bsg5.chapter3.model.Song;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/songs")
public class GetSongsForArtistServlet extends HttpServlet {
    static final long serialVersionUID = 123123;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ApplicationContext context = (ApplicationContext) req.getServletContext().getAttribute("context");
        MusicService musicService = context.getBean(MusicService.class);
        ObjectMapper mapper = new ObjectMapper();
        String artist = req.getParameter("artist");
        if (artist == null) {
            log("Missing data in request: Requires artist parameter");
            resp.setStatus(500);
        } else {
            ArrayList<Song> songs = (ArrayList<Song>) musicService.getSongsForArtist(artist);
            resp.setStatus(200);
            resp.getWriter().println(mapper.writeValueAsString(songs));
        }
    }
}

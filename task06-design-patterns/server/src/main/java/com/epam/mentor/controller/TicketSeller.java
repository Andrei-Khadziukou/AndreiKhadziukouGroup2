package com.epam.mentor.controller;

import com.epam.mentor.aggregator.SeatsMap;
import com.epam.mentor.domain.BusinessException;
import com.epam.mentor.ohsl.ITicketService;
import com.epam.mentor.ohsl.TicketServise;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;


public class TicketSeller extends HttpServlet {

    private ITicketService service = new TicketServise();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String row = req.getParameter("row");
        String seat = req.getParameter("seat");
        String seansId = req.getParameter("id");
        String cost = req.getParameter("cost");

        resp.setContentType("application/json");
        resp.setHeader("Cache-Control", "nocache");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        try {
            service.sellOne(seansId, new BigDecimal(cost), new Integer(row), new Integer(seat));
            out.print("ok");
        } catch (BusinessException e) {
            resp.sendError(400, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String seansId = req.getParameter("id");
        SeatsMap map = new SeatsMap(seansId);
        map.refresh();

        resp.setContentType("application/json");
        resp.setHeader("Cache-Control", "nocache");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.print(map);
    }

}

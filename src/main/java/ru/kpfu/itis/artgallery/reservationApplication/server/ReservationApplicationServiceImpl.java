//package ru.kpfu.itis.artgallery.reservationApplication.server;
//
//import com.google.gwt.user.server.rpc.RemoteServiceServlet;
//
//import ru.kpfu.itis.artgallery.reservationApplication.client.ReservationApplicationService;
//import ru.kpfu.itis.artgallery.reservationApplication.docGenerator.Generator;
//import java.util.Date;
//
//
//public class ReservationApplicationServiceImpl extends RemoteServiceServlet implements ReservationApplicationService {
//    // Implementation of sample interface method
//    public String getMessage(String msg) {
//        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
//    }
//
//
//    @Override
//    public String saveReservation(String customer, String city, Integer numberOfVisitors, Date date, String exposition, String time) {
//        Generator generator = new Generator(customer, numberOfVisitors, date, time, city, exposition);
//
//        return generator.fillData();
//    }
//}
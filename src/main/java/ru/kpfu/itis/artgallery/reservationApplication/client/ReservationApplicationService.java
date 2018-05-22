//package ru.kpfu.itis.artgallery.reservationApplication.client;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.user.client.rpc.RemoteService;
//import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
//
//import java.util.Date;
//
//@RemoteServiceRelativePath("ReservationApplicationService")
//public interface ReservationApplicationService extends RemoteService {
//    // Sample interface method of remote interface
//    String getMessage(String msg);
//    String saveReservation(String customer, String city, Integer numberOfVisitors, Date date, String exposition, String time);
//
//    class App {
//        private static ReservationApplicationServiceAsync ourInstance = GWT.create(ReservationApplicationService.class);
//
//        public static synchronized ReservationApplicationServiceAsync getInstance() {
//            return ourInstance;
//        }
//    }
//}

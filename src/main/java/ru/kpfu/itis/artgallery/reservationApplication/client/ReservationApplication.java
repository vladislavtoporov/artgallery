//package ru.kpfu.itis.artgallery.reservationApplication.client;
//
//import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.*;
//import com.google.gwt.user.datepicker.client.DateBox;
//import org.springframework.beans.factory.annotation.Autowired;
//import ru.kpfu.itis.artgallery.models.Exposition;
//import ru.kpfu.itis.artgallery.repositories.ExpositionRepository;
//
///**
// * Entry point classes define <code>onModuleLoad()</code>
// */
//public class ReservationApplication implements EntryPoint {
//
//    /**
//     * This is the entry point method.
//     */
//
//    public void onModuleLoad() {
//        Button submit = new Button("OK");
//        Label label = new Label();
//        ListBox city = new ListBox();
//        city.setItemText(0, "Казань");
//        city.setItemText(1, "Набережные Челны");
//        city.setItemText(2, "Альметьевск");
//        TextBox customer = new TextBox();
//        IntegerBox numberOfVisitors = new IntegerBox();
//        DateBox date = new DateBox();
//        ListBox exposition = new ListBox();
//        exposition.setItemText(0, "Выставка Импрессионистов");
//        exposition.setItemText(1, "Выставка авангардистов");
//        exposition.setItemText(2, "Выставка современного искусства");
//
//        ListBox time = new ListBox();
//        time.setItemText(0, "09:00");
//        time.setItemText(1, "12:00");
//        time.setItemText(2, "15:00");
//
//        submit.addClickHandler(event -> {
//            if (label.getText().equals("")) {
//                ReservationApplicationService.App.getInstance().saveReservation(
//                        customer.getText(), city.getSelectedItemText(),
//                        numberOfVisitors.getValue(),
//                        date.getValue(),
//                        exposition.getSelectedItemText(),
//                        time.getSelectedItemText(),
//                        new MyAsyncCallback(label));
//            } else {
//                label.setText("");
//            }
//        });
//
//        RootPanel.get("slot1").add(label);
//        RootPanel.get("slot2").add(customer);
//        RootPanel.get("slot3").add(numberOfVisitors);
//        RootPanel.get("slot4").add(city);
//        RootPanel.get("slot5").add(date);
//        RootPanel.get("slot6").add(exposition);
//        RootPanel.get("slot7").add(time);
//        RootPanel.get("slot8").add(submit);
//    }
//
//    private static class MyAsyncCallback implements AsyncCallback<String> {
//        private Label label;
//
//        public MyAsyncCallback(Label label) {
//            this.label = label;
//        }
//
//        public void onSuccess(String result) {
//            label.getElement().setInnerHTML(result);
//        }
//
//        public void onFailure(Throwable throwable) {
//            label.setText("Failed to receive answer from server!");
//        }
//    }
//}

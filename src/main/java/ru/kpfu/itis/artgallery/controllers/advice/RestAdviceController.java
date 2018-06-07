//package ru.kpfu.itis.artgallery.controllers.advice;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RestController;
//import ru.kpfu.itis.artgallery.forms.AjaxForm;
//import ru.kpfu.itis.artgallery.services.AuthenticationService;
//
//@ControllerAdvice(annotations = RestController.class)
//public class RestAdviceController {
//
//    public ResponseEntity<?> setError(Model model, BindingResult errors) {
//        AjaxForm result = new AjaxForm();
//        if (errors.hasErrors()) {
//            result.setMsg(errors.getAllErrors().get(0).getDefaultMessage());
//            return ResponseEntity.ok(result);
//        }
//        return null;
//    }
//}

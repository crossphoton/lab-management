package lab.management.Controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lab.management.Middlewares.JWT_Helper;
import lab.management.Services.AnnouncementService;
import lab.management.Services.TaskService;

@RestController
public class DeleteController {
    
    @DeleteMapping("/announcement/{id}")
    public String deleteAnnouncement(@PathVariable String id, @CookieValue(name = "token", defaultValue = "")String token, HttpServletResponse response){
        if(!JWT_Helper.checkTeacher(token)) {
            response.setStatus(403);
            return "NOT AUTHORIZED";
        }
        return AnnouncementService.delete(id);
    }
    
    @DeleteMapping("/task/{id}")
    public String deleteTask(@PathVariable String id, @CookieValue(name = "token", defaultValue = "")String token, HttpServletResponse response){
        if(!JWT_Helper.checkTeacher(token)) {
            response.setStatus(403);
            return "NOT AUTHORIZED";
        }
        return TaskService.delete(id);
    }

    @DeleteMapping("/task/{id}/notice/{notice}")
    public String deleteNotice(@PathVariable String task, @CookieValue(name = "token", defaultValue = "")String token, @PathVariable String notice, HttpServletResponse response){
        if(!JWT_Helper.checkTeacher(token)) {
            response.setStatus(403);
            return "NOT AUTHORIZED";
        }
        return TaskService.deleteNotice(task, notice);
    }

}

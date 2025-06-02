import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @PostMapping("/satellite")
    public void receiveSatelliteData(@RequestBody SatelliteData data){
        System.out.println(data);
    }
}

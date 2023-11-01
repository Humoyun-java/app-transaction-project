package humoyun.olimjonov.appclickhumoyun.contreoller;

import humoyun.olimjonov.appclickhumoyun.dto.P2PDto;
import humoyun.olimjonov.appclickhumoyun.dto.user.UserEditReq;
import humoyun.olimjonov.appclickhumoyun.dto.user.UsersRequestDto;
import humoyun.olimjonov.appclickhumoyun.dto.user.UuidDto;
import humoyun.olimjonov.appclickhumoyun.entity.Users;
import humoyun.olimjonov.appclickhumoyun.service.TransactionService;
import humoyun.olimjonov.appclickhumoyun.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@RestControllerAdvice
public class UserController {
    private final UsersService service;
    private final TransactionService serviceT;

    @GetMapping("/read")
    public List<Users> readAllUsers() {
        return service.readAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody UsersRequestDto dto) throws Throwable {
        return service.createUser(dto);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody UuidDto uuid) {
        return service.deleteUser(uuid);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody UserEditReq dto) throws Exception {
        return service.edit(dto);
    }

    @PostMapping("/findById")
    public Optional<?> findById(@RequestBody UuidDto dto) {
        return service.findById(dto.getUuid());
    }

    @PostMapping("/p2p")
    public ResponseEntity<String> p2p(@RequestBody P2PDto dto) throws Exception {
        return serviceT.p2p(dto.getSenderPan(), dto.getRecipientPan(), dto.getAmount());
    }

    /*@GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }*/

    @ExceptionHandler(Exception.class)
    public String string(Exception exception) {
        exception.printStackTrace();
        return exception.getMessage();
    }

}

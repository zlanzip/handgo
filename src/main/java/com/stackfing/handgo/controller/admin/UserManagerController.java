package com.stackfing.handgo.controller.admin;

import com.stackfing.handgo.entity.User;
import com.stackfing.handgo.pojo.Result;
import com.stackfing.handgo.pojo.ResultGenerator;
import com.stackfing.handgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserManagerController {

	@Autowired
	private UserService userService;


	@GetMapping("")
	public String getAllUsers() {

		return "redirect:/admin";
	}

	@GetMapping("/allUser")
	@ResponseBody
	public Result allUserListByPage(@RequestParam("page") Long page) {
		System.out.println(page);
//		return new JsonBodyUtil().send(0, "成功", 20, userService.selectAllUserByPage(page));
		return ResultGenerator.genSuccessResult(userService.selectAllUserByPage(page));
	}

	@GetMapping("/allDelUser")
	@ResponseBody
	public Result deletedUserList(@RequestParam("page") Long page) {
		System.out.println(page);
//		return new JsonBodyUtil().send(0, "成功", 20, userService.selectAllUserByPage(page));
		return ResultGenerator.genSuccessResult(userService.selectAllDeletedUserByPage(page));
	}

	//通过id查询用户
	@GetMapping("{uid}")
	@ResponseBody
	public User getUserById(@PathVariable Long uid) {
		User user = userService.selectUserById(uid);
		System.out.println(user);
		return user;
	}

	@RequestMapping("userList")
	@ResponseBody
	public List<User> getUserList() {
		List<User> list = userService.selectAllUser();
		for (User u : list) {
			System.out.println(u.toString());
		}
		return list;
	}

	@GetMapping("add")
	public  String addUser() {
		return "/admin/user-edit";
	}

	@ResponseBody
	@PostMapping("del/{uid}")
	public Result delUser(@PathVariable Long uid) {
		if (userService.deleteUserById(uid) == 1)
			return ResultGenerator.genSuccessResult();
		return ResultGenerator.genFailResult("FAILD");
	}

	@GetMapping("edit")
	public String editUser() {
		return "admin/user-edit";
	}
}

package com.sunjet.front.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sunjet.common.entity.SjLeave;
import com.sunjet.front.common.services.TestService;
import com.sunjet.front.job.service.JobService;
import com.sunjet.front.management.vo.UserVO;
import com.sunjet.front.model.AjaxResponseBody;

@Controller
// @RequestMapping("/user")
public class TestController {
	@Autowired
	private TestService testService;
	@Autowired
	private JobService jobService;

	@PostMapping("/api/search")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ResponseBody
	public ResponseEntity<?> getSearchResultViaAjax(@RequestParam MultiValueMap<String, String> data) {

		AjaxResponseBody result = new AjaxResponseBody();
		return ResponseEntity.ok(result);

	}

	@PostMapping("/yourMapping1")
	public ModelAndView insertSong(@Valid UserVO song, BindingResult result) {
		// @Valid註解啟動後臺校驗,
		ModelAndView modelAndView = new ModelAndView();

		// System.out.println("歌手名稱:"+ song.getSinger());

		if (result.hasErrors()) {
			modelAndView.addObject("hintMessage", "出錯啦！");
		} else {
			// String songName = song.getSongName();
			// Song dataSong = songService.findSongByName(songName);
			// if (dataSong != null) {
			// modelAndView.addObject("hintMessage", "資料庫已有該條記錄！");
			// } else {
			modelAndView.addObject("hintMessage", "提交成功！");
			// songService.insertSong(song);
			// }

		}

		modelAndView.setViewName("/leave/leave2");

		return modelAndView;

	}

	@RequestMapping("/blog/ajaxTest")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String test(Model model) {
		System.out.println("ajaxTest");
		List<String> list = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			list.add("hello" + i);
		}
		model.addAttribute("aa", list);
		return "leave/leave2::div1";
	}

	@PostMapping(value = "/yourMapping")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String ajaxCtrlExample(@ModelAttribute("userVO") UserVO userVO, Model model) {
		UserVO u = new UserVO();
		u.setAccount("王八蛋");
		model.addAttribute("userVO", u);
		return "leave/leave2::yourFragment";
	}

	@RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ResponseBody
	public String AddItem(@RequestBody UserVO userInfo) {
		return "sucess";
	}

	@RequestMapping(value = "/saveUserInfo1", method = RequestMethod.POST)
	public String saveUserInfo(@Valid @ModelAttribute("userInfo") UserVO userInfo, BindingResult result, Model model) {
		// if any errors, re-render the user info edit form
		model.addAttribute("userVO", userInfo);
		if (result.hasErrors()) {
			return "leave/leave2 :: info-form";
		}
		// let the service layer handle the saving of the validated form fields
		// userServices.saveUserInfo(userInfo);
		return "leave/leave2 :: info-success";
	}

	@GetMapping("/")
	public String index(Model model) {
		// List<SjMenu> sjMenus = testService.getAllMenu();
		// model.addAttribute("sjMenus", sjMenus);
		return "Content1";
	}

	@GetMapping("/index")
	public String index1(Model model) {
		// List<SjMenu> sjMenus = testService.getAllMenu();
		// model.addAttribute("sjMenus", sjMenus);
		return "index";
	}

	@GetMapping("/list")
	@PreAuthorize("hasAuthority('CREATE')")
	public String list(Model model) {
		List<SjLeave> sjLeaves = testService.getAllLeave();
		model.addAttribute("sjLeave", sjLeaves);
		return "list";
	}

	@RequestMapping("/alculateCalendar")
	public String tables(Model model) {
		LocalDate start = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
		LocalDate end = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
		jobService.calculateCalendar(start, end);
		return "/list";
	}

	// @GetMapping("/charts")
	// public String charts() {
	// System.out.println("55555ddddddddddddd");
	// return "charts";
	// }

	// @RequestMapping(value = "/index", method = RequestMethod.GET)
	// public String test() {
	// try {
	// // testService.loadUserByUsername("username");
	// } catch (Exception e) {
	// System.out.println(e.toString());
	// }
	// return "index";
	// }
	//
	// @RequestMapping(value = "/hello", method = RequestMethod.POST)
	// public String sayHello(@RequestParam("name") String name, Model model) {
	// model.addAttribute("name", name);
	// return "hello";
	// }
	//
	// @RequestMapping("/login-error")
	// public String loginError() {
	// return "login-error";
	//
	// }
	//
	// @RequestMapping("/thymeleaf")
	// public String helloThymeleaf(Model model){
	// model.addAttribute("hello","hello Thymeleaf！");
	// return "NewFile";
	// }
}

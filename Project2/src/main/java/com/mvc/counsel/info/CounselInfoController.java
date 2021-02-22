package com.mvc.counsel.info;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.counsel.info.exception.CounselBlankException;
import com.mvc.counsel.info.exception.DeleteException;
import com.mvc.counsel.info.exception.LockDeniedException;
import com.mvc.counsel.info.exception.UserURLDeniedException;
import com.mvc.counsel.info.handler.ExceptionHandling;
import com.mvc.counsel.info.service.CounselInfoService;
import com.mvc.counsel.info.vo.CounselInfoVO;
import com.mvc.counsel.info.vo.CounselLockVO;

@Controller
@RequestMapping("/counsel/*")
public class CounselInfoController {

	final static Logger logger = LoggerFactory.getLogger(ExceptionHandling.class);

	@Autowired
	private CounselInfoService counselInfoService;

	/*
	 * 목록 조회
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView CounselInfoList() {
		ModelAndView mav = new ModelAndView("counsel/counselList");
		List<CounselInfoVO> counselList = counselInfoService.counselInfoList();
		mav.addObject("counselList", counselList);
		return mav;
	}

	/*
	 * 글쓰기 form으로 이동
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String CounselInfoCreateForm() {
		return "counsel/counselCreateForm";
	}

	/*
	 * 게시물 작성
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String CounselInfoCreate(CounselInfoVO counselInfoVO, CounselLockVO counselLockVO, HttpServletRequest requset)
			throws CounselBlankException {
		if (counselInfoVO.getCounsel_title() == "" || counselInfoVO.getCounsel_title() == null)
			throw new CounselBlankException("제목을 입력하세요");
		String id = requset.getUserPrincipal().getName();
		counselInfoService.counselInfoCreate(counselInfoVO, counselLockVO, id);
		return "redirect:list";
	}

	/*
	 * 수정 form으로 이동 - 유저확인필요
	 */
	@RequestMapping(value = "/update/{seq_counsel}", method = RequestMethod.GET)
	public ModelAndView CounselInfoUpdateForm(@PathVariable("seq_counsel") int seq_counsel, HttpServletRequest request)
			throws UserURLDeniedException {
		ModelAndView mav = new ModelAndView();
		CounselInfoVO vo = counselInfoService.counselInfoDetail(seq_counsel);
		if (!counselInfoService.checkAuth(vo.getCounsel_userId(), request)) {
			throw new UserURLDeniedException("사용자가 다릅니다");
		}

		mav.addObject("counselInfo", vo);
		mav.setViewName("counsel/counselUpdateForm");

		return mav;
	}

	/*
	 * 게시물 (수정)업데이트 - 유저확인필요
	 */
	@RequestMapping(value = "/update/{seq_counsel}", method = RequestMethod.POST)
	public String CounselInfoUpdate(CounselInfoVO counselInfo, @PathVariable("seq_counsel") int seq_counsel,
			HttpServletRequest request) throws UserURLDeniedException {
		counselInfo.setSeq_counsel(seq_counsel);
		counselInfoService.counselInfoUpdate(counselInfo, request);
		return "redirect:/counsel/list";
	}

	/*
	 * 게시물 읽기. - 비밀글확인필요
	 */
	@RequestMapping(value = "/{seq_counsel}", method = RequestMethod.POST)
	public ModelAndView CounselInfoRead(@PathVariable("seq_counsel") int seq_counsel, String lockChk)
			throws UserURLDeniedException {
		ModelAndView mav = new ModelAndView("counsel/counselDetailForm");
		CounselInfoVO vo = counselInfoService.counselInfoDetail(seq_counsel, lockChk);
		mav.addObject("counselInfo", vo);
		return mav;
	}

	/*
	 * 게시물 삭제 - 유저확인필요
	 */
	@RequestMapping(value = "/delete/{seq_counsel}", method = RequestMethod.GET, produces = "application/text; charset=utf8")
	@ResponseBody
	public void CounselInfoDelete(@RequestBody @PathVariable("seq_counsel") int seq_counsel, HttpServletRequest request)
			throws DeleteException, UserURLDeniedException {
		if (counselInfoService.counselInfoDelete(seq_counsel, request) == 0)
			throw new DeleteException("삭제되지 않습니다.");
	}

	/*
	 * 게시물 검색.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ModelAndView SearchData(String search_option, String search_query) {
		ModelAndView mav = new ModelAndView("counsel/counselList");

		List<CounselInfoVO> list = counselInfoService.searchList(search_option, search_query);
		mav.addObject("counselList", list);
		mav.addObject("search_text", search_query);
		return mav;
	}

	/*
	 * 비밀글 체크. 비밀번호가 틀렸을 때 LockDeniedException.
	 */
	@RequestMapping(value = "/accessLock", method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public void accessLock(CounselLockVO counselLockVO) throws LockDeniedException {
		counselInfoService.accessLock(counselLockVO);
	}

}

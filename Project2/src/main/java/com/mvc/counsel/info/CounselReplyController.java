package com.mvc.counsel.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.counsel.info.service.CounselReplyService;
import com.mvc.counsel.info.vo.CounselInfoVO;
import com.mvc.counsel.info.vo.CounselReplyVO;

@Controller
@RequestMapping("/reply")
public class CounselReplyController {
	@Autowired
	private CounselReplyService counselReplyService;
	
	/*
	 * 댓글 생성
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public void replyCreate(CounselReplyVO replyVO, CounselInfoVO counselInfoVO) {
		replyVO.setCounselInfoVO(counselInfoVO);
		counselReplyService.replyCreate(replyVO);
	}
	
	/*
	 * 댓글 목록.
	 */
	@RequestMapping(value="/list", produces="application/json; charset=utf8")
	@ResponseBody
	public String replyList(@RequestBody int seq_counsel){
		ArrayList<Map<String, Object>> counselReplylist = new ArrayList<Map<String, Object>>();
		Map<String, Object> map;
		
		List<CounselReplyVO> list =  counselReplyService.replyList(seq_counsel);

		for(CounselReplyVO vo : list) {
			map = new HashMap<String, Object>();
			map.put("reply_content", vo.getReply_content());
			map.put("reply_date", vo.getReply_date());
			counselReplylist.add(map);
		}
	
		JSONArray jsonArray = new JSONArray(counselReplylist);
		return jsonArray.toString();
	}
	
	
}

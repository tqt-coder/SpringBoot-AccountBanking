package tranquoctuan.AcountBanking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tranquoctuan.AcountBanking.bean.AccountBean;
import tranquoctuan.AcountBanking.bean.AmountBean;
import tranquoctuan.AcountBanking.dao.BankDAO;

@Controller
public class BankController {
	@Autowired
	private BankDAO bankDao;
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public String showList(Model model) {
		List<AccountBean> ls = bankDao.listAccount();
		model.addAttribute("ls",ls);
		return "list";
	}
	@RequestMapping(value= {"/sendMoney"}, method= RequestMethod.GET)
	public String SendMoney(Model model) {
		AmountBean amountObject = new AmountBean();
		List<AccountBean> ls = bankDao.listAccount();
		model.addAttribute("ls",ls);
		model.addAttribute("amountObject",amountObject);
		return "sendMoney";
	}
	
	@RequestMapping(value= {"/sendMoney"}, method= RequestMethod.POST)
	public String PostMoney(Model model, @ModelAttribute("amountObject") AmountBean amountObj) {
		System.out.println(amountObj.getAmount());
		if(amountObj.getIdSend() > 0 && amountObj.getIdReceive() > 0 && amountObj.getAmount() > 0) {
			
			System.out.println("Nhập thành công");
		}
		return "sendMoney";
	}
}

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
import tranquoctuan.AcountBanking.exception.BankTransactionException;

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
		amountObject.setIdReceive(1);
		amountObject.setIdSend(2);
		amountObject.setAmount(100d);
		List<AccountBean> ls = bankDao.listAccount();
		model.addAttribute("ls",ls);
		model.addAttribute("amountObject",amountObject);
		return "sendMoney";
	}
	
	@RequestMapping(value= {"/sendMoney"}, method= RequestMethod.POST)
	public String PostMoney(Model model,   @ModelAttribute("amountObject") AmountBean  amountObject) {
		System.out.println(amountObject.getAmount());
		if(amountObject.getIdSend() > 0 && amountObject.getIdReceive() > 0 && amountObject.getAmount() > 0) {
			try {
				bankDao.sendMoney(amountObject.getIdSend(), amountObject.getIdReceive(), amountObject.getAmount());
			} catch (BankTransactionException e) {
				// TODO Auto-generated catch block
				model.addAttribute("errorMessage","Error "+e.getMessage());
			}
			
		}else {
			model.addAttribute("errorMessage","The input data is required");
			return "sendMoney";
		}
		return "redirect:/";
	}
}

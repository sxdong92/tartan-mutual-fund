package edu.cmu.cs.webapp.tartan.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import edu.cmu.cs.webapp.tartan.formbean.TransitionDayForm;
import edu.cmu.cs.webapp.tartan.model.FundDAO;
import edu.cmu.cs.webapp.tartan.model.FundPriceHistoryDAO;
import edu.cmu.cs.webapp.tartan.model.Model;

public class ResearchAction extends Action {
	private FormBeanFactory<TransitionDayForm> formBeanFactory = FormBeanFactory.getInstance(TransitionDayForm.class);
	
	private FundDAO fundDAO;
	private FundPriceHistoryDAO fundPriceHistoryDAO;
	
	public ResearchAction(Model model) {
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}

	public String getName() { return "research.do"; }
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
        	
        	
        	
        } catch (RollbackException e) {
			
			errors.add(e.getMessage());
			//not sure
			return "research.jsp";
			
		} catch (FormBeanException e) {
			
        	errors.add(e.getMessage());
        	//not sure
        	return "research.jsp";
        	
        } catch (ParseException e) {
        	
			e.printStackTrace();
			//not sure
        	return "research.jsp";
		}
        
        
        //everything was done
        errors.add("Success!");
        
        return "transition-day.jsp";
        
	}
}

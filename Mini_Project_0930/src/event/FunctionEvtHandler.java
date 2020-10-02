package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.AddController;
import controller.Controller;
import controller.DeleteController;
import controller.ModifyController;
import controller.SearchController;
import controller.ShowController;
import resource.R;
import view.ShowView;

public class FunctionEvtHandler implements ActionListener, R {

	ShowView target;
	
	public FunctionEvtHandler(ShowView target) {
	
		Controller addCtrl = new AddController();
		Controller searchCtrl = new SearchController();
		Controller modifyCtrl = new ModifyController();
		Controller deleteCtrl = new DeleteController();
		
		ctrlMap.put(funcBtnArray[0], addCtrl);
		ctrlMap.put(funcBtnArray[1], searchCtrl);
		ctrlMap.put(funcBtnArray[2], modifyCtrl);
		ctrlMap.put(funcBtnArray[3], deleteCtrl);
			
		this.target = target;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		Controller ctrl = ctrlMap.get(btn);
		ctrl.service();

	}

}

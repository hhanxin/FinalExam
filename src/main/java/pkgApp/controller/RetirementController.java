package pkgApp.controller;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {

		
	private RetirementApp mainApp = null;

	@FXML
	private TextField text1;
	
	@FXML
	private TextField text2;
	
	@FXML
	private TextField text3;
	
	@FXML
	private TextField text4;
	
	@FXML
	private TextField text5;
	
	@FXML
	private TextField text6;
	
	@FXML
	private TextField text7;
	
	@FXML
	private TextField text8;
	
	@FXML
	private Button btn_clear;

	@FXML 
	private Button btn_calculate;
	

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {	
		
		// numeric only
		// years to work 
		text2.textProperty().addListener(new ChangeListener<String>() {
			@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		        	text2.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		        // no longer than 100 year
		        if(text2.getText().length() > 2){
		        	text2.setText(text2.getText().substring(0,2));
		        }
		    }
		});
		
		// annual return when in investment mode 
		text3.textProperty().addListener(new ChangeListener<String>() {
			@Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        
				if(newValue == null || newValue.length() == 0) return ;
				if (!newValue.matches("\\d+(\\.[\\d]*)?")) {
		        	text3.setText(oldValue);
		        }
		        String st3 = text3.getText();
		        Double dt;
		        if(st3 != null && st3.length() > 0){
		        	dt = Double.valueOf(st3);
		        	if(dt > 20){
		        		text3.setText(st3.substring(0, st3.length()-1));
		        	}
		        	st3 = text3.getText();
		        	if(st3.contains(".")) {
		        		String[] tmpA = st3.split("\\.");
		        		if(tmpA.length >1 && tmpA[1].length() >2){
		        			text3.setText(st3.substring(0, st3.length()-1));
		        		}
		        	}
		        }
		    }
		});
		
		// years retired
		text5.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!newValue.matches("\\d*")) {
					text5.setText(newValue.replaceAll("[^\\d]", ""));
				}
				// no longer than 100 year
				if (text5.getText().length() > 2) {
					text5.setText(text5.getText().substring(0, 2));
				}
			}
		});
		
		// annual return when in payback mode
		text6.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (newValue == null || newValue.length() == 0)
					return;
				if (!newValue.matches("\\d+(\\.[\\d]*)?")) {
					text6.setText(oldValue);
				}
//				if (newValue.equals("0")) {
//					text6.setText("");
//				}
				String st6 = text6.getText();
				Double dt;
				if (st6 != null && st6.length() > 0) {
					dt = Double.valueOf(st6);
					if (dt > 3) {
						text6.setText(st6.substring(0, st6.length() - 1));
					}
					st6 = text6.getText();
					if (st6.contains(".")) {
						String[] tmpA = st6.split("\\.");
						if (tmpA.length > 1 && tmpA[1].length() > 2) {
							text6.setText(st6.substring(0, st6.length() - 1));
						}
					}
				}
			}
		});
		
		// required income
		text7.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (newValue == null || newValue.length() == 0)
					return;
				if (!newValue.matches("\\d+(\\.[\\d]*)?")) {
					text7.setText(oldValue);
				}
			}
		});

		// Monthly SSI
		text8.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				if (newValue == null || newValue.length() == 0)
					return;
				if (!newValue.matches("\\d+(\\.[\\d]*)?")) {
					text8.setText(oldValue);
				}
			}
		});
		
	}
	
	@FXML
	public void btnClear(ActionEvent event) {
//		TODO: Clear all the text inputs
		
		text1.setText("0");
		text2.setText("");
		text3.setText("");
		text4.setText("0");
		text5.setText("");
		text6.setText("");
		text7.setText("");
		text8.setText("");
	}
	
	@FXML
	public void btnCalculate(ActionEvent event) {
		//System.out.println(text1.getText().toString());
		
		// check the text if ok
		// years to work
		if(text2.getText() == null || text2.getText().length() < 1){
			alertMessage("Years to Work is Empty", "Years to Work should in 0~100");
		}
		// annual return(Working)
		else if (text3.getText() == null || text3.getText().length() < 1) {
			alertMessage("Annual Return(Working) is Empty", "Annual Return should in 0~20%");
		}
		// years retired
		else if(text5.getText() == null || text5.getText().length() <1){
			alertMessage("Years Retired is Empty", "Years retired should in 0~100");
		}
		// annual return(Retired) 
		else if (text6.getText() == null || text6.getText().length() < 1) {
			alertMessage("Annual Return(Retired) is Empty", "Annual Return should in 0~3%");
		}
		// Required Income
		else if (text7.getText() == null || text7.getText().length() < 1) {
			alertMessage("Required Income is Empty", "You should input Required Income");
		}
		// Monthly SSI
		else if (text8.getText() == null || text8.getText().length() < 1) {
			alertMessage("Monthly SSI is Empty", "You Should input Monthly SSI");
		}
		
		else {
			// TODO: Call AmountToSave and TotalAmountSaved and populate
			DecimalFormat df = new DecimalFormat("###,##0.00");

			int iYearsToWork = Integer.valueOf(text2.getText());
			double dAnnualReturnWorking = Double.valueOf(text3.getText()) / 100;
			int iYearsRetired = Integer.valueOf(text5.getText());
			double dAnnualReturnRetired = Double.valueOf(text6.getText()) / 100;
			double dRequiredIncome = Double.valueOf(text7.getText());
			double dMonthlySSI = Double.valueOf(text8.getText());
			Retirement retirement = new Retirement(iYearsToWork, dAnnualReturnWorking, iYearsRetired,
					dAnnualReturnRetired, dRequiredIncome, dMonthlySSI);

			text4.setText("(" + df.format(-retirement.TotalAmountSaved()) + ")");
			text1.setText(df.format(retirement.AmountToSave()));
		}
	}
	
	
	// alert message 
	public void alertMessage(String info, String help){
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("WARNING");
		alert.setHeaderText(info);
		alert.setContentText(help);
		alert.showAndWait();
	}
	
	
}

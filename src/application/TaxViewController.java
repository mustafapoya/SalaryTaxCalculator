package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TaxViewController {
	@FXML
	private TextField txtSalary;
	@FXML
	private Button btnCalculate;
	@FXML
	private TextField txtTax;
	@FXML
	private TextField txtNetSalary;

	@FXML
	private TextArea txtDetails;

	@FXML
	private void initialize() {

		txtSalary.setText("0.0");
		txtSalary.textProperty().addListener(new OnlyNumberChangeListener(txtSalary));

		btnCalculate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					double salary = txtSalary.getText().length() > 0 ? Double.parseDouble(txtSalary.getText()) : 0.0;
					double tax = calculateTax(salary);
					txtTax.setText(String.valueOf(tax));
					txtNetSalary.setText(String.valueOf(salary - tax));
				} catch (Exception ex) {
					ex.printStackTrace();
					txtTax.setText("Invalid Data");
				}
			}
		});
	}

	public static double calculateTax(double salary) {
		double tax = 0;

		if (salary <= 5000) {
			tax = 0;
		}

		if (salary > 5000 && salary <= 12500) {
			salary = salary - 5000;
			tax = (salary * 2) / 100.0;
		}

		if (salary > 12500 && salary < 100000) {
			salary = salary - 12500;
			tax = (salary * 10) / 100.0;
			tax += 150;
		}

		if (salary >= 100000) {
			double salaryOver100 = salary - 100000;
			double tax1 = (salaryOver100 * 20) / 100.0;

			//
			double salaryUnder100 = 100000;
			salaryUnder100 = salaryUnder100 - 12500;
			double tax2 = (salaryUnder100 * 10) / 100.0;
			tax2 += 150;

			tax = tax1 + tax2;
		}

		return tax;
	}
}

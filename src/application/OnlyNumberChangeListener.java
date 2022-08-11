package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class OnlyNumberChangeListener implements ChangeListener<String> {
	private TextField txtInput;

	public OnlyNumberChangeListener(TextField txtInput) {
		this.txtInput = txtInput;
	}

	@Override
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if (!newValue.matches("\\d*")) {
			txtInput.setText(newValue.replaceAll("[^\\d]", ""));
		}
	}
}

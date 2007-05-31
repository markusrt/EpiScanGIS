package de.meningococcus.episcangis.map;

public class ArbitraryValueParameter extends ParameterComposite {
	private String value;

	public ArbitraryValueParameter(String uniqueName) {
		this(uniqueName, "");
	}

	public ArbitraryValueParameter(String uniqueName, String longTitle) {
		super(uniqueName, longTitle);
	}

	public void selectValue(String selectedValue, boolean isSelected)
			throws InvalidParameterValueException {
		value = selectedValue;
	}

	public String getValue() {
		return value;
	}
}

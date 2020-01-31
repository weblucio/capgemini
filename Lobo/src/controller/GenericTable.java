package controller;

import java.util.ArrayList;
import java.util.List;

public class GenericTable {

	String ColumnName="";
	String ValueColumn="";
	
	
	public String getColumnName() {
		return ColumnName;
	}
	public GenericTable setColumnName(String columnName) {
		ColumnName = columnName;
		return this;
	}
	public String getValueColumn() {
		return ValueColumn;
	}
	public GenericTable setValueColumn(String valueColumn) {
		ValueColumn = valueColumn;
		return this;
	}
	
	
	
	
}
